package com.gtpl.base;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.gtpl.pageObjects.CustomisedStatementPage;
import com.gtpl.pageObjects.DeleteAccountPage;
import com.gtpl.pageObjects.DeleteCustomerPage;
import com.gtpl.pageObjects.EditAccountPage;
import com.gtpl.pageObjects.EditCustomerPage;
import com.gtpl.pageObjects.HomePage;
import com.gtpl.pageObjects.ManagerPage;
import com.gtpl.pageObjects.MiniStatementPage;
import com.gtpl.pageObjects.NewAccountPage;
import com.gtpl.pageObjects.NewCustomerPage;
import com.gtpl.utilities.ExcelUtilities;
import com.gtpl.utilities.ReadConfig;

public class BaseClass {
	public static WebDriver driver;
	public static Logger log = Logger.getLogger("GTPL Bank");
	public static String resultString = "";
	public static ReadConfig readConfig;
	public static CommonActions commonActions;
	public static ExcelUtilities excel;
	public static SoftAssert softAssert;
	public static String excelPath = System.getProperty("user.dir")
			+ "/src/test/java/com/gtpl/testData/RequiredData.xlsx";
	public static HomePage homePage;
	public static ManagerPage managerPage;
	public static NewCustomerPage newcustomerPage;
	public static EditCustomerPage editcustomerPage;
	public static DeleteCustomerPage deletecustomerPage;
	public static NewAccountPage newaccountPage;
	public static EditAccountPage editaccountPage;
	public static DeleteAccountPage deleteaccountPage;
	public static MiniStatementPage ministatementPage;
	public static CustomisedStatementPage customisedstatementPage;

	// Loads the configuration settings from the log4j properties file before the
	// suite starts.
	@BeforeSuite
	public void loadConfig() throws IOException {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/configurations/log4j.properties");
	}

	// Initializes the page objects and utility classes before each test.
	@BeforeTest
	public void invokePageObjects() throws IOException {
		readConfig = new ReadConfig();
		commonActions = new CommonActions();
		excel = new ExcelUtilities();
		softAssert = new SoftAssert();
		homePage = new HomePage(driver);
		managerPage = new ManagerPage(driver);
		newcustomerPage = new NewCustomerPage(driver);
		editcustomerPage = new EditCustomerPage(driver);
		deletecustomerPage = new DeleteCustomerPage(driver);
		newaccountPage = new NewAccountPage(driver);
		editaccountPage = new EditAccountPage(driver);
		deleteaccountPage = new DeleteAccountPage(driver);
		ministatementPage = new MiniStatementPage(driver);
		customisedstatementPage = new CustomisedStatementPage(driver);
	}

	// Launches the application by initializing
	public void launchApplication() throws IOException {
		readConfig.invokeBrowser(); // Launch the preferred browser
		driver.manage().window().maximize(); // Maximize the browser window
		driver.manage().deleteAllCookies(); // Delete all the cookies
		commonActions.implicitWait(driver, 10); // Apply implicit wait timeout
		commonActions.pageLoadTimeOut(driver, 120); // Apply page load timeout
		readConfig.getApplicationUrl(); // Enter the application url
		invokePageObjects(); // Call pageobjects method
	}

	// Closes all browser windows and ends the webdriver session
	public void closeApplication() {
		driver.quit();
	}
}
