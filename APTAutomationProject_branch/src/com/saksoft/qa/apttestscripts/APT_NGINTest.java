package com.saksoft.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.scripthelpers.APT_LoginHelper;

public class APT_NGINTest extends DriverTestcase{
	
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=0)
	public void CreateCustomer(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("CreateCustomer"); 
		APT_NGIN.get().createcustomer("nginservice", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));		
	} 
	
	@Test(description = "TC-02",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=1)
    public void choosecustomer(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("choosecustomer"); 
		APT_NGIN.get().selectCustomertocreateOrder("nginservice",map.get("ChooseCustomerToBeSelected"),map.get("Name1"),map.get("Name2"));
		
	}


	@Test(description = "TC-03",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=2)
	 public void verifycreateorder(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyneworder");
		APT_NGIN.get().createorderservice("nginservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
	}
	
		
	@Test(description = "TC-04",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=3)
	 public void verifyservicetypeselection(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicetypeselection");
		APT_NGIN.get().verifyselectservicetype("nginservice", map.get("ServiceType"));
	}
	
	
	@Test(description = "TC-05",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=4)
	 public void verifyservicecreation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicecreation");
		APT_NGIN.get().verifyingservicecreation("nginservice", map.get("ServiceIdentification"), map.get("Remarks"), map.get("Customer Administration"), map.get("SAN Administration"), map.get("Reseller Administration"),  map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"));
	}
	
	
	@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=5)
	 public void verifyCustomerDetailsInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyCustomerDetailsInformation");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().verifyCustomerDetailsInformation("nginservice", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		APT_NGIN.get().verifyUserDetailsInformation("nginservice", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
	}
		
	@Test(description = "TC-07",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=6)
	public void verifyUserDetailsInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyUserDetailsInformation");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().createnewuser("nginservice", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), map.get("EditEmail"), map.get("EditPhone"));
		
	}
	
	@Test(description = "TC-08",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=7)
	public void verifyOrderDetailsInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyOrderDetailsInformation");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		//APT_NGIN.get().verifyorderpanelinformation_Existingorder("nginservice", map.get("Existing OrderService"), map.get("ExistingOrderNumber"), map.get("Existing RFIREQNumber"));
		APT_NGIN.get().verifyorderpanelinformation_Neworder("nginservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		APT_NGIN.get().verifyorderpanel_editorder("nginservice", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
		APT_NGIN.get().verifyorderpanel_changeorder("nginservice", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));
		
}
	
	@Test(description = "TC-09",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=8)
	 public void verifyServicepanelinviewservicepage(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyServicepanelinviewservicepage");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().verifyservicepanelInformationinviewservicepage("nginservice", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("Remarks"));
		APT_NGIN.get().verifyservicepanel_links("nginservice", map.get("EditRemarks"), map.get("Remarks"), map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"), map.get("BulkJob_FilePath"));
	}
	
	@Test(description = "TC-10",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=9)
	 public void verifyManagementOptionspanel(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyManagementOptionspanel");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().verifyManagementOptionspanel("nginservice");
	
	}
	
	@Test(description = "TC-11",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=10)
	 public void verifyResellerpanelinviewservicepage(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyResellerpanelinviewservicepage");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().verifyResellerpanel("nginservice");
	
	}
	
	@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=11)
	 public void AddReseller(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddReseller");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().AddReseller("nginservice", map.get("OCN"), map.get("Reseller_Email"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"), map.get("Reseller_Phone"), map.get("Reseller_Fax"));
	
	}
	
	@Test(description = "TC-13",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=12)
	 public void verifyResellerLinks(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyResellerLinks");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().verifyResellerLinks("nginservice", map.get("OCN"), map.get("Reseller_EditEmail"), map.get("Reseller_EditCity"), map.get("Reseller_EditStreetName"), map.get("Reseller_EditStreetNumber"), map.get("Reseller_EditPOBox"), map.get("Reseller_EditZipcode"), map.get("Reseller_EditPhone"), map.get("Reseller_EditFax"));
	
	}
	
	@Test(description = "TC-14",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=13)
	 public void verifyCustomerpanelinviewservicepage(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyCustomerpanelinviewservicepage");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().verifyCustomerpanel("nginservice");
	
	}
	
	@Test(description = "TC-15",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=14)
	 public void AddCustomer(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddCustomer");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().AddCustomer("nginservice", map.get("ResellerName"), map.get("DefaultValue_checkbox"), map.get("Configure_checkbox"), map.get("Customer_Country"), map.get("Email"), map.get("Phone"), map.get("Fax"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"));
	
	}
	
	@Test(description = "TC-16",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=15)
	 public void verifyCustomerLinks(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyCustomerLinksinviewservicepage");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().verifyCustomerLinks("nginservice", map.get("Reseller_EditResellerName"), map.get("Reseller_EditEmail"), map.get("Reseller_EditCity"), map.get("Reseller_EditStreetName"), map.get("Reseller_EditStreetNumber"), map.get("Reseller_EditPOBox"), map.get("Reseller_EditZipcode"), map.get("Reseller_EditPhone"), map.get("Reseller_EditFax"));
	
	}
	
	@Test(description = "TC-17",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=16)
	 public void verifySANpanelinviewservicepage(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifySANpanelinviewservicepage");
		APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().verifySANpanel("nginservice");
	
	}
	
	@Test(description = "TC-18",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=17)
	 public void AddSAN(Map<String, String> map) throws InterruptedException, DocumentException, IOException, InvalidFormatException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddSAN");
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().AddSAN("nginservice", map.get("Customer_Country"), map.get("AddSAN_SANNumber"), map.get("PreDestinationNumber"), map.get("Ringtonumber"), map.get("Routingforpayphone_value"), map.get("Routingformobile_value"), map.get("Defaultrouting_value"), map.get("RingToNumber_Checkbox"), map.get("AnnouncementToPlay_Checkbox"), map.get("ComplexRouting_Checkbox"), map.get("defaultroutebusy_value"), map.get("noanswer_value"), map.get("networkcongestion"), map.get("ResellerName"), map.get("DefaultValue_checkbox"), map.get("Configure_checkbox"), map.get("Email"), map.get("Phone"), map.get("Fax"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"), map.get("ServiceProfile"), map.get("Maxcallduration"), map.get("Chargebandname"), map.get("InternationalOutgoingCalls_checkbox"), map.get("InternationalIncomingCalls_checkbox"), map.get("MobileCallsAllowed_checkbox"), map.get("PayphoneBlockingenabled_checkbox"), map.get("Supervisionfieldvalue"), map.get("NoReplyTimerValue"), map.get("WebAccessBlocked_checkbox"), map.get("CPSFreeFormatValue"), map.get("SANBlock_Checkbox"), map.get("FOCEnabled_Checkbox"), map.get("EnableLogicalRouting_Checkbox"), map.get("EnablePriceAnnouncement_Checkbox"));
	
	}
	
	@Test(description = "TC-19",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=18)
	 public void verifySANLinks(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifySANLinks");
		APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		APT_NGIN.get().verifySANLinks("nginservice", map.get("CustomerNameValue"), map.get("SANNumberValue"), map.get("PortInNumber"), map.get("PortOutNumber"), map.get("Edit_ServiceProfile"), map.get("Supervisionfieldvalue"), map.get("Edit_SupervisionValue"), map.get("Maxcallduration"), map.get("Chargebandname"), map.get("PreDestinationNumber"), map.get("Ringtonumber"), map.get("AnnouncementToPlay_value"), map.get("Routingforpayphone_value"), map.get("Routingformobile_value"), map.get("Defaultrouting_value"), map.get("RingToNumber_Checkbox"), map.get("AnnouncementToPlay_Checkbox"), map.get("ComplexRouting_Checkbox"), map.get("defaultroutebusy_value"), map.get("noanswer_value"), map.get("networkcongestion"), map.get("PriceAnnouncementValue"), map.get("PriceAnnOriginvalue"), map.get("InternationalOutgoingCalls_checkbox"), map.get("InternationalIncomingCalls_checkbox"), map.get("MobileCallsAllowed_checkbox"), map.get("PayphoneBlockingenabled_checkbox"), map.get("NoReplyTimerValue"), map.get("WebAccessBlocked_checkbox"), map.get("CPSFreeFormatValue"), map.get("SANBlock_Checkbox"), map.get("FOCEnabled_Checkbox"), map.get("EnablePriceAnnouncement_Checkbox"), map.get("SelectSANSearchType"), map.get("InterruptiblePriceAnnouncement_Checkbox"), map.get("ValueInPrice"), map.get("SendFCI_Checkbox"), map.get("SendSCI_Checkbox"), map.get("EnableCallerConfirmation_Checkbox"), map.get("CallerConfirmationAnnouncementValue"), map.get("CallerConfirmationDigitValue"), map.get("NumberOfRepetitionsAllowedValue"), map.get("Edit_InterruptiblePriceAnnouncement_Checkbox"), map.get("Edit_EnablePriceAnnouncement_Checkbox"), map.get("Edit_ValueInPrice"), map.get("Edit_SendFCI_Checkbox"), map.get("Edit_SendSCI_Checkbox"), map.get("Edit_EnableCallerConfirmation_Checkbox"), map.get("Edit_CallerConfirmationAnnouncementValue"), map.get("Edit_CallerConfirmationDigitValue"), map.get("Edit_NumberOfRepetitionsAllowedValue"), map.get("Edit_ChargeBandName"), map.get("Edit_PriceAnnouncementValue"), map.get("Edit_PriceAnnOriginvalue"), map.get("Edit_InternationalOutgoingCalls_checkbox"), map.get("Edit_InternationalIncomingCalls_checkbox"), map.get("Edit_MobileCallsAllowed_checkbox"), map.get("Edit_NoReplyTimerValue"), map.get("Edit_MaxCallDuration"), map.get("Edit_PayphoneBlockingenabled_checkbox"), map.get("Edit_WebAccessBlocked_checkbox"), map.get("Edit_SANBlock_Checkbox"), map.get("Edit_FOCEnabled_Checkbox"), map.get("Edit_RingToNumber_Checkbox"), map.get("Edit_AnnouncementToPlay_Checkbox"), map.get("Edit_ComplexRouting_Checkbox"), map.get("Edit_PreDestinationNumber"));
		APT_NGIN.get().verifySANMove("nginservice", map.get("CustomerNameValue"), map.get("SANNumberValue"), map.get("SelectSANSearchType"), map.get("DestinationCustomerName"), map.get("SANMove_Orderno"));
		APT_NGIN.get().verifyBulkMove("nginservice", map.get("ServiceIdentification"), map.get("CustomerNameValue"), map.get("SANNumberValue"), map.get("SelectSANSearchType"), map.get("Bulkmove_Country"), map.get("Bulkmove_Customer"), map.get("Filterfrcnumber"), map.get("Bulkmove_Service"), map.get("Customer_Country"), map.get("AddSAN_SANNumber"), map.get("Ringtonumber"), map.get("Routingforpayphone_value"), map.get("Routingformobile_value"), map.get("Defaultrouting_value"), map.get("RingToNumber_Checkbox"), map.get("AnnouncementToPlay_Checkbox"), map.get("ComplexRouting_Checkbox"), map.get("defaultroutebusy_value"), map.get("noanswer_value"), map.get("networkcongestion"), map.get("ResellerName"), map.get("DefaultValue_checkbox"), map.get("Configure_checkbox"), map.get("Email"), map.get("Phone"), map.get("Fax"), map.get("Reseller_City"), map.get("Reseller_StreetName"), map.get("Reseller_StreetNumber"), map.get("Reseller_POBox"), map.get("Reseller_Zipcode"), map.get("ServiceProfile"), map.get("Bulkmove_SANNumberValue1"), map.get("Bulkmove_SANNumberValue2"));
		//APT_NGIN.get().searchorder("nginservice", map.get("ServiceIdentification"));
		//APT_NGIN.get().verifyAllDeleteOperations("nginservice", map.get("CustomerNameValue"), map.get("SelectSANSearchType"), map.get("SANNumberValue"));
	
	}

	
	
	//============================================================================================================
	
	//										SAN MANAGEMENT

   //==============================================================================================================	
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=19)
	 public void verifySearchforSAN(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifySearchforSAN");
		APT_NGIN.get().verifySearchSAN("nginservice", map.get("Search_SANNumber"), map.get("CustomerNameValue"), map.get("SearchSANfilename"), map.get("Browserfiles_Downloadspath"), map.get("ServiceProfile"), map.get("InternationalOutgoingCalls_checkedvalue"), map.get("InternationalIncomingCalls_checkedvalue"), map.get("MobileCallsAllowed_checkedvalue"), map.get("PayphoneBlockingenabled_checkedvalue"), map.get("Supervisionfieldvalue"), map.get("NoReplyTimerValue"), map.get("WebAccessBlockedvalue"), map.get("CPSFreeFormatValue"), map.get("Maxcallduration"), map.get("Chargebandname"), map.get("PreDestinationNumber"));
		
	
	}
	
	
	//============================================================================================================
	
	//										NGIN MESSAGE

	//==============================================================================================================	
		
	
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGIN", priority=20)
	 public void verifyNGINMessage(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyNGINMessage");
		APT_NGIN.get().verifyNGINMessage("nginservice", map.get("NGINMessage_SANNumber"));
		
	}
	

	
}
