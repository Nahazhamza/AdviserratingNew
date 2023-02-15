package com.adviserratinguiautomation.emailreport;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.logger.FrameworkLogger;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import com.adviserratinguiautomation.utilities.GenericUtilities;

public class SendOutlookEmail {

	final static Logger log = Logger.getLogger(SendOutlookEmail.class);
	private static final String Server_SMTP = "smtp.office365.com";
	private static final int Server_Port = 587;
	private static String UserName, Password, from, to;
	// private static String subject = "Global Search Prod - Test Execution Report";
	private static String messageContent = "Hi, This is a system generated email, Please do not reply";
	private static String subjectPass, subjectFail;

	public static void sendEmail(String filePath, String fileName, boolean testStatus)
			throws ResourceCustomException, IOException {

		log.info("Entered Send Email Method of SendOutlookEmail");

		String decodePassword;
		// In case of Pass
		subjectPass = System.getProperty("TestType") + " " + System.getProperty("Environment") + " "
				+ "Execution Report - Test Pass";
		// In case of failure
		subjectFail = System.getProperty("TestType") + " " + System.getProperty("Environment") + " "
				+ "Execution Report - Test Failure";

		UserName = ResourceRead.getEmailConfigValue().getProperty("senderUsername");
		Password = ResourceRead.getEmailConfigValue().getProperty("senderPassword");

		from = ResourceRead.getEmailConfigValue().getProperty("senderUsername");
		// Will be from CI/CD
		// to = ResourceRead.getEmailConfigValue().getProperty("recepients");
		to = System.getProperty("Recepients");
		Properties props = new Properties();
		props.put("mail.smtp.host", Server_SMTP);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", Server_Port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		System.out.println(UserName);
		System.out.println(Password);

		UserName = UserName.trim();
		Password = Password.trim();

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(UserName, Password);
			}
		});

		try {

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, to);
			// Send Status based on pass or failure
			if (testStatus) {
				msg.setSubject(subjectPass);
			} else {
				msg.setSubject(subjectFail);
			}

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setContent(ResourceRead.getEmailConfigValue().getProperty("emailMessageBody"), "text/html");
			// Create a multipart message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			MimeBodyPart attachPart = new MimeBodyPart();
			attachPart.attachFile(filePath);
			multipart.addBodyPart(attachPart);

			msg.setContent(multipart);
			// Send the Message
			Transport.send(msg);

			log.info("Exited Send Email Method of SendOutlookEmail");

		} catch (MessagingException e) {
			System.out.println("Sending Email Failed, exception: " + e);
		}

	}

}