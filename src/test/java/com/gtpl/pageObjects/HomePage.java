package com.gtpl.pageObjects;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.gtpl.base.CommonActions;
import com.gtpl.utilities.ExcelUtilities;

public class HomePage extends CommonActions {

	WebDriver driver;

	public HomePage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//input[@name='uid']")
	WebElement inputUserName;

	@FindBy(xpath = "//input[@name='password']")
	WebElement inputPassword;

	@FindBy(xpath = "//input[@name='btnLogin']")
	WebElement buttonLogin;

	@FindBy(xpath = "//h2[@class='barone' and contains(text(),'Gtpl Bank')]")
	WebElement textGtplBank;

	@FindBy(xpath = "//a[@href='Logout.php']")
	WebElement linkLogout;

	// Get login user name credentials from excel data
	public String loginUserName() throws IOException {
		String loginUserName = ExcelUtilities.getCellData(excelPath, "Credentials", 1, 0);
		return loginUserName;
	}

	// Get login password credentials from excel data
	public String loginPassword() throws IOException {
		String loginPassword = ExcelUtilities.getCellData(excelPath, "Credentials", 1, 1);
		return loginPassword;
	}

	// Perform login application and verify if login is success / failed
	public void login() {
		try {
			Assert.assertTrue(sendKeys(inputUserName, loginUserName()), "Username input failed");
			Assert.assertTrue(sendKeys(inputPassword, loginPassword()), "Password input failed");
			click(driver, buttonLogin, 10);
			Assert.assertTrue(textGtplBank.isDisplayed(), "GTPL Bank internet banking login failed");
			log("GTPL Bank internet banking login is successfull in " + getEnviromentName()
					+ " environment on " + getBrowserName() + " browser");
		} catch (Exception e) {
			log("GTPL Bank internet banking login failed, An error occurred: " + e.getMessage());
			Assert.fail();
		}
	}

	// Perform logout application and verify if logout is success / failed
	public void logout() {
		try {
			click(driver, linkLogout, 10);
			getCurrentUrl();
		} catch (Exception e) {
			boolean isLoggedOut = e.getMessage().contains("Logged");
			Assert.assertTrue(isLoggedOut, "Application logout failed");
			log("Application logout " + (isLoggedOut ? "successful" : "failed"));
		}
	}
}
