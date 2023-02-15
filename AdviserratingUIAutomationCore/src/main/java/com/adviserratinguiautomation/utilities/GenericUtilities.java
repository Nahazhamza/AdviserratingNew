package com.adviserratinguiautomation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

public class GenericUtilities {

	private static Random rnd = new Random();
	final static Logger log = Logger.getLogger(GenericUtilities.class);

	/**
	 * Generate random vehicle value Parameters - None
	 */
	public static String getRandomVehicle() {
		log.info("Entered getRandomVehicle method of GenericUtilities ");

		int randomNum;

		// String array
		String[] car = { "Kia", "Suzuki", "Audi", "Toyota", "Holden", "Honda", "Nissan", "Jeep", "Ford", "BMW",
				"Nissan", "Subaru", "Volkswagen", "Mitsubishi", "Hyundai", "Volvo", "Skoda", "Isuzu" };
		randomNum = rnd.nextInt(car.length);

		log.info("Exited getRandomVehicle method of GenericUtilities");
		return car[randomNum];

	}

	/**
	 * Generate random vehicle value for Quoting for FleetPartners Parameters - None
	 */
	public static String getRandomQuotingVehicleFP() {
		log.info("Entered getRandomQuotingVehicle method of GenericUtilities ");

		int randomNum;

		// String array
		String[] car = { "JEEP", "TOYOTA" };

		randomNum = rnd.nextInt(car.length);

		log.info("Exited getRandomQuotingVehicle method of GenericUtilities");
		return car[randomNum];

	}

	/**
	 * Generate random vehicle value for Quoting FleetPlus Parameters - None
	 */
	public static String getRandomQuotingVehicleFPLUS() {
		log.info("Entered getRandomQuotingVehicle method of GenericUtilities ");

		int randomNum;

		// String array
		String[] car = { "HYUNDAI" };

		randomNum = rnd.nextInt(car.length);

		log.info("Exited getRandomQuotingVehicle method of GenericUtilities");
		return car[randomNum];

	}

	/**
	 * Used to check Level1 security code for FleetPartnersNZ Check if value exists
	 * in the array Parameters - Value to check
	 */

	public static boolean FPFirstLevelValue(String value) {
		log.info("Entered FPFirstLevelValue method of GenericUtilities");

		String[] compCode = new String[] { "100073", "101370" };

		// Convert String Array to List
		List<String> list = Arrays.asList(compCode);

		if (list.contains(value)) {
			return true;

		}

		return false;

	}

	/**
	 * Used to check Level2 security code for FleetPartnersNZ Check if value exists
	 * in the array Parameters - Value to check
	 */

	public static boolean FPSecondLevelValue(String value) {
		log.info("Entered FPFirstLevelValue method of GenericUtilities");

		String[] compCode = new String[] { "101370" };

		// Convert String Array to List
		List<String> list = Arrays.asList(compCode);

		if (list.contains(value)) {

			return true;

		}

		return false;

	}

	/**
	 * Generate random Driver Name Parameters - None
	 */
	public static String getRandomDriverName() {
		log.info("Entered getRandomDriverName method of GenericUtilities");
		int randomNum;

		// String array
		String[] name = { "ab", "cd", "ef", "gh", "gj", "kl", "mn", "ar" };
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomDriverName method of GenericUtilities");
		return name[randomNum];

	}

	/**
	 * Generate random Company Name Parameters - None
	 */
	public static String getRandomCompanyName() {
		log.info("Entered getRandomCompanyName method of GenericUtilities");
		int randomNum;

		// String array
		String[] name = { "bank of new zealand", "anz banking", "woodside energy" };
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomCompanyName method of GenericUtilities");
		return name[randomNum];

	}

	/**
	 * Generate random Company Name for Product Manager Role Parameters - None
	 */
	public static String getRandomProductRoleCompanyName() {
		log.info("Entered getRandomProductRoleCompanyName method of GenericUtilities");
		int randomNum;

		// String array
		String[] name = { "AAN0481", "CCA1" };
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomProductRoleCompanyName method of GenericUtilities");
		return name[randomNum];

	}

	/**
	 * Generate random Business Entity Parameters - None
	 */
	public static String getRandomBusinessEntity() {
		log.info("Entered getRandomBusinessEntity method of GenericUtilities");
		int randomNum;

		// String array
		String[] name = { "FleetPartners", "FleetPlus", "FleetSmart" };
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomBusinessEntity method of GenericUtilities");
		return name[randomNum];

	}

	/**
	 * Generate random User Role Parameters - None
	 */
	public static String getRandomUserRole() {
		log.info("Entered getRandomUserRole method of GenericUtilities");
		int randomNum;

		// String array

		String[] name = { "Fleet Manager" };
		// "People Manager"};
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomUserRole method of GenericUtilities");
		return name[randomNum];

	}

