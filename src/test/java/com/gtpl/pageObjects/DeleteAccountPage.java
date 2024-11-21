package com.gtpl.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gtpl.base.CommonActions;

public class DeleteAccountPage extends CommonActions {

	WebDriver driver;

	public DeleteAccountPage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//a[@href='deleteAccountInput.php']")
	WebElement linkDeleteAccountPage;

	@FindBy(xpath = "//p[@class='heading3' and text()='Delete Account Form']")
	WebElement textDeleteAccountForm;

	// Click delete account page and verify if page is selected
	public void verifyDeleteAccountPage() {
		try {
			click(driver, linkDeleteAccountPage, 10);
			softAssert.assertTrue(textDeleteAccountForm.isDisplayed(), "Failed to enter delete account page");
			log("Verified presence of delete account page, working as expected");
		} catch (Exception e) {
			log("Failed to enter delete account page, An error occurred: " + e.getMessage());
			softAssert.fail();
		}
	}
}
