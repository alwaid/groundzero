package com.alwaid.email.service;

public interface IEmailService {
	public void sendMail(String TOs, String CCs, String BCCs, String subject,
			String body, String filePath, String fileName);
}
