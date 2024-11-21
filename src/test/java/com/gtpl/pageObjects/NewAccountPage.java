package com.gtpl.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gtpl.base.CommonActions;

public class NewAccountPage extends CommonActions {

	WebDriver driver;

	public NewAccountPage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//p[@align='right']")
	WebElement elementOverLay;

	@FindBy(xpath = "//ul[@class='menusubnav']//li//a[@href='addAccount.php']//parent::li")
	WebElement linkNewAccountPage;

	@FindBy(xpath = "//p[@class='heading3' and text()='Add new account form']")
	WebElement textAddNewAccountForm;

	// Click new account page and verify if page is selected
	public void verifyNewAccountPage() {
		try {
			hideOverlayElement(driver, elementOverLay);
			click(driver, linkNewAccountPage, 10);
			explicitWait(driver, textAddNewAccountForm, 10);
			softAssert.assertTrue(textAddNewAccountForm.isDisplayed(), "Failed to enter new account page");
			log("Verified presence of new account page, working as expected");
		} catch (Exception e) {
			log("Failed to enter new account page, An error occurred: " + e.getMessage());
			softAssert.fail();
		}
	}
}
