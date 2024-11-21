package com.gtpl.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gtpl.base.CommonActions;

public class EditAccountPage extends CommonActions {

	WebDriver driver;

	public EditAccountPage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//a[@href='editAccount.php']")
	WebElement linkEditAccountPage;

	@FindBy(xpath = "//p[@class='heading3' and text()='Edit Account Form']")
	WebElement textEditAccountForm;

	// Click edit account page and verify if page is selected
	public void verifyEditAccountPage() {
		try {
			click(driver, linkEditAccountPage, 10);
			softAssert.assertTrue(textEditAccountForm.isDisplayed(), "Failed to enter edit account page");
			log("Verified presence of edit account page, working as expected");
		} catch (Exception e) {
			log("Failed to enter edit account page, An error occurred: " + e.getMessage());
			softAssert.fail();
		}
	}
}
