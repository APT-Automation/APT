package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class IPVPNHelper_newTab extends DriverHelper{

	public IPVPNHelper_newTab(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	
	XMLReader xml = new XMLReader("src\\com\\colt\\qa\\pagerepository\\IPVPNnewTab.xml");
	
	
	public void searchOrderORservice(String application, String existingSiteOrder, String existingService) throws InterruptedException, DocumentException, IOException {
		
		Moveon(getwebelement(xml.getlocator("//locators/"+application+"/ManageCustomerServiceLink")));
		Thread.sleep(1000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Mouse hovered on 'Manage Customer Service' link");
		
		
		click_commonMethod(application, "Search order/service", "searchorderORservicelink", xml);
		
		
		addtextFields_commonMethod(application, "VPN Site Order", "searchSiteOrder_VPNSiteOrderField", existingSiteOrder, xml);
		scrolltoend();
		
		click_commonMethod(application, "Search", "searchButton" , xml);
		   waitforPagetobeenable();
		
		WebElement selectTheSearchedService = getwebelement(xml.getlocator("//locators/"+application+"/selectSearchedService").replace("value", existingService));
		Clickon(selectTheSearchedService);
		
		click_commonMethod(application, "Action", "searchSerice_ActionDropdown", xml);
		click_commonMethod(application, "View", "searchService_viewLink", xml);
		Thread.sleep(2000);
		
		   waitforPagetobeenable();
	}
	
	
	
	public void clickOnAddInterfaceLink(String application, String customerName, String serviceId, String siteOrderNumber,
			String deviceName) throws Exception {
		
		String expectedPageTitle= "Add Interface";
		
		WebElement routerToolPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/routerToolPanelheader"));
		scrolltoview(routerToolPanelHeader);
		Thread.sleep(1000);
		
		click_commonMethod(application, "Action", "InterfacePanel_actionDropdown" , xml);
		
		
		openLinkInNewTab(application, "addInterfaceOrLink", "Add Interface",  xml);		//opens Add Interface link in new tab
		Switchtotab();
		Thread.sleep(2000);
		
		    waitforPagetobeenable();
  		scrollToTop();
  		
  		String actualPageTitle=driver.getTitle();
  		
  		if(expectedPageTitle.equals(actualPageTitle)  ||  actualPageTitle.contains(expectedPageTitle)) {
  			
  		ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title is displaying as "+ actualPageTitle + " as expected");
  			Log.info("Page Title is displaying as "+ actualPageTitle + " as expected");
  			
  		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate breadcrumbs");
  			WebElement el1 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", "Home"));
  			isDisplayed(application, el1 , "Home", xml);
  			
  			WebElement el2 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", customerName));
  			isDisplayed(application, el2 , "Customer Name", xml);
  			
  			WebElement el3 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", serviceId));
  			isDisplayed(application, el3, "Service Name", xml);
  			
  			WebElement el4 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", siteOrderNumber));
  			isDisplayed(application, el4 , "Site order Number", xml);
  			
  			WebElement el5 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", deviceName));
  			isDisplayed(application, el5, "Device Name", xml);
  			
  			WebElement el6 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumbForcurrentPage").replace("value", "Add Interface / Link"));
  			isDisplayed(application, el6, "Add Interface", xml);
  			
  			
  		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate Panel Header");
  			compareText(application, "Add Interface Panel Header", "addInterfacePanelHeader", "Add Interface", xml);	//compare Panel Header
  			
  		}	
		
	}
	
	
	public String addInterface_Juniper(String application, String serviceSubType, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, String newAddressRangeIpv4selection, String newinterfaceAddressrange,
			String subnetSizeValue_IPv4, String availableBlocksValue_IPv4, String link, String bearerType, String encapsulation, String slot, String port, String vlanid,
			String unitId, String pic, String STM1number, String bandwidth, String cardType, String frameType, String clockSource, String timeSlot, 
			String configureInterfaceOnDevice, String IVmanagement,
			String existingAddressRangeIPv6selection, String  existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6,
			String subnetSizeValue_IPv6, String availableBlocksValue_IPv6, String bearerNumber) throws InterruptedException, DocumentException, 
	IOException { 
		
		boolean addressValue=false;
		
		   waitforPagetobeenable();
		
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
			
			  waitforPagetobeenable();
			click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
			
			String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
			
			if(interfaceAddressRange.isEmpty()) {
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
				Log.info("No values displaying under 'Interface Address Range_IPv4' dropdown");
				
			}else {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
				Log.info("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
				
				click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				Thread.sleep(5000);
				   waitforPagetobeenable();
				
				addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
				if(addressValue) {
					
					selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface" , existingAddressIPv4DropdownValue);
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
					Log.info("'Address_IPv4' dropdown is not displaying");
				}
			}
		}
		else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
			
			  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
			
			  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
			  
			  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
			  if(interfaceValueIntextField.isEmpty()) {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
				  Log.info("No values dipslaying under 'Address_IPv4' text field");
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  Log.info( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
			  }
		}
		
		

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
					Log.info("No values displaying under 'Interface Address Range_IPv6' dropdown");
					
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
					Log.info("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
					
					click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					Thread.sleep(4000);
					  waitforPagetobeenable();
					
				try {	
					addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
					if(addressValue) {
						
						selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						Log.info("'Address_IPv6' dropdown is not displaying");
					}
				  }catch(Exception e) {
					  e.printStackTrace();
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
					  Log.info("'Address_IPv6' dropdown is not displaying");
				  }
				
				}
				
			}
			else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
				
				  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
					  Log.info("No values dipslaying under 'Address_IPv6' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  Log.info( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
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
//			WebElement interfaceDefaultValue=getwebelement(xml.getlocator("//locators/" + application + "/Interface_textField"));
//			Gettext(interfaceDefaultValue);
			
			compareText(application, "Interface", "Interface_textField", "xe-//.0", xml);		//compare Interface default value
			
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
//			if(IVmanagement.equalsIgnoreCase("Yes")) {
//				
//				//VLAN Id
//				addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//			}
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
				 
				 Log.info("No additonal fields");
			 }
			 
			//Clock Source
			 	addDropdownValues_commonMethod(application, "Clock Source" , "clockSource_Dropdown", clockSource, xml);
			 	
			//STM1 Number
				addtextFields_commonMethod(application, "STM1 Number", "stm1Number_textField" , STM1number, xml); 	
				
			//Bearer No
				addtextFields_commonMethod(application, "Bearer No", "bearerNumber_addtextField", bearerNumber, xml);
				
			//Interface
				compareText(application, "Interface", "Interface_textField", "e1-//::.0", xml);
				
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
			
//			if(IVmanagement.equalsIgnoreCase("Yes")) {
//				
//				//VLAN Id
//				addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//			}
			
			
			//compare Interface Name
			String interfaceName_E3 = "e3-" + slot + "/" + pic + "/" + port + ":" + STM1number + ":." + unitId;
			compareText(application, "Interface", "Interface_textField", interfaceName_E3 , xml);
					
		}
		else if(bearerType.equals("Gigabit Ethernet")) {
			
			//Bandwidth
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_Dropdown" , bandwidth, xml);
			
			//Card Type
//			addDropdownValues_commonMethod(application, "Card Type", "cardType_Dropdown", cardType, xml);
			
			//Interface
			compareText(application, "Interface", "Interface_textField", "ge-//.0", xml);	
			
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
			
//			if(IVmanagement.equalsIgnoreCase("Yes")) {
//				
//				//VLAN Id
//				addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//			}
			
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
			
//			if(IVmanagement.equalsIgnoreCase("Yes")) {
//				
//				//VLAN Id
//				addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//			}
			
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
			
//			if(IVmanagement.equalsIgnoreCase("Yes")) {
//				
//				//VLAN Id
//				addtextFields_commonMethod(application, "VLAN Id", "vlanId_textField" , vlanid, xml);
//			}
			
			
			//Compare Interface Name
			String interfaceName_T3 = "t3-" + slot + "/" + pic + "/" + port + ":" + STM1number + "::." + unitId;
			compareText(application, "Interface", "Interface_textField", interfaceName_T3 , xml);
		}
		
		
	//Interface
		String interfaceName=getwebelement(xml.getlocator("//locators/" + application + "/Interface_textField")).getAttribute("value");
		if(interfaceName.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface' text field");
			Log.info("No values displaying under 'Interface' text field");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Name is displaying as: "+ interfaceName);
			Log.info("Interface Name is displaying as: "+ interfaceName);
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
			Log.info("'Configuration' panel is displaying");
			
			click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
			Thread.sleep(2000);
			
			scrolltoend();
			Thread.sleep(1000);
			
			String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
			if(configurationvalues.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				Log.info("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				Log.info("After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
			}
			Thread.sleep(2000);
//			click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			Log.info("'Configuration' panel is not displaying");
		}
	}
	else {
		
		scrolltoend();
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_okButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'OK' button");
//		click_commonMethod(application, "OK" , "" , xml);
	}
	return interfaceName;
}
	
	
	public void EIPallocationSuccessMessage(String application, String expected) throws InterruptedException {
		
		
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(3000);
		try {	
			
			boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert")).isDisplayed();

			if(successMsg) {
				String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
				if(alrtmsg.contains(expected)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
					Log.info("Message is verified. It is displaying as: "+alrtmsg);
					
				}else if(expected.equals(alrtmsg)){
					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
					Log.info("Message is verified. It is displaying as: "+alrtmsg);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
					Log.info("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
				Log.info(" Success Message is not displaying");
			}
			Thread.sleep(2000);
			
		}catch(Exception e) {
			Log.info("failure in fetching success message");
			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
			Log.info(expected+ " message is not getting dislpayed");
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
					Log.info("Message is verified. It is displaying as: "+alrtmsg);
					
				}else if(alrtmsg.contains(expected)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
					Log.info("Message is verified. It is displaying as: "+alrtmsg);
					
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
					Log.info("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
				}
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
				Log.info(" Success Message is not displaying");
			}
			
		}catch(Exception e) {
			Log.info("failure in fetching success message - 'Service created Successfully'  ");
			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
			Log.info(expected+ " message is not getting dislpayed");
		}
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
			            
			         Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectFirstValueUnderAddressDropdown")));
			         Thread.sleep(1000);
					
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
* 	
* @param application
* @throws DocumentException 
* @throws InterruptedException 
*/
	public void clickOnBreadCrump(String application, String breadCrumpLink) throws InterruptedException, DocumentException {
		
		
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(2000);
		WebElement breadcrumb=null;

		try {
		breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
		if(breadcrumb.isDisplayed()) {
			click_commonMethod_PassingWebelementDirectly_forBreadcrumb(application, "Breadcrump", breadcrumb, xml);
			Thread.sleep(3000);
		}else {
			Log.info("Breadcrumb is not displaying for the element "+ breadcrumb);
		}
	}catch(Exception e) {
		e.printStackTrace();
		Log.info("Breadcrumb is not displaying for the element "+ breadcrumb);
	}
}
	
	
	
	public void isDisplayed(String application, String xpath, String labelname) {
	boolean availability = false;
	
	try {
	Thread.sleep(1000);
	availability= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
	if (availability) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: '"+labelname+"' is displayed as expected");
		Log.info("Step: '"+labelname+"' is displayed as expected");
	}
	else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' is not displayed");
		Log.info("Step: '"+labelname+"' is not displayed");
		}
	
	} catch (Exception e) {
	ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: '"+labelname+"' is not available to display");
	e.printStackTrace();
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
			Thread.sleep(2000);
			
		}catch(Exception e) {
			Log.info("failure in fetching success message");
			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
			Log.info(expected+ " message is not getting dislpayed");
			Thread.sleep(2000);
		}

	}
	
	
	
	public String successmessageForInterfaceOrMultilinkCreation(String application, String expected) throws InterruptedException {
		
		
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
	
	

	public void PEinterface_clickOEditlink(String application, String interfaceName) throws Exception {
		
		  waitforPagetobeenable();
		
		scrolltoend();
		Thread.sleep(1000);
		
		click_commonMethod(application, "Show Interface", "PEdevice_showInterfaceLink" , xml);
		Thread.sleep(2000);
		
		WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectInterfaceUnderPEdevice").replace("value", interfaceName));
		Clickon(interfaceValue);
		ExtentTestManager.getTest().log(LogStatus.PASS, interfaceName + " interface is selected under 'Provider Equipment' panel");
		Log.info( interfaceName + " interface is selected under 'Provider Equipment' panel");
		
		click_commonMethod(application, "Action", "actionDropdown_UnderProviderEquipmentPanel" , xml);    //Click on 'Action' button
		
		
		openLinkInNewTab(application, "editLink_underProviderEquipment", "Edit Interface",  xml);		//opens Edit Interface link in new tab
		Switchtotab();
		Thread.sleep(2000);
//		click_commonMethod(application, "Edit", "editLink_underProviderEquipment", xml);	//click on edit Link
		
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
		
		  waitforPagetobeenable();
		click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
		
		String interfaceAddressRange=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
		
		if(interfaceAddressRange.isEmpty()) {
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv4' dropdown");
			Log.info("No values displaying under 'Interface Address Range_IPv4' dropdown");
			  
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
			Log.info("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
			
			click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
			
			addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterface")).isDisplayed();
			if(addressValue) {
				
				selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterface" , existingAddressIPv4DropdownValue);
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv4' dropdown is not displaying");
				Log.info("'Address_IPv4' dropdown is not displaying");
			}
		}
	}
	else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
		
		  edittextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
		
		  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
		  
		  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
		  if(interfaceValueIntextField.isEmpty()) {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv4' text field");
			  Log.info("No values dipslaying under 'Address_IPv4' text field");
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
			  Log.info( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
		  }
	}
}else {
	ExtentTestManager.getTest().log(LogStatus.PASS, "'V4 Configuration' is not edited");
	Log.info("'V4 Configuration' is not edited");
}
	
	
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
					Log.info("No values displaying under 'Interface Address Range_IPv6' dropdown");
					
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
					Log.info("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
					
					click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					
				try {	
					addressValue = getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceDropdown_addInterfaceIPv6")).isDisplayed();
					if(addressValue) {
						
						selectEnableValueUnderAddressDropdown(application, "Address", "FetchInterfaceDropdown_addInterfaceIPv6" , existingAddressIPv6DropdownValue);
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
						Log.info("'Address_IPv6' dropdown is not displaying");
					}
				  }catch(Exception e) {
					  e.printStackTrace();
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address_IPv6' dropdown is not displaying");
					  Log.info("'Address_IPv6' dropdown is not displaying");
				  }
				
				}
				
			}
			else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
				
				  edittextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
					  Log.info("No values dipslaying under 'Address_IPv6' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  Log.info( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'V6 Configuration' is not edited");
			Log.info("'V6 Configuration' is not edited");
		}
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
//			String InterfaceName_10Gig = "xe-" + slot + "/" + pic + "/" + port + "." + unitId; 
//			compareText(application, "Interface", "Interface_textField", InterfaceName_10Gig , xml);
			
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
				 
				 Log.info("No additonal fields");
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
//				String interfaceName_E1 = "e1-" + slot + "/" + pic + "/" + port + ":" + STM1number + ":." + unitId;
//				compareText(application, "Interface", "Interface_textField", interfaceName_E1 , xml);
			
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
//			String interfaceName_E3 = "e3-" + slot + "/" + pic + "/" + port + ":" + STM1number + ":." + unitId;
//			compareText(application, "Interface", "Interface_textField", interfaceName_E3 , xml);
					
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
//			String interfaceName_GigaBit = "ge-" + slot + "/" + pic + "/" + port + "."+ unitId;
//			compareText(application, "Interface", "Interface_textField", interfaceName_GigaBit , xml);
			
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
//			String interfaceName_STM1Number = "so-" + slot + "/" + pic + "/" + port + ":" + STM1number + "." + unitId;
//			compareText(application, "Interface", "Interface_textField", interfaceName_STM1Number , xml);	
			
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
//			//Compare Interface Name
//			String interfaceName_T3 = "t3-" + slot + "/" + pic + "/" + port + ":" + STM1number + "::." + unitId;
//			compareText(application, "Interface", "Interface_textField", interfaceName_T3 , xml);
		}
		
		
	//Interface
		String interfaceName=getwebelement(xml.getlocator("//locators/" + application + "/Interface_textField")).getAttribute("value");
		if(interfaceName.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface' text field");
			Log.info("No values displaying under 'Interface' text field");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Name is displaying as: "+ interfaceName);
			Log.info("Interface Name is displaying as: "+ interfaceName);
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
			Log.info("'Configuration' panel is displaying");
			
			click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
			Thread.sleep(2000);
			
			scrolltoend();
			Thread.sleep(1000);
			
			String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
			if(configurationvalues.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				Log.info("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				Log.info("After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
			}
		
			Thread.sleep(2000);
//			click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_executeAndSaveButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Execute and Save' button");

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			Log.info("'Configuration' panel is not displaying");
		}
	}
	else {
		
		scrolltoend();
//		click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_okButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'OK' button");

	}
	
	return interfaceName;
	}
	
	
	
	/**
	 *  Method is for clicking on "View" link for a PE device	
	 * @param application
	 * @param existingdevicename
	 * @throws Exception 
	 */
	public void PEdevice_clickOnViewPEdevice(String application,String customerName, String serviceId, String siteOrderNumber, String existingdevicename) throws Exception {
		
		String expectedPageTitle = existingdevicename;
		
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
	                      WebElement PEdevice_autoDiscoverVPN=getwebelement(xml.getlocator("//locators/" + application + "/clickOnviewLinkUnderProviderEqiupment").replace("value", AddedDevice_SNo)); 
	                     
	                      
	                      Actions action=new Actions(driver);
	      				
	      				action.keyDown(Keys.CONTROL).click(PEdevice_autoDiscoverVPN)
	      				.keyUp(Keys.CONTROL)
	      				.build()
	      				.perform();
	      				
	      				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'View' link");
	      				Log.info("Clicked on 'View' link");
	                    Switchtotab();
	                      
	                      waitforPagetobeenable();
	            		scrollToTop();
	            		
	            		String actualPageTitle=driver.getTitle();
	            		
	            		if(expectedPageTitle.equals(actualPageTitle)  ||  actualPageTitle.contains(expectedPageTitle)) {
	            			
	            		ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title is displaying as "+ actualPageTitle + " as expected");
	            			Log.info("Page Title is displaying as "+ actualPageTitle + " as expected");
	            			
	            		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate breadcrumbs");
	            			WebElement el1 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", "Home"));
	            			isDisplayed(application, el1 , "Home", xml);
	            			
	            			WebElement el2 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", customerName));
	            			isDisplayed(application, el2 , "Customer Name", xml);
	            			
	            			WebElement el3 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", serviceId));
	            			isDisplayed(application, el3, "Service Name", xml);
	            			
	            			WebElement el4 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", siteOrderNumber));
	            			isDisplayed(application, el4 , "Site order Number", xml);
	            			
	            			WebElement el5 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumbForcurrentPage").replace("value", existingdevicename));
	            			isDisplayed(application, el5, "Device Name", xml);
	            			
	            			
	            		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate Panel Header");
	            			compareText(application, "view device page_Device PanelHeader", "devicePanelHeader", "Device", xml);	//compare Panel Header
	            			  
	            		}
	            		
	            		
	                      Thread.sleep(2000);
	                      break;
	                }
	                else
	                {
//	                      ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
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
	 *  Method is for clicking on "Select interface" link for a PE device	
	 * @param application
	 * @param existingdevicename
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void PEdevice_clickOnselectInterface(String application,String customerName, String serviceId, String siteOrderNumber,
			String existingdevicename) throws Exception {
		
		String expectedPageTitle = existingdevicename;
		
		WebElement providerEquipment_panelHeader= getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_panelHeader"));
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
                          WebElement PEDevice_selectInterfaceLink=getwebelement(xml.getlocator("//locators/" + application + "/PE_selectInterface_InViewPage").replace("value", AddedDevice_SNo)); 
                          
                          Actions action=new Actions(driver);
  	      				
  	      				action.keyDown(Keys.CONTROL).click(PEDevice_selectInterfaceLink)
  	      				.keyUp(Keys.CONTROL)
  	      				.build()
  	      				.perform();
  	      				
  	      				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Select Interface' link");
  	      				Log.info("Clicked on 'View' link");
  	                    Switchtotab();
  	                      	
  	                      waitforPagetobeenable();
  	            		scrollToTop();
  	            		
  	            		String actualPageTitle=driver.getTitle();
  	            		
  	            		if(expectedPageTitle.equals(actualPageTitle)  ||  actualPageTitle.contains(expectedPageTitle)) {
  	            			
  	            		ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title is displaying as "+ actualPageTitle + " as expected");
  	            			Log.info("Page Title is displaying as "+ actualPageTitle + " as expected");
  	            			
  	            		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate breadcrumbs");
  	            			WebElement el1 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", "Home"));
  	            			isDisplayed(application, el1 , "Home", xml);
  	            			
  	            			WebElement el2 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", customerName));
  	            			isDisplayed(application, el2 , "Customer Name", xml);
  	            			
  	            			WebElement el3 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", serviceId));
  	            			isDisplayed(application, el3, "Service Name", xml);
  	            			
  	            			WebElement el4 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", siteOrderNumber));
  	            			isDisplayed(application, el4 , "Site order Number", xml);
  	            			
  	            			WebElement el5 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumbForcurrentPage").replace("value", existingdevicename));
  	            			isDisplayed(application, el5, "Device Name", xml);
  	            			
  	            			
  	            		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate Panel Header");
  	            			compareText(application, "view device page_Device PanelHeader", "devicePanelHeader", "Device", xml);	//compare Panel Header
  	     
  	            		}     
                          Thread.sleep(2000);
                          break;
                    }
                    else
                    {
//                          ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                    	Log.info("Invalid device name");
                    }
                    
              }
        }
        else
        {
              ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
        }		
	}
	
	

	public void SelectInterfacetoremovefromservice(String Application, String interfacename)
			throws IOException, InterruptedException, DocumentException {
		
		waitforPagetobeenable();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Select Interface'_Remove Interace from Service");
		
		WebElement siteValue = getwebelement(xml.getlocator("//locators/" + Application + "/siteValue_IPVPN"));
		scrolltoview(siteValue);
		
			addtextFields_commonMethod(Application, "search", "interfacesInServices_searchTextBox", interfacename, xml);
			
			Thread.sleep(1000);
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

		waitforPagetobeenable();
		
		WebElement interfaceInService = getwebelement(xml.getlocator("//locators/" + Application + "/interfaceInService_PanelHeader"));
		scrolltoview(interfaceInService);
		Thread.sleep(1000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Select interface'_Add Interface to Service'");
		
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

				Log.info("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[text()='" + interfacenumber + "']");
				int numofrows = results.size();
				Log.info("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {
					PageNavigation_NextPageForInterfaceToselect(Application);
				}

				else {
					for (int i = 0; i < numofrows; i++) {
						try {
							resultflag = results.get(i).isDisplayed();
							Log.info("status of result: " + resultflag);
							if (resultflag) {
								Log.info(results.get(i).getText());
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
			Log.info("No values found inside the table");
			Log.info("No values available inside the Interfacetoselect table");
			ExtentTestManager.getTest().log(LogStatus.FAIL, interfacenumber + " value is not dipslaying under 'Interfaces to select' table");
		}

	}
	

	public void PageNavigation_NextPageForInterfaceToselect(String Application)
			throws InterruptedException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_nextpage")));
		Thread.sleep(3000);

	}

	
}
