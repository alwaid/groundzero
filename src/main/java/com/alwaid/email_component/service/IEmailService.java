package com.alwaid.email_component.service;

import java.io.IOException;

import javax.mail.MessagingException;

public interface IEmailService {
	public void sendMail(String TOs, String CCs, String BCCs, String subject,
			String body, String filePath, String fileName) throws IOException,
			MessagingException;

	public void sendMail(String TOs, String subject, String body)
			throws IOException, MessagingException;
}
