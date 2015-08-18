package com.alwaid.email.bean;

public interface IEmailBean {
	public void setSubject(String subject);

	public void setTOs(String tOs);

	public void setCCs(String cCs);

	public void setBCCs(String bCCs);

	public void setFilePath(String filePath);

	public void setFileName(String fileName);

	public void setBody(String body);

	public String getSubject();

	public String getTOs();

	public String getCCs();

	public String getBCCs();

	public String getFilePath();

	public String getFileName();

	public String getBody();

	public void sendMail();
}
