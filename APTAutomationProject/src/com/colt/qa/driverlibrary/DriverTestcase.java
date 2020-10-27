package com.colt.qa.driverlibrary;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.colt.qa.reporter.ExtentManager;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.colt.qa.scripthelpers.APT_VOIPAccessHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.scripthelpers.APT_ColtTotalConfigHelper;
import com.colt.qa.scripthelpers.APT_CreateAccessCoreDevice_ManageNetworkHelper;
import com.colt.qa.scripthelpers.APT_DomainManagementHelper;
import com.colt.qa.scripthelpers.APT_HSSHelper;
import com.colt.qa.scripthelpers.APT_IPAccessCMSDataCenterHelper;
import com.colt.qa.scripthelpers.APT_IPAccessMultihomedHelper;
import com.colt.qa.scripthelpers.APT_IPAccessNoCPEHelper;
import com.colt.qa.scripthelpers.APT_IPAccessResilientConfigHelper;
import com.colt.qa.scripthelpers.APT_IPAccessSpeedboatHelper;
import com.colt.qa.scripthelpers.APT_IPAccess_VCPEConfigHelper;
import com.colt.qa.scripthelpers.APT_IPAccess_VCPEResilientConfigHelper;
import com.colt.qa.scripthelpers.APT_IPTransitHelper;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateAccessCoreDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateAccessSwitchCoreDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateCoreRouterDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateDCNDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateDSLAMDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateFirewallDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateKeyserverDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateLoadBalancerDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateMDFFirewallDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateMiniDSLAMDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateOrder_IPVPNHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreatePrizmnetDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateTrafficAggregatorDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateVOIPAccessDASSwitchDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CreateVoiceGatewayDeviceHelper;
import com.colt.qa.scripthelpers.APT_MCS_CustomerUserHelper;
import com.colt.qa.scripthelpers.APT_ManageNetworkHelper;
import com.colt.qa.scripthelpers.APT_NGINHelper;
import com.colt.qa.scripthelpers.APT_NGINMessageHelper;
import com.colt.qa.scripthelpers.APT_SANManagementHelper;
import com.colt.qa.scripthelpers.APT_VOIPAccessHelper;
import com.colt.qa.scripthelpers.APT_VoiceLineHelper;
import com.colt.qa.scripthelpers.APT_wholeSaleHelper;
import com.colt.qa.scripthelpers.IPVPNHelper_newTab;
import com.colt.qa.scripthelpers.ImsNmbrTranslator_Helper;
import com.colt.qa.scripthelpers.LANLINK_NewTab;
import com.colt.qa.scripthelpers.ManagePostcode_Helper;
import com.colt.qa.scripthelpers.searchForDeviceHelper;
import com.colt.qa.scripthelpers.voipAccessHelper_NewTab;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.scripthelpers.Lanlink_DirectFiberHelper;
import com.colt.qa.scripthelpers.Lanlink_InternationalHelper;
import com.colt.qa.scripthelpers.Lanlink_MetroHelper;
import com.colt.qa.scripthelpers.Lanlink_NationalHelper;
import com.colt.qa.scripthelpers.Lanlink_OLOHelper;
import com.colt.qa.scripthelpers.Lanlink_Outbandmanagementhelper;

public class DriverTestcase {

	public static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();
	// public static final ThreadLocal<RemoteWebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();

	public static final ThreadLocal<APT_LoginHelper> APTLogin = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateAccessCoreDeviceHelper> APT_CreateAccessCoreDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateAccessSwitchCoreDeviceHelper> APT_CreateAccessSwitchDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateCoreRouterDeviceHelper> APT_CreateCoreRouterDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateKeyserverDeviceHelper> APT_CreateKeyserverDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateMDFFirewallDeviceHelper> APT_CreateMDFFirewallDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreatePrizmnetDeviceHelper> APT_CreatePrizmnetDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateDCNDeviceHelper> APT_CreateDCNDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateFirewallDeviceHelper> APT_CreateFirewallDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateLoadBalancerDeviceHelper> APT_CreateLoadBalancerDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateTrafficAggregatorDeviceHelper> APT_CreateTrafficAggregatorDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateVoiceGatewayDeviceHelper> APT_CreateVoiceGatewayDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateVOIPAccessDASSwitchDeviceHelper> APT_CreateVOIPAccessDASSwitchDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateDSLAMDeviceHelper> APT_CreateDSLAMDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateMiniDSLAMDeviceHelper> APT_CreateMiniDSLAMDeviceHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_CreateAccessCoreDevice_ManageNetworkHelper> APT_ManageNetworkHelpr = new InheritableThreadLocal<>();
	public static final ThreadLocal<ManagePostcode_Helper> ManagePostcode_Helper = new InheritableThreadLocal<>();
	public static final ThreadLocal<ImsNmbrTranslator_Helper> ImsNmbrTranslator_Helper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_wholeSaleHelper> APT_Helper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CustomerUserHelper> createCustomerSeparateHelper= new InheritableThreadLocal<>();
	
