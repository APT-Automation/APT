package com.saksoft.qa.scripthelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.driverlibrary.XMLReader;

public class APT_MCS_CreateAccessDeviceHelper extends DriverHelper{

	public APT_MCS_CreateAccessDeviceHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	WebElement el;

	SoftAssert sa = new SoftAssert();
	
	//APT_MCN_CreateAccessCoreDevice_TestData

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/APT_MCN_CreateAccessCoreDevice.xml");

	public void webelementpresencelogger(WebElement ele, String fieldname) {

		boolean flag = ele.isDisplayed();
		System.out.println("element presence state : " + flag);
		if (flag) {

			System.out.println("webElement is present " + ele.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "Step: expected field is displayed " , fieldname);
		} else {

			System.out.println("webElement is not  present" + ele.getText());
			DriverTestcase.logger.log(LogStatus.FAIL, "Step: expected field is not displayed ", fieldname);
		}

	}
	
	
	public void navigatetomanagecoltnetwork(String application) throws InterruptedException, DocumentException {
	
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/mcnlink")));
		Thread.sleep(2000);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on manage colt's Network link");
	}
	
	public void navigatetocreateaccesscoredevicepage(String application) throws InterruptedException, DocumentException {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createaccesscoredevicelink")));
		Thread.sleep(2000);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on manage colt's Network link");
	}
	

	public String SelectDeviceType(String DeviceName) throws IOException
	{
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\saksoft\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("DeviceType");

		int rowCount = sheet.getLastRowNum();
		
		System.out.println("total row count: "+rowCount);

		int colCount = sheet.getRow(0).getLastCellNum();

		System.out.println("Column count: "+colCount);
		
		for (int i = 0; i <= rowCount-1; i++) {

				DeviceName=sheet.getRow(i).getCell(0).toString();
		}
		System.out.println("Device Name:"+DeviceName);
		return DeviceName;
		
	}
	
	public void verifycreatedevicefields(String application) throws InterruptedException, DocumentException {
		
		try {
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), "Name" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), "Device Type" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), "Vendor Model" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/snmproinputfield")), "Snmpro" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), "Router Id" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), "Management Address" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), "Country" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")), "Modular MSP" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/fulliqnetcheckbox")), "Full IQNET" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/citydropdowninput")), "City" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/sitedropdowninput")), "Site" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/premisedropdowninput")), "Premise" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/addcityswitch")), "Add City" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/addsiteswitch")), "Add Site" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/addpremiseswitch")), "Add Premise");
			
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/clearbutton")), "Clear");
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")), "OK");
			
		
		
		} catch (NoSuchElementException e) {
			System.out.println("webElement is not  present");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step: expected field is not displayed in page ");
			e.printStackTrace();
		}catch (TimeoutException e) {
			System.out.println("webElement is not  present" );
			DriverTestcase.logger.log(LogStatus.FAIL, "Step: expected field is not displayed in page ");
			e.printStackTrace();
			
		}
	
	
	}
	
