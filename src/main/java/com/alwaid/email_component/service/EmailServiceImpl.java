package com.alwaid.email_component.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.ow2.bonita.connector.impl.email.SMTPAuthenticator;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements IEmailService {
	Properties emailServiceConfiguration = null;
	InputStream inputStream = null;

	public EmailServiceImpl() throws IOException {
		emailServiceConfiguration = new Properties();
		// inputStream = getClass()
		// .getResourceAsStream(
		// "/com/alwaid/email_component/properties/configuration.properties");
		inputStream = getClass()
				.getResourceAsStream("configuration.properties");
		emailServiceConfiguration.load(inputStream);
		inputStream.close();
	}

	public void sendMail(String TOs, String subject, String body)
			throws IOException, MessagingException {

		SMTPAuthenticator auth = new SMTPAuthenticator(
				emailServiceConfiguration.getProperty("mail.userid"),
				emailServiceConfiguration.getProperty("mail.password"));

		Session session = Session.getInstance(emailServiceConfiguration, auth);
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailServiceConfiguration
				.getProperty("mail.userid"), "Alwaid Team"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(TOs));
		message.setRecipient(Message.RecipientType.CC, new InternetAddress(
				emailServiceConfiguration.getProperty("mail.userid")));
		message.setSubject(subject);
		message.setContent(body, "text/html; charset=utf-8");
		Transport.send(message);

		System.out.println("Mail Sent Successfully");
	}

	public void sendMail(String TOs, String CCs, String BCCs, String subject,
			String body, String filePath, String fileName)
			throws UnsupportedEncodingException, MessagingException {
		SMTPAuthenticator auth = new SMTPAuthenticator(
				emailServiceConfiguration.getProperty("mail.userid"),
				emailServiceConfiguration.getProperty("mail.password"));

		Session session = Session.getInstance(emailServiceConfiguration, auth);
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailServiceConfiguration
				.getProperty("mail.userid"), "Alwaid Team"));

		StringTokenizer st = null;

		// Getting & Setting TOs Recipient(s)
		st = new StringTokenizer(TOs, ",");
		while (st.hasMoreTokens()) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					st.nextToken()));
		}
		st = null;

		// Getting & Setting CCs Receipient(s)
		st = new StringTokenizer(CCs, ",");
		while (st.hasMoreTokens()) {
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(
					st.nextToken()));
		}
		st = null;

		// Getting & Setting BCCs Receipient(s)
		st = new StringTokenizer(BCCs, ",");
		while (st.hasMoreTokens()) {
			message.addRecipient(Message.RecipientType.BCC,
					new InternetAddress(st.nextToken()));
		}
		st = null;

		message.setSubject(subject);

		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(body, "text/html; charset=utf-8");

		// Create a Multipart
		Multipart multipart = new MimeMultipart();
		// Add part one
		multipart.addBodyPart(messageBodyPart);
		// // Part two is attachment // // Create second body part
		messageBodyPart = new MimeBodyPart();
		// Get the attachment
		DataSource source = new FileDataSource(filePath);
		// Set the data handler to the attachment
		messageBodyPart.setDataHandler(new DataHandler(source));
		// Set the filename
		messageBodyPart.setFileName(fileName);
		// Add part two
		multipart.addBodyPart(messageBodyPart);
		// Put parts in message
		message.setContent(multipart);

		Transport.send(message);

		System.out.println("Mail Sent Successfully");
	}

}