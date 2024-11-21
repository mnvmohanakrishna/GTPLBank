package com.gtpl.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int counter = 0;
	int retryLimit = 2;

	// Attempt retry execution for the failure and skipped
	public boolean retry(ITestResult result) {
		if (counter < retryLimit && (result.getStatus() == 2 || result.getStatus() == 3)) {
			counter++;
			return true;
		}
		return false;
	}
}
