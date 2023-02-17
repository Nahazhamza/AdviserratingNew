package com.adviserratinguiautomation.browser;

import com.adviserratinguiautomation.base.BasePage;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.InvalidInputException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import org.apache.log4j.Logger;
import org.codehaus.plexus.util.Os;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.time.Duration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BrowserInstanceFactory extends BasePage {
	public static String HEADLESS_PROPERTY="Headless";
	final static Logger log = Logger.getLogger(BrowserInstanceFactory.class);

	/**
	 * @return return the instance of the web driver with the settings for Chrome
	 */
	public static WebDriver chromeWebDriver() {
		try {
			log.info("Entered the ChromeWebDriver method in BrowserInstanceFactory");

			String CHROME_DRIVERNAME_PROPERTY = "ChromeDriverName";
			String CHROME_WINDOWS_DRIVERPATH_PROPERTY = "ChromeDriverWindowsPath";
			String CHROME_LINUX_DRIVERPATH_PROPERTY = "ChromeDriverLinuxPath";
			String TIME_OUT_PROPERTY = "WebDriverTimeOutInSeconds";
			
			
			
			WebDriver seleniumWebdriver = null;
			ResourceRead resourceRead = new ResourceRead();
			final ChromeOptions chromeOptions = new ChromeOptions();
			String headlessValue = new ResourceRead().getResourceValueFromXML().getProperty(HEADLESS_PROPERTY);
			if(headlessValue.toLowerCase().contains("false"))	
			{
			chromeOptions.setHeadless(false);
			}
			
			else
				
			{
				System.out.println("Opening Chrome driver in Headless mode");
				log.info("Opening Chrome driver in Headless mode");
				chromeOptions.setHeadless(true);
			}
			
			chromeOptions.addArguments("no-sandbox");
			chromeOptions.addArguments("window-size=1920,1080");
			chromeOptions.addArguments("disable-gpu");
			chromeOptions.addArguments("disable-application-cache");
			chromeOptions.addArguments("disk-cache-size=0");
			chromeOptions.addArguments("disable-dev-shm-usage");
			
			Map<String, Integer> userPrefences = new HashMap<>();
			Map<String, Object> profile = new HashMap<>();
			Map<String, Object> prefs = new HashMap<>();
			userPrefences.put("media_stream", 1);
			profile.put("managed_default_content_settings", userPrefences);
			prefs.put("profile", profile);
			chromeOptions.setExperimentalOption("prefs", prefs);

			String osName = System.getProperty("os.name");
			if (osName.toLowerCase().contains("windows")) {
				System.out.println("osName: " + osName);
				System.out.println("ChromeDriverName: "
						+ resourceRead.getResourceValueFromXML().getProperty(CHROME_DRIVERNAME_PROPERTY));
				System.out.println("ChromeDriverName: "
						+ resourceRead.getResourceValueFromXML().getProperty(CHROME_WINDOWS_DRIVERPATH_PROPERTY));

				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(CHROME_DRIVERNAME_PROPERTY),
						resourceRead.getResourceValueFromXML().getProperty(CHROME_WINDOWS_DRIVERPATH_PROPERTY));
			} else {
				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(CHROME_DRIVERNAME_PROPERTY),
						resourceRead.getResourceValueFromXML().getProperty(CHROME_LINUX_DRIVERPATH_PROPERTY));
			}

				seleniumWebdriver = new ChromeDriver(chromeOptions);

				seleniumWebdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				
			log.info("Exited the ChromeWebDriver method in BrowserInstanceFactory");
			return seleniumWebdriver;
		} catch (IOException e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
			return null;
		} catch (ResourceCustomException e) {
			new ExceptionHandeler().resourceExceptionHandeler(e);
			return null;
		}
	}

	/**
	 * @return return the instance of the web driver with the settings for Firefox
	 */
	public static WebDriver firefoxWebDriver() {
		try {
			log.info("Entered the firefoxWebDriver method in BrowserInstanceFactory");
			String FIREFOX_DRIVERPATH_PROPERTY = "FireFoxDriverPath";
			String TIME_OUT_PROPERTY = "WebDriverTimeOutInSeconds";
			String FIREFOX_WINDOWS_DRIVERPATH_PROPERTY="FireFoxDriverWindowsPath";
			String FIREFOX_LINUX_DRIVERPATH_PROPERTY="FireFoxDriverLinuxPath";
			String FIREFOX_DRIVERNAME_PROPERTY="FireFoxDriverName";
			WebDriver seleniumWebdriver = null;
			ResourceRead resourceRead = new ResourceRead();
			

			final FirefoxOptions firefoxOptions = new FirefoxOptions();
			String headlessValue = new ResourceRead().getResourceValueFromXML().getProperty(HEADLESS_PROPERTY);
			if(headlessValue.toLowerCase().contains("false"))	
			{
				firefoxOptions.setHeadless(false);
			}
			
			else
				
			{
				System.out.println("Opening FireFox driver in Headless mode");
				log.info("Opening FireFox driver in Headless mode");
				firefoxOptions.setHeadless(true);
			}
			

			firefoxOptions.setCapability("marionette", true);
			Map<String, Integer> userPrefences = new HashMap<>();
			Map<String, Object> profile = new HashMap<>();
			Map<String, Object> prefs = new HashMap<>();
			userPrefences.put("media_stream", 1);
			profile.put("managed_default_content_settings", userPrefences);
			prefs.put("profile", profile);


			String osName = System.getProperty("os.name");
			if (osName.toLowerCase().contains("windows")) {
				System.out.println("osName: " + osName);
				System.out.println("GeckoDriverName: "
						+ resourceRead.getResourceValueFromXML().getProperty(FIREFOX_DRIVERNAME_PROPERTY));
				System.out.println("GeckoDriverName: "
						+ resourceRead.getResourceValueFromXML().getProperty(FIREFOX_WINDOWS_DRIVERPATH_PROPERTY));

				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(FIREFOX_DRIVERNAME_PROPERTY),
						resourceRead.getResourceValueFromXML().getProperty(FIREFOX_WINDOWS_DRIVERPATH_PROPERTY));
			} else {
				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(FIREFOX_DRIVERNAME_PROPERTY),
						resourceRead.getResourceValueFromXML().getProperty(FIREFOX_LINUX_DRIVERPATH_PROPERTY));
			}

				seleniumWebdriver = new FirefoxDriver(firefoxOptions);

				seleniumWebdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				
			log.info("Exited the ChromeWebDriver method in BrowserInstanceFactory");
			return seleniumWebdriver;
		} catch (IOException e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
			return null;
		} catch (ResourceCustomException e) {
			new ExceptionHandeler().resourceExceptionHandeler(e);
			return null;
		}
	}

	public static WebDriver edgeWebDriver() {
		try {
			log.info("Entered the ChromeWebDriver method in BrowserInstanceFactory");

			String EDGE_DRIVERNAME_PROPERTY = "EdgeDriverName";
			String EDGE_WINDOWS_DRIVERPATH_PROPERTY = "EdgeDriverWindowsPath";
			String EDGE_LINUX_DRIVERPATH_PROPERTY = "EdgeDriverLinuxPath";
			String TIME_OUT_PROPERTY = "WebDriverTimeOutInSeconds";
			
			WebDriver seleniumWebdriver = null;
			ResourceRead resourceRead = new ResourceRead();
			final EdgeOptions edgeOptions = new EdgeOptions();

			String headlessValue = new ResourceRead().getResourceValueFromXML().getProperty(HEADLESS_PROPERTY);
			if(headlessValue.toLowerCase().contains("false"))	
			{
				edgeOptions.setHeadless(false);
			}
			
			else
				
			{
				System.out.println("Opening Edge driver in Headless mode");
				log.info("Opening Edge driver in Headless mode");
				edgeOptions.setHeadless(true);
				
			}
			
			edgeOptions.addArguments("no-sandbox");
			edgeOptions.addArguments("window-size=1920,1080");
			edgeOptions.addArguments("disable-gpu");
			edgeOptions.addArguments("disable-application-cache");
			edgeOptions.addArguments("disk-cache-size=0");
			edgeOptions.addArguments("disable-dev-shm-usage");
			Map<String, Integer> userPrefences = new HashMap<>();
			Map<String, Object> profile = new HashMap<>();
			Map<String, Object> prefs = new HashMap<>();
			userPrefences.put("media_stream", 1);
			profile.put("managed_default_content_settings", userPrefences);
			prefs.put("profile", profile);
			edgeOptions.setExperimentalOption("prefs", prefs);

			String osName = System.getProperty("os.name");
			if (osName.toLowerCase().contains("windows")) {
				System.out.println("osName: " + osName);
				System.out.println("EdgeDriverName: "
						+ resourceRead.getResourceValueFromXML().getProperty(EDGE_DRIVERNAME_PROPERTY));
				System.out.println("EdgeDriverName: "
						+ resourceRead.getResourceValueFromXML().getProperty(EDGE_WINDOWS_DRIVERPATH_PROPERTY));

				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(EDGE_DRIVERNAME_PROPERTY),
						resourceRead.getResourceValueFromXML().getProperty(EDGE_WINDOWS_DRIVERPATH_PROPERTY));
			} else {
				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(EDGE_DRIVERNAME_PROPERTY),
						resourceRead.getResourceValueFromXML().getProperty(EDGE_LINUX_DRIVERPATH_PROPERTY));
			}

			seleniumWebdriver = new EdgeDriver(edgeOptions);
			
			seleniumWebdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			log.info("Exited the EdgeWebDriver method in BrowserInstanceFactory");
			return seleniumWebdriver;
		} catch (IOException e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
			return null;
		} catch (ResourceCustomException e) {
			new ExceptionHandeler().resourceExceptionHandeler(e);
			return null;
		}
	}
}