	public static final ThreadLocal<APT_NGINHelper> APT_NGIN = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_DomainManagementHelper> APT_DomainManageHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_ManageNetworkHelper> APT_ManageNetworkHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_IPTransitHelper> APT_IPTransitHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_HSSHelper> APT_HSSHelper=new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_SANManagementHelper> APT_SANMgmtHelper=new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_NGINMessageHelper> APT_NGINMessageHelper=new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_VoiceLineHelper> APT_VoiceLineHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<searchForDeviceHelper>  APT_searchDevice = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_VOIPAccessHelper> APT_VOIPHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<com.colt.qa.scripthelpers.DDI_Helper> DDI_Helper = new InheritableThreadLocal<>();
	public static final ThreadLocal<com.colt.qa.scripthelpers.Lanlink_DirectFiberHelper> DirectFiber = new ThreadLocal<>();
	public static final ThreadLocal<com.colt.qa.scripthelpers.Lanlink_MetroHelper> Metro=new ThreadLocal<>();
	public static final ThreadLocal<com.colt.qa.scripthelpers.Lanlink_OLOHelper> OLO=new ThreadLocal<>();
	public static final ThreadLocal<com.colt.qa.scripthelpers.Lanlink_Outbandmanagementhelper> Outband = new InheritableThreadLocal<>();
	public static final ThreadLocal<com.colt.qa.scripthelpers.Lanlink_InternationalHelper> International = new InheritableThreadLocal<>();
	public static final ThreadLocal<com.colt.qa.scripthelpers.Lanlink_NationalHelper> National= new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MCS_CreateOrder_IPVPNHelper> APT_IPVPNHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<LANLINK_NewTab> lanlinkNewTab = new InheritableThreadLocal<>();
	public static final ThreadLocal<voipAccessHelper_NewTab>  voipAccessnewtab = new InheritableThreadLocal<>();
	public static final ThreadLocal<IPVPNHelper_newTab> ipvpnnewTab = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_IPAccessNoCPEHelper> NoCPEHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_IPAccessMultihomedHelper>MultihomedHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_IPAccessResilientConfigHelper>ResilientHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_ColtTotalConfigHelper>ColtTotalHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<com.colt.qa.scripthelpers.APT_IPAccessConfigHelper>APT_IPAccessConfigHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_IPAccessSpeedboatHelper>APT_IPASpeedboatHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_IPAccess_VCPEConfigHelper> APT_IPAccess_VCPEConfigHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_IPAccess_VCPEResilientConfigHelper> APT_IPA_VCPEResilientConfigHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_IPAccessCMSDataCenterHelper> APT_IPA_CMSHelper = new InheritableThreadLocal<>();

	
	
	
	public static com.colt.qa.listeners.TestListener Testlistener;
	public ThreadLocal<String> TestName = new ThreadLocal();
	public static SessionId session_id;
	public static ChromeDriver driver;
	public static int itr;
	public  ExtentTest logger;

	
	@BeforeMethod
	public void BeforeMethod(Method method, ITestContext ctx, Object[] data) throws IOException, Exception 
	{
		Object[] st = null;
		try
		{
			st = (Object[]) data[0];
		} 
		catch (Exception e) 
		{
			st = new Object[][] { { "" } };
		}
	}
	public void setup() throws Exception 
	{
		WebDriver dr = null;
		PropertyReader pr = new PropertyReader();
		String targatedbrowser = pr.readproperty("browser");
		String url = pr.readproperty("URL");
		Log.info("URL");
		if (targatedbrowser.equals("chrome")) {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			dr = new ChromeDriver(capabilities);
			dr.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS );
		} 
		else if (targatedbrowser.equals("ie")) 
		{
			Log.info("For IE inprogress");
		}

