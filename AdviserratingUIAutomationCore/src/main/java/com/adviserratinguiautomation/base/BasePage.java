package com.adviserratinguiautomation.base;

import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.*;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.adviserratinguiautomation.driver.ADRWebdriverFactory.getADRWebDriverInstance;

/**
 * This is the base class for all pages that responsible to handle the WebDriver
 * functionality across pages
 */
public abstract class BasePage extends BaseTestFixture {

	final static Logger log = Logger.getLogger(BasePage.class);
	WebDriver seleniumWebDriver;

	public void createSFDriverInstanceIfNull() {
		log.info("Entered the createSFDriverInstanceIfNull method in BasePage");
		if (seleniumWebDriver == null) {
			seleniumWebDriver = getADRWebDriverInstance();
		}
		log.info("Exited the createSFDriverInstanceIfNull method in BasePage");

	}

	/**
	 * @return current URL that the browse is looking on.
	 */
	public String getCurrentURL(WebDriver seleniumWebDriver) {
		log.info("Entered the getCurrentURL method in BasePage");
		if (seleniumWebDriver == null) {
			createSFDriverInstanceIfNull();
		}
		log.info("Exited the getCurrentURL method in BasePage");
		return seleniumWebDriver.getCurrentUrl();
	}

	/**
	 * @return get the title of the current page
	 */
	public String getPageTitle(WebDriver seleniumWebDriver) {
		log.info("Entered the getPageTitle method in BasePage");
		if (seleniumWebDriver == null) {
			createSFDriverInstanceIfNull();
		}
		log.info("Exited the getPageTitle method in BasePage");
		return seleniumWebDriver.getTitle();
	}

	/**
	 * @return get the current page source
	 */
	public String getPageSource(WebDriver seleniumWebDriver) {
		log.info("Entered the getPageSource method in BasePage");
		if (seleniumWebDriver == null) {
			createSFDriverInstanceIfNull();
		}
		log.info("Exited the getPageSource method in BasePage");
		return seleniumWebDriver.getPageSource();
	}

	/**
	 * @param urlKey Launch the URL.
	 * 
	 */
	public void LaunchURL(String url, WebDriver seleniumWebDriver) {
		log.info("Entered the LaunchURL method in BasePage: " + url);
		if (seleniumWebDriver == null) {
			createSFDriverInstanceIfNull();
		}
		seleniumWebDriver.get(url);
		seleniumWebDriver.manage().window().maximize();
		// log.info("Exited the LaunchURL method in BasePage");

	}

