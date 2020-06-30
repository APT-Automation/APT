package com.saksoft.qa.scripthelpers;

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
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.interactions.DoubleClickAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.driverlibrary.Log;
import com.saksoft.qa.driverlibrary.XMLReader;


public class APT_MCS_CreateOrder_IPVPNHelper extends DriverHelper {

	public APT_MCS_CreateOrder_IPVPNHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/APT_MCS_CreateOrder_IPVPN_ServiceType.xml");

	
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
				DriverTestcase.logger.log(LogStatus.PASS, msg);
			} else {

				System.out.println("webElement is not  present" + ele.getText());
				DriverTestcase.logger.log(LogStatus.FAIL, msg);
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
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Downloads folder is empty");
				flag = false;
			} else {
				for (File listFile : files) {
					if (listFile.getName().contains(fileName)) {
						System.out.println(fileName + " is present");
						DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+fileName+"' excel file is downloaded successfully");
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
			
			
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigate to Customer detail in view service page is " +DetailPage);

			DriverTestcase.logger.log(LogStatus.PASS, " Costomer Detail page is displaying as expected");
		}
		
		
		
		
		
		
		
		public void addDropdownValues(String application, String fieldname, String xpath, String expectedValueToAdd) throws InterruptedException, DocumentException {
			boolean availability=false;
			try {  
				availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				if(availability) {
					DriverTestcase.logger.log(LogStatus.PASS, fieldname + " dropdown is displaying as expected");
					System.out.println(fieldname + " dropdown is displaying as expected");

					if(expectedValueToAdd.equalsIgnoreCase("null")) {

						DriverTestcase.logger.log(LogStatus.PASS, " No values selected under "+ fieldname + " dropdown");
						System.out.println(" No values selected under "+ fieldname + " dropdown");
					}else {

						Clickon(getwebelement("//div[label[text()='"+ fieldname +"']]//div[text()='×']"));
						Thread.sleep(3000);

						//verify list of values inside dropdown
						List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						DriverTestcase.logger.log(LogStatus.PASS, " List of values inside "+ fieldname + " dropdown is:  ");
						System.out.println( " List of values inside "+ fieldname + "dropdown is:  ");

						for (WebElement valuetypes : listofvalues) {

							Log.info("service sub types : " + valuetypes.getText());
							DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
							System.out.println(" " + valuetypes.getText());

						}

						Thread.sleep(3000);
						Clickon(getwebelement("//div[text()='"+ expectedValueToAdd +"']"));
						Thread.sleep(3000);

						String actualValue=getwebelement("//div[label[text()='"+ fieldname +"']]//span").getText();
						DriverTestcase.logger.log(LogStatus.PASS, fieldname + " dropdown value selected as: "+ actualValue );
						System.out.println( fieldname + " dropdown value selected as: "+ actualValue);

					}
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, fieldname + " is not displaying");
					System.out.println(fieldname + " is not displaying");
				}
			}catch(NoSuchElementException e) {
				DriverTestcase.logger.log(LogStatus.FAIL, fieldname + " is not displaying");
				System.out.println(fieldname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " NO value selected under "+ fieldname + " dropdown");
				System.out.println(" NO value selected under "+ fieldname + " dropdown");
			}

		}
		
		
		
		
		
		public void delete(String application, String labelname, String xpath, String expectedvalue) throws InterruptedException, DocumentException{
			Thread.sleep(1000);	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on delete link");

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
            DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on delete link");

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
                  DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete alert popup is not displayed");
            }
      }


		public void EnterTextValue(String application, String value, String labelname, String xpath) {
			WebElement element = null;

			try {
				//Thread.sleep(1000);
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				if(element==null)
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step: '"+labelname+"' text field not found");
					System.out.println(element);
				}
				else 
				{
					element.sendKeys(value);
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Entered '"+value+"' into '"+labelname+"' text field");
				}

			} catch (Exception e) {
				DriverTestcase.logger.log(LogStatus.FAIL,"Not able to enter '"+value+"' into '"+labelname+"' text field");
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
					DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
				}
				else {
					element.click();	
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
				}

			} catch (Exception e) {
				DriverTestcase.logger.log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
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
					DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
				}
				else if (emptyele!=null && emptyele.isEmpty()) {
					DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
				}else 
				{   
					text = element.getText();
					if(text.equals(ExpectedText)) {
						DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					}
					else if(text.contains(ExpectedText)) {
						DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
					}
				}
			}catch (Exception e) {
				DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
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
                        DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
                  }
                  else if(ExpectedText.equalsIgnoreCase("null")) {
                        DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+ labelname +"' field value is empty");
                        System.out.println("No values added to text field "+labelname);
                  }
                  else if (emptyele!=null && emptyele.isEmpty()) {
                        DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
                  }else 
                  {   
                        text = element.getText();
                        if(text.equals(ExpectedText)) {
                              DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
                        }
                        else if(text.contains(ExpectedText)) {
                              DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
                        }
                        else
                        {
                              DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
                        }
                        
                  }
            }catch (Exception e) {
                  DriverTestcase.logger.log(LogStatus.FAIL,"Step: Field is not displaying");
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
					DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
				}
				else
				{
					String WarningMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getText();

					System.out.println("'"+labelname+"' field warning  message displayed as : " + WarningMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step : validation message for '"+labelname+"' text field displayed as : " + WarningMsg);
					Log.info("'"+labelname+"' field warning message displayed as : " + WarningMsg);
				}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				System.out.println("'"+labelname+"' field warning message is not dipslaying");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: '"+labelname+"' field warning message is not displaying");
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

		
		//Added by PK
		public void ClearAndEnterTextValue(String application, String labelname,  String xpath, String newValue) {
			WebElement element = null;
			try {
				//Thread.sleep(1000);
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				String value= element.getAttribute("value");
				
				if(value.isEmpty())
				{
					DriverTestcase.logger.log(LogStatus.INFO, "Step: '"+labelname+"' text field is empty");
					System.out.println(value);
				
				}else {
					element.clear();
					Thread.sleep(1000);
					element.sendKeys(newValue);
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Entered '"+newValue+"' into '"+labelname+"' text field");
				}

			} catch (Exception e) {
				DriverTestcase.logger.log(LogStatus.FAIL,"Not able to enter '"+newValue+"' into '"+labelname+"' text field");
				e.printStackTrace();
			}

		}
		
		
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

		//	public String Gettext(WebElement el) throws IOException
		//	{ 
		//		String text=el.getText().toString();
		//		return text;
		//	} 

		public void scrolltoend() {//Or Scroll Down
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}


		public void scrollToTop() {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
		}

		public void clickOnBankPage() {
			driver.findElement(By.xpath("//body")).click();
		}


		//Scroll to particular webelement
		public void ScrolltoElement(String application, String xpath) throws InterruptedException, DocumentException {
			WebElement element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",(element));
		}

		
		
		public void scrolltoview (WebElement element) {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
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
				DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is displaying");
				System.out.println(labelname + " text field is displaying");
				
				if(expectedValueToAdd.equalsIgnoreCase("null")) {
					DriverTestcase.logger.log(LogStatus.PASS, "No values added to text field "+labelname);
					System.out.println("No values added to text field "+labelname);
				}else {
					
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToAdd);
					Thread.sleep(3000);
					
					String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field value added as: "+ actualvalue);
				}
				
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " text field is not displaying");
				System.out.println(labelname + " text field is not displaying");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " text field is not displaying");
			System.out.println(labelname + " text field is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to add value to "+ labelname + " text field");
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
				  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  System.out.println(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					  Thread.sleep(3000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
					  
					  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
									System.out.println(" " + valuetypes.getText());
						}
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(2000);
						
					  Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(3000);
					  
					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
				System.out.println(" NO value selected under "+ labelname + " dropdown");
			}
			
		}
		
		*/
		
		
		
		
		
		
		public void addDropdownValues_commonMethod(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
			  boolean availability=false;
			try {  
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			  if(availability) {
				  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  System.out.println(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					  Thread.sleep(2000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
					  
					  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
									System.out.println(" " + valuetypes.getText());
						}
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(4000);
						
					  Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(2000);
					  
					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
				  System.out.println(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
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
					
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is displaying");
					System.out.println(labelname + " checkbox is displaying");
					
				boolean isElementSelected=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isSelected();
				Thread.sleep(2000);
			
			//verify whether checkbox is selected/unselected by default		
				if(DefaultSelection.equalsIgnoreCase("yes")) {
					if(isElementSelected) {
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is selected by default as expected");
						System.out.println(labelname + " checkbox is selected by default as expected");
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checkbox is not selected by default");
						System.out.println(labelname + " checkbox is not selected by default");
					}
					
				}
				else if(DefaultSelection.equalsIgnoreCase("no")) {
					if(isElementSelected) {
						DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checkbox is selected by default");
						System.out.println(labelname + " checkbox is selected by default as expected");
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is not selected by default as expected");
						System.out.println(labelname + " checkbox is not selected by default");
					}
					
				}
			
			//Perform click on checkbox	
				if(!expectedValue.equalsIgnoreCase("null")) {
					if (expectedValue.equalsIgnoreCase("yes")) {

						if(isElementSelected) {
							DriverTestcase.logger.log(LogStatus.PASS, labelname +" checkbox is Selected by default");
						}else {
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
							Log.info(labelname + " check box is selected");
							DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is selected");
						}
					}
					else if (expectedValue.equalsIgnoreCase("no")) {
						
						if(isElementSelected) {
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
							Log.info(labelname + " check box is unselected");
							DriverTestcase.logger.log(LogStatus.PASS,labelname + " is selected");
							
						}else {
							DriverTestcase.logger.log(LogStatus.PASS, "No changes made for "+ labelname +" checkbox");
							System.out.println("No changes made for "+ labelname +" checkbox");
						}
						
					}
				}else {
					DriverTestcase.logger.log(LogStatus.PASS, "No changes made for "+ labelname +" checkbox");
					System.out.println("No changes made for "+ labelname +" checkbox");
				}
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checbox is not available");
					System.out.println(labelname + " checbox is not available");
				}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, labelname +  " checkbox is not available ");
				System.out.println( labelname +  " checkbox is not available ");
			}catch(Exception er) {
				er.printStackTrace();
				System.out.println("Not able to perform selection for "+ labelname+ " checkbox");
				DriverTestcase.logger.log(LogStatus.FAIL, "Not able to perform selection for "+ labelname+ " checkbox");
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
		public void edittextFields_commonMethod(String application, String labelname, String xpathname, String expectedValueToEdit, XMLReader xml) throws InterruptedException, DocumentException, IOException {
				boolean availability=false;
			try {	
				availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).isDisplayed();
				if(availability) {
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is displaying");
					System.out.println(labelname + " text field is displaying");
					
					if(expectedValueToEdit.equalsIgnoreCase("null")) {
						
						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is not edited. It is displaying as "+actualvalue);
						System.out.println(labelname + " text field is not edited as expected. It is displaying as "+actualvalue);
					}else {
						
						getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).clear();
						Thread.sleep(3000);
						
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
						Thread.sleep(3000);
						
						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is edited as: "+ actualvalue);
					}
					
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, labelname + " text field is not displaying");
					System.out.println(labelname + " text field is not displaying");
				}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " text field is not displaying");
				System.out.println(labelname + " text field is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to perform editing under"+ labelname + " text field");
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
			 			DriverTestcase.logger.log(LogStatus.PASS, "Step :  validation message for"+ fieldlabelName +"  field displayed as : " + ErrMsg);
			 			Log.info(fieldlabelName + " field warning  message displayed as : " + ErrMsg);
			 			}else{
			 				DriverTestcase.logger.log(LogStatus.FAIL, "validation message for"+ fieldlabelName +"  field is not displaying");
			 				System.out.println("validation message for"+ fieldlabelName +"  field is not displaying");
			 			}
			 			}catch(NoSuchElementException e) {
			 				e.printStackTrace();
			 				System.out.println( "No warning message displayed for "+ fieldlabelName);
			 				DriverTestcase.logger.log(LogStatus.FAIL, "No warning message displayed for "+ fieldlabelName);
			 			}catch(Exception ed) {
			 				ed.printStackTrace();
			 				System.out.println( "No warning message displayed for "+ fieldlabelName);
			 				DriverTestcase.logger.log(LogStatus.FAIL, "No warning message displayed for "+ fieldlabelName);
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
						DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
					}
					else {
						element.click();	
						DriverTestcase.logger.log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
					}

				} catch (Exception e) {
					DriverTestcase.logger.log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
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
					  
					  DriverTestcase.logger.log(LogStatus.PASS,  labelname+ " checkbox is displaying in edit page");
					  System.out.println(labelname+ " checkbox is displaying in edit page");
					  
					if(!expectedResult.equalsIgnoreCase("null")) {
						boolean isElementSelected=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isSelected();
						Thread.sleep(2000);
						
						if (expectedResult.equalsIgnoreCase("yes")) {
							
							if(isElementSelected) {
								DriverTestcase.logger.log(LogStatus.PASS, labelname +" checkbox is not edited and it is already Selected while creating");
							}else {
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
								Log.info(labelname + " check box is selected");
								DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is selected");
							}
						}
						else if (expectedResult.equalsIgnoreCase("no")) {
							
							if(isElementSelected) {
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
								Log.info(labelname + " check box is unselected");
								DriverTestcase.logger.log(LogStatus.PASS,labelname + " is edited and gets unselected");
							}else {
								DriverTestcase.logger.log(LogStatus.PASS, labelname + " is not edited and it remains unselected");
							}
							
						}
					}else {
						DriverTestcase.logger.log(LogStatus.PASS,"No changes made for "+ labelname +"  chekbox");
					}
				  }else {
					  DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checkbox is not available in 'Edit Service' page");
				  }

					}catch(NoSuchElementException e) {
						e.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checkbox is not displaying under 'Edit service' page");
						System.out.println(labelname+" checkbox is not displaying under 'Edit service' page");
					}catch(Exception err) {
						err.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on "+ labelname + " checkbox");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on delete link");

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
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete alert popup is not displayed");
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
						DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
					}
					else {
						element.click();	
						DriverTestcase.logger.log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
					}

				} catch (Exception e) {
					DriverTestcase.logger.log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
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
						DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
					}
					else if (emptyele!=null && emptyele.isEmpty()) {
						DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
					}else 
					{   
						text = element.getText();
						if(text.equals(ExpectedText)) {
							DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
						}
						else if(text.contains(ExpectedText)) {
							DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
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
						DriverTestcase.logger.log(LogStatus.FAIL, labelname+" not found");
						System.out.println(labelname+" not found");
					}
					else if (emptyele!=null && emptyele.isEmpty()) {
//						DriverTestcase.logger.log(LogStatus.PASS,  labelname + "' value is empty");
						
						emptyele= "Null";
						
						sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
						
						if(emptyele.equalsIgnoreCase(ExpectedText)) {
							
							DriverTestcase.logger.log(LogStatus.PASS, " The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							System.out.println(" The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
							
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL,"The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							System.out.println(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
						

					}else 
					{   
						text = element.getText();
						if(text.equals(ExpectedText)) {
							DriverTestcase.logger.log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							System.out.println(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(text.contains(ExpectedText)) {
							DriverTestcase.logger.log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
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
							DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
						}
						else {
							element.click();	
							DriverTestcase.logger.log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
						}

					} catch (Exception e) {
						DriverTestcase.logger.log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
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
							  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown is displaying");
							  System.out.println(labelname + " dropdown is displaying");
							  
							  WebElement el =getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
							  
							  Select sel = new Select(el);
							  
							 String firstSelectedOption=sel.getFirstSelectedOption().getText();
							 DriverTestcase.logger.log(LogStatus.PASS, "By default "+ labelname+" dropdown is displaying as: "+firstSelectedOption);
							 System.out.println("By default "+ labelname+" dropdown is displaying as: "+firstSelectedOption);
							 
							    List<WebElement> we = sel.getOptions();
							   
							    for(WebElement a : we)
							    {
							        if(!a.getText().equals("select")){
							            ls.add(a.getText());
							            
							        }
							    }
						
							    
							    DriverTestcase.logger.log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
					            System.out.println("list of values inside "+labelname+" dropdown is: "+ls);
					            
							  if(expectedValueToAdd.equalsIgnoreCase("null")) {
								  
								  DriverTestcase.logger.log(LogStatus.PASS, "No values selected under "+ labelname + " dropdown");
							  }else {
								  Select s1=new Select(el);
								  s1.selectByVisibleText(expectedValueToAdd);
								  
								  String SelectedValueInsideDropdown=sel.getFirstSelectedOption().getText();
									 DriverTestcase.logger.log(LogStatus.PASS,  labelname+" dropdown value selected as: "+SelectedValueInsideDropdown);
									 System.out.println(labelname+" dropdown value selected as: "+SelectedValueInsideDropdown);
							  }
							 }
							
						}catch(NoSuchElementException e) {
							DriverTestcase.logger.log(LogStatus.FAIL, labelname + " Value is not displaying");
							  System.out.println(labelname + " value is not displaying");
						}catch(Exception ee) {
							ee.printStackTrace();
							DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
							System.out.println(" NO value selected under "+ labelname + " dropdown");
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
						Log.info("failure in fetching success message - 'Service created Successfully'  ");
						DriverTestcase.logger.log(LogStatus.FAIL, expected+ " Message is not displaying");
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click(application, "create customer link", "createcustomerlink");
		//Thread.sleep(2000);
		compareText(application, "create customer page header", "createcustomer_header", "Customer");

		scrolltoend();
		
		click(application, "Ok", "okbutton");
		//Warning msg check
		WarningMessage(application, "customernamewarngmsg", "Customer Name");
		WarningMessage(application, "countrywarngmsg", "Country");
		WarningMessage(application, "ocnwarngmsg", "OCN");
		WarningMessage(application, "typewarngmsg", "Type");
		WarningMessage(application, "emailwarngmsg", "Email");

		//Clear customer info
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		EnterTextValue(application, email, "Email", "emailtextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		click(application, "Clear button", "clearbutton");
		DriverTestcase.logger.log(LogStatus.PASS, "All text field values are cleared");

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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click(application, "Create Order/Service Link", "CreateOrderServiceLink");	
		Log.info("=== Create Order/Service navigated ===");

		//click on Next button to check mandatory messages	
		click(application, "Next", "nextbutton");

		//Customer Error message	
		WarningMessage(application, "customer_createorderpage_warngmsg", "Customer");

		//Entering Customer name	
		EnterTextValue(application, customerName1, "Customer Name", "entercustomernamefield");
		EnterTextValue(application, customerName2, "Customer Name", "entercustomernamefield");	

		//Select Customer from dropdown
		
		addDropdownValues_commonMethod(application, "Customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected,xml);
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


	public void createnewuser(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email,String Phone, String EditUsername, String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone)
			throws InterruptedException, DocumentException, IOException {

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
			
			scrolltoview(OKButton);
			
			click(application, "OK", "OkButton");
			Thread.sleep(2000);
			compareText(application, "Header Message", "UserCreatedMessage", "User successfully created");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : User successfully created");
			

			//Edit User
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

			}
			Thread.sleep(2000);
			//ScrolltoElement(application, "DedicatedPortalCustomer");
			
			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "Edit", "edit");
			Thread.sleep(2000);
			compareText(application, "Edit User Header", "edituser_header", "Edit User");
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
			cleartext(application, "User Name", "UserName");
			EnterTextValue(application, EditUsername, "User Name", "UserName");
			cleartext(application, "First Name", "FirstName");
			EnterTextValue(application, EditFirstname, "First Name", "FirstName");
			cleartext(application, "SurName", "SurName");
			EnterTextValue(application, EditSurname, "SurName", "SurName");
			cleartext(application, "Postal Address", "PostalAddress");
			EnterTextValue(application, EditPostaladdress, "Postal Address", "PostalAddress");
			cleartext(application, "Email", "Email");
			EnterTextValue(application, EditEmail, "Email", "Email");
			cleartext(application, "Phone", "Phone");
			EnterTextValue(application, EditPhone, "Phone", "Phone");
			cleartext(application, "Password", "Password");
			click(application, "Generate Password", "GeneratePassword");
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			click(application, "OK", "OkButton");
			Thread.sleep(2000);
			compareText(application, "Header Message", "UserUpdateMessage", "User successfully updated");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : User successfully Updated");

			//View User

			ScrolltoElement(application, "DedicatedPortalCustomer");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");
			}
			Thread.sleep(2000);
			//ScrolltoElement(application, "DedicatedPortalCustomer");
			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "view", "UserView");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/usernamevalue")));
			compareText(application, "User Name", "usernamevalue", EditUsername);
			compareText(application, "First Name", "firstnamevalue", EditFirstname);
			compareText(application, "SurName", "surnamevalue", EditSurname);
			compareText(application, "Postal Address", "postaladdressvalue", EditPostaladdress);
			compareText(application, "Email", "emailvalue", EditEmail);
			compareText(application, "Phone", "phonevalue", EditPhone);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/viewpage_backbutton")));
			click(application, "Back", "viewpage_backbutton");
			Log.info("------ View User successful ------");
			Thread.sleep(2000);

			//Delete User

			ScrolltoElement(application, "DedicatedPortalCustomer");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else {
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");
			}
			Thread.sleep(2000);
			//ScrolltoElement(application, "DedicatedPortalCustomer");
			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "Delete", "Userdelete");
			click(application, "Delete", "deletebutton");
			Thread.sleep(2000);
			scrollToTop();
			Thread.sleep(2000);
			compareText(application, "Header Message", "UserDeleteMessage", "User successfully deleted");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : User successfully Updated");


			//delete(application, "delete", "Userdelete", "User successfully deleted");
			
	//	}

		/*else if(!UserGrid.contains("1px"))
		{
		
			//Edit User
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "User Radio button", "UserUnchecked");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

			Thread.sleep(2000);
			ScrolltoElement(application, "DedicatedPortalCustomer");
			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "Edit", "edit");

			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/edituser_header")));
			compareText(application, "Edit User Header", "edituser_header", "Edit User");
			cleartext(application, "User Name", "UserName");
			EnterTextValue(application, EditUsername, "User Name", "UserName");
			cleartext(application, "First Name", "FirstName");
			EnterTextValue(application, EditFirstname, "First Name", "FirstName");
			cleartext(application, "SurName", "SurName");
			EnterTextValue(application, EditSurname, "SurName", "SurName");
			cleartext(application, "Postal Address", "PostalAddress");
			EnterTextValue(application, EditPostaladdress, "Postal Address", "PostalAddress");
			cleartext(application, "Email", "Email");
			EnterTextValue(application, EditEmail, "Email", "Email");
			cleartext(application, "Phone", "Phone");
			EnterTextValue(application, EditPhone, "Phone", "Phone");
			cleartext(application, "Password", "Password");
			click(application, "Generate Password", "GeneratePassword");
			WebElement OKButton= getwebelement("//button[@type='submit']");
			scrolltoview(OKButton);
			click(application, "OK", "OkButton");

			//View User

			if(NoOfUsers==1)
			{
				click(application, "User Radio button", "UserUnchecked");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

			Thread.sleep(2000);
			ScrolltoElement(application, "DedicatedPortalCustomer");
			click(application, "Action dropdown", "UserActionDropdown");
			
			click(application, "view", "UserView");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/usernamevalue")));
			compareText(application, "User Name", "usernamevalue", EditUsername);
			compareText(application, "First Name", "firstnamevalue", EditFirstname);
			compareText(application, "SurName", "surnamevalue", EditSurname);
			compareText(application, "Postal Address", "postaladdressvalue", EditPostaladdress);
			compareText(application, "Email", "emailvalue", EditEmail);
			compareText(application, "Phone", "phonevalue", EditPhone);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/viewpage_backbutton")));
			click(application, "Back", "viewpage_backbutton");
			Log.info("------ View User successful ------");

			//Delete User

			if(NoOfUsers==1)
			{
				click(application, "User Radio button", "UserUnchecked");
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

			Thread.sleep(2000);
			ScrolltoElement(application, "DedicatedPortalCustomer");
			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "Delete", "Userdelete");
			delete("nginservice", "delete", "User", "User successfully deleted");

		}
		*/
	}


	public void verifyManagementOptionspanel(String application) throws InterruptedException, DocumentException, IOException {

		Boolean ManagementOptions_Header = getwebelement(xml.getlocator("//locators/" + application + "/managementoptions_header")).isDisplayed();
		Log.info("Management options header text is displayed as : " + ManagementOptions_Header);
		System.out.println("Management options header text:"+ ManagementOptions_Header);
		sa.assertTrue(ManagementOptions_Header,"Management Options");
		
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
		String PerformanceReporting_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PerformanceReporting_text")));
		GetText(application, PerformanceReporting_text, "PerformanceReporting_value");

		// verify Proactive Notification information
		String ProactiveNotification_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/ProactiveNotification_text")));
		GetText(application, ProactiveNotification_text, "ProactiveNotification_Value");

		// verify Notification Management Team information
		//String NotificationManagementTeam_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/NotificationManagementTeam_text")));
		//GetText(application, NotificationManagementTeam_text, "ManageService_value");

		// verify DialUser Administration information
		String DialUserAdministration_text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/DialUserAdministration_text")));
		GetText(application, DialUserAdministration_text, "DialUserAdministration_Value");

		
		/*//				String ResellerAdmin_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/reselleradmin_text")));
		//				String ResellerAdmin_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/reselleradmin_value")));
		//				Log.info(ResellerAdmin_Text + " : checkbox value is displayed as : " + ResellerAdmin_Value);
		//				System.out.println(ResellerAdmin_Text + " : " + ResellerAdmin_Value); */
		
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
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Add link is displaying under Reseller panel");
			Log.info("Add link is displayed");
		}
		else
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Add link is not displaying under Reseller panel");

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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Reseller Name is displaying as: '"+resellername+"'");
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
				DriverTestcase.logger.log(LogStatus.PASS, ""+Link+" link is displaying under reseller panel");
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
		DriverTestcase.logger.log(LogStatus.FAIL, "Reseller is not added in the grid");
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
			DriverTestcase.logger.log(LogStatus.FAIL, "Reseller is not added in the grid");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Edit Reseller page is displaying as expected");
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
				DriverTestcase.logger.log(LogStatus.FAIL, "Reseller is not added in the grid");
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
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete Reseller alert is displaying as expected");
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletealertclose")));
						Thread.sleep(2000);
						DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on delete alert popup close button");
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
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Associate Reseller with NGIN Objects link verified");
				}

			}
			else
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : View link is not displaying under Reseller panel");
		}
		else
		{
			Log.info("Reseller is not added in the grid");
			System.out.println("Reseller is not added in the grid");
			DriverTestcase.logger.log(LogStatus.FAIL, "Reseller is not added in the grid");
		}
	}

	public void verifyCustomerpanel(String application) throws InterruptedException, DocumentException, IOException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
		compareText(application, "Customer panel header", "customerheader", "Customer");
		click(application, "Action dropdown", "CustomerpanelActionDropdown");

		if(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Add link is displaying under Customer panel");
			Log.info("Add link is displayed");
		}
		else
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Add link is not displaying under Customer panel");

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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");

		//Select Customer Name from dropdown
		/*	try {	

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/customerpanel_customername")));
			//	Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/customerpanel_customername")), selectcustomer);
				Thread.sleep(2000);
				DriverTestcase.logger.log(LogStatus.PASS, "Step :selected customer is : " + selectcustomer);

				WebElement el1 = driver
						.findElement(By.xpath("//span[contains(text(),'" + selectcustomer + "')][1]"));
				el1.click();
				Log.info("=== customer selected===");
				DriverTestcase.logger.log(LogStatus.PASS, "Customer name has been selected from the dropdown");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "Select Customer name dropdown is not available");
			} */

		String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Default checkbox is checked");
				Log.info("Default checkbox is checked");
			}
			else
				DriverTestcase.logger.log(LogStatus.FAIL, "Default checkbox is not checked by default");
		}

		else if(configure.equalsIgnoreCase("YES"))
		{
			String Configure_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/configurecheckbox")).getAttribute("checked");
			if(Configure_Checkbox.isEmpty())
			{
				System.out.println("Configure checkbox is checked");
				Thread.sleep(1000);
				DriverTestcase.logger.log(LogStatus.PASS, "Configure checkbox is checked");
				Log.info("Configure checkbox is checked");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Configure checkbox is not checked");
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
				DriverTestcase.logger.log(LogStatus.PASS, ""+Link+" link is displaying under customer panel");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Country dropdown is disabled as expected");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Country dropdown is enabled");
			}
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");

			String CustomernameDisabled= getwebelement(xml.getlocator("//locators/" + application + "/customername_disabled")).getAttribute("disabled");
			if(CustomernameDisabled!=null)
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer name dropdown is disabled as expected");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Customer name dropdown is enabled");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Edit Customer in view customer page is verified");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete Customer in view customer page is verified");
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
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : View link is not displaying under customer panel");
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
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Add SAN link is displaying under SAN/FRC panel");
			Log.info("Add link is displayed");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Add link is not displaying under SAN/FRC panel");
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
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Existing customer is available");
			click(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay");
		}
		else if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_nomatchesfound")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Existing customer is not available");
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
			DriverTestcase.logger.log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
			String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
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
					DriverTestcase.logger.log(LogStatus.PASS, "Default checkbox is checked");
					Log.info("Default checkbox is checked");
				}
				else
					DriverTestcase.logger.log(LogStatus.FAIL, "Default checkbox is not checked by default");
			}

			else if(configure.equalsIgnoreCase("YES"))
			{
				String Configure_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/configurecheckbox")).getAttribute("checked");
				if(Configure_Checkbox.isEmpty())
				{
					System.out.println("Configure checkbox is checked");
					Thread.sleep(1000);
					DriverTestcase.logger.log(LogStatus.PASS, "Configure checkbox is checked");
					Log.info("Configure checkbox is checked");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Configure checkbox is not checked");
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step :Customer name selected is : " + SAN_Customernamevalue);
		String SAN_Customername=SAN_Customernamevalue.substring(0, SAN_Customernamevalue.indexOf("(")).trim();
		writetoexcel("src\\com\\saksoft\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 106, SAN_Customername);

		String SANNumber_CountryCode=getwebelement(xml.getlocator("//locators/" + application + "/san_number")).getAttribute("value");
		EnterTextValue(application, sannumber, "SAN Number", "san_number");
		//SAN number
		String SANNumberValue= SANNumber_CountryCode+sannumber;
		DriverTestcase.logger.log(LogStatus.PASS, "Step :SAN Number is dsplayed as : " + SANNumberValue);
		writetoexcel("src\\com\\saksoft\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 107, SANNumberValue);

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
				DriverTestcase.logger.log(LogStatus.PASS, ""+Link+" link is displaying under SAN/FRC panel");
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
			DriverTestcase.logger.log(LogStatus.PASS, "FRC Number for Added SAN is displayed as : " + AddedSAN_FRCNumber);

			WebElement AddedSan1= driver.findElement(By.xpath("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + SAN_Customername + "')]"));
			String AddedSAN_Customervalue = AddedSan1.getText();
			Log.info("Customer Name for Added SAN is displayed as : " + AddedSAN_Customervalue);
			System.out.println("Customer Name for Added SAN: "+ AddedSAN_Customervalue);
			sa.assertEquals(AddedSAN_Customervalue,SAN_Customername);
			DriverTestcase.logger.log(LogStatus.PASS, "Customer Name for Added SAN is displayed as : " + AddedSAN_Customervalue);
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
//				DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name field is disabled as expected");
//				String CustomerNamevalue= getwebelement(xml.getlocator("//locators/" + application + "/editsan_customername")).getAttribute("value");
//				DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name field value is displayed as:"+ CustomerNamevalue);
//			}
//			else
//			{
//				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Customer Name field is enabled");
//			}
//			
//			//verify SAN Number field
//			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_sannumber")).getAttribute("readonly")!=null)
//			{
//				DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Number field is disabled as expected");
//				String SANNumbervalue= getwebelement(xml.getlocator("//locators/" + application + "/editsan_sannumber")).getAttribute("value");
//				DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Number field value is displayed as:"+ SANNumbervalue);
//			}
//			else
//			{
//				DriverTestcase.logger.log(LogStatus.FAIL, "Step : SAN Number field is enabled");
//			}
//			
//			//Select service profile from dropdown
//			addDropdownValues(application, "Service Profile", "serviceprofile", serviceprofilevalue);
//			Thread.sleep(1000);
//			String Supervisionfield= GetText(application, "Supervision mode", "supervisionfield");
//			DriverTestcase.logger.log(LogStatus.PASS, "Step : Supervision field value is displayed as:"+ Supervisionfield);
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
//			DriverTestcase.logger.log(LogStatus.PASS, "Step : CPS Free Format field value is displayed as:"+ CPSFreeFormatValue);
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
//					DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
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
//			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to Edit SAN page");
//			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//			click(application, "Cancel", "cancelbutton");
//			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
//			{
//				Log.info("Navigated to view service page");
//				System.out.println("Navigated to view service page");
//			}
//			else
//			{
//				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
//			}
//			}
//			else
//			{
//				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to Edit SAN page");
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
//				DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to Add SAN page");
//				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//				click(application, "Cancel", "cancelbutton");
//			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
//			{
//				Log.info("Navigated to view service page");
//				System.out.println("Navigated to view service page");
//			}
//			else
//			{
//				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
//			}
//			}
//			else
//			{
//				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to Add SAN page");
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
//				DriverTestcase.logger.log(LogStatus.PASS, "Step: Delete alert popup is displayed as expected");
//				click(application, "Delete alert close", "deletealertclose");
//			}
//			else
//			{
//				Log.info("Delete alert popup is not displayed");
//				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Delete alert popup is not displayed");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step: PortIN Number is displayed as:" +PortToNumber);
			}
			else
			{
				EnterTextValue(application, portinnumber, "Port To Number", "porttonumber");
			}
			
			try {
				WebElement WebAccessBlocked_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/webaccessblocked_checkbox"));
				if (WebAccessBlocked_Checkbox.isEnabled())
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Web access blocked checkbox is checked as expected");
				}
				else
					DriverTestcase.logger.log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
			}

			try {
				String PortDate= getwebelement(xml.getlocator("//locators/" + application + "/webaccessblocked_checkbox")).getAttribute("value");
				if(PortDate!= null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Port Date value is: '"+PortDate+"'");
				}
				else
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Port Date value is not displaying");
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Port Date value is not displaying");
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
					DriverTestcase.logger.log(LogStatus.PASS, "Step : PortIN is successful");
				}
				else
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortIN is not successful");
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortIN is unsuccessful");
			}

			click(application, "OK", "port_okbutton");
		}
		else
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortIN page is not opened");	
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step: PortIN Number is displayed as:" +PortToNumber);
			}
			else
			{
				EnterTextValue(application, portinnumber, "Port To Number", "porttonumber");
			}

			try {
				WebElement WebAccessBlocked_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/webaccessblocked_checkbox"));
				if (WebAccessBlocked_Checkbox.isEnabled())
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Web access blocked checkbox is checked as expected");
				}
				else
					DriverTestcase.logger.log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
			}

			try {
				String PortDate= getwebelement(xml.getlocator("//locators/" + application + "/webaccessblocked_checkbox")).getAttribute("value");
				if(PortDate!= null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Port Date value is: '"+PortDate+"'");
				}
				else
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Port Date value is not displaying");
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Port Date value is not displaying");
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
					DriverTestcase.logger.log(LogStatus.PASS, "Step : PortOUT is successful");
				}
				else
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortOUT is not successful");
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortOUT is unsuccessful");
			}
			
			click(application, "OK", "port_okbutton");

			//Cancel porting not done
		}
		else
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortOut page is not opened");

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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Move is cancelled");
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Move is successful");
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Bulk Move is cancelled");
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Bulk Move is successful");
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
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown button");

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
			// DriverTestcase.logger.log(LogStatus.PASS, "order contract number textfield is
			// displayed");
			System.out.println("order contract number textfield is displayed");

			boolean selectorderswitch = getwebelement(
					xml.getlocator("//locators/" + application + "/selectorderswitch")).isDisplayed();
			sa.assertTrue(selectorderswitch, "c");
			// DriverTestcase.logger.log(LogStatus.PASS, "select order switch is
			// displayed");
			System.out.println("select order switch is displayed");

			boolean rfireqiptextfield = getwebelement(
					xml.getlocator("//locators/" + application + "/rfireqiptextfield")).isDisplayed();
			sa.assertTrue(rfireqiptextfield, "RFI / RFQ /IP Voice Line number text field is displayed ");
			// DriverTestcase.logger.log(LogStatus.PASS, "RFI / RFQ /IP Voice Line number
			// text field is displayed ");
			System.out.println("RFI / RFQ /IP Voice Line number text field is displayed ");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectorderswitch")));
			Thread.sleep(5000);

			System.out.println("clicked on the switch to verify the presence of create order button");
			// DriverTestcase.logger.log(LogStatus.PASS, "clicked on the switch to verify
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
			// DriverTestcase.logger.log(LogStatus.PASS, "clicked on the switch to verify
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
			DriverTestcase.logger.log(LogStatus.INFO, "Step :existing order not selected");
		}

	}

	public static String newordernumber, newVoiceLineNumber;

	public void createneworderservice(String application, String neworder, String neworderno, String newrfireqno)
			throws InterruptedException, IOException, DocumentException {

		if (neworder.equalsIgnoreCase("YES")) {

			WebElement CreateOrder_Header= driver.findElement(By.xpath("//div[text()='Create Order / Service']"));
			scrolltoview(CreateOrder_Header);
			Thread.sleep(2000);

			click(application, "select order switch", "selectorderswitch");	
			EnterTextValue(application, neworderno, "Order/Contract Number", "newordertextfield");
			EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");
			click(application, "create order", "createorderbutton");	
			compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully");			
			scrolltoview(CreateOrder_Header);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;

		} else {
			
			ScrolltoElement(application, "nextbutton");
			addDropdownValues_commonMethod(application,"Order/Contract Number(Parent SID)", "OrdernumberDropdown", neworderno, xml);
			//EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");

			System.out.println("new order not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : Existing Order Selected");
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
				DriverTestcase.logger.log(LogStatus.FAIL,
						"Step : service identification textfield is not empty, value displayed is : "
								+ serviceidentificationtextfieldvalue);
			} else {

				DriverTestcase.logger.log(LogStatus.PASS, "Step : service identification textfield is empty ");
			}

			// service type
			boolean servicetype = getwebelement(xml.getlocator("//locators/" + application + "/servicetypevalue")).isDisplayed();
			sa.assertTrue(servicetype, "service type value is not displayed");

			String servicetypevalue = getwebelement(xml.getlocator("//locators/" + application + "/servicetypevalue")).getText();
			if (servicetypevalue != null && servicetypevalue.equalsIgnoreCase("NGIN")) {

				DriverTestcase.logger.log(LogStatus.PASS,
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
				DriverTestcase.logger.log(LogStatus.FAIL,
						"Step : remarks textarea is not empty, value displayed is : " + remarktextareavalue);
			} else {

				DriverTestcase.logger.log(LogStatus.PASS, "Step : remarks textarea is empty ");
			}

			// management options- customer administration
			boolean customeradministrationcheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/customeradministrationcheckbox")).isEnabled();
			boolean customeradministrationcheckbox2 = getwebelement(
					xml.getlocator("//locators/" + application + "/customeradministrationcheckbox")).isSelected();

			if (customeradministrationcheckbox == true && customeradministrationcheckbox2 == false) {

				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : customer administration checkbox is unselected by default ");

			} else {

				sa.fail("customer administration checkbox is disabled or selected by default");
				DriverTestcase.logger.log(LogStatus.FAIL,
						"Step : customer administration checkbox is disabled or selected by default ");
			}

			// management options- SAN administration
			boolean SANadministrationcheckbox1 = getwebelement(
					xml.getlocator("//locators/" + application + "/sanadministrationcheckbox")).isEnabled();
			boolean SANadministrationcheckbox2 = getwebelement(
					xml.getlocator("//locators/" + application + "/sanadministrationcheckbox")).isSelected();

			if (SANadministrationcheckbox1 == true && SANadministrationcheckbox2 == false) {

				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : SAN administration checkbox is unselected by default ");

			} else {

				sa.fail("SAN administration checkbox is disabled or selected by default");
				DriverTestcase.logger.log(LogStatus.FAIL,
						"Step : SAN administration checkbox is disabled or selected by default ");
			}

			// management options- Reseller Administration
			boolean Reselleradministrationcheckbox1 = getwebelement(
					xml.getlocator("//locators/" + application + "/reselleradministrationcheckbox")).isEnabled();
			boolean Reselleradministrationcheckbox2 = getwebelement(
					xml.getlocator("//locators/" + application + "/reselleradministrationcheckbox")).isSelected();

			if (Reselleradministrationcheckbox1 == true && Reselleradministrationcheckbox2 == false) {

				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Reseller Administration checkbox is unselected by default ");

			} else {

				sa.fail("Reseller Administration checkbox is disabled or selected by default");
				DriverTestcase.logger.log(LogStatus.FAIL,
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
	
	
	public void createservice(String application, String sid, String Remarks, String EmailService, String PhoneService,String DeliveryChannel, String Pakage) throws InterruptedException, IOException, DocumentException 
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
		        	
		        	DriverTestcase.logger.log(LogStatus.PASS, CheckboxName + " text field is Checked");
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
		        	
		        	DriverTestcase.logger.log(LogStatus.PASS, CheckboxName + " text field is Checked");
					System.out.println(CheckboxName + " text field is displaying");
		        }
		        }
		        }

		        
		        
		    }
		scrolltoend();
		click(application, "OK", "okbutton");
		compareText(application, "Service creation success msg", "servicecreationmessage", "Service successfully created");
	sa.assertAll();
			
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
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Manage Service checkbox is not selected");
//		}
//		
//		if (ManageService.equalsIgnoreCase("NO")) {
//			System.out.println("Manage Service checkbox is already selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Manage Service checkbox is already selected");
//		} else {
//			click(application, "Manage Service checkbox", "ManageServicecheckbox");
//		}
//		
//		//Syslog Event View checkbox
//		if (SyslogEventView.equalsIgnoreCase("YES")) {
//			click(application, "Syslog Event View checkbox", "SyslogEventViewcheckbox");
//		} else {
//			System.out.println("Syslog Event View checkbox is not selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Syslog Event View checkbox is not selected");
//		}
//		if (SyslogEventView.equalsIgnoreCase("NO")) {
//			System.out.println("Syslog Event View checkbox is already selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Syslog Event View checkbox is already selected");
//		} else {
//			click(application, "Syslog Event View checkbox", "SyslogEventViewcheckbox");
//		}
//		
//		//Service Status View checkbox
//		if (ServiceStatusView.equalsIgnoreCase("YES")) {
//			click(application, "Service Status View checkbox", "ServiceStatusViewcheckbox");
//		} else {
//			System.out.println("Service Status View checkbox is not selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Service Status View checkbox is not selected");
//		}
//		if (ServiceStatusView.equalsIgnoreCase("NO")) {
//			System.out.println("Service Status View checkbox is already selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Service Status View checkbox is already selected");
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
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Router Configuration View checkbox is not selected");
//		}
//		if (RouterConfigurationView.equalsIgnoreCase("NO")) {
//			System.out.println("Router Configuration View checkbox is already selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Router Configuration View checkbox is already selected");
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
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Performance Reporting checkbox is not selected");
//		}
//		if (PerformanceReporting.equalsIgnoreCase("NO")) {
//			System.out.println("Performance Reporting checkbox is already selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Performance Reporting checkbox is already selected");
//		} else {
//			click(application, "Performance Reporting checkbox", "PerformanceReportingcheckbox");
//		}

		click(application, "Performance Reporting checkbox", "PerformanceReportingcheckbox");
		
		
