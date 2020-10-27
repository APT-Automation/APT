package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.scripthelpers.APT_LoginHelper;

public class APT_NGINTest extends DriverTestcase{
	
public String CustomerName=null;
	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_NGIN", priority=0)
	public void NGINService(Map<String, String> map) throws Exception {
		
		setup();	
		Login.APT_Login_1(map.get("url for the product"));
		
        String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
        
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
              
              logger= ExtentTestManager.startTest ("CreateCustomer - NGIN");
              APT_NGIN.get().createcustomer("nginservice", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              ExtentTestManager.endTest();
              
              logger= ExtentTestManager.startTest ("selectNewCustomer - NGIN"); 
              APT_NGIN.get().selectCustomertocreateOrder("nginservice",map.get("newCustomer"));
              ExtentTestManager.endTest();
        }
        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              logger= ExtentTestManager.startTest ("selectExistingCustomer - NGIN"); 
              APT_NGIN.get().selectCustomertocreateOrder("nginservice",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
              ExtentTestManager.endTest();
        }
    		
		logger= ExtentTestManager.startTest ("verifyneworder");
		APT_NGIN.get().createorderservice("nginservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyservicetypeselection");
		APT_NGIN.get().verifyselectservicetype("nginservice", map.get("ServiceType"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyservicecreation");
		APT_NGIN.get().verifyservicecreation("nginservice", map.get("ServiceIdentification"), map.get("Remarks"), map.get("Customer Administration"), map.get("SAN Administration"), map.get("Reseller Administration"),  map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyCustomerDetailsInformation");
		APT_NGIN.get().verifyCustomerDetailsInformation("nginservice", map.get("newCustomerCreation"), map.get("existingCustomerSelection"),
				map.get("newCustomer"),	map.get("existingCustomer"),
				map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"), 
				map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		APT_NGIN.get().verifyUserDetailsInformation("nginservice", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
		ExtentTestManager.endTest();
		
//		logger= ExtentTestManager.startTest ("verifyUserDetailsInformation");
//		APT_NGIN.get().VerifyUsersPanel("nginservice", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//				map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//				map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"), map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), 
//				map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"), map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), 
//				map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), map.get("SiteOrders_ToBeAvailable"), map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
//		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyorderpanelinviewservicepage");
		APT_NGIN.get().verifyorderpanel_editorder("nginservice", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
		APT_NGIN.get().verifyorderpanel_changeorder("nginservice", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
				map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyServicepanelinviewservicepage");
		APT_NGIN.get().verifyservicepanelInformationinviewservicepage("nginservice", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("Remarks"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyManagementOptionspanel");
		APT_NGIN.get().verifyManagementOptionspanel("nginservice", map.get("Customer Administration"), map.get("SAN Administration"), map.get("Reseller Administration"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("ServicepanelLinks");
		APT_NGIN.get().verifyEditService("nginservice", map.get("EditRemarks"), map.get("Remarks"), map.get("ServiceIdentification"), map.get("ServiceType"));
		APT_NGIN.get().verifyManageService("nginservice", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		APT_NGIN.get().verifySynchronizeLink("nginservice");
		if(map.get("BulkInterface").equalsIgnoreCase("Yes"))
		{
		APT_NGIN.get().verifyBulkInterface("nginservice", map.get("BulkJob_FilePath"), map.get("OCN"));
		}
		ExtentTestManager.endTest();
	
		if(!(map.get("Reseller Administration")).equalsIgnoreCase("No")) {
		logger= ExtentTestManager.startTest ("verifyResellerpanelinviewservicepage");
		APT_NGIN.get().verifyResellerpanel("nginservice");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyAddReseller");
		APT_NGIN.get().AddReseller("nginservice", map.get("OCN"), map.get("Reseller_Email"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"), map.get("Reseller_Phone"), map.get("Reseller_Fax"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyResellerLinks");
		APT_NGIN.get().verify_ViewReseller("nginservice", map.get("OCN"), map.get("Reseller_Email"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"), map.get("Reseller_Phone"), map.get("Reseller_Fax"));
		APT_NGIN.get().verify_EditReseller("nginservice", map.get("OCN"), map.get("Reseller_EditEmail"), map.get("Reseller_EditCity"), map.get("Reseller_EditStreetName"), map.get("Reseller_EditStreetNumber"), map.get("Reseller_EditPOBox"), map.get("Reseller_EditZipcode"), map.get("Reseller_EditPhone"), map.get("Reseller_EditFax"));
		ExtentTestManager.endTest();
		}
		
		if(!(map.get("Customer Administration")).equalsIgnoreCase("No")) {
		logger= ExtentTestManager.startTest ("verifyCustomerpanelinviewservicepage");
		APT_NGIN.get().verifyCustomerpanel("nginservice");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyAddCustomer");
		APT_NGIN.get().AddCustomer("nginservice", map.get("ResellerName"), map.get("DefaultValue_checkbox"), map.get("Configure_checkbox"), map.get("Customer_Country"), map.get("Email"), map.get("Phone"), map.get("Fax"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyCustomerLinksinviewservicepage");
		APT_NGIN.get().verify_ViewCustomer("nginservice", map.get("ResellerName"), map.get("DefaultValue_checkbox"), map.get("Configure_checkbox"), map.get("Customer_Country"), map.get("Email"), map.get("Phone"), map.get("Fax"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"));
		APT_NGIN.get().verify_EditCustomer("nginservice", map.get("Reseller_EditResellerName"), map.get("Reseller_EditEmail"), map.get("Reseller_EditCity"), map.get("Reseller_EditStreetName"), map.get("Reseller_EditStreetNumber"), map.get("Reseller_EditPOBox"), map.get("Reseller_EditZipcode"), map.get("Reseller_EditPhone"), map.get("Reseller_EditFax"));
		ExtentTestManager.endTest();
		}
		
		if(!(map.get("SAN Administration")).equalsIgnoreCase("No")) {
		logger= ExtentTestManager.startTest ("verifySANpanelinviewservicepage");
		APT_NGIN.get().verifySANpanel("nginservice");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyAddSAN");
		APT_NGIN.get().AddSAN("nginservice", map.get("Customer_Country"), map.get("AddSAN_SANNumber"), map.get("PreDestinationNumber"), map.get("Ringtonumber"), map.get("Routingforpayphone_value"), map.get("Routingformobile_value"), map.get("Defaultrouting_value"), map.get("RingToNumber_Checkbox"), map.get("AnnouncementToPlay_Checkbox"), map.get("ComplexRouting_Checkbox"), map.get("defaultroutebusy_value"), map.get("noanswer_value"), map.get("networkcongestion"), map.get("ResellerName"), map.get("DefaultValue_checkbox"), map.get("Configure_checkbox"), map.get("Email"), map.get("Phone"), map.get("Fax"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"), map.get("ServiceProfile"), map.get("Maxcallduration"), map.get("Chargebandname"), map.get("InternationalOutgoingCalls_checkbox"), map.get("InternationalIncomingCalls_checkbox"), map.get("MobileCallsAllowed_checkbox"), map.get("PayphoneBlockingenabled_checkbox"), map.get("Supervisionfieldvalue"), map.get("NoReplyTimerValue"), map.get("WebAccessBlocked_checkbox"), map.get("CPSFreeFormatValue"), map.get("SANBlock_Checkbox"), map.get("FOCEnabled_Checkbox"), map.get("EnableLogicalRouting_Checkbox"), map.get("EnablePriceAnnouncement_Checkbox"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifySANLinks");
		APT_NGIN.get().verifyViewSAN("nginservice", map.get("CustomerNameValue"), map.get("SANNumberValue"), map.get("ServiceProfile"), map.get("Supervisionfieldvalue"), map.get("Maxcallduration"), map.get("PreDestinationNumber"), map.get("Ringtonumber"), map.get("NoReplyTimerValue"), map.get("SelectSANSearchType"));
		APT_NGIN.get().verifyEditSAN("nginservice", map.get("CustomerNameValue"), map.get("SANNumberValue"), map.get("Edit_ServiceProfile"), map.get("Supervisionfieldvalue"), map.get("Edit_SupervisionValue"), map.get("Maxcallduration"), map.get("Chargebandname"), map.get("PreDestinationNumber"), map.get("Ringtonumber"), map.get("Routingforpayphone_value"), map.get("Routingformobile_value"), map.get("Defaultrouting_value"), map.get("RingToNumber_Checkbox"), map.get("AnnouncementToPlay_Checkbox"), map.get("ComplexRouting_Checkbox"), map.get("defaultroutebusy_value"), map.get("noanswer_value"), map.get("networkcongestion"), map.get("InternationalOutgoingCalls_checkbox"), map.get("InternationalIncomingCalls_checkbox"), map.get("MobileCallsAllowed_checkbox"), map.get("PayphoneBlockingenabled_checkbox"), map.get("NoReplyTimerValue"), map.get("WebAccessBlocked_checkbox"), map.get("CPSFreeFormatValue"), map.get("EnablePriceAnnouncement_Checkbox"), map.get("SelectSANSearchType"), map.get("Edit_EnablePriceAnnouncement_Checkbox"), map.get("Edit_ChargeBandName"), map.get("Edit_InternationalOutgoingCalls_checkbox"), map.get("Edit_InternationalIncomingCalls_checkbox"), map.get("Edit_MobileCallsAllowed_checkbox"), map.get("Edit_NoReplyTimerValue"), map.get("Edit_MaxCallDuration"), map.get("Edit_PayphoneBlockingenabled_checkbox"), map.get("Edit_WebAccessBlocked_checkbox"), map.get("Edit_SANBlock_Checkbox"), map.get("Edit_FOCEnabled_Checkbox"), map.get("Edit_RingToNumber_Checkbox"), map.get("Edit_AnnouncementToPlay_Checkbox"), map.get("Edit_ComplexRouting_Checkbox"), map.get("Edit_PreDestinationNumber"));
		//APT_NGIN.get().verifyPortIn("nginservice", map.get("CustomerNameValue"), map.get("SANNumberValue"), map.get("SelectSANSearchType"), map.get("PortInNumber"), map.get("CancelPorting"));
		//APT_NGIN.get().verifyPortOut("nginservice", map.get("CustomerNameValue"), map.get("SANNumberValue"), map.get("SelectSANSearchType"), map.get("PortOutNumber"));
		APT_NGIN.get().verifySANMove("nginservice", map.get("CustomerNameValue"), map.get("SANNumberValue"), map.get("SelectSANSearchType"), map.get("DestinationCustomerName"), map.get("SANMove_Orderno"));
		APT_NGIN.get().verifyBulkMove("nginservice", map.get("ServiceIdentification"), map.get("CustomerNameValue"), map.get("SANNumberValue"), map.get("SelectSANSearchType"), map.get("Bulkmove_Country"), map.get("Bulkmove_Customer"), map.get("Filterfrcnumber"), map.get("Bulkmove_Service"), map.get("Customer_Country"), map.get("AddSAN_SANNumber"), map.get("Ringtonumber"), map.get("Routingforpayphone_value"), map.get("Routingformobile_value"), map.get("Defaultrouting_value"), map.get("RingToNumber_Checkbox"), map.get("AnnouncementToPlay_Checkbox"), map.get("ComplexRouting_Checkbox"), map.get("defaultroutebusy_value"), map.get("noanswer_value"), map.get("networkcongestion"), map.get("ResellerName"), map.get("DefaultValue_checkbox"), map.get("Configure_checkbox"), map.get("Email"), map.get("Phone"), map.get("Fax"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"), map.get("ServiceProfile"), map.get("Bulkmove_SANNumberValue1"), map.get("Bulkmove_SANNumberValue2"));
		APT_NGIN.get().verifyManageAddnlFRC("nginservice", map.get("ServiceIdentification"), map.get("CustomerNameValue"), map.get("SANNumberValue1"), map.get("SelectSANSearchType"), map.get("EnablePriceAnnouncement_Checkbox"), map.get("PriceAnnouncementValue"), map.get("PriceAnnOriginvalue"), map.get("InterruptiblePriceAnnouncement_Checkbox"), map.get("ValueInPrice"), map.get("SendFCI_Checkbox"), map.get("SendSCI_Checkbox"), map.get("EnableCallerConfirmation_Checkbox"), map.get("CallerConfirmationAnnouncementValue"), map.get("CallerConfirmationDigitValue"), map.get("NumberOfRepetitionsAllowedValue"), map.get("Edit_InterruptiblePriceAnnouncement_Checkbox"), map.get("Edit_EnablePriceAnnouncement_Checkbox"), map.get("Edit_ValueInPrice"), map.get("Edit_SendFCI_Checkbox"), map.get("Edit_SendSCI_Checkbox"), map.get("Edit_EnableCallerConfirmation_Checkbox"), map.get("Edit_CallerConfirmationAnnouncementValue"), map.get("Edit_CallerConfirmationDigitValue"), map.get("Edit_NumberOfRepetitionsAllowedValue"), map.get("Chargebandname"), map.get("Edit_ChargeBandName"), map.get("Edit_PriceAnnouncementValue"), 
				map.get("Edit_PriceAnnOriginvalue"), map.get("Customer_Country"), map.get("ResellerName"), map.get("DefaultValue_checkbox"), map.get("Configure_checkbox"), map.get("Email"), map.get("Phone"), map.get("Fax"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"), map.get("Ringtonumber"), map.get("Routingforpayphone_value"), map.get("Routingformobile_value"), map.get("Defaultrouting_value"), map.get("RingToNumber_Checkbox"), map.get("AnnouncementToPlay_Checkbox"), map.get("ComplexRouting_Checkbox"), map.get("defaultroutebusy_value"), map.get("noanswer_value"), map.get("networkcongestion"), map.get("ServiceProfile"));
		ExtentTestManager.endTest();
		}
		
		logger= ExtentTestManager.startTest ("AllDeleteOperations");
		APT_NGIN.get().verifyAllDeleteOperations("nginservice", map.get("CustomerNameValue"), map.get("SelectSANSearchType"), map.get("SANNumberValue"), map.get("Customer Administration"), map.get("SAN Administration"), map.get("Reseller Administration"));
		ExtentTestManager.endTest();
	}

	
}
