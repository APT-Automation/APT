package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.dom4j.DocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;


public class ManagePostcode_Helper extends DriverHelper{
	
	public ManagePostcode_Helper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	private static Scanner s;
	
	WebElement ChooseCustomer_Select, Next_Button, CreateOrderService_Text, Wildcard, Search_Field, EmergencyAreaID_Text;
	XMLReader xml = new XMLReader("src\\com\\colt\\qa\\pagerepository\\Manage_Postcode.xml");

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
	
	public void selectColtnetwork (String application) throws InterruptedException, DocumentException
	{
		
		waitforPagetobeenable();
		
		
			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageColtNetworkLink")));
			Thread.sleep(3000);
			Log.info("Mouse hovered on Manage Colt Network ");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Mouse hovered on Manage Colt Network");
			
			Thread.sleep(2000);
			boolean isDisplayed = getwebelement(xml.getlocator("//locators/" + application + "/ManagePostcode")).isDisplayed();
			sa.assertTrue(isDisplayed,"Manage Postcode is not displayed ");
			Log.info("Manage Postcode link verified");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Manage Postcode link verified");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ManagePostcode")));
			Thread.sleep(2000);
			Log.info("=== Clicked on Manage post code link ===");
			Log.info("Manage Postcode link clicked");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Manage Postcode link");


			Log.info("=== Manage Postcode for IMS platform Navigated ===");
	}
	
	public void verifyManagePostcodepage (String application) throws InterruptedException, DocumentException
	{
		
		String[] CountryList= {"Belgium","Denmark","France","Germany","Portugal","Spain","Sweden","Switzerland"};
		Log.info("CountryLists");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying list of countries");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Country names displaying are: ");
		
		//Verifying list of Countries
		//try {
		List<WebElement> CountryPresent = getwebelements(xml.getlocator("//locators/" + application + "/fetchListofCountryNames"));
		for (WebElement Country: CountryPresent ) {
			boolean match = false;
			Log.info("Country list displaying from application:"  +Country.getText());
			

			
			for (int i=0; i < CountryList.length;i++) {
				if (Country.getText().equals(CountryList[i]))
				{
					match = true;
					Log.info("Country name : " + Country.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, Country.getText());
				}
								
			}
			sa.assertTrue(match);
		}
		
		
		}
//		catch(Exception e)
//		{
//			Log.info("Country lists got mismatched");
//			ExtentTestManager.getTest().log(LogStatus.FAIL, "Country lists are mismatching");
//		}
		
