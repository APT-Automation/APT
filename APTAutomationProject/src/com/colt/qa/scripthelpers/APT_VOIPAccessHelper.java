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
import com.colt.qa.reporter.ExtentManager;
import com.colt.qa.reporter.ExtentTestManager;
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

				System.out.println("webElement is present " + ele.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, msg);
			} else {

				System.out.println("webElement is not  present" + ele.getText());
				ExtentTestManager.getTest().log(LogStatus.FAIL, msg);
			}

		}
		
					
		
		
		
		
		public void searchorder(String application, String sid) throws InterruptedException, DocumentException, IOException {
			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
			WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
			scrolltoend();
			Clickon(searchbutton);
			scrolltoend();
			scrolltoend();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewLink_SearchOrderPage")));
		}
		
		
	
		
		
		
		public void searchorder_UI(String application, String sid) throws InterruptedException, DocumentException, IOException {
			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
			WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
			scrolltoend();
			Clickon(searchbutton);
			scrolltoend();
			scrolltoend();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewLink_SearchOrderPage")));
		}
		
		
		
		public void verifysuccessmessage(String application, String expected) throws InterruptedException {
			
			scrollToTop();
			
			try {	
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMsg")).isDisplayed();

				if(successMsg) {
					
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
					
					if(expected.contains(alrtmsg)) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
						System.out.println("Message is verified. It is displaying as: "+alrtmsg);
						successScreenshot(application);
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
						System.out.println("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
						successScreenshot(application);
					}
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
					System.out.println(" Success Message is not displaying");
				}
				
			}catch(Exception e) {
				Log.info("failure in fetching success message - 'Service created Successfully'  ");
				ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
				System.out.println(expected+ " message is not getting dislpayed");
				successScreenshot(application);
			}

		}


public void verifysuccessmessageforEditService(String application) throws InterruptedException {
			
			scrollToTop();
			
			try {	
				
				String expected= "Service updated successfully";
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMsg")).isDisplayed();
				
				if(successMsg) {
					
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
					
					if(alrtmsg.equals(expected)) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS," 'service successfully created' message is verified. It is displaying as: "+alrtmsg);
						System.out.println(" 'service successfully created' message is verified. It is displaying as: "+alrtmsg);
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Service creation message is displaying but the success message display as: "+ alrtmsg);
						System.out.println("Service creation message is displaying and the message gets mismatches. It is displaying as: "+ alrtmsg);
					}
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service updated successfully' message is not displaying after creating service");
				}
				
			}catch(Exception e) {
				Log.info("failure in fetching success message - 'Service updated successfully'  ");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service updated successfully' message is not displaying after editing the service");
				System.out.println("Success message for edit Service is not getting dislpayed");
			}

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

						
						Clickon(getwebelement("//div[text()='"+ expectedValueToAdd +"']"));
						

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
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on delete link");

			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click(application, "Delete", "deletebutton");
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

		public void click(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
			WebElement element= null;

			try {
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
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
				e.printStackTrace();
			}

		}
		
		
		
		
	
		
		

		public void WarningMessage(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
			WebElement element= null;
			try {
				element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
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
				
				element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				String value= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
				}
				else if(value!=null) {
					
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
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				String value= element.getAttribute("value");
				
				if(value.isEmpty())
				{
					ExtentTestManager.getTest().log(LogStatus.INFO, "Step: '"+labelname+"' text field is empty");
					System.out.println(value);
				
				}else {
					element.clear();
					element.sendKeys(newValue);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Entered '"+newValue+"' into '"+labelname+"' text field");
				}

			} catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+newValue+"' into '"+labelname+"' text field");
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
				element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")); 
				String ele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' is not found");
				}
				else if (ele!=null && ele.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
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
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is displaying");
				System.out.println(labelname + " text field is displaying");
				
				if(expectedValueToAdd.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No values added to text field "+labelname);
					System.out.println("No values added to text field "+labelname);
				}else {
					
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToAdd);
					
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
			
			//verify whether checkbox is selected/unselected by default		
				if(DefaultSelection.equalsIgnoreCase("yes")) {
					if(isElementSelected) {
						ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " checkbox is selected by default as expected");
						System.out.println(labelname + " checkbox is selected by default as expected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.INFO, labelname + " checkbox is not selected by default");
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
		public void edittextFields_commonMethod(String application, String labelname, String xpathname, String expectedValueToEdit, XMLReader xml) throws InterruptedException, DocumentException, IOException {
				boolean availability=false;
			try {	
				availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).isDisplayed();
				if(availability) {
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is displaying");
					System.out.println(labelname + " text field is displaying");
					
					if(expectedValueToEdit.equalsIgnoreCase("null")) {
						
						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
						ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is not edited. It is displaying as "+actualvalue);
						System.out.println(labelname + " text field is not edited as expected. It is displaying as "+actualvalue);
					}else {
						
						getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).clear();
						
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
						
						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
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
					
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on delete link");

				WebElement DeleteAlertPopup= driver.findElement(By.xpath("//div[@class='modal-content']"));
				if(DeleteAlertPopup.isDisplayed())
				{
					click_commonMethod(application, "Delete", "deletebutton", xml);
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
				public void click_commonMethod_PassingWebelementDirectly(String application, String labelname, String webelement, XMLReader xml) throws InterruptedException, DocumentException {
					WebElement element= null;

					try {
						
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
				
		
		
				
				
				public void SelectDropdownValueUnderDivTag(String application ,String lebelname, String dropdownToBeSelectedInTheEnd, String dropdownXpath, String commonDropdownValueTag) throws InterruptedException, DocumentException, IOException {
					
					try {
						// Select  Country dropdown
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+dropdownXpath+"")));
							List<WebElement> dropdownValueList = driver.findElements(By.xpath(commonDropdownValueTag));
							
							for (WebElement dropdownvaluelist : dropdownValueList) {
								System.out.println("Available " +lebelname+ " are : " + dropdownvaluelist.getText().toString()+ "  ,  ");
								ExtentTestManager.getTest().log(LogStatus.PASS,"Step : Available  '" +lebelname+ "'  is : " + dropdownvaluelist.getText().toString());
								Log.info("Available " +lebelname+ " is : " + dropdownvaluelist.getText().toString());
							}
							
							
							//
							// click on Country dropdown
							WebElement selectDropdownValue = driver.findElement(By.xpath("//div[contains(text(),'" + dropdownToBeSelectedInTheEnd + "')]"));
							System.out.println("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
							ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected  '"+lebelname+ "'  is : " + selectDropdownValue.getText().toString());
							Log.info("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
							selectDropdownValue.click();
							
							
						
				}catch(NoSuchElementException e) {
					e.printStackTrace();
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
					System.out.println( lebelname + " dropdown is not displaying");
					
				}catch(Exception ee) {
					ee.printStackTrace();
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
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
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				  System.out.println(labelname + " dropdown is displaying");
				  
				  WebElement el =getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdownXpath +""));
				  
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
		            
				  if(dropdownToBeSelectedInTheEnd.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, "No values selected under "+ labelname + " dropdown");
				  }else {
					  Select s1=new Select(el);
					  s1.selectByVisibleText(dropdownToBeSelectedInTheEnd);
					  
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
	

}



					
					
					
					
				
				
				
				//======================================  Common Methods  ===========================================	
				
				//======================================  Common Methods  ===========================================

				//======================================  Common Methods  ===========================================

				


	public void createcustomer(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Customer Creation Functionality");
		try {
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		
		System.out.println("Mouse hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
		Log.info("Mouse hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "create customer link", "createcustomerlink", xml);
		
		compareText(application, "create customer page header", "createcustomer_header", "Create Customer", xml);
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);

		//Warning msg check
		warningMessage_commonMethod(application, "customernamewarngmsg", "Legal Customer Name", xml);
		warningMessage_commonMethod(application, "countrywarngmsg", "Country", xml);
		warningMessage_commonMethod(application, "ocnwarngmsg", "OCN", xml);
		warningMessage_commonMethod(application, "typewarngmsg", "Type", xml);
		warningMessage_commonMethod(application, "emailwarngmsg", "Email", xml);

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
		
		click_commonMethod(application, "Ok", "okbutton", xml);
//		compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
		verifysuccessmessage(application, "Customer successfully created.");
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
			System.out.println(  e+ " : Field is not displayed in  Service Creation page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
			System.out.println(  e+" : Field is not displayed in Service Creation page");
		}
		sa.assertAll();
	}



				
		

public void customerCreationForm_UI(String application)	throws Exception {
	Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
	
	System.out.println("Mouse hovered on Manage Customer's Service");
	ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
	Log.info("Mouse hovered on 'Manage Customers Service' menu item");

	click_commonMethod(application, "create customer link", "createcustomerlink", xml);
	
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/createcustomer_header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Create Customer' page navigated as expected");
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
	
	isDisplayed(application, "okbutton", "OK", xml);

	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Create Customer' page not navigated");
		System.out.println("'Create Customer' page navigated");
	}
	sa.assertAll();
}


	

	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected)
			throws InterruptedException, DocumentException, IOException {

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		
		System.out.println("Mouse hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
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
	
	System.out.println("Mouse hovered on Manage Customer's Service");
	ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
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
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Customer Selection Functionality");
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		
		System.out.println("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click(application, "Create Order/Service Link", "CreateOrderServiceLink");	
		Log.info("=== Create Order/Service navigated ===");
		
		//click on Next button to check mandatory messages	
		click(application, "Next", "nextbutton");


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
		
		click_commonMethod(application, "Next", "nextbutton", xml);
		

		//Warning messages verify
		warningMessage_commonMethod(application, "order_contractnumber_warngmsg", "Order/Contract Number(Parent SID)", xml);
		warningMessage_commonMethod(application, "servicetype_warngmsg", "Service Type", xml);

		if (neworder.equalsIgnoreCase("YES")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying New Order Creation Functionality");
			addtextFields_commonMethod(application, "Order/Contract Number", "newordertextfield", neworderno, xml);
			addtextFields_commonMethod(application, "RFI Voice line Number", "newrfireqtextfield", newrfireqno, xml);
			click_commonMethod(application, "create order", "createorderbutton", xml);
//			compareText(application, "Order Creation Success message", "OrderCreatedSuccessMsg", "Order created successfully", xml);
			verifysuccessmessage(application, "Order created successfully");
			ScrolltoElement(application, "CreateOrderHeader", xml);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;
		} 

		else if (existingorderservice.equalsIgnoreCase("YES")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Existing Order Selection Functionality");
			
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber, xml);
			Log.info("=== Order Contract Number selected===");

			

			SelectOrderNumber = existingordernumber;
		} else {
			System.out.println("Order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step :Order not selected");
			Log.info("Order not selected");
		}
	}

	
	
	public void OrderCreation_UI(String application, String neworder, String neworderno, String newrfireqno, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		scrolltoend();
		
		click_commonMethod(application, "Next", "nextbutton", xml);
		

		//Warning messages verify
		warningMessage_commonMethod(application, "order_contractnumber_warngmsg", "Order/Contract Number(Parent SID)", xml);
		warningMessage_commonMethod(application, "servicetype_warngmsg", "Service Type", xml);

		if (neworder.equalsIgnoreCase("YES")) {

			
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

			

			SelectOrderNumber = existingordernumber;
		} else {
			System.out.println("Order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step :Order not selected");
			Log.info("Order not selected");
		}
	}

public void verifyCustomerDetailsInformation(String application, String newCustomerCreation, String existingCustomerSelection,String newCustomer,
			String existingCustomer, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Customer informations");
		
		waitforPagetobeenable();
		ScrolltoElement(application, "CustomerDetailsHeader", xml);
		//Customer Name
			if(newCustomerCreation.equalsIgnoreCase("Yes") || existingCustomerSelection.equalsIgnoreCase("No")) {
				compareText(application, "Customer Name", "Name_Value", newCustomer, xml);
				compareText(application, "Country", "Country_Value", country, xml);
				compareText(application, "OCN", "OCN_Value", ocn, xml);
				compareText(application, "Reference", "Reference_Value", reference, xml);
				compareText(application, "Technical Contact Name", "TechnicalContactName_Value", tcn, xml);
				compareText(application, "Type", "Type_Value", type, xml);
				compareText(application, "Email", "Email_Value", email, xml);
				compareText(application, "Phone", "Phone_Value", phone, xml);
				compareText(application, "Fax", "Fax_Value", fax, xml);
			}
			else if(newCustomerCreation.equalsIgnoreCase("No") || existingCustomerSelection.equalsIgnoreCase("Yes")) {
				compareText(application, "Customer Name", "Name_Value", existingCustomer, xml);
			}
			
		//Main Domain
			if(maindomain.equalsIgnoreCase("Null")) {
				Log.info("A default displays for main domain field, if no provided while creating customer");
			}else {
				compareText(application, "Main Domain", "MainDomain_Value", maindomain, xml);
			}
		
		Log.info("=== Customer Details panel fields Verified ===");
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
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User added successfully");

			//Edit User
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click(application, "User Radio button", "UserUnchecked");
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

			click(application, "Action dropdown", "UserActionDropdown");
			click(application, "Edit", "edit");
			
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
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

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
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

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
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

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
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

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
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

			click(application, "Action dropdown", "UserActionDropdown");
			delete("nginservice", "delete", "User", "User successfully deleted");

		}
	}


	
	public void verifyDumpFunctionInviewServicepage(String application) throws InterruptedException, DocumentException {
		
		try {
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		waitforPagetobeenable();
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		
			click_commonMethod(application, "Action", "serviceactiondropdown", xml);
		   
		   click_commonMethod(application, "Dump", "DumpLink", xml);
		   
		   waitforPagetobeenable();
		   boolean DumpPage=false;
		   DumpPage=getwebelement(xml.getlocator("//locators/" + application + "/dumpPage_header")).isDisplayed();
		   if(DumpPage) {
			   ExtentTestManager.getTest().log(LogStatus.PASS, "Service Dump Detail page is displaying");
			   System.out.println("Service Dump Detail page is displaying");
			  
			  //Header 
			   String headerName=getwebelement(xml.getlocator("//locators/" + application + "/dumpheaderName")).getText();
			   if(headerName.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "Header name is not displaying");
				   System.out.println("Header name is not displaying");
				 
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "Dump header name is displaying as "+ headerName);  
				   System.out.println("Dump header name is displaying as "+ headerName);
			   }
			   
			   //body
			   String bodyContent=getwebelement(xml.getlocator("//locators/" + application + "/dumpMessage_body")).getText();
			   if(bodyContent.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "Dump value is not displaying");
				   System.out.println("Dump value is not displaying");
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "Dump value is displaying as "+bodyContent);
				   System.out.println("Dump value is displaying as "+bodyContent);
			   }
			   
			   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/dump_xButton")));
			   waitforPagetobeenable();
			   
				click(application, "Action dropdown", "serviceactiondropdown");
				compareText(application, "Edit Link", "EditLink", "Edit");
				compareText(application, "Delete Link", "DeleteLink", "Delete");
				compareText(application, "Show Infovista Report Link", "ShowInfovistaReportLink", "Show Infovista Report");
				compareText(application, "Show New Infovista Report Link", "ShowNewInfovistaReportLink", "Show New Infovista Report");
				compareText(application, "Manage Subnets Ipv6 Link", "ManageSubnetsIpv6Link", "Manage Subnets Ipv6");
				compareText(application, "Dump Link", "DumpLink", "Dump");
		   }else {
			   ExtentTestManager.getTest().log(LogStatus.FAIL, "Service Dump Detail page is not displaying");
			   System.out.println("Service Dump Detail page is not displaying");

		   }
		   
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		sa.assertAll();
	}

	
	public void manageSubnet_viewServicepage(String application) throws InterruptedException, DocumentException {
		
		waitforPagetobeenable();
		try {
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		waitforPagetobeenable();
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click_commonMethod(application, "Action", "serviceactiondropdown", xml);
		waitforPagetobeenable();
		   click_commonMethod(application, "Manage Subnet IPv6", "ManageSubnetsIpv6Link", xml);
		   
		   waitforPagetobeenable();
		   boolean DumpPage=false;
		   DumpPage=getwebelement(xml.getlocator("//locators/" + application + "/dumpPage_header")).isDisplayed();
		   if(DumpPage) {
			   ExtentTestManager.getTest().log(LogStatus.PASS, "Manage Subnet IPv6 page is displaying");
			   System.out.println("manage subnet ipv6 page is displaying");
			  
			   try {
			  //Header 
			   String headerName=getwebelement(xml.getlocator("//locators/" + application + "/dumpheaderName")).getText();
			   if(headerName.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "Header name is not displaying");
				   System.out.println("Header name is not displaying");
				 
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "manage Subnet IPv6 header name is displaying as "+ headerName);  
				   System.out.println("manage subnet ipv6 header name is displaying as "+ headerName);
			   }
			   
			   //body
			   String bodyContent=getwebelement(xml.getlocator("//locators/" + application + "/manageSubnet_successMSG")).getText();
			   if(bodyContent.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "manage subnet value is not displaying");
				   System.out.println("manage subnet value is not displaying");
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "manage subnet message is displaying as "+bodyContent);
				   System.out.println("manage subnet message is displaying as "+bodyContent);
			   }
			   
			   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/dump_xButton")));
			   
			   
		   }catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in manage subnet ipv6");
				System.out.println(  e+ " : Field is not displayed in manage subnet ipv6");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in manage subnet ipv6 ");
				System.out.println(  e+" : Field is not displayed in manage subnet ipv6");
			}
			
		   }else {
			   ExtentTestManager.getTest().log(LogStatus.FAIL, "manage subnet ipv6 page is not displaying");
			   System.out.println("manage subnet ipv6 page is not displaying");

		   }
		   
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		sa.assertAll();
		   
	}
	
	
	
	public void manageSubnet_viewServicepage_UI(String application) throws InterruptedException, DocumentException {
		
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		waitforPagetobeenable();
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
			waitforPagetobeenable();
			try {
			click_commonMethod(application, "Action", "serviceactiondropdown", xml);
			waitforPagetobeenable();
		   click_commonMethod(application, "Manage Subnet IPv6", "ManageSubnetsIpv6Link", xml);
		   
		   
		   boolean DumpPage=false;
		   DumpPage=getwebelement(xml.getlocator("//locators/" + application + "/dumpPage_header")).isDisplayed();
		   if(DumpPage) {
			   ExtentTestManager.getTest().log(LogStatus.PASS, "Manage Subnet IPv6 page is displaying");
			   System.out.println("manage subnet ipv6 page is displaying");
			  
			  //Header 
			   String headerName=getwebelement(xml.getlocator("//locators/" + application + "/dumpheaderName")).getText();
			   if(headerName.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "Header name is not displaying");
				   System.out.println("Header name is not displaying");
				 
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "manage Subnet IPv6 header name is displaying as "+ headerName);  
				   System.out.println("manage subnet ipv6 header name is displaying as "+ headerName);
			   }
			   
			   //body
			   String bodyContent=getwebelement(xml.getlocator("//locators/" + application + "/manageSubnet_successMSG")).getText();
			   if(bodyContent.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "manage subnet value is not displaying");
				   System.out.println("manage subnet value is not displaying");
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "manage subnet message is displaying as "+bodyContent);
				   System.out.println("manage subnet message is displaying as "+bodyContent);
			   }
			   
			   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/dump_xButton")));
			   
			   
			   
		   }else {
			   ExtentTestManager.getTest().log(LogStatus.FAIL, "manage subnet ipv6 page is not displaying");
			   System.out.println("manage subnet ipv6 page is not displaying");

		   }
		   
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in manage subnet ipv6");
			System.out.println(  e+ " : Field is not displayed in manage subnet ipv6");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in manage subnet ipv6 ");
			System.out.println(  e+" : Field is not displayed in manage subnet ipv6");
		}
		
	}
	


	public void verifyManagementOptionspanel(String application, String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView
			,String PerformanceReporting,	String ProactiveNotification, String	NotificationManagementTeam, String	DialUserAdministration) throws InterruptedException, DocumentException, IOException {
		
			implicitlyWait("Service screen");
			webdriverWait(application, "servicepanel_header", xml);
			waitforPagetobeenable();	
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
		}
		
	}

	
	
	
	




	//============================================================================================


	public void navigateToManageCustomerServicePage(String application) throws InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/mcslink")));
		
		Reporter.log("=== MCS page navigated ===");
		
	}

	public void navigateToCreateOrderServicePage(String application) throws InterruptedException, DocumentException {

		navigateToManageCustomerServicePage(application);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createorderlink")));
		
		Reporter.log("=== Create Order/Service navigated ===");
	}

	boolean customername;

	public void verifychoosecustomer(String application, String name, String customer)
			throws InterruptedException, IOException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		waitforPagetobeenable();

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

		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/chooseCustomerdropdown")));
		waitforPagetobeenable();
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
			getwebelement(xml.getlocator("//locators/" + application + "/chooseCustomerdropdown")).clear();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/chooseCustomerdropdown")));
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
			waitforPagetobeenable();

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
			waitforPagetobeenable();
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

			SelectDropdownValueUnderDivTag(application, "Existing order", existingordernumber, "existingorderdropdown", "commonDropdownValueTag");
			Log.info("=== Order Contract Number selected===");

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
			

			click(application, "select order switch", "selectorderswitch");	
			EnterTextValue(application, neworderno, "Order/Contract Number", "newordertextfield");
			EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");
			click(application, "create order", "createorderbutton");	
