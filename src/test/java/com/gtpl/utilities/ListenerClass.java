package com.gtpl.utilities;

import java.io.IOException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener, ISuiteListener {

	// Triggered before the suite starts.
	public void onStart(ISuite suite) {
		try {
			ExtentReport.setExtentReports();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Triggered after the suite finishes.
	public void onFinish(ISuite suite) {
		try {
			ExtentReport.flushExtentReports();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Triggered when a test starts.
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());
	}

	// Triggered when a test is successful.
	public void onTestSuccess(ITestResult result) {
		// ExtentLogger.info(BaseClass.resultString);
		ExtentLogger.pass(result.getMethod().getMethodName() + " is passed");

	}

	// Triggered when a test fails.
	public void onTestFailure(ITestResult result) {
		try {
			// ExtentLogger.info(BaseClass.resultString);
			ExtentLogger.fail(result.getMethod().getMethodName() + " is failed");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Triggered when a test is skipped.
	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped");
	}
}
