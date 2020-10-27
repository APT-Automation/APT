package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.java_cup.internal.runtime.Scanner;
import com.sun.java_cup.internal.runtime.Symbol;


public class APT_MCS_CustomerUserHelper extends DriverHelper {
	WebDriver driver;
	public APT_MCS_CustomerUserHelper(WebDriver dr) {
		super(dr);
		driver=dr;
	}
	
	WebElement Name_Textfield, MainDomain_Textfield, countryDropdown, OCN_Textfield, Reference_Textfield, TechnicalContactName_Textfield, Type_Textfield, Email_Textfield, Phone_Textfield, Fax_Textfield, EnableDedicatedPortal_TextField, OkButton_CreateCustomer, ClearButton_CreateCustomer, typeDropdown ;
	SoftAssert sa=new SoftAssert();
	XMLReader xml=new XMLReader("src\\com\\colt\\qa\\pagerepository\\APT_MCS_CreateCustomer.xml");
	
	
	
	public void navigateToManageCustomerServicePage(String application) throws InterruptedException, DocumentException {
		
		Moveon(getwebelement(xml.getlocator("//locators/"+application+"/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Mouse Hovered on 'Manage Customer Service'");
		Log.info(" MCS page navigated ");
		Thread.sleep(4000);
	}
	
	
	public void navigateToCreateCustomerPage(String application) throws InterruptedException, DocumentException {
		navigateToManageCustomerServicePage(application);
		Clickon(getwebelement(xml.getlocator("//locators/"+application+"/CreateCustomerLink"))); 	
		ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated to 'create Customer' Page");
		Log.info(" Create customer navigated ");	
	}
	
	
	
	public void createCustomerFunctionality(String application, String Name, String MainDomain, String CountryToBeSelected,
			String TypeToBeSelected, String OCN,String Reference, String TechnicalContactName, String Email, String Phone,
			String Fax, String enableDedicatedPortal, String DedicatedPortalValue) throws InterruptedException, DocumentException, IOException {

		Log.info(" Create customer navigated ");
		
		scrolltoend();
		Clickon(getwebelement(xml.getlocator("//locators/"+application+"/OkButton_CreateCustomer")));
		scrollToTop();
		
		//Warning Messages
		warningMessage_commonMethod(application, "name_validationmsg", "Legal Customer Name", xml);
		warningMessage_commonMethod(application, "country_validationmsg", "Country", xml);
		warningMessage_commonMethod(application, "OCN_validationmsg", "OCN", xml);
		warningMessage_commonMethod(application, "type_validationmsg", "Type", xml);
		warningMessage_commonMethod(application, "email_Validationmsg", "Email", xml);
		
		
		//Name
			addtextFields_commonMethod(application, "Legal Customer Name", "Name", Name, xml);

		//Main Domain
			addtextFields_commonMethod(application, "Main Domain", "MainDomain", MainDomain, xml);

		//Country dropdown
			addDropdownValues_commonMethod(application, "Country", "CountrySelect", CountryToBeSelected, xml);
				
		//OCN
			addtextFields_commonMethod(application, "OCN", "OCN", OCN, xml);

		//Reference
			addtextFields_commonMethod(application, "reference", "Referance_Build4", Reference, xml);

		//technical Contact name
			addtextFields_commonMethod(application, "Technical Contact Name", "TechinicalContactName_Build4", TechnicalContactName, xml);
			
		//Type dropdown
			addDropdownValues_commonMethod(application, "Type", "TypeSelect_Build4", TypeToBeSelected, xml);
					
		//Email
			addtextFields_commonMethod(application, "Email", "Email", Email, xml);

		//Phone	
			addtextFields_commonMethod(application, "Phone", "Phone", Phone, xml);

		//FAX
			addtextFields_commonMethod(application, "Fax", "Fax", Fax, xml);
		
		//Enable dedicated portal checkbox
			addCheckbox_commonMethod(application, "EnableDedicatedPortalCheckbox", "Enable Dedicated Portal", enableDedicatedPortal, "no", xml);
			
			if(enableDedicatedPortal.equalsIgnoreCase("Yes")) {
				//Dedicated Portal dropdown	
				addDropdownValues_commonMethod(application, "Dedicated Portal", "dedicatedPortalDropdown", DedicatedPortalValue, xml);
			}
		
		
		scrolltoend();
		click_commonMethod(application, "OK", "OkButton_CreateCustomer", xml);
		
}

	
	
	
	
	public void deleteCustomer(String application) throws InterruptedException, DocumentException {
		scrollToTop();
		Thread.sleep(2000);
		
		click_commonMethod(application, "Action", "CustomerDetails_Action", xml);
		
		click_commonMethod(application, "Delete Customer Link", "DeleteCustomerLink" , xml);
		
		
		Clickon(getwebelement(xml.getlocator("//locators/"+application+"/DeleteButton_DeleteCustomer")));	Thread.sleep(1000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Delete button");
		Log.info(" Clicked on Delete button ");
		Thread.sleep(2000);
	}
	
	
	
	public void verifyEditCustomerFunction(String application, String Name, String MainDomain, String CountryToBeSelected,
		String TypeToBeSelected, String OCN,String Reference, String TechnicalContactName, String Email, String Phone,
		String Fax, String enableDedicatedPortal, String DedicatedPortalValue) throws InterruptedException, DocumentException, IOException {

		click_commonMethod(application, "Action", "CustomerDetails_Action", xml);
		Thread.sleep(1000);

		click_commonMethod(application, "Edit", "EditCustomerLink", xml);
		
		edittextFields_commonMethod(application, "Legal Customer Name", "Name_EditCustomer", Name, xml);   //name field
		
		edittextFields_commonMethod(application, "Main Domain", "domain_EditCustomer", MainDomain, xml);  //main Domain field
		
		addDropdownValues_commonMethod(application, "Country", "CountrySelect", CountryToBeSelected, xml);   //Country dropdown

		edittextFields_commonMethod(application, "Reference", "reference_EditCustomer", Reference, xml);   //reference field

		edittextFields_commonMethod(application, "Technical Contact Name", "Technicalname_EditCustomer", TechnicalContactName, xml);   //technical contact name field

		addDropdownValues_commonMethod(application, "Type", "TypeSelect_Build4", TypeToBeSelected, xml);   //Type dropdown

		edittextFields_commonMethod(application, "Email", "Email_EditCustomer", Email, xml);   //Email field

		edittextFields_commonMethod(application, "Phone", "phone_EditCustomer", Phone, xml);   //Phone field
		
		edittextFields_commonMethod(application, "Fax", "fax_EditCustomer", Fax, xml);
		
		
	//Enable dedicated portal checkbox
		addCheckbox_commonMethod(application, "EnableDedicatedPortalCheckbox", "Enable Dedicated Portal", enableDedicatedPortal, "no", xml);
		
		WebElement dedicatedPortalStatus=getwebelement(xml.getlocator("//locators/"+application+"/EnableDedicatedPortalCheckbox"));
		if(dedicatedPortalStatus.isSelected()) {
			addDropdownValues_commonMethod(application, "Dedicated Portal", "dedicatedPortalDropdown", DedicatedPortalValue, xml);
		}
		
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "OkButton_EditCustomer" , xml);

	}
	
	
	public void VerifyAddUserFunctionality(String application, String UserName, String FirstName, String SurName,String PostalAddress, String Email_AddUser, String Phone_AddUser,
			String IPGuardianAccountGroup,String ColtOnlineUser, String GeneratePassword, String RolesToBeSelected,String HideRouterToolsIPv6CommandsCisco_ToBeSelected, 
			String HideRouterToolsIPv4CommandsHuiwai_ToBeSelected,String HideRouterToolsIPv4CommandsCisco_ToBeSelected,String HideServicesToBeSelected,String HideSiteOrderToBeSelected) throws InterruptedException, DocumentException, IOException {   
		Thread.sleep(1000);
		
		
		String[] rolestobeSelectedList=RolesToBeSelected.split(",");
		String[] routerToolIPv4CiscoTobeSelectedList = HideRouterToolsIPv4CommandsCisco_ToBeSelected.split(",");
		String[] routerToolIPv4HuaweiTobeSelectedList =  HideRouterToolsIPv4CommandsHuiwai_ToBeSelected.split(",");
		String[] ServicesTobeSelectedlist= HideServicesToBeSelected.split(",");
		String[] siteOrdersToBeselectedList = HideSiteOrderToBeSelected.split(",");
		String[] routerToolIPv6ToBeSelectedList = HideRouterToolsIPv6CommandsCisco_ToBeSelected.split(",");
		
		scrolltoend();

			click_commonMethod(application, "user_Action", "Users_Action" , xml);

			click_commonMethod(application, "Add User", "AddUserLink" , xml);

			Thread.sleep(2000);
			
		scrollToTop();
		Thread.sleep(1000);
			addtextFields_commonMethod(application, "User Name", "UserName" , UserName, xml);   //User Name Field

			addtextFields_commonMethod(application, "First Name", "FirstName" , FirstName, xml);   //First Name Field

			addtextFields_commonMethod(application, "Sur Name", "SurName" , SurName, xml);   //Sur Name Field

			addtextFields_commonMethod(application, "Postal Address", "PostalAddress" , PostalAddress, xml);   //Postal Address Field

			addtextFields_commonMethod(application, "Email", "Email" , Email_AddUser, xml);   //Email field

			addtextFields_commonMethod(application, "Phone", "Phone" , Phone_AddUser, xml);   //Phone Field
			
			addtextFields_commonMethod(application, "IPGuardian Account Group" , "IPGuardianAccountGroup" , IPGuardianAccountGroup, xml);  //IPGuardian Account Group Field
			
			addtextFields_commonMethod(application, "Colt Online User", "ColtOnlineUser", ColtOnlineUser, xml);   //Colt online User field

			click_commonMethod(application, "Generate Password", "GeneratePasswordLink", xml);   //Generate Password Link
			
			String  password=getwebelement(xml.getlocator("//locators/"+application+"/Password_Textfield")).getAttribute("value");
			Log.info("Generated Password is : "+password);
			
			if(password.isEmpty()) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password Field is empty. No values displaying after clicked on 'Generate password link");

				SendKeys(getwebelement(xml.getlocator("//locators/"+application+"/Password_Textfield")), GeneratePassword);	
				Thread.sleep(1000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password entered manually not automatically generated :  "+GeneratePassword);
				Log.info("Password entered manually not automatically generated ");

			}else {
				Log.info("Automatically generated Password value is : "+ password);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password generated and the value is displaying as :  "+password);
			}

			
			WebElement Email=getwebelement(xml.getlocator("//locators/"+application+"/EmailLabelname"));
			scrolltoview(Email);
			
		//Role	
			selectAndAddValueFromLeftDropdown(application, "Role", "roleDropdown_available" , rolestobeSelectedList, "roleDropdown_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Role", "roleDropdown_selectedValues");
			
			
		//Hide Service
			selectAndAddValueFromLeftDropdown(application, "Hide Service", "HideService_Available", ServicesTobeSelectedlist, "HideService_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Services", "HideServicesDropdown_selectedValues");
		
			
		//Hide Site Order
			selectAndAddValueFromLeftDropdown(application, "Hide Site Order", "HideSiteOrder_Available" , siteOrdersToBeselectedList , "hideSiteOrder_addbutton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Site Order" , "HideSiteOrderDropdown_selectedValues");
			
			scrolltoend();
			Thread.sleep(1000);
			
		//Hide Router Tool IPv4 Commands(Cisco)
			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv4 Commands(Cisco)", "hideRouterToolIPv4_Cisco_Available", routerToolIPv4CiscoTobeSelectedList, "hideRouterToolIPv4_Cisco_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv4 Commands(Cisco)", "hideRouterToolIpv4_Cisco_selectedvalues");
			
			
		//Hide Router Tool IPv4 Commands(Huawei)
			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIPv4_Huawei_available" , routerToolIPv4HuaweiTobeSelectedList, "hideRouterToolIPv4__Huawei_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIpv4_Huawei_selectedvalues");
			
			
//		//Hide Router Tool IPv6 Commands(Cisco)
			selectAndRemoveValueFromRightDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)", "hidenRouterIPv6_cisco", routerToolIPv6ToBeSelectedList, "hideRouterToolIPv6_Cisco_removeButton");
//			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , "HideRouterToolIPv6_Cisco_Available" , selectValue, xpathForAddButton);
//			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , xpath);
			
			
		scrolltoend();

		click_commonMethod(application, "OK" , "OkButton_AddUser" , xml);
		
	}
	
	
	
	public void verifyEditUserFunction(String application, String UserName, String FirstName, String SurName,String PostalAddress, String Email_AddUser, String Phone_AddUser,
			String IPGuardianAccountGroup,String ColtOnlineUser, String GeneratePassword,
			String editRolesToBeSelected, String edit_RoleToBeHidden,
			String	RouterToolsIPv6CommandsCisco_ToBeAvailable, String RouterToolsIPv6CommandsCisco_ToBeHidden,
			String RouterToolsIPv4CommandsHuiwai_ToBeAvailable, String HideRouterToolsIPv4CommandsHuiwai_ToBeHidden,
			String HideRouterToolsIPv4CommandsCisco_ToBeAvailable, String HideRouterToolsIPv4CommandsCisco_ToBeHidden,
			String Services_ToBeAvailable, String Services_ToBeHidden,
			String SiteOrders_ToBeAvailable, String SiteOrders_ToBeHidden)
					throws InterruptedException, DocumentException, IOException {
	

		String[] rolestobeAvailableList=editRolesToBeSelected.split(",");
		String[] rolestobeHiddenList=edit_RoleToBeHidden.split(",");
		
		String[] routerToolIPv4CiscoTobeAvailableList = HideRouterToolsIPv4CommandsCisco_ToBeAvailable.split(",");
		String[] routerToolIPv4CiscoTobeHiddenList = HideRouterToolsIPv4CommandsCisco_ToBeHidden.split(",");
		
		String[] routerToolIPv4HuaweiTobeAvailableList =  RouterToolsIPv4CommandsHuiwai_ToBeAvailable.split(",");
		String[] routerToolIPv4HuaweiTobeHiddenList =  HideRouterToolsIPv4CommandsHuiwai_ToBeHidden.split(",");
		
		
		String[] ServicesTobeAvailablelist= Services_ToBeAvailable.split(",");
		String[] ServicesTobeHiddenlist= Services_ToBeHidden.split(",");
		
		String[] siteOrdersToBeAvailableList = SiteOrders_ToBeAvailable.split(",");
		String[] siteOrdersToBeHiddenList = SiteOrders_ToBeHidden.split(",");
		
		scrollToTop();
		Thread.sleep(3000);
		
			edittextFields_commonMethod(application, "User Name", "UserName" , UserName, xml);   //User Name Field

			edittextFields_commonMethod(application, "First Name", "FirstName" , FirstName, xml);   //First Name Field

			edittextFields_commonMethod(application, "Sur Name", "SurName" , SurName, xml);   //Sur Name Field

			edittextFields_commonMethod(application, "Postal Address", "PostalAddress" , PostalAddress, xml);   //Postal Address Field

			edittextFields_commonMethod(application, "Email", "Email" , Email_AddUser, xml);   //Email field

			edittextFields_commonMethod(application, "Phone", "Phone" , Phone_AddUser, xml);   //Phone Field
			
			edittextFields_commonMethod(application, "IPGuardian Account Group" , "IPGuardianAccountGroup" , IPGuardianAccountGroup, xml);  //IPGuardian Account Group Field
			
			edittextFields_commonMethod(application, "Colt Online User", "ColtOnlineUser", ColtOnlineUser, xml);   //Colt online User field

//			click_commonMethod(application, "Generate Password", "GeneratePasswordLink", xml);   //Generate Password Link
			
			String  password=getwebelement(xml.getlocator("//locators/"+application+"/Password_Textfield")).getAttribute("value");
			Log.info("Generated Password is : "+password);
			
			if(password.isEmpty()) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password Field is empty. No values displaying under'Generate password link");
				
				click_commonMethod(application, "Generate Password", "GeneratePasswordLink", xml);   //Generate Password Link
				
			}else {
				Log.info("Automatically generated Password value is : "+ password);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password generated and the value is displaying as :  "+password);
			}

			WebElement Email=getwebelement(xml.getlocator("//locators/"+application+"/EmailLabelname"));
			scrolltoview(Email);
			Thread.sleep(2000);
			
		//Role	
			selectAndRemoveValueFromRightDropdown(application, "Roles_Hidden", "roleDropdown_selectedValues", rolestobeAvailableList, "roleDropdown_removeButton");
			selectAndAddValueFromLeftDropdown(application, "Role_Available", "roleDropdown_available" , rolestobeHiddenList, "roleDropdown_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Role_Hidden", "roleDropdown_selectedValues");
			
			
		//Hide Service
			selectAndRemoveValueFromRightDropdown(application, "Service_Hidden" , "HideServicesDropdown_selectedValues" , ServicesTobeAvailablelist, "HideService_removeButton");
			selectAndAddValueFromLeftDropdown(application, "Service_Available", "HideService_Available", ServicesTobeHiddenlist, "HideService_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hidden Services", "HideServicesDropdown_selectedValues");
		
			
		//Hide Site Order
			selectAndRemoveValueFromRightDropdown(application, "SiteOrder_Hidden" , "HideSiteOrderDropdown_selectedValues", siteOrdersToBeAvailableList, "hideSiteOrder_removeButton");
			selectAndAddValueFromLeftDropdown(application, "SiteOrder_Available", "HideSiteOrder_Available" , siteOrdersToBeHiddenList , "hideSiteOrder_addbutton");
			verifySelectedValuesInsideRightDropdown(application, "Hiden Site Orders" , "HideSiteOrderDropdown_selectedValues");
			
			scrolltoend();
			Thread.sleep(1000);
			
		//Hide Router Tool IPv4 Commands(Cisco)
			selectAndRemoveValueFromRightDropdown(application, "Router Tool IPv4 Commands(Cisco)_Available", "hideRouterToolIpv4_Cisco_selectedvalues", routerToolIPv4CiscoTobeAvailableList, "hideRouterToolIPv4_Cisco_removeButton");
			selectAndAddValueFromLeftDropdown(application, "Router Tool IPv4 Commands(Cisco)_Hidden", "hideRouterToolIPv4_Cisco_Available", routerToolIPv4CiscoTobeHiddenList, "hideRouterToolIPv4_Cisco_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hiden Router Tool IPv4 Commands(Cisco)", "hideRouterToolIpv4_Cisco_selectedvalues");
			
			
		//Hide Router Tool IPv4 Commands(Huawei)
			selectAndRemoveValueFromRightDropdown(application, "Router Tool IPv4 Commands(Huawei)_Hidden", "hideRouterToolIpv4_Huawei_selectedvalues", routerToolIPv4HuaweiTobeAvailableList, "hideRouterToolIPv4_Huawei_removeButton");
			selectAndAddValueFromLeftDropdown(application, "Router Tool IPv4 Commands(Huawei)_Available" , "hideRouterToolIPv4_Huawei_available" , routerToolIPv4HuaweiTobeHiddenList, "hideRouterToolIPv4__Huawei_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hideen Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIpv4_Huawei_selectedvalues");
		
			
			scrolltoend();
			Thread.sleep(3000);

			click_commonMethod(application, "OK" , "OkButton_AddUser" , xml);
		
	}
	
	
	
