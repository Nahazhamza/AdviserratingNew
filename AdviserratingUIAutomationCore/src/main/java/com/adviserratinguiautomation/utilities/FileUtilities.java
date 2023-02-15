package com.adviserratinguiautomation.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;

public class FileUtilities {

	final static Logger log = Logger.getLogger(FileUtilities.class);

	/**
	 * @return get the fileName of the text file
	 */
	public static String CreateTextFile(String appType) throws IOException, ResourceCustomException {
		log.info("Entered CreateTextFile method of FileUtilities");

		String fileName = new ResourceRead().getResourceValueFromXML().getProperty("TextFileDestination") + "TextFile"
				+ "_" + appType + "_" + DateTime.now().toString("yyyyMMddHHmmss");
		File file = new File(fileName);
		if (!file.exists()) {

			file.createNewFile();
		}

		log.info("Exited CreateTextFile method of FileUtilities");
		// Return the filename
		return fileName;
	}

	/**
	 * @return get the fileName of the text file
	 */
	public static String CreateGlobalSearchFile() throws IOException, ResourceCustomException {
		log.info("Entered CreateGlobalSearchFile method of FileUtilities");

		String fileName = new ResourceRead().getResourceValueFromXML().getProperty("TextFileDestination")
				+ "TestResults.txt";
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}

		log.info("Exited CreateGlobalSearchFile method of FileUtilities");
		// Return the filename
		return fileName;
	}

	/**
	 * Write the data to the text file Parameters - Text to write, Name of the file
	 */

	public static void WriteToTextFile(String text, String fileName) throws IOException, ResourceCustomException {

		log.info("Entered WriteToTextFile method of FileUtilities");

		// Create new file if it does not exist +
		FileOutputStream outputStream = new FileOutputStream(fileName, true);
		byte[] strToBytes = text.getBytes();
		outputStream.write(strToBytes);
		// New Line
		outputStream.write("\n".getBytes());
		// close
		outputStream.close();

		log.info("Exited WriteToTextFile method of FileUtilities");

	}

	/**
	 * Removes the file with extension Parameters - String folder, extension @
	 */

	public static void deleteFileWithExtension(String directory, String extension) throws IOException {
		log.info("Entered deleteFileWithExtension method of FileUtilities");

		String[] data = null;
		int len;
		// Check if there are multiple extensions to remove
		if (extension.contains(";")) {
			data = extension.split(";");
			len = data.length;
		} else {
			len = 1;
		}

		File dir = new File(directory);

		for (int i = 0; i < len; i++) {

			for (File file : dir.listFiles()) {

				if (file.getName().endsWith(data[i])) {
					file.delete();
				}
			}
		}

		log.info("Exited deleteFileWithExtension method of FileUtilities");
	}

	/**
	 * Validate the file count Parameters - String fodlerPath @
	 */

	public static int validateFileCount(String downloadPath) {

		log.info("Entered validateFileCount method of FileUtilities");

		File directory = new File(downloadPath);
		File[] content = directory.listFiles();

		try {
			log.info("Exited validateFileCount method of FileUtilities");

			return content.length;
		} catch (Exception e) {
			log.info("Exited validateFileCount method of FileUtilities");

			// In case of no files
			return 0;
		}

	}

	/**
	 * Validate the file is downloaded in the folder Parameters - String fodlerPath
	 * 
	 * @throws InterruptedException @
	 */

	public static boolean validateFileDownloaded(String downloadPath, int previousCount) throws InterruptedException {

		log.info("Entered validateFileDownloaded method of FileUtilities ");

		boolean fileFound = false;
		int iCounter = 0;
		File[] content;
		File directory;

		directory = new File(downloadPath);

		while (iCounter < 120) {
			content = directory.listFiles();

			// Check if the count is same
			if (content.length > previousCount) {
				iCounter = 120;
				break;
			} else {
				Thread.sleep(1000);
			}
			if (iCounter == 119) {
				return false;
			}
		}

		log.info("Exited  validateFileDownloaded method of FileUtilities");
		return true;
	}

}