//			compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully");
			verifysuccessmessage(application, "Order created successfully");
			scrolltoview(CreateOrder_Header);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;

		} else {

			System.out.println("new order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : clicked on button to create new order");
		}

	}

	
	public void addDropdownValues_commonMethod4(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml) throws InterruptedException, DocumentException {
		  boolean availability=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
			  System.out.println(labelname + " dropdown is displaying");
			  
			  if(expectedValueToAdd.equalsIgnoreCase("null")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+labelname+ " dropdown");
				  System.out.println(" No values selected under "+labelname+ " dropdown");
			  }else {
				  
				  Clickon(getwebelement("//div[label[text()='"+labelname+"']]//div[text()='×']"));
				  
				  
				  //verify list of values inside dropdown
				  List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+labelname+ " dropdown is:  ");
				  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
				  
					for (WebElement valuetypes : listofvalues) {
								Log.info("service sub types : " + valuetypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
								System.out.println(" " + valuetypes.getText());
					}
					
					
				SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
				
				 Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//input"));
					
				  Clickon(getwebelement("(//div[contains(text(),'"+expectedValueToAdd+"')])[1]"));
				  
				  
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
	
	public void verifyselectservicetype(String application, String servicetype)
			throws InterruptedException, IOException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Service Type Selection Functionality");
		// select service type
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		addDropdownValues_commonMethod4(application, "Service Type", "servicetypetextfield", servicetype, xml);
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

	public void verifyingservicecreation(String application, String sid, String ResellerCode,  String Remarks, String EmailService, String PhoneService, 
			String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView, String PerformanceReporting, String ProactiveNotification,
			String NotificationManagementTeam, String DialUserAdministration, String orderno, String rfireqno, String servicetype) throws InterruptedException, IOException, DocumentException {
		
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Service Creation Functionality");
		if(getwebelement(xml.getlocator("//locators/" + application + "/createorderservice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Servce Creation' page navigated as expected");
			System.out.println("'Servce Creation' page navigated as expected");
		
		try {
			
		Clear(getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")));
		
		WebElement OKbutton= getwebelement(xml.getlocator("//locators/" + application + "/OKbutton_ServiceCreation"));
		scrolltoview(OKbutton);
		
		click(application, "OK", "OKbutton_ServiceCreation");
		//
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
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Package' Dropdown is Enabled");
			System.out.println("Step : 'Package' Dropdown is Enabled");
		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
			System.out.println("Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
		}

		
		// management options- Manage Service Checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ManageServicecheckbox")).isEnabled()){
			if (ManageService.equalsIgnoreCase("YES")) {
				click(application, "'Manage Service' checkbox", "ManageServicecheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
				System.out.println("Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Manage Service' checkbox is not Selected");
				System.out.println("Step : 'Manage Service' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		
		
		//Syslog Event View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/SyslogEventViewcheckbox")).isEnabled()){
			if (SyslogEventView.equalsIgnoreCase("YES")) {
				click(application, "'Syslog Event View' checkbox", "SyslogEventViewcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is not Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		//Service Status View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ServiceStatusViewcheckbox")).isEnabled()){
			if (ServiceStatusView.equalsIgnoreCase("YES")) {
				click(application, "'Service Status View' checkbox", "ServiceStatusViewcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
				System.out.println("Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Service Status View' checkbox is not Selected");
				System.out.println("Step : 'Service Status View' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		//Router Configuration View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/RouterConfigurationViewcheckbox")).isEnabled()){
			if (RouterConfigurationView.equalsIgnoreCase("YES")) {
				click(application, "'Router Configuration View' checkbox", "RouterConfigurationViewcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is not Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
	
		scrolltoend();
			//Performance Reporting checkbox
				if(getwebelement(xml.getlocator("//locators/" + application + "/PerformanceReportingcheckbox")).isEnabled()){
					if (PerformanceReporting.equalsIgnoreCase("YES")) {
						click(application, "'Performance Reporting' checkbox", "PerformanceReportingcheckbox");
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
					} else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is not Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is not Selected");
					}
				
					}else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
						System.out.println("Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
					}
				

		
		//Dial User Administration checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/DialUserAdministrationcheckbox")).isEnabled()){
			if (DialUserAdministration.equalsIgnoreCase("YES")) {
				click(application, "'Dial User Administration' checkbox", "DialUserAdministrationcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is not Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
				System.out.println("Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
			}

				click(application, "OK", "okbutton");
				compareText(application, "Service creation success msg", "servicecreationmessage", "Service successfully created");
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
			System.out.println(  e+ " : Field is not displayed in Service Creation page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
			System.out.println(  e+" : Field is not displayed in Service Creation page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Servce Creation' page not navigated");
			System.out.println("'Servce Creation' page navigated");
		}
				sa.assertAll();
	}

	
	
	
	
	public void serviceCreationForm_UI(String application,String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView, String PerformanceReporting, String ProactiveNotification,
			String NotificationManagementTeam, String DialUserAdministration,String servicetype) throws InterruptedException, IOException, DocumentException {
 
		if(getwebelement(xml.getlocator("//locators/" + application + "/createorderservice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Servce Creation' page navigated as expected");
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
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Package' Dropdown is Enabled");
			System.out.println("Step : 'Package' Dropdown is Enabled");
		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
			System.out.println("Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
		}

		
		// management options- Manage Service Checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ManageServicecheckbox")).isEnabled()){
			if (ManageService.equalsIgnoreCase("YES")) {
				click(application, "'Manage Service' checkbox", "ManageServicecheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
				System.out.println("Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Manage Service' checkbox is not Selected");
				System.out.println("Step : 'Manage Service' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		
		
		//Syslog Event View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/SyslogEventViewcheckbox")).isEnabled()){
			if (SyslogEventView.equalsIgnoreCase("YES")) {
				click(application, "'Syslog Event View' checkbox", "SyslogEventViewcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is not Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		//Service Status View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ServiceStatusViewcheckbox")).isEnabled()){
			if (ServiceStatusView.equalsIgnoreCase("YES")) {
				click(application, "'Service Status View' checkbox", "ServiceStatusViewcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
				System.out.println("Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Service Status View' checkbox is not Selected");
				System.out.println("Step : 'Service Status View' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		//Router Configuration View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/RouterConfigurationViewcheckbox")).isEnabled()){
			if (RouterConfigurationView.equalsIgnoreCase("YES")) {
				click(application, "'Router Configuration View' checkbox", "RouterConfigurationViewcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is not Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
	
		scrolltoend();
			//Performance Reporting checkbox
				if(getwebelement(xml.getlocator("//locators/" + application + "/PerformanceReportingcheckbox")).isEnabled()){
					if (PerformanceReporting.equalsIgnoreCase("YES")) {
						click(application, "'Performance Reporting' checkbox", "PerformanceReportingcheckbox");
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
					} else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is not Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is not Selected");
					}
				
					}else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
						System.out.println("Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
					}
				

		
		
		
		//Dial User Administration checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/DialUserAdministrationcheckbox")).isEnabled()){
			if (DialUserAdministration.equalsIgnoreCase("YES")) {
				click(application, "'Dial User Administration' checkbox", "DialUserAdministrationcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is not Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
				System.out.println("Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
			}
		
		isDisplayed(application, "okbutton", "OK", xml);
		isDisplayed(application, "cancelbutton", "Cancel", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
			System.out.println(  e+ " : Field is not displayed in Service Creation page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
			System.out.println(  e+" : Field is not displayed in Service Creation page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Servce Creation' page not navigated");
			System.out.println("'Servce Creation' page navigated");
		}
				sa.assertAll();
	}
	
	
	
	public void verifyorderpanelinformation_Existingorder(String application, String existingorder,
			String expectedorderno, String expectedvoicelineno) throws InterruptedException, DocumentException {
		
		waitforPagetobeenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));//orderpanelheader//userspanel_header

		if (getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Order Panel' page navigated as expected");
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
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : order information is matched");
			} else {
				sa.fail("order information is not matched");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
				System.out.println(  e+ " : Field is not displayed in Service Creation page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
				System.out.println(  e+" : Field is not displayed in Service Creation page");
			}
			
		} else {

			System.out.println("existing order is not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : existing order is not selected");
		}

		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Order panel header is not displayed");
			System.out.println("Order panel header is not displayed");
		}
		
		//sa.assertAll();
	}

	
	
	
	public void verifyorderpanelinformation_Neworder(String application, String neworder, String expectedneworderno,
			String expectednewvoicelineno) throws InterruptedException, DocumentException {
		implicitlyWait("Service screen");
		webdriverWait(application, "userspanel_header", xml);
		waitforPagetobeenable();
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
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Order page");
			System.out.println(  e+ " : Field is not displayed in  View Order page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Order page ");
			System.out.println(  e+" : Field is not displayed in View Order page");
		}

		sa.assertAll();
	}


	
	public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno) throws InterruptedException, DocumentException, IOException {
		
		
		waitforPagetobeenable();
		ScrolltoElement(application, "userspanel_header", xml);
try {
		//Cancel Edit order in Order panel
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		waitforPagetobeenable();
		compareText(application, "Edit Order", "editorderheader", "Edit Order", xml);
		

		WebElement EditOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/editorderno"));
		click_commonMethod(application, "Order Number", "editorderno", xml);
		
		Clear(EditOrderNo);
		
		addtextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);

		WebElement EditVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/editvoicelineno"));
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		
		Clear(EditVoiceLineNo);
		
		addtextFields_commonMethod(application, "Order Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		compareText(application, "Order Header", "orderpanelheader", "Order", xml);
		Log.info("Navigated to order panel in view service page");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		//Edit Order
		
		ScrolltoElement(application, "userspanel_header", xml);
		
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		waitforPagetobeenable();
		compareText(application, "Edit Order Header", "editorderheader", "Edit Order", xml);
		
		click_commonMethod(application, "Order Number", "editorderno", xml);
		
		cleartext(application, "Order Number", "editorderno");
		
		addtextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		
		cleartext(application, "RFI Voice Line Number", "editvoicelineno");
		
		addtextFields_commonMethod(application, "Order Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "OK", "editorder_okbutton", xml);
		waitforPagetobeenable();
		
		ScrolltoElement(application, "userspanel_header", xml);
		
		compareText(application, "Order Header", "orderpanelheader", "Order", xml);
		Log.info("Navigated to order panel in view service page");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		Log.info("------ Edit Order is successful ------");
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	System.out.println(  e+ " : Field is not displayed");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	System.out.println(  e+" : Field is not displayed");
}
sa.assertAll();
	}

public void verifyorderpanel_changeorder(String application, String ChangeOrder_newOrderNumber, String changevoicelineno, String changeOrderSelection_newOrder,
			String changeOrderSelection_existingOrder, String ChangeOrder_existingOrderNumber) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "userspanel_header", xml);
				
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Change Order' Functionality");
		
		if((changeOrderSelection_newOrder.equalsIgnoreCase("No")) && (changeOrderSelection_existingOrder.equalsIgnoreCase("No"))) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Change Order is not performed");
			Log.info("Change Order is not performed");
		}
		else if(changeOrderSelection_newOrder.equalsIgnoreCase("Yes")) {
			
			//Change Order
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			click_commonMethod(application, "Select order switch", "changeorder_selectorderswitch", xml);
			click_commonMethod(application, "Order Number", "changeordernumber", xml);
			waitforPagetobeenable();
			addtextFields_commonMethod(application, "Order Number", "changeordernumber", ChangeOrder_newOrderNumber, xml);
			click_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", xml);
			addtextFields_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", changevoicelineno, xml);
			click_commonMethod(application, "Create Order", "createorder_button", xml);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Order successfully  changed.");
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_newOrderNumber, xml);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
			Log.info("------ Change Order is successful ------");
		}
		else if(changeOrderSelection_existingOrder.equalsIgnoreCase("yes")) 
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Performing Change Order functionality");
			waitforPagetobeenable();
			ScrolltoElement(application, "userspanel_header", xml);
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			
				addDropdownValues_commonMethod(application, "Order/Contract Number (Parent SID)", "changeorder_chooseorderdropdown", ChangeOrder_existingOrderNumber, xml);
				
				click_commonMethod(application, "OK", "changeorder_okbutton", xml);
				waitforPagetobeenable();
				verifysuccessmessage(application, "Order successfully  changed.");
				ScrolltoElement(application, "userspanel_header", xml);
				compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_existingOrderNumber, xml);
				compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
				Log.info("------ Change Order is successful ------");
	
		}
		
	}
	
	
	

	public void verifyorderpanel_editorder_UI(String application, String editorderno, String editvoicelineno) throws InterruptedException, DocumentException, IOException {
		implicitlyWait("Service screen");
		webdriverWait(application, "userspanel_header", xml);
		waitforPagetobeenable();
		try {
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		if(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Order' panel navigated as expected in view service page");
			System.out.println("'Order' panel navigated as expected in view service page");
			
		//Cancel Edit order in Order panel
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Edit Order", "editorderlink");
		if(getwebelement(xml.getlocator("//locators/" + application + "/editorderheader")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Order' Page navigated as expected");
			System.out.println("'Edit Order' Page navigated as expected");
			isDisplayed(application, "editorderno", "Order/Contract Number (Parent SID)", xml);
			isDisplayed(application, "editvoicelineno", "RFI/RFQ/IP Voice Line Number", xml);
			isDisplayed(application, "okbutton", "OK", xml);
			isDisplayed(application, "cancelbutton", "Cancel", xml);
			click(application, "Cancel", "cancelbutton");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit Order' Page not navigated");
			System.out.println("'Edit Order' Page not navigated");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Order' panel not navigated in view service page");
			System.out.println("'Order' panel not navigated in view service page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		sa.assertAll();
	}

	
	
	public void verifyorderpanel_changeorder_UI(String application, String changeorderno, String changevoicelineno) throws InterruptedException, DocumentException, IOException {
		
		implicitlyWait("Service screen");
		webdriverWait(application, "userspanel_header", xml);
		waitforPagetobeenable();
		try {
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		

		if(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Order' panel navigated as expected in view service page");
			System.out.println("'Order' panel navigated as expected in view service page");
			
		//Cancel Change order in Order panel
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Change Order", "changeorderlink");	
		try {
		if(getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Change Order' Page navigated as expected");
			System.out.println("'Change Order' Page navigated as expected");
			
			isDisplayed(application, "RFIRFQIPVoiceLineNumberText", "RFI/RFQ/IP Voice Line Number", xml);
			isDisplayed(application, "changeorder_chooseorderdropdown", "Order/Contract Number (Parent SID)", xml);
			isDisplayed(application, "okbutton", "OK", xml);
			isDisplayed(application, "cancelbutton", "Cancel", xml);
			
			click(application, "Select order switch", "changeorder_selectorderswitch");
			
			isDisplayed(application, "editorderno", "Order/Contract Number (Parent SID)", xml);
			isDisplayed(application, "editvoicelineno", "RFI/RFQ/IP Voice Line Number", xml);
			click(application, "Cancel","cancelbutton");//"changeorder_cancelbutton");////body//button[span[contains(text(),'Cancel')]]
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Change Order' Page not navigated");
			System.out.println("'Change Order' Page not navigated");
		}
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
			}
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Order' Panel not navigated in view service page");
		System.out.println("'Order' Panel not navigated in view service page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		sa.assertAll();
	}
	
	
	
	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String ResellerCode ,
			String Remarks , String EmailService, String PhoneService) throws InterruptedException, DocumentException, IOException {
		waitforPagetobeenable();
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
		}
		
	}
	
	
	
	public void editService(String application, String ServiceIdentification, String ResellerCode,  String Remarks, String EmailService, String PhoneService, 
			String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView, String PerformanceReporting,
			String ProactiveNotification,
			String NotificationManagementTeam, String DialUserAdministration,String ServiceType,
			String EditServiceIdentification, String EditResellerCode,
			String EditRemarks, String EditEmailService, String EditPhoneService, 
			String EditManageService, String EditSyslogEventView, String EditServiceStatusView, String EditRouterConfigurationView, String EditPerformanceReporting,
			String EditProactiveNotification,
			String EditNotificationManagementTeam, String EditDialUserAdministration) throws InterruptedException, DocumentException, IOException {
		
		waitforPagetobeenable();
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		waitforPagetobeenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		
		try { 
		
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		waitforPagetobeenable();
		cleartext(application, "Remarks", "remarktextarea");
		EnterTextValue(application, EditRemarks, "Remarks", "remarktextarea");
		ScrolltoElement(application, "cancelbutton", xml);
		click(application, "Cancel", "cancelbutton");
		
		waitforPagetobeenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks);	
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
		}
		
		//Edit service
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		
		//service identification
		edittextFields_commonMethod(application,"Service Identification" , "serviceidentificationtextfield", ServiceIdentification, xml);
	
		//Service Type
		compareText_InViewPage(application, "Service Type", ServiceType, xml);
		
		//Remark	
		edittextFields_commonMethod(application, "Remark", "remarktextarea", Remarks, xml);
		
	
		ScrolltoElement(application, "OKbutton_ServiceCreation", xml);


	//Performance Reporting
		editcheckbox_commonMethod(application, PerformanceReporting, "PerformanceReportingcheckbox", "Performance Reporting", xml);
		
		
		ScrolltoElement(application, "OKbutton_ServiceCreation", xml);
		click(application, "OK", "OKbutton_ServiceCreation");
		waitforPagetobeenable();
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDetailsHeader")));
		if(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDetailsHeader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
//			compareText(application, "Service updated success message", "serviceupdate_successmsg", "Service successfully updated");	
			verifysuccessmessage(application, "Service successfully updated");
		}
		else
		{
			Log.info("Service not updated");
			System.out.println("Service not updated");
		}
		
		
		
		searchorder(application, ServiceIdentification);//ByMe
		//Verify all the links available in service actions list
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		waitforPagetobeenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		
		click(application, "Action dropdown", "serviceactiondropdown");
		compareText(application, "Edit Link", "EditLink", "Edit");
		compareText(application, "Delete Link", "DeleteLink", "Delete");
		compareText(application, "Show Infovista Report Link", "ShowInfovistaReportLink", "Show Infovista Report");
		compareText(application, "Show New Infovista Report Link", "ShowNewInfovistaReportLink", "Show New Infovista Report");
		compareText(application, "Manage Subnets Ipv6 Link", "ManageSubnetsIpv6Link", "Manage Subnets Ipv6");
		compareText(application, "Dump Link", "DumpLink", "Dump");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
		}
		
		//Service delete is performed in the last test case
	}

	
	public void verifyServicePanel_Actions_UI(String application, String sid, String ManageService, String SyslogEventView, String ServiceStatusView, String RouterConfigurationView, String PerformanceReporting, String ProactiveNotification,
			String NotificationManagementTeam, String DialUserAdministration,String servicetype) throws InterruptedException, IOException, DocumentException {
 
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		waitforPagetobeenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/EditService_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Service' page navigated as expected");
			System.out.println("'Edit Service' page navigated as expected");
		
		try {
		// service identification
		isDisplayed(application, "serviceidentificationtextfield", "Service Identification", xml);
		isDisplayed(application, "ResellerCodetextfield", "Reseller Code", xml);
		isDisplayed(application, "remarktextarea", "Remarks", xml);
		ScrolltoElement(application, "remarktextarea");
		isDisplayed(application, "emailtextfield", "Email", xml);
		isDisplayed(application, "phonecontacttextfield", "Phone Contact", xml);
		
		//Package
		if(getwebelement(xml.getlocator("//locators/" + application + "/PackageDropdownDisabled")).isEnabled()){
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Package' Dropdown is Enabled");
			System.out.println("Step : 'Package' Dropdown is Enabled");
		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
			System.out.println("Step : 'Package' Dropdown is Disabled, So can't make any changes in Dropdown status");
		}

		// management options- Manage Service Checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ManageServicecheckbox")).isEnabled()){
			if (ManageService.equalsIgnoreCase("YES")) {
				click(application, "'Manage Service' checkbox", "ManageServicecheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
				System.out.println("Step : 'Manage Service' checkbox is Enabled and 'Manage Service' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Manage Service' checkbox is not Selected");
				System.out.println("Step : 'Manage Service' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Manage Service' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		//Syslog Event View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/SyslogEventViewcheckbox")).isEnabled()){
			if (SyslogEventView.equalsIgnoreCase("YES")) {
				click(application, "'Syslog Event View' checkbox", "SyslogEventViewcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is Enabled and 'Syslog Event View' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is not Selected");
				System.out.println("Step : 'Syslog Event View' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Syslog Event View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		//Service Status View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/ServiceStatusViewcheckbox")).isEnabled()){
			if (ServiceStatusView.equalsIgnoreCase("YES")) {
				click(application, "'Service Status View' checkbox", "ServiceStatusViewcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
				System.out.println("Step : 'Service Status View' checkbox is Enabled and 'Service Status View' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Service Status View' checkbox is not Selected");
				System.out.println("Step : 'Service Status View' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Service Status View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
		
		
		//Router Configuration View checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/RouterConfigurationViewcheckbox")).isEnabled()){
			if (RouterConfigurationView.equalsIgnoreCase("YES")) {
				click(application, "'Router Configuration View' checkbox", "RouterConfigurationViewcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is Enabled and 'Router Configuration View' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is not Selected");
				System.out.println("Step : 'Router Configuration View' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
				System.out.println("Step : 'Router Configuration View' checkbox is Disabled, So can't make any changes in Checkbox status");
			}
	
			scrolltoend();
			//Performance Reporting checkbox
				if(getwebelement(xml.getlocator("//locators/" + application + "/PerformanceReportingcheckbox")).isEnabled()){
					if (PerformanceReporting.equalsIgnoreCase("YES")) {
						click(application, "'Performance Reporting' checkbox", "PerformanceReportingcheckbox");
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is Enabled and 'Performance Reporting' checkbox Selected");
					} else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is not Selected");
						System.out.println("Step : 'Performance Reporting' checkbox is not Selected");
					}
				
					}else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
						System.out.println("Step : 'Performance Reporting' checkbox is Disabled, So can't make any changes in Checkbox status");
					}
				

		
		
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
		
		//Dial User Administration checkbox
		if(getwebelement(xml.getlocator("//locators/" + application + "/DialUserAdministrationcheckbox")).isEnabled()){
			if (DialUserAdministration.equalsIgnoreCase("YES")) {
				click(application, "'Dial User Administration' checkbox", "DialUserAdministrationcheckbox");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is Enabled and 'Dial User Administration' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is not Selected");
				System.out.println("Step : 'Dial User Administration' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
				System.out.println("Step : 'Dial User Administration' checkbox is Disabled, SO can't make any changes in Checkbox status");
			}
		
		compareText(application, "OK", "okbutton", "OK", xml);
		compareText(application, "Cancel", "cancelbutton", "Cancel", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
			System.out.println(  e+ " : Field is not displayed in Service Creation page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
			System.out.println(  e+" : Field is not displayed in Service Creation page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit Service' page not navigated");
			System.out.println("'Edit Service' page navigated");
		}
		
	}

	
	
	
	
	public void manageServiceFunctionTest(String application,String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, 
			String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		
		try {
			waitforPagetobeenable();
		//Manage service
				
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
				click(application, "Action dropdown", "serviceactiondropdown");
				click(application, "Manage", "manageLink");
				waitforPagetobeenable();
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Manage Service page");
			System.out.println(  e+ " : Field is not displayed in Manage Service page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Manage Service page ");
			System.out.println(  e+" : Field is not displayed in Manage Service page");
		}
		
	}

	
	
	
	
	public void manageServiceFunctionTest_UI(String application,String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, 
			String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		
		try {
		//Manage service
				implicitlyWait("Service screen");
				webdriverWait(application, "orderpanelheader", xml);
				waitforPagetobeenable();
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Manage Service page");
			System.out.println(  e+ " : Field is not displayed in Manage Service page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Manage Service page ");
			System.out.println(  e+" : Field is not displayed in Manage Service page");
		}
		
	}
	
	
	public void choosecustomertocreateorder(String application, String selectCustomer, String name)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createorderlink")));
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Create Order/Service Link");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/createordernametextfield")), name);
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Name is entered in name textfield");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/chooseCustomerdropdown")));
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Customer Dropdown");

		WebElement el = driver
				.findElement(By.xpath("//div[@role='list']//span[contains(text(),'" + selectCustomer + "')]"));
		String selectedcustomer = el.getText();
		el.click();
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer is selected from dropdown" + selectedcustomer);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Next button");

	}
	
	
	
	//////////////////////////////////////  MAS SWITCH  ////////////////////////
	//////////////////////////////////////  MAS SWITCH  ////////////////////////
	
	
	
	
	
	public void verifyAddMASswitch(String application, String MAS_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {
try {
		WebElement ProviderEquipment_header= getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header"));
		scrolltoview(ProviderEquipment_header);
		click(application, "Add MAS Switch Link", "MAS_AddMASSwitchLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_AddMASSwitch_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add MAS Switch' page navigated as expected");
			System.out.println("'Add MAS Switch' page navigated as expected");
			
		try {
		compareText(application, "Add MAS Switch header", "MAS_AddMASSwitch_header", "Add MAS Switch");

		click(application, "OK button", "MAS_OKbutton");//
		compareText(application, "Add Switch Warning Message for MAS Switch", "MAS_AddSwitchWarningMessage", "IMS POP Location is required");//

		addDropdownValues_commonMethod(application, "IMS POP Location", "MAS_IMSPOPLocationDropdown", MAS_IMSPOPLocation, xml);
		click(application, "OK button", "MAS_OKbutton");
		compareText(application, "Add Switch Successful Message for MAS Switch", "MAS_AddSwitchSuccessfulMessage", "MAS switch added successfully");
		ExtentTestManager.getTest().log(LogStatus.PASS, "MAS switch added successfully");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add MAS Switch page");
			System.out.println(  e+ " : Field is not displayed in Add MAS Switch page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add MAS Switch page ");
			System.out.println(  e+" : Field is not displayed in Add MAS Switch page");
		}

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add MAS Switch' page not navigated");
			System.out.println("'Add MAS Switch' page not navigated");
		}
		
		
		Log.info("------ MAS Switch added successfully ------");
		
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	System.out.println(  e+ " : Field is not displayed");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	System.out.println(  e+" : Field is not displayed");
}
sa.assertAll();
	}

	
	
	public void verifyAddMASswitch_UI(String application, String MAS_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {
		waitforPagetobeenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_header")));//portalaccess_header//ProviderEquipment_header
		click(application, "Add MAS Switch Link", "MAS_AddMASSwitchLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_AddMASSwitch_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add MAS Switch' page navigated as expected");
			System.out.println("'Add MAS Switch' page navigated as expected");
			
		try {

		click(application, "OK button", "MAS_OKbutton");//
		compareText(application, "Add Switch Warning Message for MAS Switch", "MAS_AddSwitchWarningMessage", "IMS POP Location is required");//
		isDisplayed(application, "MAS_IMSPOPLocationDropdown", "IMS POP Location", xml);
		click(application, "Cancel", "cancelbutton");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add MAS Switch page");
			System.out.println(  e+ " : Field is not displayed in Add MAS Switch page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add MAS Switch page ");
			System.out.println(  e+" : Field is not displayed in Add MAS Switch page");
		}

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add MAS Switch' page not navigated");
			System.out.println("'Add MAS Switch' page not navigated");
		}
		
		
	}

	
	public void verifyAddedMASswitchInformation_View(String application, String MAS_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View MAS Switch' page navigated as expected");
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View MAS Switch page");
			System.out.println(  e+ " : Field is not displayed in View MAS Switch page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View MAS Switch page ");
			System.out.println(  e+" : Field is not displayed in View MAS Switch page");
		}

		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View MAS Switch' page not navigated");
			System.out.println("'View MAS Switch' page not navigated");
		}
		
		
		Log.info("------ Verified Added MAS Switch Information successfully ------");
	}

	
	
	
public void testStatus_MAS(String application) throws InterruptedException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Test Status' table");
		
		String element=null;
		String status=null;
		
		List<WebElement> testlist=getwebelements("//tbody/tr");
		int listSize=testlist.size();
		
		
		for(int i=1; i<=listSize; i++) {
		  try {	
			element=getwebelement("(//tbody/tr["+ i +"]/td)[1]").getText();
			
			if(element.isEmpty()) {
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Test Name is displaying as: "+element);
				System.out.println("Test Name is displaying as: "+element);
				
				
				status=getwebelement("(//tbody/tr["+ i +"]/td)[2]/div").getAttribute("class");
				System.out.println("status displays as: "+status);
				
				if(status.contains("red")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: red");
					System.out.println(element + " status colour dipslays as: red");
				}
				else if(status.contains("green")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: green");
					System.out.println(element + " status colour dipslays as: green");
				}
			}
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		}
	}

	
	
	public void verifyAddedMASswitchInformation_View_UI(String application, String MAS_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View MAS Switch' page navigated as expected");
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View MAS Switch page");
			System.out.println(  e+ " : Field is not displayed in View MAS Switch page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View MAS Switch page ");
			System.out.println(  e+" : Field is not displayed in View MAS Switch page");
		}

		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View MAS Switch' page not navigated");
			System.out.println("'View MAS Switch' page not navigated");
		}
		
	}

	
	
	public void verifyEditMASswitchFunction(String application, String ServiceIdentification,  String MAS_DeviceName_Edit, String MAS_VendorModelEdit, String MAS_ManagementAddressEdit, String MAS_SnmproEdit, String MAS_CountryEdit, String MAS_CityEdit, String MAS_SiteEdit, String MAS_PremiseEdit) throws InterruptedException, DocumentException, IOException {

		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View MAS Device' page navigated as expected");
			System.out.println("'View MAS Device' page navigated as expected");
			
			click(application, "ACTION link", "MAS_View_ActionLink");
			click(application, "Edit Link", "MAS_View_Action_EditLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/Edit_MASSwitch_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit MAS Device' page navigated as expected");
			System.out.println("'Edit MAS Device' page navigated as expected");
			
			try { 	
			
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Edit_PremiseLevel")));
		click(application, "OK in Edit MAS Device", "MAS_OKbutton");
	 waitforPagetobeenable();
//		compareText(application, "MAS Switch Update message", "MAS_UpdateSwitchSuccessfulMessage", "MAS switch updated successfully");
		verifysuccessmessage(application, "MAS switch updated successfully");
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit MAS Device page");
			System.out.println(  e+ " : Field is not displayed in Edit MAS Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Edit MAS Device page ");
			System.out.println(  e+" : Field is not displayed in Edit MAS Device page");
		}

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit MAS Switch' page not navigated");
			System.out.println("'Edit MAS Switch' page not navigated");	
		}
		
		Log.info("------  MAS switch updated successfully   ------");
		
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View MAS Device' page not navigated");
			System.out.println("'View MAS Device' page navigated");
		}
		
		
	}

	
	
	
	public void verifyEditMASswitch_UI(String application, String ServiceIdentification,  String MAS_DeviceName_Edit, String MAS_VendorModelEdit, String MAS_ManagementAddressEdit, String MAS_SnmproEdit, String MAS_CountryEdit, String MAS_CityEdit, String MAS_SiteEdit, String MAS_PremiseEdit) throws InterruptedException, DocumentException, IOException {
		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View MAS Device' page navigated as expected");
			System.out.println("'View MAS Device' page navigated as expected");
			
			click(application, "ACTION link", "MAS_View_ActionLink");
			click(application, "Edit Link", "MAS_View_Action_EditLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/Edit_MASSwitch_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit MAS Device' page navigated as expected");
			System.out.println("'Edit MAS Device' page navigated as expected");
			
			try { 	
			
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Edit_PremiseLevel")));
		click(application, "Cancel", "MAS_Cancelbutton");
		waitforPagetobeenable();		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit MAS Device page");
			System.out.println(  e+ " : Field is not displayed in Edit MAS Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Edit MAS Device page ");
			System.out.println(  e+" : Field is not displayed in Edit MAS Device page");
		}

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit MAS Switch' page not navigated");
			System.out.println("'Edit MAS Switch' page not navigated");	
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View MAS Device' page not navigated");
			System.out.println("'View MAS Device' page navigated");
		}
	}
	
	
	
	public void veriyFetchDeviceInterfacesFunction_MAS(String application,String ServiceIdentification, String MAS_ServiceStatusChangeRequired) throws InterruptedException, DocumentException, IOException {
		scrollToTop();
		if (getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View MAS Device' page navigated as expected");
			System.out.println("'View MAS Device' page navigated as expected");
		
		click(application, "ACTION link", "MAS_View_ActionLink");
		click(application, "Fetch Device Interfaces Link", "MAS_View_Action_FetchDeviceInterfacesLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_FetchDeviceInterfacesSuccessMessage")).isDisplayed()){
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
			System.out.println("Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			System.out.println("Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
		}
		
		click(application, "Click here Link for MAS", "MAS_hereLink_UnderFetchDeviceInterfacesSuccessMessage");
		
		
		//Manage COLT's Network - Manage Network
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_ManageCOLTsNetworkManageNetwork_header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Manage COLT's Network - Manage Network' page navigated as expected");
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
//			compareText(application, "Synchronize Success Message", "MAS_Manage_SynchronizeSuccessMessage", "Sync started successfully. Please check the sync status of this device.");
				verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this device.");

			Log.info("------  Sync started successfully. Please check the sync status of this device   ------");
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Manage Colt Network page");
			System.out.println(  e+ " : Field is not displayed in Manage Colt Network page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Manage Colt Network page ");
			System.out.println(  e+" : Field is not displayed in Manage Colt Network page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage COLT's Network - Manage Network' page not navigated");
			System.out.println("'Manage COLT's Network - Manage Network' page not navigated");
		}
	
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View MAS Device' page not navigated");
			System.out.println("'View MAS Device' page not navigated");
			}
		
	}
	
	
	
	
	
	////
	
	public void verifyRouterToolFunction_MAS(String application,String ServiceIdentification, String MAS_CommandIPV4, String MAS_CommandIPV6, String MAS_ManagementAddress) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		
		WebElement serviceIdLink=getwebelement("//span[text()='"+ ServiceIdentification +"']");
		Clickon(serviceIdLink);
		waitforPagetobeenable();


		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header")));
		click(application, "View Link", "MAS_viewdevice1");
		waitforPagetobeenable();
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
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View MAS Device' page navigated as expected");
			System.out.println("'View MAS Device' page navigated as expected");
			
		click(application, "ACTION link", "MAS_View_ActionLink");
		click(application, "Delete Device from View Device page", "MAS_View_Action_DeleteLink");
		compareText(application, "Delete MAS Device Warning Message from View Device page", "MAS_ViewDevice_Action_DeleteMASDeviceWarningMessage", "Are you sure that you want to delete this item?");
		click(application, "Delete Button", "MAS_ViewDevice_Action_DeleteButton");
		compareText(application, "Delete MAS Device Successful Message", "MAS_DeleteSwitchSuccessfulMessage", "MAS switch deleted successfully");
		
		
		//Delete from Service "From View Service page"
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/portalaccess_header")));
		click(application, "Delete From Service Link", "MAS_deletefromservicedevice1");
		compareText(application, "Delete MAS Device Warning Message from View Service page", "MAS_ViewService_DeleteMASDeviceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "MAS_ViewService_DeleteButton");
		compareText(application, "Delete MAS Device Successfull Message", "MAS_DeleteSwitchSuccessfulMessage", "MAS switch deleted successfully");
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View MAS Device' page not navigated");
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
			ExtentTestManager.getTest().log(LogStatus.PASS, "'MAS Add Interface' page navigated as expected");
			System.out.println("'MAS Add Interface' page navigated as expected");
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
		
		try {
		click(application, "OK Button", "MAS_PE_OKButton");
		
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface is required", "MAS_PE_InterfaceWarningMessage");
 
		
		
		if(MAS_AccessMedia.equalsIgnoreCase("VPN")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMedia);
			
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
			
			
//			//IV Management Checkbox
//			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")).isEnabled()){
//				if (MAS_IVManagement.equalsIgnoreCase("YES")) {
//					click(application, "'IV Management' checkbox", "MAS_PE_IVManagementCheckbox");
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//				} else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
//					System.out.println("Step : 'IV Management' checkbox is not Selected");
//				}
//			
//				}else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}

			addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", MAS_IVManagement, "No", xml);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			
			

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
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces sucessfully added by selecting VPN as Access Media");
			
		}else if(MAS_AccessMedia.equalsIgnoreCase("EPN")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMedia);

			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectMAS_AccessMediaDropdownValue=getwebelement("//div[text()='"+MAS_AccessMedia+"']");
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
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//				} else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
//					System.out.println("Step : 'IV Management' checkbox is not Selected");
//				}
//			
//				}else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}

			addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", MAS_IVManagement, "No", xml);
		
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			
			
			EnterTextValue(application, MAS_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")));
			
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ MAS_GenerateConfiguration +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);

			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces sucessfully added by selecting EPN as Access Media");
			Log.info("------ Interface Added successfully ------");
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Interface page");
			System.out.println(  e+ " : Field is not displayed in  Add Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in  Add Interface page ");
			System.out.println(  e+" : Field is not displayed in  Add Interface page");
		}

		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'MAS Add Interface' page not navigated");
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
		
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "MASSwitch_ACTION_Device1"); 
		click(application, "ACTION MAS Switch", "MAS_PE_ACTION_EditLink_Device1"); 
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_EditInterface_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'MAS Edit Interface' page navigated as expected");
			System.out.println("'MAS Edit Interface' page navigated as expected");
			
		try {
		if(MAS_AccessMediaEdit.equalsIgnoreCase("VPN")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMediaEdit);

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


			editcheckbox_commonMethod(application, MAS_IVManagementEdit, "MAS_PE_IVManagementCheckbox", "IV Management", xml);
		

			
			scrolltoend();
			//**EnterTextValue(application, MAS_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ MAS_GenerateConfigurationEdit +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);

			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
//			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			verifysuccessmessage(application, "Interface updated successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces sucessfully updated by selecting VPN as Access Media");
			
		
		}else if(MAS_AccessMediaEdit.equalsIgnoreCase("EPN")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMediaEdit);
			
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
			
			scrolltoend();
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ MAS_GenerateConfigurationEdit +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
//			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			verifysuccessmessage(application, "Interface updated successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces sucessfully updated by selecting EPN as Access Media");
			Log.info("------ Interface Updated successfully ------");
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
	
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Interface page");
			System.out.println(  e+ " : Field is not displayed in  Edit Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in  Edit Interface page ");
			System.out.println(  e+" : Field is not displayed in  Edit Interface page");
		}

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'MAS Edit Interface' page not navigated");
			System.out.println("'MAS Edit Interface' page not navigated");
		}
		
		sa.assertAll();
		
	}
		
		

	

	
	public void verifyAddInterfaceFunction_MAS_UI(String application, String MAS_AccessMedia, String MAS_Network,
			String MAS_HSRPBGP, String MAS_GenerateConfiguration, String	MAS_Interface, String MAS_InterfaceAddressRange,
			String MAS_InterfaceAddressMask, String	MAS_HSRPIP, String	MAS_InterfaceAddressRangeIPV6, String MAS_HSRPIPv6Address, 
			String	MAS_PrimaryIPv6onMas1, String MAS_SecondaryIPv6onMas2, String MAS_GroupNumber, String MAS_Link, String MAS_VLANID,
			String	MAS_IVManagement, String MAS_Configuration, String MAS_HSRPTrackInterface, String MAS_HSRPAuthentication) throws InterruptedException, DocumentException, IOException {
		waitforPagetobeenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_CommandIPV4_header")));//RouterTool_header//MAS_PE_CommandIPV4_header//MAS_PE_InterfacesPanel_header
		click(application, "ACTION Link", "MAS_View_InterfacesActionLink");
		click(application, "Add Interface Link", "MAS_PE_AddInterfaceLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_AddInterface_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'MAS Add Interface' page navigated as expected");
			System.out.println("'MAS Add Interface' page navigated as expected");
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
		
		try {
		click(application, "OK Button", "MAS_PE_OKButton");
		
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface is required", "MAS_PE_InterfaceWarningMessage");
 
		
		
		if(MAS_AccessMedia.equalsIgnoreCase("VPN")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMedia);
			
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
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+MAS_AccessMedia);

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
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Interface page");
			System.out.println(  e+ " : Field is not displayed in  Add Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in  Add Interface page ");
			System.out.println(  e+" : Field is not displayed in  Add Interface page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'MAS Add Interface' page not navigated");
			System.out.println("'MAS Add Interface' page not navigated");
		}
		sa.assertAll();
		
	}
	
	
	
	
	
	
	public void verifyConfigureInterfaceFunction_MAS(String application, String ServiceIdentification, String MAS_GenerateConfiguration, String MAS_Configuration) throws InterruptedException, DocumentException, IOException {
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/portalaccess_header")));//ProviderEquipment_header//portalaccess_header//managementoptions_header
		//***click(application, "Show Interfaces Link", "MAS_ShowInterfaceLink");
		
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "MASSwitch_ACTION_Device1"); 
		click(application, "Configure interface", "MAS_PE_ACTION_ConfigureLink_Device1"); 
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configure_ConfigureInterface_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'MAS Select Interface' page navigated as expected");
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
		
		
		SelectDropdownValueUnderSelectTag(application, "Configuration", MAS_GenerateConfiguration, "MAS_PE_Configure_GenerateConfigurationDropdown", xml);
		
		compareText(application, "Generate Configuration for All CPE Routes Link", "MAS_PE_Configure_GenerateConfigurationForAllCPERoutesButton", "Generate Configuration for All CPE Routes");
		compareText(application, "Save Configuration Link", "MAS_PE_Configure_SaveConfigurationButton", "Save Configuration");
		compareText(application, "Execute Configuration on Device Link", "MAS_PE_Configure_ExecuteConfigurationonDeviceButton", "Execute Configuration on Device");
		GetText(application, "Configuration information after configuration generated", "MAS_PE_Configure_ConfigurationTextfield");
		Log.info("------ Verified Configure Interfaces Information successfully ------");
		
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Select Interface page");
				System.out.println(  e+ " : Field is not displayed in  Select Interface page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in  Select Interface page ");
				System.out.println(  e+" : Field is not displayed in  Select Interface page");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'MAS Select Interface' page not navigated");
			System.out.println("'MAS Select Interface' page not navigated");
		}
	}
	
	
	
	
	
	
	
	
	
	public void verifyDeleteInterfaceFunction_MAS(String application, String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/portalaccess_header")));//ProviderEquipment_header//portalaccess_header//managementoptions_header
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "MASSwitch_ACTION_Device1"); 
		click(application, "Delete interface link", "MAS_PE_ACTION_ResetLink_Device1"); 
		
		compareText(application, "Delete Interface Warning Message from View Service page", "MAS_PE_DeleteInterfaceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "MAS_PE_DeleteButton");
		waitforPagetobeenable();
		compareText(application, "Delete Interface Successfull Message", "MAS_PE_DeleteInterfaceSuccessfullMessage", "Interface successfully removed from this service.");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////  PE DEVICE PANEL  ///////////////////////////////////////
	
	public void verifyAddPEDevice(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {
try {
		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		click(application, "Add PE Device Link", "PE_AddPEDeviceLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/PE_AddPEDevice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add PE Device' page navigated as expected");
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add PE Device page");
			System.out.println(  e+ " : Field is not displayed in Add PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add PE Device page ");
			System.out.println(  e+" : Field is not displayed in Add PE Device page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add PE Device' page not navigated");
			System.out.println("'Add PE Device' page not navigated");
		}
		
		Log.info("------ PE Device added successfully ------");
		
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	System.out.println(  e+ " : Field is not displayed");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	System.out.println(  e+" : Field is not displayed");
}
sa.assertAll();
	}

	
	
	
	public void verifyAddPEDevice_UI(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		click(application, "Add PE Device Link", "PE_AddPEDeviceLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/PE_AddPEDevice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add PE Device' page navigated as expected");
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add PE Device page");
			System.out.println(  e+ " : Field is not displayed in Add PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add PE Device page ");
			System.out.println(  e+" : Field is not displayed in Add PE Device page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add PE Device' page not navigated");
			System.out.println("'Add PE Device' page not navigated");
		}
		
		Log.info("------ PE Device added successfully ------");
	}
	
	
	public void navigateToViewDevicePage_PE(String application, String PE_DeviceName) throws InterruptedException, DocumentException {
		try {
		ScrolltoElement(application, "MAS_AddMASSwitchLink", xml);//TrunkGroupSiteOrders_header //ProviderEquipment_header //MASswitch_header//PE_AddPEDeviceLink
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingPEdevicegrid")).isDisplayed())
		{
			click_commonMethod(application, "View Link", "PE_viewdevice1", xml);

			if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
				System.out.println("'View PE Device' page navigated as expected");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
				System.out.println("'View PE Device' page not navigated");
			}
			
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
	}
	
	
	
	
	public void navigateToViewDevicePage_PE_UI(String application, String PE_DeviceName) throws InterruptedException, DocumentException {
		try {
		ScrolltoElement(application, "MAS_AddMASSwitchLink", xml);//TrunkGroupSiteOrders_header //ProviderEquipment_header //MASswitch_header//PE_AddPEDeviceLink
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingPEdevicegrid")).isDisplayed())
		{
			click_commonMethod(application, "View Link", "PE_viewdevice1", xml);
			compareText(application, "View device header", "PE_ViewDevice_Header", "Device", xml);
				
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}

		
	}
	
	
	
	public void verifyAddedPEDeviceInformation_View(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View PE Device page");
			System.out.println(  e+ " : Field is not displayed in View PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View PE Device page ");
			System.out.println(  e+" : Field is not displayed in View PE Device page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page not navigated");
		}
		
		Log.info("------ Verified Added PE Device Information successfully ------");
	}
	
	
	
public void testStatus_PE(String application) throws InterruptedException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Test Status' table");
		
		String element=null;
		String status=null;
		
		List<WebElement> testlist=getwebelements("//tbody/tr");
		int listSize=testlist.size();
		
		
		for(int i=1; i<=listSize; i++) {
		  try {	
			element=getwebelement("(//tbody/tr["+ i +"]/td)[1]").getText();
			
			if(element.isEmpty()) {
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Test Name is displaying as: "+element);
				System.out.println("Test Name is displaying as: "+element);
				
				
				status=getwebelement("(//tbody/tr["+ i +"]/td)[2]/div").getAttribute("class");
				System.out.println("status displays as: "+status);
				
				if(status.contains("red")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: red");
					System.out.println(element + " status colour dipslays as: red");
				}
				else if(status.contains("green")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: green");
					System.out.println(element + " status colour dipslays as: green");
				}
			}
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		}
	}


	
	public void verifyAddedPEDeviceInformation_View_UI(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

	if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
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
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View PE Device page");
		System.out.println(  e+ " : Field is not displayed in View PE Device page");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View PE Device page ");
		System.out.println(  e+" : Field is not displayed in View PE Device page");
	}
	
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
		System.out.println("'View PE Device' page not navigated");
	}
	
}

	
	
	public void verifyEditPEDeviceFunction(String application, String ServiceIdentification,  String PE_DeviceName_Edit, String PE_VendorModelEdit,
			String PE_ManagementAddressEdit, String PE_SnmproEdit, String PE_CountryEdit,
			String editExistingCity, String editNewCity, String editExistingCityValue, String editNewCityName, String editNewCityCode,
			String editExistingSite, String editNewSite, String editExistingSiteValue, String editNewSiteName, String editNewSiteCode,
			String editExistingPremise, String editNewPremise, String editExistingPremiseValue, String editNewPremiseName, String editNewPremiseCode
			) throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit PE Device' Functionality");
		
		try {
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
			click(application, "ACTION link", "PE_View_ActionLink");
			click(application, "Edit Link", "PE_View_Action_EditLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/Edit_PEDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit PE Device' page navigated as expected");
			System.out.println("'Edit PE Device' page navigated as expected");
			
			try { 	

				///////////////////////////////
			//Device name	
			//edittextFields_commonMethod(application, "Device name", "MAS_PE_Edit_deviceName", PE_DeviceName_Edit, xml);
				
			//vendor/model	
				//SelectDropdownValueUnderSpanTag(application, "Vendor/Model", PE_VendorModelEdit, "MAS_PE_Edit_vendorModel", "commonDropdownValueTag", xml);
				
			//Management Address	
				//edittextFields_commonMethod(application, "Management Address", "MAS_PE_Edit_managementAddress", PE_ManagementAddressEdit, xml);
				
			//Snmrpo	
				//edittextFields_commonMethod(application, "Snmpro", "MAS_PE_Edit_snmpro", PE_SnmproEdit, xml);
			
				scrolltoend();
				
				
				scrolltoend();
				
				//Country
						if(!PE_CountryEdit.equalsIgnoreCase("Null")) {
							
//							addDropdownValues_commonMethod(application, "Country", "countryinput", editCountry, xml);
							selectValueInsideDropdown(application, "countryinput", "Country", PE_CountryEdit, xml);
							
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
								
							   //**addDropdownValues_commonMethod(application, "City", "citydropdowninput", editExistingCityValue, xml);
							   //**selectValueInsideDropdown(application, "citydropdowninput", "City", editExistingCityValue, xml);
							   SelectDropdownValueUnderSelectTag(application, "City", editExistingCityValue, "citydropdowninput", xml);
								
								
							 //Existing Site
								  if(editExistingSite.equalsIgnoreCase("yes") & editNewSite.equalsIgnoreCase("no")) {
									  //addDropdownValues_commonMethod(application, "Site", "sitedropdowninput", editExistingSiteValue, xml);
									 //** selectValueInsideDropdown(application, "sitedropdowninput", "Site", editExistingSiteValue, xml);
									  SelectDropdownValueUnderSelectTag(application, "Site", editExistingSiteValue, "sitedropdowninput", xml);
									  
								 //Existing Premise
									  if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("no")) {
										  //addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", editExistingPremiseValue, xml);
										 //** selectValueInsideDropdown(application, "premisedropdowninput", "Premise", editExistingPremiseValue, xml);
										  SelectDropdownValueUnderSelectTag(application, "Premise", editExistingPremiseValue, "premisedropdowninput", xml);
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
						else if(PE_CountryEdit.equalsIgnoreCase("Null")) {
							
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
				click(application, "OK in Edit PE Device", "PE_OKbutton");
				waitforPagetobeenable();
//				compareText(application, "PE Device Update message", "PE_UpdatePEDeviceSuccessfulMessage", "PE Device updated successfully");
				verifysuccessmessage(application, "PE Device updated successfully");
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit PE Device page");
			System.out.println(  e+ " : Field is not displayed in Edit PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Edit PE Device page ");
			System.out.println(  e+" : Field is not displayed in Edit PE Device page");
		}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit PE Device' page not navigated");
				System.out.println("'Edit PE Device' page not navigated");	
			}
			
			Log.info("------  PE Device updated successfully   ------");
		
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page navigated");
		}
		
		 }catch(NoSuchElementException e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		    	System.out.println(  e+ " : Field is not displayed");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		    	System.out.println(  e+" : Field is not displayed");
		    }
		    sa.assertAll();
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
			System.out.println("No chnges made under 'City' field");
		}
		
	}


	public void existingCity(String application, String dropdown_xpath, String dropdownValue, String selectCityToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
		
		boolean cityDisplayed=false;
	try {	
		cityDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
		
		if(cityDisplayed) {
			
			selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
			
		}else {
			
			click_commonMethod(application, "Select City toggle button", selectCityToggleButton, xml);
			
			
			selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
		
		click_commonMethod(application, "Select City toggle button", selectCityToggleButton, xml);
		
		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	}
		
	}


	public void newCity(String application, String dropdown_xpath, String addCitytoggleButton, String editNewCityName,
			String editNewCityCode, String labelname) throws InterruptedException, DocumentException, IOException {
		
		boolean cityDisplayed=false;
	try {	
		cityDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
		
		if(cityDisplayed) {
			
			click_commonMethod(application, "Select City toggle button", addCitytoggleButton, xml);
			
			
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
			System.out.println("No changes made under 'Site' field");
			
		}
		
	}

	public void existingSite(String application, String dropdown_xpath, String dropdownValue, String selectSiteToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
		
		boolean siteDisplayed=false;
	try {	
		siteDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
		
		if(siteDisplayed) {
			
			selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
			
		}else {
			
			click_commonMethod(application, "Select Site toggle button", selectSiteToggleButton, xml);
			
			
			selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
		
		click_commonMethod(application, "Select Site toggle button", selectSiteToggleButton, xml);
		
		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	}
		
	}


	public void newSite(String application, String dropdown_xpath, String addSitetoggleButton, String editNewSiteName,
			String editNewSiteCode, String labelname) throws InterruptedException, DocumentException, IOException {
		
		boolean siteDisplayed=false;
	try {	
		siteDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
		
		if(siteDisplayed) {
			
			click_commonMethod(application, "Select City toggle button", addSitetoggleButton, xml);
			
			
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
			System.out.println("No changes made under 'Premise' field");
			
		}
		
	}

	public void existingPremise(String application, String dropdown_xpath, String dropdownValue, String selectPremiseToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
		
		boolean premiseDisplayed=false;
	try {	
		premiseDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
		
		if(premiseDisplayed) {
			
			selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
			
		}else {
			
			click_commonMethod(application, "Select Premise toggle button", selectPremiseToggleButton, xml);
			
			
			selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
		
		click_commonMethod(application, "Select Premise toggle button", selectPremiseToggleButton, xml);
		
		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
	}
		
	}


	public void newPremise(String application, String dropdown_xpath, String addPremisetoggleButton, String editNewPremiseName,
			String editNewPremiseCode, String labelname) throws InterruptedException, DocumentException, IOException {
		
		boolean premiseDisplayed=false;
	try {	
		premiseDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
		
		if(premiseDisplayed) {
			
			click_commonMethod(application, "Select Premise toggle button", addPremisetoggleButton, xml);
			
			
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
	
public void verifyEditPEDeviceFunction_UI(String application, String ServiceIdentification,  String PE_DeviceName_Edit, String PE_VendorModelEdit, String PE_ManagementAddressEdit, String PE_SnmproEdit, String PE_CountryEdit, String PE_CityEdit, String PE_SiteEdit, String PE_PremiseEdit) throws InterruptedException, DocumentException, IOException {
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
			click(application, "ACTION link", "PE_View_ActionLink");
			click(application, "Edit Link", "PE_View_Action_EditLink");
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/Edit_PEDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit PE Device' page navigated as expected");
			System.out.println("'Edit PE Device' page navigated as expected");
			
			try { 	
			
				scrolltoend();
				click(application, "Cancel", "MAS_Cancelbutton");
				waitforPagetobeenable();
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit PE Device page");
			System.out.println(  e+ " : Field is not displayed in Edit PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Edit PE Device page ");
			System.out.println(  e+" : Field is not displayed in Edit PE Device page");
		}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit PE Device' page not navigated");
				System.out.println("'Edit PE Device' page not navigated");	
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page navigated");
		}
	}


	
public void verifFetchDeviceInterfacesFunction_PE(String application,String ServiceIdentification, String PE_ServiceStatusChangeRequired) throws InterruptedException, DocumentException, IOException {
try {
	
	scrollToTop();
	if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
		System.out.println("'View PE Device' page navigated as expected");
	
		click(application, "ACTION link", "PE_View_ActionLink");
		click(application, "Fetch Device Interfaces Link", "PE_View_Action_FetchDeviceInterfacesLink");
	
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_FetchDeviceInterfacesSuccessMessage")).isDisplayed()){
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
			System.out.println("Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			System.out.println("Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
		}
		
		
		
		click(application, "Click here Link for PE", "PE_hereLink_UnderFetchDeviceInterfacesSuccessMessage");
		
		
		//Manage COLT's Network - Manage Network
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ManageCOLTsNetworkManageNetwork_header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Manage COLT's Network - Manage Network' page navigated as expected");
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
			click(application, "Close", "PE_servicestatus_popupclose");
		}

		
		
		////synchronize panel in manage colt page
			scrolltoend();
			click(application, "Synchronize", "PE_Manage_Synchronization_SynchronizeLink");
//			compareText(application, "Synchronize Success Message", "PE_Manage_SynchronizeSuccessMessage", "Sync started successfully. Please check the sync status of this device.");
			verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this device.");	
			Log.info("------  Sync started successfully. Please check the sync status of this device   ------");

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Manage Colt Network page");
			System.out.println(  e+ " : Field is not displayed in Manage Colt Network page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Manage Colt Network page ");
			System.out.println(  e+" : Field is not displayed in Manage Colt Network page");
		}
		
}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage COLT's Network - Manage Network' page not navigated");
	System.out.println("'Manage COLT's Network - Manage Network' page not navigated");
}	
		
}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
	System.out.println("'View PE Device' page navigated");
}
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	System.out.println(  e+ " : Field is not displayed");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	System.out.println(  e+" : Field is not displayed");
}
sa.assertAll();

	}
	
	

public void routerPanel_PE(String application, String commandIPv4, String commandIPv6, 
		String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException, IOException {
	
	scrollToTop();
	
	
	WebElement vendorModel=getwebelement(xml.getlocator("//locators/" + application + "/PE_View_VendorModelValue"));
	String vendor=Gettext(vendorModel);
	WebElement manageAddress=getwebelement(xml.getlocator("//locators/" + application + "/PE_View_ManagementAddressValue"));
	String ipAddress=Gettext(manageAddress);
	
	ScrolltoElement(application, "PE_View_VendorModelValue" , xml);
	
	
	if(vendor.startsWith("Cisco")) {
		
	//Command IPV4	
		addDropdownValues_commonMethod(application, "Command IPV4", "MAS_PE_Router_IPV4CommandsDropdown" , commandIPv4, xml);
		
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
		ExtentTestManager.getTest().log(LogStatus.INFO, "Router Panel will not display for the selected vendorModel: "+vendorModel);
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
	ExtentTestManager.getTest().log(LogStatus.PASS, "'Result' text field is displaying");
	System.out.println( "'Result' text field is displaying");
	
	String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_result_textArea")).getText();
	ExtentTestManager.getTest().log(LogStatus.PASS, "value under 'Result' field displaying as "+ remarkvalue);
	System.out.println("value under 'Result' field displaying as "+ remarkvalue);

}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
	System.out.println( "'Result' text field is not displaying");
}
}catch(Exception e) {
e.printStackTrace();
ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
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
		  ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
			System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
	  }
	}catch(Exception e) {
		e.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
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
				ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
		}
	}
	else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv6' text field is not displaying for "+ commandIPV6);
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
		  ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
			System.out.println("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
	  }
	}catch(Exception e) {
		e.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
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
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
		}
		
	}else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv4' text field is not displaying for "+ command_ipv4);
		System.out.println("'VRF Name IPv4' text field is not displaying for "+ command_ipv4 +" command");
	}
}	




	
	
	////
	
	public void verifyRouterToolFunction_PE(String application,String ServiceIdentification, String PE_CommandIPV4,String PE_ManagementAddress) throws InterruptedException, DocumentException, IOException {
				
		scrollToTop();
		
		
		WebElement serviceIdLink=getwebelement("//span[text()='"+ ServiceIdentification +"']");
		Clickon(serviceIdLink);
		waitforPagetobeenable();

		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "View Link", "PE_viewdevice1");
		waitforPagetobeenable();
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
		
		try {
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_CommandIPV4_header")));//RouterTool_header//MAS_PE_CommandIPV4_header//MAS_PE_InterfacesPanel_header
			click(application, "ACTION Link", "MAS_View_InterfacesActionLink");
			click(application, "Add Interface Link", "MAS_PE_AddInterfaceLink");
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_AddInterface_header")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'PE Add Interface' page navigated as expected");
				System.out.println("'PE Add Interface' page navigated as expected");
				
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			
		click(application, "OK Button", "MAS_PE_OKButton");
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface Suggestion Message", "PE_InterfaceSuggestionMessage");
		
		
		
		if(PE_AccessMedia.equalsIgnoreCase("VPN")) {
			
			scrollToTop();
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMedia);
			
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
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//				} else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
//					System.out.println("Step : 'IV Management' checkbox is not Selected");
//				}
//			
//				}else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}

			addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", PE_IVManagement, "No", xml);
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			//**scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			
			
			EnterTextValue(application, PE_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ PE_GenerateConfiguration +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);
			
			
			waitforPagetobeenable();
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces sucessfully added by selecting VPN as Access Media");
			
		}else if(PE_AccessMedia.equalsIgnoreCase("EPN")) {
			scrollToTop();
			
			scrollToTop();
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMedia);
			click_commonMethod(application, "Access Media", "MAS_PE_AccessMediaDropdown", xml);
			waitforPagetobeenable();
			WebElement SelectMAS_AccessMediaDropdownValue=getwebelement("//div[text()='"+PE_AccessMedia+"']");
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
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//					System.out.println("Step : 'IV Management' checkbox is Enabled and 'IV Management' checkbox Selected");
//				} else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is not Selected");
//					System.out.println("Step : 'IV Management' checkbox is not Selected");
//				}
//			
//				}else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'IV Management' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}

			addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", PE_IVManagement, "No", xml);
			
			EnterTextValue(application, PE_VRRPTrackInterface, "VRRP Track Interface textfield", "MAS_PE_VRRPTrackInterfaceTextfield");
			EnterTextValue(application, PE_VRRPAuthentication, "VRRP Authentication textfield", "MAS_PE_VRRPAuthenticationTextfield");
			
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));

			scrolltoend();
			//**EnterTextValue(application, PE_GenerateConfiguration, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+PE_GenerateConfiguration+"']");
			Clickon(SelectGenerateConfigurationDropdownValue);
			
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			EnterTextValue(application, "Configuration test", "Configuration", "MAS_PE_ConfigurationTextfield");
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			
			click(application, "OK Button", "MAS_PE_OKButton");
			
			compareText(application, "Interface Added Successfully Message", "MAS_PE_AddInterfaceSuccessfullMessage", "Interface added successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces sucessfully added by selecting EPN as Access Media");			
			Log.info("------ Interface Added successfully ------");
			
		}else {
			System.out.println("Access Media Dropdown is not selected as VPN or EPN");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'PE Add Interface' page not navigated");
				System.out.println("'PE Add Interface' page not navigated");
			}
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
			sa.assertAll();
		
	}
	
	
	
	
	
	public void verifyAddInterfaceFunction_PE_UI(String application, String PE_AccessMedia, String PE_Network,
			String PE_VRRPBGP, String PE_GenerateConfiguration, String	PE_Interface, String PE_InterfaceAddressRange,
			String PE_InterfaceAddressMask, String	PE_VRRPIP, String	PE_InterfaceAddressRangeIPV6, String PE_VRRPIPv6Address, 
			String	PE_PrimaryIPv6onMas1, String PE_SecondaryIPv6onMas2, String PE_GroupNumber, String PE_Link, String PE_VLANID, String PE_VRRPGroupName , String PE_VRF,
			String	PE_IVManagement, String PE_Configuration, String PE_VRRPTrackInterface, String PE_VRRPAuthentication) throws InterruptedException, DocumentException, IOException {
		
			waitforPagetobeenable();
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_CommandIPV4_header")));//RouterTool_header//MAS_PE_CommandIPV4_header//MAS_PE_InterfacesPanel_header
			click(application, "ACTION Link", "MAS_View_InterfacesActionLink");
			click(application, "Add Interface Link", "MAS_PE_AddInterfaceLink");
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_AddInterface_header")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'PE Add Interface' page navigated as expected");
				System.out.println("'PE Add Interface' page navigated as expected");
				
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_Configuration_label")));
			
		try {
		click(application, "OK Button", "MAS_PE_OKButton");
		WarningMessage(application, "Configuration is required", "MAS_PE_ConfigurationWarningMessage");
		scrollToTop();
		WarningMessage(application, "Interface Suggestion Message", "PE_InterfaceSuggestionMessage");
		
		
		
		if(PE_AccessMedia.equalsIgnoreCase("VPN")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMedia);
			
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
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMedia);
			
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
		}
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Interface page");
			System.out.println(  e+ " : Field is not displayed in  Add Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in  Add Interface page ");
			System.out.println(  e+" : Field is not displayed in  Add Interface page");
		}

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'PE Add Interface' page not navigated");
				System.out.println("'PE Add Interface' page not navigated");
			}
			sa.assertAll();
		
	}
	
	
	
	public void verifyEditInterfaceFunction_PE(String application, String ServiceIdentification, String PE_AccessMediaEdit, String PE_NetworkEdit,
			String PE_VRRPBGPEdit, String PE_GenerateConfigurationEdit, String	PE_InterfaceEdit, String PE_InterfaceAddressRangeEdit,
			String PE_InterfaceAddressMaskEdit, String	PE_VRRPIPEdit, String	PE_InterfaceAddressRangeIPV6Edit, String PE_VRRPIPv6AddressEdit, 
			String	PE_PrimaryIPv6onMas1Edit, String PE_SecondaryIPv6onMas2Edit, String PE_GroupNumberEdit, String PE_LinkEdit, String PE_VLANIDEdit, String PE_VRRPGroupNameEdit, String PE_VRFEdit,
			String	PE_IVManagementEdit, String PE_ConfigurationEdit, String PE_VRRPTrackInterfaceEdit, String PE_VRRPAuthenticationEdit) throws InterruptedException, DocumentException, IOException {
		
		try {
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_header")));//ProviderEquipment_header//portalaccess_header//managementoptions_header
			//***click(application, "Show Interfaces Link", "MAS_ShowInterfaceLink");
			
			click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
			click(application, "ACTION PE Device", "PE_ACTION_Device1"); 
			click(application, "Edit Link", "PE_ACTION_EditLink_Device1"); 
		
			if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_EditInterface_header")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'PE Edit Interface' page navigated as expected");
				System.out.println("'PE Edit Interface' page navigated as expected");
				
			try {
				
		if(PE_AccessMediaEdit.equalsIgnoreCase("VPN")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMediaEdit);
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectPE_AccessMediaDropdownValue=getwebelement("//div[text()='"+ PE_AccessMediaEdit +"']");
			Clickon(SelectPE_AccessMediaDropdownValue);
			waitforPagetobeenable();
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

			editcheckbox_commonMethod(application, PE_IVManagementEdit, "MAS_PE_IVManagementCheckbox", "IV Management", xml);
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			
			
			EnterTextValue(application, PE_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+ PE_GenerateConfigurationEdit +"']");
			Clickon(SelectGenerateConfigurationDropdownValue);

			waitforPagetobeenable();
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			scrolltoend();
			click(application, "OK Button", "MAS_PE_OKButton");
				
//			compareText(application, "Interface Updated Successfully Message", "MAS_PE_UpdateInterfaceSuccessfullMessage", "Interface updated successfully");
			verifysuccessmessage(application, "Interface updated successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces sucessfully updated by selecting VPN as Access Media");
			
		}else if(PE_AccessMediaEdit.equalsIgnoreCase("EPN")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  " Selected Access Media is : "+PE_AccessMediaEdit);
			click(application, "Access Media Dropdown", "MAS_PE_AccessMediaDropdown");
			WebElement SelectPE_AccessMediaDropdownValue=getwebelement("//div[text()='"+ PE_AccessMediaEdit +"']");
			Clickon(SelectPE_AccessMediaDropdownValue);
			waitforPagetobeenable();

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


			editcheckbox_commonMethod(application, PE_IVManagementEdit, "MAS_PE_IVManagementCheckbox", "IV Management", xml);

			ClearAndEnterTextValue(application, "VRRP Track Interface textfield", "MAS_PE_VRRPTrackInterfaceTextfield", PE_VRRPTrackInterfaceEdit);
			ClearAndEnterTextValue(application, "VRRP Authentication textfield", "MAS_PE_VRRPAuthenticationTextfield", PE_VRRPAuthenticationEdit);
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_GenerateConfigurationButton")));
			click(application, "Generate Configuration Dropdown", "MAS_PE_GenerateConfigurationDropdown");
			
			
			ScrolltoElement(application, "MAS_PE_IVManagementCheckbox", xml);
//			EnterTextValue(application, PE_GenerateConfigurationEdit, "Enter Generate Configuration", "MAS_PE_GenerateConfigurationDropdown");
//			WebElement SelectGenerateConfigurationDropdownValue=getwebelement("//div[text()='"+PE_GenerateConfigurationEdit+"']");
//			Clickon(SelectGenerateConfigurationDropdownValue);
			
			addDropdownValues_commonMethod(application, "Generate Configuration ", "MAS_PE_GenerateConfigurationDropdown", PE_GenerateConfigurationEdit, xml);
			click(application, "Generate Configuration Button", "MAS_PE_GenerateConfigurationButton");
			GetText(application, "Save Configuration to File Button", "MAS_PE_SaveConfigurationtoFileButton");
			EnterTextValue(application, "Configuration test", "Configuration", "MAS_PE_ConfigurationTextfield");
			GetText(application, "Configuration information after configuration generated", "MAS_PE_ConfigurationTextfield");
			ScrolltoElement(application, "MAS_PE_OKButton", xml);
			click(application, "OK Button", "MAS_PE_OKButton");
			waitforPagetobeenable();
			verifysuccessmessage(application, "Interface updated successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces sucessfully updated by selecting EPN as Access Media");
			Log.info("------ Interface Updated successfully ------");
		
	}else {
		System.out.println("Access Media Dropdown is not selected as VPN or EPN");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Access Media Dropdown is not selected as VPN or EPN");
	}

	
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Interface page");
		System.out.println(  e+ " : Field is not displayed in  Edit Interface page");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in  Edit Interface page ");
		System.out.println(  e+" : Field is not displayed in  Edit Interface page");
	}

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'PE Edit Interface' page not navigated");
				System.out.println("'PE Edit Interface' page not navigated");
			}
			
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Interface page");
			System.out.println(  e+ " : Field is not displayed in  Edit Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in  Edit Interface page ");
			System.out.println(  e+" : Field is not displayed in  Edit Interface page");
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
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Configure Interface' page navigated as expected");
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
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in 'Configure Interface' page");
				System.out.println( "Field is not displayed in 'Configure Interface' page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in 'Configure Interface' page");
				System.out.println( "Field is not displayed in 'Configure Interface' page");
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configure Interface' page not navigated");
			System.out.println("'Configure Interface' page navigated");
		}
		
		Log.info("------ Verified Configure Interfaces Information successfully ------");
	}
	
	
	
	
	
	
	
	
	public void verifyDeleteInterfaceFunction_PE(String application, String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		
		//Delete Interface - MAS Device from View Service Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "Select Checkbox interface", "MAS_PE_Checkbox1_ViewServicePage"); 
		click(application, "ACTION MAS Switch", "PE_ACTION_Device1");
		
		click(application, "Delete Button", "PE_ACTION_DeleteLink_Device1");  
				
		compareText(application, "Delete Interface Warning Message from View Service page", "MAS_PE_DeleteInterfaceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "MAS_PE_DeleteButton");
		waitforPagetobeenable();
		compareText(application, "Delete Interface Successfull Message", "PE_DeleteInterfaceSuccessfullMessage", "Interface deleted successfully");
		
	}
	
	
	
	
	////
	public void verifyDeleteDeviceFunction_PE(String application,String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
		//Delete MAS Device from View Device Page
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header")));
		click(application, "View Link", "PE_viewdevice1"); 
		
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");

			
		click(application, "ACTION link", "PE_View_ActionLink");
		click(application, "Delete Device from View Device page", "PE_View_Action_DeleteLink");
		compareText(application, "Delete PE Device Warning Message from View Device page", "PE_ViewDevice_Action_DeletePEDeviceWarningMessage", "Are you sure that you want to delete this item?");
		click(application, "Delete Button", "PE_ViewDevice_Action_DeleteButton");
		waitforPagetobeenable();
		compareText(application, "Delete MAS Device Successful Message", "PE_DeletePEDeviceSuccessfulMessage", "PE Device deleted successfully");

		
		
		//Delete from Service "From View Service page"
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_AddPEDeviceLink")));
		click(application, "Delete From Service Link", "PE_deletefromservicedevice1");
		compareText(application, "Delete PE Device Warning Message from View Service page", "PE_ViewService_DeletePEDeviceWarningMessage", "Are you sure that you want to delete?");
		click(application, "Delete Button", "PE_ViewService_DeleteButton");
		waitforPagetobeenable();
		compareText(application, "Delete PE Device Successfull Message", "PE_DeletePEDeviceSuccessfulMessage", "PE Device deleted successfully");
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page navigated");
		}


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
			
	
	
	
	
	//////////////////////////  TTTRRRUUUNNNKKK   /////////////////////////////////////////////////
	
	
	
	public void addTrunkGroupSiteOrderNumber(String application, String TrunkGroupOrderCheckboxStatus, String TrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException { 
		
		boolean TrunkGroupSiteOrderPanel_header=false;
		boolean addtrunkSiteorderPage_panelheader=false;
		boolean trunkgrupOrderErrMsg= false;
		try {
		scrolltoend();
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PE_AddPEDeviceLink")));
		TrunkGroupSiteOrderPanel_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrderPanel_header")).isDisplayed();
		if(TrunkGroupSiteOrderPanel_header) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");
			System.out.println("'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");
			
			
		//Perform Add Site order
			click_commonMethod(application, "Add TrunkGroup/SiteOrder link", "addTrunkSiteOrderlink", xml);
			
			addtrunkSiteorderPage_panelheader= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
			if(addtrunkSiteorderPage_panelheader) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is displaying as expected");
				System.out.println("'Add Trunk Group/Site Order' page is displaying as expected");
				
			//verify mandatory Warning Message
				click_commonMethod(application, "OK", "trunk_okButton", xml);
				
				warningMessage_commonMethod(application, "trunkGrouporder_warningmsg", "Trunk Group Order", xml);
				
				warningMessage_commonMethod(application, "trunkGroupOrderName_warningmsg", "Trunk group Order Number", xml);
				
			//Add trunkgroup Order checkbox
				if(TrunkGroupOrderCheckboxStatus.equalsIgnoreCase("yes")) {
					
					addCheckbox_commonMethod(application, "trunkGroupOrder_checkbox", "Trunk Group Order", TrunkGroupOrderCheckboxStatus, "No", xml);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " ' Trunk Group order' checkbox is a mandatory field. No values passed");
					System.out.println(" ' Trunk Group order' checkbox is a mandatory field. No values passed");

				}
				
			//Add Trunk Group order Number text field
				addtextFields_commonMethod(application, "Trunk Group Order Number", "trunkGroupOrderName_textField", TrunkGroupSiteOrderNumber, xml);
				
				
			//Click on OK button
				click_commonMethod(application, "OK", "trunk_okButton", xml);
				//waitforPagetobeenable();
				
//				compareText(application, "Add Trunk Group/Site Order Creation success message", "alertMSg_siteorderCreation", "Trunk Group created successfully", xml);
				verifysuccessmessage(application, "Trunk Group created successfully");
				try {
				trunkgrupOrderErrMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).isDisplayed();
				String actualMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).getText();
				if(trunkgrupOrderErrMsg) {
					if(actualMsg.contains("1.trunkgroup number already exists")) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
						System.out.println(" Error message we are getting as: "+ actualMsg);
					}
					else if(actualMsg.equalsIgnoreCase("Trunk Group created successfully")) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as 'Trunk Group created successfully'");
						System.out.println(" Success Message displays as 'Trunk Group created successfully'");
					}
					
				}
				}catch(Exception e) {
					e.printStackTrace();
					
				}
				
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is not displaying");
				System.out.println("'Add Trunk Group/Site Order' page is not displaying");
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
			System.out.println("'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
		}
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		sa.assertAll();
	}
	
	
	
	
	
	
	
	
	
	
	
