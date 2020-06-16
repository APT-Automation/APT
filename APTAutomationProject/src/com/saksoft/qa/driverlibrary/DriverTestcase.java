package com.saksoft.qa.driverlibrary;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.scripthelpers.APT_LoginHelper;

import com.saksoft.qa.scripthelpers.APT_AutomationHelper;
import com.saksoft.qa.scripthelpers.APT_NGINHelper;
import com.saksoft.qa.scripthelpers.APT_DomainManagementHelper;
import com.saksoft.qa.scripthelpers.APT_MSPLatencyHelper;
import com.saksoft.qa.scripthelpers.APT_ManageNetworkHelper;
import com.saksoft.qa.scripthelpers.Hss_Helper;
import com.saksoft.qa.scripthelpers.ImsNmbrTranslator_Helper;

public class DriverTestcase {

	public static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();
	// public static final ThreadLocal<RemoteWebDriver> WEB_DRIVER_THREAD_LOCAL =
	// new InheritableThreadLocal<>();

	public static final ThreadLocal<APT_LoginHelper> APTLogin = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_AutomationHelper> APT_Helper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_NGINHelper> APT_NGIN = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_DomainManagementHelper> APT_DomainManageHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_MSPLatencyHelper> APT_MSPLatencyHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<APT_ManageNetworkHelper> APT_ManageNetworkHelper = new InheritableThreadLocal<>();
	public static final ThreadLocal<Hss_Helper> Hss = new InheritableThreadLocal<>();
	public static final ThreadLocal<ImsNmbrTranslator_Helper> ImsNmbrTranslator_Helper = new InheritableThreadLocal<>();

	public static com.saksoft.qa.listeners.TestListener Testlistener;
	public ThreadLocal<String> TestName = new ThreadLocal();
	public static SessionId session_id;
	public static ChromeDriver driver;
	public static int itr;

	public static ExtentReports extent;
	public static ExtentTest logger;
	public WebDriver dr = null;

	@org.testng.annotations.BeforeSuite
	public void BeforeSuite() throws Exception {
		itr = 0;
		DOMConfigurator.configure("log4j.xml"); // For log
		Log.clearFile("E:\\APTSaiWorkspace\\APT_Automation_NGIN\\Logs\\logfile.log");

		// Open Browser
		System.out.println("Method started");

		PropertyReader pr = new PropertyReader();
		String targatedbrowser = pr.readproperty("browser");
		String url = pr.readproperty("URL");
		Log.info("URL");
		if (targatedbrowser.equals("chrome")) {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			Map<String, Object> prefs = new HashMap<String, Object>();
			// Set the notification setting it will override the default setting
			prefs.put("profile.default_content_setting_values.notifications", 2);

			// Create object of ChromeOption class
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			dr = new ChromeDriver(capabilities);
		} else if (targatedbrowser.equalsIgnoreCase("firefox")) {
			Log.info("For FF inprogress");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			Map<String, Object> prefs = new HashMap<String, Object>();
			// Set the notification setting it will override the default setting
			prefs.put("profile.default_content_setting_values.notifications", 2);
			// Create object of FirefoxOptions class
			FirefoxOptions options2 = new FirefoxOptions();
//				options2.setExperimentalOption("prefs", prefs);
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
//				capabilities.setCapability(FirefoxOptions.CAPABILITY, options2);
			System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
			dr = new FirefoxDriver(capabilities);
		} else if (targatedbrowser.equalsIgnoreCase("ie")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			Map<String, Object> prefs = new HashMap<String, Object>();
			// Set the notification setting it will override the default setting
			prefs.put("profile.default_content_setting_values.notifications", 2);
			// Create object of ieOptions class
			InternetExplorerOptions options3 = new InternetExplorerOptions();
			// options3.ignoreZoomSettings();
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
			// capabilities.setCapability(InternetExplorerOptions.CAPABILITY, options3);
			System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
			dr = new InternetExplorerDriver(capabilities);
		} else {
			Log.info("For MS Edge is in progress");
		}

		dr.manage().window().maximize();
		WEB_DRIVER_THREAD_LOCAL.set(dr);
		dr.manage().deleteAllCookies();
		Thread.sleep(3000);

		/**
		 * For APT projects
		 */
		APT_LoginHelper apt = new APT_LoginHelper(getwebdriver());
		APTLogin.set(apt);
		APT_NGINHelper ngin = new APT_NGINHelper(getwebdriver());
		APT_NGIN.set(ngin);
		APT_AutomationHelper aptautomation = new APT_AutomationHelper(getwebdriver());
		APT_Helper.set(aptautomation);
		APT_DomainManagementHelper DM = new APT_DomainManagementHelper(getwebdriver());
		APT_DomainManageHelper.set(DM);
		APT_MSPLatencyHelper msplatency = new APT_MSPLatencyHelper(getwebdriver());
		APT_MSPLatencyHelper.set(msplatency);
		APT_ManageNetworkHelper managenetwork = new APT_ManageNetworkHelper(getwebdriver());
		APT_ManageNetworkHelper.set(managenetwork);
		Hss_Helper Hs = new Hss_Helper(getwebdriver());
		Hss.set(Hs);
		ImsNmbrTranslator_Helper imnt = new ImsNmbrTranslator_Helper(getwebdriver());
		ImsNmbrTranslator_Helper.set(imnt);

		// APT_Login aptLogin=new APT_Login();
		// aptLogin.APT_Login_1();

		// OR
		apt.Login("APT_login_1");

		System.out.println("Method started");
	}

	@org.testng.annotations.Parameters({ "test-name" })
	@BeforeTest
	// @org.testng.annotations.Parameters("browser")
	public void startReport() {

		String dateName1 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		// E:\Sai Workspace\APT_Automation_NGIN
		// extent = new ExtentReports
		// ("C:/Automation/ExtentReports/SS_ExtentReport-"+dateName1+".html", true);
		extent = new ExtentReports(
				System.getProperty("user.dir") + "/ExtentReports/" + "SS_ExtentReport-" + dateName1 + ".html", true);
		extent.addSystemInfo("Host Name", "APT_QA_Colt").addSystemInfo("Environment", "QA").addSystemInfo("User Name",
				"Sai12345");
	}

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName2 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + "-"
				+ dateName2 + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@BeforeClass
	public void setup() throws Exception {

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
	}

	@AfterClass
	public void Teardown() {
		// dr.close();
	}

	public static WebDriver getwebdriver() {
		WebDriver dr = WEB_DRIVER_THREAD_LOCAL.get();
		return dr;
	}

	@AfterTest
	public void endReport() {
		extent.endTest(logger);
		extent.flush();

		// extent.close();

	}

	@BeforeMethod
	public void BeforeMethod(Method method, ITestContext ctx, Object[] data) throws IOException, Exception {
		// setup();

		Object[] st = null;

		try

		{
			st = (Object[]) data[0];
		} catch (Exception e) {
			st = new Object[][] { { "" } };
		}

	}

}