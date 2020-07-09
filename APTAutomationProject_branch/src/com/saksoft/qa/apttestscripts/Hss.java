package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader_SS;

public class Hss extends DriverTestcase {

	APT_Login login = new APT_Login();

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_Hss", priority = 1)
	public void chooseCustomer(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("chooseCustomer");

		System.out.println("-------Login functionality------------1");
		login.APT_Login_1();

		System.out.println("Login is done");
		DriverTestcase.logger.log(LogStatus.INFO, "The service type to be created is: " + map.get("Servicesubtype"));

		Hss.get().selectCustomertocreateOrder("CreateOrderService", map.get("ChooseCustomerToBeSelected"),
				map.get("Customername"));

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_Hss", priority = 2)
	public void verifyListofFieldsForOrderandServicetype(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyListofFieldsForOrderandServicetype");

		System.out.println("-------Verify the fields for Service type and select the service sub type------------1");

		Hss.get().Verifyfields(("CreateOrderService"), map.get("ServiceType"), map.get("Modularmsp"),
				map.get("AutocreateService"));
		Hss.get().selectCustomertocreateOrderfromleftpane("CreateOrderService", map.get("ChooseCustomerToBeSelected"),
				map.get("Customername"));
		// Hss.get().SelectServiceType("CreateOrderService",
		// map.get("Order/ContractNumber"),map.get("ServiceType"), map.get("Ordertype"),
		// map.get("valuetobeselectedinorderdropdown"));

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_Hss", priority = 3)
	public void verifySelectServicetype(Map<String, String> map) throws Exception {

		DriverTestcase.logger = DriverTestcase.extent.startTest("verifySelectServicetype");

		System.out.println("-------Select Service Type------------1");

		Hss.get().SelectServiceType("CreateOrderService", map.get("Order/ContractNumber"), map.get("ServiceType"),
				map.get("Ordertype"), map.get("valuetobeselectedinorderdropdown"));

		System.out.println("-------Order Service Page label Validation------------2");

		Hss.get().orderServiceFieldvalidation("HssTag");

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_Hss", priority = 4)
	public void fillvaluesServiceOrder(Map<String, String> map) throws Exception

	{

		DriverTestcase.logger = DriverTestcase.extent.startTest("fillvaluesServiceOrder");
		System.out.println("-------Fill the values in Order/Service Page------------1");

		Hss.get().orderServiceFillValues("HssTag", map.get("ServiceIdvalue"), map.get("RemarkValue"),
				map.get("EmailValue"), map.get("PhoneContactValue"), map.get("Performanceexpected"),
				map.get("PerformanceDefault"), map.get("Waveexpected"), map.get("WaveDefault"),
				map.get("InterfaceSpeedValue"), map.get("TypeOfServiceValue"));

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_Hss", priority = 5)
	public void VerifyServicecreatedPanel(Map<String, String> map) throws Exception

	{
		DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyServicecreatedPanel");

		System.out.println("-------Verify Service Succes messae and Service  Panel ------------1");

		Hss.get().VerifyServicecreated("HssTag", map.get("ServiceIdvalue"), map.get("RemarkValue"),
				map.get("EmailValue"), map.get("PhoneContactValue"), map.get("ServiceType"),
				map.get("Performanceexpected"), map.get("PerformanceDefault"), map.get("Waveexpected"),
				map.get("WaveDefault"));
	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_Hss", priority = 6)
	public void VerifyManagementPanel(Map<String, String> map) throws Exception

	{
		DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyManagementPanel");

		Hss.get().VerifyManagement("HssTag", map.get("Performanceexpected"), map.get("Waveexpected"),
				map.get("InterfaceSpeed"), map.get("TypeOfService"));

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_Hss", priority = 7)
	public void VerifyOrderPanel(Map<String, String> map) throws Exception

	{

		DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyOrderPanel");

		System.out.println("-------Verify Order Panel and Order Action Dropdown------------1");

		Hss.get().VerifyOrder("HssTag", map.get("Ordertype"), map.get("OrderContractNumber"),
				map.get("valuetobeselectedinorderdropdown"));
		Hss.get().OrderActionDropdown("HssTag", map.get("VoiceLineNumber"));
		Hss.get().changeOrder("HssTag", map.get("VoiceLineNumber"), map.get("Ordertype"), map.get("OrderNumberchange"),
				map.get("changeordervalue"));
	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_Hss", priority = 8)
	public void VerifyAddDevice(Map<String, String> map) throws Exception

	{
		System.out.println("-------Add Device-----------1");

		DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyAddDevice");

		Hss.get().AddDevice("HssTag", map.get("DeviceType"), map.get("DeviceName"), map.get("ManagementAddress"),
				map.get("VendorModel"), map.get("Country"), map.get("City"), map.get("Site"), map.get("Name"));
		Hss.get().DeviceActionDropdown("HssTag");
		Hss.get().DeleteDeviceActionDropdown("HssTag");

	}

	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_Hss", priority = 9)
	public void verifyServicePanelAction(Map<String, String> map) throws Exception

	{
		System.out.println("-------Add Device-----------1");
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyServicePanelAction");

		Hss.get().VerifyServicePanelAction("HssTag");
	}

}