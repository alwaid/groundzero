package com.alwaid.email_component.bean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alwaid.email_component.service.IEmailService;

@Component
@Scope("prototype")
public class EmailBean implements IEmailBean {
	private String subject;
	private String TOs;
	private String CCs;
	private String BCCs;
	private String filePath;
	private String fileName;
	private String body;

	@Autowired
	IEmailService emailService;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTOs() {
		return TOs;
	}

	public void setTOs(String tOs) {
		TOs = tOs;
	}

	public String getCCs() {
		return CCs;
	}

	public void setCCs(String cCs) {
		CCs = cCs;
	}

	public String getBCCs() {
		return BCCs;
	}

	public void setBCCs(String bCCs) {
		BCCs = bCCs;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean sendMail() {
		boolean isSentMail = false;

		if (StringUtils.isNotBlank(this.TOs)
				&& StringUtils.isNotBlank(this.subject)
				&& StringUtils.isNotBlank(this.body)) {
			emailService.sendMail(this.TOs, this.subject, this.body);
			isSentMail = true;
		} else if (StringUtils.isNotBlank(this.CCs)
				&& StringUtils.isNoneBlank(this.BCCs)
				&& StringUtils.isNotBlank(this.filePath)
				&& StringUtils.isNotBlank(this.fileName)) {
			emailService.sendMail(this.TOs, this.CCs, this.BCCs, this.subject,
					this.body, this.filePath, this.fileName);
			isSentMail = true;
		} else {
			isSentMail = false;
		}

		return isSentMail;
	}

}
