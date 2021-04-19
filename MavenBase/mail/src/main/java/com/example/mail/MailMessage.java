package com.example.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

/**
 * The Class MailMessage.
 */
@SuppressWarnings("serial")
public class MailMessage extends SimpleMailMessage {

	/** The template file name. */
	private String templateFileName;

	/** The variables. */
	private Map<String, Object> variables;

	/**
	 * Sets the template.
	 *
	 * @return the string
	 */
	public String getTemplate() {
		return templateFileName;
	}

	/**
	 * Gets the variables.
	 *
	 * @return the variables
	 */
	public Map<String, Object> getVariables() {
		return variables;
	}

	/**
	 * Sets the template.
	 *
	 * @param templateFileName the template file name
	 * @param variables        the variables
	 */
	public void setTemplate(String templateFileName, Map<String, Object> variables) {
		this.templateFileName = templateFileName;
		this.variables = variables;
		this.setText(null);
	}

	/**
	 * Sets the variables.
	 *
	 * @param templateFileName the new variables
	 */
	public void setTemplate(String templateFileName) {
		this.templateFileName = templateFileName;
		this.variables = new HashMap<>();
		this.setText(null);
	}

}