		else {
			Log.info("For FF inprogress");
		}

		dr.manage().window().maximize();
		WEB_DRIVER_THREAD_LOCAL.set(dr);
		Thread.sleep(3000);
		
		APT_LoginHelper apt=new APT_LoginHelper(getwebdriver());
		APTLogin.set(apt);
		
		APT_MCS_CreateAccessCoreDeviceHelper createdevice = new APT_MCS_CreateAccessCoreDeviceHelper(getwebdriver());
		APT_CreateAccessCoreDeviceHelper.set(createdevice);
		
		APT_MCS_CreateAccessSwitchCoreDeviceHelper createAccessSwitchdevice = new APT_MCS_CreateAccessSwitchCoreDeviceHelper(getwebdriver());
		APT_CreateAccessSwitchDeviceHelper.set(createAccessSwitchdevice);
		
		APT_MCS_CreateCoreRouterDeviceHelper createCoreRouterdevice = new APT_MCS_CreateCoreRouterDeviceHelper(getwebdriver());
		APT_CreateCoreRouterDeviceHelper.set(createCoreRouterdevice);
		
		APT_MCS_CreateKeyserverDeviceHelper createKeyserverdevice = new APT_MCS_CreateKeyserverDeviceHelper(getwebdriver());
		APT_CreateKeyserverDeviceHelper.set(createKeyserverdevice);
		
		APT_MCS_CreateMDFFirewallDeviceHelper createMDFFirewalldevice = new APT_MCS_CreateMDFFirewallDeviceHelper(getwebdriver());
		APT_CreateMDFFirewallDeviceHelper.set(createMDFFirewalldevice);
		
		APT_MCS_CreatePrizmnetDeviceHelper createPrizmnetdevice = new APT_MCS_CreatePrizmnetDeviceHelper(getwebdriver());
		APT_CreatePrizmnetDeviceHelper.set(createPrizmnetdevice);
		
		APT_MCS_CreateDCNDeviceHelper createDCNdevice = new APT_MCS_CreateDCNDeviceHelper(getwebdriver());
		APT_CreateDCNDeviceHelper.set(createDCNdevice);
		
		APT_MCS_CreateFirewallDeviceHelper createFirewalldevice = new APT_MCS_CreateFirewallDeviceHelper(getwebdriver());
		APT_CreateFirewallDeviceHelper.set(createFirewalldevice);
		
		APT_MCS_CreateLoadBalancerDeviceHelper createLoadBalancerdevice = new APT_MCS_CreateLoadBalancerDeviceHelper(getwebdriver());
		APT_CreateLoadBalancerDeviceHelper.set(createLoadBalancerdevice);
		
		APT_MCS_CreateTrafficAggregatorDeviceHelper createTrafficAggregatordevice = new APT_MCS_CreateTrafficAggregatorDeviceHelper(getwebdriver());
		APT_CreateTrafficAggregatorDeviceHelper.set(createTrafficAggregatordevice);
		
		APT_MCS_CreateVoiceGatewayDeviceHelper createVoiceGatewaydevice = new APT_MCS_CreateVoiceGatewayDeviceHelper(getwebdriver());
		APT_CreateVoiceGatewayDeviceHelper.set(createVoiceGatewaydevice);
		
		APT_MCS_CreateVOIPAccessDASSwitchDeviceHelper createVOIPAccessDASSwitchdevice = new APT_MCS_CreateVOIPAccessDASSwitchDeviceHelper(getwebdriver());
		APT_CreateVOIPAccessDASSwitchDeviceHelper.set(createVOIPAccessDASSwitchdevice);
		
		APT_MCS_CreateDSLAMDeviceHelper createDSLAMdevice = new APT_MCS_CreateDSLAMDeviceHelper(getwebdriver());
		APT_CreateDSLAMDeviceHelper.set(createDSLAMdevice);
		
		APT_MCS_CreateMiniDSLAMDeviceHelper createMiniDSLAMdevice = new APT_MCS_CreateMiniDSLAMDeviceHelper(getwebdriver());
		APT_CreateMiniDSLAMDeviceHelper.set(createMiniDSLAMdevice);
		
