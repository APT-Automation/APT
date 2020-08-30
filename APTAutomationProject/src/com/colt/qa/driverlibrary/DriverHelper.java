package com.colt.qa.driverlibrary;

import static org.testng.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//wholesale

public class DriverHelper{
	
	protected WebDriver driver;
	Wait<WebDriver> wait;
	WebElement el;
	List<WebElement> ellist;
	SoftAssert sa=new SoftAssert();
	public static ThreadLocal<String> NameStatus= new ThreadLocal<>();
	public static ThreadLocal<String> CustomerCreatedStatus= new ThreadLocal<>();
	public static ThreadLocal<String> Count =  new ThreadLocal<>();
	
	
	public DriverHelper(WebDriver dr)
	{
		driver=dr;
		wait = new FluentWait<WebDriver>(driver) 
				.withTimeout(60, TimeUnit.SECONDS)    
				.pollingEvery(15, TimeUnit.SECONDS)    
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
	}
	 
	public void javascriptexecutor(WebElement el) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", el);
		//js.executeScript("window.scrollBy(0, 350)");
		//window.scrollTo(0, 0);
	}
	
	
	public void Expandthesection(WebElement Section, WebElement ClickableElement) throws Exception {
		Thread.sleep(5000);	
		String classvalue=Getattribute(Section,"class");
			Log.info(classvalue);
		if(!classvalue.contains("green")){
			Log.info("In IF class");
			//Clickon(ClickableElement);
			safeJavaScriptClick(ClickableElement);
			((JavascriptExecutor)

					driver).executeScript("arguments[0].scrollIntoView(window.innerHeight/2);", ClickableElement);
		}
		else {
			Log.info("Already expanded");
		}
		}
	
	
	public void Clickonoutofviewport(WebElement locator) throws Exception {
		((JavascriptExecutor)
				driver).executeScript("arguments[0].scrollIntoView(window.innerHeight/2);", locator);
		safeJavaScriptClick(locator);
	}
	
	
	public void Clickonoutofviewportwithstring(String locator) throws Exception {
		((JavascriptExecutor)
				driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[text()='Show Groups']")));
		safeJavaScriptClick(driver.findElement(By.xpath(locator)));
	}
	
	
	
	@DataProvider
	public static Map<String, String> getTestData(String sheetName){
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			FileInputStream fis = new FileInputStream(new File("src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			switch (sheetName) {
			case "Sheet1":
				Iterator<Row> rowIterator = sheet.iterator();
				
				while(rowIterator.hasNext())
				{
					Row row = rowIterator.next();
					if(row.getRowNum() != 0) {
						/*
						 * Iterator<Cell> cellIterator = row.cellIterator();
						 * while(cellIterator.hasNext()) { Cell cell = cellIterator.next();
						 * Log.info(cell.getStringCellValue() + "\t"); }
						 */
						returnMap.put("Name", row.getCell(0).getStringCellValue());
						returnMap.put("Device Type", row.getCell(1).getStringCellValue());
						returnMap.put("Vender Model", row.getCell(2).getStringCellValue());
						
						returnMap.put("Router Id", row.getCell(4).getStringCellValue());
						returnMap.put("Country", row.getCell(5).getStringCellValue());
						returnMap.put("Management Address", row.getCell(6).getStringCellValue());
						
					}
					
				}
							
							break;
			case "Sheet2":
				
							
							
							break;

			default:
				break;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
		return returnMap;
	}
	
	
	public void WaitforElementtobeclickable(final String locator) throws InterruptedException
	{
		waitForpageload();
		if(locator.startsWith("//") || locator.startsWith("(")) {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))); 
		//getwebelement(xml.getlocator("//locators/StandrdQuote"));
		Log.info("Code for Loading");
		Thread.sleep(2000);
		}
		else if(locator.startsWith("name"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.name(locator.split("=")[1]))); 
			//getwebelement(xml.getlocator("//locators/StandrdQuote"));
			Log.info("Code for Loading");
			Thread.sleep(2000);
			
		}
		else if(locator.startsWith("id"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locator.split("=")[1]))); 
			//getwebelement(xml.getlocator("//locators/StandrdQuote"));
			Log.info("Code for Loading");
			Thread.sleep(2000);
			
		}
	}
	public void Getloadingcomplete(final String locator) throws InterruptedException
	{
		wait.until(ExpectedConditions.attributeToBe(By.xpath(locator), "style", "display: none;")); 
		//getwebelement(xml.getlocator("//locators/StandrdQuote"));
		Log.info("Code for Loading");
		Thread.sleep(2000);
		
	}
	
	public void CloseProposalwindow() throws InterruptedException
	{   String parentWinHandle = driver.getWindowHandle();
		Set<String> totalopenwindow=driver.getWindowHandles();
		if(totalopenwindow.size()>1) {
		for(String handle: totalopenwindow)
		{
            if(!handle.equals(parentWinHandle))
            {
            driver.switchTo().window(handle);
            
            }
		}
		driver.close();
		driver.switchTo().window(parentWinHandle);
		}
		else {
			Log.info("Something went wrong. Proposal has not be generated");
		}
	}
	public void Switchtotabandsignthequote() throws Exception
	{   String parentWinHandle = driver.getWindowHandle();
		Set<String> totalopenwindow=driver.getWindowHandles();
		for(String handle: totalopenwindow)
		{
            if(!handle.equals(parentWinHandle))
            {
            driver.switchTo().window(handle);
            Thread.sleep(12000);
            try {
            safeJavaScriptClick(getwebelement("//*[@id='disclosureAccepted']"));
            }
            catch(Exception e) {
            Clickon(getwebelement("//*[text()='Required']"));
            }
            Clickon(getwebelement("//button[text()='Continue']"));
    		Clickon(getwebelement("//button[@data-qa='SignHere']"));
    		Clickon(getwebelement("//div[@class='page-tabs']"));
    		//create object 'action' of Actions class
    		//Dragedrop(getwebelement("//button[@data-qa='SignHere']"),getwebelement("//div[@class='page-tabs']"));
    		Thread.sleep(10000);
    		Clickon(getwebelement("//button[text()='Adopt and Sign']"));
//    		Thread.sleep(10000);
//    		Clickon(getwebelement("//button[text()='Ok']"));
    		Thread.sleep(10000);
    		WaitforElementtobeclickable("//button[text()='Finish']");
    		Clickon(getwebelement("//button[text()='Finish']"));
    		WaitforElementtobeclickable("(//button[text()='Continue'])[2]");
    		Clickon(getwebelement("(//button[text()='Continue'])[2]"));
    		Thread.sleep(10000);
            }
		}
		driver.close();
		driver.switchTo().window(parentWinHandle);
	}
	public void Switchtotab() throws Exception
	{   String parentWinHandle = driver.getWindowHandle();
		Set<String> totalopenwindow=driver.getWindowHandles();
		for(String handle: totalopenwindow)
		{
            if(!handle.equals(parentWinHandle))
            {
            driver.switchTo().window(handle);
            Thread.sleep(4000);
           
            }
		}
		//driver.close();
		//driver.switchTo().window(parentWinHandle);
	}
	public void Getmaploaded(final String framlocator, final String messagelocator) throws InterruptedException
	{
		
		Log.info("Code for Map Loading");
		Thread.sleep(3000);
		String[] finalval=framlocator.split("=");
		//Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.id(finalval[1])));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(messagelocator))); 
		Log.info(driver.findElement(By.xpath(messagelocator)).getText().toString());
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		Log.info("Code for Map Loading");
		
	}
	
		public WebElement getwebelement(final String locator) throws InterruptedException
			{   //Log.info("Indriverhelper"+driver);
				//WebElement el;
				final String[] finalval;
				if(locator.startsWith("name"))
				{
					finalval=locator.split("=");
					//Log.info(finalval[1]);
					//Log.info("Indriverhelper"+driver);
					//wait.until();  
					wait.until(new Function<WebDriver, WebElement>() {       
						public WebElement apply(WebDriver driver) { 
							el=driver.findElement(By.name(finalval[1]));
							return driver.findElement(By.name(finalval[1]));     
						}  
					});  
				}
				else if(locator.startsWith("id"))
				{
					finalval=locator.split("=");
					//Log.info(finalval[1]);
					//Log.info("Indriverhelper"+driver);
					wait.until(new Function<WebDriver, WebElement>() {       
						public WebElement apply(WebDriver driver) { 
							el=driver.findElement(By.id(finalval[1]));
							return driver.findElement(By.id(finalval[1]));     
						}  
					});
					//el= driver.findElement(By.id(finalval[1]));
				}
				else if (locator.startsWith("//")|| locator.startsWith("(//"))
				{
					wait.until(new Function<WebDriver, WebElement>() {       
						public WebElement apply(WebDriver driver) { 
							el=driver.findElement(By.xpath(locator)); 
							return driver.findElement(By.xpath(locator));     
						}  
					});

				}
				Thread.sleep(1000);
				return el;
			} 

	
