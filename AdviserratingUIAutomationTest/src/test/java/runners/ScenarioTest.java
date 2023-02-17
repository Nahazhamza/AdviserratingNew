package runners;

import java.io.File;
import org.openqa.selenium.WebDriver;
import java.net.URL;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;

import com.adviserratinguiautomation.base.BasePage;
import com.adviserratinguiautomation.base.BaseTestScripts;
import com.adviserratinguiautomation.customReport.CustomExtendReport;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.logger.FrameworkLogger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import stepdefinition.AdviserRatingsSteps;
import stepdefinition.StepHooks;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
glue = { "stepdefinition"}, 
// For instance, once cucumber
																							// runs I want to add
plugin = { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, monochrome = true, dryRun = false
//publish = true
//strict=true

)

public class ScenarioTest extends BasePage {
	final static Logger log = Logger.getLogger(ScenarioTest.class);
	public static  StepHooks stephooks= new StepHooks();

	
	@AfterClass
	public  static void teardown() throws InterruptedException
	{
		log.info("Entered Teardown method");
		System.out.println("Entered teardown");
		try {
			
			
			stephooks.teardown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
