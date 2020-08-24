package com.colt.qa.scripthelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.listeners.Retry;


public class APT_VOIPAccessHelper extends DriverHelper {

	public APT_VOIPAccessHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_VOIPAccess.xml");

	
	public String primarytrunkGroupname=null;
	

	
	
	
	
	//======================================  Common Methods  ===========================================	
	
		//======================================  Common Methods  ===========================================

			//======================================  Common Methods  ===========================================

		
		public void webelementpresencelogger(WebElement ele, String msg) {

			boolean flag = ele.isDisplayed();
			System.out.println("element presence state : " + flag);
			if (flag) {

				Log.info("webElement is present " + ele.getText());
				DriverTestcase.logger.log(LogStatus.PASS, msg);
			} else {

				System.out.println("webElement is not  present" + ele.getText());
				DriverTestcase.logger.log(LogStatus.FAIL, msg);
			}

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
			//Thread.sleep(2000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
			//Thread.sleep(1000);

			WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
//			scrolltoview(searchbutton);
			scrolltoend();
			//Thread.sleep(2000);
			Clickon(searchbutton);
			scrolltoend();
			//Thread.sleep(2000);
			scrolltoend();
			//Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			//Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
			//Thread.sleep(1000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewLink_SearchOrderPage")));
			//Thread.sleep(3000);
		}
		
		
	
		
		
		
		public void searchorder_UI(String application, String sid) throws InterruptedException, DocumentException, IOException {
			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
			//Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
			//Thread.sleep(1000);
			WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
//			scrolltoview(searchbutton);
			scrolltoend();
			//Thread.sleep(2000);
			Clickon(searchbutton);
			scrolltoend();
			//Thread.sleep(2000);
			scrolltoend();
			//Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			//Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
			//Thread.sleep(1000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewLink_SearchOrderPage")));
			//Thread.sleep(3000);
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


public void verifysuccessmessageforEditService(String application) throws InterruptedException {
			
			scrollToTop();
			Thread.sleep(3000);
			try {	
				
				String expected= "Service updated successfully";
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMsg")).isDisplayed();
				
				if(successMsg) {
					
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
					
					if(alrtmsg.equals(expected)) {
						
						DriverTestcase.logger.log(LogStatus.PASS," 'service successfully created' message is verified. It is displaying as: "+alrtmsg);
						System.out.println(" 'service successfully created' message is verified. It is displaying as: "+alrtmsg);
						
					}else {
						
						DriverTestcase.logger.log(LogStatus.FAIL, "Service creation message is displaying but the success message display as: "+ alrtmsg);
						System.out.println("Service creation message is displaying and the message gets mismatches. It is displaying as: "+ alrtmsg);
					}
					
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Service updated successfully' message is not displaying after creating service");
				}
				
			}catch(Exception e) {
				Log.info("failure in fetching success message - 'Service updated successfully'  ");
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Service updated successfully' message is not displaying after editing the service");
				System.out.println("Success message for edit Service is not getting dislpayed");
			}

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
				//Thread.sleep(1000);
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
				DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
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
					//Thread.sleep(1000);
					element.sendKeys(newValue);
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Entered '"+newValue+"' into '"+labelname+"' text field");
				}

			} catch (Exception e) {
				DriverTestcase.logger.log(LogStatus.FAIL,"Not able to enter '"+newValue+"' into '"+labelname+"' text field");
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
				//Thread.sleep(1000);
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
					//Thread.sleep(3000);
					
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
//		public void addDropdownValues_commonMethod(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
//			  boolean availability=false;
//			try {  
//			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
//			  if(availability) {
//				  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown is displaying");
//				  System.out.println(labelname + " dropdown is displaying");
//				  
//				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
//					  
//					  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
//					  System.out.println(" No values selected under "+ labelname + " dropdown");
//				  }else {
//					  
//					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
//					  //Thread.sleep(3000);
//
//					  //verify list of values inside dropdown
//					  List<WebElement> listofvalues = driver
//								.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
//					  
//					  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
//					  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
//					  
//						for (WebElement valuetypes : listofvalues) {
//									Log.info("service sub types : " + valuetypes.getText());
//									DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
//									System.out.println(" " + valuetypes.getText());
//						}
//						
//						Thread.sleep(2000);
//					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
//					Thread.sleep(2000);
//						
//					  Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
//					  Thread.sleep(3000);
//					  
//					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
//					  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
//					  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
//					  
//				  }
//			  }else {
//				  DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
//				  System.out.println(labelname + " is not displaying");
//			  }
//			}catch(NoSuchElementException e) {
//				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
//				  System.out.println(labelname + " is not displaying");
//			}catch(Exception ee) {
//				ee.printStackTrace();
//				DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
//				System.out.println(" NO value selected under "+ labelname + " dropdown");
//			}
//			
//		}
		
		

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
				//Thread.sleep(2000);
			
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
						//Thread.sleep(3000);
						
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
						//Thread.sleep(3000);
						
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
			 				//Thread.sleep(3000);
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
//			public void compareText(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {
//
//				String text = null;
//				WebElement element = null;
//
//				try {
//					Thread.sleep(1000);
//					element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
//					String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
//					if(element==null)
//					{
//						DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
//					}
//					else if (emptyele!=null && emptyele.isEmpty()) {
//						DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
//					}else 
//					{   
//						text = element.getText();
//						if(text.equals(ExpectedText)) {
//							DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
//						}
//						else if(text.contains(ExpectedText)) {
//							DriverTestcase.logger.log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
//						}
//						else
//						{
//							DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
//						}
//					}
//				}catch (Exception e) {
//					e.printStackTrace();
//					DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
//					System.out.println(labelname + " field is not displaying");
//				}
//
//			}
			
			
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
//			public void compareText_InViewPage(String application, String labelname,  String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {
//
//				String text = null;
//				WebElement element = null;
//
//				try {
//					Thread.sleep(1000);
//					element = getwebelement("//div[div[label[contains(text(),'"+ labelname + "')]]]/div[2]");
//					String emptyele = element.getText().toString();
//
//					if(element==null)
//					{
//						DriverTestcase.logger.log(LogStatus.FAIL, labelname+" not found");
//						System.out.println(labelname+" not found");
//					}
//					else if (emptyele!=null && emptyele.isEmpty()) {
////						DriverTestcase.logger.log(LogStatus.PASS,  labelname + "' value is empty");
//						
//						emptyele= "Null";
//						
//						sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
//						
//						if(emptyele.equalsIgnoreCase(ExpectedText)) {
//							
//							DriverTestcase.logger.log(LogStatus.PASS, " The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
//							System.out.println(" The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
//					
//							
//						}else {
//							
//							DriverTestcase.logger.log(LogStatus.FAIL,"The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
//							System.out.println(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
//							
//								}
//						
//
//					}else 
//					{   
//						text = element.getText();
//						if(text.equals(ExpectedText)) {
//							DriverTestcase.logger.log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
//							System.out.println(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
//						}
//						else if(text.contains(ExpectedText)) {
//							DriverTestcase.logger.log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
//							System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
//						
//						}
//						else
//						{
//							DriverTestcase.logger.log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
//							System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
//						}
//					}
//				}catch (Exception e) {
//					e.printStackTrace();
//					DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
//					System.out.println(labelname + " field is not displaying");
//				}
//
//			}


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
				
		
		
				
				
				public void SelectDropdownValueUnderDivTag(String application ,String lebelname, String dropdownToBeSelectedInTheEnd, String dropdownXpath, String commonDropdownValueTag) throws InterruptedException, DocumentException, IOException {
					
					try {
						// Select  Country dropdown
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+dropdownXpath+"")));
							List<WebElement> dropdownValueList = driver.findElements(By.xpath(commonDropdownValueTag));
							
							for (WebElement dropdownvaluelist : dropdownValueList) {
								System.out.println("Available " +lebelname+ " are : " + dropdownvaluelist.getText().toString()+ "  ,  ");
								DriverTestcase.logger.log(LogStatus.PASS,"Step : Available  '" +lebelname+ "'  is : " + dropdownvaluelist.getText().toString());
								Log.info("Available " +lebelname+ " is : " + dropdownvaluelist.getText().toString());
							}
							
							
							//Thread.sleep(1000);
							// click on Country dropdown
							WebElement selectDropdownValue = driver.findElement(By.xpath("//div[contains(text(),'" + dropdownToBeSelectedInTheEnd + "')]"));
							System.out.println("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
							DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected  '"+lebelname+ "'  is : " + selectDropdownValue.getText().toString());
							Log.info("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
							selectDropdownValue.click();
							Thread.sleep(2000);
							
						
				}catch(NoSuchElementException e) {
					e.printStackTrace();
					
					DriverTestcase.logger.log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
					System.out.println( lebelname + " dropdown is not displaying");
					
				}catch(Exception ee) {
					ee.printStackTrace();
					
					DriverTestcase.logger.log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
					System.out.println( lebelname + " dropdown is not displaying");
				}

			}
							
							
							
					
	
				
				
				
public void SelectDropdownValueUnderSelectTag(String application, String labelname , String dropdownToBeSelectedInTheEnd ,  String dropdownXpath,   XMLReader xml) throws InterruptedException, DocumentException, IOException {
				
	
	{ 
		//SelectDropdownValueUnderSelectTag
		 boolean availability=false;
		 List<String> ls = new ArrayList<String>();
		 
			try {  
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdownXpath +"")).isDisplayed();
			  if(availability) {
				  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  WebElement el =getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdownXpath +""));
				  
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
		            
				  if(dropdownToBeSelectedInTheEnd.equalsIgnoreCase("null")) {
					  
					  DriverTestcase.logger.log(LogStatus.PASS, "No values selected under "+ labelname + " dropdown");
				  }else {
					  Select s1=new Select(el);
					  s1.selectByVisibleText(dropdownToBeSelectedInTheEnd);
					  
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
	

}



					
					
					
					
				
				
				
				//======================================  Common Methods  ===========================================	
				
				//======================================  Common Methods  ===========================================

				//======================================  Common Methods  ===========================================

				


public void createcustomer(String application, String name, String maindomain, String country, String ocn,
		String reference, String tcn, String type, String email, String phone, String fax)
				throws Exception {


	Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
	Thread.sleep(2000);
	System.out.println("Mouse hovered on Manage Customer's Service");
	DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
	Log.info("Mouse hovered on 'Manage Customers Service' menu item");

	click_commonMethod(application, "create customer link", "createcustomerlink", xml);
	Thread.sleep(2000);
	compareText(application, "create customer page header", "createcustomer_header", "Create Customer", xml);
	scrolltoend();
	click_commonMethod(application, "Ok", "okbutton", xml);

	//Warning msg check
	warningMessage_commonMethod(application, "customernamewarngmsg", "Legal Customer Name", xml);
	warningMessage_commonMethod(application, "countrywarngmsg", "Country", xml);
	warningMessage_commonMethod(application, "ocnwarngmsg", "OCN", xml);
	warningMessage_commonMethod(application, "typewarngmsg", "Type", xml);
	warningMessage_commonMethod(application, "emailwarngmsg", "Email", xml);

	//Clear customer info
	addtextFields_commonMethod(application, "Customer Name", "nametextfield", name, xml);
	addtextFields_commonMethod(application, "Main Domain", "maindomaintextfield", maindomain, xml);
	Thread.sleep(1000);
	scrolltoend();
	click_commonMethod(application, "Clear", "clearbutton", xml);
	DriverTestcase.logger.log(LogStatus.PASS, "All text field values are cleared");
	Log.info("All text field values are cleared");

	//Create customer by providing all info
	addtextFields_commonMethod(application, "Customer Name", "nametextfield", name, xml);
	addtextFields_commonMethod(application, "Main Domain", "maindomaintextfield", maindomain, xml);
	addDropdownValues_commonMethod(application, "Country", "country", country, xml);
	addtextFields_commonMethod(application, "OCN", "ocntextfield", ocn, xml);
	addtextFields_commonMethod(application, "Reference", "referencetextfield", reference, xml);
	addtextFields_commonMethod(application, "Technical Contact Name", "technicalcontactnametextfield", tcn, xml);
	addDropdownValues_commonMethod(application, "Type", "typedropdown", type, xml);
	addtextFields_commonMethod(application, "Email", "emailtextfield", email, xml);
	addtextFields_commonMethod(application, "Phone", "phonetextfield", phone, xml);
	addtextFields_commonMethod(application, "Fax", "faxtextfield", fax, xml);
	scrolltoend();
	Thread.sleep(1000);
	click_commonMethod(application, "Ok", "okbutton", xml);
	//compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
	verifysuccessmessage(application, "Customer successfully created.");
	sa.assertAll();
}



				
		

public void customerCreationForm_UI(String application)	throws Exception {
	Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
	Thread.sleep(2000);
	System.out.println("Mouse hovered on Manage Customer's Service");
	DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
	Log.info("Mouse hovered on 'Manage Customers Service' menu item");

	click_commonMethod(application, "create customer link", "createcustomerlink", xml);
	Thread.sleep(2000);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/createcustomer_header")).isDisplayed()) {
		DriverTestcase.logger.log(LogStatus.PASS, "'Create Customer' page navigated as expected");
		System.out.println("'Create Customer' page navigated as expected");
		
	scrolltoend();
	click_commonMethod(application, "Ok", "okbutton", xml);

	//Warning msg check
	warningMessage_commonMethod(application, "customernamewarngmsg", "Legal Customer Name", xml);
	warningMessage_commonMethod(application, "countrywarngmsg", "Country", xml);
	warningMessage_commonMethod(application, "ocnwarngmsg", "OCN", xml);
	warningMessage_commonMethod(application, "typewarngmsg", "Type", xml);
	warningMessage_commonMethod(application, "emailwarngmsg", "Email", xml);

	isDisplayed(application, "nametextfield", "Legal Customer Name", xml);
	isDisplayed(application, "maindomaintextfield", "Main Domain", xml);
	isDisplayed(application, "country", "Country", xml);
	isDisplayed(application, "ocntextfield", "OCN", xml);
	isDisplayed(application, "referencetextfield", "Reference", xml);
	isDisplayed(application, "technicalcontactnametextfield", "Technical Contact Name", xml);
	isDisplayed(application, "typedropdown", "Type", xml);
	isDisplayed(application, "emailtextfield", "Email", xml);
	isDisplayed(application, "phonetextfield", "Phone", xml);
	isDisplayed(application, "faxtextfield", "Fax", xml);
	
	scrolltoend();
	Thread.sleep(1000);
	isDisplayed(application, "okbutton", "OK", xml);

	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'Create Customer' page not navigated");
		System.out.println("'Create Customer' page navigated");
	}
	sa.assertAll();
}


	

public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected)
		throws InterruptedException, DocumentException, IOException {

	Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
	Thread.sleep(3000);
	System.out.println("Mouse hovered on Manage Customer's Service");
	DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
	Log.info("Mouse hovered on 'Manage Customers Service' menu item");

	click_commonMethod(application, "Create Order/Service", "CreateOrderServiceLink", xml);
	Log.info("=== Create Order/Service navigated ===");

	//click on Next button to check mandatory messages
	click_commonMethod(application, "Next", "nextbutton", xml);

	//Customer Error message	
	warningMessage_commonMethod(application, "customer_createorderpage_warngmsg", "Choose a customer", xml);

	//Entering Customer name
    addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected, xml);
    
    Thread.sleep(7000);
    addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", "*", xml);
    
    //Select Customer from dropdown
    addDropdownValues_commonMethod(application, "Choose a customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);

	click_commonMethod(application, "Next", "nextbutton", xml);

}




