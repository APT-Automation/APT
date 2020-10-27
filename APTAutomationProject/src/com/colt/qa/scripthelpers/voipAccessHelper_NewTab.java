package com.colt.qa.scripthelpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.Log;

public class voipAccessHelper_NewTab extends DriverHelper{

	public voipAccessHelper_NewTab(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
XMLReader xml = new XMLReader("src\\com\\colt\\qa\\pagerepository\\voipAccess_newtab.xml");
	
	
	public void searchOrderORservice(String application, String existingService) throws InterruptedException, DocumentException, IOException {
		
		Moveon(getwebelement(xml.getlocator("//locators/"+application+"/ManageCustomerServiceLink")));
		Thread.sleep(1000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Mouse hovered on 'Manage Customer Service' link");
		
		
		click_commonMethod(application, "Search order/service", "searchorderORservicelink", xml);
		
		
		addtextFields_commonMethod(application, "Service", "searchService_serviceTextField", existingService, xml);
		scrolltoend();
		
		click_commonMethod(application, "Search", "searchButton" , xml);
		waitForpageload();   waitforPagetobeenable();
		
		WebElement selectTheSearchedService = getwebelement(xml.getlocator("//locators/"+application+"/selectSearchedService").replace("value", existingService));
		Clickon(selectTheSearchedService);
		
		click_commonMethod(application, "Action", "searchSerice_ActionDropdown", xml);
		click_commonMethod(application, "View", "searchService_viewLink", xml);
		Thread.sleep(2000);
		
		waitForpageload();   waitforPagetobeenable();
	}
	
	
	
	/**
	 * select IMS location for MAS switch	
	 * @param application
	 * @param MAS_IMSPOPLocation
	 * @throws Exception 
	 */
	public void verifyAddMASswitch(String application, String MAS_IMSPOPLocation, String customerName, String serviceName) throws Exception {

		String expectedPageTitle = "Add MAS Switch";
		
		WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
		scrolltoview(managementOptions_header);
		Thread.sleep(1000);
		
		openLinkInNewTab(application, "addMASswitch_link", "Add MAS switch",  xml);		//opens MAS SWitch link in new tab
		Switchtotab();

		
		String actualPageTitle=driver.getTitle();
		if(expectedPageTitle.equals(actualPageTitle)) {
			
		ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title is displaying as "+ actualPageTitle + " as expected");
			Log.info("Page Title is displaying as "+ actualPageTitle + " as expected");
			
		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate breadcrumbs");
			WebElement el1 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", "Home"));
			isDisplayed(application, el1 , "Home", xml);
			
			WebElement el2 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", customerName));
			isDisplayed(application, el2 , "Customer Name", xml);
			
			WebElement el3 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", serviceName));
			isDisplayed(application, el3, "Service Name", xml);
			
			WebElement el4 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumbForcurrentPage").replace("value", "Add MAS Switch"));
			isDisplayed(application, el4, "Add MAS Switch", xml);
			
		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate Panel Header");
			compareText(application, "Add MAS Switch header", "MAS_AddMASSwitch_header", "Add MAS Switch", xml);  //compare MAS switch Header
			
			
		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate Functionalities in 'Add MAS SWitch' page");
			click_commonMethod(application, "OK", "okButton", xml);
			warningMessage_commonMethod(application, "MASswitch_warningmessage", "IMS POP Location", xml);
			
			addDropdownValues_commonMethod(application, "IMS POP Location", "AddmasSWitch_IMSpopswitch_dropdown", MAS_IMSPOPLocation, xml);
			
			click_commonMethod(application, "OK", "okButton", xml);
			Thread.sleep(4000);
			
			verifysuccessmessage(application, "MAS switch added successfully.");
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title mismatches. It is displaying as "+ actualPageTitle);
			Log.info("Page Title mismatches. It is displaying as "+ actualPageTitle);
		}
		
		Log.info("------ MAS Switch added successfully ------");
	}
	
	
	
		
		public void editMASdevice(String application, String customerName, String serviceName,  String editDeviceName, String editVendorModel, String editmanageAddress,
				String editSnmpro) 
						throws Exception {
			
			
			WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
			scrolltoview(managementOptions_header);
			Thread.sleep(2000);
			
			String expectedPageTitle = "Edit MAS Switch";
			openLinkInNewTab(application, "editLink", "Edit MAS switch",  xml);		//opens Edit MAS SWitch link in new tab
			Switchtotab();

			waitForpageload();   waitforPagetobeenable();
			String actualPageTitle=driver.getTitle();
			if(expectedPageTitle.equals(actualPageTitle)) {
				
				
			ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title is displaying as "+ actualPageTitle + " as expected");
				Log.info("Page Title is displaying as "+ actualPageTitle + " as expected");
				
			ExtentTestManager.getTest().log(LogStatus.INFO, "Validate breadcrumbs");
				WebElement el1 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", "Home"));
				isDisplayed(application, el1 , "Home", xml);
				
				WebElement el2 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", customerName));
				isDisplayed(application, el2 , "Customer Name", xml);
				
				WebElement el3 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", serviceName));
				isDisplayed(application, el3, "Service Name", xml);
				
				WebElement el4 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumbForcurrentPage").replace("value", "Edit MAS Switch"));
				isDisplayed(application, el4, "Edit MAS Switch", xml);
				
				
				
			ExtentTestManager.getTest().log(LogStatus.INFO, "validate Fields");
				//Device name	
//					edittextFields_commonMethod(application, "Device name", "MAS_deviceName", editDeviceName, xml);
					
				//vendor/model	
					addDropdownValues_ForSpanTag(application, "Vendor/Model", "MAS_vendorModel", editVendorModel, xml);
					
				//Management Address	
					edittextFields_commonMethod(application, "Management Address", "MAS_managementAddress", editmanageAddress, xml);
					
				//Snmrpo	
					edittextFields_commonMethod(application, "Snmpro", "MAS_snmpro", editSnmpro, xml);
					
					scrolltoend();
					click_commonMethod(application, "OK", "okButton", xml);
					
					verifysuccessmessage(application, "MAS switch updated successfully");
				
			}else {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title is mismatches. It is displaying as "+ actualPageTitle);
				Log.info("Page Title is mismatches. It is displaying as "+ actualPageTitle);
			}
		}
		
		
		
		
		public void closeChildtab(String application) throws InterruptedException {
			
			CloseProposalwindow();
			CloseProposalwindow();
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
		public void addDropdownValues_ForSpanTag(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml) throws InterruptedException, DocumentException {
			  boolean availability=false;
			  List<String> ls = new ArrayList<String>();
			  
			try {  
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			  if(availability) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				  Log.info(labelname + " dropdown is displaying");
				  
				  if(expectedValueToAdd.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					  Log.info(" No values selected under "+ labelname + " dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					  Thread.sleep(3000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					  Log.info( " List of values inside "+ labelname + "dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									
									 ls.add(valuetypes.getText());
						}
						
						
						    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
				            Log.info("list of values inside "+labelname+" dropdown is: "+ls);
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(2000);
						
					  Clickon(getwebelement("(//span[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					  Thread.sleep(3000);
					  
					  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
					  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
					  Log.info( labelname + " dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  Log.info(labelname + " is not displaying");
			  }
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				  Log.info(labelname + " is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
				Log.info(" NO value selected under "+ labelname + " dropdown");
			}
			
		}
		
		
		
		
		/**
		 * 	clicking on breadcrump for navigation
		 * @param application
		 * @throws DocumentException 
		 * @throws InterruptedException 
		 */
			public void clickOnBreadCrump(String application, String breadCrumpLink) throws InterruptedException, DocumentException {
				
				scrollToTop();
				Thread.sleep(2000);
				WebElement breadcrumb=null;
		
				try {
				breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
				if(breadcrumb.isDisplayed()) {
					click_commonMethod_PassingWebelementDirectly_forBreadcrumb(application, "Breadcrump", breadcrumb, xml);
				}else {
					Log.info("Breadcrumb is not displaying for the element "+ breadcrumb);
				}
			}catch(Exception e) {
				e.printStackTrace();
				Log.info("Breadcrumb is not displaying for the element "+ breadcrumb);
			}
	}
		
	
			public void verifysuccessmessage(String application, String expected) throws InterruptedException {
				
				waitforPagetobeenable();
				
				scrollToTop();
				Thread.sleep(3000);
				try {	
					
					boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert")).isDisplayed();
					if(successMsg) {
						String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
						if(expected.contains(alrtmsg)) {
							ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
							Log.info("Message is verified. It is displaying as: "+alrtmsg);
							successScreenshot(application);
						}else if(expected.equals(alrtmsg)){
							ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
							Log.info("Message is verified. It is displaying as: "+alrtmsg);
							successScreenshot(application);
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
							Log.info("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
						}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
						Log.info(" Success Message is not displaying");
					}
					
				}catch(Exception e) {
					Log.info("failure in fetching success message");
					ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
					Log.info(expected+ " message is not getting dislpayed");
					Thread.sleep(2000);
				}
			}
			
			
			
			/**
			 * For deleting the MAS SWitches
			 * @param application
			 * @param existingdevicename
			 * @throws InterruptedException
			 * @throws DocumentException
			 */
			public void MASswitch__DeleteFromServiceFunctionality(String application, String existingdevicename) throws InterruptedException, DocumentException { 
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete MAS Switch' Functionality");
				
				WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
				scrolltoview(managementOptions_header);
				Thread.sleep(2000);
				
				if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid_MAS")).isDisplayed())
	            {
	                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/MAS_fetchAlldevice_InviewPage"));
//	                  Log.info(addeddevicesList);
	                  int AddedDevicesCount= addeddevicesList.size();
	                  for(int i=0;i<AddedDevicesCount;i++) {
	                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
	                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
	                        if(AddedDeviceNameText.contains(existingdevicename))
	                        {
	                              WebElement AddedDevice_DeletefromserviceLink=getwebelement(xml.getlocator("//locators/" + application + "/MAS_deleteFromService_InViewPage").replace("value", AddedDevice_SNo)); 
	                              Clickon(AddedDevice_DeletefromserviceLink);
	                              Thread.sleep(2000);
	                              WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
	                              if(DeleteAlertPopup.isDisplayed())
	                              {
	                                    click_commonMethod(application, "Delete", "deletebutton", xml);
	                                    compareText(application, "Device delete success message", "MAS_deleteSuccessMessage", "MAS switch deleted successfully", xml);
	                                    break;
	                              
	                              }
	                              else
	                              {
	                                    Log.info("Delete alert popup is not displayed");
	                                    ExtentTestManager.getTest().log(LogStatus.FAIL, " Delete alert popup is not displayed");
	                              }
	                        }
	                        else
	                        {
	                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
	                        }
	                       
	                  }
	            }
	            else
	            {
	                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid under 'MAS Switch' Panel");
	            }		
				}
			
			


		


}