	/**
	 * Generate random Email Parameters - None
	 */
	public static String getRandomEmail() {
		log.info("Entered getRandomEmail method of GenericUtilities");
		int randomNum;

		// String array
		String[] name = { "@yopmail.com", "@adviserrating.com" };
		// "Product Manager", "Company Admin", "Human Resources", "People manager"
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomEmail method of GenericUtilities");
		return name[randomNum];

	}

	/**
	 * Generate random value for username search Parameters - None
	 */
	public static String getRandomUserList() {
		log.info("Entered getRandomUserListSearch method of GenericUtilities");
		int randomNum;

		// String array
		String[] name = { "ab", "cd", "ef", "gh" };
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomUserListSearch method of GenericUtilities");
		return name[randomNum];

	}

	/**
	 * Generate random User Driver Option Parameters - None
	 */
	public static String getRandomUserDriverOption() {
		log.info("Entered getRandomUserDriverOption method of GenericUtilities");
		int randomNum;

		// String array
		String[] name = { "Yes", "No" };
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomUserDriverOption method of GenericUtilities");
		return name[randomNum];

	}

	/**
	 * Generate random Driver Name Parameters - None
	 */

	public static String getRandomFuelRego() {
		log.info("Entered getRandomFuelRego method of GenericUtilities");
		int randomNum;

		// String array
		// EJD72F"
		String[] name = { "1DB3RT", "S371CFN" };
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomFuelRego method of GenericUtilities");
		return name[randomNum];

	}

	public static String getRandomTollRego() {
		log.info("Entered getRandomTollRego method of GenericUtilities");
		int randomNum;

		// String array
		String[] name = { "CR92LK" };
		randomNum = rnd.nextInt(name.length);

		log.info("Exited getRandomTollRego method of GenericUtilities");
		return name[randomNum];

	}

	/**
	 * Generate random number Parameters - None
	 */

	public static String getRandomNumberAU() {
		log.info("Entered getRandomNumberAU method of GenericUtilities");
		int randomNum;

		// String array
		String[] number = { "0412", "0422" };
		randomNum = rnd.nextInt(number.length);

		log.info("Exited getRandomNumberAU method of GenericUtilities");
		return number[randomNum];

	}

	/**
	 * Generate random number from the available Parameters - None
	 */

	public static String getRandomAddress() {
		log.info("Entered getRandomAddress method of GenericUtilities");
		int randomNum;

		// String array
		String[] number = { "110", "220" };
		randomNum = rnd.nextInt(number.length);

		log.info("Exited getRandomAddress method of GenericUtilities");
		return number[randomNum];

	}

	public static String getRandomNumberNZ() {
		log.info("Entered getRandomNumberNZ method of GenericUtilities");
		int randomNum;

		// String array
		String[] number = { "021", "022", "023", "024", "025", "026", "027", "028", "029" };
		randomNum = rnd.nextInt(number.length);
		log.info("Exited getRandomNumberNZ method of GenericUtilities");
		return number[randomNum];

	}

	/**
	 * Generate random alphanumeric value Parameters - Data format, size of the
	 * format
	 */
	public static String getRandomAlphaNumeric(String data, int size) {
		log.info("Entered getRandomAlphaNumeric method of GenericUtilities");
		// create StringBuffer size of
		StringBuilder sb = new StringBuilder(size);

		// Loop through the rows
		for (int i = 0; i < size; i++) {

			// generate a random number
			int index = (int) (data.length() * Math.random());
			// add Character one by one in end of sb
			sb.append(data.charAt(index));
		}

		log.info("Exited getRandomAlphaNumeric method of GenericUtilities");
		return sb.toString();

	}

	/**
	 * Generate random Integer Parameters - Min and Max value
	 */
	public static int generateRandomNumber(int min, int max) {
		log.info("Entered getRandomAlphaNumber method of GenericUtilities");

		return min + (int) (Math.random() * ((max - min) + 1));

	}

	/**
	 * Get the next day value Parameters - Number of Days to Add
	 */

	public static int getNextDay(int numofDays) {
		log.info("Entered getNextDay method of GenericUtilities");
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		log.info("Exited getNextDay method of GenericUtilities");
		return localDate.getDayOfMonth() + numofDays;

	}

	/**
	 * Get the month and year of today Parameters - None
	 */

	public static String getMonthYear() {
		log.info("Entered getMonthYear method of GenericUtilities");
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String[] newVal = localDate.toString().split("-");
		log.info("Exited getMonthYear method of GenericUtilities");
		return newVal[1] + newVal[0];

	}

	/**
	 * Get the current time in HH:MM and AM/PM format Parameters - None
	 */

	public static String getCurrentTime() {
		log.info("Entered getRandomVehicle method of GenericUtilities");
		DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
		log.info("Exited getRandomVehicle method of GenericUtilities");
		return dateFormat.format(new Date()).toString();

	}

	/**
	 * Get the current time in dd MMM yyy format e.g 06 Jul 20 Parameters -
	 * NumofDays - 0 or 1
	 * 
	 * @throws ParseException
	 */

