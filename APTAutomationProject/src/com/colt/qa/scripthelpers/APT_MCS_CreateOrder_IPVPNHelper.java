package com.colt.qa.scripthelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.interactions.DoubleClickAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.assertEquals;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;


public class APT_MCS_CreateOrder_IPVPNHelper extends DriverHelper {

	public APT_MCS_CreateOrder_IPVPNHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_MCS_CreateOrder_IPVPN_ServiceType.xml");

	
	public String primarytrunkGroupname=null;
	
	
	
	
	//======================================  Common Methods  ===========================================	
	
		//======================================  Common Methods  ===========================================

		//======================================  Common Methods  ===========================================
//check
		
		public void webelementpresencelogger(WebElement ele, String msg) {

			boolean flag = ele.isDisplayed();
			System.out.println("element presence state : " + flag);
			if (flag) {

				System.out.println("webElement is present " + ele.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, msg);
			} else {

				System.out.println("webElement is not  present" + ele.getText());
				ExtentTestManager.getTest().log(LogStatus.FAIL, msg);
			}

		}
		
		public void waitforPageenable() throws InterruptedException
		{
			WebElement el = driver.findElement(By.xpath("//body"));
			
			System.out.println("Start");
			while (el.getAttribute("class").contains("loading-indicator"))
			{
				System.out.println("Page Loading");
				Thread.sleep(5000);
			}
		}			
		
		
		
		
		
		public static Boolean isFileDownloaded(String fileName, String downloadspath) {
			boolean flag = false;
			//paste your directory path below
			//eg: C:\\Users\\username\\Downloads
			String dirPath = downloadspath; 
			File dir = new File(dirPath);
			File[] files = dir.listFiles();
			if (files.length == 0 || files == null) {
				System.out.println("The directory is empty");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Downloads folder is empty");
				flag = false;
			} else {
				for (File listFile : files) {
					if (listFile.getName().contains(fileName)) {
						System.out.println(fileName + " is present");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+fileName+"' excel file is downloaded successfully");
						break;
					}
					flag = true;
				}
			}
			return flag;
		}
			
		
		
		
		
		
		public void searchorder_OLD(String application, String sid) throws InterruptedException, DocumentException, IOException {


			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
			Thread.sleep(2000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
			Thread.sleep(1000);

			WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
			scrolltoview(searchbutton);
			Clickon(searchbutton);
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
			Thread.sleep(1000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewLink_SearchOrderPage")));
			Thread.sleep(3000);
		}
		
		
		public void searchorder(String application, String sid) throws InterruptedException, DocumentException, IOException {

            waitforPageenable();
			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
			Thread.sleep(2000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
			Thread.sleep(1000);

			WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
//			scrolltoview(searchbutton);
			scrolltoend();
			Thread.sleep(2000);
			Clickon(searchbutton);
			Thread.sleep(2000);
			waitforPageenable();
			Thread.sleep(2000);
			scrolltoend();
			Thread.sleep(2000);
			scrolltoend();
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
			Thread.sleep(1000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewLink_SearchOrderPage")));
			Thread.sleep(3000);
			Boolean DetailPage = getwebelement(xml.getlocator("//locators/" + application + "/DetailPageHeader")).isDisplayed();
			
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigate to Customer detail in view service page is " +DetailPage);

			ExtentTestManager.getTest().log(LogStatus.PASS, " Costomer Detail page is displaying as expected");
		}
		
		
		public void searchdevice(String application, String DeviceName,String ManagementAdd,String Vendor, String DeviceType, String RouterID, String SearchCity, String City, String SearchCO,String CO) throws InterruptedException, DocumentException, IOException {


			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageColtNetworkLink")));
			Thread.sleep(2000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchdevicelink")));
			Thread.sleep(2000);
			EnterTextValueCommon(application, DeviceName, "Device Name", "TextValueCommon");
			
			EnterTextValueCommon(application, ManagementAdd, "Management/Interface Address", "TextValueCommon");
			
			String[] VendorSelectedList=Vendor.split(","); 
			String[] DeviceTypeSelectedList=DeviceType.split(","); 
			//System.out.println(VendorSelectedList);
			
			
			for(int i=0;i<VendorSelectedList.length;i++) {
			click_commonMethod(application, "Vendoe Selection", "NetworkDeviceVendor", xml);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/NetworkDeviceVendor")),VendorSelectedList[i]);
		    Thread.sleep(2000);
			click_commonMethod(application, "Vendor Selection", "SelectDeviceVendor", xml);
			Thread.sleep(1000);
				
			}
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/NetworkDeviceVendor")), Keys.ENTER);
			Thread.sleep(3000);
			for(int i=0;i<DeviceTypeSelectedList.length;i++) {
				click_commonMethod(application, "Device Selection", "NetworkDeviceType", xml);
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/NetworkDeviceType")),DeviceTypeSelectedList[i]);
			    Thread.sleep(2000);
				click_commonMethod(application, "Device Selection", "SelectDeviceType", xml);
				Thread.sleep(1000);
				
			}
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/" + application + "/NetworkDeviceType")), Keys.ENTER);
			Thread.sleep(3000);	
			EnterTextValueCommon(application, RouterID, "Router Id", "TextValueCommon");
				
			EnterTextValueCommon(application, SearchCity, "Search City", "TextValueCommon");
			Thread.sleep(3000);
			
			EnterTextValueCommon(application, SearchCO, "Search CO", "TextValueCommon");
			Thread.sleep(3000);
			

			WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
			scrolltoend();
			Thread.sleep(2000);
			Clickon(searchbutton);
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/DeviceSelectioradio")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
			Thread.sleep(1000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewLink_SearchOrderPage")));
			Thread.sleep(5000);
			scrollToTop();
			Thread.sleep(5000);
			compareText(application, "Device detail", "DeviceDetailHeader", "Device Details");
			GetText(application, "Name", "NetworkDeviceName");
			GetText(application, "Device Type", "NetworkDeviceTypeValue");
			GetText(application, "Vendor", "NetworkVendor");
			GetText(application, "Router ID", "NetworkRouterID");
			GetText(application, "Management Address", "NetworkManagementAdd");
			GetText(application, "Country", "NetworkCountry");
			Thread.sleep(4000);
			/*
			
			
			Boolean DetailPage = getwebelement(xml.getlocator("//locators/" + application + "/DetailPageHeader")).isDisplayed();
			
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigate to Customer detail in view service page is " +DetailPage);

			ExtentTestManager.getTest().log(LogStatus.PASS, " Costomer Detail page is displaying as expected");
			*/
		}
		
		
		
		
		
		
		
		public void addDropdownValues(String application, String fieldname, String xpath, String expectedValueToAdd) throws InterruptedException, DocumentException {
			boolean availability=false;
			try {  
				availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				if(availability) {
					ExtentTestManager.getTest().log(LogStatus.PASS, fieldname + " dropdown is displaying as expected");
					System.out.println(fieldname + " dropdown is displaying as expected");

					if(expectedValueToAdd.equalsIgnoreCase("null")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ fieldname + " dropdown");
						System.out.println(" No values selected under "+ fieldname + " dropdown");
					}else {

						Clickon(getwebelement("//div[label[text()='"+ fieldname +"']]//div[text()='×']"));
						Thread.sleep(3000);

						//verify list of values inside dropdown
						List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ fieldname + " dropdown is:  ");
						System.out.println( " List of values inside "+ fieldname + "dropdown is:  ");

						for (WebElement valuetypes : listofvalues) {

							Log.info("service sub types : " + valuetypes.getText());
							ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
							System.out.println(" " + valuetypes.getText());

						}

						Thread.sleep(3000);
						Clickon(getwebelement("//div[text()='"+ expectedValueToAdd +"']"));
						Thread.sleep(3000);

						String actualValue=getwebelement("//div[label[text()='"+ fieldname +"']]//span").getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, fieldname + " dropdown value selected as: "+ actualValue );
						System.out.println( fieldname + " dropdown value selected as: "+ actualValue);

					}
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, fieldname + " is not displaying");
					System.out.println(fieldname + " is not displaying");
				}
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, fieldname + " is not displaying");
				System.out.println(fieldname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NO value selected under "+ fieldname + " dropdown");
				System.out.println(" NO value selected under "+ fieldname + " dropdown");
			}

		}
		
		
		
		
		
		public void delete(String application, String labelname, String xpath, String expectedvalue) throws InterruptedException, DocumentException{
			Thread.sleep(1000);	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
			Thread.sleep(2000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on delete link");

			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click(application, "Delete", "deletebutton");
				//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")));
				WebDriverWait wait= new WebDriverWait(driver,50);
				wait= (WebDriverWait) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//locators/" + application + "/"+ xpath +"")));
				compareText(application, "'"+ labelname +"' delete success message", "'"+ xpath +"'", "'"+expectedvalue+"'");
			}
			else
				Log.info("Delete alert popup is not displayed");
		}

		
		public void delete_New(String application, String xpath, String labelname, String expectedvalue, XMLReader xml) throws InterruptedException, DocumentException{
            Thread.sleep(1000);     
            Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
            Thread.sleep(2000);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on delete link");

            WebElement DeleteAlertPopup= driver.findElement(By.xpath("//div[@class='modal-content']"));
            if(DeleteAlertPopup.isDisplayed())
            {
                  WebElement Deletebutton= driver.findElement(By.xpath("//button[text()='Delete']"));
                  Clickon(Deletebutton);
                  Thread.sleep(2000);
                  compareText_New(application, "'"+ labelname +"' delete success message", "'"+ xpath +"'", "'"+expectedvalue+"'", xml);
            }
            else
            {
                  Log.info("Delete alert popup is not displayed");
                  ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete alert popup is not displayed");
            }
      }


		public void EnterTextValue(String application, String value, String labelname, String xpath) {
			WebElement element = null;

			try {
				//Thread.sleep(1000);
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' text field not found");
					System.out.println(element);
				}
				else 
				{
					element.sendKeys(value);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Entered '"+value+"' into '"+labelname+"' text field");
				}

			} catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+value+"' into '"+labelname+"' text field");
				e.printStackTrace();
			}

		}
		
		public void EnterTextValueCommon(String application, String value, String labelname, String xpath) {
			WebElement element = null;

			try {
				//Thread.sleep(1000);
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value",labelname));
				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' text field not found");
					System.out.println(element);
				}
				else 
				{
					if (value.equalsIgnoreCase("null")) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "No values added to text field " + labelname);
						System.out.println("No values added to text field " + labelname);
					}
					else {
					element.sendKeys(value);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Entered '"+value+"' into '"+labelname+"' text field");
					}
				}

			} catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+value+"' into '"+labelname+"' text field");
				e.printStackTrace();
			}

		}
		public void ClickCommon(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
			WebElement element= null;

			try {
				//Thread.sleep(1000);
				element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname));
				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
				}
				else {
					element.click();	
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
				}

			} catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
				e.printStackTrace();
			}
		}

		

		public void click(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
			WebElement element= null;

			try {
				//Thread.sleep(1000);
				element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
				}
				else {
					element.click();	
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
				}

			} catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
				e.printStackTrace();
			}
		}

		public void compareText(String application, String labelname, String xpath, String ExpectedText) throws InterruptedException, DocumentException {

			String text = null;
			WebElement element = null;

			try {
				Thread.sleep(1000);
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
				System.out.println("text is present as:"+element.getText());
				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
				}
				else if (emptyele!=null && emptyele.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
				}else 
				{   
					text = element.getText();
					
					if(text.equalsIgnoreCase(ExpectedText)) {
						ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					}
					else if(text.contains(ExpectedText)) {
						ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
					}
				}
			}catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
				e.printStackTrace();
			}

		}
		
		
		
		
	
		
		
		public void compareText_New(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

            String text = null;
            WebElement element = null;

            try {
                  Thread.sleep(1000);
                  element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
                  String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
                  if(element==null)
                  {
                        ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
                  }
                  else if(ExpectedText.equalsIgnoreCase("null")) {
                        ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' field value is empty");
                        System.out.println("No values added to text field "+labelname);
                  }
                  else if (emptyele!=null && emptyele.isEmpty()) {
                        ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
                  }else 
                  {   
                        text = element.getText();
                        if(text.equals(ExpectedText)) {
                              ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
                        }
                        else if(text.contains(ExpectedText)) {
                              ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
                        }
                        
                  }
            }catch (Exception e) {
                  ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: Field is not displaying");
                  e.printStackTrace();
            }

      }

		
		public void compareText_Common(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

            String text = null;
            WebElement element = null;

            try {
                  Thread.sleep(1000);
                  element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname));
                  String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname)).getAttribute("value");
                  if(element==null)
                  {
                        ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
                  }
                  else if(ExpectedText.equalsIgnoreCase("null")) {
                        ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' field value is empty");
                        System.out.println("No values added to text field "+labelname);
                  }
                  else if (emptyele!=null && emptyele.isEmpty()) {
                        ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
                  }
                  else if(emptyele!=null) {
                	  if(emptyele.contains(ExpectedText)) {
                		  ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+emptyele+"'");
                	  }
                  }
                  else 
                  {   
                        text = element.getText();
                  
                        if(text.equalsIgnoreCase(ExpectedText)) {
                              ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
                        }
                        else if(text.contains(ExpectedText)||ExpectedText.contains(text)) {
                              ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
                        }
                        
                  }
            }catch (Exception e) {
                  ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: Field is not displaying");
                  e.printStackTrace();
            }

      }

		

		
		

		public void WarningMessage(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
			WebElement element= null;
			try {
				//Thread.sleep(1000);
				element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				//Thread.sleep(2000);
				if(element==null) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
				}
				else
				{
					String WarningMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getText();

					System.out.println("'"+labelname+"' field warning  message displayed as : " + WarningMsg);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Step : validation message for '"+labelname+"' text field displayed as : " + WarningMsg);
					Log.info("'"+labelname+"' field warning message displayed as : " + WarningMsg);
				}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				System.out.println("'"+labelname+"' field warning message is not dipslaying");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' field warning message is not displaying");
			}catch(Exception ed) {
				ed.printStackTrace();
			}
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

		
		//Added by PK
		public void ClearAndEnterTextValue(String application, String labelname,  String xpath, String newValue) {
			WebElement element = null;
			try {
				//Thread.sleep(1000);
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				String value= element.getAttribute("value");
				
				if(value.isEmpty())
				{
					ExtentTestManager.getTest().log(LogStatus.INFO, "Step: '"+labelname+"' text field is empty");
					System.out.println(value);
				
				}else {
					element.clear();
					Thread.sleep(1000);
					element.sendKeys(newValue);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Entered '"+newValue+"' into '"+labelname+"' text field");
				}

			} catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+newValue+"' into '"+labelname+"' text field");
				e.printStackTrace();
			}

		}
		public void ClearAndEnterTextValueComm(String application, String labelname,  String xpath, String newValue) {
			WebElement element = null;
			try {
				//Thread.sleep(1000);
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname));
				String value= element.getAttribute("value");
				
				if(value.isEmpty())
				{
					ExtentTestManager.getTest().log(LogStatus.INFO, "Step: '"+labelname+"' text field is empty");
					System.out.println(value);
				
				}else {
					element.clear();
					Thread.sleep(1000);
					element.sendKeys(newValue);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Entered '"+newValue+"' into '"+labelname+"' text field");
				}

			} catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+newValue+"' into '"+labelname+"' text field");
				e.printStackTrace();
			}

		}
		

		
		public void isDisplayed1(String application, String xpath, String labelname) {
			boolean availability = false;

			try {
				Thread.sleep(1000);
				availability= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				System.out.println(availability);
				if (availability) {
					Thread.sleep(2000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: '"+labelname+"' is displayed as expected");
				}
				else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' is not displaying as expected");
				}

			} catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: '"+labelname+"' is not available to display");
				e.printStackTrace();
			}
		}

	
		
		public String Checkboxvalue(String application, String xpath) throws InterruptedException, DocumentException {

			String value= null;
			value= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
			if(value!=null)
			{
				return "Yes";
			}
			else
				return "No";
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
				else if(ele!=null) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step: '"+ labelname +"' value is displayed as : '"+ele+"'");
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

		
		public String GetTextCommon(String application, String labelname, String xpath) throws InterruptedException, DocumentException {

			String text = null;
			WebElement element = null;

			try {
				Thread.sleep(1000);
				element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname)); 
				String ele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname)).getAttribute("value");
				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' is not found");
				}
				else if (ele!=null && ele.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
				}
				else if(ele!=null) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step: '"+ labelname +"' value is displayed as : '"+ele+"'");
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

		
		//	public String Gettext(WebElement el) throws IOException
		//	{ 
		//		String text=el.getText().toString();
		//		return text;
		//	} 

		//public void scrolltoend() {//Or Scroll Down
			//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//}
		
		//		public void scrollToTop() {
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
		//}

		public void clickOnBankPage() {
			driver.findElement(By.xpath("//body")).click();
		}


		//Scroll to particular webelement
		public void ScrolltoElementComm(String application,String labelname, String xpath) throws InterruptedException, DocumentException {
			WebElement element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",(element));
		}

		public void ScrolltoElement(String application, String xpath)
				throws InterruptedException, DocumentException {
			WebElement element = getwebelement(xml.getlocator("//locators/" + application + "/" + xpath + ""));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", (element));
		}
		
		
		
		
		public void writetoexcel(String filepath, String sheetname, int columnnumber, String inputvalue) throws IOException{

			File file= new File(filepath);
			FileInputStream fis= new FileInputStream(file);
			XSSFWorkbook work= new XSSFWorkbook(fis);
			XSSFSheet sh= work.getSheet(sheetname);
			sh.getRow(1).createCell(columnnumber).setCellValue(inputvalue);
			fis.close();
			FileOutputStream fos= new FileOutputStream(file);
			work.write(fos);
			work.close();
			fis.close();

		}
		
		
		
		
		
		
		
			//======================================  Common Methods  ===========================================	
		
			//======================================  Common Methods  ===========================================

			//======================================  Common Methods  ===========================================

		
		
		/**
		 *   For text field commmon method _  Add
		 * @param application
		 * @param labelname
		 * @param xpathname
		 * @param expectedValueToAdd
		 * @param xml
		 * @throws InterruptedException
		 * @throws DocumentException
		 * @throws IOException
		 */
		public void addtextFields_commonMethod(String application, String labelname, String xpathname, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException, IOException {
			boolean availability=false;
		try {	
			availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is displaying");
				System.out.println(labelname + " text field is displaying");
				
				if(expectedValueToAdd.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No values added to text field "+labelname);
					System.out.println("No values added to text field "+labelname);
				}else {
					
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToAdd);
					Thread.sleep(3000);
					
					String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field value added as: "+ actualvalue);
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to add value to "+ labelname + " text field");
			System.out.println(" Not able to add value to "+ labelname + " text field");
		}
	}
		
		
		
		/**
		 *   For Dropdown common method _  Add
		 * @param application
		 * @param labelname
		 * @param xpath
		 * @param expectedValueToAdd
		 * @param xml
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		
		/*
		public void addDropdownValues_commonMethod(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
			  boolean availability=false;
			try {  
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			  if(availability) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  System.out.println(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					  Thread.sleep(3000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
									System.out.println(" " + valuetypes.getText());
						}
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(2000);
						
					  Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(3000);
					  
					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
				System.out.println(" NO value selected under "+ labelname + " dropdown");
			}
			
		}
		
		*/
		
		
		
		
		
		
		public void addDropdownValues_commonMethod(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
			  boolean availability=false;
			try {  
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			  if(availability) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  System.out.println(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					  Thread.sleep(2000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
									System.out.println(" " + valuetypes.getText());
						}
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(4000);
						
					Clickon(getwebelement("(//div[label[text()='"+ labelname +"']]//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(3000); 
					  
					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
				System.out.println(" NO value selected under "+ labelname + " dropdown");
			}
			
		}


		
		public void addDropdownValues_common(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
			  boolean availability=false;
			try {  
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname)).isDisplayed();
			  if(availability) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  System.out.println(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					  Thread.sleep(2000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
									System.out.println(" " + valuetypes.getText());
						}
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(4000);
					Clickon(getwebelement("(//div[label[text()='"+ labelname +"']]//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(3000); 
					 // Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  //Thread.sleep(2000);
					  
					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
				System.out.println(" NO value selected under "+ labelname + " dropdown");
			}
			
		}

		public void addDropdownValues_commonMethodDivSpan(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml)
				throws InterruptedException, DocumentException {
			  boolean availability=false;
			  List<String> ls = new ArrayList<String>();
			  
			try {  
				
				 availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname)).isDisplayed();
				 if(availability) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  System.out.println(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelementNoWait("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					  
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//span[@role='option']"));
					  
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("List of values : " + valuetypes.getText());
									 ls.add(valuetypes.getText());
						}
						
						    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
				            System.out.println("list of values inside "+labelname+" dropdown is: "+ls);
						
					SendKeys(getwebelementNoWait("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
						
					  Clickon(getwebelementNoWait("(//div[label[text()='"+ labelname +"']]//span[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(1000);
					  
					  String actualValue=getwebelementNoWait("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
				System.out.println(" NO value selected under "+ labelname + " dropdown");
			}
			
		}
		
		
		
		public void ClearDropdownValues_common(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
			  boolean availability=false;
			try {  
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname)).isDisplayed();
			  if(availability) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  System.out.println(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					  Thread.sleep(2000);
					  Clear(getwebelement("//div[label[text()='"+ labelname +"']]//input"));
					  Thread.sleep(2000);
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
									System.out.println(" " + valuetypes.getText());
						}
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(4000);
						
					  Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(2000);
					  
					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
				System.out.println(" NO value selected under "+ labelname + " dropdown");
			}
			
		}

				
		
		

		/**
		 *  For checkbox common method _  Add
		 * @param application
		 * @param xpath
		 * @param labelname
		 * @param expectedValue
		 * @param DefaultSelection
		 * @param xml
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void addCheckbox_commonMethod(String application, String xpath, String labelname, String expectedValue, String DefaultSelection, XMLReader xml) throws InterruptedException, DocumentException {
			
			boolean availability=false;
			try {	
				availability = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				if(availability) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " checkbox is displaying");
					System.out.println(labelname + " checkbox is displaying");
					
				boolean isElementSelected=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isSelected();
				Thread.sleep(2000);
			
			//verify whether checkbox is selected/unselected by default		
				if(DefaultSelection.equalsIgnoreCase("yes")) {
					if(isElementSelected) {
						ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " checkbox is selected by default as expected");
						System.out.println(labelname + " checkbox is selected by default as expected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " checkbox is not selected by default");
						System.out.println(labelname + " checkbox is not selected by default");
					}
					
				}
				else if(DefaultSelection.equalsIgnoreCase("no")) {
					if(isElementSelected) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " checkbox is selected by default");
						System.out.println(labelname + " checkbox is selected by default as expected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " checkbox is not selected by default as expected");
						System.out.println(labelname + " checkbox is not selected by default");
					}
					
				}
			
			//Perform click on checkbox	
				if(!expectedValue.equalsIgnoreCase("null")) {
					if (expectedValue.equalsIgnoreCase("yes")) {

						if(isElementSelected) {
							ExtentTestManager.getTest().log(LogStatus.PASS, labelname +" checkbox is Selected by default");
						}else {
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
							Log.info(labelname + " check box is selected");
							ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " checkbox is selected");
						}
					}
					else if (expectedValue.equalsIgnoreCase("no")) {
						
						if(isElementSelected) {
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
							Log.info(labelname + " check box is unselected");
							ExtentTestManager.getTest().log(LogStatus.PASS,labelname + " is selected");
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for "+ labelname +" checkbox");
							System.out.println("No changes made for "+ labelname +" checkbox");
						}
						
					}
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for "+ labelname +" checkbox");
					System.out.println("No changes made for "+ labelname +" checkbox");
				}
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " checbox is not available");
					System.out.println(labelname + " checbox is not available");
				}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname +  " checkbox is not available ");
				System.out.println( labelname +  " checkbox is not available ");
			}catch(Exception er) {
				er.printStackTrace();
				System.out.println("Not able to perform selection for "+ labelname+ " checkbox");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to perform selection for "+ labelname+ " checkbox");
			}
		}



	/**
	 *  For Text field common Method _  Edit
	 * @param application
	 * @param labelname
	 * @param xpathname
	 * @param expectedValueToEdit
	 * @param xml
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
		public void edittext_common(String application, String labelname, String xpathname, String expectedValueToEdit, XMLReader xml) throws InterruptedException, DocumentException, IOException {
				boolean availability=false;
			try {	
				availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"").replace("Value", labelname)).isDisplayed();
				if(availability) {
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is displaying");
					System.out.println(labelname + " text field is displaying");
					
					if(expectedValueToEdit.equalsIgnoreCase("null")) {
						
						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"").replace("Value", labelname)).getAttribute("value");
						ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is not edited. It is displaying as "+actualvalue);
						System.out.println(labelname + " text field is not edited as expected. It is displaying as "+actualvalue);
					}else {
						
						getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"").replace("Value", labelname)).clear();
						Thread.sleep(3000);
						
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"").replace("Value", labelname)), expectedValueToEdit);
						Thread.sleep(3000);
						
						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"").replace("Value", labelname)).getAttribute("value");
						ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is edited as: "+ actualvalue);
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
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to perform editing under"+ labelname + " text field");
				System.out.println(" Not able to perform editing under "+ labelname + " text field");
			}
			}

			

		/**
		 *  Mandatory field  Warning message validation
		 * @param application
		 * @param xpath
		 * @param fieldlabelName
		 * @param xml
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void warningMessage_commonMethod(String application, String xpath, String fieldlabelName, XMLReader xml) throws InterruptedException, DocumentException {
			 	boolean message=false;
			 	//Field Error Message
			 			try {
			 				message = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath  +"")).isDisplayed();
			 				Thread.sleep(3000);
			 			sa.assertTrue(message, fieldlabelName + " field warning message is not displayed ");
			 			if(message) {
			 			String ErrMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath  +"")).getText();
			 			
			 			System.out.println( fieldlabelName + " field warning  message displayed as : " + ErrMsg);
			 			ExtentTestManager.getTest().log(LogStatus.PASS, "Step :  validation message for"+ fieldlabelName +"  field displayed as : " + ErrMsg);
			 			Log.info(fieldlabelName + " field warning  message displayed as : " + ErrMsg);
			 			}else{
			 				ExtentTestManager.getTest().log(LogStatus.FAIL, "validation message for"+ fieldlabelName +"  field is not displaying");
			 				System.out.println("validation message for"+ fieldlabelName +"  field is not displaying");
			 			}
			 			}catch(NoSuchElementException e) {
			 				e.printStackTrace();
			 				System.out.println( "No warning message displayed for "+ fieldlabelName);
			 				ExtentTestManager.getTest().log(LogStatus.FAIL, "No warning message displayed for "+ fieldlabelName);
			 			}catch(Exception ed) {
			 				ed.printStackTrace();
			 				System.out.println( "No warning message displayed for "+ fieldlabelName);
			 				ExtentTestManager.getTest().log(LogStatus.FAIL, "No warning message displayed for "+ fieldlabelName);
			 			}
			 }
			
			
			//Click Method
			public void click(String application, String labelname, String xpath, XMLReader xml) throws InterruptedException, DocumentException {
				WebElement element= null;

				try {
					Thread.sleep(1000);
					element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
					if(element==null)
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
					}
					else {
						element.click();	
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
					}

				} catch (Exception e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
					e.printStackTrace();
				}
			}
			
			

		/**
		 *  For checkbox common method _  Edit	
		 * @param application
		 * @param expectedResult
		 * @param xpath
		 * @param labelname
		 * @param xml
		 */
			public void editcheckbox_commonMethod(String application, String expectedResult, String xpath, String labelname, XMLReader xml) {
				 
				  boolean Availability=false;
				  try {
					  Availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				  
				  if(Availability) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS,  labelname+ " checkbox is displaying in edit page");
					  System.out.println(labelname+ " checkbox is displaying in edit page");
					  
					if(!expectedResult.equalsIgnoreCase("null")) {
						boolean isElementSelected=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isSelected();
						Thread.sleep(2000);
						
						if (expectedResult.equalsIgnoreCase("yes")) {
							
							if(isElementSelected) {
								ExtentTestManager.getTest().log(LogStatus.PASS, labelname +" checkbox is not edited and it is already Selected while creating");
							}else {
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
								Log.info(labelname + " check box is selected");
								ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " checkbox is selected");
							}
						}
						else if (expectedResult.equalsIgnoreCase("no")) {
							
							if(isElementSelected) {
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
								Log.info(labelname + " check box is unselected");
								ExtentTestManager.getTest().log(LogStatus.PASS,labelname + " is edited and gets unselected");
							}else {
								ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " is not edited and it remains unselected");
							}
							
						}
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made for "+ labelname +"  chekbox");
					}
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " checkbox is not available in 'Edit Service' page");
				  }

					}catch(NoSuchElementException e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " checkbox is not displaying under 'Edit service' page");
						System.out.println(labelname+" checkbox is not displaying under 'Edit service' page");
					}catch(Exception err) {
						err.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on "+ labelname + " checkbox");
						System.out.println(" Not able to click on "+ labelname + " checkbox");
					}
			}

			
			/**
			 *  For delete common method
			 * @param application
			 * @param xpath
			 * @param labelname
			 * @param expectedvalue
			 * @throws InterruptedException
			 * @throws DocumentException
			 */
			
			public void delete(String application, String xpath, String labelname, String expectedvalue, XMLReader xml) throws InterruptedException, DocumentException{
				Thread.sleep(1000);	
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
				Thread.sleep(2000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on delete link");

				WebElement DeleteAlertPopup= driver.findElement(By.xpath("//div[@class='modal-content']"));
				if(DeleteAlertPopup.isDisplayed())
				{
					click_commonMethod(application, "Delete", "deletebutton", xml);
					//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")));
					WebDriverWait wait= new WebDriverWait(driver,50);
					wait= (WebDriverWait) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//locators/" + application + "/"+ xpath +"")));
					compareText(application, "'"+ labelname +"' delete success message", "'"+ xpath +"'", "'"+expectedvalue+"'", xml);
				}
				else
				{
					Log.info("Delete alert popup is not displayed");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete alert popup is not displayed");
				}
			}
			
			
		/**
		 *  verify whether button is available for clicking and click on respective button	
		 * @param application
		 * @param labelname
		 * @param xpath
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
			public void click_commonMethod(String application, String labelname, String xpath, XMLReader xml) throws InterruptedException, DocumentException {
				WebElement element= null;

				try {
					Thread.sleep(1000);
					element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
					if(element==null)
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
					}
					else {
						element.click();	
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
					}

				} catch (Exception e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
					e.printStackTrace();
				}
			}
			
			
			/**
			 *  For Comparing the values 
			 * @param application
			 * @param labelname
			 * @param xpath
			 * @param ExpectedText
			 * @param xml
			 * @throws InterruptedException
			 * @throws DocumentException
			 */
			public void compareText(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

				String text = null;
				WebElement element = null;

				try {
					Thread.sleep(1000);
					element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
					String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
					if(element==null)
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
					}
					else if (emptyele!=null && emptyele.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
					}else 
					{   
						text = element.getText();
						if(text.equals(ExpectedText)) {
							ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
						}
						else if(text.contains(ExpectedText)) {
							ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
					System.out.println(labelname + " field is not displaying");
				}

			}
			
			
			/**
			 *  For Comparing the values under view page
			 * @param application
			 * @param labelname
			 * @param xpath
			 * @param ExpectedText
			 * @param xml
			 * @throws InterruptedException
			 * @throws DocumentException
			 */
			@SuppressWarnings("unused")
			public void compareText_InViewPage(String application, String labelname,  String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

				String text = null;
				WebElement element = null;

				try {
					Thread.sleep(1000);
					element = getwebelement("//div[div[label[contains(text(),'"+ labelname + "')]]]/div[2]");
					String emptyele = element.getText().toString();

					if(element==null)
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
						System.out.println(labelname+" not found");
					}
					else if (emptyele!=null && emptyele.isEmpty()) {
//						ExtentTestManager.getTest().log(LogStatus.PASS,  labelname + "' value is empty");
						
						emptyele= "Null";
						
						sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
						
						if(emptyele.equalsIgnoreCase(ExpectedText)) {
							
							ExtentTestManager.getTest().log(LogStatus.PASS, " The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							System.out.println(" The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							System.out.println(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
						

					}else 
					{   
						text = element.getText();
						if(text.equals(ExpectedText)) {
							ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							System.out.println(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(text.contains(ExpectedText)) {
							ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
					System.out.println(labelname + " field is not displaying");
				}

			}


			/**
			 *  verify whether button is available for clicking and click on respective button	
			 * @param application
			 * @param labelname
			 * @param xpath
			 * @throws InterruptedException
			 * @throws DocumentException
			 */
				public void click_commonMethod_PassingWebelementDirectly(String application, String labelname, String webelement, XMLReader xml) throws InterruptedException, DocumentException {
					WebElement element= null;

					try {
						Thread.sleep(1000);
						element = getwebelement(webelement);
						if(element==null)
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
						}
						else {
							element.click();	
							ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
						}

					} catch (Exception e) {
						ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
						e.printStackTrace();
					}
				}
				
				
				public void selectValueInsideDropdown(String application, String xpath, String labelname, String expectedValueToAdd, XMLReader xml) throws IOException, InterruptedException
				{ 
					//getAllValuesInsideDropDown
					 boolean availability=false;
					 List<String> ls = new ArrayList<String>();
					 
						try {  
						  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
						  if(availability) {
							  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
							  System.out.println(labelname + " dropdown is displaying");
							  
							  WebElement el =getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
							  
							  Select sel = new Select(el);
							  
							 String firstSelectedOption=sel.getFirstSelectedOption().getText();
							 ExtentTestManager.getTest().log(LogStatus.PASS, "By default "+ labelname+" dropdown is displaying as: "+firstSelectedOption);
							 System.out.println("By default "+ labelname+" dropdown is displaying as: "+firstSelectedOption);
							 
							    List<WebElement> we = sel.getOptions();
							   
							    for(WebElement a : we)
							    {
							        if(!a.getText().equals("select")){
							            ls.add(a.getText());
							            
							        }
							    }
						
							    
							    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
					            System.out.println("list of values inside "+labelname+" dropdown is: "+ls);
					            
							  if(expectedValueToAdd.equalsIgnoreCase("null")) {
								  
								  ExtentTestManager.getTest().log(LogStatus.PASS, "No values selected under "+ labelname + " dropdown");
							  }else {
								  Select s1=new Select(el);
								  s1.selectByVisibleText(expectedValueToAdd);
								  
								  String SelectedValueInsideDropdown=sel.getFirstSelectedOption().getText();
									 ExtentTestManager.getTest().log(LogStatus.PASS,  labelname+" dropdown value selected as: "+SelectedValueInsideDropdown);
									 System.out.println(labelname+" dropdown value selected as: "+SelectedValueInsideDropdown);
							  }
							 }
							
						}catch(NoSuchElementException e) {
							ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " Value is not displaying");
							  System.out.println(labelname + " value is not displaying");
						}catch(Exception ee) {
							ee.printStackTrace();
							ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
							System.out.println(" NO value selected under "+ labelname + " dropdown");
						}
				}
				
		
		
				
				
				
				
				public void verifysuccessmessage1(String application, String expected) throws InterruptedException {
					
					scrollToTop();
					Thread.sleep(3000);
					try {	
						
						boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMsg")).isDisplayed();

						if(successMsg) {
							
							String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
							
							if(expected.contains(alrtmsg)) {
								
								ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
								System.out.println("Message is verified. It is displaying as: "+alrtmsg);
								
							}else {
								
								ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
								System.out.println("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
							}
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
							System.out.println(" Success Message is not displaying");
						}
						
					}catch(Exception e) {
						Log.info("failure in fetching success message - 'Service created Successfully'  ");
						ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
						System.out.println(expected+ " message is not getting dislpayed");
					}

				}

	
				
				
				//======================================  Common Methods  ===========================================	
				
				//======================================  Common Methods  ===========================================

				//======================================  Common Methods  ===========================================

				
				
				
		

	public void createnewcustomer(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws Exception {

		// webelementpresencelogger(getwebelement(xml.getlocator("//locators/" +
		// application + "/createcustomerlink")), "Create Customer link");

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		//Thread.sleep(2000);
		System.out.println("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click(application, "create customer link", "createcustomerlink");
		//Thread.sleep(2000);
		compareText(application, "create customer page header", "createcustomer_header", "Customer");

		scrolltoend();
		
		click(application, "Ok", "okbutton");
		//Warning msg check
		WarningMessage(application, "Customer Name","customernamewarngmsg");
		WarningMessage(application,"Country", "countrywarngmsg");
		WarningMessage(application,"OCN", "ocnwarngmsg");
		WarningMessage(application,"Type", "typewarngmsg");
		WarningMessage(application, "Email","emailwarngmsg");

		//Clear customer info
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		EnterTextValue(application, email, "Email", "emailtextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		click(application, "Clear button", "Resetbutton");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "All text field values are cleared");

		//Create customer by providing all info
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		addDropdownValues(application, "Country", "country", country);
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		addDropdownValues(application, "Type", "typedropdown", type);
		EnterTextValue(application, email, "Email", "emailtextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		click(application, "Ok", "okbutton");
		compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.");
		sa.assertAll();	
	}

	

	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected, String customerName1, String customerName2)
			throws InterruptedException, DocumentException, IOException {


		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		System.out.println("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click(application, "Create Order/Service Link", "CreateOrderServiceLink");	
		Log.info("=== Create Order/Service navigated ===");

		//click on Next button to check mandatory messages	
		click(application, "Next", "nextbutton");

		//Customer Error message	
		//WarningMessage(application,"Customer","customer_createorderpage_warngmsg");
		WarningMessageCom(application, "Choose a customer", "WarningMessageddnCommon");

		//Entering Customer name	
		EnterTextValue(application, ChooseCustomerToBeSelected, "Customer Name", "entercustomernamefield");
		waitforPageenable();
		//Thread.sleep(5000);
		EnterTextValue(application, "*", "Customer Name", "entercustomernamefield");
		//Thread.sleep(3000);
		//ClearAndEnterTextValue(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected);
		waitforPageenable();
		//Thread.sleep(2000);
		
		//Select Customer from dropdown
		
		addDropdownValues_commonMethod(application, "Choose a customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected,xml);
		click(application, "Next", "Next_Button");

	}


	public void verifyCustomerDetailsInformation(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws InterruptedException, DocumentException, IOException {

		WebElement CustomerDetailsHeader=getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader"));
		scrolltoview(CustomerDetailsHeader);

		// verify Name information
		compareText(application, "Customer Name", "Name_Value", name);
		//String Name_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name_Text")));

		// verify MainDomain information
		compareText(application, "Main Domain", "MainDomain_Value", maindomain);
		//String MainDomain_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/MainDomain_Text")));

		// verify Country information
		compareText(application, "Country", "Country_Value", country);
		//String Country_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Country_Text")));

		// verify OCN information
		compareText(application, "OCN", "OCN_Value", ocn);
		//String OCN_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/OCN_Text")));

		// verify Reference information
		compareText(application, "Reference", "Reference_Value", reference);
		//String Reference_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Reference_Text")));

		// verify Technical Contact Name information
		compareText(application, "Technical Contact Name", "TechnicalContactName_Value", tcn);
		//String TechnicalContactName_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/TechnicalContactName_Text")));

		// verify Type information
		compareText(application, "Type", "Type_Value", type);
		//String Type_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Type_Text")));

		//		// verify Name2 information
		//		String Name2_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name2_Text")));
		//		String Name2_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name2_Value")));
		//		Log.info(Name2_Text + " : TextField value is displayed as : " + Name2_Value);
		//		System.out.println(Name2_Text + " : " + Name2_Value);

		// verify Email information
		compareText(application, "Email", "Email_Value", email);
		//String Email_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Email_Text")));

		// verify Phone information
		compareText(application, "Phone", "Phone_Value", phone);
		//String Phone_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Phone_Text")));

		// verify Fax information
		compareText(application, "Fax", "Fax_Value", fax);
		//String Fax_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Fax_Text")));
		Log.info("=== Customer Details panel fields Verified ===");
		sa.assertAll();
	}

	public void verifyUserDetailsInformation(String application, String Login, String Name, String Email, String Roles, String Address, String Resource)
			throws InterruptedException, DocumentException, IOException {

		// verify Login column
		compareText(application, "Login column", "LoginColumn", Login);

		// verify Name column
		compareText(application, "Name column", "NameColumn", Name);

		// verify Email column
		compareText(application, "Email column", "EmailColumn", Email);

		// verify Roles column
		compareText(application, "Roles column", "RolesColumn", Roles);

		// verify Address column
		compareText(application, "Address column", "AddressColumn", Address);

		// verify Resource column
		compareText(application, "Resource column", "ResourcesColumn", Resource);

	}


	public void createnewuser(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email,String Phone, String EditUsername, String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone,
			String UserRoles,String UserHideService,String UserHideRouterCis,String UserHideRouterHua,String UserHideIPV6)throws InterruptedException, DocumentException, IOException {

		WebElement Fax_TextinViewServicepage=getwebelement(xml.getlocator("//locators/" + application + "/Fax_Text"));
		scrolltoview(Fax_TextinViewServicepage);
		
		
		
		WebElement UserGridCheck= driver.findElement(By.xpath("(//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String UserGrid= UserGridCheck.getAttribute("style");

		//if(UserGrid.contains("height: 1px"))
			
		
			System.out.println("no user is Present");
			
			
			//Cancel User
			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "Add", "AddLink");
			compareText(application, "Create User Header", "CreateUserHeader", "Create User");
			
			
			
			EnterTextValue(application, Username, "User Name", "UserName");
			EnterTextValue(application, Firstname, "First Name", "FirstName");
			EnterTextValue(application, Surname, "SurName", "SurName");
			EnterTextValue(application, Postaladdress, "Postal Address", "PostalAddress");
			EnterTextValue(application, Email, "Email", "Email");
			EnterTextValue(application, Phone, "Phone", "Phone");
            
			WebElement CancelButton= getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton"));
			scrolltoview(CancelButton);
			click(application, "Cancel", "cancelbutton");
			Thread.sleep(2000);
			WebElement Dp= getwebelement("//label[text()='Dedicated Portal']");
			scrolltoview(Dp);
			
			
			compareText(application, "User panel Header", "userspanel_header", "Users");


			//Create User
			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "Add", "AddLink");
			compareText(application, "Create User Header", "CreateUserHeader", "Create User");
			
			
			//Mandatory field Check
			WebElement OKButton= getwebelement("//button[@type='submit']");
			scrolltoview(OKButton);
			click(application, "OK", "OkButton");
			WarningMessageCom(application, "User Name","WarningMessageCommon");
			WarningMessageCom(application, "First Name","WarningMessageCommon");
			WarningMessageCom(application, "Surname", "WarningMessageCommon");
			//WarningMessageCom(application ,"Postal Address", "WarningMessageCommon");
			WarningMessageCom(application, "Email", "WarningMessageCommon");
			WarningMessageCom(application, "Phone", "WarningMessageCommon");
			WarningMessageCom(application, "Password", "WarningMessageCommon");
			
			
			
			EnterTextValue(application, Username, "User Name", "UserName");
			EnterTextValue(application, Firstname, "First Name", "FirstName");
			EnterTextValue(application, Surname, "SurName", "SurName");
			EnterTextValue(application, Postaladdress, "Postal Address", "PostalAddress");
			EnterTextValue(application, Email, "Email", "Email");
			EnterTextValue(application, Phone, "Phone", "Phone");
			click(application, "Generate Password", "GeneratePassword");
				
			EnterTextValueCommon(application, "Test1", "Colt Online User", "TextValueCommon");
			EnterTextValueCommon(application, "Test2", "IPGuardian Account Group", "TextValueCommon");
			ScrolltoElementComm(application, "Colt Online User", "TextValueCommon");

			
			String[] UserRoleList=UserRoles.split(","); 
			String[] UserHideServiceList=UserHideService.split(","); 
			String[] UserHideRouterCisList=UserHideRouterCis.split(","); 
			String[] UserHideRouterHuaList=UserHideRouterHua.split(","); 
			String[] UserHideIPV6List=UserHideIPV6.split(","); 
			
			for(int i=0;i<UserRoleList.length;i++) {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserRoles").replace("Select",UserRoleList[i])));
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserRolestransferbutton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserRoleList[i]+"  User Roles Selected");
				Thread.sleep(1000);	
			}
			for(int i=0;i<UserHideServiceList.length;i++) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserHideService").replace("Select",UserHideServiceList[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserHideServicetransferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserHideServiceList[i]+"  User Roles Selected");
			Thread.sleep(1000);
			}
			for(int i=0;i<UserHideRouterCisList.length;i++) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserIPV4CommandCis").replace("Select",UserHideRouterCisList[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IPV4Cistransferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserHideRouterCisList[i]+"  User Roles Selected");
			Thread.sleep(1000);
			}
			scrolltoview(OKButton);
			for(int i=0;i<UserHideRouterHuaList.length;i++) {	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserIPV4CommandHua").replace("Select",UserHideRouterHuaList[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IPV4Huatransferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserHideRouterHuaList[i]+"  User Roles Selected");
			Thread.sleep(1000);
			}
			for(int i=0;i<UserHideIPV6List.length;i++) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserIPV6CommandCis").replace("Select",UserHideIPV6List[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IPV6transferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserHideIPV6List[i]+"  User Roles Selected");
			Thread.sleep(1000);
			}
			//scrolltoview(OKButton);
			
			click(application, "OK", "OkButton");
			Thread.sleep(2000);
			compareText(application, "Header Message", "UserCreatedMessage", "User successfully created");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User successfully created");
			

			
				}
	
	public void Viewnewuser(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email,String Phone,
			String UserRoles,String UserHideService,String UserHideRouterCis,String UserHideRouterHua,String UserHideIPV6)throws InterruptedException, DocumentException, IOException {
		ScrolltoElement(application, "DedicatedPortalCustomer");
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers==1)
		{
			click(application, "User Radio button", "UserUnchecked");
			//Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			Thread.sleep(3000);
		}
		else if(NoOfUsers>1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");
		}
		Thread.sleep(2000);
		//ScrolltoElement(application, "DedicatedPortalCustomer");
		click(application, "Action dropdown", "UserActionDropdown");
		click(application, "view", "UserView");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/usernamevalue")));
		compareText(application, "User Name", "usernamevalue", Username);
		compareText(application, "First Name", "firstnamevalue", Firstname);
		compareText(application, "SurName", "surnamevalue", Surname);
		compareText(application, "Postal Address", "postaladdressvalue", Postaladdress);
		compareText(application, "Email", "emailvalue", Email);
		compareText(application, "Phone", "phonevalue", Phone);
		
		List<WebElement> HideSerives= driver.findElements(By.xpath("//label[text()='Hide Services']//parent::div//following-sibling::div//*"));
		int HideSerivesCount= HideSerives.size();
		for(int i=0;i<HideSerivesCount;i++)
		{
			String HideServiceData= HideSerives.get(i).getText();
			System.out.println("text is "+HideServiceData);
			ExtentTestManager.getTest().log(LogStatus.PASS, ""+HideServiceData+" is Present");
			Thread.sleep(1000);
		}
		
		List<WebElement> Services_Available_to_User= driver.findElements(By.xpath("//label[text()='Services Available to User']//parent::div//following-sibling::div//br"));
		int ServiceAvailCount= Services_Available_to_User.size();
		if(ServiceAvailCount>0) {
		for(int i=0;i<ServiceAvailCount;i++)
		{
			String Data= Services_Available_to_User.get(i).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, ""+Data+" is Present");
			Thread.sleep(1000);
		}
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No Data Present");
		}
		
		List<WebElement> Hide_Site_Orders= driver.findElements(By.xpath("//label[text()='Hide Site Orders']//parent::div//following-sibling::div//br"));
		int Hide_Site_OrdersCount= Hide_Site_Orders.size();
		if(Hide_Site_OrdersCount>0) {
		for(int i=0;i<Hide_Site_OrdersCount;i++)
		{
			String Data= Hide_Site_Orders.get(i).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, ""+Data+" is Present");
			Thread.sleep(1000);
		}
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No Data Present");
		}
		
		List<WebElement> IPV4Cisco= driver.findElements(By.xpath("//label[text()='Hide Router Tools IPv4 Commands(Cisco)']//parent::div//following-sibling::div//br"));
		int IPV4CiscoCount= IPV4Cisco.size();
		if(IPV4CiscoCount>0) {
		for(int i=0;i<IPV4CiscoCount;i++)
		{
			String Data= IPV4Cisco.get(i).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, ""+Data+" is Present");
			Thread.sleep(1000);
		}
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No Data Present");
		}
		
		
		List<WebElement> IPV4Huawai= driver.findElements(By.xpath("//label[text()='Hide Router Tools IPv4 Commands(Huawei)']//parent::div//following-sibling::div//br"));
		int IPV4HuawaiCount= IPV4Huawai.size();
		if(IPV4HuawaiCount>0) {
		for(int i=0;i<IPV4HuawaiCount;i++)
		{
			String Data= IPV4Huawai.get(i).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, ""+Data+" is Present");
			Thread.sleep(1000);
		}
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No Data Present");
		}
		
		List<WebElement> IPV6Cisco= driver.findElements(By.xpath("//label[text()='Available Router Tools IPv6 Commands(Cisco)']//parent::div//following-sibling::div//br"));
		int IPV6CiscoCount= IPV6Cisco.size();
		if(IPV6CiscoCount>0) {
		for(int i=0;i<IPV6CiscoCount;i++)
		{
			String Data= IPV6Cisco.get(i).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, ""+Data+" is Present");
			Thread.sleep(1000);
		}
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No Data Present");
		}
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/viewpage_backbutton")));
		click(application, "Back", "viewpage_backbutton");
		Log.info("------ View User successful ------");
		Thread.sleep(2000);

	}
	
	public void Editnewuser(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email,String Phone, 
			String EditUserRoles,String EditUserHideService,String EditUserHideRouterCis,String EditUserHideRouterHua,String EditUserHideIPV6,
			String UserRoles,String UserHideService,String UserHideRouterCis,String UserHideRouterHua,String UserHideIPV6)throws InterruptedException, DocumentException, IOException {
		//Edit User
		WebElement OKButton1= getwebelement("//button[@type='submit']");
		ScrolltoElement(application, "DedicatedPortalCustomer");
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers==1)
		{
			click(application, "User Radio button", "UserUnchecked");
			//Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			System.out.println("user checked");
			Thread.sleep(3000);
		}
		else if(NoOfUsers>1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

		}
		Thread.sleep(2000);
		//ScrolltoElement(application, "DedicatedPortalCustomer");
		
		click(application, "Action dropdown", "UserActionDropdown");
		click(application, "Edit", "edit");
		Thread.sleep(2000);
		compareText(application, "Edit User Header", "edituser_header", "Edit User");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		cleartext(application, "User Name", "UserName");
		EnterTextValue(application, Username, "User Name", "UserName");
		cleartext(application, "First Name", "FirstName");
		EnterTextValue(application, Firstname, "First Name", "FirstName");
		cleartext(application, "SurName", "SurName");
		EnterTextValue(application, Surname, "SurName", "SurName");
		cleartext(application, "Postal Address", "PostalAddress");
		EnterTextValue(application, Postaladdress, "Postal Address", "PostalAddress");
		cleartext(application, "Email", "Email");
		EnterTextValue(application, Email, "Email", "Email");
		cleartext(application, "Phone", "Phone");
		EnterTextValue(application, Phone, "Phone", "Phone");
		cleartext(application, "Password", "Password");
		click(application, "Generate Password", "GeneratePassword");
		
		cleartext(application, "IP Gurdian Account Group", "UserIPGurdian");
		cleartext(application, "Colt online User", "UserColtnline");
		EnterTextValueCommon(application, "Test2", "IPGuardian Account Group", "TextValueCommon");
		EnterTextValueCommon(application, "Test1", "Colt Online User", "TextValueCommon");
		ScrolltoElementComm(application, "Colt Online User", "TextValueCommon");

		
		
		
		
		
		String[] UserRoleList=UserRoles.split(","); 
		String[] UserHideServiceList=UserHideService.split(","); 
		String[] UserHideRouterCisList=UserHideRouterCis.split(","); 
		String[] UserHideRouterHuaList=UserHideRouterHua.split(","); 
		String[] UserHideIPV6List=UserHideIPV6.split(",");
		
		
		String[] EditUserRoleList=EditUserRoles.split(","); 
		String[] EditUserHideServiceList=EditUserHideService.split(","); 
		String[] EditUserHideRouterCisList=EditUserHideRouterCis.split(","); 
		String[] EditUserHideRouterHuaList=EditUserHideRouterHua.split(","); 
		String[] EditUserHideIPV6List=EditUserHideIPV6.split(","); 
		
		
		for(int i=0;i<UserRoleList.length;i++) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditUserRoles").replace("Select",UserRoleList[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditUserRolestransferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserRoleList[i]+"  User Roles Edited");
			Thread.sleep(1000);	
		}
		Thread.sleep(5000);
		for(int i=0;i<EditUserRoleList.length;i++) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserRoles").replace("Select",EditUserRoleList[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserRolestransferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+EditUserRoleList[i]+"  User Roles Selected");
			Thread.sleep(1000);	
		}
		
		for(int i=0;i<UserHideServiceList.length;i++) {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditUserHideService").replace("Select",UserHideServiceList[i])));
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditUserHideServicetransferbutton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserHideServiceList[i]+"  User Roles Edited");
		Thread.sleep(1000);
		}
		Thread.sleep(5000);
		for(int i=0;i<EditUserHideServiceList.length;i++) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserHideService").replace("Select",EditUserHideServiceList[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserHideServicetransferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+EditUserHideServiceList[i]+"  User Roles Selected");
			Thread.sleep(1000);
			}
			
		for(int i=0;i<UserHideRouterCisList.length;i++) {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditUserIPV4CommandCis").replace("Select",UserHideRouterCisList[i])));
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditIPV4Cistransferbutton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserHideRouterCisList[i]+"  User Roles Edited");
		Thread.sleep(1000);
		}
		Thread.sleep(5000);
		for(int i=0;i<EditUserHideRouterCisList.length;i++) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserIPV4CommandCis").replace("Select",EditUserHideRouterCisList[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IPV4Cistransferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+EditUserHideRouterCisList[i]+"  User Roles Selected");
			Thread.sleep(1000);
			}
			Thread.sleep(3000);
		//scrolltoview(OKButton1);
		scrolltoend();
		for(int i=0;i<UserHideRouterHuaList.length;i++) {	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditUserIPV4CommandHua").replace("Select",UserHideRouterHuaList[i])));
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditIPV4Huatransferbutton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserHideRouterHuaList[i]+"  User Roles Edited");
		Thread.sleep(1000);
		}
		Thread.sleep(5000);
		for(int i=0;i<EditUserHideRouterHuaList.length;i++) {	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserIPV4CommandHua").replace("Select",EditUserHideRouterHuaList[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IPV4Huatransferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+EditUserHideRouterHuaList[i]+"  User Roles Selected");
			Thread.sleep(1000);
			}
			
		for(int i=0;i<UserHideIPV6List.length;i++) {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditUserIPV6CommandCis").replace("Select",UserHideIPV6List[i])));
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditIPV6transferbutton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+UserHideIPV6List[i]+"  User Roles Edited");
		Thread.sleep(1000);
		}
		Thread.sleep(5000);
		for(int i=0;i<EditUserHideIPV6List.length;i++) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserIPV6CommandCis").replace("Select",EditUserHideIPV6List[i])));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IPV6transferbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : "+EditUserHideIPV6List[i]+"  User Roles Selected");
			Thread.sleep(1000);
			}

		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		click(application, "OK", "OkButton");
		Thread.sleep(2000);
		compareText(application, "Header Message", "UserUpdateMessage", "User successfully updated");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User successfully Updated");

		
		//Delete User

			
	}
	
	public void Deletenewuser(String application, String Username)throws InterruptedException, DocumentException, IOException {
	
		ScrolltoElement(application, "DedicatedPortalCustomer");
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);
      if(NoOfUsers==1)
		{
			click(application, "User Radio button", "UserUnchecked");
			//Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			
			Thread.sleep(3000);
		}
		else if(NoOfUsers>1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");
		}
		Thread.sleep(2000);
		//ScrolltoElement(application, "DedicatedPortalCustomer");
		click(application, "Action dropdown", "UserActionDropdown");
		click(application, "Delete", "Userdelete");
		//click(application, "Delete", "deletebutton");
		Thread.sleep(2000);
		AcceptJavaScriptMethod();
		
		scrollToTop();
		Thread.sleep(2000);
		compareText(application, "Header Message", "UserDeleteMessage", "User successfully deleted");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User successfully Deleted");



	}


	public void verifyManagementOptionspanel(String application,String IPVPNSubType) throws InterruptedException, DocumentException, IOException {

		Boolean ManagementOptions_Header = getwebelement(xml.getlocator("//locators/" + application + "/managementoptions_header")).isDisplayed();
		Log.info("Management options header text is displayed as : " + ManagementOptions_Header);
		System.out.println("Management options header text:"+ ManagementOptions_Header);
		sa.assertTrue(ManagementOptions_Header,"Management Options");
		
			ScrolltoElement(application, "managementoptions_header");
		
		// verify Delivery Channel information
				String DeliveryChannel_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/DeliveryChannel_text")));
				GetText(application, DeliveryChannel_text, "DeliveryChannel_value");
			

		// verify Managed Service information
		String ManageService_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/ManageService_text")));
		GetText(application, ManageService_text, "ManageService_value");
		
		// verify Syslog Event View information
		String SyslogEventView_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SyslogEventView_text")));		
		GetText(application, SyslogEventView_text, "SyslogEventView_value");	
		
		// verify Service Status View information
		String ServiceStatusView_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/ServiceStatusView_text")));
		GetText(application, ServiceStatusView_text, "ServiceStatusView_value");

		// verify Router Configuration View information
		String RouterConfigurationViewIPV6_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/RouterConfigurationViewIPV6_text")));
		GetText(application, RouterConfigurationViewIPV6_text, "RouterConfigurationViewIPV6_Value");

		// verify Performance Reporting information
		//String PerformanceReporting_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PerformanceReporting_text")));
		//GetText(application, PerformanceReporting_text, "PerformanceReporting_value");

		// verify Proactive Notification information
		String ProactiveNotification_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/ProactiveNotification_text")));
		GetText(application, ProactiveNotification_text, "ProactiveNotification_Value");

		// verify Notification Management Team information
		//String NotificationManagementTeam_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/NotificationManagementTeam_text")));
		//GetText(application, NotificationManagementTeam_text, "ManageService_value");

		// verify DialUser Administration information
		String DialUserAdministration_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/DialUserAdministration_text")));
		GetText(application, DialUserAdministration_text, "DialUserAdministration_Value");

		if(IPVPNSubType.contains("IPVPN")) {

			// verify DialUser Administration information
			String ManagementOption_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/ManagementOption_text")));
			GetText(application, ManagementOption_text, "ManagementOption_value");
			String AllowSMCEmail_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/AllowSMCEmail_text")));
			GetText(application, AllowSMCEmail_text, "AllowSMCEmail_value");


		}
		
	}

	
	
	
	

	public void verifyResellerpanel(String application) throws InterruptedException, DocumentException, IOException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")));

		isDisplayed(application, "resellerheader", "Reseller Header");
		//		Boolean Reseller_Header = getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")).isDisplayed();
		//		Log.info("Reseller panel header text is displayed as : " + Reseller_Header);
		//		System.out.println("Reseller panel header text:"+ Reseller_Header);
		//		sa.assertTrue(Reseller_Header,"Reseller");

		click(application, "Action dropdown", "ResellerActionDropdown");

		if(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Add link is displaying under Reseller panel");
			Log.info("Add link is displayed");
		}
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Add link is not displaying under Reseller panel");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));

	}

	public static String ResellerName;
	public void AddReseller(String application, String ocn, String email, String city, String streetname, String streetno, String pobox, String zipcode, String phone, String fax) throws InterruptedException, DocumentException, IOException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")));

		isDisplayed(application, "resellerheader", "Reseller Header");
		//	Boolean Reseller_Header = getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")).isDisplayed();
		//	Log.info("Reseller panel header text is displayed as : " + Reseller_Header);
		//	System.out.println("Reseller panel header text:"+ Reseller_Header);
		//	sa.assertTrue(Reseller_Header,"Reseller");

		// verify customer name column
		compareText(application, "Customer Name", "reseller_customername_column", "Customer Name");
		// verify status column
		GetText(application, "Status", "statuscolumn");
		// verify Add link in Reseller action dropdown
		//Cancel add reseller
		click(application, "Action dropdown", "ResellerActionDropdown");
		click(application, "Add", "AddLink");
		isDisplayed(application, "manageresellerheader", "Manage Reseller in OSP");
		click(application, "Cancel", "cancelbutton");
		//				
		//		Boolean ManageReseller_Header = getwebelement(xml.getlocator("//locators/" + application + "/manageresellerheader")).isDisplayed();
		//		Log.info("Manage Reseller in OSP header text is displayed as : " + ManageReseller_Header);
		//		System.out.println("Manage Reseller in OSP header text:"+ ManageReseller_Header);
		//		sa.assertTrue(ManageReseller_Header,"Manage Reseller in OSP");
		//		Thread.sleep(1000);


		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}

		//warning msg verfiy in reseller panel
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")));
		click(application, "Action dropdown", "ResellerActionDropdown");
		click(application, "Add", "AddLink");
		isDisplayed(application, "manageresellerheader", "Manage Reseller in OSP");
		click(application, "Next", "nextbutton");
		WarningMessage(application, "reselleremail_warngmsg", "Email");
		WarningMessage(application, "resellercity_warngmsg", "City");
		WarningMessage(application, "resellerstreetname_warngmsg", "Street Name");
		WarningMessage(application, "resellerstreetno_warngmsg", "Street Number");
		WarningMessage(application, "resellerpobox_warngmsg", "PO Box");
		WarningMessage(application, "resellerzipcode_warngmsg", "Zip Code");
		WarningMessage(application, "resellerphone_warngmsg", "Phone");

		//Add reseller
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/manageresellerheader")));
		Thread.sleep(1000);
		String resellername= getwebelement(xml.getlocator("//locators/" + application + "/resellername")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Reseller Name is displaying as: '"+resellername+"'");
		EnterTextValue(application, email, "Email", "reseller_email");	
		EnterTextValue(application, city, "City", "reseller_city");
		EnterTextValue(application, streetname, "Street Name", "reseller_streetname");
		EnterTextValue(application, streetno, "Street Number", "reseller_streetno");
		EnterTextValue(application, pobox, "PO Box", "reseller_pobox");
		EnterTextValue(application, zipcode, "Zip Code", "reseller_zipcode");
		EnterTextValue(application, phone, "Phone", "reseller_phone");
		EnterTextValue(application, fax, "Fax", "reseller_fax");
		click(application, "Next", "nextbutton");

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellercreated_successmsg")));
			compareText(application, "Reseller created success message", "resellercreated_successmsg", "Manage Reseller successfully created");
		}
		else
			Log.info("Reseller not created");
		System.out.println("Reseller not created");

		//Added Reseller
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")));
		WebElement ResellerGridCheck= driver.findElement(By.xpath("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String ResellerGrid= ResellerGridCheck.getAttribute("style");

		WebElement AddedReseller= driver.findElement(By.xpath("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + ocn + "')]/parent::div//input[@type='radio']"));
		if(!ResellerGrid.contains("height: 1px"))
		{
			Clickon(AddedReseller);
			Thread.sleep(1000);

			click(application, "Action dropdown", "ResellerActionDropdown");

			List<WebElement> ResellerLinks= driver.findElements(By.xpath("//div[contains(text(),'Reseller')]/following-sibling::div//div//a"));
			int ResellerLinksCount= ResellerLinks.size();
			for(int i=0;i<ResellerLinksCount;i++)
			{
				String Link= ResellerLinks.get(i).getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, ""+Link+" link is displaying under reseller panel");
				System.out.println("Reseller link:"+ Link + "is displaying");
				Thread.sleep(2000);
			}

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
			//Added Reseller grid verification

			compareText(application, "Added Reseller Name", "Addedreseller_columnvalue", ocn);

		}

		else
			Log.info("Reseller is not added in the grid");
		System.out.println("Reseller is not added in the grid");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Reseller is not added in the grid");
		ResellerName= ocn;		
	}



	public void verifyResellerLinks(String application, String ocn, String editemail, String editcity, String editstreetname, String editstreetno, String editpobox, String editzipcode, String editphone, String editfax) throws InterruptedException, DocumentException, IOException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")));
		WebElement ResellerGridCheck= driver.findElement(By.xpath("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String ResellerGrid= ResellerGridCheck.getAttribute("style");

		WebElement AddedReseller= driver.findElement(By.xpath("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ ocn +"')]/parent::div//input[@type='radio']"));

		//Edit Reseller
		if(!ResellerGrid.contains("height: 1px"))
		{
			Clickon(AddedReseller);
			Thread.sleep(1000);

			click(application, "Action dropdown", "ResellerActionDropdown");
			click(application, "Edit", "edit");
			compareText(application, "Manage Reseller Header", "manageresellerheader", "Manage Reseller in OSP");
			cleartext(application, "Email", "reseller_email");
			EnterTextValue(application, editemail, "Email", "reseller_email");
			cleartext(application, "City", "reseller_city");
			EnterTextValue(application, editcity, "City", "reseller_city");
			cleartext(application, "Street Name", "reseller_streetname");
			EnterTextValue(application, editstreetname, "Street Name", "reseller_streetname");
			cleartext(application, "Street Number", "reseller_streetno");
			EnterTextValue(application, editstreetno, "Street Number", "reseller_streetno");
			cleartext(application, "PO Box", "reseller_pobox");
			EnterTextValue(application, editpobox, "PO Box", "reseller_pobox");
			cleartext(application, "Zip Code", "reseller_zipcode");
			EnterTextValue(application, editzipcode, "Zip Code", "reseller_zipcode");
			cleartext(application, "Phone", "reseller_phone");
			EnterTextValue(application, editphone, "Phone", "reseller_phone");
			cleartext(application, "Fax", "reseller_fax");
			EnterTextValue(application, editfax, "Fax", "reseller_fax");
			click(application, "OK", "okbutton");

			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
				System.out.println("Navigated to view service page");
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/editreseller_successmsg")));
				compareText(application, "Edit Reseller success message", "editreseller_successmsg", "Reseller already in OSP. Successfully updated.");
			}
			else
			{
				Log.info("Reseller not updated");
				System.out.println("Reseller not updated");
			}
		}
		else
		{
			Log.info("Reseller is not added in the grid");
			System.out.println("Reseller is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Reseller is not added in the grid");
		}

		//View reseller
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")));
		//WebElement ResellerGridCheck= driver.findElement(By.xpath("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		//String ResellerGrid= ResellerGridCheck.getAttribute("style");
		WebElement AddedReseller1= driver.findElement(By.xpath("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ ocn +"')]/parent::div//input[@type='radio']"));
		if(!ResellerGrid.contains("height: 1px"))
		{
			Clickon(AddedReseller1);
			Thread.sleep(1000);
			click(application, "Action dropdown", "ResellerActionDropdown");

			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				click(application, "View", "view");
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
				compareText(application, "Manage Reseller Header", "manageresellerheader_viewpage", "Manage Reseller in OSP");
				compareText(application, "Reseller Name", "resellernamevalue", ocn);
				compareText(application, "Email", "reselleremailvalue", editemail);
				compareText(application, "City", "resellercityvalue", editcity);
				compareText(application, "Street Name", "resellerstreetnamevalue", editstreetname);
				compareText(application, "Street Number", "resellerstreetnovalue", editstreetno);
				compareText(application, "PO Box", "resellerpoboxvalue", editpobox);
				compareText(application, "Zipcode", "resellerzipcodevalue", editzipcode);
				compareText(application, "Phone", "resellerphonevalue", editphone);
				compareText(application, "Fax", "resellerfaxvalue", editfax);
				GetText(application, "Web Access Authorized", "resellerwebaccessvalue");
				Thread.sleep(1000);

				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
				Thread.sleep(2000);
				click(application, "View page Action dropdown", "viewpage_actiondropdown");

				//Edit link in view reseller page
				click(application, "View page Edit", "edit");
				compareText(application, "Manage Reseller Header", "manageresellerheader", "Manage Reseller in OSP");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Edit Reseller page is displaying as expected");
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
				click(application, "Cancel", "cancelbutton");

				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
				}
			}
			else
			{
				Log.info("Reseller is not added in the grid");
				System.out.println("Reseller is not added in the grid");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Reseller is not added in the grid");
			}
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")));
			WebElement AddedReseller2= driver.findElement(By.xpath("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ ocn +"')]/parent::div//input[@type='radio']"));
			if(!ResellerGrid.contains("height: 1px"))
			{
				Clickon(AddedReseller2);
				Thread.sleep(1000);

				click(application, "Action dropdown", "ResellerActionDropdown");

				if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
				{
					click(application, "View", "view");
					compareText(application, "Manage Reseller Header", "manageresellerheader_viewpage", "Manage Reseller in OSP");
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
					click(application, "View page Action dropdown", "viewpage_actiondropdown");

					//Delete link in view reseller page
					click(application, "View page Delete", "delete");

					if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete Reseller alert is displaying as expected");
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletealertclose")));
						Thread.sleep(2000);
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on delete alert popup close button");
					}
					else
						Log.info("Delete alert popup is not displayed");
				}

				//Associate Reseller with NGIN Objects link in view reseller page
				click(application, "View page Action dropdown", "viewpage_actiondropdown");
				click(application, "Associate Reseller with NGIN Objects", "associateresellerlink");
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Associate Reseller with NGIN Objects link verified");
				}

			}
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : View link is not displaying under Reseller panel");
		}
		else
		{
			Log.info("Reseller is not added in the grid");
			System.out.println("Reseller is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Reseller is not added in the grid");
		}
	}

	public void verifyCustomerpanel(String application) throws InterruptedException, DocumentException, IOException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
		compareText(application, "Customer panel header", "customerheader", "Customer");
		click(application, "Action dropdown", "CustomerpanelActionDropdown");

		if(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Add link is displaying under Customer panel");
			Log.info("Add link is displayed");
		}
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Add link is not displaying under Customer panel");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));

	}

	String Customername=null;
	public void AddCustomer(String application, String resellername, String defaultvalue, String configure, String country, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode) throws InterruptedException, DocumentException, IOException {


		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
		compareText(application, "Customer panel header", "customerheader", "Customer");

		// verify customer name column
		compareText(application, "Customer Name column", "customerpanel_customernamecolumntext", "Customer Name");

		// verify Add link in customer action dropdown

		//Cancel add customer
		click(application, "Action dropdown", "CustomerpanelActionDropdown");
		click(application, "Add", "AddLink");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		click(application, "Cancel", "cancelbutton");
		Thread.sleep(2000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}

		//Add Customer

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
		click(application, "Action dropdown", "CustomerpanelActionDropdown");
		click(application, "Add", "AddLink");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP");
		Thread.sleep(1000);

		//Select Country from dropdown
		addDropdownValues(application, "Country", "customer_country", country);
		String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");

		//Select Customer Name from dropdown
		/*	try {	

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/customerpanel_customername")));
			//	Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/customerpanel_customername")), selectcustomer);
				Thread.sleep(2000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step :selected customer is : " + selectcustomer);

				WebElement el1 = driver
						.findElement(By.xpath("//span[contains(text(),'" + selectcustomer + "')][1]"));
				el1.click();
				Log.info("=== customer selected===");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Customer name has been selected from the dropdown");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Select Customer name dropdown is not available");
			} */

		String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
		String CustomerName_viewpage= CustomerNamevalue.replace("(New)", "").trim();
		System.out.println("Customer name is :"+CustomerName_viewpage);
		EnterTextValue(application, resellername, "Reseller Name", "resellername");
		GetText(application, "Email", "reseller_email");
		EnterTextValue(application, city, "City", "reseller_city");
		EnterTextValue(application, streetname, "Street Name", "reseller_streetname");
		EnterTextValue(application, streetno, "Street Number", "reseller_streetno");
		EnterTextValue(application, pobox, "PO Box", "reseller_pobox");
		EnterTextValue(application, zipcode, "Zip Code", "reseller_zipcode");
		GetText(application, "Phone", "reseller_phone");
		GetText(application, "Fax", "reseller_fax");

		if(defaultvalue.equalsIgnoreCase("YES"))
		{
			String Default_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/defaultcheckbox")).getAttribute("checked");
			if(Default_Checkbox.isEmpty())
			{
				System.out.println("Default checkbox is checked");
				Thread.sleep(1000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Default checkbox is checked");
				Log.info("Default checkbox is checked");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Default checkbox is not checked by default");
		}

		else if(configure.equalsIgnoreCase("YES"))
		{
			String Configure_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/configurecheckbox")).getAttribute("checked");
			if(Configure_Checkbox.isEmpty())
			{
				System.out.println("Configure checkbox is checked");
				Thread.sleep(1000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Configure checkbox is checked");
				Log.info("Configure checkbox is checked");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Configure checkbox is not checked");
				click(application, "Configure checkbox", "configurecheckbox");
			}
		}
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		click(application, "Next", "nextbutton");
		Thread.sleep(3000);	

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			compareText(application, "Customer created success message", "addcustomer_successmsg", "Manage Customer successfully created");
		}
		else
		{
			Log.info("Customer not created");
			System.out.println("Customer not created");
		}

		//Added Customer
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
		WebElement CustomerGridCheck= driver.findElement(By.xpath("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String CustomerGrid= CustomerGridCheck.getAttribute("style");

		WebElement AddedCustomer= driver.findElement(By.xpath("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + CustomerName_viewpage + "')]/parent::div//input[@type='radio']"));
		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer);
			Thread.sleep(1000);
			click(application, "Action dropdown", "CustomerpanelActionDropdown");

			List<WebElement> CustomerLinks= driver.findElements(By.xpath("//div[contains(text(),'Customer')]/following-sibling::div//div//a"));
			int CustomerLinksCount= CustomerLinks.size();
			for(int i=0;i<CustomerLinksCount;i++)
			{
				String Link= CustomerLinks.get(i).getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, ""+Link+" link is displaying under customer panel");
				System.out.println("Customer link:"+ Link + " is displaying");
				Thread.sleep(2000);
			}
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));

			//Added Customer grid verification
			WebElement AddedCustomer1= driver.findElement(By.xpath("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + CustomerName_viewpage + "')]"));
			String AddedCustomerName_value = AddedCustomer1.getText();
			Log.info("Added Customer Name is displayed as : " + AddedCustomerName_value);
			System.out.println("Added Customer Name:"+ AddedCustomerName_value);
			sa.assertEquals(AddedCustomerName_value,CustomerName_viewpage);
		}
		else
		{
			Log.info("Customer is not added in the grid");
			System.out.println("Customer is not added in the grid");
		}

		Customername= CustomerName_viewpage;
	}


	public void verifyCustomerLinks(String application, String editreseller, String editemail, String editcity, String editstreetname, String editstreetno, String editpobox, String editzipcode, String editphone, String editfax) throws InterruptedException, DocumentException, IOException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
		WebElement CustomerGridCheck= driver.findElement(By.xpath("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String CustomerGrid= CustomerGridCheck.getAttribute("style");

		//WebElement AddedCustomer= driver.findElement(By.xpath("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + CustomerName + "')]/parent::div//input[@type='radio']"));
		WebElement AddedCustomer= driver.findElement(By.xpath("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ Customername+"')]/parent::div//input[@type='radio']"));

		//Edit Customer
		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer);
			Thread.sleep(1000);
			click(application, "Action dropdown", "CustomerpanelActionDropdown");
			click(application, "Edit", "edit");
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
			compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP");
			Thread.sleep(1000);

			String CountryDisabled= getwebelement(xml.getlocator("//locators/" + application + "/customercountry_disabled")).getAttribute("disabled");
			if(CountryDisabled!=null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Country dropdown is disabled as expected");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Country dropdown is enabled");
			}
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");

			String CustomernameDisabled= getwebelement(xml.getlocator("//locators/" + application + "/customername_disabled")).getAttribute("disabled");
			if(CustomernameDisabled!=null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer name dropdown is disabled as expected");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Customer name dropdown is enabled");
			}
			EnterTextValue(application, editreseller, "Reseller name", "resellername");
			cleartext(application, "Email", "reseller_email");
			EnterTextValue(application, editemail, "Email", "reseller_email");
			cleartext(application, "City", "reseller_city");
			EnterTextValue(application, editcity, "City", "reseller_city");
			cleartext(application, "Street Name", "reseller_streetname");
			EnterTextValue(application, editstreetname, "Street Name", "reseller_streetname");
			cleartext(application, "Street Number", "reseller_streetno");
			EnterTextValue(application, editstreetno, "Street Number", "reseller_streetno");
			cleartext(application, "PO Box", "reseller_pobox");
			EnterTextValue(application, editpobox, "PO Box", "reseller_pobox");
			cleartext(application, "Zip Code", "reseller_zipcode");
			EnterTextValue(application, editzipcode, "Zip Code", "reseller_zipcode");
			cleartext(application, "Phone", "reseller_phone");
			EnterTextValue(application, editphone, "Phone", "reseller_phone");
			cleartext(application, "Fax", "reseller_fax");
			EnterTextValue(application, editfax, "Fax", "reseller_fax");
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			click(application, "Ok", "okbutton");

			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
				System.out.println("Navigated to view service page");
				compareText(application, "Customer updated success message", "editcustomer_successmsg", "Manage Customer successfully updated");
			}
			else
			{
				Log.info("Customer not updated");
				System.out.println("Customer not updated");
			}

		}

		//View customer
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
		//WebElement AddedCustomer1= driver.findElement(By.xpath("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + CustomerName + "')]/parent::div//input[@type='radio']"));
		WebElement AddedCustomer1= driver.findElement(By.xpath("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ Customername +"')]/parent::div//input[@type='radio']"));

		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer1);
			Thread.sleep(1000);
			click(application, "Action dropdown", "CustomerpanelActionDropdown");

			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				click(application, "View", "view");
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
				compareText(application, "Manage Customer in OSP header", "manageresellerheader_viewpage", "Manage Customer In OSP");
				Thread.sleep(1000);

				GetText(application, "Country", "customer_countryvalue");
				GetText(application, "Customer Name", "customer_customernamevalue");
				GetText(application, "Reseller Name", "resellernamevalue");
				GetText(application, "Email", "reselleremailvalue");
				GetText(application, "City", "resellercityvalue");
				GetText(application, "Street Name", "resellerstreetnamevalue");
				GetText(application, "Street Number", "resellerstreetnovalue");
				GetText(application, "PO Box", "resellerpoboxvalue");
				GetText(application, "Zip Code", "resellerzipcodevalue");
				GetText(application, "Phone", "resellerphonevalue");
				GetText(application, "Fax", "resellerfaxvalue");
				Thread.sleep(1000);

				//Edit customer in view customer page
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
				click(application, "Action dropdown", "viewpage_actiondropdown");
				click(application, "Edit", "viewpage_editcustomer");
				compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Edit Customer in view customer page is verified");
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
				click(application, "Cancel", "cancelbutton");
				Thread.sleep(1000);
			}
		}
		//Delete customer in view customer page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
		//WebElement AddedCustomer2= driver.findElement(By.xpath("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + CustomerName + "')]/parent::div//input[@type='radio']"));
		WebElement AddedCustomer2= driver.findElement(By.xpath("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ Customername +"')]/parent::div//input[@type='radio']"));

		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer2);
			Thread.sleep(1000);
			click(application, "Action dropdown", "CustomerpanelActionDropdown");

			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				click(application, "View", "view");
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
				compareText(application, "Manage Customer in OSP header", "manageresellerheader_viewpage", "Manage Customer In OSP");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete Customer in view customer page is verified");
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
				click(application, "Action dropdown", "viewpage_actiondropdown");

				//Delete customer in view customer page
				click(application, "Delete", "viewpage_deletecustomer");

				if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
				{
					click(application, "Delete Alert close", "deletealertclose");
				}
				else
					Log.info("Delete alert popup is not displayed");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : View link is not displaying under customer panel");
			}
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			click(application, "Back", "viewpage_backbutton");

		}


	}

	public void verifySANpanel(String application) throws InterruptedException, DocumentException, IOException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		click(application, "Action dropdown", "SANActionDropdown");		

		if(getwebelement(xml.getlocator("//locators/" + application + "/addsan_link")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Add SAN link is displaying under SAN/FRC panel");
			Log.info("Add link is displayed");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Add link is not displaying under SAN/FRC panel");
		}
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/sanheader")));

	}


	public void AddSAN(String application, String country, String sannumber, String maxcallduration, String chargebandname, String predestinationnumber, String ringtonumber, String routingforpayphone_value, String routingformobile_value, String defaultrouting_value, String RingToNumber_Checkbox, String AnnouncementToPlay_Checkbox, String ComplexRouting_Checkbox, String defaultroutebusy_value, String noanswer_value, String networkcongestion, String resellername, String defaultvalue, String configure, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode, String serviceprofilevalue) throws InterruptedException, DocumentException, IOException, InvalidFormatException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");

		//verify FRC number column
		compareText(application, "FRC Number column", "frcnumber_column", "FRC Number");
		// verify customer name column
		compareText(application, "Customer name column", "san_customername", "Customer Name");

		// verify Add SAN link in SAN/FRC action dropdown

		//Cancel add SAN
		click(application, "Action dropdown", "SANActionDropdown");	
		click(application, "Add SAN", "addsan_link");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		compareText(application, "Add SAN header", "addsan_header", "Add SAN");	
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		click(application, "Cancel", "cancelbutton");
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}

		//Add SAN
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		click(application, "Action dropdown", "SANActionDropdown");	
		click(application, "Add SAN", "addsan_link");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		compareText(application, "Add SAN header", "addsan_header", "Add SAN");

		addDropdownValues(application, "Country", "customer_country", country);
		click(application, "Customer Name dropdown", "customername");
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_valuesdisplay")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Existing customer is available");
			click(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay");
		}
		else if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_nomatchesfound")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Existing customer is not available");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")));

			//Manage new customer link
			click(application, "Manage new customer", "managenewcustomer_link");
			compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP");
			Thread.sleep(1000);

			//=========================

			//Add Customer
			//Select Country from dropdown
			addDropdownValues(application, "Country", "customer_country", country);
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
			String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
			CustomerNamevalue.replace("(New)", "").trim();
			EnterTextValue(application, resellername, "Reseller Name", "resellername");
			GetText(application, "Email", "reseller_email");
			EnterTextValue(application, city, "City", "reseller_city");
			EnterTextValue(application, streetname, "Street Name", "reseller_streetname");
			EnterTextValue(application, streetno, "Street Number", "reseller_streetno");
			EnterTextValue(application, pobox, "PO Box", "reseller_pobox");
			EnterTextValue(application, zipcode, "Zip Code", "reseller_zipcode");
			GetText(application, "Phone", "reseller_phone");
			GetText(application, "Fax", "reseller_fax");

			if(defaultvalue.equalsIgnoreCase("YES"))
			{
				String Default_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/defaultcheckbox")).getAttribute("checked");
				if(Default_Checkbox.isEmpty())
				{
					System.out.println("Default checkbox is checked");
					Thread.sleep(1000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Default checkbox is checked");
					Log.info("Default checkbox is checked");
				}
				else
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Default checkbox is not checked by default");
			}

			else if(configure.equalsIgnoreCase("YES"))
			{
				String Configure_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/configurecheckbox")).getAttribute("checked");
				if(Configure_Checkbox.isEmpty())
				{
					System.out.println("Configure checkbox is checked");
					Thread.sleep(1000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Configure checkbox is checked");
					Log.info("Configure checkbox is checked");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Configure checkbox is not checked");
					click(application, "Configure checkbox", "configurecheckbox");
				}
			}
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			click(application, "Next", "nextbutton");
			Thread.sleep(3000);	

			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")));
			compareText(application, "Add SAN header", "addsan_header", "Add SAN");
			Thread.sleep(1000);

			//Select Country from dropdown
			addDropdownValues(application, "Country", "customer_country", country);
			click(application, "Customer Name dropdown", "customername");
			Thread.sleep(1000);
			click(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay");
		}

		Thread.sleep(1000);
		//SAN customer name
		String SAN_Customernamevalue= getwebelement(xml.getlocator("//locators/" + application + "/customername_selectedtext")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step :Customer name selected is : " + SAN_Customernamevalue);
		String SAN_Customername=SAN_Customernamevalue.substring(0, SAN_Customernamevalue.indexOf("(")).trim();
		writetoexcel("src\\com\\colt\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 106, SAN_Customername);

		String SANNumber_CountryCode=getwebelement(xml.getlocator("//locators/" + application + "/san_number")).getAttribute("value");
		EnterTextValue(application, sannumber, "SAN Number", "san_number");
		//SAN number
		String SANNumberValue= SANNumber_CountryCode+sannumber;
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step :SAN Number is dsplayed as : " + SANNumberValue);
		writetoexcel("src\\com\\colt\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 107, SANNumberValue);

		//Select service profile from dropdown
		addDropdownValues(application, "Service Profile", "serviceprofile", serviceprofilevalue);
		Thread.sleep(1000);

		if(RingToNumber_Checkbox.equalsIgnoreCase("YES"))
		{
			click(application, "'Ring To Number' radio", "ringtonumber_radiobutton");
			Thread.sleep(1000);
			EnterTextValue(application, ringtonumber, "Ring To Number", "ringtonumber_field");
		}
		else if(AnnouncementToPlay_Checkbox.equalsIgnoreCase("YES"))
		{
			click(application, "'Announcement to play' radio", "announcementtoplay_radiobutton");
			Thread.sleep(1000);
			//Select announcement to play value from dropdown
			click(application, "Announcement To Play dropdown", "announcementtoplay_dropdown");
			click(application, "Announcement To Play value", "announcementtoplay_dropdownvalue");
		}
		else if(ComplexRouting_Checkbox.equalsIgnoreCase("YES"))
		{
			click(application, "'Complex route' radio", "complexroute_radiobutton");
			Thread.sleep(1000);
			EnterTextValue(application, routingforpayphone_value, "Routing for payphone", "routingforpayphone_field");
			EnterTextValue(application, routingformobile_value, "Routing for mobile", "routingformobile");
			EnterTextValue(application, defaultrouting_value, "Default Routing", "defaultrouting");
			click(application, "Enable logical routing checkbox", "enablelogicalrouting");
			EnterTextValue(application, defaultroutebusy_value, "Default Route busy", "defaultroutebusy");
			EnterTextValue(application, noanswer_value, "No Answer", "noanswer");
			EnterTextValue(application, networkcongestion, "Network Congestion", "networkcongestion");
		}
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		click(application, "Next", "nextbutton");

		if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
		{
			Log.info("Navigated to view SAN page");
			System.out.println("Navigated to view SAN page");
			compareText(application, "Add SAN success message", "addsan_successmsg", "SAN successfully created");
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			click(application, "Back", "viewpage_backbutton");
		}
		else
		{
			Log.info("SAN not created");
			System.out.println("SAN not created");
		}

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}
		else
		{
			Log.info("Not navigated to view service page");
			System.out.println("Not navigated to view service page");
		}
		//Added Customer
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		WebElement SANGridCheck= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String SANGrid= SANGridCheck.getAttribute("style");

		WebElement AddedSAN= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + SAN_Customername + "')]/parent::div//input[@type='radio']"));
		if(!SANGrid.contains("height: 1px"))
		{
			Clickon(AddedSAN);
			Thread.sleep(1000);

			click(application, "Action dropdown", "SANActionDropdown");

			List<WebElement> SANLinks= driver.findElements(By.xpath("//div[contains(text(),'SAN/FRC')]/following-sibling::div//div//a"));
			int SANLinksCount= SANLinks.size();
			for(int i=0;i<SANLinksCount;i++)
			{
				String Link= SANLinks.get(i).getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, ""+Link+" link is displaying under SAN/FRC panel");
				System.out.println("Customer link:"+ Link + " is displaying");
				Thread.sleep(2000);
			}
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/sanheader")));

			//Added Customer grid verification

			WebElement AddedSan= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + SANNumberValue + "')]"));
			String AddedSAN_FRCNumber = AddedSan.getText();
			Log.info("FRC Number for Added SAN is displayed as : " + AddedSAN_FRCNumber);
			System.out.println("FRC Number for Added SAN: "+ AddedSAN_FRCNumber);
			sa.assertEquals(AddedSAN_FRCNumber,SANNumberValue);
			ExtentTestManager.getTest().log(LogStatus.PASS, "FRC Number for Added SAN is displayed as : " + AddedSAN_FRCNumber);

			WebElement AddedSan1= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + SAN_Customername + "')]"));
			String AddedSAN_Customervalue = AddedSan1.getText();
			Log.info("Customer Name for Added SAN is displayed as : " + AddedSAN_Customervalue);
			System.out.println("Customer Name for Added SAN: "+ AddedSAN_Customervalue);
			sa.assertEquals(AddedSAN_Customervalue,SAN_Customername);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Customer Name for Added SAN is displayed as : " + AddedSAN_Customervalue);
		}

		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}

	}


	public void verifySANLinks(String application, String customernamevalue, String sannumbervalue, String portinnumber, String portoutnumber, String serviceprofilevalue, String maxcallduration, String chargebandname, String predestinationnumber, String ringtonumber, String announcementtoplay_value, String routingforpayphone_value, String routingformobile_value, String defaultrouting_value, String RingToNumber_Checkbox, String AnnouncementToPlay_Checkbox, String ComplexRouting_Checkbox, String defaultroutebusy_value, String noanswer_value, String networkcongestion, String destinationcustomername, String sanmove_orderno, String bulkmove_country, String bulkmove_customer, String filterfrcnumber, String bulkmove_service, String priceannouncementvalue, String priceannoriginvalue, String internationaloutgoingcalls_checkedvalue, String internationalincomingcalls_checkedvalue, String mobilecallsallowed_checkedvalue, String payphoneblocking_checkedvalue, String supervisionfieldvalue, String noreplytimervalue, String webaccessblockedvalue, String cpsfreeformatvalue, String sanblockenabled_checkedvalue, String focenabled_checkedvalue, String enablepriceannouncement_checkedvalue, String select_sansearchtype) throws InterruptedException, DocumentException, IOException {
		
		//Edit SAN link in View SAN page
//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
//		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
//		//SANFilter
//		click(application, "Search San Menu", "sanpanel_searchsanmenu");
//		click(application, "Select SAN Search type", "select_sansearchtype");
//		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
//		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
//		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
//		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();
//		
//		WebElement SANGridCheck= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
//		String SANGrid= SANGridCheck.getAttribute("style");
//		WebElement AddedSAN= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + customernamevalue + "')]/parent::div//input[@type='radio']"));
//		
//		if(!SANGrid.contains("height: 1px"))
//		{
//			Clickon(AddedSAN);
//			Thread.sleep(1000);
//			click(application, "SAN Action dropdown", "SANActionDropdown");
//			click(application, "Edit SAN", "editsan_link");
//			Thread.sleep(2000);
//			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
//			compareText(application, "Edit SAN Header", "editsan_header", "Edit SAN");
//			
//			//verify Customer Name field
//			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_customername")).getAttribute("readonly")!=null)
//			{
//				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name field is disabled as expected");
//				String CustomerNamevalue= getwebelement(xml.getlocator("//locators/" + application + "/editsan_customername")).getAttribute("value");
//				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name field value is displayed as:"+ CustomerNamevalue);
//			}
//			else
//			{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Customer Name field is enabled");
//			}
//			
//			//verify SAN Number field
//			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_sannumber")).getAttribute("readonly")!=null)
//			{
//				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Number field is disabled as expected");
//				String SANNumbervalue= getwebelement(xml.getlocator("//locators/" + application + "/editsan_sannumber")).getAttribute("value");
//				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Number field value is displayed as:"+ SANNumbervalue);
//			}
//			else
//			{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : SAN Number field is enabled");
//			}
//			
//			//Select service profile from dropdown
//			addDropdownValues(application, "Service Profile", "serviceprofile", serviceprofilevalue);
//			Thread.sleep(1000);
//			String Supervisionfield= GetText(application, "Supervision mode", "supervisionfield");
//			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Supervision field value is displayed as:"+ Supervisionfield);
//			
//			if(internationaloutgoingcalls_checkedvalue.equalsIgnoreCase("Yes"))
//			{
//				click(application, "International Outgoing Calls Forbidden checkbox", "internationaloutgoingforbidden_checkbox");
//				Thread.sleep(1000);
//			}
//			if(internationalincomingcalls_checkedvalue.equalsIgnoreCase("Yes"))
//			{
//				click(application, "International Incoming Calls Barring checkbox", "internationalincomingbarring");
//				Thread.sleep(1000);
//			}
//			if(mobilecallsallowed_checkedvalue.equalsIgnoreCase("Yes"))
//			{
//				click(application, "Mobile Calls Allowed checkbox", "mobilecallsallowed_checkbox");	
//				Thread.sleep(1000);
//			}
//			cleartext(application, "No reply timer value", "noreplytimervalue");
//			EnterTextValue(application, noreplytimervalue, "No reply timer value", "noreplytimervalue");
//			cleartext(application, "Max call duration", "maxcallduration");
//			EnterTextValue(application, maxcallduration, "Max call duration", "maxcallduration");
//			//Select charge band name from dropdown
//			addDropdownValues(application, "Charge Band Name", "chargebandname", chargebandname);
//			
//			if(payphoneblocking_checkedvalue.equalsIgnoreCase("Yes"))
//			{
//				click(application, "Pay phone blocking enabled checkbox", "payphoneblockingenabled");
//				Thread.sleep(1000);
//			}
//			if(webaccessblockedvalue.equalsIgnoreCase("Yes"))
//			{
//				click(application, "Web Access Blocked checkbox", "webaccessblocked");
//				Thread.sleep(1000);
//			}
//			ScrolltoElement(application, "sanblock");
//			
//			if(sanblockenabled_checkedvalue.equalsIgnoreCase("Yes"))
//			{
//				click(application, "Sanblock checkbox", "sanblock");
//				Thread.sleep(1000);
//			}
//			if(focenabled_checkedvalue.equalsIgnoreCase("Yes"))
//			{
//				click(application, "Foc enabled checkbox", "focenabled");
//				Thread.sleep(1000);
//			}
//			//cleartext(application, "Pre destination number", "predestinationnumber");
//			//EnterTextValue(application, predestinationnumber, "Pre destination number", "predestinationnumber");
//			String CPSFreeFormatValue= getwebelement(xml.getlocator("//locators/" + application + "/cpsfreeformat")).getAttribute("value");
//			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : CPS Free Format field value is displayed as:"+ CPSFreeFormatValue);
//			if(RingToNumber_Checkbox.equalsIgnoreCase("YES"))
//			{
//				click(application, "'Ring To Number' radio", "ringtonumber_radiobutton");
//				Thread.sleep(1000);
//				cleartext(application, "Ring To Number", "ringtonumber_field");
//				EnterTextValue(application, ringtonumber, "Ring To Number", "ringtonumber_field");
//			}
//			else if(AnnouncementToPlay_Checkbox.equalsIgnoreCase("YES"))
//			{
//				click(application, "'Announcement to play' radio", "announcementtoplay_radiobutton");
//				Thread.sleep(1000);
//				//Select announcement to play value from dropdown
//				click(application, "Announcement To Play dropdown", "announcementtoplay_dropdown");
//				click(application, "Announcement To Play value", "announcementtoplay_dropdownvalue");
//			}
//			else if(ComplexRouting_Checkbox.equalsIgnoreCase("YES"))
//			{
//				click(application, "'Complex route' radio", "complexroute_radiobutton");
//				Thread.sleep(1000);
//				cleartext(application, "Routing for payphone", "routingforpayphone_field");
//				EnterTextValue(application, routingforpayphone_value, "Routing for payphone", "routingforpayphone_field");
//				cleartext(application, "Routing for mobile", "routingformobile");
//				EnterTextValue(application, routingformobile_value, "Routing for mobile", "routingformobile");
//				cleartext(application, "Default Routing", "defaultrouting");
//				EnterTextValue(application, defaultrouting_value, "Default Routing", "defaultrouting");
//				click(application, "Enable logical routing checkbox", "enablelogicalrouting");
//				cleartext(application, "Default Route busy", "defaultroutebusy");
//				EnterTextValue(application, defaultroutebusy_value, "Default Route busy", "defaultroutebusy");
//				cleartext(application, "No Answer", "noanswer");
//				EnterTextValue(application, noanswer_value, "No Answer", "noanswer");
//				cleartext(application, "Network Congestion", "networkcongestion");
//				EnterTextValue(application, networkcongestion, "Network Congestion", "networkcongestion");
//			}
//			
//			if(enablepriceannouncement_checkedvalue.equalsIgnoreCase("Yes"))
//			{
//				click(application, "Enable price announcement checkbox", "enablepriceannouncement");
//				Thread.sleep(1000);
//			}
//			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//			Thread.sleep(1000);
//			click(application, "Next", "nextbutton");
//			Thread.sleep(2000);
//			
//			if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
//			{
//				Log.info("Navigated to view SAN page");
//				System.out.println("Navigated to view SAN page");
//				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/editsan_successmsg")));
//				compareText(application, "San Updated Success message", "editsan_successmsg", "San successfully updated");
//				Thread.sleep(3000);
//				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//				click(application, "Back", "viewpage_backbutton");
//				Thread.sleep(2000);
//				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
//				{
//					Log.info("Navigated to view service page");
//					System.out.println("Navigated to view service page");
//				}
//				else
//				{
//					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
//				}
//				
//			}
//			else
//			{
//				Log.info("SAN not updated");
//				System.out.println("SAN not updated");
//			}
//		
//		}
//		else
//		{
//			Log.info("SAN is not added in the grid");
//			System.out.println("SAN is not added in the grid");
//		}
//
//
//		//======================================
//		
//		//View SAN
//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
//		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
//		//SANFilter
//		click(application, "Search San Menu", "sanpanel_searchsanmenu");
//		click(application, "Select SAN Search type", "select_sansearchtype");
//		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
//		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
//		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
//		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();
//		WebElement SANGridCheck1= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
//		String SANGrid1= SANGridCheck1.getAttribute("style");
//		System.out.println("Customer Name displaying: " +customernamevalue);
//		WebElement AddedSAN1= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
//		if(!SANGrid1.contains("height: 1px"))
//		{
//			Clickon(AddedSAN1);
//			Thread.sleep(1000);
//
//			click(application, "SAN Action dropdown", "SANActionDropdown");
//			click(application, "View SAN link", "viewsan_link");
//			compareText(application, "View SAN Header", "viewsan_header", "View SAN");
//			compareText(application, "Customer Name", "viewsan_customername", customernamevalue);
//			compareText(application, "SAN/FRC Number", "viewsan_sannumber", sannumbervalue);
//			compareText(application, "Service Profile", "serviceprofilevalue", serviceprofilevalue);
//			compareText(application, "Supervision Mode", "supervisionmodevalue", supervisionfieldvalue);
//			compareText(application, "International outgoing calls", "internationaloutgoingcallsvalue", internationaloutgoingcalls_checkedvalue);
//			compareText(application, "International incoming calls", "internationalincomingcallsvalue", internationalincomingcalls_checkedvalue);
//			compareText(application, "Mobile calls allowed", "mobilecallsallowedvalue", mobilecallsallowed_checkedvalue);
//			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/view_noreplytimervalue")));
//			compareText(application, "No reply timer", "view_noreplytimervalue", noreplytimervalue);
//			compareText(application, "Max call duration", "maxcalldurationvalue", maxcallduration);
//			compareText(application, "Charge band name", "chargebandnamevalue", chargebandname);
//			compareText(application, "Payphone blocking enabled", "payphoneblockingenabledvalue", payphoneblocking_checkedvalue);
//			compareText(application, "Web access blocked", "webaccessblockedvalue", webaccessblockedvalue);
//			compareText(application, "San block", "sanblockvalue", sanblockenabled_checkedvalue);
//			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/focenabledvalue")));
//			compareText(application,  "FOC enabled", "focenabledvalue", focenabled_checkedvalue);
//			compareText(application, "Predestination number", "predestinationnumbervalue", predestinationnumber);
//			compareText(application, "CPS Free Format", "CPSvalue", cpsfreeformatvalue);
//			compareText(application, "Ring To Number", "ringtonumbervalue", ringtonumber);
//			GetText(application, "Announcement to play", "announcementtoplay");
//			GetText(application, "Tree name", "treenamevalue");
//
//			//Edit SAN link in view SAN page
//			Thread.sleep(2000);
//			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
//			click(application, "Action dropdown", "viewsan_actiondropdown");
//			click(application, "Edit SAN", "editsan_link");
//			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_header")).isDisplayed())
//			{
//			compareText(application, "Edit SAN Header", "editsan_header", "Edit SAN");
//			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to Edit SAN page");
//			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//			click(application, "Cancel", "cancelbutton");
//			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
//			{
//				Log.info("Navigated to view service page");
//				System.out.println("Navigated to view service page");
//			}
//			else
//			{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
//			}
//			}
//			else
//			{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to Edit SAN page");
//			}
//		}
//		else
//		{
//			Log.info("SAN is not added in the grid");
//			System.out.println("SAN is not added in the grid");
//		}
//			
//		
//		//Add Another SAN link in view SAN page
//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
//		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
//		//SANFilter
//		click(application, "Search San Menu", "sanpanel_searchsanmenu");
//		click(application, "Select SAN Search type", "select_sansearchtype");
//		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
//		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
//		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
//		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();
//		
//			WebElement SANGridCheck2= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
//			String SANGrid2= SANGridCheck2.getAttribute("style");
//			System.out.println("Customer Name displaying: " +customernamevalue);
//			WebElement AddedSAN2= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
//			if(!SANGrid2.contains("height: 1px"))
//			{
//				Clickon(AddedSAN2);
//				Thread.sleep(1000);
//
//				click(application, "SAN Action dropdown", "SANActionDropdown");
//				click(application, "View SAN link", "viewsan_link");
//				compareText(application, "View SAN Header", "viewsan_header", "View SAN");
//			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
//			click(application, "Action dropdown", "viewsan_actiondropdown");
//			Thread.sleep(1000);
//			click(application, "Add Another SAN", "addanothersanlink");
//			if(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")).isDisplayed())
//			{
//				compareText(application, "Add SAN Header", "addsan_header", "Add SAN");
//				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to Add SAN page");
//				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//				click(application, "Cancel", "cancelbutton");
//			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
//			{
//				Log.info("Navigated to view service page");
//				System.out.println("Navigated to view service page");
//			}
//			else
//			{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
//			}
//			}
//			else
//			{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to Add SAN page");
//			}
//			
//			}
//			else
//			{
//				Log.info("SAN is not added in the grid");
//				System.out.println("SAN is not added in the grid");
//			}
//
//
//		//Delete SAN link in View SAN page
//			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
//			compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
//			//SANFilter
//			click(application, "Search San Menu", "sanpanel_searchsanmenu");
//			click(application, "Select SAN Search type", "select_sansearchtype");
//			EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
//			getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
//			EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
//			getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();
//			
//		WebElement SANGridCheck3= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
//		String SANGrid3= SANGridCheck3.getAttribute("style");
//		System.out.println("Customer Name displaying: " +customernamevalue);
//		WebElement AddedSAN3= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
//		if(!SANGrid3.contains("height: 1px"))
//		{
//			Clickon(AddedSAN3);
//			Thread.sleep(1000);
//
//			click(application, "SAN Action dropdown", "SANActionDropdown");
//			click(application, "View SAN", "viewsan_link");
//			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
//			compareText(application, "View SAN Header", "viewsan_header", "View SAN");
//			click(application, "Action dropdown", "viewsan_actiondropdown");
//			click(application, "Delete SAN", "deletesan_link");
//			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
//			{
//				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Delete alert popup is displayed as expected");
//				click(application, "Delete alert close", "deletealertclose");
//			}
//			else
//			{
//				Log.info("Delete alert popup is not displayed");
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Delete alert popup is not displayed");
//			}
//		}
//		else
//		{
//			Log.info("SAN is not added in the grid");
//			System.out.println("SAN is not added in the grid");
//		}
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		click(application, "Back", "viewpage_backbutton");
//		Thread.sleep(2000);
//		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
//		{
//			Log.info("Navigated to view service page");
//			System.out.println("Navigated to view service page");
//		}

		//=====================================================================


		// PortIn SAN

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		//SANFilter
		click(application, "Search San Menu", "sanpanel_searchsanmenu");
		click(application, "Select SAN Search type", "select_sansearchtype");
		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck4= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String SANGrid4= SANGridCheck4.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN4= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
		if(!SANGrid4.contains("height: 1px"))
		{
			Clickon(AddedSAN4);
			Thread.sleep(1000);

		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		click(application, "SAN Action dropdown", "SANActionDropdown");
		click(application, "PortIN SAN link", "portin_link");

		WebElement Port_Dialog= getwebelement(xml.getlocator("//locators/" + application + "/portin_dialog"));
		if(Port_Dialog.isDisplayed())
		{
			compareText(application, "Customer Name", "Port_Customername", customernamevalue);
			compareText(application, "SAN/FRC Number", "Port_SanNumber", sannumbervalue);
			
			String PortToNumber= getwebelement(xml.getlocator("//locators/" + application + "/porttonumber")).getAttribute("value");
			if(PortToNumber!=null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: PortIN Number is displayed as:" +PortToNumber);
			}
			else
			{
				EnterTextValue(application, portinnumber, "Port To Number", "porttonumber");
			}
			
			try {
				WebElement WebAccessBlocked_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/webaccessblocked_checkbox"));
				if (WebAccessBlocked_Checkbox.isEnabled())
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Web access blocked checkbox is checked as expected");
				}
				else
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
			}
			catch (Exception e) {
				// TODO: handle exception
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
			}

			try {
				String PortDate= getwebelement(xml.getlocator("//locators/" + application + "/webaccessblocked_checkbox")).getAttribute("value");
				if(PortDate!= null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Port Date value is: '"+PortDate+"'");
				}
				else
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Port Date value is not displaying");
			}
			catch (Exception e) {
				// TODO: handle exception
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Port Date value is not displaying");
			}

			//Port Time
			//EnterTextValue(application, "05:23:46", "Port Time", "porttime");
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
			LocalDateTime ldt= LocalDateTime.now().plusHours(1).plusMinutes(10).plusSeconds(20);
			String FutureTime= ldt.format(formatter).substring(10).trim();
			System.out.println("Port Time:"+FutureTime);
			EnterTextValue(application, FutureTime, "Port Time", "porttime");

			//porting status
			try {
				String PortStatus= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/portingstatus")));
				if(PortStatus.equalsIgnoreCase("Ported"))
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : PortIN is successful");
				}
				else
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortIN is not successful");
			}
			catch (Exception e) {
				// TODO: handle exception
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortIN is unsuccessful");
			}

			click(application, "OK", "port_okbutton");
		}
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortIN page is not opened");	
		//Cancel porting not done
		
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}

		//===================================================================

		//Port OUT SAN

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		//SANFilter
		click(application, "Search San Menu", "sanpanel_searchsanmenu");
		click(application, "Select SAN Search type", "select_sansearchtype");
		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck5= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String SANGrid5= SANGridCheck5.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN5= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
		if(!SANGrid5.contains("height: 1px"))
		{
			Clickon(AddedSAN5);
			Thread.sleep(1000);

		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		click(application, "SAN Action dropdown", "SANActionDropdown");
		click(application, "PortIN SAN link", "portin_link");
		WebElement Port_Dialog= getwebelement(xml.getlocator("//locators/" + application + "/portin_dialog"));
		if(Port_Dialog.isDisplayed())
		{
			compareText(application, "Customer Name", "Port_Customername", customernamevalue);
			compareText(application, "SAN/FRC Number", "Port_SanNumber", sannumbervalue);
			String PortToNumber= getwebelement(xml.getlocator("//locators/" + application + "/porttonumber")).getAttribute("value");
			if(PortToNumber!=null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: PortIN Number is displayed as:" +PortToNumber);
			}
			else
			{
				EnterTextValue(application, portinnumber, "Port To Number", "porttonumber");
			}

			try {
				WebElement WebAccessBlocked_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/webaccessblocked_checkbox"));
				if (WebAccessBlocked_Checkbox.isEnabled())
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Web access blocked checkbox is checked as expected");
				}
				else
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
			}
			catch (Exception e) {
				// TODO: handle exception
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
			}

			try {
				String PortDate= getwebelement(xml.getlocator("//locators/" + application + "/webaccessblocked_checkbox")).getAttribute("value");
				if(PortDate!= null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Port Date value is: '"+PortDate+"'");
				}
				else
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Port Date value is not displaying");
			}
			catch (Exception e) {
				// TODO: handle exception
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Port Date value is not displaying");
			}

			//Port Time
			//EnterTextValue(application, "05:23:46", "Port Time", "porttime");
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
			LocalDateTime ldt= LocalDateTime.now().plusHours(1).plusMinutes(10).plusSeconds(20);
			String FutureTime= ldt.format(formatter).substring(10).trim();
			System.out.println("Port Time:"+FutureTime);
			EnterTextValue(application, FutureTime, "Port Time", "porttime");

			//porting status
			try {
				String PortStatus= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/portingstatus")));
				if(PortStatus.equalsIgnoreCase("Ported"))
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : PortOUT is successful");
				}
				else
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortOUT is not successful");
			}
			catch (Exception e) {
				// TODO: handle exception
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortOUT is unsuccessful");
			}
			
			click(application, "OK", "port_okbutton");

			//Cancel porting not done
		}
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortOut page is not opened");

		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}


		//====================================================================================

		//SAN Move link under SAN panel in view service page

		//Cancel SAN Move
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		//SANFilter
		click(application, "Search San Menu", "sanpanel_searchsanmenu");
		click(application, "Select SAN Search type", "select_sansearchtype");
		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck6= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String SANGrid6= SANGridCheck6.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN6= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
		if(!SANGrid6.contains("height: 1px"))
		{
			Clickon(AddedSAN6);
			Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		click(application, "SAN Action dropdown", "SANActionDropdown");
		click(application, "SAN Move", "sanmove_link");
		Thread.sleep(2000);
		compareText(application, "SAN Move Header", "sanmoveheader", "SAN Move");
		click(application, "Cancel", "cancelbutton");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Move is cancelled");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}
		//SAN Move
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		//SANFilter
		click(application, "Search San Menu", "sanpanel_searchsanmenu");
		click(application, "Select SAN Search type", "select_sansearchtype");
		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck7= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String SANGrid7= SANGridCheck7.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN7= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
		if(!SANGrid7.contains("height: 1px"))
		{
			Clickon(AddedSAN7);
			Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		click(application, "SAN Action dropdown", "SANActionDropdown");
		click(application, "SAN Move", "sanmove_link");
		Thread.sleep(2000);
		compareText(application, "SAN Move Header", "sanmoveheader", "SAN Move");
		click(application, "Move", "movebutton_sanmove");
		//warning message check
		WarningMessage(application, "destinationcustomername_warningmsg", "Destination Customer name");
		WarningMessage(application, "orderservice_warningmsg", "Order/Service");

		compareText(application, "Customer Name", "SANMove_CustomerName", customernamevalue);
		compareText(application, "SAN Number", "SANMove_SANNumber", sannumbervalue);
		addDropdownValues(application, "Destination Customer Name", "destinationcustomername", destinationcustomername);
		addDropdownValues(application, "Order/Service", "orderservice_dropdown", sanmove_orderno);
		click(application, "Move", "movebutton_sanmove");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Move is successful");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}
		
		//Bulk Move
		//Cancel Bulk Move
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		//SANFilter
		click(application, "Search San Menu", "sanpanel_searchsanmenu");
		click(application, "Select SAN Search type", "select_sansearchtype");
		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck8= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String SANGrid8= SANGridCheck8.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN8= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
		if(!SANGrid8.contains("height: 1px"))
		{
			Clickon(AddedSAN8);
			Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		click(application, "SAN Action dropdown", "SANActionDropdown");
		click(application, "Bulk Move", "bulkmove_link");
		Thread.sleep(2000);
		compareText(application, "Bulk Move Header", "bulkmoveheader", "Bulk Move");
		click(application, "Cancel", "cancelbutton");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Bulk Move is cancelled");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}
		
		//Bulk Move
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		//SANFilter
		click(application, "Search San Menu", "sanpanel_searchsanmenu");
		click(application, "Select SAN Search type", "select_sansearchtype");
		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck9= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String SANGrid9= SANGridCheck9.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN9= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
		if(!SANGrid9.contains("height: 1px"))
		{
			Clickon(AddedSAN9);
			Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		click(application, "SAN Action dropdown", "SANActionDropdown");
		click(application, "Bulk Move", "bulkmove_link");
		Thread.sleep(2000);
		compareText(application, "Bulk Move Header", "bulkmoveheader", "Bulk Move");
		click(application, "Move", "movebutton_sanmove");
		//warning message check
		WarningMessage(application, "bulkmove_countrywarngmsg", "Country");
		WarningMessage(application, "bulkmove_customerwarngmsg", "Customer");
		WarningMessage(application, "filterfrcnumber_warngmsg", "Filter FRC Number");
		WarningMessage(application, "bulkmove_Servicewarngmsg", "Service");

		addDropdownValues(application, "Country", "bulkmove_countrydropdown", bulkmove_country);
		addDropdownValues(application, "Customer", "bulkmove_customerdropdown", bulkmove_customer);
		addDropdownValues(application, "Filter FRC Number", "filterfrcnumberdropdown", filterfrcnumber);
		addDropdownValues(application, "Service", "bulkmove_servicedropdown", bulkmove_service);
		GetText(application, "Remarks", "remarktextarea");
		click(application, "Move", "movebutton_sanmove");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Bulk Move is successful");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}

		//Manage additional FRC
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		//SANFilter
		click(application, "Search San Menu", "sanpanel_searchsanmenu");
		click(application, "Select SAN Search type", "select_sansearchtype");
		EnterTextValue(application, select_sansearchtype, "Enter SAN Search type", "select_sansearchtype");
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		EnterTextValue(application, sannumbervalue, "SAN Number Search", "sannumbersearchfield");
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck10= driver.findElement(By.xpath("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String SANGrid10= SANGridCheck10.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN10= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
		if(!SANGrid10.contains("height: 1px"))
		{
			Clickon(AddedSAN10);
			Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		click(application, "SAN Action dropdown", "SANActionDropdown");
		click(application, "Manage Addnl FRC", "manageaddnlfrc_link");
		compareText(application, "Manage Addnl FRC Header", "manageaddnlfrc_header", "Manage Addnl FRC");
		click(application, "Add Addnl FRC", "addaddnlfrc_link");
		compareText(application, "Manage Addnl FRC Header", "addpriceannouncement_header", "Add Price Announcement");
		compareText(application, "SAN/FRC", "sanfrcvalue", sannumbervalue);
		compareText(application, "Customer Name", "customernamevalue", customernamevalue);
		click(application, "Enable price announcement checkbox", "enablepriceannouncement_checkbox");
		addDropdownValues(application, "Price Announcement", "priceannouncement_dropdown", priceannouncementvalue);
		addDropdownValues(application, "Price Ann Origin", "priceannorigin_dropdown", priceannoriginvalue);
		addDropdownValues(application, "Charge band name", "chargebandnamevalue", chargebandname);
		click(application, "OK", "OkButton");
		compareText(application, "Addl FRC created success message", "addsan_successmsg", "Addnl Frc successfully created.");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}
	}

	public void verifyAllDeleteOperations(String application, String customernamevalue) throws InterruptedException, DocumentException	{

		//Delete SAN
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/sanheader")));
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC");
		click(application, "SAN Action dropdown", "SANActionDropdown");
		click(application, "Delete SAN link", "deletesan_link");	
		delete(application, "deletesan_link", "SAN", "San deleted Successfully");

		//Delete Customer
		WebElement CustomerGridCheck= driver.findElement(By.xpath("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String CustomerGrid= CustomerGridCheck.getAttribute("style");
		WebElement AddedCustomer= driver.findElement(By.xpath("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']"));
		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer);
			Thread.sleep(1000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CustomerpanelActionDropdown")));
			Thread.sleep(1000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Action dropdown button");

			delete(application, "delete", "Customer", "Data deleted successfully. ");
		}

		//Delete Reseller
		WebElement ResellerGridCheck= driver.findElement(By.xpath("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String ResellerGrid= ResellerGridCheck.getAttribute("style");

		WebElement AddedReseller= driver.findElement(By.xpath("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + ResellerName + "')]/parent::div//input[@type='radio']"));

		if(!ResellerGrid.contains("height: 1px"))
		{
			Clickon(AddedReseller);
			Thread.sleep(1000);
			click(application, "Action dropdown", "ResellerActionDropdown");

			delete(application, "delete", "Reseller", "Delete Reseller:Data deleted successfully. RESELLER REMOVED");
		}

		//Delete Service
		click(application, "Action dropdown", "serviceactiondropdown");
		delete(application, "delete", "Service", "Service successfully deleted");
	}


	//============================================================================================


	public void navigateToManageCustomerServicePage(String application) throws InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/mcslink")));
		Thread.sleep(2000);
		Reporter.log("=== MCS page navigated ===");
		Thread.sleep(2000);
	}

	public void navigateToCreateOrderServicePage(String application) throws InterruptedException, DocumentException {

		navigateToManageCustomerServicePage(application);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createorderlink")));
		Thread.sleep(2000);
		Reporter.log("=== Create Order/Service navigated ===");
	}

	boolean customername;

	public void verifychoosecustomer(String application, String name, String customer)
			throws InterruptedException, IOException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		Thread.sleep(5000);

		boolean choosocustomerwarningmsg = getwebelement(
				xml.getlocator("//locators/" + application + "/choosocustomerwarningmsg")).isDisplayed();
		sa.assertTrue(choosocustomerwarningmsg, "next button is displayed");
		System.out.println("choose customer is required message is displayed");
		Log.info("===choose customer is required message is displayed ===");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
		Thread.sleep(10000);
		System.out.println("Entered Name is : " + name);

		/*
		 * SendKeys(getwebelement(xml.getlocator("//locators/" + application +
		 * "/choosecustomerdropdown")), customer); Thread.sleep(5000);
		 * System.out.println("Entered customer Name is : " + customer);
		 */

		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/choosecustomerdropdown")));
		Thread.sleep(5000);
		System.out.println("clicked on customer dropdown");

		try {
			customername = driver.findElement(By.xpath("//div[text()='No matches found']")).isDisplayed();
			Thread.sleep(5000);
			System.out.println("flag:" + customername);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			WebElement customername1 = driver.findElement(By.xpath("//span[text()='" + customer + "']"));
			Thread.sleep(5000);
			customername1.click();
		}

		while (customername) {

			getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")).clear();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(10000);
			getwebelement(xml.getlocator("//locators/" + application + "/choosecustomerdropdown")).clear();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/choosecustomerdropdown")));
			Thread.sleep(5000);
			// System.out.println("clicked on customer dropdown")
			try {
				customername = driver.findElement(By.xpath("//div[text()='No matches found']")).isDisplayed();
			} catch (Exception e) {
				break;
			}

		}

		WebElement customername1 = driver.findElement(By.xpath("//span[text()='" + customer + "']"));
		Thread.sleep(5000);
		customername1.click();
		// System.out.println("selected cutomer is : " +
		// customername1.getText().toString());

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		Thread.sleep(5000);
		System.out.println("clicked on next button");

	}

	// verify create order/service panel
	public void verifycreateorderservicepanel(String application) throws InterruptedException, DocumentException {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		try {
			boolean ordercontractnumbertextfield = getwebelement(
					xml.getlocator("//locators/" + application + "/ordercontractnumber")).isDisplayed();
			sa.assertTrue(ordercontractnumbertextfield, "order contract number textfield is displayed");
			// ExtentTestManager.getTest().log(LogStatus.PASS, "order contract number textfield is
			// displayed");
			System.out.println("order contract number textfield is displayed");

			boolean selectorderswitch = getwebelement(
					xml.getlocator("//locators/" + application + "/selectorderswitch")).isDisplayed();
			sa.assertTrue(selectorderswitch, "c");
			// ExtentTestManager.getTest().log(LogStatus.PASS, "select order switch is
			// displayed");
			System.out.println("select order switch is displayed");

			boolean rfireqiptextfield = getwebelement(
					xml.getlocator("//locators/" + application + "/rfireqiptextfield")).isDisplayed();
			sa.assertTrue(rfireqiptextfield, "RFI / RFQ /IP Voice Line number text field is displayed ");
			// ExtentTestManager.getTest().log(LogStatus.PASS, "RFI / RFQ /IP Voice Line number
			// text field is displayed ");
			System.out.println("RFI / RFQ /IP Voice Line number text field is displayed ");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectorderswitch")));
			Thread.sleep(5000);

			System.out.println("clicked on the switch to verify the presence of create order button");
			// ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on the switch to verify
			// the presence of create order button ");

			boolean createorderbutton1 = getwebelement(
					xml.getlocator("//locators/" + application + "/createorderbutton")).isDisplayed();

			if (createorderbutton1) {

				System.out.println("create order button is displayed");
			} else {

				sa.fail("create order button is not displayed");
			}

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createorderswitch")));
			Thread.sleep(5000);
			// ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on the switch to verify
			// the presence of create order button ");

			System.out.println("clicked on switch to verify the presence of create order button");

			try {
				boolean actualcreateorder = getwebelement(
						xml.getlocator("//locators/" + application + "/createorderbutton")).isDisplayed();
			} catch (Exception e) {

				System.out.println("create order button is not displayed");
			}

			sa.assertAll();

		} catch (TimeoutException e) {

			e.printStackTrace();
		}

	}

	public static String SelectOrderNumber;

	public void createexistingorderservice(String application, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		if (existingorderservice.equalsIgnoreCase("YES")) {

			addDropdownValues(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

			SelectOrderNumber = existingordernumber;


		} else {

			System.out.println("existing order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step :existing order not selected");
		}

	}

	public static String newordernumber, newVoiceLineNumber;

	public void createneworderservice(String application, String neworder, String neworderno, String newrfireqno)
			throws InterruptedException, IOException, DocumentException {

		if (neworder.equalsIgnoreCase("YES")) {

			WebElement CreateOrder_Header= driver.findElement(By.xpath("//div[text()='Create Order / Service']"));
			scrolltoview(CreateOrder_Header);
			Thread.sleep(2000);

			//click(application, "select order switch", "selectorderswitch");	
			EnterTextValue(application, neworderno, "Order/Contract Number", "newordertextfield");
			EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");
			click(application, "create order", "createorderbutton");	
			compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully");			
			scrolltoview(CreateOrder_Header);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;

		} else {
			
			ScrolltoElement(application, "nextbutton");
			click(application, "select order switch", "selectorderswitch");
			addDropdownValues_commonMethod(application,"Order/Contract Number(Parent SID)", "OrdernumberDropdown", neworderno, xml);
			//EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");

			System.out.println("new order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Existing Order Selected");
		}

	}

	public void verifyservicetypeandSubtype(String application, String servicetype, String servicesubtype)
			throws InterruptedException, IOException, DocumentException {

		// select service type
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		addDropdownValues_commonMethod(application,"Service Type", "servicetypetextfield", servicetype, xml);
		addDropdownValues_commonMethod(application, "IP VPN Type", "servicetypetextfield", servicesubtype, xml);
		//addDropdownValues(application, "Service Type", "servicetypetextfield", servicetype);
		//addDropdownValues(application, "IP VPN Type", "servicetypetextfield", servicetype);
		// click on next button
		click(application, "Next", "nextbutton");	
		//compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service");

	}

	public void servicecreationfields(String application) throws InterruptedException, DocumentException {

		try {

			// service identification
			boolean serviceidentificationtextfield = getwebelement(
					xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")).isDisplayed();
			sa.assertTrue(serviceidentificationtextfield, "service identification textfield is not displayed");

			String serviceidentificationtextfieldvalue = getwebelement(
					xml.getlocator("//locators/" + application + "/serviceidentificationtextfield"))
					.getAttribute("value");
			if (!(serviceidentificationtextfieldvalue.isEmpty())) {

				sa.fail("service identification textfield is not empty");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Step : service identification textfield is not empty, value displayed is : "
								+ serviceidentificationtextfieldvalue);
			} else {

				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : service identification textfield is empty ");
			}

			// service type
			boolean servicetype = getwebelement(xml.getlocator("//locators/" + application + "/servicetypevalue")).isDisplayed();
			sa.assertTrue(servicetype, "service type value is not displayed");

			String servicetypevalue = getwebelement(xml.getlocator("//locators/" + application + "/servicetypevalue")).getText();
			if (servicetypevalue != null && servicetypevalue.equalsIgnoreCase("NGIN")) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Service Type value is not empty  and displayed service type is : " + servicetypevalue);
			}

			// remarks
			boolean remarktextarea = getwebelement(xml.getlocator("//locators/" + application + "/remarktextarea"))
					.isDisplayed();
			sa.assertTrue(remarktextarea, "remarks textarea is not displayed");

			String remarktextareavalue = getwebelement(
					xml.getlocator("//locators/" + application + "/remarktextareavalue")).getAttribute("value");
			if (!(remarktextareavalue).isEmpty()) {

				sa.fail("remarks textarea is not empty");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Step : remarks textarea is not empty, value displayed is : " + remarktextareavalue);
			} else {

				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : remarks textarea is empty ");
			}

			// management options- customer administration
			boolean customeradministrationcheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/customeradministrationcheckbox")).isEnabled();
			boolean customeradministrationcheckbox2 = getwebelement(
					xml.getlocator("//locators/" + application + "/customeradministrationcheckbox")).isSelected();

			if (customeradministrationcheckbox == true && customeradministrationcheckbox2 == false) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : customer administration checkbox is unselected by default ");

			} else {

				sa.fail("customer administration checkbox is disabled or selected by default");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Step : customer administration checkbox is disabled or selected by default ");
			}

			// management options- SAN administration
			boolean SANadministrationcheckbox1 = getwebelement(
					xml.getlocator("//locators/" + application + "/sanadministrationcheckbox")).isEnabled();
			boolean SANadministrationcheckbox2 = getwebelement(
					xml.getlocator("//locators/" + application + "/sanadministrationcheckbox")).isSelected();

			if (SANadministrationcheckbox1 == true && SANadministrationcheckbox2 == false) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : SAN administration checkbox is unselected by default ");

			} else {

				sa.fail("SAN administration checkbox is disabled or selected by default");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Step : SAN administration checkbox is disabled or selected by default ");
			}

			// management options- Reseller Administration
			boolean Reselleradministrationcheckbox1 = getwebelement(
					xml.getlocator("//locators/" + application + "/reselleradministrationcheckbox")).isEnabled();
			boolean Reselleradministrationcheckbox2 = getwebelement(
					xml.getlocator("//locators/" + application + "/reselleradministrationcheckbox")).isSelected();

			if (Reselleradministrationcheckbox1 == true && Reselleradministrationcheckbox2 == false) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Reseller Administration checkbox is unselected by default ");

			} else {

				sa.fail("Reseller Administration checkbox is disabled or selected by default");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Step : Reseller Administration checkbox is disabled or selected by default ");
			}

			// next and cancel buttons
			boolean nextbutton = getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")).isDisplayed();
			sa.assertTrue(nextbutton, "Next button is not displayed");

			boolean cancelbutton = getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton")).isDisplayed();
			sa.assertTrue(cancelbutton, "Cancel button is not displayed");
			sa.assertAll();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Element is not displayed in UI");
		}

	}
	
	
	public void createservice(String application, String sid, String Remarks, String EmailService, String PhoneService,String DeliveryChannel, String Pakage,
			String ManagementVpn,String VPNTopology,String WholesaleVPN,String MulticastGroupAddress,String MulticastGroupPool,String MulticastThreshold,String MulticastCheckbox,
			String AllowSMCEmail,String ProactiveNotification,String DialUserAdmin,String ApplicationAwareNetwork,String PerformanceReportingType) throws InterruptedException, IOException, DocumentException 
	{
		Thread.sleep(3000);
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service");
	
        Clear(getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")));
		
		WebElement OKbutton= getwebelement(xml.getlocator("//locators/" + application + "/OKbutton_ServiceCreation"));
		scrolltoview(OKbutton);
		
		click(application, "OK", "OKbutton_ServiceCreation");
		Thread.sleep(1000);
		scrollToTop();
//		Thread.sleep(1000);
		WarningMessage(application,"Service Identification","sidwarngmsg");
		Clear(getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")));
		EnterTextValue(application, sid, "Service Identification", "serviceidentificationtextfield");
		EnterTextValue(application, Remarks, "Remarks", "remarktextarea");
		ScrolltoElement(application, "remarktextarea");
		EnterTextValue(application, EmailService, "Email", "emailtextfield");
		click(application, "Email Arrow", "emailarrow");
		EnterTextValue(application, PhoneService, "Phone Contact", "phonecontacttextfield");
		click(application, "Phone Contact Arrow", "phonearrow");
		
		addDropdownValues_commonMethod(application, "Delivery Channel", "DeliveryChannelField", DeliveryChannel, xml);
		String type=getwebelement(xml.getlocator("//locators/" + application + "/IPVPNType")).getText();
		if(!type.equalsIgnoreCase("IP Voice")) {
		addDropdownValues_commonMethod(application, "Package", "PackageField", Pakage, xml);
		Thread.sleep(2000);
		
		if(Pakage.equalsIgnoreCase("Silver")) {

		    {   
		    	
		       // WebElement webElement=driver.findElement(By.xpath("//div[@class='checkbox']//input"));
		    	//webElement.isSelected();
		        List<WebElement> Checkbox = driver.findElements(By.xpath("//div[@class='checkbox']//input"));
		        int count=Checkbox.size();
	
		        for(int i=0;i<count;i++)
		        {
		        if(Checkbox.get(i).isSelected())	{
		        	String CheckboxName=Checkbox.get(i).getAttribute("name");
		        	
		        	ExtentTestManager.getTest().log(LogStatus.PASS, CheckboxName + " text field is Checked");
					System.out.println(CheckboxName + " text field is displaying");
		        }
		        }
		        }

		        
		        
		    }
		if(Pakage.equalsIgnoreCase("Gold")) {

		    {   
		    	
		       // WebElement webElement=driver.findElement(By.xpath("//div[@class='checkbox']//input"));
		    	//webElement.isSelected();
		        List<WebElement> Checkbox = driver.findElements(By.xpath("//div[@class='checkbox']//input"));
		        int count=Checkbox.size();
	
		        for(int i=0;i<count;i++)
		        {
		        if(Checkbox.get(i).isSelected())	{
		        	String CheckboxName=Checkbox.get(i).getAttribute("name");
		        	
		        	ExtentTestManager.getTest().log(LogStatus.PASS, CheckboxName + " text field is Checked");
					System.out.println(CheckboxName + " text field is displaying");
		        }
		        }
		        }

		        
		        
		    }
		}
		ScrolltoElementComm(application, "Delivery Channel", "SelectValueDropdown");
		if(AllowSMCEmail.equalsIgnoreCase("Yes")) {
			if(type.contains("IPVPN")) {
			ClickCommon(application, "Allow SMC Email", "CheckboxCommon");
			}
			ClickCommon(application, "Router Configuration View IPV6", "CheckboxCommon");
		}
         if(DialUserAdmin.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "Dial User Administration", "CheckboxCommon");
         }
        if(ApplicationAwareNetwork.equalsIgnoreCase("Yes")) {
        	ClickCommon(application, "Application Aware Networking", "CheckboxCommon");
         }
        
        if(ProactiveNotification.equalsIgnoreCase("Yes")) {
        	if(type.equalsIgnoreCase("SwiftNet")) {
        	ClickCommon(application, "Pro-active Notification", "CheckboxCommon");
        	addDropdownValues_common(application, "Notification Management Team", "SelectValueDropdown", "DNA", xml);
        	}
        	}
		
		scrollToTop();
		
		if(type.equalsIgnoreCase("IPVPN Connect" )||type.equalsIgnoreCase("IPVPN IPSec")||type.equalsIgnoreCase("IP VPN Access")) {
			scrolltoend();
			scrolltoview(OKbutton);
			
			addDropdownValues_commonMethod(application, "Management Vpn", "ManagementVpnField", ManagementVpn, xml);
			addDropdownValues_commonMethod(application, "VPN Topology", "VPNTopologyField", VPNTopology, xml);
			ClickCommon(application, "Per CoS Performance Reporting", "CheckboxCommon");
			addDropdownValues_commonMethod(application, "Wholesale VPN/Global MPLS", "WholesaleVPNField", WholesaleVPN, xml);
			addDropdownValues_common(application, "Performance Reporting Type", "SelectValueDropdown", PerformanceReportingType, xml);

			click(application, "Multi VPN Checkbox", "MultiVPNCheckbox");
			click(application, "Disable Voip Checkbox", "DisableVoipCheckbox");
			addDropdownValues_commonMethod(application, "Management Vpn", "ManagementVpnField", ManagementVpn, xml);
			
		}
		if(type.equalsIgnoreCase("IPVPN Plus")) {
			scrolltoend();
			addDropdownValues_commonMethod(application, "Management Vpn", "ManagementVpnField", ManagementVpn, xml);
			addDropdownValues_commonMethod(application, "VPN Topology", "VPNTopologyField", VPNTopology, xml);
			addDropdownValues_commonMethod(application, "Wholesale VPN/Global MPLS", "WholesaleVPNField", WholesaleVPN, xml);
			addDropdownValues_common(application, "Performance Reporting Type", "SelectValueDropdown", PerformanceReportingType, xml);

			if(MulticastCheckbox.equalsIgnoreCase("Yes")) {
			click(application, "Multicast Checkbox", "MulticastCheckbox");
			EnterTextValue(application, MulticastGroupAddress, "Multicast Group Address value", "MulticastGroupAddressvalue");
			EnterTextValue(application, MulticastThreshold, "Multicast Threshold value", "MulticastThresholdvalue");
			EnterTextValue(application, MulticastGroupPool, "Multicast Group Pool value", "MulticastGroupPoolvalue");
			}
			click(application, "Multi VPN Checkbox", "MultiVPNCheckbox");
			click(application, "Multi Group Checkbox", "DisableVoipCheckbox");
			addDropdownValues_commonMethod(application, "Management Vpn", "ManagementVpnField", ManagementVpn, xml);
		}

		scrolltoend();
		
		click(application, "OK", "OKbutton_ServiceCreation");
		Thread.sleep(3000);
		waitforPageenable();
		compareText(application, "Service creation success msg", "servicecreationmessage", "Service successfully created");
	//sa.assertAll();
			
		}
		

	public void verifyingservicecreation(String application, String sid, String ResellerCode,  String Remarks, String EmailService, String PhoneService, 
			String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView, String PerformanceReporting, String ProactiveNotification,
			String NotificationManagementTeam, String DialUserAdministration, String orderno, String rfireqno, String servicetype) throws InterruptedException, IOException, DocumentException {
			
 
		//Cancel service creation

		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service");
//		click(application, "Cancel", "cancelbutton");

//		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
//		{
//			Log.info("Navigated to create order page");
//			System.out.println("Navigated to create order page");
//		}

		
//		//Create service
//		WebElement CreateOrder_Header= driver.findElement(By.xpath("//div[text()='Create Order / Service']"));
//		scrolltoview(CreateOrder_Header);
//
//		addDropdownValues(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno);
//		addDropdownValues(application, "Service Type", "servicetypetextfield", servicetype);
//		// click on next button
//		click(application, "Next", "nextbutton");
//		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service");
//
//		// verify warning messages
		
		Clear(getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")));
		
		WebElement OKbutton= getwebelement(xml.getlocator("//locators/" + application + "/OKbutton_ServiceCreation"));
		scrolltoview(OKbutton);
		
		click(application, "OK", "OKbutton_ServiceCreation");
		Thread.sleep(1000);
		scrollToTop();
//		Thread.sleep(1000);
//		scrollToTop();
		WarningMessage(application,"Service Identification","sidwarngmsg");
		
		// service identification
		//Clear(getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")));
		EnterTextValue(application, sid, "Service Identification", "serviceidentificationtextfield");
		//EnterTextValue(application, ResellerCode, "Reseller Code", "ResellerCodetextfield");
		// remarks
		EnterTextValue(application, Remarks, "Remarks", "remarktextarea");
		ScrolltoElement(application, "remarktextarea");
				//ScrolltoElement(application, "Email_header");//Not working
				//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/Email_header")));//Not working
				//click(application, "Email", "emailtextfield");
		EnterTextValue(application, EmailService, "Email", "emailtextfield");
		click(application, "Email Arrow", "emailarrow");
		EnterTextValue(application, PhoneService, "Phone Contact", "phonecontacttextfield");
		click(application, "Phone Contact Arrow", "phonearrow");
		
		
//		// management options- Manage Service Checkbox
//		if (ManageService.equalsIgnoreCase("YES")) {
//			click(application, "Manage Service checkbox", "ManageServicecheckbox");
//		} else {
//			System.out.println("Manage Service checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Manage Service checkbox is not selected");
//		}
//		
//		if (ManageService.equalsIgnoreCase("NO")) {
//			System.out.println("Manage Service checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Manage Service checkbox is already selected");
//		} else {
//			click(application, "Manage Service checkbox", "ManageServicecheckbox");
//		}
//		
//		//Syslog Event View checkbox
//		if (SyslogEventView.equalsIgnoreCase("YES")) {
//			click(application, "Syslog Event View checkbox", "SyslogEventViewcheckbox");
//		} else {
//			System.out.println("Syslog Event View checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Syslog Event View checkbox is not selected");
//		}
//		if (SyslogEventView.equalsIgnoreCase("NO")) {
//			System.out.println("Syslog Event View checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Syslog Event View checkbox is already selected");
//		} else {
//			click(application, "Syslog Event View checkbox", "SyslogEventViewcheckbox");
//		}
//		
//		//Service Status View checkbox
//		if (ServiceStatusView.equalsIgnoreCase("YES")) {
//			click(application, "Service Status View checkbox", "ServiceStatusViewcheckbox");
//		} else {
//			System.out.println("Service Status View checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Service Status View checkbox is not selected");
//		}
//		if (ServiceStatusView.equalsIgnoreCase("NO")) {
//			System.out.println("Service Status View checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Service Status View checkbox is already selected");
//		} else {
//			click(application, "Service Status View checkbox", "ServiceStatusViewcheckbox");
//		}
//		
//		
//		//Router Configuration View checkbox
//		if (RouterConfigurationView.equalsIgnoreCase("YES")) {
//			click(application, "Router Configuration View checkbox", "RouterConfigurationViewcheckbox");
//		} else {
//			System.out.println("Router Configuration View checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Router Configuration View checkbox is not selected");
//		}
//		if (RouterConfigurationView.equalsIgnoreCase("NO")) {
//			System.out.println("Router Configuration View checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Router Configuration View checkbox is already selected");
//		} else {
//			click(application, "Router Configuration View checkbox", "RouterConfigurationViewcheckbox");
//		}
//		
//		
//		//Performance Reporting checkbox
//		if (PerformanceReporting.equalsIgnoreCase("YES")) {
//			click(application, "Performance Reporting checkbox", "PerformanceReportingcheckbox");
//		} else {
//			System.out.println("Performance Reporting checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Performance Reporting checkbox is not selected");
//		}
//		if (PerformanceReporting.equalsIgnoreCase("NO")) {
//			System.out.println("Performance Reporting checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Performance Reporting checkbox is already selected");
//		} else {
//			click(application, "Performance Reporting checkbox", "PerformanceReportingcheckbox");
//		}

		click(application, "Performance Reporting checkbox", "PerformanceReportingcheckbox");
		
		
//		//Proactive Notification checkbox
//		if (ProactiveNotification.equalsIgnoreCase("YES")) {
//			click(application, "Proactive Notification checkbox", "ProactiveNotificationcheckbox");
//		} else {
//			System.out.println("Proactive Notification checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Proactive Notification checkbox is not selected");
//		}
//		if (ProactiveNotification.equalsIgnoreCase("NO")) {
//			System.out.println("Proactive Notification checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Proactive Notification checkbox is already selected");
//		} else {
//			click(application, "Proactive Notification checkbox", "ProactiveNotificationcheckbox");
//		}
//		
//		//Dial User Administration checkbox
//		if (DialUserAdministration.equalsIgnoreCase("YES")) {
//			click(application, "Dial User Administration checkbox", "DialUserAdministrationcheckbox");
//		} else {
//			System.out.println("Dial User Administration checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Dial User Administration checkbox is not selected");
//		}
//		if (DialUserAdministration.equalsIgnoreCase("NO")) {
//			System.out.println("Dial User Administration checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Dial User Administration checkbox is already selected");
//		} else {
//			click(application, "Dial User Administration checkbox", "DialUserAdministrationcheckbox");
//		}
//		

		scrolltoend();
		click(application, "OK", "okbutton");
		compareText(application, "Service creation success msg", "servicecreationmessage", "Service successfully created");
		sa.assertAll();

	}

	
	
	public void verifyorderpanelinformation_Existingorder(String application, String existingorder,
			String expectedorderno, String expectedvoicelineno) throws InterruptedException, DocumentException {

		if (existingorder.equalsIgnoreCase("Yes")) {

			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue"))
					.getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue"))
					.getText();
			System.out.println("actual voice line number displayed in order panel is : " + actualvoicelineno);

			if (expectedorderno.equalsIgnoreCase(actualorderno)
					&& expectedvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

				System.out.println("order information is matched");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : order information is matched");
			} else {
				sa.fail("order information is not matched");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

		} else {

			System.out.println("existing order is not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : existing order is not selected");
		}

		sa.assertAll();
	}

	public void verifyorderpanelinformation_Neworder(String application, String neworder, String expectedneworderno,
			String expectednewvoicelineno) throws InterruptedException, DocumentException {

		WebElement UsersPanel_Header= getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header"));
		scrolltoview(UsersPanel_Header);

		if (neworder.equalsIgnoreCase("YES")) {

			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue"))
					.getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue"))
					.getText();
			System.out.println("actual voice line number displayed in order panel is : " + actualvoicelineno);

			if (expectedneworderno.equalsIgnoreCase(actualorderno)
					&& expectednewvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

				System.out.println("order information is matched");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : order information is matched");				
			} else {
				sa.fail("order information is not matched");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

		} else {

			System.out.println("new order is not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : new order is not selected");
		}

		//sa.assertAll();
	}


	
	public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno) throws InterruptedException, DocumentException, IOException {

		WebElement UsersPanel_Header= getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header"));
		scrolltoview(UsersPanel_Header);

		//Cancel Edit order in Order panel
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Edit Order", "editorderlink");

		Boolean EditOrder_Header = getwebelement(xml.getlocator("//locators/" + application + "/editorderheader")).isDisplayed();
		Log.info("Edit Order header text is displayed as : " + EditOrder_Header);
		System.out.println("Edit Order header text:"+ EditOrder_Header);
		sa.assertTrue(EditOrder_Header,"Edit Order");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Edit Order header text is displayed as : "+ EditOrder_Header);
		Thread.sleep(1000);

		WebElement EditOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/editorderno"));
		click(application, "Order Number", "editorderno");
		Thread.sleep(2000);
		Clear(EditOrderNo);
		Thread.sleep(2000);
		EnterTextValue(application, editorderno, "Order Number", "editorderno");

		WebElement EditVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/editvoicelineno"));
		click(application, "RFI Voice Line Number", "editvoicelineno");
		Thread.sleep(2000);
		Clear(EditVoiceLineNo);
		Thread.sleep(2000);
		EnterTextValue(application, editvoicelineno, "Order Number", "editvoicelineno");
		click(application, "Cancel", "cancelbutton");

		Boolean OrderHeader = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
		sa.assertTrue(OrderHeader,"Order");
		Log.info("Navigated to order panel in view service page");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		//Edit Order
		Thread.sleep(1000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		Thread.sleep(1000);
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Edit Order", "editorderlink");

		Boolean EditOrder1_Header = getwebelement(xml.getlocator("//locators/" + application + "/editorderheader")).isDisplayed();
		Log.info("Edit Order header text is displayed as : " + EditOrder1_Header);
		System.out.println("Edit Order header text:"+ EditOrder1_Header);
		sa.assertTrue(EditOrder1_Header,"Edit Order");
		Thread.sleep(1000);
		WebElement EditOrderNo1= getwebelement(xml.getlocator("//locators/" + application + "/editorderno"));
		click(application, "Order Number", "editorderno");
		Thread.sleep(2000);
		Clear(EditOrderNo1);
		Thread.sleep(2000);
		EnterTextValue(application, editorderno, "Order Number", "editorderno");
		WebElement EditVoiceLineNo1= getwebelement(xml.getlocator("//locators/" + application + "/editvoicelineno"));
		click(application, "RFI Voice Line Number", "editvoicelineno");
		Thread.sleep(2000);
		Clear(EditVoiceLineNo1);
		Thread.sleep(2000);
		EnterTextValue(application, editvoicelineno, "Order Number", "editvoicelineno");
		click(application, "OK", "editorder_okbutton");
		Thread.sleep(2000);
		waitforPageenable();
		compareText(application, "Header Message", "OrderUpdatedMessage", "Order successfully updated");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Order successfully updated");
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		Thread.sleep(1000);
		//Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
		sa.assertTrue(OrderHeader,"Order");
		Log.info("Navigated to order panel in view service page");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		compareText(application, "Order Number", "ordernumbervalue", editorderno);
		compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno);
		Log.info("------ Edit Order is successful ------");

	}

	public void verifyorderpanel_changeorder(String application, String changeordernumber, String changevoicelineno,String createNew) throws InterruptedException, DocumentException, IOException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		Thread.sleep(1000);

		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Change Order", "changeorderlink");

		Boolean ChangeOrder_Header = getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed();
		Log.info("Change Order header text is displayed as : " + ChangeOrder_Header);
		System.out.println("Change Order header text:"+ ChangeOrder_Header);
		sa.assertTrue(ChangeOrder_Header,"Change Order");
		Thread.sleep(1000);

		click(application, "Choose order dropdown", "changeorder_chooseorderdropdown");

		List<WebElement> ChangeOrder_DropdownList= driver.findElements(By.xpath(xml.getlocator("//locators/" + application + "/changeorder_dropdownlist")));
		int ChangeOrder_Dropdown_count= ChangeOrder_DropdownList.size();
		for (int i=0;i<ChangeOrder_Dropdown_count;i++) {
			ExtentTestManager.getTest().log(LogStatus.PASS,"Order present are:"+ ChangeOrder_DropdownList.get(i).getText());
			System.out.println(ChangeOrder_DropdownList.get(i).getText());
		}
		ExtentTestManager.getTest().log(LogStatus.PASS,"Count of order is"+ ChangeOrder_Dropdown_count);
		System.out.println("order count="+ChangeOrder_Dropdown_count);
		
		if(!createNew.equalsIgnoreCase("YES"))
		{
			
			//addDropdownValues_common(application, "Order/Contract Number (Parent SID)", "SelectValueDropdown", changeordernumber, xml);
			Clickon(getwebelement("//div[contains(@class,'sc-ifAKCX oLlzc') and (text()='Order')]".replace("Order", changeordernumber)));
			Thread.sleep(3000);

			//Cancel change order
			click(application, "Cancel", "changeorder_cancelbutton");
			Thread.sleep(1000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader,"Order");
			Log.info("Navigated to order panel in view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

			//Change order
			click(application, "Action dropdown", "orderactionbutton");
			click(application, "Change Order", "changeorderlink");

			Boolean ChangeOrder1_Header = getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed();
			Log.info("Change Order header text is displayed as : " + ChangeOrder1_Header);
			System.out.println("Change Order header text:"+ ChangeOrder1_Header);
			sa.assertTrue(ChangeOrder1_Header,"Change Order");
			Thread.sleep(1000);

			click(application, "Choose order dropdown", "changeorder_chooseorderdropdown");
			//addDropdownValues_common(application, "Order/Contract Number (Parent SID)", "SelectValueDropdown", changeordernumber, xml);
			Clickon(getwebelement("//div[contains(@class,'sc-ifAKCX oLlzc') and (text()='Order')]".replace("Order", changeordernumber)));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected order from dropdown");

			click(application, "OK", "changeorder_okbutton");
			Thread.sleep(2000);
			waitforPageenable();
			compareText(application, "Header Message", "OrderChangeMessage", "Order successfully changed.");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Order successfully Changed.");
			
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader1,"Order");
			Log.info("Navigated to order panel in view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

			compareText(application, "Order Number", "ordernumbervalue", changeordernumber);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno);
			Log.info("------ Change Order is successful ------");

		}
		else
		{
			click(application, "Select order switch", "changeorder_selectorderswitch");

			//WebElement ChangeOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/changeordernumber"));
			click(application, "Order Number", "changeordernumber");
			Thread.sleep(2000);
			EnterTextValue(application, changeordernumber, "Order Number", "changeordernumber");

			//WebElement ChangeVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/changeordervoicelinenumber"));
			click(application, "RFI Voice Line Number", "changeordervoicelinenumber");
			Thread.sleep(3000);
			EnterTextValue(application, changevoicelineno, "RFI Voice Line Number", "changeordervoicelinenumber");
			click(application, "Cancel", "changeorder_cancelbutton");

			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader,"Order");
			Log.info("Navigated to order panel in view service page");

			//Change Order
			click(application, "Action dropdown", "orderactionbutton");
			click(application, "Change Order", "changeorderlink");

			Boolean ChangeOrder1_Header = getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed();
			Log.info("Change Order header text is displayed as : " + ChangeOrder1_Header);
			System.out.println("Change Order header text:"+ ChangeOrder1_Header);
			sa.assertTrue(ChangeOrder1_Header,"Change Order");
			Thread.sleep(1000);

			click(application, "Select order switch", "changeorder_selectorderswitch");
			click(application, "Order Number", "changeordernumber");
			Thread.sleep(2000);
			EnterTextValue(application, changeordernumber, "Order Number", "changeordernumber");
			click(application, "RFI Voice Line Number", "changeordervoicelinenumber");
			Thread.sleep(3000);
			EnterTextValue(application, changevoicelineno, "RFI Voice Line Number", "changeordervoicelinenumber");
			click(application, "Create Order", "createorder_button");
			waitforPageenable();
			Thread.sleep(2000);
			compareText(application, "Header Message", "OrderChangeMessage", "Order successfully changed.");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Order successfully created");
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader1,"Order");
			Log.info("Navigated to order panel in view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to order panel in view service page");

			compareText(application, "Order Number", "ordernumbervalue", changeordernumber);
			compareText(application, "RFI VoicreateNewce Line Number", "ordervoicelinenumbervalue", changevoicelineno);
			Log.info("------ Change Order is successful ------");
		}
	}

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String ServiceSubType ,
			String Remarks , String EmailService, String PhoneService) throws InterruptedException, DocumentException, IOException {
		
		Thread.sleep(5000);
		compareText(application, "Service panel Header", "servicepanel_header", "Service");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		compareText(application, "Service Identification", "servicepanel_serviceidentificationvalue", sid);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype);
		if(!ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
		compareText(application, "Service SubType", "servicepanel_ResselerCodevalue", ServiceSubType);
		}
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks);
		compareText(application, "Email", "servicepanel_Emailvalue", EmailService);
		compareText(application, "Phone Contact", "servicepanel_PhoneContactvalue", PhoneService);
	}
	
	public void Editservice(String application, String sid, String Remarks, String EmailService, String PhoneService,String DeliveryChannel, String Pakage,
			String ManagementVpn,String VPNTopology,String WholesaleVPN,String MulticastGroupAddress,String MulticastGroupPool,String MulticastThreshold) throws InterruptedException, IOException, DocumentException 
	{
		waitforPageenable();
		Thread.sleep(2000);
		waitforPageenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		waitforPageenable();
		Thread.sleep(2000);
		cleartext(application, "Remarks", "remarktextarea");
		scrolltoend();
		//WebElement CancelButton= getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton"));
		//scrolltoview(CancelButton);
		click(application, "Cancel", "cancelbutton");
		Thread.sleep(2000);
		waitforPageenable();
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		Thread.sleep(5000);
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks);	
		//Edit service
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		waitforPageenable();
		Thread.sleep(2000);
		Clear(getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")));
		EnterTextValue(application, sid, "Service Identification", "serviceidentificationtextfield");
		cleartext(application, "Remarks", "remarktextarea");
		EnterTextValue(application, Remarks, "Remarks", "remarktextarea");
		ScrolltoElement(application, "remarktextarea");
		click(application, "Selected Email", "SelectedEmail");
		click(application, "Email Arrow second", "emailarrowB");
		cleartext(application, "Email", "emailtextfield");
		EnterTextValue(application, EmailService, "Email", "emailtextfield");
		click(application, "Email Arrow", "emailarrow");
		click(application, "Selected Phone", "SelectedPhone");
		click(application, "Phone Arrow second", "phonearrowB");
		cleartext(application, "Phone Contact", "phonecontacttextfield");
		EnterTextValue(application, PhoneService, "Phone Contact", "phonecontacttextfield");
		click(application, "Phone Contact Arrow", "phonearrow");
		addDropdownValues_commonMethod(application, "Delivery Channel", "DeliveryChannelField", DeliveryChannel, xml);
		String type=getwebelement(xml.getlocator("//locators/" + application + "/IPVPNType")).getText();
		if(!type.equalsIgnoreCase("IP Voice")) {
			
		addDropdownValues_commonMethod(application, "Package", "PackageField", Pakage, xml);
		Thread.sleep(2000);
		
		if(Pakage.equalsIgnoreCase("Silver")) {

		    {   
		    	
		       // WebElement webElement=driver.findElement(By.xpath("//div[@class='checkbox']//input"));
		    	//webElement.isSelected();
		        List<WebElement> Checkbox = driver.findElements(By.xpath("//div[@class='checkbox']//input"));
		        int count=Checkbox.size();
	
		        for(int i=0;i<count;i++)
		        {
		        if(Checkbox.get(i).isSelected())	{
		        	String CheckboxName=Checkbox.get(i).getAttribute("name");
		        	
		        	ExtentTestManager.getTest().log(LogStatus.PASS, CheckboxName + " text field is Checked");
					System.out.println(CheckboxName + " text field is displaying");
		        }
		        }
		        }

		        
		        
		    }
		if(Pakage.equalsIgnoreCase("Gold")) {

		    {   
		    	
		       // WebElement webElement=driver.findElement(By.xpath("//div[@class='checkbox']//input"));
		    	//webElement.isSelected();
		        List<WebElement> Checkbox = driver.findElements(By.xpath("//div[@class='checkbox']//input"));
		        int count=Checkbox.size();
	
		        for(int i=0;i<count;i++)
		        {
		        if(Checkbox.get(i).isSelected())	{
		        	String CheckboxName=Checkbox.get(i).getAttribute("name");
		        	
		        	ExtentTestManager.getTest().log(LogStatus.PASS, CheckboxName + " text field is Checked");
					System.out.println(CheckboxName + " text field is displaying");
		        }
		        }
		        }

		        
		        
		    }
		}
		
		scrollToTop();
		//String type=getwebelement(xml.getlocator("//locators/" + application + "/IPVPNType")).getText();
		//System.out.println(type);
		if(type.equalsIgnoreCase("IPVPN Connect" )||type.equalsIgnoreCase("IPVPN IPSec")||type.equalsIgnoreCase("IPVPN Access")) {
			scrolltoend();
			//ScrolltoElement(application, "VPNNext");
			addDropdownValues_commonMethod(application, "Management Vpn", "ManagementVpnField", ManagementVpn, xml);
			addDropdownValues_commonMethod(application, "VPN Topology", "VPNTopologyField", VPNTopology, xml);
			addDropdownValues_commonMethod(application, "Wholesale VPN/Global MPLS", "WholesaleVPNField", WholesaleVPN, xml);
		
			//click(application, "Multi VPN Checkbox", "MultiVPNCheckbox");
			//click(application, "Disable Voip Checkbox", "DisableVoipCheckbox");
			addDropdownValues_commonMethod(application, "Management Vpn", "ManagementVpnField", ManagementVpn, xml);
			
		}
		if(type.equalsIgnoreCase("IPVPN Plus")) {
			scrolltoend();
			addDropdownValues_commonMethod(application, "Management Vpn", "ManagementVpnField", ManagementVpn, xml);
			addDropdownValues_commonMethod(application, "VPN Topology", "VPNTopologyField", VPNTopology, xml);
			addDropdownValues_commonMethod(application, "Wholesale VPN/Global MPLS", "WholesaleVPNField", WholesaleVPN, xml);
			//click(application, "Multicast Checkbox", "MulticastCheckbox");
			//click(application, "Multi VPN Checkbox", "MultiVPNCheckbox");
			//click(application, "Multi Group Checkbox", "DisableVoipCheckbox");
			//EnterTextValue(application, MulticastGroupAddress, "Multicast Group Address value", "MulticastGroupAddressvalue");
			//EnterTextValue(application, MulticastThreshold, "Multicast Threshold value", "MulticastThresholdvalue");
			//EnterTextValue(application, MulticastGroupPool, "Multicast Group Pool value", "MulticastGroupPoolvalue");
			addDropdownValues_commonMethod(application, "Management Vpn", "ManagementVpnField", ManagementVpn, xml);
		}

		scrolltoend();
		click(application, "Next", "VPNNext");
		waitforPageenable();
		Thread.sleep(3000);
		compareText(application, "Service creation success msg", "servicecreationmessage", "Service updated successfully");
	//sa.assertAll();
			
		}
		

	public void Dumpservice(String application, String sid, String Remarks, String EmailService, String PhoneService,String DeliveryChannel, String Pakage,
			String ManagementVpn,String VPNTopology,String WholesaleVPN,String MulticastGroupAddress,String MulticastGroupPool,String MulticastThreshold) throws InterruptedException, IOException, DocumentException 
	{
		waitforPageenable();
		Thread.sleep(2000);
		waitforPageenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Dump", "DumpLink");
		waitforPageenable();
		Thread.sleep(2000);
		GetText(application, "Dump Details", "DumpDetails");
		Thread.sleep(5000);
		waitforPageenable();
		click(application, "Dump Close", "DumpClose");
		Thread.sleep(2000);
		waitforPageenable();
	}
	

	public void verifyservicepanel_links(String application, String Subtype,String EditRemarks, String Remarks, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service

				//Manage service
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Manage", "manageLink");
		waitforPageenable();
		compareText(application, "Manage service header", "manageservice_header", "Manage Service");
		scrollToTop();
		//compareText(application, "Order Name", "status_ordername", changeorderno);
		compareText(application, "Service Identification", "status_servicename", sid);
		compareText(application, "Service type", "status_servicetype", servicetype);

		String ServiceDetails_value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/status_servicedetails")));
		if(ServiceDetails_value.isEmpty())
		{
			Log.info("Service Details column value is empty as expected");
			System.out.println("Service Details column value is empty as expected");
		}
		else
		{
			Log.info("Service Details column value should be empty");
			System.out.println("Service Details column value should be empty");
		}

		compareText(application, "Service Status", "status_currentstatus", servicestatus);

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
		click(application, "Status", "statuslink");

		if(servicestatuschangerequired.equalsIgnoreCase("Yes"))
		{
			
			Thread.sleep(3000);
			WebElement ServiceStatusPage= getwebelement(xml.getlocator("//locators/" + application + "/Servicestatus_popup"));
			if(ServiceStatusPage.isDisplayed())
			{
				click(application, "Change Status", "changestatus_dropdown");
				//click(application, "Change Status value", "changestatus_dropdownvalue");
				click(application, "OK", "okbutton");
				Thread.sleep(3000);
				WebElement ServiceStatusHistory= getwebelement(xml.getlocator("//locators/" + application + "/servicestatushistory"));
				try
				{
					if(ServiceStatusHistory.isDisplayed())
					{
					String mess=getwebelement(xml.getlocator("//locators/" + application + "/servicestatushistory")).getText();	
					ExtentTestManager.getTest().log(LogStatus.PASS, mess);
					if(mess.equalsIgnoreCase("Service Status History successfully changed.")) 
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request logged");
					}
						
					}
					else
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request is not logged");
				}
				catch(StaleElementReferenceException e)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No service history to display");
				}
			}
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Status link is not working");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change not reqired");
			click(application, "Close", "servicestatus_popupclose");
		}

		
		
		
		//synchronize panel in manage service page

		//compareText(application, "Order Name", "sync_ordername", changeorderno);
		compareText(application, "Service Identification", "sync_servicename", sid);
		compareText(application, "Service type", "sync_servicetype", servicetype);

		String ServiceDetails_value1 = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/sync_servicedetails")));
		if(ServiceDetails_value1.isEmpty())
		{
			Log.info("Service Details column value is empty as expected");
			System.out.println("Service Details column value is empty as expected");
		}
		else
		{
			Log.info("Service Details column value should be empty");
			System.out.println("Service Details column value should be empty");
		}

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		compareText(application, "Sync Status", "sync_status", syncstatus);
		/*
		if(!Subtype.equalsIgnoreCase("IP Voice")) {
		click(application, "Synchronize", "synchronizelink");
		waitforPageenable();
		compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.");
		}
		*/
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		click(application, "Back", "managepage_backbutton");
		waitforPageenable();
		
		
		//Verify all the links available in service actions list
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		Thread.sleep(1000);
		click(application, "Action dropdown", "serviceactiondropdown");
		compareText(application, "Edit Link", "EditLink", "Edit");
		compareText(application, "Delete Link", "DeleteLink", "Delete");
		//compareText(application, "Show Infovista Report Link", "ShowNewInfovistaReportLink", "Show Infovista Report");
		compareText(application, "Show New Infovista Report Link", "ShowNewInfovistaReportLink", "Show New Infovista Report");
		compareText(application, "Manage Link", "manageLink", "Manage");
		if((!Subtype.equalsIgnoreCase("IPVPN IPSec"))&& (!Subtype.equalsIgnoreCase("IPVPN Access"))) {
		compareText(application, "Synchronize Link", "SynchronizeServiceLink", "Synchronize");
	}
		//compareText(application, "Manage Subnets Ipv6 Link", "ManageSubnetsIpv6Link", "Manage Subnets Ipv6");
		compareText(application, "Dump Link", "DumpLink", "Dump");
		//click(application, "Action dropdown", "serviceactiondropdown");
		
		
		//click(application, "Manage Subnets Ipv6 Link", "ManageSubnetsIpv6Link");
	//	compareText(application, "Manage Subnets Ipv6 headertext", "ManageSubnetsIpv6_header", "Manage Subnets Ipv6");
		//compareText(application, "Confirmation message under Manage Subnets Ipv6 window", "ManageSubnetsIpv6_Confirmationmessage", "There are no subnets to be managed for this service.");
		//click(application, "Action button under Manage Subnets Ipv6", "ManageSubnetsIpv6_action");
		//compareText(application, "ManageIPs Link", "ManageSubnetsIpv6_ManageIPs", "Manage IPs");
		//compareText(application, "Delete Link", "ManageSubnetsIpv6__Delete", "Delete");
		//Thread.sleep(2000);
		//click(application, "Close popup X mark", "ManageSubnetsIpv6_popupcloseX");
		
		
		//Service delete is performed in the last test case
	
	}

	
	
	
	
	
	
	

	public void choosecustomertocreateorder(String application, String selectCustomer, String name)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createorderlink")));
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Create Order/Service Link");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/createordernametextfield")), name);
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Name is entered in name textfield");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/choosecustomerdropdown")));
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Customer Dropdown");

		WebElement el = driver
				.findElement(By.xpath("//div[@role='list']//span[contains(text(),'" + selectCustomer + "')]"));
		String selectedcustomer = el.getText();
		el.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer is selected from dropdown" + selectedcustomer);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Next button");

	}

	public void verifySearchSAN(String application, String search_sannumber, String customernamevalue, String searchSANfilename, String browserfiles_downloadspath, String serviceprofilevalue, String internationaloutgoingcalls_checkedvalue, String internationalincomingcalls_checkedvalue, String mobilecallsallowed_checkedvalue, String payphoneblocking_checkedvalue, String supervisionfieldvalue, String noreplytimervalue, String webaccessblockedvalue, String cpsfreeformatvalue, String maxcallduration, String chargebandname, String predestinationnumber) throws InterruptedException, DocumentException
	{
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/managecoltnetworklink")));
		Thread.sleep(1000);
		click(application, "Search for SANs", "searchsanlink");
		Thread.sleep(2000);
		compareText(application, "SAN Search", "sansearchheader", "SAN Search");
		EnterTextValue(application, search_sannumber, "SAN", "santextfield");
		Thread.sleep(1000);
		click(application, "Search", "san_searchbutton");
		Thread.sleep(3000);
		click(application, "Download To Excel", "downloadtoexcellink");
		Thread.sleep(3000);
		isFileDownloaded(searchSANfilename, browserfiles_downloadspath);
		WebElement SelectSAN= driver.findElement(By.xpath("//div[contains(text(),'"+search_sannumber+"')]/parent::div//input[@type='radio']"));

		if(SelectSAN.isDisplayed())
		{
			//Verify SearchSAN columns headers
			compareText(application, "FRC Number", "frcnumber_column", "FRC Number");
			compareText(application, "Service Profile", "serviceprofilecolumn", "Service Profile");
			compareText(application, "Customer Name", "customernamecolumn", "Customer Name");
			compareText(application, "Begin Date", "begindatecolumn", "Begin Date");
			compareText(application, "Order Name", "ordernamecolumn", "Order Name");
			Thread.sleep(1000);

			Clickon(SelectSAN);
			Thread.sleep(2000);
			click(application, "Action dropdown", "searchsan_actiondropdown");
			//View link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : View link is displaying in Search SAN page");
				Log.info("View link is displayed");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : View link is not displaying in Search SAN page");
			}

			//Delete link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete")).isDisplayed())
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete link is displaying in Search SAN page");
				Log.info("Add link is displayed");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete link is not displaying in Search SAN page");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing SAN to display");
		}
		if(SelectSAN.isDisplayed())
		{
			Clickon(SelectSAN);
			Thread.sleep(2000);
			click(application, "Action dropdown", "searchsan_actiondropdown");
			Thread.sleep(1000);
			click(application, "View", "view");
			compareText(application, "View San Header", "searchsan_viewpageheader", "View San");

			//Verify view San details

			compareText(application, "Customer Name", "viewsan_customername", customernamevalue);
			compareText(application, "SAN/FRC Number", "viewsan_sannumber", search_sannumber);
			compareText(application, "Service Profile", "serviceprofilevalue", serviceprofilevalue);
			compareText(application, "International outgoing calls", "internationaloutgoingcallsvalue", internationaloutgoingcalls_checkedvalue);
			compareText(application, "International incoming calls", "searchsan_internationalincomingcallsvalue", internationalincomingcalls_checkedvalue);
			compareText(application, "Mobile calls allowed", "mobilecallsallowedvalue", mobilecallsallowed_checkedvalue);
			compareText(application, "No reply timer", "view_noreplytimervalue", noreplytimervalue);
			compareText(application, "Max call duration", "maxcalldurationvalue", maxcallduration);
			compareText(application, "Charge band name", "chargebandnamevalue", chargebandname);
			compareText(application, "Payphone blocking enabled", "payphoneblockingenabledvalue", payphoneblocking_checkedvalue);
			compareText(application, "Web access blocked", "webaccessblockedvalue", webaccessblockedvalue);
			compareText(application, "Predestination number", "searchsan_predestinationnumbervalue", predestinationnumber);
			compareText(application, "CPS Free Format", "CPSvalue", cpsfreeformatvalue);
			GetText(application, "Ring To Number", "ringtonumbervalue");
			GetText(application, "Announcement to play", "searchsan_announcementtoplayvalue");
			Thread.sleep(2000);
			click(application, "Back", "viewpage_backbutton");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing SAN to display");
		}

		if(getwebelement(xml.getlocator("//locators/" + application + "/sansearchheader")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to SAN Search page");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to SAN Search page");
		}

		//Verify Search order/service page
		EnterTextValue(application, search_sannumber, "SAN", "santextfield");
		Thread.sleep(1000);
		click(application, "Search", "san_searchbutton");
		Thread.sleep(3000);
		WebElement SelectSAN1= driver.findElement(By.xpath("//div[contains(text(),'"+search_sannumber+"')]/parent::div//input[@type='radio']"));
		if(SelectSAN1.isDisplayed())
		{	
			String OrderNumber= getwebelement(xml.getlocator("//locators/" + application + "/ordername_link")).getText();
			click(application, "Order Name", "ordername_link");
			compareText(application, "Search For Order / Service Header", "searchfororder_header", "Search For Order / Service");
			Thread.sleep(1000);
			WebElement SelectExistingOrder= driver.findElement(By.xpath("//div[contains(text(),'"+OrderNumber+"')]/parent::div//input[@type='radio']"));

			if(SelectExistingOrder.isDisplayed())
			{
				Clickon(SelectExistingOrder);
				click(application, "Action dropdown", "searchsan_actiondropdown");
				Thread.sleep(1000);
				click(application, "View", "searchfororder_viewlink");
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to view service page");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
				}

			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Existing order is not available");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing SAN to display");
		}

		driver.navigate().back();
		Thread.sleep(2000);

		//Verify manage link in search order page
		compareText(application, "SAN Search", "sansearchheader", "SAN Search");
		if(getwebelement(xml.getlocator("//locators/" + application + "/sansearchheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to 'SAN Search' page");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to 'SAN Search' page");
		}

		EnterTextValue(application, search_sannumber, "SAN", "santextfield");
		Thread.sleep(1000);
		click(application, "Search", "san_searchbutton");
		Thread.sleep(3000);

		WebElement SelectSAN2= driver.findElement(By.xpath("//div[contains(text(),'"+search_sannumber+"')]/parent::div//input[@type='radio']"));
		if(SelectSAN2.isDisplayed())
		{
			String OrderNumber= getwebelement(xml.getlocator("//locators/" + application + "/ordername_link")).getText();
			click(application, "Order Name", "ordername_link");
			compareText(application, "Search For Order / Service Header", "searchfororder_header", "Search For Order / Service");
			Thread.sleep(1000);
			WebElement SelectExistingOrder= driver.findElement(By.xpath("//div[contains(text(),'"+OrderNumber+"')]/parent::div//input[@type='radio']"));
			if(SelectExistingOrder.isDisplayed())
			{
				Clickon(SelectExistingOrder);
				click(application, "Action dropdown", "searchsan_actiondropdown");
				Thread.sleep(1000);
				click(application, "Manage", "searchfororder_managelink");
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to view service page");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
				}

			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Existing order is not available");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing SAN to display");
		}

		driver.navigate().back();
		Thread.sleep(2000);

		//Verify Delete SAN details
		EnterTextValue(application, search_sannumber, "SAN", "santextfield");
		Thread.sleep(1000);
		click(application, "Search", "san_searchbutton");
		Thread.sleep(3000);
		WebElement SelectSAN3= driver.findElement(By.xpath("//div[contains(text(),'"+search_sannumber+"')]/parent::div//input[@type='radio']"));
		if(SelectSAN3.isDisplayed())
		{
			Clickon(SelectSAN3);
			Thread.sleep(2000);
			click(application, "Action dropdown", "searchsan_actiondropdown");
			Thread.sleep(1000);
			//	delete(application, "delete", "Delete", "San deleted Successfully");
			Thread.sleep(1000);
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing SAN to display");
		}

	}


		
	
	
	
		public void AddVPNSiteOrderPlus(String application, String ServiceSubType, String VPNCountry,String VPNDeviceCity,String VPNSiteOrder,String VPNSiteAlias,
			String CSRName,String SiteOrderType,String IAReference,String IVReference,String PerfReport,String PerCoS,String RouterIPv4,String RouterIPv6, String CPEName,String VoipCheckbox,String VoipService, String VirtualCPECheckbox,
			String SelectSiteToggle,String SelectCityToggle,String Device_Xng_City_Name,String Device_Xng_City_Code,String DSLSite,String SpeedboatSite,String ActelisBased,String Physical_Site) throws InterruptedException, DocumentException, IOException {
if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")) {
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		
		click(application, "Action dropdown", "VPNSiteactionbutton");
		click(application, "Add VPN Site Order", "AddVPNSiteOrderLink");

		
		//compareText(application, "Add VPN Site Order header", "AddVPNSitePage", "Add VPN Site Order");
		
		scrolltoend();
		click(application, "Next button", "VPNNext");
		WarningMessageCom(application, "Site Order Number","WarningMessageCommon");
		//WarningMessageCom(application, "Device Country","WarningMessageddnCommon");
		WarningMessageCom(application, "Device Xng City","WarningMessageddnCommon");
		WarningMessageCom(application, "Physical Site","WarningMessageCommon");
		WarningMessageCom(application, "IA Reference","WarningMessageCommon");
		WarningMessageCom(application, "CPE Name","WarningMessageCommon");
		WarningMessageCom(application, "IV Reference","WarningMessageddnCommon");
		WarningMessageCom(application, "Smart Monitoring Destination","WarningMessageddnCommon");
		
		EnterTextValue(application, VPNSiteOrder, "Site Order Number", "VPNSiteOrderNum");
		EnterTextValue(application, VPNSiteAlias, "Site Alias", "VPNSiteAlis");
		addDropdownValues_commonMethod(application, "Device Country", "VPNDeviceCountry", VPNCountry, xml);
		
		if(SelectCityToggle.equalsIgnoreCase("Yes")) {
			click(application, "Toggle", "SelectCityToogle");
			EnterTextValueCommon(application, Device_Xng_City_Name, "Device Xng City Name", "TextValueCommon");
			EnterTextValueCommon(application, Device_Xng_City_Code, "Device Xng City Code", "TextValueCommon");
			}
			else {
				addDropdownValues_commonMethod(application, "Device Xng City", "VPNDeviceCity", VPNDeviceCity, xml);
				}
		
		
		if(SelectSiteToggle.equalsIgnoreCase("Yes")) {
		click(application, "Toggle", "SelectsiteToogle");
		addDropdownValues_commonMethodDivSpan(application, "Physical Site", "SelectValueDropdown", Physical_Site, xml);
		//addDropdownValues_common(application, "Physical Site", "SelectValueDropdown", " (1232)", xml);
		}
		else {
			EnterTextValueCommon(application, CSRName, "Physical Site/CSR Name", "TextValueCommon");	
		}
		addDropdownValues_common(application, "Site Order Type", "SelectValueDropdown", SiteOrderType, xml);
		if(SiteOrderType.equalsIgnoreCase("secondary")) {
			EnterTextValueCommon(application,"Check1", "Primary Cpe Lan Ip", "TextValueCommon");
			EnterTextValueCommon(application,"Check2", "Primary Loopback Ip", "TextValueCommon");	
		}
		if(SiteOrderType.equalsIgnoreCase("primary")) {
			EnterTextValueCommon(application,"Check2", "Secondary Cpe Lan Ip", "TextValueCommon");
			EnterTextValueCommon(application,"Check1", "Secondary Loopback Ip", "TextValueCommon");
			
		}
		EnterTextValueCommon(application,"10.38", "Cpe Lan Interface", "TextValueCommon");
		ScrolltoElementComm(application, "Cpe Lan Interface", "SelectValueDropdown");
		//addDropdownValues_commonMethod(application, "Priority", "SelectValueDropdown", Priority, xml);
		addDropdownValues_common(application, "IV Reference", "SelectValueDropdown", IVReference, xml);
		
		if(VoipCheckbox.equalsIgnoreCase("YES")) {
			ClickCommon(application, "VoIP", "CheckboxCommon");
		addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
		EnterTextValueCommon(application,"980", "IPV Number", "TextValueCommon");
		EnterTextValueCommon(application,"2", "Band Width", "TextValueCommon");
		addDropdownValues_commonMethod(application, "Smart Monitoring Destination", "VPNVoipService", "ITSM Ticket", xml);
		}
		if(VirtualCPECheckbox.equalsIgnoreCase("YES")) {
			ClickCommon(application, "VirtualCPE Site", "CheckboxCommon");
			if(VoipCheckbox.equalsIgnoreCase("YES")) {
			addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
			}
			}
		else {
			addDropdownValues_commonMethod(application, "Managed Site", "VPNVoipService", "no", xml);
			addDropdownValues_commonMethod(application, "Smart Monitoring Destination", "VPNVoipService", "ITSM Ticket", xml);
			
		}
		EnterTextValueCommon(application,"123", "VPN Proposition ID", "TextValueCommon");
		addDropdownValues_common(application, "Performance Reporting", "SelectValueDropdown", PerfReport, xml);
		addDropdownValues_common(application, "Per CoS Performance Reporting", "SelectValueDropdown", PerCoS, xml);
		ScrolltoElementComm(application, "Performance Reporting", "SelectValueDropdown");
		addDropdownValues_common(application, "Router Configuration View IPv4", "SelectValueDropdown", RouterIPv4, xml);
		addDropdownValues_common(application, "Router Configuration View IPv6", "SelectValueDropdown", RouterIPv6, xml);
		EnterTextValueCommon(application,"2345", "Related Site Order Number", "TextValueCommon");
		
		ScrolltoElementComm(application, "Related Site Order Number", "SelectValueDropdown");
		if(!VirtualCPECheckbox.equalsIgnoreCase("YES")) {
		if(DSLSite.equalsIgnoreCase("Yes")) {
			ClickCommon(application, "DSL Site", "CheckboxCommon");
			}
		if(SpeedboatSite.equalsIgnoreCase("Yes")) {
			if(!DSLSite.equalsIgnoreCase("Yes")) {
			ClickCommon(application, "Speedboat Site", "CheckboxCommon");
			}
		  }
		if(ActelisBased.equalsIgnoreCase("Yes")) {
			ClickCommon(application, "Actelis Based", "CheckboxCommon");
			}
		}
		ClickCommon(application, "SDVpn Site", "CheckboxCommon");
		EnterTextValueCommon(application, IAReference, "VRRP IP", "TextValueCommon");
		//EnterTextValueCommon(application, CPEName, "Bespoke Reference", "TextValueCommon");
		
		scrolltoend();
		EnterTextValueCommon(application, IAReference, "IA Reference", "TextValueCommon");
		EnterTextValueCommon(application, CPEName, "CPE Name", "TextValueCommon");
		click(application, "Rate Flag", "RateFlagRadio");
		addDropdownValues_common(application, "Billing Type", "SelectValueDropdown", "UBB - Per GigaByte", xml);
		//addDropdownValues_common(application, "CNG Option", "SelectValueDropdown", RouterIv6, xml);
		EnterTextValue(application, CPEName, "Remark", "VPNRemark");
		//EnterTextValueCommon(application, CPEName, "BGP as Number Local", "TextValueCommon");
		//EnterTextValueCommon(application, CPEName, "BGP as Number Remote", "TextValueCommon");
		if(VirtualCPECheckbox.equalsIgnoreCase("YES")) {
		addDropdownValues_common(application, "L2 Technology", "SelectValueDropdown", "Actelis", xml);
		}
		scrolltoend();
			click(application, "Next button", "VPNNext");
			Thread.sleep(3000);
			compareText(application, "Add VPN Site", "SuccessMessageCom", "Site order created successfully");
			Thread.sleep(2000);
}
	}

	public void AddVPNSiteOrder3(String application, String ServiceSubType, String VPNCountry,String VPNDeviceCity,String VPNSiteOrder,String VPNSiteAlias,
			String CSRName,String SiteOrderType,String IAReference,String IVReference,String PerfReport,String PerCoS,String RouterIPv4,String RouterIPv6, String CPEName,String VoipService,
			String SelectSiteToggle,String SelectCityToggle,String Device_Xng_City_Name,String Device_Xng_City_Code,String VoipCheckbox,String DSLSite,String SpeedboatSite,String ActelisBased,String CNGOption,String CNG,String Physical_Site) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")||ServiceSubType.equalsIgnoreCase("IPVPN Connect")||ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		
		click(application, "Action dropdown", "VPNSiteactionbutton");
		click(application, "Add VPN Site Order", "AddVPNSiteOrderLink");

		
		//compareText(application, "Add VPN Site Order header", "AddVPNSitePage", "Add VPN Site Order");
		
		scrolltoend();
		click(application, "Next button", "VPNNext");
		WarningMessageCom(application, "Site Order Number","WarningMessageCommon");
		//WarningMessageCom(application, "Device Country","WarningMessageddnCommon");
		WarningMessageCom(application, "Device Xng City","WarningMessageddnCommon");
		WarningMessageCom(application, "Physical Site","WarningMessageCommon");
		//WarningMessageCom(application, "CSR Name","WarningMessageCommon");
		WarningMessageCom(application, "IA Reference","WarningMessageCommon");
		WarningMessageCom(application, "CPE Name","WarningMessageCommon");
		WarningMessageCom(application, "IV Reference","WarningMessageddnCommon");
		
		EnterTextValue(application, VPNSiteOrder, "Site Order Number", "VPNSiteOrderNum");
		EnterTextValue(application, VPNSiteAlias, "Site Alias", "VPNSiteAlis");
		addDropdownValues_commonMethod(application, "Device Country", "VPNDeviceCountry", VPNCountry, xml);
		
		
		if(SelectCityToggle.equalsIgnoreCase("Yes")) {
			click(application, "Toggle", "SelectCityToogle");
			EnterTextValueCommon(application, Device_Xng_City_Name, "Device Xng City Name", "TextValueCommon");
			EnterTextValueCommon(application, Device_Xng_City_Code, "Device Xng City Code", "TextValueCommon");
			}
			else {
				addDropdownValues_commonMethod(application, "Device Xng City", "VPNDeviceCity", VPNDeviceCity, xml);
				}
		
		
		if(SelectSiteToggle.equalsIgnoreCase("Yes")) {
		click(application, "Toggle", "SelectsiteToogle");
		addDropdownValues_commonMethodDivSpan(application, "Physical Site", "SelectValueDropdown", Physical_Site, xml);
		//addDropdownValues_common(application, "Physical Site", "SelectValueDropdown", " (1232)", xml);
		
		}
		else {
			EnterTextValueCommon(application, CSRName, "Physical Site/CSR Name", "TextValueCommon");
			
			}
		
		
		//addDropdownValues_common(application, "Site Order Type", "SelectValueDropdown", SiteOrderType, xml);
		addDropdownValues_common(application, "IV Reference", "SelectValueDropdown", IVReference, xml);
		ScrolltoElementComm(application, "Primary Site Order", "SelectValueDropdown");
		if(VoipCheckbox.equalsIgnoreCase("YES")) {
		ClickCommon(application, "VoIP", "CheckboxCommon");
		//if(ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			//addDropdownValues_common(application, "Smart Monitoring Destination", "SelectValueDropdown", "ITSM Ticket", xml);
	//	}
		addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
		
		//if(ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
		//EnterTextValueCommon(application,"980", "IPV Number", "TextValueCommon");
		//EnterTextValueCommon(application,"2", "Band Width", "TextValueCommon");
		//EnterTextValueCommon(application,"123", "VPN Proposition ID", "TextValueCommon");
		//}
		}
		
		addDropdownValues_common(application, "Performance Reporting", "SelectValueDropdown", PerfReport, xml);
		addDropdownValues_common(application, "Per CoS Performance Reporting", "SelectValueDropdown", PerCoS, xml);
		addDropdownValues_common(application, "Managed Site", "SelectValueDropdown", "no", xml);
		addDropdownValues_common(application, "Router Configuration View IPv4", "SelectValueDropdown", RouterIPv4, xml);
		addDropdownValues_common(application, "Router Configuration View IPv6", "SelectValueDropdown", RouterIPv6, xml);
        EnterTextValueCommon(application,"2345", "Related Site Order Number", "TextValueCommon");
		scrolltoend();
		
			if(DSLSite.equalsIgnoreCase("Yes")) {
				ClickCommon(application, "DSL Site", "CheckboxCommon");
				}
			if(ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			if(SpeedboatSite.equalsIgnoreCase("Yes")) {
				if(!DSLSite.equalsIgnoreCase("Yes")) {
				ClickCommon(application, "Speedboat Site", "CheckboxCommon");
				}
			  }	
			}
		if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")||ServiceSubType.equalsIgnoreCase("IPVPN Connect")) {
			
			if(ActelisBased.equalsIgnoreCase("Yes")) {
				ClickCommon(application, "Actelis Based", "CheckboxCommon");
				}
			EnterTextValueCommon(application, IAReference, "CIID", "TextValueCommon");
			addDropdownValues_common(application, "CNG Option", "SelectValueDropdown", CNGOption, xml);
			
			//addDropdownValues_common(application, "CNG", "SelectValueDropdown", CNG, xml);
			}
		//addDropdownValues_common(application, "Wholesale Provider", "SelectValueDropdown", "IPC-Standard", xml);
		EnterTextValueCommon(application, IAReference, "IA Reference", "TextValueCommon");
		EnterTextValueCommon(application, CPEName, "CPE Name", "TextValueCommon");
		click(application, "Rate Flag", "RateFlagRadio");
		addDropdownValues_common(application, "Billing Type", "SelectValueDropdown", "UBB - Per GigaByte", xml);
		//addDropdownValues_common(application, "CNG Option", "SelectValueDropdown", RouterIv6, xml);
		EnterTextValue(application, CPEName, "Remark", "VPNRemark");
		//EnterTextValueCommon(application, CPEName, "BGP as Number Local", "TextValueCommon");
		//EnterTextValueCommon(application, CPEName, "BGP as Number Remote", "TextValueCommon");

			click(application, "Next button", "VPNNext");
			compareText(application, "Add VPN Site", "SuccessMessageCom", "Site order created successfully");
			Thread.sleep(2000);
	}
	}


	
	
	
	
	public void AddVPNSiteOrder(String application, String ServiceSubType,String VPNCountry,String VPNDeviceCity,String VPNPhysicalSite,String VPNVendorModel,String VPNSiteOrder,String VPNSiteAlias,
			String VPNRouterId,String VPNManagementAdd,String VoipService,String HubSpoke,String SelectSiteToggle,String SelectCityToggle,String Device_Xng_City_Name,String Device_Xng_City_Code,String VoipCheckbox,String DeviceToggle,String Physical_Site) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")||ServiceSubType.equalsIgnoreCase("IP Voice")||ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		}
		else {
			WebElement HUB_header= getwebelement("//div[text()='Hub']");
			scrolltoview(HUB_header);
		}
		
		click(application, "Add VPN Site Order Link", "AddVPNSitelink");
		Thread.sleep(2000);
		//compareText(application, "Add VPN Site Order header", "AddVPNSitePage", "Add VPN Site Order");
		
		scrolltoend();
		click(application, "Next button", "VPNNext");
		WarningMessageCom(application, "Site Order Number","WarningMessageCommon");
		WarningMessageCom(application, "Device Country","WarningMessageddnCommon");
		WarningMessageCom(application, "Device Xng City","WarningMessageddnCommon");
		WarningMessageCom(application, "Physical Site","WarningMessageCommon");
		WarningMessageCom(application, "Router Id","WarningMessageCommon");
		WarningMessageCom(application, "Management Address","WarningMessageCommon");
		WarningMessageCom(application, "Vendor/Model","WarningMessageddnCommon");
		WarningMessageCom(application, "Device Name","WarningMessageCommon");
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
		WarningMessageCom(application, "Snmp V3 Auth Password","WarningMessageCommon");
		WarningMessageCom(application, "Snmp V3 Auth Password","WarningMessageCommon");
		}
		EnterTextValue(application, VPNSiteOrder, "Site Order Number", "VPNSiteOrderNum");
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
		EnterTextValue(application, VPNSiteAlias, "Site Alias", "VPNSiteAlis");
		}
		addDropdownValues_commonMethod(application, "Device Country", "VPNDeviceCountry", VPNCountry, xml);
		
		if(SelectCityToggle.equalsIgnoreCase("Yes")) {
			click(application, "Toggle", "SelectCityToogle");
			EnterTextValueCommon(application, Device_Xng_City_Name, "Device Xng City Name", "TextValueCommon");
			EnterTextValueCommon(application, Device_Xng_City_Code, "Device Xng City Code", "TextValueCommon");
			}
			else {
				addDropdownValues_commonMethod(application, "Device Xng City", "VPNDeviceCity", VPNDeviceCity, xml);
				}
		
		
		if(SelectSiteToggle.equalsIgnoreCase("Yes")) {
		click(application, "Toggle", "SelectsiteToogleL2");
		addDropdownValues_commonMethodDivSpan(application, "Physical Site", "SelectValueDropdown", Physical_Site, xml);
		//addDropdownValues_common(application, "Physical Site", "SelectValueDropdown", " (1232)", xml);
		}
		else {
			EnterTextValue(application, VPNPhysicalSite, "Physical Site", "VPNPhysicalSite");
			}
		if(ServiceSubType.equalsIgnoreCase("SwiftNet")){
		addDropdownValues_common(application, "VPN Site Order Type", "SelectValueDropdown", HubSpoke, xml);
		}
		if(VoipCheckbox.equalsIgnoreCase("YES")) {
			ClickCommon(application, "VoIP", "CheckboxCommon");
			addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
		}
		scrolltoview(getwebelement("//div[text()='Device']"));
		
		if(!DeviceToggle.equalsIgnoreCase("Yes")) {
		EnterTextValue(application, VPNRouterId, "Router Id", "VPNRouterId");
		EnterTextValue(application, VPNManagementAdd, "Management Address", "VPNManagementAdd");
		//addDropdownValues_commonMethod(application, "Vendor/Model", "VPNVendoeModel", VPNVendorModel, xml);
		
		
		Clickon(getwebelement("//div[label[text()='Vendor/Model']]//input"));
		  Thread.sleep(2000);
		  
		  SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VPNVendorModel);	
		Thread.sleep(2000);
			
		 Clickon(getwebelement("(//div[contains(text(),'"+ VPNVendorModel +"')])[1]"));	
		 Thread.sleep(2000);
		 AcceptJavaScriptMethod();
		//Clickon(getwebelement("//span[@aria-hidden='true']"));
		GetText(application, "Device Name", "VPNDeviceName");
		scrolltoend();
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
		click(application, "Generate Password", "VPNGeneratePass1");
		click(application, "Generate Password", "VPNGeneratePass2");
		EnterTextValueCommon(application, "Test", "Premise Name", "TextValueCommon");
		EnterTextValueCommon(application, "123", "Premise Code", "TextValueCommon");
		}
		else {
			
			ClearAndEnterTextValueComm(application, "Snmpro", "TextValueCommon", "Colt");
			ClearAndEnterTextValueComm(application, "Snmprw", "TextValueCommon", "Colt123");
			if(ServiceSubType.equalsIgnoreCase("SwiftNet")) {
			EnterTextValueCommon(application, "Colt", "Snmp V3 User Name", "TextValueCommon");
			}else {
				EnterTextValueCommon(application, "Colt", "Snmp V3 Security User Name", "TextValueCommon");	
			}
			EnterTextValueCommon(application, "Colt123", "Snmp V3 Auth Password", "TextValueCommon");
			//EnterTextValueCommon(application, "Colt32", "Snmp V3 Priv Password", "TextValueCommon");
			EnterTextValueCommon(application, "ColtTest", "Snmp V3 Context Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Snmp V3 Context Engine ID", "TextValueCommon");
			EnterTextValueCommon(application, "ColtTest", "Snmp V3 Security User Name", "TextValueCommon");
			EnterTextValueCommon(application, "Test", "Premise Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Premise Code", "TextValueCommon");
			
	
		}
		}
		else {
			addDropdownValues_common(application, "Device Name", "SelectValueDropdown", " ", xml);
			
		}
		scrolltoend();
		click(application, "Next button", "VPNNext");
		waitforPageenable();
		Thread.sleep(5000);
		compareText(application, "Add VPN Site", "SuccessMessageCom", "Site order created successfully");
		Thread.sleep(2000);
	}
	}
	
	public void SwifNetSpoke(String application, String ServiceSubType,String VPNCountry,String VPNDeviceCity,String VPNPhysicalSite,String VPNVendorModel,String VPNSiteOrder,String VPNSiteAlias,
			String VPNRouterId,String VPNManagementAdd,String VoipService,String HubSpoke,String SelectSiteToggle,String SelectCityToggle,String Device_Xng_City_Name,String Device_Xng_City_Code,String VoipCheckbox,String DeviceToggle,String Physical_Site) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		
			WebElement HUB_header= getwebelement("//div[text()='Spoke']");
			scrolltoview(HUB_header);
		
		
		click(application, "Add VPN Site Order Link", "AddVPNSitelinkSpoke");
		Thread.sleep(2000);
		//compareText(application, "Add VPN Site Order header", "AddVPNSitePage", "Add VPN Site Order");
		
		scrolltoend();
		click(application, "Next button", "VPNNext");
		WarningMessageCom(application, "Site Order Number","WarningMessageCommon");
		WarningMessageCom(application, "Device Country","WarningMessageddnCommon");
		WarningMessageCom(application, "Device Xng City","WarningMessageddnCommon");
		WarningMessageCom(application, "Physical Site","WarningMessageCommon");
		WarningMessageCom(application, "Router Id","WarningMessageCommon");
		WarningMessageCom(application, "Management Address","WarningMessageCommon");
		WarningMessageCom(application, "Vendor/Model","WarningMessageddnCommon");
		WarningMessageCom(application, "Device Name","WarningMessageCommon");
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
		WarningMessageCom(application, "Snmp V3 Auth Password","WarningMessageCommon");
		WarningMessageCom(application, "Snmp V3 Auth Password","WarningMessageCommon");
		}
		EnterTextValue(application, VPNSiteOrder, "Site Order Number", "VPNSiteOrderNum");
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
		EnterTextValue(application, VPNSiteAlias, "Site Alias", "VPNSiteAlis");
		}
		addDropdownValues_commonMethod(application, "Device Country", "VPNDeviceCountry", VPNCountry, xml);
		
		if(SelectCityToggle.equalsIgnoreCase("Yes")) {
			click(application, "Toggle", "SelectCityToogle");
			EnterTextValueCommon(application, Device_Xng_City_Name, "Device Xng City Name", "TextValueCommon");
			EnterTextValueCommon(application, Device_Xng_City_Code, "Device Xng City Code", "TextValueCommon");
			}
			else {
				addDropdownValues_commonMethod(application, "Device Xng City", "VPNDeviceCity", VPNDeviceCity, xml);
				}
		
		
		if(SelectSiteToggle.equalsIgnoreCase("Yes")) {
		click(application, "Toggle", "SelectsiteToogleL2");
		addDropdownValues_commonMethodDivSpan(application, "Physical Site", "SelectValueDropdown", Physical_Site, xml);
		//addDropdownValues_common(application, "Physical Site", "SelectValueDropdown", " (1232)", xml);
		}
		else {
			EnterTextValue(application, VPNPhysicalSite, "Physical Site", "VPNPhysicalSite");
			}
		if(ServiceSubType.equalsIgnoreCase("SwiftNet")){
		addDropdownValues_common(application, "VPN Site Order Type", "SelectValueDropdown", HubSpoke, xml);
		}
		if(VoipCheckbox.equalsIgnoreCase("YES")) {
			ClickCommon(application, "VoIP", "CheckboxCommon");
			addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
		}
		scrolltoview(getwebelement("//div[text()='Device']"));
		
		if(!DeviceToggle.equalsIgnoreCase("Yes")) {
		EnterTextValue(application, VPNRouterId, "Router Id", "VPNRouterId");
		EnterTextValue(application, VPNManagementAdd, "Management Address", "VPNManagementAdd");
		//addDropdownValues_commonMethod(application, "Vendor/Model", "VPNVendoeModel", VPNVendorModel, xml);
		
		
		Clickon(getwebelement("//div[label[text()='Vendor/Model']]//input"));
		  Thread.sleep(2000);
		  
		  SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VPNVendorModel);	
		Thread.sleep(2000);
			
		 Clickon(getwebelement("(//div[contains(text(),'"+ VPNVendorModel +"')])[1]"));	
		 Thread.sleep(2000);
		 AcceptJavaScriptMethod();
		//Clickon(getwebelement("//span[@aria-hidden='true']"));
		GetText(application, "Device Name", "VPNDeviceName");
		scrolltoend();
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
		click(application, "Generate Password", "VPNGeneratePass1");
		click(application, "Generate Password", "VPNGeneratePass2");
		EnterTextValueCommon(application, "Test", "Premise Name", "TextValueCommon");
		EnterTextValueCommon(application, "123", "Premise Code", "TextValueCommon");
		}
		else {
			
			ClearAndEnterTextValueComm(application, "Snmpro", "TextValueCommon", "Colt");
			ClearAndEnterTextValueComm(application, "Snmprw", "TextValueCommon", "Colt123");
			EnterTextValueCommon(application, "Colt", "Snmp V3 User Name", "TextValueCommon");
			EnterTextValueCommon(application, "Colt123", "Snmp V3 Auth Password", "TextValueCommon");
			//EnterTextValueCommon(application, "Colt32", "Snmp V3 Priv Password", "TextValueCommon");
			EnterTextValueCommon(application, "ColtTest", "Snmp V3 Context Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Snmp V3 Context Engine ID", "TextValueCommon");
			EnterTextValueCommon(application, "ColtTest", "Snmp V3 Security User Name", "TextValueCommon");
			EnterTextValueCommon(application, "Test", "Premise Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Premise Code", "TextValueCommon");
			
	
		}
		}
		else {
			addDropdownValues_common(application, "Device Name", "SelectValueDropdown", " ", xml);
			
		}
		scrolltoend();
		click(application, "Next button", "VPNNext");
		waitforPageenable();
		Thread.sleep(5000);
		compareText(application, "Add VPN Site", "SuccessMessageCom", "Site order created successfully Device successfully created");
		Thread.sleep(2000);
	}
	}
	

	
	
	

	public void verifyAddedVPNSitePlusInformation_View(String application, String ServiceSubType,String VPNCountry,String VPNDeviceCity,String VPNSiteOrder,String VPNSiteAlias,
			String CSRName,String SiteOrderType,String IAReference,String IVReference,String PerfReport,String PerCoS,String RouterIPv4,String RouterIPv6, String CPEName) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")) {
		
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiteOrder + "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");

			
ClickCommon(application, "View", "AddVPNSiteCommonLink");
        compareText_Common(application, "Site Order Number", "CompareTextCommon", VPNSiteOrder, xml);
        compareText_Common(application, "Site Order Alias", "CompareTextCommon", VPNSiteAlias, xml);
       // compareText_Common(application, "Device Country", "CompareTextCommon", VPNCountry, xml);
        // compareText_Common(application, "Device Xng City", "CompareTextCommon", VPNDeviceCity, xml);
        GetTextCommon(application, "Device Country", "CompareTextCommon");
        GetTextCommon(application, "Device Xng City", "CompareTextCommon");
        GetTextCommon(application, "Physical Site", "CompareTextCommon");
        GetTextCommon(application, "IV Reference", "CompareTextCommon"); 
        GetTextCommon(application, "Performance Reporting", "CompareTextCommon");
        GetTextCommon(application, "Per CoS Performance Reporting", "CompareTextCommon");
        GetTextCommon(application, "Router Configuration View IPv4", "CompareTextCommon");
        //GetTextCommon(application, "VoIP Class of Service", "CompareTextCommon");
        GetTextCommon(application, "Router Configuration View IPv6", "CompareTextCommon");
        GetTextCommon(application, "CPE Name", "CompareTextCommon");
        GetTextCommon(application, "IA Reference", "CompareTextCommon");
       /*
        
        compareText_Common(application, "Physical Site", "CompareTextCommon", CSRName, xml);
        compareText_Common(application, "IV Reference", "CompareTextCommon", IVReference, xml);
        compareText_Common(application, "Performance Reporting", "CompareTextCommon", PerfReport, xml);
        compareText_Common(application, "Per CoS Performance Reporting", "CompareTextCommon", PerCoS, xml);
        compareText_Common(application, "Router Configuration View IPv4", "CompareTextCommon", RouterIPv4, xml);
        compareText_Common(application, "Router Configuration View IPv6", "CompareTextCommon", RouterIPv6, xml);
        compareText_Common(application, "CPE Name", "CompareTextCommon", CPEName, xml);
        compareText_Common(application, "IA Reference", "CompareTextCommon", IAReference, xml);
        */
    	click(application, "Back", "VPN_Backbutton");
    	waitforPageenable();
    	Thread.sleep(2000);
    	
		}
        
	}
	
	public void VerifyVPNSiteOrder3(String application, String ServiceSubType, String VPNCountry,String VPNDeviceCity,String VPNSiteOrder,String VPNSiteAlias,
			String CSRName,String SiteOrderType,String IAReference,String IVReference,String PerfReport,String PerCoS,String RouterIPv4,String RouterIPv6, String CPEName,String VoipService) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")||ServiceSubType.equalsIgnoreCase("IPVPN Connect")||ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers==1)
		{
			click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
			Thread.sleep(3000);
		}
		else if(NoOfUsers>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + VPNSiteOrder + "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

		}

		click(application, "Action dropdown", "VPNSiteactionbutton");

		
		
		//click(application, "Click Radio Button", "SiteOrderRadioButton");
		//click(application, "Action dropdown", "VPNSiteactionbutton");
		ClickCommon(application, "View", "AddVPNSiteCommonLink");
        compareText_Common(application, "Site Order Number", "CompareTextCommon", VPNSiteOrder, xml);
        compareText_Common(application, "Site Order Alias", "CompareTextCommon", VPNSiteAlias, xml);
        GetTextCommon(application, "Device Country", "CompareTextCommon");
        GetTextCommon(application, "Device Xng City", "CompareTextCommon");
        // compareText_Common(application, "Device Country", "CompareTextCommon", VPNCountry, xml);
        // compareText_Common(application, "Device Xng City", "CompareTextCommon", VPNDeviceCity, xml);
      //  compareText_Common(application, "Physical Site", "CompareTextCommon", CSRName, xml);
        //compareText_Common(application, "IV Reference", "CompareTextCommon", IVReference, xml);
      //  compareText_Common(application, "Performance Reporting", "CompareTextCommon", PerfReport, xml);
        //compareText_Common(application, "Per CoS Performance Reporting", "CompareTextCommon", PerCoS, xml);
        //compareText_Common(application, "Router Configuration View IPv4", "CompareTextCommon", RouterIPv4, xml);
        GetTextCommon(application, "Physical Site", "CompareTextCommon");
        GetTextCommon(application, "IV Reference", "CompareTextCommon"); 
        GetTextCommon(application, "Performance Reporting", "CompareTextCommon");
        GetTextCommon(application, "Per CoS Performance Reporting", "CompareTextCommon");
        GetTextCommon(application, "Router Configuration View IPv4", "CompareTextCommon");
        GetTextCommon(application, "VoIP Class of Service", "CompareTextCommon");
        GetTextCommon(application, "Router Configuration View IPv6", "CompareTextCommon");
        GetTextCommon(application, "CPE Name", "CompareTextCommon");
        GetTextCommon(application, "IA Reference", "CompareTextCommon");
       
        
        //compareText_Common(application, "Router Configuration View IPv6", "CompareTextCommon", RouterIPv6, xml);
        //compareText_Common(application, "CPE Name", "CompareTextCommon", CPEName, xml);
        //compareText_Common(application, "IA Reference", "CompareTextCommon", IAReference, xml);
        //compareText_Common(application, "VoIP Class of Service", "CompareTextCommon", VoipService, xml);
    	click(application, "Back", "VPN_Backbutton");
    	waitforPageenable();
    	Thread.sleep(2000);
    	
		}
        
	}

	public void VerifyVPNSiteOrder(String application, String ServiceSubType,String VPNCountry,String VPNDeviceCity,String VPNPhysicalSite,String VPNVendorModel,String VPNSiteOrder,String VPNSiteAlias,
			String VPNRouterId,String VPNManagementAdd,String VoipService,String HubSpoke) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")||ServiceSubType.equalsIgnoreCase("IP Voice")||ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		click(application, "VPN View Link", "VPN_viewdevice1");
		 
		}
		else {
			WebElement HUB_header= getwebelement("//div[text()='Hub']");
			scrolltoview(HUB_header);
			click(application, "VPN View Link", "Hub_viewdevice1");
		}
        
		
		Thread.sleep(4000);
		GetText(application, "Device", "VPN_ViewDevice_Header");//Device//Last Modified on :2020-05-14 07:18 GMT, Modified By :colttest@colt.net, Sync Status: sync in progress
 
		

		GetTextCommon(application, "Router Id", "CompareTextCommon");
		GetTextCommon(application, "Vendor/Model", "CompareTextCommon");
		GetTextCommon(application, "Country", "CompareTextCommon");
		GetTextCommon(application, "City", "CompareTextCommon");
		GetTextCommon(application, "Management Address", "CompareTextCommon");
		GetTextCommon(application, "Site", "CompareTextCommon");
		 
/*
		 compareText_Common(application, "Router Id", "CompareTextCommon", VPNRouterId, xml);
	        compareText_Common(application, "Vendor/Model", "CompareTextCommon", VPNVendorModel, xml);
	        compareText_Common(application, "Country", "CompareTextCommon", VPNCountry, xml);
	        compareText_Common(application, "City", "CompareTextCommon", VPNDeviceCity, xml);
	        compareText_Common(application, "Management Address", "CompareTextCommon", VPNManagementAdd, xml);
	        compareText_Common(application, "Site", "CompareTextCommon", VPNPhysicalSite, xml);
	        */
	        click(application, "Back", "VPN_Backbutton");
	        waitforPageenable();
	    	Thread.sleep(2000);
	    	       
	
		
	}	
	}
	public void VerifyVPNSiteOrderSpoke(String application, String ServiceSubType,String VPNCountry,String VPNDeviceCity,String VPNPhysicalSite,String VPNVendorModel,String VPNSiteOrder,String VPNSiteAlias,
			String VPNRouterId,String VPNManagementAdd,String VoipService,String HubSpoke) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		
			WebElement HUB_header= getwebelement("//div[text()='Hub']");
			scrolltoview(HUB_header);
			click(application, "VPN View Link", "Spoke_viewdevice1");
		
        
		
		Thread.sleep(4000);
		GetText(application, "Device", "VPN_ViewDevice_Header");//Device//Last Modified on :2020-05-14 07:18 GMT, Modified By :colttest@colt.net, Sync Status: sync in progress
 
		GetTextCommon(application, "Router Id", "CompareTextCommon");
		GetTextCommon(application, "Vendor/Model", "CompareTextCommon");
		GetTextCommon(application, "Country", "CompareTextCommon");
		GetTextCommon(application, "City", "CompareTextCommon");
		GetTextCommon(application, "Management Address", "CompareTextCommon");
		GetTextCommon(application, "Site", "CompareTextCommon");
		 
		
		/*
		compareText_Common(application, "Router Id", "CompareTextCommon", VPNRouterId, xml);
	        compareText_Common(application, "Vendor/Model", "CompareTextCommon", VPNVendorModel, xml);
	        compareText_Common(application, "Country", "CompareTextCommon", VPNCountry, xml);
	        compareText_Common(application, "City", "CompareTextCommon", VPNDeviceCity, xml);
	        compareText_Common(application, "Management Address", "CompareTextCommon", VPNManagementAdd, xml);
	        compareText_Common(application, "Site", "CompareTextCommon", VPNPhysicalSite, xml);
	      */
	        click(application, "Back", "VPN_Backbutton");
	       
	waitforPageenable();
	Thread.sleep(2000);
		
	}	
	}

	
	public void AddManageUser(String application, String ServiceSubType, String ManageUserName) throws InterruptedException, DocumentException, IOException {
		 
		
		WebElement ManageUser_header= getwebelement("//div[text()='Management Options']");
		scrolltoview(ManageUser_header);
		click(application, "Action dropdown", "ManageUserAction");
        ClickCommon(application, "Add", "ManageUserActionDdn");
        Thread.sleep(3000);
        click(application, "Click Ok", "UserOk");
        WarningMessageCom(application, "User Name","WarningMessageCommon");
		WarningMessageCom(application, "Password","WarningMessageCommon");
		EnterTextValueCommon(application, ManageUserName, "User Name", "TextValueCommon");
        click(application, "Generate Password", "AddUserGeneretePass");
        waitforPageenable();
        Thread.sleep(2000);
        click(application, "Available User", "SelectHiddenUser");
        click(application, "Forward Arrow Click", "BackwardArrow");
        waitforPageenable();
        Thread.sleep(2000);
        click(application, "Click Ok", "UserOk");
      //  Thread.sleep(2000);
		 GetText(application, "Success Message", "SuccessMessageCom");
		 Thread.sleep(4000);
       waitforPageenable();
        Thread.sleep(2000);

}

	
	public void EditManageUser(String application, String ServiceSubType, String EditUserName,String ManageUserName ) throws InterruptedException, DocumentException, IOException {
		 
			
			WebElement ManageUser_header= getwebelement("//div[text()='Management Options']");
			scrolltoview(ManageUser_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("(//div[@ref='eBodyContainer'])[2]//div[contains(@class,'ag-row')]"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("((//div[text()='"+ManageUserName+"']//parent::div)[3]//div//span)[1]"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "ManageUserAction");
            ClickCommon(application, "Edit", "ManageUserActionDdn");
            Thread.sleep(3000);
            ClearAndEnterTextValueComm(application, "User Name", "TextValueCommon", EditUserName);
            click(application, "Generate Password", "AddUserGeneretePass");
            waitforPageenable();
            Thread.sleep(2000);
            click(application, "Available User", "AvailableHiddenUser");
            click(application, "Forward Arrow Click", "ForwardArrow");
            waitforPageenable();
            Thread.sleep(2000);
            click(application, "Click Ok", "UserOk");
            //Thread.sleep(2000);
    		 GetText(application, "Success Message", "SuccessMessageCom");
    		 Thread.sleep(4000);
            waitforPageenable();
            Thread.sleep(2000);
           
          	}
	
	public void ViewManageUser(String application, String ServiceSubType, String EditUserName,String ManageUserName ) throws InterruptedException, DocumentException, IOException {
		 
		
		WebElement ManageUser_header= getwebelement("//div[text()='Management Options']");
		scrolltoview(ManageUser_header);
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("(//div[@ref='eBodyContainer'])[2]//div[contains(@class,'ag-row')]"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("((//div[text()='"+ManageUserName+"']//parent::div)[3]//div//span)[1]"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

		}

		click(application, "Action dropdown", "ManageUserAction");
        ClickCommon(application, "View", "ManageUserActionDdn");
        Thread.sleep(15000);
        String actualValue=getwebelement("(//tr//td)[2]//div").getText();
		  ExtentTestManager.getTest().log(LogStatus.PASS, "Device" + " Services Hidden To Users "+ actualValue );
		  Thread.sleep(6000);
		  String actualValue1=getwebelement("(//tr//td)[3]//div").getText();
		  ExtentTestManager.getTest().log(LogStatus.PASS, "Device" + " Services Available To Users "+ actualValue1);
		  Thread.sleep(6000);
        
        click(application, "Close", "DumpClose");
        Thread.sleep(2000);
		 Thread.sleep(2000);
       
      	}

	public void DeleteManageUser(String application, String ServiceSubType, String EditUserName) throws InterruptedException, DocumentException, IOException {
		 
		
		WebElement ManageUser_header= getwebelement("//div[text()='Management Options']");
		scrolltoview(ManageUser_header);
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("(//div[@ref='eBodyContainer'])[2]//div[contains(@class,'ag-row')]"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("((//div[text()='"+EditUserName+"']//parent::div)[3]//div//span)[1]"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

		}

		click(application, "Action dropdown", "ManageUserAction");
        ClickCommon(application, "Delete", "ManageUserActionDdn");
        Thread.sleep(3000);
        AcceptJavaScriptMethod();
     //  click(application, "Delete", "DeleteAny");
 		Thread.sleep(2000);
 		 GetText(application, "Success Message", "SuccessMessageCom");
 		 Thread.sleep(4000);
         waitforPageenable();
        //compareText(application, "Delete Device", "SuccessMessageCom", "Static Route successfully deleted.");
 		Thread.sleep(2000);
	
    

}

public void AddVPNAlis(String application, String ServiceSubType, String VPNName,String VPNAlis) throws InterruptedException, DocumentException, IOException {
		 
	if(ServiceSubType.contains("IPVPN")) {
		WebElement ManageUser_header= getwebelement("//div[text()='Configuration Options']");
		scrolltoview(ManageUser_header);
		click(application, "Action dropdown", "VPNAlisactionbutton");
		ClickCommon(application, "Add", "VPNAlisActionCommonLink");
        Thread.sleep(3000);
        click(application, "Next", "VPNNext");
        WarningMessageCom(application, "VPN Name","WarningMessageCommon");
		WarningMessageCom(application, "VPN Alias","WarningMessageCommon");
		EnterTextValueCommon(application, VPNName, "VPN Name", "TextValueCommon");
		EnterTextValueCommon(application, VPNAlis, "VPN Alias", "TextValueCommon");
		 click(application, "Next", "VPNNext");
		 Thread.sleep(2000);
		 waitforPageenable();
        Thread.sleep(2000);
         GetText(application, "Success Message", "SuccessMessageCom");
		 Thread.sleep(2000);
       waitforPageenable();
        Thread.sleep(2000);
		}
	else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Add VPN Alis Should not Present");
		}

}

	
public void EditVPNAlis(String application, String ServiceSubType, String VPNName,String VPNAlis) throws InterruptedException, DocumentException, IOException {
	 
	if(ServiceSubType.contains("IPVPN")) {
	WebElement ManageUser_header= getwebelement("//div[text()='Configuration Options']");
	scrolltoview(ManageUser_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("(//div[text()='VPN Name/Alias']//parent::div//following-sibling::div)[3]//div[contains(@class,'ag-body-container')]//div[contains(@class,'ag-row')]"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers>=0)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("((//div[text()='"+VPNName+"']//parent::div)[1]//div//span)[2]"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			
			
			click(application, "Action dropdown", "VPNAlisactionbutton");
			ClickCommon(application, "Edit", "VPNAlisActionCommonLink");
	        Thread.sleep(3000);
	        ClearAndEnterTextValueComm(application, "VPN Name", "TextValueCommon", VPNName);
	        ClearAndEnterTextValueComm(application, "VPN Alias", "TextValueCommon", VPNAlis);
            click(application, "Click Ok", "multilink_okButton");
            Thread.sleep(2000);
            waitforPageenable();
    		 GetText(application, "Success Message", "SuccessMessageCom");
    		Thread.sleep(2000);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

	}
	else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Edit VPN Alis Should not Present");
		}

          	}
public void DeleteVPNAlis(String application, String ServiceSubType, String VPNName,String VPNAlis) throws InterruptedException, DocumentException, IOException {
	 
	if(ServiceSubType.contains("IPVPN")) {
	WebElement ManageUser_header= getwebelement("//div[text()='Configuration Options']");
	scrolltoview(ManageUser_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("(//div[text()='VPN Name/Alias']//parent::div//following-sibling::div)[3]//div[contains(@class,'ag-body-container')]//div[contains(@class,'ag-row')]"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers>=0)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("((//div[text()='"+VPNName+"']//parent::div)[1]//div//span)[2]"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
						click(application, "Action dropdown", "VPNAlisactionbutton");
			ClickCommon(application, "Delete", "VPNAlisActionCommonLink");
	        Thread.sleep(3000);
        //AcceptJavaScriptMethod();
          click(application, "Delete", "DeleteAny");
 		Thread.sleep(2000);
 		waitforPageenable();
 		 GetText(application, "Success Message", "SuccessMessageCom");
 		 Thread.sleep(2000);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}


	}
	else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Delete VPN Alis Should not Present");
		}

}


	
	public void EditVPNSiteOrderPlus(String application, String ServiceSubType, String VoipCheckbox,String VoipService,String VirtualCPECheckbox,String VPNSiteAlias,
			String CSRName,String SiteOrderType,String IAReference,String IVReference,String PerfReport,String PerCoS,String RouterIPv4,String RouterIPv6, String CPEName,String VPNSiteOrder,String Physical_Site) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiteOrder + "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");

			
ClickCommon(application, "Edit", "AddVPNSiteCommonLink");
	        
		
		//compareText(application, "Add VPN Site Order header", "AddVPNSitePage", "Add VPN Site Order");
		
		edittext_common(application, "Site Alias", "VPNSiteAlis", VPNSiteAlias,xml);
		//edittext_common(application, "CSR Name", "TextValueCommon",CSRName,xml);
		//click(application, "Toggle", "Togglebutton");
		//addDropdownValues_commonMethodDivSpan(application, "Physical Site", "SelectValueDropdown", Physical_Site, xml);
		//addDropdownValues_common(application, "Physical Site", "SelectValueDropdown", " (1232)", xml);
		edittext_common(application, "Cpe Lan Interface", "TextValueCommon","10.38",xml);
		addDropdownValues_common(application, "Site Order Type", "SelectValueDropdown", SiteOrderType, xml);
		ScrolltoElementComm(application, "Site Order Type", "SelectValueDropdown");
		//addDropdownValues_commonMethod(application, "Priority", "SelectValueDropdown", Priority, xml);
		addDropdownValues_common(application, "IV Reference", "SelectValueDropdown", IVReference, xml);
		Boolean SelectVoIP=driver.findElement(By.xpath("//label[text()='VoIP']//parent::div//div//input")).isSelected();
		if(VoipCheckbox.equalsIgnoreCase("YES") && (!SelectVoIP)) {
			ClickCommon(application, "VoIP", "CheckboxCommon");
		addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
		edittext_common(application, "IPV Number", "TextValueCommon","980",xml);
		edittext_common(application, "Band Width", "TextValueCommon","2",xml);
		addDropdownValues_commonMethod(application, "Smart Monitoring Destination", "VPNVoipService", "ITSM Ticket", xml);
		}
		else {
			addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
			edittext_common(application, "IPV Number", "TextValueCommon","980",xml);
			edittext_common(application, "Band Width", "TextValueCommon","2",xml);
			addDropdownValues_commonMethod(application, "Smart Monitoring Destination", "VPNVoipService", "ITSM Ticket", xml);
		
		}
		/*
		Boolean SelectCPE=driver.findElement(By.xpath("//label[text()='VirtualCPE Site']//parent::div//div//input")).isSelected();
		if(VirtualCPECheckbox.equalsIgnoreCase("YES")&&(SelectCPE)) {
			//ClickCommon(application, "VirtualCPE Site", "CheckboxCommon");
			}
		else {
			addDropdownValues_commonMethod(application, "Smart Monitoring Destination", "VPNVoipService", "ITSM Ticket", xml);
			addDropdownValues_commonMethod(application, "Managed Site", "VPNVoipService", "no", xml);
			
		}
		*/
		ScrolltoElementComm(application, "VoIP", "CheckboxCommon");
		edittext_common(application, "VPN Proposition ID", "TextValueCommon","123",xml);
		addDropdownValues_common(application, "Performance Reporting", "SelectValueDropdown", PerfReport, xml);
		addDropdownValues_common(application, "Per CoS Performance Reporting", "SelectValueDropdown", PerCoS, xml);
		addDropdownValues_common(application, "Router Configuration View IPv4", "SelectValueDropdown", RouterIPv4, xml);
		addDropdownValues_common(application, "Router Configuration View IPv6", "SelectValueDropdown", RouterIPv6, xml);
		
		ScrolltoElementComm(application, "Performance Reporting", "SelectValueDropdown");
		if(!VirtualCPECheckbox.equalsIgnoreCase("YES")) {
		//ClickCommon(application, "Speedboat Site", "CheckboxCommon");
		}
		ClickCommon(application, "SDVpn Site", "CheckboxCommon");
		edittext_common(application, "VRRP IP", "TextValueCommon",IAReference,xml);
		edittext_common(application, "Bespoke Reference", "TextValueCommon",CPEName,xml);
		scrolltoend();
		edittext_common(application, "IA Reference", "TextValueCommon",IAReference,xml);
		edittext_common(application, "CPE Name", "TextValueCommon",CPEName,xml);
		
		click(application, "Rate Flag", "RateFlagRadio");
		addDropdownValues_common(application, "Billing Type", "SelectValueDropdown", "UBB - Per GigaByte", xml);
		//addDropdownValues_common(application, "CNG Option", "SelectValueDropdown", RouterIv6, xml);
		edittextFields_commonMethod(application, "Remark", "VPNRemark",CPEName,xml);
		edittext_common(application, "BGP as Number Local", "TextValueCommon",CPEName,xml);
		edittext_common(application, "BGP as Number Remote", "TextValueCommon",CPEName,xml);
		
		if(VirtualCPECheckbox.equalsIgnoreCase("YES")) {
		//addDropdownValues_common(application, "L2 Technology", "SelectValueDropdown", "Actelis", xml);
		}
			click(application, "Next button", "VPNNext");
		Thread.sleep(3000);
			compareText(application, "Add VPN Site", "SuccessMessageCom", "Site Order successfully updated.");
			Thread.sleep(2000);
}
	}

	
	public void EditVPNSiteOrder3(String application, String ServiceSubType, String VPNCountry,String VPNDeviceCity,String VPNSiteOrder,String VPNSiteAlias,
			String CSRName,String SiteOrderType,String IAReference,String IVReference,String PerfReport,String PerCoS,String RouterIPv4,String RouterIPv6, String CPEName,String VoipService,String Physical_Site) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")||ServiceSubType.equalsIgnoreCase("IPVPN Connect")||ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers==1)
		{
			click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
			Thread.sleep(3000);
		}
		else if(NoOfUsers>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + VPNSiteOrder + "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

		}

		click(application, "Action dropdown", "VPNSiteactionbutton");

		
        ClickCommon(application, "Edit", "AddVPNSiteCommonLink");
        
	
	//compareText(application, "Add VPN Site Order header", "AddVPNSitePage", "Add VPN Site Order");
	
	edittext_common(application, "Site Alias", "VPNSiteAlis", VPNSiteAlias,xml);
	//addDropdownValues_commonMethodDivSpan(application, "Physical Site", "SelectValueDropdown", Physical_Site, xml);
	//edittext_common(application, "CSR Name", "TextValueCommon",CSRName,xml);
			//addDropdownValues_common(application, "Site Order Type", "SelectValueDropdown", SiteOrderType, xml);
		addDropdownValues_common(application, "IV Reference", "SelectValueDropdown", IVReference, xml);
		ScrolltoElementComm(application, "Primary Site Order", "SelectValueDropdown");
		ClickCommon(application, "VoIP", "CheckboxCommon");
		if(ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			addDropdownValues_common(application, "Smart Monitoring Destination", "SelectValueDropdown", "ITSM Ticket", xml);
		}
		//addDropdownValues_common(application, "VoIP Class of Service", "SelectValueDropdown", VoipService, xml);
		addDropdownValues_common(application, "Performance Reporting", "SelectValueDropdown", PerfReport, xml);
		addDropdownValues_common(application, "Per CoS Performance Reporting", "SelectValueDropdown", PerCoS, xml);
		addDropdownValues_common(application, "Managed Site", "SelectValueDropdown", "no", xml);
		addDropdownValues_common(application, "Router Configuration View IPv4", "SelectValueDropdown", RouterIPv4, xml);
		addDropdownValues_common(application, "Router Configuration View IPv6", "SelectValueDropdown", RouterIPv6, xml);
		scrolltoend();
		ClickCommon(application, "DSL Site", "CheckboxCommon");
		if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")||ServiceSubType.equalsIgnoreCase("IPVPN Connect")) {
			
		//ClickCommon(application, "Actelis Based", "CheckboxCommon");
		}
		//addDropdownValues_common(application, "Wholesale Provider", "SelectValueDropdown", "IPC-Standard", xml);
		scrolltoend();
		edittext_common(application, "IA Reference", "TextValueCommon",IAReference,xml);
		edittext_common(application, "CPE Name", "TextValueCommon",CPEName,xml);
		
		click(application, "Rate Flag", "RateFlagRadio");
		addDropdownValues_common(application, "Billing Type", "SelectValueDropdown", "UBB - Per GigaByte", xml);
		//addDropdownValues_common(application, "CNG Option", "SelectValueDropdown", RouterIv6, xml);
		edittextFields_commonMethod(application, "Remark", "VPNRemark",CPEName,xml);
		
			click(application, "Next button", "VPNNext");
			Thread.sleep(3000);
			compareText(application, "Add VPN Site", "SuccessMessageCom", "Site Order successfully updated.");
			Thread.sleep(2000);

	}
	}
	public void EditVPNSiteOrder(String application, String ServiceSubType,String VPNCountry,String VPNDeviceCity,String VPNPhysicalSite,String VPNVendorModel,String VPNSiteOrder,String VPNSiteAlias,
			String VPNRouterId,String VPNManagementAdd,String VoipService,String HubSpoke,String Physical_Site) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")||ServiceSubType.equalsIgnoreCase("IP Voice")||ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		}
		else {
			WebElement HUB_header= getwebelement("//div[text()='Hub']");
			scrolltoview(HUB_header);
		}
		if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
       click(application, "VPN Edit Site Link", "VPN_EditSite");
		}
		else {
			click(application, "VPN HUB Edit Site Link", "VPN_EditSiteHub");
			
		}
		waitforPageenable();
		Thread.sleep(5000);
		//compareText(application, "Add VPN Site Order header", "AddVPNSitePage", "Add VPN Site Order");
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
			edittext_common(application, "Site Alias", "VPNSiteAlis", VPNSiteAlias,xml);
			}
		//click(application, "Toggle", "Togglebutton");
		//addDropdownValues_commonMethodDivSpan(application, "Physical Site", "SelectValueDropdown", Physical_Site, xml);
		//addDropdownValues_common(application, "Physical Site", "SelectValueDropdown", " (1232)", xml);
		
		edittext_common(application, "Physical Site", "VPNPhysicalSite",VPNPhysicalSite,xml);
		if(ServiceSubType.equalsIgnoreCase("SwiftNet")){
		addDropdownValues_common(application, "VPN Site Order Type", "SelectValueDropdown", HubSpoke, xml);
		}
		click(application, "VoIP", "VPNVoIP");
		/*
		//addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
		//scrolltoview(getwebelement("//div[text()='Device']"));
		//EnterTextValue(application, VPNRouterId, "Router Id", "VPNRouterId");
		//EnterTextValue(application, VPNManagementAdd, "Management Address", "VPNManagementAdd");
		//addDropdownValues_commonMethod(application, "Vendor/Model", "VPNVendoeModel", VPNVendorModel, xml);
		
		
		Clickon(getwebelement("//div[label[text()='Vendor/Model']]//input"));
		  Thread.sleep(2000);
		  
		  SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VPNVendorModel);	
		Thread.sleep(2000);
			
		 Clickon(getwebelement("(//div[contains(text(),'"+ VPNVendorModel +"')])[1]"));	
		 Thread.sleep(2000);
		 
		Clickon(getwebelement("//span[@aria-hidden='true']"));
		GetText(application, "Device Name", "VPNDeviceName");
		scrolltoend();
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
		click(application, "Generate Password", "VPNGeneratePass1");
		click(application, "Generate Password", "VPNGeneratePass2");
		}
		*/
		click(application, "OK in Edit HUB VPN Order", "VPN_OKbutton");
		waitforPageenable();
		Thread.sleep(5000);
		compareText(application, "Add VPN Site", "SuccessMessageCom", "Site Order successfully updated.");
		Thread.sleep(2000);
	}
	}

	public void EditVPNSiteOrderSpoke(String application, String ServiceSubType,String VPNCountry,String VPNDeviceCity,String VPNPhysicalSite,String VPNVendorModel,String VPNSiteOrder,String VPNSiteAlias,
			String VPNRouterId,String VPNManagementAdd,String VoipService,String HubSpoke,String Physical_Site) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		
			WebElement HUB_header= getwebelement("//div[text()='Hub']");
			scrolltoview(HUB_header);
		
		if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
       click(application, "VPN Edit Site Link", "VPN_EditSite");
		}
		else {
			click(application, "VPN Spoke Edit Site Link", "VPN_EditSiteSpoke");
			
		}
		waitforPageenable();
		Thread.sleep(5000);
		//compareText(application, "Add VPN Site Order header", "AddVPNSitePage", "Add VPN Site Order");
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
			edittext_common(application, "Site Alias", "VPNSiteAlis", VPNSiteAlias,xml);
			}
		//click(application, "Toggle", "Togglebutton");
		//addDropdownValues_commonMethodDivSpan(application, "Physical Site", "SelectValueDropdown", Physical_Site, xml);
		//addDropdownValues_common(application, "Physical Site", "SelectValueDropdown", " (1232)", xml);
		
		edittext_common(application, "Physical Site", "VPNPhysicalSite",VPNPhysicalSite,xml);
		if(ServiceSubType.equalsIgnoreCase("SwiftNet")){
		addDropdownValues_common(application, "VPN Site Order Type", "SelectValueDropdown", HubSpoke, xml);
		}
		click(application, "VoIP", "VPNVoIP");
		/*
		//addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
		//scrolltoview(getwebelement("//div[text()='Device']"));
		//EnterTextValue(application, VPNRouterId, "Router Id", "VPNRouterId");
		//EnterTextValue(application, VPNManagementAdd, "Management Address", "VPNManagementAdd");
		//addDropdownValues_commonMethod(application, "Vendor/Model", "VPNVendoeModel", VPNVendorModel, xml);
		
		
		Clickon(getwebelement("//div[label[text()='Vendor/Model']]//input"));
		  Thread.sleep(2000);
		  
		  SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VPNVendorModel);	
		Thread.sleep(2000);
			
		 Clickon(getwebelement("(//div[contains(text(),'"+ VPNVendorModel +"')])[1]"));	
		 Thread.sleep(2000);
		 
		Clickon(getwebelement("//span[@aria-hidden='true']"));
		GetText(application, "Device Name", "VPNDeviceName");
		scrolltoend();
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
		click(application, "Generate Password", "VPNGeneratePass1");
		click(application, "Generate Password", "VPNGeneratePass2");
		}
		*/
		click(application, "OK in Edit VPN Spoke ", "VPN_OKbutton");
		waitforPageenable();
		Thread.sleep(5000);
		compareText(application, "Add VPN Site", "SuccessMessageCom", "Site Order successfully updated.");
		Thread.sleep(2000);
	}
	}

	
	
	
	public void DeleteVPNSiteOrder(String application, String ServiceSubType,String VPNSiteOrder) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiteOrder + "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");

			
            ClickCommon(application, "Delete", "AddVPNSiteCommonLink");
	        
            Thread.sleep(2000);
	        waitforPageenable();
	        
	        Thread.sleep(2000);
			 click(application, "Delete", "DeleteAny");
		 		Thread.sleep(3000);
		         waitforPageenable();
		        // GetText(application, "Delete Interface", "SuccessMessageCom");
		  		compareText(application, "Delete VPN order", "SuccessMessageCom", "Site Order deleted successfully");
		 		Thread.sleep(2000);
		
		}
		
	}
	public void DeleteVPNSiteOrder4(String application, String ServiceSubType,String VPNSiteOrder) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")||ServiceSubType.equalsIgnoreCase("IP Voice")||ServiceSubType.equalsIgnoreCase("SwiftNet")) {
			if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			 click(application, "VPN Device Delete Link", "VPN_Deletedevice1");
			 Thread.sleep(2000);
				 click(application, "Delete", "DeleteAny");
			 		Thread.sleep(3000);
			         waitforPageenable();
			         GetText(application, "Delete Interface", "SuccessMessageCom");
			  		//compareText(application, "Delete Device", "SuccessMessageCom", "Static Route successfully deleted.");
			 		Thread.sleep(2000);
			 		
			 		scrolltoview(VPNSiteOrder_header);
			 		 click(application, "VPN Site Delete Link", "VPN_DeleteSite");
					 Thread.sleep(2000);
						 click(application, "Delete", "DeleteAny");
					 		Thread.sleep(3000);
					         waitforPageenable();
					       //  GetText(application, "Delete Interface", "SuccessMessageCom");
					  		compareText(application, "Delete Site Order", "SuccessMessageCom", "Site Order deleted successfully");
					 		Thread.sleep(2000);
						
			
			}
			else {
				WebElement HUB_header= getwebelement("//div[text()='Hub']");
				scrolltoview(HUB_header);
				 click(application, "Spoke Device Delete Link", "VPN_DeleteDeviceSpoke");
				 Thread.sleep(2000);
					 click(application, "Delete", "DeleteAny");
				 		Thread.sleep(3000);
				         waitforPageenable();
				         GetText(application, "Delete Interface", "SuccessMessageCom");
				  		//compareText(application, "Delete Device", "SuccessMessageCom", "Static Route successfully deleted.");
				 		Thread.sleep(2000);
				 		
				 		scrolltoview(HUB_header);
				 		 click(application, "Spoke Site Delete Link", "VPN_DeleteSiteSpoke");
						 Thread.sleep(2000);
							 click(application, "Delete", "DeleteAny");
						 		Thread.sleep(3000);
						         waitforPageenable();
						         GetText(application, "Delete Interface", "SuccessMessageCom");
						  		//compareText(application, "Delete Device", "SuccessMessageCom", "Static Route successfully deleted.");
						 		Thread.sleep(2000);
					
						 		scrolltoview(HUB_header);
								 click(application, "Hub Device Delete Link", "VPN_DeleteDeviceHub");
								 Thread.sleep(2000);
									 click(application, "Delete", "DeleteAny");
								 		Thread.sleep(3000);
								         waitforPageenable();
								         GetText(application, "Delete Interface", "SuccessMessageCom");
								  		//compareText(application, "Delete Device", "SuccessMessageCom", "Static Route successfully deleted.");
								 		Thread.sleep(2000);
								 		
								 		scrolltoview(HUB_header);
								 		 click(application, "Hub Site Delete Link", "VPN_DeleteSiteHub");
										 Thread.sleep(2000);
											 click(application, "Delete", "DeleteAny");
										 		Thread.sleep(3000);
										         waitforPageenable();
										         GetText(application, "Delete Interface", "SuccessMessageCom");
										  		//compareText(application, "Delete Device", "SuccessMessageCom", "Static Route successfully deleted.");
										 		Thread.sleep(2000);
											
							
				
				
			}
		
		}
	}
	


	public void verifyEditSiteFunction(String application,String EditVPNSiteAlias) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service

		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		
		click(application, "VPN Edit Site Link", "VPN_EditSite");
		
		Thread.sleep(10000);
		
		
		EnterTextValue(application, EditVPNSiteAlias, "Site Alias", "VPNSiteAlis");
		
		click(application, "OK in Edit MAS Device", "VPN_OKbutton");
		Thread.sleep(5000);
		compareText(application, "Site Order Update message", "VPN_EditSiteSuccessfulMessage", "Site Order successfully updated.");

		Log.info("------  Device updated successfully   ------");
	}

	
	
	
	public void verifyEditDeviceFunction(String application,String ServiceSubType,String VPNRouterId,String VPNManagementAddRe, String VPNVendorModel) throws InterruptedException, DocumentException, IOException {


		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")||ServiceSubType.equalsIgnoreCase("IP Voice")||ServiceSubType.equalsIgnoreCase("SwiftNet")) {
			if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			}
			else {
				WebElement HUB_header= getwebelement("//div[text()='Hub']");
				scrolltoview(HUB_header);
			}
			if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
				click(application, "VPN Edit Link", "VPN_editdevice1");
			}
			else {
				click(application, "VPN Edit HUB  Device Link", "VPN_EditDeviceHub");
				
			}
			waitforPageenable();
			Thread.sleep(3000);
			scrolltoview(getwebelement("//div[text()='Device']"));
			edittext_common(application, "Router Id", "VPNRouterId",VPNRouterId,xml);
			edittext_common(application, "Management Address", "VPNManagementAdd",VPNManagementAddRe,xml);
			addDropdownValues_commonMethod(application, "Vendor/Model", "VPNVendoeModel", VPNVendorModel, xml);
			
			
//			Clickon(getwebelement("//div[label[text()='Vendor/Model']]//input"));
//			  Thread.sleep(2000);
//			  
//			  SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VPNVendorModel);	
//			Thread.sleep(2000);
//				
//			 Clickon(getwebelement("(//div[contains(text(),'"+ VPNVendorModel +"')])[1]"));	
//			 Thread.sleep(2000);
//			 
//			Clickon(getwebelement("//span[@aria-hidden='true']"));
//			GetText(application, "Device Name", "VPNDeviceName");
			scrolltoend();
			if(ServiceSubType.equalsIgnoreCase("SwiftNet")||ServiceSubType.equalsIgnoreCase("IP Voice")) {
			EnterTextValueCommon(application, "Testing", "Snmp V3 Context Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Snmp V3 Context Engine ID", "TextValueCommon");
			EnterTextValueCommon(application, "Test1", "Snmp V3 Security User Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Snmp V3 Auth Password", "TextValueCommon");
			}
			if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
			click(application, "Generate Password", "VPNGeneratePass1");
			click(application, "Generate Password", "VPNGeneratePass2");
			}
		//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/VPN_Edit_PremiseLevel")));
		//click(application, "Generate Password", "VPNGeneratePass1");
		
		click(application, "OK in Edit HUB Device", "VPN_OKbutton");
		waitforPageenable();
		Thread.sleep(5000);
		compareText(application, "VPN Update message", "VPN_UpdateSwitchSuccessfulMessage", "Device successfully updated");

		Log.info("------  Device updated successfully   ------");
			
		}
	}



	public void verifyEditDeviceFunctionSpoke(String application,String ServiceSubType,String VPNRouterId,String VPNManagementAddRe, String VPNVendorModel) throws InterruptedException, DocumentException, IOException {


		if(ServiceSubType.equalsIgnoreCase("SwiftNet")) {
				WebElement HUB_header= getwebelement("//div[text()='Hub']");
				scrolltoview(HUB_header);
			
			if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
				click(application, "VPN Edit Link", "VPN_editdevice1");
			}
			else {
				click(application, "VPN Edit Spoke Device Link", "VPN_EditDeviceSpoke");
				
			}
			waitforPageenable();
			Thread.sleep(3000);
			scrolltoview(getwebelement("//div[text()='Device']"));
			edittext_common(application, "Router Id", "VPNRouterId",VPNRouterId,xml);
			edittext_common(application, "Management Address", "VPNManagementAdd",VPNManagementAddRe,xml);
			addDropdownValues_commonMethod(application, "Vendor/Model", "VPNVendoeModel", VPNVendorModel, xml);
			
			
//			Clickon(getwebelement("//div[label[text()='Vendor/Model']]//input"));
//			  Thread.sleep(2000);
//			  
//			  SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VPNVendorModel);	
//			Thread.sleep(2000);
//				
//			 Clickon(getwebelement("(//div[contains(text(),'"+ VPNVendorModel +"')])[1]"));	
//			 Thread.sleep(2000);
//			 
//			Clickon(getwebelement("//span[@aria-hidden='true']"));
//			GetText(application, "Device Name", "VPNDeviceName");
			scrolltoend();
			if(ServiceSubType.equalsIgnoreCase("SwiftNet")||ServiceSubType.equalsIgnoreCase("IP Voice")) {
			EnterTextValueCommon(application, "Testing", "Snmp V3 Context Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Snmp V3 Context Engine ID", "TextValueCommon");
			EnterTextValueCommon(application, "Test1", "Snmp V3 Security User Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Snmp V3 Auth Password", "TextValueCommon");
			}
			if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
			click(application, "Generate Password", "VPNGeneratePass1");
			click(application, "Generate Password", "VPNGeneratePass2");
			}
		//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/VPN_Edit_PremiseLevel")));
		//click(application, "Generate Password", "VPNGeneratePass1");
		
		click(application, "OK in Edit Spoke Device", "VPN_OKbutton");
		waitforPageenable();
		Thread.sleep(5000);
		compareText(application, "VPN Update message", "VPN_UpdateSwitchSuccessfulMessage", "Device successfully updated");

		Log.info("------  Device updated successfully   ------");
			
		}
	}


	public void AddNewDevice(String application, String ServiceSubType,String VPNVendorModel,String VPNRouterId,String VPNManagementAdd,String SamPName ) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")||ServiceSubType.equalsIgnoreCase("IP Voice")||ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		click(application, "Add New device Link", "VPN_AddNewDevice");
		}
		else {
			WebElement HUB_header= getwebelement("//div[text()='Hub']");
			scrolltoview(HUB_header);
			click(application, "Add New device Link", "HUB_AddNewDevice");
		}
		
        
		
		Thread.sleep(10000);
		
		scrolltoview(getwebelement("//div[text()='Device']"));
		EnterTextValue(application, VPNRouterId, "Router Id", "VPNRouterId");
		EnterTextValue(application, VPNManagementAdd, "Management Address", "VPNManagementAdd");
		//addDropdownValues_commonMethod(application, "Vendor/Model", "VPNVendoeModel", VPNVendorModel, xml);
		click(application, "Add Premise Toggle", "AddPrimierToggle");
		Thread.sleep(2000);
		EnterTextValueCommon(application, "Testing", "Premise Name", "TextValueCommon");
		EnterTextValueCommon(application, "123", "Premise Code", "TextValueCommon");
		
		
		
		Clickon(getwebelement("//div[label[text()='Vendor/Model']]//input"));
		  Thread.sleep(2000);
		  
		  SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VPNVendorModel);	
		Thread.sleep(2000);
			
		 Clickon(getwebelement("(//div[contains(text(),'"+ VPNVendorModel +"')])[1]"));	
		 Thread.sleep(2000);
		 
		 AcceptJavaScriptMethod();
		//Clickon(getwebelement("//span[@aria-hidden='true']"));
		
		GetText(application, "Device Name", "VPNDeviceName");
		scrolltoend();
		
		if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")) {
		EnterTextValue(application, SamPName, "Snmp V3 User Name", "VPNSampName");
		click(application, "Generate Password", "VPNGeneratePass1");
		click(application, "Generate Password", "VPNGeneratePass2");
		}
		else {
			EnterTextValueCommon(application, "Testing", "Snmp V3 Context Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Snmp V3 Context Engine ID", "TextValueCommon");
			EnterTextValueCommon(application, "Testing", "Snmp V3 Security User Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Snmp V3 Auth Password", "TextValueCommon");
			
			
		}
		if(ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		EnterTextValue(application, SamPName, "Snmp V3 User Name", "VPNSampName");
		}
		click(application, "OK in Edit", "VPN_OKbutton");
		Thread.sleep(5000);
		compareText(application, "Add Site Device Successful Message for VPN Order", "VPN_AddDeviceSuccessfulMessage", "Site device created successfully");
	Thread.sleep(2000);
	}
	else {
	ExtentTestManager.getTest().log(LogStatus.PASS, "Step :No Device Should Exit");
	}
	}

	public void AddWholesaleInterconnect(String application, String ServiceSubType,String WholesaleDeviceName,String WholesaleInterface) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")) {
			if(ServiceSubType.equalsIgnoreCase("IPVPN Connect")||ServiceSubType.equalsIgnoreCase("IPVPN IPSec")) {
				ScrolltoElement(application, "ConfigurationOption");
			}
			else {
			scrolltoend();
			}
			click(application, "Action dropdown", "Wholesaleactionbutton");
			click(application, "SelectInterconnect Link", "SelectInterconnectink");
			waitforPageenable();
			click(application, "Search Button", "WholesaleSearch");
			WarningMessageCom(application, "Device","WarningMessageddnCommon");
			Thread.sleep(5000);
			//waitforPageenable();
			//addDropdownValues_commonMethod(application, "Device", "WholesaleDevice", WholesaleDeviceName, xml);
			
			
			 Clickon(getwebelement("//div[label[text()='Device']]//div[text()='×']"));
			  Thread.sleep(4000); 
			 SendKeys(getwebelement("//div[label[text()='Device']]//input"), WholesaleDeviceName);	
			Thread.sleep(4000);
				
			  Clickon(getwebelement("//div[div[text()='Select Interconnect']]//following-sibling::div//div[text()='"+WholesaleDeviceName+"']"));
			  Thread.sleep(2000);
			  
			  String actualValue=getwebelement("//label[text()='Device']/following-sibling::div//span").getText();
			  ExtentTestManager.getTest().log(LogStatus.PASS, "Device" + " dropdown value selected as: "+ actualValue );
			  System.out.println( "Device" + " dropdown value selected as: "+ actualValue);
			  
		  
			
			
			
			
			
			EnterTextValueCommon(application, WholesaleInterface, "Interface Name/Address", "TextValueCommon");
			click(application, "Search Button", "WholesaleSearch");
			Thread.sleep(7000);
			//click(application, "Search Button", "WholesaleSearch");
			if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")) {
			click(application, "Select Interface", "WholesaleSelectPlus");
			}
			if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")) {
				click(application, "Select Interface", "WholesaleSelectIPSec");
				}
			if(ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
				click(application, "Select Interface", "WholesaleSelectAccess");
				}
			if(ServiceSubType.equalsIgnoreCase("IPVPN Connect")) {
				click(application, "Select Interface", "WholesaleSelectConnect");
				}
			Thread.sleep(3000);
			click(application, "Next", "WholesaleNext");
			waitforPageenable();
			Thread.sleep(3000);
			compareText(application, "Wholesale Interconnect selected", "SuccessMessageCom", "Wholesale Interconnect successfully selected.");
			Thread.sleep(2000);
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Add Wholesale Should not Present");
			}
	
	}
	
	public void EditWholesaleInterconnect(String application, String ServiceSubType,String IVManagement) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")) {
			if(ServiceSubType.equalsIgnoreCase("IPVPN Connect")||ServiceSubType.equalsIgnoreCase("IPVPN IPSec")) {
				ScrolltoElement(application, "ConfigurationOption");
			}
			else {
			scrolltoend();
			}
			click(application, "Select Device", "SelectWholesaleDevice");
			click(application, "Action dropdown", "Wholesaleactionbutton");
			click(application, "Edit Link", "WholesaleEditLink");
			waitforPageenable();
			Thread.sleep(8000);
			if(IVManagement.equalsIgnoreCase("YES")) {
			click(application, "IV Management", "IV_Management");
			}
			GetText(application, "Device Name", "GetDevice");
			GetText(application, "Device Name", "GetInterface");
			GetText(application, "Device Name", "GetAddress");
			click(application, "OK Button", "OKButtonWholesale");
			waitforPageenable();
			Thread.sleep(3000);
			compareText(application, "Wholesale Interconnect updated", "SuccessMessageCom", "Wholesale Interconnect interface successfully updated.");
			Thread.sleep(2000);
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edit  Wholesale Should not Present");
			}
	}
	
	
	public void DeleteWholesaleInterconnect(String application, String ServiceSubType, String Delete) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")) {
			if(ServiceSubType.equalsIgnoreCase("IPVPN Connect")||ServiceSubType.equalsIgnoreCase("IPVPN IPSec")) {
				ScrolltoElement(application, "ConfigurationOption");
			}
			else {
			scrolltoend();
			}
			if(Delete.equalsIgnoreCase("Yes")) {
			click(application, "Select Device", "SelectWholesaleDevice");
			click(application, "Action dropdown", "Wholesaleactionbutton");
			click(application, "Delete link", "WholesaleDeleteLink");
			waitforPageenable();
			Thread.sleep(8000);
			click(application, "Delete Wholesale", "DeleteCommon");
			waitforPageenable();
			Thread.sleep(3000);
			compareText(application, "Wholesale Interconnect Deleted", "SuccessMessageCom", "Wholesale Interconnect interface successfully removed from service.");
			Thread.sleep(2000);
			}
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Delete  Wholesale Should not Present");
			}
	}
	
	public void AddCPEDevice(String application, String ServiceSubType,String VPNVendorModel,String VPNRouterId,String VPNManagementAdd,String VPNSiterderNum,String GetAddressCPE,String AvailableBlockCPE,
			String PremiseName,String PremiseCode,String PremiseToggle,String JitterCPE,String ConnectProtocoltal,String ConnectProtocolssh, String Premise,String SNMP3) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers==1)
		{
			click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
			Thread.sleep(3000);
		}
		else if(NoOfUsers>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

		}

		click(application, "Action dropdown", "VPNSiteactionbutton");
		ClickCommon(application, "View", "AddVPNSiteCommonLink");
		waitforPageenable();
       // compareText_Common(application, "Site Order Number", "CompareTextCommon", "View VPN Order", xml);
        scrolltoend();
        click(application, "CPE Device Link", "CPE_AddPEDeviceLink");
        waitforPageenable();
        scrolltoend();
		click(application, "Next button", "VPNNext");
		WarningMessageCom(application, "Router Id","WarningMessageCommon");
		WarningMessageCom(application, "Management Address","WarningMessageCommon");
		if(ServiceSubType.equalsIgnoreCase("IPVPN Access")){
			WarningMessageCom(application, "Snmp V3 Priv Password","WarningMessageCommon");	
		}
		WarningMessageCom(application, "Vendor/Model","WarningMessageddnCommon");
		EnterTextValue(application, VPNRouterId, "Router Id", "VPNRouterId");
		if(GetAddressCPE.equalsIgnoreCase("Yes")) {
			click(application, "Get Address button", "GetAddressCPE");
			Thread.sleep(2000);
			addDropdownValues_common(application, "Available Blocks", "SelectValueDropdown", AvailableBlockCPE, xml);
			Thread.sleep(6000);
			click(application, "Get Free IP button", "FreeIPCPE");
			Thread.sleep(5000);
		}
		else {
		EnterTextValue(application, VPNManagementAdd, "Management Address", "VPNManagementAdd");
		}
		//addDropdownValues_commonMethod(application, "Vendor/Model", "VPNVendoeModel", VPNVendorModel, xml);
		
		
		Clickon(getwebelement("//div[label[text()='Vendor/Model']]//input"));
		  Thread.sleep(2000);
		  
		  SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VPNVendorModel);	
		Thread.sleep(2000);
			
		 Clickon(getwebelement("(//div[contains(text(),'"+ VPNVendorModel +"')])[1]"));	
		 Thread.sleep(2000);
		 AcceptJavaScriptMethod();
		//Clickon(getwebelement("//span[@aria-hidden='true']"));
		GetText(application, "Device Name", "VPNDeviceName");
		if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")||ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
		scrolltoend();
		}
		else {
			ScrolltoElementComm(application, "Device Name", "TextValueCommon");
		}
		if(PremiseToggle.equalsIgnoreCase("Yes")) {
			click(application, "Add Premise Toggle", "AddPrimiseToggle");
			EnterTextValueCommon(application, PremiseName, "Premise Name", "TextValueCommon");
			EnterTextValueCommon(application, PremiseCode, "Premise Code", "TextValueCommon");	
		}
		else {
			addDropdownValues_common(application, "Premise", "SelectValueDropdown", Premise, xml);
			
		}
		
		if(JitterCPE.equalsIgnoreCase("Yes")) {
			ClickCommon(application, "Jitter Reporting", "CheckboxCommon");
		}
		if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")||ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
		if(ConnectProtocoltal.equalsIgnoreCase("Yes")) {
			click(application, "Connectivity Portal Telnet", "ConnectivityProtocolCPEtelnet");
			Thread.sleep(3000);
		}
		if(ConnectProtocolssh.equalsIgnoreCase("Yes")) {
			click(application, "Connectivity Portal SSH", "ConnectivityProtocolCPEssh");
		}
		if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")) {
		
		if(SNMP3.equalsIgnoreCase("Yes")) {
			click(application, "SNMP Version 3", "SNMPCpe3");
			ClearAndEnterTextValueComm(application, "Snmp V3 User Name", "TextValueCommon", "Colt");
			ClearAndEnterTextValueComm(application, "Snmp V3 Auth Password", "TextValueCommon", "Colt123#");
			ClearAndEnterTextValueComm(application, "Snmp V3 Priv Password", "TextValueCommon", "Colt654@12");
		}
		else
		{
			click(application, "SNMP Version 2c", "SNMPCpe2c");
			ClearAndEnterTextValueComm(application, "Snmpro", "TextValueCommon", "Colt");
			ClearAndEnterTextValueComm(application, "Snmprw", "TextValueCommon", "Colt123");
		}
		}
		if(ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			if(SNMP3.equalsIgnoreCase("Yes")) {
				click(application, "SNMP Version 3", "SNMPCpe3");
				EnterTextValueCommon(application, "Colt", "Snmp V3 User Name", "TextValueCommon");
				EnterTextValueCommon(application, "Colt123", "Snmp V3 Auth Password", "TextValueCommon");
				EnterTextValueCommon(application, "Colt654@12", "Snmp V3 Priv Password", "TextValueCommon");
					}
			else
			{
				click(application, "SNMP Version 2c", "SNMPCpe2c");
				//EnterTextValueCommon(application, "Colt", "Snmpro", "TextValueCommon");
				//EnterTextValueCommon(application, "Colt123", "Snmprw", "TextValueCommon");
				ClearAndEnterTextValueComm(application, "Snmpro", "TextValueCommon", "Colt");
				ClearAndEnterTextValueComm(application, "Snmprw", "TextValueCommon", "Colt123");
			}
			}
		}
		else {
			EnterTextValueCommon(application, "Colt", "Snmp V3 User Name", "TextValueCommon");
			EnterTextValueCommon(application, "Colt123", "Snmp V3 Auth Password", "TextValueCommon");
			EnterTextValueCommon(application, "Colt32", "Snmp V3 Priv Password", "TextValueCommon");
			EnterTextValueCommon(application, "ColtTest", "Snmp V3 Context Name", "TextValueCommon");
			EnterTextValueCommon(application, "123", "Snmp V3 Context Engine ID", "TextValueCommon");
			EnterTextValueCommon(application, "ColtTest", "Snmp V3 Security User Name", "TextValueCommon");
			//addDropdownValues_common(application, "Snmp v3 Auth Proto", "SelectValueDropdown", "", xml);
			ClearAndEnterTextValueComm(application, "Snmpro", "TextValueCommon", "Colt");
			ClearAndEnterTextValueComm(application, "Snmprw", "TextValueCommon", "Colt123");	
		 }
		scrolltoend();
		click(application, "Next button", "VPNNext");
		waitforPageenable();
		Thread.sleep(3000);
		compareText(application, "Add CPE Device", "SuccessMessageCom", "Device successfully created.");
		Thread.sleep(2000);

    
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}
	
	public void ViewCPEDevice(String application, String ServiceSubType,String VPNVendorModel,String VPNRouterId,String VPNManagementAdd,String VPNSiterderNum,String GetAddressCPE,String AvailableBlockCPE,
			String PremiseName,String PremiseCode,String PremiseToggle,String JitterCPE,String VPNCountry,String VPNDeviceCity, String CSRName) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);

		if(NoOfDevice<=5)
		{
			click(application, "View CPE Device", "ViewCPEDevice");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>5)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'1')]/parent::span/parent::div//following-sibling::div//span[text()='View']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device View button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
		scrollToTop();
		Thread.sleep(10000);
		
		GetTextCommon(application, "Router Id", "CompareTextCommon");
        GetTextCommon(application, "Name", "CompareTextCommon");
        GetTextCommon(application, "Country", "CompareTextCommon");
		 //compareText_Common(application, "Router Id", "CompareTextCommon", VPNRouterId, xml);
	       // compareText_Common(application, "Name", "CompareTextCommon", VPNRouterId, xml);
	        //compareText_Common(application, "Country", "CompareTextCommon", VPNCountry, xml);
	        ScrolltoElementComm(application, "Router Id", "CompareTextCommon");
	        GetTextCommon(application, "City", "CompareTextCommon");
	        GetTextCommon(application, "Site", "CompareTextCommon");
	        GetTextCommon(application, "Vendor/Model", "CompareTextCommon");
	        GetTextCommon(application, "Jitter Reporting", "CompareTextCommon");
	        GetTextCommon(application, "Premise", "CompareTextCommon");
	        if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")) {
	        GetTextCommon(application, "Snmp v3 Username", "CompareTextCommon");
	        GetTextCommon(application, "Snmp v3 Auth password", "CompareTextCommon");
	        GetTextCommon(application, "Snmp v3 Priv password", "CompareTextCommon");
	        }
	       /* compareText_Common(application, "City", "CompareTextCommon", VPNDeviceCity, xml);
	        compareText_Common(application, "Site", "CompareTextCommon", CSRName, xml);
	        compareText_Common(application, "Vendor/Model", "CompareTextCommon", VPNVendorModel, xml);
	        compareText_Common(application, "Jitter Reporting", "CompareTextCommon", JitterCPE, xml);
	        compareText_Common(application, "Premise", "CompareTextCommon", PremiseName, xml);
	        compareText_Common(application, "Snmp v3 Username", "CompareTextCommon", "Colt", xml);
	        compareText_Common(application, "Snmp v3 Auth password", "CompareTextCommon", "Colt123#", xml);
	        compareText_Common(application, "Snmp v3 Priv password", "CompareTextCommon", "Colt654@12", xml);
	        */
		       
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}
	
	
	public void EditCPEDevice(String application, String ServiceSubType,String VPNVendorModel,String VPNRouterId,String VPNManagementAdd,String VPNSiterderNum,String GetAddressCPE,String AvailableBlockCPE,
			String PremiseName,String PremiseCode,String PremiseToggle,String JitterCPE,String ConnectProtocoltal,String ConnectProtocolssh, String Premise,String SNMP3) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers==1)
		{
			click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
			Thread.sleep(3000);
		}
		else if(NoOfUsers>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

		}

		click(application, "Action dropdown", "VPNSiteactionbutton");
		ClickCommon(application, "View", "AddVPNSiteCommonLink");
		waitforPageenable();
       // compareText_Common(application, "Site Order Number", "CompareTextCommon", "View VPN Order", xml);
        scrolltoend();
        
        List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);

		if(NoOfDevice<=51)
		{
			click(application, "Edit CPE Device", "EditCPEDevice");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>5)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//b[contains(text(),'" + VPNRouterId + "')]//parent::*//parent::div//following-sibling::div//span[contains(text(),'Edit')]"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device Edit button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
        scrolltoend();
		click(application, "Cancel button", "CancelCPEDevice");
		Thread.sleep(3000);
        waitforPageenable();
         scrolltoend();
        
        List<WebElement> ExistingDevices1= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices1 = ExistingDevices1.size();
		int NoOfDevice1=NoOfDevices1-1;
		System.out.println("Numberofuser="+ NoOfDevice1);

		if(NoOfDevice1==1)
		{
			click(application, "Edit CPE Device", "EditCPEDevice");
			Thread.sleep(3000);
		}
		else if(NoOfDevice1>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'1')]/parent::span/parent::div//following-sibling::div//span[text()='Edit']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device Edit button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
           scrollToTop();
         ClearAndEnterTextValueComm(application, "Router Id", "VPNRouterId", VPNRouterId);            
		//EnterTextValue(application, VPNRouterId, "Router Id", "VPNRouterId");
		if(GetAddressCPE.equalsIgnoreCase("Yes")) {
			click(application, "Get Address button", "GetAddressCPE");
			Thread.sleep(2000);
			addDropdownValues_common(application, "Available Blocks", "SelectValueDropdown", AvailableBlockCPE, xml);
			Thread.sleep(6000);
			click(application, "Get Free IP button", "FreeIPCPE");
			Thread.sleep(5000);
		}
		else {
			 ClearAndEnterTextValueComm(application, "Management Address", "VPNManagementAdd", VPNManagementAdd);
		//EnterTextValue(application, VPNManagementAdd, "Management Address", "VPNManagementAdd");
		}
		//addDropdownValues_commonMethod(application, "Vendor/Model", "VPNVendoeModel", VPNVendorModel, xml);
		
		
		Clickon(getwebelement("//div[label[text()='Vendor/Model']]//input"));
		  Thread.sleep(2000);
		  
		  SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VPNVendorModel);	
		Thread.sleep(2000);
			
		 Clickon(getwebelement("(//div[contains(text(),'"+ VPNVendorModel +"')])[1]"));	
		 Thread.sleep(2000);
		 AcceptJavaScriptMethod();
		//Clickon(getwebelement("//span[@aria-hidden='true']"));
		GetText(application, "Device Name", "VPNDeviceName");
		//ScrolltoElementComm(application, "Device Name", "VPNDeviceName");
		scrolltoend();
		if(PremiseToggle.equalsIgnoreCase("Yes")) {
			click(application, "Add Premise Toggle", "AddPrimiseToggle");
			 ClearAndEnterTextValueComm(application, "Premise Name", "TextValueCommon", PremiseName);
			 ClearAndEnterTextValueComm(application, "Premise Code", "TextValueCommon", PremiseCode);
		//	EnterTextValueCommon(application, PremiseName, "Premise Name", "TextValueCommon");
			//EnterTextValueCommon(application, PremiseCode, "Premise Code", "TextValueCommon");	
		}
		else {
			addDropdownValues_common(application, "Premise", "SelectValueDropdown", Premise, xml);
			
		}
		
		if(JitterCPE.equalsIgnoreCase("Yes")) {
			ClickCommon(application, "Jitter Reporting", "CheckboxCommon");
		}
		if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")||ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
		if(ConnectProtocoltal.equalsIgnoreCase("Yes")) {
			click(application, "Connectivity Portal Telnet", "ConnectivityProtocolCPEtelnet");
			Thread.sleep(3000);
		}
		if(ConnectProtocolssh.equalsIgnoreCase("Yes")) {
			click(application, "Connectivity Portal SSH", "ConnectivityProtocolCPEssh");
		}
		if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")) {
		
		if(SNMP3.equalsIgnoreCase("Yes")) {
			click(application, "SNMP Version 3", "SNMPCpe3");
			ClearAndEnterTextValueComm(application, "Snmp V3 User Name", "TextValueCommon", "Colt");
			ClearAndEnterTextValueComm(application, "Snmp V3 Auth Password", "TextValueCommon", "Colt123#");
			ClearAndEnterTextValueComm(application, "Snmp V3 Priv Password", "TextValueCommon", "Colt654@12");
		}
		else
		{
			click(application, "SNMP Version 2c", "SNMPCpe2c");
			ClearAndEnterTextValueComm(application, "Snmpro", "TextValueCommon", "Colt");
			ClearAndEnterTextValueComm(application, "Snmprw", "TextValueCommon", "Colt123");
		}
		}
		if(ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			if(SNMP3.equalsIgnoreCase("Yes")) {
				click(application, "SNMP Version 3", "SNMPCpe3");
				ClearAndEnterTextValueComm(application, "Snmp V3 User Name", "TextValueCommon", "Colt");
				ClearAndEnterTextValueComm(application, "Snmp V3 Auth Password", "TextValueCommon", "Colt123");
				ClearAndEnterTextValueComm(application, "Snmp V3 Priv Password", "TextValueCommon", "Colt654@12");
				
				//EnterTextValueCommon(application, "Colt", "Snmp V3 User Name", "TextValueCommon");
				//EnterTextValueCommon(application, "Colt123", "Snmp V3 Auth Password", "TextValueCommon");
				//EnterTextValueCommon(application, "Colt654@12", "Snmp V3 Priv Password", "TextValueCommon");
					}
			else
			{
				click(application, "SNMP Version 2c", "SNMPCpe2c");
				//EnterTextValueCommon(application, "Colt", "Snmpro", "TextValueCommon");
				//EnterTextValueCommon(application, "Colt123", "Snmprw", "TextValueCommon");
				ClearAndEnterTextValueComm(application, "Snmpro", "TextValueCommon", "Colt");
				ClearAndEnterTextValueComm(application, "Snmprw", "TextValueCommon", "Colt123");
			}
			}
		}
		else {
			
			ClearAndEnterTextValueComm(application, "Snmp V3 User Name", "TextValueCommon", "Colt");
			ClearAndEnterTextValueComm(application, "Snmp V3 Auth Password", "TextValueCommon", "Colt123");
			ClearAndEnterTextValueComm(application, "Snmp V3 Priv Password", "TextValueCommon", "Colt32");
			ClearAndEnterTextValueComm(application, "Snmp V3 Context Name", "TextValueCommon", "ColtTest");
			ClearAndEnterTextValueComm(application, "Snmp V3 Context Engine ID", "TextValueCommon", "123");
			ClearAndEnterTextValueComm(application, "Snmp V3 Security User Name", "TextValueCommon", "ColtTe");
			
			//EnterTextValueCommon(application, "Colt", "Snmp V3 User Name", "TextValueCommon");
			//EnterTextValueCommon(application, "Colt123", "Snmp V3 Auth Password", "TextValueCommon");
			//EnterTextValueCommon(application, "Colt32", "Snmp V3 Priv Password", "TextValueCommon");
			//EnterTextValueCommon(application, "ColtTest", "Snmp V3 Context Name", "TextValueCommon");
			//EnterTextValueCommon(application, "123", "Snmp V3 Context Engine ID", "TextValueCommon");
			//EnterTextValueCommon(application, "ColtTest", "Snmp V3 Security User Name", "TextValueCommon");
			//addDropdownValues_common(application, "Snmp v3 Auth Proto", "SelectValueDropdown", "", xml);
			ClearAndEnterTextValueComm(application, "Snmpro", "TextValueCommon", "Colt");
			ClearAndEnterTextValueComm(application, "Snmprw", "TextValueCommon", "Colt123");	
		 }

		scrolltoend();
		click(application, "Next button", "VPNNext");
		waitforPageenable();
		Thread.sleep(3000);
		compareText(application, "Add CPE Device", "SuccessMessageCom", "Device successfully updated.");
		Thread.sleep(2000);

    
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}
	
	public void DeleteCPEDevice(String application, String ServiceSubType,String VPNVendorModel,String VPNRouterId,String VPNManagementAdd,String VPNSiterderNum,String GetAddressCPE,String AvailableBlockCPE,
			String PremiseName,String PremiseCode,String PremiseToggle,String JitterCPE,String ConnectProtocoltal,String ConnectProtocolssh, String Premise,String SNMP3) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers==1)
		{
			click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
			Thread.sleep(3000);
		}
		else if(NoOfUsers>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

		}

		click(application, "Action dropdown", "VPNSiteactionbutton");
		ClickCommon(application, "View", "AddVPNSiteCommonLink");
		waitforPageenable();
       // compareText_Common(application, "Site Order Number", "CompareTextCommon", "View VPN Order", xml);
        scrolltoend();
        
        List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);
		Thread.sleep(3000);
		if(NoOfDevice<5)
		{
			click(application, "Delete CPE Device", "DeleteCPE");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>5)
		{
			
			WebElement AddedUser = driver
					.findElement(By.xpath("//span[contains(text(),'"+VPNManagementAdd+"')]//parent::div//parent::div//div//span[text()='Delete']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device Edit button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		 Thread.sleep(2000);
	        waitforPageenable();
	        
	        Thread.sleep(2000);
			 click(application, "Delete", "DeleteAny");
		 		Thread.sleep(3000);
		         waitforPageenable();
		         GetText(application, "Delete Interface", "SuccessMessageCom");
		  		//compareText(application, "Delete Device", "SuccessMessageCom", "Static Route successfully deleted.");
		 		Thread.sleep(2000);
					}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	public void CPEDeviceConfiguration(String application, String ServiceSubType,String VPNVendorModel,String VPNRouterId,String VPNManagementAdd,String VPNSiterderNum,String GetAddressCPE,String AvailableBlockCPE,
			String PremiseName,String PremiseCode,String PremiseToggle,String JitterCPE,String ConnectProtocoltal,String ConnectProtocolssh, String Premise,String SNMP3) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers==1)
		{
			click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
			Thread.sleep(3000);
		}
		else if(NoOfUsers>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

		}

		click(application, "Action dropdown", "VPNSiteactionbutton");
		ClickCommon(application, "View", "AddVPNSiteCommonLink");
		waitforPageenable();
       // compareText_Common(application, "Site Order Number", "CompareTextCommon", "View VPN Order", xml);
        scrolltoend();
        
        List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);
		Thread.sleep(3000);
		if(NoOfDevice<5)
		{
			click(application, "Delete CPE Device", "ConfigureCPE");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>5)
		{
			
			WebElement AddedUser = driver
					.findElement(By.xpath("//span[contains(text(),'"+VPNManagementAdd+"')]//parent::div//parent::div//div//span[text()='Configure']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device Edit button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		 Thread.sleep(2000);
	        waitforPageenable();
	        WebElement Configuration_header= getwebelement("//div[text()='Configuration']");
			scrolltoview(Configuration_header);
			
	        if(ServiceSubType.equalsIgnoreCase("IPVPN Plus")) {
	        Clickon(getwebelement("//span[text()='Generate Configuration']"));
 			Thread.sleep(2000);
 			waitforPageenable();
 			ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Generate Button");
 			 Clickon(getwebelement("//span[text()='Save Configuration']"));
  			Thread.sleep(2000);
  			waitforPageenable();
  			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Save Configuration");
  			 Clickon(getwebelement("//span[text()='Execute Configuration on Device']"));
  			Thread.sleep(2000);
  			waitforPageenable();
  			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Execute Configuration");
	        }
	        else if(ServiceSubType.equalsIgnoreCase("IPVPN Connect")|| ServiceSubType.equalsIgnoreCase("IPVPN IPSec")) {
	        	addDropdownValues_common(application, "Generate Configuration For", "SelectValueDropdown", "Full Configuration", xml);
	        Thread.sleep(2000);
	        	Clickon(getwebelement("//span[text()='Generate']"));
	   			Thread.sleep(2000);
	   			waitforPageenable();
	   			ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Generate Button");
	   		 Clickon(getwebelement("//span[text()='Save Configuration']"));
	  			Thread.sleep(2000);
	  			waitforPageenable();
	  			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Save Configuration");
	  			 Clickon(getwebelement("//span[text()='Execute Configuration']"));
	   			Thread.sleep(2000);
	   			waitforPageenable();
	   			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Execute Configuration");
	   			
	        	
	        }
	        scrolltoend();
  			 Clickon(getwebelement("//span[text()='Back']"));
  			Thread.sleep(2000);
  			waitforPageenable();
  			
 			
	        
	      				}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	public void AddInterfaceCPE(String application, String ServiceSubType,String CPEBearerType,String CPEBandwidthE1,String CPEBandwidthE3,String CPEBandwidthSTM,String CPELink,String CpeEncapsulation,
			String CpeClockSource,String CpePrimary_Backup,String CpenterfaceDirection,String CpeVoiceline,String CpeIVManagement,String CpeIVBitCounter, String CpeAddRange,String CpeSecondaryIp,String CpeNetmask,
			String CpeSpeed,String CpeDuplex,String CpeInterface,String CpeInterfaceAddRangeDdn,String CpeInterfaceAddRangeText,String CpeAddress ,String CPEGetAddress,String VPNRouterId,String VPNSiterderNum,String CpeAddInterfaceButton,
			String InterfaceCNGReferance) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);

		if(NoOfDevice==1)
		{
			click(application, "View CPE Device", "ViewCPEDevice");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'1')]/parent::span/parent::div//following-sibling::div//span[text()='View']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device View button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
		WebElement Interface_header= getwebelement("//div[text()='Routes']");
		scrolltoview(Interface_header);
		click(application, "Action dropdown", "InterfaceActionButton");
		ClickCommon(application, "Add interface/link ", "InterfaceActionCommonLink");
		waitforPageenable();
       
		click(application, "Save button", "SaveInterface");
		Thread.sleep(3000);
        waitforPageenable();
        scrollToTop();
        List<WebElement> WarningMssg= driver.findElements(By.xpath("//div[@role='alert']//span//li"));
		int MsgSize = WarningMssg.size();
		System.out.println("Numberofuser="+ MsgSize);
         for(int i=0;i<MsgSize;i++) {
        	 String msg= WarningMssg.get(i).getText();
        	 ExtentTestManager.getTest().log(LogStatus.PASS, msg + "   Displayed as Warning msg"); 
        	 }
         EnterTextValueCommon(application, CpeInterface, "Interface", "TextValueCommon");
         ScrolltoElementComm(application, "Interface", "TextValueCommon");
         if(CPEGetAddress.equalsIgnoreCase("Yes")) {
 			click(application, "Get Address button", "GetAddressCPE");
 			Thread.sleep(5000);
 			click(application, "Allocate Subnet button", "AllocateSubnetButton");
 			Thread.sleep(3000);
 			waitforPageenable();
 			// addDropdownValues_common(application, "Interface Address Range", "SelectValueDropdown", CpeInterfaceAddRangeDdn, xml);
 			Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[1]"));
 			Thread.sleep(2000);
 			waitforPageenable();
 			addDropdownValues_common(application, "Address", "SelectValueDropdown", CpeAddress, xml);
 			
 		}
 		else {
 			 EnterTextValueCommon(application, CpeInterfaceAddRangeText, "Interface Address Range", "TextValueCommon");
 			Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[1]"));
 			Thread.sleep(2000);
 			waitforPageenable();
 			// EnterTextValueCommon(application, CpeAddress, "Address", "TextValueCommon");
 	        
 			} 	
         ScrolltoElementComm(application, "Bearer Type", "SelectValueDropdown");
         if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")||ServiceSubType.equalsIgnoreCase("IPVPN Connect")) {
         addDropdownValues_common(application, "CNG Reference", "SelectValueDropdown", InterfaceCNGReferance, xml);
         }
         if(!CPEBearerType.equalsIgnoreCase("Ethernet")) {
 			
         addDropdownValues_common(application, "Bearer Type", "SelectValueDropdown", CPEBearerType, xml);
         Thread.sleep(2000);
         waitforPageenable();
         if(CPEBearerType.equalsIgnoreCase("E3")||CPEBearerType.equalsIgnoreCase("T3")) {
        	 addDropdownValues_common(application, "Bandwidth", "SelectValueDropdown", CPEBandwidthE3, xml);    
         }
         if(CPEBearerType.equalsIgnoreCase("STM-1")) {
        	 addDropdownValues_common(application, "Bandwidth", "SelectValueDropdown", CPEBandwidthSTM, xml);    
         }
         if(CPEBearerType.equalsIgnoreCase("E1")) {
        	 addDropdownValues_common(application, "Bandwidth", "SelectValueDropdown", CPEBandwidthE1, xml);    
         }
        
         addDropdownValues_common(application, "Encapsulation", "SelectValueDropdown", CpeEncapsulation, xml);
               //addDropdownValues_common(application, "Clock Source", "SelectValueDropdown", CpeClockSource, xml);
        // addDropdownValues_common(application, "Primary/Backup", "SelectValueDropdown", CpePrimary_Backup, xml);
       //  addDropdownValues_common(application, "Interface Direction", "SelectValueDropdown", CpenterfaceDirection, xml);
         Clickon(getwebelement("//div[div[label[text()='Clock Source']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Clock Source']]]//select//option[@label='"+ CpeClockSource +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Clock Source" + " dropdown value selected as: "+ CpeClockSource );
         Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select//option[@label='"+ CpePrimary_Backup +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Primary/Backup" + " dropdown value selected as: "+ CpePrimary_Backup );
         Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select//option[@label='"+ CpenterfaceDirection +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Direction" + " dropdown value selected as: "+ CpenterfaceDirection );
         EnterTextValueCommon(application, CpeVoiceline, "Voice Line Reference", "TextValueCommon");
         
         }
        
         if(CPEBearerType.equalsIgnoreCase("Ethernet")) {
  			
             addDropdownValues_common(application, "Bearer Type", "SelectValueDropdown", CPEBearerType, xml);
             Thread.sleep(2000);
             waitforPageenable();
            addDropdownValues_common(application, "Speed", "SelectValueDropdown", CpeSpeed, xml);                
             addDropdownValues_common(application, "Encapsulation", "SelectValueDropdown", CpeEncapsulation, xml);
             addDropdownValues_common(application, "Duplex", "SelectValueDropdown", CpeDuplex, xml);
             Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select"));
             Thread.sleep(2000);
             Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select//option[@label='"+ CpePrimary_Backup +"']"));
             ExtentTestManager.getTest().log(LogStatus.PASS, "Primary/Backup" + " dropdown value selected as: "+ CpePrimary_Backup );
             Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select"));
             Thread.sleep(2000);
             Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select//option[@label='"+ CpenterfaceDirection +"']"));
             ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Direction" + " dropdown value selected as: "+ CpenterfaceDirection );
             EnterTextValueCommon(application, CpeVoiceline, "Voice Line Reference", "TextValueCommon");
              }
         if(CpeAddInterfaceButton.equalsIgnoreCase("Yes")) {
         click(application, "Add button", "AddInterfacebutton");
         EnterTextValueCommon(application, CpeInterfaceAddRangeText, "Link", "TextValueCommon");
         	 
         }
         else {
        	  addDropdownValues_common(application, "Link", "SelectValueDropdown", CPELink, xml);
         }
         if(CpeIVManagement.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "IV management", "CheckboxCommon");
         }
         if(CpeIVBitCounter.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "IV 64 Bit Counter", "CheckboxCommon");
         }
         scrolltoend();
         if(CpeAddInterfaceButton.equalsIgnoreCase("Yes")) {
             click(application, "Add button", "AddInterfacebutton2");
             Clickon(getwebelement("//span[text()='Remove']"));
   			Thread.sleep(2000);
   			click(application, "Add button", "AddInterfacebutton2");
   			Thread.sleep(2000);
             EnterTextValueCommon(application, CpeAddRange, "Address Range", "TextValueCommon");
             Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[2]"));
  			Thread.sleep(2000);
  			waitforPageenable();
            // EnterTextValueCommon(application, CpeSecondaryIp, "Secondary IP", "TextValueCommon");
            // EnterTextValueCommon(application, CpeNetmask, "Netmask", "TextValueCommon");
             
             }
         scrolltoend();
         click(application, "Save button", "SaveInterface");
 		Thread.sleep(3000);
         waitforPageenable();
 		compareText(application, "Add CPE Device", "SuccessMessageCom", "Interface successfully created.");
 		Thread.sleep(2000);
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	
	public void EditInterfaceCPE(String application, String ServiceSubType,String CPEBearerType,String CPEBandwidthE1,String CPEBandwidthE3,String CPEBandwidthSTM,String CPELink,String CpeEncapsulation,
			String CpeClockSource,String CpePrimary_Backup,String CpenterfaceDirection,String CpeVoiceline,String CpeIVManagement,String CpeIVBitCounter, String CpeAddRange,String CpeSecondaryIp,String CpeNetmask,
			String CpeSpeed,String CpeDuplex,String CpeInterface,String CpeInterfaceAddRangeDdn,String CpeInterfaceAddRangeText,String CpeAddress ,String CPEGetAddress,String VPNRouterId,String VPNSiterderNum,String CpeAddInterfaceButton,
			String InterfaceCNGReferance) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		        click(application, "Show Interface Link", "CPEShowInterfaces");
	        Thread.sleep(3000);
	        WebElement InterfaceRadio = driver
					.findElement(By.xpath("//div[contains(text(),'" + CpeInterface + "')]//parent::div//div//input"));
	        InterfaceRadio.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing Interface radio button");
			  click(application, "Interface Action Button", "InterfaceActionButtonCPE");
		    click(application, "Interface Edit Link", "InterfaceEditLink");
	        Thread.sleep(2000);
	        waitforPageenable();

	        ClearAndEnterTextValueComm(application, "Interface", "TextValueCommon", CpeInterface);
        // EnterTextValueCommon(application, CpeInterface, "Interface", "TextValueCommon");
         ScrolltoElementComm(application, "Interface", "TextValueCommon");
         if(CPEGetAddress.equalsIgnoreCase("Yes")) {
 			click(application, "Get Address button", "GetAddressCPE");
 			Thread.sleep(5000);
 			click(application, "Allocate Subnet button", "AllocateSubnetButton");
 			Thread.sleep(3000);
 			waitforPageenable();
 			// addDropdownValues_common(application, "Interface Address Range", "SelectValueDropdown", CpeInterfaceAddRangeDdn, xml);
 			Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[1]"));
 			Thread.sleep(2000);
 			waitforPageenable();
 			addDropdownValues_common(application, "Address", "SelectValueDropdown", CpeAddress, xml);
 			
 		}
 		else {
 			 ClearAndEnterTextValueComm(application, "Interface Address Range", "TextValueCommon", CpeInterfaceAddRangeText);
 			// EnterTextValueCommon(application, CpeInterfaceAddRangeText, "Interface Address Range", "TextValueCommon");
 			Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[1]"));
 			Thread.sleep(2000);
 			waitforPageenable();
 			// EnterTextValueCommon(application, CpeAddress, "Address", "TextValueCommon");
 	        
 			} 	
         ScrolltoElementComm(application, "Interface Address Range", "TextValueCommon");
         if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")||ServiceSubType.equalsIgnoreCase("IPVPN Connect")) {
        	 ClearDropdownValues_common(application, "CNG Reference", "SelectValueDropdown", InterfaceCNGReferance, xml);
         }
         if(!CPEBearerType.equalsIgnoreCase("Ethernet")) {
 			
        	 ClearDropdownValues_common(application, "Bearer Type", "SelectValueDropdown", CPEBearerType, xml);
         Thread.sleep(2000);
         waitforPageenable();
         if(CPEBearerType.equalsIgnoreCase("E3")||CPEBearerType.equalsIgnoreCase("T3")) {
        	 ClearDropdownValues_common(application, "Bandwidth", "SelectValueDropdown", CPEBandwidthE3, xml);    
         }
         if(CPEBearerType.equalsIgnoreCase("STM-1")) {
        	 ClearDropdownValues_common(application, "Bandwidth", "SelectValueDropdown", CPEBandwidthSTM, xml);    
         }
         if(CPEBearerType.equalsIgnoreCase("E1")) {
        	 ClearDropdownValues_common(application, "Bandwidth", "SelectValueDropdown", CPEBandwidthE1, xml);    
         }
        
         addDropdownValues_common(application, "Encapsulation", "SelectValueDropdown", CpeEncapsulation, xml);
               //addDropdownValues_common(application, "Clock Source", "SelectValueDropdown", CpeClockSource, xml);
        // addDropdownValues_common(application, "Primary/Backup", "SelectValueDropdown", CpePrimary_Backup, xml);
       //  addDropdownValues_common(application, "Interface Direction", "SelectValueDropdown", CpenterfaceDirection, xml);
         Clickon(getwebelement("//div[div[label[text()='Clock Source']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Clock Source']]]//select//option[@label='"+ CpeClockSource +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Clock Source" + " dropdown value selected as: "+ CpeClockSource );
         Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select//option[@label='"+ CpePrimary_Backup +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Primary/Backup" + " dropdown value selected as: "+ CpePrimary_Backup );
         Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select//option[@label='"+ CpenterfaceDirection +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Direction" + " dropdown value selected as: "+ CpenterfaceDirection );
         //ClearAndEnterTextValueComm(application, "Voice Line Reference", "TextValueCommon", CpeVoiceline);
        EnterTextValueCommon(application, CpeVoiceline, "Voice Line Reference", "TextValueCommon");
         
         }
        
         if(CPEBearerType.equalsIgnoreCase("Ethernet")) {
  			
        	 ClearDropdownValues_common(application, "Bearer Type", "SelectValueDropdown", CPEBearerType, xml);
             Thread.sleep(2000);
             waitforPageenable();
             ClearDropdownValues_common(application, "Speed", "SelectValueDropdown", CpeSpeed, xml);                
             addDropdownValues_common(application, "Encapsulation", "SelectValueDropdown", CpeEncapsulation, xml);
             ClearDropdownValues_common(application, "Duplex", "SelectValueDropdown", CpeDuplex, xml);
             Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select"));
             Thread.sleep(2000);
             Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select//option[@label='"+ CpePrimary_Backup +"']"));
             ExtentTestManager.getTest().log(LogStatus.PASS, "Primary/Backup" + " dropdown value selected as: "+ CpePrimary_Backup );
             Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select"));
             Thread.sleep(2000);
             Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select//option[@label='"+ CpenterfaceDirection +"']"));
             ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Direction" + " dropdown value selected as: "+ CpenterfaceDirection );
            // ClearAndEnterTextValueComm(application, "Voice Line Reference", "TextValueCommon", CpeVoiceline);
             EnterTextValueCommon(application, CpeVoiceline, "Voice Line Reference", "TextValueCommon");
              }
         if(CpeAddInterfaceButton.equalsIgnoreCase("Yes")) {
            click(application, "Add button", "AddInterfacebutton");
            //ClearAndEnterTextValueComm(application, "Link", "TextValueCommon", CpeInterfaceAddRangeText);
            EnterTextValueCommon(application, CpeInterfaceAddRangeText, "Link", "TextValueCommon");
         	 
         }
         else {
        	  addDropdownValues_common(application, "Link", "SelectValueDropdown", CPELink, xml);
         }
         if(CpeIVManagement.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "IV management", "CheckboxCommon");
         }
         if(CpeIVBitCounter.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "IV 64 Bit Counter", "CheckboxCommon");
         }
         scrolltoend();
        // if(CpeAddInterfaceButton.equalsIgnoreCase("Yes")) {
          //   click(application, "Add button", "AddInterfacebutton2");
             Clickon(getwebelement("//span[text()='Remove']"));
   			Thread.sleep(2000);
   			click(application, "Add button", "AddInterfacebutton2");
   			Thread.sleep(2000);
   			//ClearAndEnterTextValueComm(application, "Address Range", "TextValueCommon", CpeAddRange);
            EnterTextValueCommon(application, CpeAddRange, "Address Range", "TextValueCommon");
             Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[2]"));
  			Thread.sleep(2000);
  			waitforPageenable();
            // EnterTextValueCommon(application, CpeSecondaryIp, "Secondary IP", "TextValueCommon");
            // EnterTextValueCommon(application, CpeNetmask, "Netmask", "TextValueCommon");
             
             
         scrolltoend();
         click(application, "Save button", "SaveInterface");
 		Thread.sleep(3000);
         waitforPageenable();
 		compareText(application, "Add CPE Device", "SuccessMessageCom", "Interface successfully updated.");
 		Thread.sleep(2000);
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	public void DeleteInterfaceCPE(String application, String ServiceSubType,String CPEBearerType,String CPEBandwidthE1,String CPEBandwidthE3,String CPEBandwidthSTM,String CPELink,String CpeEncapsulation,
			String CpeClockSource,String CpePrimary_Backup,String CpenterfaceDirection,String CpeVoiceline,String CpeIVManagement,String CpeIVBitCounter, String CpeAddRange,String CpeSecondaryIp,String CpeNetmask,
			String CpeSpeed,String CpeDuplex,String CpeInterface,String CpeInterfaceAddRangeDdn,String CpeInterfaceAddRangeText,String CpeAddress ,String CPEGetAddress,String VPNRouterId,String VPNSiterderNum,String CpeAddInterfaceButton,
			String InterfaceCNGReferance) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		        click(application, "Show Interface Link", "CPEShowInterfaces");
	        Thread.sleep(3000);
	        WebElement InterfaceRadio = driver
					.findElement(By.xpath("//div[contains(text(),'" + CpeInterface + "')]//parent::div//div//input"));
	        InterfaceRadio.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing Interface radio button");
			  click(application, "Interface Action Button", "InterfaceActionButtonCPE");
		    click(application, "Interface Edit Link", "InterfaceDeleteLink");
	        Thread.sleep(2000);
	        waitforPageenable();
	        
	        Thread.sleep(2000);
			 click(application, "Delete", "DeleteAny");
		 		Thread.sleep(3000);
		         waitforPageenable();
		         GetText(application, "Delete Interface", "SuccessMessageCom");
		  		//compareText(application, "Delete Device", "SuccessMessageCom", "Static Route successfully deleted.");
		 		Thread.sleep(2000);
			
	        
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	
	
	public void AddMultilinkCPE(String application, String ServiceSubType,String MultilinkEthernetCheckbox,String CPEBandwidthE1,String CPEBandwidthE3,String CPEBandwidthSTM,String CPELink,String CpeEncapsulation,
			String CpeClockSource,String CpePrimary_Backup,String CpenterfaceDirection,String CpeVoiceline,String CpeIVManagement,String CpeIVBitCounter, String CpeAddRange,String CpeSecondaryIp,String CpeNetmask,
			String CpeSpeed,String CpeDuplex,String CpeInterface,String CpeInterfaceAddRangeDdn,String CpeInterfaceAddRangeText,String CpeAddress ,String CPEGetAddress,String VPNRouterId,String VPNSiterderNum,String CpeAddInterfaceButton,
			String InterfaceCNGReferance) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);

		if(NoOfDevice==1)
		{
			click(application, "View CPE Device", "ViewCPEDevice");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'1')]/parent::span/parent::div//following-sibling::div//span[text()='View']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device View button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
		WebElement Interface_header= getwebelement("//div[text()='Routes']");
		scrolltoview(Interface_header);
		click(application, "Action dropdown", "InterfaceActionButton");
		ClickCommon(application, "Add Multlink ", "InterfaceActionCommonLink");
		waitforPageenable();
       
		click(application, "Cancel button", "CancelCPEDevice");
		Thread.sleep(3000);
		waitforPageenable();
		scrollToTop();
		Thread.sleep(2000);
		WebElement Interface_header1= getwebelement("//div[text()='Routes']");
		scrolltoview(Interface_header1);
		click(application, "Action dropdown", "InterfaceActionButton");
		ClickCommon(application, "Add Multlink ", "InterfaceActionCommonLink");
		waitforPageenable();
       
        scrollToTop();
     
         EnterTextValueCommon(application, CpeInterface, "Interface", "TextValueCommon");
         ScrolltoElementComm(application, "Interface", "TextValueCommon");
         if(CPEGetAddress.equalsIgnoreCase("Yes")) {
 			click(application, "Get Address button", "GetAddressCPE");
 			Thread.sleep(5000);
 			click(application, "Allocate Subnet button", "AllocateSubnetButton");
 			Thread.sleep(3000);
 			waitforPageenable();
 			// addDropdownValues_common(application, "Interface Address Range", "SelectValueDropdown", CpeInterfaceAddRangeDdn, xml);
 			Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[1]"));
 			Thread.sleep(2000);
 			waitforPageenable();
 			addDropdownValues_common(application, "Address", "SelectValueDropdown", CpeAddress, xml);
 			
 		}
 		else {
 			 EnterTextValueCommon(application, CpeInterfaceAddRangeText, "Interface Address Range", "TextValueCommon");
 			Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[1]"));
 			Thread.sleep(2000);
 			waitforPageenable();
 			// EnterTextValueCommon(application, CpeAddress, "Address", "TextValueCommon");
 	        
 			} 	
        // ScrolltoElementComm(application, "Bearer Type", "SelectValueDropdown");
         if(MultilinkEthernetCheckbox.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "Ethernet", "CheckboxCommon");
        	 Thread.sleep(2000);
        	 Clickon(getwebelement("//div[div[label[text()='Speed']]]//select"));
             Thread.sleep(2000);
             Clickon(getwebelement("//div[div[label[text()='Speed']]]//select//option[@label='"+ CpeSpeed +"']"));
             ExtentTestManager.getTest().log(LogStatus.PASS, "Speed" + " dropdown value selected as: "+ CpeSpeed );
             Clickon(getwebelement("//div[div[label[text()='Duplex']]]//select"));
             Thread.sleep(2000);
             Clickon(getwebelement("//div[div[label[text()='Duplex']]]//select//option[@label='"+ CpeDuplex +"']"));
             ExtentTestManager.getTest().log(LogStatus.PASS, "Duplex" + " dropdown value selected as: "+ CpeDuplex );
            
        	 
         }
 			
         Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select//option[@label='"+ CpePrimary_Backup +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Primary/Backup" + " dropdown value selected as: "+ CpePrimary_Backup );
         Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select//option[@label='"+ CpenterfaceDirection +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Direction" + " dropdown value selected as: "+ CpenterfaceDirection );
         EnterTextValueCommon(application, CpeVoiceline, "Voice Line Reference", "TextValueCommon");
         
         if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")||ServiceSubType.equalsIgnoreCase("IPVPN Connect")) {
             addDropdownValues_common(application, "CNG Reference", "SelectValueDropdown", InterfaceCNGReferance, xml);
             }
             
         
        
          if(CpeAddInterfaceButton.equalsIgnoreCase("Yes")) {
         click(application, "Add button", "AddInterfacebutton");
         EnterTextValueCommon(application, CpeInterfaceAddRangeText, "Link", "TextValueCommon");
         	 
         }
         else {
        	  addDropdownValues_common(application, "Link", "SelectValueDropdown", CPELink, xml);
         }
         if(CpeIVManagement.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "IV management", "CheckboxCommon");
         }
         if(CpeIVBitCounter.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "IV 64 Bit Counter", "CheckboxCommon");
         }
         scrolltoend();
         if(CpeAddInterfaceButton.equalsIgnoreCase("Yes")) {
             click(application, "Add button", "AddInterfacebutton2");
             Clickon(getwebelement("//span[text()='Remove']"));
   			Thread.sleep(2000);
   			click(application, "Add button", "AddInterfacebutton2");
   			Thread.sleep(2000);
             EnterTextValueCommon(application, CpeAddRange, "Address Range", "TextValueCommon");
             Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[2]"));
  			Thread.sleep(2000);
  			waitforPageenable();
            // EnterTextValueCommon(application, CpeSecondaryIp, "Secondary IP", "TextValueCommon");
            // EnterTextValueCommon(application, CpeNetmask, "Netmask", "TextValueCommon");
             
             }
         scrolltoend();
         click(application, "ok button", "multilink_okButton");
 		Thread.sleep(3000);
         waitforPageenable();
 		compareText(application, "Add CPE Device", "SuccessMessageCom", "Multilink interface successfully created.");
 		Thread.sleep(2000);
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	public void EditMultilinkCPE(String application, String ServiceSubType,String MultilinkEthernetCheckbox,String CPEBandwidthE1,String CPEBandwidthE3,String CPEBandwidthSTM,String CPELink,String CpeEncapsulation,
			String CpeClockSource,String CpePrimary_Backup,String CpenterfaceDirection,String CpeVoiceline,String CpeIVManagement,String CpeIVBitCounter, String CpeAddRange,String CpeSecondaryIp,String CpeNetmask,
			String CpeSpeed,String CpeDuplex,String CpeInterface,String CpeInterfaceAddRangeDdn,String CpeInterfaceAddRangeText,String CpeAddress ,String CPEGetAddress,String VPNRouterId,String VPNSiterderNum,String CpeAddInterfaceButton,
			String InterfaceCNGReferance) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		        click(application, "Show Interface Link", "CPEShowInterfaces");
	        Thread.sleep(3000);
	        WebElement InterfaceRadio = driver
					.findElement(By.xpath("//div[contains(text(),'"+"Multilink"+CpeInterface+"')]//parent::div//div//input"));
	        InterfaceRadio.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing Interface radio button");
			  click(application, "Interface Action Button", "InterfaceActionButtonCPE");
		    click(application, "Interface Edit Link", "InterfaceEditLink");
	        Thread.sleep(2000);
	        waitforPageenable();

	        ClearAndEnterTextValueComm(application, "Interface", "TextValueCommon", CpeInterface);
        // EnterTextValueCommon(application, CpeInterface, "Interface", "TextValueCommon");
         ScrolltoElementComm(application, "Interface", "TextValueCommon");
         if(CPEGetAddress.equalsIgnoreCase("Yes")) {
 			click(application, "Get Address button", "GetAddressCPE");
 			Thread.sleep(5000);
 			click(application, "Allocate Subnet button", "AllocateSubnetButton");
 			Thread.sleep(3000);
 			waitforPageenable();
 			// addDropdownValues_common(application, "Interface Address Range", "SelectValueDropdown", CpeInterfaceAddRangeDdn, xml);
 			Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[1]"));
 			Thread.sleep(2000);
 			waitforPageenable();
 			addDropdownValues_common(application, "Address", "SelectValueDropdown", CpeAddress, xml);
 			
 		}
 		else {
 			 ClearAndEnterTextValueComm(application, "Interface Address Range", "TextValueCommon", CpeInterfaceAddRangeText);
 			// EnterTextValueCommon(application, CpeInterfaceAddRangeText, "Interface Address Range", "TextValueCommon");
 			Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[1]"));
 			Thread.sleep(2000);
 			waitforPageenable();
 			// EnterTextValueCommon(application, CpeAddress, "Address", "TextValueCommon");
 	        
 			} 	
        // ScrolltoElementComm(application, "Bearer Type", "SelectValueDropdown");
         if(MultilinkEthernetCheckbox.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "Ethernet", "CheckboxCommon");
        	 ClickCommon(application, "Ethernet", "CheckboxCommon");
        	 Thread.sleep(2000);
        	 Clickon(getwebelement("//div[div[label[text()='Speed']]]//select"));
             Thread.sleep(2000);
             Clickon(getwebelement("//div[div[label[text()='Speed']]]//select//option[@label='"+ CpeSpeed +"']"));
             ExtentTestManager.getTest().log(LogStatus.PASS, "Speed" + " dropdown value selected as: "+ CpeSpeed );
             Clickon(getwebelement("//div[div[label[text()='Duplex']]]//select"));
             Thread.sleep(2000);
             Clickon(getwebelement("//div[div[label[text()='Duplex']]]//select//option[@label='"+ CpeDuplex +"']"));
             ExtentTestManager.getTest().log(LogStatus.PASS, "Duplex" + " dropdown value selected as: "+ CpeDuplex );
            
        	 
         }
 			
         Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Primary/Backup']]]//select//option[@label='"+ CpePrimary_Backup +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Primary/Backup" + " dropdown value selected as: "+ CpePrimary_Backup );
         Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select"));
         Thread.sleep(2000);
         Clickon(getwebelement("//div[div[label[text()='Interface Direction']]]//select//option[@label='"+ CpenterfaceDirection +"']"));
         ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Direction" + " dropdown value selected as: "+ CpenterfaceDirection );
        ClearAndEnterTextValueComm(application, "Voice Line Reference", "TextValueCommon", CpeVoiceline);
         //EnterTextValueCommon(application, CpeVoiceline, "Voice Line Reference", "TextValueCommon");
         if(ServiceSubType.equalsIgnoreCase("IPVPN IPSec")||ServiceSubType.equalsIgnoreCase("IPVPN Connect")) {
        	 ClearDropdownValues_common(application, "CNG Reference", "SelectValueDropdown", InterfaceCNGReferance, xml);
         }
        
         
        
          if(CpeAddInterfaceButton.equalsIgnoreCase("Yes")) {
         click(application, "Add button", "AddInterfacebutton");
         EnterTextValueCommon(application, CpeInterfaceAddRangeText, "Link", "TextValueCommon");
         	 
         }
         else {
        	  addDropdownValues_common(application, "Link", "SelectValueDropdown", CPELink, xml);
         }
         if(CpeIVManagement.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "IV management", "CheckboxCommon");
        	 ClickCommon(application, "IV management", "CheckboxCommon");
         }
         if(CpeIVBitCounter.equalsIgnoreCase("Yes")) {
        	 ClickCommon(application, "IV 64 Bit Counter", "CheckboxCommon");
        	 ClickCommon(application, "IV 64 Bit Counter", "CheckboxCommon");
         }
         scrolltoend();
         if(CpeAddInterfaceButton.equalsIgnoreCase("Yes")) {
             click(application, "Add button", "AddInterfacebutton2");
             Clickon(getwebelement("//span[text()='Remove']"));
   			Thread.sleep(2000);
   			click(application, "Add button", "AddInterfacebutton2");
   			Thread.sleep(2000);
             EnterTextValueCommon(application, CpeAddRange, "Address Range", "TextValueCommon");
             Clickon(getwebelement("(//span[@class='arrow badge badge-secondary'])[2]"));
  			Thread.sleep(2000);
  			waitforPageenable();
            // EnterTextValueCommon(application, CpeSecondaryIp, "Secondary IP", "TextValueCommon");
            // EnterTextValueCommon(application, CpeNetmask, "Netmask", "TextValueCommon");
             
             }
         scrolltoend();
         click(application, "ok button", "multilink_okButton");
 		Thread.sleep(3000);
         waitforPageenable();
 		compareText(application, "Add CPE Device", "SuccessMessageCom", "Multilink interface successfully updated.");
 		Thread.sleep(2000);
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	public void DeleteMultilinkCPE(String application, String ServiceSubType,String MultilinkEthernetCheckbox,String CPEBandwidthE1,String CPEBandwidthE3,String CPEBandwidthSTM,String CPELink,String CpeEncapsulation,
			String CpeClockSource,String CpePrimary_Backup,String CpenterfaceDirection,String CpeVoiceline,String CpeIVManagement,String CpeIVBitCounter, String CpeAddRange,String CpeSecondaryIp,String CpeNetmask,
			String CpeSpeed,String CpeDuplex,String CpeInterface,String CpeInterfaceAddRangeDdn,String CpeInterfaceAddRangeText,String CpeAddress ,String CPEGetAddress,String VPNRouterId,String VPNSiterderNum,String CpeAddInterfaceButton,
			String InterfaceCNGReferance) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		        click(application, "Show Interface Link", "CPEShowInterfaces");
	        Thread.sleep(3000);
	        WebElement InterfaceRadio = driver
					.findElement(By.xpath("//div[contains(text(),'"+"Multilink"+CpeInterface+"')]//parent::div//div//input"));
	        InterfaceRadio.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing Interface radio button");
			  click(application, "Interface Action Button", "InterfaceActionButtonCPE");
			  click(application, "Interface Edit Link", "InterfaceDeleteLink");
		        Thread.sleep(2000);
		        waitforPageenable();
		        
		        Thread.sleep(2000);
				 click(application, "Delete", "DeleteAny");
			 		Thread.sleep(3000);
			         waitforPageenable();
			         GetText(application, "Delete Interface", "SuccessMessageCom");
			  		//compareText(application, "Delete Device", "SuccessMessageCom", "Static Route successfully deleted.");
			 		Thread.sleep(2000);
				
				}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	
	public void AddRoutesCPE(String application, String ServiceSubType,String VPNRouterId,String VPNSiterderNum,String RoutesDestination,String RoutesNetMask,String RoutesMetrics) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);

		if(NoOfDevice==1)
		{
			click(application, "View CPE Device", "ViewCPEDevice");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'1')]/parent::span/parent::div//following-sibling::div//span[text()='View']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device View button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
		WebElement Router_Tools= getwebelement("//div[text()='Router Tools']");
		scrolltoview(Router_Tools);
		click(application, "Action dropdown", "RoutesActionButton");
		ClickCommon(application, "Add Route", "RoutesActionCommonLink");
		waitforPageenable();
		 click(application, "ok button", "Routes_okButton");
	 		Thread.sleep(3000);
		WarningMessageCom(application, "Destination","WarningMessageCommon");
		WarningMessageCom(application, "Network Mask","WarningMessageCommon");
		
      //  if(ServiceSubType.equalsIgnoreCase("IPVPN Connect")) {
        	EnterTextValueCommon(application, "10.34.89.90", "Gateway", "TextValueCommon");
        //}
		 EnterTextValueCommon(application, RoutesDestination, "Destination", "TextValueCommon");
		 EnterTextValueCommon(application, RoutesNetMask, "Network Mask", "TextValueCommon");
		 EnterTextValueCommon(application, RoutesMetrics, "Metrics", "TextValueCommon");
	        
		 click(application, "ok button", "Routes_okButton");
	 		Thread.sleep(3000);
	         waitforPageenable();
	 		compareText(application, "Add CPE Device", "SuccessMessageCom", "Static Route successfully created");
	 		Thread.sleep(2000);
			
		
       		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	public void EditRoutesCPE(String application, String ServiceSubType,String VPNRouterId,String VPNSiterderNum,String RoutesDestination,String RoutesNetMask,String RoutesMetrics) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);

		if(NoOfDevice==1)
		{
			click(application, "View CPE Device", "ViewCPEDevice");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'1')]/parent::span/parent::div//following-sibling::div//span[text()='View']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device View button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
		WebElement Router_Tools= getwebelement("//div[text()='Router Tools']");
		scrolltoview(Router_Tools);
		
		WebElement ExistingRoutes = driver
				.findElement(By.xpath("(//div[text()='"+RoutesDestination+"']//parent::div//div//span//span//span)[2]"));
		ExistingRoutes.click();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing Routes Checkbox");
	
		
		click(application, "Action dropdown", "RoutesActionButton");
		ClickCommon(application, "Edit", "RoutesActionCommonLink");
		waitforPageenable();
		
        ClearAndEnterTextValueComm(application, "Destination", "TextValueCommon", RoutesDestination);
        ClearAndEnterTextValueComm(application, "Network Mask", "TextValueCommon", RoutesNetMask);
        ClearAndEnterTextValueComm(application, "Metrics", "TextValueCommon", RoutesMetrics);
		 
        
       // EnterTextValueCommon(application, RoutesDestination, "Destination", "TextValueCommon");
		 //EnterTextValueCommon(application, RoutesNetMask, "Network Mask", "TextValueCommon");
		 //EnterTextValueCommon(application, RoutesMetrics, "Metrics", "TextValueCommon");
	        
		 click(application, "ok button", "Routes_okButton");
	 		Thread.sleep(3000);
	         waitforPageenable();
	 		compareText(application, "Add CPE Device", "SuccessMessageCom", "Static Route successfully updated");
	 		Thread.sleep(2000);
			
		
       		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}

	public void DeleteRoutesCPE(String application, String ServiceSubType,String VPNRouterId,String VPNSiterderNum,String RoutesDestination,String RoutesNetMask,String RoutesMetrics) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);

		if(NoOfDevice==1)
		{
			click(application, "View CPE Device", "ViewCPEDevice");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'1')]/parent::span/parent::div//following-sibling::div//span[text()='View']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device View button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
		WebElement Router_Tools= getwebelement("//div[text()='Router Tools']");
		scrolltoview(Router_Tools);
		Thread.sleep(2000);
		
		WebElement ExistingRoutes = driver
				.findElement(By.xpath("(//div[text()='"+RoutesDestination+"']//parent::div//div//span//span//span)[2]"));
		ExistingRoutes.click();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing Routes Checkbox");
	
		
		click(application, "Action dropdown", "RoutesActionButton");
		ClickCommon(application, "Delete", "RoutesActionCommonLink");
		Thread.sleep(2000);
		 click(application, "Delete", "DeleteAny");
	 		Thread.sleep(3000);
	         waitforPageenable();
	 		compareText(application, "Add CPE Device", "SuccessMessageCom", "Static Route successfully deleted.");
	 		Thread.sleep(2000);
			
		
       		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}
	}
	
	public void AddRouterTool4(String application, String ServiceSubType,String VPNCountry,String VPNDeviceCity,String VPNPhysicalSite,String VPNVendorModel,String VPNSiteOrder,String VPNSiteAlias,
			String VPNRouterId,String RouterToolIPV4,String RouterToolIPV6,String HubSpoke) throws InterruptedException, DocumentException, IOException {
	if(ServiceSubType.equalsIgnoreCase("CPE Solutions L2")||ServiceSubType.equalsIgnoreCase("CPE Solutions L3")||ServiceSubType.equalsIgnoreCase("IP Voice")||ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		if(!ServiceSubType.equalsIgnoreCase("SwiftNet")) {
		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		click(application, "VPN View Link", "VPN_viewdevice1");
		 
		}
		else {
			WebElement HUB_header= getwebelement("//div[text()='Hub']");
			scrolltoview(HUB_header);
			click(application, "VPN View Link", "Hub_viewdevice1");
		}
       waitforPageenable();
		Thread.sleep(2000);
		
		waitforPageenable();
		scrollToTop();
		String VendorName=GetTextCommon(application, "Vendor/Model", "CompareTextCommon");
		if(VendorName.contains("Cisco")||VendorName.contains("Huawei")||VendorName.contains("Cisco")) {
			
		WebElement CountryName= getwebelement("//label[text()='Country']");
		scrolltoview(CountryName);
		addDropdownValues_common(application, "Command IPV4", "SelectValueDropdown", RouterToolIPV4, xml);
//		addDropdownValues_common(application, "Command IPV6", "SelectValueDropdown", RouterToolIPV6, xml);
		WebElement Element = driver
				.findElement(By.xpath("//input[@id='routertools.hostnameOrIPAddress']"));
		
		if(Element.isDisplayed()) {
			EnterTextValue(application, "Colt@Colt.net", "Command IPV4" ,"RouterToolIPV4");
		}
		click(application, "Execute", "RouterToolExecuteA");
		Thread.sleep(3000);
		//WebElement Element2 = driver
			//	.findElement(By.xpath("//input[@id='routertools.hostnameOrIPv6Address']"));
		
		//if(Element2.isDisplayed()) {
			//	EnterTextValue(application, "colt@Colt.net", "Command IPV6" ,"RouterToolIPV6");
			//}
		//click(application, "Execute", "RouterToolExecuteB");
		Thread.sleep(3000);
		GetText(application, "Router Tool Result", "RouterToolResult");
		}
	}
	}
	
	public void AddRouterTool(String application, String ServiceSubType,String VPNRouterId,String VPNSiterderNum,String RouterToolIPV4,String RouterToolIPV6) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);

		if(NoOfDevice==1)
		{
			click(application, "View CPE Device", "ViewCPEDevice");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'1')]/parent::span/parent::div//following-sibling::div//span[text()='View']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing CPE Device View button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
		scrollToTop();
		String VendorName=GetTextCommon(application, "Vendor/Model", "CompareTextCommon");
		if(VendorName.contains("Cisco")||VendorName.contains("Huawei")||VendorName.contains("Cisco")) {
			
		WebElement CountryName= getwebelement("//label[text()='Country']");
		scrolltoview(CountryName);
		addDropdownValues_common(application, "Command IPV4", "SelectValueDropdown", RouterToolIPV4, xml);
		addDropdownValues_common(application, "Command IPV6", "SelectValueDropdown", RouterToolIPV6, xml);
		WebElement Element = driver
				.findElement(By.xpath("//input[@id='routertools.hostnameOrIPAddress']"));
		
		if(Element.isDisplayed()) {
			EnterTextValue(application, "Colt@Colt.net", "Command IPV4" ,"RouterToolIPV4");
		}
		click(application, "Execute", "RouterToolExecuteA");
		Thread.sleep(3000);
		WebElement Element2 = driver
				.findElement(By.xpath("//input[@id='routertools.hostnameOrIPv6Address']"));
		
		if(Element2.isDisplayed()) {
				EnterTextValue(application, "colt@Colt.net", "Command IPV6" ,"RouterToolIPV6");
			}
		click(application, "Execute", "RouterToolExecuteB");
		Thread.sleep(3000);
		GetText(application, "Router Tool Result", "RouterToolResult");
		}
		}
		else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
		}
	}
		
		


	public void SelectInterface(String application, String ServiceSubType,String VPNRouterId,String VPNSiterderNum,String CpeInterface) throws InterruptedException, DocumentException, IOException {
		if(ServiceSubType.contains("IPVPN")&& !ServiceSubType.equalsIgnoreCase("IPVPN Access")) {
			
			WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
			scrolltoview(VPNSiteOrder_header);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Numberofuser="+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>=1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + VPNSiterderNum+ "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

			}

			click(application, "Action dropdown", "VPNSiteactionbutton");
			ClickCommon(application, "View", "AddVPNSiteCommonLink");
			waitforPageenable();
	        scrolltoend();
		List<WebElement> ExistingDevices= driver.findElements(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']//parent::div//following-sibling::div[@class='div-margin row']"));
		int NoOfDevices = ExistingDevices.size();
		int NoOfDevice=NoOfDevices-1;
		System.out.println("Numberofuser="+ NoOfDevice);

		if(NoOfDevice==1)
		{
			click(application, "Select Interface", "SelectInterface");
			Thread.sleep(3000);
		}
		else if(NoOfDevice>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[text()='Customer Premise Equipment ( CPE ) ']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'1')]/parent::span/parent::div//following-sibling::div//span[text()='Select Interfaces']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing Select Interface button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Device displayed");

		}
		waitforPageenable();
		scrolltoend();
		WebElement ClickInterface = driver
				.findElement(By.xpath("(//div[text()='"+CpeInterface+"']//parent::div//div//span//input)[1]"));
		ClickInterface.click();
		Thread.sleep(3000);
		 click(application, "ok button", "Routes_okButton");
		 Thread.sleep(2000);
		 waitforPageenable();
				}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
			}

	}


	
	
	
	
	
	public void edittextFields_commonMethod(String application, String labelname, String xpathname,
			String expectedValueToEdit, XMLReader xml) throws InterruptedException, DocumentException, IOException {
		boolean availability = false;
		try {
			availability = getwebelement(xml.getlocator("//locators/" + application + "/" + xpathname + ""))
					.isDisplayed();
			if (availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is displaying");
				System.out.println(labelname + " text field is displaying");

				if (expectedValueToEdit.equalsIgnoreCase("null")) {

					String actualvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/" + xpathname + "")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							labelname + " text field is not edited. It is displaying as " + actualvalue);
					System.out.println(
							labelname + " text field is not edited as expected. It is displaying as " + actualvalue);
				} else {

					getwebelement(xml.getlocator("//locators/" + application + "/" + xpathname + "")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/" + xpathname + "")),
							expectedValueToEdit);
					Thread.sleep(3000);

					String actualvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/" + xpathname + "")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is edited as: " + actualvalue);
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " text field is not displaying");
				System.out.println(labelname + " text field is not displaying");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " text field is not displaying");
			System.out.println(labelname + " text field is not displaying");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to perform editing under" + labelname + " text field");
			System.out.println(" Not able to perform editing under " + labelname + " text field");
		}
	}

	
	public void verifFetchDeviceInterfacesFunction_MAS(String application,String ServiceIdentification, String MAS_ServiceStatusChangeRequired) throws InterruptedException, DocumentException, IOException {

		
		click(application, "ACTION link", "MAS_View_ActionLink");
		click(application, "Fetch Device Interfaces Link", "MAS_View_Action_FetchDeviceInterfacesLink");
		compareText(application, "Fetch Interfaces started successfully Message for MAS", "MAS_FetchDeviceInterfacesSuccessMessage", " Fetch Interfaces started successfully. Please check the sync status of this device");
		
		click(application, "Click here Link for MAS", "MAS_hereLink_UnderFetchDeviceInterfacesSuccessMessage");

		Thread.sleep(2000);
		
		//Manage COLT's Network - Manage Network
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Manage_Synchronization_SynchronizeLink")));
		
		compareText(application, "Manage COLT's Network - Manage Network header", "MAS_ManageCOLTsNetworkManageNetwork_header", "Manage COLT's Network - Manage Network");
		GetText(application, "Device Name in Manage Colt page under Status Panel", "MAS_Manage_Status_DeviceValue");
		GetText(application, "Status in Manage Colt page", "MAS_Manage_Status_StatusValue");
		GetText(application, "Last Modification in Manage Colt page", "MAS_Manage_Status_LastModificationValue");
		GetText(application, "Status Link in Manage Colt page", "MAS_Manage_Status_StatusLink");
		GetText(application, "View Interface Link in Manage Colt page", "MAS_Manage_Status_ViewInterfacesLink");
		
		GetText(application, "Device Name in Manage Colt page under Synchronization Panel", "MAS_Manage_Synchronization_DeviceValue");
		GetText(application, "Sync Status in Manage Colt page", "MAS_Manage_Synchronization_SyncStatusValue");
		GetText(application, "Smarts in Manage Colt page", "MAS_Manage_Synchronization_SmartsValue");
		GetText(application, "Fetch Interfaces in Manage Colt page", "MAS_Manage_Synchronization_FetchInterfacesValue");
		GetText(application, "VistaMart Device in Manage Colt page", "MAS_Manage_Synchronization_VistaMartDeviceValue");
		GetText(application, "Synchronize Link in Manage Colt page", "MAS_Manage_Synchronization_SynchronizeLink");

		
		scrollToTop();
		String MAS_Manage_Status_LastModificationValue= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Manage_Status_LastModificationValue")));
		if(MAS_Manage_Status_LastModificationValue.contains("GMT"))
		{
			Log.info("Service status is displayed as : " + MAS_Manage_Status_LastModificationValue);
			System.out.println("Service status is :"+ MAS_Manage_Status_LastModificationValue);
		}
		else
		{
			Log.info("Incorrect modification time format");
			System.out.println("Incorrect modification time format");
		}
		
		
		////
		click(application, "Status", "MAS_Manage_Status_StatusLink");

		if(MAS_ServiceStatusChangeRequired=="Yes")
		{
			WebElement ServiceStatusPage= getwebelement(xml.getlocator("//locators/" + application + "/MAS_Manage_Status_Servicestatus_popup"));
			if(ServiceStatusPage.isDisplayed())
			{
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Device_Status_OK")));
				click(application, "Click on OK to change Status", "MAS_Device_Status_OK");

				WebElement MAS_servicestatushistoryValue= getwebelement(xml.getlocator("//locators/" + application + "/MAS_servicestatushistoryValue"));
				try
				{
					if(MAS_servicestatushistoryValue.isDisplayed())
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request logged");
					}
					else
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request is not logged");
				}
				catch(StaleElementReferenceException e)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No service history to display");
				}
			}
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Status link is not working");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change not reqired");
			click(application, "Close", "MAS_servicestatus_popupclose");
		}

		
		
		////synchronize panel in manage colt page
			scrolltoend();
			click(application, "Synchronize", "MAS_Manage_Synchronization_SynchronizeLink");
			compareText(application, "Synchronize Success Message", "MAS_Manage_SynchronizeSuccessMessage", "Sync started successfully. Please check the sync status of this device.");
				
//			//**scrollToTop();
//			//**click(application, "Service ID on Top Of The Bredcome Navigation", "//li/a[text()='"+ServiceIdentification+"");


		//Delete Device will performed in the last test case
		
	}
	
	
	
	
	
	////
	
	public void verifyRouterToolFunction_MAS(String application,String ServiceIdentification, String MAS_CommandIPV4, String MAS_CommandIPV6, String MAS_ManagementAddress) throws InterruptedException, DocumentException, IOException {
		
		//Moveon(getwebelement(xml.getlocator("//locators/" + application + "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]")));
		//Thread.sleep(2000);
		//click(application, "Service ID on Top Of The Bredcome Navigation", "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]");
		
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/HomeLink_Bredcome")));
		Thread.sleep(2000);
		click(application, "Service ID on Top Of The Bredcome Navigation", "//a[@href='/uiApt/voiceService/voipaccess/viewService']");//Not Working

		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header")));
		click(application, "View Link", "MAS_viewdevice1");
		Thread.sleep(10000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/RouterTool_header")));
		compareText(application, "Router Tools header", "RouterTool_header", "Router Tools");
		
		
		
		// Select  MAS_CommandIPV4 dropdown
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Router_IPV4CommandsDropdown")));
				List<WebElement> Router_IPV4CommandsDropdownList = driver.findElements(By.xpath("//div[@class='sc-htpNat AUGYd']//div//div"));////span[@role='option']-Not taking
				for (WebElement clist : Router_IPV4CommandsDropdownList) {

					System.out.println("Available IPV 4 Commands is : " + clist.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step : Available IPV 4 Commands is : " + clist.getText().toString());
					Log.info("Available IPV 4 Commands is :" + clist.getText().toString());
				}
				
				Thread.sleep(1000);
				
				// click on MAS_CommandIPV4 Dropdown
				WebElement MAS_CommandIPV4Dropdown = driver.findElement(By.xpath("//div[text()='" + MAS_CommandIPV4 + "']"));
				System.out.println("Selected IMS POP Location dropdowm is : " + MAS_CommandIPV4Dropdown.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected IMS POP Location dropdowm is : " + MAS_CommandIPV4Dropdown.getText().toString());
				Log.info("Selected IMS POP Location dropdowm is : " + MAS_CommandIPV4Dropdown.getText().toString());
				MAS_CommandIPV4Dropdown.click();
				Thread.sleep(2000);
				
				EnterTextValue(application, MAS_ManagementAddress, "Commands IPV4", "MAS_Router_IPV4CommandTextfield");
				click(application, "Execute button", "MAS_Router_IPV4Command_Executebutton");
				
				
		
				//// Select  MAS_CommandIPV6 dropdown
				Thread.sleep(10000);
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Router_IPV6CommandsDropdown")));
				List<WebElement> Router_IPV6CommandsDropdownList = driver.findElements(By.xpath("//div[@class='sc-htpNat AUGYd']//div//div"));////span[@role='option']-Not taking
				for (WebElement clist : Router_IPV6CommandsDropdownList) {

					System.out.println("Available IPV 6 Commands is : " + clist.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step : Available IPV 6 Commands is : " + clist.getText().toString());
					Log.info("Available IPV 6 Commands is :" + clist.getText().toString());
				}
				
				Thread.sleep(1000);
				
				// click on MAS_CommandIPV4 Dropdown
				WebElement MAS_CommandIPV6Dropdown = driver.findElement(By.xpath("//div[text()='" + MAS_CommandIPV6 + "']"));
				System.out.println("Selected IMS POP Location dropdowm is : " + MAS_CommandIPV6Dropdown.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected IMS POP Location dropdowm is : " + MAS_CommandIPV6Dropdown.getText().toString());
				Log.info("Selected IMS POP Location dropdowm is : " + MAS_CommandIPV6Dropdown.getText().toString());
				MAS_CommandIPV6Dropdown.click();
				Thread.sleep(2000);
				
				EnterTextValue(application, MAS_ManagementAddress, "Commands IPV6", "MAS_Router_IPV6CommandTextfield");
				click(application, "Execute button", "MAS_Router_IPV6Command_Executebutton");
				
}
	
	
	
	
	
	////
		
	
	
	public void verifyConfigureInterfaceFunction_MAS(String application, String ServiceIdentification, String MAS_GenerateConfiguration, String MAS_Configuration) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();//Below 3 lines of code require if we try to move from view device page to view service page for configure
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_BackButton_viewdevicepage")));
		click(application, "BACK button", "MAS_PE_BackButton_viewdevicepage"); 
		
		
		//Configure Interface - MAS Device from View Device Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header")));
		click(application, "Show Interfaces Link", "MAS_ShowInterfaceLink"); 
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "MASSwitch_ACTION");
		
		click(application, "Configure", "MAS_PE_ACTION_ConfigureLink");  
		GetText(application, "Select Interfaces header", "MAS_PE_Configure_ConfigureInterface_header");
		
		
		// Verify All Device Information under Device panel for MAS Switch
		GetText(application, "Device Name", "MAS_PE_Configure_DeviceNameValue");
		GetText(application, "Interface", "MAS_PE_Configure_InterfaceValue");
		GetText(application, "Link/Circuit Id", "MAS_PE_Configure_LinkCircuitIdValue");
		GetText(application, "Interface Address Range", "MAS_PE_Configure_InterfaceAddressRangeValue");
		GetText(application, "Interface Address/Mask", "MAS_PE_Configure_InterfaceAddressMaskValue");
		GetText(application, "HSRP IP", "MAS_PE_Configure_HSRPIPValue");
		GetText(application, "Group Number", "MAS_PE_Configure_GroupNumberValue");
		GetText(application, "Bearer Type", "MAS_PE_Configure_BearerTypeValue");
		GetText(application, "Band Width", "MAS_PE_Configure_BandWidthValue");
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configure_Configuration_header")));
		GetText(application, "VLAN Id", "MAS_PE_Configure_VLANIdValue");
		
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
		click(application, "Generate Configuration Dropdown", "MAS_PE_Configure_GenerateConfigurationDropdown");
		Thread.sleep(2000);
		
//		WebElement MAS_GenerateConfigurationSelect=driver.findElement(By.xpath("//div[div[text()='\"+MAS_GenerateConfiguration+\"']]"));
//		MAS_GenerateConfigurationSelect.click();

		EnterTextValue(application, MAS_GenerateConfiguration, "Configuration dropdown textfield", "MAS_PE_Configure_GenerateConfigurationDropdown");
		click(application, "Select Generate Configuration value from dropdown", "//div[div[text()='"+MAS_GenerateConfiguration+"']]");
		click(application, "Generate Configuration Button", "MAS_PE_Configure_GenerateConfigurationButton");
		
		compareText(application, "Generate Configuration for All CPE Routes Link", "MAS_PE_Configure_GenerateConfigurationForAllCPERoutesButton", "Generate Configuration for All CPE Routes");
		compareText(application, "Save Configuration Link", "MAS_PE_Configure_SaveConfigurationButton", "Save Configuration");
		compareText(application, "Execute Configuration on Device Link", "MAS_PE_Configure_ExecuteConfigurationonDeviceButton", "Execute Configuration on Device");
		
		GetText(application, "Configuration information after configuration generated", "MAS_PE_Configure_ConfigurationTextfield");
		click(application, "Back Button", "MAS_PE_Configure_BackButton");
		
		
		Log.info("------ Verified Configure Interfaces Information successfully ------");
	}
	
	
	
	
	
	
	
	
	
		public void verifyRouterToolFunction_PE(String application,String ServiceIdentification, String PE_CommandIPV4,String PE_ManagementAddress) throws InterruptedException, DocumentException, IOException {
				
		//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/HomeLink_Bredcome")));
		scrollToTop();
		Thread.sleep(2000);
		//Moveon(getwebelement(xml.getlocator("//locators/" + application + "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]")));
		//click(application, "Service ID on Top Of The Bredcome Navigation", "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]");
		//click(application, "Service ID on Top Of The Bredcome Navigation", "//a[@href='/uiApt/voiceService/voipaccess/viewService']");//Not Working
		click(application, "Service ID on Top Of The Bredcome Navigation", "//nav/ol/li[3]");//Not Working

		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "View Link", "PE_viewdevice1");
		Thread.sleep(10000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_View_PremiseValue")));
		compareText(application, "Router Tools header", "RouterTool_header", "Router Tools");
		
		
		
		// Select  MAS_CommandIPV4 dropdown
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PE_Router_IPV4CommandsDropdown")));
				List<WebElement> Router_IPV4CommandsDropdownList = driver.findElements(By.xpath("//div[@class='sc-htpNat AUGYd']//div//div"));////span[@role='option']-Not taking
				for (WebElement clist : Router_IPV4CommandsDropdownList) {

					System.out.println("Available IPV 4 Commands is : " + clist.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step : Available IPV 4 Commands is : " + clist.getText().toString());
					Log.info("Available IPV 4 Commands is :" + clist.getText().toString());
				}
				
				Thread.sleep(1000);
				
				// click on MAS_CommandIPV4 Dropdown
				WebElement PE_CommandIPV4Dropdown = driver.findElement(By.xpath("//div[text()='" + PE_CommandIPV4 + "']"));
				System.out.println("Selected IMS POP Location dropdowm is : " + PE_CommandIPV4Dropdown.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected IMS POP Location dropdowm is : " + PE_CommandIPV4Dropdown.getText().toString());
				Log.info("Selected IMS POP Location dropdowm is : " + PE_CommandIPV4Dropdown.getText().toString());
				PE_CommandIPV4Dropdown.click();
				Thread.sleep(2000);
				
				EnterTextValue(application, PE_ManagementAddress, "Commands IPV4", "PE_Router_IPV4CommandTextfield");
				click(application, "Execute button", "PE_Router_IPV4Command_Executebutton");
				
}
	
	
	
	
	
	
	
	
	
	public void verifyConfigureInterfaceFunction_PE(String application, String ServiceIdentification, String PE_GenerateConfiguration, String PE_Configuration) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();//Below 3 lines of code require if we try to move from view device page to view service page for configure
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_BackButton_viewdevicepage")));
		click(application, "BACK button", "MAS_PE_BackButton_viewdevicepage"); 
		
		
		//Configure Interface - MAS Device from View Device Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "Show Interfaces Link", "PE_showInterfacesLink"); 
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION PE", "PE_ACTION");
		
		click(application, "Configure", "MAS_PE_ACTION_ConfigureLink");  
		GetText(application, "Select Interfaces header", "MAS_PE_Configure_ConfigureInterface_header");
		
		
		// Verify All Device Information under Device panel for MAS Switch
		GetText(application, "Device Name", "MAS_PE_Configure_DeviceNameValue");
		GetText(application, "Interface", "MAS_PE_Configure_InterfaceValue");
		GetText(application, "Link/Circuit Id", "MAS_PE_Configure_LinkCircuitIdValue");
		GetText(application, "Interface Address Range", "MAS_PE_Configure_InterfaceAddressRangeValue");
		GetText(application, "Interface Address/Mask", "MAS_PE_Configure_InterfaceAddressMaskValue");
		GetText(application, "VRRP IP", "MAS_PE_Configure_VRRPIPValue");
		GetText(application, "Group Number", "MAS_PE_Configure_GroupNumberValue");
		GetText(application, "Bearer Type", "MAS_PE_Configure_BearerTypeValue");
		GetText(application, "Band Width", "MAS_PE_Configure_BandWidthValue");
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configure_Configuration_header")));
		GetText(application, "VLAN Id", "MAS_PE_Configure_VLANIdValue");
		GetText(application, "VRF Id", "MAS_PE_Configure_VRFValue");
		
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
		click(application, "Generate Configuration Dropdown", "MAS_PE_Configure_GenerateConfigurationDropdown");
		Thread.sleep(2000);
		
//		WebElement MAS_GenerateConfigurationSelect=driver.findElement(By.xpath("//div[div[text()='\"+PE_GenerateConfiguration+\"']]"));
//		MAS_GenerateConfigurationSelect.click();

		EnterTextValue(application, PE_GenerateConfiguration, "Configuration dropdown textfield", "MAS_PE_Configure_GenerateConfigurationDropdown");
		click(application, "Select Generate Configuration value from dropdown", "//div[div[text()='"+PE_GenerateConfiguration+"']]");
		click(application, "Generate Configuration Button", "MAS_PE_Configure_GenerateConfigurationButton");
		
		compareText(application, "Generate Configuration for All CPE Routes Link", "MAS_PE_Configure_GenerateConfigurationForAllCPERoutesButton", "Generate Configuration for All CPE Routes");
		compareText(application, "Save Configuration Link", "MAS_PE_Configure_SaveConfigurationButton", "Save Configuration");
		compareText(application, "Execute Configuration on Device Link", "MAS_PE_Configure_ExecuteConfigurationonDeviceButton", "Execute Configuration on Device");
		
		GetText(application, "Configuration information after configuration generated", "MAS_PE_Configure_ConfigurationTextfield");
		click(application, "Back Button", "MAS_PE_Configure_BackButton");
		
		
		Log.info("------ Verified Configure Interfaces Information successfully ------");
	}
	
	
	
	
	
	
	
	
	public void verifyDeleteInterfaceFunction_PE(String application, String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		/*Moveon(getwebelement(xml.getlocator("//locators/" + application + "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]")));
		Thread.sleep(2000);
		click(application, "Service ID on Top Of The Bredcome Navigation", "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]");*/
		
		
//		scrolltoend();//Below 3 lines of code require if we try to move from view device page to view service page for configure
//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_BackButton_viewdevicepage")));
//		click(application, "BACK button", "MAS_PE_BackButton_viewdevicepage"); 
		
		
		//Delete Interface - MAS Device from View Service Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "Show Interfaces Link", "PE_showInterfacesLink"); 
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "PE_ACTION");
		
		click(application, "Delete Button", "MAS_PE_ACTION_DeleteLink");  
				
		compareText(application, "Delete Interface Warning Message from View Service page", "MAS_PE_DeleteInterfaceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "MAS_PE_DeleteButton");
		Thread.sleep(3000);
		compareText(application, "Delete Interface Successfull Message", "MAS_PE_DeleteInterfaceSuccessfullMessage", "Interface deleted successfully");
		
	}
	
	
	
	
	////
	public void verifyDeleteDeviceFunction_PE(String application,String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "View Link", "PE_viewdevice1"); 
		
		//Delete MAS Device from View Device Page
		click(application, "ACTION link", "PE_View_ActionLink");
		click(application, "Delete Device from View Device page", "PE_View_Action_DeleteLink");
		compareText(application, "Delete PE Device Warning Message from View Device page", "PE_ViewDevice_Action_DeletePEDeviceWarningMessage", "Are you sure that you want to delete this item?");
		click(application, "Delete Button", "PE_ViewDevice_Action_DeleteButton");
		Thread.sleep(3000);
		compareText(application, "Delete MAS Device Successful Message", "PE_DeletePEDeviceSuccessfulMessage", "PE Device deleted successfully");

		
		
		//Delete from Service "From View Service page"
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_AddPEDeviceLink")));
		click(application, "Delete From Service Link", "PE_deletefromservicedevice1");
		compareText(application, "Delete PE Device Warning Message from View Service page", "PE_ViewService_DeletePEDeviceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "PE_ViewService_DeleteButton");
		Thread.sleep(3000);
		compareText(application, "Delete PE Device Successfull Message", "PE_DeletePEDeviceSuccessfulMessage", "PE Device deleted successfully");
		
	}
	
	
	
	
	
	
	
			
			
	
	
	
	
	//////////////////////////  TTTRRRUUUNNNKKK   /////////////////////////////////////////////////
	
		public void methodToFindMessagesUnderTextField(String application ,String xpath, String labelname, String expectedmsg) {
		
		boolean defaultPortValueUnderSIPSignalling=false;
		try {	
			defaultPortValueUnderSIPSignalling=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			if(defaultPortValueUnderSIPSignalling) {
				
				WebElement defaultValue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				if(defaultValue.getText().contains(expectedmsg)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
					System.out.println("Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
					System.out.println("Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
				System.out.println("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			System.out.println("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
		}
	}
	
		public void compareText_fromtextFields(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

		WebElement element = null;

		try {
			Thread.sleep(1000);
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			}
			else if (emptyele!=null && emptyele.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
			}else 
			{   
				if(emptyele.equals(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+emptyele+"'");
				}
				else if(emptyele.contains(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+emptyele+"'");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal Text '"+emptyele+"'");
				}
			}
		}catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			System.out.println(labelname + " field is not displaying");
			e.printStackTrace();
		}

	}
	public void WarningMessageCom(String application, String labelname, String xpath ) throws InterruptedException, DocumentException {
		WebElement element= null;
		System.out.println(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname));
		
		try {
			//Thread.sleep(1000);
			element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname));
			
			//Thread.sleep(2000);
			if(element==null) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			}
			else
			{
				String WarningMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname)).getText();

				System.out.println("'"+labelname+"' field warning  message displayed as : " + WarningMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : validation message for '"+labelname+"' text field displayed as : " + WarningMsg);
				Log.info("'"+labelname+"' field warning message displayed as : " + WarningMsg);
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("'"+labelname+"' field warning message is not dipslaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' field warning message is not displaying");
		}catch(Exception ed) {
			ed.printStackTrace();
		}
	}

	
///////// Siva Code//////////////////////////
	
	
	public void CPEdevice_clickOnPPPconfiguration(String application, String existingdevicename,String VPNSiteOrder ) throws InterruptedException, DocumentException {
		
		scrolltoend();
		List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Numberofuser="+ NoOfUsers);

		if(NoOfUsers==1)
		{
			click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
			Thread.sleep(3000);
		}
		else if(NoOfUsers>=1)
		{
			WebElement AddedUser = driver
					.findElement(By.xpath("//div[contains(text(),'" + VPNSiteOrder + "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
			AddedUser.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

		}

		click(application, "Action dropdown", "VPNSiteactionbutton");

		
ClickCommon(application, "View", "AddVPNSiteCommonLink");

			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'PPP configuration link'");
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_existingDeviceGrid")).isDisplayed())
            {
                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/CPE_fetchAlldevice_InviewPage"));
                  int AddedDevicesCount= addeddevicesList.size();
                  for(int i=0;i<AddedDevicesCount;i++) {
                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                        Log.info("number "+ AddedDevice_SNo);
                        if(AddedDeviceNameText.contains(existingdevicename))
                        {
                              WebElement pppConfigurationLink=getwebelement(xml.getlocator("//locators/" + application + "/CPE_pppConfigurationLink").replace("value", AddedDevice_SNo)); 
                              Clickon(pppConfigurationLink);
                              Thread.sleep(2000);
                              break; 
                             
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                        }
                        
                  }
            }
            else
            {
                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
            }		
		}






	public void pppConfiguration(String application) throws InterruptedException, DocumentException {

		waitforPagetobeenable();

		WebElement pppConfigurationPanelHeader = getwebelement(
				xml.getlocator("//locators/" + application + "/pppConfigurationPanelheader"));

		if (pppConfigurationPanelHeader.isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'PPP Configuration' panel is displaying");
			System.out.println(" 'PPP Configuration' panel is displaying");

			boolean clickHereLink = getwebelement(
					xml.getlocator("//locators/" + application + "/pppConfiguration_CPEdevice_clickHereLink"))
							.isDisplayed();
			if (clickHereLink) {
				click_commonMethod(application, "Click here to add", "pppConfiguration_CPEdevice_clickHereLink", xml);
			} else {

				ExtentTestManager.getTest().log(LogStatus.FAIL, "No links displayed for creating new PPP Configuration");

			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'PPP Configuration' panel is not displaying");
			System.out.println(" 'PPP Configuration' panel is not displaying");

		}
	}
	

	
	
	
	
	
	
	
	
	public void addPPPconfiguration(String application, String expectedDevicename,
			String framedWANipAddress, String framedRoute0, String framedRoute1, String framedRoute2,
			String framedRoute3, String framedRoute4, String framedRoute5, String framedRoute6, String framedRoute7,
			String uniQOSprofileName, String QosParameters,String QosParametertextValue, String SAvalidation, 
			String uniSphereEnable,String uniSphereVersion, String ipv6Parameter, String framedIPv6Prefix, String framedIpv6Address, String delegateIPv6Prefix,
			String framedIPv6route0, String framedIPv6route1, String framedIPv6route2, String framedIPv6route3, String framedIPv6route4, String framedIPv6route5,
			String framedIPv6route6, String framedIPv6route7, String uniIPv6LocalInterface, String uniIPv6VirtualRouter,String uniIngressStatistics)
			throws InterruptedException, DocumentException, IOException {

		String actualSelectValueInsideQosParamert = "Null";
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Add PPP Configuration");
		
		WebElement adpppConfigurationPanelHeader = getwebelement(
				xml.getlocator("//locators/" + application + "/addPPPconfigurationPanel_CPedevice"));

		if (adpppConfigurationPanelHeader.isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add PPP Configuration' panel is displaying");
			System.out.println(" 'Add PPP Configuration' panel is displaying");

			// Device Name
			String actualDevicename = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfiguration_deviceName")));
			if (actualDevicename.equalsIgnoreCase(expectedDevicename)) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Device name is displaying as " + actualDevicename + " as expected");
				System.out.println("Device name is displaying as " + actualDevicename + " as expected");
			} else if (actualDevicename.contains(expectedDevicename)) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Device name is displaying as " + actualDevicename + " as expected");
				System.out.println("Device name is displaying as " + actualDevicename + " as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Device name is displaying as " + actualDevicename
						+ ". But the expected value is " + expectedDevicename);
				System.out.println("Device name is displaying as " + actualDevicename + ". But the expected value is "
						+ expectedDevicename);
			}

			// PPP Password
			click_commonMethod(application, "generate password", "addPPPconfiguration_generatePassowrd", xml);

			String passwordField = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfiguration_passwordField")).getAttribute("value");
			if (passwordField.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No value displaying under 'Password' field");
				System.out.println("No value displaying under 'Password' field");
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"value displaying under 'Password' field is displaying as " + passwordField);
				System.out.println("value displaying under 'Password' field is displaying as " + passwordField);

			}

			// Framed/WAN/IP Address
			addtextFields_commonMethod(application, "Framed/WAN/IP Address", "addPPPcofig_famedWANipAddress",
					framedWANipAddress, xml);
			
			
			// Framed/WAN IP Subnet Mask
			String framedWANorIPsubnetMask = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_framedWANIpAddress")));
			if (framedWANorIPsubnetMask.equals("255.255.255.255")) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed/WAN IP Subnet Mask value is displaying as " + framedWANorIPsubnetMask + " as expected");
				System.out.println("Framed/WAN IP Subnet Mask value is displaying as " + framedWANorIPsubnetMask + " as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed/WAN IP Subnet Mask value is displaying as " + framedWANorIPsubnetMask + ". Expected value is 'PPP'");
				System.out.println(
						"Framed/WAN IP Subnet Mask value is displaying as " + framedWANorIPsubnetMask + ". Expected value is 'PPP'");
			}
			
			
			//IPv6 Parameter
			addCheckbox_commonMethod(application, "addPPPconfig_IPv6Paramert", "IPv6 parameters", ipv6Parameter, "no", xml);
			
			
			if(ipv6Parameter.equalsIgnoreCase("Yes")) {
				
				//Framed-IPv6-Prefix  
				addtextFields_commonMethod(application, "Framed-IPv6-Prefix ", "addConfig_framedIPvPrefix", framedIPv6Prefix, xml);
				
				//Framed-IPv6-Address
				addtextFields_commonMethod(application, "Framed-IPv6-Address", "addPPPconfig_framedIPV6address", framedIpv6Address, xml);
				
				//Delegate-IPv6-Prefix:
				addtextFields_commonMethod(application, "Delegate-IPv6-Prefix:", "addPPPconfig_delegateIPv6Prefix", delegateIPv6Prefix, xml);
				
				WebElement framedProtocolElement = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_framedProtocol"));
				scrolltoview(framedProtocolElement);
			}
			

			// Framed Protocol
			String framedProtocol = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_framedProtocol")));
			if (framedProtocol.equals("PPP")) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed protocol value is displaying as " + framedProtocol + " as expected");
				System.out.println("Framed protocol value is displaying as " + framedProtocol + " as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed protocol value is displaying as " + framedProtocol + ". Expected value is 'PPP'");
				System.out.println(
						"Framed protocol value is displaying as " + framedProtocol + ". Expected value is 'PPP'");

			}

			// Framed Route0
			addtextFields_commonMethod(application, "Framed Route0", "addPPPconfig_framedRouter0", framedRoute0, xml);

			// Framed Router1
			addtextFields_commonMethod(application, "Framed Route1", "addPPPconfig_framedRouter1", framedRoute1, xml);

			// Framed Route2
			addtextFields_commonMethod(application, "Framed Route2", "addPPPconfig_framedroute2", framedRoute2, xml);

			// Framed Route3
			addtextFields_commonMethod(application, "Framed Route3", "addPPPconfig_framedRoute3", framedRoute3, xml);

			// Framed Route4
			addtextFields_commonMethod(application, "Framed Route4", "addPPconfig_framedRoute4", framedRoute4, xml);

			// Framed Router5
			addtextFields_commonMethod(application, "Framed Route5", "addPPPconfig_framedRoute5", framedRoute5, xml);

			if(ipv6Parameter.equalsIgnoreCase("Yes")) {
				Log.info("no scroll down");
			}else {
				scrolltoend();
				Thread.sleep(1000);
			}
			
			// Framed route6
			addtextFields_commonMethod(application, "Framed Route6", "addPPPconfig_framedRoute6", framedRoute6, xml);

			// Framed route7
			addtextFields_commonMethod(application, "Framed Route7", "addPPPconfig_framedRoute7", framedRoute7, xml);
			
			
			if(ipv6Parameter.equalsIgnoreCase("Yes")) {
				
				//Framed-IPv6-Route0
				addtextFields_commonMethod(application, "Framed-IPv6-Route0", "addPPPconfig_framedIPv6route0", framedIPv6route0, xml);
				
				//Framed-IPv6-Route1
				addtextFields_commonMethod(application, "Framed-IPv6-Route1", "addPPPconfig_framedIPv6route1", framedIPv6route1, xml);
				
				//Framed-IPv6-Route2
				addtextFields_commonMethod(application, "Framed-IPv6-Route2", "addPPPconfig_framedIPv6route2", framedIPv6route2, xml);
				
				//Framed-IPv6-Route3
				addtextFields_commonMethod(application, "Framed-IPv6-Route3", "addPPPconfig_framedIPv6Route3", framedIPv6route3, xml);
				
				//Framed-IPv6-Route4
				addtextFields_commonMethod(application, "Framed-IPv6-Route4", "addPPPconfig_framedIPv6route4", framedIPv6route4, xml);
				
				//Framed-IPv6-Route5
				addtextFields_commonMethod(application, "Framed-IPv6-Route5", "addPPPconfig_framedIPv6Route5", framedIPv6route5, xml);
				
				//Framed-IPv6-Route7
				addtextFields_commonMethod(application, "Framed-IPv6-Route7", "addPPPconfig_framedIPv6Route7", framedIPv6route6, xml);
				
				scrolltoend();
				
				//Uni-IPv6-Local-Interface (eg. loopback10)
				addtextFields_commonMethod(application, "Uni-IPv6-Local-Interface", "addPPPconfig_uniIPv6LocalInterface", uniIPv6LocalInterface, xml);
				
				//Uni-IPv6-Virtual-Router (eg. TEST3BGP)
				addtextFields_commonMethod(application, "Uni-IPv6-Virtual-Router", "addPPPconfig_uniIPv6Virtualrouter", uniIPv6VirtualRouter, xml);
				
				//Uni-Ingress-Statistics (eg. 1)
				addtextFields_commonMethod(application, "Uni-Ingress-Statistics", "addPPPconfig_uniIngressStatistics", uniIngressStatistics, xml);
				
			}

			// Uni Virtual Route Name
			String uniVirtualActualValue = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_uniVirtualRouterName")).getAttribute("value");
			if (uniVirtualActualValue.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Uni Virtual Route Name'");
				System.out.println("No values dipslaying under 'Uni Virtual Route Name'");

			} else {
				if(uniVirtualActualValue.equalsIgnoreCase("MGMT")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Uni Virtual Route Name Value displays as 'MGMT' as expected");
					Log.info( "Uni Virtual Route Name Value displays as 'MGMT' as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Uni Virtual Route Name mismataches. It displays as: "+ uniVirtualActualValue + " .The Expected value is 'MGMT'");
					Log.info("Uni Virtual Route Name mismataches. It displays as: "+ uniVirtualActualValue + " .The Expected value is 'MGMT'");
				}
			}

			
			// Uni Local Loopback Interface
			String  uniLoopBackInterface = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_uniLocalLoopbackInterface")).getAttribute("value");
			if (uniLoopBackInterface.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Uni Local Loopback Interface field");
				Log.info("No values displaying under 'Uni Local Loopback Interface field");

			} else {
				if(uniLoopBackInterface.equalsIgnoreCase("Loopback999")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Uni Local Loopback Interface Value displays as 'Loopback999' as expected");
					Log.info( "Uni Local Loopback Interface Value displays as 'Loopback999' as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Uni Local Loopback Interface value mismataches. It displays as: "+ uniLoopBackInterface + " .The Expected value is 'Loopback999'");
					Log.info("Uni Local Loopback Interface value mismataches. It displays as: "+ uniLoopBackInterface + " .The Expected value is 'Loopback999'");
				}
			}
			
			//Uni Ingress Policy
			String UNIingresspolicy = getwebelement(xml.getlocator("//locators/" + application + "/uniIngressPolicy_textField")).getAttribute("value");
			if (UNIingresspolicy.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Uni Ingress Policy' field");
				Log.info("No values displaying under 'Uni Ingress Policy' field");

			} else {
				if(UNIingresspolicy.equalsIgnoreCase("VPN-DEFAULT")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Uni Ingress Policy Value displays as 'VPN-DEFAULT' as expected");
					Log.info( "Uni Ingress Policy Value displays as 'VPN-DEFAULT' as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Uni Ingress Policy value mismataches. It displays as: "+ UNIingresspolicy + " .The Expected value is 'VPN-DEFAULT'");
					Log.info("Uni Ingress Policy value mismataches. It displays as: "+ UNIingresspolicy + " .The Expected value is 'VPN-DEFAULT'");
				}
			}					
			
			// Uni Qos Profile Name
			addDropdownValues_commonMethod(application, " Uni Qos Profile Name", "addPPPconfig_uniQOSprofileName", uniQOSprofileName, xml);
			
			 
			//Qos Parameters
			addDropdownValues_commonMethod(application, "Qos Parameters", "addPPPconfig_QosParameters", QosParameters, xml);
			
			//Qos Paramerts text
			addtextFields_commonMethod(application, "Qos Parameters", "addPPPconfig_QosParamertsTextField", QosParametertextValue, xml);
			
			click_commonMethod(application, ">>", "addPPPconfig_rightArrowButtonForSelection", xml);
			
			WebElement selectedQosParamert = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_selectedQosParameters"));
			org.openqa.selenium.support.ui.Select sel = new Select(selectedQosParamert);
			List<WebElement> we = sel.getOptions();
			   
		    for(WebElement a : we)
		    {
		        if(!a.getText().equals("select")){
		           
		        	actualSelectValueInsideQosParamert = a.getText();
		        }
		    }
		    
			String expectedQosParamertvalue = QosParameters + " " + QosParametertextValue;
			if(actualSelectValueInsideQosParamert.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Select Qos paramert' textbox");
				Log.info("No values displaying under 'Select Qos parameters' textbox");
				
			}else {
				if(expectedQosParamertvalue.equals(actualSelectValueInsideQosParamert)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "value displaying under 'Qos Selected Paramert' field displaying as: " + actualSelectValueInsideQosParamert);
					Log.info("value displaying under 'Qos Selected Parameters' field displaying as: " + actualSelectValueInsideQosParamert);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "value displaying under 'Qos Selected Paramert' field mismatches. It is displaying as: " + actualSelectValueInsideQosParamert + ". But the expected value is: " + expectedQosParamertvalue);
					Log.info("value displaying under 'Qos Selected Parameters' field mismatches. It is displaying as: " + actualSelectValueInsideQosParamert + ". But the expected value is: " + expectedQosParamertvalue);
				}
			}
			
			//SA Validation
			addDropdownValues_commonMethod(application, "SA Validation", "addPPPconfig_SAvalidation", SAvalidation, xml);
			
			//Unisphere -IGMP-enable
			addDropdownValues_commonMethod(application, " Unisphere -IGMP-enable", "addPPPconfig_uniSphereIGMPenable", uniSphereEnable, xml);
			
			// Unisphere -IGMP-version
			addDropdownValues_commonMethod(application, " Unisphere -IGMP-version", "addPPPconfig_uniSphereIGMPversion", uniSphereVersion, xml);
			
			
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "addPPPconfig_OKbutton", xml); // OK button
			waitforPageenable();

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add PPP Configuration' panel is not displaying");
			System.out.println(" 'Add PPP Configuration' panel is not displaying");
		}
	}
	
	
	public void viewPPPconfiguration(String application, String expectedDevicename,
			String framedWANipAddress, String framedRoute0, String framedRoute1, String framedRoute2,
			String framedRoute3, String framedRoute4, String framedRoute5, String framedRoute6, String framedRoute7,
			String uniVirtualRouterName, String uniQOSprofileName, String QosParameters,String QosParametertextValue, String SAvalidation, 
			String uniSphereEnable,String uniSphereVersion, String ipv6Parameter, String framedIPv6Prefix, String framedIpv6Address, String delegateIPv6Prefix,
			String framedIPv6route0, String framedIPv6route1, String framedIPv6route2, String framedIPv6route3, String framedIPv6route4, String framedIPv6route5,
			String framedIPv6route6, String framedIPv6route7)
			throws InterruptedException, DocumentException, IOException {
		
		String expectedQosParamertvalue = QosParameters + " " + QosParametertextValue;
		
		Thread.sleep(15000);
		waitForpageload();  waitforPagetobeenable();
		
		compareText_InViewPage(application, "Framed/WAN IP Address", framedWANipAddress , xml);		//Framed/WAN IP Address
		
		compareText_InViewPage(application, "Framed/WAN IP Subnet Mask", "255.255.255.255" , xml);		//Framed/WAN IP Subnet Mask
		
		compareText_InViewPPPconfigurationPage(application, "Framed Protocol", "PPP", xml);		//Framed Protocol
		
		if(ipv6Parameter.equalsIgnoreCase("Yes")) {
			
			compareText_InViewPPPconfigurationPage(application, "Framed-IPv6-Address", framedIpv6Address, xml);
			
			compareText_InViewPPPconfigurationPage(application, "Framed-Interface-Id", "0000000000003130" , xml);  	//Framed-Interface-Id
			
			compareText_InViewPPPconfigurationPage(application, "Delegate-IPv6-Prefix ", delegateIPv6Prefix, xml); 	//Delegate-IPv6-Prefix 
			
			compareText_InViewPPPconfigurationPage(application, "Framed-IPv6-Route0 ", framedIPv6route0, xml);  //Framed-IPv6-Route0 
			
			compareText_InViewPPPconfigurationPage(application, "Framed-IPv6-Route1", framedIPv6route1, xml);  	//Framed-IPv6-Route1
			
			compareText_InViewPPPconfigurationPage(application, "Framed-IPv6-Route2", framedIPv6route2, xml);    //Framed-IPv6-Route2
			
			compareText_InViewPPPconfigurationPage(application, "Framed-IPv6-Route3", framedIPv6route3, xml);  	 //Framed-IPv6-Route3
			
			compareText_InViewPPPconfigurationPage(application, "Framed-IPv6-Route4", framedIPv6route4, xml);  	 //Framed-IPv6-Route4
			
			compareText_InViewPPPconfigurationPage(application, "Framed-IPv6-Route5 ", framedIPv6route5, xml);    //Framed-IPv6-Route5 
			
			compareText_InViewPPPconfigurationPage(application, "Framed-IPv6-Route6 ", framedIPv6route6, xml);    //Framed-IPv6-Route6
			
			compareText_InViewPPPconfigurationPage(application, "Framed-IPv6-Route7 ", framedIPv6route7, xml);    //Framed-IPv6-Route7 
			
		}
		
		
		compareText_InViewPPPconfigurationPage(application, "Framed Route0 ", framedRoute0, xml);  		//Framed Route0
		
		compareText_InViewPPPconfigurationPage(application, "Framed Route1 ", framedRoute1, xml);  	//Framed Route1 
		
		compareText_InViewPPPconfigurationPage(application, "Framed Route2 ", framedRoute2, xml);   //Framed Route2 
		
		compareText_InViewPPPconfigurationPage(application, "Framed Route3 ", framedRoute3, xml);  	//Framed Route3 
		
		compareText_InViewPPPconfigurationPage(application, "Framed Route4 ", framedRoute4, xml);  	//Framed Route4 
		
		compareText_InViewPPPconfigurationPage(application, "Framed Route5 ", framedRoute5, xml);  	//Framed Route5 
		
		compareText_InViewPPPconfigurationPage(application, "Framed Route6 ", framedRoute6, xml);  	//Framed Route6 
		
//		compareText_InViewPage(application, "Uni-IPv6-Local-Interface ", ExpectedText, xml);  	//Uni-IPv6-Local-Interface 
//		
//		compareText_InViewPage(application, "Uni-IPv6-Virtual-Router ", ExpectedText, xml);   //Uni-IPv6-Virtual-Router 
//		
//		compareText_InViewPage(application, "Uni-Ingress-Statistics ", ExpectedText, xml);    //Uni-Ingress-Statistics 
		
//		compareText_InViewPPPconfigurationPage(application, "Uni Virtual Route Name ", "MGMT", xml);  	//Uni Virtual Route Name 
//		
//		compareText_InViewPPPconfigurationPage(application, "Uni Local Loopback Interface ", "Loopback999", xml);  	//Uni Local Loopback Interface
//		
//		compareText_InViewPPPconfigurationPage(application, "Uni Ingress Policy ", "VPN-DEFAULT", xml);  	//Uni Ingress Policy
		
		compareText_InViewPPPconfigurationPage(application, "Uni Qos Profile Name ", uniQOSprofileName, xml);  	//Uni Qos Profile Name 
		
		compareText_InViewPPPconfigurationPage(application, "Qos Parameters ", "business-weight", xml);  	//Qos Parameters 
		
		String actualSAvalidationvalue ="Null";
		if(SAvalidation.equalsIgnoreCase("Enable")) {
			
			actualSAvalidationvalue = "Enabled";
					
		}else if(SAvalidation.equalsIgnoreCase("disable")) {
			
			actualSAvalidationvalue = "Disabled";
		}
		compareText_InViewPPPconfigurationPage(application, "SA Validations ", actualSAvalidationvalue, xml);  		//SA Validations
		
		compareText_InViewPPPconfigurationPage(application, "Unisphere-IGMP-enabled ", uniSphereEnable , xml);  	//Unisphere-IGMP-enabled
		
		compareText_InViewPPPconfigurationPage(application, "Unisphere-IGMP-Version ", uniSphereVersion, xml);  	// Unisphere -IGMP-version
		
	}
	
	
	
	public void editPPPconfiguration(String application, String framedWANipAddress, String framedRoute0, String framedRoute1, String framedRoute2,
			String framedRoute3, String framedRoute4, String framedRoute5, String framedRoute6, String framedRoute7,
			String uniQOSprofileName, String QosParameters,String QosParametertextValue, String SAvalidation, 
			String uniSphereEnable,String uniSphereVersion, String ipv6Parameter, String framedIPv6Prefix, String framedIpv6Address, String delegateIPv6Prefix,
			String framedIPv6route0, String framedIPv6route1, String framedIPv6route2, String framedIPv6route3, String framedIPv6route4, String framedIPv6route5,
			String framedIPv6route6, String framedIPv6route7,String uniIPv6LocalInterface, String uniIPv6VirtualRouter,String uniIngressStatistics) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		Thread.sleep(1000);
		//ScrolltoElement(application, "viewPPPconfig_ActionDropdown");
		click_commonMethod(application, "Action", "viewPPPconfig_ActionDropdown", xml);
		
		click_commonMethod(application, "Edit", "pppConfig_editLink", xml);
		
		String actualSelectValueInsideQosParamert = "Null";
		
		WebElement editPPPPanel = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_uniVirtualRouterName"));
		
		if(editPPPPanel.isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit PPP Configuration' page is displaying");
			Log.info("'Edit PPP Configuration' page is displaying");
			
			// Framed/WAN/IP Address
			edittextFields_commonMethod(application, "Framed/WAN/IP Address", "addPPPcofig_famedWANipAddress",
					framedWANipAddress, xml);
			
			
			// Framed/WAN IP Subnet Mask
			String framedWANorIPsubnetMask = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_framedWANIpAddress")));
			if (framedWANorIPsubnetMask.equals("255.255.255.255")) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed/WAN IP Subnet Mask value is displaying as " + framedWANorIPsubnetMask + " as expected");
				System.out.println("Framed/WAN IP Subnet Mask value is displaying as " + framedWANorIPsubnetMask + " as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed/WAN IP Subnet Mask value is displaying as " + framedWANorIPsubnetMask + ". Expected value is 'PPP'");
				System.out.println(
						"Framed/WAN IP Subnet Mask value is displaying as " + framedWANorIPsubnetMask + ". Expected value is 'PPP'");
			}
			
			
			//IPv6 Address
			editcheckbox_commonMethod(application, ipv6Parameter, "addPPPconfig_IPv6Paramert", "IPv6 parameters", xml);
			
			
			boolean IPv6ParamertSelection = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_IPv6Paramert")).isSelected();
			if(IPv6ParamertSelection) {
				
				//Framed-IPv6-Prefix  
				edittextFields_commonMethod(application, "Framed-IPv6-Prefix ", "addConfig_framedIPvPrefix", framedIPv6Prefix, xml);
				
				//Framed-IPv6-Address
				edittextFields_commonMethod(application, "Framed-IPv6-Address", "addPPPconfig_framedIPV6address", framedIpv6Address, xml);
				
				//Delegate-IPv6-Prefix:
				edittextFields_commonMethod(application, "Delegate-IPv6-Prefix:", "addPPPconfig_delegateIPv6Prefix", delegateIPv6Prefix, xml);
				
				WebElement framedProtocolElement = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_framedProtocol"));
				scrolltoview(framedProtocolElement);
				
			}
			
			
			// Framed Protocol
			String framedProtocol = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_framedProtocol")));
			if (framedProtocol.equals("PPP")) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed protocol value is displaying as " + framedProtocol + " as expected");
				System.out.println("Framed protocol value is displaying as " + framedProtocol + " as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed protocol value is displaying as " + framedProtocol + ". Expected value is 'PPP'");
				System.out.println(
						"Framed protocol value is displaying as " + framedProtocol + ". Expected value is 'PPP'");

			}

			// Framed Route0
			edittextFields_commonMethod(application, "Framed Route0", "addPPPconfig_framedRouter0", framedRoute0, xml);

			// Framed Router1
			edittextFields_commonMethod(application, "Framed Route1", "addPPPconfig_framedRouter1", framedRoute1, xml);

			// Framed Route2
			edittextFields_commonMethod(application, "Framed Route2", "addPPPconfig_framedroute2", framedRoute2, xml);

			// Framed Route3
			edittextFields_commonMethod(application, "Framed Route3", "addPPPconfig_framedRoute3", framedRoute3, xml);

			// Framed Route4
			edittextFields_commonMethod(application, "Framed Route4", "addPPconfig_framedRoute4", framedRoute4, xml);

			// Framed Router5
			edittextFields_commonMethod(application, "Framed Route5", "addPPPconfig_framedRoute5", framedRoute5, xml);

			if(IPv6ParamertSelection) {
				Log.info("no scroll down");
			}else {
				scrolltoend();
				Thread.sleep(1000);
			}
			
			// Framed route6
			edittextFields_commonMethod(application, "Framed Route6", "addPPPconfig_framedRoute6", framedRoute6, xml);

			// Framed route7
			edittextFields_commonMethod(application, "Framed Route7", "addPPPconfig_framedRoute7", framedRoute7, xml);
			
			
			if(IPv6ParamertSelection) {
				
				//Framed-IPv6-Route0
				edittextFields_commonMethod(application, "Framed-IPv6-Route0", "addPPPconfig_framedIPv6route0", framedIPv6route0, xml);
				
				//Framed-IPv6-Route1
				edittextFields_commonMethod(application, "Framed-IPv6-Route1", "addPPPconfig_framedIPv6route1", framedIPv6route1, xml);
				
				//Framed-IPv6-Route2
				edittextFields_commonMethod(application, "Framed-IPv6-Route2", "addPPPconfig_framedIPv6route2", framedIPv6route2, xml);
				
				//Framed-IPv6-Route3
				edittextFields_commonMethod(application, "Framed-IPv6-Route3", "addPPPconfig_framedIPv6Route3", framedIPv6route3, xml);
				
				//Framed-IPv6-Route4
				edittextFields_commonMethod(application, "Framed-IPv6-Route4", "addPPPconfig_framedIPv6route4", framedIPv6route4, xml);
				
				//Framed-IPv6-Route5
				edittextFields_commonMethod(application, "Framed-IPv6-Route5", "addPPPconfig_framedIPv6Route5", framedIPv6route5, xml);
				
				//Framed-IPv6-Route7
				edittextFields_commonMethod(application, "Framed-IPv6-Route7", "addPPPconfig_framedIPv6Route7", framedIPv6route6, xml);
				
				scrolltoend();
				
				//Uni-IPv6-Local-Interface (eg. loopback10)
				addtextFields_commonMethod(application, "Uni-IPv6-Local-Interface", "addPPPconfig_uniIPv6LocalInterface", uniIPv6LocalInterface, xml);
				
				//Uni-IPv6-Virtual-Router (eg. TEST3BGP)
				addtextFields_commonMethod(application, "Uni-IPv6-Virtual-Router", "addPPPconfig_uniIPv6Virtualrouter", uniIPv6VirtualRouter, xml);
				
				//Uni-Ingress-Statistics (eg. 1)
				addtextFields_commonMethod(application, "Uni-Ingress-Statistics", "addPPPconfig_uniIngressStatistics", uniIngressStatistics, xml);
				
			}
			
			// Uni Virtual Route Name
			String uniVirtualActualValue = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_uniVirtualRouterName")).getAttribute("value");
			if (uniVirtualActualValue.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Uni Virtual Route Name'");
				System.out.println("No values dipslaying under 'Uni Virtual Route Name'");

			} else {
				if(uniVirtualActualValue.equalsIgnoreCase("MGMT")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Uni Virtual Route Name Value displays as 'MGMT' as expected");
					Log.info( "Uni Virtual Route Name Value displays as 'MGMT' as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Uni Virtual Route Name mismataches. It displays as: "+ uniVirtualActualValue + " .The Expected value is 'MGMT'");
					Log.info("Uni Virtual Route Name mismataches. It displays as: "+ uniVirtualActualValue + " .The Expected value is 'MGMT'");
				}
			}

			
			// Uni Local Loopback Interface
			String  uniLoopBackInterface = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_uniLocalLoopbackInterface")).getAttribute("value");
			if (uniLoopBackInterface.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Uni Local Loopback Interface field");
				Log.info("No values displaying under 'Uni Local Loopback Interface field");

			} else {
				if(uniLoopBackInterface.equalsIgnoreCase("Loopback999")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Uni Local Loopback Interface Value displays as 'Loopback999' as expected");
					Log.info( "Uni Local Loopback Interface Value displays as 'Loopback999' as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Uni Local Loopback Interface value mismataches. It displays as: "+ uniLoopBackInterface + " .The Expected value is 'Loopback999'");
					Log.info("Uni Local Loopback Interface value mismataches. It displays as: "+ uniLoopBackInterface + " .The Expected value is 'Loopback999'");
				}
			}
			
			//Uni Ingress Policy
			String UNIingresspolicy = getwebelement(xml.getlocator("//locators/" + application + "/uniIngressPolicy_textField")).getAttribute("value");
			if (UNIingresspolicy.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Uni Ingress Policy' field");
				Log.info("No values displaying under 'Uni Ingress Policy' field");

			} else {
				if(UNIingresspolicy.equalsIgnoreCase("VPN-DEFAULT")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Uni Ingress Policy Value displays as 'VPN-DEFAULT' as expected");
					Log.info( "Uni Ingress Policy Value displays as 'VPN-DEFAULT' as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Uni Ingress Policy value mismataches. It displays as: "+ UNIingresspolicy + " .The Expected value is 'VPN-DEFAULT'");
					Log.info("Uni Ingress Policy value mismataches. It displays as: "+ UNIingresspolicy + " .The Expected value is 'VPN-DEFAULT'");
				}
			}
			
			
			// Uni Qos Profile Name
			addDropdownValues_commonMethod(application, " Uni Qos Profile Name", "addPPPconfig_uniQOSprofileName", uniQOSprofileName, xml);
			
			//Qos Parameters
			addDropdownValues_commonMethod(application, "Qos Parameters", "addPPPconfig_QosParameters", QosParameters, xml);
			
			//Qos Paramerts text
			addtextFields_commonMethod(application, "Qos Parameters", "addPPPconfig_QosParamertsTextField", QosParametertextValue, xml);
			
			click_commonMethod(application, ">>", "addPPPconfig_rightArrowButtonForSelection", xml);
			
			WebElement selectedQosParamert = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_selectedQosParameters"));
			org.openqa.selenium.support.ui.Select sel = new Select(selectedQosParamert);
			List<WebElement> we = sel.getOptions();
			   
		    for(WebElement a : we)
		    {
		        if(!a.getText().equals("select")){
		           
		        	actualSelectValueInsideQosParamert = a.getText();
		        }
		    }
		    
			String expectedQosParamertvalue = QosParameters + " " + QosParametertextValue;
			if(actualSelectValueInsideQosParamert.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Select Qos paramert' textbox");
				Log.info("No values displaying under 'Select Qos parameters' textbox");
				
			}else {
				if(expectedQosParamertvalue.equals(actualSelectValueInsideQosParamert)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "value displaying under 'Qos Selected Paramert' field displaying as: " + actualSelectValueInsideQosParamert);
					Log.info("value displaying under 'Qos Selected Parameters' field displaying as: " + actualSelectValueInsideQosParamert);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "value displaying under 'Qos Selected Paramert' field mismatches. It is displaying as: " + actualSelectValueInsideQosParamert + ". But the expected value is: " + expectedQosParamertvalue);
					Log.info("value displaying under 'Qos Selected Parameters' field mismatches. It is displaying as: " + actualSelectValueInsideQosParamert + ". But the expected value is: " + expectedQosParamertvalue);
				}
			}
			
			//SA Validation
			addDropdownValues_commonMethod(application, "SA Validation", "addPPPconfig_SAvalidation", SAvalidation, xml);
			
			//Unisphere -IGMP-enable
			addDropdownValues_commonMethod(application, " Unisphere -IGMP-enable", "addPPPconfig_uniSphereIGMPenable", uniSphereEnable, xml);
			
			// Unisphere -IGMP-version
			addDropdownValues_commonMethod(application, " Unisphere -IGMP-version", "addPPPconfig_uniSphereIGMPversion", uniSphereVersion, xml);
			
			
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "addPPPconfig_OKbutton", xml); // OK button
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit PPP Configuration' page is not displaying");
			Log.info("'Edit PPP Configuration' page is not displaying");
			
		}
	}

	/**
	 *  For Comparing the values under view page
	 * @param application
	 * @param labelname
	 * @param xpath
	 * @param ExpectedText
	 * @param xml
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unused")
	public void compareText_InViewPPPconfigurationPage(String application, String labelname,  String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

		String text = null;
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element = getwebelement("(//div[div[label[contains(text(),'" + labelname + "')]]]//label)[2]");
			String emptyele = element.getText().toString();

			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
				System.out.println(labelname+" not found");
			}
			
			else if(emptyele!=null && emptyele.isEmpty()) {
//				ExtentTestManager.getTest().log(LogStatus.PASS,  labelname + "' value is empty");
				
				emptyele= "Null";
				
				sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
				
				if(emptyele.equalsIgnoreCase(ExpectedText)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " The Expected value for '"+ labelname +"' field is same as the Acutal value '"+text+"'");
					System.out.println(" The Expected Text for '"+ labelname +"' field is same as the Acutal Text '"+text+"'");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					System.out.println(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
				

			}else 
			{   
				text = element.getText();
				if(text.equalsIgnoreCase(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				}
				else if(ExpectedText.contains(text)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			System.out.println(labelname + " field is not displaying");
		}
	}
	
	
	
	
	public void deletePPPconfiguration(String application) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		Thread.sleep(1000);
		
		click_commonMethod(application, "Action", "viewPPPconfig_ActionDropdown", xml);
		
		click_commonMethod(application, "Delete", "deletePPPconfigurationLink", xml);
		
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/pppConfiguration_deletepopup"));
         if(DeleteAlertPopup.isDisplayed())
         {
       	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/pppConfiguration_deletePopup_TextMessage")));
       	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete alert POP up displays. Message is displaying as: "+ deletPopUpMessage);
       	 
            click_commonMethod(application, "Delete", "deletePPPconfigurationPopup_deleteButton", xml);
       	//click(application, "Delete", "DeleteAny");
       	Thread.sleep(2000);
            scrollToTop();
            Thread.sleep(1000);
            
            verifysuccessmessage(application, "PPP Configuration successfully deleted.");
            
         }
         else
         {
               Log.info("Delete alert popup is not displayed");
               ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
         }
		
		
	}

 	public void shownewInfovista(String application) throws InterruptedException, DocumentException {
 		  
 		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		
   		//WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		//ScrolltoElement(orderPanel);
		Thread.sleep(3000);
   		
	   click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
	   Thread.sleep(2000);
	   click_commonMethod(application, "Show infovista link", "Editservice_infovistareport", xml);
	   Thread.sleep(6000);
	   
	   String expectedPageName= "SSO login Page";
	   
	   //Switch to new tab
	   List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
	   driver.switchTo().window(browserTabs .get(1));
	   Thread.sleep(10000);

	  try { 
	   // Get Tab name
	   String pageTitle=driver.switchTo().window(browserTabs .get(1)).getTitle();
	   System.out.println("page title displays as: "+pageTitle);
	  
	   
	   Thread.sleep(3000);
	   driver.switchTo().window(browserTabs.get(0)); 
	   
//	   assertEquals(pageTitle, expectedPageName, " on clicking 'Show Infovista link', it got naviagted to "+pageTitle);
	   
	  ExtentTestManager.getTest().log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
	   Thread.sleep(3000);
	   
	   ExtentTestManager.getTest().log(LogStatus.PASS, "show info vista page actual title: "+pageTitle );
	   ExtentTestManager.getTest().log(LogStatus.PASS, "show info vista page expected title: "+ expectedPageName);
	   
	  }catch(Exception e) {
		  
		  e.printStackTrace();
		  
		  Thread.sleep(3000);
		  driver.switchTo().window(browserTabs.get(0));
		  
		  ExtentTestManager.getTest().log(LogStatus.FAIL, expectedPageName + " page is not displaying");
		   
	  }
   		
   }

 	public void syncservices(String application) throws InterruptedException, DocumentException {
 		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
 	   //WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
 	//	ScrolltoElement(application,orderPanel);
 		Thread.sleep(3000);
 	   
 	   click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
 	   Thread.sleep(2000);
 	   click_commonMethod(application, "Synchronize link", "Editservice_sysnchronizelink", xml);
 	   Thread.sleep(3000);
 	   
 	   scrollToTop();
 	   Thread.sleep(3000);
 	   
 	  try { 
 	   boolean syncSuccessMessage=getwebelement(xml.getlocator("//locators/" + application + "/alertForSynchronize")).isDisplayed();
 	   if(syncSuccessMessage) {
 		   
 		   String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSG_synchronize")).getText();
 		   ExtentTestManager.getTest().log(LogStatus.PASS, " success message for synchronize displays as: "+actualmsg);
 		   System.out.println( " success message for synchronize displays as: "+actualmsg);
 		   
 	   }else {
 		   ExtentTestManager.getTest().log(LogStatus.FAIL, " Success message did not display after clicking on 'Synchronize' button");
 		   System.out.println(" Success message did not display after clicking on 'Synchronize' button");
 	   }
 	   
 	  }catch(Exception e) {
 		  e.printStackTrace();
 		  ExtentTestManager.getTest().log(LogStatus.FAIL, " Success message did not display after clicking on 'Synchronize' button");
 		  System.out.println(" Success message did not display after clicking on 'Synchronize' button");
 	  }
    }	
	
	
	
	
	
	
	
	
	
	
	
	
	 public boolean findPanelHeader(String application, String devicePanelName) throws InterruptedException, DocumentException {

		 waitForpageload();
		 waitforPagetobeenable();
		 Thread.sleep(1000);
		 
		  scrolltoend();
		  Thread.sleep(2000);
		  WebElement el=null;
		  boolean panelheader=false;
		try { 
			el=getwebelement(xml.getlocator("//locators/" + application + "/devicePanelHeaders_InViewSiteOrderPage").replace("value", devicePanelName));
			panelheader=el.isDisplayed();
			
		 if(panelheader) {
			 ExtentTestManager.getTest().log(LogStatus.INFO, devicePanelName +" panel is displaying under 'view VPN site order' page");
			 System.out.println(" 'Equipment' panel is displaying under 'view VPN site order' page");
			 panelheader=true;
			 
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.INFO, devicePanelName + "  panel is not displaying under 'view VPN site order' page");
			 System.out.println(" 'Equipment' panel is not displaying under 'view VPN site order' page");
			 panelheader=false;
			 
		 }}catch(NoSuchElementException e) {
			 e.printStackTrace();
			 ExtentTestManager.getTest().log(LogStatus.INFO, devicePanelName + " panel is not displaying under 'view VPN site order' page");
			 System.out.println(" 'Equipment' panel is not displaying under 'view VPN site order' page");
			 panelheader=false;
			 
		 }
		 
		  return panelheader;
	  }

	 
	 public void SelectPEdevice_existingDevice(String application, String existingDeviceName)
				throws InterruptedException, DocumentException, IOException {

		 scrolltoend();
			Thread.sleep(3000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO,"Select existing PE device");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PEdevice_adddevicelink")));
			Thread.sleep(6000);
			
			waitForpageload();
			waitforPagetobeenable();
			
			addDropdownValues_PEdevice(application, "Choose a device", "chooseAdeviceDropdown", existingDeviceName, xml);
			Thread.sleep(3000);
			
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "Next", "nextButton" , xml);
			Thread.sleep(3000);
			
			}
	 
	 

	 public void verifyValuesforProviderEqiupment(String application) throws InterruptedException, DocumentException	{
  	   scrollToTop();
  	   Thread.sleep(2000);
		 
  	   fetchValueFromViewPage(application, "Name");
  	   fetchValueFromViewPage(application, "Vendor/Model");
  	   fetchValueFromViewPage(application, "Management Address");
  	   fetchValueFromViewPage(application, "SNMP Version");
  	   fetchValueFromViewPage(application, "Snmprw");
  	   fetchValueFromViewPage(application, "Snmpro");
  	   fetchValueFromViewPage(application, "Country");
  	   fetchValueFromViewPage(application, "City");
  	   fetchValueFromViewPage(application, "Site");
  	   fetchValueFromViewPage(application, "Connectiviy Protocol");
  	   fetchValueFromViewPage(application, "Premise");
  	   
     }
	 
	 
	 public String fetchVendorModel_PE(String application) throws InterruptedException, DocumentException, IOException {
		 
		 scrollToTop();
		String vendorModelName = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewPEdveice_fetchVendorName")));
		 
		return vendorModelName;
	 }
	 
	 
	 public String fetchManagementAddress_PE(String application) throws InterruptedException, DocumentException, IOException {
		 
		 scrollToTop();
			String managementAddress = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewPEdevice_fetchManagementAddress")));
			 
			return managementAddress;
		 }
	 
	 
	 /**
		 *  Fetching the value from View device page
		 * @param application
		 * @param labelname
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		@SuppressWarnings("unused")
		public void fetchValueFromViewPage(String application, String labelname) throws InterruptedException, DocumentException { 

			String text = null;
			WebElement element = null;

			try {
				Thread.sleep(1000);
				element = getwebelement(xml.getlocator("//locators/" + application + "/viewPEdevicePage_fetchDeviceDetails").replace("value", labelname));
				String emptyele = element.getText().toString();

				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
					System.out.println(labelname+" not found");
				}
				else if (emptyele!=null && emptyele.isEmpty()) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "No value displaying under "+ labelname);
					System.out.println("No value displaying under "+ labelname);
				}
//					
				else {
					element.getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " value is displaying as: "+ emptyele);
					System.out.println(labelname + " value is displaying as: "+ emptyele);
					
				} 
				
			}catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
				System.out.println(labelname + " field is not displaying");
			}
		}
		
		
		public void clickOnAddInterfaceLink(String application) throws InterruptedException, DocumentException {
			
			WebElement routerToolPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/routerToolPanelheader"));
			scrolltoview(routerToolPanelHeader);
			Thread.sleep(1000);
			
			click_commonMethod(application, "Action", "InterfacePanel_actionDropdown" , xml);
			
			click_commonMethod(application, "Add Interface", "addInterfaceOrLink" , xml);
			Thread.sleep(2000);
			
		}
		
		
		public String addInterface_Juniper(String application, String serviceSubType, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, String newAddressRangeIpv4selection, String newinterfaceAddressrange,
				String subnetSizeValue_IPv4, String availableBlocksValue_IPv4, String link, String bearerType, String encapsulation, String slot, String port, String vlanid,
				String unitId, String pic, String STM1number, String bandwidth, String cardType, String frameType, String clockSource, String timeSlot, 
				String configureInterfaceOnDevice, String IVmanagement,
				String existingAddressRangeIPv6selection, String  existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6,
				String subnetSizeValue_IPv6, String availableBlocksValue_IPv6, String bearerNumber) throws InterruptedException, DocumentException, 
		IOException { 
			
			boolean addressValue=false;
			
			waitForpageload();   waitforPagetobeenable();
			
			scrollToTop();
		//Configure Interface on Device	
			addCheckbox_commonMethod(application, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", configureInterfaceOnDevice, "Yes" , xml);
			
		//IPv4 Configuration	
			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
				
			//EIP Allocation
				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
				
				addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv4, xml);
				
				addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv4, xml);
				
				click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
				
				EIPallocationSuccessMessage(application, "successfully allocated");
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				Thread.sleep(2000);
				
				waitForpageload();  waitforPagetobeenable();
				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
				
				String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
				
				if(interfaceAddressRange.isEmpty()) {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
					System.out.println("No values displaying under 'Interface Address Range_IPv4' dropdown");
					  
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					
					click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
					Thread.sleep(5000);
					waitForpageload();   waitforPagetobeenable();
					
					addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
					if(addressValue) {
						
						selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface" , existingAddressIPv4DropdownValue);
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
						System.out.println("'Address_IPv4' dropdown is not displaying");
					}
				}
			}
			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
				
				  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
			
			WebElement IPv4Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/PE_addInterface_networklabelName"));
			scrolltoview(IPv4Netwrk); 


		//IPv6 Configuration
		if(serviceSubType.equalsIgnoreCase("IPVPN Plus") ||  serviceSubType.equalsIgnoreCase("IPVPN Access")) {	
			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv6, xml);
					
					addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
					
					EIPallocationSuccessMessage(application, "successfully allocated");
					
					click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					
					String interfaceAddressRange=getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")).getText();
					
					if(interfaceAddressRange.isEmpty()) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv6' dropdown");
						System.out.println("No values displaying under 'Interface Address Range_IPv6' dropdown");
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						System.out.println("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						
						click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
						Thread.sleep(4000);
						waitForpageload();  waitforPagetobeenable();
						
					try {	
						addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
						if(addressValue) {
							
							selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
							System.out.println("'Address_IPv6' dropdown is not displaying");
						}
					  }catch(Exception e) {
						  e.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						  System.out.println("'Address_IPv6' dropdown is not displaying");
					  }
					
					}
					
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
			WebElement IPv6Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/networkLabelname_IPv6"));
			scrolltoview(IPv6Netwrk); 

		}
			
			
		//Link Text Field
			addtextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
			
		//Bearer Type Dropdown
			addDropdownValues_commonMethod(application, "Bearer Type" , "bearerType_Dropdown" , bearerType, xml);
			
			if(bearerType.equals("10Gigabit Ethernet")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//Interface
//				WebElement interfaceDefaultValue=getwebelement(xml.getlocator("//locators/" + application + "/Interface_textField"));
//				Gettext(interfaceDefaultValue);
				
				compareText(application, "Interface", "Interface_textField", "xe-//.0", xml);		//compare Interface default value
				WebElement linkElement = getwebelement(xml.getlocator("//locators/" + application + "/Link_textField"));
				scrolltoview(linkElement);
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				addtextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				addtextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				addtextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
//				
//				if(IVmanagement.equalsIgnoreCase("Yes")) {
//					
//					//VLAN Id
//					addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//				}
//				
				String InterfaceName_10Gig = "xe-" + slot + "/" + pic + "/" + port + "." + unitId; 
				compareText(application, "Interface", "Interface_textField", InterfaceName_10Gig , xml);
				
				
			}
			else if(bearerType.equals("E1")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//Framing Type
				addDropdownValues_commonMethod(application, "Framing Type", "framingType_Dropdown" , frameType, xml);
				
				 if(frameType.equals("no-crc4")) {
					 
					 //Time Slot
					 	addtextFields_commonMethod(application, "Timeslot", "timeSlot_textField", timeSlot, xml);
				 }
				 else  if(frameType.equals("crc4")) {
					 
					 //Time Slot
					 	addtextFields_commonMethod(application, "Timeslot", "timeSlot_textField", timeSlot, xml);
				 }
				 else  if(frameType.equals("g704")) {
					 
					 //Time Slot
					 	addtextFields_commonMethod(application, "Timeslot", "timeSlot_textField", timeSlot, xml);
				 }
				 else  if(frameType.equals("unframed")) {
					 
					 System.out.println("No additonal fields");
				 }
				 
				//Clock Source
				 	addDropdownValues_commonMethod(application, "Clock Source" , "clockSource_Dropdown", clockSource, xml);
				 	
				//STM1 Number
					addtextFields_commonMethod(application, "STM1 Number", "stm1Number_textField" , STM1number, xml); 	
					
				//Bearer No
					addtextFields_commonMethod(application, "Bearer No", "bearerNumber_addtextField", bearerNumber, xml);
					
				//Interface
					compareText(application, "Interface", "Interface_textField", "e1-//::.0", xml);
					WebElement linkElement = getwebelement(xml.getlocator("//locators/" + application + "/Link_textField"));
					scrolltoview(linkElement);
				//Encapsulation
					addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
					
				//Slot
					addtextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
					
				//Port
					addtextFields_commonMethod(application, "Port", "port_textField" , port , xml);
					
				//Unit Id
					edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
					
				//Pic
					addtextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
					
				//IV Management
					addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
					
//					if(IVmanagement.equalsIgnoreCase("Yes")) {
//						
//						//VLAN Id
//						addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//					}
//					
					
				//compare Interface value
					String interfaceName_E1 = "e1-" + slot + "/" + pic + "/" + port + ":" + STM1number + ":." + unitId;
					compareText(application, "Interface", "Interface_textField", interfaceName_E1 , xml);
				
			}
			else if(bearerType.equals("E3")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//STM1 Number
				addtextFields_commonMethod(application, "STM1 Number", "stm1Number_textField" , STM1number, xml); 
				
				//Bearer No
				addtextFields_commonMethod(application, "Bearer No", "bearerNumber_addtextField", bearerNumber, xml);
				
				//Interface
				compareText(application, "Interface", "Interface_textField", "e3-//::.0", xml);	
				WebElement linkElement = getwebelement(xml.getlocator("//locators/" + application + "/Link_textField"));
				scrolltoview(linkElement);
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				addtextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				addtextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				addtextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
				
//				if(IVmanagement.equalsIgnoreCase("Yes")) {
//					
//					//VLAN Id
//					addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//				}
				
				
				//compare Interface Name
				String interfaceName_E3 = "e3-" + slot + "/" + pic + "/" + port + ":" + STM1number + ":." + unitId;
				compareText(application, "Interface", "Interface_textField", interfaceName_E3 , xml);
						
			}
			else if(bearerType.equals("Gigabit Ethernet")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
//				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//Interface
				compareText(application, "Interface", "Interface_textField", "ge-//.0", xml);	
				WebElement linkElement = getwebelement(xml.getlocator("//locators/" + application + "/Link_textField"));
				scrolltoview(linkElement);
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				addtextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				addtextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				addtextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
				
//				if(IVmanagement.equalsIgnoreCase("Yes")) {
//					
//					//VLAN Id
//					addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//				}
				
				//Compare Interface Name
				String interfaceName_GigaBit = "ge-" + slot + "/" + pic + "/" + port + "."+ unitId;
				compareText(application, "Interface", "Interface_textField", interfaceName_GigaBit , xml);
				
			}
			else if(bearerType.equals("STM-1")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//STM1 Number
				addtextFields_commonMethod(application, "STM1 Number", "stm1Number_textField" , STM1number, xml); 
				
				//Interface
				compareText(application, "Interface", "Interface_textField", "so-//:.0", xml);	
				WebElement linkElement = getwebelement(xml.getlocator("//locators/" + application + "/Link_textField"));
				scrolltoview(linkElement);
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				addtextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				addtextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				addtextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
				
//				if(IVmanagement.equalsIgnoreCase("Yes")) {
//					
//					//VLAN Id
//					addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//				}
				
				//Compare Interface Name
				String interfaceName_STM1Number = "so-" + slot + "/" + pic + "/" + port + ":" + STM1number + "." + unitId;
				compareText(application, "Interface", "Interface_textField", interfaceName_STM1Number , xml);	
				
			}
			else if(bearerType.equals("T3")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//STM1 Number
				addtextFields_commonMethod(application, "STM1 Number", "stm1Number_textField" , STM1number, xml); 
				
				//Bearer No
				addtextFields_commonMethod(application, "Bearer No", "bearerNumber_addtextField", bearerNumber, xml);
				
				//Interface
				compareText(application, "Interface", "Interface_textField", "t3-//::.0", xml);	
				WebElement linkElement = getwebelement(xml.getlocator("//locators/" + application + "/Link_textField"));
				scrolltoview(linkElement);
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				addtextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				addtextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				addtextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
				
//				if(IVmanagement.equalsIgnoreCase("Yes")) {
//					
//					//VLAN Id
//					addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//				}
				
				
				//Compare Interface Name
				String interfaceName_T3 = "t3-" + slot + "/" + pic + "/" + port + ":" + STM1number + "::." + unitId;
				compareText(application, "Interface", "Interface_textField", interfaceName_T3 , xml);
			}
			
			
		//Interface
			String interfaceName=getwebelement(xml.getlocator("//locators/" + application + "/Interface_textField")).getAttribute("value");
			if(interfaceName.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface' text field");
				System.out.println("No values displaying under 'Interface' text field");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Name is displaying as: "+ interfaceName);
				System.out.println("Interface Name is displaying as: "+ interfaceName);
			}
			
			scrollToTop();
			Thread.sleep(1000);
			boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configuraInterfaceCheckboxSelection")).isSelected();
			
			scrolltoend();
			Thread.sleep(1000);
		
		if(configureInterfaceSelection) {

			//perform Generate configuration
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
				Thread.sleep(2000);
//				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
			}
		}
		else {
			
			scrolltoend();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_okButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'OK' button");
//			click_commonMethod(application, "OK" , "" , xml);
		}
		return interfaceName;
	}
		
		

		public void verifysuccessmessage(String application, String expected) throws InterruptedException {
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(3000);
			try {	
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert")).isDisplayed();

				if(successMsg) {
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
					if(expected.contains(alrtmsg)) {
						ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
						System.out.println("Message is verified. It is displaying as: "+alrtmsg);
						
					}else if(expected.equals(alrtmsg)){
						ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
						System.out.println("Message is verified. It is displaying as: "+alrtmsg);
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
						System.out.println("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
					}
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
					System.out.println(" Success Message is not displaying");
				}
				Thread.sleep(2000);
				
			}catch(Exception e) {
				Log.info("failure in fetching success message");
				ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
				System.out.println(expected+ " message is not getting dislpayed");
				Thread.sleep(2000);
			}

		}
		

		

		public String successmessageForInterfaceOrMultilinkCreation(String application, String expected) throws InterruptedException {
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(2000);
			
			String creation = "No";
			
			try {	
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert")).isDisplayed();

				if(successMsg) {
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
					if(alrtmsg.contains(expected)) {
						creation = "Yes";
						
					}else if(expected.equalsIgnoreCase(alrtmsg)){
						creation = "Yes";
					}else {
						creation = "No";
					}
				}else {
					creation = "No";
				}
				Thread.sleep(2000);
				
			}catch(Exception e) {
				e.printStackTrace();
				creation = "No";
			}
			
			return creation;

		}

		
		
		public void EIPallocationSuccessMessage(String application, String expected) throws InterruptedException {
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(3000);
			try {	
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/succeMessageFor_EIpAllocation")).isDisplayed();

				if(successMsg) {
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/successTextMessage_EIPAllocation")).getText();
					if(alrtmsg.contains(expected)) {
						ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
						System.out.println("Message is verified. It is displaying as: "+alrtmsg);
						
					}else if(expected.equals(alrtmsg)){
						ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
						System.out.println("Message is verified. It is displaying as: "+alrtmsg);
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
						System.out.println("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
					}
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
					System.out.println(" Success Message is not displaying");
				}
				Thread.sleep(2000);
				
			}catch(Exception e) {
				Log.info("failure in fetching success message");
				ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
				System.out.println(expected+ " message is not getting dislpayed");
				Thread.sleep(2000);
			}

		}

		
		
		public void verifysuccessmessage_fetchEIPallocation(String application, String expected) throws InterruptedException {
			
			scrollToTop();
			Thread.sleep(3000);
			try {	
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMsg")).isDisplayed();

				if(successMsg) {
					
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
					
					if(expected.contains(alrtmsg)) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
						System.out.println("Message is verified. It is displaying as: "+alrtmsg);
						
					}else if(alrtmsg.contains(expected)) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
						System.out.println("Message is verified. It is displaying as: "+alrtmsg);
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
						System.out.println("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
					}
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
					System.out.println(" Success Message is not displaying");
				}
				
			}catch(Exception e) {
				Log.info("failure in fetching success message - 'Service created Successfully'  ");
				ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
				System.out.println(expected+ " message is not getting dislpayed");
			}
		}
		
		
		
		public String addInterface_Cisco(String application, String serviceSubType, String existingAddressRangeIPv4selection, String existingAddressIPv4DropdownValue, String newAddressRangeIpv4selection, String newinterfaceAddressrange,
				String subnetSizeValue_IPv4, String availableBlocksValue_IPv4, String link, String bearerType, String encapsulation, String slot, String port, String vlanid,
				String unitId, String pic, String STM1number, String bandwidth, String cardType, String frameType, String clockSource, String timeSlot, 
				String configureInterfaceOnDevice, String IVmanagement, String pppEncapsulation, String vpi, String vci, String dslDownstreamSpeed, String dslUpstreamSpeed,
				String SIPbay, String existingAddressRangeIPv6selection,
				String newAddressRangeIpv6selection, String subnetSizeValue_IPv6, String availableBlocksValue_IPv6,
				String existingAddressIPv6DropdownValue, String newinterfaceAddressrangeIPv6) throws InterruptedException, DocumentException, IOException { 
			
			boolean addressValue=false;
			
			waitForpageload();   waitforPagetobeenable();
			
			scrolltoend();	Thread.sleep(1000);
//			click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");
			Thread.sleep(2000);
			waitForpageload();  waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(1000);
			
			//warningMessage_commonMethod(application, "interface_warningMessage" , "Interface", xml);	//Interface warning Message
			//warningMessage_commonMethod(application, "BearerType_warningMessage", "Bearer Type" , xml);	//Bearer Type warning Mesages
			//warningMessage_commonMethod(application, "encapsulation_warningMessage", "Encapsulation", xml);	//Encapsulation  warning Message
			warningMessage_commonMethod(application, "slot_warningMessage", "Slot", xml);	//Slot warning Message
			warningMessage_commonMethod(application, "port_warningMessage", "Port", xml);	//Port warning Message
			warningMessage_commonMethod(application, "configuration_warningMessage", "Configuration", xml);	//Configuration warning Message
			
			
		//IPv4 Configuration	
			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
					
					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv4, xml);
					
					addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv4, xml);
					
					click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
					
					EIPallocationSuccessMessage(application, "successfully allocated");
					
					click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					
					Thread.sleep(1000);
					waitForpageload();  waitforPagetobeenable();
					click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
					
					String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
					
					if(interfaceAddressRange.isEmpty()) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
						System.out.println("No values displaying under 'Interface Address Range_IPv4' dropdown");
						  
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
						System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
						
						waitForpageload();  waitforPagetobeenable();
						click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
						scrollToTop();
						addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
						if(addressValue) {
							
							selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface" , existingAddressIPv4DropdownValue);
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
							System.out.println("'Address_IPv4' dropdown is not displaying");
						}
					
					}
					
				}
				else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
					
					  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
						  System.out.println("No values dipslaying under 'Address_IPv4' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
				
			WebElement IPv4Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/PE_addInterface_networklabelName"));
			scrolltoview(IPv4Netwrk); 
		//IPv6 Configuration
			if(serviceSubType.equalsIgnoreCase("IPVPN Plus")  ||   serviceSubType.equalsIgnoreCase("IPVPN Access"))	{
			
				if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv6, xml);
					
					addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
					
					EIPallocationSuccessMessage(application, "successfully allocated");
					
					click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					
					String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")));
					
					if(interfaceAddressRange.isEmpty()) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv6' dropdown");
						System.out.println("No values displaying under 'Interface Address Range_IPv6' dropdown");
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						System.out.println("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						
						click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
						
					try {	
						addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
						if(addressValue) {
							
							selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
							System.out.println("'Address_IPv6' dropdown is not displaying");
						}
					  }catch(Exception e) {
						  e.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						  System.out.println("'Address_IPv6' dropdown is not displaying");
					  }
					}
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
				WebElement IPv6Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/networkLabelname_IPv6"));
				scrolltoview(IPv6Netwrk);
				
		}
				
				
		//Link Text Field
			addtextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
			
		//Bearer Type Dropdown
			addDropdownValues_commonMethod(application, "Bearer Type" , "bearerType_Dropdown" , bearerType, xml);
			
			if(bearerType.equals("ATM")) {
				
				//Encapsulation
					addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//DSL DownStream Speed
					addDropdownValues_commonMethod(application, "DSL Downstream Speed" , "DSLdownstreamSpead_Dropdown" , dslDownstreamSpeed , xml);
					
				//DSL Upstream Speed
					addDropdownValues_commonMethod(application, "DSL Upstream Speed", "DSLupstreamSpeed_Dropdown" , dslUpstreamSpeed , xml);
					
				//PPP Encapsulation
					addDropdownValues_commonMethod(application, "PPP Encapsulation", "PPPencapsulation_Dropdown", pppEncapsulation , xml);
					
				//SIP Bay
					//addtextFields_commonMethod(application, "SIP Bay", "sipBat_textField", SIPbay, xml);
					
				//VPI 
					addtextFields_commonMethod(application, "VPI", "vpi_textField" , vpi , xml);
					
				//VCI
					addtextFields_commonMethod(application, "VCI", "vci_textField" , vci, xml);
					
				//Slot
					addtextFields_commonMethod(application, "Slot", "slot_textField" , slot, xml);
					
				//Port
					addtextFields_commonMethod(application, "Port", "port_textField", port, xml);

			}	
			else if(bearerType.equals("E1")) {
				
				//Bandwidth
					addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
					
				//Encapsulation
					addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
					
				//SIP Bay
					//addtextFields_commonMethod(application, "SIP Bay", "sipBat_textField", SIPbay, xml);	
					
				//Slot
					addtextFields_commonMethod(application, "Slot", "slot_textField" , slot, xml);
					
				//Port
					addtextFields_commonMethod(application, "Port", "port_textField", port, xml);			
					
					
			}
			else if(bearerType.equals("E3")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Encapsulation
					addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
					
				//Card Type
					addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);			
					
				//SIP Bay
					//addtextFields_commonMethod(application, "SIP Bay", "sipBat_textField", SIPbay, xml);	
					
				//Slot
					addtextFields_commonMethod(application, "Slot", "slot_textField" , slot, xml);
					
				//Port
					addtextFields_commonMethod(application, "Port", "port_textField", port, xml);			
			
			}
			else if(bearerType.equals("Ethernet")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Encapsulation
					addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
					
				//SIP Bay
					//addtextFields_commonMethod(application, "SIP Bay", "sipBat_textField", SIPbay, xml);	
					
				//Card Type
					addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);			
					
				//Slot
					addtextFields_commonMethod(application, "Slot", "slot_textField" , slot, xml);
					
				//Port
					addtextFields_commonMethod(application, "Port", "port_textField", port, xml);	
					
				//VLAN Id
					addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);				
			
			}
			else if(bearerType.equals("STM-1")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Encapsulation
					addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
					
				//SIP Bay
					//addtextFields_commonMethod(application, "SIP Bay", "sipBat_textField", SIPbay, xml);	
					
				//Card Type
					addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);			
					
				//Slot
					addtextFields_commonMethod(application, "Slot", "slot_textField" , slot, xml);
					
				//Port
					addtextFields_commonMethod(application, "Port", "port_textField", port, xml);			
			
			}
			else if(bearerType.equals("T3")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Encapsulation
					addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
					
				//SIP Bay
					//addtextFields_commonMethod(application, "SIP Bay", "sipBat_textField", SIPbay, xml);	
					
				//Card Type
					addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);			
					
				//Slot
					addtextFields_commonMethod(application, "Slot", "slot_textField" , slot, xml);
					
				//Port
					addtextFields_commonMethod(application, "Port", "port_textField", port, xml);			
			
			}
			
		
		//IV Management
			addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
			
		//Interface
			String interfaceName=getwebelement(xml.getlocator("//locators/" + application + "/Interface_textField")).getAttribute("value");
			if(interfaceName.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface' text field");
				System.out.println("No values displaying under 'Interface' text field");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Name is displaying as: "+ interfaceName);
				System.out.println("Interface Name is displaying as: "+ interfaceName);
			}
		
//			scrollToTop();
//			Thread.sleep(1000);
//		boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configuraInterfaceCheckboxSelection")).isSelected();
//			
//		if(configureInterfaceSelection) {
			scrolltoend();
			Thread.sleep(1000);
			//perform Generate configurations
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
			
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
				}

			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicke don 'Execute and Save' button");
//			click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
			
//		}else {
//			scrolltoend();
//			Thread.sleep(1000);
//			click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
//		}
			
			return interfaceName;
				
		}
		
		
		
		public void addDropdownValues_PEdevice(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml) throws InterruptedException, DocumentException {
			  boolean availability=false;
			  List<String> ls = new ArrayList<String>();
			  
			try {  
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			  if(availability) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  System.out.println(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					  Thread.sleep(3000);
					  
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(2000);
						
					  Clickon(getwebelement("(//span[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(3000);
					  
					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
				System.out.println(" NO value selected under "+ labelname + " dropdown");
			}
		}
		
		
		public void clickOnAddMultilinkLink(String application) throws InterruptedException, DocumentException {
			
			WebElement routerToolPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/routerToolPanelheader"));
			scrolltoview(routerToolPanelHeader);
			Thread.sleep(1000);
			
			click_commonMethod(application, "Action", "InterfacePanel_actionDropdown" , xml);
			
			click_commonMethod(application, "Add Multilink", "addMultilinkLink" , xml);
			
		}
		
		
		public String addMultilink(String application, String serviceSubType, String configureInterfaceOnDevice, String link, String encapsulation, String slot, String port,
				String pic, String IVmanagement, String unitID, String existingAddressRangeIPv4selection, String newAddressRangeIpv4selection,
				String subnetSizeValue_IPv4, String availableBlocksValue_IPv4, String existingAddressIPv4DropdownValue,
				String newinterfaceAddressrange, String existingAddressRangeIPv6selection,
				String newAddressRangeIpv6selection, String subnetSizeValue_IPv6, String availableBlocksValue_IPv6,
				String existingAddressIPv6DropdownValue, String newinterfaceAddressrangeIPv6) throws InterruptedException, DocumentException, IOException { 
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(1000);
			
			boolean addressValue = false;  
			
		//Configure Interface on Device	
			addCheckbox_commonMethod(application, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", configureInterfaceOnDevice, "Yes" , xml);
		
			//IPv4 Configuration	
			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
				
			//EIP Allocation
				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
				
				addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv4, xml);
				
				addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv4, xml);
				
				click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
				
				EIPallocationSuccessMessage(application, "successfully allocated");
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				Thread.sleep(2000);
				
				waitForpageload();  waitforPagetobeenable();
				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
				
				String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addMultilink")));
				
				if(interfaceAddressRange.isEmpty()) {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
					System.out.println("No values displaying under 'Interface Address Range_IPv4' dropdown");
					  
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					
					click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
					
					addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
					if(addressValue) {
						
						selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface", existingAddressIPv4DropdownValue);
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
						System.out.println("'Address_IPv4' dropdown is not displaying");
					}
				}
			}
			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
				
				  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
			
			WebElement IPv4Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/PE_addInterface_networklabelName"));
			scrolltoview(IPv4Netwrk); 

		//IPv6 Configuration
		if(serviceSubType.equalsIgnoreCase("IPVPN Access") || serviceSubType.equalsIgnoreCase("IPVPN Plus")) {	
			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv6, xml);
					
					addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
					
					EIPallocationSuccessMessage(application, "successfully allocated");
					
					click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					
					String interfaceAddressRange= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addMultilinkIPv6")));
					
					if(interfaceAddressRange.isEmpty()) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv6' dropdown");
						System.out.println("No values displaying under 'Interface Address Range_IPv6' dropdown");
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						System.out.println("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						
						click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
						
					try {	
						addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
						if(addressValue) {
							
							selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
							System.out.println("'Address_IPv6' dropdown is not displaying");
						}
					  }catch(Exception e) {
						  e.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						  System.out.println("'Address_IPv6' dropdown is not displaying");
					  }
					}
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
			WebElement IPv6Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/networkLabelname_IPv6"));
			scrolltoview(IPv6Netwrk); 
		}	

			
			
		//Link Text Field
			addtextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
			
		//Unit ID
			addtextFields_commonMethod(application, "Unit ID" , "unitId_textField", unitID, xml);
			
		//Encapsulation
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
			
		//Slot
			addtextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
			
		//Port
			edittextFields_commonMethod(application, "Port", "port_textField" , port , xml);
			
		//Pic
			addtextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
			
		//IV Management
			addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying column Names under 'multilink Bearer' table");
			
			isDisplayed(application, "addMultilink_multilinkTableHeader", "MultiLinkBearerTable");	//Multilink Bearer Table Header Name
			isDisplayed(application, "addMultilink_checkToAddColumnName" , "'Check to Add to multilink' column name");	  //Check To Add To Multilink column ame
			isDisplayed(application, "addMultilinkTable_interfaceColumnName" , "'Interface' column Name");	//'Interface' column Name
			isDisplayed(application, "addMultilinkTable_linkOrcircuitColumnName", "'Link/Circuit Id' column Name");	//Link / Circuit Id  column Name
			isDisplayed(application, "addMultilinkTable_bearerTypeColumnName", "'Bearer Type' column Name");		//Bearer Type Column Name
			isDisplayed(application, "addMultilinkTable_vlanIDcolumnName", "'VLAN Id' column Name");  	//VLAN Id column name
			
		//Fetch value from text fields	
			String slotActualvalue = getwebelement(xml.getlocator("//locators/" + application + "/slot_textField")).getAttribute("value");
			String portActualValue = getwebelement(xml.getlocator("//locators/" + application + "/port_textField")).getAttribute("value");
			String picActualValue = getwebelement(xml.getlocator("//locators/" + application + "/pic_textField")).getAttribute("value");
			String unitIDActualValue = getwebelement(xml.getlocator("//locators/" + application + "/unitId_textField")).getAttribute("value");
			
		//Compare Interface Name
			String interfaceName_multilink = "lsq-" + slotActualvalue + "/" + picActualValue + "/" + portActualValue + "." + unitIDActualValue;
			compareText(application, "Interface", "Interface_textField", interfaceName_multilink , xml);
			
			scrollToTop();
			Thread.sleep(1000);
			boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configuraInterfaceCheckboxSelection")).isSelected();
			
			scrolltoend();
			Thread.sleep(1000);
		
			//Generate Configuration
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Generate Configuration' under 'Add Multilink' Page");
			
		if(configureInterfaceSelection) {
			
			//perform Generate configuration
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
			
				Thread.sleep(2000);
				scrolltoend();
//				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");


			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
			}
			
		}else {
			
			scrolltoend();
//			click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addMultilink_okButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'OK' button");

		}
		
		return interfaceName_multilink;
		
	}
		

		public String editMultilink(String application, String serviceSubType, String configureInterfaceOnDevice, String link, String encapsulation, String slot, String port,
				String pic, String IVmanagement, String unitID, String existingAddressRangeIPv4selection, String newAddressRangeIpv4selection,
				String subnetSizeValue_IPv4, String availableBlocksValue_IPv4, String existingAddressIPv4DropdownValue,
				String newinterfaceAddressrange, String existingAddressRangeIPv6selection,
				String newAddressRangeIpv6selection, String subnetSizeValue_IPv6, String availableBlocksValue_IPv6,
				String existingAddressIPv6DropdownValue, String newinterfaceAddressrangeIPv6, String editMultilink_v4ConfigurationSelection,
				String editMultilink_v6ConfigurationSelection) throws InterruptedException, DocumentException, IOException {
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(1000);
			
			boolean addressValue = false;  
			
		//Configure Interface on Device	
//			addCheckbox_commonMethod(application, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", configureInterfaceOnDevice, "Yes" , xml);
		
			
		if(editMultilink_v4ConfigurationSelection.equalsIgnoreCase("Yes")) {
			
			//IPv4 Configuration	
			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
				
			//EIP Allocation
				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
				
				addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv4, xml);
				
				addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv4, xml);
				
				click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
				
				EIPallocationSuccessMessage(application, "successfully allocated");
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				Thread.sleep(2000);
				
				waitForpageload();  waitforPagetobeenable();
				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
				
				String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addMultilink")));
				
				if(interfaceAddressRange.isEmpty()) {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
					System.out.println("No values displaying under 'Interface Address Range_IPv4' dropdown");
					  
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					
					click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
					
					addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
					if(addressValue) {
						
						selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface", existingAddressIPv4DropdownValue);
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
						System.out.println("'Address_IPv4' dropdown is not displaying");
					}
				}
			}
			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
				
				  edittextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'V4 Configuration' is not edited");
			Log.info("'V4 Configuration' is not edited");
		}
						
		WebElement IPv4Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/PE_addInterface_networklabelName"));
		scrolltoview(IPv4Netwrk); 

		//IPv6 Configuration
	if(serviceSubType.equalsIgnoreCase("IPVPN Plus") || serviceSubType.equalsIgnoreCase("IPVPN Access")) {
		
		if(editMultilink_v6ConfigurationSelection.equalsIgnoreCase("Yes")) {
			
			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv6, xml);
					
					addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
					
					EIPallocationSuccessMessage(application, "successfully allocated");
					
					click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					
					String interfaceAddressRange= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addMultilinkIPv6")));
					
					if(interfaceAddressRange.isEmpty()) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv6' dropdown");
						System.out.println("No values displaying under 'Interface Address Range_IPv6' dropdown");
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						System.out.println("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						
						click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
						
					try {	
						addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
						if(addressValue) {
							
							selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
							System.out.println("'Address_IPv6' dropdown is not displaying");
						}
					  }catch(Exception e) {
						  e.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						  System.out.println("'Address_IPv6' dropdown is not displaying");
					  }
					}
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  edittextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'V6 Configuration' is not edited");
				Log.info("'V6 Configuration' is not edited");
			}
		WebElement IPv6Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/networkLabelname_IPv6"));
		scrolltoview(IPv6Netwrk); 
		}
			
			
		//Link Text Field
			edittextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
			
		//Unit ID
			edittextFields_commonMethod(application, "Unit ID" , "unitId_textField", unitID, xml);
			
		//Encapsulation
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
			
		//Slot
			edittextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
			
		//Port
			edittextFields_commonMethod(application, "Port", "port_textField" , port , xml);
			
		//Pic
			edittextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
			
		//IV Management
			editcheckbox_commonMethod(application, IVmanagement, "IVmanagement_checkbox", "IV management" , xml);
			
			
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying column Names under 'multilink Bearer' table");
			isDisplayed(application, "addMultilink_multilinkTableHeader", "MultiLinkBearerTable");	//Multilink Bearer Table Header Name
			isDisplayed(application, "addMultilink_checkToAddColumnName" , "'Check to Add to multilink' column name");	  //Check To Add To Multilink column ame
			isDisplayed(application, "addMultilinkTable_interfaceColumnName" , "'Interface' column Name");	//'Interface' column Name
			isDisplayed(application, "addMultilinkTable_linkOrcircuitColumnName", "'Link/Circuit Id' column Name");	//Link / Circuit Id  column Name
			isDisplayed(application, "addMultilinkTable_bearerTypeColumnName", "'Bearer Type' column Name");		//Bearer Type Column Name
			isDisplayed(application, "addMultilinkTable_vlanIDcolumnName", "'VLAN Id' column Name");  	//VLAN Id column name
			
			
		//fetch value fom the text fields	
			String slotActualvalue = getwebelement(xml.getlocator("//locators/" + application + "/slot_textField")).getAttribute("value");
			String portActualValue = getwebelement(xml.getlocator("//locators/" + application + "/port_textField")).getAttribute("value");
			String picActualValue = getwebelement(xml.getlocator("//locators/" + application + "/pic_textField")).getAttribute("value");
			String unitIDActualValue = getwebelement(xml.getlocator("//locators/" + application + "/unitId_textField")).getAttribute("value");
			
			
			scrollToTop();
			Thread.sleep(1000);
		//Compare Interface Name
			String interfaceName_multilink = "lsq-" + slotActualvalue + "/" + picActualValue + "/" + portActualValue + "." + unitIDActualValue;
			compareText(application, "Interface", "Interface_textField", interfaceName_multilink , xml);
			
			scrollToTop();
			Thread.sleep(1000);
			boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configuraInterfaceCheckboxSelection")).isSelected();
			
			scrolltoend();
			Thread.sleep(1000);
		
			//Generate Configuration
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Generate Configuration' under 'Add Multilink' Page");
			
		if(configureInterfaceSelection) {
			
			//perform Generate configuration
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
			
				Thread.sleep(2000);
				scrolltoend();
//				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
			}
			
		}else {
			
			scrolltoend();
//			click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_okButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'OK' button");

		}
		
		return interfaceName_multilink;
		
	}

		
		
		public void clickOnBackButton(String application) throws InterruptedException, DocumentException {
			
			scrolltoend();
			Thread.sleep(1000);
			
			click_commonMethod(application, "Back", "PEdevice_backButton" , xml);
		}
		
		
		public String fetchDeviceName(String application) throws IOException, InterruptedException, DocumentException {
			//fetchDeviceName_PEdevice
			
			String peDevice = null;
			String deviceName = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/configuraInterfaceCheckboxSelection")));
			
			if(deviceName.contains("...")) {
				peDevice = deviceName.substring(0, 10);
			}else {
				peDevice=deviceName;
			}
			
			return peDevice;
		}
		
		
		
		
		
		public void SelectInterfacetoremovefromservice(String Application, String interfacename)
				throws IOException, InterruptedException, DocumentException {

			waitForpageload();
			
			waitforPagetobeenable();
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Select Interface'_Remove Interace from Service");
			
				addtextFields_commonMethod(Application, "search", "interfacesInServices_searchTextBox", interfacename, xml);
				
				Thread.sleep(5000);
				selectRowforInterfaceInService(Application, interfacename);

		}
		
		
		public void selectRowforInterfaceInService(String Application, String interfacenumber)
				throws IOException, InterruptedException, DocumentException {

			int TotalPages;

			String TextKeyword = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_totalpage")));

			TotalPages = Integer.parseInt(TextKeyword);

			Log.info("Total number of pages in table is: " + TotalPages);

			ab:

			if (TotalPages != 0) {
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_currentpage")));
					int Current_page = Integer.parseInt(CurrentPage);

					assertEquals(k, Current_page);

					Log.info("Currently we are in page number: " + Current_page);

					List<WebElement> results = getwebelements(xml.getlocator("//locators/" + Application + "/selectinterfaceUnderInterfacesInService").replace("value", interfacenumber));
					
					int numofrows = results.size();
					Log.info("no of results: " + numofrows);
					boolean resultflag;

					if (numofrows == 0) {

						PageNavigation_NextPageForInterfaceInService(Application);

					}
					else {
						for (int i = 0; i < numofrows; i++) {
							try {
								
								waitForpageload();
								waitforPagetobeenable();
								
								resultflag = results.get(i).isDisplayed();
								Log.info("status of result: " + resultflag);
								if (resultflag) {
									Log.info(results.get(i).getText());
									results.get(i).click();
									ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Interface in Service' table");
									click_commonMethod(Application, "Action", "InterfaceInselect_Actiondropdown", xml);
									Thread.sleep(1000);
									
									click_commonMethod(Application, "Remove", "InterfaceInselect_removebuton", xml);
								}
							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = getwebelements(xml.getlocator("//locators/" + Application + "/selectinterfaceUnderInterfacesInService").replace("value", interfacenumber));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								ExtentTestManager.getTest().log(LogStatus.FAIL, "failure while selecting interface to remove from service");
							}
						}
						break ab;
					}
				}
			} else {
				Log.info( interfacenumber + " is not dipslayin gunder 'Interfaces in Service' table ");
				ExtentTestManager.getTest().log(LogStatus.FAIL, interfacenumber + " is not dipslaying gunder 'Interfaces in Service' table ");
			}
		}

		
		public void PageNavigation_NextPageForInterfaceInService(String Application)
				throws InterruptedException, DocumentException {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_nextpage")));
			Thread.sleep(3000);

		}
		
		

		public void SelectInterfacetoaddwithservcie(String Application, String interfacenumber)
				throws InterruptedException, DocumentException, IOException {

			scrolltoend();
			Thread.sleep(4000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Select interface'_Add Interface to Service'");
			
			waitForpageload();
			waitforPagetobeenable();
			
			addtextFields_commonMethod(Application, "Search", "interfacesToSelect_searchtextBOx", interfacenumber, xml);
			
			click_commonMethod(Application, "Interface column Header", "InterfaceToSelect_interfaceColumnHeader", xml);
			
			selectrowforInterfaceToselecttable(Application, interfacenumber);

		}
		
		
		
		public void selectrowforInterfaceToselecttable(String Application, String interfacenumber)
				throws IOException, InterruptedException, DocumentException {

			int TotalPages;

			String TextKeyword = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_totalpage")));

			TotalPages = Integer.parseInt(TextKeyword);

			ab:

			if (TotalPages != 0) {
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_currentpage")));
					int Current_page = Integer.parseInt(CurrentPage);

					assertEquals(k, Current_page);

					System.out.println("Currently we are in page number: " + Current_page);

					List<WebElement> results = getwebelements("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[text()='" + interfacenumber + "']");
					System.out.println(results);	
					int numofrows = results.size();
					System.out.println("no of results: " + numofrows);
					boolean resultflag;

					if (numofrows == 0) {
						PageNavigation_NextPageForInterfaceToselect(Application);
					}

					else {
						for (int i = 0; i < numofrows; i++) {
							try {
								resultflag = results.get(i).isDisplayed();
								System.out.println("status of result: " + resultflag);
								if (resultflag) {
									System.out.println(results.get(i).getText());
									results.get(i).click();
									ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Interface to select' table");
									Thread.sleep(8000);
									Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_Actiondropdown")));
									Thread.sleep(5000);
									Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_addbuton")));
									Thread.sleep(3000);
									ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is added to service");
								}
							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = getwebelements("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[text()='" + interfacenumber + "']");
								numofrows = results.size();
								 results.get(i).click();
								Log.info("selected row is : " + i);
								ExtentTestManager.getTest().log(LogStatus.FAIL, " Failure on selecting an Interface to ad with service ");
							}
						}
						break ab;
					}
				}
			} else {
				System.out.println("No values found inside the table");
				Log.info("No values available inside the Interfacetoselect table");
				ExtentTestManager.getTest().log(LogStatus.FAIL, interfacenumber + " value is not dipslaying under 'Interfaces to select' table");
			}

		}
		
		

		public void PageNavigation_NextPageForInterfaceToselect(String Application)
				throws InterruptedException, DocumentException {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_nextpage")));
			Thread.sleep(3000);

		}
		
		
		public void selectEnableValueUnderAddressDropdown(String application, String labelname, String xpath, String expectedValueToAdd ) {
			
				  List<String> ls = new ArrayList<String>();
				  boolean availability = false;
				  
				try {  

					ScrolltoElement(application, xpath , xml);
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)"); 
					
				  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				  if(availability) {
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
					  System.out.println(labelname + " dropdown is displaying");
					  
					  if(expectedValueToAdd.equalsIgnoreCase("null")) {
						  
						  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
						  System.out.println(" No values selected under "+ labelname + " dropdown");
					  }else {
						  
						  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
						  Thread.sleep(3000);
						  
						  //verify list of values inside dropdown
						  List<WebElement> listofvalues = driver
									.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
						  
						  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
						  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
						  
							for (WebElement valuetypes : listofvalues) {
										Log.info("service sub types : " + valuetypes.getText());
										ls.add(valuetypes.getText());
							}
							
							    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
					            System.out.println("list of values inside "+labelname+" dropdown is: "+ls);
					            
					         Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectFirstValueUnderAddressDropdown")));
					         Thread.sleep(1000);
							
						  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
						  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
						  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
					  }
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
					  System.out.println(labelname + " is not displaying");
				  }
				}catch(NoSuchElementException e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
					  System.out.println(labelname + " is not displaying");
				}catch(Exception ee) {
					ee.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
					System.out.println(" NO value selected under "+ labelname + " dropdown");
				}
			}
		
		
		/**
		 * 	
		 * @param application
		 * @throws DocumentException 
		 * @throws InterruptedException 
		 */
			public void clickOnBreadCrump(String application, String breadCrumpLink) throws InterruptedException, DocumentException {
				
				waitForpageload();
				waitforPagetobeenable();
				
				scrollToTop();
				Thread.sleep(2000);
				WebElement breadcrumb=null;
		
				try {
				breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
				if(breadcrumb.isDisplayed()) {
					click_commonMethod_PassingWebelementDirectly(application, "Breadcrump", "breadcrump", xml);
					Thread.sleep(3000);
				}else {
					System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
			}
		}
			
			

public void isDisplayed(String application, String xpath, String labelname) {
	boolean availability = false;

	try {
		Thread.sleep(1000);
		availability= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		System.out.println(availability);
		if (availability) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: '"+labelname+"' is displayed as expected");
			System.out.println("Step: '"+labelname+"' is displayed as expected");
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' is not displayed");
			System.out.println("Step: '"+labelname+"' is not displayed");
			}

	} catch (Exception e) {
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: '"+labelname+"' is not available to display");
		e.printStackTrace();
	}
}


		public void PEinterface_clickOEditlink(String application, String interfaceName) throws InterruptedException, DocumentException {
			
			waitForpageload();  waitforPagetobeenable();
			
			scrolltoend();
			Thread.sleep(1000);
			
			click_commonMethod(application, "Show Interface", "PEdevice_showInterfaceLink" , xml);
			Thread.sleep(2000);
			
			WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectInterfaceUnderPEdevice").replace("value", interfaceName));
			Clickon(interfaceValue);
			ExtentTestManager.getTest().log(LogStatus.PASS, interfaceName + " interface is selected under 'Provider Equipment' panel");
			Log.info( interfaceName + " interface is selected under 'Provider Equipment' panel");
			
			click_commonMethod(application, "Action", "actionDropdown_UnderProviderEquipmentPanel" , xml);    //Click on 'Action' button
			
			click_commonMethod(application, "Edit", "editLink_underProviderEquipment", xml);	//click on edit Link
			
		}
		
		
		/**
		 * Router Tool Panel
		 * @throws InterruptedException 
		 * @throws DocumentException 
		 */
		public void routerPanel_Cisco(String application, String commandIPv4, String commandIPv6, String ipAddress, 
				String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException {
			
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
		
		
		/**
		 * Router Tool Panel
		 * @throws InterruptedException 
		 * @throws DocumentException 
		 */
		public void routerPanel_juniper(String application, String commandIPv4, String commandIPv6, String ipAddress, 
				String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException {
			
			scrolltoend();
			Thread.sleep(1000);
			
		//Command IPV4	
			addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);
			
			hostnametextField_IPV4(application, commandIPv4, ipAddress);
			
			vrfNametextField_IPV4(application, commandIPv4, vrfname_ipv4);
			
			executeCommandAndFetchTheValue(application, "executebutton_Ipv4");
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
		
		
		public boolean fetchdeviceInterface(String application) throws InterruptedException, DocumentException {
			
			boolean clickLink = false;
			
			click_commonMethod(application, "Action", "viewDevicePage_ActionDropdown", xml);
			
			click_commonMethod(application, "Fetch Device Interfaces", "viewDevicePage_fetchDeviceInterfaceLink" , xml);
			
			
			//verify success Message
			String expectedValue="Fetch interfaces started successfully. Please check the sync status of this device. ";
			boolean successMessage=false;
		try {	
			successMessage=getwebelement(xml.getlocator("//locators/" + application + "/fetchDeviceInterace_SuccessMessage")).isDisplayed();
			String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/fetchdeviceInterface_successtextMessage")).getText();
			if(successMessage) {
				
				if(actualMessage.isEmpty()) {
					System.out.println("No messages displays");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Success message is not displaying");
				}
				else if(actualMessage.contains(expectedValue)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaying as expected");
					System.out.println(" After clicking on 'Fetch Device Interface' link, success Message is displaying as expected");
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
					System.out.println(" Success Message displays as: "+actualMessage);
					
				//click on the 'click here' link
					click_commonMethod(application, "here", "fetchDeviceInterface_hereLink", xml);
					clickLink=true;
					
				}
				else if(actualMessage.equalsIgnoreCase(expectedValue)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaying as expected");
					System.out.println(" After clicking on 'Fetch Device Interface' link, success Message is displaying as expected");
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
					System.out.println(" Success Message displays as: "+actualMessage);
					
				//click on the 'click here' link
					click_commonMethod(application, "here", "fetchDeviceInterface_hereLink", xml);
					clickLink=true;
					
				}
				else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Fetch Device Interface' link, message displays as "+actualMessage);
					System.out.println("After clicking on 'Fetch Device Interface' link, message displays as "+actualMessage);
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Fetch Device Interface' link, success message is not displaying");
			System.out.println("After clicking on 'Fetch Device Interface' link, success message is not displaying");
			
		}
			return clickLink;
	 }
		
		
		/**
		 *  Method is for clicking on "Select interface" link for a PE device	
		 * @param application
		 * @param existingdevicename
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void PEdevice_clickOnselectInterface(String application, String existingdevicename) throws InterruptedException, DocumentException {
			
			WebElement providerEquipment_panelHeader= getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_panelHeader"));
			scrolltoview(providerEquipment_panelHeader);
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
            {
                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
//                  Log.info(addeddevicesList);
                  int AddedDevicesCount= addeddevicesList.size();
                  for(int i=0;i<AddedDevicesCount;i++) {
                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                        if(AddedDeviceNameText.contains(existingdevicename))
                        {
                              WebElement PEDevice_selectInterfaceLink=getwebelement(xml.getlocator("//locators/" + application + "/PE_selectInterface_InViewPage").replace("value", AddedDevice_SNo)); 
                              Clickon(PEDevice_selectInterfaceLink);
                              Thread.sleep(2000);
                              break;
                        }
                        else
                        {
//                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                        	Log.info("Invalid device name");
                        }
                        
                  }
            }
            else
            {
                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
            }		
		}


		/**
		 *  Method is for clicking on "AutodiscoverVPN" link for a PE device	
		 * @param application
		 * @param existingdevicename
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void PEdevice_clickOnAutodiscoverVPN(String application, String existingdevicename) throws InterruptedException, DocumentException {
			
			WebElement providerEquipment_panelHeader = getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_panelHeader"));
			scrolltoview(providerEquipment_panelHeader);
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		    {
		          List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
		          int AddedDevicesCount= addeddevicesList.size();
		          for(int i=0;i<AddedDevicesCount;i++) {
		                String AddedDeviceNameText= addeddevicesList.get(i).getText();
		                String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
		                if(AddedDeviceNameText.contains(existingdevicename))
		                {
		                      WebElement PEdevice_autoDiscoverVPN=getwebelement(xml.getlocator("//locators/" + application + "/clickOnAutoDiscoverVPNunderProviderEquipmentPanel").replace("value", AddedDevice_SNo)); 
		                      Clickon(PEdevice_autoDiscoverVPN);
		                      Thread.sleep(2000);
		                      break;
		                }
		                else
		                {
//		                      ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
		                      Log.info("Invalid device name");
		                }
		                
		          }
		    }
		    else
		    {
		          ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		    }		
		}
			


		public String editInterface_Juniper(String application, String serviceSubType, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, String newAddressRangeIpv4selection, String newinterfaceAddressrange,
				String subnetSizeValue_IPv4, String availableBlocksValue_IPv4, String link, String bearerTypeValue , String encapsulation, String slot, String port, String vlanid,
				String unitId, String pic, String STM1number, String bandwidth, String cardType, String frameType, String clockSource, String timeSlot, 
				String configureInterfaceOnDevice, String IVmanagement,String existingAddressRangeIPv6selection, String  existingAddressIPv6DropdownValue,
				String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6,String subnetSizeValue_IPv6, String availableBlocksValue_IPv6, String bearerNumber,
				String editInterface_v4ConfigurationSelection, String editInterface_v6ConfigurationSelection) throws InterruptedException, DocumentException, 
		IOException { 
			
			boolean addressValue=false;
			
			scrollToTop();
			
		//Configure Interface on Device	
			addCheckbox_commonMethod(application, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", configureInterfaceOnDevice, "Yes" , xml);
			
	
	//V4 Configuration		
	if(editInterface_v4ConfigurationSelection.equalsIgnoreCase("Yes")) {
		
		//IPv4 Configuration	
		if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
			
		//EIP Allocation
			click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
			
			addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv4, xml);
			
			addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv4, xml);
			
			click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
			
			EIPallocationSuccessMessage(application, "successfully allocated");
			
			click_commonMethod(application, "x", "EIPallocation_xButton", xml);
			
			Thread.sleep(2000);
			
			waitForpageload();  waitforPagetobeenable();
			click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
			
			String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
			
			if(interfaceAddressRange.isEmpty()) {
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
				System.out.println("No values displaying under 'Interface Address Range_IPv4' dropdown");
				  
			}else {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
				System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
				
				click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				
				addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
				if(addressValue) {
					
					selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface" , existingAddressIPv4DropdownValue);
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
					System.out.println("'Address_IPv4' dropdown is not displaying");
				}
			}
		}
		else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
			
			  edittextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
			
			  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
			  
			  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
			  if(interfaceValueIntextField.isEmpty()) {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
				  System.out.println("No values dipslaying under 'Address_IPv4' text field");
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
			  }
		}
	}else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'V4 Configuration' is not edited");
		Log.info("'V4 Configuration' is not edited");
	}
		
	WebElement IPv4Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/PE_addInterface_networklabelName"));
	scrolltoview(IPv4Netwrk);
	//V6 Configuration
	if(serviceSubType.equalsIgnoreCase("IPVPN Plus") || serviceSubType.equalsIgnoreCase("IPVPN Access")) {
		if(editInterface_v6ConfigurationSelection.equalsIgnoreCase("Yes")) {
			
		//IPv6 Configuration
			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv6, xml);
					
					addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
					
					EIPallocationSuccessMessage(application, "successfully allocated");
					
					click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					
					String interfaceAddressRange=getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")).getText();
					
					if(interfaceAddressRange.isEmpty()) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv6' dropdown");
						System.out.println("No values displaying under 'Interface Address Range_IPv6' dropdown");
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						System.out.println("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						
						click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
						
					try {	
						addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
						if(addressValue) {
							
							selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
							System.out.println("'Address_IPv6' dropdown is not displaying");
						}
					  }catch(Exception e) {
						  e.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						  System.out.println("'Address_IPv6' dropdown is not displaying");
					  }
					
					}
					
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  edittextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'V6 Configuration' is not edited");
				Log.info("'V6 Configuration' is not edited");
			}
		WebElement IPv6Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/networkLabelname_IPv6"));
		scrolltoview(IPv6Netwrk); 
		}
				
			
		//Link Text Field
			edittextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
			
		//Bearer Type Dropdown
			addDropdownValues_commonMethod(application, "Bearer Type" , "bearerType_Dropdown" , bearerTypeValue, xml);
			
			String bearerType = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/fetchBearerTypedropdownvalue")));
			
			if(bearerType.equals("10Gigabit Ethernet")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//Interface
		//		WebElement interfaceDefaultValue=getwebelement(xml.getlocator("//locators/" + application + "/Interface_textField"));
		//		Gettext(interfaceDefaultValue);
				
				compareText(application, "Interface", "Interface_textField", "xe-//.0", xml);		//compare Interface default value
				
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				edittextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				edittextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				edittextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				editcheckbox_commonMethod(application, IVmanagement , "IVmanagement_checkbox", "IV management" , xml);
//				
//				String InterfaceName_10Gig = "xe-" + slot + "/" + pic + "/" + port + "." + unitId; 
//				compareText(application, "Interface", "Interface_textField", InterfaceName_10Gig , xml);
				
			}
			else if(bearerType.equals("E1")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//Framing Type
				addDropdownValues_commonMethod(application, "Framing Type", "framingType_Dropdown" , frameType, xml);
				
				 if(frameType.equals("no-crc4")) {
					 
					 //Time Slot
					 edittextFields_commonMethod(application, "Timeslot", "timeSlot_textField", timeSlot, xml);
				 }
				 else  if(frameType.equals("crc4")) {
					 
					 //Time Slot
					 edittextFields_commonMethod(application, "Timeslot", "timeSlot_textField", timeSlot, xml);
				 }
				 else  if(frameType.equals("g704")) {
					 
					 //Time Slot
					 edittextFields_commonMethod(application, "Timeslot", "timeSlot_textField", timeSlot, xml);
				 }
				 else  if(frameType.equals("unframed")) {
					 
					 System.out.println("No additonal fields");
				 }
				 
				//Clock Source
				 	addDropdownValues_commonMethod(application, "Clock Source" , "clockSource_Dropdown", clockSource, xml);
				 	
				//STM1 Number
				 	edittextFields_commonMethod(application, "STM1 Number", "stm1Number_textField" , STM1number, xml); 	
					
				//Bearer No
				 	edittextFields_commonMethod(application, "Bearer No", "bearerNumber_addtextField", bearerNumber, xml);
					
				//Interface
					compareText(application, "Interface", "Interface_textField", "e1-//::.0", xml);
					
				//Encapsulation
					addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
					
				//Slot
					edittextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
					
				//Port
					edittextFields_commonMethod(application, "Port", "port_textField" , port , xml);
					
				//Unit Id
					edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
					
				//Pic
					edittextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
					
				//IV Management
					editcheckbox_commonMethod(application, IVmanagement , "IVmanagement_checkbox", "IV management" , xml);
					
				//compare Interface value
//					String interfaceName_E1 = "e1-" + slot + "/" + pic + "/" + port + ":" + STM1number + ":." + unitId;
//					compareText(application, "Interface", "Interface_textField", interfaceName_E1 , xml);
				
			}
			else if(bearerType.equals("E3")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//STM1 Number
				edittextFields_commonMethod(application, "STM1 Number", "stm1Number_textField" , STM1number, xml); 
				
				//Bearer No
				edittextFields_commonMethod(application, "Bearer No", "bearerNumber_addtextField", bearerNumber, xml);
				
				//Interface
				compareText(application, "Interface", "Interface_textField", "e3-//::.0", xml);	
				
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				edittextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				edittextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				edittextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				editcheckbox_commonMethod(application, IVmanagement , "IVmanagement_checkbox", "IV management" , xml);
				
				//compare Interface Name
//				String interfaceName_E3 = "e3-" + slot + "/" + pic + "/" + port + ":" + STM1number + ":." + unitId;
//				compareText(application, "Interface", "Interface_textField", interfaceName_E3 , xml);
						
			}
			else if(bearerType.equals("Gigabit Ethernet")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//Interface
				compareText(application, "Interface", "Interface_textField", "ge-//.0", xml);	
				
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				edittextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				edittextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				edittextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				editcheckbox_commonMethod(application, IVmanagement , "IVmanagement_checkbox", "IV management" , xml);
				
				//Compare Interface Name
//				String interfaceName_GigaBit = "ge-" + slot + "/" + pic + "/" + port + "."+ unitId;
//				compareText(application, "Interface", "Interface_textField", interfaceName_GigaBit , xml);
				
			}
			else if(bearerType.equals("STM-1")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//STM1 Number
				edittextFields_commonMethod(application, "STM1 Number", "stm1Number_textField" , STM1number, xml); 
				
				//Interface
				compareText(application, "Interface", "Interface_textField", "so-//:.0", xml);	
				
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				edittextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				edittextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				edittextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				editcheckbox_commonMethod(application, IVmanagement , "IVmanagement_checkbox", "IV management" , xml);
				
				//Compare Interface Name
//				String interfaceName_STM1Number = "so-" + slot + "/" + pic + "/" + port + ":" + STM1number + "." + unitId;
//				compareText(application, "Interface", "Interface_textField", interfaceName_STM1Number , xml);	
				
			}
			else if(bearerType.equals("T3")) {
				
				//Bandwidth
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
				
				//Card Type
				addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
				
				//STM1 Number
				edittextFields_commonMethod(application, "STM1 Number", "stm1Number_textField" , STM1number, xml); 
				
				//Bearer No
				edittextFields_commonMethod(application, "Bearer No", "bearerNumber_addtextField", bearerNumber, xml);
				
				//Interface
				compareText(application, "Interface", "Interface_textField", "t3-//::.0", xml);	
				
				//Encapsulation
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
				
				//Slot
				edittextFields_commonMethod(application, "Slot", "slot_textField", slot, xml);
				
				//Port
				edittextFields_commonMethod(application, "Port", "port_textField" , port , xml);
				
				//Unit Id
				edittextFields_commonMethod(application, "Unit ID", "unitId_textField" , unitId, xml);
				
				//Pic
				edittextFields_commonMethod(application, "Pic", "pic_textField" , pic , xml);
				
				//IV Management
				editcheckbox_commonMethod(application, IVmanagement , "IVmanagement_checkbox", "IV management" , xml);
//				
//				//Compare Interface Name
//				String interfaceName_T3 = "t3-" + slot + "/" + pic + "/" + port + ":" + STM1number + "::." + unitId;
//				compareText(application, "Interface", "Interface_textField", interfaceName_T3 , xml);
			}
			
			
		//Interface
			String interfaceName=getwebelement(xml.getlocator("//locators/" + application + "/Interface_textField")).getAttribute("value");
			if(interfaceName.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface' text field");
				System.out.println("No values displaying under 'Interface' text field");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Name is displaying as: "+ interfaceName);
				System.out.println("Interface Name is displaying as: "+ interfaceName);
			}
			
			scrollToTop();
			Thread.sleep(1000);
			boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configuraInterfaceCheckboxSelection")).isSelected();
			
			scrolltoend();
			Thread.sleep(1000);
		
		if(configureInterfaceSelection) {
		
			//perform Generate configuration
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
			
				Thread.sleep(2000);
//				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
			}
		}
		else {
			
			scrolltoend();
//			click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_okButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'OK' button");

		}
		
		return interfaceName;
		}
		
		
		public void CPEtoCPElink(String application, String sourceDevice, String sourceInterface, String targetDevice, String targetInterface) throws InterruptedException, DocumentException, IOException { 
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Create Link between CPE devices");
			
			scrolltoend();
			Thread.sleep(2000);
			isDisplayed(application, "CPEtoCPEpanelHeader", "'CPE To CPE Link' Panel Header");		//Verify Panel Header
			
			click_commonMethod(application, "Add New link", "CPEtoCPELink_AddNewLink", xml);	//click on Add New Link
			waitForpageload(); waitforPagetobeenable();
			
			boolean AddNewLinkPage = getwebelement(xml.getlocator("//locators/" + application + "/CPEtoCPELinkPage_Header")).isDisplayed();
			
			if(AddNewLinkPage) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add New Link' page is displaying");
				Log.info("'Add New Link' page is displaying");
				
				click_commonMethod(application, "Next", "CPEtoCPELink_nextButton", xml);	//click on Next button to verify warning Messages
				
				warningMessage_commonMethod(application, "CPEtoCPElink_SourceDeviceWarningMessage", "Source Device", xml);		//Source Device warning Message
				warningMessage_commonMethod(application, "CPEtoCPElink_SourceInterfaceWarningMessage", "Source Interface", xml);	//Source Interface warning Message
				warningMessage_commonMethod(application, "CPEtoCPElink_TargetDeviceWarningMessage", "Target Device", xml);		//Target Device warning Message
				warningMessage_commonMethod(application, "CPEtoCPElink_TargetInterfaceWarningMessage", "Target Interface", xml);	//Target Interface warning Message
				
				addDropdownValues_commonMethod(application, "Source Device", "CPEtoCPELink_sourceDeviceDropdown", sourceDevice, xml);
				addDropdownValues_commonMethod(application, "Source Interface", "CPEtoCPELink_sourceInterfaceDropdown", sourceInterface, xml);
				addDropdownValues_commonMethod(application, "Target Device", "CPEtoCPELink_targetDeviceDropdown", targetDevice, xml);
				addDropdownValues_commonMethod(application, "Target Interface", "CPEtoCPELink_targetInterfaceDropdown", targetInterface, xml);
				
				click_commonMethod(application, "Next", "CPEtoCPELink_nextButton", xml);
				Thread.sleep(1000);
				waitForpageload();  waitforPagetobeenable();
				
				verifysuccessmessage(application, "Link successfully inserted.");
				
				
			ExtentTestManager.getTest().log(LogStatus.INFO, "Delete created link");	
				scrolltoend();
				WebElement linkCreatedBetweenCPEdevices = getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedLink_UnderCPEtoCPElinkPanel").replace("value", sourceDevice));
				Clickon(linkCreatedBetweenCPEdevices);
				
				click_commonMethod(application, "Action", "CPEtoCPElink_ActionDropdown", xml);
				click_commonMethod(application, "Delete_Link", "CPEtoCPELink_deleteLink", xml);
				
				
				WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		         if(DeleteAlertPopup.isDisplayed())
		         {
		       	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
		       	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete alert POP up displays. Message is displaying as: "+ deletPopUpMessage);
		       	 
		            click_commonMethod(application, "Delete", "deletebutton", xml);
		            
		            scrollToTop();
		            Thread.sleep(1000);
		            
		            verifysuccessmessage(application, "Link successfully inserted.");
		         }
		         else
		         {
		               Log.info("Delete alert popup is not displayed");
		               ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
		         }
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add New Link' page is not displaying");
				Log.info("'Add New Link' page is not displaying");
			}
			
		}
		

		
		
		public void addInterface_DSlsiteorderSelected(String application, String serviceSubType, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, String newAddressRangeIpv4selection, String newinterfaceAddressrange,
				String subnetSizeValue_IPv4, String availableBlocksValue_IPv4, String link, String bearerType, String encapsulation, String slot, String port, String vlanid,
				String configureInterfaceOnDevice, String IVmanagement,String existingAddressRangeIPv6selection, String  existingAddressIPv6DropdownValue,
				String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSizeValue_IPv6, String availableBlocksValue_IPv6, String PPPencapsulation,
				String VPI, String VCI, String DSldownstreamSpeed, String DSLupstreamSpeed, String MBS) throws InterruptedException, DocumentException, 
		IOException { 
			
			boolean addressValue=false;
			Thread.sleep(3000);
			waitForpageload();   waitforPagetobeenable();
			
			scrolltoend();
//			click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");

			Thread.sleep(2000);
			waitForpageload();  waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(1000);
			
			warningMessage_commonMethod(application, "vci_warningMessage", "VCI", xml);	    //VCI warning Message
			warningMessage_commonMethod(application, "vpi_warningMessage", "VPI", xml);	    //VPI warning Message
			warningMessage_commonMethod(application, "slot_warningMessage", "Slot", xml);	//Slot warning Message
			warningMessage_commonMethod(application, "port_warningMessage", "Port", xml);	//Port warning Message
			
			//IPv4 Configuration	
			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
				
			//EIP Allocation
				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
				
				addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv4, xml);
				
				addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv4, xml);
				
				click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
				
				EIPallocationSuccessMessage(application, "successfully allocated");
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				Thread.sleep(2000);
				
				waitForpageload();  waitforPagetobeenable();
				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
				
				String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
				
				if(interfaceAddressRange.isEmpty()) {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
					System.out.println("No values displaying under 'Interface Address Range_IPv4' dropdown");
					  
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					
					click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
					Thread.sleep(5000);
					waitForpageload();   waitforPagetobeenable();
					
					addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
					if(addressValue) {
						
						selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface" , existingAddressIPv4DropdownValue);
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
						System.out.println("'Address_IPv4' dropdown is not displaying");
					}
				}
			}
			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
				
				  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
			WebElement IPv4Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/PE_addInterface_networklabelName"));
			scrolltoview(IPv4Netwrk);

		//IPv6 Configuration
		if(serviceSubType.equalsIgnoreCase("IPVPN Plus") || serviceSubType.equalsIgnoreCase("IPVPN Access")) {
			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv6, xml);
					
					addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
					
					EIPallocationSuccessMessage(application, "successfully allocated");
					
					click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					
					String interfaceAddressRange=getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")).getText();
					
					if(interfaceAddressRange.isEmpty()) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv6' dropdown");
						System.out.println("No values displaying under 'Interface Address Range_IPv6' dropdown");
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						System.out.println("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						
						click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
						Thread.sleep(4000);
						waitForpageload();  waitforPagetobeenable();
						
					try {	
						addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
						if(addressValue) {
							
							selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
							System.out.println("'Address_IPv6' dropdown is not displaying");
						}
					  }catch(Exception e) {
						  e.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						  System.out.println("'Address_IPv6' dropdown is not displaying");
					  }
					}
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
			WebElement IPv6Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/networkLabelname_IPv6"));
			scrolltoview(IPv6Netwrk); 
		}
			
			
		//Encapsulation
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
		
		//DSL DownStream Speed
			addDropdownValues_commonMethod(application, "DSL Downstream Speed" , "DSLdownstreamSpead_Dropdown" , DSldownstreamSpeed , xml);
		
		//DSL Upstream Speed
			addDropdownValues_commonMethod(application, "DSL Upstream Speed", "DSLupstreamSpeed_Dropdown" , DSLupstreamSpeed , xml);
			
		//PPP Encapsulation
			addDropdownValues_commonMethod(application, "PPP Encapsulation", "PPPencapsulation_Dropdown", PPPencapsulation , xml);
		
			WebElement linkElement = getwebelement(xml.getlocator("//locators/" + application + "/Link_textField"));
			scrolltoview(linkElement); 
		//Slot
			addtextFields_commonMethod(application, "Slot", "slot_textField" , slot, xml);
			
		//Port
			addtextFields_commonMethod(application, "Port", "port_textField", port, xml);
			
		//VPI 
			addtextFields_commonMethod(application, "VPI", "vpi_textField" , VPI , xml);
			
		//MBS
			addDropdownValues_commonMethod(application, "MBS" , "mbsDropdown", MBS , xml);
			
		//VCI
			addtextFields_commonMethod(application, "VCI", "vci_textField" , VCI, xml);
			
		//IV Management
			addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
			
		//compare Interface field value
			String expectedValueForInterface = "ATM" + slot + "/" + port + "." + VPI + "000" + VCI ;
			compareText(application, "Interface", "Interface_textField" , expectedValueForInterface, xml);
			
		//Interface
			String interfaceName=getwebelement(xml.getlocator("//locators/" + application + "/Interface_textField")).getAttribute("value");
			if(interfaceName.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface' text field");
				System.out.println("No values displaying under 'Interface' text field");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Name is displaying as: "+ interfaceName);
				System.out.println("Interface Name is displaying as: "+ interfaceName);
			}
			
			scrolltoend();
			Thread.sleep(1000);
		
		//perform Generate configuration
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
			
				Thread.sleep(2000);
				scrolltoend();
//				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
			}
		}
		
		
		public void addMultilink_DSLsiteOrderselected(String application, String serviceSubType, String configureInterfaceOnDevice, String link, String encapsulation, 
				String PPPencapsulation, String DSldownstreamSpeedPCR, String DSlupstreamSpeedSCR , String IVmanagement, String unitID, String existingAddressRangeIPv4selection, String newAddressRangeIpv4selection,
				String subnetSizeValue_IPv4, String availableBlocksValue_IPv4, String existingAddressIPv4DropdownValue,
				String newinterfaceAddressrange, String existingAddressRangeIPv6selection,
				String newAddressRangeIpv6selection, String subnetSizeValue_IPv6, String availableBlocksValue_IPv6,
				String existingAddressIPv6DropdownValue, String newinterfaceAddressrangeIPv6) throws InterruptedException, DocumentException, IOException { 
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(1000);
			
			boolean addressValue = false;  
			
			//IPv4 Configuration	
			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
				
			//EIP Allocation
				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
				
				addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv4, xml);
				
				addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv4, xml);
				
				click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
				
				EIPallocationSuccessMessage(application, "successfully allocated");
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				Thread.sleep(2000);
				
				waitForpageload();  waitforPagetobeenable();
				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
				
				String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addMultilink")));
				
				if(interfaceAddressRange.isEmpty()) {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
					System.out.println("No values displaying under 'Interface Address Range_IPv4' dropdown");
					  
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					
					click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
					
					addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
					if(addressValue) {
						
						selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface", existingAddressIPv4DropdownValue);
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
						System.out.println("'Address_IPv4' dropdown is not displaying");
					}
				}
			}
			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
				
				  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
			WebElement IPv4Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/PE_addInterface_networklabelName"));
			scrolltoview(IPv4Netwrk);

		//IPv6 Configuration
		if(serviceSubType.equalsIgnoreCase("IPVPN Access") || serviceSubType.equalsIgnoreCase("IPVPN Plus")) {	
			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv6, xml);
					
					addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
					
					EIPallocationSuccessMessage(application, "successfully allocated");
					
					click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					
					String interfaceAddressRange= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addMultilinkIPv6")));
					
					if(interfaceAddressRange.isEmpty()) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv6' dropdown");
						System.out.println("No values displaying under 'Interface Address Range_IPv6' dropdown");
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						System.out.println("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						
						click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
						
					try {	
						addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
						if(addressValue) {
							
							selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
							System.out.println("'Address_IPv6' dropdown is not displaying");
						}
					  }catch(Exception e) {
						  e.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						  System.out.println("'Address_IPv6' dropdown is not displaying");
					  }
					}
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
		
				}
			WebElement IPv6Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/networkLabelname_IPv6"));
			scrolltoview(IPv6Netwrk); 
		}	
		

			
		//Link Text Field
			addtextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
			
		//Encapsulation
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
			
		//PPP Encapsulation
			addDropdownValues_commonMethod(application, "PPP Encapsulation" , "PPPencapsulation_Dropdown", PPPencapsulation, xml);
			
		//DSL Downstream (PCR)
			addDropdownValues_commonMethod(application, "DSL Downstream Speed (PCR)", "addMultilink_DSLdownStreamSpeeedPCR", DSldownstreamSpeedPCR, xml);
			
		//DSL upStream (SCR)
			addDropdownValues_commonMethod(application, "DSL Upstream Speed (SCR)", "addMultilink_DSLupstreamSpeedSCR" , DSlupstreamSpeedSCR, xml);
			
		//IV Management
			addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying column Names under 'multilink Bearer' table");
			
			isDisplayed(application, "addMultilink_multilinkTableHeader", "MultiLinkBearerTable");	//Multilink Bearer Table Header Name
			isDisplayed(application, "addMultilink_checkToAddColumnName" , "'Check to Add to multilink' column name");	  //Check To Add To Multilink column ame
			isDisplayed(application, "addMultilinkTable_interfaceColumnName" , "'Interface' column Name");	//'Interface' column Name
			isDisplayed(application, "addMultilinkTable_linkOrcircuitColumnName", "'Link/Circuit Id' column Name");	//Link / Circuit Id  column Name
			isDisplayed(application, "addMultilinkTable_bearerTypeColumnName", "'Bearer Type' column Name");		//Bearer Type Column Name
			isDisplayed(application, "addMultilinkTable_vlanIDcolumnName", "'VLAN Id' column Name");  	//VLAN Id column name
			
//			
//		//Compare Interface Name
//			String interfaceName_multilink = "lsq-" + slot + "/" + pic + "/" + port + "." + unitID;
//			compareText(application, "Interface", "Interface_textField", interfaceName_multilink , xml);
			
			scrolltoend();
			Thread.sleep(1000);
		
			//Generate Configuration
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Generate Configuration' under 'Add Multilink' Page");
			
			//perform Generate configuration
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
			
				Thread.sleep(2000);
				scrolltoend();
//				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
			}
	}
		
		
		public void clickOnAddLoopback(String application) throws InterruptedException, DocumentException {
			
			WebElement routerToolPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/routerToolPanelheader"));
			scrolltoview(routerToolPanelHeader);
			Thread.sleep(1000);
			
			click_commonMethod(application, "Action", "InterfacePanel_actionDropdown" , xml);
			
			click_commonMethod(application, "Add Loopback", "addLoopBackLink" , xml);
			Thread.sleep(2000);
		}
		
		
		public String addLoopback(String application, String interfaceAddress, String IVmanagement, String configureOnBackupBRX) throws InterruptedException, DocumentException, IOException {
			
			waitForpageload();  waitforPagetobeenable();
			scrolltoend();
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");
			Thread.sleep(2000);
			
			
			//verify warning Message
				warningMessage_commonMethod(application, "interfaceAddress_warningMessage", "Interface Address", xml);
			
			//Interface Name
				String interfaceName = getwebelement(xml.getlocator("//locators/" + application + "/addLoopback_interfaceTextField")).getAttribute("value");
			
			//Interface Address text Field
				addtextFields_commonMethod(application, "Interface Address", "interfaceAddress_TextField", interfaceAddress, xml);
				
			//IV Management
				addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV Management", IVmanagement, "No", xml);
				
				
				scrolltoend();
				Thread.sleep(1000);
				
			//Configure on backup BRX
				addCheckbox_commonMethod(application, "configureOnBackupBRX_checkbox", "Configure on backup BRX", configureOnBackupBRX, "Yes", xml);
			
			//Generate Configuration
				ExtentTestManager.getTest().log(LogStatus.INFO, "'Generate Configuration' under 'Add Multilink' Page");
				
			//perform Generate configuration
				boolean configurationpanel=false;
				configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
				if(configurationpanel) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
					System.out.println("'Configuration' panel is displaying");
					
					click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
					Thread.sleep(2000);
					
					scrolltoend();
					Thread.sleep(1000);
					
					String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
					if(configurationvalues.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
						System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
								+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
						System.out.println("After clicking on 'Generate' button, "
								+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					}
				
					Thread.sleep(2000);
					scrolltoend();
//					click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
					ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");

				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
					System.out.println("'Configuration' panel is not displaying");
				}
				
				return interfaceName;
				
		}
		
		
		public String addMultilink_Cisco(String application, String serviceSubType, String link, String encapsulation,
				String bandwidth, String IVmanagement, String existingAddressRangeIPv4selection, String newAddressRangeIpv4selection,
				String subnetSizeValue_IPv4, String availableBlocksValue_IPv4, String existingAddressIPv4DropdownValue,
				String newinterfaceAddressrange, String existingAddressRangeIPv6selection,
				String newAddressRangeIpv6selection, String subnetSizeValue_IPv6, String availableBlocksValue_IPv6,
				String existingAddressIPv6DropdownValue, String newinterfaceAddressrangeIPv6, String interfaceMultilinkName) throws InterruptedException, DocumentException, IOException { 
			
			Thread.sleep(2000);
			waitForpageload();
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(1000);
			
			boolean addressValue = false;  
			
			//IPv4 Configuration	
			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
				
			//EIP Allocation
				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
				
				addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv4, xml);
				
				addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv4, xml);
				
				click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
				
				EIPallocationSuccessMessage(application, "successfully allocated");
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				Thread.sleep(2000);
				
				waitForpageload();  waitforPagetobeenable();
				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
				
				String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addMultilink")));
				
				if(interfaceAddressRange.isEmpty()) {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
					System.out.println("No values displaying under 'Interface Address Range_IPv4' dropdown");
					  
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
					
					click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
					scrollToTop();
					addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
					if(addressValue) {
						
						selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface", existingAddressIPv4DropdownValue);
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
						System.out.println("'Address_IPv4' dropdown is not displaying");
					}
				}
			}
			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
				
				  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
			WebElement IPv4Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/PE_addInterface_networklabelName"));
			scrolltoview(IPv4Netwrk);

		//IPv6 Configuration
		if(serviceSubType.equalsIgnoreCase("IPVPN Plus")  ||  serviceSubType.equalsIgnoreCase("IPVPN Access")) {
			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", subnetSizeValue_IPv6, xml);
					
					addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocksValue_IPv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
					
					EIPallocationSuccessMessage(application, "successfully allocated");
					
					click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					
					String interfaceAddressRange= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addMultilinkIPv6")));
					
					if(interfaceAddressRange.isEmpty()) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv6' dropdown");
						System.out.println("No values displaying under 'Interface Address Range_IPv6' dropdown");
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						System.out.println("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
						
						click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
						
					try {	
						addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
						if(addressValue) {
							
							selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
							System.out.println("'Address_IPv6' dropdown is not displaying");
						}
					  }catch(Exception e) {
						  e.printStackTrace();
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						  System.out.println("'Address_IPv6' dropdown is not displaying");
					  }
					}
					
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
			WebElement IPv6Netwrk = getwebelement(xml.getlocator("//locators/" + application + "/networkLabelname_IPv6"));
			scrolltoview(IPv6Netwrk); 
		}	

			
		//Link Text Field
			addtextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
			
		//Encapsulation
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_Dropdown" , encapsulation, xml);
			
		//Bandwidth
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth , xml);
			
		//IV Management
			addCheckbox_commonMethod(application, "IVmanagement_checkbox", "IV management", IVmanagement, "No", xml);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying column Names under 'multilink Bearer' table");
			
			isDisplayed(application, "addMultilink_multilinkTableHeader", "MultiLinkBearerTable");	//Multilink Bearer Table Header Name
			isDisplayed(application, "addMultilink_checkToAddColumnName" , "'Check to Add to multilink' column name");	  //Check To Add To Multilink column ame
			isDisplayed(application, "addMultilinkTable_interfaceColumnName" , "'Interface' column Name");	//'Interface' column Name
			isDisplayed(application, "addMultilinkTable_linkOrcircuitColumnName", "'Link/Circuit Id' column Name");	//Link / Circuit Id  column Name
			isDisplayed(application, "addMultilinkTable_bearerTypeColumnName", "'Bearer Type' column Name");		//Bearer Type Column Name
			isDisplayed(application, "addMultilinkTable_vlanIDcolumnName", "'VLAN Id' column Name");  	//VLAN Id column name
			
			scrolltoend();
			Thread.sleep(1000);
		
			//Generate Configuration
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Generate Configuration' under 'Add Multilink' Page");
			
			//perform Generate configuration
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
			
				Thread.sleep(2000);
//				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
			}
			
		return interfaceMultilinkName;
		
	}
		
		
		public void verifyAddDSLAMandHSLlink(String application, String DSLMdevice,String ServiceSubType,String VPNSiteOrder) throws InterruptedException, DocumentException {
			
			if(ServiceSubType.contains("IPVPN")) {
				
				WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
				scrolltoview(VPNSiteOrder_header);
				List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='VPN Site Orders']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
				int NoOfUsers = ExistingUsers.size();
				System.out.println("Numberofuser="+ NoOfUsers);

				if(NoOfUsers==1)
				{
					click(application, "VPN Order Radio button", "VPNSiteOrderRadio");
					Thread.sleep(3000);
				}
				else if(NoOfUsers>=1)
				{
					WebElement AddedUser = driver
							.findElement(By.xpath("//div[contains(text(),'" + VPNSiteOrder + "')]//parent::div//parent::div//preceding-sibling::div//span[@class='ag-selection-checkbox']"));
					AddedUser.click();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing VPN Site Order radio button");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No Order displayed");

				}

				click(application, "Action dropdown", "VPNSiteactionbutton");

				
				
				//click(application, "Click Radio Button", "SiteOrderRadioButton");
				//click(application, "Action dropdown", "VPNSiteactionbutton");
				ClickCommon(application, "View", "AddVPNSiteCommonLink");

			
			boolean actelisConfigurationPanel=false;
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add DSLAM and HSL'");
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrolltoend();
			
			actelisConfigurationPanel=getwebelement(xml.getlocator("//locators/" + application + "/ActelisConfigurationPanel")).isDisplayed();
			
			if(actelisConfigurationPanel) {
				System.out.println(" 'Actelis Configuration' panel is displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Configuration' panel is displaying as expected");
				
				boolean actelisLink=false;
			try {	
				actelisLink=getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM")).isDisplayed();
				if(actelisLink) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add DSLAM and HSL' link is displaying as expected");
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM")));
					Thread.sleep(3000);
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel");
				}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " ''Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel ");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to click on 'Add DSLAM and HSL' link");
			}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Actelis Configuration' panel is not displaying");
			}
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
				}

			
	}
		
		
		 public void AddDSLAMandHSL(String application, String DSLMdevice, String HSlname,String ServiceSubType) throws InterruptedException, DocumentException, IOException {
			 if(ServiceSubType.contains("IPVPN")) {
			 waitForpageload();
			 waitforPagetobeenable();
			 
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addDSLAMandHSL_xButton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'X' button under 'DSLAM device' dropdown");
				Log.info("Clicked on 'X' button under 'DSLAM device' dropdown");

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/DSLM_Device_Select")),DSLMdevice);
				ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is entered under 'DSLAM device' dropdown");
				
				WebElement valueToSElect=getwebelement(xml.getlocator("//locators/" + application + "/selectDSLAMdeviceValue").replace("value", DSLMdevice));
				
				try {
					if(valueToSElect.isDisplayed()) {
						ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is displaying under 'DSLAM device' dropdown");
						Log.info(DSLMdevice + " is displaying under 'DSLAM device' dropdown");
						
						Clickon(valueToSElect);
						ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is selected under 'DSLAM device' dropdown");
						Log.info(DSLMdevice + " is selected under 'DSLAM device' dropdown");
						Thread.sleep(2000);
						
						waitForpageload();
						waitforPagetobeenable();
						
						click_commonMethod(application, "List_HSL", "List_HSL_Link", xml);		//click on "List HSL" button
                        Thread.sleep(6000); 
						waitforPageenable();
                        selectRowForAddingInterface_Actelis(application, HSlname);		// select the Interface
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
						Log.info(DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
					}
					
				}catch(Exception e){
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
					Log.info(DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
				}
				
				
		 }
			 else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
					}

		 }
		 public void selectRowForAddingInterface_Actelis(String Application, String interfacenumber)
					throws IOException, InterruptedException, DocumentException {
	scrolltoend();
	Thread.sleep(3000);

			 System.out.println("check second time");
				int TotalPages;

				String TextKeyword = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToSelect_actelis_totalpage")));

				TotalPages = Integer.parseInt(TextKeyword);

				System.out.println("Total number of pages in table is: " + TotalPages);

				ab:

				if (TotalPages != 0) {
					for (int k = 1; k <= TotalPages; k++) {

						// Current page
						String CurrentPage = Gettext(
								getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToSelect_actelis_currentpage")));
						int Current_page = Integer.parseInt(CurrentPage);

						Clickon(getwebelement("//div[text()='Add DSLAM and HSL']")); 
						System.out.println("Currently we are in page number: " + Current_page);

						List<WebElement> results = getwebelements("//div[contains(text(),'"+ interfacenumber +"')]");
						
						int numofrows = results.size();
						System.out.println("no of results: " + numofrows);
						boolean resultflag;

						if (numofrows == 0) {

							Clickon(getwebelement("//button[text()='Next']"));
							Thread.sleep(3000);

						}

						else {

							for (int i = 0; i < numofrows; i++) {

								try {

									resultflag = results.get(i).isDisplayed();
									System.out.println("status of result: " + resultflag);
									if (resultflag) {
										System.out.println(results.get(i).getText());
										results.get(i).click();
										ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Add DSLAM and Device' page");
										
										Clickon(getwebelement("//span[text()='Next']"));
										Thread.sleep(3000);
									}

								} catch (StaleElementReferenceException e) {
									// TODO Auto-generated catch block
									// e.printStackTrace();
									results = driver.findElements(By.xpath("//div[contains(text(),'"+ interfacenumber +"')]"));
									numofrows = results.size();
									// results.get(i).click();
									Log.info("selected row is : " + i);
									ExtentTestManager.getTest().log(LogStatus.FAIL, "failure while selecting interface to add with service");
								}
							}
							break ab;
						}
					}
				} else {

					System.out.println("No values available in table");
					Log.info("No Interfaces got fetched");
					ExtentTestManager.getTest().log(LogStatus.FAIL, " NO interfaces got fetched");
				}

			}

		 
		 public void showInterface_ActelisConfiguuration(String application,String ServiceSubType) throws InterruptedException, DocumentException {
			 if(ServiceSubType.contains("IPVPN")){
			 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/showInterface_ActelisCnfiguration")));
			 Thread.sleep(3000);
			 
		 }
			 else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
					}

		 }
		 
		 
		 
		 public void deletInterface_ActelisConfiguration(String application, String interfaceName,String ServiceSubType) throws InterruptedException, DocumentException {
			 if(ServiceSubType.contains("IPVPN")) {
			//select the interface
			 Clickon(getwebelement("//div[text()='"+ interfaceName +"']"));
			 
			 //click on Action button
			 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AcionButton_ActelisConfiguration")));
			 
			 //Remove Button
			 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/removeButton_ActelisConfiguration")));
			 
			 boolean popupMessage=false;
			 popupMessage=getwebelement(xml.getlocator("//locators/" + application + "/popupMessage_forRemove_ActelisConfiguration")).isDisplayed();
			 
			 if(popupMessage) {
				 String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/popupMessage_forRemove_ActelisConfiguration")).getText();
				 ExtentTestManager.getTest().log(LogStatus.PASS, " On clicking remoe button, popup message displays as: "+ actualmsg);
				 System.out.println(" On clicking remoe button, popup message displays as: "+ actualmsg);
				 
					 Clickon(getwebelement("//button[@class='btn btn-danger']"));
					 Thread.sleep(3000);
			 }else {
				 
				 ExtentTestManager.getTest().log(LogStatus.FAIL, " popup message does not display after clicking on 'Remove' button");
			 }
		 }
			 else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
					}

		 }
		 public void successMessage_deleteInterfaceFromDevice_ActelisConfiguration(String application,String ServiceSubType) throws InterruptedException, DocumentException {
				if(ServiceSubType.contains("IPVPN")) {
				boolean successMessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface")).isDisplayed();
				String actualmessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface")).getText();
				if(successMessage) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for removing Interface is dipslaying as expected");
					System.out.println( " Success Message for removing interface is dipslaying as expected");
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "Message displays as: "+actualmessage);
					System.out.println("Message displays as: "+actualmessage);
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for removing Interface is not dipslaying");
					System.out.println( " Success Message for removing Interface is not dipslaying");
				}
			}
				else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Should not Present");
					}

		 }

		 public void PEInterface_clickOnDeleteLink(String application, String interfaceName) throws InterruptedException, DocumentException, IOException {
				
				waitForpageload();  waitforPagetobeenable();
				
				scrolltoend();
				Thread.sleep(1000);
				
				click_commonMethod(application, "Show Interface", "PEdevice_showInterfaceLink" , xml);
				Thread.sleep(2000);
				
				WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectInterfaceUnderPEdevice").replace("value", interfaceName));
				Clickon(interfaceValue);
				ExtentTestManager.getTest().log(LogStatus.PASS, interfaceName + " interface is selected under 'Provider Equipment' panel");
				Log.info( interfaceName + " interface is selected under 'Provider Equipment' panel");
				
				click_commonMethod(application, "Action", "actionDropdown_UnderProviderEquipmentPanel" , xml);    //Click on 'Action' button
				
				click_commonMethod(application, "Delete", "PEdevice_deleteLink", xml);	//click on Delete Link
				
				
				WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		         if(DeleteAlertPopup.isDisplayed())
		         {
		       	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
		       	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete alert POP up displays. Message is displaying as: "+ deletPopUpMessage);
		       	 
		            click_commonMethod(application, "Delete", "deletebutton", xml);
		            
		            scrollToTop();
		            Thread.sleep(1000);
		            
//		            verifysuccessmessage(application, "Link successfully deleted.");
		         }
		         else
		         {
		               Log.info("Delete alert popup is not displayed");
		               ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
		         }
			}
		 
		 
		 
		 public void deletePEdevice(String application, String existingdevicename) throws InterruptedException, DocumentException, IOException {
				
				WebElement providerEquipment_panelHeader= getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_panelHeader"));
				scrolltoview(providerEquipment_panelHeader);
				Thread.sleep(2000);
				
				if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	            {
	                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
//	                  Log.info(addeddevicesList);
	                  int AddedDevicesCount= addeddevicesList.size();
	                  for(int i=0;i<AddedDevicesCount;i++) {
	                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
	                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
	                        if(AddedDeviceNameText.contains(existingdevicename))
	                        {
	                              WebElement PEDevice_deleteLink=getwebelement(xml.getlocator("//locators/" + application + "/PE_deleteFromService").replace("value", AddedDevice_SNo)); 
	                              Clickon(PEDevice_deleteLink);
	                              Thread.sleep(2000);
	                              
	                              WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
	             		         if(DeleteAlertPopup.isDisplayed())
	             		         {
	             		       	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
	             		       	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete alert POP up displays. Message is displaying as: "+ deletPopUpMessage);
	             		       	 
	             		            click_commonMethod(application, "Delete", "deletebutton", xml);
	             		            
	             		            scrollToTop();
	             		            Thread.sleep(1000);
	             		            
	             		         }
	             		         else
	             		         {
	             		               Log.info("Delete alert popup is not displayed");
	             		               ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
	             		         }
	                              break;
	                        }
	                        else
	                        {
//	                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
	                        	Log.info("Invalid device name");
	                        }
	                  }
	            }
	            else
	            {
	                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	            }		
			 
		 }



	
	
	
	
	
	
		
	
	
	
	
	
	
	
}
