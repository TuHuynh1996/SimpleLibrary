package com.example.mail;

/**
 * The Interface MailService.
 */
public interface MailService {

	/**
	 * Send mail.
	 *
	 * @param mailMessage the mail message
	 * @return the string
	 * @throws Exception 
	 */
	public void SendMail(MailMessage mailMessage) throws Exception;
}
