package com.adviserratinguiautomation.logger;

import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.LogDirectoryCreationException;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.driver.ADRWebDriver;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import com.adviserratinguiautomation.utilities.FileUtilities;
import com.adviserratinguiautomation.utilities.GenericUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class FrameworkLogger {
	final static Logger log = LogManager.getLogger(FrameworkLogger.class);

	//final static Logger log = Logger.getLogger(FrameworkLogger.class);

	public static String logProjectName = "";
	public static final String LOG_FOLDER_PARENT_PATH = "LogFolderParentPath";

	public static void checkExtentReportFolder() throws ResourceCustomException, IOException {
		String extentsourceFolder = ResourceRead.getResourceValueFromXML().getProperty("ExtentReportSourceLocation");
		File extentFolderFile = new File(extentsourceFolder);
		if (extentFolderFile.exists()) {
			// Delete the folder
			extentFolderFile.delete();
		}

	}

	public static String createLogReport() {

		log.info("Entered the createLogReport method in TestRunAdviserRating");
		String folder = "";
		String source, dest;

		try {

			// Delete the Files with extension
			source = ResourceRead.getResourceValueFromXML().getProperty("DeleteFileSource");
			dest = ResourceRead.getResourceValueFromXML().getProperty("DeleteFileDestination");
			FileUtilities.deleteFileWithExtension(source, ".png;.PNG;.txt;.zip;.jpg");
			FileUtilities.deleteFileWithExtension(dest, ".png;.PNG;.txt;.zip;.jpg");
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			logProjectName = ResourceRead.getResourceValueFromXML().getProperty("ProjectName") + "_" + dateName;
			String testResultFolderParentPath = ResourceRead.getResourceValueFromXML()
					.getProperty(LOG_FOLDER_PARENT_PATH);
			File testResultFolderFile = new File(testResultFolderParentPath);
			if (!testResultFolderFile.exists()) {
				testResultFolderFile.mkdir();
				File logFolderFile = new File(testResultFolderParentPath + "/log");
				logFolderFile.mkdir();
				folder = createChildFolders(testResultFolderParentPath);
			} else {
				folder = createChildFolders(testResultFolderParentPath);
			}
			// Return the folder path
			log.info("Exited the createLogReport method in TestRunAdviserRating");

		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		} catch (LogDirectoryCreationException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
		return folder;

	}

	public static void createSubFolders(File subParentFolder)
			throws LogDirectoryCreationException, ResourceCustomException, IOException {
		log.info("Entered the createSubFolders method in TestRunAdviserRating");
		String TEST_REPORT = "TestReport";

		if (subParentFolder.exists()) {
			List<String> subFolders = new ArrayList<>();
			Properties property = ResourceRead.getResourceValueFromXML();
			subFolders.add(property.getProperty(TEST_REPORT));
			for (String sub : subFolders) {
				File logChildFoldersFile = new File(subParentFolder, sub);
				logChildFoldersFile.mkdirs();
			}
		} else {
			throw new LogDirectoryCreationException(logProjectName + "directory not found");
		}
		log.info("Exited the createSubFolders method in TestRunAdviserRating");
	}

	public static String createChildFolders(String logFolderParentPath)
			throws LogDirectoryCreationException, ResourceCustomException, IOException {
		log.info("Entered the createChildFolders method in TestRunAdviserRating");
		String subParentFolderPath = logFolderParentPath + logProjectName;
		File subParentFolder = new File(subParentFolderPath);
		if (!subParentFolder.exists()) {
			subParentFolder.mkdir();
			// Not needed
			// createSubFolders(subParentFolder);

		} else {
			throw new LogDirectoryCreationException("Test Result directory not found");
		}

		log.info("Exited the createChildFolders method in TestRunAdviserRating");
		return subParentFolderPath;

	}
}