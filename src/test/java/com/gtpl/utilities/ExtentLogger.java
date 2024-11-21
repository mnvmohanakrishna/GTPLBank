package com.gtpl.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.gtpl.base.BaseClass;

public final class ExtentLogger extends BaseClass {

	private ExtentLogger() {
		// Private constructor to prevent instantiation
	}

	// Logs a pass message to the extent report.
	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}

	// Logs a fail message to the extent report along with a screenshot
	public static void fail(String message) throws IOException {
		ExtentManager.getExtentTest().fail(message,
				MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath()).build());
	}

	// Logs a skip message to the extent report.
	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}

	// Logs an info message to the extent report
	public static void info(String message) {
		ExtentManager.getExtentTest().info(message);
	}

	// Captures a screenshot and returns the path where it is saved.
	public static String getScreenShotPath() throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "failed-" + timeStamp + ".png";
		String screenshotpath = System.getProperty("user.dir") + "/screenshots/" + repName;
		FileUtils.copyFile(source, new File(screenshotpath));
		return screenshotpath;
	}
}