public void editTrunkGroupSiteOrderNumber(String application, String TrunkGroupSiteOrderNumber,  String TrunkGroupOrderCheckboxStatusEdit, String TrunkGroupSiteOrderNumberEdit) throws InterruptedException, DocumentException, IOException {
		
		boolean addtrunkSiteorderPage_panelheader=false;
		boolean trunkgrupOrderErrMsg=false;
		waitforPagetobeenable();
		scrolltoend();
		
		WebElement editlink=getwebelement("//div[div[span[text()='"+ TrunkGroupSiteOrderNumber +"']]]//span[text()='Edit']");
		Clickon(editlink);
		
		addtrunkSiteorderPage_panelheader= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
		if(addtrunkSiteorderPage_panelheader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is displaying as expected");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying as expected");
			
		//Trunk group Order
			if(TrunkGroupOrderCheckboxStatusEdit.equalsIgnoreCase("no")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Trunk Group Order' is a mandatory field. It cannot be unselected");
				System.out.println("'Trunk Group Order' is a mandatory field. It cannot be unselected");
			}else {
				editcheckbox_commonMethod(application, TrunkGroupOrderCheckboxStatusEdit, "trunkGroupOrder_checkbox", "Trunk Group Order", xml);
			}
			
			
		//Trunk Group Order Number
			ClearAndEnterTextValue(application, "Trunk Group Order Number Edit", "trunkGroupOrderName_textField", TrunkGroupSiteOrderNumberEdit);
			
		//Click on OK button
			click_commonMethod(application, "OK", "trunk_okButton", xml);
			waitforPagetobeenable();
			
			try {
			trunkgrupOrderErrMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).isDisplayed();
			String actualMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).getText();
			if(trunkgrupOrderErrMsg) {
				if(actualMsg.contains("1.trunkgroup number already exists")) {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
					System.out.println(" Error message we are getting as: "+ actualMsg);
				}
				else if(actualMsg.contains("Trunk Group successfully updated")) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as 'Trunk Group successfully updated.'");
					System.out.println(" Success Message displays as 'Trunk Group successfully updated.'");
				}
				
			}
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
			
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is not displaying");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying");
		}
	}
	
	
	





	
	
	
	
	
	
	public void editSiteOrder_OLD(String application, String siteOrderName,  String trunkGroupOrder, String trunkGrouporderNumber) throws InterruptedException, DocumentException, IOException {
		
		boolean siteOrderpage=false;
		boolean trunkgrupOrderErrMsg=false;
		waitforPagetobeenable();
		scrolltoend();
		
		WebElement editlink=getwebelement("//div[div[span[text()='"+ siteOrderName +"']]]//span[text()='Edit']");
		Clickon(editlink);
		
		siteOrderpage= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
		if(siteOrderpage) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is displaying as expected");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying as expected");
			
		//Trunk group Order
			if(trunkGroupOrder.equalsIgnoreCase("no")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Trunk Group Order' is a mandatory field. It cannot be unselected");
				System.out.println("'Trunk Group Order' is a mandatory field. It cannot be unselected");
			}else {
				editcheckbox_commonMethod(application, trunkGroupOrder, "trunkGroupOrder_checkbox", "Trunk Group Order", xml);
			}
			
			
		//Trunk Group Order Number
			edittextFields_commonMethod(application, "Trunk Group Order Number", "trunkGroupOrderName_textField", trunkGrouporderNumber, xml);
			
			
		//Click on OK button
			click_commonMethod(application, "OK", "trunk_okButton", xml);
			waitforPagetobeenable();
			
			try {
			trunkgrupOrderErrMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).isDisplayed();
			String actualMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).getText();
			if(trunkgrupOrderErrMsg) {
				if(actualMsg.contains("1.trunkgroup number already exists")) {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
					System.out.println(" Error message we are getting as: "+ actualMsg);
				}
				else if(actualMsg.contains("Trunk Group successfully updated")) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as 'Trunk Group successfully updated.'");
					System.out.println(" Success Message displays as 'Trunk Group successfully updated.'");
				}
				
			}
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
			
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is not displaying");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying");
		}
	}
	
	
	
	
	
	
	public void deleteTrunkGroupSiteOrderNumber(String application, String TrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException {
		waitforPagetobeenable();
		try {
		scrolltoend();
		boolean TrunkGroupSiteOrderNumberText=false;
		//boolean ViewService_Trunk_DeleteSiteOrderSuccessMessage=false;
		
		WebElement deletelink=getwebelement("//div[div[span[text()='"+ TrunkGroupSiteOrderNumber +"']]]//span[text()='Delete']");
		
		TrunkGroupSiteOrderNumberText=getwebelement("//span[text()='"+ TrunkGroupSiteOrderNumber +"']").isDisplayed();
		
		WebElement ViewService_Trunk_DeleteSiteOrderSuccessMessage=getwebelement("ViewService_Trunk_DeleteSiteOrderSuccessMessage");
	
   if(TrunkGroupSiteOrderNumberText){
			
			ExtentTestManager.getTest().log(LogStatus.PASS, TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			
			if(deletelink.isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Delete Link in Trunk Group/Site Order' page is displaying as expected");
				System.out.println("'Delete Link Trunk Group/Site Order' page is displaying as expected");
				
			//Trunk group Order
				deletelink.click();
				compareText(application, "Delete Site Order warning message", "ViewService_Trunk_DeleteSiteOrderWarningMessage", "Are you sure that you want to delete this item?");
				click(application, "Delete Button", "ViewService_Trunk_DeleteButton");
				
				
				if(ViewService_Trunk_DeleteSiteOrderSuccessMessage.isDisplayed()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group Site Order' Deleted Successfully");
					System.out.println("'Trunk Group Site Order' Deleted Successfully");
																					}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group Site Order' not Deleted");
					System.out.println("'Trunk Group Site Order' not Deleted");
																						   } 
		    							}else {
		    		ExtentTestManager.getTest().log(LogStatus.PASS, "'Delete Link in Trunk Group/Site Order' page is not displaying as expected");
		    		System.out.println("'Delete Link Trunk Group/Site Order' page is not  displaying as expected");
		    							}
			
			
			
							}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, TrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
								  }
   
		 }catch(NoSuchElementException e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		    	System.out.println(  e+ " : Field is not displayed");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		    	System.out.println(  e+" : Field is not displayed");
		    }
		    sa.assertAll();
}
	
	
	
	
	
	
	
	
	
	
	public void deleteUpdatedTrunkGroupSiteOrderNumber(String application,   String TrunkGroupSiteOrderNumberEdit) throws InterruptedException, DocumentException, IOException {
		scrolltoend();
		
		
		boolean TrunkGroupSiteOrderNumberUpdatedText=false;
		//boolean ViewService_Trunk_DeleteSiteOrderSuccessMessage=false;
		
		WebElement deleteLink=getwebelement("//div[div[span[text()='"+ TrunkGroupSiteOrderNumberEdit +"']]]//span[text()='Delete']");
		
		TrunkGroupSiteOrderNumberUpdatedText=getwebelement("//span[text()='"+ TrunkGroupSiteOrderNumberEdit +"']").isDisplayed();
		
		WebElement ViewService_Trunk_DeleteSiteOrderSuccessMessage=getwebelement("ViewService_Trunk_DeleteSiteOrderSuccessMessage");

		
		if(TrunkGroupSiteOrderNumberUpdatedText){
			
			ExtentTestManager.getTest().log(LogStatus.PASS, TrunkGroupSiteOrderNumberEdit + " 'Site Order' is displaying under 'Trunk Group/Site Orders' panel");
			System.out.println(TrunkGroupSiteOrderNumberEdit + " 'Site Order' is displaying under 'Trunk Group/Site Orders' panel");
			
			
			if(deleteLink.isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Delete Link in Trunk Group/Site Order' page is displaying as expected");
				System.out.println("'Delete Link Trunk Group/Site Order' page is displaying as expected");
				
			//Trunk group Order
				deleteLink.click();
				compareText(application, "Delete Site Order warning message", "ViewService_Trunk_DeleteSiteOrderWarningMessage", "Are you sure that you want to delete this item?");
				click(application, "Delete Button", "ViewService_Trunk_DeleteButton");
				
				
				if(ViewService_Trunk_DeleteSiteOrderSuccessMessage.isDisplayed()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group Site Order' Deleted Successfully");
					System.out.println("'Trunk Group Site Order' Deleted Successfully");
																					}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group Site Order' not Deleted");
					System.out.println("'Trunk Group Site Order' not Deleted");
																						   } 
		    							}else {
		    		ExtentTestManager.getTest().log(LogStatus.PASS, "'Delete Link in 'Trunk Group/Site Order' panel is not displaying as expected");
		    		System.out.println("'Delete Link in Trunk Group/Site Order' panel is not  displaying as expected");
		    							}
			 
			
			
							}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, TrunkGroupSiteOrderNumberEdit + " 'Site Order' is not displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumberEdit + " 'Site Order' is not displaying under 'Trunk' panel");
								  }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	//////////////  ADD TRUNK  ///////////
	
	
	
	public void verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel(String application, String TrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException {
		try {
		scrolltoend();
		
		
		boolean siteOrderUnderTrunkPanel=false;
		
		
		siteOrderUnderTrunkPanel=getwebelement("//span[text()='"+ TrunkGroupSiteOrderNumber +"']").isDisplayed();
		
		if(siteOrderUnderTrunkPanel) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			
		//Click on Add trunk link	
			
			String addTunklinkXpath="//div[div[span[text()='"+ TrunkGroupSiteOrderNumber +"']]]/following-sibling::div//a[span[text()='Add Trunk']]";
			scrolltoend();
			click_commonMethod_PassingWebelementDirectly(application, "Add Trunk", addTunklinkXpath, xml);
			
			
									}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, TrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
			System.out.println(TrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
										 }
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		sa.assertAll();
									}

	
	
	
	
	
	
	
public void verifyExistingSiteOrderAndTrunkLinkUnderTrunkPanel(String application, String ExistingTrunkGroupSiteOrderNumber) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		
		
		boolean siteOrderUnderTrunkPanel=false;
		
		
		siteOrderUnderTrunkPanel=getwebelement("//span[text()='"+ ExistingTrunkGroupSiteOrderNumber +"']").isDisplayed();
		
		if(siteOrderUnderTrunkPanel) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, ExistingTrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			System.out.println(ExistingTrunkGroupSiteOrderNumber + " 'Site Order' is displaying under 'Trunk' panel");
			
		//Click on Add trunk link	
			String addTunklinkXpath="//div[div[span[text()='"+ ExistingTrunkGroupSiteOrderNumber +"']]]/following-sibling::div//span[text()='Add Trunk']";
			click_commonMethod_PassingWebelementDirectly(application, "Add Trunk", addTunklinkXpath, xml);
			
			
									}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, ExistingTrunkGroupSiteOrderNumber + " 'Site Order' is not displaying under 'Trunk' panel");
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
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Trunk' Page navigated as expected");
		System.out.println("'Add Trunk' Page navigated as expected");
		
		
	
	scrolltoend();
	
	click_commonMethod(application, "OK Button", "trunk_okButton", xml);
	
	scrollToTop();
	
	
	
	
	
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
	
	
	
		//Quality
	isDisplayed(application, "quality_Dropdown", "Quality", xml);
	
	
		//IP Address Type
	isDisplayed(application, "IPaddresstype_Dropdown", "IP Address Type", xml);
	SelectDropdownValueUnderSelectTag(application, "IP Address Type", ipAddresstype, "IPaddresstype_Dropdown", xml);

	WebElement IPAddressType=getwebelement(xml.getlocator("//locators/" + application + "/IPaddresstype_Dropdown"));
	scrolltoview(IPAddressType);
	
	
	
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
	  ExtentTestManager.getTest().log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
	  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
  }
	

  
  
  
  	//Splitting the Gateway functionality into 2 SBC & Non-SBC 
  if(!gateway.contains("SBC")) {    //CASE-1 For NO-SBC
	
	  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {  //case A with Interface Based Customer selection
		  
		  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
		  
		//Internet Based Customer
		  isDisplayed(application, "internetBasedCustomer_checkbox", "Internet Based Customer", xml);
			addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
			
		  
		  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
		  if(vlanDefaultvalue.isEmpty()) {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
		  }else {
			 
			  ExtentTestManager.getTest().log(LogStatus.PASS, "When 'Internet Based Customer' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' field is  a mandatory field and not values are passed as an input");
			
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
				 
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is a mandatory field. No values has been passed as an input");
			 
				//IP Interface
				  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
				  if(ipInterface_Enabled) {
					  System.out.println("'Ip Interface' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
				  }else {
					  System.out.println("'Ip Interface' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
				  }
				  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
				  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
				  
			//Address Context
				  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
				  if(addressContext_Enabled) {
					  System.out.println("'Address Context' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
				  }else {
					  System.out.println("'Address Context' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
				  }
				  
				  isDisplayed(application, "AddressContext_textField", "Address Context", xml);
				  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
				
				  
			//IP Interface Group
				  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
				  if(ipInterfaceGroup_Enabled) {
					  System.out.println("'IP Inteface Group' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
				  }else {
					  System.out.println("'IP Inteface Group' text field is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					  }else {
						  System.out.println("'Ip Interface' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					  }
					  
					  ipInterface = ipInterfaceDEfaultValue +vlanTag;
					  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
					  
				//Address Context
					  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					  if(addressContext_Enabled) {
						  System.out.println("'Address Context' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					  }else {
						  System.out.println("'Address Context' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					  }
					  
					  addressContext=addressContextDefaultValue+vlanTag;
					  isDisplayed(application, "xpath", "labelname", xml);
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
					
					  
				//IP Interface Group
					  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					  if(ipInterfaceGroup_Enabled) {
						  System.out.println("'IP Inteface Group' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					  }else {
						  System.out.println("'IP Inteface Group' text field is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					  }
					  
					  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanTag;
					  isDisplayed(application, "ipInterfaceGroup_textField", "IP Interface Group", xml);
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
			  }
			  
			  
			  
			  
		  }else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
			  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
			  
				//Internet Based Customer
			  isDisplayed(application, "internetBasedCustomer_checkbox", "lnternet Based Customer", xml);
					addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
					
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				//VLAN tag  
				  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
				  if(VlanEnability) {
					  System.out.println("VLAN Tag is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
				  }else {
					  System.out.println("VLAN Tag is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
				  } 
			  }
			  
				//IP Interface
			  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
			  if(ipInterface_Enabled) {
				  System.out.println("'Ip Interface' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
			  }else {
				  System.out.println("'Ip Interface' text fieldis disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
			  }
			  
			  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
			  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
			  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
			  
		//Address Context
			  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
			  if(addressContext_Enabled) {
				  System.out.println("'Address Context' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
			  }else {
				  System.out.println("'Address Context' text fieldis disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
			  }
			  
			  addressContext=addressContextDefaultValue+vlanDefaultvalue;
			  isDisplayed(application, "AddressContext_textField", "Address Context", xml);
			  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
			
			  
		//IP Interface Group
			  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
			  if(ipInterfaceGroup_Enabled) {
				  System.out.println("'IP Inteface Group' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
			  }else {
				  System.out.println("'IP Inteface Group' text field is disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
				  }else {
					  System.out.println("VLAN Tag is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
				  }
				  
			//IP Interface
				  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
				  if(ipInterface_Enabled) {
					  System.out.println("'Ip Interface' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
				  }else {
					  System.out.println("'Ip Interface' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
				  }
				  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
				  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
				  
			//Address Context
				  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
				  if(addressContext_Enabled) {
					  System.out.println("'Address Context' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
				  }else {
					  System.out.println("'Address Context' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
				  }
				  isDisplayed(application, "AddressContext_textField", "Address Context", xml);
				  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
				
				  
			//IP Interface Group
				  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
				  if(ipInterfaceGroup_Enabled) {
					  System.out.println("'IP Inteface Group' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
				  }else {
					  System.out.println("'IP Inteface Group' text field is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
				  }
				  isDisplayed(application, "ipInterfaceGroup_textField", "IP Interface Group", xml);
				  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
				
				  
			//Signalling port
					signallingPort(application, gateway);	  
					
			}
		 
		 if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
			  
			  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
			  
			//Internet Based Customer
			  isDisplayed(application, "internetBasedCustomer_checkbox", "Internet Based Customer", xml);
				addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
				
		  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
		  if(vlanDefaultvalue.isEmpty()) {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
		  }else {
			//VLAN tag  
			  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
			  if(VlanEnability) {
				  System.out.println("VLAN Tag is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
			  }else {
				  System.out.println("VLAN Tag is disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
			  } 
		  }
		  
			//IP Interface
		  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
		  if(ipInterface_Enabled) {
			  System.out.println("'Ip Interface' text field is enabled");
			  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
		  }else {
			  System.out.println("'Ip Interface' text fieldis disabled");
			  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
		  }
		  
		  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
		  isDisplayed(application, "ipInterface_textField", "IP Interface", xml);
		  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
		  
	//Address Context
		  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
		  if(addressContext_Enabled) {
			  System.out.println("'Address Context' text field is enabled");
			  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
		  }else {
			  System.out.println("'Address Context' text fieldis disabled");
			  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
		  }
		  
		  addressContext=addressContextDefaultValue+vlanDefaultvalue;
		  isDisplayed(application, "AddressContext_textField", "Address Context", xml);
		  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
		
		  
	//IP Interface Group
		  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
		  if(ipInterfaceGroup_Enabled) {
			  System.out.println("'IP Inteface Group' text field is enabled");
			  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
		  }else {
			  System.out.println("'IP Inteface Group' text field is disabled");
			  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
				System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
				System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
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
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
					} else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
					}
				
					}else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
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
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
					} else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
						System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
					}
				
					}else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
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
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
	
		if(!callrateLimiteValue.equalsIgnoreCase("null")) {
			int i=Integer.parseInt(callrateLimiteValue);
				
			if(i>100) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less 100 for all Trunks");
			}
			else if(i<=100){
				edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
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
		
		
		
	
	scrolltoend();
	
	
	isDisplayed(application, "trunk_okButton", "OK", xml);
	isDisplayed(application, "cancelbutton", "Cancel", xml);
	
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Trunk' Page is not navigated");
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
		
		try {
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddTrunk_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Trunk' Page navigated as expected");
			System.out.println("'Add Trunk' Page navigated as expected");
			
			
		
		scrolltoend();
		
		click_commonMethod(application, "OK Button", "trunk_okButton", xml);
		
		scrollToTop();
		
		
		
		
		
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
		
		
		
			//Quality
		SelectDropdownValueUnderSelectTag(application, "Quality", quality, "quality_Dropdown", xml);
		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");
		
		
			//IP Address Type
		SelectDropdownValueUnderSelectTag(application, "IP Address Type", ipAddresstype, "IPaddresstype_Dropdown", xml);

		WebElement IPAddressType=getwebelement(xml.getlocator("//locators/" + application + "/IPaddresstype_Dropdown"));
		scrolltoview(IPAddressType);
		
		
		
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
		  ExtentTestManager.getTest().log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
		  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
	  }
		

	  
	  
	  
	  	//Splitting the Gateway functionality into 2 SBC & Non-SBC 
	  if(!gateway.contains("SBC")) {    //CASE-1 For NO-SBC
		
		  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {  //case A with Interface Based Customer selection
			  
			  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
			  
			//Internet Based Customer
				addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
				
			  
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				 
				  ExtentTestManager.getTest().log(LogStatus.PASS, "When 'Internet Based Customer' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
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
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' field is  a mandatory field and not values are passed as an input");
				
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
					 
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is a mandatory field. No values has been passed as an input");
				 
					//IP Interface
					  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					  if(ipInterface_Enabled) {
						  System.out.println("'Ip Interface' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					  }else {
						  System.out.println("'Ip Interface' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					  }
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					  if(addressContext_Enabled) {
						  System.out.println("'Address Context' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					  }else {
						  System.out.println("'Address Context' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					  if(ipInterfaceGroup_Enabled) {
						  System.out.println("'IP Inteface Group' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					  }else {
						  System.out.println("'IP Inteface Group' text field is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
							  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						  }else {
							  System.out.println("'Ip Interface' text fieldis disabled");
							  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						  }
						  
						  ipInterface = ipInterfaceDEfaultValue +vlanTag;
						  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
						  
					//Address Context
						  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						  if(addressContext_Enabled) {
							  System.out.println("'Address Context' text field is enabled");
							  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						  }else {
							  System.out.println("'Address Context' text fieldis disabled");
							  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
						  }
						  
						  addressContext=addressContextDefaultValue+vlanTag;
						  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
						
						  
					//IP Interface Group
						  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						  if(ipInterfaceGroup_Enabled) {
							  System.out.println("'IP Inteface Group' text field is enabled");
							  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						  }else {
							  System.out.println("'IP Inteface Group' text field is disabled");
							  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						  }
						  
						  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanTag;
						  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				 
				  }
				  
				  
				  
				  
			  }else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
					//Internet Based Customer
						addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
						
				  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				  if(vlanDefaultvalue.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
				  }else {
					//VLAN tag  
					  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
					  if(VlanEnability) {
						  System.out.println("VLAN Tag is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
					  }else {
						  System.out.println("VLAN Tag is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
					  } 
				  }
				  
					//IP Interface
				  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
				  if(ipInterface_Enabled) {
					  System.out.println("'Ip Interface' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
				  }else {
					  System.out.println("'Ip Interface' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
				  }
				  
				  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
				  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
				  
			//Address Context
				  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
				  if(addressContext_Enabled) {
					  System.out.println("'Address Context' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
				  }else {
					  System.out.println("'Address Context' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
				  }
				  
				  addressContext=addressContextDefaultValue+vlanDefaultvalue;
				  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
				
				  
			//IP Interface Group
				  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
				  if(ipInterfaceGroup_Enabled) {
					  System.out.println("'IP Inteface Group' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
				  }else {
					  System.out.println("'IP Inteface Group' text field is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
					  }else {
						  System.out.println("VLAN Tag is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
					  }
					  
				//IP Interface
					  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					  if(ipInterface_Enabled) {
						  System.out.println("'Ip Interface' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					  }else {
						  System.out.println("'Ip Interface' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					  }
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					  if(addressContext_Enabled) {
						  System.out.println("'Address Context' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					  }else {
						  System.out.println("'Address Context' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					  if(ipInterfaceGroup_Enabled) {
						  System.out.println("'IP Inteface Group' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					  }else {
						  System.out.println("'IP Inteface Group' text field is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
					
					  
				//Signalling port
						signallingPort(application, gateway);	  
						
				}
			 
			 if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
				//Internet Based Customer
					addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
					
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				//VLAN tag  
				  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
				  if(VlanEnability) {
					  System.out.println("VLAN Tag is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
				  }else {
					  System.out.println("VLAN Tag is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
				  } 
			  }
			  
				//IP Interface
			  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
			  if(ipInterface_Enabled) {
				  System.out.println("'Ip Interface' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
			  }else {
				  System.out.println("'Ip Interface' text fieldis disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
			  }
			  
			  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
			  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
			  
		//Address Context
			  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
			  if(addressContext_Enabled) {
				  System.out.println("'Address Context' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
			  }else {
				  System.out.println("'Address Context' text fieldis disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
			  }
			  
			  addressContext=addressContextDefaultValue+vlanDefaultvalue;
			  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
			
			  
		//IP Interface Group
			  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
			  if(ipInterfaceGroup_Enabled) {
				  System.out.println("'IP Inteface Group' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
			  }else {
				  System.out.println("'IP Inteface Group' text field is disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
				
		
			// Reuse Network Selector Table
			addCheckbox_commonMethod(application, "ReuseNetworkSelectorTable_checkbox", "Reuse Network Selector Table", ReuseNetworkSelectorTable, "No", xml);

			
			//Reuse NIF Group
			addCheckbox_commonMethod(application, "reuseNIFgroup_checkbox", "Reuse NIF Group", reuseNIFgroup, "No", xml);
			
					
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
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
		
			if(!callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(callrateLimiteValue);
					
				if(i>100) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
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
			
			
		
		scrolltoend();
		
		
		click_commonMethod(application, "OK", "trunk_okButton", xml);
		waitforPagetobeenable();
		GetText(application, "Trunk Creation Success Message", "CreateTrunkSuccessMessage");
		
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Trunk' Page is not navigated");
			System.out.println("'Add Trunk' Page is not navigated");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		sa.assertAll();
		
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
		
		
		click_commonMethod(application, "OK", "OKbutton", xml);
		
		scrollToTop();
		
		
		
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
			 ExtentTestManager.getTest().log(LogStatus.PASS, "When we click on 'Allocate Prefix' button, Under 'Prefix' value is displaying as: "+prefix_actualvalue);
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Prefix value cannot be less than 4. Value is displaying as "+preifxValueInsidetextField);
		}else if(prefixSize>4) {
			System.out.println("Prefix value cannot be greater than 4");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Prefix value cannot be greater than 4. Value is displaying as "+preifxValueInsidetextField);
		}
		 
		
	//Gateway
		selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", gateway, xml);
		gatewayCode=gateway_code(application, gateway);
		
		WebElement trunktype=getwebelement(xml.getlocator("//locators/" + application + "/trunkType_Dropdown"));
		scrolltoview(trunktype);
		
		
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Trunk Group NAme' length is: "+totalLen);
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
		}
		
	//traffic Direction
		selectValueInsideDropdown(application, "trafficDirection_Dropdown", "Traffic Directions", trafficDirection, xml);
		
	//IP Address Type
		selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", ipAddresstype, xml);
	
		WebElement traficDirection=getwebelement(xml.getlocator("//locators/" + application + "/trafficDirection_Dropdown"));
		scrolltoview(traficDirection);
		
		
		
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
		  ExtentTestManager.getTest().log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
		  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
	  }
		
	
	//Splitting the Gateway functionality into 2  
	  if(!gateway.contains("SBC")) {
		
		  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
			  
			  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
			  
			//Internet Based Customer
				addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
				
			  
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				 
				  ExtentTestManager.getTest().log(LogStatus.PASS, "When 'Internet Based Customer' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
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
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' field is  a mandatory field and not values are passed as an input");
				
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
					 
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is a mandatory field. No values has been passed as an input");
				 
					//IP Interface
					  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					  if(ipInterface_Enabled) {
						  System.out.println("'Ip Interface' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					  }else {
						  System.out.println("'Ip Interface' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					  }
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					  if(addressContext_Enabled) {
						  System.out.println("'Address Context' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					  }else {
						  System.out.println("'Address Context' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					  if(ipInterfaceGroup_Enabled) {
						  System.out.println("'IP Inteface Group' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					  }else {
						  System.out.println("'IP Inteface Group' text field is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
							  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						  }else {
							  System.out.println("'Ip Interface' text fieldis disabled");
							  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						  }
						  
						  ipInterface = ipInterfaceDEfaultValue +vlanTag;
						  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
						  
					//Address Context
						  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						  if(addressContext_Enabled) {
							  System.out.println("'Address Context' text field is enabled");
							  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						  }else {
							  System.out.println("'Address Context' text fieldis disabled");
							  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
						  }
						  
						  addressContext=addressContextDefaultValue+vlanTag;
						  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
						
						  
					//IP Interface Group
						  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						  if(ipInterfaceGroup_Enabled) {
							  System.out.println("'IP Inteface Group' text field is enabled");
							  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						  }else {
							  System.out.println("'IP Inteface Group' text field is disabled");
							  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						  }
						  
						  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanTag;
						  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				 
				  }
				  
			  }
			  else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
					//Internet Based Customer
						addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
						
				  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				  if(vlanDefaultvalue.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
				  }else {
					//VLAN tag  
					  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
					  if(VlanEnability) {
						  System.out.println("VLAN Tag is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
					  }else {
						  System.out.println("VLAN Tag is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
					  } 
				  }
				  
					//IP Interface
				  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
				  if(ipInterface_Enabled) {
					  System.out.println("'Ip Interface' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
				  }else {
					  System.out.println("'Ip Interface' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
				  }
				  
				  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
				  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
				  
			//Address Context
				  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
				  if(addressContext_Enabled) {
					  System.out.println("'Address Context' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
				  }else {
					  System.out.println("'Address Context' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
				  }
				  
				  addressContext=addressContextDefaultValue+vlanDefaultvalue;
				  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
				
				  
			//IP Interface Group
				  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
				  if(ipInterfaceGroup_Enabled) {
					  System.out.println("'IP Inteface Group' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
				  }else {
					  System.out.println("'IP Inteface Group' text field is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
					  }else {
						  System.out.println("VLAN Tag is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
					  }
					  
				//IP Interface
					  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					  if(ipInterface_Enabled) {
						  System.out.println("'Ip Interface' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					  }else {
						  System.out.println("'Ip Interface' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					  }
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					  if(addressContext_Enabled) {
						  System.out.println("'Address Context' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					  }else {
						  System.out.println("'Address Context' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					  if(ipInterfaceGroup_Enabled) {
						  System.out.println("'IP Inteface Group' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					  }else {
						  System.out.println("'IP Inteface Group' text field is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
					
					  
				//Signalling port
						signallingPort(application, gateway);	  
						
				}
			 
			 if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
				//Internet Based Customer
					addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
					
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				//VLAN tag  
				  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
				  if(VlanEnability) {
					  System.out.println("VLAN Tag is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
				  }else {
					  System.out.println("VLAN Tag is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
				  } 
			  }
			  
				//IP Interface
			  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
			  if(ipInterface_Enabled) {
				  System.out.println("'Ip Interface' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
			  }else {
				  System.out.println("'Ip Interface' text fieldis disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
			  }
			  
			  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
			  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
			  
		//Address Context
			  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
			  if(addressContext_Enabled) {
				  System.out.println("'Address Context' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
			  }else {
				  System.out.println("'Address Context' text fieldis disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
			  }
			  
			  addressContext=addressContextDefaultValue+vlanDefaultvalue;
			  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
			
			  
		//IP Interface Group
			  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
			  if(ipInterfaceGroup_Enabled) {
				  System.out.println("'IP Inteface Group' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
			  }else {
				  System.out.println("'IP Inteface Group' text field is disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
		
			if(!callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(callrateLimiteValue);
					
				if(i>100) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
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
		
		
		click_commonMethod(application, "OK", "OKbutton", xml);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
			code="820";
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
				
				
				edittextFields_commonMethod(application, labelname, xpath_textfield, textFieldValue, xml);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
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
			
			
			selectValueInsideDropdown(application, xpath_dropdown, labelname, existinFieldvalue, xml);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
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
try {
	waitforPagetobeenable();
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/ViewTrunk_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View Trunk' Page navigated as expected");
			System.out.println("'View Trunk' Page navigated as expected");
			
			
			
			
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View Trunk' Page is not navigated as expected");
			System.out.println("'View Trunk' Page is not navigated as expected");
		}
		
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	System.out.println(  e+ " : Field is not displayed");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	System.out.println(  e+" : Field is not displayed");
}
sa.assertAll();
		
			
	}
	
	
public void verifyAddDRPlans(String application, String Add_DRplanA, String Add_DRplanB, String Add_DRplanC, String Add_DRplanD, String Add_DRplanE, String rangestart_cc, String rangestart_lac, String rangestart_num, String rangefinish_cc, String rangefinish_lac, String rangefinish_num, String destinationnumber_cc, String destinationnumber_lac, String destinationnumber_num, String activate_deactivateDRplan_dropdownvalue, String edit_rangestart_cc, String edit_rangestart_lac, String edit_rangestart_num, String edit_rangefinish_cc, String edit_rangefinish_lac, String edit_rangefinish_num, String edit_destinationnumber_cc, String edit_destinationnumber_lac, String edit_destinationnumber_num, String edit_activate_deactivateDRplan_dropdownvalue) throws InterruptedException, DocumentException, IOException {
try {
	waitforPagetobeenable();
	ScrolltoElement(application, "customizedmedia_header", xml);
	
	click_commonMethod(application, "Disaster Recovery Header", "recovery_header", xml);
	
	click_commonMethod(application, "Add DR Plan", "addDRplans_link", xml);
	
	waitforPagetobeenable();
	compareText(application, "Add DR Plans Header", "addDRplan_header", "Disaster Recovery Plans", xml);
	
	if(Add_DRplanA.equalsIgnoreCase("Yes")) {
		scrollToTop();
		compareText(application, "DR Plan A_header", "DRplanA_header", "DR Plan A", xml);
		click_commonMethod(application, "DR Plan A Action dropdown", "DRplanA_actiondropdown", xml);
		
		click_commonMethod(application, "Add", "DRplan_addlink", xml);
		
		waitforPagetobeenable();
		AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
		compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
		compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
		compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
		compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

		//Edit DR Plan
		addedDRplan(application, "DR Plan A", rangestart_cc, rangestart_lac, rangestart_num);
		click_commonMethod(application, "DR Plan A Action dropdown", "DRplanA_actiondropdown", xml);
		
		click_commonMethod(application, "Edit", "DRplan_editlink", xml);
		
		waitforPagetobeenable();
		EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

		//Delete DR Plan
		if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
		{
			editedDRplan(application, "DR Plan A", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
		}
		else
		{
			addedDRplan(application, "DR Plan A", rangestart_cc, rangestart_lac, rangestart_num);	
		}
		click_commonMethod(application, "DR Plan A Action dropdown", "DRplanA_actiondropdown", xml);
		
		click_commonMethod(application, "Delete", "DRplan_Deletelink", xml);
		
		deleteDRPlan(application);

	}
	if(Add_DRplanB.equalsIgnoreCase("Yes")) {
		ScrolltoElement(application, "DRplanA_header", xml);
		
		compareText(application, "DR Plan B_header", "DRplanB_header", "DR Plan B", xml);
		click_commonMethod(application, "DR Plan B Action dropdown", "DRplanB_actiondropdown", xml);
		
		click_commonMethod(application, "Add", "DRplan_addlink", xml);
		
		waitforPagetobeenable();
		AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
		compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
		compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
		compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
		compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

		//Edit DR Plan
		ScrolltoElement(application, "DRplanA_header", xml);
		
		addedDRplan(application, "DR Plan B", rangestart_cc, rangestart_lac, rangestart_num);
		click_commonMethod(application, "DR Plan B Action dropdown", "DRplanB_actiondropdown", xml);
		
		click_commonMethod(application, "Edit", "DRplan_editlink", xml);
		
		waitforPagetobeenable();
		EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

		//Delete DR Plan
		ScrolltoElement(application, "DRplanA_header", xml);
		
		if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
		{
			editedDRplan(application, "DR Plan B", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
		}
		else
		{
			addedDRplan(application, "DR Plan B", rangestart_cc, rangestart_lac, rangestart_num);	
		}
		click_commonMethod(application, "DR Plan B Action dropdown", "DRplanB_actiondropdown", xml);
		
		click_commonMethod(application, "Delete", "DRplan_Deletelink", xml);
		
		deleteDRPlan(application);
	}
	if(Add_DRplanC.equalsIgnoreCase("Yes")) {

		ScrolltoElement(application, "DRplanB_header", xml);
		
		compareText(application, "DR Plan C_header", "DRplanC_header", "DR Plan C", xml);
		click_commonMethod(application, "DR Plan C Action dropdown", "DRplanC_actiondropdown", xml);
		
		click_commonMethod(application, "Add", "DRplan_addlink", xml);
		
		waitforPagetobeenable();
		AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
		compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
		compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
		compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
		compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

		//Edit DR Plan
		ScrolltoElement(application, "DRplanB_header", xml);
		
		addedDRplan(application, "DR Plan C", rangestart_cc, rangestart_lac, rangestart_num);
		click_commonMethod(application, "DR Plan C Action dropdown", "DRplanC_actiondropdown", xml);
		
		click_commonMethod(application, "Edit", "DRplan_editlink", xml);
		
		waitforPagetobeenable();
		EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

		//Delete DR Plan
		ScrolltoElement(application, "DRplanB_header", xml);
		
		if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
		{
			editedDRplan(application, "DR Plan C", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
		}
		else
		{
			addedDRplan(application, "DR Plan C", rangestart_cc, rangestart_lac, rangestart_num);	
		}
		click_commonMethod(application, "DR Plan C Action dropdown", "DRplanC_actiondropdown", xml);
		
		click_commonMethod(application, "Delete", "DRplan_Deletelink", xml);
		
		deleteDRPlan(application);

	}
	if(Add_DRplanD.equalsIgnoreCase("Yes")) {
		ScrolltoElement(application, "DRplanC_header", xml);
		
		compareText(application, "DR Plan D_header", "DRplanD_header", "DR Plan D", xml);
		click_commonMethod(application, "DR Plan D Action dropdown", "DRplanD_actiondropdown", xml);
		
		click_commonMethod(application, "Add", "DRplanD_E_addlink", xml);
		
		waitforPagetobeenable();
		AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
		compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
		compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
		compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
		compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

		//Edit DR Plan
		ScrolltoElement(application, "DRplanC_header", xml);
		
		addedDRplan(application, "DR Plan D", rangestart_cc, rangestart_lac, rangestart_num);
		click_commonMethod(application, "DR Plan D Action dropdown", "DRplanD_actiondropdown", xml);
		
		click_commonMethod(application, "Edit", "DRplanD_E_editlink", xml);
		
		waitforPagetobeenable();
		EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

		//Delete DR Plan
		ScrolltoElement(application, "DRplanC_header", xml);
		
		if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
		{
			editedDRplan(application, "DR Plan D", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
		}
		else
		{
			addedDRplan(application, "DR Plan D", rangestart_cc, rangestart_lac, rangestart_num);	
		}
		click_commonMethod(application, "DR Plan D Action dropdown", "DRplanD_actiondropdown", xml);
		
		click_commonMethod(application, "Delete", "DRplanD_E_Deletelink", xml);
		
		deleteDRPlan(application);

	}
	if(Add_DRplanE.equalsIgnoreCase("Yes")) {
		ScrolltoElement(application, "DRplanD_header", xml);
		
		compareText(application, "DR Plan E_header", "DRplanE_header", "DR Plan E", xml);
		click_commonMethod(application, "DR Plan E Action dropdown", "DRplanE_actiondropdown", xml);
		
		click_commonMethod(application, "Add", "DRplanD_E_addlink", xml);
		
		waitforPagetobeenable();
		AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
		compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
		compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
		compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
		compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

		//Edit DR Plan
		ScrolltoElement(application, "DRplanD_header", xml);
		
		addedDRplan(application, "DR Plan E", rangestart_cc, rangestart_lac, rangestart_num);
		click_commonMethod(application, "DR Plan E Action dropdown", "DRplanE_actiondropdown", xml);
		
		click_commonMethod(application, "Edit", "DRplanD_E_editlink", xml);
		
		waitforPagetobeenable();
		EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

		//Delete DR Plan
		ScrolltoElement(application, "DRplanD_header", xml);
		
		if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
		{
			editedDRplan(application, "DR Plan E", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
		}
		else
		{
			addedDRplan(application, "DR Plan E", rangestart_cc, rangestart_lac, rangestart_num);	
		}
		click_commonMethod(application, "DR Plan E Action dropdown", "DRplanE_actiondropdown", xml);
		
		click_commonMethod(application, "Delete", "DRplanD_E_Deletelink", xml);
		
		deleteDRPlan(application);
	}
	
	ScrolltoElement(application, "viewpage_backbutton", xml);
	click_commonMethod(application, "Back", "viewpage_backbutton", xml);
	waitforPagetobeenable();
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	System.out.println(  e+ " : Field is not displayed");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	System.out.println(  e+" : Field is not displayed");
}
sa.assertAll();
}

public void AddDRPlan(String application, String rangestart_cc, String rangestart_lac, String rangestart_num, String rangefinish_cc, String rangefinish_lac, String rangefinish_num, String destinationnumber_cc, String destinationnumber_lac, String destinationnumber_num, String activate_deactivateDRplan_dropdownvalue) throws InterruptedException, DocumentException, IOException {

	compareText(application, "Add DR Plan Header", "addDRplan_header1", "Disaster Recovery Plans", xml);
	click_commonMethod(application, "OK", "okbutton", xml);
	

	//verify warning messages
	warningMessage_commonMethod(application, "rangestart_cc_warngmsg", "Range Start CC", xml);
	warningMessage_commonMethod(application, "rangestart_lac_warngmsg", "Range Start LAC", xml);
	warningMessage_commonMethod(application, "rangestart_num_warngmsg", "Range Start Num", xml);
	warningMessage_commonMethod(application, "rangefinish_cc_warngmsg", "Range Finish CC", xml);
	warningMessage_commonMethod(application, "rangefinish_lac_warngmsg", "Range Finish LAC", xml);
	warningMessage_commonMethod(application, "rangefinish_num_warngmsg", "Range Finish Num", xml);
	warningMessage_commonMethod(application, "destinationnumber_cc_warngmsg", "Destination Number CC", xml);
	warningMessage_commonMethod(application, "destinationnumber_lac_warngmsg", "Destination Number LAC", xml);
	warningMessage_commonMethod(application, "destinationnumber_num_warngmsg", "Destination Number Num", xml);

	//verify Add DR plan
	compareText(application, "Range Start", "rangestart_label", "Range Start", xml);
	addtextFields_commonMethod(application, "Range Start CC", "rangestart_cc", rangestart_cc, xml);
	addtextFields_commonMethod(application, "Range Start LAC", "rangestart_lac", rangestart_lac, xml);
	addtextFields_commonMethod(application, "Range Start Num", "rangestart_num", rangestart_num, xml);
	compareText(application, "Range Finish", "rangefinish_label", "Range Finish", xml);
	addtextFields_commonMethod(application, "Range Finish CC", "rangefinish_cc", rangefinish_cc, xml);
	addtextFields_commonMethod(application, "Range Finish LAC", "rangefinish_lac", rangefinish_lac, xml);
	addtextFields_commonMethod(application, "Range Finish Num", "rangefinish_num", rangefinish_num, xml);
	compareText(application, "Destination Number(DN)", "destinationnumber_label", "Destination Number(DN)", xml);
	addtextFields_commonMethod(application, "Destination Number CC", "destinationnumber_cc", destinationnumber_cc, xml);
	addtextFields_commonMethod(application, "Destination Number LAC", "destinationnumber_lac", destinationnumber_lac, xml);
	addtextFields_commonMethod(application, "Destination Number Num", "destinationnumber_num", destinationnumber_num, xml);
	compareText(application, "Activate/Deactivate DR Plan", "activate_deactivate_label", "Activate/Deactivate DR Plan", xml);
	activate_deactivateDRPlan_dropdown(application, "Activate/Deactivate DR Plan", "activate_deactivate_dropdownvalue", activate_deactivateDRplan_dropdownvalue, xml);
	click_commonMethod(application, "OK", "okbutton", xml);
}

public void addedDRplan(String application, String DRPlanHeader, String rangestart_cc, String rangestart_lac, String rangestart_num) throws InterruptedException, DocumentException {

	String RangeStartValue= rangestart_cc+"-"+rangestart_lac+"-"+rangestart_num;
	String AddedDRplan= getwebelement(xml.getlocator("//locators/" + application + "/addedDRplan_tablelist").replace("value", DRPlanHeader)).getAttribute("style");
	if(!AddedDRplan.contains("height: 1px"))
	{
		List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/addedDRplan_rangestart").replace("value", DRPlanHeader));

		int numofrows = results.size();
		System.out.println("no of results: " + numofrows);

		if ((numofrows == 0)) {

			ExtentTestManager.getTest().log(LogStatus.PASS, DRPlanHeader+" table is empty");
		}
		else {
			for (int i = 0; i < numofrows; i++) {
				try {

					String AddedDRplandata = results.get(i).getText();
					System.out.println(AddedDRplandata);
					if (AddedDRplandata.equalsIgnoreCase(RangeStartValue)) {

						String DRPlanRowID= getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@col-id='rangeStart'][text()='"+RangeStartValue+"']/parent::div").getAttribute("row-id");
						System.out.println("DR Plan row id: "+DRPlanRowID);
						if(getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@row-id='"+DRPlanRowID+"']/div//span[contains(@class,'unchecked')]").isDisplayed())
						{
							WebElement AddedDRPlan_Checkbox= getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@row-id='"+DRPlanRowID+"']/div//span[contains(@class,'unchecked')]");
							Clickon(AddedDRPlan_Checkbox);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Added DR Plan checkbox is checked");
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.PASS, "Added DR Plan checkbox is already checked");
						}

					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}
			
		}
	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No DR Plan added in table");
	}

}

public void editedDRplan(String application, String DRPlanHeader, String rangestart_cc, String rangestart_lac, String rangestart_num, String edit_rangestart_cc, String edit_rangestart_lac, String edit_rangestart_num) throws InterruptedException, DocumentException {

	String rangestart_cc_value;
	String rangestart_lac_value;
	String rangestart_num_value;

	if(edit_rangestart_cc.equalsIgnoreCase("null"))
	{
		rangestart_cc_value= rangestart_cc;
	}
	else
	{
		rangestart_cc_value= edit_rangestart_cc;
	}
	if(edit_rangestart_lac.equalsIgnoreCase("null"))
	{
		rangestart_lac_value= rangestart_lac;
	}
	else
	{
		rangestart_lac_value= edit_rangestart_lac;
	}
	if(edit_rangestart_num.equalsIgnoreCase("null"))
	{
		rangestart_num_value= rangestart_num;
	}
	else
	{
		rangestart_num_value= edit_rangestart_num;
	}
	String RangeStartValue= rangestart_cc_value+"-"+rangestart_lac_value+"-"+rangestart_num_value;
	String AddedDRplan= getwebelement(xml.getlocator("//locators/" + application + "/addedDRplan_tablelist").replace("value", DRPlanHeader)).getAttribute("style");
	if(!AddedDRplan.contains("height: 1px"))
	{
		List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/addedDRplan_rangestart").replace("value", DRPlanHeader));

		int numofrows = results.size();
		System.out.println("no of results: " + numofrows);

		if ((numofrows == 0)) {

			ExtentTestManager.getTest().log(LogStatus.PASS, DRPlanHeader+" table is empty");
		}
		else {
			for (int i = 0; i < numofrows; i++) {
				try {

					String AddedDRplandata = results.get(i).getText();
					System.out.println(AddedDRplandata);
					if (AddedDRplandata.equalsIgnoreCase(RangeStartValue)) {

						String DRPlanRowID= getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@col-id='rangeStart'][text()='"+RangeStartValue+"']/parent::div").getAttribute("row-id");
						System.out.println("DR Plan row id: "+DRPlanRowID);
						if(getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@row-id='"+DRPlanRowID+"']/div//span[contains(@class,'unchecked')]").isDisplayed())
						{
							WebElement AddedDRPlan_Checkbox= getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@row-id='"+DRPlanRowID+"']/div//span[contains(@class,'unchecked')]");
							Clickon(AddedDRPlan_Checkbox);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Added DR Plan checkbox is checked");
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.PASS, "Added DR Plan checkbox is already checked");
						}

					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}
			
		}
	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No DR Plan added in table");
	}

}

public void EditDRPlan(String application, String edit_rangestart_cc, String edit_rangestart_lac, String edit_rangestart_num, String edit_rangefinish_cc, String edit_rangefinish_lac, String edit_rangefinish_num, String edit_destinationnumber_cc, String edit_destinationnumber_lac, String edit_destinationnumber_num, String edit_activate_deactivateDRplan_dropdownvalue) throws InterruptedException, DocumentException, IOException {

	compareText(application, "Add DR Plan Header", "addDRplan_header1", "Disaster Recovery Plans", xml);
	compareText(application, "Range Start", "rangestart_label", "Range Start", xml);
	edittextFields_commonMethod(application, "Range Start CC", "rangestart_cc", edit_rangestart_cc, xml);
	edittextFields_commonMethod(application, "Range Start LAC", "rangestart_lac", edit_rangestart_lac, xml);
	edittextFields_commonMethod(application, "Range Start Num", "rangestart_num", edit_rangestart_num, xml);
	compareText(application, "Range Finish", "rangefinish_label", "Range Finish", xml);
	edittextFields_commonMethod(application, "Range Finish CC", "rangefinish_cc", edit_rangefinish_cc, xml);
	edittextFields_commonMethod(application, "Range Finish LAC", "rangefinish_lac", edit_rangefinish_lac, xml);
	edittextFields_commonMethod(application, "Range Finish Num", "rangefinish_num", edit_rangefinish_num, xml);
	compareText(application, "Destination Number(DN)", "destinationnumber_label", "Destination Number(DN)", xml);
	edittextFields_commonMethod(application, "Destination Number CC", "destinationnumber_cc", edit_destinationnumber_cc, xml);
	edittextFields_commonMethod(application, "Destination Number LAC", "destinationnumber_lac", edit_destinationnumber_lac, xml);
	edittextFields_commonMethod(application, "Destination Number Num", "destinationnumber_num", edit_destinationnumber_num, xml);
	compareText(application, "Activate/Deactivate DR Plan", "activate_deactivate_label", "Activate/Deactivate DR Plan", xml);
	activate_deactivateDRPlan_dropdown(application, "Activate/Deactivate DR Plan", "activate_deactivate_dropdownvalue", edit_activate_deactivateDRplan_dropdownvalue, xml);
	click_commonMethod(application, "OK", "okbutton", xml);

}

public void deleteDRPlan(String application) {

	Alert alert = driver.switchTo().alert();		

	// Capturing alert message   
	String alertMessage= driver.switchTo().alert().getText();
	if(alertMessage.isEmpty()) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
		System.out.println("No Message displays"); 
	}else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
		System.out.println("Text message for alert displays as: "+alertMessage);
	}

	try {  
		alert.accept();
		

	}catch(Exception e) {
		e.printStackTrace();
	}
}

public void activate_deactivateDRPlan_dropdown(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml) {

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

				Clickon(getwebelement("//div[label[text()='"+ labelname +"']]/parent::div/following-sibling::div//div[text()='×']"));
				waitforPagetobeenable();

				//verify list of values inside dropdown
				List<WebElement> listofvalues = driver
						.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));

				ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
				System.out.println( " List of values inside "+ labelname + "dropdown is:  ");

				for (WebElement valuetypes : listofvalues) {
					Log.info("service sub types : " + valuetypes.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
					System.out.println(" " + valuetypes.getText());
				}

				
				SendKeys(getwebelement("(//label[text()='"+ labelname +"']/parent::div/parent::div/following-sibling::div//input)[1]"), expectedValueToAdd);	
				

				Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
				waitforPagetobeenable();

				String actualValue=getwebelement("//label[text()='"+ labelname +"']/parent::div/parent::div/following-sibling::div//span").getText();
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

public void verifyDRPlansBulkInterface(String application, String bulkjob_filepath, String ExistingTrunkName) throws InterruptedException, DocumentException {
try {
	waitforPagetobeenable();
	ScrolltoElement(application, "customizedmedia_header", xml);
	
	click_commonMethod(application, "Disaster Recovery Header", "recovery_header", xml);
	
	click_commonMethod(application, "DR Plans Bulk Interface", "DRplans_bulkinterface_link", xml);
	
	waitforPagetobeenable();
	compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
	try {
	WebElement BulkJob_Choosefile_button= getwebelement(xml.getlocator("//locators/" + application + "/bulkjob_choosefilebutton"));
	BulkJob_Choosefile_button.sendKeys(bulkjob_filepath);
	click_commonMethod(application, "Submit", "bulkjobsubmit", xml);
	}
	catch (Exception e) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Upload file path '"+bulkjob_filepath+"' is not found");
	}
	//Archive link in bulk interface page
	click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
	click_commonMethod(application, "Archive", "bulkinterface_archivelink", xml);
	
	waitforPagetobeenable();
	ScrolltoElement(application, "archive_backbutton", xml);
	click_commonMethod(application, "Back", "archive_backbutton", xml);

	//Refresh link in bulk interface page
	scrollToTop();
	click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
	click_commonMethod(application, "Refresh", "bulkinterface_refreshlink", xml);
	waitforPagetobeenable();
	compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
	ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Bulk Interface page refresh successful");
	Log.info("Bulk Interface page refresh successful");
	

	addedBulkInterface(application, ExistingTrunkName);
	ScrolltoElement(application, "bulkinterface_backbutton", xml);
	click_commonMethod(application, "Back", "bulkinterface_backbutton", xml);
	waitforPagetobeenable();
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	System.out.println(  e+ " : Field is not displayed");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	System.out.println(  e+" : Field is not displayed");
}
sa.assertAll();
}

public void verifydownloadDRplans(String application, String DRPlansfilename, String browserfiles_downloadspath) throws InterruptedException, DocumentException {
	try {
	waitforPagetobeenable();
	ScrolltoElement(application, "customizedmedia_header", xml);
	
	click_commonMethod(application, "Disaster Recovery Header", "recovery_header", xml);
	
	click_commonMethod(application, "Download DR Plans", "downloadDRplans_link", xml);
	
	waitforPagetobeenable();
	isFileDownloaded(DRPlansfilename, browserfiles_downloadspath);
	 }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	    	System.out.println(  e+ " : Field is not displayed");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	    	System.out.println(  e+" : Field is not displayed");
	    }
	    sa.assertAll();
}

public void VerifyDisasterRecoveryStatus(String application) throws InterruptedException, DocumentException {

	ScrolltoElement(application, "codecfield_viewpage", xml);
	
	compareText(application, "GSX/PSX Disaster Recovery Status header", "disasterrecoverystatus_header", "GSX/PSX Disaster Recovery Status", xml);
	GetText(application, "PSX TDM DR Status", "psx_tdm_drstatus_value");
	

}

public void addedBulkInterface(String application, String ExistingTrunkName) throws InterruptedException, DocumentException {

	compareText(application, "TrunkGroup Name", "trunkgroupname_columnheader", "TrunkGroup Name", xml);
	compareText(application, "User Name", "username_columnheader", "User Name", xml);
	compareText(application, "Submit Time", "submittime_columnheader", "Submit Time", xml);
	compareText(application, "Start Time", "starttime_columnheader", "Start Time", xml);
	compareText(application, "End Time", "endtime_columnheader", "End Time", xml);
	compareText(application, "Status", "status_columnheader", "Status", xml);
	compareText(application, "Completion (%)", "completion_columnheader", "Completion (%)", xml);
	compareText(application, "Log", "log_columnheader", "Log", xml);
	compareText(application, "Upload File", "uploadfile_columnheader", "Upload File", xml);

	int TotalPages;
	String TotalPagesText = getwebelement("//div[@class='ag-div-margin row']//div//span[@ref='lbTotal']").getText();
	TotalPages = Integer.parseInt(TotalPagesText);
	System.out.println("Total number of pages in table is: " + TotalPages);

	if (TotalPages != 0) {

		outerloop:
			for (int k = 1; k <= TotalPages; k++) {
				String AddedDRplan_BulkInterface= getwebelement(xml.getlocator("//locators/" + application + "/addedbulkinterface_tablelist")).getAttribute("style");
				if(!AddedDRplan_BulkInterface.contains("height: 1px"))
				{
					List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/addedbulkinterface_trunkgroupname").replace("value", ExistingTrunkName));

					int numofrows = results.size();
					System.out.println("no of results: " + numofrows);

					if ((numofrows == 0)) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "Bulk Interface table is empty");
					}
					else {
						// Current page
						String CurrentPage = getwebelement("//div[@class='ag-div-margin row']//div//span[@ref='lbCurrent']").getText();
						int Current_page = Integer.parseInt(CurrentPage);
						System.out.println("The current page is: " + Current_page);

						sa.assertEquals(k, Current_page);

						System.out.println("Currently we are in page number: " + Current_page);
						for (int i = 0; i < numofrows; i++) {
							try {

								String AddedBulkInterfacedata = results.get(i).getText();
								System.out.println(AddedBulkInterfacedata);
								if (AddedBulkInterfacedata.equalsIgnoreCase(ExistingTrunkName)) {

									String BulkInterfaceRowID= getwebelement(xml.getlocator("//locators/" + application + "/addedbulkinterface_rowid").replace("value", ExistingTrunkName)).getAttribute("row-id");
									System.out.println("Bulk Interface row id: "+BulkInterfaceRowID);
									String BulkInterface_Trunkgroupname= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_trunkgroupname_value").replace("value", BulkInterfaceRowID)).getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Trunk Group Name column value:"+BulkInterface_Trunkgroupname);
									String BulkInterface_Username= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_username_value").replace("value", BulkInterfaceRowID)).getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User Name column value:"+BulkInterface_Username);
									String BulkInterface_SubmitTime= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_submittime_value").replace("value", BulkInterfaceRowID)).getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Submit Time column value:"+BulkInterface_SubmitTime);
									String BulkInterface_startTime= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_starttime_value").replace("value", BulkInterfaceRowID)).getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Start Time column value:"+BulkInterface_startTime);
									String BulkInterface_endTime= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_endtime_value").replace("value", BulkInterfaceRowID)).getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step : End Time column value:"+BulkInterface_endTime);
									String BulkInterface_status= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_status_value").replace("value", BulkInterfaceRowID)).getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Status column value:"+BulkInterface_status);
									String BulkInterface_completionPercentage= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_completion_value").replace("value", BulkInterfaceRowID)).getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Completion Percentage column value:"+BulkInterface_completionPercentage);
									String BulkInterface_Log= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_log").replace("value", BulkInterfaceRowID)).getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Log column value:"+BulkInterface_Log);
									String BulkInterface_Uploadfile= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_file").replace("value", BulkInterfaceRowID)).getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Upload file column value:"+BulkInterface_Uploadfile);
									break outerloop;
								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}
						}
						
					}
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No Bulk Interface added in table");
				}
			}

	}else {

		System.out.println("No data available in table");
		Log.info("No data available in table");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No data available in table");
	}
}
	
	public void navigateToViewTrunkPage(String application, String TrunkGroupSiteOrderNumber,  String ExistingTrunkName) throws InterruptedException, DocumentException {
		
		//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrderPanel_header")));
		
		boolean TrunkNameToAddCPEDeviceText=false;
		boolean trunkgrupOrderErrMsg= false;
		boolean TrunkGroupSiteOrderNumberText=false;
		waitforPagetobeenable();
		
		try {
		scrolltoend();
		System.out.println("Scroll To Trunk Panel");
		Thread.sleep(6000);
		TrunkNameToAddCPEDeviceText= getwebelement("//div[text()='"+ExistingTrunkName+"']").isDisplayed();
		System.out.println("Scroll To Trunk Panel2");
		if(TrunkNameToAddCPEDeviceText) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Expected 'Trunk Name' is displaying as expected under Trunk Panel in 'view Service' page");
			System.out.println("Expected 'Trunk Name'' is displaying as expected under Trunk Panel in 'view Service' page");
		
			scrolltoend();

		//click(application, "Trunk Name in Trunk panel", "//div[text()='"+ExistingTrunkName+"']");//Select Checkbox for expected trunk Not working
		WebElement CheckboxForExpectedTrunk = driver.findElement(By.xpath("//div[text()='"+ExistingTrunkName+"']"));
		String TrunkName = CheckboxForExpectedTrunk.getText().toString();
		CheckboxForExpectedTrunk.click();
		ExtentTestManager.getTest().log(LogStatus.PASS,"Step : Checkbox Selection is done for Expected trunk, Selected Trunk is " + TrunkName);
		Log.info("Selected Trunk is  : " + TrunkName);
		
		
		  String TunkActionLinkXpathInViewServicePage="//div[div[span[contains(text(),'"+TrunkGroupSiteOrderNumber+"')]]]/following-sibling::div//button[text()='Action']";
		  
		//**click_commonMethod_PassingWebelementDirectly(application, "Trunk Action", TunkActionLinkXpathInViewServicePage, xml);
		click(application, "ACTION LINK in Trunk panel", "ViewService_Trunk_ActionLink1");
													
		
		String ViewTrunkLinkXpathInViewServicePage="//div[div[span[contains(text(),'"+TrunkGroupSiteOrderNumber+"')]]]/following-sibling::div//a[text()='View']";
		//**click_commonMethod_PassingWebelementDirectly(application, "View Link", ViewTrunkLinkXpathInViewServicePage, xml);
		click(application, "View Trunk Link", "ViewService_Trunk_ViewTrunkLink");
		
		}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Expected 'Trunk Name' is not displaying as expected under Trunk Panel in 'view Service' page");
				System.out.println("Expected 'Trunk Name'' is not displaying as expected under Trunk Panel in 'view Service' page");
			  }
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
sa.assertAll();

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
	
	
	
	
	
	
	
	
	public void verifyEditTrunkFunction(String application, String customerName, String servicename, String ServiceType, String PrimaryTrunk, String voipProtocol,
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
		
		waitforPagetobeenable();
		
		
		try {
		scrollToTop();
	//Action button	
		click_commonMethod(application, "Action", "ViewTrunkPage_ActionButton", xml);//viewPage_ActionButton
		
	//click on Edit link
		click_commonMethod(application, "Edit", "ViewTrunkPage_EditLink", xml);//editLink//ViewTrunkPage_EditLink
		Thread.sleep(7000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/EditTrunk_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Trunk' Page navigated as expected");
			System.out.println("'Edit Trunk' Page navigated as expected");
		//scrollToTop();
		
		
		try {
		
				//Trunk Group Description
			String expectedValue1=customerName+"_"+servicename+"_"+ServiceType.toUpperCase();
			compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

		
				//Primary Trunk	
		//**SelectDropdownValueUnderSelectTag(application, "Primary Trunk", PrimaryTrunk, "PrimaryTrunk_Dropdown", xml);
		
			
				//VOIP Protocol
			SelectDropdownValueUnderSelectTag(application, "VOIP Protocol", voipProtocol, "voipProtocol_Dropdown", xml);
		
			
			//Billing COuntry
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
		
		
		
			//Quality
		SelectDropdownValueUnderSelectTag(application, "Quality", quality, "quality_Dropdown", xml);
		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");
		
		
			//IP Address Type
		SelectDropdownValueUnderSelectTag(application, "IP Address Type", ipAddresstype, "IPaddresstype_Dropdown", xml);

		WebElement IPAddressType=getwebelement(xml.getlocator("//locators/" + application + "/IPaddresstype_Dropdown"));
		scrolltoview(IPAddressType);
		
		
		
//			//IP PBX Range  (Need to Update)
//		EnterTextValue(application, IPPBXRange, "IP PBX Range text field", "IPPBXRange_textField");
//		click_commonMethod(application, "Click on >>", "IPPBXRange_addButtton", xml);
//		GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/IPPBXRange_addedValue_selectDropdownField")), "IP PBX range Selection Box)");
//		
		
			//IP PBX Address
		EnterTextValue(application, IPPBXAddress, "IP PBX Address text field", "IPPBXAddress_textField");
		ClearAndEnterTextValue(application, "IP PBX Address text field", "IPPBXAddress_textField", IPPBXAddress, xml);
		
		
		
			//SIP Signalling Port
	  if(voipProtocol.equalsIgnoreCase("SIP")) {
		  ClearAndEnterTextValue(application, "SIP Signaling Port", "SIPsignallingport_textField", SIPsignallingPort, xml);
		  
		  //message displaying under "SIP Signalling Port" text field	
			methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port:5060");
	  }else {
		  ExtentTestManager.getTest().log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
		  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
	  }
		

	  
	  
	  
	  	//Splitting the Gateway functionality into 2 SBC & Non-SBC 
	  if(!gateway.contains("SBC")) {    //CASE-1 For NO-SBC
		
		  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {  //case A with Interface Based Customer selection
			  
			  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
			  
			//Internet Based Customer
				addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
				
			  
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				 
				  ExtentTestManager.getTest().log(LogStatus.PASS, "When 'Internet Based Customer' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
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
						ClearAndEnterTextValue(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

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
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' field is  a mandatory field and not values are passed as an input");
				
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
				ClearAndEnterTextValue(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

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
					 
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is a mandatory field. No values has been passed as an input");
				 
					//IP Interface
					  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					  if(ipInterface_Enabled) {
						  System.out.println("'Ip Interface' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					  }else {
						  System.out.println("'Ip Interface' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					  }
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					  if(addressContext_Enabled) {
						  System.out.println("'Address Context' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					  }else {
						  System.out.println("'Address Context' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					  if(ipInterfaceGroup_Enabled) {
						  System.out.println("'IP Inteface Group' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					  }else {
						  System.out.println("'IP Inteface Group' text field is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
					
					  
				//Signalling port
						signallingPort(application, gateway);	 
						
						
			
				  }else if(!vlanTag.equalsIgnoreCase("null")) {
					 
					//VLAN Tag
					  ClearAndEnterTextValue(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

						//IP Interface
						  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
						  if(ipInterface_Enabled) {
							  System.out.println("'Ip Interface' text field is enabled");
							  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						  }else {
							  System.out.println("'Ip Interface' text fieldis disabled");
							  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						  }
						  
						  ipInterface = ipInterfaceDEfaultValue +vlanTag;
						  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
						  
					//Address Context
						  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						  if(addressContext_Enabled) {
							  System.out.println("'Address Context' text field is enabled");
							  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						  }else {
							  System.out.println("'Address Context' text fieldis disabled");
							  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
						  }
						  
						  addressContext=addressContextDefaultValue+vlanTag;
						  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
						
						  
					//IP Interface Group
						  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						  if(ipInterfaceGroup_Enabled) {
							  System.out.println("'IP Inteface Group' text field is enabled");
							  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						  }else {
							  System.out.println("'IP Inteface Group' text field is disabled");
							  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						  }
						  
						  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanTag;
						  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				 
				  }
				  
				  
				  
				  
			  }else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
					//Internet Based Customer
						addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
						
				  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				  if(vlanDefaultvalue.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
				  }else {
					//VLAN tag  
					  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
					  if(VlanEnability) {
						  System.out.println("VLAN Tag is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
					  }else {
						  System.out.println("VLAN Tag is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
					  } 
				  }
				  
					//IP Interface
				  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
				  if(ipInterface_Enabled) {
					  System.out.println("'Ip Interface' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
				  }else {
					  System.out.println("'Ip Interface' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
				  }
				  
				  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
				  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
				  
			//Address Context
				  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
				  if(addressContext_Enabled) {
					  System.out.println("'Address Context' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
				  }else {
					  System.out.println("'Address Context' text fieldis disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
				  }
				  
				  addressContext=addressContextDefaultValue+vlanDefaultvalue;
				  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
				
				  
			//IP Interface Group
				  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
				  if(ipInterfaceGroup_Enabled) {
					  System.out.println("'IP Inteface Group' text field is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
				  }else {
					  System.out.println("'IP Inteface Group' text field is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
					  }else {
						  System.out.println("VLAN Tag is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
					  }
					  
				//IP Interface
					  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					  if(ipInterface_Enabled) {
						  System.out.println("'Ip Interface' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					  }else {
						  System.out.println("'Ip Interface' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					  }
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					  if(addressContext_Enabled) {
						  System.out.println("'Address Context' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					  }else {
						  System.out.println("'Address Context' text fieldis disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					  if(ipInterfaceGroup_Enabled) {
						  System.out.println("'IP Inteface Group' text field is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					  }else {
						  System.out.println("'IP Inteface Group' text field is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					  }
					  
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
					
					  
				//Signalling port
						signallingPort(application, gateway);	  
						
				}
			 
			 if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
				//Internet Based Customer
					addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
					
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				//VLAN tag  
				  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
				  if(VlanEnability) {
					  System.out.println("VLAN Tag is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
				  }else {
					  System.out.println("VLAN Tag is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
				  } 
			  }
			  
				//IP Interface
			  boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
			  if(ipInterface_Enabled) {
				  System.out.println("'Ip Interface' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
			  }else {
				  System.out.println("'Ip Interface' text fieldis disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
			  }
			  
			  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
			  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
			  
		//Address Context
			  boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
			  if(addressContext_Enabled) {
				  System.out.println("'Address Context' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
			  }else {
				  System.out.println("'Address Context' text fieldis disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
			  }
			  
			  addressContext=addressContextDefaultValue+vlanDefaultvalue;
			  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
			
			  
		//IP Interface Group
			  boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
			  if(ipInterfaceGroup_Enabled) {
				  System.out.println("'IP Inteface Group' text field is enabled");
				  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
			  }else {
				  System.out.println("'IP Inteface Group' text field is disabled");
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
		  ClearAndEnterTextValue(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
	  }else {
		  ClearAndEnterTextValue(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
	  }
		


	  		//Signalling Transport Protocol
		selectValueInsideDropdown(application, "signallingTransportProtocol_Dropdown", "Signalling Transport Protocol", signallingtransportProtocol, xml);

		WebElement internetBasedCusotmerView=getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox"));
		scrolltoview(internetBasedCusotmerView);
		
		
		
		if(signallingtransportProtocol.equalsIgnoreCase("sip-tls-tcp")) {
			
			//TLS Profile
			ClearAndEnterTextValue(application, "TLS Profile", "TLS_textField", TLSfield, xml);

			//SRTP
			addCheckbox_commonMethod(application, "srtp_checkbox", "SRTP", srtp, "no", xml);
		}
		
		
			// Customer Default Number	CustomerDefaultNumber_WarningMessage
		ClearAndEnterTextValue(application, "Customer Default Number", "CustomerDefaultNumber_textfield", CustomerDefaultNumber, xml);

		
		
		//Show All
//				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CustomerDefaultNumber_Header")));
//				click_commonMethod(application, "Show ALL Button", "ShowAllButton_ViewTrunk", xml);
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/AddTrunk_CENTREX_Header")));
				
		
//		  	// Reuse Network Selector Table Checkbox
//			if(getwebelement(xml.getlocator("//locators/" + application + "/ReuseNetworkSelectorTable_checkbox")).isEnabled()){
//				if (ReuseNetworkSelectorTable.equalsIgnoreCase("YES")) {
//					click(application, "'Reuse Network Selector Table' checkbox", "ReuseNetworkSelectorTable_checkbox");
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//					System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//				} else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
//					System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
//				}
//			
//				}else {
//					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//					System.out.println("Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//				}
		  
			// Reuse Network Selector Table
			addCheckbox_commonMethod(application, "ReuseNetworkSelectorTable_checkbox", "Reuse Network Selector Table", ReuseNetworkSelectorTable, "Yes", xml);
			

			
//			// Reuse Network Selector Table Checkbox
//					if(getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox")).isEnabled()){
//						if (ReuseNetworkSelectorTable.equalsIgnoreCase("YES")) {
//							click(application, "'Reuse Network Selector Table' checkbox", "reuseNIFgroup_checkbox");
//							ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//						} else {
//							ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
//						}
//					
//						}else {
//							ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//						}
			//Reuse NIF Group
			addCheckbox_commonMethod(application, "reuseNIFgroup_checkbox", "Reuse NIF Group", reuseNIFgroup, "Yes", xml);
			
//			// Reuse Network Selector Table Checkbox
//					if(getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox")).isEnabled()){
//						if (ReuseNetworkSelectorTable.equalsIgnoreCase("YES")) {
//							click(application, "'Reuse Network Selector Table' checkbox", "reuseNIFgroup_checkbox");
//							ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is Enabled and 'Reuse Network Selector Table' checkbox Selected");
//						} else {
//							ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is not Selected");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is not Selected");
//						}
//					
//						}else {
//							ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//							System.out.println("Step : 'Reuse Network Selector Table' checkbox is Disabled, So can't make any changes in Checkbox status");
//						}
					
			//Reuse Sig Zone/Part
			addCheckbox_commonMethod(application, "reuseSigZonePart_checkbox", "Reuse Sig Zone/Part", reuseSigZonePart, "Yes", xml);
		
		
		
		
			// Colt Signalling IP
			ClearAndEnterTextValue(application, "Colt Signalling IP", "coltSignalingIP_textField", coltSignalingIP, xml);

		
			//Media IP
			  ClearAndEnterTextValue(application, "Media IP", "mediaIP_textField", mediaIP, xml);
		
		
		
		
			// PBX Type	
			  ClearAndEnterTextValue(application, "PBX Type", "PBXType_textfield", PBXTYPE, xml);

			// PBX Profile
		selectValueInsideDropdown(application, "PBXProfile_Dropdown", "PBX Profile", PBXProfile, xml);


		
				// PSX Manual Configuration-Trunk Group	
			addCheckbox_commonMethod(application, "PSXManualConfigurationTrunkGroup_checkbox", "PSX Manual Configuration-Trunk Group", PSXManualConfigurationTrunkGroup, "Yes" ,xml);
			
				// PSX Manual Configuration-DDI-Range
			addCheckbox_commonMethod(application, "PSXManualConfigurationDDIRange_checkbox", "PSX Manual Configuration-DDI-Range", PSXManualConfigurationDDIRange, "Yes" ,xml);


				// Manual Configuration On GSX
			addCheckbox_commonMethod(application, "ManualConfigurationonGSX_checkbox", "Manual Configuration On GSX", ManualConfigurationonGSX, "Yes", xml);
			
			
			
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/NonServiceImpacting_Header")));
			//Call Admission Control
		addCheckbox_commonMethod(application, "callAdmissionControl_checkbox", "Call Admission Control", callAdmissionControl, "Yes", xml);

		if(callAdmissionControl.equalsIgnoreCase("yes")) {
			//Call Limit
			selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", CallLimitDropdwon, xml);
			
			if(CallLimitDropdwon.equalsIgnoreCase("Defined")) {
				addtextFields_commonMethod(application, "Limit Number", "limitNumber_textField", limitNumber, xml);
				
			}
		}
		
		
			//Call Rate Limit
		addCheckbox_commonMethod(application, "callrateLimit_checkbox", "Call Rate Limit", CallRateLimitCheckboxSelection, "Yes", xml);
		if(CallRateLimitCheckboxSelection.equalsIgnoreCase("yes")) {
			
			String callratelimitactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callRateLimitt_textField")).getAttribute("value");
			System.out.println(" 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
		
			if(!callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(callrateLimiteValue);
					
				if(i>100) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
				System.out.println("'Call rate Limit' value is not edited");
			}
		}
		
		
		
		
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/SIPSERVICES_Header")));
			//Source Address Filtering
		selectValueInsideDropdown(application, "sourceAddressFiltering_Dropdown", "Source Address Filtering", sourceAddressFiltering, xml);
		
			//100rel Support
		selectValueInsideDropdown(application, "relsupport_Dropdown", "100rel Support", relSupport, xml);
		
			//SIP Session Keepalive Timer(Sec)
		ClearAndEnterTextValue(application, "SIP Session Keepalive Timer(Sec)", "sipSessionKeepAliverTimer_textField", sipSessionkeepAliveTimer, xml);


		//Text message under "SIP Session Keepalive timer"
			methodToFindMessagesUnderTextField(application, "defaultTextMessageUnderSIPsessionTimer", "SIP Session Keepalive Timer(Sec)", "Default SIP Session Keepalive Timer (sec): 1800");
		
			
			
			
			
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/PSXTRUNKGROUPFIELDS_Header")));
			//Carrier
			ClearAndEnterTextValue(application, "Carrier textfield", "carriers_TextField", Carrier);
			
			
			//IP Signalling Profile
			ClearAndEnterTextValue(application, "IP Signaling Profile", "IPsignallingProfile_textField", IPSignalingProfile);
					
					
			//Egress IP Signaling Profile
			ClearAndEnterTextValue(application, "Egress IP Signaling Profile", "EgressipSignal_TextField", EgressIPSignalingProfile);

		
			
			WebElement reusenifgroup=getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox"));
			scrolltoview(reusenifgroup);
			
			
		
		scrolltoend();
		
		
		click_commonMethod(application, "OK", "trunk_okButton", xml);
		GetText(application, "Trunk Update Success Message", "UpdateTrunkSuccessMessage");
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Trunk page");
			System.out.println(  e+ " : Field is not displayed in Edit Trunk page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Edit Trunk page ");
			System.out.println(  e+" : Field is not displayed in Edit Trunk page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit Trunk' Page is not navigated");
			System.out.println("'Edit Trunk' Page is not navigated");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		sa.assertAll();
		
	  }
	  
	 //Edit DONE

	
	
	
	
	
	
	
	
	
	
	public void editTrunk2(String application,String customerName, String servicename,String trunktype, String edit_TrunkType, String edit_VOIPprotocol, String edit_BillingCountry, String edit_CDRdelivery,
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
		
		waitforPagetobeenable();
		scrollToTop();
	//Action button	
		click_commonMethod(application, "Action", "ViewTrunkPage_ActionButton", xml);//viewPage_ActionButton
		
	//click on Edit link
		click_commonMethod(application, "Edit", "ViewTrunkPage_EditLink", xml);//editLink//ViewTrunkPage_EditLink
		
		waitforPagetobeenable();
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
				
				String voipProtocol_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "voipProtocol_Dropdown", "VOIP Protocol");
				
				System.out.println("The voip protocol displaying is "+ voipProtocol_actualvalue);
				String voip_code=voipProtocol_code(application, voipProtocol_actualvalue);
				
			//CDR Delivery
				selectValueInsideDropdown(application, "CDRdelivery_Dropdown", "CDR Delivery", edit_CDRdelivery, xml);
				
				String cdrDelivery_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "CDRdelivery_Dropdown", "CDR Delivery");
				
				String CDR_code=CDRdelivery_code(application, cdrDelivery_actualvalue);
			
			//Prefix
					 edittextFields_commonMethod(application, "Prefix", "prefix_textField", editPrefix, xml);
					 

			//valdate the prefix value for adding into Trunk group Name field	 
				 String preifxValueInsidetextField=getwebelement(xml.getlocator("//locators/" + application + "/prefix_textField")).getAttribute("value");
				
				 int prefixSize=preifxValueInsidetextField.length(); 
				 if(prefixSize==3) {
					 prefix_code=preifxValueInsidetextField;
				}else if(prefixSize==4) {
					prefix_code=preifxValueInsidetextField.substring(1);
				}else if(prefixSize<3) {
					System.out.println("Prefix value cannot be less than 3");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Prefix value cannot be less than 4. Value is displaying as "+preifxValueInsidetextField);
				}else if(prefixSize>4) {
					System.out.println("Prefix value cannot be greater than 4");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Prefix value cannot be greater than 4. Value is displaying as "+preifxValueInsidetextField);
				}
				 
			//Gateway
				selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", editGateway, xml);
				String gateway_actualValue=GetTheSelectedValueInsideDropdown_trunk(application, "gateway_Dropdown", "Gateway");
				gatewayCode=gateway_code(application, gateway_actualValue);
				
				WebElement trunk_type=getwebelement(xml.getlocator("//locators/" + application + "/trunkType_Dropdown"));
				scrolltoview(trunk_type);
				
				
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
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Trunk Group NAme' length is: "+totalLen);
					compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
				}
				
			//Traffic Directions 
				selectValueInsideDropdown(application, "trafficDirection_Dropdown", "Traffic Directions", editTrafficDirection, xml);
				
			//IP Address Type
				selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", edit_IpAddressType, xml);
			
				WebElement traficDirection=getwebelement(xml.getlocator("//locators/" + application + "/trafficDirection_Dropdown"));
				scrolltoview(traficDirection);
				
				
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
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
				  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
			  }
			  
			
			//Internet Based Customer
			  editcheckbox_commonMethod(application, edit_internetBasedCustomer, "internetBasedCustomer_checkbox", "Internet Based Customer", xml);
			  
		    
			//VLAN Tag
			  edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", edit_vlantag, xml);
			  String vlan_actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  
			  
			//Sub Interface Slot
			  selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", edit_subInterfaceSlot, xml);
			  
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
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
				
					if(!edit_callrateLimitvalue.equalsIgnoreCase("null")) {
						int i=Integer.parseInt(edit_callrateLimitvalue);
							
						if(i>100) {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less 100 for all Trunks");
						}
						else if(i<=100){
							edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", edit_callrateLimitvalue, xml);
						}
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
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
				
				
				click_commonMethod(application, "OK", "OKbutton", xml);
				
				
					
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void fetchDisabledFieldValue(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
		
//		String actualvalue=null;
		WebElement ele=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		
		boolean elementEnabled=ele.isEnabled();
		
		if(elementEnabled) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is disabled");
			System.out.println(labelname + " is disabled");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, labelname + "is disabled as expected");
			System.out.println(labelname + "is disabled as expected");
			
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
		
		
		scrolltoend();
		
		
		click_commonMethod(application, "OK", "OKbutton", xml);
		
		scrollToTop();
		
		
		//Primary Trunk Group
		System.out.println("Primary trunk group value: "+primarytrunkGroupname);
		selectValueInsideDropdown(application, "primaryTrunkGroup_Dropdown", "Primary Trunk Group" , primarytrunkGroupname, xml);
		
		
		//Trunk Type
		fetchDisabledFieldValue(application, "Trunk Type", "trunkType_Dropdown");
		String trunktypeSelected=GetTheSelectedValueInsideDropdown_trunk(application, "trunkType_Dropdown", "Trunk Type");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'Trunk Type' field is displaying as:"+trunktypeSelected);
		System.out.println("Value for 'Trunk Type' field is displaying as:"+trunktypeSelected);
		
		//VOIP Protocol
		fetchDisabledFieldValue(application, "VOIP Protocol", "voipProtocol_Dropdown");
		String voipProtocolSelected=GetTheSelectedValueInsideDropdown_trunk(application, "voipProtocol_Dropdown", "VOIP Protocol");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'VOIP Protocol' field is displaying as:"+voipProtocolSelected);
		System.out.println("Value for 'VOIP Protocol' field is displaying as:"+voipProtocolSelected);
		
		selectValueInsideDropdown(application, "voipProtocol_Dropdown", "VOIP Protocol", voip_resilientTrunk, xml);
		String voipProtocoledited=GetTheSelectedValueInsideDropdown_trunk(application, "voipProtocol_Dropdown", "VOIP Protocol");  //select value under dropdown
		
		
		//Billing Country
		fetchDisabledFieldValue(application, "Billing Country", "billingCoutry_Dropdown");
		String coutrySelected=GetTheSelectedValueInsideDropdown_trunk(application, "billingCoutry_Dropdown", "Billing Country");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'Billing Country' field is displaying as:"+coutrySelected);
		System.out.println("Value for 'Billing Country' field is displaying as:"+coutrySelected);
		
		//CDR Delivery
		fetchDisabledFieldValue(application, "CDR Delivery", "CDRdelivery_Dropdown");
		String cdrSelected=GetTheSelectedValueInsideDropdown_trunk(application, "CDRdelivery_Dropdown", "CDR Delivery");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'CDR Delivery' field is displaying as:"+cdrSelected);
		System.out.println("Value for 'CDR Delivery' field is displaying as:"+cdrSelected);
		
		//Prefix
		fetchDisabledFieldValue(application, "Prefix", "prefix_textField");
		String prefixSelected=GetTheSelectedValueInsideDropdown_trunk(application, "prefix_textField", "Prefix");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'Prefix' field is displaying as:"+prefixSelected);
		System.out.println("Value for 'Prefix' field is displaying as:"+prefixSelected);
		
		
		//Gateway
		fetchDisabledFieldValue(application, "Gateway", "gateway_Dropdown");
		String gatewaySelected=GetTheSelectedValueInsideDropdown_trunk(application, "gateway_Dropdown", "Gateway");
		selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", gateway_resilientTrunk, xml);
		
		//Quality
		fetchDisabledFieldValue(application, "Quality", "quality_Dropdown");
		String qualitySelected=GetTheSelectedValueInsideDropdown_trunk(application, "quality_Dropdown", "Quality");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'Prefix' field is displaying as:"+qualitySelected);
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
					  ExtentTestManager.getTest().log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
					  System.out.println("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
				  }
			
			
			 
		
		
	}
	
	public String GetTheSelectedValueInsideDropdown_trunk(String application , String xpath, String labelname) throws IOException, InterruptedException, DocumentException
	{ 
		String selectedValue=null;
		
	try {	
		WebElement el=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		
		Select s1=new Select(el);
		WebElement option = s1.getFirstSelectedOption();
		System.out.println(option);
		selectedValue = option.getText();	
		
		
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
		System.out.println(labelname + " field is not displaying");
	}
		
		return selectedValue;
	
	}
	
	public void GetTheValuesInsideDropdown(WebElement el, String labelname) throws IOException, InterruptedException
	{
		
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
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value displaying under "+labelname+" is: "+ ls);
	
	}	
	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		

	

			////////////////////	Add CPE DEVICE   //////////////////////////


	public void verifyAddCPEDeviceFunction(String application, String ServiceIdentification, String TrunkGroupSiteOrderNumber, String ExistingTrunkName,
			String AddNewCPEDevice, String AddExistingCPEDevice , String  CPE_RouterID,  String  CPE_DeviceName, String  CPE_VendorModel,  String   CPE_ManagementAddress,  String  CPE_Snmpro,  String  CPE_Snmprw ,String   CPE_SnmpV3ContextName,  String   CPE_SnmpV3ContextEngineID,  String  CPE_SnmpV3SecurityUsername ,  String  CPE_SnmpV3AuthProto , String   CPE_SnmpV3AuthPassword,  String   CPE_Country,  String   CPE_City,  String CPE_Site ,  String CPE_Premise) throws Exception {
		
		boolean TrunkNameToAddCPEDeviceText=false;
		boolean trunkgrupOrderErrMsg= false;
		boolean TrunkGroupSiteOrderNumberText=false;
		WebElement Trunknumber1=null;
		
		waitforPagetobeenable();
		try {
		ScrolltoElement(application, "addTrunkSiteOrderlink", xml);
		
			
		//// OR
		//***TrunkNameToAddCPEDeviceText= getwebelement(xml.getlocator("//locators/" +application+ "/selectTrunkName").replace("value", ExistingTrunkName)).isDisplayed();
		TrunkNameToAddCPEDeviceText= getwebelement("//div[text()='"+ExistingTrunkName+"']").isDisplayed();
		if(TrunkNameToAddCPEDeviceText) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Expected 'Trunk Name' is displaying as expected under Trunk Panel in 'view Service' page");
			System.out.println("Expected 'Trunk Name'' is displaying as expected under Trunk Panel in 'view Service' page");
			
		//click(application, "Trunk Name in Trunk panel", "//div[text()='"+ExistingTrunkName+"']");//Select Checkbox for expected trunk Not working
		WebElement CheckboxForExpectedTrunk = driver.findElement(By.xpath("//div[text()='"+ExistingTrunkName+"']"));
		String TrunkName = CheckboxForExpectedTrunk.getText().toString();
		CheckboxForExpectedTrunk.click();
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS,"Step : Checkbox Selection is done for Expected trunk, Selected Trunk is " + TrunkName);
		Log.info("Selected Trunk is  : " + TrunkName);
		click(application, "ACTION LINK in Trunk panel", "ViewService_Trunk_ActionLink1");
		click(application, "Add CPE Device Link", "ViewService_Trunk_AddCPEDeviceLink1");
		
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEDevice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add CPE Device' navigated as expected");
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
			
			
			SelectDropdownValueUnderSpanTag(application, "Vendor/Model", CPE_VendorModel, "CPE_VendorModelDropdown", "commonDropdownValueTag", xml); 

			EnterTextValue(application, CPE_ManagementAddress, "Management Address", "CPE_ManagementAddressTextfield");
			EnterTextValue(application, CPE_Snmpro, "Snmpro", "CPE_SnmproTextfield");
			EnterTextValue(application, CPE_Snmprw, "Snmprw", "CPE_SnmprwTextfield");
			EnterTextValue(application, CPE_SnmpV3ContextName, "Snmp v3 Context Name", "CPE_Snmpv3ContextNameTextfield");
			EnterTextValue(application, CPE_SnmpV3ContextEngineID, "Snmp V3 Context Engine ID", "CPE_snmpv3ContextEngineIdTextfield");
			EnterTextValue(application, CPE_SnmpV3SecurityUsername, "Snmp V3 Security Username", "CPE_snmpv3SecurityUserNameTextfield");
			EnterTextValue(application, CPE_SnmpV3AuthPassword, "Snmp V3 Auth Password", "CPE_snmpv3AuthPasswordTextfield");
			
			scrolltoend();
			SelectDropdownValueUnderSelectTag(application, "Country", CPE_Country, "CPE_CountryDropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "City", CPE_City, "CPE_CityDropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Site", CPE_Site, "CPE_SiteDropdown", xml);

			//**SelectDropdownValueUnderSelectTag(application, "Premise", CPE_Premise, "CPE_PremiseDropdown", "commonDropdownValueTag");

			click(application, "OK Button", "trunk_okButton");
			
//			compareText(application, "Add CPE Device Success Message", "CPE_AddCPEDeviceSuccessMessage", "CPE Device added successfully");
			verifysuccessmessage(application, "CPE Device added successfully");
			}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add CPE Device page");
			System.out.println(  e+ " : Field is not displayed in Add CPE Device page");
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add CPE Device page ");
			System.out.println(  e+" : Field is not displayed in Add CPE Device page");
			}

			
				}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add CPE Device' page not navigated");
				System.out.println("'Add CPE Device' page not navigated");
					  }
			
				}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Expected 'Trunk Name' is not displaying as expected under Trunk Panel in 'view Service' page");
				System.out.println("Expected 'Trunk Name'' is not displaying as expected under Trunk Panel in 'view Service' page");
						}
				}
		 }catch(NoSuchElementException e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		    	System.out.println(  e+ " : Field is not displayed");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		    	System.out.println(  e+" : Field is not displayed");
		    }
		    sa.assertAll();
}
		



	
	
	
	
	
	
	
	
	
	
	
	
	public void verifyAddedCPEDeviceInformation_View(String application, String ServiceIdentification, String TrunkGroupSiteOrderNumber, String ExistingTrunkName,
			String AddNewCPEDevice, String AddExistingCPEDevice , String  CPE_RouterID,  String  CPE_DeviceName, String  CPE_VendorModel,  String   CPE_ManagementAddress, 
			String  CPE_Snmpro,  String  CPE_Snmprw ,String   CPE_SnmpV3ContextName,  String   CPE_SnmpV3ContextEngineID,  String  CPE_SnmpV3SecurityUsername ,  
			String  CPE_SnmpV3AuthProto , String   CPE_SnmpV3AuthPassword,  String   CPE_Country,  String   CPE_City,  String CPE_Site ,  String CPE_Premise
			) throws InterruptedException, DocumentException, IOException {
try {
		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		
		click(application, "View Link For CPE Device under Trunk panel", "ViewService_Trunk_ViewCPEDeviceLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
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
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View CPE Device page");
			System.out.println(  e+ " : Field is not displayed in View CPE Device page");
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View CPE Device page ");
			System.out.println(  e+" : Field is not displayed in View CPE Device page");
			}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View CPE Device' page not navigated");
			System.out.println("'View CPE Device' page not navigated");
			 }
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "All Fields values verified in View CPE Device Page");
		Log.info("------ Verified Added CPE Device Information successfully ------");
		
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	System.out.println(  e+ " : Field is not displayed");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	System.out.println(  e+" : Field is not displayed");
}
sa.assertAll();

	}
	
	
	
	
	public void verifyEditCPEDeviceFunction(String application, String ServiceIdentification , String CPE_RouterIDEdit, String	CPE_DeviceNameEdit, String	CPE_VendorModelEdit, String	CPE_ManagementAddressEdit	
			, String CPE_SnmproEdit, String	CPE_SnmprwEdit, String	CPE_SnmpV3ContextNameEdit,	String CPE_SnmpV3ContextEngineIDEdit, String	CPE_SnmpV3SecurityUsernameEdit, 
			String	CPE_SnmpV3AuthProtoEdit, String	CPE_SnmpV3AuthPasswordEdit, String CPE_CountryEdit, String	CPE_CityEdit, String CPE_SiteEdit, String	CPE_PremiseEdit) throws InterruptedException, DocumentException, IOException {
		
		try {
		scrollToTop();

		if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
			System.out.println("'View CPE Device' page navigated as expected");
			
			click(application, "ACTION Link", "CPE_View_ActionLink");
			
			click(application, "Edit Link", "CPE_View_Action_EditLink");
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_EditDevice_Header")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit CPE Device' page navigated as expected");
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
			
			waitforPagetobeenable();
//			compareText(application, "CPE Device Update message", "CPE_UpdateCPEDeviceSuccessMessage", "CPE Device updated successfully");
			verifysuccessmessage(application, "CPE Device updated successfully");
			Log.info("------  CPE Device updated successfully   ------");
			
			
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in Edit CPE Device page");
			System.out.println( "Field is not displayed in Edit CPE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in Edit CPE Device page ");
			System.out.println( "Field is not displayed in Edit CPE Device page");
		}
			
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit CPE Device' page not  navigated");
				System.out.println("'Edit CPE Device' page not navigated");
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View CPE Device' page not navigated");
			System.out.println("'View CPE Device' page not navigated");
		}
		
		 }catch(NoSuchElementException e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		    	System.out.println(  e+ " : Field is not displayed");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		    	System.out.println(  e+" : Field is not displayed");
		    }
		    sa.assertAll();
			
	}

	
	
	
	
	
public void veriyFetchDeviceInterfacesFunction_CPE(String application,String ServiceIdentification, String CPE_ServiceStatusChangeRequired) throws InterruptedException, DocumentException, IOException {
	
	try{
		scrollToTop();
	if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
		System.out.println("'View CPE Device' page navigated as expected");
		implicitlyWait("View CPE Device screen");
		click(application, "ACTION link", "CPE_View_ActionLink");
		click(application, "Fetch Device Interfaces Link", "CPE_View_Action_FetchDeviceInterfacesLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_FetchDeviceInterfacesSuccessMessage")).isDisplayed()){
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
			System.out.println("Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			System.out.println("Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
		}
		
		click(application, "Click here Link for CPE", "CPE_hereLink_UnderFetchDeviceInterfacesSuccessMessage");
		
		
		//Manage COLT's Network - Manage Network
		if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ManageCOLTsNetworkManageNetwork_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Manage COLT's Network - Manage Network' page navigated as expected");
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
			//**click(application, "Close", "CPE_servicestatus_popupclose");
		}

		
		
		////synchronize panel in manage colt page
			scrolltoend();
			click(application, "Synchronize", "CPE_Manage_Synchronization_SynchronizeLink");
			compareText(application, "Synchronize Warning Message", "CPE_Manage_SynchronizeWarningMessage", "Error while synchronizing device, Reason is :: null.");
			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
				System.out.println( "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
				System.out.println( "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
			}
			
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage COLT's Network - Manage Network' page not navigated");
		System.out.println("'Manage COLT's Network - Manage Network' page not navigated");
	}
	
}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'View CPE Device' page not navigated");
	System.out.println("'View CPE Device' page not navigated");
}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
sa.assertAll();

		
	}
	
	




	
	
	
	
	
	
	
public void verifyRouterToolFunction_CPE(String application,String ServiceIdentification, String CPE_CommandIPV4, String CPE_CommandIPV6, String CPE_ManagementAddress) throws InterruptedException, DocumentException, IOException {
		try {
		scrollToTop();
		
		
		WebElement serviceIdLink=getwebelement("//a[text()='"+ ServiceIdentification +"']");
		Clickon(serviceIdLink);
		waitforPagetobeenable();
		
		WebElement TrunkGroupSiteOrders_header= getwebelement(xml.getlocator("//locators/" + application + "/TrunkGroupSiteOrders_header"));
		scrolltoview(TrunkGroupSiteOrders_header);
		
		click(application, "View Link For CPE Device under Trunk Device panel", "ViewService_Trunk_ViewCPEDeviceLink");
		waitforPagetobeenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/RouterTool_header")));
		compareText(application, "Router Tools header", "RouterTool_header", "Router Tools");
		
		SelectDropdownValueUnderDivTag(application, "Commands IPV4", CPE_CommandIPV4, "MAS_PE_Router_IPV4CommandsDropdown", "commonDropdownValueTag");
		EnterTextValue(application, CPE_ManagementAddress, "Commands IPV4", "CPE_Router_IPV4CommandTextfield");
		click(application, "Execute button IPV4", "CPE_Router_IPV4Command_Executebutton");
				
		SelectDropdownValueUnderDivTag(application, "Commands IPV6", CPE_CommandIPV6, "CPE_Router_IPV4CommandsDropdown", "commonDropdownValueTag");
		EnterTextValue(application, CPE_ManagementAddress, "Commands IPV6", "CPE_Router_IPV6CommandTextfield");
		click(application, "Execute button IPV6", "CPE_Router_IPV6Command_Executebutton");
		
		 }catch(NoSuchElementException e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		    	System.out.println(  e+ " : Field is not displayed");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		    	System.out.println(  e+" : Field is not displayed");
		    }
		    sa.assertAll();
}
	







	
	////
		public void verifyDeleteDeviceFunction_CPE(String application,String ServiceIdentification) throws InterruptedException, DocumentException, IOException {
			try {
			scrollToTop();
			if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
				System.out.println("'View CPE Device' page navigated as expected");
				
			//Delete MAS Device from View Device Page
				Thread.sleep(7000);
			click(application, "ACTION link", "CPE_View_ActionLink");
			click(application, "Delete Device from View Device page", "CPE_View_Action_DeleteLink");
			compareText(application, "Delete PE Device Warning Message from View Device page", "CPE_ViewDevice_Action_DeletePEDeviceWarningMessage", "Are you sure that you want to delete this item?");
			click(application, "Delete Button", "CPE_ViewDevice_Action_DeleteButton");
			waitforPagetobeenable();
			scrollToTop();
			compareText(application, "Delete CPE Device Successful Message", "CPE_DeleteCPEDeviceSuccessMessage", "CPE Device deleted successfully");
			
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'View CPE Device' page not navigated");
				System.out.println("'View CPE Device' page not navigated");
			}
			 }catch(NoSuchElementException e) {
			    	e.printStackTrace();
			    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			    	System.out.println(  e+ " : Field is not displayed");
			    }catch(Exception e) {
			    	e.printStackTrace();
			    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			    	System.out.println(  e+" : Field is not displayed");
			    }
			    sa.assertAll();
			}
	
		
		
		public void navigateToViewServicePageByServiceBreadcumLink_MAS(String application,String ServiceIdentification) throws InterruptedException, DocumentException {
		scrollToTop();
			try {
				waitforPagetobeenable();
			WebElement serviceIdLink=getwebelement("//a[text()='"+ServiceIdentification+"']");
			Clickon(serviceIdLink);

			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : VOIP ACCESS Service ID Link is not displayed");
				System.out.println(  e+ " : VOIP ACCESS Service ID Link is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : VOIP ACCESS Service ID Link is not displayed");
				System.out.println(  e+" : VOIP ACCESS Service ID Link is not displayed");
			}

			
		}
		
		
	   
	   public void navigateToDevicePageFromManageColtNetwork_MAS(String application,String MAS_DeviceName) throws InterruptedException, DocumentException {
				
		if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_Manage_Status_DeviceValue")).isDisplayed()){

				   GetText(application, "Device Name in Manage Colt page under Status Panel", "MAS_Manage_Status_DeviceValue");
				   click_commonMethod(application, "Device Link ", "MAS_Manage_Status_DeviceValue", xml);
				   waitforPagetobeenable();
				   
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL,  "VOIP ACCESS Device Link is not displayed under Manage Colt Network page");
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
				if(getwebelement(xml.getlocator("//locators/" + application + "/MAS_ViewDevice_Header")).isDisplayed()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'View MAS Switch' page navigated as expected");
					System.out.println("'View MAS Switch' page navigated as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'View MAS Switch' page not navigated");
					System.out.println("'View MAS Switch' page not navigated");
				}
					
			}else{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
			
			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
				System.out.println(  e+ " : Field is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
				System.out.println(  e+" : Field is not displayed");
			}
		}
	
	
		
		public void navigateToViewDevicePage_MAS_UI(String application, String MAS_DeviceName) throws InterruptedException, DocumentException {
			waitforPagetobeenable();
			try {
			ScrolltoElement(application, "portalaccess_header", xml);//managementOptionsPanelheader//portalaccess_header//ProviderEquipment_header
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingMASdevicegrid")).isDisplayed())
			{
				//**WebElement AddedDevice_ViewLink=getwebelement("//div[div[b[text()='"+MAS_DeviceName+"']]]//span[text()='View']");
				//**Clickon(AddedDevice_ViewLink);
				click_commonMethod(application, "View Link", "MAS_viewdevice1", xml);
				compareText(application, "View device header", "MAS_ViewDevice_Header", "Device", xml);
					
			}else{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
			
			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
				System.out.println(  e+ " : Field is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
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
						waitforPagetobeenable();
						compareText(application, "View device header", "MAS_ViewDevice_Header", "Device", xml);
					}else{
                          ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                    }
                    break;
				}
				
			}else{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
			
		}
	
		
		public void navigateToViewDevicePage_MAS_OLD(String application, String devicename) throws InterruptedException, DocumentException {
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
						waitforPagetobeenable();
						compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);
					}
				}
				
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
			
		}
	
		
		public void Clickon_addToggleButton(String application, String xpath) throws InterruptedException, DocumentException {

			//Add Toggle button
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
			waitforPagetobeenable();
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
			//

			WebElement vendorModel=getwebelement(xml.getlocator("//locators/" + application + "/MAS_View_VendorModelValue"));
			String vendor=Gettext(vendorModel);
			WebElement manageAddress=getwebelement(xml.getlocator("//locators/" + application + "/MAS_View_ManagementAddressValue"));
			String ipAddress=Gettext(manageAddress);
			
			ScrolltoElement(application, "MAS_View_VendorModelValue" , xml);
			//
			
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
				ExtentTestManager.getTest().log(LogStatus.INFO, "Router Panel will not display for the selected vendorModel: "+vendorModel);
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
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Result' text field is displaying");
			System.out.println( "'Result' text field is displaying");
			
			String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_result_textArea")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "value under 'Result' field displaying as "+ remarkvalue);
			System.out.println("value under 'Result' field displaying as "+ remarkvalue);

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
			System.out.println( "'Result' text field is not displaying");
		}
		}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
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
				  ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
					System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
			  }
			}catch(Exception e) {
				e.printStackTrace();
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
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
						ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
						System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
					}
				}catch(Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
					System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				}
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv6' text field is not displaying for "+ commandIPV6);
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
				  ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
					System.out.println("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
			  }
			}catch(Exception e) {
				e.printStackTrace();
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
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
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
						System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
					}
				}catch(Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
					System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				}
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv4' text field is not displaying for "+ command_ipv4);
				System.out.println("'VRF Name IPv4' text field is not displaying for "+ command_ipv4 +" command");
			}
		}	


	
		public void navigateToEditPage(String application) throws InterruptedException, DocumentException {
			
			WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/OrderPanel"));
			scrolltoview(orderPanel);
			
			
		//click on Action dropdown	
			click_commonMethod(application, "Action", "ActionDropdown_InViewPage", xml);
			
			
		//click on edit link
			click_commonMethod(application, "edit", "editLink_InViewPage", xml);
			
			
		}
	
	
	
	
	
	
		public void deleteTrunk(String application,String Trunk_ServiceIdentification) throws InterruptedException, DocumentException, IOException {
			
			try {
			String PSXSyncStatus=getwebelement(xml.getlocator("//locators/" + application + "/ViewTrunk_PSXSyncStatus")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "PSX Sync Status displayed as : "+PSXSyncStatus);
			System.out.println("PSX Sync Status displayed as : "+PSXSyncStatus);
			if(!PSXSyncStatus.contains("QUEUED")) {
			click(application, "Trunk ACTION link", "ViewTrunkPage_ActionButton");
			
			click(application, "Delete DLink", "ViewTrunkPage_DeleteLink");
			compareText(application, "Delete Trunk Warning Message from View Trunk page", "ViewTrunk_DeleteWarningMessage", "Are you sure that you want to delete?");
			click(application, "Delete Button", "ViewTrunkPage_DeleteButton");
			waitforPagetobeenable();
			compareText(application, "Delete Trunk Successful Message", "DeleteTrunkSuccessMessage", "Trunk deleted successfully");
		}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Selected Trunk can't be deleted since 'PSX Sync Status' is : "+PSXSyncStatus);
				System.out.println("Selected Trunk can't be deleted since 'PSX Sync Status' is : "+PSXSyncStatus);
				
				searchorder(application, Trunk_ServiceIdentification);
			}
			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Trunk page");
				System.out.println(  e+ " : Field is not displayed in View Trunk page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in in View Trunk page");
				System.out.println(  e+" : Field is not displayed in in View Trunk page");
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
				
				WebElement breadcrumb=null;
		
				try {
				breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
				if(breadcrumb.isDisplayed()) {
					click_commonMethod_PassingWebelementDirectly(application, "Breadcrump", "breadcrump", xml);
					waitforPagetobeenable();
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
		ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on view link");
	
}

	


				//////GSX , PSX , SBC

public void viewTrunk_GSX_generateConfiguration(String application) throws InterruptedException, DocumentException {
	
	WebElement lowerCaseRouteelement=getwebelement(xml.getlocator("//locators/" + application + "/createLowerCaseRoute_viewTrunkPage"));
	scrolltoview(lowerCaseRouteelement);
	
	
//	addDropdownValues(application, fieldname, xpath, expectedValueToAdd);
	
}


public void viewTrunk_PSX_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException, IOException {
	try {
	waitForpageload();
	Thread.sleep(7000);
	
	scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/DDIRange_Header")));
	
	
	SelectDropdownValueUnderDivTag(application, "PSX Configuration", expectedConfiguration, "PSXconfigurationDropdown_viewtrunk", "commonDropdownValueTag");
	click_commonMethod(application, "Execute", "viewTrunk_PSX_executeButton" , xml);
	
	
	 Alert alert = driver.switchTo().alert();		
 		
     // Capturing alert message.    
       String alertMessage= driver.switchTo().alert().getText();
       if(alertMessage.isEmpty()) {
    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No mEssage displays");
	       System.out.println("No Message displays"); 
       }else {
    	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
	       System.out.println("text message for alert displays as: "+alertMessage);
       }
     
     try {  
       alert.accept();
       
       
       System.out.println("aceept 2");
       alert.accept();
     }catch(Exception e) {
    	 e.printStackTrace();
     }
     
     try {
     if(expectedConfiguration.equalsIgnoreCase("Delete Trunk Group")) {
    	 //compareText(application, "PSX config sucess Message", "PSXconfig_WarningMessage", "PSX sync could not be started because this Trunk Group is already Queued / Processing or 3 step PSX sync is not completed.", xml);
    	 verifysuccessmessage(application, "PSX sync started successfully.");
     }else if(expectedConfiguration.equalsIgnoreCase("Add Destination IP Address")) {
    	 String alertMessage1=alert.getText();
    	 if(alertMessage1.isEmpty()) {
	    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on OK, No Message displays");
		       System.out.println("No Message displays"); 
	       }else {
	    	   ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on OK button, Alert message displays as: "+alertMessage);
		       System.out.println("text message for alert displays as: "+alertMessage);
	       }
     }
     }catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed ");
			System.out.println(  e+ " : Field is not displayedpage");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed ");
			System.out.println(  e+" : Field is not displayed");
		}
     
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
	sa.assertAll();

}


public void viewTrunk_SBC_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException {
	try {
	
	System.out.println("expected value "+ expectedConfiguration);
	scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/DDIRange_Header")));
	
	
	addDropdownValues_commonMethod(application, "SBC Configuration", "SBCconfigurationDropdown_viewtrunk", expectedConfiguration, xml);
	
	click_commonMethod(application, "Execute", "viewTrunk_SBC_executeButton" , xml);
	
	 Alert alert = driver.switchTo().alert();		
		
     // Capturing alert message.    
       String alertMessage= driver.switchTo().alert().getText();
       if(alertMessage.isEmpty()) {
    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No mEssage displays");
	       System.out.println("No Message displays"); 
       }else {
    	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
	       System.out.println("text message for alert displays as: "+alertMessage);
       }
       
       waitforPagetobeenable();
     
       //TODO 
     try {  
       alert.accept();
       
       
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
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
	sa.assertAll();
}


public void viewTrunk_GSX_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException, IOException {
	
	try {
	System.out.println("expected value "+ expectedConfiguration);
	scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/DDIRange_Header")));
	
	
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
                  
                
                
              String gsxConfiguredValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/GSXcongig_textArea")));
              if(gsxConfiguredValue.isEmpty()) {
            	  ExtentTestManager.getTest().log(LogStatus.PASS, "No values displaying under GSX Configuration");
              }else {
            	  ExtentTestManager.getTest().log(LogStatus.PASS, "'Under GSX Configuration' value is displaying as: "+ gsxConfiguredValue);
              }
//               Write here  whatever you want to do and perform
                System.out.println("came inside child window");
                
               //
                scrolltoend();
                
                click_commonMethod(application, "Execute", "GSX_config_executeButton", xml);
          }
    }
    
   waitforPagetobeenable();
   driver.close();
    driver.switchTo().window(mainWindow);
    

    scrollToTop();
   String gsxSuccessMesage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/GSXconfig_sucessMessage")));
   if(gsxSuccessMesage.isEmpty()) {
	   ExtentTestManager.getTest().log(LogStatus.FAIL, "NO message displays after clicking on 'Execute' button");
   }
   else {
	   ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Execute' button, success Message displays as: "+gsxSuccessMesage);
   }
   
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
	sa.assertAll();
}



/**
 *  perform Manual SBC execution
 * @param application
 * @throws InterruptedException
 * @throws DocumentException
 * @throws IOException 
 */
public void addSBC_manualExecutionConfig(String application, String manualConfigurationValue) throws InterruptedException, DocumentException, IOException {
	try {
	scrolltoend();
	
	
	boolean SBCHeder=getwebelement(xml.getlocator("//locators/" + application + "/SBCmanualConfig_PanelHeader")).isDisplayed();
	if(SBCHeder) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'SBC Manully Executed Configuration' panel is displaying");
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
	                  
	                
	                
	              String psxConfiguredValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/manualConfiguration_textArea")));
	              if(psxConfiguredValue.isEmpty()) {
	            	  ExtentTestManager.getTest().log(LogStatus.PASS, "No values displaying under SBC Manually Executed Configurations");
	              }else {
	            	  ExtentTestManager.getTest().log(LogStatus.PASS, "'Under SBC Manually Executed Configurations' value is displaying as: "+ psxConfiguredValue);
	              }
//	               Write here  whatever you want to do and perform
	                System.out.println("came inside child window");
	                
	            	//Text Area	
	                addtextFields_commonMethod(application, "SBC Manual Configuration", "manualConfiguration_textArea", manualConfigurationValue, xml);	        		scrolltoend();
	        		click_commonMethod(application, "Save", "saveButton_manualConfiguration", xml);   //click on Save buttton
	        		
	        			                
	          }
	    }
	    
	    waitforPagetobeenable();
	    //**driver.close();//Automatically getting closed
	     driver.switchTo().window(mainWindow);
	     

	     scrollToTop();
	    compareText(application, "SBC Manual Configuration added Successfully", "AddManualConfiguration_SuccessMessage", "Manual Configuration added Successfully", xml);
	    
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'SBC Manully Executed Configuration' panel is not displaying");
		System.out.println("'SBC Manully Executed Configuration' panel is not displaying");
	}
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
	sa.assertAll();
}




public void verifySBCfileAdded(String application) throws InterruptedException, DocumentException, IOException {
	
	
	scrolltoend();
	
	
	WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/SBC_selectCreatedValue"));
	String SBCfilenameCreated=Gettext(filename);
	
	if(SBCfilenameCreated.isEmpty()) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "SBC Manually Executed configuration file name is not displaying");
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
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "column names display as: "+ columnName[1] + "  " + columnName[2]);  //printing column Names
		
		
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
	
	
		ExtentTestManager.getTest().log(LogStatus.INFO, "Values under 'SBC Manually Executed Configuration' value displays as: ");
		
	for(int y=0; y<fileNameValues.length; y++) {
	ExtentTestManager.getTest().log(LogStatus.PASS,  fileNameValues[y] +"      "+ dateValues[y]);
	}
	
	}
}


public void editGSX_manualExecutionConfig(String application, String editGSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {
	
	try {
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
                  
                
                
//               Write here  whatever you want to do and perform
                System.out.println("came inside child window");
                
                edittextFields_commonMethod(application, "GSX manual Configuration", "GSX_editPage_teaxtArea", editGSXmanualConfigvalur, xml);
               
                scrolltoend();
                
                
                click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
                
          }
    }
    
    driver.switchTo().window(mainWindow);
    
    //**verifysuccessmessage(application, "Manual Configuration updated Successfully");
//    compareText(application, "Update GSX Manual Configuration Successfully", "UpdateManualConfiguration_SuccessMessage", "Manual Configuration updated Successfully", xml);
    verifysuccessmessage(application, "Manual Configuration updated Successfully");
	 }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	    	System.out.println(  e+ " : Field is not displayed");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	    	System.out.println(  e+" : Field is not displayed");
	    }
	    sa.assertAll();
    
}


public void editPSX_manualExecutionConfig(String application, String editPSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {
	try {
	
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
                  
                
                
//               Write here  whatever you want to do and perform
                System.out.println("came inside child window");
                
                edittextFields_commonMethod(application, "PSX manual Configuration", "GSX_editPage_teaxtArea", editPSXmanualConfigvalur, xml);
                
                scrolltoend();
                
                
                click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
                
          }
    }
    
    driver.switchTo().window(mainWindow);
    
//    compareText(application, "Update PSX Manual Configuration Successfully", "UpdateManualConfiguration_SuccessMessage", "Manual Configuration updated Successfully", xml);
    verifysuccessmessage(application, "Manual Configuration updated Successfully");
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
	sa.assertAll();
}


public void editSBC_manualExecutionConfig(String application, String editGSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {
	try {
	
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
                  
                
                
//               Write here  whatever you want to do and perform
                System.out.println("came inside child window");
                
                edittextFields_commonMethod(application, "GSX manual Configuration", "GSX_editPage_teaxtArea", editGSXmanualConfigvalur, xml);
               
                scrolltoend();
                
                click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
                
          }
    }
    
    driver.switchTo().window(mainWindow);
    
//    compareText(application, "Update SBC Manual Configuration Successfully", "UpdateManualConfiguration_SuccessMessage", "Manual Configuration updated Successfully", xml);
    verifysuccessmessage(application, "Manual Configuration updated Successfully");
	}catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	    	System.out.println(  e+ " : Field is not displayed");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	    	System.out.println(  e+" : Field is not displayed");
	    }
	    sa.assertAll();

    
}



public void deleteSBC_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {
	try {
	scrolltoend();
	
	
	click_commonMethod(application, "SBCcreated", "SBC_selectCreatedValue", xml);
	
	click_commonMethod(application, "Action", "SBCManualConfig_actionDropdown", xml);  //click acton dropdown
	click_commonMethod(application, "Delete link", "SBC_deleteLink", xml);   //click on edit link
	
	 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
     if(DeleteAlertPopup.isDisplayed())
     {
   	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/DeleteMessae_PSX_GSX_SBC")));
   	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
   	 
     click_commonMethod(application, "Delete", "DeleteButton_PSX_GSX_SBC", xml);
     waitforPagetobeenable();
     
     scrollToTop();
        compareText(application, "SBC Manual Configuration deleted Successfully", "SBC_DeleteManualConfiguration_SuccessMessage", "Deleted SBC manual config successfully.", xml);
     }
     else
     {
           Log.info("Delete alert popup is not displayed");
           ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
     }
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
	sa.assertAll();

}


    

/**
* perform delete operation under PSX manually executed configuration panel	
* @param application
* @throws InterruptedException
* @throws DocumentException
* @throws IOException
*/
public void deletePSX_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {
	try {
	scrolltoend();
	
	
	click_commonMethod(application, "PSXcreated", "PSX_selectCreatedValue", xml);
	
	click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);  //click action dropdown
	click_commonMethod(application, "Delete link", "PSX_deleteLink", xml);   //click on edit link
	
	 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
     if(DeleteAlertPopup.isDisplayed())
     {
   	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/DeleteMessae_PSX_GSX_SBC")));
   	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
   	 
        click_commonMethod(application, "Delete", "DeleteButton_PSX_GSX_SBC", xml);
        waitforPagetobeenable();
        
        scrollToTop();
        compareText(application, "PSX Manual Configuration deleted Successfully", "PSX_DeleteManualConfiguration_SuccessMessage", "Deleted PSX manual config successfully.", xml);


     }
     else
     {
           Log.info("Delete alert popup is not displayed");
           ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
     }
     
	 }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
	    	System.out.println(  e+ " : Field is not displayed");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
	    	System.out.println(  e+" : Field is not displayed");
	    }
	    sa.assertAll();

}


/**
 * perform delete operation under GSX manually executed configuration panel	
 * @param application
 * @throws InterruptedException
 * @throws DocumentException
 * @throws IOException
 */
	public void deleteGSX_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {
		try {
		scrolltoend();
		
		
		click_commonMethod(application, "PSXcreated", "GSX_selectCreatedValue", xml);
		
		click_commonMethod(application, "Action", "GSXManualConfig_actionDropdown", xml);  //click action dropdown
		click_commonMethod(application, "Delete link", "GSX_deleteLink", xml);   //click on edit link
		
		 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
	     if(DeleteAlertPopup.isDisplayed())
	     {
	   	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/DeleteMessae_PSX_GSX_SBC")));
	   	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
	   	 
	        click_commonMethod(application, "Delete", "DeleteButton_PSX_GSX_SBC", xml);
	        waitforPagetobeenable();
	        
	        scrollToTop();
	         compareText(application, "GSX Manual Configuration deleted Successfully", "GSX_DeleteManualConfiguration_SuccessMessage", "Deleted GSX manual config successfully.", xml);
	     }
	     else
	     {
	           Log.info("Delete alert popup is not displayed");
	           ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
	     }
		 }catch(NoSuchElementException e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		    	System.out.println(  e+ " : Field is not displayed");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		    	System.out.println(  e+" : Field is not displayed");
		    }
		    sa.assertAll();

	}

	      


/**
 *  perform Manual PSX execution
 * @param application
 * @throws InterruptedException
 * @throws DocumentException
 * @throws IOException 
 */
public void addPSX_manualExecutionConfig(String application, String PSXmanualConfiguratio) throws InterruptedException, DocumentException, IOException {
	
	try {
	scrolltoend();
	
	
	boolean PSXHeader=getwebelement(xml.getlocator("//locators/" + application + "/PSXmanualConfig_PanelHeader")).isDisplayed();
	if(PSXHeader) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'PSX Manully Executed Configuration' panel is displaying");
		System.out.println("'PSX Manully Executed Configuration' panel is displaying");
		
		
		click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);
		waitforPagetobeenable();
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
	                  
	                
	                
	              String psxConfiguredValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/manualConfiguration_textArea")));
	              if(psxConfiguredValue.isEmpty()) {
	            	  ExtentTestManager.getTest().log(LogStatus.PASS, "No values displaying under PSX Manually Executed Configurations");
	              }else {
	            	  ExtentTestManager.getTest().log(LogStatus.PASS, "'Under PSX Manually Executed Configurations' value is displaying as: "+ psxConfiguredValue);
	              }
//	               Write here  whatever you want to do and perform
	                System.out.println("came inside child window");
	                
	            	//Text Area	
	        		addtextFields_commonMethod(application, "Manul Configuration", "manualConfiguration_textArea", PSXmanualConfiguratio, xml);
	        		scrolltoend();
	        		click_commonMethod(application, "Save", "saveButton_manualConfiguration", xml);   //click on Save buttton
	        		
	        		 waitforPagetobeenable();		                
	          }
	    }
	    
	   
	    //driver.close();//Automatically getting closed
	     driver.switchTo().window(mainWindow);
	     

	     scrollToTop();
	     try {
//	    compareText(application, "PSX Manual Configuration added Successfully", "AddManualConfiguration_SuccessMessage", "Manual Configuration added Successfully", xml);
	    verifysuccessmessage(application, "Manual Configuration added Successfully");
	     }catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
				System.out.println(  e+ " : Field is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
				System.out.println(  e+" : Field is not displayed");
			}

	     }else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'PSX Manully Executed Configuration' panel is not displaying");
		System.out.println("'PSX Manully Executed Configuration' panel is not displaying");
	}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
sa.assertAll();

}


/**
 *  perform Manual GSX execution
 * @param application
 * @throws InterruptedException
 * @throws DocumentException
 * @throws IOException 
 */
public void addGSX_manualExecutionConfig(String application, String GSXmanualConfiguratio) throws InterruptedException, DocumentException, IOException {
	try {
	scrolltoend();
	
	
	boolean PSXHeader=getwebelement(xml.getlocator("//locators/" + application + "/GSXmanualConfig_PanelHeader")).isDisplayed();
	if(PSXHeader) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'GSX Manully Executed Configuration' panel is displaying");
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
	                  
	                
	                
	              String psxConfiguredValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/manualConfiguration_textArea")));
	              if(psxConfiguredValue.isEmpty()) {
	            	  ExtentTestManager.getTest().log(LogStatus.PASS, "No values displaying under GSX Manually Executed Configurations");
	              }else {
	            	  ExtentTestManager.getTest().log(LogStatus.PASS, "'Under GSX Manually Executed Configurations' value is displaying as: "+ psxConfiguredValue);
	              }
//	               Write here  whatever you want to do and perform
	                System.out.println("came inside child window");
	                
	            	//Text Area	
	        		addtextFields_commonMethod(application, "Manul Configuration", "manualConfiguration_textArea", GSXmanualConfiguratio, xml);
	        		scrolltoend();
	        		click_commonMethod(application, "Save", "saveButton_manualConfiguration", xml);   //click on Save buttton
	        		
	        			                
	          }
	    }
	    
	    waitforPagetobeenable();
	    //**driver.close();//Automatically getting closed
	     driver.switchTo().window(mainWindow);
	     

	     scrollToTop();
	    //**verifysuccessmessage(application, "Manual Configuration added Successfully");  //verify success Message
//	    compareText(application, "GSX Manual Configuration added Successfully", "GSXManualConfiguration_SuccessMessage", "Manual Configuration added Successfully", xml);
	    verifysuccessmessage(application, "Manual Configuration added Successfully");
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'GSX Manully Executed Configuration' panel is not displaying");
		System.out.println("'GSX Manully Executed Configuration' panel is not displaying");
	}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
sa.assertAll();

}


/**
* Verify the files added under PSX Manually Executed Configuration panel	
* @param application
* @throws InterruptedException
* @throws DocumentException
* @throws IOException
*/
public void verifyPSXfileAdded(String application) throws InterruptedException, DocumentException, IOException {
	
	try {
	scrolltoend();
	
	
	WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/PSX_selectCreatedValue"));
	String PSXfilenameCreated=Gettext(filename);
	
	if(PSXfilenameCreated.isEmpty()) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "PSX Manually Executed configuration file name is not displaying");
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
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "column names display as: "+ columnName[1] + "  " + columnName[2]);  //printing column Names
		
		
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
	
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Values under 'PSX Manually Executed Configuration' value displays as: ");
	
	for(int y=0; y<fileNameValues.length; y++) {
	ExtentTestManager.getTest().log(LogStatus.PASS, fileNameValues[y] +"      "+ dateValues[y]);
	}
		
	}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
sa.assertAll();

}


/**
* Verify the files added under GSX Manually Executed Configuration panel	
* @param application
* @throws InterruptedException
* @throws DocumentException
* @throws IOException
*/
public void verifyGSXfileAdded(String application) throws InterruptedException, DocumentException, IOException {
	
	try {
	scrolltoend();
	
	
	WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/GSX_selectCreatedValue"));
	String GSXfilenameCreated=Gettext(filename);
	
	if(GSXfilenameCreated.isEmpty()) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "GSX Manually Executed configuration file name is not displaying");
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
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "column names display as: ");  //printing column Names
		ExtentTestManager.getTest().log(LogStatus.PASS,  columnName[1] + "     	     " + columnName[2]);
		
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
	
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Values under 'GSX Manually Executed Configuration' value displays as: ");
	
	for(int y=0; y<fileNameValues.length; y++) {
	ExtentTestManager.getTest().log(LogStatus.PASS, fileNameValues[y] +"      "+ dateValues[y]);
	}
	
	}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
sa.assertAll();

}

	
	
	
	
public void trunkHistory(String application, String TrunkGroupSiteOrderNumber,  String ExistingTrunkName) throws InterruptedException, DocumentException {
	try {
	scrollToTop();
	waitForpageload();
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/ViewTrunk_Header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'View Trunk' Page displayed as expected");
		System.out.println("'View Trunk' Page displayed as expected");
		
		//Action button	
		click_commonMethod(application, "Action", "ViewTrunkPage_ActionButton", xml);
		//click on History link
		click_commonMethod(application, "History", "ViewTrunkPage_HistoryLink", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/HistoryPage_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'History Trunk' Page displayed as expected");
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
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in History page");
				System.out.println(  e+ " : Field is not displayed in History page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in History page ");
				System.out.println(  e+" : Field is not displayed in History page");
			}

		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'History Trunk' Page is not navigated as expected");
		System.out.println("'History Trunk' Page is not navigated as expected");
		  }
	
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'View Trunk' Page is not navigated as expected");
		System.out.println("'View Trunk' Page is not navigated as expected");
	}
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
	sa.assertAll();
	
	
}

		
	


 public void trunkPSXQueue(String application, String TrunkGroupSiteOrderNumber,  String ExistingTrunkName) throws InterruptedException, DocumentException {
	
try {
	 
	 scrollToTop();
	waitForpageload();
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/ViewTrunk_Header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'View Trunk' Page displayed as expected");
		System.out.println("'View Trunk' Page displayed as expected");
		
		//Action button	
		click_commonMethod(application, "Action", "ViewTrunkPage_ActionButton", xml);
		//click on History link
		click_commonMethod(application, "PSX Queue Link", "ViewTrunkPage_PSXQueueLink", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/PSXQueuePage_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'PSX Queue Trunk' Page displayed as expected");
			System.out.println("'PSX Queue Trunk' Page displayed as expected");
		
			try {
		isDisplayed(application, "LastStartTime_columnName", "Last Start Time Column", xml);	
		isDisplayed(application, "Customer_columnName", "Customer column", xml);
		isDisplayed(application, "Service_columnName", "Service column", xml);
		isDisplayed(application, "TrunkName_columnName", "Trunk Name column", xml);

			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in PSX Queue page");
				System.out.println(  e+ " : Field is not displayed in PSX Queue page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in PSX Queue page ");
				System.out.println(  e+" : Field is not displayed in PSX Queue page");
			}

		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'PSX Queue' Page is not navigated as expected");
		System.out.println("'PSX Queue' Page is not navigated as expected");
		  }
	
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'View Trunk' Page is not navigated as expected");
		System.out.println("'View Trunk' Page is not navigated as expected");
	}
}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in PSX Queue page");
	System.out.println(  e+ " : Field is not displayed in PSX Queue page");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in PSX Queue page ");
	System.out.println(  e+" : Field is not displayed in PSX Queue page");
}
sa.assertAll();	
	
}
	
	
	
	
	
	
 
	public void verifyBulkInterface(String application, String bulkjob_filepath) throws InterruptedException, DocumentException {
		
		//cancel bulk interface
		ScrolltoElement(application, "orderpanelheader", xml);
		
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
		
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Bulk Interface", "bulkinterfacelink", xml);
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		WebElement BulkJob_Choosefile_button= getwebelement(xml.getlocator("//locators/" + application + "/bulkjob_choosefilebutton"));
		BulkJob_Choosefile_button.sendKeys(bulkjob_filepath);
		click_commonMethod(application, "Submit", "bulkjobsubmit", xml);
//		compareText(application, "Bulk Job Success message", "successmsg", "FRC Numbers sent to Queue for Creation. Please check the bulk operation of SANs here.", xml);
		verifysuccessmessage(application, "FRC Numbers sent to Queue for Creation. Please check the bulk operation of SANs here.");
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
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Bulk Interface page refresh successful");
		Log.info("Bulk Interface page refresh successful");
		
		scrolltoend();
		click_commonMethod(application, "Cancel", "bulkinterfacepage_cancel", xml);
		

	}
	
	public void isDisplayed(String application, String xpath, String labelname, XMLReader xml) {
		boolean availability = false;

		try {
			
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
	
	
	public void implicitlyWait(String pageNavigation) {
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);			
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
			try {
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
			}
			catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Downloads folder path '"+dirPath+"' is not found");
			}
			return flag;
		}
		
				
				
				
public void deleteServiceFunction(String application, String expectedvalue) throws InterruptedException, DocumentException{
			waitforPagetobeenable();
			try {
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
				click_commonMethod(application, "Action", "serviceactiondropdown", xml);
				
				click(application, "Delete Link", "DeleteLink");
				compareText(application, "Delete Trunk Warning Message from View Trunk page", "DeleteServiceWarningMessage", "Are you sure that you want to delete this item?");
				click(application, "Delete Button", "deletebutton");
				waitforPagetobeenable();
				compareText(application, "Delete Service Successful Message", "deletesuccessmsg", "Service successfully deleted.");
				
			
			 }catch(NoSuchElementException e) {
			    	e.printStackTrace();
			    	ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			    	System.out.println(  e+ " : Field is not displayed");
			    }catch(Exception e) {
			    	e.printStackTrace();
			    	ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			    	System.out.println(  e+" : Field is not displayed");
			    }
			    sa.assertAll();
	}		
			
			
			
			
			
			
			
		
					
	






























	
}