//	public WebElement getwebelement(final String locator) throws InterruptedException
//	{   //Log.info("Indriverhelper"+driver);
//	 //WebElement el;
//	final String[] finalval;
//		if(locator.startsWith("name"))
//		{
//			finalval=locator.split("=");
//			//Log.info(finalval[1]);
//			//Log.info("Indriverhelper"+driver);
//			//wait.until();
//			
//			wait.until(new Function<WebDriver, WebElement>() {       
//				public WebElement apply(WebDriver driver) { 
//					el= driver.findElement(By.name(finalval[1]));
//					//RemoteWebDriver dr;
//					
//					wait.until(ExpectedConditions.elementToBeClickable(el)).isEnabled();
//					return el;     
//				 }  
//				}); 
//			//wait.until(ExpectedConditions.stalenessOf(element))
//		}
//		else if(locator.startsWith("id"))
//		{
//			finalval=locator.split("=");
//			//Log.info(finalval[1]);
//			//Log.info("Indriverhelper"+driver);
//			wait.until(new Function<WebDriver, WebElement>() {       
//				public WebElement apply(WebDriver driver) { 
//					el=driver.findElement(By.id(finalval[1]));
//					wait.until(ExpectedConditions.elementToBeClickable(el)).isEnabled();
//					//wait.until(el.isEnabled());
//					return el;   
//				 }  
//				});
//			 //el= driver.findElement(By.id(finalval[1]));
//		}
//		else if (locator.startsWith("//")|| locator.startsWith("(//")||locator.startsWith("("))
//		{
//			wait.until(new Function<WebDriver, WebElement>() {       
//				public WebElement apply(WebDriver driver) { 
//					el=driver.findElement(By.xpath(locator)); 
//					wait.until(ExpectedConditions.elementToBeClickable(el)).isEnabled();
//					return el;   
//				 }  
//				});
//			
//		}
//		//Thread.sleep(1000);
//		
//		return el;
//	}
		
		
	public String gettitle() {
		return driver.getTitle();
	}
	
	
	public WebElement getwebelement2(final String locator) throws InterruptedException
	{   
		if (locator.startsWith("//")|| locator.startsWith("(//"))
		{	
					el=(WebElement) driver.findElement(By.xpath(locator)); 
					return driver.findElement(By.xpath(locator));     	
		}
		Thread.sleep(1000);
		return el;
	}
	public void openurl(String environment) throws Exception {
		String URL=null;
		PropertyReader pr=new PropertyReader();
		Log.info(environment+"_URL");
		URL=pr.readproperty(environment+"_URL");
		
		driver.get(URL);
		
	}
	public void Geturl(String URL) throws Exception {
	
		driver.get(URL);
		
	}
	public void Clickon(WebElement el) throws InterruptedException {
		//Thread.sleep(3000);
		
		try {
			
			
		el.click();
		
		}
		catch(WebDriverException e)
		//Thread.sleep(3000);
		{
			//Thread.sleep(3000);
			if(e.getMessage().contains("Element is not clickable at point"))
			{
				Thread.sleep(3000);
				el.click();
			}
		}
	}
	public void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				Log.info("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				Log.info("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			Log.info("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			Log.info("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			Log.info("Unable to click on element "+ e.getStackTrace());
		}
	}
	public void switchtofram(WebElement el){
		driver.switchTo().frame(el);
		
	}
	public void switchtodefault(){
		driver.switchTo().defaultContent();
		
	}
			public String Getattribute(WebElement el,String attributename) {
			Log.info(el.getAttribute(attributename));
			return el.getAttribute(attributename);
	  }
