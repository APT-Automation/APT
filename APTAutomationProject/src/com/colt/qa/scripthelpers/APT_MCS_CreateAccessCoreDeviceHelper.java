package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.xml.internal.bind.v2.TODO;

public class APT_MCS_CreateAccessCoreDeviceHelper extends DriverHelper{

	public APT_MCS_CreateAccessCoreDeviceHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
		
	WebElement el;

	SoftAssert sa = new SoftAssert();
	
	//APT_MCN_CreateAccessCoreDevice_TestData

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_MCN_CreateAccessCoreDevice.xml");

	public void webelementpresencelogger(WebElement ele, String fieldname) {
		
	try {	
		boolean flag = ele.isDisplayed();
		Log.info("element presence state : " + flag);
		if (flag) {

			Log.info("webElement is present " + ele.getText());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: expected field is displayed " , fieldname);
		} else {

			Log.info("webElement is not  present" + ele.getText());
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: expected field is not displayed ", fieldname);
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		Log.info("webElement is not  present" + ele.getText());
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: expected field is not displayed ", fieldname);
	}

	}
	
	
	public void navigatetomanagecoltnetwork(String application) throws InterruptedException, DocumentException {
	
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/mcnlink")));
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on manage colt's Network link");
	}
	
	public void navigatetocreateaccesscoredevicepage(String application) throws InterruptedException, DocumentException {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createaccesscoredevicelink")));
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on 'Create Access/Core Device link");
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
			Log.info("webElement is not  present");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: expected field is not displayed in page ");
			e.printStackTrace();
		}catch (TimeoutException e) {
			Log.info("webElement is not  present" );
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: expected field is not displayed in page ");
			e.printStackTrace();
			
		}
	
	
	}

	
    public void verifydevicecreation_AccessRouter(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, 
    		String ios, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode,
    		String sitename, String sitecode,  String premisename,  String premisecode, 
    		String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {
    
    	
    	try {
    		scrolltoend();
    //Verify Mandatory fields
    		click_commonMethod(application, "OK", "okbutton", xml);
        	Thread.sleep(3000);
        	
        	warningMessage_commonMethod(application, "warningMessage_Country", "Country", xml);
        	warningMessage_commonMethod(application, "warningMessage_city", "City", xml);
        	warningMessage_commonMethod(application, "WarningMesassage_site", "Site", xml);
        	
        	scrollToTop();
        Thread.sleep(2000);
        	warningMessage_commonMethod(application, "warningMessage_name", "Device Name", xml);
        	warningMessage_commonMethod(application, "warningMessage_deviceType", "Device Type", xml);
        	warningMessage_commonMethod(application, "warningMessage_vendor", "Vendor/Model", xml);
        	warningMessage_commonMethod(application, "warningMessage_routerId", "Router Id", xml);
        	
        	
    		
    		//name
			addtextFields_commonMethod(application, "Name", "nametextfield", name, xml);
			
		
			//devicetype
			selectValueInsideDropdown(application, "devicetypeinput", "Device Type", devicetype, xml);
			
			
			//vendormodel
			addDropdownValues_commonMethod(application, "Vendor/Model", "vendormodelinput", vendormodel, xml);
			
			WebElement vendor=getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput"));
			scrolltoview(vendor);
			Thread.sleep(1000);
			
			//modular msp selection
			addCheckbox_commonMethod(application, "modularmspcheckbox", "Modular MSP", modularmsp, "No", xml);
			
			//full iqnet selection
			addCheckbox_commonMethod(application, "fulliqnetcheckbox", "Full IQNET", fulliqnet,"No", xml);
			
			if(fulliqnet.equalsIgnoreCase("yes")) {
				alertMessage_IQNet(application);
			}
			
			//ios xr selection
			addCheckbox_commonMethod(application, "iosxrcheckbox", "IOS-XR", ios, "No", xml);
	
			
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
		
			
		//Router id
				addtextFields_commonMethod(application, "Router Id", "routeridtextfield", routerid, xml);
				
		//Management address
				javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
				addtextFields_commonMethod(application, "Management Address", "managementaddresstextbox", managementaddress, xml);
						
			
			//select country
				scrolltoend();
				Thread.sleep(2000);
				
				selectValueInsideDropdown(application, "countryDropdown_selectTag", "Country", Country, xml);
				
				
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
					
				   selectValueInsideDropdown(application, "cityDropdown_selectTag", "City", existingcityvalue, xml);
					
				 //Existing Site
					  if(existingsite.equalsIgnoreCase("yes") & newsite.equalsIgnoreCase("no")) {
						  selectValueInsideDropdown(application, "siteDropdown_selectTag", "Site", existingsitevalue, xml);
						  
					 //Existing Premise
						  if(existingpremise.equalsIgnoreCase("yes") & NewPremise.equalsIgnoreCase("no")) {
							  selectValueInsideDropdown(application, "premiseDropdown_selectTag", "Premise", existingpremisevalue, xml);
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
    	Thread.sleep(2000);
    	click_commonMethod(application, "OK", "okbutton", xml);
    	Thread.sleep(3000);
    }
    
    
    public void verifyenteredValue_forDeviceCreation(String application, String name, String devicetype, 
    		String vendormodel, String modularmsp, String fulliqnet, 
    		String ios, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
    		String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
    		Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
    		String Snmpv3Privpasswordvalue, String routerid, String Country, String managementaddress, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode,
    		String sitename, String sitecode,  String premisename,  String premisecode, 
    		String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {
    	
    //Device Name	
    	compareText_InViewPage(application,"Name", name, xml);
    	
    //Device type
    	compareText_InViewPage(application, "Device Type", devicetype, xml);
    	
    //Vendor/model
    	compareText_InViewPage(application, "Vendor/Model", vendormodel, xml);
    	
    //Connectivity protocol
    	if((telnet.equalsIgnoreCase("Yes")) && (ssh.equalsIgnoreCase("No"))){
    		compareText_InViewPage(application, "Connectivity Protocols", "telnet", xml);
    	}
    	else if((telnet.equalsIgnoreCase("no")) && (ssh.equalsIgnoreCase("Yes"))){
    		compareText_InViewPage(application, "Connectivity Protocols", "ssh", xml);
    	}
    	
    //Router Id
    	compareText_InViewPage(application, "Router Id", routerid, xml);
    	
    //SNMP Version
    	if((snmp2c.equalsIgnoreCase("Yes")) && (snmp3.equalsIgnoreCase("No"))) {
    		
    		//Snmpro	
    		if(snmpro2cvalue.equalsIgnoreCase("null")) {
    			compareText_InViewPage(application, "Snmpro", "incc", xml);
    		}else {
    			compareText_InViewPage(application, "Snmpro", snmpro2cvalue, xml);
    		}
    		
    		//Snmprw
    		if(snmprw2cvalue.equalsIgnoreCase("null")) {
    			compareText_InViewPage(application, "Snmprw", "ip4corp3", xml);
    		}else {
    			compareText_InViewPage(application, "Snmprw", snmprw2cvalue, xml);
    		}
    			
    		
    	}
    	else if((snmp2c.equalsIgnoreCase("No")) && (snmp3.equalsIgnoreCase("Yes"))) {
    		
    		//Snmp v3 Username
    		if(Snmpv3Usernamevalue.equalsIgnoreCase("Null")) {
    			compareText_InViewPage(application, "Snmp v3 Username", "colt-nms", xml);
    		}else {
    			compareText_InViewPage(application, "Snmp v3 Username", Snmpv3Usernamevalue, xml);
    		}
    			
    			
    		//Snmp v3 Auth Password
    			if(Snmpv3Authpasswordvalue.equalsIgnoreCase("null")) {
    				compareText_InViewPage(application, "Snmp v3 Auth password", "OrHzjWmRvr4piJZb", xml);
    			}else {
    				compareText_InViewPage(application, "Snmp v3 Auth password", Snmpv3Authpasswordvalue, xml);
    			}
    			
    			
    		//Snmp v3 Priv Password
    			if(Snmpv3Privpasswordvalue.equalsIgnoreCase("Null")) {
    				compareText_InViewPage(application, "Snmp v3 Priv password", "3k0hw8thNxHucQkE", xml);
    			}else {
    				compareText_InViewPage(application, "Snmp v3 Priv password", Snmpv3Privpasswordvalue, xml);
    			}
    			
    	}
    	
    //Management Address
    	compareText_InViewPage(application, "Management Address", managementaddress, xml);
    	
    //Country
    	compareText_InViewPage(application, "Country", Country, xml);
    	
    	
    //City
    	if((existingcity.equalsIgnoreCase("yes")) && (newcity.equalsIgnoreCase("NO"))) {
    		
    		//Existing City
    		  	compareText_InViewPage(application, "City", existingcityvalue, xml);
    		
    	}
    	else if((existingcity.equalsIgnoreCase("NO")) && (newcity.equalsIgnoreCase("Yes"))) {
    		
    		//new City
    			compareText_InViewPage(application, "City", cityname, xml);
    		
    	}
    	
    //Site
    	if((existingsite.equalsIgnoreCase("yes")) && (newsite.equalsIgnoreCase("NO"))) {
    		
    		//Existing Site
    			compareText_InViewPage(application, "Site", existingsitevalue, xml);
    	}
    	else if((existingsite.equalsIgnoreCase("No")) && (newsite.equalsIgnoreCase("Yes"))) {
    		
    		//New Site
    			compareText_InViewPage(application, "Site", sitename, xml);
    		
    	}
    	
    //Premise
    	if((existingpremise.equalsIgnoreCase("yes")) && (NewPremise.equalsIgnoreCase("NO"))) {
    		
    		//Existing premise
    			 compareText_InViewPage(application, "Premise", existingpremisevalue, xml);
    		
    	}
    	else if((existingpremise.equalsIgnoreCase("No")) && (NewPremise.equalsIgnoreCase("Yes"))) {
    		
    		//new premise
    			compareText_InViewPage(application, "Premise", premisename, xml);
    	}
    	
    	
   }
    
    
    public void verifyDeviceCreationMessage(String application) throws IOException, InterruptedException, DocumentException {
    	waitForpageload();
    	waitforPagetobeenable();
    	
    	scrollToTop();
    	Thread.sleep(2000);
    	
    	
		boolean alertmsg1 = false;
		
	try {
		alertmsg1=getwebelement(xml.getlocator("//locators/" + application + "/succesMsg_alertDisplay")).isDisplayed();
		if(alertmsg1) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Success Message is displaying for device creation");
			Log.info(" 'Success Message is displaying for device creation");
			
			String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_Alert")).getText();
			assertEquals(actualmsg, "Device created successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Success Message is displaying as: "+ actualmsg);
			
			successScreenshot(application);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Success Message is not displaying for device creation");
			Log.info(" 'Success Message is not displaying for device creation");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Success Message is not displaying for device creation");
		Log.info(" 'Success Message is not displaying for device creation");
	}
	}
	
    
 public void verifyDeviceUpdationSuccessMessage(String application) throws IOException, InterruptedException, DocumentException {
    	
	 waitForpageload();
	 waitforPagetobeenable();
	 Thread.sleep(2000);
	 
	 scrollToTop();
    	Thread.sleep(2000);
    	
		boolean alertmsg1 = false;
		
	try {
		alertmsg1=getwebelement(xml.getlocator("//locators/" + application + "/succesMsg_alertDisplay")).isDisplayed();
		if(alertmsg1) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Success Message is displaying for device Updation");
			Log.info(" 'Success Message is displaying for device Updation");
			String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_Alert")).getText();
//			assertEquals(actualmsg, "Device updated successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Success Message is displaying as: "+ actualmsg);
			
			successScreenshot(application);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Success Message is not displaying for device Updation");
			Log.info(" 'Success Message is not displaying for device Updation");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Success Message is not displaying for device Updation");
		Log.info(" 'Success Message is not displaying for device Updation");
	}
	   
	}
    
    
    
    public void  customselection(String application,String element, String fieldname , String ele) throws InterruptedException, DocumentException {
    boolean availability=false;	
    	if(element.equalsIgnoreCase("yes")) {
			
    	try {	
    		availability=getwebelement(xml.getlocator("//locators/" + application + "/" + ele)).isDisplayed();
    		
    		if(availability) {
    			ExtentTestManager.getTest().log(LogStatus.PASS, fieldname + " is displaying as expected");
    			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/" + ele)));
    			Thread.sleep(3000);
    			ExtentTestManager.getTest().log(LogStatus.PASS, fieldname+ " is selected as expected");
    		}else {
    			ExtentTestManager.getTest().log(LogStatus.FAIL, fieldname + " is not displaying");
    			Log.info(fieldname + " is not displaying");
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		ExtentTestManager.getTest().log(LogStatus.FAIL, fieldname + " is not displaying");
			Log.info(fieldname + " is not displaying");
    	}
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step :  " + fieldname + " checkbox is not selected ");
		}
    }
    
    
    public void verifyDefaultSelection_connectivityprotocol_ssh(String application) throws InterruptedException, DocumentException {
    	boolean conectivityProtocolssh_availability=false;
    	boolean conectivityProtocolssh_Selection=false;
    try {	
    	conectivityProtocolssh_availability=getwebelement(xml.getlocator("//locators/" + application + "/sshradiobutton")).isDisplayed();
    	if(conectivityProtocolssh_availability) {

    		ExtentTestManager.getTest().log(LogStatus.PASS, " 'SSH' is displaying under 'Connectivity protocol' as expected");
    		Log.info(" 'SSH' is displaying under 'Connectivity protocol' as expected");
    		
    		conectivityProtocolssh_Selection=getwebelement(xml.getlocator("//locators/" + application + "/sshradiobutton")).isSelected();
    		if(conectivityProtocolssh_Selection) {
    			ExtentTestManager.getTest().log(LogStatus.PASS, " 'SSH' is selected under 'Connectivity protocol' by default as expected");
    			Log.info(" 'SSH' is selected under 'Connectivity protocol' as expected");
    			
    		}else {
    			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'SSH' is not selected by default under 'Connectivity protocol'");
    			Log.info(" 'SSH' is not selected under 'Connectivity protocol'");
    		}
    	}else {
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'SSH' is not displaying under 'Connectivity protocol'");
    		Log.info(" 'SSH' is not displaying under 'Connectivity protocol'");
    	}
    	
    }catch(NoSuchElementException e) {
    	e.printStackTrace();
    	ExtentTestManager.getTest().log(LogStatus.FAIL, " 'SSH' is not displaying under 'Connectivity protocol'");
		Log.info(" 'SSH' is not displaying under 'Connectivity protocol'");
		
    }catch(Exception ee) {
    	ee.printStackTrace();
    	ExtentTestManager.getTest().log(LogStatus.FAIL, " 'SSH' is not selected under 'Connectivity protocol'");
		Log.info(" 'SSH' is not selected under 'Connectivity protocol'");
    	
    }
    }
    
    
    public void verifyDefaultSelection_connectivityprotocol_telnet(String application) throws InterruptedException, DocumentException {
    	boolean conectivityProtocoltelnet_availability=false;
    	boolean conectivityProtocoltelnet_Selection=false;
    try {	
    	conectivityProtocoltelnet_availability=getwebelement(xml.getlocator("//locators/" + application + "/telnetradiobutton")).isDisplayed();
    	if(conectivityProtocoltelnet_availability) {

    		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Telnet' is displaying under 'Connectivity protocol' as expected");
    		Log.info(" 'Telnet' is displaying under 'Connectivity protocol' as expected");
    		
    		conectivityProtocoltelnet_Selection=getwebelement(xml.getlocator("//locators/" + application + "/telnetradiobutton")).isSelected();
    		if(conectivityProtocoltelnet_Selection) {
    			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Telnet' is selected under 'Connectivity protocol' by default");
    			Log.info(" 'Telnet' is selected under 'Connectivity protocol'");
    			
    		}else {
    			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Telnet' is not selected under 'Connectivity protocol' by default as expected");
    			Log.info(" 'Telnet' is not selected under 'Connectivity protocol'");
    		}
    	}else {
    		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Telnet' is not displaying under 'Connectivity protocol'");
    		Log.info(" 'Telnet' is not displaying under 'Connectivity protocol'");
    	}
    	
   	
    }catch(Exception ee) {
    	ee.printStackTrace();
    	ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Telnet' is not displaying under 'Connectivity protocol'");
		Log.info(" 'Telnet' is not displaying under 'Connectivity protocol'");
    	
    }
    }


    
    public void verifysnmpversion2c(String application, String snmp2c,String fieldname,String SnmPro, String Snmprw, String snmpro2cvalue, String snmprw2cvalue) throws InterruptedException, DocumentException, IOException {
    	
    	if(snmp2c.equalsIgnoreCase("yes") && SnmPro.equalsIgnoreCase("yes")&& Snmprw.equalsIgnoreCase("yes")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/c2cradiobutton")));
    		Thread.sleep(3000);
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : selected field is : " + fieldname);
    		
    		//clear and enter new value for snmpro and snpprw
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")), snmpro2cvalue);
    		Thread.sleep(3000);
    		String snmprovalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : enterted snmpro value is : " + snmprovalue);
    		Thread.sleep(3000);
    		
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmprwtextfield")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmprwtextfield")), snmprw2cvalue);
    		String snmprwvalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprwtextfield")).getAttribute("value");
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : enterted snmpro value is : " + snmprwvalue);
    		Thread.sleep(3000);
    		
    		
    	}else if(snmp2c.equalsIgnoreCase("yes") && SnmPro.equalsIgnoreCase("no")&& Snmprw.equalsIgnoreCase("no")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/c2cradiobutton")));
    		Thread.sleep(3000);
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : selected field is : " + fieldname);
    		
    		//verify snmpro & snmprw fileds
    		String snmprovalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
    		String snmprwvalue = getwebelement(xml.getlocator("//locators/" + application + "/snmprwtextfield")).getAttribute("value");
    		
    		if(!(snmprovalue.isEmpty())&&!(snmprwvalue.isEmpty())) {
    			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : snmpro and snmprw fields are not empty");
    			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : snmpro field value displayed is : " + snmprovalue);
    			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : snmprw field value displayed is : " + snmprwvalue);
    		}else {
    			
    			sa.fail("snmpro and snmprw fields are empty, it has to be prefilled");
    			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : snmpro and snmprw fields are empty, it has to be prefilled");
    		}
    			
    		
    	}else if(snmp2c.equalsIgnoreCase("no")){
    		
    		ExtentTestManager.getTest().log(LogStatus.INFO, "Step :  " + fieldname + " field is not selected ");
    	}
    	
    	sa.assertAll();
    	
    }
    
    public void verifysnmpversion3selection(String application,String snmp3 ,String fieldname, String Snmpv3Username,String Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue, String Snmpv3Privpasswordvalue) throws InterruptedException, DocumentException, IOException {
    	
         if(snmp3.equalsIgnoreCase("yes") && Snmpv3Username.equalsIgnoreCase("yes") && Snmpv3Authpassword.equalsIgnoreCase("yes") &&  Snmpv3Privpassword.equalsIgnoreCase("yes")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/c3radiobutton")));
    		Thread.sleep(3000);
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : selected field is : " + fieldname);
    		
    		//clear and enter new value for snmpv3username
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3username")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3username")), Snmpv3Usernamevalue);
    		Thread.sleep(3000);
    		String Snmpv3Usernamevalue1 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3username")).getAttribute("value");
    		ExtentTestManager.getTest().log(LogStatus.INFO, "Step : enterted Snmpv3Username value is : " + Snmpv3Usernamevalue1);
    		Thread.sleep(3000);
    		
    		//clear and enter new value for Snmpv3Authpassword
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3authpassword")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3authpassword")), Snmpv3Authpasswordvalue);
    		Thread.sleep(3000);
    		String snmpv3authpasswordvalue1 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3authpassword")).getAttribute("value");
    		ExtentTestManager.getTest().log(LogStatus.INFO, "Step : enterted snmpv3authpassword value is : " + snmpv3authpasswordvalue1);
    		Thread.sleep(3000);
    		
    		//clear and enter new value for Snmpv3Privpassword
    		Clear(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3privpassword")));
    		Thread.sleep(3000);
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3privpassword")), Snmpv3Privpasswordvalue);
    		Thread.sleep(3000);
    		String Snmpv3Privpasswordvalue1 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3privpassword")).getAttribute("value");
    		ExtentTestManager.getTest().log(LogStatus.INFO, "Step : enterted Snmpv3Privpassword value is : " + Snmpv3Privpasswordvalue1);
    		Thread.sleep(3000);
    		
    		
    	}else if(snmp3.equalsIgnoreCase("yes") && Snmpv3Username.equalsIgnoreCase("No") && Snmpv3Authpassword.equalsIgnoreCase("No") &&  Snmpv3Privpassword.equalsIgnoreCase("No")) {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/c3radiobutton")));
    		Thread.sleep(3000);
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : selected field is : " + fieldname);
    		
    		//verify Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword fileds
    		String Snmpv3Usernamevalue2 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3username")).getAttribute("value");
    		String Snmpv3Authpasswordvalue2 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3authpassword")).getAttribute("value");
    		String Snmpv3Privpasswordvalue2 = getwebelement(xml.getlocator("//locators/" + application + "/snmpv3privpassword")).getAttribute("value");
    		
    		if(!(Snmpv3Usernamevalue2.isEmpty())&&!(Snmpv3Authpasswordvalue2.isEmpty()) && !(Snmpv3Privpasswordvalue2.isEmpty())) {
    			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword fields are not empty");
    			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : snmpro field value displayed is : " + Snmpv3Usernamevalue2);
    			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : snmprw field value displayed is : " + Snmpv3Authpasswordvalue2);
    			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : snmprw field value displayed is : " + Snmpv3Privpasswordvalue2);
    		}else {
    			
    			sa.fail("Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword fields are empty, it has to be prefilled");
    			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Snmpv3Username,Snmpv3Authpassword,Snmpv3Privpassword fields are empty, it has to be prefilled");
    		}
    			
    		
    	}else if(snmp3.equalsIgnoreCase("no")){
    		
    		ExtentTestManager.getTest().log(LogStatus.INFO, "Step :  " + fieldname + " field is not selected ");
    	}
         
       sa.assertAll();
    }
    
    
    public void verifyExistingcitysitepremiseselection(String application,String existingcity, String existingcityvalue, String existingsite, String existingsitevalue, String existingpremise, String existingpremisevalue) throws InterruptedException, IOException, DocumentException {
    	
    	if(existingcity.equalsIgnoreCase("yes")&& existingsite.equalsIgnoreCase("yes")&& existingpremise.equalsIgnoreCase("Yes")) {
    		
    		//select city
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/citydropdowninput")), existingcityvalue);
    		Thread.sleep(3000);
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected Existing City Name is : " + existingcityvalue );
    		
    		WebElement Cityele = getwebelement("//span[text()='"+existingcityvalue+"']");
    		Cityele.click();
    		Thread.sleep(3000);
    		
    		//Select site
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/sitedropdowninput")), existingsitevalue);
    		Thread.sleep(3000);
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected Existing Site Name is : " + existingsitevalue );
    		
    		WebElement siteele = getwebelement("//span[text()='"+existingsitevalue+"']");
    		siteele.click();
    		Thread.sleep(3000);
    		
    		//Select Premise
    		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premisedropdowninput")), existingpremisevalue);
    		Thread.sleep(3000);
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected Existing Premise Name is : " + existingpremisevalue );
    		
    		WebElement premiseele = getwebelement("//span[text()='"+existingpremisevalue+"']");
    		premiseele.click();
    		Thread.sleep(3000);
    	}else {
    		
    		ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Existing City, Site and Premise Name is not selected ! "  );
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
    		
    		ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Add City is not selected ! "  );
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
    		
    		ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Add Site is not selected ! "  );
    	}
    	
    	
    }
    
    
    public void verifydeviceEdit_AccessRouter(String application, String editDevicename, String DeviceType, String editVendorModel, String editRouterID,
    		String editModularMSP, String editFullIQNET, String editIOSXR, String editTelnet, String editSSH, String editSnmp2C, String editSnmp3,
    		String editSnmProNewValue, String editSnmprwNewValue, String editSnmpv3UsernameNewValue, String editSnmpv3AuthpasswordNewValue, String editSnmpv3PrivpasswordNewValue,
    		String editManagementAddress, String editCountry, String editExistingCity, String editExistingCityValue, String editExistingSite, String editExistingSiteValue,
    		String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, String editNewPremise, String editNewCityName,
    		String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, IOException, DocumentException {      
    	
    	
    		scrollToTop();
    		Thread.sleep(2000);
    		
    	//Action Button Click  			
    		WebElement ActionButton = getwebelement(xml.getlocator("//locators/" + application + "/Action"));
    		ActionButton.click();
    		Thread.sleep(2000);
    		
    	//Edit Button Click
    		WebElement EditButton = getwebelement(xml.getlocator("//locators/" + application + "/Edit"));
    		String EditButtonClick = EditButton.getText();
    		EditButton.click();
    		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on edit link");
    		Thread.sleep(2000);
    		
    	//Device Name
    		 edittextFields_commonMethod(application, "Name", "nametextfield", editDevicename, xml);
    		
    		
    	//Device Type
    		 Select sel = new Select(getwebelement(xml.getlocator("//locators/" + application + "/devicetypeinput")));
    		 WebElement devicetype = sel.getFirstSelectedOption();
    		 
     		Log.info("device type is displaying as: "+devicetype.getText());
     		assertEquals(devicetype.getText(), DeviceType);
     		if(devicetype.getText().equalsIgnoreCase("Access Router")) {
     			ExtentTestManager.getTest().log(LogStatus.PASS, "Device type is displaying as expected. It is displaying as: "+ devicetype.getText());
     			Log.info("Device type is displaying as expected. It is displaying as: "+ devicetype.getText());
     		}
     		else {
     			ExtentTestManager.getTest().log(LogStatus.FAIL, "Device type is not displaying as expected. It is displaying as: "+ devicetype.getText());
     			Log.info("Device type is not displaying as expected. It is displaying as: "+ devicetype.getText());
     		}
    		 
    		
    	//Vendor Model
    		addDropdownValues_commonMethod(application, "Vendor Model", "vendormodelinput", editVendorModel, xml);
    		
    	//Router Id
    		edittextFields_commonMethod(application, "Router Id", "routeridtextfield", editRouterID, xml);
    								
		//connection protocol selection
    	if((editTelnet.equalsIgnoreCase("null")) && (editSSH.equalsIgnoreCase("null"))) {
    		
    		ExtentTestManager.getTest().log(LogStatus.PASS, " No changes made for 'Connectivity Protocol' as expected");
    		Log.info(" No changes made for 'Connectivity Protocol' as expected");
    			
    	}
    	else if((!editTelnet.equalsIgnoreCase("null")) && (!editSSH.equalsIgnoreCase("null"))){
    		
    		if((editTelnet.equalsIgnoreCase("Yes")) && (editSSH.equalsIgnoreCase("No"))) {
				
				//Connectivity Protocol selection telnet
				editcheckbox_commonMethod(application, editTelnet, "telnetradiobutton", "Telnet", xml);
				
			}
			else if((editTelnet.equalsIgnoreCase("No")) && (editSSH.equalsIgnoreCase("Yes"))) {
				
				//connection protocol selection- ssh
				editcheckbox_commonMethod(application, editSSH, "sshradiobutton", "SSH", xml);
				
			}
    	}
    		
			
		//SNMP version	
    	if((editSnmp2C.equalsIgnoreCase("null"))  && (editSnmp3.equalsIgnoreCase("null"))) {
    		ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'SNMP Version' as expected");
    		Log.info("No changes made for 'SNMP Version' as expected");
    		
    	}
    	else if((!editSnmp2C.equalsIgnoreCase("null"))  || (!editSnmp3.equalsIgnoreCase("null"))) {
    		
    		if((editSnmp2C.equalsIgnoreCase("Yes"))  && (editSnmp3.equalsIgnoreCase("NO"))) {
				
				editcheckbox_commonMethod(application, editSnmp2C, "c2cradiobutton", "Snmp Version-2C", xml);

			//snmpro	
				edittextFields_SNMPversion(application, "Snmpro", "snmprotextfield", editSnmProNewValue);
				
		   //snmprw 
				edittextFields_SNMPversion(application, "Snmprw", "snmprwtextfield", editSnmprwNewValue);
			
			}
			else if((editSnmp2C.equalsIgnoreCase("no"))  && (editSnmp3.equalsIgnoreCase("yes"))) {

				editcheckbox_commonMethod(application, editSnmp3, "c3radiobutton", "Snmp Version-3", xml);
				
				//Snmp v3 Username	
				edittextFields_SNMPversion(application, "Snmp v3 Username", "snmpv3username", editSnmpv3UsernameNewValue);

				//Snmp v3 Auth password
				edittextFields_SNMPversion(application, "Snmp v3 Auth password", "snmpv3authpassword", editSnmpv3AuthpasswordNewValue);

				//Snmp v3 Priv password
				edittextFields_SNMPversion(application, "Snmp v3 Priv password", "snmpv3privpassword", editSnmpv3PrivpasswordNewValue);
			}	
    	}
					
			
		//Modular msp
    		editcheckbox_commonMethod(application, editModularMSP, "modularmspcheckbox" , "Modular MSP", xml);
			
		//full IQNET
    		editcheckbox_commonMethod(application, editFullIQNET, "fulliqnetcheckbox", "Full IQNET", xml);
    		alertMessage_editIQNet(application);
			
			
		//IOS-XR
    		editcheckbox_commonMethod(application, editIOSXR , "iosxrcheckbox", "IOS-XR", xml);
			
			
			scrolltoend();
    		Thread.sleep(2000);
			
		//management Address 
			edittextFields_commonMethod(application, "Management Address", "managementaddresstextbox", editManagementAddress, xml);
			
		//Country
		if(!editCountry.equalsIgnoreCase("Null")) {
			
			selectValueInsideDropdown(application, "countryDropdown_selectTag", "Country", editCountry, xml);
			
			//New City		
			if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("yes")) {
				Clickon_addToggleButton(application, "addcityswitch");
			   //City name
				addtextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
			   //City Code	
				addtextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
			   //Site name
				addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
			   //Site Code
				addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", editNewSiteCode, xml);
			   //Premise name	
				addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
			   //Premise Code	
				addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);
					
			}	
		
		//Existing City	
			else if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("no")) {
				
			   selectValueInsideDropdown(application, "cityDropdown_selectTag", "City", editExistingCityValue, xml);
				
			 //Existing Site
				  if(editExistingSite.equalsIgnoreCase("yes") & editNewSite.equalsIgnoreCase("no")) {
					  selectValueInsideDropdown(application, "siteDropdown_selectTag", "Site", editExistingSiteValue, xml);
					  
				 //Existing Premise
					  if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("no")) {
						  selectValueInsideDropdown(application, "premiseDropdown_selectTag", "Premise", editExistingPremiseValue, xml);
			          	 }
					  
					//New Premise  
					  else if(editExistingPremise.equalsIgnoreCase("no") & editNewPremise.equalsIgnoreCase("yes")) {
						  
						  Clickon_addToggleButton(application, "addpremiseswitch");
						  //Premise name	
							addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", editNewPremiseName, xml);
						   //Premise Code	
							addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", editNewPremiseCode, xml);
					  } 
		         	}
	  		
			  else if(editExistingSite.equalsIgnoreCase("no") & editNewSite.equalsIgnoreCase("yes")) {
				  	
				  Clickon_addToggleButton(application, "addsiteswitch");
				  //Site name
					addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", editNewSiteName, xml);
				   //Site Code
					addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", editNewSiteCode, xml);
				   //Premise name	
					addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", editNewPremiseName, xml);
				   //Premise Code	
					addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", editNewPremiseCode, xml);
				  }
			}
			
		}
		else if(editCountry.equalsIgnoreCase("Null")) {
			
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

		scrolltoend();
		click_commonMethod(application, "OK", "okbutton", xml);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device is edited successfully");	
    		
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
			Log.info("No changes made under 'Premise' field");
			
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
			Log.info("No changes made under 'Site' field");
			
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
    	
    	boolean siteDisplayed=false;
    try {	
    	siteDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
    	
    	if(siteDisplayed) {
    		
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
			Log.info("No chnges made under 'City' field");
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
    	
    public void verifydeviceDelete_AccessRouter(String application) throws InterruptedException, DocumentException, IOException{
    	
    	waitForpageload();
		waitforPagetobeenable();
    	
    	
    	//Action Button Click  			
		WebElement ActionButton = getwebelement(xml.getlocator("//locators/" + application + "/Action"));
		String ActionButtonClick = ActionButton.getText();
		ActionButton.click();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Action button is clicked : " + ActionButtonClick);
		Thread.sleep(2000);
		
		//Delete Button Click
		WebElement DeleteButton = getwebelement(xml.getlocator("//locators/" + application + "/Delete"));
		String DeleteButtonClick = DeleteButton.getText();
		DeleteButton.click();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Delete button is clicked : " + DeleteButtonClick);
		Thread.sleep(2000);
		
	//Handling Delete Device Alert
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
        if(DeleteAlertPopup.isDisplayed())
        {
      	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
      	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
      	 
           click_commonMethod(application, "Delete", "deletebutton", xml);
           
           scrollToTop();
           Thread.sleep(1000);
           
           verifysuccessmessage(application, "Device successfully deleted.");
        }
        else
        {
              Log.info("Delete alert popup is not displayed");
              ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
        }    
    }
    
    
    public void edittextFields_SNMPversion(String application, String labelname, String xpathname, String expectedValueToEdit) throws InterruptedException, DocumentException, IOException {  
		boolean availability=false;
	try {	
		availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).isDisplayed();
		if(availability) {
			ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is displaying as expected");
			Log.info(labelname + " text field is displaying as expected");
			
			String actualvalueInsidetextfield=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
			
			if(actualvalueInsidetextfield.isEmpty()) {
				
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname+" text field");
//				Log.info("No values displaying under "+labelname+" text field");
				
				if(expectedValueToEdit.equalsIgnoreCase("null")) {
					//ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is not edited as expected");
					Log.info(labelname + " text field is not edited as expected");
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
				Log.info("Value displaying under "+labelname + " field is: "+actualvalueInsidetextfield);
				
				if(expectedValueToEdit.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is not edited as expected");
					Log.info(labelname + " text field is not edited as expected");
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
			Log.info(labelname + " text field is not displaying");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " text field is not displaying");
		Log.info(labelname + " text field is not displaying");
	}catch(Exception ee) {
		ee.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under "+ labelname + " text field");
		Log.info(" Not able to enter value under "+ labelname + " text field");
	}
}

    
public void Clickon_addToggleButton(String application, String xpath) throws InterruptedException, DocumentException {
		
		//Add Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
		Thread.sleep(5000);
	}


	public void fetchDeviceInterface_viewdevicepage(String application) throws InterruptedException, DocumentException {
		
		scrollToTop();
		 Thread.sleep(3000);
		//Action Button Click  
		 	click_commonMethod(application, "Action", "Action", xml);
			Thread.sleep(1000);
			
		//click on 'fetch device interface	
			click_commonMethod(application, "fetch device interfaces", "fetchDeviceinterfacelink_viewDevicePage", xml);
			Thread.sleep(2000);
			
			
		//verify success Message
			String expectedValue="Fetch interfaces started successfully. Please check the sync status of this device";
			boolean successMessage=false;
			successMessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_Alert")).isDisplayed();
			String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_Alert")).getText();
			if(successMessage) {
				
				if(actualMessage.isEmpty()) {
					Log.info("No messages displays");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Success message is not displaying");
				}
				if(actualMessage.contains(expectedValue)) {
					
				ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaying");
				Log.info(" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
				
				ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
				Log.info(" Success Message displays as: "+actualMessage);
				
				//click on the 'click here' link
				click_commonMethod(application, "click here Link", "ClickhereLink_fetchInterface", xml);
				Thread.sleep(3000);
				
				}
				else if (actualMessage.equalsIgnoreCase(expectedValue)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaying as expected");
					Log.info(" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
					Log.info(" Success Message displays as: "+actualMessage);
					
					//click on the 'click here' link
					click_commonMethod(application, "click here Link", "ClickhereLink_fetchInterface", xml);
					Thread.sleep(3000);
				}
				else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Fetch Device Interface' link, message displays as "+actualMessage);
					Log.info("After clicking on 'Fetch Device Interface' link, message displays as "+actualMessage);
					
				//Action Button Click 
					click_commonMethod(application, "Action", "Action", xml);
					Thread.sleep(1000);
					
				//click on 'Manage' link
					click_commonMethod(application, "Manage", "manageLink_viewDevicepage", xml);
					Thread.sleep(2000);
				}
					
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is not displaying");
				Log.info(" After clicking on 'Fetch Device Interface' link, success Message is not displaying");
			}
	
	}



	public void verifEditedValue_AccessRouter(String application, String editDevicename, String DeviceType, String editVendorModel, String editRouterID,
			String editModularMSP, String editFullIQNET, String editIOSXR, String editTelnet, String editSSH, String editSnmp2C, String editSnmp3,
			String editSnmProNewValue, String editSnmprwNewValue, String editSnmpv3UsernameNewValue, String editSnmpv3AuthpasswordNewValue, String editSnmpv3PrivpasswordNewValue,
			String editManagementAddress, String editCountry, String editExistingCity, String editExistingCityValue, String editExistingSite, String editExistingSiteValue,
			String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, String editNewPremise, String editNewCityName,
			String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, IOException, DocumentException {   
	
		
		//Device name
		   if(editDevicename.equalsIgnoreCase("Null")) {
			   compareText_InViewPage_ForNonEditedFields(application, "Name", xml);
		   }else {
			   compareText_InViewPage(application, "Name", editDevicename, xml);
		   }
		
		
	//Device Type
		compareText_InViewPage(application, "Device Type", DeviceType, xml);
		
		
	//Vendor/Model
		if(editVendorModel.equalsIgnoreCase("Null")) {
			   compareText_InViewPage_ForNonEditedFields(application, "Vendor/Model", xml);
		   }else {
			   compareText_InViewPage(application, "Vendor/Model", editVendorModel, xml);
		   }
		
	//Router Id
		if(editRouterID.equalsIgnoreCase("Null")) {
			   compareText_InViewPage_ForNonEditedFields(application, "Router Id", xml);
		   }else {
			   compareText_InViewPage(application, "Router Id" , editRouterID, xml);
		   }
		
	//Connectivity protocol
		if((editSSH.equalsIgnoreCase("null") && editTelnet.equalsIgnoreCase("null")) || (editSSH.equalsIgnoreCase("no") && editTelnet.equalsIgnoreCase("no"))) {
			
			compareText_InViewPage_ForNonEditedFields(application, "Connectivity Protocol", xml);
		}
		else if(editSSH.equalsIgnoreCase("yes") && editTelnet.equalsIgnoreCase("no")) {
			
			compareText_InViewPage(application, "Connectivity Protocol", "ssh", xml);
		}
		else if(editSSH.equalsIgnoreCase("no") && editTelnet.equalsIgnoreCase("yes")) {
			
			compareText_InViewPage(application, "Connectivity Protocol", "telnet", xml);
		}
		
		
	//Management Address
		if(editManagementAddress.equalsIgnoreCase("Null")) {
			   compareText_InViewPage_ForNonEditedFields(application, "Management Address", xml);
		   }else {
			   compareText_InViewPage(application, "Management Address" , editManagementAddress, xml);
		   }
		
		
	//Full IQNET
//		if(editFullIQNET.equalsIgnoreCase("Null")) {
////			   compareText_InViewPage_ForNonEditedFields(application, "Full IQNET", xml);
//			   Log.info("No changes");
//		   }
//		else if(editFullIQNET.equalsIgnoreCase("yes")) {
//			
//			compareText_InViewPage(application, "Full IQNET" , "Null", xml);
//				   
//			}
//		else if(editFullIQNET.equalsIgnoreCase("no")) {
//			
//			compareText_InViewPage(application, "Full IQNET" , "Null", xml);
//				   
//		   }
			   
			   
		
	//SNMP Version
		String snmpValue=null;
		if(editSnmp2C.equalsIgnoreCase("yes")) {
			snmpValue="2c";
		}else if(editSnmp3.equalsIgnoreCase("yes")){
			snmpValue="3";
		}
		
		if(editSnmp2C.equalsIgnoreCase("Yes") && editSnmp3.equalsIgnoreCase("No")) {
			
			compareText_InViewPage(application, "SNMP Version", snmpValue, xml);
			
			compareText_InViewPage(application, "Snmpro", editSnmProNewValue , xml);
			compareText_InViewPage(application, "Snmprw", editSnmprwNewValue, xml);
			
		}
		else if(editSnmp2C.equalsIgnoreCase("Yes") && editSnmp3.equalsIgnoreCase("null")) {
			
			compareText_InViewPage(application, "SNMP Version", snmpValue, xml);
			
			compareText_InViewPage(application, "Snmpro", editSnmProNewValue , xml);
			compareText_InViewPage(application, "Snmprw", editSnmprwNewValue, xml);
		}
		else if(editSnmp2C.equalsIgnoreCase("no") && editSnmp3.equalsIgnoreCase("yes")) {
			
			compareText_InViewPage(application, "SNMP Version", snmpValue, xml);
			
			compareText_InViewPage(application, "Snmp v3 Username", editSnmpv3UsernameNewValue, xml);
			compareText_InViewPage(application, "Snmp v3 Auth password", editSnmpv3AuthpasswordNewValue, xml);
			compareText_InViewPage(application, "Snmp v3 Priv password", editSnmpv3PrivpasswordNewValue, xml);
		}
		else if(editSnmp2C.equalsIgnoreCase("null") && editSnmp3.equalsIgnoreCase("yes")) {
			
			compareText_InViewPage(application, "SNMP Version", snmpValue, xml);
			
			compareText_InViewPage(application, "Snmp v3 Username", editSnmpv3UsernameNewValue, xml);
			compareText_InViewPage(application, "Snmp v3 Auth password", editSnmpv3AuthpasswordNewValue, xml);
			compareText_InViewPage(application, "Snmp v3 Priv password", editSnmpv3PrivpasswordNewValue, xml);
			
		}
		else if((editSnmp2C.equalsIgnoreCase("null") && editSnmp3.equalsIgnoreCase("null")) || (editSnmp2C.equalsIgnoreCase("no") && editSnmp3.equalsIgnoreCase("no"))) {
			
			String snmpvalue=compareText_InViewPage_ForNonEditedFields(application, "SNMP Version", xml);
			
			if(snmpvalue.equalsIgnoreCase("2c")) {
				compareText_InViewPage(application, "Snmpro", "incc", xml);
				compareText_InViewPage(application, "Snmprw", "ip4corp3", xml);
			}
			else if(snmpvalue.equalsIgnoreCase("3")) {
				compareText_InViewPage(application, "Snmp v3 Username", "colt-nms", xml);
				compareText_InViewPage(application, "Snmp v3 Auth password", "OrHzjWmRvr4piJZb", xml);
				compareText_InViewPage(application, "Snmp v3 Priv password", "3k0hw8thNxHucQkE", xml);
			} 
		}
		
	//Country
		if(editCountry.equalsIgnoreCase("Null")) {
			   compareText_InViewPage_ForNonEditedFields(application, "Country", xml);
		   }else {
			   compareText_InViewPage(application, "Country" , editCountry, xml);
		   }
		
	//City
		if((editExistingCity.equalsIgnoreCase("Null") && editNewCity.equalsIgnoreCase("Null")) || (editExistingCity.equalsIgnoreCase("no") && editNewCity.equalsIgnoreCase("no"))) {
			
			compareText_InViewPage_ForNonEditedFields(application, "City" , xml);
		}
		else if(editExistingCity.equalsIgnoreCase("yes") && editNewCity.equalsIgnoreCase("no")) {
			
			compareText_InViewPage(application, "City" , editExistingCityValue, xml);
			
		}
		else if(editExistingCity.equalsIgnoreCase("no") && editNewCity.equalsIgnoreCase("yes")) {
			
			compareText_InViewPage(application, "City", editNewCityName, xml);
		}
		
	//Site
		if((editExistingSite.equalsIgnoreCase("Null") && editNewSite.equalsIgnoreCase("Null")) || (editExistingSite.equalsIgnoreCase("no") && editNewSite.equalsIgnoreCase("no"))) {
			
			compareText_InViewPage_ForNonEditedFields(application, "Site" , xml);
		}
		else if(editExistingSite.equalsIgnoreCase("yes") && editNewSite.equalsIgnoreCase("no")) {
			
			compareText_InViewPage(application, "Site" , editExistingSiteValue, xml);
			
		}
		else if(editExistingSite.equalsIgnoreCase("no") && editNewSite.equalsIgnoreCase("yes")) {
			
			compareText_InViewPage(application, "Site", editNewSiteName, xml);
		}
		
		
	//Premise
		if((editExistingPremise.equalsIgnoreCase("Null") && editNewPremise.equalsIgnoreCase("Null")) || (editExistingPremise.equalsIgnoreCase("no") && editNewPremise.equalsIgnoreCase("no"))) {
			
			compareText_InViewPage_ForNonEditedFields(application, "Premise" , xml);
		}
		else if(editExistingPremise.equalsIgnoreCase("yes") && editNewPremise.equalsIgnoreCase("no")) {
			
			compareText_InViewPage(application, "Premise" , editExistingPremiseValue, xml);
			
		}
		else if(editExistingPremise.equalsIgnoreCase("no") && editNewPremise.equalsIgnoreCase("yes")) {
			
			compareText_InViewPage(application, "Premise", editExistingPremiseValue, xml);
		}
		
		
	}
	
	
	/**
	 * Used for fetching value in View Page
	 * @param application
	 * @param labelname
	 * @param xml
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public String compareText_InViewPage_ForNonEditedFields(String application, String labelname, XMLReader xml) throws InterruptedException, DocumentException {

		String emptyele = null;
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element = getwebelement("//div[div[label[contains(text(),'"+ labelname + "')]]]/div[2]");
			emptyele = element.getText().toString();

					ExtentTestManager.getTest().log(LogStatus.PASS,labelname +" field is not edited. It is displaying as '"+emptyele+"'");
					Log.info(labelname +" field is not edited. It is displaying as '"+emptyele+"'");
					
					
		}catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			Log.info(labelname + " field is not displaying");
		}
		return emptyele;

	}

	
	/**
	 * IQNet Device Alert  Message
	 * @throws DocumentException 
	 * @throws InterruptedException 
	 */
	public void alertMessage_IQNet(String application) throws InterruptedException, DocumentException {

		
		boolean alertHeader=false;
	try {	
		alertHeader=getwebelement(xml.getlocator("//locators/" + application + "/IQNet_alertHeader")).isDisplayed();
		if(alertHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Alert popup Message displays");
			Log.info("Alert popup Message displays");
			
			String alertMsg=getwebelement(xml.getlocator("//locators/" + application + "/IQNet_alertBody")).getText();
			
			if(alertMsg.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No alert message displays inside the popup, after clicking on 'IQNet' checkbox. It should display as"
						+ " 'Please make sure this is IQNET device'");
				
				Log.info("No alert message displays, after clicking on 'IQNet' checkbox. It should display as"
						+ " 'Please make sure this is IQNET device'");
				
				clickOnBankPage();
				Thread.sleep(1000);
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "When we click on 'IQNet' checkbox, an alert message displays as "+alertMsg);
				Log.info("When we click on 'IQNet' checkbox, an alert message displays as "+alertMsg);
				
				click_commonMethod(application, "x button", "IONet_xButton", xml);
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No alert message displays, after clicking on 'IQNet' checkbox. It should display as"
					+ " 'Please make sure this is IQNET device'");
			
			Log.info("No alert message displays, after clicking on 'IQNet' checkbox. It should display as"
					+ " 'Please make sure this is IQNET device'");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No alert message displays, after clicking on 'IQNet' checkbox. It should display as"
				+ " 'Please make sure this is IQNET device'");
		
		Log.info("No alert message displays, after clicking on 'IQNet' checkbox. It should display as"
				+ " 'Please make sure this is IQNET device'");
	}
	}	

	
	/**
	 * IQNet Device Alert  Message
	 * @throws DocumentException 
	 * @throws InterruptedException 
	 */
	public void alertMessage_editIQNet(String application) throws InterruptedException, DocumentException {

		
		boolean alertHeader=false;
	try {	
		alertHeader=getwebelement(xml.getlocator("//locators/" + application + "/IQNet_alertHeader")).isDisplayed();
		if(alertHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Alert popup Message displays");
			Log.info("Alert popup Message displays");
			
			String alertMsg=getwebelement(xml.getlocator("//locators/" + application + "/IQNet_alertBody")).getText();
			
			if(alertMsg.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No alert message displays inside the popup, after clicking on 'IQNet' checkbox. It should display as"
						+ " 'Please make sure this is IQNET device'");
				
				Log.info("No alert message displays, after clicking on 'IQNet' checkbox. It should display as"
						+ " 'Please make sure this is IQNET device'");
				
				clickOnBankPage();
				Thread.sleep(1000);
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "When we click on 'IQNet' checkbox, an alert message displays as "+alertMsg);
				Log.info("When we click on 'IQNet' checkbox, an alert message displays as "+alertMsg);
				
				click_commonMethod(application, "x button", "IONet_xButton", xml);
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No alert message displays, after clicking on 'IQNet' checkbox. It should display as"
					+ " 'Please make sure this is IQNET device'");
			
			Log.info("No alert message displays, after clicking on 'IQNet' checkbox. It should display as"
					+ " 'Please make sure this is IQNET device'");
		}
	}catch(Exception e) {
		e.printStackTrace();
	}

	
	
	}


	public void managelink(String application) throws InterruptedException, DocumentException {
		
		scrollToTop();
		 Thread.sleep(3000);
		//Action Button Click  			
			WebElement ActionButton = getwebelement(xml.getlocator("//locators/" + application + "/Action"));
			ActionButton.click();
			Thread.sleep(3000);
			
		//click on 'Manage' link
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/manageLink_viewDevicepage")));
			Thread.sleep(3000);
	}
	
	
	/**
	 * Router Tool Panel
	 * @throws InterruptedException 
	 * @throws DocumentException 
	 */
	public void routerPanel(String application, String commandIPv4, String commandIPv6, String ipAddress, 
			String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException {
		
		scrolltoend();
		Thread.sleep(1000);
		
	//Command IPV4	
		addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);
		
		hostnametextField_IPV4(application, commandIPv4, ipAddress);
		
		vrfNametextField_IPV4(application, commandIPv4, vrfname_ipv4);
		
		executeCommandAndFetchTheValue(application, "executebutton_Ipv4");
		
		
	//Commmand IPV6
		addDropdownValues_commonMethod(application, "Command IPV6", "commandIPV6_dropdown" , commandIPv6, xml);
		
		hostnametextField_IPV6(application, commandIPv6, ipAddress);
		
		vrfNametextField_IPV6(application, commandIPv6, vrfname_ipv6);
		
		executeCommandAndFetchTheValue(application, "executebutton_IPv6");
		
	}

public void executeCommandAndFetchTheValue(String application, String executeButton) throws InterruptedException, DocumentException {
		
		click_commonMethod(application, "Execute", executeButton, xml);
		
	boolean resultField=false;	
try {	
	resultField=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).isDisplayed();
	if(resultField) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Result' text field is displaying");
		Log.info( "'Result' text field is displaying");
		
		String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "value under 'Result' field displaying as "+ remarkvalue);
		Log.info("value under 'Result' field displaying as "+ remarkvalue);
	
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
		Log.info( "'Result' text field is not displaying");
	}
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
	Log.info("'Result' text field is not displaying");
}
		
	}
	
	
	public void hostnametextField_IPV6(String application, String commandIPv6, String ipv6Address) {
		boolean IPV4availability=false;
		try {  
			IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv6_hostnameTextfield")).isDisplayed();
		  
		  if(IPV4availability) {
			  
			  addtextFields_commonMethod(application, "IP Address or Hostname", "commandIPv6_hostnameTextfield", ipv6Address, xml);
			  
		  }else {
			  	ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
				Log.info("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
		  }
		}catch(Exception e) {
			e.printStackTrace();
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
			Log.info("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
		}
	}
	
	
	public void vrfNametextField_IPV6(String application, String commandIPV6, String vrfname_IPV6) {
		boolean IPV6availability=false;
		
		if(commandIPV6.equalsIgnoreCase("vrf")) {
			try {  
				IPV6availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv6_vrfnameTextField")).isDisplayed();
				
				if(IPV6availability) {
					addtextFields_commonMethod(application, "Router Vrf Name", "commandIPv6_vrfnameTextField", vrfname_IPV6, xml);
				}else {
					ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
					Log.info("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				Log.info("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			}
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv6' text field is not displaying for "+ commandIPV6);
			Log.info("'VRF Name IPv6' text field is not displaying for "+ commandIPV6 +" command");
		}
		
	}	
	
	
	public void hostnametextField_IPV4(String application, String command_ipv4, String ipAddress) {
		boolean IPV4availability=false;
		try {  
			IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv4_hostnameTextfield")).isDisplayed();
		  
		  if(IPV4availability) {
			  
			  addtextFields_commonMethod(application, "IP Address or Hostname", "commandIPv4_hostnameTextfield", ipAddress, xml);
			  
		  }else {
			  	ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
				Log.info("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
		  }
		}catch(Exception e) {
			e.printStackTrace();
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
			Log.info("'Hostname or IpAddress' for 'Ipv4' text field is not displaying for "+ command_ipv4);
		}
	}
	
	
	public void vrfNametextField_IPV4(String application, String command_ipv4, String vrfname_ipv4) {
		boolean IPV4availability=false;
		  
			
		if(command_ipv4.contains("vrf")) {
			try {
				IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv4_vrfnameTextField")).isDisplayed();
				
				if(IPV4availability) {
					addtextFields_commonMethod(application, "Router Vrf Name", "commandIPv4_vrfnameTextField", vrfname_ipv4, xml);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
					Log.info("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				Log.info("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv4' text field is not displaying for "+ command_ipv4);
			Log.info("'VRF Name IPv4' text field is not displaying for "+ command_ipv4 +" command");
		}
	}	

	
	public void testStatus(String application) throws InterruptedException, DocumentException {
		
		String element=null;
		String status=null;
		
		List<WebElement> testlist=getwebelements("//tbody/tr");
		int listSize=testlist.size();
		
		if(listSize>=1) {
			
			for(int i=1; i<=listSize; i++) {
					element=getwebelement("(//tbody/tr["+ i +"]/td)[1]").getText();
					
					if(element.isEmpty()) {
						
						Log.info("No value displays inside the table");
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Test Name is displaying as: "+element);
						Log.info("Test Name is displaying as: "+element);
						
						status=getwebelement("(//tbody/tr["+ i +"]/td)[2]/div").getAttribute("class");
						Log.info("status displays as: "+status);
						
						if(status.contains("red")) {
							ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: red");
							Log.info(element + " status colour dipslays as: red");
						}
						else if(status.contains("green")) {
							ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: green");
							Log.info(element + " status colour dipslays as: green");
						}
					}
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Command/Status table is not displaying in view device page. Instead Message displays as: ");
			ExtentTestManager.getTest().log(LogStatus.INFO, "* Device reachability tests require the device to have an IP address.");
			
			Log.info("Command/Status table is not displaying in view device page. Instead Message displays as: ");
			Log.info("* Device reachability tests require the device to have an IP address.");
		}
		
	}
	
	
	public void verifysuccessmessage(String application, String expected) throws InterruptedException {
		
		waitForpageload();
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(3000);
		try {	
			
			boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/succesMsg_alertDisplay")).isDisplayed();

			if(successMsg) {
				
				String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_Alert")).getText();
				
				if(expected.contains(alrtmsg)) {
					
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
			Log.info("failure in fetching success message - 'Service created Successfully'  ");
			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
			Log.info(expected+ " message is not getting dislpayed");
			failureScreenshot(application);
		}

	}




}

