package com.colt.qa.scripthelpers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class searchForDeviceHelper extends DriverHelper {

	public searchForDeviceHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	
	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/searchForDevice.xml");
	
	
	public void clickOnSearchForDevice(String application) throws InterruptedException, DocumentException {
		
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/mcnlink")));
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on manage colt's Network link");
		
		click_commonMethod(application, "search For Device", "searchForDeviceLink", xml);
		Thread.sleep(1000);
		waitForpageload();   waitforPagetobeenable();
	}
	
	
	public void searchDevice(String application, String deviceName, String managementAddress, String vendorModelList, String deviceTypesList,
			String searchCity, String cityList, String CO, String COlist, String routerId) throws InterruptedException, DocumentException, IOException {
		
		
		String[] vendorModelToBeSelected=vendorModelList.split(",");
		String[] deviceTypeToBeSelected=deviceTypesList.split(",");
		String[] citiesToBeSelected=cityList.split(",");
		String[] COtobeSelected=COlist.split(",");
		
		scrollToTop();
		addtextFields_commonMethod(application, "Device Name", "deviceName_textField", deviceName, xml);  	//Device Name
		
		addtextFields_commonMethod(application, "Management Address", "managementAddress", managementAddress, xml);  	//Management Address
		
		selectAndAddValueFromLeftDropdown(application, "Vendor/Model", "vendorModelDropdown" , vendorModelToBeSelected, "addButton_vendorModel");
		verifySelectedValuesInsideRightDropdown(application, "Vendor/Model", "selectedVendorModel");
		
		WebElement deviceNameField = getwebelement(xml.getlocator("//locators/" + application + "/deviceName_textField"));
		scrolltoview(deviceNameField);
		
		selectAndAddValueFromLeftDropdown(application, "Device Type", "deviceTypeDropdown" , deviceTypeToBeSelected, "addButton_deviceType");
		verifySelectedValuesInsideRightDropdown(application, "Device Type", "selectedDeviceType");
		
	//Search City	
		if(searchCity.equalsIgnoreCase("Null")) {
			addtextFields_commonMethod(application, "Search City", "searchCity", searchCity, xml);
		}else {
			addtextFields_commonMethod(application, "Search City", "searchCity", searchCity, xml);
			
			selectAndAddValueFromLeftDropdown(application, "City", "cityDropdown" , citiesToBeSelected, "addButton_city");
			verifySelectedValuesInsideRightDropdown(application, "City", "selectedCity");
		}
		
		
		WebElement cityField = getwebelement(xml.getlocator("//locators/" + application + "/searchCity"));
		scrolltoview(cityField);
		
		
		//CO
		if(CO.equalsIgnoreCase("Null")) {
			addtextFields_commonMethod(application, "Search CO", "searchCO", CO, xml);
		}else {
			addtextFields_commonMethod(application, "Search CO", "searchCO", CO, xml);
			
			selectAndAddValueFromLeftDropdown(application, "CO", "avaialableCOvalue" , COtobeSelected, "addButton_CO");
			verifySelectedValuesInsideRightDropdown(application, "CO", "selectedCOtypes");
		}
		
		scrolltoend();
		addtextFields_commonMethod(application, "Router Id", "routerId_tetField", routerId, xml);
		
		scrolltoend();
		click_commonMethod(application, "Search", "searchButton", xml);
		
	}
	
	
	public void selectValueUnderRecord(String application) throws InterruptedException, DocumentException {
		
		WebElement routerId = getwebelement(xml.getlocator("//locators/" + application + "/routerId_tetField"));
		scrolltoview(routerId);
		
		click_commonMethod(application, "selectValueUnderSearchResultCheckbox", "clickOnSearcheddevice", xml);
		
		click_commonMethod(application, "Action", "actiondropdown", xml);  	//Action
		click_commonMethod(application, "View", "viewLink", xml);  	//View
	}
	
	
	public void selectAndAddValueFromLeftDropdown(String application, String labelname, String xpath, String[] selectValue, String xpathForAddButton) {
		
		WebElement availability=null;
		List<String> ls = new ArrayList<String>();
		 
        try{
        	
              List<WebElement> elements= getwebelements(xml.getlocator("//locators/" + application + "/"+ xpath +""));
              int element_count= elements.size();
              
          if(element_count>=1) {
        	  
           //Print list of values inside Dropdown 
              for(WebElement a : elements) {
			            ls.add(a.getText());
			    }
		
			    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
	            Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);
	            
	      //select value inside the dropdown     
              for(int i=0; i<selectValue.length; i++)
              {
            	 Thread.sleep(5000);
                 for(int j=0; j<ls.size() ; j++) {
            	  Log.info("ls value "+ ls.get(j));
                    if(selectValue[i].equals(ls.get(j)))
                    {
                    	  elements.get(j).click();
                    	  ExtentTestManager.getTest().log(LogStatus.PASS, elements.get(j).getText() + " got selected" );
                          Thread.sleep(1000);
                          click_commonMethod(application, "Add", xpathForAddButton , xml);
                          Thread.sleep(5000);
                    }
                 }
              }
              
          }else {
        	  ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");
        	  
        	  Log.info("No values displaying under " + labelname + " available dropdown");
          }
        }catch(Exception e) {
              e.printStackTrace();
              ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
              Log.info( "No values displaying under "+labelname + " available dropdown");
        }
	}
	
	
	public void verifySelectedValuesInsideRightDropdown(String application, String labelname, String xpath) {

		//getAllValuesInsideDropDown
			 boolean availability=false;
			 List<String> ls = new ArrayList<String>();
			 
			 try{
                 List<WebElement> elements= getwebelements(xml.getlocator("//locators/" + application + "/"+ xpath +""));
                 int element_count= elements.size();
                 
             if(element_count>=1) {
           	  
              //Print list of values inside Dropdown 
                 for(WebElement a : elements) {
				            ls.add(a.getText());
				    }
			
				    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
		            Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);
             }else {
           	  ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");
           	  
           	  Log.info("No values displaying under " + labelname + " available dropdown");
             }
           }catch(Exception e) {
                 e.printStackTrace();
                 ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
                 Log.info( "No values displaying under "+labelname + " available dropdown");
           }
	}
	
	
	public void fetchValueInViewDevicePage(String application) throws InterruptedException, DocumentException {
		
		
		waitForpageload();  waitforPagetobeenable();
		
		GetValues(application, "Name");		//Device Name
		GetValues(application, "Device Type");	//Device Type
		GetValues(application, "Vendor/Model");		//Vendor/Model
//		GetValues(application, "Snmpro");	//Snmpro
		GetValues(application, "Router Id");	//Router Id
		GetValues(application, "Full IQNET");	//Full IQNET
		GetValues(application, "Management Address");	//Management Address
		GetValues(application, "Country");	//Country
		GetValues(application, "City");		//City
		GetValues(application, "Site");	//Site
	}
	
	
	@SuppressWarnings("unused")
	public String GetValues(String application, String labelname) throws InterruptedException, DocumentException {

		String text = null;
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element = getwebelement(xml.getlocator("//locators/" + application + "/fetchValueFromViewPage").replace("value", labelname));
			String ele = element.getText();
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname +"' is not found");
			}
			else if (ele!=null && ele.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname +"' value is empty");
			}
			else {   

				text = element.getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname +"' value is displayed as : '"+text+"'");

			}
		}catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname +"' value is not displaying");
			e.printStackTrace();
		}
		return text;

	}


	
}
