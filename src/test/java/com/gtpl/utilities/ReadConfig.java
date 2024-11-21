package com.gtpl.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.gtpl.base.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadConfig extends BaseClass {
	Properties properties;

	// Constructor to read and load the config.properties file.
	public ReadConfig() {
		try {
			properties = new Properties();
			FileInputStream inputFile = new FileInputStream(
					System.getProperty("user.dir") + "/configurations/config.properties");
			properties.load(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Invokes the browser specified in the config.properties file.
	public void invokeBrowser() {
		String browser = properties.getProperty("Browser");
		if (browser == null) {
			log.error("Browser not specified!");
			throw new IllegalArgumentException("Browser property not specified!");
		}
		switch (browser.toLowerCase()) {
		case "edge":
			//driver = WebDriverManager.edgedriver().create();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("headless");
			options.addArguments("disable-gpu");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
			break;
		case "chrome":
			driver = WebDriverManager.chromedriver().create();
		case "firefox":
			driver = WebDriverManager.firefoxdriver().create();
			break;
		default:
			log.error("Invalid browser specified: " + browser);
			throw new IllegalArgumentException("Invalid browser specified: " + browser);
		}
	}

	// Get application URL specified in the config.properties file
	public void getApplicationUrl() {
		String environment = properties.getProperty("Environment").toLowerCase();
		String url = null;

		switch (environment) {
		case "staging":
			url = properties.getProperty("stg");
			break;
		case "production":
			url = properties.getProperty("prod");
			break;
		default:
			log.error("Environment not recognized: " + environment);
			throw new IllegalArgumentException("Environment not recognized: " + environment);
		}

		if (url != null) {
			driver.get(url);
		}
	}

}
