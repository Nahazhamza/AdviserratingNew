package com.adviserratinguiautomation.emailreport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import com.adviserratinguiautomation.utilities.GenericUtilities;

public class ParseEmail {

	final static Logger log = Logger.getLogger(ParseEmail.class);

	/**
	 * Validate the email Parameters - Office 365 UserName and Password
	 */
	public static boolean OutlookEmail(String username, String password, String searchFolder, String emailText,
			String SubText) throws Exception {

		log.info("Entered Outlook method of DashboardPage");

		boolean emailFound = false;
		boolean emailBody = false;
		String[] emailVal;
		int i = 1;

		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imap");
		props.setProperty("mail.imap.ssl.enable", "true");
		props.setProperty("mail.imaps.partialfetch", "false");
		props.setProperty("Dmail.imap.auth.plain.disable", "true");
		props.put("mail.mime.base64.ignoreerrors", "true");
//			    props.put("mail.imaps.ssl.trust", "*");

		Session mailSession = Session.getInstance(props);
		// mailSession.setDebug(true);
		Store store = mailSession.getStore("imap");
		store.connect("outlook.office365.com", username, password);

		Folder folder = store.getFolder(searchFolder);
		folder.open(Folder.READ_WRITE);

		// Loop through the email messages
		while (!emailFound) {
			Message[] messages = folder.getMessages();
			for (Message mail : messages) {

				if (!mail.isSet(Flags.Flag.SEEN)) {
					if (mail.getSubject().contains(SubText)) {
						emailBody = getEmailBody(mail, emailText);
						return emailBody;
					}

				}

			}
			// Wait for 1 seconds
			if (!emailFound) {

				Thread.sleep(5000);
				i++;
			}
			// Maximum wait time of 5 minutes
			if (i == 60) {
				return false;
			}

		}

		if (emailBody && emailFound) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Validate the email Parameters - GMail UserName and Password
	 */

	public static void Gmail(String username, String password, String validation, String subText) throws Exception {

		log.info("Entered Gmail method of DashboardPage");
		String data;
		String[] emailData;
		boolean emailFound = false, regoFound = false, locFound = false, instFound = false;
		boolean pickTimeFound = false, returnTimeFound = false, dataFound = false;
		int iCount = 1;
		emailData = validation.split(";");
		// EmailFolder emailFolder;
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect("imap.gmail.com", username, password);

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);

		while (!emailFound) {

			Message[] messages = folder.getMessages();

			for (Message mail : messages) {

				if (!mail.isSet(Flags.Flag.SEEN)) {

					if (mail.getSubject().trim().toLowerCase().contains(subText.toLowerCase().trim())) {

						data = getText(true, mail);
						emailFound = true;

						// PickUpTime
						if (data.trim().toLowerCase().contains(emailData[0].trim().toLowerCase())) {
							pickTimeFound = true;

						}
						// ReturnTime
						if (data.toLowerCase().contains(emailData[1].toLowerCase())) {
							returnTimeFound = true;

						}

						// Rego
						if (data.toLowerCase().contains(emailData[2].toLowerCase())) {
							regoFound = true;

						}

						if (pickTimeFound && returnTimeFound && regoFound) {
							dataFound = true;
							break;
						}

					}
				}

			}

			// Maximum wait time of 5 minutes
			if (iCount == 150) {

				if (!dataFound) {
					Assertions.fail("The  email for rego:" + emailData[2] + " " + "PickupLocation:" + emailData[3] + " "
							+ "is not found");

				}

			}
			// Wait if not found
			if (!emailFound) {

				Thread.sleep(2000);
				iCount++;

			} else {

				if (dataFound) {
					log.info("Exited Gmail method of DashboardPage");
					return;
				}

			}

		}

	}

	/**
	 * Get the contents of the email
	 */

	public static boolean getEmailBody(Message email, String emailText) throws IOException, MessagingException {

		String line, emailContent;
		String[] emailVal = null;
		StringBuffer bufferEmailContentEncoded = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));
		while ((line = reader.readLine()) != null) {
			bufferEmailContentEncoded.append(line);
		}

		emailContent = bufferEmailContentEncoded.toString();
		emailContent = GenericUtilities.removeSpaces(emailContent);
		emailContent = GenericUtilities.removeSpecialCharacters(emailContent);

		if (!emailText.isEmpty()) {
			// Split if there are multiple values
			if (emailText.contains(";")) {
				emailVal = emailText.split(";");

				// Loop through
				for (int i = 0; i < emailVal.length; i++) {
					if (!emailContent.toLowerCase().contains(emailVal[i].toLowerCase())) {
						return false;
					}
				}
			} else {

				if (emailContent.toLowerCase().contains(emailText.toLowerCase())) {

					return true;
				} else {
					return false;
				}
			}

		}

		return true;

	}

	public static String getText(boolean textIsHtml, Part p) throws MessagingException, IOException {

		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			textIsHtml = p.isMimeType("text/html");
			return s;
		}

		if (p.isMimeType("multipart/alternative")) {

			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)

						text = getText(textIsHtml, bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(textIsHtml, bp);
					if (s != null)
						return s;
				} else {
					return getText(textIsHtml, bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(textIsHtml, mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}

		return null;
	}

}