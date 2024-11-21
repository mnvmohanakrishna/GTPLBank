package com.gtpl.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gtpl.base.CommonActions;

public class EditCustomerPage extends CommonActions {

	WebDriver driver;

	public EditCustomerPage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//a[@href='EditCustomer.php']")
	WebElement linkEditCustomerPage;

	@FindBy(xpath = "//p[@class='heading3' and text()='Edit Customer Form']")
	WebElement textEditCustomerForm;

	// Click edit customer page and verify if page is selected
	public void verifyEditCustomerPage() {
		try {
			click(driver, linkEditCustomerPage, 10);
			softAssert.assertTrue(textEditCustomerForm.isDisplayed(), "Failed to enter edit customer page");
			log("Verified presence of edit customer page, working as expected");
		} catch (Exception e) {
			log("Failed to enter edit customer page, An error occurred: " + e.getMessage());
			softAssert.fail();
		}
	}
}