public void Moveon(WebElement el) {
			
		Actions action = new Actions(driver);
		 
	    action.moveToElement(el).build().perform();
	    
	}
	public boolean isElementPresent(String locator) {
	    try {
	        driver.findElement(By.xpath(locator));
	        Log.info("Element Found: True");
	        return true;
	    } catch (NoSuchElementException e) {
	    	 Log.info("Element Found: False");
	        return false;
	    }
	}

	public void waitandclickForworkitemsPresent(String locator, int timeout) throws InterruptedException
	{
			for(int i=0;i<=timeout*60/20;i++){
				try {
		            if (isElementPresent(locator)){
		                break;
		            }
		            else{
		            	//Goto Error Tab
		            	// Clickon(getwebelement(xml.getlocator("//locators/Tasks/Errors")));
		            	//if any Error displayed
		            	//if(isElementPresent("Locator for first error"))
		            	//{
		            	//Assert.fail("An Error Occuured on Error Tab");
		            	//break;
		            	//}
		            	//else
		            	//{
		            	//Clickon(getwebelement(xml.getlocator("//locators/Tasks/Workitems")));
		            	//Log.info("Refreshing the Pages");
			        	//driver.navigate().refresh();
			        	//Log.info("Waiting For 20 Sec");
			        	//Thread.sleep(20000);
		            	//}
		            	//Assert False and Break
		            	//else navigate to WorkItems and do the page refresh and weight
		            	Log.info("Refreshing the Pages");
			        	driver.navigate().refresh();
			        	Log.info("Waiting For 20 Sec");
			        	Thread.sleep(20000);
		            }
		            }
		        catch (Exception e) {
		        	Log.info(e.getMessage());
		        }
			}
	}
	
	public void waitandclickForOrderCompleted(String locator, int timeout) throws InterruptedException
	{
			for(int i=0;i<=timeout*60/20;i++){
				try {
		            if (isElementPresent(locator)){
		                break;
		            }
		            else{
		            	Log.info("Refreshing the Pages");
			        	driver.navigate().refresh();
			        	Log.info("Waiting For 20 Sec");
			        	Thread.sleep(20000);
		            }
		            }
		        catch (Exception e) {
		        	Log.info(e.getMessage());
		        }
			}
	}
	
	public void waitandclickForOrderStarted(String locator, int timeout) throws InterruptedException
	{
			for(int i=0;i<=timeout*60/20;i++){
				try {
		            if (isElementPresent(locator)){
		                break;
		            }
		            else{
		            	Log.info("Refreshing the Pages");
			        	driver.navigate().refresh();
			        	Log.info("Waiting For 20 Sec");
			        	Thread.sleep(20000);
		            }
		            }
		        catch (Exception e) {
		        	Log.info(e.getMessage());
		        }
			}
	}
	
	public void waitandForElementDisplay(String locator, int timeout) throws InterruptedException
	{
			for(int i=0;i<=timeout*60/20;i++){
				try {
		            if (isElementPresent(locator)){
		                break;
		            }
		            else{
		            	Log.info("Refreshing the Pages");
			        	//driver.navigate().refresh();
			        	Log.info("Waiting For 20 Sec");
			        	Thread.sleep(20000);
		            }
		            }
		        catch (Exception e) {
		        	Log.info(e.getMessage());
		        }
			}
	}
	public void waitandForElementtobenotDisplay(String locator, int timeout) throws InterruptedException
	{
			for(int i=0;i<=timeout*60/20;i++){
				try {
		            if (isElementPresent(locator)){
		            	Log.info("Refreshing the Pages");
			        	//driver.navigate().refresh();
			        	Log.info("Waiting For 20 Sec");
			        	Log.info("Waiting......");
			        	Thread.sleep(3000);
		            }
		            else{
		            	break;
		            }
		            }
		        catch (Exception e) {
		        	Log.info(e.getMessage());
		        }
			}
	}
	public void waitandForElementDisplay2(String locator, int timeout) throws InterruptedException
	{
			for(int i=0;i<=timeout*60/20;i++){
				try {
		            if (isElementPresent(locator)){
		                break;
		            }
		            else{
		            	//Log.info("Refreshing the Pages");
			        	//driver.navigate().refresh();
			        	Log.info("Waiting For 20 Sec");
			        	Thread.sleep(3000);
		            }
		            }
		        catch (Exception e) {
		        	Log.info(e.getMessage());
		        }
			}
	}
	public void Pagerefresh() throws InterruptedException
	{
			driver.navigate().refresh();
			
			        	
	}
	
	public int getwebelementscount(final String locator) throws InterruptedException
	{ 
		ellist=driver.findElements(By.xpath(locator));
		return ellist.size();
	}
	
	
	public List<WebElement> getwebelements(final String locator) throws InterruptedException{   //Log.info("Indriverhelper"+driver);
		 //WebElement el;
		final String[] finalval;
			if(locator.startsWith("name"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				//wait.until();
				
				wait.until(new Function<WebDriver, List<WebElement>>() {       
					public List<WebElement> apply(WebDriver driver) { 
						ellist= driver.findElements(By.name(finalval[1]));
						//RemoteWebDriver dr;
						
						//wait.until(ExpectedConditions.elementToBeClickable(ellist)).isEnabled();
						return ellist;     
					 }  
					}); 
				//wait.until(ExpectedConditions.stalenessOf(element))
			}
			else if(locator.startsWith("id"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				wait.until(new Function<WebDriver, List<WebElement>>() {       
					public List<WebElement> apply(WebDriver driver) { 
						ellist=driver.findElements(By.id(finalval[1]));
						//wait.until(ExpectedConditions.elementToBeClickable(ellist)).isEnabled();
						//wait.until(el.isEnabled());
						return ellist;   
					 }  
					});
				 //el= driver.findElement(By.id(finalval[1]));
			}
			else if (locator.startsWith("//")|| locator.startsWith("(//")||locator.startsWith("("))
			{
				wait.until(new Function<WebDriver, List<WebElement>>() {       
					public List<WebElement> apply(WebDriver driver) { 
						ellist=driver.findElements(By.xpath(locator)); 
						//wait.until(ExpectedConditions.elementToBeClickable(ellist)).isEnabled();
						return ellist;   
					 }  
					});
				
			}
			//Thread.sleep(1000);
			
			return ellist;
		}
	
	///////////////
	
	
	
	public void SendKeys(WebElement el,String value) throws InterruptedException, IOException {
		
		el.sendKeys(value);
		
	}
	
	
	public void SendkeaboardKeys(WebElement el,Keys k) throws InterruptedException {
		//Thread.sleep(3000);
		el.sendKeys(k);
		//Thread.sleep(3000);
	}
	
	public String GetText(WebElement el) {
			String actual=el.getText().toUpperCase().toString();
	//		String actual1=el.getText().toUpperCase().toString();
			return actual;
		}
	
	public String GetInputValue(WebElement el) {
		String actual=el.getAttribute("value");
		return actual;
		}

	public String Getkeyvalue(String Key) throws IOException{ 
			PropertyReader pr=new PropertyReader();
		    String Keyvalue;
			Keyvalue=pr.readproperty(Key);
			return Keyvalue;
		}
	
	public void VerifyTextpresent(String text) throws IOException
		{ 
			Log.info(text);
			Assert.assertFalse(driver.findElement(By.xpath("//*[text()='"+text+"']")).isDisplayed());
		}
	
	public void VerifyText(String text) throws IOException
		{ 
			Log.info(text);
			Assert.assertTrue(driver.findElement(By.xpath("//*[text()='"+text+"']")).isDisplayed());
		}
	
	public void VerifyText2(String text, String expectedText, String message ) throws IOException
	{ 
		Log.info(text);
		sa.assertEquals(driver.findElement(By.xpath("//*[text()='"+text+"']")), expectedText, message);
		
		//sa.assertAll();
	}
	
	public String Gettext(WebElement el) throws IOException
		{ 
			String text=el.getText().toString();
			return text;
		}
	public String GetValueofInput(WebElement el) throws IOException
	{ 
		String text=el.getAttribute("value");
		return text;
	}
	
	public String[] GetText2(WebElement el) throws IOException
		{ 
			String text=el.getText().toString();
			String[] text2=text.split(" \\[");
			Log.info("New Task Name is:"+text2);
			return text2;
		}
	
	public String GetText3(WebElement el, String string) throws IOException
		{ 
			String text=el.getText().toString();
			return text;
		}
	
	
	
	public String GetTheSelectedValueInsideDropdown(WebElement el) throws IOException, InterruptedException
	{ //Thread.sleep(3000);
		Select s1=new Select(el);
		WebElement option = s1.getFirstSelectedOption();
		String defaultItem = option.getText();		
		return defaultItem;
	
	}
	
	
	
	public void Select(WebElement el, String value) throws IOException, InterruptedException
		{ //Thread.sleep(2000);
			Select s1=new Select(el);
			s1.selectByVisibleText(value);
			//Thread.sleep(2000);
		}
	public void Select2(WebElement el, String value) throws IOException, InterruptedException
	{ //Thread.sleep(3000);
		Select s1=new Select(el);
		s1.selectByValue(value);
		//Thread.sleep(3000);
	}

	public List<String> getAllValuesInsideDropDown(WebElement el) throws IOException, InterruptedException
	{ 
		  Select sel = new Select(el);
		    List<WebElement> we = sel.getOptions();
		    List<String> ls = new ArrayList<String>();
		    for(WebElement a : we)
		    {
		        if(!a.getText().equals("select")){
		            ls.add(a.getText());
		        }
		    }
		    return ls;
	}
	
	
	public List<String> getAllValuesInsideDropDown2(WebElement el) throws IOException, InterruptedException
	{ 
		  Select sel = new Select(el);
		    List<WebElement> we = sel.getOptions();
		    List<String> ls = new ArrayList<String>();
		    for(WebElement a : we)
		    {
		        if(!a.getText().equals("Select")){
		            ls.add(a.getText());
		        }
		    }
		    return ls;
	}
	
	
	
	public void Clear(WebElement el) throws IOException, InterruptedException
		{ //Thread.sleep(3000);
			el.clear();
			//Thread.sleep(3000);
		}
	public void WaitforC4Cloader(String el, int timeout ) throws IOException, InterruptedException
	{ Thread.sleep(3000);
//		for(int i=0;i<=timeout*60/20;i++){
//			try {
//	            if (isElementPresent(el)){
//	            	//Log.info("Refreshing the Pages");
//		        	//driver.navigate().refresh();
//		        	Log.info("Waiting For 20 Sec");
//		        	Thread.sleep(20000);
//	            }
//	            else{
//	            	//Log.info("Refreshing the Pages");
//		        	//driver.navigate().refresh();
//		        	break;
//	            }
//	            }
//	        catch (Exception e) {
//	        	Log.info(e.getMessage());
//	        }
//		}
		//Thread.sleep(3000);
	}
	public void AcceptJavaScriptMethod(){
		
			Alert alert = driver.switchTo().alert();
			alert.accept();
			driver.switchTo().defaultContent();
		}
	public void waitForpageload() throws InterruptedException
	{
		waitandForElementtobenotDisplay("//*[@id='overLayHtml_t-wrapper']",1);
		wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));	
		//Thread.sleep(1000);
	}
	public void Dragedrop(WebElement source,WebElement Destination){
		Actions action = new Actions(driver);
		//use dragAndDrop() method. It accepts two parametes source and target.
		action.dragAndDrop(source, Destination).build().perform();
	}
	
	public void EnterText(String s){
		Actions keyAction = new Actions(driver);     
		keyAction.sendKeys(s).perform();
	}
	public void savePage(){
			Actions keyAction = new Actions(driver);     
			keyAction.keyDown(Keys.CONTROL).sendKeys("s").keyUp(Keys.CONTROL).perform();
		}
	public void KeydownKey(Keys key){
		Actions keyAction = new Actions(driver);     
		keyAction.keyDown(key).perform();
	}
	public void KeyupKey(Keys key){
		Actions keyAction = new Actions(driver);     
		keyAction.keyUp(key).perform();
	}
	public void uploadafile(String  locator,String FileName)
	{
		String str = System.getProperty("user.dir")+"\\src\\Data\\"+FileName;
		String[]  finalval=locator.split("=");
		WebElement el=driver.findElement(By.id(finalval[1])); 
		el.sendKeys(str);
//		// + "\\Lib\\chromedriver.exe"
//		Toolkit toolkit = Toolkit.getDefaultToolkit();
//		Clipboard clipboard = toolkit.getSystemClipboard();
//		StringSelection strSel = new StringSelection(str);
//		clipboard.setContents(strSel, null);
//		Actions keyAction = new Actions(driver); 
//		keyAction.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
//		keyAction.sendKeys(Keys.ENTER);
		
	}
	public String capturescreenshotforelement(WebElement ele) throws IOException
	{
		String screenshot2;
	File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	BufferedImage  fullImg = ImageIO.read(screenshot);

	// Get the location of element on the page
	org.openqa.selenium.Point point = ele.getLocation();

	// Get width and height of the element
	int eleWidth = ele.getSize().getWidth();
	int eleHeight = ele.getSize().getHeight();

	// Crop the entire page screenshot to get only element screenshot
	BufferedImage eleScreenshot= fullImg.getSubimage(point.getX()-20, point.getY()-20,
	    eleWidth+20, eleHeight+20);
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	ImageIO.write(eleScreenshot, "png", bos);
	byte[] imageBytes = bos.toByteArray();
    screenshot2 = "data:image/png;base64,"+Base64.getMimeEncoder().encodeToString(imageBytes);
    bos.close();
    return screenshot2;
	}
	
	public void clickUsingAction(WebElement el)
	{
		Actions act=new Actions(driver);
		act.moveToElement(el).click().build().perform();
	}
	
	public void waitforPagetobeenable() throws InterruptedException
	 { 
        WebElement el = driver.findElement(By.xpath("//body"));
        
        Log.info("Start");
        while (el.getAttribute("class").contains("loading-indicator"))
        {
              Log.info("Page Loading");
              Thread.sleep(5000);
        }

	 }
	
	
	public static String getCurrentDate() {
		java.util.Date date=java.util.Calendar.getInstance().getTime();  
		String currentDate=String.valueOf(date);
	return currentDate;	
	}
	
	
