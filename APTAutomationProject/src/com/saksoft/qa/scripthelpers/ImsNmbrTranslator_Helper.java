package com.saksoft.qa.scripthelpers;

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
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.driverlibrary.Log;
import com.saksoft.qa.driverlibrary.XMLReader;
import com.saksoft.qa.reporter.ExtentTestManager;


public class ImsNmbrTranslator_Helper extends DriverHelper{
	
	public ImsNmbrTranslator_Helper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	WebElement ChooseCustomer_Select, Next_Button, CreateOrderService_Text, Wildcard, Search_Field, EmergencyAreaID_Text, OKbtn;
	XMLReader xml = new XMLReader("src\\com\\saksoft\\qa\\pagerepository\\Manage_Postcode.xml");

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
	
	public void selectImsTranslator (String application) throws InterruptedException, DocumentException
	{
		
		System.out.println("select Manage Colt's Network");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on 'Manage colt's Network' link");


		Thread.sleep(3000);
		
			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageColtNetworkLink")));
			Thread.sleep(3000);
			System.out.println("Mouse hovered on Manage Colt Network ");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on Manage Colt Network");
			
			Thread.sleep(2000);
			boolean isDisplayed = getwebelement(xml.getlocator("//locators/" + application + "/ManageIms")).isDisplayed();
			sa.assertTrue(isDisplayed,"Manage IMS Number Translation is not displayed ");
			
			System.out.println("Manage IMS Number Translation link verifeid");
			DriverTestcase.logger.log(LogStatus.PASS, "Step :Manage IMS Number Translation is displayed ");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ManageIms")));
			Thread.sleep(2000);
			Log.info("=== Clicked on Manage IMS Number Translation link ===");
			System.out.println("Clicked on Manage IMS Number Translation link");
			DriverTestcase.logger.log(LogStatus.PASS, "Step :Clicked on Manage IMS Number Translation link");

			
 		    DriverTestcase.logger.log(LogStatus.PASS, "Step:Navigated to Manage Number Translation");


			Log.info("=== Navigated to Manage Number Translation ===");
	}
	public void verifyManageNumberTranslationcountrypage (String application) throws InterruptedException, DocumentException
	{
		
		
		String[] CountryList= {"SE Manage Number Translation","UK Manage Number Translation","BR Manage Number Translation","PT Manage Number Translation","CH Manage Number Translation","IE Manage Number Translation","AT Manage Number Translation","IT Manage Number Translation"};
		System.out.println(""+CountryList.length);
		DriverTestcase.logger.log(LogStatus.PASS, " Step:Verifying list of countries");

		
		//Verifying list of Countries
		//try {
		List<WebElement> CountryPresent = driver.findElements(By.xpath("(//div[@class='ag-body-container ag-layout-normal']//div[@role='row'])"));
		for (WebElement Country: CountryPresent ) {
			boolean match = false;
			System.out.println("Country list displaying from application:"  +Country.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Country list displaying from application:"+Country.getText());

			
			for (int i=0; i < CountryList.length;i++)
			{
				if (Country.getText().equals(CountryList[i])) {
					match = true;
					Log.info("Country name : " + Country.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "Country name :" + Country.getText());
					System.out.println(CountryPresent);
					System.out.println("Country list matched");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Country list matched");

				}
								
			}
				Log.info("Country lists got mismatched");
				//DriverTestcase.logger.log(LogStatus.PASS, "Step:Country list not matched");
				sa.assertTrue(match);

					}
		
		
		}

	
		public void verifyManageNumberTranslation (String application ,String Countrylist) throws Exception
	{
		//selectColtnetwork("ManageColt");
		
		 //Countrylist = "BR";
		if (Countrylist.equals("BR"))
		{
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for BR");
	
		Thread.sleep(5000);
		safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/BRManageTran")));
		Log.info("Clicked on BR Manage Number Translation");
		System.out.println("Step:Clicked on BR Manage Number Translation");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for BR");

		DriverTestcase.logger.log(LogStatus.PASS, "Step:Step:Clicked on BR Manage Number Translation");
		
		}
		
		else if (Countrylist.equals("IT"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for IT");
			
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(
					xml.getlocator("//locators/" + application + "/ITManageTran")));
			Log.info("Clicked on IT Manage Number Translation");
			System.out.println("Step:Clicked on IT Manage Number Translation");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for IT");

			DriverTestcase.logger.log(LogStatus.PASS, "Step:Step:Clicked on IT Manage Number Translation");
	
		}
		
		else if (Countrylist.equals("CH"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for CH");
			
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(
					xml.getlocator("//locators/" + application + "/CHManageTran")));
			Log.info("Clicked on CH Manage Number Translation");
			System.out.println("Step:Clicked on CH Manage Number Translation");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for CH");

			DriverTestcase.logger.log(LogStatus.PASS, "Step:Step:Clicked on IT Manage Number Translation");
	
		}

		
		else if (Countrylist.equals("IE"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for IE");
			
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(
					xml.getlocator("//locators/" + application + "/IEManageTran")));
			Log.info("Clicked on IE Manage Number Translation");
			System.out.println("Step:Clicked on IE Manage Number Translation");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for IE");

			DriverTestcase.logger.log(LogStatus.PASS, "Step:Step:Clicked on IE Manage Number Translation");
	
		}
		
		
		else if (Countrylist.equals("AT"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for AT");
			
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(
					xml.getlocator("//locators/" + application + "/ATManageTran")));
			Log.info("Clicked on AT Manage Number Translation");
			System.out.println("Step:Clicked on AT Manage Number Translation");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for AT");

			DriverTestcase.logger.log(LogStatus.PASS, "Step:Step:Clicked on AT Manage Number Translation");
	
		}
		
		else if (Countrylist.equals("SE"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for SE");
			
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(
					xml.getlocator("//locators/" + application + "/SEManageTran")));
			Log.info("Clicked on SE Manage Number Translation");
			System.out.println("Step:Clicked on SE Manage Number Translation");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for SE");

			DriverTestcase.logger.log(LogStatus.PASS, "Step:Step:Clicked on SE Manage Number Translation");
	
		}
		
		else if (Countrylist.equals("UK"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for UK");
			
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(
					xml.getlocator("//locators/" + application + "/UKManageTran")));
			Log.info("Clicked on UK Manage Number Translation");
			System.out.println("Step:Clicked on UK Manage Number Translation");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for UK");

			DriverTestcase.logger.log(LogStatus.PASS, "Step:Step:Clicked on UK Manage Number Translation");
	
		}
		else if (Countrylist.equals("PT"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for PT");
			
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(
					xml.getlocator("//locators/" + application + "/PTManageTran")));
			Log.info("Clicked on PT Manage Number Translation");
			System.out.println("Step:Clicked on PT Manage Number Translation");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Manage Number Translation filed for PT");

			DriverTestcase.logger.log(LogStatus.PASS, "Step:Step:Clicked on PT Manage Number Translation");
	
		}
		


		Thread.sleep(5000);
		Wildcard = getwebelement(
				xml.getlocator("//locators/" + application + "/TranslationWildcard"));
		sa.assertTrue(Wildcard.isDisplayed(),"Wildcard Text is displayed");
		Log.info("Wildcard Text is displayed as:"
				+ Wildcard.getText());
		System.out.println("Wildcard Text is displayed as:"
				+ Wildcard.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Wildcard Text is displayed as:"+ Wildcard.getText());

		
		Search_Field = getwebelement(
				xml.getlocator("//locators/"+application+"/ImsSearcfield"));
		sa.assertTrue(Search_Field.isDisplayed(),"Search Field is not present");
		
		Log.info("======Search Field is displayed========");
		
		System.out.println("Search_Field is displayed as:"
				+ Search_Field.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Search_Field is displayed:");

		
		WebElement NumberToTranslate = getwebelement(
				xml.getlocator("//locators/" + application + "/NumberToTranslate"));
		sa.assertTrue(NumberToTranslate.isDisplayed(),"Number To Translate Field is not displayed");
		Log.info("Number To Translate Field is not displayed");
		System.out.println("Number To Translate Field is displayed as"
				+ NumberToTranslate.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Number To Translate Field is displayed as:" + NumberToTranslate.getText());
		

		 WebElement NumberToTranslated = getwebelement(
				xml.getlocator("//locators/" + application + "/NumberToTranslated"));
		 sa.assertTrue(NumberToTranslated.isDisplayed(),"Number To Translated Area Field is not displayed");
			Log.info("Number To Translated Area Field is displayed");
			
			System.out.println("Number To Translated Area Field is not displayed as:"
					+ NumberToTranslated.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Number To Translated Area Field is  displayed as:" + NumberToTranslated.getText());
        
		WebElement Country= getwebelement(
				xml.getlocator("//locators/" + application + "/Country"));
		sa.assertTrue(Country.isDisplayed(),"Country Field is not displayed");
		Log.info("Country field is displayed");
		
		System.out.println("Country field is displayed as:"
				+ Country.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Country field is displayed as:" + Country.getText());
		
		
		WebElement Carrier= getwebelement(
				xml.getlocator("//locators/" + application + "/Carrier"));
		sa.assertTrue(Carrier.isDisplayed(),"Carrier Field is not displayed");
		Log.info("Carrier Field is not displayed");
		System.out.println("Carrier Field is displayed as:" + Carrier.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Carrier Field is displayed as:" + Carrier.getText());


		WebElement NatureofAddress = getwebelement(
				xml.getlocator("//locators/" + application + "/NatureofAddress"));
		sa.assertTrue(NatureofAddress.isDisplayed(),"Nature of Address Field is not displayed");
		Log.info("Nature of Address Field is not displayed");
		
		System.out.println("Nature of Address Field is displayed as:"
				+ NatureofAddress.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Nature of Address Field is  displayed as:" + NatureofAddress.getText());
		
		WebElement PSXsyncstatus = getwebelement(
				xml.getlocator("//locators/" + application + "/PSXsyncstatus"));
		sa.assertTrue(PSXsyncstatus.isDisplayed(),"PSX sync status not displayed");
		Log.info("PSX sync status not displayed");
		
		System.out.println("PSX sync status displayed as"
				+ PSXsyncstatus.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:PSX sync status displayed as:" + PSXsyncstatus.getText());
		
	

		
		WebElement AddnumberTranslation = getwebelement(
				xml.getlocator("//locators/" + application + "/AddnumberTranslation"));
		sa.assertTrue(AddnumberTranslation.isDisplayed(),"Add number Translation Link is not displayed");
		Log.info("Add number Translation Link is displayed");
		
		System.out.println("Add number Translation Link is displayed as:"
				+ AddnumberTranslation.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Add number Translation Link is displayed as:" + AddnumberTranslation.getText());
		
		WebElement Uploadupdatefile =getwebelement(
				xml.getlocator("//locators/" + application + "/Uploadupdatefile"));
		sa.assertTrue(Uploadupdatefile.isDisplayed(),"Upload update file link is not displayed");
		Log.info("Upload update file link is displayed");
		System.out.println("Upload update file link is displayed"+Uploadupdatefile.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Upload update file link is displayed as:" +Uploadupdatefile.getText());
		
		WebElement ViewUploadHistory =getwebelement(
				xml.getlocator("//locators/" + application + "/ViewUploadHistory"));
		sa.assertTrue(ViewUploadHistory.isDisplayed(),"View Upload History link is not displayed");
		Log.info("View Upload History link is displayed");
		System.out.println("View Upload History link is displayed" +ViewUploadHistory.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "View Upload History link is displayed as:" +ViewUploadHistory.getText());

		
		Thread.sleep(5000);
		scrollToright();
		
		WebElement ViewUIHistory = getwebelement(
				xml.getlocator("//locators/" + application + "/ViewUIHistory"));
		sa.assertTrue(ViewUIHistory.isDisplayed(),"View UI History Link is not displayed");
		Log.info("View UI History Link is displayed");
		
		System.out.println("View UI History Link is displayed as:"
				+ ViewUIHistory.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:View UI History Link is displayed:" + ViewUIHistory.getText());



        WebElement SynchronizeAll =getwebelement(
				xml.getlocator("//locators/" + application + "/SynchronizeAll"));
        sa.assertTrue(SynchronizeAll.isDisplayed(),"Synchronize All Link is not displayed");
		Log.info("Synchronize All Link is displayed");

		System.out.println("Synchronize All Link is displayed as:"
				+ SynchronizeAll.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Synchronize All Link is displayed as:" + SynchronizeAll.getText());


				
	}
		
		
		public void verifyWildcardsearch (String application) throws Exception
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Verifying Wildcard * search field");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ImsSearcfield")),"*");
			Log.info("Value Entered in TextField");
			System.out.println("Value Entered in Search TextField");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Entered in Search TextField" );
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Searchbtn")));
			Log.info("Clicked on Search button");
			System.out.println("Clicked on search button");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on search button" );
			
			Thread.sleep(5000);
			
			String ExpWildcardmsg = "The following errors were encountered: At least 3 digits to be entered in the search field.";
			System.out.println("Postcode message");
			WebElement ActWildcardmsg = driver.findElement(By.xpath("//span[contains(text(),'The following errors were encountered: At least 3 digits to be entered in the search field.')]"));
			
			if (ActWildcardmsg.getText().equals(ExpWildcardmsg))
			{
				Log.info("Wildcarde Message : " + ActWildcardmsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActWildcardmsg.getText());
				System.out.println("Message Displayed as:"+ActWildcardmsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Wildcard Search Message Displayed as:" +ActWildcardmsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Wildcard Search Method Verified and Passed" );

			}
			else
			{
				sa.assertTrue(ActWildcardmsg.isDisplayed(),"Message not displayed");
				Log.info("Postcode success message not displayed");
				System.out.println("Wildcard Search message not displayed or mismatched");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Wildcard Search message not displayed or mismatched and Test Failed" );

			}
			Thread.sleep(5000);
			getwebelement(xml.getlocator("//locators/" + application + "/ImsSearcfield")).clear();
		}
		
			public void verifyAddnumberTranslationfields (String application ,String Countrylist ) throws Exception
			{
				
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Add number Translation field" );
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/AddnumberTranslation")));
				Log.info("Clicked on Add Number Translation Link");
				System.out.println("Clicked on Add Number Translation Link");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Add Number Translation Link");
				
				
				WebElement Numbertranslate = getwebelement(
						xml.getlocator("//locators/" + application + "/Numbertranslate"));
				sa.assertTrue(Numbertranslate.isDisplayed(),"Number to translate field is not displayed with Mandatory mark '*'");
				Log.info("Number to translate field is displayed with manndatory mark '*' as:"+ Numbertranslate.getText());
				System.out.println("Number to translate field is displayed with manndatory mark '*' as:" + Numbertranslate.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Number to translate field is displayed with manndatory mark '*' as:" + Numbertranslate.getText());
				
				
				WebElement Countrycode = getwebelement(
						xml.getlocator("//locators/" + application + "/Countrycode"));
				sa.assertTrue(Countrycode.isDisplayed(),"Country code field is displayed");
				Log.info("Country code field is displayed as:"+ Countrycode.getText());
				System.out.println("Country code field is displayed as:" +Countrycode.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Country code field is displayed as:" +Countrycode.getText());
				
				WebElement ActCountrycode = driver.findElement(By.xpath("//label[contains(text(),'Country Code')]/following-sibling::*[1]"));
				if (ActCountrycode.getText().equals(Countrylist))
				{
					Log.info("Country Code is same as selected country" + ActCountrycode.getText());
					System.out.println("Country Code is same as selected country:" +ActCountrycode.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "Country Code is same as selected country" + ActCountrycode.getText());
				}
				else
				{
					sa.assertTrue(ActCountrycode.isDisplayed(),"Country Code not displayed or mismatched");
					Log.info("Country Code not displayed or mismatched");
					System.out.println("Country Code not displayed or mismatched");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Country Code not displayed or mismatched" );

				}

				
				
				
				WebElement Prefix = getwebelement(
						xml.getlocator("//locators/" + application + "/Prefix"));
				sa.assertTrue(Prefix.isDisplayed(),"Country code field is displayed");
				Log.info("Country code field is displayed as:"+ Prefix.getText());
				System.out.println("Country code field is displayed as:" +Prefix.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Country code field is displayed as:" +Prefix.getText());
				
				WebElement Countrylabel = getwebelement(
						xml.getlocator("//locators/" + application + "/Countrycode"));
				sa.assertTrue(Countrylabel.isDisplayed(),"Country field is displayed not dispplayed with mandatory field '*' or field not displayed");
				Log.info("Country  field is displayed as:"+ Countrylabel.getText());
				System.out.println("Country field is displayed with mandatory field '*' as:" +Countrylabel.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Country field is displayed with mandatory field '*' as:" +Countrylabel.getText());
				
				
				
				
				if ((Countrylist.equals("SE"))||(Countrylist.equals("UK"))||(Countrylist.equals("PT"))||(Countrylist.equals("CH"))||(Countrylist.equals("IT"))||(Countrylist.equals("IE"))||
						(Countrylist.equals("AT")))
			
					
				{
				WebElement Carrier = getwebelement(
						xml.getlocator("//locators/" + application + "/CarrierLabel"));
				sa.assertTrue(Carrier.isDisplayed(),"Carrier field is not displayed");
				Log.info("Carrier field is displayed as:"+ Carrier.getText());
				System.out.println("Carrier field is displayed as:" +Carrier.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Carrier field is displayed as:" +Carrier.getText());
				}
				
				
				
				
				if ((Countrylist.equals("BR"))||(Countrylist.equals("UK")))
						
				{
				
					WebElement NatureofAddressLabel = getwebelement(
							xml.getlocator("//locators/" + application + "/NatureofAddressLabel"));
					sa.assertTrue(NatureofAddressLabel.isDisplayed(),"Nature of Address is not displayed or mandatory field mark '*' is not present");
					Log.info("Nature of Address is displayed with mandatory field mark '*' as:"+ NatureofAddressLabel.getText());
					System.out.println("Nature of Address is displayed with mandatory field mark '*' as:" +NatureofAddressLabel.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "Nature of Address is displayed with mandatory field mark '*' as:" +NatureofAddressLabel.getText());
					
				
				String[] NatureofAddress = {"Subscriber","National","International","Unknown"};
				System.out.println("Nature of address Values :" +NatureofAddress);
				
				boolean Natureofadddropdown = getwebelement(xml.getlocator("//locators/" + application + "/Natureofadddropdown")).isDisplayed();
				sa.assertTrue(Natureofadddropdown,"Nature of adddress dropdown is not displayed");
				System.out.println("Nature of adddress dropdown field is verified");
				DriverTestcase.logger.log(LogStatus.PASS, "Nature of adddress dropdown field is verified");
				
				//Verifying list of values from dropdown
				
				List<WebElement> listofaddress = driver.findElements(By.xpath("//div/span[@role='option']"));
				for (WebElement valueslist : listofaddress) 
					
				{
					System.out.println("Country list displaying from application:"  +valueslist.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Country list displaying from application:"+valueslist.getText());
					boolean match = false;
					
					for (int i = 0; i <NatureofAddress.length;i++) {
						if (valueslist.getText().equals(NatureofAddress[i]))
						{
							match = true;
							Log.info("Nature of address values displaying from page dropdown" + valueslist.getText());
							System.out.println("Nature of address values displaying from page dropdown :" + valueslist.getText());
							DriverTestcase.logger.log(LogStatus.PASS, "Nature of address values displaying from page dropdown : " + valueslist.getText());
						}
						
					}
					Log.info("Nature of address values displaying from page dropdown not matched");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Nature of address values displaying from page dropdown not matched");
					sa.assertTrue(match);

				}
				WebElement RangeLabel = getwebelement(
						xml.getlocator("//locators/" + application + "/RangeLabel"));
				sa.assertTrue(RangeLabel.isDisplayed(),"Range Checkbox is not displayed");
				Log.info("Range Checkbox Field is displayed as:" +RangeLabel.getText());
				System.out.println("Range Checkbox Field is displayed as:" +RangeLabel.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Range Checkbox Field is displayed as:" +RangeLabel.getText());
				
		
				
				}
				
				WebElement NumberToTranslatedLabel = getwebelement(
						xml.getlocator("//locators/" + application + "/NumberToTranslatedLabel"));
				sa.assertTrue(NumberToTranslatedLabel.isDisplayed(),"Nature to translated is not displayed or mandatory field mark '*' is not present");
				Log.info("Nature to translated is displayed with mandatory field mark '*' as:"+ NumberToTranslatedLabel.getText());
				System.out.println("Nature to translated is displayed with mandatory field mark '*' as:" +NumberToTranslatedLabel.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Nature to translated is displayed with mandatory field mark '*' as:" +NumberToTranslatedLabel.getText());
				
				
				
				
				WebElement OKbtn = getwebelement(
						xml.getlocator("//locators/" + application + "/OkbtnPostcode"));
				sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
				Log.info("OK btn is displayed");
				System.out.println("OK btn is displayed");
				DriverTestcase.logger.log(LogStatus.PASS, "OK button is displayed");
				
				//Cancel button is not present raised defect for that
				
//				WebElement Cancelbtn = getwebelement(
//						xml.getlocator("//locators/" + application + "/Cancelbtn"));   
//				sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
//				Log.info("Cancel button is displayed");
//				System.out.println("Cancel btn is displayed");
//				DriverTestcase.logger.log(LogStatus.PASS, "Cancel button is displayed");
//			
		}
			
			public void filladdtranslation (String application ,String Countrylist ,String TranslateNumber ,String TranslatedNumber , String PrefixNumber ,String NatureAddress,
					String CarrierNo) throws Exception
			
			{
				    SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Numbertotranslate_Text")), TranslateNumber);
					DriverTestcase.logger.log(LogStatus.PASS, "Value entered in Number to translate field");
					System.out.println("Value entered in Number to translate field");

				   
				    SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Numbertranslated_Text")), TranslatedNumber);
					DriverTestcase.logger.log(LogStatus.PASS, "Value entered in Number translated field");
					System.out.println("Value entered in Number to translated field");

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PrefixText")), PrefixNumber);
					DriverTestcase.logger.log(LogStatus.PASS, "Value entered in Prefix  field");
					System.out.println("Value entered in Prefix  field");

					
					
					if ((Countrylist.equals("SE"))||(Countrylist.equals("PT"))||(Countrylist.equals("CH"))||(Countrylist.equals("IT"))||(Countrylist.equals("IE"))||
							(Countrylist.equals("AT")))

							{
						
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OperationTextClick")));
					Thread.sleep(1000);
					
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Carrier_Text")),CarrierNo);
					System.out.println("Value entered in Carrier  field");
					SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/Carrier_Text")),Keys.TAB);
					SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/Carrier_Text")),Keys.ENTER);

					DriverTestcase.logger.log(LogStatus.PASS, "Value entered in Carrier  field");
							}



					if ((Countrylist.equals("BR"))||(Countrylist.equals("UK")))
					{
			    	 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Country_Text")),Countrylist );
					DriverTestcase.logger.log(LogStatus.PASS, "Value entered in Country field");
		 	

					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/NoAddDD")));
					DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Nature of Address Dropdown");
					
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/NoAddTextbox")), NatureAddress);
					SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/NoAddTextbox")),Keys.TAB);
					SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/NoAddTextbox")),Keys.ENTER);
					DriverTestcase.logger.log(LogStatus.PASS, "Value Entered in Nature of Address Dropdown");
					
					ClickOnCheckbox(getwebelement(xml.getlocator("//locators/" + application + "/RangeFlag")), "CHECK");
					System.out.println("Clicked on Range Checkbox");

					DriverTestcase.logger.log(LogStatus.PASS, "Range Check box checked");
					
					
					
					}


					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OkbtnPostcode")));
					System.out.println("Clicked on OK button");

					DriverTestcase.logger.log(LogStatus.PASS, "Clicked on OK button");
	

					
					Thread.sleep(2000);
					
					
					String ExpTranslationmsg = "NumberTranslation successfully created.Sync started successfully. Please check the sync status of number translation.";
					System.out.println("Translation message");
					WebElement ActTranslationmsg = driver.findElement(By.xpath("//span[contains(text(),'NumberTranslation successfully created.Sync started successfully. Please check the sync status of number translation.')]"));
					
					if (ActTranslationmsg.getText().equals(ExpTranslationmsg))
					{
						Log.info("Number Translation message :" + ActTranslationmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Number Translation message :" + ActTranslationmsg.getText());
						System.out.println("Number Translation message Displayed as:"+ActTranslationmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Number Translation message Displayed as:" +ActTranslationmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Number Translation message Verified" );

					}
					else
					{
						sa.assertTrue(ActTranslationmsg.isDisplayed(),"Number Translation message not displayed or mismatched");
						Log.info("Number Translation message not displayed or mismatched");
						System.out.println("Number Translation message not displayed or mismatched");
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Number Translation message not displayed or mismatched" );

					}
					//verifyManageNumberTranslation("ManageColt");

					
					String[] ExpectedActiondrop = {"View","Edit","Delete","Synchronize"};
					System.out.println("Action DropDown values " +ExpectedActiondrop);
					
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
					System.out.println("Clicked on Action Dropdown");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Action Dropdown" );
					
					
					
					
					//Verifying list of values from Action dropdown

					
					List<WebElement> ActualActionDD = driver.findElements(By.xpath("//a[@role='button']"));
					for (WebElement Dropdownlist : ActualActionDD) 
						
					{
						System.out.println("Country list displaying from application:"  +Dropdownlist.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Country list displaying from application:"+Dropdownlist.getText());
						boolean match = false;
						
						for (int i = 0; i <ExpectedActiondrop.length;i++) {
							if (Dropdownlist.getText().equals(ExpectedActiondrop[i]))
							{
								match = true;
								Log.info("Action Drop down values" + Dropdownlist.getText());
								System.out.println("Action Drop down values as:" + Dropdownlist.getText());
								DriverTestcase.logger.log(LogStatus.PASS, "Action Drop down values are as expected:"  +Dropdownlist.getText());
							}
							
						}
						sa.assertTrue(match);

					}
						 

			}
			
			public void verifyAddnumbertranslationfunction (String application ,String Countrylist) throws Exception 
			
			{
				//Verifying the values in View
				Thread.sleep(5000);
				
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying View operation" );
				System.out.println("Verifying View operation ");

				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeCheckbox")));
				Log.info("Clicked on checkbox");
				System.out.println("Clicked on checkbox");
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Checkbox");
				Thread.sleep(10000);
				
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
				System.out.println("Clicked on Action Dropdown");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Action Dropdown" );
				


				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/TranslationView")));
				System.out.println("Clicked on View");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on View in Dropdown" );
				
				Thread.sleep(2000);
				WebElement Numbertranslate = getwebelement(
						xml.getlocator("//locators/" + application + "/Numbertranslate"));
				sa.assertTrue(Numbertranslate.isDisplayed(),"Number to translate field is not displayed with Mandatory mark '*'");
				Log.info("Number to translate field is displayed with manndatory mark '*' as:"+ Numbertranslate.getText());
				System.out.println("Number to translate field is displayed with manndatory mark '*' as:" + Numbertranslate.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Number to translate field is displayed with manndatory mark '*' as:" + Numbertranslate.getText());
				
				
				WebElement Countrycode = getwebelement(
						xml.getlocator("//locators/" + application + "/Countrycode"));
				sa.assertTrue(Countrycode.isDisplayed(),"Country code field is displayed");
				Log.info("Country code field is displayed as:"+ Countrycode.getText());
				System.out.println("Country code field is displayed as:" +Countrycode.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Country code field is displayed as:" +Countrycode.getText());
				
				WebElement ActCountrycode = driver.findElement(By.xpath("//label[contains(text(),'Country Code')]/following-sibling::*[1]"));
				if (ActCountrycode.getText().equals("BR"))
				{
					Log.info("Country Code is same as selected country" + ActCountrycode.getText());
					System.out.println("Country Code is same as selected country:" +ActCountrycode.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "Country Code is same as selected country" + ActCountrycode.getText());
				}
				else
				{
					sa.assertTrue(ActCountrycode.isDisplayed(),"Country Code not displayed or mismatched");
					Log.info("Country Code not displayed or mismatched");
					System.out.println("Country Code not displayed or mismatched");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Country Code not displayed or mismatched" );

				}

				
				
				
				WebElement Prefix = getwebelement(
						xml.getlocator("//locators/" + application + "/Prefix"));
				sa.assertTrue(Prefix.isDisplayed(),"Country code field is displayed");
				Log.info("Country code field is displayed as:"+ Prefix.getText());
				System.out.println("Country code field is displayed as:" +Prefix.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Country code field is displayed as:" +Prefix.getText());
				
				WebElement Countrylabel = getwebelement(
						xml.getlocator("//locators/" + application + "/Prefix"));
				sa.assertTrue(Countrylabel.isDisplayed(),"Country field is displayed not dispplayed with mandatory field '*' or field not displayed");
				Log.info("Country  field is displayed as:"+ Countrylabel.getText());
				System.out.println("Country field is displayed with mandatory field '*' as:" +Countrylabel.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Country field is displayed with mandatory field '*' as:" +Countrylabel.getText());
				
				if ((Countrylist.equals("BR"))||(Countrylist.equals("UK")))
				{
				
				WebElement NatureofAddressLabel = getwebelement(
						xml.getlocator("//locators/" + application + "/NatureofAddressLabel"));
				sa.assertTrue(NatureofAddressLabel.isDisplayed(),"Nature of Address is not displayed or mandatory field mark '*' is not present");
				Log.info("Nature of Address is displayed with mandatory field mark '*' as:"+ NatureofAddressLabel.getText());
				System.out.println("Nature of Address is displayed with mandatory field mark '*' as:" +NatureofAddressLabel.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Nature of Address is displayed with mandatory field mark '*' as:" +NatureofAddressLabel.getText());
				}
				
				if ((Countrylist.equals("SE"))||(Countrylist.equals("UK"))||(Countrylist.equals("PT"))||(Countrylist.equals("CH"))||(Countrylist.equals("IT"))||(Countrylist.equals("IE"))||
						(Countrylist.equals("AT")))
				{

				
				WebElement Carrier = getwebelement(
						xml.getlocator("//locators/" + application + "/Carrier"));
				sa.assertTrue(Carrier.isDisplayed(),"Carrier field is not displayed");
				Log.info("Carrier field is displayed as:"+ Carrier.getText());
				System.out.println("Carrier field is displayed as:" +Carrier.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Carrier field is displayed as:" +Carrier.getText());
				}
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/Closesign")));
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Close button");

				Thread.sleep(2000);
				
				
				//Performing edit operation
				
				
				
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Edit operation and Update message" );
				System.out.println("Verifying Edit operation and Update message");
				Thread.sleep(10000);
				

				

//				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeCheckbox")));
//				Log.info("Clicked on checkbox");
//				System.out.println("Clicked on checkbox");
//				DriverTestcase.logger.log(LogStatus.PASS, "Clickedx on Checkbox");
//				
//				Thread.sleep(5000);
//				
				
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
				System.out.println("Clicked on Action Dropdown");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Action Dropdown" );
				
				
				
				
				
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeActionEdit")));
				System.out.println("Clicked on Edit Dropdown");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Edit Dropdown" );
			
				getwebelement(xml.getlocator("//locators/" + application + "/PrefixText")).clear();
	
				 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PrefixText")), "");
				 DriverTestcase.logger.log(LogStatus.PASS, "Prefix Value changed in field");
				 
				 safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
				 DriverTestcase.logger.log(LogStatus.PASS, "Clicked on OK button");
				 
				 Thread.sleep(10000);
				 
				 String ExpUpdateTranslationmsg = "NumberTranslation successfully updated.Sync started successfully. Please check the sync status of number translation.";
					System.out.println("Number Update Translation Message");
					WebElement ActUpdateTranslationmsg = driver.findElement(By.xpath("//span[text()='NumberTranslation successfully updated.Sync started successfully. Please check the sync status of number translation.']"));
					
					if (ActUpdateTranslationmsg.getText().equals(ExpUpdateTranslationmsg))
					{
						Log.info("Number Translation message :" + ActUpdateTranslationmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActUpdateTranslationmsg.getText());
						System.out.println("Number Translation message Displayed as:"+ActUpdateTranslationmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Number Translation update message Displayed as:" +ActUpdateTranslationmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Number Translation update message Verified" );

					}
					else
					{
						sa.assertTrue(ActUpdateTranslationmsg.isDisplayed(),"Number Translation Update message not displayed or mismatched");
						Log.info("Number Translation message not displayed or mismatched");
						System.out.println("Number Translation message not displayed or mismatched");
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Number Translation message not displayed or mismatched" );

					}
					
					//Synchronize operation
					
				
					
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Synchronize operation and synchronize message" );
					System.out.println("Verifying Synchronize operation and synchronize message");

					
					Thread.sleep(10000);
					
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeCheckbox")));
					Log.info("Clicked on checkbox");
					System.out.println("Clicked on checkbox");
					DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Checkbox");
					

					
					Thread.sleep(5000);
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
					System.out.println("Clicked on Action Dropdown");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Action Dropdown" );
					
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/Synchronize")));
					System.out.println("Clicked on Synchronize Dropdown");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Synchronize Dropdown" );
					
					Thread.sleep(1000);
					
					String ExpSyncTranslationmsg = "Sync started successfully. Please check the sync status of number translation.";
					System.out.println("Number Update Sync Translation Message");
					WebElement ActSyncTranslationmsg = driver.findElement(By.xpath("//span[text()='Sync started successfully. Please check the sync status of number translation.']"));
					
					if (ActSyncTranslationmsg.getText().equals(ExpSyncTranslationmsg))
					{
						Log.info("Number Translation message :" + ActSyncTranslationmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActSyncTranslationmsg.getText());
						System.out.println("Number Translation Synchronise message Displayed as:"+ActSyncTranslationmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Number Translation Synchronise message Displayed as:" +ActSyncTranslationmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Number Translation Synchronise message Verified" );

					}
					else
					{
						sa.assertTrue(ActUpdateTranslationmsg.isDisplayed(),"Number Translation Synchronise message not displayed or mismatched");
						Log.info("Number Translation Synchronise message not displayed or mismatched");
						System.out.println("Number Translation Synchronise message not displayed or mismatched");
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Number Translation Synchronise message not displayed or mismatched" );

					}
					
					
					
					

					//Delete Operation
					Thread.sleep(5000);
					
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Verifying Delete operation and message" );
					System.out.println("Verifying Delete operation and message");


					Thread.sleep(10000);
					
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
					System.out.println("Clicked on Action Dropdown");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Action Dropdown" );
					
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeActionDelete")));
					System.out.println("Clicked on Delete Dropdown");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Delete Dropdown" );
					
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeDeletebtn")));
					Log.info("Clicked on Delete button ");
				    System.out.println("Clicked on Delete button");
					DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Delete button");
					
					Thread.sleep(1000);
					
					DriverTestcase.logger.log(LogStatus.PASS, "Verifying Delete  Message");
					
					Thread.sleep(10000);
					String ExpTranDeletemsg = "NumberTranslation successfully marked for deletion.";
					System.out.println("NumberTranslation Delete message");
					WebElement ActTranDeletemsg = driver.findElement(By.xpath("//span[contains(text(),'NumberTranslation successfully marked for deletion.')]"));
					
					if (ActTranDeletemsg.getText().equals(ExpTranDeletemsg))
					{
						Log.info("Postcode Delete Message : " + ActTranDeletemsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "NumberTranslation Message :  :" + ActTranDeletemsg.getText());
						 System.out.println("NumberTranslation Delete message as:"+ActTranDeletemsg.getText());
							DriverTestcase.logger.log(LogStatus.PASS, "NumberTranslation Delete message as:" +ActTranDeletemsg.getText());
							DriverTestcase.logger.log(LogStatus.PASS, "NumberTranslation Delete message verified");


					}
					else
					{
						sa.assertTrue(ActTranDeletemsg.isDisplayed(),"NumberTranslation Message not displayed");
						Log.info("NumberTranslation deleted message not displayed");
						 System.out.println("PNumberTranslation Delete message as:"+ActTranDeletemsg.getText());
							DriverTestcase.logger.log(LogStatus.PASS, "NumberTranslation Delete message verified");

					}
				
				
	
			}
			
			
			
			public void uploadUpdatefile (String application) throws Exception 
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Verifying Upload Update file Service Area Link and Fields");
				
				safeJavaScriptClick(getwebelement(
						xml.getlocator("//locators/" + application + "/Uploadupdatefile")));
				Log.info("Clicked on Upload update file");
				System.out.println("Clicked on Upload update file");
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Upload update file");

				Thread.sleep(2000);
				
				WebElement ChoseFile = (getwebelement(
						xml.getlocator("//locators/" + application + "/ChosefileTrans")));
				sa.assertTrue(ChoseFile.isDisplayed(),"Chose File btn is not displayed");
				Log.info("ChoseFile is displayed");
				 System.out.println("ChoseFile is displayed");
					DriverTestcase.logger.log(LogStatus.PASS, "Verified Chose File button is diplayed");
					
					 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ChosefileTrans")), "E:\\numbertranslation_SE.csv");
						DriverTestcase.logger.log(LogStatus.PASS, "File uploaded");


					
					
					
				WebElement OKbtn = getwebelement(
						xml.getlocator("//locators/" + application + "/OkbtnPostcode"));
				
				sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
				Log.info("OK btn is displayed");
				 System.out.println("OK btn is displayed");
					DriverTestcase.logger.log(LogStatus.PASS, "Verified OK button is diplayed");			 
				 
				 
				 safeJavaScriptClick(getwebelement(
						xml.getlocator("//locators/" + application + "/OkbtnPostcode")));
				 System.out.println("Clicked on OK button");
					DriverTestcase.logger.log(LogStatus.PASS, "Clicked on OK button");
					
					
					String ExpUpdatefiledmsg = "NumberTranslation successfully created.";
					System.out.println("Upload file message");
					Thread.sleep(2000);
					WebElement ActUpdatefilemsg = driver.findElement(By.xpath("//span[contains(text(),'NumberTranslation successfully created.')]"));
					
					if (ActUpdatefilemsg.getText().equals(ExpUpdatefiledmsg))
					{
						Thread.sleep(1000);
						Log.info("Upload Message : " + ActUpdatefilemsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActUpdatefilemsg.getText());
						System.out.println("Message Displayed as:"+ActUpdatefilemsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Wildcard Search Message Displayed as:" +ActUpdatefilemsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Wildcard Search Method Verified and Passed" );

					}
					else
					{
						sa.assertTrue(ActUpdatefilemsg.isDisplayed(),"Message not displayed");
						Log.info("Update success message not displayed");
						System.out.println("Update success message not displayed");
						DriverTestcase.logger.log(LogStatus.PASS, "Step:Update success message not displayed" );

					}

			}
			
			public void viewuploadHistory (String application) throws Exception 
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Verifying View upload History link and fields");
				
				
				safeJavaScriptClick(getwebelement(
						xml.getlocator("//locators/" + application + "/ViewUploadHistory")));
				Log.info("Clicked on View Upload History");
				System.out.println("Clicked on View Upload History");
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on View Upload History");
				
				Thread.sleep(3000);
				
				safeJavaScriptClick(getwebelement(
						xml.getlocator("//locators/" + application + "/ViewUploadHisClose")));
				Log.info("Clicked on View Upload History");
				System.out.println("Clicked on View Upload History close");
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on View Upload History close");
				Thread.sleep(2000);
			}
			
			public void viewUIHistory (String application, String OperationDropDvalue,String Userfield,String NumberToTranslate) throws Exception 
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Verifying View UI History link and fields");
				
				
				safeJavaScriptClick(getwebelement(
						xml.getlocator("//locators/" + application + "/ViewUIHistory")));
				Log.info("Clicked on View Upload History");
				System.out.println("Clicked on View UI History");
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on View UI History");
				
				WebElement Operation = getwebelement(
						xml.getlocator("//locators/" + application + "/ Operation"));
				sa.assertTrue( Operation.isDisplayed()," Operation DropDown field is not displayed");
				Log.info("Operation DropDown field is displayed as:"+ Operation.getText());
				System.out.println("Operation DropDown field is displayed as:" +Operation.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Operation DropDown field is displayed as:" +Operation.getText());
				
				WebElement User = getwebelement(
						xml.getlocator("//locators/" + application + "/ViewUIUser"));
				sa.assertTrue( User.isDisplayed()," User field is not displayed");
				Log.info("User field is displayed as:"+ User.getText());
				System.out.println("User field is displayed as:" +User.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "User field is displayed as:" +User.getText());
				
				
				WebElement NumbertoTranslate = getwebelement(
						xml.getlocator("//locators/" + application + "/ViewUINumbertoTrans"));
				sa.assertTrue( NumbertoTranslate.isDisplayed()," Number to Translate field is not displayed");
				Log.info("Number to Translate field is displayed as:"+ NumbertoTranslate.getText());
				System.out.println("Number to Translate field is displayed as:" +NumbertoTranslate.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Number to Translate field is displayed as:" +NumbertoTranslate.getText());
				
				//Verifying values in from Operation Dropdown
				
				
				
				String[] ExpectedOperationdrop = {"Add","Update","In Deletion","Deleted from APT/PSX"};
				System.out.println("Action DropDown values " +ExpectedOperationdrop);
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OperationText")));
				System.out.println("Clicked on Operation Dropdown");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Operation Dropdown" );
				
				
				
				
				DriverTestcase.logger.log(LogStatus.PASS, "Verifying values from Operation Dropdown");
				
				List<WebElement> ActualOperationDD = driver.findElements(By.xpath("//span[@role='option']"));
				for (WebElement Dropdownlist : ActualOperationDD) 
					
				{
					System.out.println("Country list displaying from application:"  +Dropdownlist.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "Step:Country list displaying from application:"+Dropdownlist.getText());
					boolean match = false;
					
					for (int i = 0; i <ExpectedOperationdrop.length;i++) {
						if (Dropdownlist.getText().equals(ExpectedOperationdrop[i]))
						{
							match = true;
							Log.info("Operation Drop down values" + Dropdownlist.getText());
							System.out.println("Operation Drop down values as:" + Dropdownlist.getText());
							DriverTestcase.logger.log(LogStatus.PASS, "Operation Drop down values as:"  +Dropdownlist.getText());
						}
						
					}
					sa.assertTrue(match);

				}
				
				//performing operation on Dropdown
				Thread.sleep(5000);
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OperationTextClick")));
				System.out.println("Clicked on Operation Dropdown");
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Operation Dropdown");

				

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/OperationText")),OperationDropDvalue);
				System.out.println("Selected Value from Dropdown");
				DriverTestcase.logger.log(LogStatus.PASS, "Selected Value from Dropdown");

				SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/OperationText_1")),Keys.TAB);
				SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/OperationText_1")),Keys.ENTER);
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/ViewUISearch")));
				System.out.println("Clicked on Search");

				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Search button");
				
				DriverTestcase.logger.log(LogStatus.PASS, "Values populated as per Option selected from Dropdown");
				
				
				//Enter User Name and click on search
				Thread.sleep(3000);
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OperationTextClick")));

				//getwebelement(xml.getlocator("//locators/" + application + "/OperationText_2")).clear();
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/OperationText")), OperationDropDvalue);
				
				DriverTestcase.logger.log(LogStatus.PASS, "Selected Value from Dropdown");
				SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/OperationText_1")),Keys.TAB);
				SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/OperationText_1")),Keys.ENTER);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/UserText")), Userfield);
				DriverTestcase.logger.log(LogStatus.PASS, "Enetered Value in User field");
				Thread.sleep(10000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Numbertotranslate_Text")), NumberToTranslate);
				DriverTestcase.logger.log(LogStatus.PASS, "Enetered Value in Number to translate field");
				Thread.sleep(2000);
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/ViewUISearch")));
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Search button");
				
				DriverTestcase.logger.log(LogStatus.PASS, "Values populated as per Option selected from Dropdown and Value provided in Number to translate");

				Thread.sleep(3000);
				
				safeJavaScriptClick(getwebelement(
						xml.getlocator("//locators/" + application + "/ViewUploadHisClose")));
				Log.info("Clicked on View Upload History");
				System.out.println("Clicked on View Upload History close");
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on View Upload History close");
				Thread.sleep(2000);
		
				

				
							
			}


			
			public void downlaodTranslationlink (String application) throws Exception 
			{
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/DownloadNumberTranslation")));
				Log.info("Clicked Download Number Translation");
				System.out.println("Clicked Download Number Translation");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked Download Number Translation" );
				
			}

			public void synchronizeall (String application) throws Exception 
			{
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SynchronizeAll")));
				Log.info("Clicked on Synchronize ");
				System.out.println("Clicked on Synchronize ");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Synchronize " );
				
			}


			
			
			


			


	
	public void addPostcode (String application) throws Exception 
	{
	
		//selectColtnetwork(application);
		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/Addpostcode")));
		Thread.sleep(5000);
		System.out.println("Clicked on Add Postcode");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Add Postcode");

		
		String Countrylist = "Belgium";
		if(Countrylist.equals("Belgium"))
		{
		
		WebElement EmergencyAreaIDSubcom =getwebelement(
				xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField"));
		sa.assertTrue(EmergencyAreaIDSubcom.isDisplayed(),"Emergency Area ID Subcom Field is not displayed");
		Log.info("Emergency Area ID Subcom field is displayed");
		System.out.println("Emergency Area ID Subcom filed is displayed ");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Area ID Subcom field is displayed as: " +EmergencyAreaIDSubcom.getText());

		
		WebElement NTServiceAreaPostcode = getwebelement(
				xml.getlocator("//locators/" + application + "/NTServiceAreaPostcode_Text"));
		sa.assertTrue(NTServiceAreaPostcode.isDisplayed(),"NT Service Area Postcode Field is not displayed");
		Log.info("NT Service Area Postcode field is displayed");
		System.out.println("NT Service Area Post code:"+NTServiceAreaPostcode.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:NT Service Area Postcode field is displayed as:" +NTServiceAreaPostcode.getText());


		
		WebElement ToimplemetSwitch = getwebelement(
				xml.getlocator("//locators/" + application + "/ToimplemetSwitch_Text"));
		sa.assertTrue(ToimplemetSwitch.isDisplayed(),"To implement Switch  Field is not displayed");
		Log.info("To implement Switch field is displayed");
		System.out.println("To implement Switch field is displayed as:"+ToimplemetSwitch.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:To implement Switch field is displayed as:" +ToimplemetSwitch.getText());


		
		WebElement CityTranslatorNmbr = getwebelement(
				xml.getlocator("//locators/" + application + "/CityTranslatorNmbr_Text"));
		sa.assertTrue(CityTranslatorNmbr.isDisplayed(),"City Translator Nmbr Field is not displayed");
		Log.info("City Translator Nmbr field is displayed");
		System.out.println("City Translator Nmbr field is displayed as:"+CityTranslatorNmbr.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:City Translator Nmbr field is displayed as:" +ToimplemetSwitch.getText());

	
		WebElement SubAreaProvince_1 = getwebelement(
				xml.getlocator("//locators/" + application + "/SubAreaProvince_1_Text"));
		sa.assertTrue(SubAreaProvince_1.isDisplayed(),"Sub Area Province1 Field is not displayed");
		Log.info("Upload Nt link field is displayed");
		System.out.println("Sub Area Province Translator Nmbr field is displayed as:"+SubAreaProvince_1.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Sub Area Province Translator Nmbr field is displayed as:" +SubAreaProvince_1.getText());


		
		WebElement SubArea1D = getwebelement(
				xml.getlocator("//locators/" + application + "/SubArea1D_Text"));
		 sa.assertTrue(SubArea1D.isDisplayed(),"Sub Area 1D Field is not displayed");
		Log.info("Sub Area 1D field is displayed");
		System.out.println("SubArea1D field is displayed as:"+SubArea1D.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:SubArea1D field is displayed as:" +SubArea1D.getText());


		
		WebElement SubArea2Community = getwebelement(
				xml.getlocator("//locators/" + application + "/SubArea2Community_Text"));
		 sa.assertTrue(SubArea2Community.isDisplayed(),"Sub Area2 Community Field is not displayed");
		Log.info("Sub Area2 Community field is displayed");
		System.out.println("Sub Area 2 Community field is displayed as:" +SubArea2Community.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Sub Area 2 Community field is displayed as:" +SubArea2Community.getText());


		
		WebElement SubArea2ID = getwebelement(
				xml.getlocator("//locators/" + application + "/SubArea2ID_Text"));
		sa.assertTrue(SubArea2ID.isDisplayed(),"Sub Area2 ID Field is not displayed");
		Log.info("Sub Area2 ID field is displayed");
		System.out.println("Sub Area 2 ID field is displayed as:"+SubArea2ID.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Sub Area 2 ID field is displayed as:" +SubArea2ID.getText());


		
		WebElement SubArea3B = getwebelement(
				xml.getlocator("//locators/" + application + "/SubArea3B_Text"));
		 sa.assertTrue(SubArea3B.isDisplayed(),"Sub Area3B Text Field is not displayed");
		Log.info("Sub Area3B Text field is displayed");
		System.out.println("Sub Area 3B field is displayed as:"+SubArea3B.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Sub Area 3B field is displayed as:" +SubArea3B.getText());

		
		WebElement SubAreaZipcode = getwebelement(
				xml.getlocator("//locators/" + application + "/SubAreaZipcode_Text"));
		sa.assertTrue(SubAreaZipcode.isDisplayed(),"Sub Area Zipcode Field is not displayed");
		Log.info("Sub Area Zipcode field is displayed");
		System.out.println("Sub Area Zipcode field is displayed as:"+SubAreaZipcode.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Sub Area Zipcode field is displayed as:" +SubAreaZipcode.getText());


		
		WebElement EmergencyAreaSub = getwebelement(
				xml.getlocator("//locators/" + application + "/EmergencyAreaSub_Text"));
		sa.assertTrue(EmergencyAreaSub.isDisplayed(),"Emergency Area Sub Field is not displayed");
		Log.info("Emergency Area Sub Field is  displayed");
		System.out.println("Emergency Area Sub field is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Area Sub field is displayed");


		
		WebElement EmergencyNmbrKey = getwebelement(
				xml.getlocator("//locators/" + application + "/EmergencyNmbrKey_Text"));
		sa.assertTrue(EmergencyNmbrKey.isDisplayed(),"Emergency Nmbr Key Text Field is not displayed");
		Log.info("Emergency Nmbr Key is displayed");
		System.out.println("Emergency Nmbr Key is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Nmbr Key is displayed");


		
				
		WebElement ActualProvider = getwebelement(
				xml.getlocator("//locators/" + application + "/ActualProvider_Text"));
		sa.assertTrue(ActualProvider.isDisplayed(),"Actual Provider Text Field is not displayed");
		Log.info("Actual Provider Text Field is displayed");
		System.out.println("Actual Provider Text Field is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Actual Provider Text Field is displayed");

		WebElement DummyCode_Text = getwebelement(
				xml.getlocator("//locators/" + application + "/DummyCode_Text"));
		sa.assertTrue(DummyCode_Text.isDisplayed(),"Dummy Code Field is not displayed");
		Log.info("Dummy Code Field is not displayed");
		System.out.println("Dummy Code field is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Dummy Code field is displayed");


		
		WebElement EmptyTextbox = getwebelement(
				xml.getlocator("//locators/" + application + "/EmptyTextbox"));
		sa.assertTrue(EmptyTextbox.isDisplayed(),"Empty Text box Field is not displayed");
		Log.info("Empty Text box Field is displayed");
		System.out.println("Empty Text box field is displayed as:"+EmptyTextbox.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Empty Text box field is displayed as:");


		
		WebElement forwardarrow = getwebelement(
				xml.getlocator("//locators/" + application + "/forwardarrow"));
		 sa.assertTrue(forwardarrow.isDisplayed(),"forward arrow button Field is not displayed");
		Log.info("forward arrow button Field is  displayed");
		System.out.println("forward arrow field is displayed as >>");
		DriverTestcase.logger.log(LogStatus.PASS, "forward arrow button field is displayed as: >>");


		
		WebElement BackwardArrow = getwebelement(
				xml.getlocator("//locators/" + application + "/BackwardArrow"));
		sa.assertTrue(BackwardArrow.isDisplayed(),"Back ward Arrow is not displayed");
		Log.info("Back ward Arrow is  displayed");
		System.out.println("Backward Arrow field is displayed as <<");
		DriverTestcase.logger.log(LogStatus.PASS, "Backward arrow button field is displayed as: <<");


		
		WebElement OKbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/OKbtn"));
		sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
		Log.info("OK btn is displayed");
		System.out.println("OK btn is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "OK button is displayed");


		
		WebElement Cancelbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/Cancelbtn"));
		sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
		Log.info("Cancel button is displayed");
		System.out.println("Cancel btn is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Cancel button is displayed");

		
		
		//Filling value in Mandatory fields
		Thread.sleep(5000);
		
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetering Values to creat postcode");
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField")),"161");//EmergencyAreaIDSub);
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetered in Mandatory field: Emergency area");
		

		

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Clciked on OK  button");

		Thread.sleep(10000);
		
		String ExpPostcodemsg = "Postcode successfully created.";
		System.out.println("Postcode message");
		WebElement ActPostcodemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode successfully created.')]"));
		
		if (ActPostcodemsg.getText().equals(ExpPostcodemsg))
		{
			Log.info("Postcode Message : " + ActPostcodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodemsg.getText());
			System.out.println("Message Displayed as:"+ActPostcodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Message Displayed as:" +ActPostcodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:PostCode Success Method Verified" );

		}
		else
		{
			sa.assertTrue(ActPostcodemsg.isDisplayed(),"Message not displayed");
			Log.info("Postcode success message not displayed");
			System.out.println("Postcode success message not displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Postcode success message not displayed" );

		}
		Thread.sleep(5000);
		}
		else if(Countrylist.equals("Denmark"))
		{
			WebElement EmergencyAreaIDSubcom =getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField"));
			sa.assertTrue(EmergencyAreaIDSubcom.isDisplayed(),"Emergency Area ID (Kommunenummer) Field is not displayed");
			Log.info("Emergency Area ID (Kommunenummer)field is displayed");
			System.out.println("Emergency Area ID (Kommunenummer)field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Area ID (Kommunenummer)field is displayed" );

			
			WebElement NTServiceAreaPostcode = getwebelement(
					xml.getlocator("//locators/" + application + "/NTServiceAreaPostcode_Text"));
			sa.assertTrue(NTServiceAreaPostcode.isDisplayed(),"NT Service Area Postcode Field is not displayed");
			Log.info("NT Service Area Postcode field is displayed");
			System.out.println("NT Service Area Post code:"+NTServiceAreaPostcode.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:NT Service Area Postcode field is displayed as:" +NTServiceAreaPostcode.getText());


			
			WebElement ToimplemetSwitch = getwebelement(
					xml.getlocator("//locators/" + application + "/ToimplemetSwitch_Text"));
			sa.assertTrue(ToimplemetSwitch.isDisplayed(),"To implement Switch  Field is not displayed");
			Log.info("To implement Switch field is displayed");
			System.out.println("To implement Switch field is displayed as:"+ToimplemetSwitch.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:To implement Switch field is displayed");


			
			WebElement CityTranslatorNmbr = getwebelement(
					xml.getlocator("//locators/" + application + "/CityTranslatorNmbr_Text"));
			sa.assertTrue(CityTranslatorNmbr.isDisplayed(),"City Translator Nmbr Field is not displayed");
			Log.info("City Translator Nmbr field is displayed");
			System.out.println("City Translator Nmbr field is displayed as:"+CityTranslatorNmbr.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:City Translator Nmbr field is displayed");
		
			WebElement SubAreaProvince_1 = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaProvince_1_Text"));
			sa.assertTrue(SubAreaProvince_1.isDisplayed(),"SubArea1 (SubArea1) Nmbr field is not displayed");
			Log.info("SubArea1 (SubArea1) Nmbr field is displayed");
			System.out.println("SubArea1 (SubArea1) Nmbr field is displayed as:"+SubAreaProvince_1.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:SubArea1 (SubArea1) Nmbr field is displayed");


			
			WebElement SubArea1D = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea1D_Text"));
			 sa.assertTrue(SubArea1D.isDisplayed(),"SubArea1-ID (SubArea1-ID) field is displayed as");
			Log.info("SubArea1-ID (SubArea1-ID) field is displayed as");
			System.out.println("SubArea1-ID (SubArea1-ID) field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "SubArea1-ID (SubArea1-ID) field is displayed");

			
			WebElement SubArea2Community = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2Community_Text"));
			 sa.assertTrue(SubArea2Community.isDisplayed(),"SubArea2 (SubArea2) Community Field is not displayed");
			Log.info("SubArea2 (SubArea2) field is displayed");
			System.out.println("SubArea2 (SubArea2)field is displayed ");
			DriverTestcase.logger.log(LogStatus.PASS, "SubArea2 (SubArea2)field is displayed ");


			
			WebElement SubArea2ID = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2ID_Text"));
			sa.assertTrue(SubArea2ID.isDisplayed(),"Sub Area2 ID Field is not displayed");
			Log.info("Sub Area2 ID field is displayed");
			System.out.println("Sub Area 2 ID field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Sub Area 2 ID field is displayed");


			
			WebElement SubArea3B = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea3B_Text"));
			 sa.assertTrue(SubArea3B.isDisplayed(),"SubArea3 (SubArea3) Text Field is not displayed");
			Log.info("SubArea3 (SubArea3) Text field is displayed");
			System.out.println("SubArea3 (SubArea3) field is displayed as:"+SubArea3B.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "SubArea3 (SubArea3) field is displayed");


			
			WebElement SubAreaZipcode = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaZipcode_Text"));
			sa.assertTrue(SubAreaZipcode.isDisplayed(),"SubArea3-ID (SubArea3-ID) Field is not displayed");
			Log.info("SubArea3-ID (SubArea3-ID) field is displayed");
			System.out.println("SubArea3-ID (SubArea3-ID) field is displayed as:"+SubAreaZipcode.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "SubArea3-ID (SubArea3-ID) field is displayed");


			
			WebElement EmergencyAreaSub = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaSub_Text"));
			sa.assertTrue(EmergencyAreaSub.isDisplayed(),"Emergency Area (Kommune) Field is not displayed");
			Log.info("Emergency Area (Kommune) Field is  displayed");
			System.out.println("Emergency Area (Kommune) field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Emergency Area (Kommune) field is displayed");


			
			WebElement EmergencyNmbrKey = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyNmbrKey_Text"));
			sa.assertTrue(EmergencyNmbrKey.isDisplayed(),"Emergency Nmbr Key Text Field is not displayed");
			Log.info("Emergency Nmbr Key is displayed");
			System.out.println("Emergency Nmbr Key is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Emergency Nmbr Key is displayed");


			
					
			WebElement ActualProvider = getwebelement(
					xml.getlocator("//locators/" + application + "/ActualProvider_Text"));
			sa.assertTrue(ActualProvider.isDisplayed(),"Actual Provider Text Field is not displayed");
			Log.info("Actual Provider Text Field is displayed");
			System.out.println("Actual Provider Text Field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Actual Provider Text Field is displayed");

			
			WebElement DummyCode_Text = getwebelement(
					xml.getlocator("//locators/" + application + "/DummyCode_Text"));
			sa.assertTrue(DummyCode_Text.isDisplayed(),"Dummy Code Field is not displayed");
			Log.info("Dummy Code Field is not displayed");
			System.out.println("Dummy Code field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Dummy Code field is displayed");


			
			WebElement EmptyTextbox = getwebelement(
					xml.getlocator("//locators/" + application + "/EmptyTextbox"));
			sa.assertTrue(EmptyTextbox.isDisplayed(),"Empty Text box Field is not displayed");
			Log.info("Empty Text box Field is displayed");
			System.out.println("Empty Text box field is displayed as:"+EmptyTextbox.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Empty Text box field is displayed");


			
			WebElement forwardarrow = getwebelement(
					xml.getlocator("//locators/" + application + "/forwardarrow"));
			 sa.assertTrue(forwardarrow.isDisplayed(),"forward arrow button Field is not displayed");
			Log.info("forward arrow button Field is  displayed");
			System.out.println("forward arrow field is displayed as >>");
			DriverTestcase.logger.log(LogStatus.PASS, "forward arrow button field is displayed as: >>");


			
			WebElement BackwardArrow = getwebelement(
					xml.getlocator("//locators/" + application + "/BackwardArrow"));
			sa.assertTrue(BackwardArrow.isDisplayed(),"Back ward Arrow is not displayed");
			Log.info("Back ward Arrow is  displayed");
			System.out.println("Backward Arrow field is displayed as <<");
			DriverTestcase.logger.log(LogStatus.PASS, "Backward arrow button field is displayed as: <<");


			
			WebElement OKbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/OKbtn"));
			sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
			Log.info("OK btn is displayed");
			System.out.println("OK btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "OK button is displayed");


			
			WebElement Cancelbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/Cancelbtn"));
			sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
			Log.info("Cancel button is displayed");
			System.out.println("Cancel btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Cancel button is displayed");

			
			
			//Filling value in Mandatory fields
			Thread.sleep(5000);
			
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetering Values to creat postcode");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField")),"161");//EmergencyAreaIDSub);
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetered in Mandatory field: Emergency area");
			

			

			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Clciked on OK  button");

			Thread.sleep(10000);
			
			String ExpPostcodemsg = "Postcode successfully created.";
			System.out.println("Postcode message");
			WebElement ActPostcodemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode successfully created.')]"));
			
			if (ActPostcodemsg.getText().equals(ExpPostcodemsg))
			{
				Log.info("Postcode Message : " + ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodemsg.getText());
				System.out.println("Message Displayed as:"+ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Message Displayed as:" +ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:PostCode Success Method Verified" );

			}
			else
			{
				sa.assertTrue(ActPostcodemsg.isDisplayed(),"Message not displayed");
				Log.info("Postcode success message not displayed");
				System.out.println("Postcode success message not displayed");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Postcode success message not displayed" );

			}
			Thread.sleep(5000);
		}
		
		else if(Countrylist.equals("France"))
		{
			WebElement EmergencyAreaIDSubcom =getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField"));
			sa.assertTrue(EmergencyAreaIDSubcom.isDisplayed(),"Emergency Area ID (INSEE Code) Field is not displayed");
			Log.info("Emergency Area ID (INSEE Code) Field is not displayed");
			System.out.println("Emergency Area ID (INSEE Code) Field is not displayed");
			
			WebElement NTServiceAreaPostcode = getwebelement(
					xml.getlocator("//locators/" + application + "/NTServiceAreaPostcode_Text"));
			sa.assertTrue(NTServiceAreaPostcode.isDisplayed(),"NT Service Area Postcode Field is not displayed");
			Log.info("NT Service Area Postcode field is displayed");
			System.out.println("NT Service Area Post code:"+NTServiceAreaPostcode.getText());

			
			WebElement ToimplemetSwitch = getwebelement(
					xml.getlocator("//locators/" + application + "/ToimplemetSwitch_Text"));
			sa.assertTrue(ToimplemetSwitch.isDisplayed(),"To implement Switch  Field is not displayed");
			Log.info("To implement Switch field is displayed");
			System.out.println("To implement Switch field is displayed as:"+ToimplemetSwitch.getText());

			
			WebElement CityTranslatorNmbr = getwebelement(
					xml.getlocator("//locators/" + application + "/CityTranslatorNmbr_Text"));
			sa.assertTrue(CityTranslatorNmbr.isDisplayed(),"City Translator Nmbr Field is not displayed");
			Log.info("City Translator Nmbr field is displayed");
			System.out.println("City Translator Nmbr field is displayed as:"+CityTranslatorNmbr.getText());
			
		
			WebElement SubAreaProvince_1 = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaProvince_1_Text"));
			sa.assertTrue(SubAreaProvince_1.isDisplayed(),"SubArea1 (SubArea1) Nmbr field is not displayed");
			Log.info("SubArea1 (SubArea1) Nmbr field is displayed");
			System.out.println("SubArea1 (SubArea1) Nmbr field is displayed as:"+SubAreaProvince_1.getText());

			
			WebElement SubArea1D = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea1D_Text"));
			 sa.assertTrue(SubArea1D.isDisplayed(),"SubArea1-ID (SubArea1-ID) field is displayed as");
			Log.info("SubArea1-ID (SubArea1-ID) field is displayed as");
			System.out.println("SubArea1-ID (SubArea1-ID) field is displayed as:"+SubArea1D.getText());

			
			WebElement SubArea2Community = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2Community_Text"));
			 sa.assertTrue(SubArea2Community.isDisplayed(),"SubArea2 (SubArea2) Community Field is not displayed");
			Log.info("SubArea2 (SubArea2) field is displayed");
			System.out.println("SubArea2 (SubArea2)field is displayed as:"+SubArea2Community.getText());

			
			WebElement SubArea2ID = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2ID_Text"));
			sa.assertTrue(SubArea2ID.isDisplayed(),"Sub Area2 ID Field is not displayed");
			Log.info("Sub Area2 ID field is displayed");
			System.out.println("Sub Area 2 ID field is displayed as:"+SubArea2ID.getText());

			
			WebElement SubArea3B = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea3B_Text"));
			 sa.assertTrue(SubArea3B.isDisplayed(),"SubArea3 (SubArea3) Text Field is not displayed");
			Log.info("SubArea3 (SubArea3) Text field is displayed");
			System.out.println("SubArea3 (SubArea3) field is displayed as:"+SubArea3B.getText());

			
			WebElement SubAreaZipcode = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaZipcode_Text"));
			sa.assertTrue(SubAreaZipcode.isDisplayed(),"SubArea3-ID (SubArea3-ID) Field is not displayed");
			Log.info("SubArea3-ID (SubArea3-ID) field is displayed");
			System.out.println("SubArea3-ID (SubArea3-ID) field is displayed as:"+SubAreaZipcode.getText());

			
			WebElement EmergencyAreaSub = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaSub_Text"));
			sa.assertTrue(EmergencyAreaSub.isDisplayed(),"Emergency Area (Kommune) Field is not displayed");
			Log.info("Emergency Area (Kommune) Field is  displayed");
			System.out.println("Emergency Area (Kommune) field is displayed");

			
			WebElement EmergencyNmbrKey = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyNmbrKey_Text"));
			sa.assertTrue(EmergencyNmbrKey.isDisplayed(),"Emergency Nmbr Key Text Field is not displayed");
			Log.info("Emergency Nmbr Key is displayed");
			System.out.println("Emergency Nmbr Key is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Nmbr Key is displayed");


			
					
			WebElement ActualProvider = getwebelement(
					xml.getlocator("//locators/" + application + "/ActualProvider_Text"));
			sa.assertTrue(ActualProvider.isDisplayed(),"Actual Provider Text Field is not displayed");
			Log.info("Actual Provider Text Field is displayed");
			System.out.println("Actual Provider Text Field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Actual Provider Text Field is displayed");

			WebElement DummyCode_Text = getwebelement(
					xml.getlocator("//locators/" + application + "/DummyCode_Text"));
			sa.assertTrue(DummyCode_Text.isDisplayed(),"Dummy Code Field is not displayed");
			Log.info("Dummy Code Field is not displayed");
			System.out.println("Dummy Code field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Dummy Code field is displayed");


			
			WebElement EmptyTextbox = getwebelement(
					xml.getlocator("//locators/" + application + "/EmptyTextbox"));
			sa.assertTrue(EmptyTextbox.isDisplayed(),"Empty Text box Field is not displayed");
			Log.info("Empty Text box Field is displayed");
			System.out.println("Empty Text box field is displayed as:"+EmptyTextbox.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Empty Text box field is displayed as:");

			
			WebElement forwardarrow = getwebelement(
					xml.getlocator("//locators/" + application + "/forwardarrow"));
			 sa.assertTrue(forwardarrow.isDisplayed(),"forward arrow button Field is not displayed");
			Log.info("forward arrow button Field is  displayed");
			System.out.println("forward arrow field is displayed as >>");
			DriverTestcase.logger.log(LogStatus.PASS, "forward arrow button field is displayed as: >>");


			
			WebElement BackwardArrow = getwebelement(
					xml.getlocator("//locators/" + application + "/BackwardArrow"));
			sa.assertTrue(BackwardArrow.isDisplayed(),"Back ward Arrow is not displayed");
			Log.info("Back ward Arrow is  displayed");
			System.out.println("Backward Arrow field is displayed as <<");
			DriverTestcase.logger.log(LogStatus.PASS, "Backward arrow button field is displayed as: <<");


			
			WebElement OKbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/OKbtn"));
			sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
			Log.info("OK btn is displayed");
			System.out.println("OK btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "OK button is displayed");


			
			WebElement Cancelbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/Cancelbtn"));
			sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
			Log.info("Cancel button is displayed");
			System.out.println("Cancel btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Cancel button is displayed");

			
			
			//Filling value in Mandatory fields
			Thread.sleep(5000);
			
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetering Values to creat postcode");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField")),"161");//EmergencyAreaIDSub);
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetered in Mandatory field: Emergency area");
			

			

			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Clciked on OK  button");

			Thread.sleep(10000);
			
			String ExpPostcodemsg = "Postcode successfully created.";
			System.out.println("Postcode message");
			WebElement ActPostcodemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode successfully created.')]"));
			
			if (ActPostcodemsg.getText().equals(ExpPostcodemsg))
			{
				Log.info("Postcode Message : " + ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodemsg.getText());
				System.out.println("Message Displayed as:"+ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Message Displayed as:" +ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:PostCode Success Method Verified" );

			}
			else
			{
				sa.assertTrue(ActPostcodemsg.isDisplayed(),"Message not displayed");
				Log.info("Postcode success message not displayed");
				System.out.println("Postcode success message not displayed");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Postcode success message not displayed" );

			}
			Thread.sleep(5000);
			}
		
		
		else if(Countrylist.equals("Germany"))
		{
		
		WebElement EmergencyAreaIDSubcom =getwebelement(
				xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField"));
		sa.assertTrue(EmergencyAreaIDSubcom.isDisplayed(),"Emergency Area ID Subcom Field is not displayed");
		Log.info("Emergency Area ID Subcom field is displayed");
		System.out.println("Emergency Area ID Subcom filed is displayed ");
		
		WebElement NTServiceAreaPostcode = getwebelement(
				xml.getlocator("//locators/" + application + "/NTServiceAreaPostcode_Text"));
		sa.assertTrue(NTServiceAreaPostcode.isDisplayed(),"NT Service Area Postcode Field is not displayed");
		Log.info("NT Service Area Postcode field is displayed");
		System.out.println("NT Service Area Post code:"+NTServiceAreaPostcode.getText());

		
		WebElement ToimplemetSwitch = getwebelement(
				xml.getlocator("//locators/" + application + "/ToimplemetSwitch_Text"));
		sa.assertTrue(ToimplemetSwitch.isDisplayed(),"To implement Switch  Field is not displayed");
		Log.info("To implement Switch field is displayed");
		System.out.println("To implement Switch field is displayed as:"+ToimplemetSwitch.getText());

		
		WebElement CityTranslatorNmbr = getwebelement(
				xml.getlocator("//locators/" + application + "/CityTranslatorNmbr_Text"));
		sa.assertTrue(CityTranslatorNmbr.isDisplayed(),"City Translator Nmbr Field is not displayed");
		Log.info("City Translator Nmbr field is displayed");
		System.out.println("City Translator Nmbr field is displayed as:"+CityTranslatorNmbr.getText());
		
	
		WebElement SubAreaProvince_1 = getwebelement(
				xml.getlocator("//locators/" + application + "/SubAreaProvince_1_Text"));
		sa.assertTrue(SubAreaProvince_1.isDisplayed(),"Sub Area Province1 Field is not displayed");
		Log.info("Upload Nt link field is displayed");
		System.out.println("Sub Area Province Translator Nmbr field is displayed as:"+SubAreaProvince_1.getText());

		
		WebElement SubArea1D = getwebelement(
				xml.getlocator("//locators/" + application + "/SubArea1D_Text"));
		 sa.assertTrue(SubArea1D.isDisplayed(),"Sub Area 1D Field is not displayed");
		Log.info("Sub Area 1D field is displayed");
		System.out.println("SubArea1D field is displayed as:"+SubArea1D.getText());

		
		WebElement SubArea2Community = getwebelement(
				xml.getlocator("//locators/" + application + "/SubArea2Community_Text"));
		 sa.assertTrue(SubArea2Community.isDisplayed(),"Sub Area2 Community Field is not displayed");
		Log.info("Sub Area2 Community field is displayed");
		System.out.println("Sub Area 2 Community field is displayed as:"+SubArea2Community.getText());

		
		WebElement SubArea2ID = getwebelement(
				xml.getlocator("//locators/" + application + "/SubArea2ID_Text"));
		sa.assertTrue(SubArea2ID.isDisplayed(),"Sub Area2 ID Field is not displayed");
		Log.info("Sub Area2 ID field is displayed");
		System.out.println("Sub Area 2 ID field is displayed as:"+SubArea2ID.getText());

		
		WebElement SubArea3B = getwebelement(
				xml.getlocator("//locators/" + application + "/SubArea3B_Text"));
		 sa.assertTrue(SubArea3B.isDisplayed(),"Sub Area3B Text Field is not displayed");
		Log.info("Sub Area3B Text field is displayed");
		System.out.println("Sub Area 3B field is displayed as:"+SubArea3B.getText());

		
		WebElement SubAreaZipcode = getwebelement(
				xml.getlocator("//locators/" + application + "/SubAreaZipcode_Text"));
		sa.assertTrue(SubAreaZipcode.isDisplayed(),"Sub Area Zipcode Field is not displayed");
		Log.info("Sub Area Zipcode field is displayed");
		System.out.println("Sub Area Zipcode field is displayed as:"+SubAreaZipcode.getText());

		
		WebElement EmergencyAreaSub = getwebelement(
				xml.getlocator("//locators/" + application + "/EmergencyAreaSub_Text"));
		sa.assertTrue(EmergencyAreaSub.isDisplayed(),"Emergency Area Sub Field is not displayed");
		Log.info("Emergency Area Sub Field is  displayed");
		System.out.println("Emergency Area Sub field is displayed");

		
		WebElement EmergencyNmbrKey = getwebelement(
				xml.getlocator("//locators/" + application + "/EmergencyNmbrKey_Text"));
		sa.assertTrue(EmergencyNmbrKey.isDisplayed(),"Emergency Nmbr Key Text Field is not displayed");
		Log.info("Emergency Nmbr Key is displayed");
		System.out.println("Emergency Nmbr Key is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Nmbr Key is displayed");


		
				
		WebElement ActualProvider = getwebelement(
				xml.getlocator("//locators/" + application + "/ActualProvider_Text"));
		sa.assertTrue(ActualProvider.isDisplayed(),"Actual Provider Text Field is not displayed");
		Log.info("Actual Provider Text Field is displayed");
		System.out.println("Actual Provider Text Field is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Actual Provider Text Field is displayed");

		WebElement DummyCode_Text = getwebelement(
				xml.getlocator("//locators/" + application + "/DummyCode_Text"));
		sa.assertTrue(DummyCode_Text.isDisplayed(),"Dummy Code Field is not displayed");
		Log.info("Dummy Code Field is not displayed");
		System.out.println("Dummy Code field is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Dummy Code field is displayed");


		
		WebElement EmptyTextbox = getwebelement(
				xml.getlocator("//locators/" + application + "/EmptyTextbox"));
		sa.assertTrue(EmptyTextbox.isDisplayed(),"Empty Text box Field is not displayed");
		Log.info("Empty Text box Field is displayed");
		System.out.println("Empty Text box field is displayed as:"+EmptyTextbox.getText());
		DriverTestcase.logger.log(LogStatus.PASS, "Empty Text box field is displayed as:");

		
		WebElement forwardarrow = getwebelement(
				xml.getlocator("//locators/" + application + "/forwardarrow"));
		 sa.assertTrue(forwardarrow.isDisplayed(),"forward arrow button Field is not displayed");
		Log.info("forward arrow button Field is  displayed");
		System.out.println("forward arrow field is displayed as >>");
		DriverTestcase.logger.log(LogStatus.PASS, "forward arrow button field is displayed as: >>");


		
		WebElement BackwardArrow = getwebelement(
				xml.getlocator("//locators/" + application + "/BackwardArrow"));
		sa.assertTrue(BackwardArrow.isDisplayed(),"Back ward Arrow is not displayed");
		Log.info("Back ward Arrow is  displayed");
		System.out.println("Backward Arrow field is displayed as <<");
		DriverTestcase.logger.log(LogStatus.PASS, "Backward arrow button field is displayed as: <<");


		
		WebElement OKbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/OKbtn"));
		sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
		Log.info("OK btn is displayed");
		System.out.println("OK btn is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "OK button is displayed");


		
		WebElement Cancelbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/Cancelbtn"));
		sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
		Log.info("Cancel button is displayed");
		System.out.println("Cancel btn is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Cancel button is displayed");

		
		
		//Filling value in Mandatory fields
		Thread.sleep(5000);
		
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetering Values to creat postcode");
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField")),"161");//EmergencyAreaIDSub);
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetered in Mandatory field: Emergency area");
		

		

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Clciked on OK  button");

		Thread.sleep(10000);
		
		String ExpPostcodemsg = "Postcode successfully created.";
		System.out.println("Postcode message");
		WebElement ActPostcodemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode successfully created.')]"));
		
		if (ActPostcodemsg.getText().equals(ExpPostcodemsg))
		{
			Log.info("Postcode Message : " + ActPostcodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodemsg.getText());
			System.out.println("Message Displayed as:"+ActPostcodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Message Displayed as:" +ActPostcodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:PostCode Success Method Verified" );

		}
		else
		{
			sa.assertTrue(ActPostcodemsg.isDisplayed(),"Message not displayed");
			Log.info("Postcode success message not displayed");
			System.out.println("Postcode success message not displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Postcode success message not displayed" );

		}
		Thread.sleep(5000);
		}
		else if(Countrylist.equals("Portugal"))
		{
			WebElement EmergencyAreaIDSubcom =getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField"));
			sa.assertTrue(EmergencyAreaIDSubcom.isDisplayed(),"Emergency Area ID (CLISRVPF INDEX) Field is not displayed");
			Log.info("Emergency Area ID (CLISRVPF INDEX)field is displayed");
			System.out.println("Emergency Area ID (CLISRVPF INDEX)field is displayed");
			
			WebElement NTServiceAreaPostcode = getwebelement(
					xml.getlocator("//locators/" + application + "/NTServiceAreaPostcode_Text"));
			sa.assertTrue(NTServiceAreaPostcode.isDisplayed(),"NT Service Area Postcode Field is not displayed");
			Log.info("NT Service Area Postcode field is displayed");
			System.out.println("NT Service Area Post code:"+NTServiceAreaPostcode.getText());

			
			WebElement ToimplemetSwitch = getwebelement(
					xml.getlocator("//locators/" + application + "/ToimplemetSwitch_Text"));
			sa.assertTrue(ToimplemetSwitch.isDisplayed(),"To implement Switch  Field is not displayed");
			Log.info("To implement Switch field is displayed");
			System.out.println("To implement Switch field is displayed as:"+ToimplemetSwitch.getText());

			
			WebElement CityTranslatorNmbr = getwebelement(
					xml.getlocator("//locators/" + application + "/CityTranslatorNmbr_Text"));
			sa.assertTrue(CityTranslatorNmbr.isDisplayed(),"City Translator Nmbr Field is not displayed");
			Log.info("City Translator Nmbr field is displayed");
			System.out.println("City Translator Nmbr field is displayed as:"+CityTranslatorNmbr.getText());
			
		
			WebElement SubAreaProvince_1 = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaProvince_1_Text"));
			sa.assertTrue(SubAreaProvince_1.isDisplayed(),"SubArea1 (SubArea1) Nmbr field is not displayed");
			Log.info("SubArea1 (SubArea1) Nmbr field is displayed");
			System.out.println("SubArea1 (SubArea1) Nmbr field is displayed as:"+SubAreaProvince_1.getText());

			
			WebElement SubArea1D = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea1D_Text"));
			 sa.assertTrue(SubArea1D.isDisplayed(),"SubArea1-ID (SubArea1-ID) field is displayed as");
			Log.info("SubArea1-ID (SubArea1-ID) field is displayed as");
			System.out.println("SubArea1-ID (SubArea1-ID) field is displayed as:"+SubArea1D.getText());

			
			WebElement SubArea2Community = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2Community_Text"));
			 sa.assertTrue(SubArea2Community.isDisplayed(),"SubArea2 (SubArea2) Community Field is not displayed");
			Log.info("SubArea2 (SubArea2) field is displayed");
			System.out.println("SubArea2 (SubArea2)field is displayed as:"+SubArea2Community.getText());

			
			WebElement SubArea2ID = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2ID_Text"));
			sa.assertTrue(SubArea2ID.isDisplayed(),"Sub Area2 ID Field is not displayed");
			Log.info("Sub Area2 ID field is displayed");
			System.out.println("Sub Area 2 ID field is displayed as:"+SubArea2ID.getText());

			
			WebElement SubArea3B = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea3B_Text"));
			 sa.assertTrue(SubArea3B.isDisplayed(),"SubArea3 (SubArea3) Text Field is not displayed");
			Log.info("SubArea3 (SubArea3) Text field is displayed");
			System.out.println("SubArea3 (SubArea3) field is displayed as:"+SubArea3B.getText());

			
			WebElement SubAreaZipcode = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaZipcode_Text"));
			sa.assertTrue(SubAreaZipcode.isDisplayed(),"SubArea3-ID (SubArea3-ID) Field is not displayed");
			Log.info("SubArea3-ID (SubArea3-ID) field is displayed");
			System.out.println("SubArea3-ID (SubArea3-ID) field is displayed as:"+SubAreaZipcode.getText());

			
			WebElement EmergencyAreaSub = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaSub_Text"));
			sa.assertTrue(EmergencyAreaSub.isDisplayed(),"Emergency Area (Kommune) Field is not displayed");
			Log.info("Emergency Area (Kommune) Field is  displayed");
			System.out.println("Emergency Area (Kommune) field is displayed");

			
			WebElement EmergencyNmbrKey = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyNmbrKey_Text"));
			sa.assertTrue(EmergencyNmbrKey.isDisplayed(),"Emergency Nmbr Key Text Field is not displayed");
			Log.info("Emergency Nmbr Key is displayed");
			System.out.println("Emergency Nmbr Key is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Nmbr Key is displayed");


			
					
			WebElement ActualProvider = getwebelement(
					xml.getlocator("//locators/" + application + "/ActualProvider_Text"));
			sa.assertTrue(ActualProvider.isDisplayed(),"Actual Provider Text Field is not displayed");
			Log.info("Actual Provider Text Field is displayed");
			System.out.println("Actual Provider Text Field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Actual Provider Text Field is displayed");

			WebElement DummyCode_Text = getwebelement(
					xml.getlocator("//locators/" + application + "/DummyCode_Text"));
			sa.assertTrue(DummyCode_Text.isDisplayed(),"Dummy Code Field is not displayed");
			Log.info("Dummy Code Field is not displayed");
			System.out.println("Dummy Code field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Dummy Code field is displayed");


			
			WebElement EmptyTextbox = getwebelement(
					xml.getlocator("//locators/" + application + "/EmptyTextbox"));
			sa.assertTrue(EmptyTextbox.isDisplayed(),"Empty Text box Field is not displayed");
			Log.info("Empty Text box Field is displayed");
			System.out.println("Empty Text box field is displayed as:"+EmptyTextbox.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Empty Text box field is displayed as:");

			
			WebElement forwardarrow = getwebelement(
					xml.getlocator("//locators/" + application + "/forwardarrow"));
			 sa.assertTrue(forwardarrow.isDisplayed(),"forward arrow button Field is not displayed");
			Log.info("forward arrow button Field is  displayed");
			System.out.println("forward arrow field is displayed as >>");
			DriverTestcase.logger.log(LogStatus.PASS, "forward arrow button field is displayed as: >>");


			
			WebElement BackwardArrow = getwebelement(
					xml.getlocator("//locators/" + application + "/BackwardArrow"));
			sa.assertTrue(BackwardArrow.isDisplayed(),"Back ward Arrow is not displayed");
			Log.info("Back ward Arrow is  displayed");
			System.out.println("Backward Arrow field is displayed as <<");
			DriverTestcase.logger.log(LogStatus.PASS, "Backward arrow button field is displayed as: <<");


			
			WebElement OKbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/OKbtn"));
			sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
			Log.info("OK btn is displayed");
			System.out.println("OK btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "OK button is displayed");


			
			WebElement Cancelbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/Cancelbtn"));
			sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
			Log.info("Cancel button is displayed");
			System.out.println("Cancel btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Cancel button is displayed");

			
			
			//Filling value in Mandatory fields
			Thread.sleep(5000);
			
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetering Values to creat postcode");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField")),"161");//EmergencyAreaIDSub);
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetered in Mandatory field: Emergency area");
			

			

			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Clciked on OK  button");

			Thread.sleep(10000);
			
			String ExpPostcodemsg = "Postcode successfully created.";
			System.out.println("Postcode message");
			WebElement ActPostcodemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode successfully created.')]"));
			
			if (ActPostcodemsg.getText().equals(ExpPostcodemsg))
			{
				Log.info("Postcode Message : " + ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodemsg.getText());
				System.out.println("Message Displayed as:"+ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Message Displayed as:" +ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:PostCode Success Method Verified" );

			}
			else
			{
				sa.assertTrue(ActPostcodemsg.isDisplayed(),"Message not displayed");
				Log.info("Postcode success message not displayed");
				System.out.println("Postcode success message not displayed");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Postcode success message not displayed" );

			}
			Thread.sleep(5000);
			}
		else if(Countrylist.equals("Spain"))
		{
			WebElement EmergencyAreaIDSubcom =getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField"));
			sa.assertTrue(EmergencyAreaIDSubcom.isDisplayed(),"Emergency Area ID (Municip.Code) Field is not displayed");
			Log.info("Emergency Area ID (Municip.Code)field is displayed");
			System.out.println("Emergency Area ID (Municip.Code)field is displayed");
			
			WebElement NTServiceAreaPostcode = getwebelement(
					xml.getlocator("//locators/" + application + "/NTServiceAreaPostcode_Text"));
			sa.assertTrue(NTServiceAreaPostcode.isDisplayed(),"NT Service Area Postcode Field is not displayed");
			Log.info("NT Service Area Postcode field is displayed");
			System.out.println("NT Service Area Post code:"+NTServiceAreaPostcode.getText());

			
			WebElement ToimplemetSwitch = getwebelement(
					xml.getlocator("//locators/" + application + "/ToimplemetSwitch_Text"));
			sa.assertTrue(ToimplemetSwitch.isDisplayed(),"To implement Switch  Field is not displayed");
			Log.info("To implement Switch field is displayed");
			System.out.println("To implement Switch field is displayed as:"+ToimplemetSwitch.getText());

			
			WebElement CityTranslatorNmbr = getwebelement(
					xml.getlocator("//locators/" + application + "/CityTranslatorNmbr_Text"));
			sa.assertTrue(CityTranslatorNmbr.isDisplayed(),"City Translator Nmbr Field is not displayed");
			Log.info("City Translator Nmbr field is displayed");
			System.out.println("City Translator Nmbr field is displayed as:"+CityTranslatorNmbr.getText());
			
		
			WebElement SubAreaProvince_1 = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaProvince_1_Text"));
			sa.assertTrue(SubAreaProvince_1.isDisplayed(),"SubArea1 (SubArea1) Nmbr field is not displayed");
			Log.info("SubArea1 (SubArea1) Nmbr field is displayed");
			System.out.println("SubArea1 (SubArea1) Nmbr field is displayed as:"+SubAreaProvince_1.getText());

			
			WebElement SubArea1D = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea1D_Text"));
			 sa.assertTrue(SubArea1D.isDisplayed(),"SubArea1-ID (SubArea1-ID) field is displayed as");
			Log.info("SubArea1-ID (SubArea1-ID) field is displayed as");
			System.out.println("SubArea1-ID (SubArea1-ID) field is displayed as:"+SubArea1D.getText());

			
			WebElement SubArea2Community = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2Community_Text"));
			 sa.assertTrue(SubArea2Community.isDisplayed(),"SubArea2 (SubArea2) Community Field is not displayed");
			Log.info("SubArea2 (SubArea2) field is displayed");
			System.out.println("SubArea2 (SubArea2)field is displayed as:"+SubArea2Community.getText());

			
			WebElement SubArea2ID = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2ID_Text"));
			sa.assertTrue(SubArea2ID.isDisplayed(),"Sub Area2 ID Field is not displayed");
			Log.info("Sub Area2 ID field is displayed");
			System.out.println("Sub Area 2 ID field is displayed as:"+SubArea2ID.getText());

			
			WebElement SubArea3B = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea3B_Text"));
			 sa.assertTrue(SubArea3B.isDisplayed(),"SubArea3 (SubArea3) Text Field is not displayed");
			Log.info("SubArea3 (SubArea3) Text field is displayed");
			System.out.println("SubArea3 (SubArea3) field is displayed as:"+SubArea3B.getText());

			
			WebElement SubAreaZipcode = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaZipcode_Text"));
			sa.assertTrue(SubAreaZipcode.isDisplayed(),"SubArea3-ID (SubArea3-ID) Field is not displayed");
			Log.info("SubArea3-ID (SubArea3-ID) field is displayed");
			System.out.println("SubArea3-ID (SubArea3-ID) field is displayed as:"+SubAreaZipcode.getText());

			
			WebElement EmergencyAreaSub = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaSub_Text"));
			sa.assertTrue(EmergencyAreaSub.isDisplayed(),"Emergency Area (Kommune) Field is not displayed");
			Log.info("Emergency Area (Kommune) Field is  displayed");
			System.out.println("Emergency Area (Kommune) field is displayed");

			
			WebElement EmergencyNmbrKey = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyNmbrKey_Text"));
			sa.assertTrue(EmergencyNmbrKey.isDisplayed(),"Emergency Nmbr Key Text Field is not displayed");
			Log.info("Emergency Nmbr Key is displayed");
			System.out.println("Emergency Nmbr Key is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Nmbr Key is displayed");


			
					
			WebElement ActualProvider = getwebelement(
					xml.getlocator("//locators/" + application + "/ActualProvider_Text"));
			sa.assertTrue(ActualProvider.isDisplayed(),"Actual Provider Text Field is not displayed");
			Log.info("Actual Provider Text Field is displayed");
			System.out.println("Actual Provider Text Field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Actual Provider Text Field is displayed");

			WebElement DummyCode_Text = getwebelement(
					xml.getlocator("//locators/" + application + "/DummyCode_Text"));
			sa.assertTrue(DummyCode_Text.isDisplayed(),"Dummy Code Field is not displayed");
			Log.info("Dummy Code Field is not displayed");
			System.out.println("Dummy Code field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Dummy Code field is displayed");


			
			WebElement EmptyTextbox = getwebelement(
					xml.getlocator("//locators/" + application + "/EmptyTextbox"));
			sa.assertTrue(EmptyTextbox.isDisplayed(),"Empty Text box Field is not displayed");
			Log.info("Empty Text box Field is displayed");
			System.out.println("Empty Text box field is displayed as:"+EmptyTextbox.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Empty Text box field is displayed as:");

			
			WebElement forwardarrow = getwebelement(
					xml.getlocator("//locators/" + application + "/forwardarrow"));
			 sa.assertTrue(forwardarrow.isDisplayed(),"forward arrow button Field is not displayed");
			Log.info("forward arrow button Field is  displayed");
			System.out.println("forward arrow field is displayed as >>");
			DriverTestcase.logger.log(LogStatus.PASS, "forward arrow button field is displayed as: >>");


			
			WebElement BackwardArrow = getwebelement(
					xml.getlocator("//locators/" + application + "/BackwardArrow"));
			sa.assertTrue(BackwardArrow.isDisplayed(),"Back ward Arrow is not displayed");
			Log.info("Back ward Arrow is  displayed");
			System.out.println("Backward Arrow field is displayed as <<");
			DriverTestcase.logger.log(LogStatus.PASS, "Backward arrow button field is displayed as: <<");


			
			WebElement OKbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/OKbtn"));
			sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
			Log.info("OK btn is displayed");
			System.out.println("OK btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "OK button is displayed");


			
			WebElement Cancelbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/Cancelbtn"));
			sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
			Log.info("Cancel button is displayed");
			System.out.println("Cancel btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Cancel button is displayed");

			
			
			//Filling value in Mandatory fields
			Thread.sleep(5000);
			
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetering Values to creat postcode");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField")),"161");//EmergencyAreaIDSub);
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetered in Mandatory field: Emergency area");
			

			

			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Clciked on OK  button");

			Thread.sleep(10000);
			
			String ExpPostcodemsg = "Postcode successfully created.";
			System.out.println("Postcode message");
			WebElement ActPostcodemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode successfully created.')]"));
			
			if (ActPostcodemsg.getText().equals(ExpPostcodemsg))
			{
				Log.info("Postcode Message : " + ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodemsg.getText());
				System.out.println("Message Displayed as:"+ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Message Displayed as:" +ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:PostCode Success Method Verified" );

			}
			else
			{
				sa.assertTrue(ActPostcodemsg.isDisplayed(),"Message not displayed");
				Log.info("Postcode success message not displayed");
				System.out.println("Postcode success message not displayed");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Postcode success message not displayed" );

			}
			Thread.sleep(5000);
			}

		else if(Countrylist.equals("Sweden"))
		{
			WebElement EmergencyAreaIDSubcom =getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField"));
			sa.assertTrue(EmergencyAreaIDSubcom.isDisplayed(),"Emergency Area ID (Kommun-ID/ALARM-ID) Field is not displayed");
			Log.info("Emergency Area ID (Kommun-ID/ALARM-ID)field is displayed");
			System.out.println("Emergency Area ID (Kommun-ID/ALARM-ID)field is displayed");
			
			WebElement NTServiceAreaPostcode = getwebelement(
					xml.getlocator("//locators/" + application + "/NTServiceAreaPostcode_Text"));
			sa.assertTrue(NTServiceAreaPostcode.isDisplayed(),"NT Service Area Postcode Field is not displayed");
			Log.info("NT Service Area Postcode field is displayed");
			System.out.println("NT Service Area Post code:"+NTServiceAreaPostcode.getText());

			
			WebElement ToimplemetSwitch = getwebelement(
					xml.getlocator("//locators/" + application + "/ToimplemetSwitch_Text"));
			sa.assertTrue(ToimplemetSwitch.isDisplayed(),"To implement Switch  Field is not displayed");
			Log.info("To implement Switch field is displayed");
			System.out.println("To implement Switch field is displayed as:"+ToimplemetSwitch.getText());

			
			WebElement CityTranslatorNmbr = getwebelement(
					xml.getlocator("//locators/" + application + "/CityTranslatorNmbr_Text"));
			sa.assertTrue(CityTranslatorNmbr.isDisplayed(),"City Translator Nmbr Field is not displayed");
			Log.info("City Translator Nmbr field is displayed");
			System.out.println("City Translator Nmbr field is displayed as:"+CityTranslatorNmbr.getText());
			
		
			WebElement SubAreaProvince_1 = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaProvince_1_Text"));
			sa.assertTrue(SubAreaProvince_1.isDisplayed(),"SubArea1 (SubArea1) Nmbr field is not displayed");
			Log.info("SubArea1 (SubArea1) Nmbr field is displayed");
			System.out.println("SubArea1 (SubArea1) Nmbr field is displayed as:"+SubAreaProvince_1.getText());

			
			WebElement SubArea1D = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea1D_Text"));
			 sa.assertTrue(SubArea1D.isDisplayed(),"SubArea1-ID (SubArea1-ID) field is displayed as");
			Log.info("SubArea1-ID (SubArea1-ID) field is displayed as");
			System.out.println("SubArea1-ID (SubArea1-ID) field is displayed as:"+SubArea1D.getText());

			
			WebElement SubArea2Community = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2Community_Text"));
			 sa.assertTrue(SubArea2Community.isDisplayed(),"SubArea2 (SubArea2) Community Field is not displayed");
			Log.info("SubArea2 (SubArea2) field is displayed");
			System.out.println("SubArea2 (SubArea2)field is displayed as:"+SubArea2Community.getText());

			
			WebElement SubArea2ID = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2ID_Text"));
			sa.assertTrue(SubArea2ID.isDisplayed(),"Sub Area2 ID Field is not displayed");
			Log.info("Sub Area2 ID field is displayed");
			System.out.println("Sub Area 2 ID field is displayed as:"+SubArea2ID.getText());

			
			WebElement SubArea3B = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea3B_Text"));
			 sa.assertTrue(SubArea3B.isDisplayed(),"SubArea3 (SubArea3) Text Field is not displayed");
			Log.info("SubArea3 (SubArea3) Text field is displayed");
			System.out.println("SubArea3 (SubArea3) field is displayed as:"+SubArea3B.getText());

			
			WebElement SubAreaZipcode = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaZipcode_Text"));
			sa.assertTrue(SubAreaZipcode.isDisplayed(),"SubArea3-ID (SubArea3-ID) Field is not displayed");
			Log.info("SubArea3-ID (SubArea3-ID) field is displayed");
			System.out.println("SubArea3-ID (SubArea3-ID) field is displayed as:"+SubAreaZipcode.getText());

			
			WebElement EmergencyAreaSub = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaSub_Text"));
			sa.assertTrue(EmergencyAreaSub.isDisplayed(),"Emergency Area (Kommune) Field is not displayed");
			Log.info("Emergency Area (Kommune) Field is  displayed");
			System.out.println("Emergency Area (Kommune) field is displayed");

			
			WebElement EmergencyNmbrKey = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyNmbrKey_Text"));
			sa.assertTrue(EmergencyNmbrKey.isDisplayed(),"Emergency Nmbr Key Text Field is not displayed");
			Log.info("Emergency Nmbr Key is displayed");
			System.out.println("Emergency Nmbr Key is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Nmbr Key is displayed");


			
					
			WebElement ActualProvider = getwebelement(
					xml.getlocator("//locators/" + application + "/ActualProvider_Text"));
			sa.assertTrue(ActualProvider.isDisplayed(),"Actual Provider Text Field is not displayed");
			Log.info("Actual Provider Text Field is displayed");
			System.out.println("Actual Provider Text Field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Actual Provider Text Field is displayed");

			WebElement DummyCode_Text = getwebelement(
					xml.getlocator("//locators/" + application + "/DummyCode_Text"));
			sa.assertTrue(DummyCode_Text.isDisplayed(),"Dummy Code Field is not displayed");
			Log.info("Dummy Code Field is not displayed");
			System.out.println("Dummy Code field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Dummy Code field is displayed");


			
			WebElement EmptyTextbox = getwebelement(
					xml.getlocator("//locators/" + application + "/EmptyTextbox"));
			sa.assertTrue(EmptyTextbox.isDisplayed(),"Empty Text box Field is not displayed");
			Log.info("Empty Text box Field is displayed");
			System.out.println("Empty Text box field is displayed as:"+EmptyTextbox.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Empty Text box field is displayed as:");

			
			WebElement forwardarrow = getwebelement(
					xml.getlocator("//locators/" + application + "/forwardarrow"));
			 sa.assertTrue(forwardarrow.isDisplayed(),"forward arrow button Field is not displayed");
			Log.info("forward arrow button Field is  displayed");
			System.out.println("forward arrow field is displayed as >>");
			DriverTestcase.logger.log(LogStatus.PASS, "forward arrow button field is displayed as: >>");


			
			WebElement BackwardArrow = getwebelement(
					xml.getlocator("//locators/" + application + "/BackwardArrow"));
			sa.assertTrue(BackwardArrow.isDisplayed(),"Back ward Arrow is not displayed");
			Log.info("Back ward Arrow is  displayed");
			System.out.println("Backward Arrow field is displayed as <<");
			DriverTestcase.logger.log(LogStatus.PASS, "Backward arrow button field is displayed as: <<");


			
			WebElement OKbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/OKbtn"));
			sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
			Log.info("OK btn is displayed");
			System.out.println("OK btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "OK button is displayed");


			
			WebElement Cancelbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/Cancelbtn"));
			sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
			Log.info("Cancel button is displayed");
			System.out.println("Cancel btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Cancel button is displayed");

			
			
			//Filling value in Mandatory fields
			Thread.sleep(5000);
			
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetering Values to creat postcode");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField")),"161");//EmergencyAreaIDSub);
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetered in Mandatory field: Emergency area");
			

			

			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Clciked on OK  button");

			Thread.sleep(10000);
			
			String ExpPostcodemsg = "Postcode successfully created.";
			System.out.println("Postcode message");
			WebElement ActPostcodemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode successfully created.')]"));
			
			if (ActPostcodemsg.getText().equals(ExpPostcodemsg))
			{
				Log.info("Postcode Message : " + ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodemsg.getText());
				System.out.println("Message Displayed as:"+ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Message Displayed as:" +ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:PostCode Success Method Verified" );

			}
			else
			{
				sa.assertTrue(ActPostcodemsg.isDisplayed(),"Message not displayed");
				Log.info("Postcode success message not displayed");
				System.out.println("Postcode success message not displayed");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Postcode success message not displayed" );

			}
			Thread.sleep(5000);
			}
		
		
		else if(Countrylist.equals("Switzerland"))
		{
			WebElement EmergencyAreaIDSubcom =getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField"));
			sa.assertTrue(EmergencyAreaIDSubcom.isDisplayed(),"Emergency Area ID (Emergency Area ID) Field is not displayed");
			Log.info("Emergency Area ID (Emergency Area ID) field is displayed");
			System.out.println("Emergency Area ID (Emergency Area ID) field is displayed");
			
			WebElement NTServiceAreaPostcode = getwebelement(
					xml.getlocator("//locators/" + application + "/NTServiceAreaPostcode_Text"));
			sa.assertTrue(NTServiceAreaPostcode.isDisplayed(),"NT Service Area Postcode Field is not displayed");
			Log.info("NT Service Area Postcode field is displayed");
			System.out.println("NT Service Area Post code:"+NTServiceAreaPostcode.getText());

			
			WebElement ToimplemetSwitch = getwebelement(
					xml.getlocator("//locators/" + application + "/ToimplemetSwitch_Text"));
			sa.assertTrue(ToimplemetSwitch.isDisplayed(),"To implement Switch  Field is not displayed");
			Log.info("To implement Switch field is displayed");
			System.out.println("To implement Switch field is displayed as:"+ToimplemetSwitch.getText());

			
			WebElement CityTranslatorNmbr = getwebelement(
					xml.getlocator("//locators/" + application + "/CityTranslatorNmbr_Text"));
			sa.assertTrue(CityTranslatorNmbr.isDisplayed(),"City Translator Nmbr Field is not displayed");
			Log.info("City Translator Nmbr field is displayed");
			System.out.println("City Translator Nmbr field is displayed as:"+CityTranslatorNmbr.getText());
			
		
			WebElement SubAreaProvince_1 = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaProvince_1_Text"));
			sa.assertTrue(SubAreaProvince_1.isDisplayed(),"SubArea1 (SubArea1) Nmbr field is not displayed");
			Log.info("SubArea1 (SubArea1) Nmbr field is displayed");
			System.out.println("SubArea1 (SubArea1) Nmbr field is displayed as:"+SubAreaProvince_1.getText());

			
			WebElement SubArea1D = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea1D_Text"));
			 sa.assertTrue(SubArea1D.isDisplayed(),"SubArea1-ID (SubArea1-ID) field is displayed as");
			Log.info("SubArea1-ID (SubArea1-ID) field is displayed as");
			System.out.println("SubArea1-ID (SubArea1-ID) field is displayed as:"+SubArea1D.getText());

			
			WebElement SubArea2Community = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2Community_Text"));
			 sa.assertTrue(SubArea2Community.isDisplayed(),"SubArea2 (SubArea2) Community Field is not displayed");
			Log.info("SubArea2 (SubArea2) field is displayed");
			System.out.println("SubArea2 (SubArea2)field is displayed as:"+SubArea2Community.getText());

			
			WebElement SubArea2ID = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea2ID_Text"));
			sa.assertTrue(SubArea2ID.isDisplayed(),"Sub Area2 ID Field is not displayed");
			Log.info("Sub Area2 ID field is displayed");
			System.out.println("Sub Area 2 ID field is displayed as:"+SubArea2ID.getText());

			
			WebElement SubArea3B = getwebelement(
					xml.getlocator("//locators/" + application + "/SubArea3B_Text"));
			 sa.assertTrue(SubArea3B.isDisplayed(),"SubArea3 (SubArea3) Text Field is not displayed");
			Log.info("SubArea3 (SubArea3) Text field is displayed");
			System.out.println("SubArea3 (SubArea3) field is displayed as:"+SubArea3B.getText());

			
			WebElement SubAreaZipcode = getwebelement(
					xml.getlocator("//locators/" + application + "/SubAreaZipcode_Text"));
			sa.assertTrue(SubAreaZipcode.isDisplayed(),"SubArea3-ID (SubArea3-ID) Field is not displayed");
			Log.info("SubArea3-ID (SubArea3-ID) field is displayed");
			System.out.println("SubArea3-ID (SubArea3-ID) field is displayed as:"+SubAreaZipcode.getText());

			
			WebElement EmergencyAreaSub = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyAreaSub_Text"));
			sa.assertTrue(EmergencyAreaSub.isDisplayed(),"Emergency Area (Kommune) Field is not displayed");
			Log.info("Emergency Area (Kommune) Field is  displayed");
			System.out.println("Emergency Area (Kommune) field is displayed");

			
			WebElement EmergencyNmbrKey = getwebelement(
					xml.getlocator("//locators/" + application + "/EmergencyNmbrKey_Text"));
			sa.assertTrue(EmergencyNmbrKey.isDisplayed(),"Emergency Nmbr Key Text Field is not displayed");
			Log.info("Emergency Nmbr Key is displayed");
			System.out.println("Emergency Nmbr Key is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Emergency Nmbr Key is displayed");


			
					
			WebElement ActualProvider = getwebelement(
					xml.getlocator("//locators/" + application + "/ActualProvider_Text"));
			sa.assertTrue(ActualProvider.isDisplayed(),"Actual Provider Text Field is not displayed");
			Log.info("Actual Provider Text Field is displayed");
			System.out.println("Actual Provider Text Field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Actual Provider Text Field is displayed");

			WebElement DummyCode_Text = getwebelement(
					xml.getlocator("//locators/" + application + "/DummyCode_Text"));
			sa.assertTrue(DummyCode_Text.isDisplayed(),"Dummy Code Field is not displayed");
			Log.info("Dummy Code Field is not displayed");
			System.out.println("Dummy Code field is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Dummy Code field is displayed");


			
			WebElement EmptyTextbox = getwebelement(
					xml.getlocator("//locators/" + application + "/EmptyTextbox"));
			sa.assertTrue(EmptyTextbox.isDisplayed(),"Empty Text box Field is not displayed");
			Log.info("Empty Text box Field is displayed");
			System.out.println("Empty Text box field is displayed as:"+EmptyTextbox.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Empty Text box field is displayed as:");

			
			WebElement forwardarrow = getwebelement(
					xml.getlocator("//locators/" + application + "/forwardarrow"));
			 sa.assertTrue(forwardarrow.isDisplayed(),"forward arrow button Field is not displayed");
			Log.info("forward arrow button Field is  displayed");
			System.out.println("forward arrow field is displayed as >>");
			DriverTestcase.logger.log(LogStatus.PASS, "forward arrow button field is displayed as: >>");


			
			WebElement BackwardArrow = getwebelement(
					xml.getlocator("//locators/" + application + "/BackwardArrow"));
			sa.assertTrue(BackwardArrow.isDisplayed(),"Back ward Arrow is not displayed");
			Log.info("Back ward Arrow is  displayed");
			System.out.println("Backward Arrow field is displayed as <<");
			DriverTestcase.logger.log(LogStatus.PASS, "Backward arrow button field is displayed as: <<");


			
			WebElement OKbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/OKbtn"));
			sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
			Log.info("OK btn is displayed");
			System.out.println("OK btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "OK button is displayed");


			
			WebElement Cancelbtn = getwebelement(
					xml.getlocator("//locators/" + application + "/Cancelbtn"));
			sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
			Log.info("Cancel button is displayed");
			System.out.println("Cancel btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Cancel button is displayed");

			
			
			//Filling value in Mandatory fields
			Thread.sleep(5000);
			
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetering Values to creat postcode");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/EmergencyAreaIDSubcom_TextField")),"161");//EmergencyAreaIDSub);
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Enetered in Mandatory field: Emergency area");
			

			

			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Clciked on OK  button");

			Thread.sleep(10000);
			
			String ExpPostcodemsg = "Postcode successfully created.";
			System.out.println("Postcode message");
			WebElement ActPostcodemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode successfully created.')]"));
			
			if (ActPostcodemsg.getText().equals(ExpPostcodemsg))
			{
				Log.info("Postcode Message : " + ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodemsg.getText());
				System.out.println("Message Displayed as:"+ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Message Displayed as:" +ActPostcodemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Step:PostCode Success Method Verified" );

			}
			else
			{
				sa.assertTrue(ActPostcodemsg.isDisplayed(),"Message not displayed");
				Log.info("Postcode success message not displayed");
				System.out.println("Postcode success message not displayed");
				DriverTestcase.logger.log(LogStatus.PASS, "Step:Postcode success message not displayed" );

			}
			Thread.sleep(5000);
			
			}




		}
		
	
	public void verifyPostcodevalues(String application)throws Exception 
	{
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Searchfield")),"161");//EmergencyAreaIDSub);
		Log.info("Value Entered in TextField");
		System.out.println("Value Entered in Search TextField");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Value Entered in Search TextField" );

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Searchbtn")));
		Log.info("Clicked on Search button");
		System.out.println("Clicked on search button");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on search button" );

		Thread.sleep(5000);
		
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeCheckbox")));
		Log.info("Clicked on checkbox");
		System.out.println("Clicked on checkbox");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on checkbox" );


		
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
		System.out.println("Clicked on Action Dropdown");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on Action Dropdown" );

		
		
		//Verifying the values in View Postcode
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeActionView")));
		System.out.println("Clicked on View");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on View in Dropdown" );

		
		WebElement ViewPostcodeEAID = driver.findElement(By.xpath("//div[text()='161']"));
		if (ViewPostcodeEAID.getText().equals("161"))
		{
			Log.info("Values Matched : " + ViewPostcodeEAID.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Value Matched :"+ViewPostcodeEAID.getText());
			System.out.println("Post code values verified:"+ViewPostcodeEAID.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Post code values verified:" +ViewPostcodeEAID.getText());

		}
		else
		{
			
			Log.info("Value Didn't Matched");
			System.out.println("Post code values not verified");
			DriverTestcase.logger.log(LogStatus.PASS, "Step:Post code values not verified:" +ViewPostcodeEAID.getText());

		}
		
		
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeBackbtn")));
		Log.info("Clicked on back button");
		System.out.println("Clicked on back button");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on back button");

		
	    Log.info("Navigated to Manage Post code page");
	    System.out.println("Navigated to Manage Post code page");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Navigated to Manage Post code page");

	    //Edit the values in Edit Passcode (Confusion what to edit need to asked)
	    safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeCheckbox")));
		Log.info("Clicked on checkbox");
		System.out.println("Clicked on checkbox");
		DriverTestcase.logger.log(LogStatus.PASS, "Step:Clicked on checkbox");

		
	    
	    safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
		System.out.println("Clicked on Action Dropdown");
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Action Dropdown");

	    
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeActionEdit")));
	    System.out.println("Clicked on Edit button");
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Edit button");

	    

			
//		if (ViewPostcodeEAID.getText().equals("18"))
//		{
//			Log.info("Values Matched : " + ViewPostcodeEAID.getText());
//			DriverTestcase.logger.log(LogStatus.PASS, "Value Matched :  :" + ViewPostcodeEAID.getText());
//		}
//		else
//		{
//			
//			Log.info("Value Didn't Matched");
//		}
		
	    safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/OKbtn")));
		Thread.sleep(5000);
	    System.out.println("Clicked on OK button");
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on OK button");


		
		String ExpPostcodeUpdatemsg = "Postcode successfully updated.";
		System.out.println("Postcode Updated message");
		WebElement ActPostUpdatecodemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode successfully updated.')]"));
		
		if (ActPostUpdatecodemsg.getText().equals(ExpPostcodeUpdatemsg))
		{
			Log.info("Postcode Updated Message : " + ActPostUpdatecodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" +ActPostUpdatecodemsg.getText());
			System.out.println("Post code update message is displayed as:" +ActPostUpdatecodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Post code update message is displayed as:" +ActPostUpdatecodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Post code update message verified");

		}
		else
		{
			sa.assertTrue(ActPostUpdatecodemsg.isDisplayed(),"Message not displayed");
			Log.info("Postcode update success message not displayed");
			System.out.println("Post code update message is displayed as:"+ActPostUpdatecodemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Post code update message is displayed as:" +ActPostUpdatecodemsg.getText());

		}
		
		//Delete the Post code
		DriverTestcase.logger.log(LogStatus.PASS, "Step: Deleting Postcode");

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeCheckbox")));
		Log.info("Clicked on checkbox");
		System.out.println("Clicked on checkbox");
		DriverTestcase.logger.log(LogStatus.PASS, "Clickedx on Checkbox");

	    
		
		 safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeAction")));
		 System.out.println("Clicked on Action Dropdown");
			DriverTestcase.logger.log(LogStatus.PASS, "Clickedx on Action Dropdown");

			
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeActionDelete")));
		Log.info("Clicked on Delete option");
		System.out.println("Clicked on Delete Option");
		DriverTestcase.logger.log(LogStatus.PASS, "Selected Delete option");

		Thread.sleep(5000);
		
		

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/PostcodeDeletebtn")));
		Log.info("Clicked on Delete button ");
	    System.out.println("Clicked on Delete button");
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Delete button");
		


		DriverTestcase.logger.log(LogStatus.PASS, "Verifying Delete Post code Message");
		
		Thread.sleep(10000);
		String ExpPostcodeDeletemsg = "Postcode deleted successfully..";
		System.out.println("Postcode Delete message");
		WebElement ActPostcodeDeletemsg = driver.findElement(By.xpath("//span[contains(text(),'Postcode deleted successfully..')]"));
		
		if (ActPostcodeDeletemsg.getText().equals(ExpPostcodeDeletemsg))
		{
			Log.info("Postcode Delete Message : " + ActPostcodeDeletemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Postcode Message :  :" + ActPostcodeDeletemsg.getText());
			 System.out.println("Post code Delete message as:"+ActPostcodeDeletemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Post code Delete message as:" +ActPostcodeDeletemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Post code Delete message verified");


		}
		else
		{
			sa.assertTrue(ActPostUpdatecodemsg.isDisplayed(),"Message not displayed");
			Log.info("Postcode deleted message not displayed");
			 System.out.println("Post code Delete message as:"+ActPostcodeDeletemsg.getText());
				DriverTestcase.logger.log(LogStatus.PASS, "Post code Delete message verified");


		}

	}
	
	
	public void verifyAddEmergencyNumber(String application)
			throws Exception 
	{
		//selectColtnetwork(application);
		
//		Clickon(getwebelement(
//				xml.getlocator("//locators/" + application + "/BelMngPostcode")));
//		Log.info("Clicked on Belgium Manage Postcode");
//		 System.out.println("Clicked on Belgium Manage Postcode");
//		 

		Thread.sleep(5000);
		
		DriverTestcase.logger.log(LogStatus.PASS, "Verifying Add Emergency number link and Fields");

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/AddEmergencyNumber")));
		Log.info("Clicked on Add Emergency Number");
		 System.out.println("Clicked on Add Emergency Number");
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Add Emergency Number");


		Thread.sleep(2000);
		
		WebElement ChoseFile = (getwebelement(
				xml.getlocator("//locators/" + application + "/Chosefile")));
		sa.assertTrue(ChoseFile.isDisplayed(),"Chose File btn is not displayed");
		Log.info("ChoseFile is displayed");
		 System.out.println("ChoseFile is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Verified Chose File button is diplayed");



		WebElement OKbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/OkbtnPostcode"));
		
		sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
		Log.info("OK btn is displayed");
		 System.out.println("OK btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Verified OK button is diplayed");


		
		
		WebElement Cancelbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/Cancelbtn"));
		sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Verified cancel File button is diplayed");

		
		Log.info("Cancel button is displayed");
		 System.out.println("Cancel button is displayed");
		 
		 safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/Cancelbtn")));
		 System.out.println("Clicked on cancel button");
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on cancel button");
	}

	public void verifyUploadNtserviceArea (String application)
			throws Exception 
			{
		
		
//		selectColtnetwork(application);
//		Clickon(getwebelement(
//				xml.getlocator("//locators/" + application + "/BelMngPostcode")));
//		Log.info("Clicked on Belgium Manage Postcode");
//		Thread.sleep(5000);
//		
		
		DriverTestcase.logger.log(LogStatus.PASS, "Verifying Upload NT Service Area Link and Fields");

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/UploadNt")));
		Log.info("Clicked on Upload NT Service Area");
		 System.out.println("Clicked on Upload NT Service Area");
		
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Upload NT Service Area");
		Thread.sleep(2000);
		
		WebElement ChoseFile = (getwebelement(
				xml.getlocator("//locators/" + application + "/Chosefile")));
		sa.assertTrue(ChoseFile.isDisplayed(),"Chose File btn is not displayed");
		Log.info("ChoseFile is displayed");
		 System.out.println("ChoseFile is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Verified Chose File button is diplayed");



		WebElement OKbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/OkbtnPostcode"));
		
		sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
		Log.info("OK btn is displayed");
		 System.out.println("OK btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Verified OK button is diplayed");


		
		
		WebElement Cancelbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/Cancelbtn"));
		sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Verified cancel File button is diplayed");

		
		Log.info("Cancel button is displayed");
		 System.out.println("Cancel button is displayed");
		 
		 safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/Cancelbtn")));
		 System.out.println("Clicked on cancel button");
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on cancel button");
		
			}
	
	public void verifyUploadupdatefile (String application)
			throws Exception 
			{
		
//		selectColtnetwork(application);
//		Clickon(getwebelement(
//				xml.getlocator("//locators/" + application + "/BelMngPostcode")));
//		Log.info("Clicked on Belgium Manage Postcode");
		Thread.sleep(5000);
		DriverTestcase.logger.log(LogStatus.PASS, "Verifying Upload uodate file Area Link and Fields");

		
		safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/Uploadupdatefile")));
		Log.info("Clicked on Upload update file");
		System.out.println("Clicked on Upload update file");
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Upload update file");

		Thread.sleep(2000);
		
		WebElement ChoseFile = (getwebelement(
				xml.getlocator("//locators/" + application + "/Chosefile")));
		sa.assertTrue(ChoseFile.isDisplayed(),"Chose File btn is not displayed");
		Log.info("ChoseFile is displayed");
		 System.out.println("ChoseFile is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Verified Chose File button is diplayed");



		WebElement OKbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/OkbtnPostcode"));
		
		sa.assertTrue(OKbtn.isDisplayed(),"OK btn is not displayed");
		Log.info("OK btn is displayed");
		 System.out.println("OK btn is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Verified OK button is diplayed");


		
		
		WebElement Cancelbtn = getwebelement(
				xml.getlocator("//locators/" + application + "/Cancelbtn"));
		sa.assertTrue(Cancelbtn.isDisplayed(),"Cancel button is not displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Verified cancel File button is diplayed");

		
		Log.info("Cancel button is displayed");
		 System.out.println("Cancel button is displayed");
		 
		 safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/Cancelbtn")));
		 System.out.println("Clicked on cancel button");
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on cancel button");
		
		
			}
	
	     public void verifyViewHistory (String application)
			throws Exception 
			{
//		selectColtnetwork(application);
//		Clickon(getwebelement(
//				xml.getlocator("//locators/" + application + "/BelMngPostcode")));
//		Log.info("Clicked on Belgium Manage Postcode");
//		Thread.sleep(5000);
	 		
				DriverTestcase.logger.log(LogStatus.PASS, "Verifying View History Link and Fields");

		safeJavaScriptClick(getwebelement(
				xml.getlocator("//locators/" + application + "/ViewHistory")));
		Log.info("Clicked on View History");
		System.out.println("Clicked on View History");
		Thread.sleep(2000);
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on View History");

		
		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/ViewHistoryPage")));
		Log.info("Clicked on View History Record link");
		Log.info("Navigated to View Updated History Record");
		System.out.println("Clicked on View History Record link");
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on View History Record Link  ");


		Thread.sleep(2000);
		
		//Verifying the View History fields
		
		WebElement Filename = getwebelement(
				xml.getlocator("//locators/" + application + "/FileNamefield"));
		sa.assertTrue(Filename.isDisplayed(),"File name is not displayed");
		Log.info("File name is  displayed");
		System.out.println("File name is  is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "File Name Field is displayed");


		
		
		WebElement Datefield = getwebelement(
				xml.getlocator("//locators/" + application + "/Datefield"));
		
		sa.assertTrue(Datefield.isDisplayed(),"Date field is not displayed");
		Log.info("Date field is displayed");
		System.out.println("Date field is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Date Field is displayed");


		
		WebElement UserField = getwebelement(
				xml.getlocator("//locators/" + application + "/UserField"));
		
		sa.assertTrue(UserField.isDisplayed(),"User Field is not displayed");
		Log.info("User Field is displayed");
		System.out.println("User Field is displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "User Field is displayed");

		
		WebElement Updatebyfield = getwebelement(
				xml.getlocator("//locators/" + application + "/Updatebyfield"));

		sa.assertTrue(Updatebyfield.isDisplayed(),"Update by field is not displayed");
		Log.info("Update by field is displayed");
		System.out.println("Update by Field is displayed");
		
		DriverTestcase.logger.log(LogStatus.PASS, "Update By Field is displayed");


			}
	
	
	


	
				
					
		 		}

  