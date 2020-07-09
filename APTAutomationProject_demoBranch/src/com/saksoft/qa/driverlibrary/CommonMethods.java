// Author: Sai Supraja

package com.saksoft.qa.driverlibrary;

import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class CommonMethods extends DriverHelper{
	
	public CommonMethods(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/APT_MCS_CreateOrder_NGIN_ServiceType.xml");

	public void EnterTextValue(String application, String value, String labelname, String xpath) {
		 WebElement element = null;
		 
		 try {
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				System.out.println(element);
				if (element != null) {
					element.sendKeys(value);
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Entered '"+value+"' into '"+labelname+"' text field");
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Not able to identify the '"+labelname+"' text field");
				}
				
			} catch (Exception e) {
				DriverTestcase.logger.log(LogStatus.FAIL,"Not able to enter '"+value+"' into '"+labelname+"' text field");
				e.printStackTrace();
			}
			
		}
	 
	 public void click(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
		 WebElement element= null;
		 		 	 
		 try {
			 element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				if (element!=null) {
					element.click();	
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL,"Step: '"+labelname+"' button/link was not found");
				}
			
			} catch (Exception e) {
				DriverTestcase.logger.log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
				e.printStackTrace();
			}
	 }
	 
	 public void compareText(String application, String labelname, String xpath, String ExpectedText) throws InterruptedException, DocumentException {
		 
		 String text = null;
		 WebElement element = null;
		 element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));     
        if(element==null)
        {
       	 element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")); 
        }
       try {
           if (element != null)                
               text = element.getText();
           if(text.equals(ExpectedText)) {
           	DriverTestcase.logger.log(LogStatus.PASS,"Step: The ExpectedText '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
           }
           else if(text.contains(ExpectedText)) {
           	DriverTestcase.logger.log(LogStatus.PASS,"Step: The ExpectedText '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
           }
           else
           {
           	DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
           }
           
       }catch (Exception e) {
       	DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
           e.printStackTrace();
       }
                  
	 }
		 
		 
	}