	public static String getBookingDate(int numofDays) throws ParseException {
		log.info("Entered getBookingDate method of GenericUtilities");

		int nextDay, lastDay, today;

		Calendar c = Calendar.getInstance();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yy");
		String strDate = dateFormat.format(date);
		String day = new SimpleDateFormat("dd").format(date);
		today = Integer.parseInt(day);
		lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);

		if (numofDays == 0) {
			if (today > lastDay) {
				c.setTime(dateFormat.parse(strDate));
				c.set(Calendar.DAY_OF_MONTH, 1);
				c.add(Calendar.MONTH, 1);
				return dateFormat.format(c.getTime()).toString();
			} else {
				return strDate;
			}
		} else if (numofDays == 1) {
			if (today > lastDay) {
				c.setTime(dateFormat.parse(strDate));
				c.set(Calendar.DAY_OF_MONTH, 2);
				c.add(Calendar.MONTH, 1);
				return dateFormat.format(c.getTime()).toString();
			} else {
				nextDay = today + 1;
				if (nextDay > lastDay) {
					c.setTime(dateFormat.parse(strDate));
					c.set(Calendar.DAY_OF_MONTH, 1);
					c.add(Calendar.MONTH, 1);
					return dateFormat.format(c.getTime()).toString();
				} else {
					c.setTime(dateFormat.parse(strDate));
					c.add(Calendar.DATE, 1);
					return dateFormat.format(c.getTime()).toString();
				}
			}

		}

		return dateFormat.format(c.getTime()).toString();

	}

	/**
	 * Removes the spaces between characters Parameters - String @
	 */

	public static String removeSpaces(String name) {
		log.info("Entered removeSpaces method of GenericUtilities");
		String val;
		val = name.replaceAll("\\s+", "");
		log.info("Exited removeSpaces method of GenericUtilities");
		return val;

	}

	/**
	 * Removes the special characters in the string Parameters - String @
	 */

	public static String removeSpecialCharacters(String name) {
		log.info("Entered removeSpecialCharacters method of GenericUtilities");
		String val;
		val = name.replaceAll("[^A-Za-z0-9]", "");
		log.info("Exited removeSpecialCharacters method of GenericUtilities");
		return val;
	}

	/**
	 * get the current date in mm/dd/yyyy format Parameters - None @
	 */

	public static String getCurrentDate() {
		log.info("Entered getCurrentDate method of GenericUtilities");
		String strDate;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		strDate = formatter.format(date);
		log.info("Exited getCurrentDate method of GenericUtilities");
		return strDate;
	}

	/**
	 * get the yesterday date in mm/dd/yyyy format Parameters - None @
	 */

	public static String getPreviousDate() {
		log.info("Entered getPreviousDate method of GenericUtilities");
		String[] data;
		String dateVal;
		LocalDate yesterday = LocalDate.now().minusDays(1L);
		data = yesterday.toString().split("-");
		dateVal = data[2] + "/" + data[1] + "/" + data[0];
		log.info("Exited getPreviousDate method of GenericUtilities");
		return dateVal;
	}

	/**
	 * Encode the data Parameters - Data to encode @
	 */

	public static String encodeData(String data) {
		log.info("Entered encodeData method of GenericUtilities");
		return Base64.getEncoder().encodeToString(data.getBytes());

	}

	/**
	 * Decode the data Parameters - Data to decode @
	 */

	public static String decodeData(String data) {
		log.info("Entered decodeData method of  GenericUtilities");
		String retVal;
		byte[] decoded = Base64.getDecoder().decode(data);
		retVal = new String(decoded);

		log.info("Exited decodeData method of GenericUtilities");
		return retVal;

	}

	/**
	 * Convert date from dd/mm/yyyy to dd mmm yyyyy Parameters - Data to convert
	 * 
	 * @throws ParseException @
	 */

	public static String convertDateFormat(String dateValue) {

		log.info("Entered decodeData method of GenericUtilities");

		String[] dateArr;
		String month = "";
		dateArr = dateValue.split("/");

		// Replace the numeric format to mon format
		if (dateArr[1].equals("01")) {
			month = "January";
		} else if (dateArr[1].equals("02")) {
			month = "February";
		} else if (dateArr[1].equals("03")) {
			month = "March";
		} else if (dateArr[1].equals("04")) {
			month = "April";
		} else if (dateArr[1].equals("05")) {
			month = "May";
		} else if (dateArr[1].equals("06")) {
			month = "June";
		} else if (dateArr[1].equals("07")) {
			month = "July";
		} else if (dateArr[1].equals("08")) {
			month = "August";
		} else if (dateArr[1].equals("09")) {
			month = "September";
		} else if (dateArr[1].equals("10")) {
			month = "October";
		} else if (dateArr[1].equals("11")) {
			month = "November";
		} else if (dateArr[1].equals("12")) {
			month = "December";
		}

		log.info("Exited decodeData method of GenericUtilities");

		return dateArr[0] + " " + month + " " + dateArr[2];

	}

}
