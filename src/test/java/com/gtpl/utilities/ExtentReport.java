package com.gtpl.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReport {

	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
	static String repName = "TestReport-" + timeStamp + ".html";

	private ExtentReport() {
		// Private constructor to prevent instantiation
	}

	public static ExtentReports extent;

	/**
	 * Initializes the extent reports with spark reporter configuration. Throws
	 * IOException if an input or output exception occurred.
	 */
	public static void setExtentReports() throws IOException {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);
			File configfile = new File(System.getProperty("user.dir") + "/configurations/extent-config.xml");
			spark.loadXMLConfig(configfile);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
			extent.setSystemInfo("User", System.getProperty("user.name"));
			extent.attachReporter(spark);
		}
	}

	// Creates a test entry in the Extent Report.
	public static void createTest(String testname) {
		ExtentManager.setExtentTest(extent.createTest(testname));
	}

	/**
	 * * Flushes the extent reports, writing all information to the report file.
	 * * @throws IOException if an input or output exception occurred.
	 */
	public static void flushExtentReports() throws IOException {
		if (Objects.nonNull(extent)) {
			extent.flush();
			Desktop.getDesktop().browse(new File(System.getProperty("user.dir") + "/Reports/" + repName).toURI());
		}
	}

}
