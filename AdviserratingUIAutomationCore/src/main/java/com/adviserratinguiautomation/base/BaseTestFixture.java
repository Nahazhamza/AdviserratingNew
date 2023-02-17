package com.adviserratinguiautomation.base;

import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.InvalidInputException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.WebDriverInstanceNullException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * All tests derive from this class
 */
public abstract class BaseTestFixture extends BaseTestScripts {

//	 Logger
	static {
		System.setProperty("log4jOutputFile",
				"../AdviserratingUIAutomationTest/testResult/" + logProjectName + "/log/AdviserratingLog.log");
	}
	final static Logger log = Logger.getLogger(BaseTestFixture.class);

	// Browser Constants
	public static final String Chrome = "Chrome";
	public static final String InternetExplorer = "Internet Explorer";
	public static final String Safari = "Safari";
	public static final String FireFox = "FireFox";
	public static final String Edge = "Edge";

	public void waitForElement(By by, WebDriver seleniumWebdriver) throws ResourceCustomException, IOException {
		log.info("Entered the waitForElement method in BaseTestFixture");
		boolean isElementDisplayedInPage = false;
		isElementDisplayedInPage = isElementPresent(by, getElementFindTimeOut(), seleniumWebdriver);
		if (isElementDisplayedInPage) {
			log.info("Element found, value of isElementDisplayedInPage is = " + isElementDisplayedInPage);
			seleniumWebdriver.findElement(by);

		}
		log.info("Exited the waitForElement method in BaseTestFixture");
	}

	// throw the exception when it is needed at the page object model, this function
	// can be reused for boolean check also
	public boolean isElementPresent(By by, int timeOut, WebDriver seleniumWebDriver)
			throws ResourceCustomException, IOException {
		log.info("Entered the isElementPresent method in BaseTestFixture");
		boolean elementPresent = false;
		WebDriverWait webDriverWait = CreateWebDriverWait(timeOut, seleniumWebDriver);
		if (webDriverWait != null) {
			try {
				webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
				elementPresent = true;
			} catch (NoSuchElementException | TimeoutException ex) {
				elementPresent = false;
			}
		}
		log.info("Exited the isElementPresent method in BaseTestFixture");
		return elementPresent;
	}

	public boolean isElementPresent(WebElement element, int timeOut, WebDriver seleniumWebDriver)
			throws ResourceCustomException, IOException {
		log.info("Entered the isElementPresent method in BaseTestFixture");
		boolean elementPresent = false;
		WebDriverWait webDriverWait = CreateWebDriverWait(timeOut, seleniumWebDriver);
		if (webDriverWait != null) {
			try {
				webDriverWait.until(ExpectedConditions.visibilityOf(element));
				elementPresent = true;
			} catch (NoSuchElementException ex) {
				elementPresent = false;
			} catch (TimeoutException ex) {
				elementPresent = false;
			}

		}
		log.info("Exited the isElementPresent method in BaseTestFixture");
		return elementPresent;
	}

	public WebDriverWait CreateWebDriverWait(int timeOut, WebDriver seleniumWebDriver)
			throws ResourceCustomException, IOException {
		if (timeOut == -1) {
			timeOut = getElementFindTimeOut();
		}
		return new WebDriverWait(seleniumWebDriver, timeOut);
	}

	/**
	 * @param value
	 * @return boolean This method checks if the constant value contains value or
	 *         not
	 */
	public static boolean isConstantValuePresent(String value) throws InvalidInputException {
		log.info("Entered the isConstantValuePresent method in BaseTestFixture");
		boolean isValuePresent = false;
		if (!value.isEmpty() && value != null)
			isValuePresent = true;
		else
			throw new InvalidInputException("Input value is : " + value);
		log.info("Exited the isConstantValuePresent method in BaseTestFixture");
		return isValuePresent;
	}

	/**
	 * @param seleniumWebDriver
	 * @return
	 * @throws WebDriverInstanceNullException This method checks if the web driver
	 *                                        instance is null or not
	 */
	public static boolean isWebDriverInstancePresent(WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException {
		log.info("Entered the isWebDriverInstantPresent method in BaseTestFixture");
		boolean isWebDriverPresent = false;
		if (seleniumWebDriver != null) {
			isWebDriverPresent = true;
		} else {
			throw new WebDriverInstanceNullException("WebDriver instance is null");
		}
		log.info("Exited the isWebDriverInstantPresent method in BaseTestFixture");
		return isWebDriverPresent;
	}

	/**
	 * @param filename
	 * @return
	 * @throws Exception This function creates a hash tag based on MD5 instance for
	 *                   a file and can be used to for comparing especially images
	 *                   even though the content is changes but having same source
	 *                   name
	 */
	public static byte[] createChecksum(String filename) throws Exception {
		log.info("Entered the createChecksum method in BaseTestFixture");
		InputStream fis = new FileInputStream(filename);
		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);
		fis.close();
		log.info("Exited the createChecksum method in BaseTestFixture");
		return complete.digest();
	}

