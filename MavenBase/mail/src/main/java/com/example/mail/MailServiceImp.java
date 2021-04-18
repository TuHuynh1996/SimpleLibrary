package com.example.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * The Class MailServiceImp.
 */
@Service
public class MailServiceImp implements MailService{
	
	/** The mail information. */
	@Autowired
	MailInformation mailInformation;

	/* (non-Javadoc)
	 * @see com.example.mail.MailService#SendMail()
	 */
	@Override
	public String SendMail() {

		try {
			 // Create a mail sender
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost(mailInformation.getHost());
	        mailSender.setPort(mailInformation.getPort());
	        mailSender.setUsername(mailInformation.getUsername());
	        mailSender.setPassword(mailInformation.getPassword());
	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
//	        props.put("mail.smtp.ssl.enable", "true");
	        mailSender.setJavaMailProperties(props);

	        // Create an email instance
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setFrom("tuhuynh962285164@gmail.com");
	        mailMessage.setTo("tuhuynh.work@outlook.com.vn");
	        mailMessage.setText("test");

//	         Send mail
	        mailSender.send(mailMessage);
			return "Successful";
		}catch (Exception e) {
			return "Failed: "+ e.getMessage();
		}

	}
	
	
}
