package com.gtpl.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gtpl.base.CommonActions;

public class DeleteCustomerPage extends CommonActions {

	WebDriver driver;

	public DeleteCustomerPage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//a[@href='DeleteCustomerInput.php']")
	WebElement linkDeleteCustomerPage;

	@FindBy(xpath = "//p[@class='heading3' and text()='Delete Customer Form']")
	WebElement textDeleteCustomerForm;

	// Click delete customer page and verify if page is selected
	public void verifyDeleteCustomerPage() {
		try {
			click(driver, linkDeleteCustomerPage, 10);
			softAssert.assertTrue(textDeleteCustomerForm.isDisplayed(), "Failed to enter delete customer page");
			log("Verified presence of delete customer page, working as expected");
		} catch (Exception e) {
			log("Failed to enter delete customer page, An error occurred: " + e.getMessage());
			softAssert.fail();
		}
	}
}
