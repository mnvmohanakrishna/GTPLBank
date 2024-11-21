package com.gtpl.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.gtpl.utilities.ExtentLogger;

public class CommonActions extends BaseClass {

	Actions actions;
	static Properties properties;

	// Apply implicity wait
	public void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}

	// Apply explicit wait for specific webelement
	public void explicitWait(WebDriver driver, WebElement webElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	// Apply fluent wait
	public void fluentWait(WebDriver driver, WebElement webElement, int timeOut) {
		Wait<WebDriver> wait = null;
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOf(webElement));
			webElement.click();
		} catch (Exception e) {
		}
	}

	// Custom wait using thread.sleep
	public void customWait(int milisec) throws InterruptedException {
		Thread.sleep(milisec);
	}

	// Apply page load timeout
	public void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
	}

	// Click webelement with explicity wait with out logs
	public void click(WebDriver driver, WebElement webElement, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(webElement));
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			new Actions(driver).moveToElement(webElement).click().perform();
		} catch (Exception e) {
			log("Error clicking element: " + e.getMessage());
		}
	}

	// Click webelement with explicity wait and generate logs
	public void clickL(WebDriver driver, WebElement webElement, int timeout, String log) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(webElement));
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			new Actions(driver).moveToElement(webElement).click().perform();
			log(log);
		} catch (Exception e) {
			log("Error clicking element: " + e.getMessage());
		}
	}

	// Perform click by applying explicit wait for specific webelement with retry
	// mechanism (3)
	public void clickRetry(WebDriver driver, WebElement webElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		boolean clicked = false;
		int attempts = 0;

		while (attempts < 3 && !clicked) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				webElement.click();
				clicked = true;
			} catch (Exception e) {
				attempts++;
				try {
					customWait(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Wait before retrying
			}
		}
		if (!clicked) {
			Assert.fail("Failed to click the New Account link after multiple attempts.");
		}
	}

	// Enter text in the input field with out logs
	public boolean sendKeys(WebElement webElement, String inputText) {
		try {
			Assert.assertTrue(webElement.isDisplayed(), webElement + " : Location Not found");
			webElement.clear();
			webElement.sendKeys(inputText);
			return true;
		} catch (Exception e) {
			log(e.getMessage());
			log("Input text not entered");
			return false;
		}
	}

	// Enter text in the input field and generate logs
	public boolean sendKeysL(WebElement webElement, String inputText, String log) {
		try {
			Assert.assertTrue(webElement.isDisplayed(), webElement + " : Location Not found");
			webElement.clear();
			webElement.sendKeys(inputText);
			log(log);
			return true;
		} catch (Exception e) {
			log(e.getMessage());
			log("Input text not entered");
			return false;
		}
	}

	// Scroll by visibility of specific webelement
	public void scrollByVisibilityOfElement(WebDriver driver, WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", webElement);
	}

	// Hide the overlay webelement using javascript:
	public void hideOverlayElement(WebDriver driver, WebElement webElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", webElement);
	}

	// Move to specific webelement
	public void moveToElement(WebDriver driver, WebElement webElement) {
		actions = new Actions(driver);
		actions.moveToElement(webElement).build().perform();
	}

	// Is displayed method with explicity wait
	public boolean isDisplayed(WebDriver driver, WebElement webElement, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.visibilityOf(webElement));
			Assert.assertTrue(webElement.isDisplayed(), "The webelement is not displayed");
			return true;
		} catch (Exception e) {
			log("Exception occurred: " + e.getMessage());
			return false;
		}
	}

	// Create and switch to new window tab
	public void switchNewWindow(String url) {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(url);
		log("Switched to new window and entered url");
	}

	// Windows handling (2)
	public void switchWindowDual(int windowNumber) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();
		// Get the current window handle
		String parentWindow = iterator.next();
		String childWindow = iterator.next();

		if (windowNumber == 1) {
			driver.switchTo().window(parentWindow);
		}
		if (windowNumber == 2) {
			driver.switchTo().window(childWindow);
		}
	}

	// Windows handling (3)
	public void switchWindowMultiple(int windowNumber) {
		// Get the list of window handles
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();
		// Get the current window handle
		String parentWindow = iterator.next();
		String childWindow1 = iterator.next();
		String childWindow2 = iterator.next();

		if (windowNumber == 1) {
			driver.switchTo().window(parentWindow);
		} else if (windowNumber == 2) {
			driver.switchTo().window(childWindow1);
		} else if (windowNumber == 3) {
			driver.switchTo().window(childWindow2);
		}
	}

	// Take screeshot
	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/screenshots/" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
	}

	// Generate log in both console and extent report
	public void log(String log) {
		BaseClass.log.info(log);
		ExtentLogger.info(log);
	}

	// Get current url and return
	public String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	// Get current date and time
	public String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String currentDateTime = dateFormat.format(date);
		return currentDateTime;
	}

	// Get browser name from config.properties file
	public String getBrowserName() {
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
		String browserName = properties.getProperty("Browser");
		return browserName;
	}

	// Get environment name from config.properties file
	public String getEnviromentName() {
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
		String environmentName = properties.getProperty("Environment");
		return environmentName;
	}

	// Validate ui values with excel values using soft assertion with out logs
	public void validateSoftAssertion(Object uiValue, Object excelValue) {
		log("Actual value is : " + uiValue + " and Expected value is : " + excelValue);
		softAssert.assertEquals(uiValue, excelValue,
				"Actual value is : " + uiValue + " and Expected value is : " + excelValue);
	}

	// Validate ui values with excel values using soft assertion with logs
	public void validateSoftAssertionL(Object uiValue, Object excelValue, String log) {
		commonActions.log(log);
		softAssert.assertEquals(uiValue, excelValue,
				"Actual value is : " + uiValue + " and Expected value is : " + excelValue);
	}

}
