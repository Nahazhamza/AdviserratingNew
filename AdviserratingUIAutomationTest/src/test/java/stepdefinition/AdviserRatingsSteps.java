package stepdefinition;

import org.openqa.selenium.WebDriver;
import com.adviserratinguiautomation.base.BasePage;
import com.adviserratinguiautomation.base.BaseTestScripts;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.InvalidInputException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.WebDriverInstanceNullException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import java.io.IOException;
import org.apache.log4j.Logger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uipages.AdviserRatingPage;

public class AdviserRatingsSteps extends BasePage {

	final static Logger log = Logger.getLogger(AdviserRatingsSteps.class);

	String appUrl;
	WebDriver seleniumWebDriver;
	public String scenarioName;
	private AdviserRatingPage adviserratingpage = new AdviserRatingPage();

	public AdviserRatingsSteps() {
		this.seleniumWebDriver = get_seleniumWebDriver();
		this.scenarioName = BaseTestScripts.scenarioName;
	}

	// Launching url page with driver instance is performed

	@Given("I Navigate to {string}")
	public void user_opens_URL(String urlType) {
		try {
			log.info("Step: Navigated to "+urlType);

			String getAppUrl = new ResourceRead().getEnvironmentConfigValue().getProperty("adviserratingurl");
			String getEnvType = new ResourceRead().getEnvironmentConfigValue().getProperty("envType");
			// loginPage.verifyLogout();
			seleniumWebDriver.manage().deleteAllCookies();
			switch (urlType) {
			case "AdviserratingURL":

				appUrl = String.format(getAppUrl, getEnvType);
				System.out.println("appUrl formation:" + appUrl);

				break;

			}

			LaunchURL(appUrl, seleniumWebDriver);
			System.out.println("appUrl formation:" + appUrl);
			log.info("Opened URL: " + appUrl);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// Move to Advisortab

	@When("I Click on Adviser Tab")
	public void click_on_adviser_tab() {
		try {
			log.info("Step: Click on Adviser Tab");
			
			adviserratingpage.clickAdviserTab();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@When("I Enter {string} inside the Search box")
	public void enter_inside_the_search_box1(String value) {
		log.info("Step: Enter the value" + value + "on Search box");
		try {
			
			adviserratingpage.searchValue(value);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@When("Enter {string} on the Search box")
	public void enter_on_the_search_box(String adviserName) {
		log.info("Step: Enter the adviser name" + adviserName + "on Search box");
		try {
			
			adviserratingpage.searchAdviserName(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Dropdown should be displayed with {string}")
	public void dropdown_should_be_displayed_with(String adviserName) {
		log.info("Step: Dropdown should be displayed with "+adviserName);
		try {
			
			adviserratingpage.verifyadviserNameonDropDown(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Dropdown should be highlighted for the {string}")
	public void dropdown_should_be_highlighted_for_the(String adviserName) {
		log.info("Step: Dropdown should be highlighted for the "+adviserName);
		try {
			Thread.sleep(1000);
			adviserratingpage.verifyadviserNameHighlighted(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Practice under the {string} should be {string}")
	public void practice_under_the_should_be(String adviserName, String practise) {
		log.info("Step: Practice under the "+adviserName+" should be "+practise);
		try {
			
			adviserratingpage.verifyadviserPractise(adviserName, practise);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Two locations should be listed next to the adviser as {string} and {string}")
	public void two_locations_should_be_listed_next_to_the_and(String location1, String location2) {
		log.info("Step: Two locations should be listed next to the adviser as "+location1+" and "+location2);
		try {
			
			adviserratingpage.verifyLocations(location1, location2);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}
//click enter

	@When("click on Enter key")
	public void click_on_enter_key() {
		try {
			log.info("Step: Click on Enter Key");
			

			adviserratingpage.enterKey();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@When("On clicking Enter key")
	public void enter_key() {
		try {
			log.info("Step: Click on Enter Key");
			

			adviserratingpage.enterKey_advisor();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

//To go to Location Tab

	@And("Go to location Tab")
	public void Go_to_location_Tab() {
		log.info("Step: Go to location Tab");
		try {
			
			adviserratingpage.goToLocationTab();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

//Step Called - Provide input value and click enter from dropdown and verify it is highlighted 

	@And("Find {string} in the address drop down search box and check it is highlighted")
	public void selectLocationFeild(String Expectedlocation) throws InterruptedException,
			WebDriverInstanceNullException, InvalidInputException, ResourceCustomException, IOException {
		log.info("Step: Find "+Expectedlocation+ "in the address drop down search box and check it is highlighted");
		try {
			
			adviserratingpage.selectLocationfeildcall(Expectedlocation);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}


	@And("check the view is ListView")
	public void listViewVerify() {
		log.info("check the view is ListView");
		try {
			
			adviserratingpage.listViewVerify();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}


	@And("Iterate the dropdown and select {string}")
	public void dropdownselect(String adviserName) throws InterruptedException, ResourceCustomException, IOException {
		log.info("Step: Iterate the dropdown and select" + adviserName );
		try {
			
			adviserratingpage.dropdownselectAdvisor(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}
// Get the current Page URL and verify

	@Then("The current Page URL which should contain {string}")
	public void currentUrl(String name) {
		log.info("Step: The current Page URL which should contain"+name);
		try {
			
			adviserratingpage.advisornameCurrentUrl(name);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("Check {string} is populated into the location field")
	public void verifylocation(String location) {
		log.info("Step: Check "+location+ "is populated into the location field");
		try {
			
			adviserratingpage.locationfeildVerify(location);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("The  practice name is displayed under the map is {string}")
	public void practiceName(String practiceName) throws ResourceCustomException, IOException {
		log.info("Step: The  practice name is displayed under the map is "+practiceName);
		try {
			
			adviserratingpage.practiceName(practiceName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// Check the practice logo information is correct

	@And("The practice logo is displayed")
	public void practiceLogo() throws ResourceCustomException, IOException {
		log.info("Step: The practice logo is displayed");
		try {
			
			adviserratingpage.practiceLogo();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

//verify adviser profile image
	@And("The Advisers profile image is displayed with src url contains {string}")
	public void adviserProfile(String src) throws ResourceCustomException, IOException {
		log.info("Step: The Advisers profile image is displayed with src url contain"+src);
		try {
			
			adviserratingpage.adviserImage(src);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

//verify map pinnedaddress
	@When("The google map is displayed with the pin to the address in {string}")
	public void mapPinnedAddress(String address) throws ResourceCustomException, IOException {
		log.info("Step: The google map is displayed with the pin to the address in "+address);
		try {
			
			adviserratingpage.mapPinnedAddress(address);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// verify mapurl center parameter value 
	@Then("The Map URL center parameter value should contains {string}")
	public void mapParameterValue(String address) throws ResourceCustomException, IOException {
		log.info("Step: The Map URL center parameter value should contains "+address);
		try {
			
			adviserratingpage.mapParameterValue(address);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// Check the name, advisername and location

	@And("The name is {string}")
	public void nameValidation(String name) throws ResourceCustomException, IOException {
		log.info("Step: The name is "+name);
		try {
			
			adviserratingpage.nameVerify(name);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("The Advisor name is {string}")
	public void adviserNameValidation(String adviserName) throws ResourceCustomException, IOException {
		log.info("Step: The Advisor name is "+adviserName);
		try {
			
			adviserratingpage.adviserNameVerify(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("The location will be {string}")
	public void locationValidation(String location) throws ResourceCustomException, IOException {
		log.info("Step: The location will be "+location);
		try {
			
			adviserratingpage.locationVerify(location);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// verify Tab Info 

	@And("About tab is {string}")
	public void aboutTab(String AboutName) throws ResourceCustomException, IOException {
		log.info("Step: About tab is "+AboutName);
		try {
			
			adviserratingpage.aboutTabCheckCall(AboutName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}


// Invoke Method addressCheckCall to print the address

	@And("Address check is performed")
	public void addressCheck() {
		log.info("Step: Address check is performed");
		try {
			
			adviserratingpage.addressCheckCall();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	@When("Enter {string} inside the Search box")
	public void enter_inside_the_search_box(String value) {
		log.info("Step: Enter the value" + value + "on Search box");
		try {
			
			adviserratingpage.searchValue(value);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Find {string} from the Address search dropdown and click on it")
	public void Address_dropdown_should_be_displayed_with(String location) {
		log.info("Step: Find "+location+" from the Address search dropdown and click on it");
		try {
			
			adviserratingpage.findAddressCall(location);
//adviserratingpage.verifyadviserNameonDropDown(location);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("select for {string} from dropdown and click search")
	public void selectvalueSearch(String distance) {
		
		log.info("Step: select for "+distance+" from dropdown and click search");
		try {
			
			adviserratingpage.selectvalueSearchCall(distance);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}


	@And("Distance should be displayed as {string}")
	public void Distance_value(String distance) {
		log.info("Step: Distance should be displayed as "+distance);
		try {
			
			adviserratingpage.distanceValue(distance);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	@And("check ascendingorder of KMRange")
	public void ascendingorder() throws InterruptedException {
		log.info("Step: check ascendingorder of KMRange");
		try {
			
			adviserratingpage.ascendingordercall();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("check descendingorder of KMRange")
	public void descendingOrder() throws InterruptedException {
		log.info("Step: check descendingorder of KMRange");
		try {
			
			adviserratingpage.descendingorderCall();

		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("location name should be {string}")
	public void location_name(String locName) {
		log.info("Step: location name should be "+locName);
		try {
			
			adviserratingpage.locationName(locName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}



}