public void verifyisEmpty(String xpath,String fieldname) {
    	
    	String ele = driver.findElement(By.xpath(xpath)).getAttribute("value");
    	if (ele.isEmpty()) {
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : selected field is empty " + fieldname);
    		
    	}else {
    		
    		sa.fail();
    		DriverTestcase.logger.log(LogStatus.FAIL, "Step : selected field is not empty " + fieldname);
    	}
    	
    	sa.assertAll();
    }

    
    public void  customselection(String application,String element, String fieldname , String ele) throws InterruptedException, DocumentException {
    	
    	if(element.equalsIgnoreCase("yes")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/" + ele)));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected field is : " + fieldname);
		}else {
			
			DriverTestcase.logger.log(LogStatus.INFO, "Step :  " + fieldname + " field is not selected ");
		}
    }


    
    public void verifysnmpversion2c(String application, String snmp2c,String fieldname,String SnmPro, String Snmprw, String snmpro2cvalue, String snmprw2cvalue) throws InterruptedException, DocumentException, IOException {
    	
    	if(snmp2c.equalsIgnoreCase("yes") && SnmPro.equalsIgnoreCase("yes")&& Snmprw.equalsIgnoreCase("yes")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/c2cradiobutton")));
    		Thread.sleep(3000);
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : selected field is : " + fieldname);
    		
    		//clear and enter new value for snmpro and snpprw
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")), snmpro2cvalue);
    		Thread.sleep(3000);
    		String snmprovalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : enterted snmpro value is : " + snmprovalue);
    		Thread.sleep(3000);
    		
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmprwtextfield")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmprwtextfield")), snmprw2cvalue);
    		String snmprwvalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprwtextfield")).getAttribute("value");
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : enterted snmpro value is : " + snmprwvalue);
    		Thread.sleep(3000);
    		
    		
    	}else if(snmp2c.equalsIgnoreCase("yes") && SnmPro.equalsIgnoreCase("no")&& Snmprw.equalsIgnoreCase("no")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/c2cradiobutton")));
    		Thread.sleep(3000);
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : selected field is : " + fieldname);
    		
    		//verify snmpro & snmprw fileds
    		String snmprovalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
    		String snmprwvalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprwtextfield")).getAttribute("value");
    		
    		if(!(snmprovalue.isEmpty())&&!(snmprwvalue.isEmpty())) {
    			DriverTestcase.logger.log(LogStatus.PASS, "Step : snmpro and snmprw fields are not empty");
    			DriverTestcase.logger.log(LogStatus.INFO, "Step : snmpro field value displayed is : " + snmprovalue);
    			DriverTestcase.logger.log(LogStatus.INFO, "Step : snmprw field value displayed is : " + snmprwvalue);
    		}else {
    			
    			sa.fail("snmpro and snmprw fields are empty, it has to be prefilled");
    			DriverTestcase.logger.log(LogStatus.FAIL, "Step : snmpro and snmprw fields are empty, it has to be prefilled");
    		}
    			
    		
    	}else if(snmp2c.equalsIgnoreCase("no")){
    		
    		DriverTestcase.logger.log(LogStatus.INFO, "Step :  " + fieldname + " field is not selected ");
    	}
    	
    	sa.assertAll();
    	
    }
    
    public void verifysnmpversion3selection(String application,String snmp3 ,String fieldname, String Snmpv3Username,String Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue, String Snmpv3Privpasswordvalue) throws InterruptedException, DocumentException, IOException {
    	
         if(snmp3.equalsIgnoreCase("yes") && Snmpv3Username.equalsIgnoreCase("yes") && Snmpv3Authpassword.equalsIgnoreCase("yes") &&  Snmpv3Privpassword.equalsIgnoreCase("yes")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/c3radiobutton")));
    		Thread.sleep(3000);
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : selected field is : " + fieldname);
    		
    		//clear and enter new value for snmpv3username
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3username")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3username")), Snmpv3Usernamevalue);
    		Thread.sleep(3000);
    		String Snmpv3Usernamevalue1 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3username")).getAttribute("value");
    		DriverTestcase.logger.log(LogStatus.INFO, "Step : enterted Snmpv3Username value is : " + Snmpv3Usernamevalue1);
    		Thread.sleep(3000);
    		
    		//clear and enter new value for Snmpv3Authpassword
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3authpassword")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3authpassword")), Snmpv3Authpasswordvalue);
    		Thread.sleep(3000);
    		String snmpv3authpasswordvalue1 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3authpassword")).getAttribute("value");
    		DriverTestcase.logger.log(LogStatus.INFO, "Step : enterted snmpv3authpassword value is : " + snmpv3authpasswordvalue1);
    		Thread.sleep(3000);
    		
    		//clear and enter new value for Snmpv3Privpassword
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3privpassword")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3privpassword")), Snmpv3Privpasswordvalue);
    		Thread.sleep(3000);
    		String Snmpv3Privpasswordvalue1 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3privpassword")).getAttribute("value");
    		DriverTestcase.logger.log(LogStatus.INFO, "Step : enterted Snmpv3Privpassword value is : " + Snmpv3Privpasswordvalue1);
    		Thread.sleep(3000);
    		
    		
    	}else if(snmp3.equalsIgnoreCase("yes") && Snmpv3Username.equalsIgnoreCase("No") && Snmpv3Authpassword.equalsIgnoreCase("No") &&  Snmpv3Privpassword.equalsIgnoreCase("No")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/c3radiobutton")));
    		Thread.sleep(3000);
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : selected field is : " + fieldname);
    		
    		//verify Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword fileds
    		String Snmpv3Usernamevalue2 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3username")).getAttribute("value");
    		String Snmpv3Authpasswordvalue2 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3authpassword")).getAttribute("value");
    		String Snmpv3Privpasswordvalue2 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3privpassword")).getAttribute("value");
    		
    		if(!(Snmpv3Usernamevalue2.isEmpty())&&!(Snmpv3Authpasswordvalue2.isEmpty()) && !(Snmpv3Privpasswordvalue2.isEmpty())) {
    			DriverTestcase.logger.log(LogStatus.PASS, "Step : Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword fields are not empty");
    			DriverTestcase.logger.log(LogStatus.INFO, "Step : snmpro field value displayed is : " + Snmpv3Usernamevalue2);
    			DriverTestcase.logger.log(LogStatus.INFO, "Step : snmprw field value displayed is : " + Snmpv3Authpasswordvalue2);
    			DriverTestcase.logger.log(LogStatus.INFO, "Step : snmprw field value displayed is : " + Snmpv3Privpasswordvalue2);
    		}else {
    			
    			sa.fail("Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword fields are empty, it has to be prefilled");
    			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword fields are empty, it has to be prefilled");
    		}
    			
    		
    	}else if(snmp3.equalsIgnoreCase("no")){
    		
    		DriverTestcase.logger.log(LogStatus.INFO, "Step :  " + fieldname + " field is not selected ");
    	}
         
       sa.assertAll();
    }
    
    
    public void verifyExistingcitysitepremiseselection(String application,String existingcity, String existingcityvalue, String existingsite, String existingsitevalue, String existingpremise, String existingpremisevalue) throws InterruptedException, IOException, DocumentException {
    	
    	if(existingcity.equalsIgnoreCase("yes")&& existingsite.equalsIgnoreCase("yes")&& existingpremise.equalsIgnoreCase("Yes")) {
    		
    		//select city
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/citydropdowninput")), existingcityvalue);
    		Thread.sleep(3000);
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected Existing City Name is : " + existingcityvalue );
    		
    		WebElement Cityele = driver.findElement(By.xpath("//span[text()='"+existingcityvalue+"']"));
    		Cityele.click();
    		Thread.sleep(3000);
    		
    		//Select site
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/sitedropdowninput")), existingsitevalue);
    		Thread.sleep(3000);
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected Existing Site Name is : " + existingsitevalue );
    		
    		WebElement siteele = driver.findElement(By.xpath("//span[text()='"+existingsitevalue+"']"));
    		siteele.click();
    		Thread.sleep(3000);
    		
    		//Select Premise
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premisedropdowninput")), existingpremisevalue);
    		Thread.sleep(3000);
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected Existing Premise Name is : " + existingpremisevalue );
    		
    		WebElement premiseele = driver.findElement(By.xpath("//span[text()='"+existingpremisevalue+"']"));
    		premiseele.click();
    		Thread.sleep(3000);
    	}else {
    		
    		DriverTestcase.logger.log(LogStatus.INFO, "Step : Existing City, Site and Premise Name is not selected ! "  );
    	}
    	
    }

   
    public void verifynewcityselection(String application, String newcity, String cityname, String Citycode, String sitename, String sitecode, String premisename, String premisecode) throws InterruptedException, DocumentException, IOException {
    	
    	if(newcity.equalsIgnoreCase("Yes")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addcityswitch")));
    		Thread.sleep(4000);
    		
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/citynameinputfield")), cityname);
    		Thread.sleep(2000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/citycodeinputfield")), Citycode);
    		Thread.sleep(2000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/sitenameinputfield")), sitename);
    		Thread.sleep(2000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/sitecodeinputfield")), sitecode);
    		Thread.sleep(2000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premisenameinputfield")), premisename);
    		Thread.sleep(2000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premisecodeinputfield")), premisecode);
    		Thread.sleep(2000);	
    		
    	}else {
    		
    		DriverTestcase.logger.log(LogStatus.INFO, "Step : Add City is not selected ! "  );
    	}
    	
    	
    }
    
   
    public void verifynewsiteselection(String application, String newsite, String sitename, String sitecode, String premisename, String premisecode) throws InterruptedException, DocumentException, IOException {
    	
    	if(newsite.equalsIgnoreCase("Yes")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsiteswitch")));
    		Thread.sleep(4000);
    		
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/sitenameinputfield")), sitename);
    		Thread.sleep(2000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/sitecodeinputfield")), sitecode);
    		Thread.sleep(2000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premisenameinputfield")), premisename);
    		Thread.sleep(2000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premisecodeinputfield")), premisecode);
    		Thread.sleep(2000);	
    		
    	}else {
    		
    		DriverTestcase.logger.log(LogStatus.INFO, "Step : Add Site is not selected ! "  );
    	}
    	
    	
    }
    
    public void verifysnmPro(String application,String SnmPro,String snmpro2cvalue) throws IOException, InterruptedException, DocumentException
    {
    	if(SnmPro.equalsIgnoreCase("yes")) {
    		//clear and enter new value for snmpro
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")), snmpro2cvalue);
    		Thread.sleep(3000);
    		String snmprovalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : enterted snmpro value is : " + snmprovalue);
    		Thread.sleep(3000);
    	}else if(SnmPro.equalsIgnoreCase("no")) {
    		String snmprovalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
    		if(!(snmprovalue.isEmpty())) {
    			DriverTestcase.logger.log(LogStatus.PASS, "Step : snmpro field is not empty");
    			DriverTestcase.logger.log(LogStatus.INFO, "Step : snmpro field value displayed is : " + snmprovalue);
    		}else {
    			sa.fail("snmpro field is empty, it has to be prefilled");
    			DriverTestcase.logger.log(LogStatus.FAIL, "Step : snmpro field is empty, it has to be prefilled");
    		}
    	}
    	sa.assertAll();
    	
    }
    
    
    //Create Access Router Device
    public void verifydevicecreation_AccessRouter(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, 
    		String ios, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode, 
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
			//ios xr selection
			customselection(application, ios, "IOS-XR", "iosxrcheckbox");
						
			//connection protocol selection-Telnet
			customselection(application, telnet, "Telnet", "telnetradiobutton");
			//connection protocol selection- ssh
			customselection(application, ssh, "SSH", "sshradiobutton");
			
			//snmp version -2c selection
			verifysnmpversion2c(application, snmp2c,"Snmp Version-2C",SnmPro, Snmprw, snmpro2cvalue, snmprw2cvalue);
			
			//snmp version - 3 selection
			verifysnmpversion3selection(application,snmp3 ,"Snmp Version-3",Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword, Snmpv3Usernamevalue,Snmpv3Authpasswordvalue, Snmpv3Privpasswordvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(20000);
			
    	
    	} catch (StaleElementReferenceException e) {
			
		}
    	
    	
    }
    
    
    //Create Access Switch Device
    public void verifydevicecreation_AccessSwitch(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode, 
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			//connection protocol selection-Telnet
			customselection(application, telnet, "Telnet", "telnetradiobutton");
			//connection protocol selection- ssh
			customselection(application, ssh, "SSH", "sshradiobutton");
			
			//snmp version -2c selection
			verifysnmpversion2c(application, snmp2c,"Snmp Version-2C",SnmPro, Snmprw, snmpro2cvalue, snmprw2cvalue);
			
			//snmp version - 3 selection
			verifysnmpversion3selection(application,snmp3 ,"Snmp Version-3",Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword, Snmpv3Usernamevalue,Snmpv3Authpasswordvalue, Snmpv3Privpasswordvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);
    	
   
    	} catch (StaleElementReferenceException e) {
			
		}
    	
        	
    }
    
    
    //Create Core Router Device
    public void verifydevicecreation_CoreRouter(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode, 
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			//connection protocol selection-Telnet
			customselection(application, telnet, "Telnet", "telnetradiobutton");
			//connection protocol selection- ssh
			customselection(application, ssh, "SSH", "sshradiobutton");
			
			//snmp version -2c selection
			verifysnmpversion2c(application, snmp2c,"Snmp Version-2C",SnmPro, Snmprw, snmpro2cvalue, snmprw2cvalue);
			
			//snmp version - 3 selection
			verifysnmpversion3selection(application,snmp3 ,"Snmp Version-3",Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword, Snmpv3Usernamevalue,Snmpv3Authpasswordvalue, Snmpv3Privpasswordvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);
    	
    	
    	} catch (StaleElementReferenceException e) {
			
		}
    	
    }
    
    //Create DCN Device
    public void verifydevicecreation_DCNDevice(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String SnmPro, String snmpro2cvalue,
    		String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode,
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			verifysnmPro(application, SnmPro, snmpro2cvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);

    	
    	} catch (StaleElementReferenceException e) {
			
		}
    	
    }
    
    //Create DSLAM Device
    public void verifydevicecreation_DSLAM(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String SnmPro, String snmpro2cvalue,
    		String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode,
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			verifysnmPro(application, SnmPro, snmpro2cvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);
    	
    	
    	} catch (StaleElementReferenceException e) {
			
		}
    	
    }
    
    //Create Firewall Device
    public void verifydevicecreation_Firewall(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String SnmPro, String snmpro2cvalue,
    		String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode,
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			verifysnmPro(application, SnmPro, snmpro2cvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);
    	
        	
    	} catch (StaleElementReferenceException e) {
			
		}
    	
    }
    
    //Create Keyserver Device
    public void verifydevicecreation_Keyserver(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode, 
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			//connection protocol selection-Telnet
			customselection(application, telnet, "Telnet", "telnetradiobutton");
			//connection protocol selection- ssh
			customselection(application, ssh, "SSH", "sshradiobutton");
			
			//snmp version -2c selection
			verifysnmpversion2c(application, snmp2c,"Snmp Version-2C",SnmPro, Snmprw, snmpro2cvalue, snmprw2cvalue);
			
			//snmp version - 3 selection
			verifysnmpversion3selection(application,snmp3 ,"Snmp Version-3",Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword, Snmpv3Usernamevalue,Snmpv3Authpasswordvalue, Snmpv3Privpasswordvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);
    	
    	
    	} catch (StaleElementReferenceException e) {
			
		}

    }
    
    //Create Load Balancer Device
    public void verifydevicecreation_LoadBalancer(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String SnmPro, String snmpro2cvalue,
    		String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode,
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			verifysnmPro(application, SnmPro, snmpro2cvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);

    	
    	} catch (StaleElementReferenceException e) {
			
		}
    	
    }
    
    //Create MDFFirewall Device
    public void verifydevicecreation_MDFFirewall(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode, 
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			//connection protocol selection-Telnet
			customselection(application, telnet, "Telnet", "telnetradiobutton");
			//connection protocol selection- ssh
			customselection(application, ssh, "SSH", "sshradiobutton");
			
			//snmp version -2c selection
			verifysnmpversion2c(application, snmp2c,"Snmp Version-2C",SnmPro, Snmprw, snmpro2cvalue, snmprw2cvalue);
			
			//snmp version - 3 selection
			verifysnmpversion3selection(application,snmp3 ,"Snmp Version-3",Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword, Snmpv3Usernamevalue,Snmpv3Authpasswordvalue, Snmpv3Privpasswordvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);

    	
    	} catch (StaleElementReferenceException e) {
			
		}
   
    }
    
    //Create Mini DSLAM Device
    public void verifydevicecreation_MiniDSLAM(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String SnmPro, String snmpro2cvalue,
    		String routerid, String Country, String managementaddress, String managementaddressbutton, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode,
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			verifysnmPro(application, SnmPro, snmpro2cvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			//=======================================================================
			
			//Click on management address button
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementaddressbutton")));
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);
  
    	
    	} catch (StaleElementReferenceException e) {
			
		}
    	
    }
    
    //Create Prizmnet Device
    public void verifydevicecreation_Prizmnet(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode, 
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			//connection protocol selection-Telnet
			customselection(application, telnet, "Telnet", "telnetradiobutton");
			//connection protocol selection- ssh
			customselection(application, ssh, "SSH", "sshradiobutton");
			
			//snmp version -2c selection
			verifysnmpversion2c(application, snmp2c,"Snmp Version-2C",SnmPro, Snmprw, snmpro2cvalue, snmprw2cvalue);
			
			//snmp version - 3 selection
			verifysnmpversion3selection(application,snmp3 ,"Snmp Version-3",Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword, Snmpv3Usernamevalue,Snmpv3Authpasswordvalue, Snmpv3Privpasswordvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);

    	} catch (StaleElementReferenceException e) {
			
		}
  
    }
    
    //Create RouterCPE Device
    public void verifydevicecreation_RouterCPE(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, 
    		String ios, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode, 
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
			//ios xr selection
			customselection(application, ios, "IOS-XR", "iosxrcheckbox");
						
			//connection protocol selection-Telnet
			customselection(application, telnet, "Telnet", "telnetradiobutton");
			//connection protocol selection- ssh
			customselection(application, ssh, "SSH", "sshradiobutton");
			
			//snmp version -2c selection
			verifysnmpversion2c(application, snmp2c,"Snmp Version-2C",SnmPro, Snmprw, snmpro2cvalue, snmprw2cvalue);
			
			//snmp version - 3 selection
			verifysnmpversion3selection(application,snmp3 ,"Snmp Version-3",Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword, Snmpv3Usernamevalue,Snmpv3Authpasswordvalue, Snmpv3Privpasswordvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(20000);
			
    	
    	} catch (StaleElementReferenceException e) {
			
		}
 
    }
    
    //Create Traffic Aggregator Device
    public void verifydevicecreation_TrafficAggregator(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String SnmPro, String snmpro2cvalue,
    		String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode,
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			verifysnmPro(application, SnmPro, snmpro2cvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);

    	} catch (StaleElementReferenceException e) {
			
		}
 	
    }
    
    //Create Voice Gateway Device
    public void verifydevicecreation_VoiceGateway(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String SnmPro, String snmpro2cvalue,
    		String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode,
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			verifysnmPro(application, SnmPro, snmpro2cvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);

    	} catch (StaleElementReferenceException e) {
			
		}
 
    }
    
    //Create VOIP Access DAS Switch Device
    public void verifydevicecreation_VOIPAccessDASSwitch(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, String SnmPro, String snmpro2cvalue,
    		String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode,
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
									
			verifysnmPro(application, SnmPro, snmpro2cvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(3000);
    	
    	} catch (StaleElementReferenceException e) {
			
		}
    	
    }
    
    //Create WDM CPE Device
    public void verifydevicecreation_WDMCPE(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, 
    		String ios, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename,  
    		String sitecode,  String premisename,  String premisecode, 
    		String newsite,String sitename1,  String sitecode1,  String premisename1,  String premisecode1
    		) throws InterruptedException, IOException, DocumentException {
    	
    	try {
    		//name
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered name is : " + name);
			
			//devicetype
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")), devicetype);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered device type is : " + devicetype);
			
			WebElement devtype = driver.findElement(By.xpath("//span[text()='"+devicetype+"']"));
			String devtypevalue = devtype.getText();
			devtype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : device type is selected : " + devtypevalue);
			
			//vendormodel
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), vendormodel);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered vendor model is : " + vendormodel);
			
			WebElement ventype = driver.findElement(By.xpath("//span[text()='"+vendormodel+"']"));
			String ventypevalue= ventype.getText();
			ventype.click();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Vendor Model is selected : " + ventypevalue);
			
			//modular msp selection
			customselection(application, modularmsp, "Modular MSP", "modularmspcheckbox");
			//full iqnet selection
			customselection(application, fulliqnet, "Full IQNET", "fulliqnetcheckbox");
			//ios xr selection
			customselection(application, ios, "IOS-XR", "iosxrcheckbox");
						
			//connection protocol selection-Telnet
			customselection(application, telnet, "Telnet", "telnetradiobutton");
			//connection protocol selection- ssh
			customselection(application, ssh, "SSH", "sshradiobutton");
			
			//snmp version -2c selection
			verifysnmpversion2c(application, snmp2c,"Snmp Version-2C",SnmPro, Snmprw, snmpro2cvalue, snmprw2cvalue);
			
			//snmp version - 3 selection
			verifysnmpversion3selection(application,snmp3 ,"Snmp Version-3",Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword, Snmpv3Usernamevalue,Snmpv3Authpasswordvalue, Snmpv3Privpasswordvalue);
			
			//enter router id
			
			verifyisEmpty( "//input[@id='routerId']", "Router ID Textfield");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/routeridtextfield")), routerid);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered Router ID is : " + routerid);
			
			//select country
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), Country);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : selected country is : " + Country);
			
			WebElement countryele = driver.findElement(By.xpath("//span[text()='"+Country+"']"));
			countryele.click();
			Thread.sleep(3000);
			
			//enter management address
			javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			verifyisEmpty("//input[@id='managementAddress']", "Management Address Textfield");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), managementaddress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered management address is : " + managementaddress);
			
			
			//select city
			verifyExistingcitysitepremiseselection(application,existingcity, existingcityvalue,existingsite,existingsitevalue, existingpremise, existingpremisevalue);
			verifynewcityselection(application, newcity,cityname, Citycode,sitename, sitecode, premisename, premisecode);
			//verifynewsiteselection(application,newsite, sitename1, sitecode1, premisename1, premisecode1);
    	
    	    //TODO: premise
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
			Thread.sleep(20000);

    	} catch (StaleElementReferenceException e) {
			
		}

    }
    	//Search Device
    	public void verifydevicesearch(String application, String devicename) throws InterruptedException, DocumentException {
    		   		
    		navigatetomanagecoltnetwork(application);
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SearchDeviceLink")));
    		Thread.sleep(2000);
    		DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on search for device link");
    		
    	}
    	    
}