//		}
	
	public void verifyManagePostcodeinternal (String application, String Countrylist) throws InterruptedException, DocumentException
	{
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields under 'Manage Postcode' page");
		if (Countrylist.equals("Belgium"))
		{
		Thread.sleep(5000);
		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/BelMngPostcode")));
		Log.info("Clicked on Belgium Manage Postcode");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on Belgium Manage Postcode");
		
		
		compareText(application, "You can use * as wildcard","WildcardText","You can use * as wildcard", xml);
		
		compareText(application, "Emergency Area ID","EmergencyAreaID","Emergency Area ID", xml);
		
		compareText(application, "NT Service Area","NTServiceArea","NT Service Area", xml);
		
		compareText(application, "Emergency Area (Subcom)","EmergencyAreaSubcom","Emergency Area (Subcom)", xml);
		
		compareText(application, "SubArea1 (Province)","SubAreaProvince","SubArea1 (Province)", xml);
		
		compareText(application, "SubArea 1-ID (SubArea1-ID)","SubArea1-ID","SubArea 1-ID (SubArea1-ID)", xml);
		
		compareText(application, "SubArea2 (Community)","SubArea2","SubArea2 (Community)", xml);
		
		compareText(application, "SubArea 2-ID (SubArea2-ID)","SubArea2-ID","SubArea 2-ID (SubArea2-ID)", xml);
		
		compareText(application, "SubArea 3 (b)","SubArea3","SubArea 3 (b)", xml);
		
		compareText(application, "SubArea 3-ID (Zipcode)","SubArea3-ID","SubArea 3-ID (Zipcode)", xml);
		
		compareText(application, "Last Update","Lastupdate","Last Update", xml);
		
        compareText(application, "Add Postcode","Addpostcode","Add Postcode", xml);
		
		compareText(application, "Upload Update File","Uploadupdatefile","Upload Update File", xml);
		
		compareText(application, "View History","ViewHistory","View History", xml);
		
        compareText(application, "Add Emergency Numbers","AddEmergencyNumber","Add Emergency Numbers", xml);
		
		compareText(application, "Synchronize All Postcodes","SynchronizeAllpostcodes","Synchronize All Postcodes", xml);
		
		compareText(application, "Download NT Service Area","DownloadNt","Download NT Service Area", xml);
		
		compareText(application, "Upload NT Service Area","UploadNt","Upload NT Service Area", xml);
		
		}
		
		else if(Countrylist.equals("Denmark"))
		{
			
		
			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/DenMngPostcode")));
			Log.info("Clicked on Denmark Manage Postcode");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on Denmark Manage Postcode");
			Log.info("Clicked on Denmark Manage Postcode");

			Thread.sleep(5000);
			
			compareText(application, "You can use * as wildcard","WildcardText","You can use * as wildcard", xml);
			
			compareText(application, "Emergency Area ID","EmergencyAreaID","Emergency Area ID", xml);
			
			compareText(application, "NT Service Area","NTServiceArea","NT Service Area", xml);
			
			compareText(application, "Emergency Area(Kommune)","EmergencyArea_Kommune","Emergency Area(Kommune)", xml);
			
			compareText(application, "SubArea1(SubArea1)","SubArea1Den","SubArea1(SubArea1)", xml);
			
			compareText(application, "SubArea1-ID(SubArea1-ID)","SubArea1-ID_Denmark","SubArea1-ID(SubArea1-ID)", xml);
			
			compareText(application, "SubArea2-ID(SubArea2-ID)","SubArea2-ID_Denmark","SubArea2-ID(SubArea2-ID)", xml);
			
			compareText(application, "SubArea2(SubArea2)","SubArea2Den","SubArea2(SubArea2)", xml);
			
			compareText(application, "SubArea3(SubArea3)","SubArea3Den","SubArea3(SubArea3)", xml);

			compareText(application, "SubArea3-ID(SubArea3-ID)","SubArea3DenArea","SubArea3-ID(SubArea3-ID)", xml);
			
			compareText(application, "Last Update","Lastupdate","Last Update", xml);
			
	        compareText(application, "Add Postcode","Addpostcode","Add Postcode", xml);
			
			compareText(application, "Upload Update File","Uploadupdatefile","Upload Update File", xml);
			
			compareText(application, "View History","ViewHistory","View History", xml);
			
	        compareText(application, "Add Emergency Numbers","AddEmergencyNumber","Add Emergency Numbers", xml);
			
			compareText(application, "Synchronize All Postcodes","SynchronizeAllpostcodes","Synchronize All Postcodes", xml);
			
			compareText(application, "Download NT Service Area","DownloadNt","Download NT Service Area", xml);
			
			compareText(application, "Upload NT Service Area","UploadNt","Upload NT Service Area", xml);
			
		}

		else if(Countrylist.equals("France"))
		{
			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/FraMngPostcode")));
			Log.info("Clicked on France Manage Postcode");
			Log.info("Clicked on France Manage Postcode");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on France Manage Postcode");


			Thread.sleep(5000);
			
            compareText(application, "You can use * as wildcard","WildcardText","You can use * as wildcard", xml);
			
			compareText(application, "Emergency Area ID","EmergencyAreaID","Emergency Area ID", xml);
			
			compareText(application, "NT Service Area","NTServiceArea","NT Service Area", xml);
			
            compareText(application, "Emergency","EmergencyAreaCityArea","Emergency", xml);
            
            compareText(application, "SubArea1(Department)","SubArea1Fra","SubArea1(Department)", xml);

            compareText(application, "SubArea2(SubArea2)","SubArea2Fra","SubArea2(SubArea2)", xml);
			
            compareText(application, "SubArea1-ID(Department-ID)","Department_ID","SubArea1-ID(Department-ID)", xml);
            			
            compareText(application, "SubArea2-ID(SubArea2-ID)","SubArea2-ID_France","SubArea2-ID(SubArea2-ID)", xml);
			
			compareText(application, "SubArea3(SubArea3)","SubArea3Den","SubArea3(SubArea3)", xml);

			compareText(application, "SubArea3-ID(SubArea3-ID)","SubArea3DenArea","SubArea3-ID(SubArea3-ID)", xml);
			
			compareText(application, "Last Update","Lastupdate","Last Update", xml);
			
	        compareText(application, "Add Postcode","Addpostcode","Add Postcode", xml);
			
			compareText(application, "Upload Update File","Uploadupdatefile","Upload Update File", xml);
			
			compareText(application, "View History","ViewHistory","View History", xml);
			
	        compareText(application, "Add Emergency Numbers","AddEmergencyNumber","Add Emergency Numbers", xml);
			
			compareText(application, "Synchronize All Postcodes","SynchronizeAllpostcodes","Synchronize All Postcodes", xml);
			
			compareText(application, "Download NT Service Area","DownloadNt","Download NT Service Area", xml);
			
			compareText(application, "Upload NT Service Area","UploadNt","Upload NT Service Area", xml);
			
			
			
			
		}
		
		else if(Countrylist.equals("Germany"))
		{
			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/GerMngPostcode")));
			Log.info("Clicked on Germany Manage Postcode");
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on Germany Manage Postcode");
			Log.info("Clicked on France Manage Postcode");

			Thread.sleep(5000);    
			
            compareText(application, "You can use * as wildcard","WildcardText","You can use * as wildcard", xml);
			
			compareText(application, "Emergency Area ID","EmergencyAreaID","Emergency Area ID", xml);
			
			compareText(application, "NT Service Area","NTServiceArea","NT Service Area", xml);
			
			compareText(application, "Emergency Area(Local Area Name (U_ON_NAME))","EmergencyAreaLocal","Emergency Area(Local Area Name (U_ON_NAME))", xml);
			
			compareText(application, "SubArea1(SubArea1)","SubArea1Ger","SubArea1(SubArea1)", xml);
			
			compareText(application, "SubArea1-ID(SubArea1-ID)","SubArea1-ID_Germany","SubArea1-ID(SubArea1-ID)", xml);

			compareText(application, "SubArea2(SubArea2)","SubArea2Fra","SubArea2(SubArea2)", xml);
			
            compareText(application, "SubArea2-ID(SubArea2-ID)","SubArea2-ID_Germany","SubArea2-ID(SubArea2-ID)", xml);
            
			compareText(application, "SubArea3(SubArea3)","SubArea3Den","SubArea3(SubArea3)", xml);
			
			compareText(application, "SubArea3-ID(SubArea3-ID)","SubArea3DenArea","SubArea3-ID(SubArea3-ID)", xml);
			
            compareText(application, "Last Update","Lastupdate","Last Update", xml);
			
	        compareText(application, "Add Postcode","Addpostcode","Add Postcode", xml);
			
			compareText(application, "Upload Update File","Uploadupdatefile","Upload Update File", xml);
			
			compareText(application, "View History","ViewHistory","View History", xml);
			
	        compareText(application, "Add Emergency Numbers","AddEmergencyNumber","Add Emergency Numbers", xml);
			
			compareText(application, "Synchronize All Postcodes","SynchronizeAllpostcodes","Synchronize All Postcodes", xml);
			
			compareText(application, "Download NT Service Area","DownloadNt","Download NT Service Area", xml);
			
			compareText(application, "Upload NT Service Area","UploadNt","Upload NT Service Area", xml);			
			

		}

		
		
		else if (Countrylist.equals("Portugal"))
		{
			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/PorMngPostcode")));
			Log.info("Clicked on Portugal Manage Postcode");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on Portugal Manage Postcode");
			Log.info("Clicked on Portugal Manage  Postcode");

			Thread.sleep(5000);
			
			    compareText(application, "You can use * as wildcard","WildcardText","You can use * as wildcard", xml);
				
				compareText(application, "Emergency Area ID","EmergencyAreaID","Emergency Area ID", xml);
				
				compareText(application, "NT Service Area","NTServiceArea","NT Service Area", xml);
				
				compareText(application, "Emergency Area(Province)","EmergencyAreaProvince","Emergency Area(Province)", xml);
				
				compareText(application, "SubArea1(SubArea1)","SubArea1Ger","SubArea1(SubArea1)", xml);
				
				compareText(application, "SubArea1-ID(SubArea1-ID)","SubArea1-ID_Portugal","SubArea1-ID(SubArea1-ID)", xml);

				compareText(application, "SubArea2(SubArea2)","SubArea2Fra","SubArea2(SubArea2)", xml);
				
	            compareText(application, "SubArea2-ID(SubArea2-ID)","SubArea2-ID_Portugal","SubArea2-ID(SubArea2-ID)", xml);
	            
				compareText(application, "SubArea3(SubArea3)","SubArea3Den","SubArea3(SubArea3)", xml);
				
				compareText(application, "SubArea3-ID(SubArea3-ID)","SubArea3DenArea","SubArea3-ID(SubArea3-ID)", xml);
				
				compareText(application, "Last Update","Lastupdate","Last Update", xml);
				
		        compareText(application, "Add Postcode","Addpostcode","Add Postcode", xml);
				
				compareText(application, "Upload Update File","Uploadupdatefile","Upload Update File", xml);
				
				compareText(application, "View History","ViewHistory","View History", xml);
				
		        compareText(application, "Add Emergency Numbers","AddEmergencyNumber","Add Emergency Numbers", xml);
				
				compareText(application, "Synchronize All Postcodes","SynchronizeAllpostcodes","Synchronize All Postcodes", xml);
				
				compareText(application, "Download NT Service Area","DownloadNt","Download NT Service Area", xml);
				
				compareText(application, "Upload NT Service Area","UploadNt","Upload NT Service Area", xml);			
				
			
			}

		
		
		else if (Countrylist.equals("Spain"))
		{
			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/SpaMngPostcode")));
			Log.info("Clicked on Spain Manage Postcode");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on Spain Manage Postcode");
			Log.info("Clicked on Spain Manage  Postcode");

			Thread.sleep(5000);
			
			compareText(application, "You can use * as wildcard","WildcardText","You can use * as wildcard", xml);
			
			compareText(application, "Emergency Area ID","EmergencyAreaID","Emergency Area ID", xml);
			
			compareText(application, "NT Service Area","NTServiceArea","NT Service Area", xml);
			
			compareText(application, "Emergency Area(Municipality)","EmergencyAreaMunicipality","Emergency Area(Municipality)", xml);
			
			compareText(application, "SubArea1(Provincia)","SubArea1Provincia","SubArea1(Provincia)", xml);
			
			compareText(application, "SubArea1-ID(Prov.Code)","SubArea1DSpa","SubArea1-ID(Prov.Code)", xml);
			
			compareText(application, "SubArea2(SubArea2)","SubArea2Fra","SubArea2(SubArea2)", xml);
			
            compareText(application, "SubArea2-ID(SubArea2-ID)","SubArea2-ID_Spain","SubArea2-ID(SubArea2-ID)", xml);
            
			compareText(application, "SubArea3(SubArea3)","SubArea3Den","SubArea3(SubArea3)", xml);
			
			compareText(application, "SubArea3-ID(SubArea3-ID)","SubArea3DenArea","SubArea3-ID(SubArea3-ID)", xml);
		
			compareText(application, "Last Update","Lastupdate","Last Update", xml);
			
	        compareText(application, "Add Postcode","Addpostcode","Add Postcode", xml);
			
			compareText(application, "Upload Update File","Uploadupdatefile","Upload Update File", xml);
			
			compareText(application, "View History","ViewHistory","View History", xml);
			
	        compareText(application, "Add Emergency Numbers","AddEmergencyNumber","Add Emergency Numbers", xml);
			
			compareText(application, "Synchronize All Postcodes","SynchronizeAllpostcodes","Synchronize All Postcodes", xml);
			
			compareText(application, "Download NT Service Area","DownloadNt","Download NT Service Area", xml);
			
			compareText(application, "Upload NT Service Area","UploadNt","Upload NT Service Area", xml);			
			
		}
		else if (Countrylist.equals("Sweden"))
		{
			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/SweMngPostcode")));
			Log.info("Clicked on Sweden Manage Postcode");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on Sweden Manage Postcode");
			Log.info("Clicked on Sweden Manage  Postcode");
			Thread.sleep(5000);
			
            compareText(application, "You can use * as wildcard","WildcardText","You can use * as wildcard", xml);
			
			compareText(application, "Emergency Area ID","EmergencyAreaID","Emergency Area ID", xml);
			
			compareText(application, "NT Service Area","NTServiceArea","NT Service Area", xml);
			
			compareText(application, "Emergency Area(Kommun)","EmergencyAreaKommun","Emergency Area(Kommun)", xml);
			
			compareText(application, "SubArea1(Lan)","SubArea1Lan","SubArea1(Lan)", xml);
			
			compareText(application, "SubArea1-ID(SubArea1-ID)","SubArea1-ID_Sweden","SubArea1-ID(SubArea1-ID)", xml);
			
			compareText(application, "SubArea2(SubArea2)","SubArea2Fra","SubArea2(SubArea2)", xml);
			
			compareText(application, "SubArea3(SubArea3)","SubArea3Den","SubArea3(SubArea3)", xml);
			
			compareText(application, "SubArea3-ID(SubArea3-ID)","SubArea3DenArea","SubArea3-ID(SubArea3-ID)", xml);
			
             compareText(application, "Last Update","Lastupdate","Last Update", xml);
			
	        compareText(application, "Add Postcode","Addpostcode","Add Postcode", xml);
			
			compareText(application, "Upload Update File","Uploadupdatefile","Upload Update File", xml);
			
			compareText(application, "View History","ViewHistory","View History", xml);
			
	        compareText(application, "Add Emergency Numbers","AddEmergencyNumber","Add Emergency Numbers", xml);
			
			compareText(application, "Synchronize All Postcodes","SynchronizeAllpostcodes","Synchronize All Postcodes", xml);
			
			compareText(application, "Download NT Service Area","DownloadNt","Download NT Service Area", xml);
			
			compareText(application, "Upload NT Service Area","UploadNt","Upload NT Service Area", xml);			
			
		}

		else if (Countrylist.equals("Switzerland"))
		{
			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/SwitMngPostcode")));
			Log.info("Clicked on Switzerland Manage Postcode");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on Switzerland Manage Postcode");
			Log.info("Clicked on Switzerland Manage  Postcode");
			
			
			
			     compareText(application, "You can use * as wildcard","WildcardText","You can use * as wildcard", xml);
				
				 compareText(application, "Emergency Area ID","EmergencyAreaID","Emergency Area ID", xml);
			 	
				 compareText(application, "NT Service Area","NTServiceArea","NT Service Area", xml);
				 
				 compareText(application, "Emergency Area(Emergency Area)","EmergencyArea","Emergency Area(Emergency Area)", xml);
				 
				 compareText(application, "SubArea1(Canton)","SubAreaCanton","SubArea1(Canton)", xml);
				 
				 compareText(application, "SubArea1-ID(GDEKT)","SuAreaGDEKT","SubArea1-ID(GDEKT)", xml);
				 
				 compareText(application, "SubArea2(District)","SubAreaDistrict","SubArea2(District)", xml);
				 
				 compareText(application, "SubArea2-ID(GDEBZNR)","SubAreaGDE","SubArea2-ID(GDEBZNR)", xml);
				 
				 compareText(application, "SubArea3(Municipality)","SubArea3Mun","SubArea3(Municipality)", xml);
				 
				 compareText(application, "SubArea3-ID(SubArea3-ID)","SubArea3DenArea","SubArea3-ID(SubArea3-ID)", xml);
				 				 
				    compareText(application, "Last Update","Lastupdate","Last Update", xml);
					
			        compareText(application, "Add Postcode","Addpostcode","Add Postcode", xml);
					
					compareText(application, "Upload Update File","Uploadupdatefile","Upload Update File", xml);
					
					compareText(application, "View History","ViewHistory","View History", xml);
					
			        compareText(application, "Add Emergency Numbers","AddEmergencyNumber","Add Emergency Numbers", xml);
					
					compareText(application, "Synchronize All Postcodes","SynchronizeAllpostcodes","Synchronize All Postcodes", xml);
					
					compareText(application, "Download NT Service Area","DownloadNt","Download NT Service Area", xml);
					
					compareText(application, "Upload NT Service Area","UploadNt","Upload NT Service Area", xml);			
				
				
			 
			
			
			}




		
		
	}
	
	
	public void addPostcode (String application,String Countrylist, String EmergencyAreaIDSub,String NtServiceAreaValue
,String ImplementSwitchValue,String CityTranValue,String SubProvinValue, String SubAreaValue,String SubAreaComValue,String SubAreaIDValue
,String SubAreaBValue,String SubAreaZIpValue,String EmergencySUbValue,String EmergencyKeyValue,
String ActualValue,String DummyCodeValue) throws Exception 
	
	{
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Postcode");
	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addpostcode")));
		Thread.sleep(5000);
		Log.info("Clicked on Add Postcode");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on Add Postcode");

		if(Countrylist.equalsIgnoreCase("Belgium"))
		{
		
	    	compareText(application,"Emergency Area ID(SUBCOM-ID)","EmergencyAreaIDSubcomLabel","Emergency Area ID(SUBCOM-ID)",xml);
	    	
	    	compareText(application,"SubArea1 (Province)","SubArea1ProLabel","SubArea1 (Province)",xml);
	    	
	    	compareText(application,"SubArea2 (Community)","SubArea2CommuLabel","SubArea2 (Community)",xml);
	    	
	    	compareText(application,"SubArea3 (b)","SubArea3BLabel","SubArea3 (b)",xml);
	    	
	    	compareText(application,"SubArea3-ID (ZIP code)","SubAreaZipLabel","SubArea3-ID (ZIP code)",xml);
	    	
	    	compareText(application,"Emergency Area (SUBCOM)","EmergencyAreaSubcomLabel","Emergency Area (SUBCOM)",xml);
		}
		
	
		else if(Countrylist.equals("Denmark"))
		{
			
	    	compareText(application,"Emergency Area ID (Kommunenummer)","EmergencyKommunerlabel","Emergency Area ID (Kommunenummer)",xml);
	    	
	    	compareText(application,"SubArea1 (SubArea1)","SubArea1DenLabel","SubArea1 (SubArea1)",xml);
	    	
	    	compareText(application,"SubArea2 (SubArea2)","SubArea2DenLabel","SubArea2 (SubArea2)",xml);
	    	
	    	compareText(application,"SubArea3 (SubArea3)","SubArea3Denlabel","SubArea3 (SubArea3)",xml);
	    	
	    	compareText(application,"SubArea3-ID (SubArea3-ID)","SubArea3IDDenLabel","SubArea3-ID (SubArea3-ID)",xml);
	    	
	    	compareText(application,"Emergency Area (Kommune)","EmergencyArea_Kommunelabel","Emergency Area (Kommune)",xml);
		}
		
		else if(Countrylist.equals("France"))
		{
            compareText(application,"Emergency Area ID (INSEE Code)","EmergencyAreaIDFranceLabel","Emergency Area ID (INSEE Code)",xml);
	    	
	    	compareText(application,"SubArea1 (Department)","SubAreaDepartFrance","SubArea1 (Department)",xml);
	    	
	    	compareText(application,"SubArea1-ID (Department ID)","SubArea1DFranLabel","SubArea1-ID (Department ID)",xml);

	    	compareText(application,"SubArea2 (SubArea2)","SubArea2DenLabel","SubArea2 (SubArea2)",xml);
	    	
	    	compareText(application,"SubArea3 (SubArea3)","SubArea3Denlabel","SubArea3 (SubArea3)",xml);
	    	
	    	compareText(application,"SubArea3-ID (SubArea3-ID)","SubArea3IDDenLabel","SubArea3-ID (SubArea3-ID)",xml);
	    	
	    	compareText(application,"Emergency Area (City (Area))","EmergencyAreaFranceLabel","Emergency Area (City (Area))",xml);

	    	
		
		}
		else if(Countrylist.equals("Germany"))
		{
			    compareText(application,"Emergency Area ID (Local Area ID (U_ONKZ))","EmergencyAreaIDGermanyLabel","Emergency Area ID (INSEE Code)",xml);
			    
		    	compareText(application,"SubArea1 (SubArea1)","SubArea1DenLabel","SubArea1 (SubArea1)",xml);
		    	
		    	compareText(application,"SubArea2 (SubArea2)","SubArea2DenLabel","SubArea2 (SubArea2)",xml);
		    	
		    	compareText(application,"SubArea3 (SubArea3)","SubArea3Denlabel","SubArea3 (SubArea3)",xml);
		    	
		    	compareText(application,"SubArea3-ID (SubArea3-ID)","SubArea3IDDenLabel","SubArea3-ID (SubArea3-ID)",xml);
		    	
		    	
		    	compareText(application,"Emergency Area (Local Area Name (U_ON_NAME))","EmergencyAreaLocalGermany","Emergency Area (Local Area Name (U_ON_NAME))",xml);		

		}
		else if(Countrylist.equals("Portugal"))
		{
		
			compareText(application,"Emergency Area ID (CLISRVPF INDEX)","EmergencyAreaIDPortugalLabel","Emergency Area ID (CLISRVPF INDEX)",xml);

			compareText(application,"SubArea1 (SubArea1)","SubArea1DenLabel","SubArea1 (SubArea1)",xml);
	    	
	    	compareText(application,"SubArea2 (SubArea2)","SubArea2DenLabel","SubArea2 (SubArea2)",xml);
	    	
	    	compareText(application,"SubArea3 (SubArea3)","SubArea3Denlabel","SubArea3 (SubArea3)",xml);
	    	
	    	compareText(application,"SubArea3-ID (SubArea3-ID)","SubArea3IDDenLabel","SubArea3-ID (SubArea3-ID)",xml);
	    	
	    	compareText(application,"Emergency Area (Province)","EmergencyAreaProvGermanyLAbel","Emergency Area (Province)",xml);


		}
		else if(Countrylist.equals("Spain"))
		{
			compareText(application,"Emergency Area ID (Municip.Code)","EmergencyAreaID_SpainLabel","Emergency Area ID (Municip.Code)",xml);
			
			compareText(application,"SubArea1 (Provincia)","SubAreaProvinciaSpainLabel","SubArea1 (Provincia)",xml);

			compareText(application,"SubArea1-ID (Prov.Code)","SubArea1DSpainLabel","SubArea1-ID (Prov.Code)",xml);
	
			compareText(application,"SubArea2 (SubArea2)","SubArea2DenLabel","SubArea2 (SubArea2)",xml);
	    	
	    	compareText(application,"SubArea3 (SubArea3)","SubArea3Denlabel","SubArea3 (SubArea3)",xml);
	    	
	    	compareText(application,"SubArea3-ID (SubArea3-ID)","SubArea3IDDenLabel","SubArea3-ID (SubArea3-ID)",xml);
	    	
			compareText(application,"Emergency Area (Municipality)","EmergencyAReaMunc_Label","Emergency Area (Municipality)",xml);	
		}
		
		else if(Countrylist.equals("Sweden"))
		{
			
			compareText(application,"Emergency Area ID (Kommun-ID/ALARM-ID)","EmergencyAreaIDSweden_Label","Emergency Area ID (Kommun-ID/ALARM-ID)",xml);
			
			compareText(application,"SubArea1 (Lan)","SubAreaLanSweden_Label","SubArea1 (Lan)",xml);
			
			compareText(application,"SubArea2-ID (ZIP)","SubArea2DLanSweden_Label","SubArea2-ID (ZIP)",xml);
				
			compareText(application,"SubArea2 (SubArea2)","SubArea2DenLabel","SubArea2 (SubArea2)",xml);
	    	
	    	compareText(application,"SubArea3 (SubArea3)","SubArea3Denlabel","SubArea3 (SubArea3)",xml);
	    	
	    	compareText(application,"SubArea3-ID (SubArea3-ID)","SubArea3IDDenLabel","SubArea3-ID (SubArea3-ID)",xml);
	    	
			compareText(application,"Emergency Area (Kommun)","EmergencyAreaKommun_Label","Emergency Area (Kommun)",xml);

	    	
		}
		else if(Countrylist.equals("Switzerland"))
		{
			
			compareText(application,"Emergency Area ID (Emergency Area ID)","EmergencyAreaIDSwitLabel","Emergency Area ID (Emergency Area ID)",xml);
			
			compareText(application,"SubArea1 (Canton)","SubArea1Caton_Label","SubArea1 (Canton)",xml);
			
			compareText(application,"SubArea1-ID (GDEKT)","SubArea1DGDEKT","SubArea1-ID (GDEKT)",xml);
				
			compareText(application,"SubArea2 (District)","SubAreaDistrictLabel","SubArea2 (District)",xml);
	    	
	    	compareText(application,"SubArea2-ID (GDEBZNR)","SuArea2IDSwit","SubArea2-ID (GDEBZNR)",xml);
	    	
	    	compareText(application,"SubArea3-ID (SubArea3-ID)","SubArea3IDDenLabel","SubArea3-ID (SubArea3-ID)",xml);
	    	
			compareText(application,"SubArea3 (Municipality)","SubArea3MunLabel","SubArea3 (Municipality)",xml);
			
			compareText(application,"Emergency Area (Emergency Area)","EmergencyAreaSwit","Emergency Area (Emergency Area)",xml);


	    	
		}
		
		if(Countrylist.equalsIgnoreCase("Belgium")||(Countrylist.equalsIgnoreCase("Denmark")||(Countrylist.equalsIgnoreCase("Germany")||
				(Countrylist.equalsIgnoreCase("Portugal")))))
						{
				
				WebElement SubArea1D = getwebelement(
						xml.getlocator("//locators/" + application + "/SubArea1DLabel"));
				 sa.assertTrue(SubArea1D.isDisplayed(),"Sub Area 1D Field is not displayed");
				Log.info("Sub Area 1D field is displayed");
				Log.info("SubArea1D field is displayed as:"+SubArea1D.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step:SubArea1D field is displayed as:" +SubArea1D.getText());
						}
				
		if(Countrylist.equalsIgnoreCase("Belgium")||(Countrylist.equalsIgnoreCase("Denmark")||(Countrylist.equalsIgnoreCase("France")||
				(Countrylist.equalsIgnoreCase("Germany")||(Countrylist.equalsIgnoreCase("Portugal")||
						(Countrylist.equalsIgnoreCase("Spain")))))))
		  {
		
				WebElement SubArea2ID = getwebelement(
						xml.getlocator("//locators/" + application + "/SubArea2IDLabel"));
				sa.assertTrue(SubArea2ID.isDisplayed(),"Sub Area2 ID Field is not displayed");
				Log.info("Sub Area2 ID field is displayed");
				Log.info("Sub Area 2 ID field is displayed as:"+SubArea2ID.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Sub Area 2 ID field is displayed as:" +SubArea2ID.getText());
						}

		
		if(Countrylist.equalsIgnoreCase("Belgium")||(Countrylist.equalsIgnoreCase("Denmark")||(Countrylist.equalsIgnoreCase("France")||(Countrylist.contentEquals("Germany")||
				(Countrylist.equalsIgnoreCase("Portugal")||(Countrylist.equalsIgnoreCase("Spain")||
						(Countrylist.equalsIgnoreCase("Sweden")||(Countrylist.equalsIgnoreCase("Switzerland")))))))))
		{
			WebElement NTServiceAreaPostcode = getwebelement(
					xml.getlocator("//locators/" + application + "/NtServiceAreaLabel"));
			sa.assertTrue(NTServiceAreaPostcode.isDisplayed(),"NT Service Area Postcode Field is not displayed");
			Log.info("NT Service Area Postcode field is displayed");
			Log.info("NT Service Area Post code:"+NTServiceAreaPostcode.getText());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:NT Service Area Postcode field is displayed as:" +NTServiceAreaPostcode.getText());


			
			WebElement ToimplemetSwitch = getwebelement(
					xml.getlocator("//locators/" + application + "/ToimplementLabel"));
			sa.assertTrue(ToimplemetSwitch.isDisplayed(),"To implement Switch  Field is not displayed");
			Log.info("To implement Switch field is displayed");
			Log.info("To implement Switch field is displayed as:"+ToimplemetSwitch.getText());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:To implement Switch field is displayed as:" +ToimplemetSwitch.getText());


			
			WebElement CityTranslatorNmbr = getwebelement(
					xml.getlocator("//locators/" + application + "/CityTransLabel"));
			sa.assertTrue(CityTranslatorNmbr.isDisplayed(),"City Translator Nmbr Field is not displayed");
			Log.info("City Translator Nmbr field is displayed");
			Log.info("City Translator Nmbr field is displayed as:"+CityTranslatorNmbr.getText());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:City Translator Nmbr field is displayed as:" +CityTranslatorNmbr.getText());
			
			WebElement EmergencyNmbrKey = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyKeyLabel"));
			sa.assertTrue(EmergencyNmbrKey.isDisplayed(),"Emergency Nmbr Key Text Field is not displayed");
			Log.info("Emergency Nmbr Key is displayed");
			Log.info("Emergency Nmbr Key is displayed");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Emergency Nmbr Key is displayed");
	
					
			WebElement ActualProvider = getwebelement(
					xml.getlocator("//locators/" + application + "/ActualProviderLabel"));
			sa.assertTrue(ActualProvider.isDisplayed(),"Actual Provider Text Field is not displayed");
			Log.info("Actual Provider Text Field is displayed");
			Log.info("Actual Provider Text Field is displayed");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Actual Provider Text Field is displayed");

			WebElement DummyCode_Text = getwebelement(
					xml.getlocator("//locators/" + application + "/DummyCodeLAbel"));
			sa.assertTrue(DummyCode_Text.isDisplayed(),"Dummy Code Field is not displayed");
			Log.info("Dummy Code Field is not displayed");
			Log.info("Dummy Code field is displayed");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Dummy Code field is displayed");


			
			WebElement EmptyTextbox = getwebelement(
					xml.getlocator("//locators/" + application + "/EmptyTextbox"));
			sa.assertTrue(EmptyTextbox.isDisplayed(),"Empty Text box Field is not displayed");
			Log.info("Empty Text box Field is displayed");
			Log.info("Empty Text box field is displayed as:"+EmptyTextbox.getText());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Empty Text box field is displayed as:");


			
			WebElement forwardarrow = getwebelement(xml.getlocator("//locators/" + application + "/forwardarrow"));
			 sa.assertTrue(forwardarrow.isDisplayed(),"forward arrow button Field is not displayed");
			Log.info("forward arrow button Field is  displayed");
			Log.info("forward arrow field is displayed as >>");
			ExtentTestManager.getTest().log(LogStatus.PASS, "forward arrow button field is displayed as: >>");


			
			WebElement BackwardArrow = getwebelement(
					xml.getlocator("//locators/" + application + "/BackwardArrow"));
			sa.assertTrue(BackwardArrow.isDisplayed(),"Back ward Arrow is not displayed");
			Log.info("Back ward Arrow is  displayed");
			Log.info("Backward Arrow field is displayed as <<");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Backward arrow button field is displayed as: <<");


			
			WebElement OKbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/OkbtnPostcode"));
			sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
			Log.info("OK btn is displayed");
			Log.info("OK btn is displayed");
			ExtentTestManager.getTest().log(LogStatus.PASS, "OK button is displayed");


			
			WebElement Cancelbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/Cancelbtn"));
			sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
			Log.info("Cancel button is displayed");
			Log.info("Cancel btn is displayed");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Cancel button is displayed");

		}		

		}
	
	
	public void fillAddPostCode(String application,String Countrylist, String EmergencyAreaIDSub,String NtServiceAreaValue
			,String ImplementSwitchValue,String CityTranValue,String SubProvinValue, String SubAreaValue,String SubAreaComValue,String SubAreaIDValue
			,String SubAreaBValue,String SubAreaZIpValue,String EmergencySUbValue,String EmergencyKeyValue,
			String ActualValue,String DummyCodeValue) throws Exception {
				
	
		if(Countrylist.equalsIgnoreCase("Belgium")||(Countrylist.equalsIgnoreCase("Denmark")||(Countrylist.equalsIgnoreCase("France")
				||(Countrylist.equalsIgnoreCase("Germany")||(Countrylist.equalsIgnoreCase("Portugal")
						||(Countrylist.equalsIgnoreCase("Spain")||(Countrylist.equalsIgnoreCase("Sweden")||(Countrylist.equalsIgnoreCase("Switzerland")))))))))
		{
			
			String countryCode=countryCode(application, Countrylist);
			String expectedNTserviceArea=countryCode+"-"+EmergencyAreaIDSub;
		
		//Filling value in Mandatory fields
				Thread.sleep(5000);
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "Entering Values to create postcode");
				
				addtextFields_commonMethod(application, "Emergency Area ID(SUBCOM-ID)", "EmergencyAreaIDSubcom_TextField",EmergencyAreaIDSub,xml);
				
				String actualNTserviceAreaValue=getwebelement(xml.getlocator("//locators/" + application + "/NTServiceAreaPostcode_Text")).getAttribute("value");
				if(expectedNTserviceArea.equals(actualNTserviceAreaValue)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "NT Service Are field value is displaying as "+ actualNTserviceAreaValue +" as expected");
					Log.info("NT Service Are field value is displaying as "+ actualNTserviceAreaValue +" as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "NT Service Are field value is displaying as "+ actualNTserviceAreaValue+". The expected value is: "+ expectedNTserviceArea);
					Log.info("NT Service Are field value is displaying as "+ actualNTserviceAreaValue +" as expected");
				}
				
				addtextFields_commonMethod(application, "To Implement Switch", "ToimplemetSwitch_Text", ImplementSwitchValue,xml);
				
				addtextFields_commonMethod(application, "City Translator Number", "CityTranslatorNmbr_Text", CityTranValue,xml);
				
				addtextFields_commonMethod(application, "SubArea1", "SubAreaProvince_1_Text", SubProvinValue,xml);
				
				addtextFields_commonMethod(application, "SubArea1-ID", "SubArea1D_Text", SubAreaValue,xml);
				
				addtextFields_commonMethod(application, "SubArea2", "SubArea2Community_Text", SubAreaComValue,xml);
				
				addtextFields_commonMethod(application, "SubArea2-ID", "SubArea2ID_Text", SubAreaIDValue,xml);
				
				addtextFields_commonMethod(application, "SubArea3", "SubArea3B_Text", SubAreaBValue,xml);
				
				addtextFields_commonMethod(application, "SubArea3-ID", "SubAreaZipcode_Text", SubAreaZIpValue,xml);
				
				addtextFields_commonMethod(application, "Emergency Area", "EmergencyAreaSub_Text", EmergencySUbValue,xml);
				
				addtextFields_commonMethod(application, "Emergency Number Key", "EmergencyNmbrKey_Text", EmergencyKeyValue,xml);
				
				addtextFields_commonMethod(application, "Actual Provider Mapping", "ActualProvider_Text", ActualValue,xml);
				
				addtextFields_commonMethod(application, "Dummy Code", "DummyCode_Text", DummyCodeValue,xml);
				Thread.sleep(1000);
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/forwardarrow")));
				Thread.sleep(1000);

				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OkbtnPostcode")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clciked on OK  button");

				Thread.sleep(5000);
				
				//Verify Post code Success Message
				
				verifysuccessmessage("ManageColt", "Postcode successfully created.");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, Countrylist + " country is not available");
			assertTrue(false);
			
		}
				
	}
		
	
	public void selectAddedPostCodeInTable(String application, String Countrylist, String EmergencyAreaIDSub) throws Exception {
		
		String countryCode=countryCode(application, Countrylist);
		String expectedNTserviceArea=countryCode+"-"+EmergencyAreaIDSub;
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Searchfield")),expectedNTserviceArea);
		Log.info("Value Entered in TextField");
		Log.info("Value Entered in Search TextField");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Value Entered in Search TextField" );

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Searchbtn")));
		Log.info("Clicked on Search button");
		Log.info("Clicked on search button");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on search button" );
		
		Thread.sleep(2000);
		
		WebElement addPostCodeLink=getwebelement(xml.getlocator("//locators/" + application + "/Addpostcode"));
		scrolltoview(addPostCodeLink);
		Thread.sleep(1000);
		
		 	safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeCheckbox").replace("value", EmergencyAreaIDSub)));
			Log.info("Clicked on checkbox");
			Log.info("Clicked on checkbox");
			ExtentTestManager.getTest().log(LogStatus.PASS, "selected postcode record in Table");
			Thread.sleep(3000);
			
		    safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
			Log.info("Clicked on Action Dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Action Dropdown");
			Thread.sleep(2000);

	}
	
	public void verifyPostcodevalues(String application,String Countrylist, String EmergencyAreaIDSub, String ImplementSwitchValue,String CityTranValue,
			String SubProvinValue,String SubAreaValue, String SubAreaComValue,String SubAreaIDValue, String SubAreaBValue,String SubAreaZIpValue, String EmergencySUbValue )throws Exception 
	{
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying view Postcode");
		
	//select the postcode value in table and click on Action dropdown	
		selectAddedPostCodeInTable(application, Countrylist, EmergencyAreaIDSub);
		
		//Verifying the values in View Postcode
		
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeActionView")));
		Log.info("Clicked on View");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on View in Action Dropdown" );
		
		if(Countrylist.equalsIgnoreCase("France"))
		{
		
		compareText_InViewPage(application ,"Emergency Area ID (INSEE Code)",EmergencyAreaIDSub,xml);
		
		compareText_InViewPage(application ,"To Implement Switch:",ImplementSwitchValue,xml);
		
		compareText_InViewPage(application ,"City Translator Number:",CityTranValue,xml);
		
		compareText_InViewPage(application ,"SubArea1 (Department)",SubProvinValue,xml);
		
		compareText_InViewPage(application ,"SubArea1-ID (Department ID)",SubAreaValue,xml);
		
		compareText_InViewPage(application ,"SubArea2 (SubArea2)",SubAreaComValue,xml);
		
		compareText_InViewPage(application ,"SubArea2-ID (SubArea2-ID)",SubAreaIDValue,xml);
		
		compareText_InViewPage(application ,"SubArea3 (SubArea3)",SubAreaBValue,xml);
		
		compareText_InViewPage(application ,"SubArea3-ID (SubArea3-ID)",SubAreaZIpValue,xml);
		
		compareText_InViewPage(application ,"Emergency Area (City (Area))",EmergencySUbValue,xml);
		}
		
		else if(Countrylist.equalsIgnoreCase("Belgium"))
		{
			compareText_InViewPage(application ,"Emergency Area ID(SUBCOM-ID)",EmergencyAreaIDSub,xml);
			
			compareText_InViewPage(application ,"To Implement Switch:",ImplementSwitchValue,xml);
			
			compareText_InViewPage(application ,"City Translator Number:",CityTranValue,xml);
			
			compareText_InViewPage(application ,"SubArea1 (Province)",SubProvinValue,xml);
			
			compareText_InViewPage(application ,"SubArea1-ID (SubArea1-ID)",SubAreaValue,xml);
			
			compareText_InViewPage(application ,"SubArea2 (Community)",SubAreaComValue,xml);
			
			compareText_InViewPage(application ,"SubArea2-ID (SubArea2-ID)",SubAreaIDValue,xml);
			
			compareText_InViewPage(application ,"SubArea3 (b)",SubAreaBValue,xml);
			
			compareText_InViewPage(application ,"SubArea3-ID (ZIP code)",SubAreaZIpValue,xml);
			
			compareText_InViewPage(application ,"Emergency Area (SUBCOM)",EmergencySUbValue,xml);

		}
		else if(Countrylist.equalsIgnoreCase("Denmark"))
		{
			compareText_InViewPage(application ,"Emergency Area ID (Kommunenummer)",EmergencyAreaIDSub,xml);
			
			compareText_InViewPage(application ,"To Implement Switch:",ImplementSwitchValue,xml);
			
			compareText_InViewPage(application ,"City Translator Number:",CityTranValue,xml);
			
			compareText_InViewPage(application ,"SubArea1 (SubArea1)",SubProvinValue,xml);
			
			compareText_InViewPage(application ,"SubArea1-ID (SubArea1-ID)",SubAreaValue,xml);
			
			compareText_InViewPage(application ,"SubArea2 (SubArea2)",SubAreaComValue,xml);
			
			compareText_InViewPage(application ,"SubArea2-ID (SubArea2-ID)",SubAreaIDValue,xml);
			
			compareText_InViewPage(application ,"SubArea3 (SubArea3)",SubAreaBValue,xml);
			
			compareText_InViewPage(application ,"SubArea3-ID (SubArea3-ID)",SubAreaZIpValue,xml);
			
			compareText_InViewPage(application ,"Emergency Area (Kommune)",EmergencySUbValue,xml);

		}
		else if(Countrylist.equalsIgnoreCase("Germany"))
		{
			compareText_InViewPage(application ,"Emergency Area ID (Local Area ID (U_ONKZ))",EmergencyAreaIDSub,xml);
			
			compareText_InViewPage(application ,"To Implement Switch:",ImplementSwitchValue,xml);
			
			compareText_InViewPage(application ,"City Translator Number:",CityTranValue,xml);
			
			compareText_InViewPage(application ,"SubArea1 (SubArea1)",SubProvinValue,xml);
			
			compareText_InViewPage(application ,"SubArea1-ID (SubArea1-ID)",SubAreaValue,xml);
			
			compareText_InViewPage(application ,"SubArea2 (SubArea2)",SubAreaComValue,xml);
			
			compareText_InViewPage(application ,"SubArea2-ID (SubArea2-ID)",SubAreaIDValue,xml);
			
			compareText_InViewPage(application ,"SubArea3 (SubArea3)",SubAreaBValue,xml);
			
			compareText_InViewPage(application ,"SubArea3-ID (SubArea3-ID)",SubAreaZIpValue,xml);
			
			compareText_InViewPage(application ,"Emergency Area (Local Area Name (U_ON_NAME))",EmergencySUbValue,xml);

		}
		else if(Countrylist.equalsIgnoreCase("Portugal"))
		{
			compareText_InViewPage(application ,"Emergency Area ID (CLISRVPF INDEX)",EmergencyAreaIDSub,xml);
			
			compareText_InViewPage(application ,"To Implement Switch:",ImplementSwitchValue,xml);
			
			compareText_InViewPage(application ,"City Translator Number:",CityTranValue,xml);
			
			compareText_InViewPage(application ,"SubArea1 (SubArea1)",SubProvinValue,xml);
			
			compareText_InViewPage(application ,"SubArea1-ID (SubArea1-ID)",SubAreaValue,xml);
			
			compareText_InViewPage(application ,"SubArea2 (SubArea2)",SubAreaComValue,xml);
			
			compareText_InViewPage(application ,"SubArea2-ID (SubArea2-ID)",SubAreaIDValue,xml);
			
			compareText_InViewPage(application ,"SubArea3 (SubArea3)",SubAreaBValue,xml);
			
			compareText_InViewPage(application ,"SubArea3-ID (SubArea3-ID)",SubAreaZIpValue,xml);
			
			compareText_InViewPage(application ,"Emergency Area (Province)",EmergencySUbValue,xml);

		}
		else if(Countrylist.equalsIgnoreCase("Portugal"))
		{
			compareText_InViewPage(application ,"Emergency Area ID (CLISRVPF INDEX)",EmergencyAreaIDSub,xml);
			
			compareText_InViewPage(application ,"To Implement Switch:",ImplementSwitchValue,xml);
			
			compareText_InViewPage(application ,"City Translator Number:",CityTranValue,xml);
			
			compareText_InViewPage(application ,"SubArea1 (SubArea1)",SubProvinValue,xml);
			
			compareText_InViewPage(application ,"SubArea1-ID (SubArea1-ID)",SubAreaValue,xml);
			
			compareText_InViewPage(application ,"SubArea2 (SubArea2)",SubAreaComValue,xml);
			
			compareText_InViewPage(application ,"SubArea2-ID (SubArea2-ID)",SubAreaIDValue,xml);
			
			compareText_InViewPage(application ,"SubArea3 (SubArea3)",SubAreaBValue,xml);
			
			compareText_InViewPage(application ,"SubArea3-ID (SubArea3-ID)",SubAreaZIpValue,xml);
			
			compareText_InViewPage(application ,"Emergency Area (Province)",EmergencySUbValue,xml);

		}
		
		else if(Countrylist.equalsIgnoreCase("Spain"))
		{
			compareText_InViewPage(application ,"Emergency Area ID (Municip.Code)",EmergencyAreaIDSub,xml);
			
			compareText_InViewPage(application ,"To Implement Switch:",ImplementSwitchValue,xml);
			
			compareText_InViewPage(application ,"City Translator Number:",CityTranValue,xml);
			
			compareText_InViewPage(application ,"SubArea1 (Province)",SubProvinValue,xml);
			
			compareText_InViewPage(application ,"SubArea1-ID (Prov.Code)",SubAreaValue,xml);
			
			compareText_InViewPage(application ,"SubArea2 (SubArea2)",SubAreaComValue,xml);
			
			compareText_InViewPage(application ,"SubArea2-ID (SubArea2-ID)",SubAreaIDValue,xml);
			
			compareText_InViewPage(application ,"SubArea3 (SubArea3)",SubAreaBValue,xml);
			
			compareText_InViewPage(application ,"SubArea3-ID (SubArea3-ID)",SubAreaZIpValue,xml);
			
			compareText_InViewPage(application ,"Emergency Area (Municipality)",EmergencySUbValue,xml);

		}
		else if(Countrylist.equalsIgnoreCase("Sweden"))
		{
			compareText_InViewPage(application ,"Emergency Area ID (Kommun-ID/ALARM-ID)",EmergencyAreaIDSub,xml);
			
			compareText_InViewPage(application ,"To Implement Switch:",ImplementSwitchValue,xml);
			
			compareText_InViewPage(application ,"City Translator Number:",CityTranValue,xml);
			
			compareText_InViewPage(application ,"SubArea1 (Lan)",SubProvinValue,xml);
			
			compareText_InViewPage(application ,"SubArea1-ID (SubArea1-ID)",SubAreaValue,xml);
			
			compareText_InViewPage(application ,"SubArea2 (SubArea2)",SubAreaComValue,xml);
			
			compareText_InViewPage(application ,"SubArea2-ID (ZIP)",SubAreaIDValue,xml);
			
			compareText_InViewPage(application ,"SubArea3 (SubArea3)",SubAreaBValue,xml);
			
			compareText_InViewPage(application ,"SubArea3-ID (SubArea3-ID)",SubAreaZIpValue,xml);
			
			compareText_InViewPage(application ,"Emergency Area (Kommun)",EmergencySUbValue,xml);

		}
		else if(Countrylist.equalsIgnoreCase("Switzerland"))
		{
			compareText_InViewPage(application ,"Emergency Area ID (Emergency Area ID)",EmergencyAreaIDSub,xml);
			
			compareText_InViewPage(application ,"To Implement Switch:",ImplementSwitchValue,xml);
			
			compareText_InViewPage(application ,"City Translator Number:",CityTranValue,xml);
			
			compareText_InViewPage(application ,"SubArea1 (Canton)",SubProvinValue,xml);
			
			compareText_InViewPage(application ,"SubArea1-ID (GDEKT)",SubAreaValue,xml);
			
			compareText_InViewPage(application ,"SubArea2 (District)",SubAreaComValue,xml);
			
			compareText_InViewPage(application ,"SubArea2-ID (GDEBZNR)",SubAreaIDValue,xml);
			
			compareText_InViewPage(application ,"SubArea3 (Municipality)",SubAreaBValue,xml);
			
			compareText_InViewPage(application ,"SubArea3-ID (SubArea3-ID)",SubAreaZIpValue,xml);
			
			compareText_InViewPage(application ,"Emergency Area (Emergency Area)",EmergencySUbValue,xml);

		}
	
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeBackbtn")));
		Log.info("Clicked on back button");
		Log.info("Clicked on back button");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Clicked on back button");

		
	    Log.info("Navigated to Manage Post code page");
	    Log.info("Navigated to Manage Post code page");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step:Navigated to Manage Post code page");

	   		
		//Delete the Post code
/*		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Deleting Postcode");

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeCheckbox")));
		Log.info("Clicked on checkbox");
		Log.info("Clicked on checkbox");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clickedx on Checkbox");

	    
		
		 safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
		 Log.info("Clicked on Action Dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clickedx on Action Dropdown");

			
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeActionDelete")));
		Log.info("Clicked on Delete option");
		Log.info("Clicked on Delete Option");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Selected Delete option");

		Thread.sleep(5000);
		
		

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeDeletebtn")));
		Log.info("Clicked on Delete button ");
	    Log.info("Clicked on Delete button");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Delete button");
		


		ExtentTestManager.getTest().log(LogStatus.PASS, "Verifying Delete Post code Message");
		
		Thread.sleep(10000);
		String ExpPostcodeDeletemsg = "Postcode deleted successfully..";
		Log.info("Postcode Delete message");
		WebElement ActPostcodeDeletemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode deleted successfully..')]"));
		
		if (ActPostcodeDeletemsg.getText().equals(ExpPostcodeDeletemsg))
		{
			Log.info("Postcode Delete Message : " + ActPostcodeDeletemsg.getText());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodeDeletemsg.getText());
			 Log.info("Post code Delete message as:"+ActPostcodeDeletemsg.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Post code Delete message as:" +ActPostcodeDeletemsg.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Post code Delete message verified");


		}
		else
		{
			sa.assertTrue(ActPostUpdatecodemsg.isDisplayed(),"Message not displayed");
			Log.info("Postcode deleted message not displayed");
			 Log.info("Post code Delete message as:"+ActPostcodeDeletemsg.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Post code Delete message verified");


		}
*/
	}
	
	
	public void verifyAddEmergencyNumber(String application,String filepath)
			throws Exception 
	{

		Thread.sleep(5000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Emergency number");

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/AddEmergencyNumber")));
		Log.info("Clicked on Add Emergency Number");
		 Log.info("Clicked on Add Emergency Number");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Add Emergency Number");

			
			waitforPagetobeenable();

		Thread.sleep(2000);
		
		
        compareText("ManageColt", "Choose file", "Chosefile", "Choose file", xml);
		
		compareText("ManageColt", "OK", "OkbtnPostcode", "OK", xml);
		
		compareText("ManageColt", "Cancel", "Cancelbtn", "Cancel", xml);
		
		
		 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Chosefile")), filepath);
		 ExtentTestManager.getTest().log(LogStatus.PASS, "File uploaded");

		 click_commonMethod(application, "OK", "OkbtnPostcode", xml);
		 
		 verifysuccessmessage(application, "Emergency Number successfully created.PSX sync started successfully. Please check the status of the Postcodes.");
		 
		 
		 try {
				boolean ActNtServicemsg = getwebelement(xml.getlocator("//locators/" +application+ "/successMessageForEmergencyNumber")).isDisplayed();
	
				if(ActNtServicemsg) {
					Log.info("Success Message mismatches");
					
				}else {
					
					click_commonMethod(application, "Cancel", "Cancelbtn", xml);
				}
				}catch(Exception e) {
					e.printStackTrace();
					click_commonMethod(application, "Cancel", "Cancelbtn", xml);
				}
		
	}

	public void verifyUploadNtserviceArea (String application,String filepath)
			throws Exception 
			{
		
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Upload NT Service Area");

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/UploadNt")));
		Log.info("Clicked on Upload NT Service Area");
		 Log.info("Clicked on Upload NT Service Area");
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Upload NT Service Area");
		Thread.sleep(2000);
		
		
		waitforPagetobeenable();
		
		compareText("ManageColt", "Choose file", "Chosefile", "Choose file", xml);
		
		compareText("ManageColt", "OK", "OkbtnPostcode", "OK", xml);
		
		compareText("ManageColt", "Cancel", "Cancelbtn", "Cancel", xml);
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ChosefileTrans")), filepath);
		ExtentTestManager.getTest().log(LogStatus.PASS, "File uploaded");
		
		 safeJavaScriptClick(getwebelement(
					xml.getlocator("//locators/" + application + "/OkbtnPostcode")));
			 Log.info("Clicked on OK button");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on OK button");
				
				
				String ExpNtservicemsg = "NT Service Area successfully updated.";
				
				verifysuccessmessage(application, "NT Service Area successfully updated.");
				
				try {
				boolean ActNtServicemsg = getwebelement(xml.getlocator("//locators/" +application+ "/AlertUploadNt" )).isDisplayed();
	
				if(ActNtServicemsg) {
					Log.info("Success Message displays");
					
				}else {
					
					click_commonMethod(application, "Cancel", "Cancelbtn", xml);
				}
				}catch(Exception e) {
					e.printStackTrace();
					click_commonMethod(application, "Cancel", "Cancelbtn", xml);
				}
				
				
		
		 
			}
	
	public void verifyDownloadNt(String application, String downloadpath)throws Exception
	{
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Download NT Service Area");
		
		safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/DownloadNt")));

		Log.info("Download Nt Link is displayed");
		Log.info("Clicked on Download Nt Service Area link");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Download Nt Service Area link");
		
		isFileDownloaded(downloadpath, "postcode_cld_63");


	}
	
	public void verifyUploadupdatefile (String application, String filepath)
			throws Exception 
			{
		
		
		waitforPagetobeenable();
		
		Thread.sleep(5000);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Upload Update file");
		
		safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/Uploadupdatefile")));
		Log.info("Clicked on Upload update file");
		Log.info("Clicked on Upload update file");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Upload update file");

		Thread.sleep(2000);
		
		
		compareText("ManageColt", "Choose file", "Chosefile", "Choose file", xml);
		
		compareText("ManageColt", "OK", "OkbtnPostcode", "OK", xml);
		
		compareText("ManageColt", "Cancel", "Cancelbtn", "Cancel", xml);
		
		 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Chosefile")), filepath);
		 ExtentTestManager.getTest().log(LogStatus.PASS, "File uploaded");
		
		 
		 safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/OkbtnPostcode")));
		 Log.info("Clicked on OK button");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on OK button");
			
	    	
	    	
	    	waitforPagetobeenable();
	    	
			verifysuccessmessage(application, "Emergency Number successfully updated.PSX sync started successfully.");
			
		try {	
				
			boolean successmEssage_UploadUpdateFile= getwebelement(xml.getlocator("//locators/" + application + "/successmessageForUploadUpdateFile")).isDisplayed();
		
			if(successmEssage_UploadUpdateFile) {
				
				Log.info("upload success message is displaying");
			}else {
				click_commonMethod(application, "cancel", "Cancelbtn" , xml);
			}
		}catch(Exception e) {
			e.printStackTrace();
			
			click_commonMethod(application, "cancel", "Cancelbtn" , xml);
		}
			
		
			}
	
	     public void verifyViewHistory (String application, String countryName)throws Exception		{
	    	 
	 		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying View History");

		safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/ViewHistory")));
		Log.info("Clicked on View History");
		Log.info("Clicked on View History");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on View History");

		
		waitforPagetobeenable();
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ViewHistoryPage").replace("value", countryName)));
		Log.info("Clicked on View History Record link");
		Log.info("Navigated to View Updated History Record");
		Log.info("Clicked on View History Record link");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on View History Record Link");


		Thread.sleep(2000);
		
		//Verifying the View History fields
		
   	    compareText("ManageColt", "File Name", "FileNamefield", "File Name", xml);
   	    
   	    compareText("ManageColt", "Date", "Datefield", "Date", xml);
   	    
   	    compareText("ManageColt", "User", "UserField", "User", xml);
   	    
   	    compareText("ManageColt", "Updated By", "Updatebyfield", "Updated By", xml);
   	    
   	    String viewHistoryTable = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/historyTableValue")));
   	    
   	    ExtentTestManager.getTest().log(LogStatus.PASS, "view history table value display as " + viewHistoryTable);
   	    
   	    scrolltoend();
   	    Thread.sleep(2000);
   	    Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeBackbtn")));

			}


	     /**
	 	 * success Message common method
	 	 * @param application
	 	 * @throws InterruptedException
	 	 */
	 	public void verifysuccessmessage(String application, String expected) throws InterruptedException {
	 		
	 		
	 		waitforPagetobeenable();
	 		scrollToTop();
	 		Thread.sleep(3000);
	 		try {	
	 			
	 			boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMsg_managePOstcode")).isDisplayed();

	 			if(successMsg) {
	 				
	 				String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage_managePOstcode")).getText();
	 				
	 				if(expected.contains(alrtmsg)) {
	 					
	 					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
	 					Log.info("Message is verified. It is displaying as: "+alrtmsg);
	 					
	 					successScreenshot(application);
	 					
	 				}else if(expected.equals(alrtmsg)){
	 					
	 					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
	 					Log.info("Message is verified. It is displaying as: "+alrtmsg);
	 					successScreenshot(application);
	 					
	 				}else {
	 					
	 					ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
	 					Log.info("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
	 					failureScreenshot(application);
	 				}
	 				
	 			}else {
	 				ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
	 				Log.info(" Success Message is not displaying");
	 				failureScreenshot(application);
	 			}
	 			
	 		}catch(Exception e) {
	 			Log.info("failure in fetching success message  ");
	 			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
	 			Log.info(expected+ " message is not getting dislpayed");
	 			failureScreenshot(application);
	 		}
	 	}

	 	/*
		 * success Message common method for File Uploading
	 	 * @param application
	 	 * @throws InterruptedException
	 	 */
	 	public void verifysuccessmessage_fileUploads(String application, String expected) throws InterruptedException {
	 		
	 		scrollToTop();
	 		Thread.sleep(3000);
	 		try {	
	 			
	 			boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMesage_classTag")).isDisplayed();

	 			if(successMsg) {
	 				
	 				String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
	 				
	 				if(expected.contains(alrtmsg)) {
	 					
	 					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
	 					Log.info("Message is verified. It is displaying as: "+alrtmsg);
	 					
	 					successScreenshot(application);
	 					
	 				}else if(expected.equals(alrtmsg)){
	 					
	 					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
	 					Log.info("Message is verified. It is displaying as: "+alrtmsg);
	 					successScreenshot(application);
	 					
	 					
	 				}else {
	 					
	 					ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
	 					Log.info("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
	 					successScreenshot(application);
	 				}
	 				
	 			}else {
	 				ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
	 				Log.info(" Success Message is not displaying");
	 			}
	 			
	 			Thread.sleep(2000);
	 			
	 		}catch(Exception e) {
	 			Log.info("failure in fetching success message - 'Service created Successfully'  ");
	 			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
	 			Log.info(expected+ " message is not getting dislpayed");
	 			successScreenshot(application);
	 		}
	 	}

	 	public boolean isFileDownloaded(String downloadPath, String fileName) {
			  File dir = new File(downloadPath);
			  File[] dirContents = dir.listFiles();

			  for (int i = 0; i < dirContents.length; i++) {
			      if (dirContents[i].getName().contains(fileName)) {
			          // File has been found, it can now be deleted:
			          //dirContents[i].delete();
			    	  
			    	  String downloadedFileName=dirContents[i].getName();
			    	  Log.info("Downloaded file name is displaying as: "+ downloadedFileName);
			    	  ExtentTestManager.getTest().log(LogStatus.PASS, "Downloaded file name is displaying as: "+ dirContents[i]);
			    	  
			          return true;
			      }
			          }
			      return false;
			  }
	 	
	 	
	 	public String countryCode(String application, String countryName) {
	 		
	 		String code=null;
	 		
	 		if(countryName.equalsIgnoreCase("Belgium")){
	 			code="32";
	 		}
	 		else if(countryName.equalsIgnoreCase("Denmark")){
	 			code="45";
	 		}
	 		else if(countryName.equalsIgnoreCase("France")){
	 			code="33";
	 		}
			else if(countryName.equalsIgnoreCase("Germany")){
				code="49";
			}
			else if(countryName.equalsIgnoreCase("Portugal")){
				code="351";
			}
			else if(countryName.equalsIgnoreCase("Spain")){
				code="34";	
			}
			else if(countryName.equalsIgnoreCase("Sweden")){
				code="46";
			}
			else if(countryName.equalsIgnoreCase("Switzerland")){
				code="41";	
			}
	 		
	 		return code;
	 	}
	 	
	 	
	 	public void editPostcode(String application, String Countrylist, String EmergencyAreaIDSub, String edit_ImplementSwitchValue, String edit_CityTranValue,
	 			String edit_SubArea1, String edit_SubArea1_ID, String edit_SubArea2, String edit_SubArea2_ID, String edit_SubArea3, String edit_SubArea3_ID,
	 			String edit_EmergencyArea, String replaceEmergencyArea, String updateEmergencyArea, String edit_EmergencyKeyValue, String edit_ActualValue, String edit_DummyCodeValue) throws InterruptedException, DocumentException, Exception {
	 	
	 	//select the postcode value in table and click on Action dropdown	
			selectAddedPostCodeInTable(application, Countrylist, EmergencyAreaIDSub);
		   		    
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeActionEdit")));
		    Log.info("Clicked on Edit button");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Edit button");

			
			waitforPagetobeenable();
			
			Thread.sleep(3000);
			
			edittextFields_commonMethod(application, "To Implement Switch", "ToimplemetSwitch_Text", edit_ImplementSwitchValue,xml);
			
			edittextFields_commonMethod(application, "City Translator Number", "CityTranslatorNmbr_Text", edit_CityTranValue,xml);
			
			edittextFields_commonMethod(application, "SubArea1", "SubAreaProvince_1_Text", edit_SubArea1,xml);
			
			edittextFields_commonMethod(application, "SubArea1-ID", "SubArea1D_Text", edit_SubArea1_ID,xml);
			
			edittextFields_commonMethod(application, "SubArea2", "SubArea2Community_Text", edit_SubArea2,xml);
			
			edittextFields_commonMethod(application, "SubArea2-ID", "SubArea2ID_Text", edit_SubArea2_ID,xml);
			
			edittextFields_commonMethod(application, "SubArea3", "SubArea3B_Text", edit_SubArea3,xml);
			
			edittextFields_commonMethod(application, "SubArea3-ID", "SubAreaZipcode_Text", edit_SubArea3_ID,xml);
			
			edittextFields_commonMethod(application, "Emergency Area", "EmergencyAreaSub_Text", edit_EmergencyArea,xml);
			
			if(replaceEmergencyArea.equalsIgnoreCase("yes") && updateEmergencyArea.equalsIgnoreCase("No")) {
				
				//TODO::
				
			}
			else if(replaceEmergencyArea.equalsIgnoreCase("No") && updateEmergencyArea.equalsIgnoreCase("yes")) {
				
				addtextFields_commonMethod(application, "Emergency Number Key", "EmergencyNmbrKey_Text", edit_EmergencyKeyValue,xml);
				
				addtextFields_commonMethod(application, "Actual Provider Mapping", "ActualProvider_Text", edit_ActualValue,xml);
				
				addtextFields_commonMethod(application, "Dummy Code", "DummyCode_Text", edit_DummyCodeValue,xml);
				
				Thread.sleep(1000);
				click_commonMethod(application, ">>", "forwardarrow", xml);
				Thread.sleep(1000);

				
			}
			
		    safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OkbtnPostcode")));
			Thread.sleep(5000);
		    Log.info("Clicked on OK button");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on OK button");

			verifysuccessmessage(application, "Postcode successfully updated.");
			
	 	}
	 	
	 	
	 	public void deletePostcode(String application, String Countrylist, String EmergencyAreaIDSub) throws Exception {
	 		
	 		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete' Functionality");
	 		
	 		
	 		waitforPagetobeenable();
	 		
	 		scrolltoend();
	 		
	 	//select the postcode value in table and click on Action dropdown	
			selectAddedPostCodeInTable(application, Countrylist, EmergencyAreaIDSub);
		 
			click_commonMethod(application, "Delete", "PostcodeActionDelete" , xml);
			
		//Handling Delete Alert
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
	        if(DeleteAlertPopup.isDisplayed())
	        {
	      	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
	      	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
	      	 
	           click_commonMethod(application, "Delete", "deletebutton", xml);
	           
	           scrollToTop();
	           Thread.sleep(1000);
	           
	           verifysuccessmessage(application, "Postcode successfully deleted.");
	        }
	        else
	        {
	              Log.info("Delete alert popup is not displayed");
	              ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
	        }    
	 		
	 	}
	 	
	 	
	 	public static void editRecord(String filepath, String editTerm, String newId, String newName) {
			
			String tempFile="E:/eclipse-workspace/APT_CreateAccessCoreDevice/src/temp.csv";
			File oldfile = new File(filepath);
			File newfile = new File(tempFile);
			String id=""; String name="";
			boolean found=false;
			
			try {
				FileWriter fw= new FileWriter(tempFile, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);
				
				s = new Scanner(new File(filepath));
				s.useDelimiter("[,\n]");
				
				while(s.hasNext())
				{
					id=s.next();
					name=s.next();
					
					if(id.equals(editTerm)) {
						pw.println(newId + "," + newName);
					}
					else 
					{
						Log.info("inside else cse");
//						pw.println(newId + "," + newName);
					}
				}
				
				s.close();
				pw.flush();
				pw.close();
				
				Log.info("olf file  "+ oldfile);
				oldfile.deleteOnExit();
				if(oldfile.delete()) {
					Log.info("old file is deleted");
				}else {
					oldfile.delete();
					Log.info("old file not deleteed");
				}
				
				File dump = new File(filepath);
				Log.info("dump path is "+dump);
				
				newfile.renameTo(dump);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	 	
	 	public void createFileForAddEmergencyNumber(String filepath, String emergencyID,  String dummycode, String actualProviderMapping, String emergencyNumberKey) {
	 		
	 		String tempFile=filepath;
			File newfile = new File(tempFile);
			String id=""; String name="";
			boolean found=false;
			
			try {
				FileWriter fw= new FileWriter(tempFile, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);
				
					pw.println(emergencyID + "," + dummycode + "," + actualProviderMapping + "," + emergencyNumberKey);
					
				pw.flush();
				pw.close();
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Emergency Number' file is created");
				ExtentTestManager.getTest().log(LogStatus.PASS, "In 'Add Emergency Area' CSV file, test data is added as:  "+ emergencyID + "," + dummycode + "," + actualProviderMapping + "," + emergencyNumberKey);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
	 		
	 	}
	 	
	 	
	public void createFileForuploadNTServiceArea(String application, String filepath, String EmergencyAreaIDSub, String Countrylist) {
	 	
		String countryCode=countryCode(application, Countrylist);
		String expectedNTserviceArea=countryCode+"-"+EmergencyAreaIDSub;
		
	 		String tempFile=filepath;
			File newfile = new File(tempFile);
			String id=""; String name="";
			boolean found=false;
			
			try {
				FileWriter fw= new FileWriter(tempFile, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw); 
				
					pw.println(EmergencyAreaIDSub  + "," + expectedNTserviceArea);
					
				pw.flush();
				pw.close();
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Upload NT Service Area' file is created");
				ExtentTestManager.getTest().log(LogStatus.PASS, "In Upload NT Serice ARea CSV file, Test data is added as: "+ EmergencyAreaIDSub  + "," + expectedNTserviceArea);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	
	public void createFileForUploadupdateFile(String filepath, String dummyCode, String providerMapping) {
		
		
	 		String tempFile=filepath;
			File newfile = new File(tempFile);
			String id=""; String name="";
			boolean found=false;
			
			try {
				FileWriter fw= new FileWriter(tempFile, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw); 
				
					pw.println(dummyCode  + "," + providerMapping);
					
				pw.flush();
				pw.close();
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Upload Update File' file is created");
				ExtentTestManager.getTest().log(LogStatus.PASS, "In 'Upload Update File' CSV file, Test data is added as: "+ dummyCode  + "," + providerMapping);
				Log.info("'Upload Update File' file is created");
				Log.info("In 'Upload Update File' CSV file, Test data is added as: "+ dummyCode  + "," + providerMapping);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
	 		
	 	
	}
	 	
	 	public void deleteFile(String filepath, String fileName) {
	 		
	 		File newfile = new File(filepath);
	 		  
	 		  if(newfile.delete()) {
	 				Log.info(fileName + " file is deleted");
	 				ExtentTestManager.getTest().log(LogStatus.PASS, fileName + " file is deleted");
	 			}else {
	 				Log.info(fileName + " file is not deleted");
	 				ExtentTestManager.getTest().log(LogStatus.FAIL, fileName + " file is not deleted");
	 			}
	 		  
	 	}


}	
  