//		//Proactive Notification checkbox
//		if (ProactiveNotification.equalsIgnoreCase("YES")) {
//			click(application, "Proactive Notification checkbox", "ProactiveNotificationcheckbox");
//		} else {
//			System.out.println("Proactive Notification checkbox is not selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Proactive Notification checkbox is not selected");
//		}
//		if (ProactiveNotification.equalsIgnoreCase("NO")) {
//			System.out.println("Proactive Notification checkbox is already selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Proactive Notification checkbox is already selected");
//		} else {
//			click(application, "Proactive Notification checkbox", "ProactiveNotificationcheckbox");
//		}
//		
//		//Dial User Administration checkbox
//		if (DialUserAdministration.equalsIgnoreCase("YES")) {
//			click(application, "Dial User Administration checkbox", "DialUserAdministrationcheckbox");
//		} else {
//			System.out.println("Dial User Administration checkbox is not selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Dial User Administration checkbox is not selected");
//		}
//		if (DialUserAdministration.equalsIgnoreCase("NO")) {
//			System.out.println("Dial User Administration checkbox is already selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step : Dial User Administration checkbox is already selected");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : order information is matched");
			} else {
				sa.fail("order information is not matched");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

		} else {

			System.out.println("existing order is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : existing order is not selected");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : order information is matched");				
			} else {
				sa.fail("order information is not matched");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

		} else {

			System.out.println("new order is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : new order is not selected");
		}

		sa.assertAll();
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step: Edit Order header text is displayed as : "+ EditOrder_Header);
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

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
		compareText(application, "Header Message", "OrderUpdatedMessage", "Order successfully updated");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Order successfully updated");
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		Thread.sleep(1000);
		//Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
		sa.assertTrue(OrderHeader,"Order");
		Log.info("Navigated to order panel in view service page");
		DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		compareText(application, "Order Number", "ordernumbervalue", editorderno);
		compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno);
		Log.info("------ Edit Order is successful ------");

	}

	public void verifyorderpanel_changeorder(String application, String changeorderno, String changevoicelineno,String createNew) throws InterruptedException, DocumentException, IOException {

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
			DriverTestcase.logger.log(LogStatus.PASS,"Order present are:"+ ChangeOrder_DropdownList.get(i).getText());
			System.out.println(ChangeOrder_DropdownList.get(i).getText());
		}
		DriverTestcase.logger.log(LogStatus.PASS,"Count of order is"+ ChangeOrder_Dropdown_count);
		System.out.println("order count="+ChangeOrder_Dropdown_count);
		
		if(!createNew.equalsIgnoreCase("YES"))
		{
			
			Clickon(getwebelement("//div[contains(@class,'sc-ifAKCX oLlzc') and (text()='Order')]".replace("Order", changeorderno)));
			Thread.sleep(3000);

			//Cancel change order
			click(application, "Cancel", "changeorder_cancelbutton");
			Thread.sleep(1000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader,"Order");
			Log.info("Navigated to order panel in view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

			//Change order
			click(application, "Action dropdown", "orderactionbutton");
			click(application, "Change Order", "changeorderlink");

			Boolean ChangeOrder1_Header = getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed();
			Log.info("Change Order header text is displayed as : " + ChangeOrder1_Header);
			System.out.println("Change Order header text:"+ ChangeOrder1_Header);
			sa.assertTrue(ChangeOrder1_Header,"Change Order");
			Thread.sleep(1000);

			click(application, "Choose order dropdown", "changeorder_chooseorderdropdown");
			Clickon(getwebelement("//div[contains(@class,'sc-ifAKCX oLlzc') and (text()='Order')]".replace("Order", changeorderno)));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected order from dropdown");

			click(application, "OK", "changeorder_okbutton");
			Thread.sleep(2000);
			compareText(application, "Header Message", "OrderChangeMessage", "Order successfully changed.");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Order successfully Changed.");
			
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader1,"Order");
			Log.info("Navigated to order panel in view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

			compareText(application, "Order Number", "ordernumbervalue", changeorderno);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno);
			Log.info("------ Change Order is successful ------");

		}
		else
		{
			click(application, "Select order switch", "changeorder_selectorderswitch");

			//WebElement ChangeOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/changeordernumber"));
			click(application, "Order Number", "changeordernumber");
			Thread.sleep(2000);
			EnterTextValue(application, changeorderno, "Order Number", "changeordernumber");

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
			EnterTextValue(application, changeorderno, "Order Number", "changeordernumber");
			click(application, "RFI Voice Line Number", "changeordervoicelinenumber");
			Thread.sleep(3000);
			EnterTextValue(application, changevoicelineno, "RFI Voice Line Number", "changeordervoicelinenumber");
			click(application, "Create Order", "createorder_button");
			Thread.sleep(2000);
			compareText(application, "Header Message", "OrderCreatedMessage", "Order successfully created.");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Order successfully created");
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader1,"Order");
			Log.info("Navigated to order panel in view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to order panel in view service page");

			compareText(application, "Order Number", "ordernumbervalue", changeorderno);
			compareText(application, "RFI VoicreateNewce Line Number", "ordervoicelinenumbervalue", changevoicelineno);
			Log.info("------ Change Order is successful ------");
		}
	}

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String ServiceSubType ,
			String Remarks , String EmailService, String PhoneService) throws InterruptedException, DocumentException, IOException {
		
		
		compareText(application, "Service panel Header", "servicepanel_header", "Service");
		compareText(application, "Service Identification", "servicepanel_serviceidentificationvalue", sid);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype);
		compareText(application, "Service SubType", "servicepanel_ResselerCodevalue", ServiceSubType);
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks);
		compareText(application, "Email", "servicepanel_Emailvalue", EmailService);
		compareText(application, "Phone Contact", "servicepanel_PhoneContactvalue", PhoneService);
	}
	
	
	
	

	public void verifyservicepanel_links(String application, String EditRemarks, String Remarks, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		cleartext(application, "Remarks", "remarktextarea");
		EnterTextValue(application, EditRemarks, "Remarks", "remarktextarea");
		scrolltoend();
		//WebElement CancelButton= getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton"));
		//scrolltoview(CancelButton);
		click(application, "Cancel", "cancelbutton");
		Thread.sleep(2000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks);	
		}
		else
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");

		//Edit service
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		cleartext(application, "Remarks", "remarktextarea");
		EnterTextValue(application, EditRemarks, "Remarks", "remarktextarea");
		
		scrolltoend();
		click(application, "OK", "OKbutton_ServiceCreation");
		Thread.sleep(4000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			compareText(application, "Service updated success message", "serviceupdate_successmsg", "Service  successfully updated");	
		}
		else
		{
			Log.info("Service not updated");
			System.out.println("Service not updated");
		}
		
		
		//Manage service
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Manage", "manageLink");
		compareText(application, "Manage service header", "manageservice_header", "Manage Service");
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
					DriverTestcase.logger.log(LogStatus.PASS, mess);
					if(mess.equalsIgnoreCase("Service Status History successfully changed.")) 
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request logged");
					}
						
					}
					else
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request is not logged");
				}
				catch(StaleElementReferenceException e)
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "No service history to display");
				}
			}
			else
				DriverTestcase.logger.log(LogStatus.FAIL, "Status link is not working");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change not reqired");
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
		click(application, "Synchronize", "synchronizelink");
		compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		click(application, "Back", "managepage_backbutton");

		
		
		//Verify all the links available in service actions list
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		Thread.sleep(1000);
		click(application, "Action dropdown", "serviceactiondropdown");
		compareText(application, "Edit Link", "EditLink", "Edit");
		compareText(application, "Delete Link", "DeleteLink", "Delete");
		//compareText(application, "Show Infovista Report Link", "ShowNewInfovistaReportLink", "Show Infovista Report");
		compareText(application, "Show New Infovista Report Link", "ShowNewInfovistaReportLink", "Show New Infovista Report");
		compareText(application, "Manage Link", "manageLink", "Manage");
		compareText(application, "Synchronize Link", "SynchronizeServiceLink", "Synchronize");
		//compareText(application, "Manage Subnets Ipv6 Link", "ManageSubnetsIpv6Link", "Manage Subnets Ipv6");
		compareText(application, "Dump Link", "DumpLink", "Dump");
		
		
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Create Order/Service Link");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/createordernametextfield")), name);
		Thread.sleep(2000);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Name is entered in name textfield");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/choosecustomerdropdown")));
		Thread.sleep(2000);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Customer Dropdown");

		WebElement el = driver
				.findElement(By.xpath("//div[@role='list']//span[contains(text(),'" + selectCustomer + "')]"));
		String selectedcustomer = el.getText();
		el.click();
		Thread.sleep(2000);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer is selected from dropdown" + selectedcustomer);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		Thread.sleep(2000);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Next button");

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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : View link is displaying in Search SAN page");
				Log.info("View link is displayed");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : View link is not displaying in Search SAN page");
			}

			//Delete link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete")).isDisplayed())
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete link is displaying in Search SAN page");
				Log.info("Add link is displayed");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete link is not displaying in Search SAN page");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
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
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
		}

		if(getwebelement(xml.getlocator("//locators/" + application + "/sansearchheader")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to SAN Search page");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to SAN Search page");
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
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to view service page");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
				}

			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Existing order is not available");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
		}

		driver.navigate().back();
		Thread.sleep(2000);

		//Verify manage link in search order page
		compareText(application, "SAN Search", "sansearchheader", "SAN Search");
		if(getwebelement(xml.getlocator("//locators/" + application + "/sansearchheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to 'SAN Search' page");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to 'SAN Search' page");
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
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to view service page");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
				}

			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Existing order is not available");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
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
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
		}

	}


	public void verifyNGINMessage(String application, String nginmessage_sannumber) throws InterruptedException, DocumentException
	{
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/managecoltnetworklink")));
		Thread.sleep(1000);
		click(application, "NGIN Messages", "nginmessageslink");
		Thread.sleep(2000);
		compareText(application, "Manage Messages Header", "nginmessageheader", "Manage Messages - Messages Search");
		isDisplayed(application, "nginmsg_sannumber", "SAN Number text field");
		isDisplayed(application, "nginmsg_customername_textfield", "Customer Name text field");
		Thread.sleep(1000);
		EnterTextValue(application, nginmessage_sannumber, "SAN Number", "nginmsg_sannumber");
		click(application, "Search", "san_searchbutton");
		Thread.sleep(2000);


		WebElement SelectExistingSAN= driver.findElement(By.xpath("//div[text()='"+nginmessage_sannumber+"']/preceding-sibling::div//div//input[@type='radio']"));
		if(SelectExistingSAN.isDisplayed())
		{
			//Verify existing SAN column headers
			compareText(application, "FRC Number", "nginmsg_frcnumbercolumn", "FRC Number");
			compareText(application, "Name", "nginmsg_namecolumn", "Name");
			compareText(application, "Customer Name", "customernamecolumn", "Customer Name");
			Thread.sleep(1000);
			Clickon(SelectExistingSAN);
			Thread.sleep(2000);
			click(application, "Action dropdown", "searchsan_actiondropdown");
			//View link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/edit")).isDisplayed())
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Edit link is displaying in Manage Messages page");
				Log.info("Edit link is displayed");

				click(application, "Edit", "edit");
				Thread.sleep(2000);
				compareText(application, "Edit Message Header", "editmessageheader", "Edit Message");
				//verify edit message page fields
				//verify customer name field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_customername")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name field is disabled as expected");
					String Customernamevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_customername")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name field value is displayed as:"+ Customernamevalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Customer Name field is enabled");
				}

				//verify country code field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_countrycode")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Country code field is disabled as expected");
					String CountryCodevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_countrycode")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Country code field value is displayed as:"+ CountryCodevalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Country code field is enabled");
				}

				//verify san reference field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_sanreference")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Reference field is disabled as expected");
					String SANReferencevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_sanreference")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Reference field value is displayed as:"+ SANReferencevalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : SAN Reference field is enabled");
				}

				//verify name field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_name")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Name field is disabled as expected");
					String Namevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_name")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Name field value is displayed as:"+ Namevalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Name field is enabled");
				}

				//verify description field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_description")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Description field is disabled as expected");
					String Descriptionvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_description")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Description field value is displayed as:"+ Descriptionvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Description field is enabled");
				}

				//verify body field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_bodyfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Body field is disabled as expected");
					String Bodyvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_bodyfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Body field value is displayed as:"+ Bodyvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Body field is enabled");
				}

				//verify prefix field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_prefixfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Prefix field is disabled as expected");
					String Prefixvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_prefixfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Prefix field value is displayed as:"+ Prefixvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Prefix field is enabled");
				}

				//verify repetitions field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_repetitionsfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Repetitions field is disabled as expected");
					String Repetitionsvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_repetitionsfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Repetitions field value is displayed as:"+ Repetitionsvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Repetitions field is enabled");
				}

				//verify duration field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_durationfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Duration field is disabled as expected");
					String Durationvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_durationfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Duration field value is displayed as:"+ Durationvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Duration field is enabled");
				}

				//verify suffix field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_suffixfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Suffix field is disabled as expected");
					String Suffixvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_suffixfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Suffix field value is displayed as:"+ Suffixvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Suffix field is enabled");
				}

				//verify start network charge checkbox field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_startnetworkcharge")).getAttribute("checked")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : 'Start Network Charge' checkbox is checked by default as expected");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : 'Start Network Charge' checkbox is not checked by default");
				}

				click(application, "Cancel", "cancelbutton");
				Thread.sleep(3000);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Edit link is not displaying in Manage Messages page");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing NGIN Messages to display");
		}

		WebElement SelectExistingSAN1= driver.findElement(By.xpath("//div[text()='"+nginmessage_sannumber+"']/preceding-sibling::div//div//input[@type='radio']"));
		if(SelectExistingSAN1.isDisplayed())
		{
			Clickon(SelectExistingSAN1);
			Thread.sleep(2000);
			click(application, "Action dropdown", "searchsan_actiondropdown");
			//View link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/edit")).isDisplayed())
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Edit link is displaying in Manage Messages page");
				Log.info("Edit link is displayed");

				click(application, "Edit", "edit");
				Thread.sleep(2000);
				compareText(application, "Edit Message Header", "editmessageheader", "Edit Message");
				//Uncheck start network charge checkbox
				click(application, "Start Network Charge Checkbox", "editmessage_startnetworkcharge");

				click(application, "OK", "editmessage_okbutton");
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/nginmessageheader")).isDisplayed())
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to Manage Messages page");
					compareText(application, "Edit Message page success message", "editmessage_successmessage", "Message successfully updated.");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Not navigated to Manage Messages page");
				}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Edit link is not displaying in Manage Messages page");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing NGIN Messages to display");
		}

	}

	
	
	
	
	
	
	
	
	//////////////////////////////////////  MAS SWITCH  ////////////////////////
	//////////////////////////////////////  MAS SWITCH  ////////////////////////
	
	
	
	
	
	public void AddVPNSiteOrder(String application, String VPNCountry,String VPNDeviceCity,String VPNPhysicalSite,String VPNVendorModel,String VPNSiteOrder,String VPNSiteAlias,
			String VPNRouterId,String VPNManagementAdd,String VoipService ) throws InterruptedException, DocumentException, IOException {

		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		
		
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
		WarningMessageCom(application, "Snmp V3 Auth Password","WarningMessageCommon");
		WarningMessageCom(application, "Snmp V3 Auth Password","WarningMessageCommon");
		
		EnterTextValue(application, VPNSiteOrder, "Site Order Number", "VPNSiteOrderNum");
		EnterTextValue(application, VPNSiteAlias, "Site Alias", "VPNSiteAlis");
		addDropdownValues_commonMethod(application, "Device Country", "VPNDeviceCountry", VPNCountry, xml);
		addDropdownValues_commonMethod(application, "Device Xng City", "VPNDeviceCity", VPNDeviceCity, xml);
		EnterTextValue(application, VPNPhysicalSite, "Physical Site", "VPNPhysicalSite");
		click(application, "VoIP", "VPNVoIP");
		addDropdownValues_commonMethod(application, "VoIP Class of Service", "VPNVoipService", VoipService, xml);
		scrolltoview(getwebelement("//div[text()='Device']"));
		EnterTextValue(application, VPNRouterId, "Router Id", "VPNRouterId");
		EnterTextValue(application, VPNManagementAdd, "Management Address", "VPNManagementAdd");
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
		click(application, "Generate Password", "VPNGeneratePass1");
		click(application, "Generate Password", "VPNGeneratePass2");
		click(application, "Next button", "VPNNext");
		//*Not WOrking*selectValueInsideDropdown(application, "IMSPOPLocationDropdown", "IMS POP Location dropdown", "Birmingham", xml);
		

		/*
		// Select  IMS POP Location dropdown
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/MAS_IMSPOPLocationDropdown")));
		List<WebElement> IMSPOPLocationDropdownList = driver.findElements(By.xpath("//div[@class='sc-htpNat AUGYd']/div"));////span[@role='option']-Not taking
		for (WebElement clist : IMSPOPLocationDropdownList) {

			System.out.println("Available IMS POP Location name is : " + clist.getText().toString());
			DriverTestcase.logger.log(LogStatus.PASS,"Step : Available IMS POP Location name is : " + clist.getText().toString());
			Log.info("Available IMS POP Location name is :" + clist.getText().toString());
		}
		
		Thread.sleep(1000);
		
		// click on IMSPOPLocationDropdown
		WebElement IMSPOPLocationDropdown = driver.findElement(By.xpath("//div[contains(text(),'" + MAS_IMSPOPLocation + "')]"));
		System.out.println("Selected IMS POP Location dropdowm is : " + IMSPOPLocationDropdown.getText().toString());
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected IMS POP Location dropdowm is : " + IMSPOPLocationDropdown.getText().toString());
		Log.info("Selected IMS POP Location dropdowm is : " + IMSPOPLocationDropdown.getText().toString());
		IMSPOPLocationDropdown.click();
		Thread.sleep(2000);
		
		
		click(application, "OK button", "MAS_OKbutton");
		
		
		compareText(application, "Add Switch Successful Message for MAS Switch", "MAS_AddSwitchSuccessfulMessage", "MAS switch added successfully");
		
		Log.info("------ MAS Switch added successfully ------");
		*/
	}

	
	
	
	
	public void verifyAddedVPNSiteInformation_View(String application) throws InterruptedException, DocumentException, IOException {

		WebElement VPNSiteOrder_header= getwebelement(xml.getlocator("//locators/" + application + "/VPNSiteOrderHeader"));
		scrolltoview(VPNSiteOrder_header);
		
		click(application, "VPN View Link", "VPN_viewdevice1");
		
		Thread.sleep(10000);
		GetText(application, "Device", "VPN_ViewDevice_Header");//Device//Last Modified on :2020-05-14 07:18 GMT, Modified By :colttest@colt.net, Sync Status: sync in progress


		// Verify All Device Information under Device panel for MAS Switch
		GetText(application, "Device Name", "VPN_View_DeviceNameValue");
		GetText(application, "Vendor/Model", "VPN_View_VendorModelValue");
		GetText(application, "Management Address", "VPN_View_ManagementAddressValue");
		GetText(application, "Snmpro", "VPN_View_SnmproValue");
		GetText(application, "Country", "VPN_View_CountryValue");
		GetText(application, "City", "VPN_View_CityValue");
		GetText(application, "Site", "VPN_View_SiteValue");
		GetText(application, "Premise", "VPN_View_PremiseValue");
		//GetText(application, "Test", "VPN_View_TestColumnName");
		//GetText(application, "Status", "VPN_View_StatusColumnName");
		GetText(application, "Last Refresh", "VPN_View_LastRefresh");
		click(application, "Back", "VPN_Backbutton");
		
		
//		click(application, "ACTION link", "MAS_View_ActionLink");
//		//Verify List of links under Action
//		GetText(application, "Edit Link", "MAS_View_Action_EditLink");
//		GetText(application, "Delete Link", "MAS_View_Action_DeleteLink");
//		GetText(application, "Fetch Device Interfaces Link", "MAS_View_Action_FetchDeviceInterfacesLink");
//		click(application, "ACTION link", "MAS_View_ActionLink");
		
		
		Log.info("------ Verified Added MAS Switch Information successfully ------");
	}

	
	
	
	public void verifyEditMASswitchFunction(String application) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service