public void customerSelectionToCreateOrder_UI(String application, String ChooseCustomerToBeSelected)
		throws InterruptedException, DocumentException, IOException {

	Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
	isDisplayed(application, "ManageCustomerServiceLink", "MANAGE CUSTOMER'S SERVICE Link", xml);
	Thread.sleep(3000);
	System.out.println("Mouse hovered on Manage Customer's Service");
	DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
	Log.info("Mouse hovered on 'Manage Customers Service' menu item");

	isDisplayed(application, "CreateOrderServiceLink", "Create Order/Service Link", xml);
	click_commonMethod(application, "Create Order/Service", "CreateOrderServiceLink", xml);
	Log.info("=== Create Order/Service navigated ===");

	//click on Next button to check mandatory messages
	click_commonMethod(application, "Next", "nextbutton", xml);

	//Customer Error message	
	warningMessage_commonMethod(application, "customer_createorderpage_warngmsg", "Choose a customer", xml);

	//Entering Customer name
	isDisplayed(application, "entercustomernamefield", "Customer Name", xml);
    addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected, xml);
    
    Thread.sleep(7000);
    isDisplayed(application, "entercustomernamefield", "Customer Name", xml);
    addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", "*", xml);
    
    //Select Customer from dropdown
    isDisplayed(application, "chooseCustomerdropdown", "Choose a customer", xml);
    addDropdownValues_commonMethod(application, "Choose a customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);

    isDisplayed(application, "nextbutton", "Next button", xml);
	click_commonMethod(application, "Next", "nextbutton", xml);

}



	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected, String customerName1, String customerName2)
			throws Exception {
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		System.out.println("Mouser hovered on Manage Customer's Service");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click(application, "Create Order/Service Link", "CreateOrderServiceLink");	
		Log.info("=== Create Order/Service navigated ===");
		
		//click on Next button to check mandatory messages	
		click(application, "Next", "nextbutton");

		//Customer Error message	
		//*WarningMessage(application, "customer_createorderpage_warngmsg", "Customer");
		//*warningMessage_commonMethod(application, "customer_createorderpage_warngmsg", "Customer", xml);//Not Working

		//Entering Customer name	
		EnterTextValue(application, customerName1, "Customer Name", "entercustomernamefield");
		EnterTextValue(application, customerName2, "Customer Name", "entercustomernamefield");	
		
		//Select Customer from dropdown
		addDropdownValues(application, "Customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected);
		click(application, "Next", "Next_Button");

	}

	
	
	
	public void createorderservice(String application, String neworder, String neworderno, String newrfireqno, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(1000);

		//Warning messages verify
		warningMessage_commonMethod(application, "order_contractnumber_warngmsg", "Order/Contract Number(Parent SID)", xml);
		warningMessage_commonMethod(application, "servicetype_warngmsg", "Service Type", xml);

		if (neworder.equalsIgnoreCase("YES")) {

			Thread.sleep(2000);
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addtextFields_commonMethod(application, "Order/Contract Number", "newordertextfield", neworderno, xml);
			addtextFields_commonMethod(application, "RFI Voice line Number", "newrfireqtextfield", newrfireqno, xml);
			click_commonMethod(application, "create order", "createorderbutton", xml);
			//compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully", xml);			
			compareText(application, "Order Creation Success message", "OrderCreatedSuccessMsg", "Order created successfully", xml);
			ScrolltoElement(application, "CreateOrderHeader", xml);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;
		} 

		else if (existingorderservice.equalsIgnoreCase("YES")) {
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber, xml);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

			SelectOrderNumber = existingordernumber;
		} else {
			System.out.println("Order not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step :Order not selected");
			Log.info("Order not selected");
		}
	}

	
	
	public void OrderCreation_UI(String application, String neworder, String neworderno, String newrfireqno, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(1000);

		//Warning messages verify
		warningMessage_commonMethod(application, "order_contractnumber_warngmsg", "Order/Contract Number(Parent SID)", xml);
		warningMessage_commonMethod(application, "servicetype_warngmsg", "Service Type", xml);

		if (neworder.equalsIgnoreCase("YES")) {

			Thread.sleep(2000);
			isDisplayed(application, "selectorderswitch", "select order switch", xml);
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			isDisplayed(application, "newordertextfield", "Order/Contract Number(Parent SID)", xml);
			isDisplayed(application, "newrfireqtextfield", "RFI / RFQ /IP Voice Line number", xml);
			ScrolltoElement(application, "CreateOrderHeader", xml);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;
		} 

		else if (existingorderservice.equalsIgnoreCase("YES")) {
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber, xml);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

			SelectOrderNumber = existingordernumber;
		} else {
			System.out.println("Order not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step :Order not selected");
			Log.info("Order not selected");
		}
	}


	public void verifyCustomerDetailsInformation(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws InterruptedException, DocumentException, IOException {

		//**scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDetailsHeader")));
		
		implicitlyWait("Service screen");
		webdriverWait(application, "userspanel_header", xml);
		//scrollToTop();
		try {
			
		// verify Name information
			Thread.sleep(3000);
		compareText(application, "Legal Customer Name", "Name_Value", name);
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
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
		}
		
		Log.info("=== Customer Details panel fields Verified ===");
		//sa.assertAll();//Temp
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

		if(UserGrid.contains("height: 1px"))
		{
			//Cancel User
			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "Add", "AddLink");
			compareText(application, "Create User Header", "CreateUserHeader", "Create User page is displayed");
			EnterTextValue(application, Username, "User Name", "UserName");
			EnterTextValue(application, Firstname, "First Name", "FirstName");
			EnterTextValue(application, Surname, "SurName", "SurName");
			EnterTextValue(application, Postaladdress, "Postal Address", "PostalAddress");
			EnterTextValue(application, Email, "Email", "Email");
			EnterTextValue(application, Phone, "Phone", "Phone");

			WebElement CancelButton= getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton"));
			scrolltoview(CancelButton);
			click(application, "Cancel", "cancelbutton");
			compareText(application, "User panel Header", "userspanel_header", "Users panel in view service page is displayed");

			//Create User
			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "Add", "AddLink");
			compareText(application, "Create User Header", "CreateUserHeader", "Create User page is displayed");
			EnterTextValue(application, Username, "User Name", "UserName");
			EnterTextValue(application, Firstname, "First Name", "FirstName");
			EnterTextValue(application, Surname, "SurName", "SurName");
			EnterTextValue(application, Postaladdress, "Postal Address", "PostalAddress");
			EnterTextValue(application, Email, "Email", "Email");
			EnterTextValue(application, Phone, "Phone", "Phone");
			click(application, "Generate Password", "GeneratePassword");
			WebElement OKButton= getwebelement(xml.getlocator("//locators/" + application + "/OkButton"));
			scrolltoview(OKButton);
			click(application, "OK", "OkButton");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : User added successfully");

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

			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "view", "view");
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

			click(application, "Action dropdown", "UserActionDropdown");
			delete(application, "delete", "User", "User successfully deleted");
		}

		else if(!UserGrid.contains("1px"))
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
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/OkButton")));
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

			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "view", "view");
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

			click(application, "Action dropdown", "UserActionDropdown");
			delete("nginservice", "delete", "User", "User successfully deleted");

		}
	}


	
	public void verifyDumpFunctionInviewServicepage(String application) throws InterruptedException, DocumentException {
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		Thread.sleep(5000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		
			click_commonMethod(application, "Action", "serviceactiondropdown", xml);
		   Thread.sleep(3000);
		   click_commonMethod(application, "Dump", "DumpLink", xml);
		   Thread.sleep(2000);
		   
		   boolean DumpPage=false;
		   DumpPage=getwebelement(xml.getlocator("//locators/" + application + "/dumpPage_header")).isDisplayed();
		   if(DumpPage) {
			   DriverTestcase.logger.log(LogStatus.PASS, "Service Dump Detail page is displaying");
			   System.out.println("Service Dump Detail page is displaying");
			  
			  //Header 
			   String headerName=getwebelement(xml.getlocator("//locators/" + application + "/dumpheaderName")).getText();
			   if(headerName.isEmpty()) {
				   DriverTestcase.logger.log(LogStatus.FAIL, "Header name is not displaying");
				   System.out.println("Header name is not displaying");
				 
			   }else {
				   DriverTestcase.logger.log(LogStatus.PASS, "Dump header name is displaying as "+ headerName);  
				   System.out.println("Dump header name is displaying as "+ headerName);
			   }
			   
			   //body
			   String bodyContent=getwebelement(xml.getlocator("//locators/" + application + "/dumpMessage_body")).getText();
			   if(bodyContent.isEmpty()) {
				   DriverTestcase.logger.log(LogStatus.FAIL, "Dump value is not displaying");
				   System.out.println("Dump value is not displaying");
			   }else {
				   DriverTestcase.logger.log(LogStatus.PASS, "Dump value is displaying as "+bodyContent);
				   System.out.println("Dump value is displaying as "+bodyContent);
			   }
			   
			   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/dump_xButton")));
			   Thread.sleep(3000);
			   
				click(application, "Action dropdown", "serviceactiondropdown");
				compareText(application, "Edit Link", "EditLink", "Edit");
				compareText(application, "Delete Link", "DeleteLink", "Delete");
				compareText(application, "Show Infovista Report Link", "ShowInfovistaReportLink", "Show Infovista Report");
				compareText(application, "Show New Infovista Report Link", "ShowNewInfovistaReportLink", "Show New Infovista Report");
				compareText(application, "Manage Subnets Ipv6 Link", "ManageSubnetsIpv6Link", "Manage Subnets Ipv6");
				compareText(application, "Dump Link", "DumpLink", "Dump");
				click(application, "Action dropdown", "serviceactiondropdown");
		   }else {
			   DriverTestcase.logger.log(LogStatus.FAIL, "Service Dump Detail page is not displaying");
			   System.out.println("Service Dump Detail page is not displaying");

		   }
	}

	
	public void manageSubnet_viewServicepage(String application) throws InterruptedException, DocumentException {
		
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		Thread.sleep(5000);
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click_commonMethod(application, "Action", "serviceactiondropdown", xml);
		   Thread.sleep(3000);
		   click_commonMethod(application, "Manage Subnet IPv6", "ManageSubnetsIpv6Link", xml);
		   Thread.sleep(2000);
		   
		   boolean DumpPage=false;
		   DumpPage=getwebelement(xml.getlocator("//locators/" + application + "/dumpPage_header")).isDisplayed();
		   if(DumpPage) {
			   DriverTestcase.logger.log(LogStatus.PASS, "Manage Subnet IPv6 page is displaying");
			   System.out.println("manage subnet ipv6 page is displaying");
			  
			   try {
			  //Header 
			   String headerName=getwebelement(xml.getlocator("//locators/" + application + "/dumpheaderName")).getText();
			   if(headerName.isEmpty()) {
				   DriverTestcase.logger.log(LogStatus.FAIL, "Header name is not displaying");
				   System.out.println("Header name is not displaying");
				 
			   }else {
				   DriverTestcase.logger.log(LogStatus.PASS, "manage Subnet IPv6 header name is displaying as "+ headerName);  
				   System.out.println("manage subnet ipv6 header name is displaying as "+ headerName);
			   }
			   
			   //body
			   String bodyContent=getwebelement(xml.getlocator("//locators/" + application + "/manageSubnet_successMSG")).getText();
			   if(bodyContent.isEmpty()) {
				   DriverTestcase.logger.log(LogStatus.FAIL, "manage subnet value is not displaying");
				   System.out.println("manage subnet value is not displaying");
			   }else {
				   DriverTestcase.logger.log(LogStatus.PASS, "manage subnet message is displaying as "+bodyContent);
				   System.out.println("manage subnet message is displaying as "+bodyContent);
			   }
			   
			   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/dump_xButton")));
			   Thread.sleep(2000);
			   
		   }catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in manage subnet ipv6");
				System.out.println(  e+ " : Field is not displayed in manage subnet ipv6");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in manage subnet ipv6 ");
				System.out.println(  e+" : Field is not displayed in manage subnet ipv6");
			}
			
		   }else {
			   DriverTestcase.logger.log(LogStatus.FAIL, "manage subnet ipv6 page is not displaying");
			   System.out.println("manage subnet ipv6 page is not displaying");

		   }
		   
	}
	
	
	
	public void manageSubnet_viewServicepage_UI(String application) throws InterruptedException, DocumentException {
		
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		Thread.sleep(5000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
			Thread.sleep(4000);
			try {
			click_commonMethod(application, "Action", "serviceactiondropdown", xml);
		   Thread.sleep(3000);
		   click_commonMethod(application, "Manage Subnet IPv6", "ManageSubnetsIpv6Link", xml);
		   Thread.sleep(2000);
		   
		   boolean DumpPage=false;
		   DumpPage=getwebelement(xml.getlocator("//locators/" + application + "/dumpPage_header")).isDisplayed();
		   if(DumpPage) {
			   DriverTestcase.logger.log(LogStatus.PASS, "Manage Subnet IPv6 page is displaying");
			   System.out.println("manage subnet ipv6 page is displaying");
			  
			  //Header 
			   String headerName=getwebelement(xml.getlocator("//locators/" + application + "/dumpheaderName")).getText();
			   if(headerName.isEmpty()) {
				   DriverTestcase.logger.log(LogStatus.FAIL, "Header name is not displaying");
				   System.out.println("Header name is not displaying");
				 
			   }else {
				   DriverTestcase.logger.log(LogStatus.PASS, "manage Subnet IPv6 header name is displaying as "+ headerName);  
				   System.out.println("manage subnet ipv6 header name is displaying as "+ headerName);
			   }
			   
			   //body
			   String bodyContent=getwebelement(xml.getlocator("//locators/" + application + "/manageSubnet_successMSG")).getText();
			   if(bodyContent.isEmpty()) {
				   DriverTestcase.logger.log(LogStatus.FAIL, "manage subnet value is not displaying");
				   System.out.println("manage subnet value is not displaying");
			   }else {
				   DriverTestcase.logger.log(LogStatus.PASS, "manage subnet message is displaying as "+bodyContent);
				   System.out.println("manage subnet message is displaying as "+bodyContent);
			   }
			   
			   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/dump_xButton")));
			   Thread.sleep(2000);
			   
			   
		   }else {
			   DriverTestcase.logger.log(LogStatus.FAIL, "manage subnet ipv6 page is not displaying");
			   System.out.println("manage subnet ipv6 page is not displaying");

		   }
		   
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in manage subnet ipv6");
			System.out.println(  e+ " : Field is not displayed in manage subnet ipv6");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in manage subnet ipv6 ");
			System.out.println(  e+" : Field is not displayed in manage subnet ipv6");
		}
		
	}
	


	public void verifyManagementOptionspanel(String application, String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView
			,String PerformanceReporting,	String ProactiveNotification, String	NotificationManagementTeam, String	DialUserAdministration) throws InterruptedException, DocumentException, IOException {
		
			implicitlyWait("Service screen");
			webdriverWait(application, "servicepanel_header", xml);
			Thread.sleep(5000);	
		scrolltoview(application, "Management Options panel", "servicepanel_header", xml);		
		try {
		Boolean ManagementOptions_Header = getwebelement(xml.getlocator("//locators/" + application + "/managementoptions_header")).isDisplayed();
		Log.info("Management options header text is displayed as : " + ManagementOptions_Header);
		System.out.println("Management options header text:"+ ManagementOptions_Header);
		sa.assertTrue(ManagementOptions_Header,"Management Options");

		
		// verify Managed Service information
		compareText_InViewPage(application, "Managed Service", ManageService, xml);
		compareText_InViewPage(application, "Syslog Event View", SyslogEventView, xml);
		compareText_InViewPage(application, "Service Status View", ServiceStatusView, xml);
		compareText_InViewPage(application, "Router Configuration View", RouterConfigurationView, xml);
		compareText_InViewPage(application, "Performance Reporting", PerformanceReporting, xml);
		compareText_InViewPage(application, "Pro-active Notification", ProactiveNotification, xml);
		compareText_InViewPage(application, "Dial User Administration", DialUserAdministration, xml);
		if(ProactiveNotification.equalsIgnoreCase("Yes")) {
			compareText_InViewPage(application, "Notification Management Team", NotificationManagementTeam, xml);
		}
	
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
		}
		
	}

	
	
	
	


	public static String ResellerName;
	public void AddReseller(String application, String ocn, String email, String city, String streetname, String streetno, String pobox, String zipcode, String phone, String fax) throws InterruptedException, DocumentException, IOException {

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")));

		isDisplayed(application, "resellerheader", "Reseller Header", xml);
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
		isDisplayed(application, "manageresellerheader", "Manage Reseller in OSP", xml);
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
		isDisplayed(application, "manageresellerheader", "Manage Reseller in OSP", xml);
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
		compareText(application, "Customer panel header", "CustomerDetailsHeader", "Customer");
		click(application, "Action dropdown", "CustomerpanelActionDropdown");

		if(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Add link is displaying under Customer panel");
			Log.info("Add link is displayed");
		}
		else
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Add link is not displaying under Customer panel");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDetailsHeader")));

	}

	String Customername=null;
	public void AddCustomer(String application, String resellername, String defaultvalue, String configure, String country, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode) throws InterruptedException, DocumentException, IOException {


		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
		compareText(application, "Customer panel header", "CustomerDetailsHeader", "Customer");

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
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDetailsHeader")));

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

			SelectDropdownValueUnderDivTag(application, "Existing order", existingordernumber, "existingorderdropdown", "commonDropdownValueTag");
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

			System.out.println("new order not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : clicked on button to create new order");
		}

	}

	public void verifyselectservicetype(String application, String servicetype)
			throws InterruptedException, IOException, DocumentException {

		// select service type
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		// click on next button
		click(application, "Next", "nextbutton");
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service");
	}

	
	public void verifyselectservicetype_UI(String application, String servicetype)
			throws InterruptedException, IOException, DocumentException {

		// select service type
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		isDisplayed(application, "servicetypetextfield", "Service Type", xml);
		addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		// click on next button
		click(application, "Next", "nextbutton");
		isDisplayed(application, "createorderservice_header", "Create Order / Service Header", xml);
	}
	
	public void servicecreationfields(String application) throws InterruptedException, DocumentException {

		try {

			// service identification
			boolean serviceidentificationtextfield = getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")).isDisplayed();
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

	public void verifyingservicecreation(String application, String sid, String ResellerCode,  String Remarks, String EmailService, String PhoneService, 
			String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView, String PerformanceReporting, String ProactiveNotification,
			String NotificationManagementTeam, String DialUserAdministration, String orderno, String rfireqno, String servicetype) throws InterruptedException, IOException, DocumentException {
 
		if(getwebelement(xml.getlocator("//locators/" + application + "/createorderservice_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Servce Creation' page navigated as expected");
			System.out.println("'Servce Creation' page navigated as expected");
		
		try {
			
		Clear(getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")));
		
		WebElement OKbutton= getwebelement(xml.getlocator("//locators/" + application + "/OKbutton_ServiceCreation"));
		scrolltoview(OKbutton);
		
		click(application, "OK", "OKbutton_ServiceCreation");
		//Thread.sleep(1000);
		scrollToTop();
		warningMessage_commonMethod(application, "sidwarngmsg", "Service Identification", xml);
		
		// service identification
		Clear(getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")));
		EnterTextValue(application, sid, "Service Identification", "serviceidentificationtextfield");
		EnterTextValue(application, ResellerCode, "Reseller Code", "ResellerCodetextfield");
		
		// remarks
		EnterTextValue(application, Remarks, "Remarks", "remarktextarea");
		ScrolltoElement(application, "remarktextarea");
		EnterTextValue(application, EmailService, "Email", "emailtextfield");
		click(application, "Email Arrow", "emailarrow");
		EnterTextValue(application, PhoneService, "Phone Contact", "phonecontacttextfield");
		click(application, "Phone Contact Arrow", "phonearrow");
		
		
		//Package
		if(getwebelement(xml.getlocator("//locators/" + application + "/PackageDropdownDisabled")).isEnabled()){
			DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Package' Dropdown is Enabled");
			System.out.println("Step : 'Package' Dropdown is Enabled");
		}else {
			DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
			System.out.println("Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
		}

		
		// management options- Manage Service Checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ManageServicecheckbox")).isEnabled()){
			if (ManageService.equalsIgnoreCase("YES")) {
				click(application, "'Manage Service' checkbox", "ManageServicecheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
				System.out.println("Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Manage Service' checkbox is not Selected");
				System.out.println("Step : 'Manage Service' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		
		
		//Syslog Event View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/SyslogEventViewcheckbox")).isEnabled()){
			if (SyslogEventView.equalsIgnoreCase("YES")) {
				click(application, "'Syslog Event View' checkbox", "SyslogEventViewcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is not Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		//Service Status View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ServiceStatusViewcheckbox")).isEnabled()){
			if (ServiceStatusView.equalsIgnoreCase("YES")) {
				click(application, "'Service Status View' checkbox", "ServiceStatusViewcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
				System.out.println("Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Service Status View' checkbox is not Selected");
				System.out.println("Step : 'Service Status View' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		//Router Configuration View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/RouterConfigurationViewcheckbox")).isEnabled()){
			if (RouterConfigurationView.equalsIgnoreCase("YES")) {
				click(application, "'Router Configuration View' checkbox", "RouterConfigurationViewcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is not Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
	
		scrolltoend();
			//Performance Reporting checkbox
				if(getwebelement(xml.getlocator("//locators/" + application + "/PerformanceReportingcheckbox")).isEnabled()){
					if (PerformanceReporting.equalsIgnoreCase("YES")) {
						click(application, "'Performance Reporting' checkbox", "PerformanceReportingcheckbox");
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
					} else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is not Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is not Selected");
					}
				
					}else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
						System.out.println("Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
					}
				

		
		
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
		
		//Dial User Administration checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/DialUserAdministrationcheckbox")).isEnabled()){
			if (DialUserAdministration.equalsIgnoreCase("YES")) {
				click(application, "'Dial User Administration' checkbox", "DialUserAdministrationcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is not Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
				System.out.println("Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
			}

		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
			System.out.println(  e+ " : Field is not displayed in Service Creation page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
			System.out.println(  e+" : Field is not displayed in Service Creation page");
		}

		
				click(application, "OK", "okbutton");
				compareText(application, "Service creation success msg", "servicecreationmessage", "Service successfully created");
				
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Servce Creation' page not navigated");
			System.out.println("'Servce Creation' page navigated");
		}
				//sa.assertAll();//Temp
	}

	
	
	
	
	public void serviceCreationForm_UI(String application,String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView, String PerformanceReporting, String ProactiveNotification,
			String NotificationManagementTeam, String DialUserAdministration,String servicetype) throws InterruptedException, IOException, DocumentException {
 
		if(getwebelement(xml.getlocator("//locators/" + application + "/createorderservice_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Servce Creation' page navigated as expected");
			System.out.println("'Servce Creation' page navigated as expected");
		
		try {
			
		Clear(getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")));
		
		WebElement OKbutton= getwebelement(xml.getlocator("//locators/" + application + "/OKbutton_ServiceCreation"));
		scrolltoview(OKbutton);
		
		click(application, "OK", "OKbutton_ServiceCreation");
		scrollToTop();
		
		// service identification
		warningMessage_commonMethod(application, "sidwarngmsg", "Service Identification", xml);
		
		isDisplayed(application, "ServiceIdentificationTextlabel", "Service Identification", xml);
		isDisplayed(application, "ResellerCodetextfield", "Reseller Code", xml);
		isDisplayed(application, "remarktextarea", "Remarks", xml);
		ScrolltoElement(application, "remarktextarea");
		isDisplayed(application, "emailtextfield", "Email", xml);
		isDisplayed(application, "phonecontacttextfield", "Phone Contact", xml);
		
		//Package
		if(getwebelement(xml.getlocator("//locators/" + application + "/PackageDropdownDisabled")).isEnabled()){
			DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Package' Dropdown is Enabled");
			System.out.println("Step : 'Package' Dropdown is Enabled");
		}else {
			DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
			System.out.println("Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
		}

		
		// management options- Manage Service Checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ManageServicecheckbox")).isEnabled()){
			if (ManageService.equalsIgnoreCase("YES")) {
				click(application, "'Manage Service' checkbox", "ManageServicecheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
				System.out.println("Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Manage Service' checkbox is not Selected");
				System.out.println("Step : 'Manage Service' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		
		
		//Syslog Event View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/SyslogEventViewcheckbox")).isEnabled()){
			if (SyslogEventView.equalsIgnoreCase("YES")) {
				click(application, "'Syslog Event View' checkbox", "SyslogEventViewcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is not Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		//Service Status View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ServiceStatusViewcheckbox")).isEnabled()){
			if (ServiceStatusView.equalsIgnoreCase("YES")) {
				click(application, "'Service Status View' checkbox", "ServiceStatusViewcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
				System.out.println("Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Service Status View' checkbox is not Selected");
				System.out.println("Step : 'Service Status View' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		//Router Configuration View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/RouterConfigurationViewcheckbox")).isEnabled()){
			if (RouterConfigurationView.equalsIgnoreCase("YES")) {
				click(application, "'Router Configuration View' checkbox", "RouterConfigurationViewcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is not Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
	
		scrolltoend();
			//Performance Reporting checkbox
				if(getwebelement(xml.getlocator("//locators/" + application + "/PerformanceReportingcheckbox")).isEnabled()){
					if (PerformanceReporting.equalsIgnoreCase("YES")) {
						click(application, "'Performance Reporting' checkbox", "PerformanceReportingcheckbox");
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
					} else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is not Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is not Selected");
					}
				
					}else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
						System.out.println("Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
					}
				

		
		
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
		
		//Dial User Administration checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/DialUserAdministrationcheckbox")).isEnabled()){
			if (DialUserAdministration.equalsIgnoreCase("YES")) {
				click(application, "'Dial User Administration' checkbox", "DialUserAdministrationcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is not Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
				System.out.println("Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
			}
		
		isDisplayed(application, "okbutton", "OK", xml);
		isDisplayed(application, "cancelbutton", "Cancel", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
			System.out.println(  e+ " : Field is not displayed in Service Creation page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
			System.out.println(  e+" : Field is not displayed in Service Creation page");
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Servce Creation' page not navigated");
			System.out.println("'Servce Creation' page navigated");
		}
				sa.assertAll();//Temp
	}
	
	
	
	public void verifyorderpanelinformation_Existingorder(String application, String existingorder,
			String expectedorderno, String expectedvoicelineno) throws InterruptedException, DocumentException {
		
//		implicitlyWait("Service screen");
//		webdriverWait(application, "userspanel_header", xml);
		
		Thread.sleep(3000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));//orderpanelheader//userspanel_header

		if (getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Order Panel' page navigated as expected");
			System.out.println("'Order Panel' page navigated as expected");
			
			
		if (existingorder.equalsIgnoreCase("Yes")) {
			try {
			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue")).getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue")).getText();
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

			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
				System.out.println(  e+ " : Field is not displayed in Service Creation page");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
				System.out.println(  e+" : Field is not displayed in Service Creation page");
			}
			
		} else {

			System.out.println("existing order is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : existing order is not selected");
		}

		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Order panel header is not displayed");
			System.out.println("Order panel header is not displayed");
		}
		
		//sa.assertAll();//Temp
	}

	
	
	
	public void verifyorderpanelinformation_Neworder(String application, String neworder, String expectedneworderno,
			String expectednewvoicelineno) throws InterruptedException, DocumentException {
		implicitlyWait("Service screen");
		webdriverWait(application, "userspanel_header", xml);
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		try {
		if (neworder.equalsIgnoreCase("YES")) {

			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue")).getText();
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
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View Order page");
			System.out.println(  e+ " : Field is not displayed in  View Order page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View Order page ");
			System.out.println(  e+" : Field is not displayed in View Order page");
		}

		sa.assertAll();
	}


	
	public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno) throws InterruptedException, DocumentException, IOException {
		//Thread.sleep(5000);
		
		implicitlyWait("Service screen");
		webdriverWait(application, "userspanel_header", xml);
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));

		if(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Order' panel navigated as expected in view service page");
			System.out.println("'Order' panel navigated as expected in view service page");
		
			try {
		//Cancel Edit order in Order panel
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Edit Order", "editorderlink");
		if(getwebelement(xml.getlocator("//locators/" + application + "/editorderheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Order' Page navigated as expected");
			System.out.println("'Edit Order' Page navigated as expected");
			ClearAndEnterTextValue(application, "Order/Contract Number (Parent SID)", "editorderno", editorderno, xml);
			ClearAndEnterTextValue(application, "RFI/RFQ/IP Voice Line Number", "editvoicelineno", editvoicelineno, xml);
			click(application, "Cancel", "cancelbutton");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Edit Order' Page not navigated");
			System.out.println("'Edit Order' Page not navigated");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Order page");
			System.out.println(  e+ " : Field is not displayed in  Edit Order page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Edit Order page ");
			System.out.println(  e+" : Field is not displayed in Edit Order page");
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Order' panel not navigated in view service page");
			System.out.println("'Order' panel not navigated in view service page");
		}
		
			
		implicitlyWait10S("Service screen");
		webdriverWait(application, "userspanel_header", xml);
		
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));

		if(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Order' panel navigated as expected in view service page");
			System.out.println("'Order' panel navigated as expected in view service page");
		//Cancel Edit order in Order panel
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Edit Order", "editorderlink");
		if(getwebelement(xml.getlocator("//locators/" + application + "/editorderheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Order' Page navigated as expected");
			System.out.println("'Edit Order' Page navigated as expected");
			
			try {
			ClearAndEnterTextValue(application, "Order/Contract Number (Parent SID)", "editorderno", editorderno, xml);
			ClearAndEnterTextValue(application, "RFI/RFQ/IP Voice Line Number", "editvoicelineno", editvoicelineno, xml);
			click(application, "OK", "editorder_okbutton");
			compareText(application, "Order Edited/Updated message", "orderupdate_successmsg", "Order successfully updated");
			
			System.out.println("Step1 Edit33");
			implicitlyWait("Service screen");
			webdriverWait(application, "userspanel_header", xml);
			Thread.sleep(5000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			compareText(application, "Order Number", "ordernumbervalue", editorderno);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno);
			
			Log.info("------ Edit Order is successful ------");
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Order page");
			System.out.println(  e+ " : Field is not displayed in  Edit Order page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Edit Order page ");
			System.out.println(  e+" : Field is not displayed in Edit Order page");
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Edit Order' Page not navigated");
			System.out.println("'Edit Order' Page not navigated");
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Order' panel not navigated in view service page");
			System.out.println("'Order' panel not navigated in view service page");
		}
	}

	
	
	public void verifyorderpanel_changeorder(String application, String changeorderno, String changevoicelineno) throws InterruptedException, DocumentException, IOException {
		
		System.out.println("Step1 Change");
		implicitlyWait("Service screen");
		System.out.println("Step2");
		webdriverWait(application, "userspanel_header", xml);
		System.out.println("Step3");
		
		Thread.sleep(3000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		Thread.sleep(1000);

		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Change Order", "changeorderlink");

		if(getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Change Order' Page navigated as expected");
			System.out.println("'Change Order' Page navigated as expected");

			try {
		click(application, "Choose order dropdown", "changeorder_chooseorderdropdown");
		List<WebElement> ChangeOrder_DropdownList= driver.findElements(By.xpath(xml.getlocator("//locators/" + application + "/changeorder_dropdownlist")));
		int ChangeOrder_Dropdown_count= ChangeOrder_DropdownList.size();
		if(ChangeOrder_Dropdown_count> 1)
		{
			Clickon(getwebelement("(//label[text()='Order/Contract Number (Parent SID)']/parent::div//div[@role='list']//div/div/div/div)[1]"));
			Thread.sleep(3000);

			//Cancel change order
			click(application, "Cancel", "changeorder_cancelbutton");
			
			Thread.sleep(3000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader,"Order");
			Log.info("Navigated to order panel in view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

			//Change order
			click(application, "Action dropdown", "orderactionbutton");
			click(application, "Change Order", "changeorderlink");
			
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'Change Order' Page navigated as expected");
				System.out.println("'Change Order' Page navigated as expected");
			try {	
			click(application, "Choose order dropdown", "changeorder_chooseorderdropdown");
			Clickon(getwebelement("(//label[text()='Order/Contract Number (Parent SID)']/parent::div//div[@role='list']//div/div/div/div)[1]"));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected order from dropdown");

			click(application, "OK", "changeorder_okbutton");
			Thread.sleep(1000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader1,"Order");
			Log.info("Navigated to order panel in view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

			compareText(application, "Order Number", "ordernumbervalue", changeorderno);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno);
			Log.info("------ Change Order is successful ------");
			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Change Order page");
				System.out.println(  e+ " : Field is not displayed in  Change Order page");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Change Order page ");
				System.out.println(  e+" : Field is not displayed in Change Order page");
			}
			
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Change Order' Page not navigated");
				System.out.println("'Change Order' Page not navigated");
			}
		

		}
		else
		{
			click(application, "Select order switch", "changeorder_selectorderswitch");
			WebElement ChangeOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/changeordernumber"));
			click(application, "Order Number", "changeordernumber");
			Thread.sleep(2000);
			EnterTextValue(application, changeorderno, "Order Number", "changeordernumber");

			WebElement ChangeVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/changeordervoicelinenumber"));
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

			if(getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'Change Order' Page navigated as expected");
				System.out.println("'Change Order' Page navigated as expected");
			
				Thread.sleep(3000);
				try {
			click(application, "Select order switch", "changeorder_selectorderswitch");
			click(application, "Order Number", "changeordernumber");
			Thread.sleep(2000);
			EnterTextValue(application, changeorderno, "Order Number", "changeordernumber");
			click(application, "RFI Voice Line Number", "changeordervoicelinenumber");
			Thread.sleep(3000);
			EnterTextValue(application, changevoicelineno, "RFI Voice Line Number", "changeordervoicelinenumber");
			click(application, "Create Order", "createorder_button");
			Thread.sleep(5000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
			Thread.sleep(1000);
			Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader1,"Order");
			Log.info("Navigated to order panel in view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to order panel in view service page");

			compareText(application, "Order Number", "ordernumbervalue", changeorderno);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno);
			Log.info("------ Change Order is successful ------");
			
				}catch(NoSuchElementException e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Change Order page");
					System.out.println(  e+ " : Field is not displayed in  Change Order page");
				}catch(Exception e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Change Order page ");
					System.out.println(  e+" : Field is not displayed in Change Order page");
				}
				
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, "'Change Order' Page not navigated");
					System.out.println("'Change Order' Page not navigated");
				}
		}
		
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Change Order page");
				System.out.println(  e+ " : Field is not displayed in  Change Order page");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Change Order page ");
				System.out.println(  e+" : Field is not displayed in Change Order page");
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Change Order' Page not navigated");
			System.out.println("'Change Order' Page not navigated");
		}
	}
	
	
	

	public void verifyorderpanel_editorder_UI(String application, String editorderno, String editvoicelineno) throws InterruptedException, DocumentException, IOException {
		
		implicitlyWait("Service screen");
		webdriverWait(application, "userspanel_header", xml);
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		if(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Order' panel navigated as expected in view service page");
			System.out.println("'Order' panel navigated as expected in view service page");
			
		//Cancel Edit order in Order panel
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Edit Order", "editorderlink");
		if(getwebelement(xml.getlocator("//locators/" + application + "/editorderheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Order' Page navigated as expected");
			System.out.println("'Edit Order' Page navigated as expected");
			isDisplayed(application, "editorderno", "Order/Contract Number (Parent SID)", xml);
			isDisplayed(application, "editvoicelineno", "RFI/RFQ/IP Voice Line Number", xml);
//			compareText(application, "OK", "okbutton", "OK", xml);
//			compareText(application, "Cancel", "cancelbutton", "Cancel", xml);
			
			isDisplayed(application, "okbutton", "OK", xml);
			isDisplayed(application, "cancelbutton", "Cancel", xml);
			click(application, "Cancel", "cancelbutton");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Edit Order' Page not navigated");
			System.out.println("'Edit Order' Page not navigated");
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Order' panel not navigated in view service page");
			System.out.println("'Order' panel not navigated in view service page");
		}

	}

	
	
	public void verifyorderpanel_changeorder_UI(String application, String changeorderno, String changevoicelineno) throws InterruptedException, DocumentException, IOException {
		
		System.out.println("Step1 Change");
		implicitlyWait("Service screen");
		System.out.println("Step2");
		webdriverWait(application, "userspanel_header", xml);
		System.out.println("Step3");
		
		Thread.sleep(3000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Order' panel navigated as expected in view service page");
			System.out.println("'Order' panel navigated as expected in view service page");
			
		//Cancel Change order in Order panel
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Change Order", "changeorderlink");	
		try {
		if(getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Change Order' Page navigated as expected");
			System.out.println("'Change Order' Page navigated as expected");
			
			isDisplayed(application, "RFIRFQIPVoiceLineNumberText", "RFI/RFQ/IP Voice Line Number", xml);
			isDisplayed(application, "changeorder_chooseorderdropdown", "Order/Contract Number (Parent SID)", xml);
			isDisplayed(application, "okbutton", "OK", xml);
			isDisplayed(application, "cancelbutton", "Cancel", xml);
			
			click(application, "Select order switch", "changeorder_selectorderswitch");
			Thread.sleep(2000);
			isDisplayed(application, "editorderno", "Order/Contract Number (Parent SID)", xml);
			isDisplayed(application, "editvoicelineno", "RFI/RFQ/IP Voice Line Number", xml);
			click(application, "Cancel","cancelbutton");//"changeorder_cancelbutton");////body//button[span[contains(text(),'Cancel')]]
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Change Order' Page not navigated");
			System.out.println("'Change Order' Page not navigated");
		}
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
			}
		}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'Order' Panel not navigated in view service page");
		System.out.println("'Order' Panel not navigated in view service page");
		}
	}
	
	
	
	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String ResellerCode ,
			String Remarks , String EmailService, String PhoneService) throws InterruptedException, DocumentException, IOException {
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		
		try {
			
		compareText(application, "Service panel Header", "servicepanel_header", "Service");
		compareText(application, "Service Identification", "servicepanel_serviceidentificationvalue", sid);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype);
		compareText(application, "Reseller Code", "servicepanel_ResselerCodevalue", ResellerCode);
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks);
		compareText(application, "Email", "servicepanel_Emailvalue", EmailService);
		compareText(application, "Phone Contact", "servicepanel_PhoneContactvalue", PhoneService);
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
		}
		
	}
	
	
	
	

	public void editService(String application, String EditRemarks, String Remarks, 
			String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, 
			String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {

		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		
		try { 
		
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		cleartext(application, "Remarks", "remarktextarea");
		EnterTextValue(application, EditRemarks, "Remarks", "remarktextarea");
		scrolltoend();
		click(application, "Cancel", "cancelbutton");
		Thread.sleep(2000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks);	
		}
		else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
		}
		
		//Edit service
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		cleartext(application, "Remarks", "remarktextarea");
		EnterTextValue(application, EditRemarks, "Remarks", "remarktextarea");
		
		scrolltoend();
		click(application, "OK", "OKbutton_ServiceCreation");
		
		Thread.sleep(2000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDetailsHeader")));
		if(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDetailsHeader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			compareText(application, "Service updated success message", "serviceupdate_successmsg", "Service successfully updated");	
		}
		else
		{
			Log.info("Service not updated");
			System.out.println("Service not updated");
		}
		
		
		
		searchorder(application, sid);//ByMe
		//Verify all the links available in service actions list
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		Thread.sleep(1000);
		click(application, "Action dropdown", "serviceactiondropdown");
		compareText(application, "Edit Link", "EditLink", "Edit");
		compareText(application, "Delete Link", "DeleteLink", "Delete");
		compareText(application, "Show Infovista Report Link", "ShowInfovistaReportLink", "Show Infovista Report");
		compareText(application, "Show New Infovista Report Link", "ShowNewInfovistaReportLink", "Show New Infovista Report");
		compareText(application, "Manage Subnets Ipv6 Link", "ManageSubnetsIpv6Link", "Manage Subnets Ipv6");
		compareText(application, "Dump Link", "DumpLink", "Dump");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
		}
		
		//Service delete is performed in the last test case
	}

	
	public void verifyServicePanel_Actions_UI(String application, String sid, String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView, String PerformanceReporting, String ProactiveNotification,
			String NotificationManagementTeam, String DialUserAdministration,String servicetype) throws InterruptedException, IOException, DocumentException {
 
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
			
		if(getwebelement(xml.getlocator("//locators/" + application + "/EditService_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Service' page navigated as expected");
			System.out.println("'Edit Service' page navigated as expected");
		
		try {
		// service identification
		isDisplayed(application, "serviceidentificationtextfield", "Service Identification", xml);
		isDisplayed(application, "ResellerCodetextfield", "Reseller Code", xml);
		isDisplayed(application, "remarktextarea", "Remarks", xml);
		ScrolltoElement(application, "remarktextarea");
		isDisplayed(application, "emailtextfield", "Email", xml);
		isDisplayed(application, "phonecontacttextfield", "Phone Contact", xml);
//		compareText_fromtextFields(application, "Email", "emailtextfield", "Email", xml);
//		compareText_fromtextFields(application, "Phone Contact", "phonecontacttextfield", "Phone Contact", xml);
		
		
		//Package
		if(getwebelement(xml.getlocator("//locators/" + application + "/PackageDropdownDisabled")).isEnabled()){
			DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Package' Dropdown is Enabled");
			System.out.println("Step : 'Package' Dropdown is Enabled");
		}else {
			DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
			System.out.println("Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
		}

		// management options- Manage Service Checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ManageServicecheckbox")).isEnabled()){
			if (ManageService.equalsIgnoreCase("YES")) {
				click(application, "'Manage Service' checkbox", "ManageServicecheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
				System.out.println("Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Manage Service' checkbox is not Selected");
				System.out.println("Step : 'Manage Service' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		//Syslog Event View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/SyslogEventViewcheckbox")).isEnabled()){
			if (SyslogEventView.equalsIgnoreCase("YES")) {
				click(application, "'Syslog Event View' checkbox", "SyslogEventViewcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is not Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		//Service Status View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ServiceStatusViewcheckbox")).isEnabled()){
			if (ServiceStatusView.equalsIgnoreCase("YES")) {
				click(application, "'Service Status View' checkbox", "ServiceStatusViewcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
				System.out.println("Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Service Status View' checkbox is not Selected");
				System.out.println("Step : 'Service Status View' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		//Router Configuration View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/RouterConfigurationViewcheckbox")).isEnabled()){
			if (RouterConfigurationView.equalsIgnoreCase("YES")) {
				click(application, "'Router Configuration View' checkbox", "RouterConfigurationViewcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is not Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
	
			scrolltoend();
			//Performance Reporting checkbox
				if(getwebelement(xml.getlocator("//locators/" + application + "/PerformanceReportingcheckbox")).isEnabled()){
					if (PerformanceReporting.equalsIgnoreCase("YES")) {
						click(application, "'Performance Reporting' checkbox", "PerformanceReportingcheckbox");
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
					} else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is not Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is not Selected");
					}
				
					}else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
						System.out.println("Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
					}
				

		
		
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
		
		//Dial User Administration checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/DialUserAdministrationcheckbox")).isEnabled()){
			if (DialUserAdministration.equalsIgnoreCase("YES")) {
				click(application, "'Dial User Administration' checkbox", "DialUserAdministrationcheckbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is not Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
				System.out.println("Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
			}
		
		compareText(application, "OK", "okbutton", "OK", xml);
		compareText(application, "Cancel", "cancelbutton", "Cancel", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
			System.out.println(  e+ " : Field is not displayed in Service Creation page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
			System.out.println(  e+" : Field is not displayed in Service Creation page");
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Edit Service' page not navigated");
			System.out.println("'Edit Service' page navigated");
		}
		
	}

	
	
	
	
	public void manageServiceFunctionTest(String application,String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, 
			String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		
		try {
		//Manage service
				Thread.sleep(2000);
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
				click(application, "Action dropdown", "serviceactiondropdown");
				click(application, "Manage", "manageLink");
				compareText(application, "Manage service header", "manageservice_header", "Manage Service");
				compareText(application, "Order Name", "status_ordername", changeorderno);
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
				if(LastModificationTime_value.contains("BST"))
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

				if(servicestatuschangerequired=="Yes")
				{
					WebElement ServiceStatusPage= getwebelement(xml.getlocator("//locators/" + application + "/Servicestatus_popup"));
					if(ServiceStatusPage.isDisplayed())
					{
						click(application, "Change Status", "changestatus_dropdown");
						click(application, "Change Status value", "changestatus_dropdownvalue");
						click(application, "OK", "okbutton");
						WebElement ServiceStatusHistory= getwebelement(xml.getlocator("//locators/" + application + "/servicestatushistory"));
						try
						{
							if(ServiceStatusHistory.isDisplayed())
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
					click(application, "Close", "servicestatus_popupclose");
				}

				
				
				
				//synchronize panel in manage service page

				compareText(application, "Order Name", "sync_ordername", changeorderno);
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

			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Manage Service page");
			System.out.println(  e+ " : Field is not displayed in Manage Service page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Manage Service page ");
			System.out.println(  e+" : Field is not displayed in Manage Service page");
		}
		
	}

	
	
	
	
	public void manageServiceFunctionTest_UI(String application,String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, 
			String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		
		try {
		//Manage service
				implicitlyWait("Service screen");
				webdriverWait(application, "orderpanelheader", xml);
				Thread.sleep(5000);
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
				click(application, "Action dropdown", "serviceactiondropdown");
				click(application, "Manage", "manageLink");
				compareText(application, "Manage service header", "manageservice_header", "Manage Service");
				compareText(application, "Order Name", "status_ordername", changeorderno);
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
				if(LastModificationTime_value.contains("BST"))
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

				if(servicestatuschangerequired=="Yes")
				{
					WebElement ServiceStatusPage= getwebelement(xml.getlocator("//locators/" + application + "/Servicestatus_popup"));
					if(ServiceStatusPage.isDisplayed())
					{
						click(application, "Change Status", "changestatus_dropdown");
						click(application, "Change Status value", "changestatus_dropdownvalue");
						click(application, "OK", "okbutton");
						WebElement ServiceStatusHistory= getwebelement(xml.getlocator("//locators/" + application + "/servicestatushistory"));
						try
						{
							if(ServiceStatusHistory.isDisplayed())
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
					click(application, "Close", "servicestatus_popupclose");
				}

				
				
				
				//synchronize panel in manage service page

				compareText(application, "Order Name", "sync_ordername", changeorderno);
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

			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Manage Service page");
			System.out.println(  e+ " : Field is not displayed in Manage Service page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Manage Service page ");
			System.out.println(  e+" : Field is not displayed in Manage Service page");
		}
		
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
		isDisplayed(application, "nginmsg_sannumber", "SAN Number text field", xml);
		isDisplayed(application, "nginmsg_customername_textfield", "Customer Name text field", xml);
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
	
	
	
	
	
	public void verifyAddMASswitch(String application, String MAS_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		WebElement ProviderEquipment_header= getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header"));
		scrolltoview(ProviderEquipment_header);
		click(application, "Add MAS Switch Link", "MAS_AddMASSwitchLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_AddMASSwitch_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Add MAS Switch' page navigated as expected");
			System.out.println("'Add MAS Switch' page navigated as expected");
			
		try {
		compareText(application, "Add MAS Switch header", "MAS_AddMASSwitch_header", "Add MAS Switch");

		click(application, "OK button", "MAS_OKbutton");//
		compareText(application, "Add Switch Warning Message for MAS Switch", "MAS_AddSwitchWarningMessage", "IMS POP Location is required");//

		addDropdownValues_commonMethod(application, "IMS POP Location", "MAS_IMSPOPLocationDropdown", MAS_IMSPOPLocation, xml);
		click(application, "OK button", "MAS_OKbutton");
		compareText(application, "Add Switch Successful Message for MAS Switch", "MAS_AddSwitchSuccessfulMessage", "MAS switch added successfully");
		DriverTestcase.logger.log(LogStatus.PASS, "MAS switch added successfully");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Add MAS Switch page");
			System.out.println(  e+ " : Field is not displayed in Add MAS Switch page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Add MAS Switch page ");
			System.out.println(  e+" : Field is not displayed in Add MAS Switch page");
		}

		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Add MAS Switch' page not navigated");
			System.out.println("'Add MAS Switch' page not navigated");
		}
		
		
		Log.info("------ MAS Switch added successfully ------");
	}

	
	
	public void verifyAddMASswitch_UI(String application, String MAS_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_header")));//portalaccess_header//ProviderEquipment_header
		click(application, "Add MAS Switch Link", "MAS_AddMASSwitchLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_AddMASSwitch_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Add MAS Switch' page navigated as expected");
			System.out.println("'Add MAS Switch' page navigated as expected");
			
		try {

		click(application, "OK button", "MAS_OKbutton");//
		compareText(application, "Add Switch Warning Message for MAS Switch", "MAS_AddSwitchWarningMessage", "IMS POP Location is required");//
		isDisplayed(application, "MAS_IMSPOPLocationDropdown", "IMS POP Location", xml);
		click(application, "Cancel", "cancelbutton");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Add MAS Switch page");
			System.out.println(  e+ " : Field is not displayed in Add MAS Switch page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Add MAS Switch page ");
			System.out.println(  e+" : Field is not displayed in Add MAS Switch page");
		}

		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Add MAS Switch' page not navigated");
			System.out.println("'Add MAS Switch' page not navigated");
		}
		
		
	}

	
	public void verifyAddedMASswitchInformation_View(String application, String MAS_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		//**scrolltoview(application, "MAS Switch Header", "MASswitch_header", xml);//ProviderEquipment_header
		//**click(application, "MAS View Link", "MAS_viewdevice1");

		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View MAS Switch' page navigated as expected");
			System.out.println("'View MAS Switch' page navigated as expected");
			
		try {
		// Verify All Device Information under Device panel for MAS Switch
		GetText(application, "Device Name", "MAS_View_DeviceNameValue");
		GetText(application, "Vendor/Model", "MAS_View_VendorModelValue");
		GetText(application, "Management Address", "MAS_View_ManagementAddressValue");
		GetText(application, "Snmpro", "MAS_View_SnmproValue");
		GetText(application, "Country", "MAS_View_CountryValue");
		GetText(application, "City", "MAS_View_CityValue");
		GetText(application, "Site", "MAS_View_SiteValue");
		GetText(application, "Premise", "MAS_View_PremiseValue");
		GetText(application, "Test", "MAS_View_TestColumnName");
		GetText(application, "Status", "MAS_View_StatusColumnName");
		GetText(application, "Last Refresh", "MAS_View_LastRefresh");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View MAS Switch page");
			System.out.println(  e+ " : Field is not displayed in View MAS Switch page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View MAS Switch page ");
			System.out.println(  e+" : Field is not displayed in View MAS Switch page");
		}

		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View MAS Switch' page not navigated");
			System.out.println("'View MAS Switch' page not navigated");
		}
		
		
		Log.info("------ Verified Added MAS Switch Information successfully ------");
	}

	
	
	
	public void verifyAddedMASswitchInformation_View_UI(String application, String MAS_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		//**scrolltoview(application, "MAS Switch Header", "MASswitch_header", xml);//ProviderEquipment_header
		//**click(application, "MAS View Link", "MAS_viewdevice1");

		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View MAS Switch' page navigated as expected");
			System.out.println("'View MAS Switch' page navigated as expected");
			
		try {
		// Verify All Device Information under Device panel for MAS Switch
		GetText(application, "Device Name", "MAS_View_DeviceNameValue");
		GetText(application, "Vendor/Model", "MAS_View_VendorModelValue");
		GetText(application, "Management Address", "MAS_View_ManagementAddressValue");
		GetText(application, "Snmpro", "MAS_View_SnmproValue");
		GetText(application, "Country", "MAS_View_CountryValue");
		GetText(application, "City", "MAS_View_CityValue");
		GetText(application, "Site", "MAS_View_SiteValue");
		GetText(application, "Premise", "MAS_View_PremiseValue");
		GetText(application, "Test", "MAS_View_TestColumnName");
		GetText(application, "Status", "MAS_View_StatusColumnName");
		GetText(application, "Last Refresh", "MAS_View_LastRefresh");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View MAS Switch page");
			System.out.println(  e+ " : Field is not displayed in View MAS Switch page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View MAS Switch page ");
			System.out.println(  e+" : Field is not displayed in View MAS Switch page");
		}

		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View MAS Switch' page not navigated");
			System.out.println("'View MAS Switch' page not navigated");
		}
		
	}

	
	
	public void verifyEditMASswitchFunction(String application, String ServiceIdentification,  String MAS_DeviceName_Edit, String MAS_VendorModelEdit, String MAS_ManagementAddressEdit, String MAS_SnmproEdit, String MAS_CountryEdit, String MAS_CityEdit, String MAS_SiteEdit, String MAS_PremiseEdit) throws InterruptedException, DocumentException, IOException {

		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View MAS Device' page navigated as expected");
			System.out.println("'View MAS Device' page navigated as expected");
			
			click(application, "ACTION link", "MAS_View_ActionLink");
			click(application, "Edit Link", "MAS_View_Action_EditLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/Edit_MASSwitch_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit MAS Device' page navigated as expected");
			System.out.println("'Edit MAS Device' page navigated as expected");
			
			try { 	
			
		//**Need to recheck ClearAndEnterTextValue(application, "Snmpro textfield", "MAS_Snmprotextfield", MAS_SnmproEdit);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Edit_PremiseLevel")));
		click(application, "OK in Edit MAS Device", "MAS_OKbutton");
		//Thread.sleep(5000);
		compareText(application, "MAS Switch Update message", "MAS_UpdateSwitchSuccessfulMessage", "MAS switch updated successfully");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Edit MAS Device page");
			System.out.println(  e+ " : Field is not displayed in Edit MAS Device page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Edit MAS Device page ");
			System.out.println(  e+" : Field is not displayed in Edit MAS Device page");
		}

		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Edit MAS Switch' page not navigated");
			System.out.println("'Edit MAS Switch' page not navigated");	
		}
		
		Log.info("------  MAS switch updated successfully   ------");
		
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View MAS Device' page not navigated");
			System.out.println("'View MAS Device' page navigated");
		}
		
		
	}

	
	
	
	public void verifyEditMASswitch_UI(String application, String ServiceIdentification,  String MAS_DeviceName_Edit, String MAS_VendorModelEdit, String MAS_ManagementAddressEdit, String MAS_SnmproEdit, String MAS_CountryEdit, String MAS_CityEdit, String MAS_SiteEdit, String MAS_PremiseEdit) throws InterruptedException, DocumentException, IOException {
		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View MAS Device' page navigated as expected");
			System.out.println("'View MAS Device' page navigated as expected");
			
			click(application, "ACTION link", "MAS_View_ActionLink");
			click(application, "Edit Link", "MAS_View_Action_EditLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/Edit_MASSwitch_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit MAS Device' page navigated as expected");
			System.out.println("'Edit MAS Device' page navigated as expected");
			
			try { 	
			
		//**Need to recheck ClearAndEnterTextValue(application, "Snmpro textfield", "MAS_Snmprotextfield", MAS_SnmproEdit);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Edit_PremiseLevel")));
		click(application, "Cancel", "MAS_Cancelbutton");
		//Thread.sleep(5000);
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Edit MAS Device page");
			System.out.println(  e+ " : Field is not displayed in Edit MAS Device page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Edit MAS Device page ");
			System.out.println(  e+" : Field is not displayed in Edit MAS Device page");
		}

		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Edit MAS Switch' page not navigated");
			System.out.println("'Edit MAS Switch' page not navigated");	
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View MAS Device' page not navigated");
			System.out.println("'View MAS Device' page navigated");
		}
	}
	
	
	
	public void veriyFetchDeviceInterfacesFunction_MAS(String application,String ServiceIdentification, String MAS_ServiceStatusChangeRequired) throws InterruptedException, DocumentException, IOException {
		scrollToTop();
		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View MAS Device' page navigated as expected");
			System.out.println("'View MAS Device' page navigated as expected");
		
		click(application, "ACTION link", "MAS_View_ActionLink");
		click(application, "Fetch Device Interfaces Link", "MAS_View_Action_FetchDeviceInterfacesLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_FetchDeviceInterfacesSuccessMessage")).isDisplayed()){
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
			System.out.println("Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			System.out.println("Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
		}
		
		click(application, "Click here Link for MAS", "MAS_hereLink_UnderFetchDeviceInterfacesSuccessMessage");
		Thread.sleep(2000);
		
		//Manage COLT's Network - Manage Network
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_ManageCOLTsNetworkManageNetwork_header")).isDisplayed()) {
		DriverTestcase.logger.log(LogStatus.PASS, "'Manage COLT's Network - Manage Network' page navigated as expected");
		System.out.println("'Manage COLT's Network - Manage Network' page navigated as expected");
					
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Manage_Synchronization_SynchronizeLink")));
				
		try {
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
			System.out.println("Last Modification is :"+ MAS_Manage_Status_LastModificationValue);
		}else{
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
				

			Log.info("------  Sync started successfully. Please check the sync status of this device   ------");
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Manage Colt Network page");
			System.out.println(  e+ " : Field is not displayed in Manage Colt Network page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Manage Colt Network page ");
			System.out.println(  e+" : Field is not displayed in Manage Colt Network page");
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Manage COLT's Network - Manage Network' page not navigated");
			System.out.println("'Manage COLT's Network - Manage Network' page not navigated");
		}
	
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View MAS Device' page not navigated");
			System.out.println("'View MAS Device' page not navigated");
			}
		
	}
	
	
	
	
	
	////
	
	public void verifyRouterToolFunction_MAS(String application,String ServiceIdentification, String MAS_CommandIPV4, String MAS_CommandIPV6, String MAS_ManagementAddress) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		
		WebElement serviceIdLink=getwebelement("//span[text()='"+ ServiceIdentification +"']");
		Clickon(serviceIdLink);
		Thread.sleep(5000);


		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header")));
		click(application, "View Link", "MAS_viewdevice1");
		Thread.sleep(10000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/RouterTool_header")));
		compareText(application, "Router Tools header", "RouterTool_header", "Router Tools");
		
		
		
			// Select  MAS_CommandIPV4 dropdown
		SelectDropdownValueUnderDivTag(application, "Router IPV4 Command", MAS_CommandIPV4, "MAS_PE_Router_IPV4CommandsDropdown", "commonDropdownValueTag");
		EnterTextValue(application, MAS_ManagementAddress, "Commands IPV4", "MAS_PE_Router_IPV4CommandTextfield");
		click(application, "Execute button", "MAS_PE_Router_IPV4Command_Executebutton");
				
				
		
			// Select  MAS_CommandIPV6 dropdown
		SelectDropdownValueUnderDivTag(application, "Router IPV6 Command", MAS_CommandIPV6, "MAS_PE_Router_IPV6CommandsDropdown", "commonDropdownValueTag");
		EnterTextValue(application, MAS_ManagementAddress, "Commands IPV6", "MAS_PE_Router_IPV6CommandTextfield");
		click(application, "Execute button", "MAS_PE_Router_IPV6Command_Executebutton");
}
	
	
	
	
	
	////
	public void verifyDeleteDeviceFunction_MAS(String application,String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
	
		//Delete MAS Device from View Device Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/portalaccess_header")));//ProviderEquipment_header//portalaccess_header//managementoptions_header
		click(application, "View Link", "MAS_viewdevice1"); 
		
		scrollToTop();
		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View MAS Device' page navigated as expected");
			System.out.println("'View MAS Device' page navigated as expected");
			
		click(application, "ACTION link", "MAS_View_ActionLink");
		click(application, "Delete Device from View Device page", "MAS_View_Action_DeleteLink");
		compareText(application, "Delete MAS Device Warning Message from View Device page", "MAS_ViewDevice_Action_DeleteMASDeviceWarningMessage", "Are you sure that you want to delete this item?");
		click(application, "Delete Button", "MAS_ViewDevice_Action_DeleteButton");
		//Thread.sleep(3000);
		compareText(application, "Delete MAS Device Successful Message", "MAS_DeleteSwitchSuccessfulMessage", "MAS switch deleted successfully");
		
		
		//Delete from Service "From View Service page"
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/portalaccess_header")));
		click(application, "Delete From Service Link", "MAS_deletefromservicedevice1");
		compareText(application, "Delete MAS Device Warning Message from View Service page", "MAS_ViewService_DeleteMASDeviceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "MAS_ViewService_DeleteButton");
		//Thread.sleep(3000);
		compareText(application, "Delete MAS Device Successfull Message", "MAS_DeleteSwitchSuccessfulMessage", "MAS switch deleted successfully");
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View MAS Device' page not navigated");
			System.out.println("'View MAS Device' page not navigated");
		}
		
	}
	
	
	
	
	
	public void verifyAddInterfaceFunction_MAS(String application, String MAS_AccessMedia, String MAS_Network,
			String MAS_HSRPBGP, String MAS_GenerateConfiguration, String	MAS_Interface, String MAS_InterfaceAddressRange,
			String MAS_InterfaceAddressMask, String	MAS_HSRPIP, String	MAS_InterfaceAddressRangeIPV6, String MAS_HSRPIPv6Address, 
			String	MAS_PrimaryIPv6onMas1, String MAS_SecondaryIPv6onMas2, String MAS_GroupNumber, String MAS_Link, String MAS_VLANID,
			String	MAS_IVManagement, String MAS_Configuration, String MAS_HSRPTrackInterface, String MAS_HSRPAuthentication) throws InterruptedException, DocumentException, IOException {
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_CommandIPV4_header")));//RouterTool_header//MAS_PE_CommandIPV4_header//MAS_PE_InterfacesPanel_header
		click(application, "ACTION Link", "MAS_View_InterfacesActionLink");
		click(application, "Add Interface Link", "MAS_PE_AddInterfaceLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_AddInterface_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'MAS Add Interface' page navigated as expected");
			System.out.println("'MAS Add Interface' page navigated as expected");
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
		
		try {
		click(application, "OK Button", "MAS_PE_OKButton");
		
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface is required", "MAS_PE_InterfaceWarningMessage");
 
		
		
		if(MAS_AccessMedia.equalsIgnoreCase("VPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMedia);
			
			EnterTextValue(application, MAS_Interface, "Interface textfield", "MAS_PE_InterfaceTextfield");
			EnterTextValue(application, MAS_InterfaceAddressRange, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield");
			EnterTextValue(application, MAS_InterfaceAddressMask, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield");
			//EnterTextValue(application, MAS_HSRPIP, "HSRP IP textfield", "MAS_PE_HSRPIPTextfield");
			EnterTextValue(application, MAS_InterfaceAddressRangeIPV6, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield");
			EnterTextValue(application, MAS_HSRPIPv6Address, "HSRP IPv6 Address textfield", "MAS_PE_HSRPIPv6AddressTextfield");
			EnterTextValue(application, MAS_PrimaryIPv6onMas1, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield");
			EnterTextValue(application, MAS_SecondaryIPv6onMas2, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield");
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			EnterTextValue(application, MAS_GroupNumber, "Group Number textfield", "MAS_PE_GroupNumberTextfield");
			EnterTextValue(application, MAS_Link, "Link textfield", "MAS_PE_LinkTextfield");
			EnterTextValue(application, MAS_VLANID, "Interface textfield", "MAS_PE_VLANIDTextfield");
			//Thread.sleep(2000);
			
//			//IV Management Checkbox
//			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")).isEnabled()){
//				if (MAS_IVManagement.equalsIgnoreCase("YES")) {
//					click(application, "'IV Management' checkbox", "MAS_PE_IVManagementCheckbox");
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//				} else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
//					System.out.println("Step : 'IV Management' checkbox is not Selected");
//				}
//			
//				}else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}

			addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", MAS_IVManagement, "No", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			//Thread.sleep(2000);
			

			EnterTextValue(application, MAS_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ MAS_GenerateConfiguration +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);

			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully added by selecting VPN as Access Media");
			
		}else if(MAS_AccessMedia.equalsIgnoreCase("EPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMedia);

			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectMAS_AccessMediaDropdownValue=getwebelement("//div[text()='"+ MAS_AccessMedia +"']");
			Clickon(SelectMAS_AccessMediaDropdownValue);
			
			EnterTextValue(application, MAS_Interface, "Interface textfield", "MAS_PE_InterfaceTextfield");
			
			SelectDropdownValueUnderDivTag(application, "HSRP BGP Dropdown", MAS_HSRPBGP, "MAS_PE_HSRPBGPDropdown", "commonDropdownValueTag");
			
//			EnterTextValue(application, MAS_InterfaceAddressRange, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield");
			EnterTextValue(application, MAS_InterfaceAddressMask, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield");
//			EnterTextValue(application, MAS_HSRPIP, "HSRP IP textfield", "MAS_PE_HSRPIPTextfield");
//			EnterTextValue(application, MAS_InterfaceAddressRangeIPV6, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield");
			
//			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
//			EnterTextValue(application, MAS_HSRPIPv6Address, "HSRP IPv6 Address textfield", "MAS_PE_HSRPIPv6AddressTextfield");
//			EnterTextValue(application, MAS_PrimaryIPv6onMas1, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield");
//			EnterTextValue(application, MAS_SecondaryIPv6onMas2, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield");
			EnterTextValue(application, MAS_GroupNumber, "Group Number textfield", "MAS_PE_GroupNumberTextfield");
			EnterTextValue(application, MAS_Link, "Link textfield", "MAS_PE_LinkTextfield");
			EnterTextValue(application, MAS_VLANID, "Interface textfield", "MAS_PE_VLANIDTextfield");

//			//IV Management Checkbox
//			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")).isEnabled()){
//				if (MAS_IVManagement.equalsIgnoreCase("YES")) {
//					click(application, "'IV Management' checkbox", "MAS_PE_IVManagementCheckbox");
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//				} else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
//					System.out.println("Step : 'IV Management' checkbox is not Selected");
//				}
//			
//				}else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}

			addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", MAS_IVManagement, "No", xml);
		
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			//Thread.sleep(2000);
			
			EnterTextValue(application, MAS_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ MAS_GenerateConfiguration +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);

			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully added by selecting EPN as Access Media");
			Log.info("------ Interface Added successfully ------");
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Add Interface page");
			System.out.println(  e+ " : Field is not displayed in  Add Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in  Add Interface page ");
			System.out.println(  e+" : Field is not displayed in  Add Interface page");
		}

		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'MAS Add Interface' page not navigated");
			System.out.println("'MAS Add Interface' page not navigated");
		}
		sa.assertAll();
		
	}


	
	
	
	
	public void verifyEditInterfaceFunction_MAS(String application, String ServiceIdentification, String MAS_AccessMediaEdit, String MAS_NetworkEdit,
			String MAS_HSRPBGPEdit, String MAS_GenerateConfigurationEdit, String	MAS_InterfaceEdit, String MAS_InterfaceAddressRangeEdit,
			String MAS_InterfaceAddressMaskEdit, String	MAS_HSRPIPEdit, String	MAS_InterfaceAddressRangeIPV6Edit, String MAS_HSRPIPv6AddressEdit, 
			String	MAS_PrimaryIPv6onMas1Edit, String MAS_SecondaryIPv6onMas2Edit, String MAS_GroupNumberEdit, String MAS_LinkEdit, String MAS_VLANIDEdit,
			String	MAS_IVManagementEdit, String MAS_ConfigurationEdit, String MAS_HSRPTrackInterfaceEdit, String MAS_HSRPAuthenticationEdit) throws InterruptedException, DocumentException, IOException {
		
		

		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/portalaccess_header")));//ProviderEquipment_header//portalaccess_header//managementoptions_header
		//***click(application, "Show Interfaces Link", "MAS_ShowInterfaceLink");
		//Thread.sleep(2000);
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "MASSwitch_ACTION_Device1"); 
		click(application, "ACTION MAS Switch", "MAS_PE_ACTION_EditLink_Device1"); 
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_EditInterface_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'MAS Edit Interface' page navigated as expected");
			System.out.println("'MAS Edit Interface' page navigated as expected");
			
		try {
		if(MAS_AccessMediaEdit.equalsIgnoreCase("VPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMediaEdit);

			ClearAndEnterTextValue(application, "Interface textfield", "MAS_PE_InterfaceTextfield", MAS_InterfaceEdit);
//			ClearAndEnterTextValue(application, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield", MAS_InterfaceAddressRangeEdit);
			ClearAndEnterTextValue(application, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield", MAS_InterfaceAddressMaskEdit);
			//ClearAndEnterTextValue(application, "HSRP IP textfield", "MAS_PE_HSRPIPTextfield", MAS_HSRPIPEdit);
//			ClearAndEnterTextValue(application, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield", MAS_InterfaceAddressRangeIPV6Edit);
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
//			ClearAndEnterTextValue(application, "HSRP IPv6 Address textfield", "MAS_PE_HSRPIPv6AddressTextfield", MAS_HSRPIPv6AddressEdit);
//			ClearAndEnterTextValue(application,  "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield" , MAS_PrimaryIPv6onMas1Edit);
			ClearAndEnterTextValue(application, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield", MAS_SecondaryIPv6onMas2Edit);
			ClearAndEnterTextValue(application, "Group Number textfield", "MAS_PE_GroupNumberTextfield", MAS_GroupNumberEdit);
			ClearAndEnterTextValue(application, "Link textfield", "MAS_PE_LinkTextfield", MAS_LinkEdit);
			ClearAndEnterTextValue(application, "VLAN ID textfield", "MAS_PE_VLANIDTextfield", MAS_VLANIDEdit);

//			//IV Management Checkbox
//			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")).isEnabled()){
//				if (MAS_IVManagementEdit.equalsIgnoreCase("YES")) {
//					click(application, "'IV Management' checkbox", "MAS_PE_IVManagementCheckbox");
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//				} else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
//					System.out.println("Step : 'IV Management' checkbox is not Selected");
//				}
//			
//				}else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}

			addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", MAS_IVManagementEdit, "No", xml);
		

			//Thread.sleep(2000);
			scrolltoend();
			EnterTextValue(application, MAS_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ MAS_GenerateConfigurationEdit +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);

			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully updated by selecting VPN as Access Media");
			
		
		}else if(MAS_AccessMediaEdit.equalsIgnoreCase("EPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMediaEdit);
			
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectMAS_AccessMediaDropdownValue=getwebelement("//div[text()='"+ MAS_AccessMediaEdit +"']");
			Clickon(SelectMAS_AccessMediaDropdownValue);
			
			ClearAndEnterTextValue(application, "Interface textfield", "MAS_PE_InterfaceTextfield", MAS_InterfaceEdit);
			
//			SelectDropdownValueUnderDivTag(application, "HSRP BGP Dropdown", MAS_HSRPBGPEdit, "MAS_PE_HSRPBGPDropdown", "commonDropdownValueTag");
//			
//			ClearAndEnterTextValue(application, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield", MAS_InterfaceAddressRangeEdit);
			ClearAndEnterTextValue(application, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield", MAS_InterfaceAddressMaskEdit);
			//ClearAndEnterTextValue(application, "HSRP IP textfield", "MAS_PE_HSRPIPTextfield", MAS_HSRPIPEdit);
//			ClearAndEnterTextValue(application, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield", MAS_InterfaceAddressRangeIPV6Edit);
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			
//			ClearAndEnterTextValue(application, "HSRP IPv6 Address textfield", "MAS_PE_HSRPIPv6AddressTextfield", MAS_HSRPIPv6AddressEdit);
//			ClearAndEnterTextValue(application,  "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield" , MAS_PrimaryIPv6onMas1Edit);
			ClearAndEnterTextValue(application, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield", MAS_SecondaryIPv6onMas2Edit);
			ClearAndEnterTextValue(application, "Group Number textfield", "MAS_PE_GroupNumberTextfield", MAS_GroupNumberEdit);
			ClearAndEnterTextValue(application, "Link textfield", "MAS_PE_LinkTextfield", MAS_LinkEdit);
			ClearAndEnterTextValue(application, "VLAN ID textfield", "MAS_PE_VLANIDTextfield", MAS_VLANIDEdit);
//			ClearAndEnterTextValue(application, "HSRP Track Interface textfield", "MAS_PE_HSRPTrackInterfaceTextfield", MAS_HSRPTrackInterfaceEdit);
//			ClearAndEnterTextValue(application, "HSRP Authentication textfield", "MAS_PE_HSRPAuthenticationTextfield", MAS_HSRPAuthenticationEdit);

			click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			
			//Thread.sleep(2000);
			scrolltoend();
			EnterTextValue(application, MAS_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ MAS_GenerateConfigurationEdit +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);
			
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully updated by selecting EPN as Access Media");
			Log.info("------ Interface Updated successfully ------");
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
	
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Interface page");
			System.out.println(  e+ " : Field is not displayed in  Edit Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in  Edit Interface page ");
			System.out.println(  e+" : Field is not displayed in  Edit Interface page");
		}

		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'MAS Edit Interface' page not navigated");
			System.out.println("'MAS Edit Interface' page not navigated");
		}
		
		sa.assertAll();
		
	}
		
		

	

	
	public void verifyAddInterfaceFunction_MAS_UI(String application, String MAS_AccessMedia, String MAS_Network,
			String MAS_HSRPBGP, String MAS_GenerateConfiguration, String	MAS_Interface, String MAS_InterfaceAddressRange,
			String MAS_InterfaceAddressMask, String	MAS_HSRPIP, String	MAS_InterfaceAddressRangeIPV6, String MAS_HSRPIPv6Address, 
			String	MAS_PrimaryIPv6onMas1, String MAS_SecondaryIPv6onMas2, String MAS_GroupNumber, String MAS_Link, String MAS_VLANID,
			String	MAS_IVManagement, String MAS_Configuration, String MAS_HSRPTrackInterface, String MAS_HSRPAuthentication) throws InterruptedException, DocumentException, IOException {
		Thread.sleep(5000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_CommandIPV4_header")));//RouterTool_header//MAS_PE_CommandIPV4_header//MAS_PE_InterfacesPanel_header
		click(application, "ACTION Link", "MAS_View_InterfacesActionLink");
		click(application, "Add Interface Link", "MAS_PE_AddInterfaceLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_AddInterface_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'MAS Add Interface' page navigated as expected");
			System.out.println("'MAS Add Interface' page navigated as expected");
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
		
		try {
		click(application, "OK Button", "MAS_PE_OKButton");
		
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface is required", "MAS_PE_InterfaceWarningMessage");
 
		
		
		if(MAS_AccessMedia.equalsIgnoreCase("VPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMedia);
			
			isDisplayed(application, "MAS_PE_InterfaceTextfield", "Interface", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressRangeTextfield", "Interface Address Range", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressMaskTextfield", "Interface Address/Mask", xml);
			isDisplayed(application, "MAS_PE_HSRPIPTextfield", "HSRP IP", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressRangeIPV6Textfield", "Interface Address Range IPV6", xml);
			isDisplayed(application, "MAS_PE_HSRPIPv6AddressTextfield", "HSRP IPv6 Address", xml);
			isDisplayed(application, "MAS_PE_PrimaryIPv6onMas1Textfield", "Primary IPv6 on Mas-1", xml);
			isDisplayed(application, "MAS_PE_SecondaryIPv6onMas2Textfield", "Secondary IPv6 on Mas-2", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			isDisplayed(application, "MAS_PE_GroupNumberTextfield", "Group Number", xml);
			isDisplayed(application, "MAS_PE_LinkTextfield", "Link", xml);
			isDisplayed(application, "MAS_PE_VLANIDTextfield", "VLAN Id", xml);
			isDisplayed(application, "MAS_PE_IVManagementCheckbox", "IV Management", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			isDisplayed(application, "MAS_PE_GenerateConfigurationDropdown", "Generate Configuration Dropdown", xml);
			isDisplayed(application, "MAS_PE_GenerateConfigurationButton", "Generate Configuration Button", xml);
			isDisplayed(application, "MAS_PE_SaveConfigurationtoFileButton", "Save Configuration to File Button", xml);
			scrolltoend();
			isDisplayed(application, "MAS_PE_OKButton", "OK", xml);
			isDisplayed(application, "cancelbutton", "Cancel", xml);
			click(application, "Cancel", "cancelbutton");
			
		}else if(MAS_AccessMedia.equalsIgnoreCase("EPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMedia);

			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectMAS_AccessMediaDropdownValue=getwebelement("//div[text()='"+ MAS_AccessMedia +"']");
			Clickon(SelectMAS_AccessMediaDropdownValue);
			
			/////////////////////////////
			isDisplayed(application, "MAS_PE_InterfaceTextfield", "Interface", xml);
			isDisplayed(application, "MAS_PE_HSRPBGPDropdown", "HSRP BGP Dropdown", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressRangeTextfield", "Interface Address Range", xml);

			isDisplayed(application, "MAS_PE_InterfaceAddressMaskTextfield", "Interface Address/Mask", xml);
			isDisplayed(application, "MAS_PE_HSRPIPTextfield", "HSRP IP", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressRangeIPV6Textfield", "Interface Address Range IPV6", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			isDisplayed(application, "MAS_PE_HSRPIPv6AddressTextfield", "HSRP IPv6 Address", xml);
			isDisplayed(application, "MAS_PE_PrimaryIPv6onMas1Textfield", "Primary IPv6 on Mas-1", xml);
			isDisplayed(application, "MAS_PE_SecondaryIPv6onMas2Textfield", "Secondary IPv6 on Mas-2", xml);
			isDisplayed(application, "MAS_PE_GroupNumberTextfield", "Group Number", xml);
			isDisplayed(application, "MAS_PE_LinkTextfield", "Link", xml);
			isDisplayed(application, "MAS_PE_VLANIDTextfield", "VLAN Id", xml);
			
			isDisplayed(application, "MAS_PE_IVManagementCheckbox", "IV Management", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			
			isDisplayed(application, "MAS_PE_GenerateConfigurationDropdown", "Generate Configuration Dropdown", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			isDisplayed(application, "MAS_PE_GenerateConfigurationButton", "Generate Configuration Button", xml);
			isDisplayed(application, "MAS_PE_SaveConfigurationtoFileButton", "Save Configuration to File Button", xml);
			isDisplayed(application, "MAS_PE_OKButton", "OK", xml);
			scrolltoend();
			isDisplayed(application, "MAS_PE_OKButton", "OK", xml);
			isDisplayed(application, "cancelbutton", "Cancel", xml);
			click(application, "Cancel", "cancelbutton");
			//////////////////////////////////////////
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Add Interface page");
			System.out.println(  e+ " : Field is not displayed in  Add Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in  Add Interface page ");
			System.out.println(  e+" : Field is not displayed in  Add Interface page");
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'MAS Add Interface' page not navigated");
			System.out.println("'MAS Add Interface' page not navigated");
		}
		sa.assertAll();
		
	}
	
	
	
	
	
	
	public void verifyConfigureInterfaceFunction_MAS(String application, String ServiceIdentification, String MAS_GenerateConfiguration, String MAS_Configuration) throws InterruptedException, DocumentException, IOException {
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/portalaccess_header")));//ProviderEquipment_header//portalaccess_header//managementoptions_header
		//***click(application, "Show Interfaces Link", "MAS_ShowInterfaceLink");
		Thread.sleep(2000);
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "MASSwitch_ACTION_Device1"); 
		click(application, "Configure interface", "MAS_PE_ACTION_ConfigureLink_Device1"); 
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configure_ConfigureInterface_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'MAS Select Interface' page navigated as expected");
			System.out.println("'MAS Select Interface' page navigated as expected");
			
		
			try {
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
		
		EnterTextValue(application, MAS_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_Configure_GenerateConfigurationDropdown");
		
		WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ MAS_GenerateConfiguration +"']");
		Clickon(SelectGenerateConfigurationDropdownValue);
		
		compareText(application, "Generate Configuration for All CPE Routes Link", "MAS_PE_Configure_GenerateConfigurationForAllCPERoutesButton", "Generate Configuration for All CPE Routes");
		compareText(application, "Save Configuration Link", "MAS_PE_Configure_SaveConfigurationButton", "Save Configuration");
		compareText(application, "Execute Configuration on Device Link", "MAS_PE_Configure_ExecuteConfigurationonDeviceButton", "Execute Configuration on Device");
		GetText(application, "Configuration information after configuration generated", "MAS_PE_Configure_ConfigurationTextfield");
		Log.info("------ Verified Configure Interfaces Information successfully ------");
		
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Select Interface page");
				System.out.println(  e+ " : Field is not displayed in  Select Interface page");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in  Select Interface page ");
				System.out.println(  e+" : Field is not displayed in  Select Interface page");
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'MAS Select Interface' page not navigated");
			System.out.println("'MAS Select Interface' page not navigated");
		}
	}
	
	
	
	
	
	
	
	
	
	public void verifyDeleteInterfaceFunction_MAS(String application, String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/portalaccess_header")));//ProviderEquipment_header//portalaccess_header//managementoptions_header
		//***click(application, "Show Interfaces Link", "MAS_ShowInterfaceLink");
		//Thread.sleep(2000);
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "MASSwitch_ACTION_Device1"); 
		click(application, "Delete interface link", "MAS_PE_ACTION_ResetLink_Device1"); 
		
		compareText(application, "Delete Interface Warning Message from View Service page", "MAS_PE_DeleteInterfaceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "MAS_PE_DeleteButton");
		Thread.sleep(3000);
		compareText(application, "Delete Interface Successfull Message", "MAS_PE_DeleteInterfaceSuccessfullMessage", "Interface successfully removed from this service.");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////  PE DEVICE PANEL  ///////////////////////////////////////
	
	public void verifyAddPEDevice(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		click(application, "Add PE Device Link", "PE_AddPEDeviceLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/PE_AddPEDevice_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Add PE Device' page navigated as expected");
			System.out.println("'Add PE Device' page navigated as expected");
			
		try {
		compareText(application, "Add PE Device header", "PE_AddPEDevice_header", "Add PE Device");

		click(application, "OK button", "PE_OKbutton");
		compareText(application, "Add Device Warning Message for PE Device", "PE_AddPEDeviceWarningMessage", "IMS POP Location is required");
		
		// Select  IMS POP Location dropdown
		addDropdownValues_commonMethod(application, "IMS POP Location", "PE_IMSPOPLocationDropdown", PE_IMSPOPLocation, xml);

		click(application, "OK button", "PE_OKbutton");
		compareText(application, "Add Device Successful Message for PE Device", "PE_AddPEDeviceSuccessfulMessage", "PE Device added successfully");

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Add PE Device page");
			System.out.println(  e+ " : Field is not displayed in Add PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Add PE Device page ");
			System.out.println(  e+" : Field is not displayed in Add PE Device page");
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Add PE Device' page not navigated");
			System.out.println("'Add PE Device' page not navigated");
		}
		
		Log.info("------ PE Device added successfully ------");
	}

	
	
	
	public void verifyAddPEDevice_UI(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		click(application, "Add PE Device Link", "PE_AddPEDeviceLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/PE_AddPEDevice_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Add PE Device' page navigated as expected");
			System.out.println("'Add PE Device' page navigated as expected");
			
		try {
		click(application, "OK button", "PE_OKbutton");
		compareText(application, "Add Device Warning Message for PE Device", "PE_AddPEDeviceWarningMessage", "IMS POP Location is required");
		isDisplayed(application, "PE_IMSPOPLocationDropdown", "IMS POP Location", xml);
		// Select  IMS POP Location dropdown
		addDropdownValues_commonMethod(application, "IMS POP Location", "PE_IMSPOPLocationDropdown", PE_IMSPOPLocation, xml);

		click(application, "OK button", "PE_OKbutton");
		compareText(application, "Add Device Successful Message for PE Device", "PE_AddPEDeviceSuccessfulMessage", "PE Device added successfully");

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Add PE Device page");
			System.out.println(  e+ " : Field is not displayed in Add PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Add PE Device page ");
			System.out.println(  e+" : Field is not displayed in Add PE Device page");
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Add PE Device' page not navigated");
			System.out.println("'Add PE Device' page not navigated");
		}
		
		Log.info("------ PE Device added successfully ------");
	}
	
	
	public void navigateToViewDevicePage_PE(String application, String PE_DeviceName) throws InterruptedException, DocumentException {
		try {
		ScrolltoElement(application, "MAS_AddMASSwitchLink", xml);//TrunkGroupSiteOrders_header //ProviderEquipment_header //MASswitch_header//PE_AddPEDeviceLink
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingPEdevicegrid")).isDisplayed())
		{
			//**WebElement AddedDevice_ViewLink=getwebelement("//div[div[b[text()='"+PE_DeviceName+"']]]//span[text()='View']");
			//**Clickon(AddedDevice_ViewLink);
			click_commonMethod(application, "View Link", "PE_viewdevice1", xml);
			compareText(application, "View device header", "PE_ViewDevice_Header", "Device", xml);
				
		}else{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
	}
	
	
	
	
	public void navigateToViewDevicePage_PE_UI(String application, String PE_DeviceName) throws InterruptedException, DocumentException {
		try {
		ScrolltoElement(application, "MAS_AddMASSwitchLink", xml);//TrunkGroupSiteOrders_header //ProviderEquipment_header //MASswitch_header//PE_AddPEDeviceLink
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingPEdevicegrid")).isDisplayed())
		{
			//**WebElement AddedDevice_ViewLink=getwebelement("//div[div[b[text()='"+PE_DeviceName+"']]]//span[text()='View']");
			//**Clickon(AddedDevice_ViewLink);
			click_commonMethod(application, "View Link", "PE_viewdevice1", xml);
			compareText(application, "View device header", "PE_ViewDevice_Header", "Device", xml);
				
		}else{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}

		
	}
	
	
	
	public void verifyAddedPEDeviceInformation_View(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

			//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
			//		click(application, "PE View Link", "PE_viewdevice1");

		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
		try {
			
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
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View PE Device page");
			System.out.println(  e+ " : Field is not displayed in View PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View PE Device page ");
			System.out.println(  e+" : Field is not displayed in View PE Device page");
		}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page not navigated");
		}
		
		Log.info("------ Verified Added PE Device Information successfully ------");
	}
	
	
	
	
	public void verifyAddedPEDeviceInformation_View_UI(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		//		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		//		click(application, "PE View Link", "PE_viewdevice1");

	if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
		DriverTestcase.logger.log(LogStatus.PASS, "'View PE Device' page navigated as expected");
		System.out.println("'View PE Device' page navigated as expected");
		
	try {
		
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
	
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View PE Device page");
		System.out.println(  e+ " : Field is not displayed in View PE Device page");
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View PE Device page ");
		System.out.println(  e+" : Field is not displayed in View PE Device page");
	}
	
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'View PE Device' page not navigated");
		System.out.println("'View PE Device' page not navigated");
	}
	
}

	
	
	public void verifyEditPEDeviceFunction(String application, String ServiceIdentification,  String PE_DeviceName_Edit, String PE_VendorModelEdit, String PE_ManagementAddressEdit, String PE_SnmproEdit, String PE_CountryEdit, String PE_CityEdit, String PE_SiteEdit, String PE_PremiseEdit) throws InterruptedException, DocumentException, IOException {
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
			click(application, "ACTION link", "PE_View_ActionLink");
			click(application, "Edit Link", "PE_View_Action_EditLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/Edit_PEDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit PE Device' page navigated as expected");
			System.out.println("'Edit PE Device' page navigated as expected");
			
			try { 	
			
				//**Need to recheck ClearAndEnterTextValue(application, "Snmpro textfield", "MAS_Snmprotextfield", MAS_SnmproEdit);
				//**scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Edit_PremiseLevel")));

				scrolltoend();
				click(application, "OK in Edit PE Device", "PE_OKbutton");
				Thread.sleep(5000);
				compareText(application, "PE Device Update message", "PE_UpdatePEDeviceSuccessfulMessage", "PE Device updated successfully");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Edit PE Device page");
			System.out.println(  e+ " : Field is not displayed in Edit PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Edit PE Device page ");
			System.out.println(  e+" : Field is not displayed in Edit PE Device page");
		}
		
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Edit PE Device' page not navigated");
				System.out.println("'Edit PE Device' page not navigated");	
			}
			
			Log.info("------  PE Device updated successfully   ------");
		
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page navigated");
		}
	}

	
	
	
public void verifyEditPEDeviceFunction_UI(String application, String ServiceIdentification,  String PE_DeviceName_Edit, String PE_VendorModelEdit, String PE_ManagementAddressEdit, String PE_SnmproEdit, String PE_CountryEdit, String PE_CityEdit, String PE_SiteEdit, String PE_PremiseEdit) throws InterruptedException, DocumentException, IOException {
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
			click(application, "ACTION link", "PE_View_ActionLink");
			click(application, "Edit Link", "PE_View_Action_EditLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/Edit_PEDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit PE Device' page navigated as expected");
			System.out.println("'Edit PE Device' page navigated as expected");
			
			try { 	
			
				//**Need to recheck ClearAndEnterTextValue(application, "Snmpro textfield", "MAS_Snmprotextfield", MAS_SnmproEdit);
				//**scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Edit_PremiseLevel")));

				scrolltoend();
				click(application, "Cancel", "MAS_Cancelbutton");
				Thread.sleep(5000);
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Edit PE Device page");
			System.out.println(  e+ " : Field is not displayed in Edit PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Edit PE Device page ");
			System.out.println(  e+" : Field is not displayed in Edit PE Device page");
		}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Edit PE Device' page not navigated");
				System.out.println("'Edit PE Device' page not navigated");	
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page navigated");
		}
	}


	
public void verifFetchDeviceInterfacesFunction_PE(String application,String ServiceIdentification, String PE_ServiceStatusChangeRequired) throws InterruptedException, DocumentException, IOException {

	
	scrollToTop();
	if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
		DriverTestcase.logger.log(LogStatus.PASS, "'View PE Device' page navigated as expected");
		System.out.println("'View PE Device' page navigated as expected");
	
		click(application, "ACTION link", "PE_View_ActionLink");
		click(application, "Fetch Device Interfaces Link", "PE_View_Action_FetchDeviceInterfacesLink");
	
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_FetchDeviceInterfacesSuccessMessage")).isDisplayed()){
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
			System.out.println("Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			System.out.println("Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
		}
		
		
		
		click(application, "Click here Link for PE", "PE_hereLink_UnderFetchDeviceInterfacesSuccessMessage");
		Thread.sleep(2000);
		
		//Manage COLT's Network - Manage Network
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ManageCOLTsNetworkManageNetwork_header")).isDisplayed()) {
		DriverTestcase.logger.log(LogStatus.PASS, "'Manage COLT's Network - Manage Network' page navigated as expected");
		System.out.println("'Manage COLT's Network - Manage Network' page navigated as expected");
					
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_Manage_Synchronization_SynchronizeLink")));
				
		try {
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_Manage_Synchronization_SynchronizeLink")));
		//Manage COLT's Network - Manage Network
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
				
			Log.info("------  Sync started successfully. Please check the sync status of this device   ------");

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Manage Colt Network page");
			System.out.println(  e+ " : Field is not displayed in Manage Colt Network page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Manage Colt Network page ");
			System.out.println(  e+" : Field is not displayed in Manage Colt Network page");
		}
		
}else {
	DriverTestcase.logger.log(LogStatus.FAIL, "'Manage COLT's Network - Manage Network' page not navigated");
	System.out.println("'Manage COLT's Network - Manage Network' page not navigated");
}	
		
}else {
	DriverTestcase.logger.log(LogStatus.FAIL, "'View PE Device' page not navigated");
	System.out.println("'View PE Device' page navigated");
}


	}
	
	

public void routerPanel_PE(String application, String commandIPv4, String commandIPv6, 
		String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException, IOException {
	
	scrollToTop();
	Thread.sleep(1000);
	
	WebElement vendorModel=getwebelement(xml.getlocator("//locators/" + application + "/PE_View_VendorModelValue"));
	String vendor=Gettext(vendorModel);
	WebElement manageAddress=getwebelement(xml.getlocator("//locators/" + application + "/PE_View_ManagementAddressValue"));
	String ipAddress=Gettext(manageAddress);
	
	ScrolltoElement(application, "PE_View_VendorModelValue" , xml);
	Thread.sleep(1000);
	
	if(vendor.startsWith("Cisco")) {
		
	//Command IPV4	
		addDropdownValues_commonMethod(application, "Command IPV4", "PE_Router_IPV4CommandsDropdown" , commandIPv4, xml);
		
		hostnametextField_IPV4_PE(application, commandIPv4, ipAddress);
		
		vrfNametextField_IPV4_PE(application, commandIPv4, vrfname_ipv4);
		
		executeCommandAndFetchTheValue_PE(application, "MAS_PE_Router_IPV4Command_Executebutton");
		
		
	//Commmand IPV6
		addDropdownValues_commonMethod(application, "Command IPV6", "MAS_PE_Router_IPV6CommandsDropdown" , commandIPv6, xml);
		
		hostnametextField_IPV6_PE(application, commandIPv6, ipAddress);
		
		vrfNametextField_IPV6_PE(application, commandIPv6, vrfname_ipv6);
		
		executeCommandAndFetchTheValue_PE(application, "MAS_PE_Router_IPV6Command_Executebutton");
		
	}
	else if(vendor.startsWith("Juniper ")){
		
		//Command IPV4	
				addDropdownValues_commonMethod(application, "Command IPV4", "MAS_PE_Router_IPV4Command_Executebutton" , commandIPv4, xml);
				
				hostnametextField_IPV4_PE(application, commandIPv4, ipAddress);
				
				vrfNametextField_IPV4_PE(application, commandIPv4, vrfname_ipv4);
				
				executeCommandAndFetchTheValue_PE(application, "MAS_PE_Router_IPV4Command_Executebutton");
				
		
	}
	else {
		DriverTestcase.logger.log(LogStatus.INFO, "Router Panel will not display for the selected vendorModel: "+vendorModel);
		System.out.println("Router Panel will not display for the selected vendorModel: "+vendorModel);
	}
	

	
}

public void executeCommandAndFetchTheValue_PE(String application, String executeButton) throws InterruptedException, DocumentException {
	
	click_commonMethod(application, "Execute", executeButton, xml);
	Thread.sleep(300000);
boolean resultField=false;	
try {	
resultField=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_result_textArea")).isDisplayed();
if(resultField) {
	DriverTestcase.logger.log(LogStatus.PASS, "'Result' text field is displaying");
	System.out.println( "'Result' text field is displaying");
	
	String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_result_textArea")).getText();
	DriverTestcase.logger.log(LogStatus.PASS, "value under 'Result' field displaying as "+ remarkvalue);
	System.out.println("value under 'Result' field displaying as "+ remarkvalue);

}else {
	DriverTestcase.logger.log(LogStatus.FAIL, "'Result' text field is not displaying");
	System.out.println( "'Result' text field is not displaying");
}
}catch(Exception e) {
e.printStackTrace();
DriverTestcase.logger.log(LogStatus.FAIL, "'Result' text field is not displaying");
System.out.println("'Result' text field is not displaying");
}
	
}


public void hostnametextField_IPV6_PE(String application, String commandIPv6, String ipv6Address) {
	boolean IPV4availability=false;
	try {  
		IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_commandIPv6_hostnameTextfield")).isDisplayed();
	  
	  if(IPV4availability) {
		  
		  addtextFields_commonMethod(application, "IP Address or Hostname", "MAS_PE_commandIPv6_hostnameTextfield", ipv6Address, xml);
		  
	  }else {
		  DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
			System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
	  }
	}catch(Exception e) {
		e.printStackTrace();
		
		DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
		System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
	}
}


public void vrfNametextField_IPV6_PE(String application, String commandIPV6, String vrfname_IPV6) {
	boolean IPV6availability=false;
	
	if(commandIPV6.equalsIgnoreCase("vrf")) {
		try {  
			IPV6availability=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_commandIPv6_vrfnameTextField")).isDisplayed();
			
			if(IPV6availability) {
				addtextFields_commonMethod(application, "Router Vrf Name", "MAS_PE_commandIPv6_vrfnameTextField", vrfname_IPV6, xml);
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
		}
	}
	else {
		DriverTestcase.logger.log(LogStatus.PASS, "'VRF Name IPv6' text field is not displaying for "+ commandIPV6);
		System.out.println("'VRF Name IPv6' text field is not displaying for "+ commandIPV6 +" command");
	}
	
}	


public void hostnametextField_IPV4_PE(String application, String command_ipv4, String ipAddress) {
	boolean IPV4availability=false;
	try {  
		IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_commandIPv4_hostnameTextfield")).isDisplayed();
	  
	  if(IPV4availability) {
		  
		  addtextFields_commonMethod(application, "IP Address or Hostname", "MAS_PE_commandIPv4_hostnameTextfield", ipAddress, xml);
		  
	  }else {
		  DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
			System.out.println("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
	  }
	}catch(Exception e) {
		e.printStackTrace();
		
		DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
		System.out.println("'Hostname or IpAddress' for 'Ipv4' text field is not displaying for "+ command_ipv4);
	}
}


public void vrfNametextField_IPV4_PE(String application, String command_ipv4, String vrfname_ipv4) {
	boolean IPV4availability=false;
	  
		
	if(command_ipv4.contains("vrf")) {
		try {
			IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_commandIPv4_vrfnameTextField")).isDisplayed();
			
			if(IPV4availability) {
				addtextFields_commonMethod(application, "Router Vrf Name", "MAS_PE_commandIPv4_vrfnameTextField", vrfname_ipv4, xml);
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
		}
		
	}else {
		DriverTestcase.logger.log(LogStatus.PASS, "'VRF Name IPv4' text field is not displaying for "+ command_ipv4);
		System.out.println("'VRF Name IPv4' text field is not displaying for "+ command_ipv4 +" command");
	}
}	




	
	
	////
	
	public void verifyRouterToolFunction_PE(String application,String ServiceIdentification, String PE_CommandIPV4,String PE_ManagementAddress) throws InterruptedException, DocumentException, IOException {
				
		scrollToTop();
		Thread.sleep(2000);
		
		WebElement serviceIdLink=getwebelement("//span[text()='"+ ServiceIdentification +"']");
		Clickon(serviceIdLink);
		Thread.sleep(5000);

		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "View Link", "PE_viewdevice1");
		Thread.sleep(10000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_View_PremiseValue")));
		compareText(application, "Router Tools header", "RouterTool_header", "Router Tools");
		
		SelectDropdownValueUnderDivTag(application, "Router IPV4 Command", PE_CommandIPV4, "PE_Router_IPV4CommandsDropdown", "commonDropdownValueTag");

				EnterTextValue(application, PE_ManagementAddress, "Commands IPV4", "PE_Router_IPV4CommandTextfield");
				click(application, "Execute button", "PE_Router_IPV4Command_Executebutton");
				
}
	
	
	
	
	
	
	
	

	public void verifyAddInterfaceFunction_PE(String application, String PE_AccessMedia, String PE_Network,
			String PE_VRRPBGP, String PE_GenerateConfiguration, String	PE_Interface, String PE_InterfaceAddressRange,
			String PE_InterfaceAddressMask, String	PE_VRRPIP, String	PE_InterfaceAddressRangeIPV6, String PE_VRRPIPv6Address, 
			String	PE_PrimaryIPv6onMas1, String PE_SecondaryIPv6onMas2, String PE_GroupNumber, String PE_Link, String PE_VLANID, String PE_VRRPGroupName , String PE_VRF,
			String	PE_IVManagement, String PE_Configuration, String PE_VRRPTrackInterface, String PE_VRRPAuthentication) throws InterruptedException, DocumentException, IOException {
		
		
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_CommandIPV4_header")));//RouterTool_header//MAS_PE_CommandIPV4_header//MAS_PE_InterfacesPanel_header
			click(application, "ACTION Link", "MAS_View_InterfacesActionLink");
			click(application, "Add Interface Link", "MAS_PE_AddInterfaceLink");
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_AddInterface_header")).isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'PE Add Interface' page navigated as expected");
				System.out.println("'PE Add Interface' page navigated as expected");
				
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			
		try {
		click(application, "OK Button", "MAS_PE_OKButton");
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface Suggestion Message", "PE_InterfaceSuggestionMessage");
		
		
		
		if(PE_AccessMedia.equalsIgnoreCase("VPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMedia);
			
			EnterTextValue(application, PE_InterfaceAddressRange, "Interface Address Range Textfield", "MAS_PE_InterfaceAddressRangeTextfield");
			EnterTextValue(application, PE_InterfaceAddressMask, "Interface Address Mask textfield", "MAS_PE_InterfaceAddressMaskTextfield");
			EnterTextValue(application, PE_VRRPIP, "VRRP IP textfield", "MAS_PE_VRRPIPTextfield");
			EnterTextValue(application, PE_InterfaceAddressRangeIPV6, "Interface Address Range IPV6 textfield", "MAS_PE_InterfaceAddressRangeIPV6Textfield");
			
			EnterTextValue(application, PE_PrimaryIPv6onMas1, "Primary IPv6 on Mas1 textfield", "MAS_PE_PrimaryIPv6onMas1Textfield");
			EnterTextValue(application, PE_SecondaryIPv6onMas2, "Secondary IPv6 on Mas2 textfield", "MAS_PE_SecondaryIPv6onMas2Textfield");
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			EnterTextValue(application, PE_GroupNumber, "Group Number textfield", "MAS_PE_GroupNumberTextfield");
			EnterTextValue(application, PE_Link, "Link textfield", "MAS_PE_LinkTextfield");
			EnterTextValue(application, PE_VLANID, "Interface textfield", "MAS_PE_VLANIDTextfield");
			//*EnterTextValue(application, PE_VRRPGroupName, "VRRP-Group Name textfield", "MAS_PE_VRRPGroupNameTextfield");//Bydefault
			EnterTextValue(application, PE_VRF, "VRF textfield", "MAS_PE_VRFTextfield");

			//***click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
//			//IV Management Checkbox
//			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")).isEnabled()){
//				if (PE_IVManagement.equalsIgnoreCase("YES")) {
//					click(application, "'IV Management' checkbox", "MAS_PE_IVManagementCheckbox");
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//				} else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
//					System.out.println("Step : 'IV Management' checkbox is not Selected");
//				}
//			
//				}else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}

			addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", PE_IVManagement, "No", xml);
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			//**scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			
			EnterTextValue(application, PE_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ PE_GenerateConfiguration +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);
			
			
			Thread.sleep(3000);
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully added by selecting VPN as Access Media");
			
		}else if(PE_AccessMedia.equalsIgnoreCase("EPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMedia);
			
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectMAS_AccessMediaDropdownValue=getwebelement("//div[text()='"+ PE_AccessMedia +"']");
			Clickon(SelectMAS_AccessMediaDropdownValue);
			
			SelectDropdownValueUnderDivTag(application, "VRRP BGP Dropdown", PE_VRRPBGP, "MAS_PE_VRRPBGPDropdown", "commonDropdownValueTag");
			
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

			//**click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
//			//IV Management Checkbox
//			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")).isEnabled()){
//				if (PE_IVManagement.equalsIgnoreCase("YES")) {
//					click(application, "'IV Management' checkbox", "MAS_PE_IVManagementCheckbox");
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//				} else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
//					System.out.println("Step : 'IV Management' checkbox is not Selected");
//				}
//			
//				}else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}

			addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", PE_IVManagement, "No", xml);
			
			EnterTextValue(application, PE_VRRPTrackInterface, "VRRP Track Interface textfield", "MAS_PE_VRRPTrackInterfaceTextfield");
			EnterTextValue(application, PE_VRRPAuthentication, "VRRP Authentication textfield", "MAS_PE_VRRPAuthenticationTextfield");
			
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));

			scrolltoend();
			EnterTextValue(application, PE_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");

			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ PE_GenerateConfiguration +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);
			
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully added by selecting EPN as Access Media");			
			Log.info("------ Interface Added successfully ------");
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Add Interface page");
			System.out.println(  e+ " : Field is not displayed in  Add Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in  Add Interface page ");
			System.out.println(  e+" : Field is not displayed in  Add Interface page");
		}

			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'PE Add Interface' page not navigated");
				System.out.println("'PE Add Interface' page not navigated");
			}
			sa.assertAll();
		
	}
	
	
	
	
	
	public void verifyAddInterfaceFunction_PE_UI(String application, String PE_AccessMedia, String PE_Network,
			String PE_VRRPBGP, String PE_GenerateConfiguration, String	PE_Interface, String PE_InterfaceAddressRange,
			String PE_InterfaceAddressMask, String	PE_VRRPIP, String	PE_InterfaceAddressRangeIPV6, String PE_VRRPIPv6Address, 
			String	PE_PrimaryIPv6onMas1, String PE_SecondaryIPv6onMas2, String PE_GroupNumber, String PE_Link, String PE_VLANID, String PE_VRRPGroupName , String PE_VRF,
			String	PE_IVManagement, String PE_Configuration, String PE_VRRPTrackInterface, String PE_VRRPAuthentication) throws InterruptedException, DocumentException, IOException {
		
			Thread.sleep(5000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_CommandIPV4_header")));//RouterTool_header//MAS_PE_CommandIPV4_header//MAS_PE_InterfacesPanel_header
			click(application, "ACTION Link", "MAS_View_InterfacesActionLink");
			click(application, "Add Interface Link", "MAS_PE_AddInterfaceLink");
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_AddInterface_header")).isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'PE Add Interface' page navigated as expected");
				System.out.println("'PE Add Interface' page navigated as expected");
				
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			
		try {
		click(application, "OK Button", "MAS_PE_OKButton");
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface Suggestion Message", "PE_InterfaceSuggestionMessage");
		
		
		
		if(PE_AccessMedia.equalsIgnoreCase("VPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMedia);
			
			isDisplayed(application, "MAS_PE_InterfaceAddressRangeTextfield", "Interface Address Range", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressMaskTextfield", "Interface Address/Mask", xml);
			isDisplayed(application, "MAS_PE_VRRPIPTextfield", "VRRP IP", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressRangeIPV6Textfield", "Interface Address Range IPV6", xml);
			
			isDisplayed(application, "MAS_PE_PrimaryIPv6onMas1Textfield", "Primary IPv6 on Mas-1", xml);
			isDisplayed(application, "MAS_PE_SecondaryIPv6onMas2Textfield", "Secondary IPv6 on Mas-2", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			isDisplayed(application, "MAS_PE_GroupNumberTextfield", "Group Number", xml);
			isDisplayed(application, "MAS_PE_LinkTextfield", "Link", xml);
			isDisplayed(application, "MAS_PE_VLANIDTextfield", "VLAN Id", xml);
			isDisplayed(application, "MAS_PE_VRRPGroupNameTextfield", "VRRP-Group Name", xml);
			isDisplayed(application, "MAS_PE_VRFTextfield", "VRF", xml);
			
			isDisplayed(application, "MAS_PE_IVManagementCheckbox", "IV Management", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			isDisplayed(application, "MAS_PE_GenerateConfigurationDropdown", "Generate Configuration Dropdown", xml);
			isDisplayed(application, "MAS_PE_GenerateConfigurationButton", "Generate Configuration Button", xml);
			isDisplayed(application, "MAS_PE_SaveConfigurationtoFileButton", "Save Configuration to File Button", xml);
			scrolltoend();
			isDisplayed(application, "MAS_PE_OKButton", "OK", xml);
			isDisplayed(application, "cancelbutton", "Cancel", xml);
			click(application, "Cancel", "cancelbutton");
			
		}else if(PE_AccessMedia.equalsIgnoreCase("EPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMedia);
			
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectMAS_AccessMediaDropdownValue=getwebelement("//div[text()='"+ PE_AccessMedia +"']");
			Clickon(SelectMAS_AccessMediaDropdownValue);
			
			isDisplayed(application, "MAS_PE_VRRPBGPDropdown", "VRRP BGP Dropdown", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressRangeTextfield", "Interface Address Range", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressMaskTextfield", "Interface Address/Mask", xml);
			isDisplayed(application, "MAS_PE_VRRPIPTextfield", "VRRP IP", xml);
			isDisplayed(application, "MAS_PE_InterfaceAddressRangeIPV6Textfield", "Interface Address Range IPV6", xml);
			isDisplayed(application, "MAS_PE_VRRPIPv6AddressTextfield", "VRRP IPv6 Address", xml);
			isDisplayed(application, "MAS_PE_PrimaryIPv6onMas1Textfield", "Primary IPv6 on Mas-1", xml);
			isDisplayed(application, "MAS_PE_SecondaryIPv6onMas2Textfield", "Secondary IPv6 on Mas-2", xml);
			isDisplayed(application, "MAS_PE_GroupNumberTextfield", "Group Number", xml);
			isDisplayed(application, "MAS_PE_LinkTextfield", "Link", xml);
			isDisplayed(application, "MAS_PE_VLANIDTextfield", "VLAN Id", xml);
			isDisplayed(application, "MAS_PE_VRRPGroupNameTextfield", "VRRP-Group Name", xml);
			isDisplayed(application, "MAS_PE_VRFTextfield", "VRF", xml);
			isDisplayed(application, "MAS_PE_IVManagementCheckbox", "IV Management", xml);
			isDisplayed(application, "MAS_PE_VRRPTrackInterfaceTextfield", "VRRP Track Interface", xml);
			isDisplayed(application, "MAS_PE_VRRPAuthenticationTextfield", "VRRP Authentication", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			scrolltoend();
			isDisplayed(application, "MAS_PE_GenerateConfigurationDropdown", "Generate Configuration Dropdown", xml);
			isDisplayed(application, "MAS_PE_GenerateConfigurationButton", "Generate Configuration Button", xml);
			isDisplayed(application, "MAS_PE_SaveConfigurationtoFileButton", "Save Configuration to File Button", xml);
			isDisplayed(application, "MAS_PE_OKButton", "OK", xml);
			isDisplayed(application, "cancelbutton", "Cancel", xml);
			click(application, "Cancel", "cancelbutton");			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Add Interface page");
			System.out.println(  e+ " : Field is not displayed in  Add Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in  Add Interface page ");
			System.out.println(  e+" : Field is not displayed in  Add Interface page");
		}

			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'PE Add Interface' page not navigated");
				System.out.println("'PE Add Interface' page not navigated");
			}
			sa.assertAll();
		
	}
	
	
	
	public void verifyEditInterfaceFunction_PE(String application, String ServiceIdentification, String PE_AccessMediaEdit, String PE_NetworkEdit,
			String PE_VRRPBGPEdit, String PE_GenerateConfigurationEdit, String	PE_InterfaceEdit, String PE_InterfaceAddressRangeEdit,
			String PE_InterfaceAddressMaskEdit, String	PE_VRRPIPEdit, String	PE_InterfaceAddressRangeIPV6Edit, String PE_VRRPIPv6AddressEdit, 
			String	PE_PrimaryIPv6onMas1Edit, String PE_SecondaryIPv6onMas2Edit, String PE_GroupNumberEdit, String PE_LinkEdit, String PE_VLANIDEdit, String PE_VRRPGroupNameEdit, String PE_VRFEdit,
			String	PE_IVManagementEdit, String PE_ConfigurationEdit, String PE_VRRPTrackInterfaceEdit, String PE_VRRPAuthenticationEdit) throws InterruptedException, DocumentException, IOException {
		

			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header")));//ProviderEquipment_header//portalaccess_header//managementoptions_header
			//***click(application, "Show Interfaces Link", "MAS_ShowInterfaceLink");
			Thread.sleep(2000);
			click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
			click(application, "ACTION PE Device", "PE_ACTION_Device1"); 
			click(application, "Edit Link", "PE_ACTION_EditLink_Device1"); 
		
			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_EditInterface_header")).isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'PE Edit Interface' page navigated as expected");
				System.out.println("'PE Edit Interface' page navigated as expected");
				
			try {
				
		if(PE_AccessMediaEdit.equalsIgnoreCase("VPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMediaEdit);
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectPE_AccessMediaDropdownValue=getwebelement("//div[text()='"+ PE_AccessMediaEdit +"']");
			Clickon(SelectPE_AccessMediaDropdownValue);
			
			//*ClearAndEnterTextValue(application, "Interface textfield", "MAS_PE_InterfaceTextfield", PE_InterfaceEdit);
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

			//**click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			//addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", PE_IVManagementEdit, "Yes", xml);

			//IV Management Checkbox
			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")).isEnabled()){
				if (PE_IVManagementEdit.equalsIgnoreCase("YES")) {
					click(application, "'IV Management' checkbox", "MAS_PE_IVManagementCheckbox");
					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
				} else {
					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
					System.out.println("Step : 'IV Management' checkbox is not Selected");
				}
			
				}else {
					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
				}
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			
			EnterTextValue(application, PE_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ PE_GenerateConfigurationEdit +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);

			Thread.sleep(3000);
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
				
			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully updated by selecting VPN as Access Media");
			
		}else if(PE_AccessMediaEdit.equalsIgnoreCase("EPN")) {
			DriverTestcase.logger.log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMediaEdit);
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectPE_AccessMediaDropdownValue=getwebelement("//div[text()='"+ PE_AccessMediaEdit +"']");
			Clickon(SelectPE_AccessMediaDropdownValue);
			
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

			//**click(application, "IV Management Checkbox", "MAS_PE_IVManagementCheckbox");
			//**addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", PE_IVManagementEdit, "No", xml);

			//IV Management Checkbox
			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")).isEnabled()){
				if (PE_IVManagementEdit.equalsIgnoreCase("YES")) {
					click(application, "'IV Management' checkbox", "MAS_PE_IVManagementCheckbox");
					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
				} else {
					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
					System.out.println("Step : 'IV Management' checkbox is not Selected");
				}
			
				}else {
					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
				}

			
			ClearAndEnterTextValue(application, "VRRP Track Interface textfield", "MAS_PE_VRRPTrackInterfaceTextfield", PE_VRRPTrackInterfaceEdit);
			ClearAndEnterTextValue(application, "VRRP Authentication textfield", "MAS_PE_VRRPAuthenticationTextfield", PE_VRRPAuthenticationEdit);
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			Thread.sleep(2000);
			
			scrolltoend();
			EnterTextValue(application, PE_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ PE_GenerateConfigurationEdit +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);
			
			Thread.sleep(3000);
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			DriverTestcase.logger.log(LogStatus.PASS, "Interfaces sucessfully updated by selecting EPN as Access Media");
			Log.info("------ Interface Updated successfully ------");
		
	}else {
		System.out.println("Access Media Dropdown is not selected as VPN or EPN");
		DriverTestcase.logger.log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
	}

	
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Interface page");
		System.out.println(  e+ " : Field is not displayed in  Edit Interface page");
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in  Edit Interface page ");
		System.out.println(  e+" : Field is not displayed in  Edit Interface page");
	}

			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'PE Edit Interface' page not navigated");
				System.out.println("'PE Edit Interface' page not navigated");
			}
			
			sa.assertAll();
	
}
	
	
		

	

	
	
	
	
	
	
	
	
	public void verifyConfigureInterfaceFunction_PE(String application, String ServiceIdentification, String PE_GenerateConfiguration, String PE_Configuration) throws InterruptedException, DocumentException, IOException {
		
		//Configure Interface - MAS Device from View Device Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION PE", "PE_ACTION_Device1");
		click(application, "Configure", "PE_ACTION_ConfigureLink_Device1");  
		GetText(application, "Select Interfaces header", "MAS_PE_Configure_ConfigureInterface_header");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configure_ConfigureInterface_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Configure Interface' page navigated as expected");
			System.out.println("'Configure Interface' page navigated as expected");
		
			try {
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
		
		SelectDropdownValueUnderSelectTag(application, "Configuration", PE_GenerateConfiguration, "MAS_PE_Configure_GenerateConfigurationDropdown", xml);
		click(application, "Generate Configuration Button", "MAS_PE_Configure_GenerateConfigurationButton");
		
		compareText(application, "Generate Configuration for All CPE Routes Link", "MAS_PE_Configure_GenerateConfigurationForAllCPERoutesButton", "Generate Configuration for All CPE Routes");
		compareText(application, "Save Configuration Link", "MAS_PE_Configure_SaveConfigurationButton", "Save Configuration");
		compareText(application, "Execute Configuration on Device Link", "MAS_PE_Configure_ExecuteConfigurationonDeviceButton", "Execute Configuration on Device");
		
		GetText(application, "Configuration information after configuration generated", "MAS_PE_Configure_ConfigurationTextfield");
		
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "Field is not displayed in 'Configure Interface' page");
				System.out.println( "Field is not displayed in 'Configure Interface' page");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "Field is not displayed in 'Configure Interface' page");
				System.out.println( "Field is not displayed in 'Configure Interface' page");
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Configure Interface' page not navigated");
			System.out.println("'Configure Interface' page navigated");
		}
		
		Log.info("------ Verified Configure Interfaces Information successfully ------");
	}
	
	
	
	
	
	
	
	
	public void verifyDeleteInterfaceFunction_PE(String application, String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		
		//Delete Interface - MAS Device from View Service Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		//*****click(application, "Show Interfaces Link", "PE_showInterfacesLink"); 
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "PE_ACTION_Device1");
		
		click(application, "Delete Button", "PE_ACTION_DeleteLink_Device1");  
				
		compareText(application, "Delete Interface Warning Message from View Service page", "MAS_PE_DeleteInterfaceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "MAS_PE_DeleteButton");
		Thread.sleep(3000);
		compareText(application, "Delete Interface Successfull Message", "PE_DeleteInterfaceSuccessfullMessage", "Interface deleted successfully");
		
	}
	
	
	
	
	////
	public void verifyDeleteDeviceFunction_PE(String application,String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		//Delete MAS Device from View Device Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "View Link", "PE_viewdevice1"); 
		
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");

			
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
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page navigated");
		}


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
			
	
	
	
	
	//////////////////////////  TTTRRRUUUNNNKKK   /////////////////////////////////////////////////
	
	
	
	public void addTrunkGroupSiteOrderNumber(String application, String TrunkGroupOrderCheckboxStatus, String TrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException { 
		
		boolean TrunkGroupSiteOrderPanel_header=false;
		boolean addtrunkSiteorderPage_panelheader=false;
		boolean trunkgrupOrderErrMsg= false;
		
		scrolltoend();
		//Thread.sleep(2000);
		
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
				//Thread.sleep(3000);
				
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

	
	
	
	
	
	
	
	
	
	
	
	
	//////////////  ADD TRUNK  ///////////
	
	
	
	public void verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel(String application, String TrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(1000);
		
		boolean siteOrderUnderTrunkPanel=false;
		
		
		siteOrderUnderTrunkPanel=getwebelement("//span[text()='"+ TrunkGroupSiteOrderNumber +"']").isDisplayed();
		
		if(siteOrderUnderTrunkPanel) {
			
			DriverTestcase.logger.log(LogStatus.PASS, TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			
		//Click on Add trunk link	
			Thread.sleep(2000);
			String addTunklinkXpath="//div[div[span[text()='"+ TrunkGroupSiteOrderNumber +"']]]/following-sibling::div//a[span[text()='Add Trunk']]";
			scrolltoend();
			click_commonMethod_PassingWebelementDirectly(application, "Add Trunk", addTunklinkXpath, xml);
			Thread.sleep(1000);
			
									}else {
			DriverTestcase.logger.log(LogStatus.FAIL, TrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
										 }
									}

	
	
	
	
	
	
	
public void verifyExistingSiteOrderAndTrunkLinkUnderTrunkPanel(String application, String ExistingTrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException {
		
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





















	

  public void verifyAddTrunkForm_UI(String application, String customerName, String servicename, String ServiceType, String PrimaryTrunk, String voipProtocol,
		String country, String CDRdelivery, String ResellerID, String gateway, String quality,	String ipAddresstype,String IPPBXRange,  String  IPPBXAddress, 
		String SIPsignallingPort, String internetBasedCustomer, String vlanTag, String subInterfaceSlot, String signallngZone, 
		String signallingtransportProtocol, String  TLSfield,  String   srtp, String SignalingPort, String CustomerDefaultNumber, 
		String ReuseNetworkSelectorTable, String reuseNIFgroup, String reuseSigZonePart, String coltSignalingIP, String mediaIP,
		String callAdmissionControl, String CallLimitDropdwon, String CallRateLimitCheckboxSelection , String limitNumber, String callrateLimiteValue,
		String sourceAddressFiltering, String relSupport, String sipSessionkeepAliveTimer,String PBXTYPE, String PBXProfile,
		String PSXManualConfigurationTrunkGroup, String PSXManualConfigurationDDIRange, String ManualConfigurationonGSX,  String Carrier,
		String IPSignalingProfile, String EgressIPSignalingProfile
		
		) throws IOException, InterruptedException, DocumentException { 
	  
	  
	  
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
	
	
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/AddTrunk_Header")).isDisplayed()) {
		DriverTestcase.logger.log(LogStatus.PASS, "'Add Trunk' Page navigated as expected");
		System.out.println("'Add Trunk' Page navigated as expected");
		
		
	Thread.sleep(2000);
	scrolltoend();
	
	click_commonMethod(application, "OK Button", "trunk_okButton", xml);
	
	scrollToTop();
	//Thread.sleep(2000);
	
	
	
	
			//Trunk Group Description
		String expectedValue1=customerName+"_"+servicename+"_"+ServiceType.toUpperCase();
		compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);
		isDisplayed(application, "trunkGroupDEscription_textField", "Trunk Group Description", xml);
	
			//Primary Trunk	
		isDisplayed(application, "PrimaryTrunk_Dropdown", "Primary Trunk", xml);
	
		
			//VOIP Protocol
		isDisplayed(application, "voipProtocol_Dropdown", "VOIP Protocol", xml);
		SelectDropdownValueUnderSelectTag(application, "VOIP Protocol", voipProtocol, "voipProtocol_Dropdown", xml);
		
		
		//Billing COuntry
	warningMessage_commonMethod(application, "country_warningMessage", "Billing Country", xml);   //validate warning message
	isDisplayed(application, "billingCoutry_Dropdown", "Billing Country", xml);
	SelectDropdownValueUnderSelectTag(application, "Billing Country", country, "billingCoutry_Dropdown", xml);
	
	String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
	System.out.println("country dropdon value is: "+country_code);
	
	
			//CDR Delivery
	isDisplayed(application, "CDRdelivery_Dropdown", "CDR Delivery", xml);
	SelectDropdownValueUnderSelectTag(application, "CDR Delivery", CDRdelivery, "CDRdelivery_Dropdown", xml);
	
	 
	 
	 	//Gateway
	isDisplayed(application, "gateway_Dropdown", "Gateway", xml);
	SelectDropdownValueUnderSelectTag(application, "Gateway", gateway, "gateway_Dropdown", xml);
	gatewayCode=gateway_code(application, gateway);
	
	WebElement PrimaryTrunk2=getwebelement(xml.getlocator("//locators/" + application + "/PrimaryTrunk_Dropdown"));
	scrolltoview(PrimaryTrunk2);
	//Thread.sleep(2000);
	
	
		//Quality
	isDisplayed(application, "quality_Dropdown", "Quality", xml);
	
	
		//IP Address Type
	isDisplayed(application, "IPaddresstype_Dropdown", "IP Address Type", xml);
	SelectDropdownValueUnderSelectTag(application, "IP Address Type", ipAddresstype, "IPaddresstype_Dropdown", xml);

	WebElement IPAddressType=getwebelement(xml.getlocator("//locators/" + application + "/IPaddresstype_Dropdown"));
	scrolltoview(IPAddressType);
	//Thread.sleep(2000);
	
	
		//IP PBX Range
	warningMessage_commonMethod(application, "IPPBXRange_warningMessage", "IP PBX Range", xml);
	isDisplayed(application, "IPPBXRange_textField", "IP PBX Range", xml);
	
	
		//IP PBX Address
	warningMessage_commonMethod(application, "IPPBXAddress_warningMessage", "IP PBX Address", xml);
	isDisplayed(application, "IPPBXAddress_textField", "IP PBX Address", xml);
	
	
		//SIP Signalling Port
  if(voipProtocol.equalsIgnoreCase("SIP")) {
	  addtextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", SIPsignallingPort, xml);
	  
	  //message displaying under "SIP Signalling Port" text field	
		methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port:5060");
  }else {
	  DriverTestcase.logger.log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
	  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
  }
	

  
  
  
  	//Splitting the Gateway functionality into 2 SBC & Non-SBC 
  if(!gateway.contains("SBC")) {    //CASE-1 For NO-SBC
	
	  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {  //case A with Interface Based Customer selection
		  
		  DriverTestcase.logger.log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
		  
		//Internet Based Customer
		  isDisplayed(application, "internetBasedCustomer_checkbox", "Internet Based Customer", xml);
			addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
			
		  
		  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
		  if(vlanDefaultvalue.isEmpty()) {
			  DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
		  }else {
			 
			  DriverTestcase.logger.log(LogStatus.PASS, "When 'Internet Based Customer' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
			  if(vlanTag.equalsIgnoreCase("null")) {
					
					//Sub Interface slot
				  isDisplayed(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", xml);
					selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
					
					if(subInterfaceSlot.equalsIgnoreCase("null")) {
						
						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanDefaultvalue;
						isDisplayed(application, "subInterfaceName_textField", "Sub Interface Name", xml);
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
						
						//NIF Group  
						NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanDefaultvalue+NIGgroupdefaultValue_last;
						isDisplayed(application, "NIFgrouopp_textField", "NIF Group", xml);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
					else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
						
						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanDefaultvalue;
						isDisplayed(application, "subInterfaceName_textField", "Sub Interface Name", xml);
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
						
						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanDefaultvalue+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						isDisplayed(application, "NIFgrouopp_textField", "NIF Group", xml);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
					
				}else {
					
					//VLAN Tag
					isDisplayed(application, "vlanTag_textField", "VLAN Tag", xml);
					edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

				//Sub Interface slot
					isDisplayed(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", xml);
					selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
					
					if(subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanTag;
						isDisplayed(application, "subInterfaceName_textField", "Sub Interface Name", xml);
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
						
						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
						isDisplayed(application, "NIF Group", "NIFgrouopp_textField", xml);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
					else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
						
						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanTag;
						isDisplayed(application, "subInterfaceName_textField", "Sub Interface Name", xml);
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
						
						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						isDisplayed(application, "NIFgrouopp_textField", "NIF Group", xml);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
				}
		  }
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
	  } else {       // case B without Interface Based Customer selection
		if(vlanTag.equalsIgnoreCase("null")) {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Tag' field is  a mandatory field and not values are passed as an input");
			
			//Sub Interface slot
			isDisplayed(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", xml);
			selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
			
			if(subInterfaceSlot.equalsIgnoreCase("null")) {
				
				//Sub Interface Name
				SubInterfaceName=subInterfacename_starting+subInterfacename_middle;
				isDisplayed(application, "subInterfaceName_textField", "Sub Interface Name", xml);
				compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
				
				//NIF Group
				NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+NIGgroupdefaultValue_last;
				isDisplayed(application, "NIFgrouopp_textField", "NIF Group", xml);
				compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
			}
			else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
				
				//Sub Interface Name
				SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle;
				isDisplayed(application, "subInterfaceName_textField", "Sub Interface Name", xml);
				compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
				
				//NIF Group
				NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+NIGgroupdefaultValue_last;
				System.out.println("NIF Group value is: "+NIFgroup);
				isDisplayed(application, "NIFgrouopp_textField", "NIF Group", xml);
				compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
			}
			
			
		}else {
			
			//VLAN Tag
			isDisplayed(application, "vlanTag_textField", "VLAN Tag", xml);
			addtextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

		//Sub Interface slot
			isDisplayed(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", xml);
			selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
			
			if(subInterfaceSlot.equalsIgnoreCase("null")) {

				//Sub Interface Name
				SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanTag;
				isDisplayed(application, "subInterfaceName_textField", "Sub Interface Name", xml);
				compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
				
				//NIF Group
				NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
				isDisplayed(application, "NIFgrouopp_textField", "NIF Group", xml);
				compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
			}
			else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
				
				//Sub Interface Name
				SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanTag;
				isDisplayed(application, "subInterfaceName_textField", "Sub Interface Name", xml);
				compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
				
				//NIF Group
				NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
				System.out.println("NIF Group value is: "+NIFgroup);
				isDisplayed(application, "NIFgrouopp_textField", "NIF Group", xml);
				compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
			}
		}
		
	//Signalling port
		signallingPort(application, gateway);
	  }
  }
 
  
  
  
  
  
  
  
  
  
  
  
  
  //CASE-2 For SBC
  else if(gateway.contains("SBC")) {
	  
	  if(gateway.startsWith("FRA")) {//Case A 
		  
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
				  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
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
				  
				  isDisplayed(application, "AddressContext_textField", "Address Context", xml);
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
				  isDisplayed(application, "ipInterfaceGroup_textField", "IP Interface Group", xml);
				  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
				
				  
			//Signalling port
					signallingPort(application, gateway);	 
					
					
		
			  }else if(!vlanTag.equalsIgnoreCase("null")) {
				 
				//VLAN Tag
				  isDisplayed(application, "vlanTag_textField", "VLAN Tag", xml);
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
					  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
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
					  isDisplayed(application, "xpath", "labelname", xml);
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
					  isDisplayed(application, "ipInterfaceGroup_textField", "IP Interface Group", xml);
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
			  }
			  
			  
			  
			  
		  }else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
			  DriverTestcase.logger.log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
			  
				//Internet Based Customer
			  isDisplayed(application, "internetBasedCustomer_checkbox", "lnternet Based Customer", xml);
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
			  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
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
			  isDisplayed(application, "AddressContext_textField", "Address Context", xml);
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
			  isDisplayed(application, "ipInterfaceGroup_textField", "IP Interface Group", xml);
			  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
		  }
		  
		  
		  
		  
		  
		  
		  
		  
		  
	  }else {  //CASE-B non-FRA
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
				  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
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
				  isDisplayed(application, "AddressContext_textField", "Address Context", xml);
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
				  isDisplayed(application, "ipInterfaceGroup_textField", "IP Interface Group", xml);
				  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
				
				  
			//Signalling port
					signallingPort(application, gateway);	  
					
			}
		 
		 if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
			  
			  DriverTestcase.logger.log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
			  
			//Internet Based Customer
			  isDisplayed(application, "internetBasedCustomer_checkbox", "Internet Based Customer", xml);
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
		  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
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
		  isDisplayed(application, "AddressContext_textField", "Address Context", xml);
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
		  isDisplayed(application, "ipInterfaceGroup_textField", "IP Interface Group", xml);
		  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
		 }
	 }
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  								//STARTED SHOW ALL PANEL
  		//Signalling Zone
  if(internetBasedCustomer.equalsIgnoreCase("yes")) {
	  isDisplayed(application, "signallingZone_textField", "Signaling Zone", xml);
	  compareText_fromtextFields(application, "Signaling Zone", "signallingZone_textField", signallingZoneDefaultValue, xml);
	  edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
	  
  }else {
	  isDisplayed(application, "signallingZone_textField", "Signaling Zone", xml);
	  edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
  }
	


  		//Signalling Transport Protocol
  	isDisplayed(application, "signallingTransportProtocol_Dropdown", "Signalling Transport Protocol", xml);
	selectValueInsideDropdown(application, "signallingTransportProtocol_Dropdown", "Signalling Transport Protocol", signallingtransportProtocol, xml);

	WebElement internetBasedCusotmerView=getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox"));
	scrolltoview(internetBasedCusotmerView);
	Thread.sleep(2000);
	
	
	if(signallingtransportProtocol.equalsIgnoreCase("sip-tls-tcp")) {
		
		//TLS Profile
		isDisplayed(application, "TLS_textField", "TLS Profile", xml);
		addtextFields_commonMethod(application, "TLS Profile", "TLS_textField", TLSfield, xml);
		
		//SRTP
		isDisplayed(application, "srtp_checkbox", "SRTP", xml);
		addCheckbox_commonMethod(application, "srtp_checkbox", "SRTP", srtp, "no", xml);
	}
	
	
		// Customer Default Number	CustomerDefaultNumber_WarningMessage
	warningMessage_commonMethod(application, "CustomerDefaultNumber_WarningMessage", "Customer Default Number", xml);
	isDisplayed(application, "CustomerDefaultNumber_textfield", "Customer Default Number", xml);
	addtextFields_commonMethod(application, "Customer Default Number", "CustomerDefaultNumber_textfield", CustomerDefaultNumber, xml);

	
	
	//Show All
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDefaultNumber_Header")));
			isDisplayed(application, "ShowAllButton_ViewTrunk", "Show ALL Button", xml);
			click_commonMethod(application, "Show ALL Button", "ShowAllButton_ViewTrunk", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/AddTrunk_CENTREX_Header")));
			
	
	  	// Reuse Network Selector Table Checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ReuseNetworkSelectorTable_checkbox")).isEnabled()){
			if (ReuseNetworkSelectorTable.equalsIgnoreCase("YES")) {
				isDisplayed(application, "ReuseNetworkSelectorTable_checkbox", "Reuse Network Selector Table", xml);
				click(application, "'Reuse Network Selector Table' checkbox", "ReuseNetworkSelectorTable_checkbox");
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
				System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
			} else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
				System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
			}
		
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
	  
		// Reuse Network Selector Table
			isDisplayed(application, "ReuseNetworkSelectorTable_checkbox", "Reuse Network Selector Table", xml);
		addCheckbox_commonMethod(application, "ReuseNetworkSelectorTable_checkbox", "Reuse Network Selector Table", ReuseNetworkSelectorTable, "No", xml);

		
		// Reuse Network Selector Table Checkbox
				if(getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox")).isEnabled()){
					if (ReuseNetworkSelectorTable.equalsIgnoreCase("YES")) {
						isDisplayed(application, "reuseNIFgroup_checkbox", "Reuse Network Selector Table", xml);
						click(application, "'Reuse Network Selector Table' checkbox", "reuseNIFgroup_checkbox");
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
					} else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
					}
				
					}else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
					}
				
		//Reuse NIF Group
				isDisplayed(application, "reuseNIFgroup_checkbox", "Reuse NIF Group", xml);
		addCheckbox_commonMethod(application, "reuseNIFgroup_checkbox", "Reuse NIF Group", reuseNIFgroup, "No", xml);
		
		// Reuse Network Selector Table Checkbox
				if(getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox")).isEnabled()){
					if (ReuseNetworkSelectorTable.equalsIgnoreCase("YES")) {
						isDisplayed(application, "reuseNIFgroup_checkbox", "Reuse Network Selector Table", xml);
						click(application, "'Reuse Network Selector Table' checkbox", "reuseNIFgroup_checkbox");
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
					} else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
					}
				
					}else {
						DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
					}
				
		//Reuse Sig Zone/Part
				isDisplayed(application, "reuseSigZonePart_checkbox", "Reuse Sig Zone/Part", xml);
		addCheckbox_commonMethod(application, "reuseSigZonePart_checkbox", "Reuse Sig Zone/Part", reuseSigZonePart, "No", xml);
	
	
	
	
		// Colt Signalling IP
		isDisplayed(application, "coltSignalingIP_textField", "Colt Signalling IP", xml);
		addtextFields_commonMethod(application, "Colt Signalling IP", "coltSignalingIP_textField", coltSignalingIP, xml);
	
		//Media IP
		isDisplayed(application, "mediaIP_textField", "Media IP", xml);
	addtextFields_commonMethod(application, "Media IP", "mediaIP_textField", mediaIP, xml);
	
	
	
	
		// PBX Type	
	isDisplayed(application, "PBXType_textfield", "PBX Type", xml);
	addtextFields_commonMethod(application, "PBX Type", "PBXType_textfield", PBXTYPE, xml);

		// PBX Profile
	isDisplayed(application, "PBXProfile_Dropdown", "PBX Profile", xml);
	selectValueInsideDropdown(application, "PBXProfile_Dropdown", "PBX Profile", PBXProfile, xml);


	
			// PSX Manual Configuration-Trunk Group	
	isDisplayed(application, "PSXManualConfigurationTrunkGroup_checkbox", "PSX Manual Configuration-Trunk Group\", PSXManualConfigurationTrunkGroup", xml);
		addCheckbox_commonMethod(application, "PSXManualConfigurationTrunkGroup_checkbox", "PSX Manual Configuration-Trunk Group", PSXManualConfigurationTrunkGroup, "No" ,xml);
		
			// PSX Manual Configuration-DDI-Range
		isDisplayed(application, "PSXManualConfigurationDDIRange_checkbox", "PSX Manual Configuration-DDI-Range", xml);
		addCheckbox_commonMethod(application, "PSXManualConfigurationDDIRange_checkbox", "PSX Manual Configuration-DDI-Range", PSXManualConfigurationDDIRange, "No" ,xml);


			// Manual Configuration On GSX
		isDisplayed(application, "ManualConfigurationonGSX_checkbox", "Manual Configuration On GSX", xml);
		addCheckbox_commonMethod(application, "ManualConfigurationonGSX_checkbox", "Manual Configuration On GSX", ManualConfigurationonGSX, "No", xml);
		
		
		
	scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/NonServiceImpacting_Header")));
		//Call Admission Control
	isDisplayed(application, "callAdmissionControl_checkbox", "Call Admission Control", xml);
	addCheckbox_commonMethod(application, "callAdmissionControl_checkbox", "Call Admission Control", callAdmissionControl, "No", xml);

	if(callAdmissionControl.equalsIgnoreCase("yes")) {
		//Call Limit
		isDisplayed(application, "callLimit_Dropdown", "Call Limit", xml);
		selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", CallLimitDropdwon, xml);
		
		if(CallLimitDropdwon.equalsIgnoreCase("Defined")) {
			isDisplayed(application, "limitNumber_textField", "Limit Number", xml);
			addtextFields_commonMethod(application, "Limit Number", "limitNumber_textField", limitNumber, xml);
			
		}
	}
	
	
		//Call Rate Limit
	isDisplayed(application, "callrateLimit_checkbox", "Call Rate Limit", xml);
	addCheckbox_commonMethod(application, "callrateLimit_checkbox", "Call Rate Limit", CallRateLimitCheckboxSelection, "No", xml);
	if(CallRateLimitCheckboxSelection.equalsIgnoreCase("yes")) {
		
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
	
	
	
	
	
	
	scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/SIPSERVICES_Header")));
		//Source Address Filtering
	isDisplayed(application, "sourceAddressFiltering_Dropdown", "Source Address Filtering", xml);
	selectValueInsideDropdown(application, "sourceAddressFiltering_Dropdown", "Source Address Filtering", sourceAddressFiltering, xml);
	
		//100rel Support
	isDisplayed(application, "relsupport_Dropdown", "100rel Support", xml);
	selectValueInsideDropdown(application, "relsupport_Dropdown", "100rel Support", relSupport, xml);
	
		//SIP Session Keepalive Timer(Sec)
	isDisplayed(application, "sipSessionKeepAliverTimer_textField", "SIP Session Keepalive Timer(Sec)", xml);
	edittextFields_commonMethod(application, "SIP Session Keepalive Timer(Sec)", "sipSessionKeepAliverTimer_textField", sipSessionkeepAliveTimer, xml);

	//Text message under "SIP Session Keepalive timer"
	isDisplayed(application, "defaultTextMessageUnderSIPsessionTimer", "SIP Session Keepalive Timer(Sec)", xml);
		methodToFindMessagesUnderTextField(application, "defaultTextMessageUnderSIPsessionTimer", "SIP Session Keepalive Timer(Sec)", "Default SIP Session Keepalive Timer (sec): 1800");
	
		
		
		
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PSXTRUNKGROUPFIELDS_Header")));
		//Carrier
		warningMessage_commonMethod(application, "Carrier_warningMessage", "Carrier", xml);   //validate warning message
		isDisplayed(application, "carriers_TextField", "Carrier", xml);
		ClearAndEnterTextValue(application, "Carrier textfield", "carriers_TextField", Carrier);
		
		
		//IP Signalling Profile
		isDisplayed(application, "IPsignallingProfile_textField", "IP Signaling Profile", xml);
		ClearAndEnterTextValue(application, "IP Signaling Profile", "IPsignallingProfile_textField", IPSignalingProfile);
				
				
		//Egress IP Signaling Profile
		isDisplayed(application, "EgressipSignal_TextField", "Egress IP Signaling Profile", xml);
		ClearAndEnterTextValue(application, "Egress IP Signaling Profile", "EgressipSignal_TextField", EgressIPSignalingProfile);

	
		
		WebElement reusenifgroup=getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox"));
		scrolltoview(reusenifgroup);
		Thread.sleep(2000);
		
		
	
	scrolltoend();
	Thread.sleep(1000);
	
	isDisplayed(application, "trunk_okButton", "OK", xml);
	isDisplayed(application, "cancelbutton", "Cancel", xml);
	
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'Add Trunk' Page is not navigated");
		System.out.println("'Add Trunk' Page is not navigated");
	}
	
  }
  
 //DONE









  public void verifyAddTrunkFunction(String application, String customerName, String servicename, String ServiceType, String PrimaryTrunk, String voipProtocol,
			String country, String CDRdelivery, String ResellerID, String gateway, String quality,	String ipAddresstype,String IPPBXRange,  String  IPPBXAddress, 
			String SIPsignallingPort, String internetBasedCustomer, String vlanTag, String subInterfaceSlot, String signallngZone, 
			String signallingtransportProtocol, String  TLSfield,  String   srtp, String SignalingPort, String CustomerDefaultNumber, 
			String ReuseNetworkSelectorTable, String reuseNIFgroup, String reuseSigZonePart, String coltSignalingIP, String mediaIP,
			String callAdmissionControl, String CallLimitDropdwon, String CallRateLimitCheckboxSelection , String limitNumber, String callrateLimiteValue,
			String sourceAddressFiltering, String relSupport, String sipSessionkeepAliveTimer,String PBXTYPE, String PBXProfile,
			String PSXManualConfigurationTrunkGroup, String PSXManualConfigurationDDIRange, String ManualConfigurationonGSX,  String Carrier,
			String IPSignalingProfile, String EgressIPSignalingProfile
			
			) throws IOException, InterruptedException, DocumentException { 
		  
		  
		  
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
		
		
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddTrunk_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Add Trunk' Page navigated as expected");
			System.out.println("'Add Trunk' Page navigated as expected");
			
			
		Thread.sleep(2000);
		scrolltoend();
		
		click_commonMethod(application, "OK Button", "trunk_okButton", xml);
		
		scrollToTop();
		//Thread.sleep(2000);
		
		
		
		
				//Trunk Group Description
			String expectedValue1=customerName+"_"+servicename+"_"+ServiceType.toUpperCase();
			compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

		
				//Primary Trunk	
		//**SelectDropdownValueUnderSelectTag(application, "Primary Trunk", PrimaryTrunk, "PrimaryTrunk_Dropdown", xml);
		
			
				//VOIP Protocol
			SelectDropdownValueUnderSelectTag(application, "VOIP Protocol", voipProtocol, "voipProtocol_Dropdown", xml);
		
			
			//Billing COuntry
		warningMessage_commonMethod(application, "country_warningMessage", "Billing Country", xml);   //validate warning message
		SelectDropdownValueUnderSelectTag(application, "Billing Country", country, "billingCoutry_Dropdown", xml);
		
		String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
		System.out.println("country dropdon value is: "+country_code);
		
		
				//CDR Delivery
		SelectDropdownValueUnderSelectTag(application, "CDR Delivery", CDRdelivery, "CDRdelivery_Dropdown", xml);
		
		
		
		 
		 
		 	//Gateway
		SelectDropdownValueUnderSelectTag(application, "Gateway", gateway, "gateway_Dropdown", xml);
		gatewayCode=gateway_code(application, gateway);
		
		WebElement PrimaryTrunk2=getwebelement(xml.getlocator("//locators/" + application + "/PrimaryTrunk_Dropdown"));
		scrolltoview(PrimaryTrunk2);
		//Thread.sleep(2000);
		
		
			//Quality
		SelectDropdownValueUnderSelectTag(application, "Quality", quality, "quality_Dropdown", xml);
		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");
		
		
			//IP Address Type
		SelectDropdownValueUnderSelectTag(application, "IP Address Type", ipAddresstype, "IPaddresstype_Dropdown", xml);

		WebElement IPAddressType=getwebelement(xml.getlocator("//locators/" + application + "/IPaddresstype_Dropdown"));
		scrolltoview(IPAddressType);
		//Thread.sleep(2000);
		
		
			//IP PBX Range
		warningMessage_commonMethod(application, "IPPBXRange_warningMessage", "IP PBX Range", xml);
		EnterTextValue(application, IPPBXRange, "IP PBX Range text field", "IPPBXRange_textField");
		click_commonMethod(application, "Click on >>", "IPPBXRange_addButtton", xml);
		GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/IPPBXRange_addedValue_selectDropdownField")), "IP PBX range Selection Box)");
		
		
			//IP PBX Address
		warningMessage_commonMethod(application, "IPPBXAddress_warningMessage", "IP PBX Address", xml);
		EnterTextValue(application, IPPBXAddress, "IP PBX Address text field", "IPPBXAddress_textField");
		
		
		
			//SIP Signalling Port
	  if(voipProtocol.equalsIgnoreCase("SIP")) {
		  addtextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", SIPsignallingPort, xml);
		  
		  //message displaying under "SIP Signalling Port" text field	
			methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port:5060");
	  }else {
		  DriverTestcase.logger.log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
		  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
	  }
		

	  
	  
	  
	  	//Splitting the Gateway functionality into 2 SBC & Non-SBC 
	  if(!gateway.contains("SBC")) {    //CASE-1 For NO-SBC
		
		  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {  //case A with Interface Based Customer selection
			  
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
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
		  } else {       // case B without Interface Based Customer selection
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
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  //CASE-2 For SBC
	  else if(gateway.contains("SBC")) {
		  
		  if(gateway.startsWith("FRA")) {//Case A 
			  
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
						
						
			
				  }else if(!vlanTag.equalsIgnoreCase("null")) {
					 
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
				  
				  
				  
				  
			  }else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
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
			  
			  
			  
			  
			  
			  
			  
			  
			  
		  }else {  //CASE-B non-FRA
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
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  								//STARTED SHOW ALL PANEL
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
		
		
			// Customer Default Number	CustomerDefaultNumber_WarningMessage
		warningMessage_commonMethod(application, "CustomerDefaultNumber_WarningMessage", "Customer Default Number", xml);
		addtextFields_commonMethod(application, "Customer Default Number", "CustomerDefaultNumber_textfield", CustomerDefaultNumber, xml);

		
		
		//Show All
//				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDefaultNumber_Header")));
//				click_commonMethod(application, "Show ALL Button", "ShowAllButton_ViewTrunk", xml);
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/AddTrunk_CENTREX_Header")));
				
		
//		  	// Reuse Network Selector Table Checkbox
//			if(getwebelement(xml.getlocator("//locators/" + application + "/ReuseNetworkSelectorTable_checkbox")).isEnabled()){
//				if (ReuseNetworkSelectorTable.equalsIgnoreCase("YES")) {
//					click(application, "'Reuse Network Selector Table' checkbox", "ReuseNetworkSelectorTable_checkbox");
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//					System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//				} else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
//					System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
//				}
//			
//				}else {
//					DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}
		  
			// Reuse Network Selector Table
			addCheckbox_commonMethod(application, "ReuseNetworkSelectorTable_checkbox", "Reuse Network Selector Table", ReuseNetworkSelectorTable, "No", xml);

			
//			// Reuse Network Selector Table Checkbox
//					if(getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox")).isEnabled()){
//						if (ReuseNetworkSelectorTable.equalsIgnoreCase("YES")) {
//							click(application, "'Reuse Network Selector Table' checkbox", "reuseNIFgroup_checkbox");
//							DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//						} else {
//							DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
//						}
//					
//						}else {
//							DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//						}
			//Reuse NIF Group
			addCheckbox_commonMethod(application, "reuseNIFgroup_checkbox", "Reuse NIF Group", reuseNIFgroup, "No", xml);
			
//			// Reuse Network Selector Table Checkbox
//					if(getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox")).isEnabled()){
//						if (ReuseNetworkSelectorTable.equalsIgnoreCase("YES")) {
//							click(application, "'Reuse Network Selector Table' checkbox", "reuseNIFgroup_checkbox");
//							DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//						} else {
//							DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
//						}
//					
//						}else {
//							DriverTestcase.logger.log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//						}
					
			//Reuse Sig Zone/Part
			addCheckbox_commonMethod(application, "reuseSigZonePart_checkbox", "Reuse Sig Zone/Part", reuseSigZonePart, "No", xml);
		
		
		
		
			// Colt Signalling IP
			addtextFields_commonMethod(application, "Colt Signalling IP", "coltSignalingIP_textField", coltSignalingIP, xml);
		
			//Media IP
		addtextFields_commonMethod(application, "Media IP", "mediaIP_textField", mediaIP, xml);
		
		
		
		
			// PBX Type	
		addtextFields_commonMethod(application, "PBX Type", "PBXType_textfield", PBXTYPE, xml);

			// PBX Profile
		selectValueInsideDropdown(application, "PBXProfile_Dropdown", "PBX Profile", PBXProfile, xml);


		
				// PSX Manual Configuration-Trunk Group	
			addCheckbox_commonMethod(application, "PSXManualConfigurationTrunkGroup_checkbox", "PSX Manual Configuration-Trunk Group", PSXManualConfigurationTrunkGroup, "No" ,xml);
			
				// PSX Manual Configuration-DDI-Range
			addCheckbox_commonMethod(application, "PSXManualConfigurationDDIRange_checkbox", "PSX Manual Configuration-DDI-Range", PSXManualConfigurationDDIRange, "No" ,xml);


				// Manual Configuration On GSX
			addCheckbox_commonMethod(application, "ManualConfigurationonGSX_checkbox", "Manual Configuration On GSX", ManualConfigurationonGSX, "No", xml);
			
			
			
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/NonServiceImpacting_Header")));
			//Call Admission Control
		addCheckbox_commonMethod(application, "callAdmissionControl_checkbox", "Call Admission Control", callAdmissionControl, "No", xml);

		if(callAdmissionControl.equalsIgnoreCase("yes")) {
			//Call Limit
			selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", CallLimitDropdwon, xml);
			
			if(CallLimitDropdwon.equalsIgnoreCase("Defined")) {
				addtextFields_commonMethod(application, "Limit Number", "limitNumber_textField", limitNumber, xml);
				
			}
		}
		
		
			//Call Rate Limit
		addCheckbox_commonMethod(application, "callrateLimit_checkbox", "Call Rate Limit", CallRateLimitCheckboxSelection, "No", xml);
		if(CallRateLimitCheckboxSelection.equalsIgnoreCase("yes")) {
			
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
		
		
		
		
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/SIPSERVICES_Header")));
			//Source Address Filtering
		selectValueInsideDropdown(application, "sourceAddressFiltering_Dropdown", "Source Address Filtering", sourceAddressFiltering, xml);
		
			//100rel Support
		selectValueInsideDropdown(application, "relsupport_Dropdown", "100rel Support", relSupport, xml);
		
			//SIP Session Keepalive Timer(Sec)
		edittextFields_commonMethod(application, "SIP Session Keepalive Timer(Sec)", "sipSessionKeepAliverTimer_textField", sipSessionkeepAliveTimer, xml);

		//Text message under "SIP Session Keepalive timer"
			methodToFindMessagesUnderTextField(application, "defaultTextMessageUnderSIPsessionTimer", "SIP Session Keepalive Timer(Sec)", "Default SIP Session Keepalive Timer (sec): 1800");
		
			
			
			
			
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PSXTRUNKGROUPFIELDS_Header")));
			//Carrier
			warningMessage_commonMethod(application, "Carrier_warningMessage", "Carrier", xml);   //validate warning message
			ClearAndEnterTextValue(application, "Carrier textfield", "carriers_TextField", Carrier);
			
			
			//IP Signalling Profile
			ClearAndEnterTextValue(application, "IP Signaling Profile", "IPsignallingProfile_textField", IPSignalingProfile);
					
					
			//Egress IP Signaling Profile
			ClearAndEnterTextValue(application, "Egress IP Signaling Profile", "EgressipSignal_TextField", EgressIPSignalingProfile);

		
			
			WebElement reusenifgroup=getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox"));
			scrolltoview(reusenifgroup);
			Thread.sleep(2000);
			
		
			
			
			//Route Priority
		//**selectValueInsideDropdown(application, "routepriority_Dropdown", "Route Priority", routePriority, xml);
		//Address Reachability
			//**selectValueInsideDropdown(application, "addressReachability_Dropdown", "Address Reachability", addressReachability, xml);
			//COS profile
			//**addTrunk_existingNewDropdownField(application, COSprofile_existingFieldSelection, COSprofile_newFieldSelection, COSprofile_existingValue,
			//**COSprofile_newValue, "COSprofile_Dropdown", "COSprofile_checkbox", "COSprofile_TextField", "COS Profile");
			//PSPG Name
			//**addTrunk_existingNewDropdownField(application, PSPGname_existingFieldSelection, PSPGname_newFieldSelection, pspgName_existingValue,
			//**pspgName_newValue, "PSPSGname_Dropdown", "PSPGname_Checkbox", "PSPGname_TextField", "PSPG Name");
			//**scrolltoend();
			//**Thread.sleep(2000);
		
		
		



			
		
		
		
		scrolltoend();
		Thread.sleep(1000);
		
		click_commonMethod(application, "OK", "trunk_okButton", xml);
		GetText(application, "Trunk Creation Success Message", "CreateTrunkSuccessMessage");
		
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Add Trunk' Page is not navigated");
			System.out.println("'Add Trunk' Page is not navigated");
		}
		
	  }
	  
	 //DONE








	public void addTrunk2(String application, String customerName, String servicename, String trunkType, String voipProtocol, String country, String CDRdelivery, String gateway, String quality,
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
			code="542";
		}
		
		else if(gateway.equals("DEVSBC1")) {
			code="1029";
		}
		
		else if(gateway.equals("DEVSBC2")) {
			code="1014";		
		}
				
		else if(gateway.equals("OPSGSX1")) {
			code="508";
		}
				
		else if(gateway.equals("MILGSX1")) {
			code="696";
		}
				
		else if(gateway.equals("ZRHNBS1")) {
			code="1006";
		}
				
		else if(gateway.equals("PARNBS1")) {
			code="896";
		}
				
		else if(gateway.equals("LONNBS1")) {
			code="789";
		}
				
		else if(gateway.equals("FRANBS1")) {
			code="1137";
		}
				
		else if(gateway.equals("MADGSX1")) {
			code="741";
		}
				
		else if(gateway.equals("BHXNBS1")) {
			code="1013";
		}
				
		else if(gateway.equals("PARGSX2")) {
			code="923";
		}
				
		else if(gateway.equals("FRAGSX2")) {
			code="752";
		}
				
		else if(gateway.equals("PARGSX3")) {
			code="629";
		}
				
		else if(gateway.equals("FRASBC1")) {
			code="1026";
		}
				
		else if(gateway.equals("PARSBC1")) {
			code="1006";
		}
				
		else if(gateway.equals("ZRHGSX2")) {
			code="506";
		}
				
		else if(gateway.equals("FRASBC2")) {
			code="1024";
		}
				
		else if(gateway.equals("PARSBC2")) {
			code="1013";
		}
				
		else if(gateway.equals("FRASBC3")) {
			code="1009";
		}
				
		else if(gateway.equals("PARSBC3")) {
			code="1006";
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
	
	
	
	
	
	
	
	//VIEW TRUNK
	
public void verifyAddedTrunkInformationInviewTrunk(String application, String customerName, String servicename, String ServiceType, String PrimaryTrunk, String voipProtocol,
		String country, String CDRdelivery, String ResellerID, String gateway, String quality,	String ipAddresstype,String IPPBXRange,  String  IPPBXAddress, 
		String SIPsignallingPort, String internetBasedCustomer, String vlanTag, String subInterfaceSlot, String signallngZone, 
		String signallingtransportProtocol, String  TLSfield,  String   srtp, String SignalingPort, String CustomerDefaultNumber, 
		String ReuseNetworkSelectorTable, String reuseNIFgroup, String reuseSigZonePart, String coltSignalingIP, String mediaIP,
		String callAdmissionControl, String CallLimitDropdwon, String CallRateLimitCheckboxSelection , String limitNumber, String callrateLimiteValue,
		String sourceAddressFiltering, String relSupport, String sipSessionkeepAliveTimer,String PBXTYPE, String PBXProfile,
		String PSXManualConfigurationTrunkGroup, String PSXManualConfigurationDDIRange, String ManualConfigurationonGSX,  String Carrier,
		String IPSignalingProfile, String EgressIPSignalingProfile
				
				  ) throws IOException, InterruptedException, DocumentException { 
	
	
	
		
		String AddressContext="EXTERNAL_AC_";
		String IPINTERFACEGROUP ="EXTERNAL_IPIG_";
		String IPINTERFACE=	"EXTERNAL_IPIF_";
		
		String subInterfacename_starting="SIF-1-";
		String subInterfacename_middle="-2-";
		String NIFgroupDEfaultValue_starting="SIF-1-";
		String NIFgroupDEfaultValue_middle="-2-";
		String NIGgroupdefaultValue_last="-OUTSIDE";


		if(getwebelement(xml.getlocator("//locators/" + application + "/ViewTrunk_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View Trunk' Page navigated as expected");
			System.out.println("'View Trunk' Page navigated as expected");
			
			scrollToTop();
			Thread.sleep(2000);
			
			
//		//Trunk Group Description
//			String expectedValue1=customerName+"_"+servicename+"_"+ServiceType.toUpperCase();
//			compareText_InViewPage(application, "Trunk Group Description", expectedValue1, xml);
//
//		//Primary Trunk
//			//**compareText_InViewPage(application, "Primary Trunk", PrimaryTrunk, xml);
//			
//		//VOIP Protocol
//			compareText_InViewPage(application, "VOIP Protocol", voipProtocol, xml);
//			
//		//Billing Country
//			compareText_InViewPage(application, "Billing Country", country, xml);
//		
//		//CDR Delivery
//			compareText_InViewPage(application, "CDR Delivery", CDRdelivery, xml);
//			
//		//Reseller ID
//			compareText_InViewPage(application, "Reseller ID", ResellerID, xml);	
//			
//		//Gateway
//			compareText_InViewPage(application, "Gateway", gateway, xml);
//			
		//Quality
			compareText_InViewPage(application, "Quality", quality, xml);
			
			
		//Trunk Group Name
		//***	compareText_InViewPage(application, "Trunk Group Name", primarytrunkGroupname, xml);//Need to do
			
			
		//IP Address Type
			compareText_InViewPage(application, "IP Address Type", ipAddresstype, xml);
			
		//IP PBX Range
			compareText_InViewPage(application, "IP PBX Range", IPPBXRange, xml);
			
		//IP PBX Address
			compareText_InViewPage(application, "IP PBX Address", IPPBXAddress, xml);
		

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
					  	String SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlan_actualValue;
						compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);
						
						//NIF Group
						String NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						compareText_InViewPage(application, "NIF Group", NIFgroup, xml);
					  
					  //Signalling Zone
						if(signallngZone.equalsIgnoreCase("null")) {
					  compareText_InViewPage(application, "Signalling Zone", "OUT-UNTRUSTED", xml);
						}
						else if(!signallngZone.equalsIgnoreCase("null")) {
							  compareText_InViewPage(application, "Signalling Zone", signallngZone, xml);
								}
					  
						
							
					  
				  }else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
					 
					  //VLAN Tag
//					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  //Sub Interface Name
					  	String SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlan_actualValue;
						compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);
						
						//NIF Group
						String NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
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
				  
				  
				  
				  
			  }else if(gateway.contains("SBC")) {
				  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
//					  //Address Content
//					  compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);
//					  
//					  //IP Interface Group
//					  compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);
//					  
//					  //IP Interface
//					  compareText_InViewPage(application, "IP Interface", IPINTERFACE+vlan_actualValue, xml);
					  
					  
					//Signalling Zone
						if(signallngZone.equalsIgnoreCase("null")) {
					  compareText_InViewPage(application, "Signalling Zone", "OUT-UNTRUSTED", xml);
						}
						else if(!signallngZone.equalsIgnoreCase("null")) {
							  compareText_InViewPage(application, "Signalling Zone", signallngZone, xml);
								}
					  
					  
				  }else if(internetBasedCustomer.equalsIgnoreCase("No")) {
					  
					  //VLAN Tag
					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
					  
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  
//					  //Address Content
//					  compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);
//					  
//					  //IP Interface Group
//					  compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);
//					  
//					  //IP Interface
//					  compareText_InViewPage(application, "IP Interface", IPINTERFACE+vlan_actualValue, xml);
				  }
			  }
			
			  
			  
			  
			  
		//Signalling Transport Protocol
		compareText_InViewPage(application, "Signalling Transport Protocol", signallingtransportProtocol, xml);
			
			
		
		//Signalling Port
		String actualSignalingPort=getwebelement(xml.getlocator("//locators/" + application + "/SignallingPortValue")).getText();
				if(internetBasedCustomer.equalsIgnoreCase("Yes")) {	
					sa.assertEquals(actualSignalingPort, SignalingPort, "Actual & Expected Signalling Port is not same");
					//Or signallingPort_viewPage(application, gateway);
				}
				
				
		
		//Customer Default Number
		compareText_InViewPage(application, "Customer Default Number", CustomerDefaultNumber, xml);


		
		//Show All
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDefaultNumber_Header")));
		click_commonMethod(application, "Show ALL Button", "ShowAllButton_ViewTrunk", xml);
		//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CENTREX_Header")));

		
		
		//Reuse Network Selector Table	
		compareText_InViewPage(application, "Reuse Network Selector Table", ReuseNetworkSelectorTable, xml);
				
		//Reuse NIF Group	
		compareText_InViewPage(application, "Reuse NIF Group", reuseNIFgroup, xml);
					
		//Reuse Sig Zone/Port
		compareText_InViewPage(application, "Reuse Sig Zone/Part", reuseSigZonePart, xml);				
				
		
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CENTREX_Header")));
		//Colt Signalling IP
		compareText_InViewPage(application, "Colt Signalling IP", coltSignalingIP, xml);
		
		//Media IP
		compareText_InViewPage(application, "Media IP", mediaIP, xml);
			

			
		// PBX Type	
		compareText_InViewPage(application, "PBX Type", PBXTYPE, xml);

		// PBX Profile
		compareText_InViewPage(application, "PBX Profile", PBXProfile, xml);



		// PSX Manual Configuration-Trunk Group	
		compareText_InViewPage(application, "PSX Manual Configuration-Trunk Group", PSXManualConfigurationTrunkGroup, xml);
	
		// PSX Manual Configuration-DDI-Range
		compareText_InViewPage(application, "PSX Manual Configuration-DDI-Range", PSXManualConfigurationDDIRange, xml);

		// Manual Configuration On GSX
		compareText_InViewPage(application, "Manual Configuration on GSX", ManualConfigurationonGSX, xml);
		
		
		//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MANUALCONFIGURATION_Header")));
		//Call Admission Control
			compareText_InViewPage(application, "Call Admission Control", callAdmissionControl, xml);
			
			if(callAdmissionControl.equalsIgnoreCase("yes")) {
			  //call limit	
				compareText_InViewPage(application, "Call Limit",CallLimitDropdwon , xml);
				
				if(CallLimitDropdwon.equalsIgnoreCase("Defined")) {
					//Limit Number
					compareText_InViewPage(application, "Limit Number", limitNumber , xml);
				}
			}
		
		//Call Rate Limit
			if(CallRateLimitCheckboxSelection.equalsIgnoreCase("Yes")) {
				
				//call rate limit value
				compareText_InViewPage(application,"Call Rate Limit", callrateLimiteValue, xml);
			}
			
		
			
			
			
			
		//Source Address Filtering
			compareText_InViewPage(application, "Source Address Filtering", sourceAddressFiltering, xml);
			
		//100rel Support	
			compareText_InViewPage(application, "100rel Support", relSupport, xml);
			
		//SIP session Keepalive timer
			compareText_InViewPage(application, "SIP Session Keepalive Timer(Sec)", sipSessionkeepAliveTimer, xml);
			


		// Carrier	
			compareText_InViewPage(application, "Carrier", Carrier, xml);
			
		// IP Signaling Profile
			compareText_InViewPage(application, "IP Signaling Profile", IPSignalingProfile, xml);
			
		// Egress IP Signaling Profile
			compareText_InViewPage(application, "Egress IP Signaling Profile", EgressIPSignalingProfile, xml);
			
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View Trunk' Page is not navigated as expected");
			System.out.println("'View Trunk' Page is not navigated as expected");
		}
		
			
	}
	
	
	
	
	
	public void navigateToViewTrunkPage(String application, String TrunkGroupSiteOrderNumber,  String ExistingTrunkName) throws InterruptedException, DocumentException {
		
		//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrderPanel_header")));
		
		boolean TrunkNameToAddCPEDeviceText=false;
		boolean trunkgrupOrderErrMsg= false;
		boolean TrunkGroupSiteOrderNumberText=false;
//		waitForpageload();
		scrolltoend();
		Thread.sleep(5000);
		scrolltoend();
		TrunkNameToAddCPEDeviceText= getwebelement("//div[text()='"+ExistingTrunkName+"']").isDisplayed();
		if(TrunkNameToAddCPEDeviceText) {
			DriverTestcase.logger.log(LogStatus.PASS, "Expected 'Trunk Name' is displaying as expected under Trunk Panel in 'view Service' page");
			System.out.println("Expected 'Trunk Name'' is displaying as expected under Trunk Panel in 'view Service' page");
		
			scrolltoend();

		//click(application, "Trunk Name in Trunk panel", "//div[text()='"+ExistingTrunkName+"']");//Select Checkbox for expected trunk Not working
		WebElement CheckboxForExpectedTrunk = driver.findElement(By.xpath("//div[text()='"+ExistingTrunkName+"']"));
		String TrunkName = CheckboxForExpectedTrunk.getText().toString();
		CheckboxForExpectedTrunk.click();
		DriverTestcase.logger.log(LogStatus.PASS,"Step : Checkbox Selection is done for Expected trunk, Selected Trunk is " + TrunkName);
		Log.info("Selected Trunk is  : " + TrunkName);
		
		Thread.sleep(2000);
		//String TunkActionLinkXpathInViewServicePage="//div[div[span[text()='"+ TrunkGroupSiteOrderNumber +"']]]/following-sibling::div//span[text()='Add Trunk']";
		String TunkActionLinkXpathInViewServicePage="//div[div[span[contains(text(),'"+TrunkGroupSiteOrderNumber+"')]]]/following-sibling::div//button[text()='Action']";
		//**click_commonMethod_PassingWebelementDirectly(application, "Trunk Action", TunkActionLinkXpathInViewServicePage, xml);
		click(application, "ACTION LINK in Trunk panel", "ViewService_Trunk_ActionLink2");
													
		//Thread.sleep(2000);
		String ViewTrunkLinkXpathInViewServicePage="//div[div[span[contains(text(),'"+TrunkGroupSiteOrderNumber+"')]]]/following-sibling::div//a[text()='View']";
		//**click_commonMethod_PassingWebelementDirectly(application, "View Link", ViewTrunkLinkXpathInViewServicePage, xml);
		click(application, "View Trunk Link", "ViewService_Trunk_ViewTrunkLink");
		
		}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "Expected 'Trunk Name' is not displaying as expected under Trunk Panel in 'view Service' page");
				System.out.println("Expected 'Trunk Name'' is not displaying as expected under Trunk Panel in 'view Service' page");
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
		
		Thread.sleep(5000);
		scrollToTop();
	//Action button	
		click_commonMethod(application, "Action", "ViewTrunkPage_ActionButton", xml);//viewPage_ActionButton
		
	//click on Edit link
		click_commonMethod(application, "Edit", "ViewTrunkPage_EditLink", xml);//editLink//ViewTrunkPage_EditLink
		
		Thread.sleep(4000);
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
	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		

	

			////////////////////	Add CPE DEVICE   //////////////////////////


	public void verifyAddCPEDeviceFunction(String application, String ServiceIdentification, String TrunkGroupSiteOrderNumber, String ExistingTrunkName,
			String AddNewCPEDevice, String AddExistingCPEDevice , String  CPE_RouterID,  String  CPE_DeviceName, String  CPE_VendorModel,  String   CPE_ManagementAddress,  String  CPE_Snmpro,  String  CPE_Snmprw ,String   CPE_SnmpV3ContextName,  String   CPE_SnmpV3ContextEngineID,  String  CPE_SnmpV3SecurityUsername ,  String  CPE_SnmpV3AuthProto , String   CPE_SnmpV3AuthPassword,  String   CPE_Country,  String   CPE_City,  String CPE_Site ,  String CPE_Premise) throws InterruptedException, DocumentException, IOException {
		
		boolean TrunkNameToAddCPEDeviceText=false;
		boolean trunkgrupOrderErrMsg= false;
		boolean TrunkGroupSiteOrderNumberText=false;
		Thread.sleep(2000);
		scrolltoend();
		Thread.sleep(5000);
		scrolltoend();
		TrunkNameToAddCPEDeviceText= getwebelement("//div[text()='"+ ExistingTrunkName +"']").isDisplayed();
		if(TrunkNameToAddCPEDeviceText) {
			DriverTestcase.logger.log(LogStatus.PASS, "Expected 'Trunk Name' is displaying as expected under Trunk Panel in 'view Service' page");
			System.out.println("Expected 'Trunk Name'' is displaying as expected under Trunk Panel in 'view Service' page");
			
			
		//click(application, "Trunk Name in Trunk panel", "//div[text()='"+ExistingTrunkName+"']");//Select Checkbox for expected trunk Not working
		WebElement CheckboxForExpectedTrunk = driver.findElement(By.xpath("//div[text()='"+ExistingTrunkName+"']"));
		String TrunkName = CheckboxForExpectedTrunk.getText().toString();
		CheckboxForExpectedTrunk.click();
		Thread.sleep(5000);
		DriverTestcase.logger.log(LogStatus.PASS,"Step : Checkbox Selection is done for Expected trunk, Selected Trunk is " + TrunkName);
		Log.info("Selected Trunk is  : " + TrunkName);
		
		
		click(application, "ACTION LINK in Trunk panel", "ViewService_Trunk_ActionLink2");
		click(application, "Add CPE Device Link", "ViewService_Trunk_AddCPEDeviceLink1");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEDevice_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Add CPE Device' navigated as expected");
			System.out.println("'Add CPE Device' navigated as expected");
			
		if(AddNewCPEDevice.equalsIgnoreCase("Yes")) {
			
			scrolltoend();
			click(application, "OK Button", "AddCPE_OKButton");
			
			try { 
			WarningMessage(application, "Country", "AddCPE_WarningMessage_Country");
			WarningMessage(application, "City", "AddCPE_WarningMessage_City");
			WarningMessage(application, "Site", "AddCPE_WarningMessage_Site");
			scrollToTop();
			WarningMessage(application, "Router ID", "AddCPE_WarningMessage_RouterID");	
			WarningMessage(application, "Device Name", "AddCPE_WarningMessage_DeviceName");
			WarningMessage(application, "Vendor Model", "AddCPE_WarningMessage_VendorModel");
			//*WarningMessage(application, "Management Address", "AddCPE_WarningMessage_ManagementAddress");//Issues reported
			WarningMessage(application, "Snmpro", "AddCPE_WarningMessage_Snmpro");
			
			
			EnterTextValue(application, CPE_RouterID, "Router ID", "CPE_RouterIdTextfield");
			//**EnterTextValue(application, CPE_DeviceName, "Device Name", "CPE_DeviceNameTextfield");//Bydefault
			
			
			//**SelectDropdownValueUnderDivTag(application, "Vendor/Model", CPE_VendorModel, "CPE_VendorModelDropdown", "commonDropdownValueTag"); //div[@class='sc-htpNat AUGYd']/div
			SelectDropdownValueUnderSpanTag(application, "Vendor/Model", CPE_VendorModel, "CPE_VendorModelDropdown", "commonDropdownValueTag", xml); 

			EnterTextValue(application, CPE_ManagementAddress, "Management Address", "CPE_ManagementAddressTextfield");
			EnterTextValue(application, CPE_Snmpro, "Snmpro", "CPE_SnmproTextfield");
			EnterTextValue(application, CPE_Snmprw, "Snmprw", "CPE_SnmprwTextfield");
			EnterTextValue(application, CPE_SnmpV3ContextName, "Snmp v3 Context Name", "CPE_Snmpv3ContextNameTextfield");
			EnterTextValue(application, CPE_SnmpV3ContextEngineID, "Snmp V3 Context Engine ID", "CPE_snmpv3ContextEngineIdTextfield");
			EnterTextValue(application, CPE_SnmpV3SecurityUsername, "Snmp V3 Security Username", "CPE_snmpv3SecurityUserNameTextfield");
			//EnterTextValue(application, CPE_SnmpV3AuthProto, labelname, "CPE_SnmpV3AuthProtoDropdown");
			EnterTextValue(application, CPE_SnmpV3AuthPassword, "Snmp V3 Auth Password", "CPE_snmpv3AuthPasswordTextfield");
			
			scrolltoend();
			//**SelectDropdownValueUnderDivTag(application, "Country", CPE_Country, "CPE_CountryDropdown", "commonDropdownValueTag");
			SelectDropdownValueUnderSelectTag(application, "Country", CPE_Country, "CPE_CountryDropdown", xml);
			//**SelectDropdownValueUnderDivTag(application, "City", CPE_City, "CPE_CityDropdown", "commonDropdownValueTag");
			SelectDropdownValueUnderSelectTag(application, "City", CPE_City, "CPE_CityDropdown", xml);
			//**SelectDropdownValueUnderDivTag(application, "Site", CPE_Site, "CPE_SiteDropdown", "commonDropdownValueTag");
			SelectDropdownValueUnderSelectTag(application, "Site", CPE_Site, "CPE_SiteDropdown", xml);

			//**SelectDropdownValueUnderDivTag(application, "Premise", CPE_Premise, "CPE_PremiseDropdown", "commonDropdownValueTag");
			//**SelectDropdownValueUnderSelectTag(application, "Premise", CPE_Premise, "CPE_PremiseDropdown", "commonDropdownValueTag");

			click(application, "OK Button", "trunk_okButton");
			
			//verifysuccessmessage(application, "CPE Device added successfully");
			compareText(application, "Add CPE Device Success Message", "CPE_AddCPEDeviceSuccessMessage", "CPE Device added successfully");
			
			}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in Add CPE Device page");
			System.out.println(  e+ " : Field is not displayed in Add CPE Device page");
			}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in Add CPE Device page ");
			System.out.println(  e+" : Field is not displayed in Add CPE Device page");
			}

			
				}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Add CPE Device' page not navigated");
				System.out.println("'Add CPE Device' page not navigated");
					  }
			
				}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "Expected 'Trunk Name' is not displaying as expected under Trunk Panel in 'view Service' page");
				System.out.println("Expected 'Trunk Name'' is not displaying as expected under Trunk Panel in 'view Service' page");
						}
				}
}
		



	
	
	
	
	
	
	
	
	
	
	
	
	public void verifyAddedCPEDeviceInformation_View(String application, String ServiceIdentification, String TrunkGroupSiteOrderNumber, String ExistingTrunkName,
			String AddNewCPEDevice, String AddExistingCPEDevice , String  CPE_RouterID,  String  CPE_DeviceName, String  CPE_VendorModel,  String   CPE_ManagementAddress, 
			String  CPE_Snmpro,  String  CPE_Snmprw ,String   CPE_SnmpV3ContextName,  String   CPE_SnmpV3ContextEngineID,  String  CPE_SnmpV3SecurityUsername ,  
			String  CPE_SnmpV3AuthProto , String   CPE_SnmpV3AuthPassword,  String   CPE_Country,  String   CPE_City,  String CPE_Site ,  String CPE_Premise
			) throws InterruptedException, DocumentException, IOException {

		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		
		click(application, "View Link For CPE Device under Trunk panel", "ViewService_Trunk_ViewCPEDeviceLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
			System.out.println("'View CPE Device' page navigated as expected");
		
		try {
		// Verify All Device Information under View CPE Device page//Not working
		
		compareText_InViewPage(application, "Router Id", CPE_RouterID, xml);
		
		String CompleteDeviceName=CPE_RouterID+"-"+ServiceIdentification+"..ipc.colt.net";
		compareText_InViewPage(application, "Device Name", CompleteDeviceName, xml);

		compareText_InViewPage(application, "Vendor/Model", CPE_VendorModel, xml);

		compareText_InViewPage(application, "Management Address", CPE_ManagementAddress, xml);

		compareText_InViewPage(application, "Snmpro", CPE_Snmpro, xml);

		compareText_InViewPage(application, "Snmprw", CPE_Snmprw, xml);

		compareText_InViewPage(application, "Snmp V3 Context Name", CPE_SnmpV3ContextName, xml);
		
		compareText_InViewPage(application, "Snmp V3 Context Engine ID", CPE_SnmpV3ContextEngineID, xml);
		
		compareText_InViewPage(application, "Snmp V3 Security Username", CPE_SnmpV3SecurityUsername, xml);
		
		//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/RouterTool_header")));
		//**compareText_InViewPage(application, "Snmp V3 Auth Proto", CPE_SnmpV3AuthProto, xml);//Novalues

		compareText_InViewPage(application, "Snmp V3 Auth Password", CPE_SnmpV3AuthPassword, xml);

		GetText(application, "Country", "CPE_View_CountryValue");//Compare Won't work here
		GetText(application, "City", "CPE_View_CityValue");
		GetText(application, "Site", "CPE_View_SiteValue");
		//**GetText(application, "Premise", "CPE_View_PremiseValue");

		GetText(application, "Test", "CPE_View_TestColumnName");
		GetText(application, "Status", "CPE_View_StatusColumnName");
		GetText(application, "Last Refresh", "CPE_View_LastRefresh");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in View CPE Device page");
			System.out.println(  e+ " : Field is not displayed in View CPE Device page");
			}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in View CPE Device page ");
			System.out.println(  e+" : Field is not displayed in View CPE Device page");
			}
		
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View CPE Device' page not navigated");
			System.out.println("'View CPE Device' page not navigated");
			 }
		
		DriverTestcase.logger.log(LogStatus.PASS, "All Fields values verified in View CPE Device Page");
		Log.info("------ Verified Added CPE Device Information successfully ------");
		
		

	}
	
	
	
	
	public void verifyEditCPEDeviceFunction(String application, String ServiceIdentification , String CPE_RouterIDEdit, String	CPE_DeviceNameEdit, String	CPE_VendorModelEdit, String	CPE_ManagementAddressEdit	
			, String CPE_SnmproEdit, String	CPE_SnmprwEdit, String	CPE_SnmpV3ContextNameEdit,	String CPE_SnmpV3ContextEngineIDEdit, String	CPE_SnmpV3SecurityUsernameEdit, 
			String	CPE_SnmpV3AuthProtoEdit, String	CPE_SnmpV3AuthPasswordEdit, String CPE_CountryEdit, String	CPE_CityEdit, String CPE_SiteEdit, String	CPE_PremiseEdit) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();

		//These 3 lines code needed only for direct edit cpe device
		//**WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		//**scrolltoview(TrunkGroupSiteOrders_header);
		//**click(application, "Edit Link For CPE Device under Trunk panel", "ViewService_Trunk_EditCPEDeviceLink");
		
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
			System.out.println("'View CPE Device' page navigated as expected");
			
			click(application, "ACTION Link", "CPE_View_ActionLink");
			Thread.sleep(2000);
			click(application, "Edit Link", "CPE_View_Action_EditLink");
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_EditDevice_Header")).isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'Edit CPE Device' page navigated as expected");
				System.out.println("'Edit CPE Device' page navigated as expected");
		
		
		try {
			
			ClearAndEnterTextValue(application, "Router ID", "CPE_RouterIdTextfield", CPE_RouterIDEdit);
			//***ClearAndEnterTextValue(application, "Device Name", "CPE_DeviceNameTextfield", CPE_DeviceNameEdit);
			
			//NW**SelectDropdownValueUnderDivTag(application, "Vendor/Model", CPE_VendorModelEdit, "CPE_VendorModelDropdown", "commonDropdownValueTag"); //div[@class='sc-htpNat AUGYd']/div
			SelectDropdownValueUnderSpanTag(application, "Vendor/Model", CPE_VendorModelEdit, "CPE_VendorModelDropdown", "commonDropdownValueTag", xml); 
			
			//***ClearAndEnterTextValue(application, "Management Address", "CPE_ManagementAddressTextfield", CPE_ManagementAddressEdit);
			ClearAndEnterTextValue(application, "Snmpro", "CPE_SnmproTextfield", CPE_SnmproEdit);
			ClearAndEnterTextValue(application, "Snmprw", "CPE_SnmprwTextfield", CPE_SnmprwEdit);
			ClearAndEnterTextValue(application, "Snmp v3 Context Name", "CPE_Snmpv3ContextNameTextfield", CPE_SnmpV3ContextNameEdit);
			ClearAndEnterTextValue(application, "Snmp V3 Context Engine ID", "CPE_snmpv3ContextEngineIdTextfield", CPE_SnmpV3ContextEngineIDEdit);
			ClearAndEnterTextValue(application, "Snmp V3 Security Username", "CPE_snmpv3SecurityUserNameTextfield", CPE_SnmpV3SecurityUsernameEdit);
			//***SelectDropdownValueUnderDivTag(application, "Snmp V3 Auth Proto", CPE_SnmpV3AuthProtoEdit, "CPE_SnmpV3AuthProtoDropdown", "commonDropdownValueTag");
			//***SelectDropdownValueUnderSelectTag(application, "Snmp V3 Auth Proto", CPE_SnmpV3AuthProtoEdit, "CPE_SnmpV3AuthProtoDropdown", xml);

			ClearAndEnterTextValue(application, "Snmp V3 Auth Password", "CPE_snmpv3AuthPasswordTextfield", CPE_SnmpV3AuthPasswordEdit);
			
			scrolltoend();
			//**SelectDropdownValueUnderDivTag(application, "Country", CPE_CountryEdit, "CPE_CountryDropdown", "commonDropdownValueTag");
			//**SelectDropdownValueUnderDivTag(application, "City", CPE_CityEdit, "CPE_CityDropdown", "commonDropdownValueTag");
			//**SelectDropdownValueUnderDivTag(application, "Site", CPE_SiteEdit, "CPE_SiteDropdown", "commonDropdownValueTag");
			//****SelectDropdownValueUnderDivTag(application, "Premise", CPE_PremiseEdit, "CPE_PremiseDropdown", "commonDropdownValueTag")
			
			SelectDropdownValueUnderSelectTag(application, "Country", CPE_CountryEdit, "CPE_CountryDropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "City", CPE_CityEdit, "CPE_CityDropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Site", CPE_SiteEdit, "CPE_SiteDropdown", xml);
			
			
			scrolltoend();
			click(application, "OK in Edit CPE Device", "AddCPE_OKButton"); //Not Working Issues raised
			//**click(application, "Cancel in Edit CPE Device", "AddCPE_CancelButton");
			
			Thread.sleep(5000);
			compareText(application, "CPE Device Update message", "CPE_UpdateCPEDeviceSuccessMessage", "CPE Device updated successfully");

			Log.info("------  CPE Device updated successfully   ------");
			
			
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Field is not displayed in Edit CPE Device page");
			System.out.println( "Field is not displayed in Edit CPE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Field is not displayed in Edit CPE Device page ");
			System.out.println( "Field is not displayed in Edit CPE Device page");
		}
			
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Edit CPE Device' page not  navigated");
				System.out.println("'Edit CPE Device' page not navigated");
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'View CPE Device' page not navigated");
			System.out.println("'View CPE Device' page not navigated");
		}
		
			
	}

	
	
	
	
	
public void veriyFetchDeviceInterfacesFunction_CPE(String application,String ServiceIdentification, String CPE_ServiceStatusChangeRequired) throws InterruptedException, DocumentException, IOException {
	//	scrolltoend();
	//	WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
	//	scrolltoview(TrunkGroupSiteOrders_header);
	//	click(application, "View Link For CPE Device under Trunk Device panel", "ViewService_Trunk_ViewCPEDeviceLink");
	//	GetText(application, "Device", "CPE_ViewDevice_Header");//Device//Last Modified on :2020-05-14 07:18 GMT, Modified By :colttest@colt.net, Sync Status: sync in progress
	
	scrollToTop();
	if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
		DriverTestcase.logger.log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
		System.out.println("'View CPE Device' page navigated as expected");
		
		click(application, "ACTION link", "CPE_View_ActionLink");
		click(application, "Fetch Device Interfaces Link", "CPE_View_Action_FetchDeviceInterfacesLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_FetchDeviceInterfacesSuccessMessage")).isDisplayed()){
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
			System.out.println("Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			System.out.println("Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
		}
		
		click(application, "Click here Link for CPE", "CPE_hereLink_UnderFetchDeviceInterfacesSuccessMessage");
		Thread.sleep(2000);
		
		//Manage COLT's Network - Manage Network
		if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ManageCOLTsNetworkManageNetwork_header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Manage COLT's Network - Manage Network' page navigated as expected");
			System.out.println("'Manage COLT's Network - Manage Network' page navigated as expected");
			
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CPE_Manage_Synchronization_SynchronizeLink")));
		
		try {
		compareText(application, "Manage COLT's Network - Manage Network header", "CPE_ManageCOLTsNetworkManageNetwork_header", "Manage COLT's Network - Manage Network");
		GetText(application, "Device Name in Manage Colt page under Status Panel", "CPE_Manage_Status_DeviceValue");
		GetText(application, "Status in Manage Colt page", "CPE_Manage_Status_StatusValue");
		GetText(application, "Last Modification in Manage Colt page", "CPE_Manage_Status_LastModificationValue");
		GetText(application, "Status Link in Manage Colt page", "CPE_Manage_Status_StatusLink");
		GetText(application, "View Interface Link in Manage Colt page", "CPE_Manage_Status_ViewInterfacesLink");
		
		GetText(application, "Device Name in Manage Colt page under Synchronization Panel", "CPE_Manage_Synchronization_DeviceValue");
		GetText(application, "Sync Status in Manage Colt page", "CPE_Manage_Synchronization_SyncStatusValue");
		GetText(application, "Fetch Interfaces in Manage Colt page", "CPE_Manage_Synchronization_FetchInterfacesValue");
		GetText(application, "Synchronize Link in Manage Colt page", "CPE_Manage_Synchronization_SynchronizeLink");

		
		scrollToTop();
		String CPE_Manage_Status_LastModificationValue= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/CPE_Manage_Status_LastModificationValue")));
		if(CPE_Manage_Status_LastModificationValue.contains("GMT"))
		{
			Log.info("Service status is displayed as : " + CPE_Manage_Status_LastModificationValue);
			System.out.println("Last Modification is :"+ CPE_Manage_Status_LastModificationValue);
		}
		else
		{
			Log.info("Incorrect modification time format");
			System.out.println("Incorrect modification time format");
		}
		
		
		////
		click(application, "Status", "CPE_Manage_Status_StatusLink");

		if(CPE_ServiceStatusChangeRequired=="Yes")
		{
			WebElement ServiceStatusPage= getwebelement(xml.getlocator("//locators/" + application + "/CPE_Manage_Status_Servicestatus_popup"));
			if(ServiceStatusPage.isDisplayed())
			{
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CPE_Device_Status_OK")));
				click(application, "Click on OK to change Status", "CPE_Device_Status_OK");

				WebElement PE_servicestatushistoryValue= getwebelement(xml.getlocator("//locators/" + application + "/CPE_servicestatushistoryValue"));
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
			//**click(application, "Close", "CPE_servicestatus_popupclose");
		}

		
		
		////synchronize panel in manage colt page
			scrolltoend();
			click(application, "Synchronize", "CPE_Manage_Synchronization_SynchronizeLink");
			compareText(application, "Synchronize Warning Message", "CPE_Manage_SynchronizeWarningMessage", "Error while synchronizing device, Reason is :: null.");
			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
				System.out.println( "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
				System.out.println( "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
			}
			
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'Manage COLT's Network - Manage Network' page not navigated");
		System.out.println("'Manage COLT's Network - Manage Network' page not navigated");
	}
	
}else {
	DriverTestcase.logger.log(LogStatus.FAIL, "'View CPE Device' page not navigated");
	System.out.println("'View CPE Device' page not navigated");
}

		
	}
	
	




	
	
	
	
	
	
	
public void verifyRouterToolFunction_CPE(String application,String ServiceIdentification, String CPE_CommandIPV4, String CPE_CommandIPV6, String CPE_ManagementAddress) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		Thread.sleep(2000);
		
		WebElement serviceIdLink=getwebelement("//a[text()='"+ ServiceIdentification +"']");
		Clickon(serviceIdLink);
		Thread.sleep(5000);
		
		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		
		click(application, "View Link For CPE Device under Trunk Device panel", "ViewService_Trunk_ViewCPEDeviceLink");
		Thread.sleep(10000);
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/RouterTool_header")));
		compareText(application, "Router Tools header", "RouterTool_header", "Router Tools");
		
		SelectDropdownValueUnderDivTag(application, "Commands IPV4", CPE_CommandIPV4, "MAS_PE_Router_IPV4CommandsDropdown", "commonDropdownValueTag");
		EnterTextValue(application, CPE_ManagementAddress, "Commands IPV4", "CPE_Router_IPV4CommandTextfield");
		click(application, "Execute button IPV4", "CPE_Router_IPV4Command_Executebutton");
				
		SelectDropdownValueUnderDivTag(application, "Commands IPV6", CPE_CommandIPV6, "CPE_Router_IPV4CommandsDropdown", "commonDropdownValueTag");
		EnterTextValue(application, CPE_ManagementAddress, "Commands IPV6", "CPE_Router_IPV6CommandTextfield");
		click(application, "Execute button IPV6", "CPE_Router_IPV6Command_Executebutton");
}
	







	
	////
		public void verifyDeleteDeviceFunction_CPE(String application,String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
			
			scrollToTop();
			if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
				DriverTestcase.logger.log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
				System.out.println("'View CPE Device' page navigated as expected");
				
			//Delete MAS Device from View Device Page
			click(application, "ACTION link", "CPE_View_ActionLink");
			click(application, "Delete Device from View Device page", "CPE_View_Action_DeleteLink");
			compareText(application, "Delete PE Device Warning Message from View Device page", "CPE_ViewDevice_Action_DeletePEDeviceWarningMessage", "Are you sure that you want to delete this item?");
			click(application, "Delete Button", "CPE_ViewDevice_Action_DeleteButton");
			Thread.sleep(5000);
			scrollToTop();
			compareText(application, "Delete CPE Device Successful Message", "CPE_DeleteCPEDeviceSuccessMessage", "CPE Device deleted successfully");

			
				//Delete from Service "From View Service page"
			//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
			//click(application, "Delete CPE Device From Service Link", "ViewService_Trunk_DeleteCPEDeviceLink");
			//compareText(application, "Delete CPE Device Warning Message from View Service page", "PE_ViewService_DeletePEDeviceWarningMessage", "Are you sure that you want to delete?");
			//click(application, "Delete Button", "CPE_ViewService_DeleteButton");
			//Thread.sleep(3000);
			//compareText(application, "Delete CPE Device Successfull Message", "CPE_DeleteCPEDeviceSuccessMessage", "CPE Device deleted successfully");
			
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'View CPE Device' page not navigated");
				System.out.println("'View CPE Device' page not navigated");
			}
			}
	
		
		
		public void navigateToViewServicePageByServiceBreadcumLink_MAS(String application,String ServiceIdentification) throws InterruptedException, DocumentException {
		scrollToTop();
			try {
				Thread.sleep(4000);
			WebElement serviceIdLink=getwebelement("//a[text()='"+ServiceIdentification+"']");
			Clickon(serviceIdLink);

			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : VOIP ACCESS Service ID Link is not displayed");
				System.out.println(  e+ " : VOIP ACCESS Service ID Link is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : VOIP ACCESS Service ID Link is not displayed");
				System.out.println(  e+" : VOIP ACCESS Service ID Link is not displayed");
			}

			
		}
		
		
	   
	   public void navigateToDevicePageFromManageColtNetwork_MAS(String application,String MAS_DeviceName) throws InterruptedException, DocumentException {
				
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Manage_Status_DeviceValue")).isDisplayed()){

				   GetText(application, "Device Name in Manage Colt page under Status Panel", "MAS_Manage_Status_DeviceValue");
				   click_commonMethod(application, "Device Link ", "MAS_Manage_Status_DeviceValue", xml);
				   Thread.sleep(3000);
				   
		}else{
			DriverTestcase.logger.log(LogStatus.FAIL,  "VOIP ACCESS Device Link is not displayed under Manage Colt Network page");
			System.out.println("VOIP ACCESS Device Link is not displayed under Manage Colt Network page");
		}
				


				
			}
	   
	   
		
		public void navigateToViewDevicePage_MAS(String application, String MAS_DeviceName) throws InterruptedException, DocumentException {
			try {
			ScrolltoElement(application, "portalaccess_header", xml);//managementOptionsPanelheader//portalaccess_header//ProviderEquipment_header
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingMASdevicegrid")).isDisplayed())
			{
				//**WebElement AddedDevice_ViewLink=getwebelement("//div[div[b[text()='"+MAS_DeviceName+"']]]//span[text()='View']");
				//**Clickon(AddedDevice_ViewLink);
				click_commonMethod(application, "View Link", "MAS_viewdevice1", xml);
				compareText(application, "View device header", "MAS_ViewDevice_Header", "Device", xml);
					
			}else{
				DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
			}
			
			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed");
				System.out.println(  e+ " : Field is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed");
				System.out.println(  e+" : Field is not displayed");
			}
		}
	
	
		
		public void navigateToViewDevicePage_MAS_UI(String application, String MAS_DeviceName) throws InterruptedException, DocumentException {
			Thread.sleep(4000);
			try {
			ScrolltoElement(application, "portalaccess_header", xml);//managementOptionsPanelheader//portalaccess_header//ProviderEquipment_header
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingMASdevicegrid")).isDisplayed())
			{
				//**WebElement AddedDevice_ViewLink=getwebelement("//div[div[b[text()='"+MAS_DeviceName+"']]]//span[text()='View']");
				//**Clickon(AddedDevice_ViewLink);
				click_commonMethod(application, "View Link", "MAS_viewdevice1", xml);
				compareText(application, "View device header", "MAS_ViewDevice_Header", "Device", xml);
					
			}else{
				DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
			}
			
			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed");
				System.out.println(  e+ " : Field is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed");
				System.out.println(  e+" : Field is not displayed");
			}

			
		}
		
		public void navigateToViewDevicePage_MAS3(String application, String existingdevicename) throws InterruptedException, DocumentException {
			//**click_commonMethod(application, "Back Button", "BackButtonxpath", xml);
			ScrolltoElement(application, "portalaccess_header", xml);//managementOptionsPanelheader
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingMASdevicegrid")).isDisplayed())
			{
				List<WebElement> addeddevicesList= driver.findElements(By.xpath("MAS_fetchAlldevice_InviewPage"));
				int AddedDevicesCount= addeddevicesList.size();
				for(int i=0;i<AddedDevicesCount;i++) {
					String AddedDeviceNameText= addeddevicesList.get(i).getText();
					String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
					if(AddedDeviceNameText.contains(existingdevicename))
					{
						WebElement AddedDevice_ViewLink= driver.findElement(By.xpath("//div[div[b[text()='"+existingdevicename+"']]]//span[text()='View']"));
                        Clickon(AddedDevice_ViewLink);
						Thread.sleep(5000);
						compareText(application, "View device header", "MAS_ViewDevice_Header", "Device", xml);
					}else{
                          DriverTestcase.logger.log(LogStatus.FAIL, "Invalid device name");
                    }
                    break;
				}
				
			}else{
				DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
			}
			
		}
	
		
		public void navigateToViewDevicePage_MAS_OLD(String application, String devicename) throws InterruptedException, DocumentException {
			//**click_commonMethod(application, "Back Button", "BackButtonxpath", xml);
			ScrolltoElement(application, "portalaccess_header", xml);//managementOptionsPanelheader
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingMASdevicegrid")).isDisplayed())
			{
				List<WebElement> addeddevicesList= driver.findElements(By.xpath("MAS_fetchAlldevice_InviewPage"));
				int AddedDevicesCount= addeddevicesList.size();
				for(int i=0;i<AddedDevicesCount;i++) {
					String AddedDeviceNameText= addeddevicesList.get(i).getText();
					String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
					if(AddedDeviceNameText.contains(devicename))
					{
						WebElement AddedDevice_ViewLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='View']"));
						//Clickon(AddedDevice_ViewLink);
						click_commonMethod(application, "View Link", "viewservicepage_viewdevicelink", xml);
						Thread.sleep(5000);
						compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);
					}
				}
				
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
			}
			
		}
	
		
		public void Clickon_addToggleButton(String application, String xpath) throws InterruptedException, DocumentException {

			//Add Toggle button
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
			Thread.sleep(5000);
		}

		
		
		/**
		 * Router Tool Panel
		 * @throws InterruptedException 
		 * @throws DocumentException 
		 * @throws IOException 
		 */
		public void routerPanel_MAS(String application, String commandIPv4, String commandIPv6, 
				String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException, IOException {
			
			scrollToTop();
			//Thread.sleep(1000);

			WebElement vendorModel=getwebelement(xml.getlocator("//locators/" + application + "/MAS_View_VendorModelValue"));
			String vendor=Gettext(vendorModel);
			WebElement manageAddress=getwebelement(xml.getlocator("//locators/" + application + "/MAS_View_ManagementAddressValue"));
			String ipAddress=Gettext(manageAddress);
			
			ScrolltoElement(application, "MAS_View_VendorModelValue" , xml);
			//Thread.sleep(1000);
			
			if(vendor.startsWith("Cisco")) {
				
			//Command IPV4	
				addDropdownValues_commonMethod(application, "Command IPV4", "MAS_PE_Router_IPV4CommandsDropdown" , commandIPv4, xml);
				
				hostnametextField_IPV4_MAS(application, commandIPv4, ipAddress);
				
				vrfNametextField_IPV4_MAS(application, commandIPv4, vrfname_ipv4);
				
				executeCommandAndFetchTheValue_MAS(application, "MAS_PE_Router_IPV4Command_Executebutton");
				
				
			//Commmand IPV6
				addDropdownValues_commonMethod(application, "Command IPV6", "MAS_PE_Router_IPV6CommandsDropdown" , commandIPv6, xml);
				
				hostnametextField_IPV6_MAS(application, commandIPv6, ipAddress);
				
				vrfNametextField_IPV6_MAS(application, commandIPv6, vrfname_ipv6);
				
				executeCommandAndFetchTheValue_MAS(application, "MAS_PE_Router_IPV6Command_Executebutton");
				
			}
			else if(vendor.startsWith("Juniper ")){
				
				//Command IPV4	
						addDropdownValues_commonMethod(application, "Command IPV4", "MAS_PE_Router_IPV4CommandsDropdown" , commandIPv4, xml);
						
						hostnametextField_IPV4_MAS(application, commandIPv4, ipAddress);
						
						vrfNametextField_IPV4_MAS(application, commandIPv4, vrfname_ipv4);
						
						executeCommandAndFetchTheValue_MAS(application, "MAS_PE_Router_IPV4Command_Executebutton");
						
				
			}
			else {
				DriverTestcase.logger.log(LogStatus.INFO, "Router Panel will not display for the selected vendorModel: "+vendorModel);
				System.out.println("Router Panel will not display for the selected vendorModel: "+vendorModel);
			}
			

			
		}

		public void executeCommandAndFetchTheValue_MAS(String application, String executeButton) throws InterruptedException, DocumentException {
			
			click_commonMethod(application, "Execute", executeButton, xml);
			Thread.sleep(180000);
		boolean resultField=false;	
		try {	
		resultField=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_result_textArea")).isDisplayed();
		if(resultField) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Result' text field is displaying");
			System.out.println( "'Result' text field is displaying");
			
			String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_result_textArea")).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "value under 'Result' field displaying as "+ remarkvalue);
			System.out.println("value under 'Result' field displaying as "+ remarkvalue);

		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Result' text field is not displaying");
			System.out.println( "'Result' text field is not displaying");
		}
		}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "'Result' text field is not displaying");
		System.out.println("'Result' text field is not displaying");
		}
			
		}


		public void hostnametextField_IPV6_MAS(String application, String commandIPv6, String ipv6Address) {
			boolean IPV4availability=false;
			try {  
				IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_commandIPv6_hostnameTextfield")).isDisplayed();
			  
			  if(IPV4availability) {
				  
				  addtextFields_commonMethod(application, "IP Address or Hostname", "MAS_PE_commandIPv6_hostnameTextfield", ipv6Address, xml);
				  
			  }else {
				  DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
					System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
			  }
			}catch(Exception e) {
				e.printStackTrace();
				
				DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
				System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
			}
		}


		public void vrfNametextField_IPV6_MAS(String application, String commandIPV6, String vrfname_IPV6) {
			boolean IPV6availability=false;
			
			if(commandIPV6.equalsIgnoreCase("vrf")) {
				try {  
					IPV6availability=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_commandIPv6_vrfnameTextField")).isDisplayed();
					
					if(IPV6availability) {
						addtextFields_commonMethod(application, "Router Vrf Name", "MAS_PE_commandIPv6_vrfnameTextField", vrfname_IPV6, xml);
					}else {
						DriverTestcase.logger.log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
						System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
					}
				}catch(Exception e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
					System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				}
			}
			else {
				DriverTestcase.logger.log(LogStatus.PASS, "'VRF Name IPv6' text field is not displaying for "+ commandIPV6);
				System.out.println("'VRF Name IPv6' text field is not displaying for "+ commandIPV6 +" command");
			}
			
		}	


		public void hostnametextField_IPV4_MAS(String application, String command_ipv4, String ipAddress) {
			boolean IPV4availability=false;
			try {  
				IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_commandIPv4_hostnameTextfield")).isDisplayed();
			  
			  if(IPV4availability) {
				  
				  addtextFields_commonMethod(application, "IP Address or Hostname", "MAS_PE_commandIPv4_hostnameTextfield", ipAddress, xml);
				  
			  }else {
				  DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
					System.out.println("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
			  }
			}catch(Exception e) {
				e.printStackTrace();
				
				DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
				System.out.println("'Hostname or IpAddress' for 'Ipv4' text field is not displaying for "+ command_ipv4);
			}
		}


		public void vrfNametextField_IPV4_MAS(String application, String command_ipv4, String vrfname_ipv4) {
			boolean IPV4availability=false;
			  
				
			if(command_ipv4.contains("vrf")) {
				try {
					IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_commandIPv4_vrfnameTextField")).isDisplayed();
					
					if(IPV4availability) {
						addtextFields_commonMethod(application, "Router Vrf Name", "MAS_PE_commandIPv4_vrfnameTextField", vrfname_ipv4, xml);
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
						System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
					}
				}catch(Exception e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
					System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				}
				
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "'VRF Name IPv4' text field is not displaying for "+ command_ipv4);
				System.out.println("'VRF Name IPv4' text field is not displaying for "+ command_ipv4 +" command");
			}
		}	


	
		public void navigateToEditPage(String application) throws InterruptedException, DocumentException {
			
			WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/OrderPanel"));
			scrolltoview(orderPanel);
			Thread.sleep(2000);
			
		//click on Action dropdown	
			click_commonMethod(application, "Action", "ActionDropdown_InViewPage", xml);
			Thread.sleep(1000);
			
		//click on edit link
			click_commonMethod(application, "edit", "editLink_InViewPage", xml);
			Thread.sleep(1000);
			
		}
	
	
	
	
	
	
		public void deleteTrunk(String application,String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
			click(application, "Trunk ACTION link", "ViewTrunkPage_ActionButton");
			Thread.sleep(2000);
			click(application, "Delete DLink", "ViewTrunkPage_DeleteLink");
			compareText(application, "Delete Trunk Warning Message from View Trunk page", "ViewTrunk_DeleteWarningMessage", "Are you sure that you want to delete?");
			click(application, "Delete Button", "ViewTrunkPage_DeleteButton");
			Thread.sleep(3000);
			compareText(application, "Delete Trunk Successful Message", "DeleteTrunkSuccessMessage", "Trunk deleted successfully");
		}
	
	
		
public void clickOnBreadCrump(String application, String breadCrumpLink) throws InterruptedException, DocumentException {
			scrollToTop();
			Thread.sleep(2000);
			WebElement breadcrumb=null;
			try {
			breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
			if(breadcrumb.isDisplayed()) {
				click_commonMethod_PassingWebelementDirectly(application, "Breadcrump", "breadcrump", xml);
			}else {
				System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
			}
		}
		
	

public void clickOnViewLink(String application, String trunkGroupName, String siteOrderName) throws Exception {
	
	scrolltoend();
	
	WebElement getTrunkRow=getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedTrunk_InViewServicePage").replace("value", trunkGroupName));
	safeJavaScriptClick(getTrunkRow);
	
	
	//click on Action dropdown under Trunk Panel	
		WebElement trukPanel_actionDropdown=getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel_ActionDropdown").replace("value", siteOrderName));
		safeJavaScriptClick(trukPanel_actionDropdown);
		
	
	//Add CPE link
		Clickon(getwebelement("//a[contains(text(),'View')]"));
		DriverTestcase.logger.log(LogStatus.PASS, "clicked on view link");
	
}

	


				//////GSX , PSX , SBC

public void viewTrunk_GSX_generateConfiguration(String application) throws InterruptedException, DocumentException {
	
	WebElement lowerCaseRouteelement=getwebelement(xml.getlocator("//locators/" + application + "/createLowerCaseRoute_viewTrunkPage"));
	scrolltoview(lowerCaseRouteelement);
	Thread.sleep(2000);
	
//	addDropdownValues(application, fieldname, xpath, expectedValueToAdd);
	
}


public void viewTrunk_PSX_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException {
	
	waitForpageload();
	Thread.sleep(7000);
	
	scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/DDIRange_Header")));
	Thread.sleep(2000);
	
	addDropdownValues_commonMethod(application, "PSX Configuration", "PSXconfigurationDropdown_viewtrunk", expectedConfiguration , xml);
	click_commonMethod(application, "Execute", "viewTrunk_PSX_executeButton" , xml);
	
	
	 Alert alert = driver.switchTo().alert();		
 		
     // Capturing alert message.    
       String alertMessage= driver.switchTo().alert().getText();
       if(alertMessage.isEmpty()) {
    	   DriverTestcase.logger.log(LogStatus.FAIL, "No mEssage displays");
	       System.out.println("No Message displays"); 
       }else {
    	   DriverTestcase.logger.log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
	       System.out.println("text message for alert displays as: "+alertMessage);
       }
     
     try {  
       alert.accept();
       Thread.sleep(2000);
       
       System.out.println("aceept 2");
       alert.accept();
     }catch(Exception e) {
    	 e.printStackTrace();
     }
     
     
     if(expectedConfiguration.equalsIgnoreCase("Delete Trunk Group")) {
    	 //**verifysuccessmessage(application, "PSX sync started successfully.");
    	 compareText(application, "PSX config sucess Message", "PSXconfig_sucessMessage", "PSX sync started successfully.", xml);
     }else if(expectedConfiguration.equalsIgnoreCase("Add Destination IP Address")) {
    	 String alertMessage1=alert.getText();
    	 if(alertMessage1.isEmpty()) {
	    	   DriverTestcase.logger.log(LogStatus.FAIL, "After clicking on OK, No Message displays");
		       System.out.println("No Message displays"); 
	       }else {
	    	   DriverTestcase.logger.log(LogStatus.PASS, "After clicking on OK button, Alert message displays as: "+alertMessage);
		       System.out.println("text message for alert displays as: "+alertMessage);
	       }
    	 
     }
}


public void viewTrunk_SBC_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException {
	
	
	System.out.println("expected value "+ expectedConfiguration);
	scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/DDIRange_Header")));
	Thread.sleep(2000);
	
	addDropdownValues_commonMethod(application, "SBC Configuration", "SBCconfigurationDropdown_viewtrunk", expectedConfiguration, xml);
	
	click_commonMethod(application, "Execute", "viewTrunk_SBC_executeButton" , xml);
	
	 Alert alert = driver.switchTo().alert();		
		
     // Capturing alert message.    
       String alertMessage= driver.switchTo().alert().getText();
       if(alertMessage.isEmpty()) {
    	   DriverTestcase.logger.log(LogStatus.FAIL, "No mEssage displays");
	       System.out.println("No Message displays"); 
       }else {
    	   DriverTestcase.logger.log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
	       System.out.println("text message for alert displays as: "+alertMessage);
       }
       
       Thread.sleep(3000);
     
       //TODO 
     try {  
       alert.accept();
       Thread.sleep(2000);
       
       System.out.println("accept 2");
       alert.accept();
     }catch(Exception e) {
    	 e.printStackTrace();
     }

     
     //fetch success Message
     if(expectedConfiguration.equalsIgnoreCase("Cease Trunk Group")) {
    	 verifysuccessmessage(application, "SBC sync started successfully. Please check the sync status of this Trunk Group.");
     }else if(expectedConfiguration.equalsIgnoreCase("Synchronize All")) {
    	 verifysuccessmessage("wholesaleService", "SBC sync has not started because of the manual change in the SBC. Uncheck the 'Manual Configuration' flag "
					+ "to overwrite the changes.");
     }
	
}


public void viewTrunk_GSX_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException, IOException {
	
	
	System.out.println("expected value "+ expectedConfiguration);
	scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/DDIRange_Header")));
	Thread.sleep(2000);
	
	addDropdownValues_commonMethod(application, "GSX Configuration", "GSXconfigurationDropdown_viewtrunk", expectedConfiguration, xml);
	
	click_commonMethod(application, "Generate Configuration", "viewTrunk_GSX_generateConfigurationButton" , xml);
	
	String mainWindow=driver.getWindowHandle();
	Set<String> allwindows = driver.getWindowHandles();
    Iterator<String> itr = allwindows.iterator();
    while(itr.hasNext())
    {
          String childWindow = itr.next();
          if(!mainWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
                System.out.println(driver.switchTo().window(childWindow).getTitle());
                  
                Thread.sleep(1000);
                
              String gsxConfiguredValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/GSXcongig_textArea")));
              if(gsxConfiguredValue.isEmpty()) {
            	  DriverTestcase.logger.log(LogStatus.PASS, "No values displaying under GSX Configuration");
              }else {
            	  DriverTestcase.logger.log(LogStatus.PASS, "'Under GSX Configuration' value is displaying as: "+ gsxConfiguredValue);
              }
//               Write here  whatever you want to do and perform
                System.out.println("came inside child window");
                
               //
                scrolltoend();
                Thread.sleep(2000);
                click_commonMethod(application, "Execute", "GSX_config_executeButton", xml);
                
          }
    }
    
   Thread.sleep(3000);
   driver.close();
    driver.switchTo().window(mainWindow);
    Thread.sleep(1000);

    scrollToTop();
   String gsxSuccessMesage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/GSXconfig_sucessMessage")));
   if(gsxSuccessMesage.isEmpty()) {
	   DriverTestcase.logger.log(LogStatus.FAIL, "NO message displays after clicking on 'Execute' button");
   }
   else {
	   DriverTestcase.logger.log(LogStatus.PASS, "After clicking on 'Execute' button, success Message displays as: "+gsxSuccessMesage);
   }
 
    
	
}



/**
 *  perform Manual SBC execution
 * @param application
 * @throws InterruptedException
 * @throws DocumentException
 * @throws IOException 
 */
public void addSBC_manualExecutionConfig(String application, String manualConfigurationValue) throws InterruptedException, DocumentException, IOException {
	
	scrolltoend();
	Thread.sleep(2000);
	
	boolean SBCHeder=getwebelement(xml.getlocator("//locators/" + application + "/SBCmanualConfig_PanelHeader")).isDisplayed();
	if(SBCHeder) {
		DriverTestcase.logger.log(LogStatus.PASS, "'SBC Manully Executed Configuration' panel is displaying");
		System.out.println("'SBC Manully Executed Configuration' panel is displaying");
		
		
		click_commonMethod(application, "Action", "SBCManualConfig_actionDropdown", xml);
		click_commonMethod(application, "Add link", "SBC_addLink", xml);
		
		//TODO compare Header name
		
		String mainWindow=driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
	    Iterator<String> itr = allwindows.iterator();
	    while(itr.hasNext())
	    {
	          String childWindow = itr.next();
	          if(!mainWindow.equals(childWindow)){
	                driver.switchTo().window(childWindow);
	                System.out.println(driver.switchTo().window(childWindow).getTitle());
	                  
	                Thread.sleep(1000);
	                
	              String psxConfiguredValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/manualConfiguration_textArea")));
	              if(psxConfiguredValue.isEmpty()) {
	            	  DriverTestcase.logger.log(LogStatus.PASS, "No values displaying under SBC Manually Executed Configurations");
	              }else {
	            	  DriverTestcase.logger.log(LogStatus.PASS, "'Under SBC Manually Executed Configurations' value is displaying as: "+ psxConfiguredValue);
	              }
//	               Write here  whatever you want to do and perform
	                System.out.println("came inside child window");
	                
	            	//Text Area	
	                addtextFields_commonMethod(application, "SBC Manual Configuration", "manualConfiguration_textArea", manualConfigurationValue, xml);	        		scrolltoend();
	        		click_commonMethod(application, "Save", "saveButton_manualConfiguration", xml);   //click on Save buttton
	        		Thread.sleep(1000);
	        			                
	          }
	    }
	    
	    Thread.sleep(5000);
	    //**driver.close();//Automatically getting closed
	     driver.switchTo().window(mainWindow);
	     Thread.sleep(1000);

	     scrollToTop();
	    compareText(application, "SBC Manual Configuration added Successfully", "AddManualConfiguration_SuccessMessage", "Manual Configuration added Successfully", xml);
	    
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'SBC Manully Executed Configuration' panel is not displaying");
		System.out.println("'SBC Manully Executed Configuration' panel is not displaying");
	}
}




public void verifySBCfileAdded(String application) throws InterruptedException, DocumentException, IOException {
	
	
	scrolltoend();
	Thread.sleep(1000);
	
	WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/SBC_selectCreatedValue"));
	String SBCfilenameCreated=Gettext(filename);
	
	if(SBCfilenameCreated.isEmpty()) {
		DriverTestcase.logger.log(LogStatus.FAIL, "SBC Manually Executed configuration file name is not displaying");
		System.out.println("SBC Manually Executed configuration file name is not displaying");
	}else {
		
		int i=0; int k=0; int j=0;
		
	//Fetching Column Names	
		List<WebElement> columns=getwebelements(xml.getlocator("//locators/" + application + "/SBC_columnNames"));
		int listOfColumns=columns.size();
		
		String columnName[]=new String[listOfColumns];
		for (WebElement column : columns) {
			
			columnName[k]=column.getText();
			k++;
		}
		
		DriverTestcase.logger.log(LogStatus.INFO, "column names display as: "+ columnName[1] + "  " + columnName[2]);  //printing column Names
		
		
	//Fetching value under File Column	
		List<WebElement> files=getwebelements(xml.getlocator("//locators/" + application + "/SBC_filenames"));
		int listOfFiles=files.size();
		
		String[] fileNameValues=new String[listOfFiles];
		
		for(WebElement filenametransfer : files) {
			
			fileNameValues[i] = filenametransfer.getText();
			i++;
		}
		
	
	//Fetching value under Date Column
		List<WebElement> dates=getwebelements(xml.getlocator("//locators/" + application + "/SBC_dates"));
		int listOfDates=dates.size();
		
		String[] dateValues=new String[listOfDates];
		
		for(WebElement dateList : dates) {
			
			dateValues[j] = dateList.getText();
			j++;
		}
	
	
		DriverTestcase.logger.log(LogStatus.INFO, "Values under 'SBC Manually Executed Configuration' value displays as: ");
		
	for(int y=0; y<fileNameValues.length; y++) {
	DriverTestcase.logger.log(LogStatus.PASS,  fileNameValues[y] +"      "+ dateValues[y]);
	}
	
	}
}


public void editGSX_manualExecutionConfig(String application, String editGSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {
	
	
	click_commonMethod(application, "GSXcreated", "GSX_selectCreatedValue", xml);
	
	click_commonMethod(application, "Action", "GSXManualConfig_actionDropdown", xml);  //click acton dropdown
	click_commonMethod(application, "Edit link", "GSX_editLink", xml);   //click on edit link
	
//	System.out.println("date displaying as : "+getCurrentDate());
//	System.out.println("username displays as: "+Getkeyvalue("APT_login_1_Username"));
	
	Thread.sleep(6000);
	
	String mainWindow=driver.getWindowHandle();
	Set<String> allwindows = driver.getWindowHandles();
    Iterator<String> itr = allwindows.iterator();
    while(itr.hasNext())
    {
          String childWindow = itr.next();
          if(!mainWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
                System.out.println(driver.switchTo().window(childWindow).getTitle());
                  
//                driver.manage().window().maximize();
                Thread.sleep(1000);
                
//               Write here  whatever you want to do and perform
                System.out.println("came inside child window");
                
                edittextFields_commonMethod(application, "GSX manual Configuration", "GSX_editPage_teaxtArea", editGSXmanualConfigvalur, xml);
               
                scrolltoend();
                Thread.sleep(2000);
                
                click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
                
          }
    }
    
    driver.switchTo().window(mainWindow);
    Thread.sleep(1000);
    //**verifysuccessmessage(application, "Manual Configuration updated Successfully");
    compareText(application, "Update GSX Manual Configuration Successfully", "UpdateManualConfiguration_SuccessMessage", "Manual Configuration updated Successfully", xml);


    
}


public void editPSX_manualExecutionConfig(String application, String editPSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {
	
	
	click_commonMethod(application, "PSXcreated", "PSX_selectCreatedValue", xml);
	
	click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);  //click acton dropdown
	click_commonMethod(application, "Edit link", "PSX_editLink", xml);   //click on edit link
	
//	System.out.println("date displaying as : "+getCurrentDate());
//	System.out.println("username displays as: "+Getkeyvalue("APT_login_1_Username"));
	
	String mainWindow=null;
	
	Thread.sleep(7000);
	
	mainWindow=driver.getWindowHandle();
	Set<String> allwindows = driver.getWindowHandles();
    Iterator<String> itr = allwindows.iterator();
    while(itr.hasNext())
    {
          String childWindow = itr.next();
          if(!mainWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
                System.out.println(driver.switchTo().window(childWindow).getTitle());
                  
//                driver.manage().window().maximize();
                Thread.sleep(1000);
                
//               Write here  whatever you want to do and perform
                System.out.println("came inside child window");
                
                edittextFields_commonMethod(application, "PSX manual Configuration", "GSX_editPage_teaxtArea", editPSXmanualConfigvalur, xml);
                
                scrolltoend();
                Thread.sleep(1000);
                
                click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
                
          }
    }
    
    driver.switchTo().window(mainWindow);
    Thread.sleep(1000);
    compareText(application, "Update PSX Manual Configuration Successfully", "UpdateManualConfiguration_SuccessMessage", "Manual Configuration updated Successfully", xml);

    
}


public void editSBC_manualExecutionConfig(String application, String editGSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {
	
	
	click_commonMethod(application, "PSXcreated", "SBC_selectCreatedValue", xml);
	
	click_commonMethod(application, "Action", "SBCManualConfig_actionDropdown", xml);  //click acton dropdown
	click_commonMethod(application, "Edit link", "SBC_editLink", xml);   //click on edit link
	
	System.out.println("date displaying as : "+getCurrentDate());
	System.out.println("username displays as: "+Getkeyvalue("APT_login_1_Username"));
	
	Thread.sleep(6000);
	
	String mainWindow=driver.getWindowHandle();
	Set<String> allwindows = driver.getWindowHandles();
    Iterator<String> itr = allwindows.iterator();
    while(itr.hasNext())
    {
          String childWindow = itr.next();
          if(!mainWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
                System.out.println(driver.switchTo().window(childWindow).getTitle());
                  
//                driver.manage().window().maximize();
                Thread.sleep(1000);
                
//               Write here  whatever you want to do and perform
                System.out.println("came inside child window");
                
                edittextFields_commonMethod(application, "GSX manual Configuration", "GSX_editPage_teaxtArea", editGSXmanualConfigvalur, xml);
               
                scrolltoend();
                Thread.sleep(2000);
                click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
                
          }
    }
    
    driver.switchTo().window(mainWindow);
    Thread.sleep(1000);
    compareText(application, "Update SBC Manual Configuration Successfully", "UpdateManualConfiguration_SuccessMessage", "Manual Configuration updated Successfully", xml);


    
}



public void deleteSBC_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {
	
	scrolltoend();
	Thread.sleep(2000);
	
	click_commonMethod(application, "SBCcreated", "SBC_selectCreatedValue", xml);
	
	click_commonMethod(application, "Action", "SBCManualConfig_actionDropdown", xml);  //click acton dropdown
	click_commonMethod(application, "Delete link", "SBC_deleteLink", xml);   //click on edit link
	
	 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
     if(DeleteAlertPopup.isDisplayed())
     {
   	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/DeleteMessae_PSX_GSX_SBC")));
   	 DriverTestcase.logger.log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
   	 
     click_commonMethod(application, "Delete", "DeleteButton_PSX_GSX_SBC", xml);
     Thread.sleep(3000);
     
     scrollToTop();
        compareText(application, "SBC Manual Configuration deleted Successfully", "SBC_DeleteManualConfiguration_SuccessMessage", "Deleted SBC manual config successfully.", xml);
     }
     else
     {
           Log.info("Delete alert popup is not displayed");
           DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
     }

}


    

/**
* perform delete operation under PSX manually executed configuration panel	
* @param application
* @throws InterruptedException
* @throws DocumentException
* @throws IOException
*/
public void deletePSX_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {
	
	scrolltoend();
	Thread.sleep(2000);
	
	click_commonMethod(application, "PSXcreated", "PSX_selectCreatedValue", xml);
	
	click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);  //click action dropdown
	click_commonMethod(application, "Delete link", "PSX_deleteLink", xml);   //click on edit link
	
	 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
     if(DeleteAlertPopup.isDisplayed())
     {
   	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/DeleteMessae_PSX_GSX_SBC")));
   	 DriverTestcase.logger.log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
   	 
        click_commonMethod(application, "Delete", "DeleteButton_PSX_GSX_SBC", xml);
        Thread.sleep(3000);
        
        scrollToTop();
        compareText(application, "PSX Manual Configuration deleted Successfully", "PSX_DeleteManualConfiguration_SuccessMessage", "Deleted PSX manual config successfully.", xml);


     }
     else
     {
           Log.info("Delete alert popup is not displayed");
           DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
     }

}


/**
 * perform delete operation under GSX manually executed configuration panel	
 * @param application
 * @throws InterruptedException
 * @throws DocumentException
 * @throws IOException
 */
	public void deleteGSX_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(2000);
		
		click_commonMethod(application, "PSXcreated", "GSX_selectCreatedValue", xml);
		
		click_commonMethod(application, "Action", "GSXManualConfig_actionDropdown", xml);  //click action dropdown
		click_commonMethod(application, "Delete link", "GSX_deleteLink", xml);   //click on edit link
		
		 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
	     if(DeleteAlertPopup.isDisplayed())
	     {
	   	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/DeleteMessae_PSX_GSX_SBC")));
	   	 DriverTestcase.logger.log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
	   	 
	        click_commonMethod(application, "Delete", "DeleteButton_PSX_GSX_SBC", xml);
	        Thread.sleep(3000);
	        
	        scrollToTop();
	         compareText(application, "GSX Manual Configuration deleted Successfully", "GSX_DeleteManualConfiguration_SuccessMessage", "Deleted GSX manual config successfully.", xml);
	     }
	     else
	     {
	           Log.info("Delete alert popup is not displayed");
	           DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
	     }

	}

	      


/**
 *  perform Manual PSX execution
 * @param application
 * @throws InterruptedException
 * @throws DocumentException
 * @throws IOException 
 */
public void addPSX_manualExecutionConfig(String application, String PSXmanualConfiguratio) throws InterruptedException, DocumentException, IOException {
	
	scrolltoend();
	Thread.sleep(2000);
	
	boolean PSXHeader=getwebelement(xml.getlocator("//locators/" + application + "/PSXmanualConfig_PanelHeader")).isDisplayed();
	if(PSXHeader) {
		DriverTestcase.logger.log(LogStatus.PASS, "'PSX Manully Executed Configuration' panel is displaying");
		System.out.println("'PSX Manully Executed Configuration' panel is displaying");
		
		
		click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);
		Thread.sleep(5000);
		click_commonMethod(application, "Add link", "PSX_addLink", xml);
		
		//TODO compare Header name
		
		String mainWindow=driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
	    Iterator<String> itr = allwindows.iterator();
	    while(itr.hasNext())
	    {
	          String childWindow = itr.next();
	          if(!mainWindow.equals(childWindow)){
	                driver.switchTo().window(childWindow);
	                System.out.println(driver.switchTo().window(childWindow).getTitle());
	                  
	                Thread.sleep(1000);
	                
	              String psxConfiguredValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/manualConfiguration_textArea")));
	              if(psxConfiguredValue.isEmpty()) {
	            	  DriverTestcase.logger.log(LogStatus.PASS, "No values displaying under PSX Manually Executed Configurations");
	              }else {
	            	  DriverTestcase.logger.log(LogStatus.PASS, "'Under PSX Manually Executed Configurations' value is displaying as: "+ psxConfiguredValue);
	              }
//	               Write here  whatever you want to do and perform
	                System.out.println("came inside child window");
	                
	            	//Text Area	
	        		addtextFields_commonMethod(application, "Manul Configuration", "manualConfiguration_textArea", PSXmanualConfiguratio, xml);
	        		scrolltoend();
	        		click_commonMethod(application, "Save", "saveButton_manualConfiguration", xml);   //click on Save buttton
	        		Thread.sleep(1000);
	        			                
	          }
	    }
	    
	    Thread.sleep(5000);
	    //**driver.close();//Automatically getting closed
	     driver.switchTo().window(mainWindow);
	     Thread.sleep(1000);

	     scrollToTop();
	    compareText(application, "PSX Manual Configuration added Successfully", "AddManualConfiguration_SuccessMessage", "Manual Configuration added Successfully", xml);
	    
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'PSX Manully Executed Configuration' panel is not displaying");
		System.out.println("'PSX Manully Executed Configuration' panel is not displaying");
	}
}


/**
 *  perform Manual GSX execution
 * @param application
 * @throws InterruptedException
 * @throws DocumentException
 * @throws IOException 
 */
public void addGSX_manualExecutionConfig(String application, String GSXmanualConfiguratio) throws InterruptedException, DocumentException, IOException {
	
	scrolltoend();
	Thread.sleep(2000);
	
	boolean PSXHeader=getwebelement(xml.getlocator("//locators/" + application + "/GSXmanualConfig_PanelHeader")).isDisplayed();
	if(PSXHeader) {
		DriverTestcase.logger.log(LogStatus.PASS, "'GSX Manully Executed Configuration' panel is displaying");
		System.out.println("'GSX Manully Executed Configuration' panel is displaying");
		
		
		click_commonMethod(application, "Action", "GSXManualConfig_actionDropdown", xml);
		click_commonMethod(application, "Add link", "GSX_addLink", xml);
		
		//TODO compare Header name
		
		String mainWindow=driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
	    Iterator<String> itr = allwindows.iterator();
	    while(itr.hasNext())
	    {
	          String childWindow = itr.next();
	          if(!mainWindow.equals(childWindow)){
	                driver.switchTo().window(childWindow);
	                System.out.println(driver.switchTo().window(childWindow).getTitle());
	                  
	                Thread.sleep(1000);
	                
	              String psxConfiguredValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/manualConfiguration_textArea")));
	              if(psxConfiguredValue.isEmpty()) {
	            	  DriverTestcase.logger.log(LogStatus.PASS, "No values displaying under GSX Manually Executed Configurations");
	              }else {
	            	  DriverTestcase.logger.log(LogStatus.PASS, "'Under GSX Manually Executed Configurations' value is displaying as: "+ psxConfiguredValue);
	              }
//	               Write here  whatever you want to do and perform
	                System.out.println("came inside child window");
	                
	            	//Text Area	
	        		addtextFields_commonMethod(application, "Manul Configuration", "manualConfiguration_textArea", GSXmanualConfiguratio, xml);
	        		scrolltoend();
	        		click_commonMethod(application, "Save", "saveButton_manualConfiguration", xml);   //click on Save buttton
	        		Thread.sleep(1000);
	        			                
	          }
	    }
	    
	    Thread.sleep(5000);
	    //**driver.close();//Automatically getting closed
	     driver.switchTo().window(mainWindow);
	     Thread.sleep(1000);

	     scrollToTop();
	    //**verifysuccessmessage(application, "Manual Configuration added Successfully");  //verify success Message
	    compareText(application, "GSX Manual Configuration added Successfully", "GSXManualConfiguration_SuccessMessage", "Manual Configuration added Successfully", xml);
	    
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'GSX Manully Executed Configuration' panel is not displaying");
		System.out.println("'GSX Manully Executed Configuration' panel is not displaying");
	}
}


/**
* Verify the files added under PSX Manually Executed Configuration panel	
* @param application
* @throws InterruptedException
* @throws DocumentException
* @throws IOException
*/
public void verifyPSXfileAdded(String application) throws InterruptedException, DocumentException, IOException {
	
	
	scrolltoend();
	Thread.sleep(1000);
	
	WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/PSX_selectCreatedValue"));
	String PSXfilenameCreated=Gettext(filename);
	
	if(PSXfilenameCreated.isEmpty()) {
		DriverTestcase.logger.log(LogStatus.FAIL, "PSX Manually Executed configuration file name is not displaying");
		System.out.println("PSX Manually Executed configuration file name is not displaying");
	}else {
		
		int i=0; int k=0; int j=0;
		
	//Fetching Column Names	
		List<WebElement> columns=getwebelements(xml.getlocator("//locators/" + application + "/PSX_columnNames"));
		int listOfColumns=columns.size();
		
		String columnName[]=new String[listOfColumns];
		for (WebElement column : columns) {
			
			columnName[k]=column.getText();
			k++;
		}
		
		DriverTestcase.logger.log(LogStatus.INFO, "column names display as: "+ columnName[1] + "  " + columnName[2]);  //printing column Names
		
		
	//Fetching value under File Column	
		List<WebElement> files=getwebelements(xml.getlocator("//locators/" + application + "/PSX_fileName"));
		int listOfFiles=files.size();
		
		String[] fileNameValues=new String[listOfFiles];
		
		for(WebElement filenametransfer : files) {
			
			fileNameValues[i] = filenametransfer.getText();
			i++;
		}
		
	
	//Fetching value under Date Column
		List<WebElement> dates=getwebelements(xml.getlocator("//locators/" + application + "/PSX_dates"));
		int listOfDates=dates.size();
		
		String[] dateValues=new String[listOfDates];
		
		for(WebElement dateList : dates) {
			
			dateValues[j] = dateList.getText();
			j++;
		}
	
		
		DriverTestcase.logger.log(LogStatus.INFO, "Values under 'PSX Manually Executed Configuration' value displays as: ");
	
	for(int y=0; y<fileNameValues.length; y++) {
	DriverTestcase.logger.log(LogStatus.PASS, fileNameValues[y] +"      "+ dateValues[y]);
	}
		
	}
}


/**
* Verify the files added under GSX Manually Executed Configuration panel	
* @param application
* @throws InterruptedException
* @throws DocumentException
* @throws IOException
*/
public void verifyGSXfileAdded(String application) throws InterruptedException, DocumentException, IOException {
	
	
	scrolltoend();
	Thread.sleep(1000);
	
	WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/GSX_selectCreatedValue"));
	String GSXfilenameCreated=Gettext(filename);
	
	if(GSXfilenameCreated.isEmpty()) {
		DriverTestcase.logger.log(LogStatus.FAIL, "GSX Manually Executed configuration file name is not displaying");
		System.out.println("GSX Manually Executed configuration file name is not displaying");
	}else {
		
		int i=0; int k=0; int j=0;
		
	//Fetching Column Names	
		List<WebElement> columns=getwebelements(xml.getlocator("//locators/" + application + "/GSX_columnNames"));
		int listOfColumns=columns.size();
		
		String columnName[]=new String[listOfColumns];
		for (WebElement column : columns) {
			
			columnName[k]=column.getText();
			k++;
		}
		
		DriverTestcase.logger.log(LogStatus.INFO, "column names display as: ");  //printing column Names
		DriverTestcase.logger.log(LogStatus.PASS,  columnName[1] + "     	     " + columnName[2]);
		
	//Fetching value under File Column	
		List<WebElement> files=getwebelements(xml.getlocator("//locators/" + application + "/GSX_fileName"));
		int listOfFiles=files.size();
		
		String[] fileNameValues=new String[listOfFiles];
		
		for(WebElement filenametransfer : files) {
			
			fileNameValues[i] = filenametransfer.getText();
			i++;
		}
		
	
	//Fetching value under Date Column
		List<WebElement> dates=getwebelements(xml.getlocator("//locators/" + application + "/GSX_dates"));
		int listOfDates=dates.size();
		
		String[] dateValues=new String[listOfDates];
		
		for(WebElement dateList : dates) {
			
			dateValues[j] = dateList.getText();
			j++;
		}
	
		
		DriverTestcase.logger.log(LogStatus.INFO, "Values under 'GSX Manually Executed Configuration' value displays as: ");
	
	for(int y=0; y<fileNameValues.length; y++) {
	DriverTestcase.logger.log(LogStatus.PASS, fileNameValues[y] +"      "+ dateValues[y]);
	}
	
	}
}

	
	
	
	
public void trunkHistory(String application, String TrunkGroupSiteOrderNumber,  String ExistingTrunkName) throws InterruptedException, DocumentException {
	
	scrollToTop();
	waitForpageload();
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/ViewTrunk_Header")).isDisplayed()) {
		DriverTestcase.logger.log(LogStatus.PASS, "'View Trunk' Page displayed as expected");
		System.out.println("'View Trunk' Page displayed as expected");
		
		//Action button	
		click_commonMethod(application, "Action", "ViewTrunkPage_ActionButton", xml);
		//click on History link
		click_commonMethod(application, "History", "ViewTrunkPage_HistoryLink", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/HistoryPage_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'History Trunk' Page displayed as expected");
			System.out.println("'History Trunk' Page displayed as expected");
		
			try {
		isDisplayed(application, "Rivision_columnName", "Rivision Column", xml);	
		isDisplayed(application, "RestoredForm_columnName", "Restored Form column", xml);
		isDisplayed(application, "RevisionType_columnName", "Revision Type_column", xml);
		isDisplayed(application, "Date_columnName", "Date column", xml);
		isDisplayed(application, "LastModifiedBy_columnName", "Last Modified By column", xml);
		isDisplayed(application, "TrunkGroupName_columnName", "Trunk Group Name column", xml);
		isDisplayed(application, "Gateway_columnName", "Gateway column", xml);
		isDisplayed(application, "VoIPProtocol_columnName", "VoIPProtocol column", xml);
		isDisplayed(application, "SignallingTransportProtocol_columnName", "Signalling Transport Protocol column", xml);
		//**isDisplayed(application, "Action_columnName", "Action column");
		isDisplayed(application, "BackButton_History", "Back Button", xml);

			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in History page");
				System.out.println(  e+ " : Field is not displayed in History page");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in History page ");
				System.out.println(  e+" : Field is not displayed in History page");
			}

		}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'History Trunk' Page is not navigated as expected");
		System.out.println("'History Trunk' Page is not navigated as expected");
		  }
	
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'View Trunk' Page is not navigated as expected");
		System.out.println("'View Trunk' Page is not navigated as expected");
	}
	
	
}

		
	


 public void trunkPSXQueue(String application, String TrunkGroupSiteOrderNumber,  String ExistingTrunkName) throws InterruptedException, DocumentException {
	
	 //**click_commonMethod(application, "Back Button in History page", "BackButton_History", xml);
	scrollToTop();
	waitForpageload();
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/ViewTrunk_Header")).isDisplayed()) {
		DriverTestcase.logger.log(LogStatus.PASS, "'View Trunk' Page displayed as expected");
		System.out.println("'View Trunk' Page displayed as expected");
		
		//Action button	
		click_commonMethod(application, "Action", "ViewTrunkPage_ActionButton", xml);
		//click on History link
		click_commonMethod(application, "PSX Queue Link", "ViewTrunkPage_PSXQueueLink", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/PSXQueuePage_Header")).isDisplayed()) {
			DriverTestcase.logger.log(LogStatus.PASS, "'PSX Queue Trunk' Page displayed as expected");
			System.out.println("'PSX Queue Trunk' Page displayed as expected");
		
			try {
		isDisplayed(application, "LastStartTime_columnName", "Last Start Time Column", xml);	
		isDisplayed(application, "Customer_columnName", "Customer column", xml);
		isDisplayed(application, "Service_columnName", "Service column", xml);
		isDisplayed(application, "TrunkName_columnName", "Trunk Name column", xml);

			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, e+ " : Field is not displayed in PSX Queue page");
				System.out.println(  e+ " : Field is not displayed in PSX Queue page");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,  e+" : Field is not displayed in PSX Queue page ");
				System.out.println(  e+" : Field is not displayed in PSX Queue page");
			}

		}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'PSX Queue' Page is not navigated as expected");
		System.out.println("'PSX Queue' Page is not navigated as expected");
		  }
	
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, "'View Trunk' Page is not navigated as expected");
		System.out.println("'View Trunk' Page is not navigated as expected");
	}
	
	
}
	
	
	
	
	
	
 
	public void verifyBulkInterface(String application, String bulkjob_filepath) throws InterruptedException, DocumentException {
		
		//cancel bulk interface
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Bulk Interface", "bulkinterfacelink", xml);
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		click_commonMethod(application, "Cancel", "bulkjobcancel", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}

		//submit bulk job
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Bulk Interface", "bulkinterfacelink", xml);
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		WebElement BulkJob_Choosefile_button= getwebelement(xml.getlocator("//locators/" + application + "/bulkjob_choosefilebutton"));
		BulkJob_Choosefile_button.sendKeys(bulkjob_filepath);
		click_commonMethod(application, "Submit", "bulkjobsubmit", xml);
		compareText(application, "Bulk Job Success message", "successmsg", "FRC Numbers sent to Queue for Creation. Please check the bulk operation of SANs here.", xml);
		
		//Archive link in bulk interface page
		click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
		click_commonMethod(application, "Archive", "bulkinterface_archivelink", xml);
		compareText(application, "Status history found message", "successmsg", "Service Status History Found Successfully.", xml);
		scrolltoend();
		click_commonMethod(application, "Cancel", "bulkinterfacepage_cancel", xml);

		//Refresh link in bulk interface page
		scrollToTop();
		click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
		click_commonMethod(application, "Archive", "bulkinterface_refreshlink", xml);
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Bulk Interface page refresh successful");
		Log.info("Bulk Interface page refresh successful");
		Thread.sleep(1000);
		scrolltoend();
		click_commonMethod(application, "Cancel", "bulkinterfacepage_cancel", xml);
		Thread.sleep(2000);

	}
	
	public void isDisplayed(String application, String xpath, String labelname, XMLReader xml) {
		boolean availability = false;

		try {
			Thread.sleep(1000);
			availability= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			System.out.println(availability);
			if (availability) {
				DriverTestcase.logger.log(LogStatus.PASS, "Step: '"+labelname+"' is displayed as expected");
				System.out.println("Step: '"+labelname+"' is displayed as expected");
			}
			else {
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: '"+labelname+"' is not displayed");
				System.out.println("Step: '"+labelname+"' is not displayed");
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
				
				
				public void scrolltoview (String application, String labelname, String xpath, XMLReader xml) throws InterruptedException, DocumentException {
					WebElement element = null;
					element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));

					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				}
				
	
	
	
	
}