	public void selectUserTOEdit(String application, String username) throws InterruptedException, DocumentException {

		WebElement customerName=getwebelement(xml.getlocator("//locators/"+application+"/legalcustomerName_labelName"));
		scrolltoview(customerName);
		Thread.sleep(1000);
		
		Log.info("user name is: "+username);
		WebElement Username=getwebelement(xml.getlocator("//locators/"+application+"/selectUser").replace("value", username));
		Clickon(Username);
		ExtentTestManager.getTest().log(LogStatus.PASS, Username +" is selected");
		Thread.sleep(1000);
		
		click_commonMethod(application, "User_Action", "Users_Action" , xml);
		
		click_commonMethod(application, "Edit_User", "EditUserLink" , xml);
	}
		
		public void verifyAddedUserValuesInViewUserPage(String application, String UserName, String FirstName, String SurName,String PostalAddress, 
				String Email_AddUser, String Phone_AddUser, String IPGuardianAccountGroup,String ColtOnlineUser, String RolesToBeSelected,
				String	HideRouterToolsIPv6CommandsCisco_ToBeSelected, String HideRouterToolsIPv4CommandsHuiwai_ToBeSelected,
				String	HideRouterToolsIPv4CommandsCisco_ToBeSelected, String HideServicesToBeSelected,String HideSiteOrderToBeSelected ) 
				throws InterruptedException, DocumentException {
				
				Thread.sleep(1000);

				WebElement customerName=getwebelement(xml.getlocator("//locators/"+application+"/legalcustomerName_labelName"));
				scrolltoview(customerName);
				Thread.sleep(1000);
				
				WebElement username=getwebelement(xml.getlocator("//locators/"+application+"/selectUser").replace("value", UserName));
				Clickon(username);
				ExtentTestManager.getTest().log(LogStatus.PASS, UserName +" is selected");
				Thread.sleep(1000);
				
				click_commonMethod(application, "User_Action", "Users_Action" , xml);
				Thread.sleep(1000);
				
				click_commonMethod(application, "View_User", "ViewuserLink" , xml);
				Thread.sleep(10000);
	
			//User Name
				compareTextForViewUserPage(application, "User Name", UserName, xml);
				
			//First Name
				compareTextForViewUserPage(application, "First Name" , FirstName, xml);
				
			//Sur Name
				compareTextForViewUserPage(application, "Surname", SurName, xml);
				
			//Postal Address
				compareTextForViewUserPage(application, "Postal Address", PostalAddress, xml);
				
			//Email
				compareTextForViewUserPage(application, "Email", Email_AddUser, xml);
				
			//Phone
				compareTextForViewUserPage(application, "Phone" , Phone_AddUser, xml);
				
			//IP Guardian Accouunt Group
				compareTextForViewUserPage(application, "IPGuardian Account Group" , IPGuardianAccountGroup, xml);
				
			//Colt Online User
				compareTextForViewUserPage(application, "Colt Online User"  , ColtOnlineUser, xml);
				
				WebElement userNameLabelName=getwebelement(xml.getlocator("//locators/"+application+"/viewUser_userNameLabelName"));
				scrolltoview(userNameLabelName);
				Thread.sleep(1000);
				
			//Roles
//				compareTextForViewUserPage(application, labelname, ExpectedText, xml);
		
			
		//Hidden Router Tools IPv4 (Cisco)
			List<WebElement> HRcisco = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolIPv4Cisco"));	
			
			for(WebElement listofHiddenCiscoValues : HRcisco) {
				Log.info("list of values in Hide router Tool Command IPv4(Cisco) are: "+listofHiddenCiscoValues.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Cisco) are: " + listofHiddenCiscoValues.getText());
			}
	
	scrolltoend();
	Thread.sleep(2000);
			
		//Hidden Router Tool IPv4 (Huawei)
			List<WebElement> Ipv4CommandHuawei = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv4Huawei"));	
			
			for(WebElement listofHuaweiValues : Ipv4CommandHuawei) {
				Log.info("list of values in Hide router Tool Command (Cisco) are: "+listofHuaweiValues.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Huawei) are: "+ listofHuaweiValues.getText());
			}	
			
		
		//Hidden Router Tools IPv6 (Cisco)
			List<WebElement> HiddenIPv6cisco = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv6Cisco"));	
			
			for(WebElement listofHiddenIPv6CiscoValues : HiddenIPv6cisco) {
				Log.info("list of values in Hide router Tool Command IPv6 (Cisco) are: "+listofHiddenIPv6CiscoValues.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv6 Commands(Cisco) are: " + listofHiddenIPv6CiscoValues.getText());
			}			
			
		
			scrolltoend();
			Thread.sleep(2000);
			
			click_commonMethod(application, "Back", "backbutton" , xml);
			Thread.sleep(2000);
			
		}
	
		
		public void deleteUser(String application, String username) throws InterruptedException, DocumentException {
	
			WebElement customerName=getwebelement(xml.getlocator("//locators/"+application+"/legalcustomerName_labelName"));
			scrolltoview(customerName);
			Thread.sleep(1000);
			
			Log.info("user name is: "+username);
			WebElement Username=getwebelement(xml.getlocator("//locators/"+application+"/selectUser").replace("value", username));
			Clickon(Username);
			ExtentTestManager.getTest().log(LogStatus.PASS, Username +" is selected");
			Thread.sleep(1000);
			
			click_commonMethod(application, "User_Action", "Users_Action" , xml);
			
			click_commonMethod(application, "Delete User Link", "DeleteUserLink", xml);
			
			
			Alert alert= driver.switchTo().alert ();
			alert.accept();
		}
	
		
	
	public void deleteService(String application) throws InterruptedException, DocumentException {
		
		 WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		 scrolltoview(orderPanel);
			Thread.sleep(3000);
		   
		   click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);   //click on Action dropodwn
		   
		   click_commonMethod(application, "Delete", "viewService_deletLink", xml);
		   Thread.sleep(1000);
		   
//		   boolean alertpopup=false;
//			alertpopup=getwebelement(xml.getlocator("//locators/" + application + "/alertPopupForviewLink_underOrderpanel")).isDisplayed();
//			if(alertpopup) {
//				
//				ExtentTestManager.getTest().log(LogStatus.PASS, "Alert popup displays as expected, when we click on 'delete' link ");
//				String alertmsg=getwebelement(xml.getlocator("//locators/" + application + "/alertmsgForviewlink_underOrderPanel")).getText();
//				
//				Log.info("Alert popup message displays as: "+alertmsg);
//				ExtentTestManager.getTest().log(LogStatus.PASS, "Alert popup message displays as: "+alertmsg);
//				
//				click_commonMethod(application, "Delete", "viweServicepage_deleteButton", xml);
//				Thread.sleep(2000);
//				
//			}else {
//				Log.info("Alert popup did not display");
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Alert popup is not displaying, when we click on 'delete' link ");
//			}
		   
		   
		   Alert alert = driver.switchTo().alert();		
    		
		     // Capturing alert message.    
		       String alertMessage= driver.switchTo().alert().getText();
		       if(alertMessage.isEmpty()) {
		    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No mEssage displays");
			       Log.info("No Message displays"); 
		       }else {
		    	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
			       Log.info("text message for alert displays as: "+alertMessage);
		       }
		     
		       alert.accept();
		       Thread.sleep(2000);
		       
		  
	}
	
	
	public void verifySuccessMessageFor_deleteService(String application) throws InterruptedException, DocumentException {
		
		
		waitforPagetobeenable();
		
		Thread.sleep(5000);
		scrollToTop();
		Thread.sleep(2000);
		
		boolean successMsg=false;
		successMsg=getwebelement(xml.getlocator("//locators/" + application + "/service_deleteSuccessMessage")).isDisplayed();
		if(successMsg) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "After deleting,  Success Message displays as expected ");
			Log.info("After deleting,  Success Message displays as expected ");
			
