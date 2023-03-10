package stepdefinition;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.AssumptionViolatedException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.adviserratinguiautomation.base.BasePage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class StepHooks extends BasePage {
	private int counter = 0;
	public static boolean passStatus = true;
	public WebDriver seleniumWebDriver = get_seleniumWebDriver();
	final static Logger log = Logger.getLogger(StepHooks.class);
	String imageName;

	@Before
	public void before(Scenario scenario) throws Exception {
		/*
		 * my other pre-setup tasks for each scenario.
		 */
		log.info("Entered Step Hooks Before method in StepHooks class");

		scenarioName = scenario.getName();
		// get all the scenario tags from the scenario head.
		final ArrayList<String> scenarioTags = new ArrayList<>();
		// Get the name of all the scenario
		scenarioTags.addAll(scenario.getSourceTagNames());

		log.info("Exited Step Hooks Before method in StepHooks class");
	}

	public void teardown() throws InterruptedException {
		Thread.sleep(2000);
		seleniumWebDriver.quit();
	}

	@After
	public void AfterScenario(Scenario scenario) throws Exception {

		log.info("Entered Step Hooks AfterScenario method in StepHooks class");

		scenarioName = scenario.getName();

		if (scenario.isFailed()) {

			passStatus = false;
			TakesScreenshot ts = (TakesScreenshot) seleniumWebDriver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

			// Prod
			if (System.getProperty("Environment").equalsIgnoreCase("Prod")) {
				/*
				 * seleniumWebDriver.switchTo().parentFrame(); // In case of error popup
				 * pooldriverPage.CloseErrorPopup(); // In case car popup exists
				 * poolcarPage.CloseCarPopup(); logPage.logoutApplication();
				 */
			}

			else {
				// Portal Dev or UAT
				if (System.getProperty("Application").equalsIgnoreCase("Portal-Dev")
						|| System.getProperty("Application").equalsIgnoreCase("Portal-UAT")) {

					// logPage.logoutPortalDevApplication();
				}
				// Nitro UAT or Dev
				else {

					// For Driver Booking as it is executed with Nitro tests
					if (seleniumWebDriver.getCurrentUrl().contains("driver-booking")) {
						// logPage.logoutPortalDevApplication();
						return;
					}

					// In case of custom page popup
					// logPage.clickYesButton();

					// In case of error popup
					// pooldriverPage.CloseErrorPopup();
					// In case car popup exists
					// poolcarPage.CloseCarPopup();

					if (System.getProperty("Environment").equalsIgnoreCase("Dev")) {
						// In case of failure logout of the application
						// logPage.logoutDevApplication();

					}

					else {

						// In case if it is fuel card
						if (scenario.getName().toLowerCase().contains("fuel card")) {
							// logPage.fuellogoutApplication();
						} else {

							// For Power BI reports
							try {
								seleniumWebDriver.switchTo().parentFrame();
							}
							// For rest of the scripts where frame is not available
							catch (Exception e) {

							}
							// In case of failure logout of the application
							// logPage.logoutApplication();
						}
					}

				}

			}

		}

		log.info("Exited  Step Hooks AfterScenario method in StepHooks class");
//	   seleniumWebDriver.quit();
	}

}