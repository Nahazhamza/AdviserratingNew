package com.adviserratinguiautomation.base;

import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.driver.ADRWebDriver;
import com.adviserratinguiautomation.logger.FrameworkLogger;
import com.adviserratinguiautomation.resourceRead.ResourceRead;

import io.cucumber.plugin.event.PickleStepTestStep;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class BaseTestScripts extends FrameworkLogger {

	private WebDriver _seleniumWebDriver = ADRWebDriver.getADRWebDriverInstance().getSeleniumWebDriver();

	public static String scenarioName = "";

	public static int restAssuredport = 0;

	public static PickleStepTestStep currentStep;

	public static WebElement productElement;

	public static String dynamicProductName;

	private int timeOut;

	public int getElementFindTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("ElementFindTimeOutInSeconds"));
	}

	public int getLinkFindTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("LinkFindTimeOutInSeconds"));
	}

	public int getWebDriverTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("WebDriverTimeOutInSeconds"));
	}

	public int getBookingTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("DriverBookingTimeOutInSeconds"));
	}

	public int getRandomTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("RandomTimeOutInSeconds"));
	}

	public int getDashboardTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("DashboardFindTimeOutInSeconds"));
	}

	public int getSearchTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("SearchTimeOutInSeconds"));
	}

	public int getLoginTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("LoginTimeOutInSeconds"));
	}

	public int getQuoteTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("QuoteTimeOutInSeconds"));
	}

	public int getMinimumTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("MinimumTimeOutInSeconds"));
	}

	public int getLeastTimeOut() throws ResourceCustomException, IOException {
		return timeOut = Integer
				.parseInt(new ResourceRead().getResourceValueFromXML().getProperty("LeastTimeOutInSeconds"));
	}

	protected WebDriver get_seleniumWebDriver() {
		return _seleniumWebDriver;
	}

}