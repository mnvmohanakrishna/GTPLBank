package com.gtpl.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gtpl.base.CommonActions;

public class MiniStatementPage extends CommonActions {

	WebDriver driver;

	public MiniStatementPage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//a[@href='MiniStatementInput.php']")
	WebElement linkMiniStatementPage;

	@FindBy(xpath = "//p[@class='heading3' and text()='Mini Statement Form']")
	WebElement textMiniStatementForm;

	// Click mini statement page and verify if page is selected
	public void verifyMiniStatementPage() {
		try {
			click(driver, linkMiniStatementPage, 10);
			softAssert.assertTrue(textMiniStatementForm.isDisplayed(), "Failed to enter mini statement page");
			log("Verified presence of mini statement page, working as expected");
		} catch (Exception e) {
			log("Failed to enter mini statement page, An error occurred: " + e.getMessage());
			softAssert.fail();
		}
	}
}
