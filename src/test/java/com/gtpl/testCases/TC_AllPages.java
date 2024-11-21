package com.gtpl.testCases;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.gtpl.base.CommonActions;

public class TC_AllPages extends CommonActions {

	@BeforeMethod
	public void initialise() throws IOException {
		launchApplication();
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		closeApplication();
	}

	// Test1, Verify presence of all pages in the application
	@Test(priority = 1)
	public void verifyPresenceOfAllPagesInTheApplication() {
		log("Initiated verification for presence of all pages in the application");
		homePage.login();
		managerPage.verifyManagerPage();
		newcustomerPage.verifyNewCustomerPage();
		editcustomerPage.verifyEditCustomerPage();
		deletecustomerPage.verifyDeleteCustomerPage();
		newaccountPage.verifyNewAccountPage();
		editaccountPage.verifyEditAccountPage();
		deleteaccountPage.verifyDeleteAccountPage();
		ministatementPage.verifyMiniStatementPage();
		customisedstatementPage.verifyCustomisedStatementPage();
		softAssert.assertAll();
		homePage.logout();
		log("Completed verification for presence of all pages in the application");
	}

}