	public void clickOnButton(By by, WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		try {
			log.info("Entered the clickOnButton method in BasePage");
			if (isWebDriverInstancePresent(seleniumWebDriver)) {
				waitForElement(by, seleniumWebDriver);
				if (isExpectedCondition(ExpectedConditions.elementToBeClickable(by), seleniumWebDriver)) {
					seleniumWebDriver.findElement(by).click();
				} else {
					throw new ElementNotClickableException("Element is not clickable");

				}

			}
			log.info("Exited the clickOnButton method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		} catch (ElementNotClickableException ex) {
			new ExceptionHandeler().webDriverExceptionhandeler(ex);
		}
	}

	public void clickOnButtonByXpath(String xPathValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickOnButtonByXpath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue), seleniumWebDriver);
		}
		log.info("Exited the clickOnButtonByXpath method in BasePage");
	}

	public void clickOnButtonByWebElement(WebElement button, WebDriver seleniumWebDriver)
			throws ResourceCustomException, IOException {
		log.info("Entered the clickOnButtonByWebElement method in BasePage");
		if (isElementPresent(button, getElementFindTimeOut(), seleniumWebDriver)) {
			button.click();
		}
		log.info("Exited the clickOnButtonByWebElement method in BasePage");
	}

	public void clickButtonById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue), seleniumWebDriver);
		}
		log.info("Exited the clickButtonById method in BasePage");
	}

	public void clickButtonByClassName(String classNameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonByClassName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue), seleniumWebDriver);
		}
		log.info("Exited the clickButtonByClassName method in BasePage");
	}

	public void clickButtonByPartialLinkText(String partialLinkTextValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonByPartialLinkText method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(partialLinkTextValue)) {
			clickOnButton(By.partialLinkText(partialLinkTextValue), seleniumWebDriver);
		}
		log.info("Exited the clickButtonByPartialLinkText method in BasePage");
	}

	public void clickButtonByCssSelector(String cssSelectorValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(cssSelectorValue)) {
			clickOnButton(By.cssSelector(cssSelectorValue), seleniumWebDriver);
		}
		log.info("Exited the clickButtonByCssSelector method in BasePage");
	}

	public void clickButtonByLinkText(String linkTextValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonByLinkText method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(linkTextValue)) {
			clickOnButton(By.linkText(linkTextValue), seleniumWebDriver);
		}
		log.info("Exited the clickButtonByLinkText method in BasePage");
	}

	public void selectButtonByName(String nameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectButtonByName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(nameValue)) {
			clickOnButton(By.name(nameValue), seleniumWebDriver);
		}
		log.info("Exited the selectButtonByName method in BasePage");
	}

	public void selectRadioButtonById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue), seleniumWebDriver);
		}
		log.info("Exited the selectRadioButtonById method in BasePage");
	}

	public void selectRadioButtonByName(String nameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonByName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(nameValue)) {
			clickOnButton(By.name(nameValue), seleniumWebDriver);
		}
		log.info("Exited the selectRadioButtonByName method in BasePage");
	}

	public void selectRadioButtonByXpath(String xPathValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonByXpath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue), seleniumWebDriver);
		}
		log.info("Exited the selectRadioButtonByXpath method in BasePage");
	}

	public void selectRadioButtonByClassName(String classNameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonByClassName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue), seleniumWebDriver);
		}
		log.info("Exited the selectRadioButtonByClassName method in BasePage");
	}

	public void selectRadioButtonByCssSelector(String cssSelectorValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(cssSelectorValue)) {
			clickOnButton(By.cssSelector(cssSelectorValue), seleniumWebDriver);
		}
		log.info("Exited the selectRadioButtonByCssSelector method in BasePage");
	}

	public void selectCheckBoxById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue), seleniumWebDriver);
		}
		log.info("Exited the selectCheckBoxById method in BasePage");
	}

	public void selectCheckBoxByXpath(String xPathValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxByXpath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue), seleniumWebDriver);
		}
		log.info("Exited the selectCheckBoxByXpath method in BasePage");
	}

	public void selectCheckBoxByClassName(String classNameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxByClassName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue), seleniumWebDriver);
		}
		log.info("Exited the selectCheckBoxByClassName method in BasePage");
	}

	public void selectCheckBoxByCssSelector(String cssSelectorValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(cssSelectorValue)) {
			clickOnButton(By.cssSelector(cssSelectorValue), seleniumWebDriver);
		}
		log.info("Exited the selectCheckBoxByCssSelector method in BasePage");
	}

	public void selectCheckBoxByName(String nameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxByName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(nameValue)) {
			clickOnButton(By.name(nameValue), seleniumWebDriver);
		}
		log.info("Exited the selectCheckBoxByName method in BasePage");
	}

	public void clickImageByXpath(String xPathValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickImageByXpath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue), seleniumWebDriver);
		}
		log.info("Exited the clickImageByXpath method in BasePage");
	}

	public void clickImageById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickImageById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue), seleniumWebDriver);
		}
		log.info("Exited the clickImageById method in BasePage");
	}

	public void clickImageByClassName(String classNameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickImageByClassName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue), seleniumWebDriver);
		}
		log.info("Exited the clickImageByClassName method in BasePage");
	}

	public void clickLinkByPartialLinkText(String partialLinkTextValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkByPartialLinkText method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(partialLinkTextValue)) {
			clickOnButton(By.partialLinkText(partialLinkTextValue), seleniumWebDriver);
		}
		log.info("Exited the clickLinkByPartialLinkText method in BasePage");
	}

	public void clickLinkByLinkText(String linkTextValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkByLinkText method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(linkTextValue)) {
			clickOnButton(By.linkText(linkTextValue), seleniumWebDriver);
		}
		log.info("Exited the clickLinkByLinkText method in BasePage");
	}

	public void clickLinkByCssSelector(String cssSelectorValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(cssSelectorValue)) {
			clickOnButton(By.cssSelector(cssSelectorValue), seleniumWebDriver);
		}
		log.info("Exited the clickLinkByCssSelector method in BasePage");
	}

	public void clickLinkByXpath(String xPathValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkByXpath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue), seleniumWebDriver);
		}
		log.info("Exited the clickLinkByXpath method in BasePage");
	}

	public void clickLinkById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue), seleniumWebDriver);
		}
		log.info("Exited the clickLinkById method in BasePage");
	}

	public void selectTabById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectTabById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue), seleniumWebDriver);
		}
		log.info("Exited the selectTabById method in BasePage");
	}

	public void selectTabByClassName(String classNameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectTabByClassName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue), seleniumWebDriver);
		}
		log.info("Exited the selectTabByClassName method in BasePage");
	}

	public void selectTabByXpath(String xPathValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectTabByXpath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.className(xPathValue), seleniumWebDriver);
		}
		log.info("Exited the selectTabByXpath method in BasePage");
	}

	public void findTextBoxAndSetValue(By by, String value, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException {
		try {
			log.info("Entered the findTextBoxAndSetValue method in BasePage");
			if (isWebDriverInstancePresent(seleniumWebDriver)) {
				waitForElement(by, seleniumWebDriver);
				seleniumWebDriver.findElement(by).click();
				seleniumWebDriver.findElement(by).clear();
				seleniumWebDriver.findElement(by).sendKeys(value);
			}
			log.info("Exited the findTextBoxAndSetValue method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	public void findTextboxAndSetValueByXpath(String xPathValue, String value, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueByXpath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			findTextBoxAndSetValue(By.xpath(xPathValue), value, seleniumWebDriver);
		}
		log.info("Exited the findTextboxAndSetValueByXpath method in BasePage");
	}

	public void findTextboxAndSetValueById(String idValue, String value, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			findTextBoxAndSetValue(By.id(idValue), value, seleniumWebDriver);
		}
		log.info("Exited the findTextboxAndSetValueById method in BasePage");
	}

	public void findTextboxAndSetValueByClassName(String classNameValue, String value, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueByClassName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameValue)) {
			findTextBoxAndSetValue(By.className(classNameValue), value, seleniumWebDriver);
		}
		log.info("Exited the findTextboxAndSetValueByClassName method in BasePage");
	}

	public void findTextboxAndSetValueByCssSelector(String cssSelectorValue, String value, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(cssSelectorValue)) {
			findTextBoxAndSetValue(By.cssSelector(cssSelectorValue), value, seleniumWebDriver);
		}
		log.info("Exited the findTextboxAndSetValueByCssSelector method in BasePage");
	}

	public void findTextboxAndSetValueByName(String nameValue, String value, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueByName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(nameValue)) {
			findTextBoxAndSetValue(By.name(nameValue), value, seleniumWebDriver);
		}
		log.info("Exited the findTextboxAndSetValueByName method in BasePage");
	}

	public void fillEmptyText(By by, WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		try {
			log.info("Entered the findTextBoxAndSetValue method in BasePage");
			if (isWebDriverInstancePresent(seleniumWebDriver)) {
				waitForElement(by, seleniumWebDriver);
				seleniumWebDriver.findElement(by).clear();
				seleniumWebDriver.findElement(by).sendKeys("");
			}
			log.info("Exited the findTextBoxAndSetValue method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	public void fillEmptyTextById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the fillEmptyTextById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			fillEmptyText(By.id(idValue), seleniumWebDriver);
		}
		log.info("Exited the fillEmptyTextById method in BasePage");
	}

	public void fillEmptyTextByxPath(String xPathValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the fillEmptyTextByxPath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			fillEmptyText(By.xpath(xPathValue), seleniumWebDriver);
		}
		log.info("Exited the fillEmptyTextByxPath method in BasePage");
	}

	public void fillEmptyTextByPartialLinkText(String partialLinkTextValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the fillEmptyTextByPartialLinkText method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(partialLinkTextValue)) {
			fillEmptyText(By.partialLinkText(partialLinkTextValue), seleniumWebDriver);
		}
		log.info("Exited the fillEmptyTextByPartialLinkText method in BasePage");
	}

	public void fillEmptyTextByClassName(String classNameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the fillEmptyTextByClassName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameValue)) {
			fillEmptyText(By.className(classNameValue), seleniumWebDriver);
		}
		log.info("Exited the fillEmptyTextByClassName method in BasePage");
	}

	public void focusOnElementById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			fillEmptyText(By.id(idValue), seleniumWebDriver);
		}
		log.info("Exited the focusOnElementById method in BasePage");
	}

	public void focusOnElementByCssSelector(String cssSelectorValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(cssSelectorValue)) {
			fillEmptyText(By.cssSelector(cssSelectorValue), seleniumWebDriver);
		}
		log.info("Exited the focusOnElementByCssSelector method in BasePage");
	}

	public void focusOnElementByClassName(String classNameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByClassName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameValue)) {
			fillEmptyText(By.className(classNameValue), seleniumWebDriver);
		}
		log.info("Exited the focusOnElementByClassName method in BasePage");
	}

	public void focusOnElementByPartialLinkText(String partialLinkTextValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByPartialLinkText method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(partialLinkTextValue)) {
			fillEmptyText(By.partialLinkText(partialLinkTextValue), seleniumWebDriver);
		}
		log.info("Exited the focusOnElementByPartialLinkText method in BasePage");
	}

	public void focusOnElementByLinkText(String linkTextValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByLinkText method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(linkTextValue)) {
			fillEmptyText(By.linkText(linkTextValue), seleniumWebDriver);
		}
		log.info("Exited the focusOnElementByLinkText method in BasePage");
	}

	public void focusOnElementByXpath(String xPathValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByXpath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			fillEmptyText(By.xpath(xPathValue), seleniumWebDriver);
		}
		log.info("Exited the focusOnElementByXpath method in BasePage");
	}

	/**
	 * @param webElement        Element to move to
	 * @param seleniumWebDriver Moves the mouse to the middle of the element.
	 *                          Actions : The user-facing API for emulating complex
	 *                          user gestures.Use this class rather than using the
	 *                          Keyboard or Mouse directly. Implements the builder
	 *                          pattern: Builds a CompositeAction containing all
	 *                          actions specified by the method calls. MoveToElement
	 *                          : Moves the mouse to the middle of the element.The
	 *                          element is scrolled into view and its location is
	 *                          calculated using getBoundingClientRect. Perform : A
	 *                          convenience method for performing the actions
	 *                          without calling build() first.
	 */
	public void performMoveToElementAction(WebElement webElement, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException {
		try {
			log.info("Entered the performMoveToElementAction method in BasePage");
			if (isWebDriverInstancePresent(seleniumWebDriver)
					&& isElementPresent(webElement, getElementFindTimeOut(), seleniumWebDriver)) {
				new Actions(seleniumWebDriver).moveToElement(webElement).perform();
			}
			log.info("Entered the performMoveToElementAction method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		}
	}

	/**
	 * @param webElement        Defines the interface through which the user
	 *                          controls elements on the page.
	 * @param seleniumWebDriver Simulate focus on element by Moves the mouse to the
	 *                          middle of the element. The element is scrolled into
	 *                          view and its location is calculated using
	 *                          getBoundingClientRect. This is working with google
	 *                          chome and other browsers.
	 */
	public void performFocusOnElementAction(WebElement webElement, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, ResourceCustomException, IOException {
		log.info("Entered the performFocusOnElementAction method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver)
				&& isElementPresent(webElement, getElementFindTimeOut(), seleniumWebDriver)) {
			performMoveToElementAction(webElement, seleniumWebDriver);
		}
		log.info("Exited the performFocusOnElementAction method in BasePage");
	}

	/**
	 * @param idLocatorValue    Defines the interface by locating element Id through
	 *                          which the user controls elements on the page.
	 * @param seleniumWebDriver Simulate focus on element id by Moves the mouse to
	 *                          the middle of the element. The element is scrolled
	 *                          into view and its location is calculated using
	 *                          getBoundingClientRect. This is working with google
	 *                          chome and other browsers.
	 */
	public void performFocusOnElementActionById(String idLocatorValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException, ResourceCustomException,
			ElementNotFoundException, IOException {
		log.info("Entered the performFocusOnElementActionById method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idLocatorValue)) {
			WebElement idLocator = getWebElementPropertiesById(idLocatorValue, seleniumWebDriver);
			performFocusOnElementAction(idLocator, seleniumWebDriver);
		}
		log.info("Exited the performFocusOnElementActionById method in BasePage");
	}

	/**
	 * @param xPathLocatorValue Defines the interface by locating element xpath
	 *                          through which the user controls elements on the
	 *                          page.
	 * @param seleniumWebDriver Simulate focus on element id by Moves the mouse to
	 *                          the middle of the element. The element is scrolled
	 *                          into view and its location is calculated using
	 *                          getBoundingClientRect. This is working with google
	 *                          chome and other browsers.
	 */
	public void performFocusOnElementActionByXpath(String xPathLocatorValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException, ResourceCustomException,
			ElementNotFoundException, IOException {
		log.info("Entered the performFocusOnElementActionByXpath method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathLocatorValue)) {
			WebElement idLocator = getWebElementPropertiesByXpath(xPathLocatorValue, seleniumWebDriver);
			performFocusOnElementAction(idLocator, seleniumWebDriver);
		}
		log.info("Exited the performFocusOnElementActionByXpath method in BasePage");
	}

	/**
	 * @param classNameLocatorValue Defines the interface by locating element class
	 *                              name through which the user controls elements on
	 *                              the page.
	 * @param seleniumWebDriver     Simulate focus on element id by Moves the mouse
	 *                              to the middle of the element. The element is
	 *                              scrolled into view and its location is
	 *                              calculated using getBoundingClientRect. This is
	 *                              working with google chome and other browsers.
	 */
	public void performFocusOnElementActionByClassName(String classNameLocatorValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException, ResourceCustomException,
			ElementNotFoundException, IOException {
		log.info("Entered the performFocusOnElementActionByClassName method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameLocatorValue)) {
			WebElement idLocator = getWebElementPropertiesByClassName(classNameLocatorValue, seleniumWebDriver);
			performFocusOnElementAction(idLocator, seleniumWebDriver);
		}
		log.info("Exited the performFocusOnElementActionByClassName method in BasePage");
	}

	/**
	 * @param frameName         The name of the frame window, the id of the frame or
	 *                          iframe element, or the (zero-based) index.
	 * @param seleniumWebDriver Select a frame by its name or ID. Frames located by
	 *                          matching name attributes are always given precedence
	 *                          over those matched by ID.
	 */
	public void switchToFrame(String frameName, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the switchToFrame method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(frameName)) {
			seleniumWebDriver.switchTo().frame(frameName);
		}
		log.info("Exited the switchToFrame method in BasePage");
	}

	/**
	 * @param webElement        The frame element to switch to.
	 * @param seleniumWebDriver Select a frame using its previously located
	 *                          WebElement.
	 */
	public void switchToFrameByWebElement(WebElement webElement, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, ResourceCustomException, IOException {
		log.info("Entered the switchToFrameByWebElement method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver)
				&& isElementPresent(webElement, getElementFindTimeOut(), seleniumWebDriver)) {
			seleniumWebDriver.switchTo().frame(webElement);
		}
		log.info("Exited the switchToFrameByWebElement method in BasePage");
	}

	/**
	 * @param nameValue
	 * @param seleniumWebDriver Select a frame by its name. Frames located by
	 *                          matching name attributes are always given precedence
	 *                          over those matched by ID.
	 */
	public void switchToFrameByName(String nameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		try {
			log.info("Entered the switchToFrameByName method in BasePage");
			if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(nameValue)) {
				waitForElement(By.name(nameValue), seleniumWebDriver);
				switchToFrame(nameValue, seleniumWebDriver);
			}
			log.info("Exited the switchToFrameByName method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	/**
	 * @param indexNumber       (zero-based) index.
	 * @param seleniumWebDriver Select a frame by its (zero-based) index. That is,
	 *                          if a page has three frames, the first frame would be
	 *                          at index "0", the second at index "1" and the third
	 *                          at index "2". Once the frame has been selected, all
	 *                          subsequent calls on the WebDriver interface are made
	 *                          to that frame.
	 */
	public void switchToFrameByIndex(int indexNumber, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException {
		log.info("Entered the switchToFrameByIndex method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver)) {
			seleniumWebDriver.switchTo().frame(indexNumber);
		}
		log.info("Exited the switchToFrameByIndex method in BasePage");
	}

	/**
	 * @param idValue           This is iFrame id atribute value.
	 * @param seleniumWebDriver Select a frame by its name or ID. Frames located by
	 *                          matching name attributes are always given precedence
	 *                          over those matched by ID.
	 */
	public void switchToFrameById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		try {
			log.info("Entered the switchToFrameById method in BasePage");
			if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
				waitForElement(By.id(idValue), seleniumWebDriver);
				switchToFrame(idValue, seleniumWebDriver);
			}
			log.info("Exited the switchToFrameById method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	/**
	 * @param nameValue         This is iFrame source attribute value.
	 * @param seleniumWebDriver Select a frame by its SOURCE. Frames located by
	 *                          matching SRC attributes
	 */
	public void switchToIFrameBySource(String nameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, ElementNotFoundException, ResourceCustomException,
			InvalidInputException, IOException {
		try {
			log.info("Entered the switchToIFrameBySource method in BasePage");
			if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(nameValue)) {
				List<WebElement> frames = seleniumWebDriver.findElements(By.tagName("iframe"));
				if (frames == null) {
					return;
				}
				for (WebElement iframe : frames) {
					if (iframe.getAttribute("src").contains(nameValue)) {
						switchToFrameByWebElement(iframe, seleniumWebDriver);
						break;
					} else {
						throw new IFrameNotFoundException("Iframe " + nameValue + " do not exists");
					}
				}
			}
			log.info("Exited the switchToIFrameBySource method in BasePage");
		} catch (IFrameNotFoundException ex) {
			new ExceptionHandeler().webDriverExceptionhandeler(ex);
		}
	}

	/**
	 * @param seleniumWebDriver Selects either the first frame on the page, or the
	 *                          main document when a page contains iframes.
	 *                          DefaultContent : This driver focused on the top
	 *                          window/first frame.
	 */
	public void switchToDefaultPageContent(WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		log.info("Entered the switchToDefaultPageContent method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver)) {
			seleniumWebDriver.switchTo().defaultContent();
		}
		log.info("Exited the switchToDefaultPageContent method in BasePage");
	}

	/**
	 * @param seleniumWebDriver Switches to the element that currently has focus
	 *                          within the document currently "switched to", or the
	 *                          body element if this cannot be detected. This
	 *                          matches the semantics of calling
	 *                          "document.activeElement" in Javascript. Active
	 *                          Element : The WebElement with focus, or the body
	 *                          element if no element with focus can be detected.
	 */
	public void switchToActivePageElement(WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		log.info("Entered the switchToActivePageElement method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver)) {
			seleniumWebDriver.switchTo().activeElement();
		}
		log.info("Exited the switchToActivePageElement method in BasePage");
	}

	/**
	 * @param seleniumWebDriver Switch the focus of future commands for this driver
	 *                          to the parent window with the given empty string.
	 */
	public void switchToDefaultWindow(WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		log.info("Entered the switchToDefaultWindow method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver)) {
			seleniumWebDriver.switchTo().defaultContent();
		}
		log.info("Exited the switchToDefaultWindow method in BasePage");
	}

	/**
	 * @param seleniumWebDriver Switch the focus of future commands for this driver
	 *                          to the parent window with the given empty string
	 */
	public void switchToOtherWindow(WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		log.info("Entered the switchToOtherWindow method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver)) {
			Set<String> allWindows = seleniumWebDriver.getWindowHandles();
			for (String handel : allWindows) {
				seleniumWebDriver.switchTo().window(handel);
			}
			seleniumWebDriver.switchTo().defaultContent();
		}
		log.info("Exited the switchToOtherWindow method in BasePage");
	}

	public WebElement getWebElementProperties(By by, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException {
		try {
			WebElement element = null;
			if (isWebDriverInstancePresent(seleniumWebDriver)) {
				log.info("Entered the getWebElementProperties method in BasePage");
				waitForElement(by, seleniumWebDriver);
				log.info("Exited the getWebElementProperties method in BasePage");
				element = seleniumWebDriver.findElement(by);
			}
			return element;
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
			return null;
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
			return null;
		}
	}

	public List<WebElement> getWebElementsProperties(By by, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException {
		try {
			List<WebElement> elementList = new ArrayList<>();
			if (isWebDriverInstancePresent(seleniumWebDriver)) {
				log.info("Entered the getWebElementsProperties method in BasePage");
				waitForElement(by, seleniumWebDriver);
				log.info("Exited the getWebElementsProperties method in BasePage");
				elementList = seleniumWebDriver.findElements(by);
			}
			return elementList;
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
			return null;
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
			return null;
		}
	}

	public WebElement getWebElementPropertiesById(String idValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		WebElement element = null;
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(idValue)) {
			log.info("Entered the getWebElementPropertiesById method in BasePage");
			element = getWebElementProperties(By.id(idValue), seleniumWebDriver);
			log.info("Exited the getWebElementPropertiesById method in BasePage");
		}
		return element;
	}

	public WebElement getWebElementPropertiesByXpath(String xPathValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		WebElement element = null;
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(xPathValue)) {
			log.info("Entered the getWebElementPropertiesByXpath method in BasePage");
			element = getWebElementProperties(By.xpath(xPathValue), seleniumWebDriver);
			log.info("Exited the getWebElementPropertiesByXpath method in BasePage");
		}
		return element;
	}

	public WebElement getWebElementPropertiesByClassName(String classNameValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException, InvalidInputException {
		WebElement element = null;
		if (isWebDriverInstancePresent(seleniumWebDriver) && isConstantValuePresent(classNameValue)) {
			log.info("Entered the getWebElementPropertiesByClassName method in BasePage");
			element = getWebElementProperties(By.className(classNameValue), seleniumWebDriver);
			log.info("Exited the getWebElementPropertiesByClassName method in BasePage");
		}
		return element;
	}

	private void selectDropDownValueByText(By by, String value, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException {
		try {
			log.info("Entered the selectDropDownValueByText method in BasePage");
			if (isWebDriverInstancePresent(seleniumWebDriver)) {
				waitForElement(by, seleniumWebDriver);
				Select dropDown = new Select(seleniumWebDriver.findElement(by));
				dropDown.selectByVisibleText(value);
			}
			log.info("Exited the selectDropDownValueByText method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	public void selectDropDownValueThroughTextById(String id, String selectTextValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException {
		log.info("Entered the selectDropDownValueThroughTextById method in BasePage");
		selectDropDownValueByText(By.id(id), selectTextValue, seleniumWebDriver);
		log.info("Exited the selectDropDownValueThroughTextById method in BasePage");
	}

	public void selectDropDownValueThroughTextByCssSelector(String cssSelector, String selectTextValue,
			WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		log.info("Entered the selectDropDownValueThroughTextById method in BasePage");
		selectDropDownValueByText(By.cssSelector(cssSelector), selectTextValue, seleniumWebDriver);
		log.info("Exited the selectDropDownValueThroughTextById method in BasePage");
	}

	public void selectDropDownValueThroughTextByName(String name, String selectTextValue, WebDriver seleniumWebDriver)
			throws WebDriverInstanceNullException {
		log.info("Entered the selectDropDownValueThroughTextById method in BasePage");
		selectDropDownValueByText(By.name(name), selectTextValue, seleniumWebDriver);
		log.info("Exited the selectDropDownValueThroughTextById method in BasePage");
	}

	public void selectDropDownValueThroughTextByXpath(String xpathValue, String selectTextValue,
			WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		log.info("Entered the selectDropDownValueThroughTextById method in BasePage");
		selectDropDownValueByText(By.xpath(xpathValue), selectTextValue, seleniumWebDriver);
		log.info("Exited the selectDropDownValueThroughTextById method in BasePage");
	}

	public void closeAllBrowserWindow(WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		try {
			log.info("Entered the closeAllBrowserWindow method in BasePage");
			if (isWebDriverInstancePresent(seleniumWebDriver)) {
				Thread.sleep(1000);
				seleniumWebDriver.quit();
				Thread.sleep(1000);
			}
			log.info("Exited the closeAllBrowserWindow method in BasePage");
		} catch (InterruptedException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	public void scrollUpTheBrowser(WebDriver seleniumWebDriver) throws WebDriverInstanceNullException {
		log.info("Entered the scrollUpTheBrowser method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver)) {
			JavascriptExecutor jse = (JavascriptExecutor) seleniumWebDriver;
			jse.executeScript("scroll(0, -250);");
		}
		log.info("Exited the scrollUpTheBrowser method in BasePage");
	}

	public void scrollDownTheBrowser(WebDriver seleniumWebDriver, int scrollDownVal)
			throws WebDriverInstanceNullException {
		log.info("Entered the scrollDownTheBrowser method in BasePage");
		if (isWebDriverInstancePresent(seleniumWebDriver)) {
			JavascriptExecutor jse = (JavascriptExecutor) seleniumWebDriver;
			jse.executeScript("scroll(0," + scrollDownVal + ");");
		}
		log.info("Exited the scrollDownTheBrowser method in BasePage");
	}

	public void scrollToViewWebElement(WebDriver seleniumWebDriver, WebElement element) {
		log.info("Entered the scrollToViewWebElement method in BasePage");
		((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].scrollIntoView();", element);
		log.info("Exited the scrollToViewWebElement method in BasePage");
	}

	public void clickByJavaScriptWebElement(WebDriver seleniumWebDriver, WebElement element) {
		log.info("Entered the clickByJavaScriptWebElement method in BasePage");
		((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click();", element);
		log.info("Exited the clickByJavaScriptWebElement method in BasePage");
	}

}
