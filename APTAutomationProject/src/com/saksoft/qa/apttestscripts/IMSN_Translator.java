package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader_SS;

public class IMSN_Translator extends DriverTestcase {

	APT_Login login = new APT_Login();

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 1)
	public void manageColtNetwork(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("manageColtNetwork");

		System.out.println("-------Login functionality------------1");
		login.APT_Login_1();

		System.out.println("Login is done");

		// DriverTestcase.logger.log(LogStatus.INFO,"The service type to be created is:
		// "+map.get("Servicesubtype"));
		// Postcode.get().selectCustomertocreateOrder("CreateOrderService",map.get("ChooseCustomerToBeSelected"),map.get("Customername"));

		ImsNmbrTranslator_Helper.get().selectImsTranslator("ManageColt");

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 2)
	public void verifyListofCountries(Map<String, String> map) throws Exception {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyListofCountries");

		ImsNmbrTranslator_Helper.get().verifyManageNumberTranslationcountrypage("ManageColt");
		System.out.println("Verified the list of countries");

//			
//			System.out.println("-------Verify the fields for Service type and select the service sub type------------1");	
//		
//			Metro.get().Verifyfields(("CreateOrderService"),map.get("ServiceType"), map.get("Modularmsp"), map.get("AutocreateService"));
//			Metro.get().selectCustomertocreateOrderfromleftpane("CreateOrderService",map.get("ChooseCustomerToBeSelected"),map.get("Customername"));
//			Metro.get().SelectServiceType("CreateOrderService", map.get("Order/ContractNumber"),map.get("ServiceType"), map.get("Ordertype"), map.get("valuetobeselectedinorderdropdown"));

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 3)
	public void manageNumberTranslationinternal(Map<String, String> map) throws Exception {
		DriverTestcase.logger = DriverTestcase.extent.startTest("manageNumberTranslationinternal");

		ImsNmbrTranslator_Helper.get().verifyManageNumberTranslation("ManageColt", map.get("Country"));

//			
//			System.out.println("------After selecting service type------2");
//			Metro.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
//					map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
//			
	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 4)
	public void wildcardsearch(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("wildcardsearch");

		ImsNmbrTranslator_Helper.get().verifyWildcardsearch("ManageColt");

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 5)
	public void verifyAddTranslationfields(Map<String, String> map) throws Exception

	{

		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddTranslationfields");

		ImsNmbrTranslator_Helper.get().verifyAddnumberTranslationfields("ManageColt", map.get("Country"));

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 6)
	public void entervaluesinAddTranslation(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("entervaluesinAddTranslation");

		ImsNmbrTranslator_Helper.get().filladdtranslation("ManageColt", map.get("Country"), map.get("TranslateNumber"),
				map.get("TranslatedNumber"), map.get("PrefixNumber"), map.get("NatureAddress"), map.get("CarrierNo"));

//			Thread.sleep(2000);
//			
//			 Metro.get().verifysuccessmessageforCreateService("LANLINK");		
//			
	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 7)
	public void Addnumbertranslationfunction(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("Addnumbertranslationfunction");

		ImsNmbrTranslator_Helper.get().verifyAddnumbertranslationfunction("ManageColt", map.get("Country"));

//			
//			System.out.println("-------- Verify data entered under service sub types---------5");		
//				Metro.get().VerifydatenteredForServiceSubTypeSelected("LANLINK",map.get("servicetypeforverification"),map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
//						map.get("PerformMonitor"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
//						map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
//						map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"),  map.get("performanceReportngForServices"),
//						map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), 
//						map.get("premiumEIR_ServiceCreation"), map.get("Notification management"),  map.get("E_VPNtechnology"), map.get("HCoSPerformanceReporting"));
//			
	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 8)
	public void verifyUploadupdatefile(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyUploadupdatefile");

		ImsNmbrTranslator_Helper.get().uploadUpdatefile("ManageColt");

//			
//			System.out.println("----------- Edit the service type -----------------");	
//			Metro.get().EditTheservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Edit_serviceNumber"),map.get("Edit_endpointCPE"),map.get("EditService_email"), map.get("EditService_phone"), map.get("EditService_remark"), 
//					map.get("EditService_PerformMonitor"),map.get("EditService_proactiveMonitor"), map.get("EditService_deliveryChannel"), map.get("EditService_ManagementOrder"), map.get("vpnTopology"), map.get("EditService_intermediateTechnology"),
//					map.get("EditService_CircuitReference"), map.get("EditService_CircuitType"), map.get("EditService_AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
//					map.get("AutocreateService"), map.get("EditService_ENNI"), map.get("EditService_manageConnectiondropdown"), map.get("Edit_A_Endtechnology"), map.get("Edit_B_Endtechnology"), map.get("EditService_NotificationManagement"),
//					map.get("EditService_perCoSperformanceReport"), map.get("EditService_actelisBased"), map.get("EditService_standardCIR"), map.get("EditService_standardEIR"), map.get("EditService_premiumCIR"), map.get("EditService_premiumEIR"), 
//					map.get("CircuitType"), map.get("EditService_EVPNtechnology"), map.get("EditService_HCoSPerformanceReporting"));
//			
	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 9)
	public void verifyviewHistory(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyviewHistory");

		ImsNmbrTranslator_Helper.get().viewuploadHistory("ManageColt");

		Thread.sleep(2000);

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 10)
	public void verifydownlaodTranslationlink(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("verifydownlaodTranslationlink");

		ImsNmbrTranslator_Helper.get().downlaodTranslationlink("ManageColt");
		Thread.sleep(2000);

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_ManageTranslation", priority = 10)
	public void verifyviewUIHistory(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyviewUIHistory");

		ImsNmbrTranslator_Helper.get().viewUIHistory("ManageColt", map.get("Operation"), map.get("User"),
				map.get("TranslateNumber"));
		Thread.sleep(2000);

	}

}