		APT_CreateAccessCoreDevice_ManageNetworkHelper  managenetwork= new APT_CreateAccessCoreDevice_ManageNetworkHelper(getwebdriver());
		APT_ManageNetworkHelpr.set(managenetwork);
		
		ManagePostcode_Helper psc= new ManagePostcode_Helper(getwebdriver());
		ManagePostcode_Helper.set(psc);
		
		ImsNmbrTranslator_Helper imnt = new ImsNmbrTranslator_Helper(getwebdriver());
		ImsNmbrTranslator_Helper.set(imnt);
		
		APT_wholeSaleHelper aptautomation = new APT_wholeSaleHelper(getwebdriver());
		APT_Helper.set(aptautomation);
		
		APT_MCS_CustomerUserHelper cc2=new APT_MCS_CustomerUserHelper(getwebdriver());
		createCustomerSeparateHelper.set(cc2);
		
		APT_NGINHelper ngin = new APT_NGINHelper(getwebdriver());
		APT_NGIN.set(ngin);
		
		APT_DomainManagementHelper DM= new APT_DomainManagementHelper(getwebdriver());
		APT_DomainManageHelper.set(DM);
		
		APT_ManageNetworkHelper managenetwork1= new APT_ManageNetworkHelper(getwebdriver());
		APT_ManageNetworkHelper.set(managenetwork1);
		
		APT_IPTransitHelper iptransit= new APT_IPTransitHelper(getwebdriver());
		APT_IPTransitHelper.set(iptransit);
		
		APT_HSSHelper Hss=new APT_HSSHelper(getwebdriver());
		APT_HSSHelper.set(Hss);
		
		APT_SANManagementHelper san=new APT_SANManagementHelper(getwebdriver());
		APT_SANMgmtHelper.set(san);
		
		APT_NGINMessageHelper nginmsg=new APT_NGINMessageHelper(getwebdriver());
		APT_NGINMessageHelper.set(nginmsg);
		
		APT_VoiceLineHelper vlv= new APT_VoiceLineHelper(getwebdriver());
		APT_VoiceLineHelper.set(vlv);
		
		
		APT_VOIPAccessHelper voip = new APT_VOIPAccessHelper(getwebdriver());
		APT_VOIPHelper.set(voip);

		com.colt.qa.scripthelpers.DDI_Helper Ddi = new com.colt.qa.scripthelpers.DDI_Helper(getwebdriver());
		DDI_Helper.set(Ddi);
		
		com.colt.qa.scripthelpers.Lanlink_DirectFiberHelper dirctFbr = new com.colt.qa.scripthelpers.Lanlink_DirectFiberHelper(getwebdriver());
		DirectFiber.set(dirctFbr);
		
		com.colt.qa.scripthelpers.Lanlink_MetroHelper metro= new com.colt.qa.scripthelpers.Lanlink_MetroHelper(getwebdriver());
		Metro.set(metro);
		
		com.colt.qa.scripthelpers.Lanlink_OLOHelper olo=new com.colt.qa.scripthelpers.Lanlink_OLOHelper(getwebdriver());
		OLO.set(olo);
		
		com.colt.qa.scripthelpers.Lanlink_Outbandmanagementhelper out= new com.colt.qa.scripthelpers.Lanlink_Outbandmanagementhelper(getwebdriver());
		Outband.set(out);
		
		com.colt.qa.scripthelpers.Lanlink_InternationalHelper intnal = new com.colt.qa.scripthelpers.Lanlink_InternationalHelper(getwebdriver());
		International.set(intnal);
		
		com.colt.qa.scripthelpers.Lanlink_NationalHelper natnal=new com.colt.qa.scripthelpers.Lanlink_NationalHelper(getwebdriver());
		National.set(natnal);

		searchForDeviceHelper saerchDveiec = new searchForDeviceHelper(getwebdriver());
		 APT_searchDevice.set(saerchDveiec);
		 
		 APT_MCS_CreateOrder_IPVPNHelper ipvpn = new APT_MCS_CreateOrder_IPVPNHelper(getwebdriver());
			APT_IPVPNHelper.set(ipvpn);
			
		LANLINK_NewTab whles = new LANLINK_NewTab(getwebdriver());
			lanlinkNewTab.set(whles);
			