//		click(application, "Edit Link", "MAS_View_Action_EditLink");
//		//**EnterTextValue(application, Site textfield, "Site", "Sitetextarea");
//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Edit_PremiseLevel")));
//		//click(application, "Cancel in Edit MAS Switch Page", "MAS_Cancelbutton");//Not working
//		Thread.sleep(3000);
		
		click(application, "ACTION link", "MAS_View_ActionLink");
		click(application, "Edit Link", "MAS_View_Action_EditLink");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Edit_PremiseLevel")));
		click(application, "OK in Edit MAS Device", "MAS_OKbutton");
		Thread.sleep(5000);
		compareText(application, "MAS Switch Update message", "MAS_UpdateSwitchSuccessfulMessage", "MAS switch updated successfully");

		Log.info("------  MAS switch updated successfully   ------");
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
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request logged");
					}
					else
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request is not logged");
				}
				catch(StaleElementReferenceException e)
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "No service history to display");
				}
			}
			else
				DriverTestcase.logger.log(LogStatus.FAIL, "Status link is not working");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change not reqired");
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
					DriverTestcase.logger.log(LogStatus.PASS,"Step : Available IPV 4 Commands is : " + clist.getText().toString());
					Log.info("Available IPV 4 Commands is :" + clist.getText().toString());
				}
				
				Thread.sleep(1000);
				
				// click on MAS_CommandIPV4 Dropdown
				WebElement MAS_CommandIPV4Dropdown = driver.findElement(By.xpath("//div[text()='" + MAS_CommandIPV4 + "']"));
				System.out.println("Selected IMS POP Location dropdowm is : " + MAS_CommandIPV4Dropdown.getText().toString());
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected IMS POP Location dropdowm is : " + MAS_CommandIPV4Dropdown.getText().toString());
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
					DriverTestcase.logger.log(LogStatus.PASS,"Step : Available IPV 6 Commands is : " + clist.getText().toString());
					Log.info("Available IPV 6 Commands is :" + clist.getText().toString());
				}
				
				Thread.sleep(1000);
				
				// click on MAS_CommandIPV4 Dropdown
				WebElement MAS_CommandIPV6Dropdown = driver.findElement(By.xpath("//div[text()='" + MAS_CommandIPV6 + "']"));
				System.out.println("Selected IMS POP Location dropdowm is : " + MAS_CommandIPV6Dropdown.getText().toString());
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected IMS POP Location dropdowm is : " + MAS_CommandIPV6Dropdown.getText().toString());
				Log.info("Selected IMS POP Location dropdowm is : " + MAS_CommandIPV6Dropdown.getText().toString());
				MAS_CommandIPV6Dropdown.click();
				Thread.sleep(2000);
				
				EnterTextValue(application, MAS_ManagementAddress, "Commands IPV6", "MAS_Router_IPV6CommandTextfield");
				click(application, "Execute button", "MAS_Router_IPV6Command_Executebutton");
				
}
	
	
	
	
	
	////
	public void verifyDeleteDeviceFunction_MAS(String application,String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		
		//Delete MAS Device from View Device Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header")));
		click(application, "View Link", "MAS_viewdevice1"); 
		
		click(application, "ACTION link", "MAS_View_ActionLink");
		click(application, "Delete Device from View Device page", "MAS_View_Action_DeleteLink");
		compareText(application, "Delete MAS Device Warning Message from View Device page", "MAS_ViewDevice_Action_DeleteMASDeviceWarningMessage", "Are you sure that you want to delete this item?");
		click(application, "Delete Button", "MAS_ViewDevice_Action_DeleteButton");
		Thread.sleep(3000);
		compareText(application, "Delete MAS Device Successful Message", "MAS_DeleteSwitchSuccessfulMessage", "MAS switch deleted successfully");
		
		
		//Delete from Service "From View Service page"
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_AddMASSwitchLink")));
		click(application, "Delete From Service Link", "MAS_deletefromservicedevice1");
		compareText(application, "Delete MAS Device Warning Message from View Service page", "MAS_ViewService_DeleteMASDeviceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "MAS_ViewService_DeleteButton");
		Thread.sleep(3000);
		compareText(application, "Delete MAS Device Successfull Message", "MAS_DeleteSwitchSuccessfulMessage", "MAS switch deleted successfully");
		
	}
	
	
	
	
	
	public void verifyAddInterfaceFunction_MAS(String application, String MAS_AccessMedia, String MAS_Network,
			String MAS_HSRPBGP, String MAS_GenerateConfiguration, String	MAS_Interface, String MAS_InterfaceAddressRange,
			String MAS_InterfaceAddressMask, String	MAS_HSRPIP, String	MAS_InterfaceAddressRangeIPV6, String MAS_HSRPIPv6Address, 
			String	MAS_PrimaryIPv6onMas1, String MAS_SecondaryIPv6onMas2, String MAS_GroupNumber, String MAS_Link, String MAS_VLANID,
			String	MAS_IVManagement, String MAS_Configuration, String MAS_HSRPTrackInterface, String MAS_HSRPAuthentication) throws InterruptedException, DocumentException, IOException {
		
		
//		WebElement ProviderEquipment_header= getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header"));
//		scrolltoview(ProviderEquipment_header);
//		click(application, "MAS View Link", "MAS_viewdevice1"); 
//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_BackButton_viewdevicepage")));
		
		click(application, "ACTION Link", "MAS_View_InterfacesActionLink");
		click(application, "Add Interface Link", "MAS_PE_AddInterfaceLink");
		compareText(application, "Add Interface Header", "MAS_PE_AddInterface_header", "Add Interface");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
		click(application, "OK Button", "MAS_PE_OKButton");
		
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface is required", "MAS_PE_InterfaceWarningMessage");
 
		
		
		if(MAS_AccessMedia.equalsIgnoreCase("VPN")) {
			EnterTextValue(application, MAS_Interface, "Interface textfield", "MAS_PE_InterfaceTextfield");
			EnterTextValue(application, MAS_InterfaceAddressRange, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield");
			EnterTextValue(application, MAS_InterfaceAddressMask, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield");
			//EnterTextValue(application, MAS_HSRPIP, "HSRP IP textfield", "MAS_PE_HSRPIPTextfield");
			EnterTextValue(application, MAS_InterfaceAddressRangeIPV6, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield");
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			EnterTextValue(application, MAS_HSRPIPv6Address, "HSRP IPv6 Address textfield", "MAS_PE_HSRPIPv6AddressTextfield");
			EnterTextValue(application, MAS_PrimaryIPv6onMas1, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield");
			EnterTextValue(application, MAS_SecondaryIPv6onMas2, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield");
			EnterTextValue(application, MAS_GroupNumber, "Group Number textfield", "MAS_PE_GroupNumberTextfield");
			EnterTextValue(application, MAS_Link, "Link textfield", "MAS_PE_LinkTextfield");
			EnterTextValue(application, MAS_VLANID, "Interface textfield", "MAS_PE_VLANIDTextfield");

			click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			

			EnterTextValue(application, MAS_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));

			click(application, "Select Generate Configuration value from dropdown", "//div[contains(text(),'"+MAS_GenerateConfiguration+"')]");

			
			//*EnterTextValue(application, MAS_Configuration, "Configuration textfield", "MAS_PE_ConfigurationTextfield");
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			DriverTestcase.logger.log(LogStatus.FAIL, "Interfaces sucessfully added by selecting VPN as Access Media");
			
		}else if(MAS_AccessMedia.equalsIgnoreCase("EPN")) {
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			click(application, "Select EPN as Access Media", "//div[text()='"+MAS_AccessMedia+"']");
			
			EnterTextValue(application, MAS_Interface, "Interface textfield", "MAS_PE_InterfaceTextfield");
			EnterTextValue(application, MAS_InterfaceAddressRange, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield");
			EnterTextValue(application, MAS_InterfaceAddressMask, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield");
			EnterTextValue(application, MAS_HSRPIP, "HSRP IP textfield", "MAS_PE_HSRPIPTextfield");
			EnterTextValue(application, MAS_InterfaceAddressRangeIPV6, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield");
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			EnterTextValue(application, MAS_HSRPIPv6Address, "HSRP IPv6 Address textfield", "MAS_PE_HSRPIPv6AddressTextfield");
			EnterTextValue(application, MAS_PrimaryIPv6onMas1, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield");
			EnterTextValue(application, MAS_SecondaryIPv6onMas2, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield");
			EnterTextValue(application, MAS_GroupNumber, "Group Number textfield", "MAS_PE_GroupNumberTextfield");
			EnterTextValue(application, MAS_Link, "Link textfield", "MAS_PE_LinkTextfield");
			EnterTextValue(application, MAS_VLANID, "Interface textfield", "MAS_PE_VLANIDTextfield");

			click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			
//			WebElement MAS_GenerateConfigurationSelect=driver.findElement(By.xpath("//div[div[text()='\"+MAS_GenerateConfiguration+\"']]"));
//			MAS_GenerateConfigurationSelect.click();
			click(application, "Select Generate Configuration value from dropdown", "//div[div[text()='"+MAS_GenerateConfiguration+"']]");
			EnterTextValue(application, MAS_GenerateConfiguration, "Configuration textfield", "MAS_PE_ConfigurationTextfield");
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			click(application, "OK Button", "MAS_PE_OKButton");
			
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			DriverTestcase.logger.log(LogStatus.FAIL, "Interfaces sucessfully added by selecting EPN as Access Media");
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
		
		
		
		Log.info("------ Interface Added successfully ------");
		
		
	}


	
	
	
	
	public void verifyEditInterfaceFunction_MAS(String application, String ServiceIdentification, String MAS_AccessMediaEdit, String MAS_NetworkEdit,
			String MAS_HSRPBGPEdit, String MAS_GenerateConfigurationEdit, String	MAS_InterfaceEdit, String MAS_InterfaceAddressRangeEdit,
			String MAS_InterfaceAddressMaskEdit, String	MAS_HSRPIPEdit, String	MAS_InterfaceAddressRangeIPV6Edit, String MAS_HSRPIPv6AddressEdit, 
			String	MAS_PrimaryIPv6onMas1Edit, String MAS_SecondaryIPv6onMas2Edit, String MAS_GroupNumberEdit, String MAS_LinkEdit, String MAS_VLANIDEdit,
			String	MAS_IVManagementEdit, String MAS_ConfigurationEdit, String MAS_HSRPTrackInterfaceEdit, String MAS_HSRPAuthenticationEdit) throws InterruptedException, DocumentException, IOException {
		
		/*Moveon(getwebelement(xml.getlocator("//locators/" + application + "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]")));
		*Thread.sleep(2000);
		*click(application, "Service ID on Top Of The Bredcome Navigation", "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]");*/
		
		scrolltoend();//Need to comment below 3 lines of code if want to edit existing added interface
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_BackButton_viewdevicepage")));
		click(application, "BACK button", "MAS_PE_BackButton_viewdevicepage"); 
		
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header")));
		click(application, "Show Interfaces Link", "MAS_ShowInterfaceLink"); 
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "MASSwitch_ACTION"); 
		click(application, "ACTION MAS Switch", "MAS_PE_ACTION_EditLink"); 
		
		
		
//		String MAS_AccessMedia=driver.findElement(By.xpath("//div[label[text()='Access Media']]//div//span")).getText();//div[@class='react-dropdown-select-content react-dropdown-select-type-single css-v1jrxw-ContentComponent e1gn6jc30'])[1]
//		System.out.println("Selected Access Media in Edit Interface page is :  "+MAS_AccessMedia);
		
		if(MAS_AccessMediaEdit.equalsIgnoreCase("VPN")) {
			ClearAndEnterTextValue(application, "Interface textfield", "MAS_PE_InterfaceTextfield", MAS_InterfaceEdit);
			ClearAndEnterTextValue(application, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield", MAS_InterfaceAddressRangeEdit);
			ClearAndEnterTextValue(application, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield", MAS_InterfaceAddressMaskEdit);
			//ClearAndEnterTextValue(application, "HSRP IP textfield", "MAS_PE_HSRPIPTextfield", MAS_HSRPIPEdit);
			ClearAndEnterTextValue(application, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield", MAS_InterfaceAddressRangeIPV6Edit);
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			ClearAndEnterTextValue(application, "HSRP IPv6 Address textfield", "MAS_PE_HSRPIPv6AddressTextfield", MAS_HSRPIPv6AddressEdit);
			ClearAndEnterTextValue(application,  "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield" , MAS_PrimaryIPv6onMas1Edit);
			ClearAndEnterTextValue(application, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield", MAS_SecondaryIPv6onMas2Edit);
			ClearAndEnterTextValue(application, "Group Number textfield", "MAS_PE_GroupNumberTextfield", MAS_GroupNumberEdit);
			ClearAndEnterTextValue(application, "Link textfield", "MAS_PE_LinkTextfield", MAS_LinkEdit);
			ClearAndEnterTextValue(application, "Interface textfield", "MAS_PE_VLANIDTextfield", MAS_VLANIDEdit);

			click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			

			EnterTextValue(application, MAS_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));

			click(application, "Select Generate Configuration value from dropdown", "//div[contains(text(),'"+MAS_GenerateConfigurationEdit+"')]");

			
			//*EnterTextValue(application, MAS_GenerateConfigurationEdit, "Configuration textfield", "MAS_PE_ConfigurationTextfield");
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully updated by selecting VPN as Access Media");
			
		
		
			
		}else if(MAS_AccessMediaEdit.equalsIgnoreCase("EPN")) {
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			click(application, "Select EPN as Access Media", "//div[text()='"+MAS_AccessMediaEdit+"']");
			
			EnterTextValue(application, MAS_InterfaceEdit, "Interface textfield", "MAS_PE_InterfaceTextfield");
			EnterTextValue(application, MAS_InterfaceAddressRangeEdit, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield");
			EnterTextValue(application, MAS_InterfaceAddressMaskEdit, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield");
			EnterTextValue(application, MAS_HSRPIPEdit, "HSRP IP textfield", "MAS_PE_HSRPIPTextfield");
			EnterTextValue(application, MAS_InterfaceAddressRangeIPV6Edit, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield");
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			EnterTextValue(application, MAS_HSRPIPv6AddressEdit, "HSRP IPv6 Address textfield", "MAS_PE_HSRPIPv6AddressTextfield");
			EnterTextValue(application, MAS_PrimaryIPv6onMas1Edit, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield");
			EnterTextValue(application, MAS_SecondaryIPv6onMas2Edit, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield");
			EnterTextValue(application, MAS_GroupNumberEdit, "Group Number textfield", "MAS_PE_GroupNumberTextfield");
			EnterTextValue(application, MAS_LinkEdit, "Link textfield", "MAS_PE_LinkTextfield");
			EnterTextValue(application, MAS_VLANIDEdit, "Interface textfield", "MAS_PE_VLANIDTextfield");

			click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			
//			WebElement MAS_GenerateConfigurationSelect=driver.findElement(By.xpath("//div[div[text()='\"+MAS_GenerateConfiguration+\"']]"));
//			MAS_GenerateConfigurationSelect.click();
			click(application, "Select Generate Configuration value from dropdown", "//div[div[text()='"+MAS_GenerateConfigurationEdit+"']]");
			EnterTextValue(application, MAS_GenerateConfigurationEdit, "Configuration textfield", "MAS_PE_ConfigurationTextfield");
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			click(application, "OK Button", "MAS_PE_OKButton");
			
			
			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			DriverTestcase.logger.log(LogStatus.FAIL, "Interfaces sucessfully updated by selecting EPN as Access Media");
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
	
		
		Log.info("------ Interface Updated successfully ------");
		
	}
		
		

	

	
	
	
	
	
	
	
	
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
	
	
	
	
	
	
	
	
	
	public void verifyDeleteInterfaceFunction_MAS(String application, String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		/*Moveon(getwebelement(xml.getlocator("//locators/" + application + "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]")));
		Thread.sleep(2000);
		click(application, "Service ID on Top Of The Bredcome Navigation", "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]");*/
		
		
//		scrolltoend();//Below 3 lines of code require if we try to move from view device page to view service page for configure
//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_BackButton_viewdevicepage")));
//		click(application, "BACK button", "MAS_PE_BackButton_viewdevicepage"); 
		
		
		//scrolltoend();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_BackButton_viewdevicepage")));
		click(application, "BACK button", "MAS_PE_BackButton_viewdevicepage"); 
		
		
		//Delete Interface - MAS Device from View Service Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header")));
		click(application, "Show Interfaces Link", "MAS_ShowInterfaceLink"); 
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "MASSwitch_ACTION");
		
		click(application, "Delete Button", "MAS_PE_ACTION_DeleteLink");  
				
		compareText(application, "Delete Interface Warning Message from View Service page", "MAS_PE_DeleteInterfaceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "MAS_PE_DeleteButton");
		Thread.sleep(3000);
		compareText(application, "Delete Interface Successfull Message", "MAS_PE_DeleteInterfaceSuccessfullMessage", "Interface deleted successfully");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////  PE DEVICE PANEL  ///////////////////////////////////////
	
	public void verifyAddPEDevice(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		
		
		click(application, "Add PE Device Link", "PE_AddPEDeviceLink");
		compareText(application, "Add PE Device header", "PE_AddPEDevice_header", "Add PE Device");

		click(application, "OK button", "PE_OKbutton");
		compareText(application, "Add Device Warning Message for PE Device", "PE_AddPEDeviceWarningMessage", "IMS POP Location is required");
		
		// Select  IMS POP Location dropdown
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PE_IMSPOPLocationDropdown")));
		List<WebElement> PE_IMSPOPLocationDropdown = driver.findElements(By.xpath("//div[@class='sc-htpNat AUGYd']/div"));
		for (WebElement clist : PE_IMSPOPLocationDropdown) {

			System.out.println("Available IMS POP Location name is : " + clist.getText().toString());
			DriverTestcase.logger.log(LogStatus.PASS,"Step : Available IMS POP Location name is : " + clist.getText().toString());
			Log.info("Available IMS POP Location name is :" + clist.getText().toString());
		}
		
		Thread.sleep(1000);
		
		// click on IMSPOPLocationDropdown
		WebElement IMSPOPLocationDropdown = driver.findElement(By.xpath("//div[contains(text(),'" + PE_IMSPOPLocation + "')]"));
		System.out.println("Selected IMS POP Location dropdowm is : " + IMSPOPLocationDropdown.getText().toString());
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected IMS POP Location dropdowm is : " + IMSPOPLocationDropdown.getText().toString());
		Log.info("Selected IMS POP Location dropdowm is : " + IMSPOPLocationDropdown.getText().toString());
		IMSPOPLocationDropdown.click();
		Thread.sleep(2000);
		
		
		click(application, "OK button", "PE_OKbutton");
		
		
		compareText(application, "Add Device Successful Message for PE Device", "PE_AddPEDeviceSuccessfulMessage", "PE Device added successfully");
		
		Log.info("------ PE Device added successfully ------");
	}

	
	
	
	
	
	
	public void verifyAddedPEDeviceInformation_View(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		
		click(application, "PE View Link", "PE_viewdevice1");
		
		//Thread.sleep(5000);
		GetText(application, "Device", "PE_ViewDevice_Header");//Device//Last Modified on :2020-05-14 07:18 GMT, Modified By :colttest@colt.net, Sync Status: sync in progress


		// Verify All Device Information under Device panel for PE Device
		GetText(application, "Device Name", "PE_View_DeviceNameValue");
		GetText(application, "Vendor/Model", "PE_View_VendorModelValue");
		GetText(application, "Management Address", "PE_View_ManagementAddressValue");
		GetText(application, "Snmpro", "PE_View_SnmproValue");
		GetText(application, "Country", "PE_View_CountryValue");
		GetText(application, "City", "PE_View_CityValue");
		GetText(application, "Site", "PE_View_SiteValue");
		GetText(application, "Premise", "PE_View_PremiseValue");
		GetText(application, "Test", "PE_View_TestColumnName");
		GetText(application, "Status", "PE_View_StatusColumnName");
		GetText(application, "Last Refresh", "PE_View_LastRefresh");
		
		
//		click(application, "ACTION link", "PE_View_ActionLink");
//		//Verify List of links under Action
//		GetText(application, "Edit Link", "PE_View_Action_EditLink");
//		GetText(application, "Delete Link", "PE_View_Action_DeleteLink");
//		GetText(application, "Fetch Device Interfaces Link", "PE_View_Action_FetchDeviceInterfacesLink");
//		click(application, "ACTION link", "PE_View_ActionLink");
		
		Log.info("------ Verified Added PE Device Information successfully ------");
	}
	
	
	
	
	public void verifyEditPEDeviceFunction(String application) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service

//		click(application, "Edit Link", "PE_View_Action_EditLink");
//		//**EnterTextValue(application, Site textfield, "Site", "Sitetextarea");
//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_Edit_PremiseLevel")));
//		//click(application, "Cancel in Edit MAS Switch Page", "MAS_Cancelbutton");//Not working
//		Thread.sleep(3000);
		
		click(application, "ACTION link", "PE_View_ActionLink");
		click(application, "Edit Link", "PE_View_Action_EditLink");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_Edit_PremiseLevel")));
		click(application, "OK in Edit PE Device", "PE_OKbutton");
		Thread.sleep(5000);
		compareText(application, "PE Device Update message", "PE_UpdatePEDeviceSuccessfulMessage", "PE Device updated successfully");

		Log.info("------  PE Device updated successfully   ------");
	}

	
	
	
	
	
public void verifFetchDeviceInterfacesFunction_PE(String application,String ServiceIdentification, String PE_ServiceStatusChangeRequired) throws InterruptedException, DocumentException, IOException {

		
		click(application, "ACTION link", "PE_View_ActionLink");
		click(application, "Fetch Device Interfaces Link", "PE_View_Action_FetchDeviceInterfacesLink");
		compareText(application, "Fetch Interfaces started successfully Message for MAS", "PE_FetchDeviceInterfacesSuccessMessage", " Fetch Interfaces started successfully. Please check the sync status of this device  here");
		
		click(application, "Click here Link for PE", "PE_hereLink_UnderFetchDeviceInterfacesSuccessMessage");

		Thread.sleep(2000);
		
		//Manage COLT's Network - Manage Network
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_Manage_Synchronization_SynchronizeLink")));
		
		compareText(application, "Manage COLT's Network - Manage Network header", "PE_ManageCOLTsNetworkManageNetwork_header", "Manage COLT's Network - Manage Network");
		GetText(application, "Device Name in Manage Colt page under Status Panel", "PE_Manage_Status_DeviceValue");
		GetText(application, "Status in Manage Colt page", "PE_Manage_Status_StatusValue");
		GetText(application, "Last Modification in Manage Colt page", "PE_Manage_Status_LastModificationValue");
		GetText(application, "Status Link in Manage Colt page", "PE_Manage_Status_StatusLink");
		GetText(application, "View Interface Link in Manage Colt page", "PE_Manage_Status_ViewInterfacesLink");
		
		GetText(application, "Device Name in Manage Colt page under Synchronization Panel", "PE_Manage_Synchronization_DeviceValue");
		GetText(application, "Sync Status in Manage Colt page", "PE_Manage_Synchronization_SyncStatusValue");
		GetText(application, "Smarts in Manage Colt page", "PE_Manage_Synchronization_SmartsValue");
		GetText(application, "Fetch Interfaces in Manage Colt page", "PE_Manage_Synchronization_FetchInterfacesValue");
		GetText(application, "VistaMart Device in Manage Colt page", "PE_Manage_Synchronization_VistaMartDeviceValue");
		GetText(application, "Synchronize Link in Manage Colt page", "PE_Manage_Synchronization_SynchronizeLink");

		
		scrollToTop();
		String PE_Manage_Status_LastModificationValue= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PE_Manage_Status_LastModificationValue")));
		if(PE_Manage_Status_LastModificationValue.contains("GMT"))
		{
			Log.info("Service status is displayed as : " + PE_Manage_Status_LastModificationValue);
			System.out.println("Service status is :"+ PE_Manage_Status_LastModificationValue);
		}
		else
		{
			Log.info("Incorrect modification time format");
			System.out.println("Incorrect modification time format");
		}
		
		
		////
		click(application, "Status", "PE_Manage_Status_StatusLink");

		if(PE_ServiceStatusChangeRequired=="Yes")
		{
			WebElement ServiceStatusPage= getwebelement(xml.getlocator("//locators/" + application + "/PE_Manage_Status_Servicestatus_popup"));
			if(ServiceStatusPage.isDisplayed())
			{
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_Device_Status_OK")));
				click(application, "Click on OK to change Status", "PE_Device_Status_OK");

				WebElement PE_servicestatushistoryValue= getwebelement(xml.getlocator("//locators/" + application + "/PE_servicestatushistoryValue"));
				try
				{
					if(PE_servicestatushistoryValue.isDisplayed())
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request logged");
					}
					else
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request is not logged");
				}
				catch(StaleElementReferenceException e)
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "No service history to display");
				}
			}
			else
				DriverTestcase.logger.log(LogStatus.FAIL, "Status link is not working");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change not reqired");
			click(application, "Close", "PE_servicestatus_popupclose");
		}

		
		
		////synchronize panel in manage colt page
			scrolltoend();
			click(application, "Synchronize", "PE_Manage_Synchronization_SynchronizeLink");
			compareText(application, "Synchronize Success Message", "PE_Manage_SynchronizeSuccessMessage", "Sync started successfully. Please check the sync status of this device.");
				
//			//**scrollToTop();
//			//**click(application, "Service ID on Top Of The Bredcome Navigation", "//li/a[text()='"+ServiceIdentification+"");


		//Delete Device will performed in the last test case
		
	}
	
	
	
	
	
	////
	
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
					DriverTestcase.logger.log(LogStatus.PASS,"Step : Available IPV 4 Commands is : " + clist.getText().toString());
					Log.info("Available IPV 4 Commands is :" + clist.getText().toString());
				}
				
				Thread.sleep(1000);
				
				// click on MAS_CommandIPV4 Dropdown
				WebElement PE_CommandIPV4Dropdown = driver.findElement(By.xpath("//div[text()='" + PE_CommandIPV4 + "']"));
				System.out.println("Selected IMS POP Location dropdowm is : " + PE_CommandIPV4Dropdown.getText().toString());
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected IMS POP Location dropdowm is : " + PE_CommandIPV4Dropdown.getText().toString());
				Log.info("Selected IMS POP Location dropdowm is : " + PE_CommandIPV4Dropdown.getText().toString());
				PE_CommandIPV4Dropdown.click();
				Thread.sleep(2000);
				
				EnterTextValue(application, PE_ManagementAddress, "Commands IPV4", "PE_Router_IPV4CommandTextfield");
				click(application, "Execute button", "PE_Router_IPV4Command_Executebutton");
				
}
	
	
	
	
	
	
	
	

	public void verifyAddInterfaceFunction_PE(String application, String PE_AccessMedia, String PE_Network,
			String PE_VRRPBGP, String PE_GenerateConfiguration, String	PE_Interface, String PE_InterfaceAddressRange,
			String PE_InterfaceAddressMask, String	PE_VRRPIP, String	PE_InterfaceAddressRangeIPV6, String PE_VRRPIPv6Address, 
			String	PE_PrimaryIPv6onMas1, String PE_SecondaryIPv6onMas2, String PE_GroupNumber, String PE_Link, String PE_VLANID, String PE_VRRPGroupName , String PE_VRF,
			String	PE_IVManagement, String PE_Configuration, String PE_VRRPTrackInterface, String PE_VRRPAuthentication) throws InterruptedException, DocumentException, IOException {
		
		//Below 3 lince code required for to start execution from View Device page 
		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		click(application, "PE View Link", "PE_viewdevice1"); 
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_BackButton_viewdevicepage")));
		
		click(application, "ACTION Link", "PE_View_InterfacesActionLink");
		click(application, "Add Interface Link", "MAS_PE_AddInterfaceLink");
		compareText(application, "Add Interface Header", "MAS_PE_AddInterface_header", "Add Interface");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
		click(application, "OK Button", "MAS_PE_OKButton");
		
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface Suggestion Message", "PE_InterfaceSuggestionMessage");
		
		
		
		if(PE_AccessMedia.equalsIgnoreCase("VPN")) {
			EnterTextValue(application, PE_InterfaceAddressRange, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield");
			EnterTextValue(application, PE_InterfaceAddressMask, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield");
			EnterTextValue(application, PE_VRRPIP, "VRRP IP textfield", "MAS_PE_VRRPIPTextfield");
			EnterTextValue(application, PE_InterfaceAddressRangeIPV6, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield");
			
			EnterTextValue(application, PE_PrimaryIPv6onMas1, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield");
			EnterTextValue(application, PE_SecondaryIPv6onMas2, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield");
			EnterTextValue(application, PE_GroupNumber, "Group Number textfield", "MAS_PE_GroupNumberTextfield");
			EnterTextValue(application, PE_Link, "Link textfield", "MAS_PE_LinkTextfield");
			EnterTextValue(application, PE_VLANID, "Interface textfield", "MAS_PE_VLANIDTextfield");
			//*EnterTextValue(application, PE_VRRPGroupName, "VRRP-Group Name textfield", "MAS_PE_VRRPGroupNameTextfield");//Bydefault
			EnterTextValue(application, PE_VRF, "VRF textfield", "MAS_PE_VRFTextfield");

			click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			
			EnterTextValue(application, PE_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			//*scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));

			click(application, "Select Generate Configuration value from dropdown", "//div[contains(text(),'"+PE_GenerateConfiguration+"')]");
			
			Thread.sleep(3000);
			//*EnterTextValue(application, MAS_Configuration, "Configuration textfield", "MAS_PE_ConfigurationTextfield");
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully added by selecting VPN as Access Media");
			
		}else if(PE_AccessMedia.equalsIgnoreCase("EPN")) {
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			click(application, "Select EPN as Access Media", "//div[text()='"+PE_AccessMedia+"']");
			
			EnterTextValue(application, PE_InterfaceAddressRange, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield");
			EnterTextValue(application, PE_InterfaceAddressMask, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield");
			EnterTextValue(application, PE_VRRPIP, "VRRP IP textfield", "MAS_PE_VRRPIPTextfield");
			EnterTextValue(application, PE_InterfaceAddressRangeIPV6, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield");
			EnterTextValue(application, PE_VRRPIPv6Address, "VRRP IPv6 Address textfield", "MAS_PE_VRRPIPv6AddressTextfield");

			EnterTextValue(application, PE_PrimaryIPv6onMas1, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield");
			EnterTextValue(application, PE_SecondaryIPv6onMas2, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield");
			EnterTextValue(application, PE_GroupNumber, "Group Number textfield", "MAS_PE_GroupNumberTextfield");
			EnterTextValue(application, PE_Link, "Link textfield", "MAS_PE_LinkTextfield");
			EnterTextValue(application, PE_VLANID, "Interface textfield", "MAS_PE_VLANIDTextfield");
			//*EnterTextValue(application, PE_VRRPGroupName, "VRRP-Group Name textfield", "MAS_PE_VRRPGroupNameTextfield");//Bydefault
			EnterTextValue(application, PE_VRF, "VRF textfield", "MAS_PE_VRFTextfield");

			click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			
			EnterTextValue(application, PE_VRRPTrackInterface, "VRRP Track Interface textfield", "MAS_PE_VRRPTrackInterfaceTextfield");
			EnterTextValue(application, PE_VRRPAuthentication, "VRRP Authentication textfield", "MAS_PE_VRRPAuthenticationTextfield");
			
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			
			EnterTextValue(application, PE_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			//*scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));

			click(application, "Select Generate Configuration value from dropdown", "//div[contains(text(),'"+PE_GenerateConfiguration+"')]");
			
			Thread.sleep(3000);
			//*EnterTextValue(application, MAS_Configuration, "Configuration textfield", "MAS_PE_ConfigurationTextfield");
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			DriverTestcase.logger.log(LogStatus.FAIL, "Interfaces sucessfully added by selecting EPN as Access Media");			
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
		
		
		
		Log.info("------ Interface Added successfully ------");
		
		
	}


	
	
	
	public void verifyEditInterfaceFunction_PE(String application, String ServiceIdentification, String PE_AccessMediaEdit, String PE_NetworkEdit,
			String PE_VRRPBGPEdit, String PE_GenerateConfigurationEdit, String	PE_InterfaceEdit, String PE_InterfaceAddressRangeEdit,
			String PE_InterfaceAddressMaskEdit, String	PE_VRRPIPEdit, String	PE_InterfaceAddressRangeIPV6Edit, String PE_VRRPIPv6AddressEdit, 
			String	PE_PrimaryIPv6onMas1Edit, String PE_SecondaryIPv6onMas2Edit, String PE_GroupNumberEdit, String PE_LinkEdit, String PE_VLANIDEdit, String PE_VRRPGroupNameEdit, String PE_VRFEdit,
			String	PE_IVManagementEdit, String PE_ConfigurationEdit, String PE_VRRPTrackInterfaceEdit, String PE_VRRPAuthenticationEdit) throws InterruptedException, DocumentException, IOException {
		
		/*Moveon(getwebelement(xml.getlocator("//locators/" + application + "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]")));
		*Thread.sleep(2000);
		*click(application, "Service ID on Top Of The Bredcome Navigation", "//li[3]/span[contains(text(),'"+ServiceIdentification+"')]");*/
		
		scrolltoend();//Need to comment below 3 lines of code if want to edit existing added interface
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_BackButton_viewdevicepage")));
		click(application, "BACK button", "MAS_PE_BackButton_viewdevicepage"); 
		
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "Show Interfaces Link", "PE_showInterfacesLink"); 
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "PE_ACTION"); 
		click(application, "ACTION MAS Switch", "MAS_PE_ACTION_EditLink"); 
		
		
		
//		String MAS_AccessMedia=driver.findElement(By.xpath("//div[label[text()='Access Media']]//div//span")).getText();//div[@class='react-dropdown-select-content react-dropdown-select-type-single css-v1jrxw-ContentComponent e1gn6jc30'])[1]
//		System.out.println("Selected Access Media in Edit Interface page is :  "+MAS_AccessMedia);
		
		if(PE_AccessMediaEdit.equalsIgnoreCase("VPN")) {
			ClearAndEnterTextValue(application, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield", PE_InterfaceAddressRangeEdit);
			ClearAndEnterTextValue(application, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield", PE_InterfaceAddressMaskEdit);
			ClearAndEnterTextValue(application, "VRRP IP textfield", "MAS_PE_VRRPIPTextfield", PE_VRRPIPEdit);
			ClearAndEnterTextValue(application, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield", PE_InterfaceAddressRangeIPV6Edit);
			
			ClearAndEnterTextValue(application, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield", PE_PrimaryIPv6onMas1Edit);
			ClearAndEnterTextValue(application, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield", PE_SecondaryIPv6onMas2Edit);
			ClearAndEnterTextValue(application, "Group Number textfield", "MAS_PE_GroupNumberTextfield", PE_GroupNumberEdit);
			ClearAndEnterTextValue(application, "Link textfield", "MAS_PE_LinkTextfield", PE_LinkEdit);
			ClearAndEnterTextValue(application, "VLAN ID textfield", "MAS_PE_VLANIDTextfield", PE_VLANIDEdit);
			ClearAndEnterTextValue(application, "VRRP Group textfield", "MAS_PE_VRRPGroupNameTextfield", PE_VRRPGroupNameEdit);
			ClearAndEnterTextValue(application, "VRF textfield", "MAS_PE_VRFTextfield", PE_VRFEdit);

			click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			
			EnterTextValue(application, PE_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			//*scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));

			click(application, "Select Generate Configuration value from dropdown", "//div[contains(text(),'"+PE_GenerateConfigurationEdit+"')]");
			
			Thread.sleep(3000);
			//*EnterTextValue(application, MAS_Configuration, "Configuration textfield", "MAS_PE_ConfigurationTextfield");
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
						
			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully updated by selecting VPN as Access Media");
			
			
		}else if(PE_AccessMediaEdit.equalsIgnoreCase("EPN")) {
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			click(application, "Select EPN as Access Media", "//div[text()='"+PE_AccessMediaEdit+"']");
			
			ClearAndEnterTextValue(application, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield", PE_InterfaceAddressRangeEdit);
			ClearAndEnterTextValue(application, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield", PE_InterfaceAddressMaskEdit);
			ClearAndEnterTextValue(application, "VRRP IP textfield", "MAS_PE_VRRPIPTextfield", PE_VRRPIPEdit);
			ClearAndEnterTextValue(application, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield", PE_InterfaceAddressRangeIPV6Edit);
			ClearAndEnterTextValue(application, "VRRP IPV6 Address textfield", "MAS_PE_VRRPIPv6AddressTextfield", PE_VRRPIPv6AddressEdit);
			ClearAndEnterTextValue(application, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield", PE_PrimaryIPv6onMas1Edit);
			ClearAndEnterTextValue(application, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield", PE_SecondaryIPv6onMas2Edit);
			ClearAndEnterTextValue(application, "Group Number textfield", "MAS_PE_GroupNumberTextfield", PE_GroupNumberEdit);
			ClearAndEnterTextValue(application, "Link textfield", "MAS_PE_LinkTextfield", PE_LinkEdit);
			ClearAndEnterTextValue(application, "VLAN ID textfield", "MAS_PE_VLANIDTextfield", PE_VLANIDEdit);
			ClearAndEnterTextValue(application, "VRRP Group textfield", "MAS_PE_VRRPGroupNameTextfield", PE_VRRPGroupNameEdit);
			ClearAndEnterTextValue(application, "VRF textfield", "MAS_PE_VRFTextfield", PE_VRFEdit);

			click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			ClearAndEnterTextValue(application, "VRRP Track Interface textfield", "MAS_PE_VRRPTrackInterfaceTextfield", PE_VRRPTrackInterfaceEdit);
			ClearAndEnterTextValue(application, "VRRP Authentication textfield", "MAS_PE_VRRPAuthenticationTextfield", PE_VRRPAuthenticationEdit);
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			
			EnterTextValue(application, PE_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			//*scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));

			click(application, "Select Generate Configuration value from dropdown", "//div[contains(text(),'"+PE_GenerateConfigurationEdit+"')]");
			
			Thread.sleep(3000);
			//*EnterTextValue(application, MAS_Configuration, "Configuration textfield", "MAS_PE_ConfigurationTextfield");
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			DriverTestcase.logger.log(LogStatus.FAIL, "Interfaces sucessfully updated by selecting EPN as Access Media");
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
	
		
		Log.info("------ Interface Updated successfully ------");
		
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
	
	
	
	public void addTrunkGroupSiteOrderNumber(String application, String TrunkGroupOrderCheckboxStatus, String TrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException { 
		
		boolean TrunkGroupSiteOrderPanel_header=false;
		boolean addtrunkSiteorderPage_panelheader=false;
		boolean trunkgrupOrderErrMsg= false;
		
		scrolltoend();
		Thread.sleep(2000);
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_AddPEDeviceLink")));
		TrunkGroupSiteOrderPanel_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrderPanel_header")).isDisplayed();
		if(TrunkGroupSiteOrderPanel_header) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");
			System.out.println("'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");
			
			
		//Perform Add Site order
			click_commonMethod(application, "Add TrunkGroup/SiteOrder link", "addTrunkSiteOrderlink", xml);
			
			addtrunkSiteorderPage_panelheader= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
			if(addtrunkSiteorderPage_panelheader) {
				
				DriverTestcase.logger.log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is displaying as expected");
				System.out.println("'Add Trunk Group/Site Order' page is displaying as expected");
				
			//verify mandatory Warning Message
				click_commonMethod(application, "OK", "trunk_okButton", xml);
				
				warningMessage_commonMethod(application, "trunkGrouporder_warningmsg", "Trunk Group Order", xml);
				
				warningMessage_commonMethod(application, "trunkGroupOrderName_warningmsg", "Trunk group Order Number", xml);
				
			//Add trunkgroup Order checkbox
				if(TrunkGroupOrderCheckboxStatus.equalsIgnoreCase("yes")) {
					
					addCheckbox_commonMethod(application, "trunkGroupOrder_checkbox", "Trunk Group Order", TrunkGroupOrderCheckboxStatus, "No", xml);
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " ' Trunk Group order' checkbox is a mandatory field. No values passed");
					System.out.println(" ' Trunk Group order' checkbox is a mandatory field. No values passed");

				}
				
			//Add Trunk Group order Number text field
				addtextFields_commonMethod(application, "Trunk Group Order Number", "trunkGroupOrderName_textField", TrunkGroupSiteOrderNumber, xml);
				
				
			//Click on OK button
				click_commonMethod(application, "OK", "trunk_okButton", xml);
				Thread.sleep(3000);
				
				try {
				trunkgrupOrderErrMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).isDisplayed();
				String actualMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).getText();
				if(trunkgrupOrderErrMsg) {
					if(actualMsg.contains("1.trunkgroup number already exists")) {
						
						DriverTestcase.logger.log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
						System.out.println(" Error message we are getting as: "+ actualMsg);
					}
					else if(actualMsg.equalsIgnoreCase("Trunk Group created successfully")) {
						
						DriverTestcase.logger.log(LogStatus.PASS, " Success Message displays as 'Trunk Group created successfully'");
						System.out.println(" Success Message displays as 'Trunk Group created successfully'");
					}
					
				}
				}catch(Exception e) {
					e.printStackTrace();
					
				}
				
				
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is not displaying");
				System.out.println("'Add Trunk Group/Site Order' page is not displaying");
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
			System.out.println("'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
		}
	}
	
	
	
	
	
	
	
	
public void editTrunkGroupSiteOrderNumber(String application, String TrunkGroupSiteOrderNumber,  String TrunkGroupOrderCheckboxStatusEdit, String TrunkGroupSiteOrderNumberEdit) throws InterruptedException, DocumentException, IOException {
		
		boolean addtrunkSiteorderPage_panelheader=false;
		boolean trunkgrupOrderErrMsg=false;
		
		scrolltoend();
		Thread.sleep(3000);
		
		WebElement editlink=getwebelement("//div[div[span[text()='"+ TrunkGroupSiteOrderNumber +"']]]//span[text()='Edit']");
		Clickon(editlink);
		
		addtrunkSiteorderPage_panelheader= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
		if(addtrunkSiteorderPage_panelheader) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is displaying as expected");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying as expected");
			
		//Trunk group Order
			if(TrunkGroupOrderCheckboxStatusEdit.equalsIgnoreCase("no")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Trunk Group Order' is a mandatory field. It cannot be unselected");
				System.out.println("'Trunk Group Order' is a mandatory field. It cannot be unselected");
			}else {
				editcheckbox_commonMethod(application, TrunkGroupOrderCheckboxStatusEdit, "trunkGroupOrder_checkbox", "Trunk Group Order", xml);
			}
			
			
		//Trunk Group Order Number
			//edittextFields_commonMethod(application, "Trunk Group Order Number", "trunkGroupOrderName_textField", edit_TrunkGroupOrderNumber, xml);
			ClearAndEnterTextValue(application, "Trunk Group Order Number Edit", "trunkGroupOrderName_textField", TrunkGroupSiteOrderNumberEdit);
			
		//Click on OK button
			click_commonMethod(application, "OK", "trunk_okButton", xml);
			Thread.sleep(3000);
			
			try {
			trunkgrupOrderErrMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).isDisplayed();
			String actualMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).getText();
			if(trunkgrupOrderErrMsg) {
				if(actualMsg.contains("1.trunkgroup number already exists")) {
					
					DriverTestcase.logger.log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
					System.out.println(" Error message we are getting as: "+ actualMsg);
				}
				else if(actualMsg.contains("Trunk Group successfully updated")) {
					
					DriverTestcase.logger.log(LogStatus.PASS, " Success Message displays as 'Trunk Group successfully updated.'");
					System.out.println(" Success Message displays as 'Trunk Group successfully updated.'");
				}
				
			}
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
			
			
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is not displaying");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying");
		}
	}
	
	
	





	
	
	
	
	
	
	public void editSiteOrder_OLD(String application, String siteOrderName,  String trunkGroupOrder, String trunkGrouporderNumber) throws InterruptedException, DocumentException, IOException {
		
		boolean siteOrderpage=false;
		boolean trunkgrupOrderErrMsg=false;
		
		scrolltoend();
		Thread.sleep(3000);
		
		WebElement editlink=getwebelement("//div[div[span[text()='"+ siteOrderName +"']]]//span[text()='Edit']");
		Clickon(editlink);
		
		siteOrderpage= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
		if(siteOrderpage) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is displaying as expected");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying as expected");
			
		//Trunk group Order
			if(trunkGroupOrder.equalsIgnoreCase("no")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Trunk Group Order' is a mandatory field. It cannot be unselected");
				System.out.println("'Trunk Group Order' is a mandatory field. It cannot be unselected");
			}else {
				editcheckbox_commonMethod(application, trunkGroupOrder, "trunkGroupOrder_checkbox", "Trunk Group Order", xml);
			}
			
			
		//Trunk Group Order Number
			edittextFields_commonMethod(application, "Trunk Group Order Number", "trunkGroupOrderName_textField", trunkGrouporderNumber, xml);
			
			
		//Click on OK button
			click_commonMethod(application, "OK", "trunk_okButton", xml);
			Thread.sleep(3000);
			
			try {
			trunkgrupOrderErrMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).isDisplayed();
			String actualMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).getText();
			if(trunkgrupOrderErrMsg) {
				if(actualMsg.contains("1.trunkgroup number already exists")) {
					
					DriverTestcase.logger.log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
					System.out.println(" Error message we are getting as: "+ actualMsg);
				}
				else if(actualMsg.contains("Trunk Group successfully updated")) {
					
					DriverTestcase.logger.log(LogStatus.PASS, " Success Message displays as 'Trunk Group successfully updated.'");
					System.out.println(" Success Message displays as 'Trunk Group successfully updated.'");
				}
				
			}
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
			
			
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is not displaying");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying");
		}
	}
	
	
	
	
	
	
	public void deleteTrunkGroupSiteOrderNumber(String application, String TrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException {
		scrolltoend();
		Thread.sleep(1000);
		
		boolean TrunkGroupSiteOrderNumberText=false;
		//boolean ViewService_Trunk_DeleteSiteOrderSuccessMessage=false;
		
		WebElement deletelink=getwebelement("//div[div[span[text()='"+ TrunkGroupSiteOrderNumber +"']]]//span[text()='Delete']");
		
		TrunkGroupSiteOrderNumberText=getwebelement("//span[text()='"+ TrunkGroupSiteOrderNumber +"']").isDisplayed();
		
		WebElement ViewService_Trunk_DeleteSiteOrderSuccessMessage=getwebelement("ViewService_Trunk_DeleteSiteOrderSuccessMessage");
	
   if(TrunkGroupSiteOrderNumberText){
			
			DriverTestcase.logger.log(LogStatus.PASS, TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			
			
			
			
			if(deletelink.isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'Delete Link in Trunk Group/Site Order' page is displaying as expected");
				System.out.println("'Delete Link Trunk Group/Site Order' page is displaying as expected");
				
			//Trunk group Order
				deletelink.click();
				compareText(application, "Delete Site Order warning message", "ViewService_Trunk_DeleteSiteOrderWarningMessage", "Are you sure that you want to delete this item?");
				click(application, "Delete Button", "ViewService_Trunk_DeleteButton");
				
				
				if(ViewService_Trunk_DeleteSiteOrderSuccessMessage.isDisplayed()) {
					DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group Site Order' Deleted Successfully");
					System.out.println("'Trunk Group Site Order' Deleted Successfully");
																					}else {
					DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group Site Order' not Deleted");
					System.out.println("'Trunk Group Site Order' not Deleted");
																						   } 
		    							}else {
		    		DriverTestcase.logger.log(LogStatus.PASS, "'Delete Link in Trunk Group/Site Order' page is not displaying as expected");
		    		System.out.println("'Delete Link Trunk Group/Site Order' page is not  displaying as expected");
		    							}
			
			
			
							}else {
			DriverTestcase.logger.log(LogStatus.FAIL, TrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
								  }
}
	
	
	
	
	
	
	
	
	
	
	public void deleteUpdatedTrunkGroupSiteOrderNumber(String application,   String TrunkGroupSiteOrderNumberEdit) throws InterruptedException, DocumentException, IOException {
		scrolltoend();
		Thread.sleep(1000);
		
		boolean TrunkGroupSiteOrderNumberUpdatedText=false;
		//boolean ViewService_Trunk_DeleteSiteOrderSuccessMessage=false;
		
		WebElement deleteLink=getwebelement("//div[div[span[text()='"+ TrunkGroupSiteOrderNumberEdit +"']]]//span[text()='Delete']");
		
		TrunkGroupSiteOrderNumberUpdatedText=getwebelement("//span[text()='"+ TrunkGroupSiteOrderNumberEdit +"']").isDisplayed();
		
		WebElement ViewService_Trunk_DeleteSiteOrderSuccessMessage=getwebelement("ViewService_Trunk_DeleteSiteOrderSuccessMessage");

		
	if(TrunkGroupSiteOrderNumberUpdatedText){
			
			DriverTestcase.logger.log(LogStatus.PASS, TrunkGroupSiteOrderNumberEdit + " 'Site Order' is displaying under 'Trunk Group/Site Orders' panel");
			System.out.println(TrunkGroupSiteOrderNumberEdit + " 'Site Order' is displaying under 'Trunk Group/Site Orders' panel");
			
			
			if(deleteLink.isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'Delete Link in Trunk Group/Site Order' page is displaying as expected");
				System.out.println("'Delete Link Trunk Group/Site Order' page is displaying as expected");
				
			//Trunk group Order
				deleteLink.click();
				compareText(application, "Delete Site Order warning message", "ViewService_Trunk_DeleteSiteOrderWarningMessage", "Are you sure that you want to delete this item?");
				click(application, "Delete Button", "ViewService_Trunk_DeleteButton");
				
				
				if(ViewService_Trunk_DeleteSiteOrderSuccessMessage.isDisplayed()) {
					DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group Site Order' Deleted Successfully");
					System.out.println("'Trunk Group Site Order' Deleted Successfully");
																					}else {
					DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group Site Order' not Deleted");
					System.out.println("'Trunk Group Site Order' not Deleted");
																						   } 
		    							}else {
		    		DriverTestcase.logger.log(LogStatus.PASS, "'Delete Link in 'Trunk Group/Site Order' panel is not displaying as expected");
		    		System.out.println("'Delete Link in Trunk Group/Site Order' panel is not  displaying as expected");
		    							}
			 
			
			
							}else {
			DriverTestcase.logger.log(LogStatus.FAIL, TrunkGroupSiteOrderNumberEdit + " 'Site Order' is not displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumberEdit + " 'Site Order' is not displaying under 'Trunk' panel");
								  }
	}

	
	
	
	
	
	
	
	
	
	
	
	public void verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel(String application, String ExistingTrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(1000);
		
		boolean siteOrderUnderTrunkPanel=false;
		
		
		siteOrderUnderTrunkPanel=getwebelement("//span[text()='"+ ExistingTrunkGroupSiteOrderNumber +"']").isDisplayed();
		
		if(siteOrderUnderTrunkPanel) {
			
			DriverTestcase.logger.log(LogStatus.PASS, ExistingTrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			System.out.println(ExistingTrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			
		//Click on Add trunk link	
			String addTunklinkXpath="//div[div[span[text()='"+ ExistingTrunkGroupSiteOrderNumber +"']]]/following-sibling::div//span[text()='Add Trunk']";
			click_commonMethod_PassingWebelementDirectly(application, "Add Trunk", addTunklinkXpath, xml);
			Thread.sleep(1000);
			
									}else {
			DriverTestcase.logger.log(LogStatus.FAIL, ExistingTrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
			System.out.println(ExistingTrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
										 }
									}

	
	
	
	
	
	public void addTrunk(String application, String customerName, String servicename, String trunkType, String voipProtocol, String country, String CDRdelivery, String gateway, String quality,
			String trafficDirection, String ipAddresstype, String SIPsignallingPort, String internetBasedCustomer, String vlanTag, String subInterfaceSlot,
			String signallngZone, String signallingtransportProtocol, String coltSignalingIP, String mediaIP, String reuseNIFgroup, String reuseSigZonePart,
			String callAdmissionControl, String callrateLimit, String sourceAddressFiltering, String relSupport, String sipSessionkeepAliveTimer,
			String retryInvite, String routePriority, String addressReachability, String carrierIPoriginating, String carrierIPterminating, String TLSfield,
			String srtp, String prefix, String globalProfile_ExistingSelection, String globalProfile_newSelection, String globalProfile_ExistingValue, String globalProfile_newValue,
			String localProfile_existingFieldSelection, String localProfile_newFieldSelection, String localProfile_existingvalue, String localProfile_newvalue,
			String COSprofile_existingFieldSelection, String COSprofile_newFieldSelection, String COSprofile_existingValue, String COSprofile_newValue,
			String PSPGname_existingFieldSelection, String PSPGname_newFieldSelection,String pspgName_existingValue, String pspgName_newValue,
			String prefferedPSP_existingFieldSelection, String prefferedPSP_newFieldSelection,String preferredPSP_exitingvalue, String preferredPSP_newvalue,
			String carrier_existingFieldSelection, String carrier_newFieldSelection, String carrier_existingValue, String carrier_newValue,
			String IPsignallingProfile_existingFieldSelection, String IPsignallingProfile_newFieldSelection, String IPsignallingProfile_existingValue, String IPsignallingProfile_newValue,
			String EgressIpsignal_existingFieldSelection,String EgressIpsignal_newFieldSelection, String EgressIPsignal_existingValue, String EgressIPsignal_newValue,
			String InDMPMrule_existingFieldSelection, String InDMPMrule_newFieldSelection, String InDMPMrule_existingValue, String InDMPMrule_newValue,
			String OutDMPMrule_existingFieldSelection, String OutDMPMrule_newFieldSelection, String OutDMPMrule_existingValue, String OutDMPMrule_newValue,
			String featureControlprofile_existingFieldSelection,String featureControlprofile_newFieldSelection, String featureControlprofile_existingValue, String featureControlprofile_newValue,
			String localRingBackTone_existingFieldSelection, String localRingBackTone_newFieldSelection, String localRingBackTone_existingValue, String localRingBackTone_newValue,
			String createLowerCaseRoutervalue,String PSXmanualConfigvalue, String GSXmanualConfigvalue, String callLimit, String limitNumber, String callrateLimiteValue) throws IOException, InterruptedException, DocumentException {   
		
		String gatewayCode=null;
		String subInterfacename_starting="SIF-1-";
		String subInterfacename_middle="-2-";
		String NIFgroupDEfaultValue_starting="SIF-1-";
		String NIFgroupDEfaultValue_middle="-2-";
		String NIGgroupdefaultValue_last="-OUTSIDE";
		String ipInterfaceDEfaultValue="EXTERNAL_IPIF_";
		String addressContextDefaultValue="EXTERNAL_AC_";
		String ipInterfaceGroupDefaultvalue="EXTERNAL_IPIG_";
		String signallingZoneDefaultValue="OUT-UNTRUSTED";
		
		
		String SubInterfaceName=null;
		String NIFgroup=null;
		String ipInterface=null;
		String addressContext=null;
		String ipInterfaceGroup=null;
		String prefix_code=null;
		
		scrolltoend();
		Thread.sleep(2000);
		
		click_commonMethod(application, "OK", "OKbutton", xml);
		
		scrollToTop();
		Thread.sleep(2000);
		
		
	//Trunk Type	
		selectValueInsideDropdown(application, "trunkType_Dropdown", "Trunk Type", trunkType, xml);
		String trunktype_code=trunkType_code(application, trunkType);
		
		
	//Trunk Group Description
		String expectedValue1=customerName+"_"+servicename+"_"+trunkType;
		compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

		
	//VOIP Protocol
		selectValueInsideDropdown(application, "voipProtocol_Dropdown", "VOIP Protocol", voipProtocol, xml);
		String voip_code=voipProtocol_code(application, voipProtocol);
		
	//Billing COuntry
		warningMessage_commonMethod(application, "country_warningMessage", "Billing Country", xml);   //validate warning message
		selectValueInsideDropdown(application, "billingCoutry_Dropdown", "Billing Country", country, xml);
		
		String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
		System.out.println("country dropdon value is: "+country_code);
		
	//CDR Delivery
		selectValueInsideDropdown(application, "CDRdelivery_Dropdown", "CDR Delivery", CDRdelivery, xml);
		String CDR_code=CDRdelivery_code(application, CDRdelivery);
		
	//Prefix
		warningMessage_commonMethod(application, "prefix_warningMessage", "Prefix", xml);  //validate warning messsage
		
		//click on "Allocate Prefix" button
		 click_commonMethod(application, "Allocate Prefix", "allocatePrefix_button", xml);
		 
		 String prefix_actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/prefix_textField")).getAttribute("value");
		 if(prefix_actualvalue.isEmpty()) {
			 
			 verifysuccessmessage(application, "Prefix not available.Please enter manually");
			 
			 addtextFields_commonMethod(application, "Prefix", "prefix_textField", prefix, xml);
			 
		 }else {
			 DriverTestcase.logger.log(LogStatus.PASS, "When we click on 'Allocate Prefix' button, Under 'Prefix' value is displaying as: "+prefix_actualvalue);
			 System.out.println("When we click on 'Allocate Prefix' button, Under 'Prefix' value is displaying as: "+prefix_actualvalue);
		 }

	//valdate the preifx value for adding into Trunk group Name field	 
		 String preifxValueInsidetextField=getwebelement(xml.getlocator("//locators/" + application + "/prefix_textField")).getAttribute("value");
		
		 int prefixSize=preifxValueInsidetextField.length(); 
		 if(prefixSize==3) {
			 prefix_code=preifxValueInsidetextField;
		}else if(prefixSize==4) {
			prefix_code=preifxValueInsidetextField.substring(1);
		}else if(prefixSize<3) {
			System.out.println("Prefix value cannot be less than 3");
			DriverTestcase.logger.log(LogStatus.FAIL, "Prefix value cannot be less than 4. Value is displaying as "+preifxValueInsidetextField);
		}else if(prefixSize>4) {
			System.out.println("Prefix value cannot be greater than 4");
			DriverTestcase.logger.log(LogStatus.FAIL, "Prefix value cannot be greater than 4. Value is displaying as "+preifxValueInsidetextField);
		}
		 
		
	//Gateway
		selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", gateway, xml);
		gatewayCode=gateway_code(application, gateway);
		
		WebElement trunktype=getwebelement(xml.getlocator("//locators/" + application + "/trunkType_Dropdown"));
		scrolltoview(trunktype);
		Thread.sleep(2000);
		
	//Quality
		selectValueInsideDropdown(application, "quality_Dropdown", "Quality", quality, xml);
		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");
		
	//Trunk Group Name
		String trunGroup=country_code+gatewayCode+voip_code+trunktype_code+CDR_code+prefix_code+"0"+quality_code;
		
		int totalLen=trunGroup.length();
		System.out.println("Total lenth of 'Trunk Group' field is "+ totalLen);
		System.out.println("----------------Trunk group name is "+ trunGroup);
		if(totalLen==13) {
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
			primarytrunkGroupname=trunGroup;
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Trunk Group NAme' length is: "+totalLen);
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
		}
		
	//traffic Direction
		selectValueInsideDropdown(application, "trafficDirection_Dropdown", "Traffic Directions", trafficDirection, xml);
		
	//IP Address Type
		selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", ipAddresstype, xml);
	
		WebElement traficDirection=getwebelement(xml.getlocator("//locators/" + application + "/trafficDirection_Dropdown"));
		scrolltoview(traficDirection);
		Thread.sleep(2000);
		
		
	//Carrier IP originating
		warningMessage_commonMethod(application, "carrierIPoriginating_warningMessage", "Carrier IP Originating (Address/Mask)", xml);
		
		addtextFields_commonMethod(application, "Carrier IP Originating (Address/Mask)", "carrierIPoriginating_textField", carrierIPoriginating, xml);
		click_commonMethod(application, ">>", "carrierIPoriginating_addButtton", xml);
		GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPOriginating_addedValue_selectDropdownField")), "Carrier IP Originating (Address/Mask)");
		
		
	//Carrier IP Terminating
		warningMessage_commonMethod(application, "carrerIPterminating_warningMessage", "Carrier IP Terminating((Address)", xml);
		
		addtextFields_commonMethod(application, "Carrier IP Terminating(Address)", "carrierIPterminating_textField", carrierIPterminating, xml);
		click_commonMethod(application, ">>", "carrierIPterminating_addButton", xml);
		GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPterminating_addedValue_selectDropdownField")), "Carrier IP Terminating (Address)");
		
		
	//SIP Signalling Port
	  if(voipProtocol.equalsIgnoreCase("SIP")) {
		  addtextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", SIPsignallingPort, xml);
		  
		  //message displaying under "SIP Signalling Port" text field	
			methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port:5060");
			
	  }else {
		  DriverTestcase.logger.log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
		  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
	  }
		
	
	//Splitting the Gateway functionality into 2  
	  if(!gateway.contains("SBC")) {
		
		  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
			  
			  DriverTestcase.logger.log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
			  
			//Internet Based Customer
				addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
				
			  
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
				  DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				 
				  DriverTestcase.logger.log(LogStatus.PASS, "When 'Internet Based Customer' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
				  if(vlanTag.equalsIgnoreCase("null")) {
						
						//Sub Interface slot
						selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
						
						if(subInterfaceSlot.equalsIgnoreCase("null")) {
							
							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanDefaultvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
							
							//NIF Group  
							NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanDefaultvalue+NIGgroupdefaultValue_last;
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
							
							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanDefaultvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
							
							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanDefaultvalue+NIGgroupdefaultValue_last;
							System.out.println("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						
					}else {
						
						//VLAN Tag
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

					//Sub Interface slot
						selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
						
						if(subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanTag;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
							
							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
							
							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanTag;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
							
							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
							System.out.println("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
					}
			  }
		  }
		 //Internet Based Customer checkbox not selected 
		  else {
			if(vlanTag.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Tag' field is  a mandatory field and not values are passed as an input");
				
				//Sub Interface slot
				selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
				
				if(subInterfaceSlot.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					SubInterfaceName=subInterfacename_starting+subInterfacename_middle;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+NIGgroupdefaultValue_last;
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
				else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+NIGgroupdefaultValue_last;
					System.out.println("NIF Group value is: "+NIFgroup);
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
				
			}else {
				
				//VLAN Tag
				addtextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

			//Sub Interface slot
				selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
				
				if(subInterfaceSlot.equalsIgnoreCase("null")) {

					//Sub Interface Name
					SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanTag;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
				else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanTag;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
					System.out.println("NIF Group value is: "+NIFgroup);
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
			}
			
		//Signalling port
			signallingPort(application, gateway);
		  }
	  }
	 
	  else if(gateway.contains("SBC")) {
		  if(gateway.startsWith("FRA")) {
			  if(!internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  if(vlanTag.equalsIgnoreCase("null")) {
					 
					  DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Tag' text field is a mandatory field. No values has been passed as an input");
				 
					//IP Interface
					  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					  if(ipInterface_Enabled) {
						  System.out.println("'Ip Interface' text field is enabled");
						  DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					  }else {
						  System.out.println("'Ip Interface' text fieldis disabled");
						  DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					  }
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					  if(addressContext_Enabled) {
						  System.out.println("'Address Context' text field is enabled");
						  DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					  }else {
						  System.out.println("'Address Context' text fieldis disabled");
						  DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					  if(ipInterfaceGroup_Enabled) {
						  System.out.println("'IP Inteface Group' text field is enabled");
						  DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					  }else {
						  System.out.println("'IP Inteface Group' text field is disabled");
						  DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
					
					  
				//Signalling port
						signallingPort(application, gateway);	  
			
				  }
				  else if(!vlanTag.equalsIgnoreCase("null")) {
					 
					//VLAN Tag
						addtextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

						//IP Interface
						  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
						  if(ipInterface_Enabled) {
							  System.out.println("'Ip Interface' text field is enabled");
							  DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						  }else {
							  System.out.println("'Ip Interface' text fieldis disabled");
							  DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						  }
						  
						  ipInterface = ipInterfaceDEfaultValue +vlanTag;
						  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
						  
					//Address Context
						  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						  if(addressContext_Enabled) {
							  System.out.println("'Address Context' text field is enabled");
							  DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						  }else {
							  System.out.println("'Address Context' text fieldis disabled");
							  DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
						  }
						  
						  addressContext=addressContextDefaultValue+vlanTag;
						  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
						
						  
					//IP Interface Group
						  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						  if(ipInterfaceGroup_Enabled) {
							  System.out.println("'IP Inteface Group' text field is enabled");
							  DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						  }else {
							  System.out.println("'IP Inteface Group' text field is disabled");
							  DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						  }
						  
						  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanTag;
						  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				 
				  }
				  
			  }
			  else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  DriverTestcase.logger.log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
					//Internet Based Customer
						addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
						
				  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				  if(vlanDefaultvalue.isEmpty()) {
						  DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
				  }else {
					//VLAN tag  
					  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
					  if(VlanEnability) {
						  System.out.println("VLAN Tag is enabled");
						  DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
					  }else {
						  System.out.println("VLAN Tag is disabled");
						  DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
					  } 
				  }
				  
					//IP Interface
				  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
				  if(ipInterface_Enabled) {
					  System.out.println("'Ip Interface' text field is enabled");
					  DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
				  }else {
					  System.out.println("'Ip Interface' text fieldis disabled");
					  DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
				  }
				  
				  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
				  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
				  
			//Address Context
				  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
				  if(addressContext_Enabled) {
					  System.out.println("'Address Context' text field is enabled");
					  DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
				  }else {
					  System.out.println("'Address Context' text fieldis disabled");
					  DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
				  }
				  
				  addressContext=addressContextDefaultValue+vlanDefaultvalue;
				  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
				
				  
			//IP Interface Group
				  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
				  if(ipInterfaceGroup_Enabled) {
					  System.out.println("'IP Inteface Group' text field is enabled");
					  DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
				  }else {
					  System.out.println("'IP Inteface Group' text field is disabled");
					  DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
				  }
				  
				  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanDefaultvalue;
				  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
			  }
		  }
		 else {
			 if(!internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  
				//VLAN tag  
					  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
					  if(VlanEnability) {
						  System.out.println("VLAN Tag is enabled");
						  DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
					  }else {
						  System.out.println("VLAN Tag is disabled");
						  DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
					  }
					  
				//IP Interface
					  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					  if(ipInterface_Enabled) {
						  System.out.println("'Ip Interface' text field is enabled");
						  DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					  }else {
						  System.out.println("'Ip Interface' text fieldis disabled");
						  DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					  }
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					  if(addressContext_Enabled) {
						  System.out.println("'Address Context' text field is enabled");
						  DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					  }else {
						  System.out.println("'Address Context' text fieldis disabled");
						  DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					  if(ipInterfaceGroup_Enabled) {
						  System.out.println("'IP Inteface Group' text field is enabled");
						  DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					  }else {
						  System.out.println("'IP Inteface Group' text field is disabled");
						  DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
					
					  
				//Signalling port
						signallingPort(application, gateway);	  
						
				}
			 
			 if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  
				  DriverTestcase.logger.log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
				//Internet Based Customer
					addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
					
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
					  DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				//VLAN tag  
				  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
				  if(VlanEnability) {
					  System.out.println("VLAN Tag is enabled");
					  DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
				  }else {
					  System.out.println("VLAN Tag is disabled");
					  DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
				  } 
			  }
			  
				//IP Interface
			  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
			  if(ipInterface_Enabled) {
				  System.out.println("'Ip Interface' text field is enabled");
				  DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
			  }else {
				  System.out.println("'Ip Interface' text fieldis disabled");
				  DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
			  }
			  
			  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
			  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
			  
		//Address Context
			  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
			  if(addressContext_Enabled) {
				  System.out.println("'Address Context' text field is enabled");
				  DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
			  }else {
				  System.out.println("'Address Context' text fieldis disabled");
				  DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
			  }
			  
			  addressContext=addressContextDefaultValue+vlanDefaultvalue;
			  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
			
			  
		//IP Interface Group
			  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
			  if(ipInterfaceGroup_Enabled) {
				  System.out.println("'IP Inteface Group' text field is enabled");
				  DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
			  }else {
				  System.out.println("'IP Inteface Group' text field is disabled");
				  DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
			  }
			  
			  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanDefaultvalue;
			  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
			 }
		 }
	  }
	  
	//Signalling Zone
	  if(internetBasedCustomer.equalsIgnoreCase("yes")) {
		  
		  compareText_fromtextFields(application, "Signaling Zone", "signallingZone_textField", signallingZoneDefaultValue, xml);
		  
		  edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
		  
	  }else {
		  edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
	  }
		
	
	
	//Signalling Transport Protocol
		selectValueInsideDropdown(application, "signallingTransportProtocol_Dropdown", "Signalling Transport Protocol", signallingtransportProtocol, xml);
	
		WebElement internetBasedCusotmerView=getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox"));
		scrolltoview(internetBasedCusotmerView);
		Thread.sleep(2000);
		
		
		if(signallingtransportProtocol.equalsIgnoreCase("sip-tls-tcp")) {
			
			//TLS Profile
			addtextFields_commonMethod(application, "TLS Profile", "TLS_textField", TLSfield, xml);
			
			//SRTP
			addCheckbox_commonMethod(application, "srtp_checkbox", "SRTP", srtp, "no", xml);
		}
		
	//Colt Signalling IP
		addtextFields_commonMethod(application, "Colt Signaling IP", "coltSignalingIP_textField", coltSignalingIP, xml);
		
	//Media IP
		addtextFields_commonMethod(application, "Media IP", "mediaIP_textField", mediaIP, xml);
		
	//Reuse NIF Group
		addCheckbox_commonMethod(application, "reuseNIFgroup_checkbox", "Reuse NIF Group", reuseNIFgroup, "No", xml);
		
	//Reuse Sig Zone/Part
		addCheckbox_commonMethod(application, "reuseSigZonePart_checkbox", "Reuse Sig Zone/Part", reuseSigZonePart, "No", xml);
		
	//Call Admission Control
		addCheckbox_commonMethod(application, "callAdmissionControl_checkbox", "Call Admission Control", callAdmissionControl, "No", xml);
		
		if(callAdmissionControl.equalsIgnoreCase("yes")) {
			
			//Call Limit
			selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", callLimit, xml);
			
			if(callLimit.equalsIgnoreCase("Defined")) {
				addtextFields_commonMethod(application, "Limit Number", "limitNumber_textField", limitNumber, xml);
			}
		}
		
	//Call Rate Limit
		addCheckbox_commonMethod(application, "callrateLimit_checkbox", "Call Rate Limit", callrateLimit, "No", xml);
		if(callrateLimit.equalsIgnoreCase("yes")) {
			
			String callratelimitactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callRateLimitt_textField")).getAttribute("value");
			System.out.println(" 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
			DriverTestcase.logger.log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
		
			if(!callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(callrateLimiteValue);
					
				if(i>100) {
					DriverTestcase.logger.log(LogStatus.FAIL, "The CallRateLimit should be less 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "'Call rate Limit' value is not edited");
				System.out.println("'Call rate Limit' value is not edited");
			}
		}
		
		
	//Source Address Filtering
		selectValueInsideDropdown(application, "sourceAddressFiltering_Dropdown", "Source Address Filtering", sourceAddressFiltering, xml);
		
	//100rel Support
		selectValueInsideDropdown(application, "relsupport_Dropdown", "100rel Support", relSupport, xml);
		
	//SIP Session Keepalive Timer(Sec)
		edittextFields_commonMethod(application, "SIP Session Keepalive Timer(Sec)", "sipSessionKeepAliverTimer_textField", sipSessionkeepAliveTimer, xml);
	
		//Text message under "SIP Session Keepalive timer"
			methodToFindMessagesUnderTextField(application, "defaultTextMessageUnderSIPsessionTimer", "SIP Session Keepalive Timer(Sec)", "Default SIP Session Keepalive Timer (sec): 1800");
		
			
			WebElement reusenifgroup=getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox"));
			scrolltoview(reusenifgroup);
			Thread.sleep(2000);
			
	//Retry Invite
		edittextFields_commonMethod(application, "Retry Invite", "retryinvite_textField", retryInvite, xml);
		
		//Text message under "Retry Invite" field
		methodToFindMessagesUnderTextField(application, "defaultTextMessageUnderretryInvite", "Retry Invite", "Default Retry Invite :2");
		
	//Route Priority
		selectValueInsideDropdown(application, "routepriority_Dropdown", "Route Priority", routePriority, xml);
		
	//Address Reachability
		selectValueInsideDropdown(application, "addressReachability_Dropdown", "Address Reachability", addressReachability, xml);
		
		WebElement retryinviteView=getwebelement(xml.getlocator("//locators/" + application + "/retryinvite_textField"));
		scrolltoview(retryinviteView);
		Thread.sleep(2000);
	
	//E164GlobalProfile
		addTrunk_existingNewDropdownField(application, globalProfile_ExistingSelection, globalProfile_newSelection, globalProfile_ExistingValue, 
				globalProfile_newValue, "globalProfile_Dropdown" , "globalProfile_Checkbox", "globalProfile_textField", "E164GlobalProfile");
		
		
	//E164LocalProfile	
		addTrunk_existingNewDropdownField(application, localProfile_existingFieldSelection, localProfile_newFieldSelection, localProfile_existingvalue,
				localProfile_newvalue, "localProfile_Dropdown", "localProfile_checkbox", "localProfile_TextField", "E164LocalProfile");
		
		
	//COS profile
		addTrunk_existingNewDropdownField(application, COSprofile_existingFieldSelection, COSprofile_newFieldSelection, COSprofile_existingValue,
				COSprofile_newValue, "COSprofile_Dropdown", "COSprofile_checkbox", "COSprofile_TextField", "COS Profile");
		
		
	//PSPG Name
		addTrunk_existingNewDropdownField(application, PSPGname_existingFieldSelection, PSPGname_newFieldSelection, pspgName_existingValue,
				pspgName_newValue, "PSPSGname_Dropdown", "PSPGname_Checkbox", "PSPGname_TextField", "PSPG Name");
		
		
	//Preferred  PSP
		addTrunk_existingNewDropdownField(application, prefferedPSP_existingFieldSelection, prefferedPSP_newFieldSelection, preferredPSP_exitingvalue,
				preferredPSP_newvalue, "preferredPSP_Dropdown", "preferredPSP_checkbox", "preferredPSP_TextField", "Preferred PSP");
		

		WebElement globalProfileView=getwebelement(xml.getlocator("//locators/" + application + "/globalProfile_textField"));
		scrolltoview(globalProfileView);
		Thread.sleep(2000);
		
		
	//Carrier
		addTrunk_existingNewDropdownField(application, carrier_existingFieldSelection, carrier_newFieldSelection, carrier_existingValue,
				carrier_newValue, "carriers_Dropdown", "carriers_checkbox", "carriers_TextField", "Carrier");
		
		
	//IP Signalling Profile
		addTrunk_existingNewDropdownField(application, IPsignallingProfile_existingFieldSelection, IPsignallingProfile_newFieldSelection, IPsignallingProfile_existingValue,
				IPsignallingProfile_newValue, "IPsignallingProfile_Dropdown", "IPsignallingProfile_Checkbox", "IPsignallingProfile_textField", "IP Signaling Profile");
		
		
	//Egress IP Signaling Profile
		addTrunk_existingNewDropdownField(application, EgressIpsignal_existingFieldSelection, EgressIpsignal_newFieldSelection, EgressIPsignal_existingValue,
				EgressIPsignal_newValue, "EgressIpsignal_Dropdown", "EgressIPsignal_checkbox", "EgressipSignal_TextField", "Egress IP Signaling Profile");
		
		
		scrolltoend();
		Thread.sleep(2000);
		
	
	//In DM/PM rule
		addTrunk_existingNewDropdownField(application, InDMPMrule_existingFieldSelection, InDMPMrule_newFieldSelection, InDMPMrule_existingValue,
				InDMPMrule_newValue,  "InDMPMrule_Dropdown", "InDMPMrule_checkbox", "InDMPMrule_TextField", "In DM/PM rule");
		
		
	//Out DM/PM rule
		addTrunk_existingNewDropdownField(application, OutDMPMrule_existingFieldSelection, OutDMPMrule_newFieldSelection, OutDMPMrule_existingValue,
				OutDMPMrule_newValue, "OutDMPMrule_Dropdown", "OutDMPMrule_checkbox", "OutDMPMrule_TextField", "Out DM/PM rule");
		
		
	//Feature Control Profile	
		addTrunk_existingNewDropdownField(application, featureControlprofile_existingFieldSelection, featureControlprofile_newFieldSelection, featureControlprofile_existingValue,
				featureControlprofile_newValue, "featureControlprofile_Dropdown", "featureControlprofile_Checkbox", "featureControlprofile_TextField", "Feature Control Profile");
		
		
	//Local Ring Back Tone
		addTrunk_existingNewDropdownField(application, localRingBackTone_existingFieldSelection, localRingBackTone_newFieldSelection, localRingBackTone_existingValue,
				localRingBackTone_newValue, "localRingBackTone_Dropdown", "localRingBackTone_checkbox", "localRingBackTone_TextField", "Local Ring Back Tone");
	
		
	//Create Lower Case Routes
		addCheckbox_commonMethod(application, "createLowerCaseRoute_checkbox", "Create Lower Case Routes", createLowerCaseRoutervalue, "no", xml);
		
		
	//PSX Manual Configuration	
		addCheckbox_commonMethod(application, "PSXmanualConfig_checkbox", "PSX Manual Configuration", PSXmanualConfigvalue, "No" ,xml);
		
		
	//Manual Configuration On GSX
		addCheckbox_commonMethod(application, "GSXmanualConfig_checkbox", "Manual Configuration On GSX", GSXmanualConfigvalue, "No", xml);
		
		
		
		scrolltoend();
		Thread.sleep(1000);
		
		click_commonMethod(application, "OK", "OKbutton", xml);
		
	}
	
	
	public void methodToFindMessagesUnderTextField(String application ,String xpath, String labelname, String expectedmsg) {
		
		boolean defaultPortValueUnderSIPSignalling=false;
		try {	
			defaultPortValueUnderSIPSignalling=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			if(defaultPortValueUnderSIPSignalling) {
				
				WebElement defaultValue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				if(defaultValue.getText().contains(expectedmsg)) {
					DriverTestcase.logger.log(LogStatus.PASS, "Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
					System.out.println("Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
					System.out.println("Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
				System.out.println("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.PASS, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			System.out.println("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
		}
	}
	
	
	public String gateway_code(String application, String gateway) {
		
		String code=null;
		
		if(gateway.equals("DEVGSX1")) {
			code="DEV";
		}
		
		else if(gateway.equals("DEVSBC1")) {
			code="DEA";
		}
		
		else if(gateway.equals("DEVSBC2")) {
			code="DEB";		
		}
				
		else if(gateway.equals("OPSGSX1")) {
			code="OPS";
		}
				
		else if(gateway.equals("MILGSX1")) {
			code="MIA";
		}
				
		else if(gateway.equals("ZRHNBS1")) {
			code="ZHA";
		}
				
		else if(gateway.equals("PARNBS1")) {
			code="PSA";
		}
				
		else if(gateway.equals("LONNBS1")) {
			code="LNA";
		}
				
		else if(gateway.equals("FRANBS1")) {
			code="FTA";
		}
				
		else if(gateway.equals("MADGSX1")) {
			code="MDA";
		}
				
		else if(gateway.equals("BHXNBS1")) {
			code="BHA";
		}
				
		else if(gateway.equals("PARGSX2")) {
			code="PSB";
		}
				
		else if(gateway.equals("FRAGSX2")) {
			code="FTB";
		}
				
		else if(gateway.equals("PARGSX3")) {
			code="PSC";
		}
				
		else if(gateway.equals("FRASBC1")) {
			code="FTC";
		}
				
		else if(gateway.equals("PARSBC1")) {
			code="PSD";
		}
				
		else if(gateway.equals("ZRHGSX2")) {
			code="ZRB";
		}
				
		else if(gateway.equals("FRASBC2")) {
			code="FTD";
		}
				
		else if(gateway.equals("PARSBC2")) {
			code="PSE";
		}
				
		else if(gateway.equals("FRASBC3")) {
			code="FTF";
		}
				
		else if(gateway.equals("PARSBC3")) {
			code="PSF";
		}
		
		return code;
	}
	
	
	public void signallingPort(String application, String gateway) throws InterruptedException, DocumentException {
		
	
		String signallingPort_expectedVaue=signalingport(application, gateway);
		compareText_fromtextFields(application, "Signaling Port", "signallingPort_textField", signallingPort_expectedVaue, xml);
	}
	
	public void signallingPort_viewPage(String application, String gateway) throws InterruptedException, DocumentException {
		
		
		String signallingPort_expectedVaue=signalingport(application, gateway);
		compareText_InViewPage(application, "Signalling Port", signallingPort_expectedVaue, xml);
	}
	
	public String signalingport(String application, String gateway) {
		
		String code=null;
		
		if(gateway.equals("DEVGSX1")) {
			code="543";
		}
		
		else if(gateway.equals("DEVSBC1")) {
			code="1017";
		}
		
		else if(gateway.equals("DEVSBC2")) {
			code="1013";		
		}
				
		else if(gateway.equals("OPSGSX1")) {
			code="509";
		}
				
		else if(gateway.equals("MILGSX1")) {
			code="690";
		}
				
		else if(gateway.equals("ZRHNBS1")) {
			code="1003";
		}
				
		else if(gateway.equals("PARNBS1")) {
			code="889";
		}
				
		else if(gateway.equals("LONNBS1")) {
			code="755";
		}
				
		else if(gateway.equals("FRANBS1")) {
			code="1120";
		}
				
		else if(gateway.equals("MADGSX1")) {
			code="725";
		}
				
		else if(gateway.equals("BHXNBS1")) {
			code="1013";
		}
				
		else if(gateway.equals("PARGSX2")) {
			code="773";
		}
				
		else if(gateway.equals("FRAGSX2")) {
			code="629";
		}
				
		else if(gateway.equals("PARGSX3")) {
			code="556";
		}
				
		else if(gateway.equals("FRASBC1")) {
			code="1023";
		}
				
		else if(gateway.equals("PARSBC1")) {
			code="1005";
		}
				
		else if(gateway.equals("ZRHGSX2")) {
			code="506";
		}
				
		else if(gateway.equals("FRASBC2")) {
			code="1013";
		}
				
		else if(gateway.equals("PARSBC2")) {
			code="1007";
		}
				
		else if(gateway.equals("FRASBC3")) {
			code="1001";
		}
				
		else if(gateway.equals("PARSBC3")) {
			code="1003";
		}
		
		return code;
	}
	
	
	
	public String trunkType_code(String application, String trunkType) {
			
			String code=null;
			
			if(trunkType.equalsIgnoreCase("Number Hosting")) {
				code="N";
			}
			
			else if(trunkType.equalsIgnoreCase("Carrier VoIP")) {
				code="I";
			}
			
			else if(trunkType.equalsIgnoreCase("IN over Carrier VoIP")) {
				code="D";
			}
			
			else if(trunkType.equalsIgnoreCase("National Carrier VoIP")) {
				code="National Carrier VoIP";
			}
			
			return code;
		}
	
	
		public String voipProtocol_code(String application, String voipProtocol) {
			
			String code1=null;
			
			if(voipProtocol.equalsIgnoreCase("SIP")) {
				code1="S";
			}
			
			else if(voipProtocol.equalsIgnoreCase("SIP-I")) {
				code1="S";
			}
			
			return code1;
		}
	
	
		public String CDRdelivery_code(String application, String cdrDElivery) {
			
			String code_cdr=null;
			
			if(cdrDElivery.equalsIgnoreCase("Delivery to COCOM")) {
				code_cdr="C";
			}
			
			else if(cdrDElivery.equalsIgnoreCase("No delivery to COCOM")) {
				code_cdr="0";
			}
			
			else if(cdrDElivery.equalsIgnoreCase("IN Tremination")) {
				code_cdr="I";
			}
			
			return code_cdr;
		}
		
		
	public void addTrunk_existingNewDropdownField(String application, String existingFieldSelection, String newFieldSelection, String existinFieldvalue,
			String newFieldvalue,String xpath_dropdown,String xpath_checkbox, String xpath_textfield, String labelname ) throws IOException, InterruptedException, DocumentException {
		
		if((existingFieldSelection.equalsIgnoreCase("yes")) && (newFieldSelection.equalsIgnoreCase("no"))) {
			
			selectValueInsideDropdown(application, xpath_dropdown, labelname, existinFieldvalue, xml);
			
		}
		else if((existingFieldSelection.equalsIgnoreCase("no")) && (newFieldSelection.equalsIgnoreCase("yes"))) {
			
			click_commonMethod(application, labelname, xpath_checkbox, xml);
			Thread.sleep(1000);
			
			addtextFields_commonMethod(application, labelname, xpath_textfield, newFieldvalue, xml);
		}
		
	}
	
	
	public void editTrunk_existingNewDropdownField(String application, String existingFieldSelection, String newFieldSelection, String existinFieldvalue,
			String newFieldvalue,String xpath_dropdown,String xpath_checkbox, String xpath_textfield, String labelname ) throws IOException, InterruptedException, DocumentException {
		
		if((existingFieldSelection.equalsIgnoreCase("yes")) && (newFieldSelection.equalsIgnoreCase("no"))) {
			
			editTrunk_selectExistingOrNewDropdown(application, xpath_dropdown, xpath_checkbox, existinFieldvalue, labelname);
			
		}
		else if((existingFieldSelection.equalsIgnoreCase("no")) && (newFieldSelection.equalsIgnoreCase("yes"))) {
			
			editTrunk_selectExistingOrNewTextField(application, xpath_textfield, xpath_checkbox, labelname, newFieldvalue);
			
		}
		
	}
	
	
	public void editTrunk_selectExistingOrNewTextField(String application, String xpath_textfield, String xpath_checkbox, String labelname, String textFieldValue) {
		
		boolean fieldEnabled=false;
		
		try {	
			fieldEnabled=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath_textfield +"")).isEnabled();
			
			if(fieldEnabled) {
				
				edittextFields_commonMethod(application, labelname, xpath_textfield, textFieldValue, xml);
				
			}else {
				
				click_commonMethod(application, labelname, xpath_checkbox, xml);
				Thread.sleep(1000);
				
				edittextFields_commonMethod(application, labelname, xpath_textfield, textFieldValue, xml);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
			System.out.println(labelname + " field is not displaying");
			
		}
		
	}
	
	
	public void editTrunk_selectExistingOrNewDropdown(String application, String xpath_dropdown, String xpath_checkbox, String existinFieldvalue, String labelname) throws InterruptedException, DocumentException, IOException {
		
		boolean fieldEnabled=false;
		
	try {	
		fieldEnabled=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath_dropdown +"")).isEnabled();
		
		if(fieldEnabled) {
			
			selectValueInsideDropdown(application, xpath_dropdown, labelname, existinFieldvalue, xml);
			
		}else {
			
			click_commonMethod(application, labelname, xpath_checkbox, xml);
			Thread.sleep(1000);
			
			selectValueInsideDropdown(application, xpath_dropdown, labelname, existinFieldvalue, xml);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
		
		DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
		System.out.println(labelname + " field is not displaying");
	}
	}
	
	
	public void verifyaddTrunk_existingNewDropdownField(String application, String existingFieldSelection, String newFieldSelection, String existinFieldvalue,
			String newFieldvalue,String xpath_dropdown,String xpath_checkbox, String xpath_textfield, String labelname ) throws IOException, InterruptedException, DocumentException {

		
		if((existingFieldSelection.equalsIgnoreCase("yes")) && (newFieldSelection.equalsIgnoreCase("no"))) {
			
			compareText_InViewPage(application, labelname, existinFieldvalue, xml);
			
		}
		else if((existingFieldSelection.equalsIgnoreCase("no")) && (newFieldSelection.equalsIgnoreCase("yes"))) {
			
			compareText_InViewPage(application, labelname, newFieldvalue, xml);
		}
		
	}
	
	
	public void viewTrunk_Primary(String application, String customerName, String servicename, String trunkType, String voipProtocol, String country, String CDRdelivery, String gateway, String quality,
			String trafficDirection, String ipAddresstype, String SIPsignallingPort, String internetBasedCustomer, String vlanTag, String subInterfaceSlot,
			String signallngZone, String signallingtransportProtocol, String coltSignalingIP, String mediaIP, String reuseNIFgroup, String reuseSigZonePart,
			String callAdmissionControl, String callrateLimitSelection, String sourceAddressFiltering, String relSupport, String sipSessionkeepAliveTimer,
			String retryInvite, String routePriority, String addressReachability, String carrierIPoriginating, String carrierIPterminating, String TLSfield,
			String srtp, String prefix, String globalProfile_ExistingSelection, String globalProfile_newSelection, String globalProfile_ExistingValue, String globalProfile_newValue,
			String localProfile_existingFieldSelection, String localProfile_newFieldSelection, String localProfile_existingvalue, String localProfile_newvalue,
			String COSprofile_existingFieldSelection, String COSprofile_newFieldSelection, String COSprofile_existingValue, String COSprofile_newValue,
			String PSPGname_existingFieldSelection, String PSPGname_newFieldSelection,String pspgName_existingValue, String pspgName_newValue,
			String prefferedPSP_existingFieldSelection, String prefferedPSP_newFieldSelection,String preferredPSP_exitingvalue, String preferredPSP_newvalue,
			String carrier_existingFieldSelection, String carrier_newFieldSelection, String carrier_existingValue, String carrier_newValue,
			String IPsignallingProfile_existingFieldSelection, String IPsignallingProfile_newFieldSelection, String IPsignallingProfile_existingValue, String IPsignallingProfile_newValue,
			String EgressIpsignal_existingFieldSelection,String EgressIpsignal_newFieldSelection, String EgressIPsignal_existingValue, String EgressIPsignal_newValue,
			String InDMPMrule_existingFieldSelection, String InDMPMrule_newFieldSelection, String InDMPMrule_existingValue, String InDMPMrule_newValue,
			String OutDMPMrule_existingFieldSelection, String OutDMPMrule_newFieldSelection, String OutDMPMrule_existingValue, String OutDMPMrule_newValue,
			String featureControlprofile_existingFieldSelection,String featureControlprofile_newFieldSelection, String featureControlprofile_existingValue, String featureControlprofile_newValue,
			String localRingBackTone_existingFieldSelection, String localRingBackTone_newFieldSelection, String localRingBackTone_existingValue, String localRingBackTone_newValue,
			String createLowerCaseRoutervalue,String PSXmanualConfigvalue, String GSXmanualConfigvalue, String callLimit, String limitNumber, String callrateLimiteValue) throws IOException, InterruptedException, DocumentException {
		
		
		scrollToTop();
		Thread.sleep(2000);
		
		String AddressContext="EXTERNAL_AC_";
		String IPINTERFACEGROUP ="EXTERNAL_IPIG_";
		String IPINTERFACE=	"EXTERNAL_IPIF_";
		
		String subInterfacename_starting="SIF-1-";
		String subInterfacename_middle="-2-";
		String NIFgroupDEfaultValue_starting="SIF-1-";
		String NIFgroupDEfaultValue_middle="-2-";
		String NIGgroupdefaultValue_last="-OUTSIDE";
		
		
		
		//Trunk Group Description
		String expectedValue1=customerName+"_"+servicename+"_"+trunkType;
		compareText_InViewPage(application, "Trunk Group Description", expectedValue1, xml);
		
		//Trunk Type
			compareText_InViewPage(application, "Trunk Type", trunkType, xml);
			
		//VOIP Protocol
			compareText_InViewPage(application, "VOIP Protocol", voipProtocol, xml);
			
		//Billing Country
			compareText_InViewPage(application, "Billing Country", country, xml);
			
		//CDR Delivery
			compareText_InViewPage(application, "CDR Delivery", CDRdelivery, xml);
			
		//Prefix
			compareText_InViewPage(application, "Prefix", prefix, xml);

		//Gateway
			compareText_InViewPage(application, "Gateway", gateway, xml);
			
		//Quality
			compareText_InViewPage(application, "Quality", quality, xml);
			
		//Trunk Group Name
			compareText_InViewPage(application, "Trunk Group Name", primarytrunkGroupname, xml);
			
		//Traffic Direction
			compareText_InViewPage(application, "Traffic Directions", trafficDirection, xml);
			
		//IP Address Type
			compareText_InViewPage(application, "IP Address Type", ipAddresstype, xml);
			
		//Carrier IP Originating
			compareText_InViewPage(application, "Carrier IP Originating", carrierIPoriginating, xml);
			
		//Carrier IP Terminating
			compareText_InViewPage(application, "Carrier IP Terminating", carrierIPterminating, xml);
			
		//SIP Signaling Port
			  if(voipProtocol.equalsIgnoreCase("SIP")) {
				  compareText_InViewPage(application, "SIP Signalling Port", SIPsignallingPort, xml);
			  }else {
				  System.out.println(" 'SIP Signalling port' will not display, if voip protocol selected as 'SIP-1'");
			  }
			
		//Sub Interface Slot
			  String subinterfaceSlot_viewPage=getwebelement("//div[div[label[contains(text(),'Sub Interface Slot')]]]/div[2]").getText();
			 
	//VLAN Tag 
			  if(!gateway.contains("SBC")) {
					
				  if(internetBasedCustomer.equalsIgnoreCase("no")) {
					  
					  //VLAN Tag
//					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  //Sub Interface Name
					  	String SubInterfaceName=subInterfacename_starting+subinterfaceSlot_viewPage+subInterfacename_middle+vlan_actualValue;
						compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);
						
						//NIF Group
						String NIFgroup=NIFgroupDEfaultValue_starting+subinterfaceSlot_viewPage+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						compareText_InViewPage(application, "NIF Group", NIFgroup, xml);
					  
					  //Signalling Zone
						if(signallngZone.equalsIgnoreCase("null")) {
					  compareText_InViewPage(application, "Signalling Zone", "OUT-UNTRUSTED", xml);
						}
						else if(!signallngZone.equalsIgnoreCase("null")) {
							  compareText_InViewPage(application, "Signalling Zone", signallngZone, xml);
								}
					  
					  
				  }
				  else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
					 
					  //VLAN Tag
//					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  //Sub Interface Name
					  	String SubInterfaceName=subInterfacename_starting+subinterfaceSlot_viewPage+subInterfacename_middle+vlan_actualValue;
						compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);
						
						//NIF Group
						String NIFgroup=NIFgroupDEfaultValue_starting+subinterfaceSlot_viewPage+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						compareText_InViewPage(application, "NIF Group", NIFgroup, xml);
					  
						//Signalling Zone
						if(signallngZone.equalsIgnoreCase("null")) {
					  compareText_InViewPage(application, "Signalling Zone", "OUT-UNTRUSTED", xml);
						}
						else if(!signallngZone.equalsIgnoreCase("null")) {
							  compareText_InViewPage(application, "Signalling Zone", signallngZone, xml);
								}
					  
				  }
			  }
			  else if(gateway.contains("SBC")) {
				  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  //Address Content
					  compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);
					  
					  //IP Interface Group
					  compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);
					  
					  //IP Interface
					  compareText_InViewPage(application, "IP Interface", IPINTERFACE+vlan_actualValue, xml);
					  
					//Signalling Zone
						if(signallngZone.equalsIgnoreCase("null")) {
					  compareText_InViewPage(application, "Signalling Zone", "OUT-UNTRUSTED", xml);
						}
						else if(!signallngZone.equalsIgnoreCase("null")) {
							  compareText_InViewPage(application, "Signalling Zone", signallngZone, xml);
								}
					  
					  
				  }
				  else if(internetBasedCustomer.equalsIgnoreCase("No")) {
					  
					  //VLAN Tag
					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
					  
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  //Address Content
					  compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);
					  
					  //IP Interface Group
					  compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);
					  
					  //IP Interface
					  compareText_InViewPage(application, "IP Interface", IPINTERFACE+vlan_actualValue, xml);
				  }
			  }
				  
		//Signalling Transport Protocol
			compareText_InViewPage(application, "Signalling Transport Protocol", signallingtransportProtocol, xml);
			
		//Signalling Port
		if(internetBasedCustomer.equalsIgnoreCase("No")) {	
			signallingPort_viewPage(application, gateway);
		}
			
		//Colt Signalling IP
			compareText_InViewPage(application, "Colt Signalling IP", coltSignalingIP, xml);

		//Media IP
			compareText_InViewPage(application, "Media IP", mediaIP, xml);
			
		//Reuse NIF Group	
			compareText_InViewPage(application, "Reuse NIF Group", reuseNIFgroup, xml);
			
		//Reuse Sig Zone/Port
			compareText_InViewPage(application, "Reuse Sig Zone/Port", reuseSigZonePart, xml);
		
		//Call Admission Control
			compareText_InViewPage(application, "Call Admission Control", callAdmissionControl, xml);
			
			if(callAdmissionControl.equalsIgnoreCase("yes")) {
			  //call limit	
				compareText_InViewPage(application, "Call Limit",callLimit , xml);
				
				if(callLimit.equalsIgnoreCase("Defined")) {
					//Limit Number
					compareText_InViewPage(application, "Limit Number", limitNumber , xml);
				}
			}
		
		//Call Rate Limit
			if(callrateLimitSelection.equalsIgnoreCase("Yes")) {
				
				//call rate limit value
				compareText_InViewPage(application,"Call Rate Limit", callrateLimiteValue, xml);
			}
			
			
			
		//Source Address Filtering
			compareText_InViewPage(application, "Source Address Filtering", sourceAddressFiltering, xml);
			
		//100rel Support	
			compareText_InViewPage(application, "100rel Support", relSupport, xml);
			
		//SIP session Keepalive timer
			compareText_InViewPage(application, "SIP session Keepalive timer", sipSessionkeepAliveTimer, xml);
			
		//Retry Invite
			compareText_InViewPage(application, "Retry Invite", retryInvite, xml);
			
		//Route Priority
			compareText_InViewPage(application, "Route Priority", routePriority, xml);
		
		//Address Reachability	
			compareText_InViewPage(application, "Address Reachability", addressReachability, xml);
			
			
		//E164Global Profile	
			verifyaddTrunk_existingNewDropdownField(application, globalProfile_ExistingSelection, globalProfile_newSelection, globalProfile_ExistingValue, 
					globalProfile_newValue, "globalProfile_Dropdown" , "globalProfile_Checkbox", "globalProfile_textField", "E164Global Profile");
			
			
		//E164LocalProfile	
			verifyaddTrunk_existingNewDropdownField(application, localProfile_existingFieldSelection, localProfile_newFieldSelection, localProfile_existingvalue,
					localProfile_newvalue, "localProfile_Dropdown", "localProfile_checkbox", "localProfile_TextField", "E164 Local Profile");
			
			
		//COS profile
			verifyaddTrunk_existingNewDropdownField(application, COSprofile_existingFieldSelection, COSprofile_newFieldSelection, COSprofile_existingValue,
					COSprofile_newValue, "COSprofile_Dropdown", "COSprofile_checkbox", "COSprofile_TextField", "COS Profile");
			
			
		//PSPG Name
			verifyaddTrunk_existingNewDropdownField(application, PSPGname_existingFieldSelection, PSPGname_newFieldSelection, pspgName_existingValue,
					pspgName_newValue, "PSPSGname_Dropdown", "PSPGname_Checkbox", "PSPGname_TextField", "PSPG Name");
			
			
		//Preferred  PSP
			verifyaddTrunk_existingNewDropdownField(application, prefferedPSP_existingFieldSelection, prefferedPSP_newFieldSelection, preferredPSP_exitingvalue,
					preferredPSP_newvalue, "preferredPSP_Dropdown", "preferredPSP_checkbox", "preferredPSP_TextField", "Preferred PSP");
			

		//Carrier
//			verifyaddTrunk_existingNewDropdownField(application, carrier_existingFieldSelection, carrier_newFieldSelection, carrier_existingValue,
//					carrier_newValue, "carriers_Dropdown", "carriers_checkbox", "carriers_TextField", "Carrier");
			
			
			
		//IP Signalling Profile
			verifyaddTrunk_existingNewDropdownField(application, IPsignallingProfile_existingFieldSelection, IPsignallingProfile_newFieldSelection, IPsignallingProfile_existingValue,
					IPsignallingProfile_newValue, "IPsignallingProfile_Dropdown", "IPsignallingProfile_Checkbox", "IPsignallingProfile_textField", "IP Signaling Profile");
			
			
		//Egress IP Signaling Profile
			verifyaddTrunk_existingNewDropdownField(application, EgressIpsignal_existingFieldSelection, EgressIpsignal_newFieldSelection, EgressIPsignal_existingValue,
					EgressIPsignal_newValue, "EgressIpsignal_Dropdown", "EgressIPsignal_checkbox", "EgressipSignal_TextField", "Egress IP Signalling Profile");
			
		
		//In DM/PM rule
			verifyaddTrunk_existingNewDropdownField(application, InDMPMrule_existingFieldSelection, InDMPMrule_newFieldSelection, InDMPMrule_existingValue,
					InDMPMrule_newValue,  "InDMPMrule_Dropdown", "InDMPMrule_checkbox", "InDMPMrule_TextField", "In DM/PM rule");
			
			
		//Out DM/PM rule
			verifyaddTrunk_existingNewDropdownField(application, OutDMPMrule_existingFieldSelection, OutDMPMrule_newFieldSelection, OutDMPMrule_existingValue,
					OutDMPMrule_newValue, "OutDMPMrule_Dropdown", "OutDMPMrule_checkbox", "OutDMPMrule_TextField", "Out DM/PM rule");
			
			
		//Feature Control Profile	
			verifyaddTrunk_existingNewDropdownField(application, featureControlprofile_existingFieldSelection, featureControlprofile_newFieldSelection, featureControlprofile_existingValue,
					featureControlprofile_newValue, "featureControlprofile_Dropdown", "featureControlprofile_Checkbox", "featureControlprofile_TextField", "Feature Control Profile");
			
			
		//Local Ring Back Tone
			verifyaddTrunk_existingNewDropdownField(application, localRingBackTone_existingFieldSelection, localRingBackTone_newFieldSelection, localRingBackTone_existingValue,
					localRingBackTone_newValue, "localRingBackTone_Dropdown", "localRingBackTone_checkbox", "localRingBackTone_TextField", "Local Ring Back Tone");

		//Create Lower Case Routes
			compareText_InViewPage(application, "Create Lower Case Routes", createLowerCaseRoutervalue, xml);
			
		//PSX Manual Configuration	
			compareText_InViewPage(application, "PSX Manual Configuration", PSXmanualConfigvalue, xml);

	}
	
	
	public void editTrunk(String application,String customerName, String servicename,String trunktype, String edit_TrunkType, String edit_VOIPprotocol, String edit_BillingCountry, String edit_CDRdelivery,
			String editPrefix, String editGateway, String editQuality, String editTrafficDirection, String edit_IpAddressType, String editCarrierIPoriginating,
			String editCarrierIPterminating, String editSIPsignallingPort, String editSignallingTransport, String edit_TLSproflile, String edit_SRTP, String edit_signallingZone,
			String edit_coltSignalIP, String edit_mediaIP, String edit_reuseNIFgroup, String edit_reuseSigZonePart, String edit_callAdmissionControl, String edit_callLimit,
			String edit_limitNumber, String edit_callrateLimit, String edit_callrateLimitvalue, String edit_sourceAddressFiltering, String edit_relSupport,
			String edit_sipSessionkeepAliveTimer, String edit_internetBasedCustomer, String edit_vlantag, String edit_subInterfaceSlot, String edit_retryInvite,
			String edit_addressReachability, String edit_routePriority,
			String editglobalProfile_ExistingSelection, String editglobalProfile_newSelection, String editGlobalProfile_ExistingValue,String editGlobalProfile_newValue,
			String editLocalProfile_existingFieldSelection, String editLocalProfile_newFieldSelection, String editLocalProfile_existingvalue, String editLocalProfile_newvalue,
			String editCOSprofile_existingFieldSelection, String editCOSprofile_newFieldSelection, String editCOSprofile_existingValue, String editCOSprofile_newValue,
			String editPSPGname_existingFieldSelection, String editPSPGname_newFieldSelection, String editpspgName_existingValue, String editpspgName_newValue,
			String editPrefferedPSP_existingFieldSelection, String editPrefferedPSP_newFieldSelection, String editPreferredPSP_exitingvalue, String editPreferredPSP_newvalue,
			String editCarrier_existingFieldSelection, String editCarrier_newFieldSelection, String editCarrier_existingValue, String editCarrier_newValue,
			String editIPsignalProfile_existingFieldSelection, String editIPsignalProfile_newFieldSelection, String editIPsignalProfile_existingValue, String editIPsignalProfile_newValue,
			String editEgressIpsignal_existingFieldSelection, String editEgressIpsignal_newFieldSelection, String editEgressIPsignal_existingValue, String editEgressIPsignal_newValue,
			String editInDMPMrule_existingFieldSelection, String editInDMPMrule_newFieldSelection, String editInDMPMrule_existingValue, String editInDMPMrule_newValue,
			String editOutDMPMrule_existingFieldSelection, String editOutDMPMrule_newFieldSelection, String editOutDMPMrule_existingValue, String editOutDMPMrule_newValue,
			String editFeatureControlprofile_existingFieldSelection, String editFeatureControlprofile_newFieldSelection, String editFeatureControlprofile_existingValue, String editFeatureControlprofile_newValue,
			String editLocalRingBackTone_existingFieldSelection, String editLocalRingBackTone_newFieldSelection, String editLocalRingBackTone_existingValue, String editLocalRingBackTone_newValue,
			String editCreateLowerCaseRoutervalue, String edit_PSXmanualConfigvalue, String edit_GSXmanualConfigvalue) throws InterruptedException, DocumentException, IOException {    
		

		String subInterfacename_starting="SIF-1-";
		String subInterfacename_middle="-2-";
		String NIFgroupDEfaultValue_starting="SIF-1-";
		String NIFgroupDEfaultValue_middle="-2-";
		String NIGgroupdefaultValue_last="-OUTSIDE";
		String ipInterfaceDEfaultValue="EXTERNAL_IPIF_";
		String addressContextDefaultValue="EXTERNAL_AC_";
		String ipInterfaceGroupDefaultvalue="EXTERNAL_IPIG_";
		String signallingZoneDefaultValue="OUT-UNTRUSTED";
		
		String trunktype_code=null;
		String trunkNameToBePassed=null;
		String prefix_code=null;
		String gatewayCode=null;
		String primarytrunk="0";
		
		
	//Action button	
		click_commonMethod(application, "Action", "viewPage_ActionButton", xml);
		
	//click on Edit link
		click_commonMethod(application, "Edit", "editLink", xml);
		
		
		//Trunk Type	
				selectValueInsideDropdown(application, "trunkType_Dropdown", "Trunk Type", edit_TrunkType, xml);
				
				if(edit_TrunkType.equalsIgnoreCase("null")) {
					trunktype_code=trunkType_code(application, trunktype);
					trunkNameToBePassed=trunktype;
				}else {
					trunktype_code=trunkType_code(application, edit_TrunkType);
					trunkNameToBePassed=edit_TrunkType;
				}
			
				
		//Trunk Group Description
				String expectedValue1=customerName+"_"+servicename+"_"+trunkNameToBePassed;
				compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

				
			//Billing COuntry
				selectValueInsideDropdown(application, "billingCoutry_Dropdown", "Billing Country", edit_BillingCountry, xml);
				
				String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
				System.out.println("country dropdon value is: "+country_code);
				
			//VOIP Protocol
				selectValueInsideDropdown(application, "voipProtocol_Dropdown", "VOIP Protocol", edit_VOIPprotocol, xml);
				Thread.sleep(1000);
				String voipProtocol_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "voipProtocol_Dropdown", "VOIP Protocol");
				Thread.sleep(1000);
				System.out.println("The voip protocol displaying is "+ voipProtocol_actualvalue);
				String voip_code=voipProtocol_code(application, voipProtocol_actualvalue);
				
			//CDR Delivery
				selectValueInsideDropdown(application, "CDRdelivery_Dropdown", "CDR Delivery", edit_CDRdelivery, xml);
				Thread.sleep(1000);
				String cdrDelivery_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "CDRdelivery_Dropdown", "CDR Delivery");
				Thread.sleep(1000);
				String CDR_code=CDRdelivery_code(application, cdrDelivery_actualvalue);
			
			//Prefix
					 edittextFields_commonMethod(application, "Prefix", "prefix_textField", editPrefix, xml);
					 Thread.sleep(1000);

			//valdate the prefix value for adding into Trunk group Name field	 
				 String preifxValueInsidetextField=getwebelement(xml.getlocator("//locators/" + application + "/prefix_textField")).getAttribute("value");
				
				 int prefixSize=preifxValueInsidetextField.length(); 
				 if(prefixSize==3) {
					 prefix_code=preifxValueInsidetextField;
				}else if(prefixSize==4) {
					prefix_code=preifxValueInsidetextField.substring(1);
				}else if(prefixSize<3) {
					System.out.println("Prefix value cannot be less than 3");
					DriverTestcase.logger.log(LogStatus.FAIL, "Prefix value cannot be less than 4. Value is displaying as "+preifxValueInsidetextField);
				}else if(prefixSize>4) {
					System.out.println("Prefix value cannot be greater than 4");
					DriverTestcase.logger.log(LogStatus.FAIL, "Prefix value cannot be greater than 4. Value is displaying as "+preifxValueInsidetextField);
				}
				 
			//Gateway
				selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", editGateway, xml);
				String gateway_actualValue=GetTheSelectedValueInsideDropdown_trunk(application, "gateway_Dropdown", "Gateway");
				gatewayCode=gateway_code(application, gateway_actualValue);
				
				WebElement trunk_type=getwebelement(xml.getlocator("//locators/" + application + "/trunkType_Dropdown"));
				scrolltoview(trunk_type);
				Thread.sleep(2000);
				
			//Quality
				selectValueInsideDropdown(application, "quality_Dropdown", "Quality", editQuality, xml);
				String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");
				
			//Trunk Group Name
				String trunGroup=country_code+gatewayCode+voip_code+trunktype_code+CDR_code+prefix_code+primarytrunk+quality_code;
				
				int totalLen=trunGroup.length();
				System.out.println("Total lenth of 'Trunk Group' field is "+ totalLen);
				System.out.println("----------------Trunk group name is "+ trunGroup);
				if(totalLen==13) {
					compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
					primarytrunkGroupname=trunGroup;
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Trunk Group NAme' length is: "+totalLen);
					compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
				}
				
			//Traffic Directions 
				selectValueInsideDropdown(application, "trafficDirection_Dropdown", "Traffic Directions", editTrafficDirection, xml);
				
			//IP Address Type
				selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", edit_IpAddressType, xml);
			
				WebElement traficDirection=getwebelement(xml.getlocator("//locators/" + application + "/trafficDirection_Dropdown"));
				scrolltoview(traficDirection);
				Thread.sleep(2000);
				
			//Carrier IP originating
				edittextFields_commonMethod(application, "Carrier IP Originating (Address/Mask)", "carrierIPoriginating_textField", editCarrierIPoriginating, xml);
				click_commonMethod(application, ">>", "carrierIPoriginating_addButtton", xml);
				GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPOriginating_addedValue_selectDropdownField")), "Carrier IP Originating (Address/Mask)");
				
				
			//Carrier IP Terminating
				edittextFields_commonMethod(application, "Carrier IP Terminating(Address)", "carrierIPterminating_textField", editCarrierIPterminating, xml);
				click_commonMethod(application, ">>", "carrierIPterminating_addButton", xml);
				GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPterminating_addedValue_selectDropdownField")), "Carrier IP Terminating (Address)");
			
			//SIP Signalling Port
			  if(edit_VOIPprotocol.equalsIgnoreCase("SIP")) {
				  edittextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", editSIPsignallingPort, xml);
						
			  }else {
				  DriverTestcase.logger.log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
				  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
			  }
			  
			
			//Internet Based Customer
			  editcheckbox_commonMethod(application, edit_internetBasedCustomer, "internetBasedCustomer_checkbox", "Internet Based Customer", xml);
			  
		    
			//VLAN Tag
			  edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", edit_vlantag, xml);
			  String vlan_actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  
			  
			//Sub Interface Slot
			  selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", edit_subInterfaceSlot, xml);
			  Thread.sleep(1000);
			  String subIntercaeSlot_actualValue=GetTheSelectedValueInsideDropdown_trunk(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot");
			  
				 
			if(!gateway_actualValue.contains("SBC")) {
				
				if(subIntercaeSlot_actualValue.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					String SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlan_actualvalue;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group  
					String NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlan_actualvalue+NIGgroupdefaultValue_last;
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
				else if(!subIntercaeSlot_actualValue.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					String SubInterfaceName=subInterfacename_starting+subIntercaeSlot_actualValue+subInterfacename_middle+vlan_actualvalue;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					String NIFgroup=NIFgroupDEfaultValue_starting+subIntercaeSlot_actualValue+NIFgroupDEfaultValue_middle+vlan_actualvalue+NIGgroupdefaultValue_last;
					System.out.println("NIF Group value is: "+NIFgroup);
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
			}
			else if(gateway_actualValue.contains("SBC")) {
				

				//IP Interface
				  String ipInterface = ipInterfaceDEfaultValue +vlan_actualvalue;
				  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
				  
			//Address Context
				  String addressContext=addressContextDefaultValue+vlan_actualvalue;
				  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
				
				  
			//IP Interface Group
				  String ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlan_actualvalue;
				  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
		 
			}

			
			//Signalling Zone
			  edittextFields_commonMethod(application, "Signalling Zone", "signallingZone_textField", edit_signallingZone, xml);
			  
			  
			//Signalling Transport Protocol
			  	selectValueInsideDropdown(application, "signallingTransportProtocol_Dropdown", "Signalling Transport Protocol", editSignallingTransport, xml);
				
				WebElement internetBasedCusotmerView=getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox"));
				scrolltoview(internetBasedCusotmerView);
				Thread.sleep(2000);
				
				String signaltransport_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "signallingTransportProtocol_Dropdown", "Signalling Transport Protocol");
				if(signaltransport_actualvalue.equalsIgnoreCase("sip-tls-tcp")) {
					
					//TLS Profile
					edittextFields_commonMethod(application, "TLS Profile", "TLS_textField", edit_TLSproflile, xml);
					
					//SRTP
					editcheckbox_commonMethod(application, edit_SRTP, "srtp_checkbox", "SRTP", xml);
				}
				
			//Colt Signalling IP
				edittextFields_commonMethod(application, "Colt Signaling IP", "coltSignalingIP_textField", edit_coltSignalIP, xml);
				
			//Media IP
				edittextFields_commonMethod(application, "Media IP", "mediaIP_textField", edit_mediaIP, xml);
				
			//Reuse NIF Group
				editcheckbox_commonMethod(application, edit_reuseNIFgroup, "reuseNIFgroup_checkbox", "Reuse NIF Group", xml);
				
			//Reuse Sig Zone/Part
				editcheckbox_commonMethod(application, edit_reuseSigZonePart, "reuseSigZonePart_checkbox", "Reuse Sig Zone/Part", xml);
				
				
				//Call Admission Control
				editcheckbox_commonMethod(application, edit_callAdmissionControl, "callAdmissionControl_checkbox", "Call Admission Control", xml);
				
				boolean calladmisssion_actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callAdmissionControl_checkbox")).isSelected();
				
				if(calladmisssion_actualvalue) {
					
					//Call Limit
					selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", edit_callLimit, xml);
					String callimit_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "callLimit_Dropdown", "CAll Limit");
					
					if(callimit_actualvalue.equalsIgnoreCase("Defined")) {
						edittextFields_commonMethod(application, "Limit Number", "limitNumber_textField", edit_limitNumber, xml);
					}
				}
				
			//Call Rate Limit
				editcheckbox_commonMethod(application, edit_callrateLimit, "callrateLimit_checkbox", "Call Rate Limit", xml);
				boolean callratelimit_actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callrateLimit_checkbox")).isSelected();
				
				if(callratelimit_actualvalue) {
					
					String callratelimitactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callRateLimitt_textField")).getAttribute("value");
					System.out.println(" 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
					DriverTestcase.logger.log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
				
					if(!edit_callrateLimitvalue.equalsIgnoreCase("null")) {
						int i=Integer.parseInt(edit_callrateLimitvalue);
							
						if(i>100) {
							DriverTestcase.logger.log(LogStatus.FAIL, "The CallRateLimit should be less 100 for all Trunks");
						}
						else if(i<=100){
							edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", edit_callrateLimitvalue, xml);
						}
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "'Call rate Limit' value is not edited");
						System.out.println("'Call rate Limit' value is not edited");
					}
				}
				
				
			//Source Address Filtering
				selectValueInsideDropdown(application, "sourceAddressFiltering_Dropdown", "Source Address Filtering", edit_sourceAddressFiltering, xml);
				
			//100rel Support
				selectValueInsideDropdown(application, "relsupport_Dropdown", "100rel Support", edit_relSupport, xml);
				
			//SIP Session Keepalive Timer(Sec)
				edittextFields_commonMethod(application, "SIP Session Keepalive Timer(Sec)", "sipSessionKeepAliverTimer_textField", edit_sipSessionkeepAliveTimer, xml);
			
			//Retry Invite
				edittextFields_commonMethod(application, "Retry Invite", "retryinvite_textField", edit_retryInvite, xml);
				
			//Text message under "Retry Invite" field
				methodToFindMessagesUnderTextField(application, "defaultTextMessageUnderretryInvite", "Retry Invite", "Default Retry Invite :2");
				
			//Route Priority
				selectValueInsideDropdown(application, "routepriority_Dropdown", "Route Priority", edit_routePriority, xml);
				
			//Address Reachability
				selectValueInsideDropdown(application, "addressReachability_Dropdown", "Address Reachability", edit_addressReachability, xml);
				
				WebElement retryinviteView=getwebelement(xml.getlocator("//locators/" + application + "/retryinvite_textField"));
				scrolltoview(retryinviteView);
				Thread.sleep(2000);
			
			//E164GlobalProfile
				editTrunk_existingNewDropdownField(application, editglobalProfile_ExistingSelection, editglobalProfile_newSelection, editGlobalProfile_ExistingValue, 
						editGlobalProfile_newValue, "globalProfile_Dropdown" , "globalProfile_Checkbox", "globalProfile_textField", "E164GlobalProfile");
				
			//E164LocalProfile	
				editTrunk_existingNewDropdownField(application, editLocalProfile_existingFieldSelection, editLocalProfile_newFieldSelection, editLocalProfile_existingvalue,
						editLocalProfile_newvalue, "localProfile_Dropdown", "localProfile_checkbox", "localProfile_TextField", "E164LocalProfile");
				
				 
			//COS profile
				editTrunk_existingNewDropdownField(application, editCOSprofile_existingFieldSelection, editCOSprofile_newFieldSelection, editCOSprofile_existingValue,
						editCOSprofile_newValue, "COSprofile_Dropdown", "COSprofile_checkbox", "COSprofile_TextField", "COS Profile");
				
							
			//PSPG Name
				editTrunk_existingNewDropdownField(application, editPSPGname_existingFieldSelection, editPSPGname_newFieldSelection, editpspgName_existingValue,
						editpspgName_newValue, "PSPSGname_Dropdown", "PSPGname_Checkbox", "PSPGname_TextField", "PSPG Name");
				
				
			//Preferred  PSP
				editTrunk_existingNewDropdownField(application, editPrefferedPSP_existingFieldSelection, editPrefferedPSP_newFieldSelection, editPreferredPSP_exitingvalue,
						editPreferredPSP_newvalue, "preferredPSP_Dropdown", "preferredPSP_checkbox", "preferredPSP_TextField", "Preferred PSP");
				
				
				
				WebElement globalProfileView=getwebelement(xml.getlocator("//locators/" + application + "/globalProfile_textField"));
				scrolltoview(globalProfileView);
				Thread.sleep(2000);
				
			//Carrier
				editTrunk_existingNewDropdownField(application, editCarrier_existingFieldSelection, editCarrier_newFieldSelection, editCarrier_existingValue,
						editCarrier_newValue, "carriers_Dropdown", "carriers_checkbox", "carriers_TextField", "Carrier");
				
			
			//IP Signalling Profile
				editTrunk_existingNewDropdownField(application, editIPsignalProfile_existingFieldSelection, editIPsignalProfile_newFieldSelection, editIPsignalProfile_existingValue,
						editIPsignalProfile_newValue, "IPsignallingProfile_Dropdown", "IPsignallingProfile_Checkbox", "IPsignallingProfile_textField", "IP Signaling Profile");
				
	
			//Egress IP Signaling Profile
				editTrunk_existingNewDropdownField(application, editEgressIpsignal_existingFieldSelection, editEgressIpsignal_newFieldSelection, editEgressIPsignal_existingValue,
						editEgressIPsignal_newValue, "EgressIpsignal_Dropdown", "EgressIPsignal_checkbox", "EgressipSignal_TextField", "Egress IP Signaling Profile");
				
				scrolltoend();
				Thread.sleep(2000);
				
			
			//In DM/PM rule
				editTrunk_existingNewDropdownField(application, editInDMPMrule_existingFieldSelection, editInDMPMrule_newFieldSelection, editInDMPMrule_existingValue,
						editInDMPMrule_newValue,  "InDMPMrule_Dropdown", "InDMPMrule_checkbox", "InDMPMrule_TextField", "In DM/PM rule");
				
				
			//Out DM/PM rule
				editTrunk_existingNewDropdownField(application, editOutDMPMrule_existingFieldSelection, editOutDMPMrule_newFieldSelection, editOutDMPMrule_existingValue,
						editOutDMPMrule_newValue, "OutDMPMrule_Dropdown", "OutDMPMrule_checkbox", "OutDMPMrule_TextField", "Out DM/PM rule");
				
		
			//Feature Control Profile	
				editTrunk_existingNewDropdownField(application, editFeatureControlprofile_existingFieldSelection, editFeatureControlprofile_newFieldSelection, editFeatureControlprofile_existingValue,
						editFeatureControlprofile_newValue, "featureControlprofile_Dropdown", "featureControlprofile_Checkbox", "featureControlprofile_TextField", "Feature Control Profile");
				
				
			//Local Ring Back Tone
				editTrunk_existingNewDropdownField(application, editLocalRingBackTone_existingFieldSelection, editLocalRingBackTone_newFieldSelection, editLocalRingBackTone_existingValue,
						editLocalRingBackTone_newValue, "localRingBackTone_Dropdown", "localRingBackTone_checkbox", "localRingBackTone_TextField", "Local Ring Back Tone");
			
				
			//Create Lower Case Routes
				editcheckbox_commonMethod(application, editCreateLowerCaseRoutervalue, "createLowerCaseRoute_checkbox", "Create Lower Case Routes", xml);
				
				
			//PSX Manual Configuration	
				editcheckbox_commonMethod(application, edit_PSXmanualConfigvalue, "PSXmanualConfig_checkbox", "PSX Manual Configuration", xml);
				
			//Manual Configuration On GSX
				editcheckbox_commonMethod(application, edit_GSXmanualConfigvalue, "GSXmanualConfig_checkbox", "Manual Configuration On GSX", xml);
		
				
				scrolltoend();
				Thread.sleep(1000);
				
				click_commonMethod(application, "OK", "OKbutton", xml);
				
				
					
				
	}
	
	
	public void fetchDisabledFieldValue(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
		
//		String actualvalue=null;
		WebElement ele=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		
		boolean elementEnabled=ele.isEnabled();
		
		if(elementEnabled) {
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is disabled");
			System.out.println(labelname + " is disabled");
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, labelname + "is disabled as expected");
			System.out.println(labelname + "is disabled as expected");
			
//			actualvalue=ele.getAttribute("value");
//			DriverTestcase.logger.log(LogStatus.PASS, "Value for "+ labelname+ " field is displaying as:"+actualvalue);
//			System.out.println("Value for "+ labelname+ " field is displaying as:"+actualvalue);
		}
	}
	
	
	
	public void addResilienttrunk(String application, String customerName, String servicename, String trunkType, String voipProtocol, String country, String CDRdelivery, String gateway, String quality,
			String trafficDirection, String ipAddresstype, String SIPsignallingPort, String internetBasedCustomer, String vlanTag, String subInterfaceSlot,
			String signallngZone, String signallingtransportProtocol, String coltSignalingIP, String mediaIP, String reuseNIFgroup, String reuseSigZonePart,
			String callAdmissionControl, String callrateLimit, String sourceAddressFiltering, String relSupport, String sipSessionkeepAliveTimer,
			String retryInvite, String routePriority, String addressReachability, String carrierIPoriginating, String carrierIPterminating, String TLSfield,
			String srtp, String prefix, String globalProfile_ExistingSelection, String globalProfile_newSelection, String globalProfile_ExistingValue, String globalProfile_newValue,
			String localProfile_existingvalue, String localProfile_newvalue,String COSprofile_existingValue, String COSprofile_newValue,String pspgName_existingValue, 
			String pspgName_newValue,String preferredPSP_exitingvalue, String preferredPSP_newvalue,String carrier_existingValue, String carrier_newValue, 
			String IPsignallingProfile_existingValue, String IPsignallingProfile_newValue,String EgressIPsignal_existingValue, String EgressIPsignal_newValue,
			String InDMPMrule_existingValue, String InDMPMrule_newValue,String OutDMPMrule_existingValue, String OutDMPMrule_newValue,String featureControlprofile_existingValue, 
			String featureControlprofile_newValue,String localRingBackTone_existingFieldSelection, String localRingBackTone_newFieldSelection, String localRingBackTone_existingValue, String localRingBackTone_newValue,
			String createLowerCaseRoutervalue,String PSXmanualConfigvalue, String GSXmanualConfigvalue, String callLimit, String limitNumber, String callrateLimiteValue,
			
			String gateway_resilientTrunk, String voip_resilientTrunk, String trafficDirection_resiltrunk, String ipAddressType_resilTrunk, String carierIPOrient_resiltrunk,
			String carierIPterminat_resiltrunk) throws IOException, InterruptedException, DocumentException {
		
		
		WebElement hyperLink=getwebelement("//a[text()='"+ servicename +"']");
		click_commonMethod_PassingWebelementDirectly(application, "Sevice name hyperlink","hyperLink" , xml);
		Thread.sleep(1000);
		
		scrolltoend();
		Thread.sleep(2000);
		
		click_commonMethod(application, "OK", "OKbutton", xml);
		
		scrollToTop();
		Thread.sleep(2000);
		
		//Primary Trunk Group
		System.out.println("Primary trunk group value: "+primarytrunkGroupname);
		selectValueInsideDropdown(application, "primaryTrunkGroup_Dropdown", "Primary Trunk Group" , primarytrunkGroupname, xml);
		Thread.sleep(2000);
		
		//Trunk Type
		fetchDisabledFieldValue(application, "Trunk Type", "trunkType_Dropdown");
		String trunktypeSelected=GetTheSelectedValueInsideDropdown_trunk(application, "trunkType_Dropdown", "Trunk Type");
		DriverTestcase.logger.log(LogStatus.PASS, "Value for 'Trunk Type' field is displaying as:"+trunktypeSelected);
		System.out.println("Value for 'Trunk Type' field is displaying as:"+trunktypeSelected);
		
		//VOIP Protocol
		fetchDisabledFieldValue(application, "VOIP Protocol", "voipProtocol_Dropdown");
		String voipProtocolSelected=GetTheSelectedValueInsideDropdown_trunk(application, "voipProtocol_Dropdown", "VOIP Protocol");
		DriverTestcase.logger.log(LogStatus.PASS, "Value for 'VOIP Protocol' field is displaying as:"+voipProtocolSelected);
		System.out.println("Value for 'VOIP Protocol' field is displaying as:"+voipProtocolSelected);
		
		selectValueInsideDropdown(application, "voipProtocol_Dropdown", "VOIP Protocol", voip_resilientTrunk, xml);
		String voipProtocoledited=GetTheSelectedValueInsideDropdown_trunk(application, "voipProtocol_Dropdown", "VOIP Protocol");  //select value under dropdown
		
		
		//Billing Country
		fetchDisabledFieldValue(application, "Billing Country", "billingCoutry_Dropdown");
		String coutrySelected=GetTheSelectedValueInsideDropdown_trunk(application, "billingCoutry_Dropdown", "Billing Country");
		DriverTestcase.logger.log(LogStatus.PASS, "Value for 'Billing Country' field is displaying as:"+coutrySelected);
		System.out.println("Value for 'Billing Country' field is displaying as:"+coutrySelected);
		
		//CDR Delivery
		fetchDisabledFieldValue(application, "CDR Delivery", "CDRdelivery_Dropdown");
		String cdrSelected=GetTheSelectedValueInsideDropdown_trunk(application, "CDRdelivery_Dropdown", "CDR Delivery");
		DriverTestcase.logger.log(LogStatus.PASS, "Value for 'CDR Delivery' field is displaying as:"+cdrSelected);
		System.out.println("Value for 'CDR Delivery' field is displaying as:"+cdrSelected);
		
		//Prefix
		fetchDisabledFieldValue(application, "Prefix", "prefix_textField");
		String prefixSelected=GetTheSelectedValueInsideDropdown_trunk(application, "prefix_textField", "Prefix");
		DriverTestcase.logger.log(LogStatus.PASS, "Value for 'Prefix' field is displaying as:"+prefixSelected);
		System.out.println("Value for 'Prefix' field is displaying as:"+prefixSelected);
		
		
		//Gateway
		fetchDisabledFieldValue(application, "Gateway", "gateway_Dropdown");
		String gatewaySelected=GetTheSelectedValueInsideDropdown_trunk(application, "gateway_Dropdown", "Gateway");
		selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", gateway_resilientTrunk, xml);
		
		//Quality
		fetchDisabledFieldValue(application, "Quality", "quality_Dropdown");
		String qualitySelected=GetTheSelectedValueInsideDropdown_trunk(application, "quality_Dropdown", "Quality");
		DriverTestcase.logger.log(LogStatus.PASS, "Value for 'Prefix' field is displaying as:"+qualitySelected);
		System.out.println("Value for 'Prefix' field is displaying as:"+qualitySelected);
		
		
		//Trunk Group Name
		
		
		//Traffic Direction
		fetchDisabledFieldValue(application, "Traffic Direction", "trafficDirection_Dropdown");
		GetTheSelectedValueInsideDropdown_trunk(application, "trafficDirection_Dropdown", "Traffic Direction");
		selectValueInsideDropdown(application, "trafficDirection_Dropdown", "Traffic Direction", trafficDirection_resiltrunk, xml);
		
		
		//IP Address Type
		fetchDisabledFieldValue(application, "IP Address Type", "IPaddresstype_Dropdown");
		GetTheSelectedValueInsideDropdown_trunk(application, "IPaddresstype_Dropdown", "IP Address Type");
		selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", ipAddressType_resilTrunk, xml);
		
		//Carrier IP originating
			addtextFields_commonMethod(application, "Carrier IP Originating (Address/Mask)", "carrierIPoriginating_textField", carierIPOrient_resiltrunk, xml);
			click_commonMethod(application, ">>", "carrierIPoriginating_addButtton", xml);
			GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPOriginating_addedValue_selectDropdownField")), "Carrier IP Originating (Address/Mask)");
				
				
		//Carrier IP Terminating
			addtextFields_commonMethod(application, "Carrier IP Terminating(Address)", "carrierIPterminating_textField", carierIPterminat_resiltrunk, xml);
			click_commonMethod(application, ">>", "carrierIPterminating_addButton", xml);
			GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPterminating_addedValue_selectDropdownField")), "Carrier IP Terminating (Address)");
				
		//SIP Signalling Port
				if(voipProtocoledited.equalsIgnoreCase("SIP")) {
					  addtextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", SIPsignallingPort, xml);
					  //message displaying under "SIP Signalling Port" text field	
						methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port:5060");
				  }else {
					  DriverTestcase.logger.log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
					  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
				  }
			
			
			 
		
		
	}
	
	public String GetTheSelectedValueInsideDropdown_trunk(String application , String xpath, String labelname) throws IOException, InterruptedException, DocumentException
	{ //Thread.sleep(3000);
	
		String selectedValue=null;
		
	try {	
		WebElement el=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		
		Select s1=new Select(el);
		WebElement option = s1.getFirstSelectedOption();
		System.out.println(option);
		selectedValue = option.getText();	
		
		
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
		System.out.println(labelname + " field is not displaying");
	}
		
		return selectedValue;
	
	}
	
	public void GetTheValuesInsideDropdown(WebElement el, String labelname) throws IOException, InterruptedException
	{ //Thread.sleep(3000);
		
		List<String> ls = new ArrayList<String>();
		Select sel=new Select(el);
		 List<WebElement> we = sel.getOptions();
		   
		    for(WebElement a : we)
		    {
		        if(!a.getText().equals("select")){
		            ls.add(a.getText());
		            
		        }
		    }
		
		System.out.println("Value displaying under "+labelname+" is: "+ ls);
		DriverTestcase.logger.log(LogStatus.PASS, "Value displaying under "+labelname+" is: "+ ls);
	
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
	public void compareText_fromtextFields(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

		WebElement element = null;

		try {
			Thread.sleep(1000);
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
			if(element==null)
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			}
			else if (emptyele!=null && emptyele.isEmpty()) {
				DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
			}else 
			{   
				if(emptyele.equals(ExpectedText)) {
					DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+emptyele+"'");
				}
				else if(emptyele.contains(ExpectedText)) {
					DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+emptyele+"'");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal Text '"+emptyele+"'");
				}
			}
		}catch (Exception e) {
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
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
				DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			}
			else
			{
				String WarningMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"").replace("Value", labelname)).getText();

				System.out.println("'"+labelname+"' field warning  message displayed as : " + WarningMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : validation message for '"+labelname+"' text field displayed as : " + WarningMsg);
				Log.info("'"+labelname+"' field warning message displayed as : " + WarningMsg);
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("'"+labelname+"' field warning message is not dipslaying");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step: '"+labelname+"' field warning message is not displaying");
		}catch(Exception ed) {
			ed.printStackTrace();
		}
	}

	



	
	
	
	
	
	
		

	

			////////////////////	Add CPE DEVICE   //////////////////////////


	public void verifyAddCPEDeviceFunction(String application, String ServiceIdentification, String TrunkGroupSiteOrderNumber, String ExistingTrunkName,
			String AddNewCPEDevice, String AddExistingCPEDevice, String PE_IMSPOPLocation,  String TrunkGroupOrderCheckboxStatus) throws InterruptedException, DocumentException, IOException {
		
		boolean TrunkNameToAddCPEDeviceText=false;
		boolean trunkgrupOrderErrMsg= false;
		boolean TrunkGroupSiteOrderNumberText=false;
		scrolltoend();
		Thread.sleep(2000);
		
		//*scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_AddPEDeviceLink")));
		TrunkNameToAddCPEDeviceText= getwebelement("//div[text()='"+ ExistingTrunkName +"']").isDisplayed();
		if(TrunkNameToAddCPEDeviceText) {
			DriverTestcase.logger.log(LogStatus.PASS, "Expected 'Trunk Name' is displaying as expected under Trunk Panel in 'view Service' page");
			System.out.println("Expected 'Trunk Name'' is displaying as expected under Trunk Panel in 'view Service' page");
			
		click(application, "Trunk Name in Trunk panel", "//div[text()='"+ ExistingTrunkName +"']");
		click(application, "ACTION LINK in Trunk panel", "ViewService_Trunk_ActionLink");
		//boolean ViewService_Trunk_DeleteSiteOrderSuccessMessage=false;
		
		WebElement deletelink=getwebelement("//div[div[span[text()='"+ TrunkGroupSiteOrderNumber +"']]]//span[text()='Delete']");
		
		TrunkGroupSiteOrderNumberText=getwebelement("//span[text()='"+ TrunkGroupSiteOrderNumber +"']").isDisplayed();
		
		WebElement ViewService_Trunk_DeleteSiteOrderSuccessMessage=getwebelement("ViewService_Trunk_DeleteSiteOrderSuccessMessage");
	
   if(TrunkGroupSiteOrderNumberText){
			
			DriverTestcase.logger.log(LogStatus.PASS, TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			
			
			
			
			if(deletelink.isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'Delete Link in Trunk Group/Site Order' page is displaying as expected");
				System.out.println("'Delete Link Trunk Group/Site Order' page is displaying as expected");
				
			//Trunk group Order
				deletelink.click();
				compareText(application, "Delete Site Order warning message", "ViewService_Trunk_DeleteSiteOrderWarningMessage", "Are you sure that you want to delete this item?");
				click(application, "Delete Button", "ViewService_Trunk_DeleteButton");
				
				
				if(ViewService_Trunk_DeleteSiteOrderSuccessMessage.isDisplayed()) {
					DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group Site Order' Deleted Successfully");
					System.out.println("'Trunk Group Site Order' Deleted Successfully");
																					}else {
					DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group Site Order' not Deleted");
					System.out.println("'Trunk Group Site Order' not Deleted");
																						   } 
		    							}else {
		    		DriverTestcase.logger.log(LogStatus.PASS, "'Delete Link in Trunk Group/Site Order' page is not displaying as expected");
		    		System.out.println("'Delete Link Trunk Group/Site Order' page is not  displaying as expected");
		    							}
			
			
			
			
			
							}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "Expected 'Trunk Name' is not displaying as expected under Trunk Panel in 'view Service' page");
				System.out.println("Expected 'Trunk Name'' is not displaying as expected under Trunk Panel in 'view Service' page");
								  }
}
		
	}
		
		////////////////////////////////
//		click(application, "Add PE Device Link", "PE_AddPEDeviceLink");
//		compareText(application, "Add PE Device header", "PE_AddPEDevice_header", "Add PE Device");
//
//		click(application, "OK button", "PE_OKbutton");
//		compareText(application, "Add Device Warning Message for PE Device", "PE_AddPEDeviceWarningMessage", "IMS POP Location is required");
//		
//		// Select  IMS POP Location dropdown
//		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PE_IMSPOPLocationDropdown")));
//		List<WebElement> PE_IMSPOPLocationDropdown = driver.findElements(By.xpath("//div[@class='sc-htpNat AUGYd']/div"));
//		for (WebElement clist : PE_IMSPOPLocationDropdown) {
//
//			System.out.println("Available IMS POP Location name is : " + clist.getText().toString());
//			DriverTestcase.logger.log(LogStatus.PASS,"Step : Available IMS POP Location name is : " + clist.getText().toString());
//			Log.info("Available IMS POP Location name is :" + clist.getText().toString());
//		}
//		
//		Thread.sleep(1000);
//		
//		// click on IMSPOPLocationDropdown
//		WebElement IMSPOPLocationDropdown = driver.findElement(By.xpath("//div[contains(text(),'" + PE_IMSPOPLocation + "')]"));
//		System.out.println("Selected IMS POP Location dropdowm is : " + IMSPOPLocationDropdown.getText().toString());
//		DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected IMS POP Location dropdowm is : " + IMSPOPLocationDropdown.getText().toString());
//		Log.info("Selected IMS POP Location dropdowm is : " + IMSPOPLocationDropdown.getText().toString());
//		IMSPOPLocationDropdown.click();
//		Thread.sleep(2000);
//		
//		
//		click(application, "OK button", "PE_OKbutton");
//		
//		
//		compareText(application, "Add Device Successful Message for PE Device", "PE_AddPEDeviceSuccessfulMessage", "PE Device added successfully");
//		
//		Log.info("------ PE Device added successfully ------");
//	}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
