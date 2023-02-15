package com.adviserratinguiautomation.emailreport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.adviserratinguiautomation.base.BasePage;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import com.adviserratinguiautomation.utilities.GenericUtilities;

public class SentEmailReport extends BasePage {

	final static Logger log = Logger.getLogger(SentEmailReport.class);
	private static String messageContent = "Hi, This is a system generated email, Please do not reply";
	private static String subjectPass, subjectFail;
	private static String userName, password;

	// Send the mail with attachments
	public static void sendEmailWithAttachments(String filePath, String fileName, boolean testStatus) {
		try {
			log.info("Entered sendEmailWithAttachments method in SentEmailReport class");
			String decodePwd;

			fileName = ResourceRead.getEmailConfigValue().getProperty("ZipFolderDestinationLocation");
			String to = System.getProperty("Recipients");

			subjectPass = System.getProperty("TestType") + " " + System.getProperty("Environment") + " "
					+ "Execution Report - Test Pass";
			// In case of failure
			subjectFail = System.getProperty("TestType") + " " + System.getProperty("Environment") + " "
					+ "Execution Report - Test Failure";

			// sets SMTP server properties
			Properties properties = new Properties();
			userName = ResourceRead.getEmailConfigValue().getProperty("gmailsenderUsername");
			password = ResourceRead.getEmailConfigValue().getProperty("gmailsenderPassword");
			properties.put("mail.smtp.host", ResourceRead.getEmailConfigValue().getProperty("host"));
			properties.put("mail.smtp.port", ResourceRead.getEmailConfigValue().getProperty("hostPort"));
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.user", userName);
			properties.put("mail.password", password);

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			};
			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(ResourceRead.getEmailConfigValue().getProperty("gmailsenderUsername")));
			InternetAddress[] toAddresses = InternetAddress.parse(System.getProperty("Recepients"));
			// ResourceRead.getEmailConfigValue().getProperty("recepients"));
			msg.setRecipients(Message.RecipientType.TO, toAddresses);

			if (testStatus) {
				msg.setSubject(subjectPass);
			} else {
				msg.setSubject(subjectFail);
			}
			// creates message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(ResourceRead.getEmailConfigValue().getProperty("emailMessageBody"), "text/html");

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			MimeBodyPart attachPart = new MimeBodyPart();
			attachPart.attachFile(filePath);
			multipart.addBodyPart(attachPart);
			msg.setContent(multipart);

			// sends the e-mail
			Transport.send(msg);
			log.info("Email had sent successfully");
			log.info("Entered sendEmailWithAttachments method in SentEmailReport class");
		} catch (ResourceCustomException | MessagingException | IOException ex) {
			log.info("Email had not sent successfully");
			new ExceptionHandeler().emailReportExceptionhandeler(ex);
		}
	}

	// Zip the Folder
	public static void zipFolder() throws Exception {

		log.info("Entered ZipFolder method");
		String sourceFolderPath, zipPath;
		Path source, dest;
		// Zip Source Folder Path
		sourceFolderPath = ResourceRead.getEmailConfigValue().getProperty("ZipFolderSourceLocation");
		zipPath = ResourceRead.getEmailConfigValue().getProperty("ZipFolderDestinationLocation");

		source = Paths.get(sourceFolderPath);
		dest = Paths.get(zipPath);

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest.toFile()));
		Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				zos.putNextEntry(new ZipEntry(source.relativize(file).toString()));
				Files.copy(file, zos);
				zos.closeEntry();
				return FileVisitResult.CONTINUE;
			}
		});

		zos.close();

		log.info("Exited ZipFolder method");
	}
}