package com.alwaid.email_component.service;

import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements IEmailService {

	public void sendMail(String TOs, String CCs, String BCCs, String subject,
			String body, String filePath, String fileName) {
		System.out.println("Email Sent Successfully");
	}

	public void sendMail(String TOs, String subject, String body) {
		System.out.println("Email Sent Successfully");
	}

}