			successScreenshot(application);
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "After deleting,  Success Message is not displaying ");
			Log.info("After deleting,  Success Message is not displaying");
		}
		
	}
	
		 
	 public void deleteOrder(String application) throws InterruptedException, DocumentException {
		 
		 click_commonMethod(application, "Action", "actionbutton_orderDeletion", xml);
		 Thread.sleep(1000);
		 
		 click_commonMethod(application, "Delete", "viewService_deletLink", xml);
		 Thread.sleep(1000);
		 
		 
		 Alert alert = driver.switchTo().alert();		
 		
	     // Capturing alert message.    
	       String alertMessage= driver.switchTo().alert().getText();
	       if(alertMessage.isEmpty()) {
	    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No mEssage displays");
		       Log.info("No Message displays"); 
	       }else {
	    	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
		       Log.info("text message for alert displays as: "+alertMessage);
	       }
	     
	       alert.accept();
	       Thread.sleep(2000);
	       
	       verifysuccessmessage(application, "Order successfully deleted.");
	 }
	
	 
	 /**
		 * success Message common method
		 * @param application
		 * @throws InterruptedException
		 */
		public void verifysuccessmessage(String application, String expected) throws InterruptedException {
			
			
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(1000);
			try {	
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert")).isDisplayed();

				if(successMsg) {
					
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
					
					if(expected.contains(alrtmsg)) {
						
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
				Log.info("failure in fetching success message - 'Service created Successfully'  ");
				ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
				Log.info(expected+ " message is not getting dislpayed");
				Thread.sleep(2000);
			}

		}

		
		
		/**
		 * verify created customer values
		 * @param application
		 * @param Name
		 * @param MainDomain
		 * @param CountryToBeSelected
		 * @param TypeToBeSelected
		 * @param OCN
		 * @param Reference
		 * @param TechnicalContactName
		 * @param Email
		 * @param Phone
		 * @param Fax
		 * @param DedicatedPortalStatus
		 * @param DedicatedPortal
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void verifyCustomerdetails_InviewCustomerPage(String application, String Name, String MainDomain, String CountryToBeSelected,
				String TypeToBeSelected, String OCN,String Reference, String TechnicalContactName, String Email, String Phone,
				String Fax, String DedicatedPortalStatus, String DedicatedPortal) throws InterruptedException, DocumentException {
			
			compareText_InViewPage(application, "Legal Customer Name", Name, xml);
			
			if(MainDomain.equalsIgnoreCase("null")) {
				
			}else{
				compareText_InViewPage(application, "Main Domain", MainDomain, xml);	
			}
			
			compareText_InViewPage(application, "Country" , CountryToBeSelected, xml);
			
			compareText_InViewPage(application, "Type" , TypeToBeSelected, xml);
			
			compareText_InViewPage(application, "OCN" , OCN , xml);
			
			compareText_InViewPage(application, "Reference" , Reference, xml);
			
			compareText_InViewPage(application, "Technical Contact Name" , TechnicalContactName, xml);
			
			compareText_InViewPage(application, "Email" , Email, xml);
			
			compareText_InViewPage(application, "Phone" , Phone, xml);
			
			compareText_InViewPage(application, "Fax" , Fax, xml);
			
		}
		
		/**
		 * verify Edited Customer Values
		 * @param application
		 * @param Name
		 * @param MainDomain
		 * @param CountryToBeSelected
		 * @param TypeToBeSelected
		 * @param OCN
		 * @param Reference
		 * @param TechnicalContactName
		 * @param Email
		 * @param Phone
		 * @param Fax
		 * @param DedicatedPortalStatus
		 * @param DedicatedPortal
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void verifyCustomerdetails_InviewCustomerPage_edited(String application, String Name, String MainDomain, String CountryToBeSelected,
				String TypeToBeSelected, String OCN,String Reference, String TechnicalContactName, String Email, String Phone,
				String Fax, String DedicatedPortalStatus, String DedicatedPortal) throws InterruptedException, DocumentException {
			
		//Name	
			if(Name.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Customer Name is not edited");
			}else {
				compareText_InViewPage(application, "Legal Customer Name", Name, xml);
			}
			
		//Main Domain	
			if(MainDomain.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Main Domain' field is not edited");
			}else {
				compareText_InViewPage(application, "Main Domain", MainDomain, xml);
			}	
			
		//Country	
			if(CountryToBeSelected.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Country' field is not edited");
			}else {
				compareText_InViewPage(application, "Country" , CountryToBeSelected, xml);
			}
			
		//Type
			if(TypeToBeSelected.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Type' field is not edited");
			}else {
				compareText_InViewPage(application, "Type" , TypeToBeSelected, xml);
			}
			
			
		//OCN	
			if(OCN.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'OCN' field is not edited");
			}else {
				compareText_InViewPage(application, "OCN" , OCN , xml);
			}
			
			
		//Reference	
			if(Reference.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Reference' field is not edited");
			}else {
				compareText_InViewPage(application, "Reference" , Reference, xml);
			}
			
			
		//Technical Contact Name	
			if(TechnicalContactName.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Technical Contact Name' field is not edited");
			}else {
				compareText_InViewPage(application, "Technical Contact Name" , TechnicalContactName, xml);
			}	
			
			
		//Email	
			if (Email.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Email' field is not edited");
			} else {
				compareText_InViewPage(application, "Email" , Email, xml);
			}
				
			// Phone
				if (Phone.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Phone' field is not edited");
				} else {
					compareText_InViewPage(application, "Phone" , Phone, xml);
				}
		
			//OCN
				if (Fax.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Fax' field is not edited");
				} else {
					compareText_InViewPage(application, "Fax" , Fax, xml);
				}
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
                        	  ExtentTestManager.getTest().log(LogStatus.PASS, elements.get(j) + " got selected" );
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

		
		
		
	public void selectAndRemoveValueFromRightDropdown(String application, String labelname, String xpath, String[] selectValue, String xpathForRemoveButton) {
			
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
                	 Thread.sleep(2000);
                     for(int j=0; j<ls.size() ; j++) {
                	  Log.info("ls value "+ ls.get(j));
                        if(selectValue[i].equals(ls.get(j)))
                        {
                        	  elements.get(j).click();
                        	  ExtentTestManager.getTest().log(LogStatus.PASS, elements.get(j) + " got selected" );
                              Thread.sleep(1000);
                              WebElement removeButton=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathForRemoveButton +"").replace("value", "<<"));
                              Clickon(removeButton);
                              ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on remove '<<' button");
                              Thread.sleep(3000);
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
	public void compareTextForViewUserPage(String application, String labelname,  String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

		String text = null;
		WebElement element = null;

		try {
			Thread.sleep(1000);
			
			element = getwebelement(xml.getlocator("//locators/"+application+"/viewUser_fetchValuesForAddedUser").replace("value", labelname));
			String emptyele = element.getText().toString();

			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
				Log.info(labelname+" not found");
			}
			
			else if(emptyele!=null && emptyele.isEmpty()) {
//				ExtentTestManager.getTest().log(LogStatus.PASS,  labelname + "' value is empty");
				
				emptyele= "Null";
				
				sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
				
				if(emptyele.equalsIgnoreCase(ExpectedText)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					Log.info(" The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
				

			}else 
			{   
				text = element.getText();
				if(text.equals(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				}
				else if(text.contains(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			Log.info(labelname + " field is not displaying");
		}

	}
	
	
	/////////////////////////////////////////////////// updated Supply order//////////////////////////////////////////////////
	
	
	public void createexistingorderservice(String application, String existingorder, String orderno, String rfireqno)
			throws InterruptedException, IOException, DocumentException {
	
waitforPagetobeenable();
		scrolltoend();
	
		Thread.sleep(1000);

		click_commonMethod(application, "Order_Service_Action", "Orders_Services_Action", xml);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddOrder_Link")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Add Order' link under Order/Services pannel");
		Log.info("Clicked on 'Add Order' link under Orders/Services pannel");

		waitforPagetobeenable();
		
		if (existingorder.equalsIgnoreCase("YES")) {
			scrolltoend();
			addDropdownValues_commonMethod(application, "'Order/Contract Number(Parent SID)" , "existingorderdropdown" , orderno, xml);
			
			addtextFields_commonMethod(application, "RFI / RFQ /IP Voice Line number" , "rfireqiptextfield", rfireqno, xml);

		} else {

			Log.info("Existing order is not selected");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Existing order is not selected");
			Log.info(" Existing order is not selected ");
		}
	}
	

	public void createneworderservice(String application, String neworder, String neworderno, String newrfireqno)
			throws InterruptedException, IOException, DocumentException {

		
		if (neworder.equalsIgnoreCase("YES")) {
			scrolltoend();
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectorderswitch")));
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked to 'Select Order Switch' toggle button for creating 'new order'");
			Log.info(" Clicked to create new order ");

			addtextFields_commonMethod(application, "Order/Contract Number(Parent SID)", "newordertextfield", neworderno, xml);

			addtextFields_commonMethod(application, "RFI / RFQ /IP Voice Line number", "newrfireqtextfield", newrfireqno, xml);

			click_commonMethod(application, "Create Order", "createorderbutton", xml);

		} else {
			Log.info("New order not selected");
			ExtentTestManager.getTest().log(LogStatus.PASS, "New order is not selected");
			Log.info("New order is not selected");
		}
	}
	
	
	public void verifyselectservicetype(String application, String servicetye, String subtype)
			throws InterruptedException, IOException, DocumentException {

		try {
			scrolltoend();
			Thread.sleep(1000);

			// select service type
			addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield" , servicetye, xml);
			
			// select subtype
			addDropdownValues_commonMethod(application, "Network Configuration", "networkconfigurationinputfield" , subtype, xml);

			// click on next button
			click_commonMethod(application, "Next", "nextbutton", xml);

		} catch (StaleElementReferenceException e) {

			List<WebElement> servicetypes = getwebelements("//div[label[text()='Service Type']]//div[@role='list']//span[@role='option']");
			List<WebElement> servicesubtypes = getwebelements("//div[@role='list']//span[@role='option']");

		}
	}
	
	
	public void Verify_createorderservice(String application, String serviceidenfication, String belongstobundle,
			String bundleselectiontype, String billingtype, String billingselection, String terminationdate,
			String email, String phone, String remarks) throws InterruptedException, DocumentException, IOException {

		try {
			
			
			waitforPagetobeenable();
			
			scrolltoend();
			Thread.sleep(1000);
			
			click_commonMethod(application, "Next", "nextbutton", xml);
			
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(1000);
			
		//check warning messages	
			warningMessage_commonMethod(application, "serviceidentificationerror" , "Service Identification", xml);

			warningMessage_commonMethod(application, "billingtypeerror" , "Biling Type", xml);

			addtextFields_commonMethod(application, "Service Id", "serviceidentificationtextfield", serviceidenfication, xml);	//Service Identification Text Field

		//displayed service type	
			String displayedServiceTypevalue = getwebelement(
					xml.getlocator("//locators/" + application + "/servicetypevalue")).getText();
			Log.info("Displayed service type is : " + displayedServiceTypevalue);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Displayed service type is : " + displayedServiceTypevalue);
			Log.info("Displayed service type is : " + displayedServiceTypevalue);
			
			
		//displayed network configuration
			String displayednetworkconfiguration = getwebelement(
					xml.getlocator("//locators/" + application + "/networkconfigurationvalue")).getText();
			Log.info("Displayed network configuration is : " + displayednetworkconfiguration);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Displayed network configuration is : " + displayednetworkconfiguration);
			Log.info("Displayed network configuration is : " + displayednetworkconfiguration);

			
		// billingtype
//			addDropdownValues_commonMethod(application, "Billing Type", "billingtypeinputfield" , billingselection, xml);
			selectValueInsideDropdown(application, "billingType_selectTag", "Billing Type", billingselection, xml);
			
		//Email
			addtextFields_commonMethod(application, "Email", "emailtextfield", email, xml);

		// phone contact
			addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfield", phone, xml);

		//remarks
			addtextFields_commonMethod(application, "Remarks", "remarktextfield", remarks, xml);

		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void Verify_Selectmanagementoptions(String application, String ivp4selectmode, String ipv6selectmode,
			String preportselectmode, String ipguardainselectmode, String deliverychannel, String manageserviceselectmode,
			String snmpselectmode, String Traptargetadress)
			throws InterruptedException, DocumentException, IOException {

		//Managed Service
			addCheckbox_commonMethod(application, "managedservicecheckbox", "Managed Service", manageserviceselectmode, "no", xml);
			
		//IP Guardian
			addCheckbox_commonMethod(application, "ipguardianchecbox", "IP Guardian", ipguardainselectmode, "no", xml);
			
		//Router Configuration View IPv4
			addCheckbox_commonMethod(application, "routerconfigurationviewipv4checkbox", "Router Configuration View IPv4", ivp4selectmode, "no", xml);
			
		//Router Configuration View IPv6
			addCheckbox_commonMethod(application, "routerconfigurationviewipv6checkbox", "Router Configuration View IPv6", ipv6selectmode, "no", xml);
		
		// delivery channel
			selectValueInsideDropdown(application, "deliveryChannel_selectTag" , "Delivery Channel", deliverychannel, xml);
			
		//SNMP Notification	
			addCheckbox_commonMethod(application, "snmpNotificationcheckbox", "SNMP Notification", snmpselectmode, "no", xml);
				
		//Trap target Address
		if(snmpselectmode.equalsIgnoreCase("YES")) {
			
		   addtextFields_commonMethod(application, "Trap Target Address", "traptargetAddress", Traptargetadress, xml);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Trap target Address' text field is not available as 'SNMP notification' is not selected");
			Thread.sleep(5000);
		}

	}
	
	
	public void verifydisplaystateandselectmanagementoptions(WebElement ele, String selectmode)
			throws InterruptedException {

		String elementname;
		boolean flag = ele.isDisplayed();

		if (flag == true && selectmode.equalsIgnoreCase("YES")) {

			elementname = ele.getAttribute("name");

			ele.click();

			Thread.sleep(3000);

			Log.info(elementname + "is displayed" + "and" + "selected in Management Options");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"" + elementname + "is displayed" + "and" + "selected in Management Options");
		} else {
			elementname = ele.getAttribute("name");
			Log.info(elementname + " not selected in Management Options");
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"" + elementname + " not selected in Management Options");
		}

	}
	
	
	public void Verify_ConfigurationOptions(String application, String firewall, String Qos)
			throws InterruptedException, DocumentException, IOException {

			WebElement routerBasedfirewall, qos;
			
			scrolltoend();
			Thread.sleep(2000);
				
			
			//Router Based Firewall
				addCheckbox_commonMethod(application, "routerbasedfirewalcheckbox" , "Router Based Firewall", firewall, "no", xml);
				
			//Qos
				addCheckbox_commonMethod(application, "Qoscheckbox" , "Qos", Qos, "no", xml);
			
				scrolltoend();
				Thread.sleep(2000);
				
			// click on next
				click_commonMethod(application, "Next", "nextbutton" , xml);
				waitforPagetobeenable();
			Thread.sleep(1000);

	}
	
	public void clickOnSearchCustomerLink(String application) throws InterruptedException, DocumentException {
		
		Moveon(getwebelement(xml.getlocator("//locators/"+application+"/ManageCustomerServiceLink")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Mouse hovered on 'MANAGE CUSTOMER'S SERVICE'");
		Log.info("Mouse hovered on 'MANAGE CUSTOMER'S SERVICE'");
		Thread.sleep(1000);
		
		click_commonMethod(application, "Search Customer Link", "SearchCustomerLink", xml);
	}
	
	
	public void searchCustomerAndperformSupply(String application, String SearchName, String SearchOCN,
			String SupplyServiceToCustomerName,String OrderNumber)throws Exception {
	
			try {
				
			//Name Field	
				addtextFields_commonMethod(application, "Name", "Name", SearchName, xml);
				
			//Search Button	
				click_commonMethod(application, "Search", "Search_Button", xml);
				
				scrolltoend();
					
		//select the Customer		
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SearchResultRowsRadioButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, SearchName + " is selected under 'Search Customer Result");
			Log.info(SearchName + " is selected under 'Search Customer Result");
			
			
			click_commonMethod(application, "Action", "SearchCustomerResult_Actionmenu", xml);		//action button
		
			click_commonMethod(application, "View_Link", "View_Link", xml);		//click on view link
			
			waitforPagetobeenable();
			scrolltoend();
			Thread.sleep(1000);	
			
		//Select an order to perform Supply
			WebElement valueUnderOrderPanel=getwebelement(xml.getlocator("//locators/" + application + "/selectRowUnderOrderPanel").replace("value", OrderNumber));
			safeJavaScriptClick(valueUnderOrderPanel);
			ExtentTestManager.getTest().log(LogStatus.PASS, "selected the Order_"+OrderNumber);
			Log.info(" Clicked on Radio Button in Order/Service Panel ");
			
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Order_Service_Action")));
			Thread.sleep(1000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Action link under Order Service panel " );
			Log.info(" Clicked on Action link under Order Service pannel ");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Supply_Link")));
			Thread.sleep(1000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Supply Link");
			Log.info(" Clicked on Supply Link ");	
			
		//Supply _Name Field	
			addtextFields_commonMethod(application, "Name", "SupplyServicetoCustomer_Name", SupplyServiceToCustomerName, xml);
			Thread.sleep(4000);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/SupplyServicetoCustomer_Name")), "*" );
			Thread.sleep(1000);
			
		//Select Choose a customer dropdown
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SupplyServicetoCustomer_ChooseACustomer")));
			Thread.sleep(2000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Choose a customer' dropdown");
			Log.info("Clicked on 'Choose a customer' dropdown");
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/SupplyServicetoCustomer_ChooseACustomer")),
					SupplyServiceToCustomerName);
			
			Thread.sleep(2000);
			WebElement ChooseACustomer=getwebelement(xml.getlocator("//locators/" + application + "/selectvalueUnderChooseAcustomerDropdown").replace("value", SupplyServiceToCustomerName));
			
			boolean chooseCustomerdropodnVaue=false;
		try {	
			chooseCustomerdropodnVaue=ChooseACustomer.isDisplayed();
			if(chooseCustomerdropodnVaue) {
				
				Clickon(ChooseACustomer);
//				String ChooseACustomerText = ChooseACustomer.getText().toString();
//				Thread.sleep(2000);
//				ChooseACustomer.click();
//				Thread.sleep(4000);
				ExtentTestManager.getTest().log(LogStatus.PASS, SupplyServiceToCustomerName + " is selected under 'Choose A Customer' dropdown");
				Log.info("Selected Choose A Customer  is  : " + SupplyServiceToCustomerName);
				
			}else {
			//clearing the name text field value and entering value again	
				edittextFields_commonMethod(application, "Name", "SupplyServicetoCustomer_Name", SupplyServiceToCustomerName, xml);
				
			//select value under 'Choose A Customer' dropdown	
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SupplyServicetoCustomer_ChooseACustomer")));
				Thread.sleep(2000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'Choose A Customer' dropdown");
				
				Clickon(getwebelement("//div[div[@class='modal-body']]//div[text()='×']"));
				Thread.sleep(2000);
				
				Clickon(getwebelement("//div[text()='" + SupplyServiceToCustomerName + "']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, SupplyServiceToCustomerName + " is selected under 'Chooose A Customer' dropdown");
				Thread.sleep(3000);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
			ExtentTestManager.getTest().log(LogStatus.PASS, SupplyServiceToCustomerName + " value is not displaying under 'Chooose A Customer' dropdown");
			Log.info(SupplyServiceToCustomerName + " value is not displaying under 'Chooose A Customer' dropdown");
//			//clearing the name text field value and entering value again	
//			edittextFields_commonMethod(application, "Name", "SupplyServicetoCustomer_Name", SupplyServiceToCustomerName, xml);
////			getwebelement(xml.getlocator("//locators/" + application + "/SupplyServicetoCustomer_Name")).clear();
////			Thread.sleep(2000);
////			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/SupplyServicetoCustomer_Name")),SupplyServiceToCustomerName);
//			
//		//select value under dropdown	
//			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SupplyServicetoCustomer_ChooseACustomer")));
//			Thread.sleep(2000);
//			
//			Clickon(getwebelement("//div[div[@class='modal-body']]//div[text()='×']"));
//			Thread.sleep(2000);
//			
//			Clickon(getwebelement("//div[text()='" + SupplyServiceToCustomerName + "']"));
//			Thread.sleep(3000);
			
		}
			
			
		click_commonMethod(application, "OK", "OK_Button", xml); 		//click on OK button
				
		}
		catch (NullPointerException e) {
		e.printStackTrace();
		Log.info("Expected Value is not displayed in Search Customer Result Grid ");
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Expected Value is not displayed in Search Customer Result Grid :  " + e);
	}
		catch (NoSuchElementException e) {
		e.printStackTrace();
		Log.info("Expected Value is not displayed in Search Customer Result Grid");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Expected Value is not displayed in Search Customer Result Grid :  " + e);
	
		}
		sa.assertAll();
		
	}	 

		
		public void verifySuppliesPanelInformation(String application, String SupplyServiceToCustomerName, String NewOrderNumber,
				String ServiceIdentification, String ServiceType, String Status, String SyncStatus )	
				throws InterruptedException, DocumentException, IOException {

			scrolltoend();
			Thread.sleep(1000);
				// Verify Supplies pannel information
			boolean suppliesPanel=false;
			try {
				
				suppliesPanel=getwebelement(xml.getlocator("//locators/"+application+"/SupplyHeadertab")).isDisplayed();
				if(suppliesPanel) {
					
					Log.info("Supplies table is displaying as expected");
					ExtentTestManager.getTest().log(LogStatus.INFO, "'Supplies' panel is displaying. Verifying values under 'Supplies' panel");
					
					
					//Customer Name
						compareText(application, "Customer", "suppliesPanel_customerName", SupplyServiceToCustomerName, xml);
					
					//Order Column
						compareText(application, "Order", "suppliesPanel_orderNumber", NewOrderNumber, xml);

					//Service Id Column
						compareText(application, "Service", "suppliesPanel_ServiceId", ServiceIdentification, xml);
						
					//Service Type Column
						compareText(application, "Service Type", "suppliesPanel_serviceType", ServiceType, xml);
						
					//Status Column
						compareText(application, "Status", "suppliesPanel_statusColmn", Status, xml);
						
					//Sync Status
						compareText(application, "Sync Status", "suppliesPanel_syncStatus", SyncStatus, xml);
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Supplies table is not displaying");
					Log.info("Supplies table is not displaying");
				}
				
				
			}catch(Exception rr) {
				rr.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Supplies table is not displaying");
				Log.info("Supplies table is not displaying");
			}
		}	

	

		public void verifySearchCustomerFunctionality(String application, String SearchSubscribedCustomer, String SearchOCN)
				throws InterruptedException, DocumentException, IOException {

			//Customer Name Field	
				addtextFields_commonMethod(application, "Name", "Name", SearchSubscribedCustomer, xml);
				
			//Search Button	
				click_commonMethod(application, "Search", "Search_Button", xml);

				scrolltoend();
					
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SearchResultRowsRadioButton")));
			Thread.sleep(2000);
			ExtentTestManager.getTest().log(LogStatus.PASS, SearchSubscribedCustomer + " is selected under 'Search Customer Result");
			Log.info(SearchSubscribedCustomer + " is selected under 'Search Customer Result");
			
			
			
			click_commonMethod(application, "Action", "SearchCustomerResult_Actionmenu", xml);	//click on Action dropdown
			
			click_commonMethod(application, "view_Link", "View_Link", xml);		//click on view link
			Thread.sleep(2000);
			scrolltoend();
			Thread.sleep(2000);	
					
	}

		public void verifySubscribedCustomers(String application, String SupplyServiceToCustomerName, String NewOrderNumber,
				String ServiceIdentification, String ServiceType, String Status, String SyncStatus) throws InterruptedException, DocumentException, IOException {

			boolean subscriberTable=false;
			
			
			waitforPagetobeenable();
			
			scrolltoend();
		try {	
			subscriberTable=getwebelement(xml.getlocator("//locators/"+application+"/SubscriberPanel")).isDisplayed();
			
			if(subscriberTable) {
				Log.info("'Subscriber' Table is displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.INFO, "'Subscribes' Panel is displaying. verifying  value under 'Subscribes' table");
				
				
				//Customer column
				compareText(application, "Customer", "subscribesPanel_CustomerColumnValue", SupplyServiceToCustomerName, xml);
				
				//Order Column
				compareText(application, "Order", "subscribesPanel_orderClumnValue", NewOrderNumber, xml);
				
				//Service Id Column
				compareText(application, "Service", "subscribesPanel_serviceIdColumnValue", ServiceIdentification, xml);
				
				//Service Type Column
				compareText(application, "Service Type", "subscribesPanel_ServiceTypeColumnValue", ServiceType, xml);
				
				//Status Column
				compareText(application, "Status", "subscribesPanel_StatusColumnValue", Status, xml);
				
				//Sync Status Column
				compareText(application, "Sync Status", "subscribesPanel_syncStatusColumnValue", SyncStatus, xml);
				
				
				
			//select Customer and navigate to view service page	
				selectRowForSubscriber_toViewService(application, SupplyServiceToCustomerName);
				
			//verify whether it got navigated to View Service page
				boolean viewServicePage=false;
			try {
				
				waitforPagetobeenable();
				
				scrollToTop();
				viewServicePage=getwebelement(xml.getlocator("//locators/"+application+"/viewServicePage_customerDetailsPanel")).isDisplayed();
				if(viewServicePage) {
					Log.info("got navigated to view Service page as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Subscribe' panel after selecting the customer,if we click on 'view' link it gets navigated to 'view Service' page");
				}else {
					Log.info("not getting navigated to 'view Service' page");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Under 'Subscribe' panel after selecting the customer,if we click on 'view' link it is not getting navigated to 'view Serice' page");
				}
			}catch(Exception er) {
				er.printStackTrace();
				Log.info("not getting navigated to 'view Service' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Under 'Subscribe' panel after selecting the customer,if we click on 'view' link it is not getting navigated to 'view Serice' page");

			}
		
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Subscribes' Table is not displaying");
				Log.info(" 'Subscribes' Table is not displaying");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Subscribes' Table is not displaying");
			Log.info(" 'Subscriber' Table is not displaying");
		}
			
		}


		
		public void selectRowForSubscriber_toViewService(String application,String customerName) throws InterruptedException, DocumentException, IOException {

				int TotalPages;

				String TextKeyword = getwebelement(xml.getlocator("//locators/"+application+"/subscribePanel_totalPage")).getText();

				TotalPages = Integer.parseInt(TextKeyword);

				Log.info("Total number of pages in table is: " + TotalPages);

				ab:
				if (TotalPages != 0) {

					for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = getwebelement(xml.getlocator("//locators/"+application+"/subscribePanel_currentPage")).getText();
					int Current_page = Integer.parseInt(CurrentPage);
					Log.info("The current page is: " + Current_page);

					assertEquals(k, Current_page);

					Log.info("Currently we are in page number: " + Current_page);

					List<WebElement> results = getwebelements("(//div[div[text()='Subscribes']]/following-sibling::div//div[text()='"+ customerName +"'])[1]");
					
						
					int numofrows = results.size();
					Log.info("no of results: " + numofrows);
					boolean resultflag;

				
					if ((numofrows == 0)) {
						
						Clickon(getwebelement(xml.getlocator("//locators/"+application+"/subscribePanel_nextPage")));
						Thread.sleep(3000);

					}

					else {
						for (int i = 0; i < numofrows; i++) {
							try {
								resultflag = results.get(i).isDisplayed();
								Log.info("status of result: " + resultflag);
								if (resultflag) {
									Log.info(results.get(i).getText());
									results.get(i).click();
									ExtentTestManager.getTest().log(LogStatus.PASS, customerName + " is selected under 'Subscribes' panel ");
									Thread.sleep(8000);
									Clickon(getwebelement(xml.getlocator("//locators/"+application+"/subscribePanel_ActionDropdown")));
									Thread.sleep(2000);
									Clickon(getwebelement(xml.getlocator("//locators/"+application+"/subscribePanel_viewlink")));
									Thread.sleep(3000);
									
									
								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								 e.printStackTrace();
							}

						}
					}
				}
				}else {
					Log.info("No values available in table");
					Log.info("No values available inside the 'Subscribes' table");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No value available inside 'Subscribes' panel");
				}
			}



	public void deletSupplyFunctionalityAndSelectOrderInsideTable(String application, String SearchName, String SearchOCN, String SupplyServiceToCustomerName,
			String OrderNumber)throws InterruptedException, DocumentException, IOException {


			 addtextFields_commonMethod(application, "Name", "Name", SearchName, xml);	//Enter Customer name
					
			 click_commonMethod(application, "Search", "Search_Button", xml);	//click on Search button
			 Thread.sleep(1000);
					
			scrolltoend();
						
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SearchResultRowsRadioButton")));
			Thread.sleep(2000);
			ExtentTestManager.getTest().log(LogStatus.PASS, SearchName + " is selected under 'Search Customer Result");
			Log.info(SearchName + " is selected under 'Search Customer Result");

			click_commonMethod(application, "Action", "SearchCustomerResult_Actionmenu", xml); // click on Action dropdown
	
			click_commonMethod(application, "view_Link", "View_Link", xml); // click on view link
	
			waitforPagetobeenable();
			scrolltoend();
			Thread.sleep(2000);	
			
			
		//Remove Supply
			WebElement selectValueUnderSupliesTable=getwebelement(xml.getlocator("//locators/" + application + "/selectRowUnderSupplyPanel").replace("value", SupplyServiceToCustomerName));
			Clickon(selectValueUnderSupliesTable);
			Thread.sleep(1000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected "+ SupplyServiceToCustomerName + " value under 'Supplies' table");
			Log.info("Selected "+ SupplyServiceToCustomerName + " value under 'Supplies' table");
				
			click_commonMethod(application, "Action", "suppliesTable_ActionDropdown", xml);
				
			click_commonMethod(application, "Remove_Link", "suppliesTable_removeLink", xml);
			
			waitforPagetobeenable();
			
			
			scrollToTop();
			verifysuccessmessage(application, "Supplied service successfully removed");
			
			
		//Click on View without selecting value under Order Panel
			scrolltoend();
				click_commonMethod(application, "Action", "Order_Service_Action", xml);		//click on Action dropdown in view Customer Page
			
			try {	
				click_commonMethod(application, "View_Link", "order_viewLink", xml);		//click on view link
				
				boolean alertpopup=false;
				alertpopup=getwebelement(xml.getlocator("//locators/" + application + "/alertPopupForviewLink_underOrderpanel")).isDisplayed();
				if(alertpopup) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "Alert popup displays as expected, when we click on 'view' without selecting any Order ");
					String alertmsg=getwebelement(xml.getlocator("//locators/" + application + "/alertmsgForviewlink_underOrderPanel")).getText();
					
					Log.info("Alert popup message displays as: "+alertmsg);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Alert popup message displays as: "+alertmsg);
					
					click_commonMethod(application, "x", "xbuttonForviewlink_underOrderPanel", xml);
					Thread.sleep(2000);
					
				}else {
					Log.info("Alert popup did not display");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Alert popup is not displaying, when we click on 'view' without selecting any Order ");
				}
			}catch(Exception e) {
				e.printStackTrace();
				Log.info("Alert popup did not display");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Alert popup is not displaying, when we click on 'view' without selecting any Order ");
			}
				
				
			
		//Select an Order Number and click on View Link
			WebElement selectValueUnderOrderTable=getwebelement(xml.getlocator("//locators/" + application + "/selectRowUnderOrderPanel").replace("value", OrderNumber));
			Clickon(selectValueUnderOrderTable);
			Thread.sleep(1000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected "+ OrderNumber + " value under 'Order' table");
			Log.info("Selected "+ OrderNumber + " value under 'Order' table");
				
			click_commonMethod(application, "Action", "Order_Service_Action", xml);
				
			click_commonMethod(application, "View_Link", "order_viewLink", xml);
				
		}
	
	
	
	public void deletOrderFunctionality_selectTheOrderInsideTable(String application, String SearchName, String SearchOCN, String SupplyServiceToCustomerName,
			String OrderNumber)throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(1000);
		
	//Select an Order Number and click on View Link
		WebElement selectValueUnderOrderTable=getwebelement(xml.getlocator("//locators/" + application + "/selectRowUnderOrderPanel").replace("value", OrderNumber));
		Clickon(selectValueUnderOrderTable);
		Thread.sleep(1000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Selected "+ OrderNumber + " value under 'Order' table");
		Log.info("Selected "+ OrderNumber + " value under 'Order' table");
			
		click_commonMethod(application, "Action", "Order_Service_Action", xml);
			
		click_commonMethod(application, "View_Link", "order_viewLink", xml);
			
		}
	



	
		
}