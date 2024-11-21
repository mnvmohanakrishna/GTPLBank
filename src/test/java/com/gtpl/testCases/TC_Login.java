package com.gtpl.testCases;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.gtpl.base.CommonActions;

public class TC_Login extends CommonActions {

	@BeforeMethod
	public void initialise() throws IOException {
		launchApplication();
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		closeApplication();
	}

	// Test1, Verify application login and logout
	@Test(priority = 1)
	public void verifyApplicationLoginAndLogout() {
		log("Initiated verification for login and logout of application");
		homePage.login();
		homePage.logout();
		log("Completed verification for login and logout of application");
	}

}