//	DriverTestcase.logger.log(LogStatus.PASS, "Step : MCS page navigated");
//	DriverTestcase dtc=new DriverTestcase();
//	public static ExtentReports extent;
//	public static ExtentTest logger;
	
	
	public static void logStatus(WebElement ele,String customTextpass, String customtextfail) {
		
		try {
			boolean flag = ele.isDisplayed();
			
			if(flag) {
				
				Log.info(customTextpass);
				DriverTestcase.logger.log(LogStatus.PASS,customTextpass );
			}else {
				
				try {
					Log.info(customtextfail);
					DriverTestcase.logger.log(LogStatus.FAIL,customtextfail );
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.info("Element is not available");
					DriverTestcase.logger.log(LogStatus.FAIL,customtextfail );
				}
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("element is not displayed");
			
		}catch (TimeoutException e) {
			
			// TODO Auto-generated catch block
						e.printStackTrace();
						Log.info("element not available");
		}
		
	}
	
//Common Methods
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
	public void ScrolltoElement(String application, String xpath, XMLReader xml) throws InterruptedException, DocumentException {
		WebElement element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",(element));
	}

	
	public void scrolltoview(WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}


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
			Log.info(labelname + " text field is displaying");
			
			if(expectedValueToAdd.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No values added to text field "+labelname);
				Log.info("No values added to text field "+labelname);
			}else {
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToAdd);
				Thread.sleep(3000);
				
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field value added as: "+ actualvalue);
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " text field is not displaying");
			Log.info(labelname + " text field is not displaying");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, labelname + " text field is not displaying");
		Log.info(labelname + " text field is not displaying");
	}catch(Exception ee) {
		ee.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to add value to "+ labelname + " text field");
		Log.info(" Not able to add value to "+ labelname + " text field");
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
	public void addDropdownValues_commonMethod(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml) throws InterruptedException, DocumentException {
		  boolean availability=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown is displaying");
			  Log.info(labelname + " dropdown is displaying");
			  
			  if(expectedValueToAdd.equalsIgnoreCase("null")) {
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
				  Log.info(" No values selected under "+ labelname + " dropdown");
			  }else {
				  
				  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
				  Thread.sleep(3000);
				  
				  //verify list of values inside dropdown
				  List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
				  Log.info( " List of values inside "+ labelname + "dropdown is:  ");
				  
					for (WebElement valuetypes : listofvalues) {
								Log.info("service sub types : " + valuetypes.getText());
								DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
								Log.info(" " + valuetypes.getText());
					}
					
					Thread.sleep(2000);
				SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
				Thread.sleep(2000);
					
				  Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
				  Thread.sleep(3000);
				  
				  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
				  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
				  Log.info( labelname + " dropdown value selected as: "+ actualValue);
				  
			  }
		  }else {
			  DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
			  Log.info(labelname + " is not displaying");
		  }
		}catch(NoSuchElementException e) {
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
			  Log.info(labelname + " is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
			Log.info(" NO value selected under "+ labelname + " dropdown");
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
				Log.info(labelname + " checkbox is displaying");
				
			boolean isElementSelected=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isSelected();
			Thread.sleep(2000);
		
		//verify whether checkbox is selected/unselected by default		
			if(DefaultSelection.equalsIgnoreCase("yes")) {
				if(isElementSelected) {
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is selected by default");
					Log.info(labelname + " checkbox is selected by default as expected");
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checkbox is not selected by default");
					Log.info(labelname + " checkbox is not selected by default");
				}
				
			}
			else if(DefaultSelection.equalsIgnoreCase("no")) {
				if(isElementSelected) {
					DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checkbox is selected by default");
					Log.info(labelname + " checkbox is selected by default as expected");
				}else {
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is not selected by default");
					Log.info(labelname + " checkbox is not selected by default");
				}
				
			}
	
		//Perform click on checkbox	
			if(!expectedValue.equalsIgnoreCase("null")) {
				if (expectedValue.equalsIgnoreCase("yes")) {

					if(isElementSelected) {
						DriverTestcase.logger.log(LogStatus.PASS, labelname +" checkbox is Selected.");
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
						Log.info("No changes made for "+ labelname +" checkbox");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for "+ labelname +" checkbox");
				Log.info("No changes made for "+ labelname +" checkbox");
			}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checbox is not available");
				Log.info(labelname + " checbox is not available");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, labelname +  " checkbox is not available ");
			Log.info( labelname +  " checkbox is not available ");
		}catch(Exception er) {
			er.printStackTrace();
			Log.info("Not able to perform selection for "+ labelname+ " checkbox");
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
				Log.info(labelname + " text field is displaying");
				
				if(expectedValueToEdit.equalsIgnoreCase("null")) {
					
					String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is not edited. It is displaying as "+actualvalue);
					Log.info(labelname + " text field is not edited as expected. It is displaying as "+actualvalue);
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
				Log.info(labelname + " text field is not displaying");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " text field is not displaying");
			Log.info(labelname + " text field is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to perform editing under"+ labelname + " text field");
			Log.info(" Not able to perform editing under "+ labelname + " text field");
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
		 			
		 			Log.info( fieldlabelName + " field warning  message displayed as : " + ErrMsg);
		 			DriverTestcase.logger.log(LogStatus.PASS, "Step :  validation message for"+ fieldlabelName +"  field displayed as : " + ErrMsg);
		 			Log.info(fieldlabelName + " field warning  message displayed as : " + ErrMsg);
		 			}else{
		 				DriverTestcase.logger.log(LogStatus.FAIL, "validation message for"+ fieldlabelName +"  field is not displaying");
		 				Log.info("validation message for"+ fieldlabelName +"  field is not displaying");
		 			}
		 			}catch(NoSuchElementException e) {
		 				e.printStackTrace();
		 				Log.info( "No warning message displayed for "+ fieldlabelName);
		 				DriverTestcase.logger.log(LogStatus.FAIL, "No warning message displayed for "+ fieldlabelName);
		 			}catch(Exception ed) {
		 				ed.printStackTrace();
		 				Log.info( "No warning message displayed for "+ fieldlabelName);
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
				  Log.info(labelname+ " checkbox is displaying in edit page");
				  
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
					Log.info(labelname+" checkbox is not displaying under 'Edit service' page");
				}catch(Exception err) {
					err.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on "+ labelname + " checkbox");
					Log.info(" Not able to click on "+ labelname + " checkbox");
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
					if((text.contains(" ")) ||  text.contains("-")) {
						
						String[] actualTextValue=text.split(" ");
						String[] expectedValue =ExpectedText.split(" ");
						
						if(expectedValue[0].equalsIgnoreCase(actualTextValue[0])) {
							DriverTestcase.logger.log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(expectedValue[0].contains(actualTextValue[0])) {
							DriverTestcase.logger.log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
						
					}else {
						if(ExpectedText.equalsIgnoreCase(text)) {
							DriverTestcase.logger.log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(ExpectedText.contains(text)) {
							DriverTestcase.logger.log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
				Log.info(labelname + " field is not displaying");
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
						DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+emptyele+"'");
					}
				}
			}catch (Exception e) {
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
				Log.info(labelname + " field is not displaying");
				e.printStackTrace();
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
					Log.info(labelname+" not found");
				}
				else if (emptyele!=null && emptyele.isEmpty()) {
//					DriverTestcase.logger.log(LogStatus.PASS,  labelname + "' value is empty");
					
					emptyele= "Null";
					
					sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
					
					if(emptyele.equalsIgnoreCase(ExpectedText)) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "The Expected value for '"+ labelname +"' field  is same as the Acutal value. It is id displaying blank");
						Log.info("The Expected value for '\"+ labelname +\"' field  is same as the Acutal value. It is displaying blank");
						
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL,"The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						Log.info(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					}
					

				}else 
				{   
					text = element.getText();
					if((text.contains(" ")) ||  text.contains("-")) {
						
						String[] actualTextValue=text.split(" ");
						String[] expectedValue =ExpectedText.split(" ");
						
						if(expectedValue[0].equalsIgnoreCase(actualTextValue[0])) {
							DriverTestcase.logger.log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(expectedValue[0].contains(actualTextValue[0])) {
							DriverTestcase.logger.log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
						
					}else {
						if(ExpectedText.equalsIgnoreCase(text)) {
							DriverTestcase.logger.log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						}
						else if(ExpectedText.contains(text)) {
							DriverTestcase.logger.log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
							Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						}
					}
					
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " field is not displaying");
				Log.info(labelname + " field is not displaying");
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
			
/**
 *  It fetches all the value inside dropdown. And it selects the required value inside dropdown			
 * @param application
 * @param xpath
 * @param labelname
 * @param expectedValueToAdd
 * @param xml
 * @throws IOException
 * @throws InterruptedException
 */
public void selectValueInsideDropdown(String application, String xpath, String labelname, String expectedValueToAdd, XMLReader xml) throws IOException, InterruptedException
			{ 
				//getAllValuesInsideDropDown
				 boolean availability=false;
				 List<String> ls = new ArrayList<String>();
				 
					try {  
					  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
					  if(availability) {
						  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown is displaying");
						  Log.info(labelname + " dropdown is displaying");
						  
						  WebElement el =getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
						  
						  Select sel = new Select(el);
						  
						 String firstSelectedOption=sel.getFirstSelectedOption().getText();
						 DriverTestcase.logger.log(LogStatus.PASS, "By default "+ labelname+" dropdown is displaying as: "+firstSelectedOption);
						 Log.info("By default "+ labelname+" dropdown is displaying as: "+firstSelectedOption);
						 
						    List<WebElement> we = sel.getOptions();
						   
						    for(WebElement a : we)
						    {
						        if(!a.getText().equals("select")){
						            ls.add(a.getText());
						            
						        }
						    }
					
						    DriverTestcase.logger.log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
				            Log.info("list of values inside "+labelname+" dropdown is: "+ls);
				            
						  if(expectedValueToAdd.equalsIgnoreCase("null")) {
							  
							  DriverTestcase.logger.log(LogStatus.PASS, "No values selected under "+ labelname + " dropdown");
						  }else {
							  Select s1=new Select(el);
							  s1.selectByVisibleText(expectedValueToAdd);
							  
							  String SelectedValueInsideDropdown=sel.getFirstSelectedOption().getText();
								 DriverTestcase.logger.log(LogStatus.PASS,  labelname+" dropdown value selected as: "+SelectedValueInsideDropdown);
								 Log.info(labelname+" dropdown value selected as: "+SelectedValueInsideDropdown);
						  }
						 }
						
					}catch(NoSuchElementException e) {
						DriverTestcase.logger.log(LogStatus.FAIL, labelname + " Value is not displaying");
						  Log.info(labelname + " value is not displaying");
					}catch(Exception ee) {
						ee.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
						Log.info(" NO value selected under "+ labelname + " dropdown");
					}
			}


public void successScreenshot(String application) {
	String screenshotBase64 = "data:image/jpg;base64," +((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	String ScreenshottoReport= DriverTestcase.logger.addScreenCapture(screenshotBase64);
	DriverTestcase.logger.log(LogStatus.PASS, ScreenshottoReport);
	
	}



public void SelectDropdownValueUnderSpanTag(String application ,String lebelname, String dropdownToBeSelectedInTheEnd, String dropdownXpath, String commonDropdownValueTag, XMLReader xml) throws InterruptedException, DocumentException, IOException {
	
	try {
		// Select  Country dropdown
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+dropdownXpath+"")));
			List<WebElement> dropdownValueList = driver.findElements(By.xpath(commonDropdownValueTag));
			
			for (WebElement dropdownvaluelist : dropdownValueList) {
				Log.info("Available " +lebelname+ " are : " + dropdownvaluelist.getText().toString()+ "  ,  ");
				DriverTestcase.logger.log(LogStatus.PASS,"Step : Available  '" +lebelname+ "'  is : " + dropdownvaluelist.getText().toString());
				Log.info("Available " +lebelname+ " is : " + dropdownvaluelist.getText().toString());
			}
			
			Thread.sleep(2000);
			// click on Country dropdown
			WebElement selectDropdownValue = driver.findElement(By.xpath("//span[@aria-label='"+dropdownToBeSelectedInTheEnd+"']"));//span[text()='" + dropdownToBeSelectedInTheEnd + "']
			Log.info("Selected  '" +lebelname+ "'  is : "+ selectDropdownValue.getText().toString());
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected  '"+lebelname+ "'  is : "+ selectDropdownValue.getText().toString());
			Log.info("Selected  '" +lebelname+ "'  is : " +selectDropdownValue.getText().toString());
			selectDropdownValue.click();
			Thread.sleep(2000);
			
			//span[@aria-label='Device-901']

}catch(NoSuchElementException e) {
	e.printStackTrace();
	
	DriverTestcase.logger.log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
	Log.info( lebelname + " dropdown is not displaying");
	
}catch(Exception ee) {
	ee.printStackTrace();
	
	DriverTestcase.logger.log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
	Log.info( lebelname + " dropdown is not displaying");
}

}


public void ClearAndEnterTextValue(String application, String labelname,  String xpath, String newValue, XMLReader xml) {
	WebElement element = null;
	try {
		//Thread.sleep(1000);
		element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		String value= element.getAttribute("value");
		
		if(value.isEmpty())
		{
			DriverTestcase.logger.log(LogStatus.INFO, "Step: '"+labelname+"' text field is empty");
			Log.info(value);
		
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
			
			
			
		
	
}
