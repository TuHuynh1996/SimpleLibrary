package com.example.mail;

import org.springframework.stereotype.Service;

/**
 * The Class MailServiceImp.
 */
@Service
public class MailServiceImp implements MailService{

	/* (non-Javadoc)
	 * @see com.example.mail.MailService#SendMail()
	 */
	@Override
	public String SendMail() {

		return "test";
	}
	
	
}
