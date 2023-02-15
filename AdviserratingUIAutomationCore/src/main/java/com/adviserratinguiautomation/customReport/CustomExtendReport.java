package com.adviserratinguiautomation.customReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.zeroturnaround.zip.ZipUtil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.adviserratinguiautomation.base.BasePage;
import com.adviserratinguiautomation.base.BaseTestScripts;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.emailreport.SendOutlookEmail;
import com.adviserratinguiautomation.emailreport.SentEmailReport;
import com.adviserratinguiautomation.resourceRead.ResourceRead;

import io.cucumber.java.Scenario;

public class CustomExtendReport extends BasePage {

	final static Logger log = Logger.getLogger(CustomExtendReport.class);

	public static ExtentTest test;

	public static ExtentReports extentReports;

	public static WebDriver seleniumWebDriver;

	private static Scenario scenario;

	public static String scenarioName;

	private static final String ERROR_SCREENSHOT_LOCATION = "ErrorScreenShotLocation";

	private static final String EXTEND_REPORT_CONFIG_FILE = "ExtentReportConfigFile";

	private static final String EXTENT_REPORTER_HTML_OUT = "extent.reporter.html.out";

	public CustomExtendReport() {
		this.seleniumWebDriver = get_seleniumWebDriver();
		this.scenarioName = BaseTestScripts.scenarioName;
	}

	public static void startTest(String testCaseName) {
		log.info("Entered the startTest method in CustomReportClass");
		test = extentReports.createTest(testCaseName);
		log.info("Exited the startTest method in CustomReportClass");
	}

	public enum ReportStatus {
		PASS, INFO, SKIP, WARNNING, ERROR, DEBUG, FATAL, ATTACHSCREENSHOT, FAIL
	}

	public static String captureScreenShot(WebDriver seleniumWebDriver) {
		try {
			log.info("Entered the captureScreenShot method in CustomReportClass");

			String name = scenarioName.replace(" ", "_");
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String screenShotLocationPrefix = new ResourceRead().getResourceValueFromXML()
					.getProperty("TestResultLocationPath");
			String screenShotLocationSufix = new ResourceRead().getResourceValueFromXML()
					.getProperty("ErrorScreenShotLocation") + name + "_" + dateName + ".png";
			String screenShotPath = screenShotLocationPrefix + logProjectName + screenShotLocationSufix;
			File screenShotFile = ((TakesScreenshot) seleniumWebDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile, new File(screenShotPath));
			screenShotPath = "../screenshots/" + name + "_" + dateName + ".png";

			log.info("Exited the captureScreenShot method in CustomReportClass");
			return screenShotPath;
		} catch (IOException e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
			return null;
		} catch (ResourceCustomException e) {
			new ExceptionHandeler().resourceExceptionHandeler(e);
			return null;
		}
	}

	public synchronized static void createExtendReport() {
		try {
			log.info("Entered the createExtendReport method in CustomReportClass");
			String extendReportDestinationSufix = new ResourceRead().getResourceValueFromXML()
					.getProperty("ExtentReportDestinationLocation");
			String extendReportDestinationPrefix = new ResourceRead().getResourceValueFromXML()
					.getProperty("TestResultLocationPath");
			ExtentSparkReporter reporter = new ExtentSparkReporter(
					extendReportDestinationPrefix + logProjectName + extendReportDestinationSufix);
			extentReports = new ExtentReports();
			extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
			extentReports.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
			extentReports.setSystemInfo("Machine", "Windows 10" + "64 Bit");
			extentReports.setSystemInfo("Selenium", "3.12.0");
			extentReports.setSystemInfo("Maven", "3.6.2");
			extentReports.setSystemInfo("Java Version", "1.8.0_112");
			extentReports.attachReporter(reporter);
			log.info("Exited the createExtendReport method in CustomReportClass");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	public synchronized static void flushReport() {
		log.info("Entered the flushReport method in CustomReportClass");
		extentReports.flush();
		log.info("Exited the flushReport method in CustomReportClass");
	}

	public static void copyReport(String destinationFolder, boolean testStatus) {
		try {
			log.info("Entered the copyReport method in CustomReportClass");

			String sourceFolder, zipPath, zipFolderPath;

			sourceFolder = ResourceRead.getResourceValueFromXML().getProperty("ExtentReportSourceLocation");
			zipPath = ResourceRead.getResourceValueFromXML().getProperty("DeleteFileDestination") + "TestReport.zip";
			zipFolderPath = ResourceRead.getResourceValueFromXML().getProperty("DeleteFileDestination");

			FileUtils.copyDirectory(new File(sourceFolder), new File(destinationFolder));
			log.info("Exited the copyReport method in CustomReportClass");

		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		}
	}

	public static void zipDirectory(String sourceDirectoryPath, String zipPath) throws IOException {
		Path zipFilePath = Files.createFile(Paths.get(zipPath));

		try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFilePath))) {
			Path sourceDirPath = Paths.get(sourceDirectoryPath);

			Files.walk(sourceDirPath).filter(path -> !Files.isDirectory(path)).forEach(path -> {
				ZipEntry zipEntry = new ZipEntry(sourceDirPath.relativize(path).toString());
				try {
					zipOutputStream.putNextEntry(zipEntry);
					zipOutputStream.write(Files.readAllBytes(path));
					zipOutputStream.closeEntry();
				} catch (Exception e) {
					System.err.println(e);
				}
			});
		}
	}

	public String getStepResultStatus() {
		log.info("Entered the getStepResultStatus method in CustomReportClass");
		String result = test.getStatus().toString();
		log.info("Exited the getStepResultStatus method in CustomReportClass");
		return result;
	}

	public static void addStatusToReport(ReportStatus status, String message) {
		try {
			switch (status) {
			case PASS:
				test.log(Status.PASS, message);
				break;
			case INFO:
				test.log(Status.INFO, message);
				break;

			case SKIP:
				test.log(Status.SKIP, message);
				break;
			case WARNNING:
				test.log(Status.WARNING, message);
				break;
			case ATTACHSCREENSHOT: {
				String screenShotPath = captureScreenShot(seleniumWebDriver);
				test.addScreenCaptureFromPath(screenShotPath);
				break;
			}
			/*
			 * default: throw new Exception("Unsupported status");
			 */
			}
		} catch (Exception e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
		}
	}

	public static void addErrorStatusToReport(ReportStatus status, String message, Exception ex) {
		try {
			switch (status) {

			case FAIL:
				String screenShotPath = captureScreenShot(seleniumWebDriver);
				test.log(Status.FAIL, message
						+ "<div style=\" width: 35px; margin-left: 650px;\"><i class=\"material-icons\" style=\"cursor: pointer;\" data-featherlight="
						+ screenShotPath + " src=" + screenShotPath + " data-src=" + screenShotPath
						+ ">filter</i></div>");
				test.log(Status.FAIL, ex);
				break;

			default:
				throw new Exception("Unsupported status");
			}
		} catch (Exception e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
		}
	}
}
