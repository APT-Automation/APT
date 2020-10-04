package com.colt.qa.scripthelpers;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;

public class Lanlink_NationalHelper extends DriverHelper {

	public Lanlink_NationalHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	XMLReader xml = new XMLReader("src\\com\\colt\\qa\\pagerepository\\Lanlink_National.xml");
	
	

	public void addCircuit_Overture(String application, String serviceName, String customerName) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(1000);
		
		boolean circuitOvertureOrAccedianPanel=getwebelement(xml.getlocator("//locators/" + application + "/cicuit1G_OverturePanel")).isDisplayed();
		if(circuitOvertureOrAccedianPanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Circuits (Overture/Accedian-1G)' panel is displaying");
			Log.info("'Circuits (Overture/Accedian-1G)' panel is displaying");
			
			click_commonMethod(application, "Add Overture", "circuit1G_addOvertureLink" , xml);		//click on 'Add Overture' link
			
			waitForpageload();
			waitforPagetobeenable();
			
			
			boolean overturePanel=getwebelement(xml.getlocator("//locators/" + application + "/circuit1G_OverturePanelInsideAddOverturePage")).isDisplayed();
			if(overturePanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Oveture Circuit' panel is displaying");
				Log.info("'Overture Ciruit' panel is displaying");
			
			//Verify Column names inside the table	
				compareText(application, "Customer Column", "columnHeader_customerColumn", "Customer", xml);	 //Customer Column Header
				
				compareText(application, "Order Column", "columnHeader_orderColumn", "Order", xml);		//Order Column Header
				
				compareText(application, "Service Name Column", "columnHeader_serviceNameColumn", "Service Name", xml);	//Service Name Column Header
				
				compareText(application, "Service SubType", "columnHeader_serviceSubTypeColumn", "Service Subtype", xml);	//Service SubType
				
				compareText(application, "VPN Topology", "columnHeader_vpnTopology", "VPN Topology", xml);   //VPN Topology
				
				
			//Enter value in Serice Name text field	
				addtextFields_commonMethod(application, "Service Name", "serviceName_TextField", serviceName, xml);
				
				
				click_commonMethod(application, "Customer column Filter", "filterIcon_customerName", xml);
				Thread.sleep(2000);
				
				addtextFields_commonMethod(application, "Customer_Filter", "filterTextField", customerName, xml);
				Thread.sleep(2000);
				
				clickOnBankPage();
				
			//compare values under the columns
				compareText(application, "Customer Name", "CustomercolumnVaue", customerName, xml);

				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Oveture Circuit' panel is displaying");
				Log.info("'Overture Ciruit' panel is displaying");
			}
			
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Circuits (Overture/Accedian-1G)' panel is not displaying");
			Log.info("'Circuits (Overture/Accedian-1G)' panel is not displaying");
		}
		
	}
	
	

}
