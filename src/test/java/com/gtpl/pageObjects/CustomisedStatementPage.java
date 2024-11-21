package com.gtpl.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gtpl.base.CommonActions;

public class CustomisedStatementPage extends CommonActions {

	WebDriver driver;

	public CustomisedStatementPage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//a[@href='CustomisedStatementInput.php']")
	WebElement linkCustomisedStatementPage;

	@FindBy(xpath = "//p[@class='heading3' and text()='Customized Statement Form']")
	WebElement textCustomisedStatementForm;

	// Click customised statement page and verify if page is selected
	public void verifyCustomisedStatementPage() {
		try {
			click(driver, linkCustomisedStatementPage, 10);
			softAssert.assertTrue(textCustomisedStatementForm.isDisplayed(),
					"Failed to enter customised statement page");
			log("Verified presence of customised statement page, working as expected");
		} catch (Exception e) {
			log("Failed to enter customised statement page, An error occurred: " + e.getMessage());
			softAssert.fail();
		}
	}
}