	/**
	 * @param filename
	 * @return
	 * @throws Exception This function converts a byte array to a HEX string so that
	 *                   it can be comparable and can be used for comparing the
	 *                   image having same file name but different immage.
	 */
	public static String getMD5Checksum(String filename) throws Exception {
		log.info("Entered the getMD5Checksum method in BaseTestFixture");
		byte[] bytes = createChecksum(filename);
		String result = "";
		for (int i = 0; i < bytes.length; i++) {
			result += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
		}
		log.info("Exited the getMD5Checksum method in BaseTestFixture");
		return result;
	}

	/**
	 * @param cookies
	 * @param headers
	 * @param urlToDownload
	 * @param outputPath
	 * @param filename
	 * @throws IOException This method is used to download file from the webpage
	 *                     using restassure
	 */
	public void downloadFileUsingRestAssured(final Map<String, String> cookies, final Map<String, String> headers,
			final String urlToDownload, final File outputPath, final String filename) throws IOException {
		log.info("Entered the downloadFileUsingRestAssured method in BaseTestFixture");
		File outputFile = new File(outputPath.getPath(), filename);
		final Response response = RestAssured.given().headers(headers).cookies(cookies).when().get(urlToDownload)
				.andReturn();
		if (response.getStatusCode() == 200) {
			if (outputFile.exists()) {
				outputFile.delete();
			}
			log.info("Downloaded an " + response.getHeader("Content-Type"));
			byte[] fileContents = response.getBody().asByteArray();
			OutputStream outStream = null;
			try {
				outStream = new FileOutputStream(outputFile);
				outStream.write(fileContents);
			} catch (Exception e) {
				System.out.println("Error writing file " + outputFile.getAbsolutePath());
				new ExceptionHandeler().genricExceptionHandeler(e);
			} finally {
				if (outStream != null) {
					outStream.close();
				}
			}
		}
		log.info("Exited the downloadFileUsingRestAssured method in BaseTestFixture");
	}

	/**
	 * @param expectedCondition
	 * @param seleniumWebDriver
	 * @return
	 */
	public boolean isExpectedCondition(ExpectedCondition expectedCondition, WebDriver seleniumWebDriver) {
		Object value = expectedCondition.apply(seleniumWebDriver);
		if (value == null) {
			return false;
		}

		if (value.getClass() == Boolean.class) {
			return (boolean) value;
		} else {
			return true;
		}
	}

	/**
	 * @param seleniumWebDriver
	 * @return
	 * @throws InterruptedException
	 */
	public boolean isPageLoaded(WebDriver seleniumWebDriver) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) seleniumWebDriver;
		for (int i = 0; i < 25; i++) {
			// To check page ready state.
			if (jse.executeScript("return document.readyState").toString().equals("complete")) {
				return true;

			} else {
				// Sleep for 1 second
				Thread.sleep(1000);
			}
		}
		return false;
	}

	/**
	 * @param expectedCondition
	 * @param seleniumWebDriver
	 * @return
	 */

	public boolean isElementClickable(WebDriver seleniumwebDriver, String elem) {
		try {
			WebDriverWait wait = new WebDriverWait(seleniumwebDriver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(elem)));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @param expectedCondition
	 * @param seleniumWebDriver
	 * @return
	 */

	public boolean isElementEnabled(By by, WebDriver seleniumwebDriver, String elem) {

		return seleniumwebDriver.findElement(by).isEnabled();
	}

	/**
	 * @param webdriver instance
	 * 
	 * @return
	 */

	// Wait for the page to Load
	public void waitForJStoLoad(WebDriver seleniumwebDriver) {

		WebDriverWait wait = new WebDriverWait(seleniumwebDriver, Duration.ofSeconds(60));

		JavascriptExecutor js = (JavascriptExecutor) seleniumwebDriver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) js.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		wait.until(jQueryLoad);
		wait.until(jsLoad);

	}
}