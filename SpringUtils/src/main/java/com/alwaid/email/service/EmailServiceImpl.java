package com.alwaid.email.service;

import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements IEmailService {

	public void sendMail(String TOs, String CCs, String BCCs, String subject,
			String body, String filePath, String fileName) {
		System.out.print("Mail Sent Successfully...");

	}

}
