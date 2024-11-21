package com.gtpl.utilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
	private ExtentManager() {
		// Private constructor to prevent instantiation
	}

	private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

	/**
	 * Retrieves the current extent test instance. 
	 * Default access modifier - can only be accessed within the package. 
	 * Return the current extent test instance.
	 */
	static ExtentTest getExtentTest() {
		return extTest.get();
	}

	// Sets the extent test instance for the current thread
	static void setExtentTest(ExtentTest test) {
		extTest.set(test);
	}

	// Removes the extent test instance for the current thread
	static void unload() {
		extTest.remove();
	}
}
