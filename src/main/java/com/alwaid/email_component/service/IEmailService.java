package com.alwaid.email_component.service;

public interface IEmailService {
	public void sendMail(String TOs, String CCs, String BCCs, String subject,
			String body, String filePath, String fileName);

	public void sendMail(String TOs, String subject, String body);
}
