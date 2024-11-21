package com.gtpl.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gtpl.base.CommonActions;

public class NewCustomerPage extends CommonActions {

	WebDriver driver;

	public NewCustomerPage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//a[@href='addcustomerpage.php']")
	WebElement linkNewCustomerPage;

	@FindBy(xpath = "//p[@class='heading3' and text()='Add New Customer']")
	WebElement textAddNewCustomer;

	// Click new customer page and verify if page is selected
	public void verifyNewCustomerPage() {
		try {
			click(driver, linkNewCustomerPage, 10);
			softAssert.assertTrue(textAddNewCustomer.isDisplayed(), "Failed to enter add new customer page");
			log("Verified presence of new customer page, working as expected");
		} catch (Exception e) {
			log("Failed to enter add new customer page, An error occurred: " + e.getMessage());
			softAssert.fail();
		}
	}
}