		voipAccessHelper_NewTab voiAcs = new voipAccessHelper_NewTab(getwebdriver());
			voipAccessnewtab.set(voiAcs);

		IPVPNHelper_newTab ipvpnNewTab = new IPVPNHelper_newTab(getwebdriver());
			ipvpnnewTab.set(ipvpnNewTab);
			
		
		
		APT_IPAccessNoCPEHelper ipaccessnocpeconfig= new APT_IPAccessNoCPEHelper(getwebdriver());
		NoCPEHelper.set(ipaccessnocpeconfig);
		
		APT_IPAccessMultihomedHelper ipaccessmultihomedonfig= new APT_IPAccessMultihomedHelper(getwebdriver());
		MultihomedHelper.set(ipaccessmultihomedonfig);
		
		APT_IPAccessResilientConfigHelper ipaccessResilientConfig= new APT_IPAccessResilientConfigHelper(getwebdriver());
		ResilientHelper.set(ipaccessResilientConfig);
		
		APT_ColtTotalConfigHelper colttotal= new APT_ColtTotalConfigHelper(getwebdriver());
		ColtTotalHelper.set(colttotal);
		
		com.colt.qa.scripthelpers.APT_IPAccessConfigHelper ipaccessconfig= new com.colt.qa.scripthelpers.APT_IPAccessConfigHelper(getwebdriver());
		APT_IPAccessConfigHelper.set(ipaccessconfig);
		

		APT_IPAccessSpeedboatHelper speedboat= new APT_IPAccessSpeedboatHelper(getwebdriver());
		APT_IPASpeedboatHelper.set(speedboat);
		
		com.colt.qa.scripthelpers.APT_IPAccess_VCPEConfigHelper IPVCPE= new com.colt.qa.scripthelpers.APT_IPAccess_VCPEConfigHelper(getwebdriver());
		APT_IPAccess_VCPEConfigHelper.set(IPVCPE);
		
		APT_IPAccess_VCPEResilientConfigHelper IPVCPEResilient= new APT_IPAccess_VCPEResilientConfigHelper(getwebdriver());
		APT_IPA_VCPEResilientConfigHelper.set(IPVCPEResilient);
		
		
		APT_IPAccessCMSDataCenterHelper IPACMS= new APT_IPAccessCMSDataCenterHelper(getwebdriver());
		APT_IPA_CMSHelper.set(IPACMS);
		
		
	}
	
	@org.testng.annotations.BeforeSuite
	public void BeforeSuite() 
	{
		itr = 0;
		DOMConfigurator.configure("log4j.xml");
	}

	@AfterClass
	public void Teardown() 
	{
//		WebDriver dr = WEB_DRIVER_THREAD_LOCAL.get();
//		 dr.quit();
	}

	public static WebDriver getwebdriver() 
	{
		WebDriver dr = WEB_DRIVER_THREAD_LOCAL.get();
		return dr;
	}

	
	@org.testng.annotations.Parameters({ "test-name" })
	@BeforeTest
	// @org.testng.annotations.Parameters("browser")
	public void startReport() 
	{

//		String dateName1 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//
//		extent = new ExtentReports(
//				System.getProperty("user.dir") + "/ExtentReports/" + "SS_ExtentReport-" + dateName1 + ".html", true);
//		extent.addSystemInfo("Host Name", "APT_QA_Colt").addSystemInfo("Environment", "QA").addSystemInfo("User Name",
//				"Sai12345");
	}

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception 
	{
		String dateName2 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + "-"
				+ dateName2 + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}



	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is : " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is : " + result.getThrowable());
			String base64ScreenshotPath = "data:image/png;base64,"
					+ ((TakesScreenshot) getwebdriver()).getScreenshotAs(OutputType.BASE64);
			logger.log(LogStatus.FAIL, logger.addScreenCapture(base64ScreenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is :" + result.getName());
		}
		
		WebDriver dr = WEB_DRIVER_THREAD_LOCAL.get();
		dr.quit();
		 
	}

	@AfterTest
	public void endReport() 
	{
		ExtentManager.getReporter().flush();
	}

	
	
	@AfterSuite
	public void closeReport() 
	{
		ExtentManager.getReporter().flush();
		
		ExtentTestManager.endAllTest();
		
		ExtentManager.getReporter().close();


	}

	

}
