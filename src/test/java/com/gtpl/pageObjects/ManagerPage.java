package com.gtpl.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gtpl.base.CommonActions;

public class ManagerPage extends CommonActions {

	WebDriver driver;

	public ManagerPage(WebDriver driver) // Constructor to point webdriver when ever we call
	{
		this.driver = driver; // Map webriver to objects location
		PageFactory.initElements(driver, this); // Initialize webelements declared in the page class
	}

	// LOCATORS
	@FindBy(xpath = "//a[@href='Managerhomepage.php']")
	WebElement linkManagerPage;

	@FindBy(xpath = "//img[@src='../images/1.gif']")
	WebElement imageManagerPage;

	// Click manager page and verify if page is selected
	public void verifyManagerPage() {
		try {
			click(driver, linkManagerPage, 10);
			softAssert.assertTrue(imageManagerPage.isDisplayed(), "Failed to enter manager page");
			log("Verified presence of manager page, working as expected");
		} catch (Exception e) {
			log("Failed to enter manager page, An error occurred: " + e.getMessage());
			softAssert.fail();
		}
	}
}
