package com.example.mail;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * The Class MailServiceImp.
 */
@Service
public class MailServiceImp implements MailService {

	/** The mail information. */
	@Autowired
	MailInformation mailInformation;

	/** The template engine. */
	@Autowired
	private TemplateEngine templateEngine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.mail.MailService#SendMail()
	 */
	@Override
	public void SendMail(MailMessage mailMessage) throws Exception {

		try {
			// Create a mail sender
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost(mailInformation.getHost());
			mailSender.setPort(mailInformation.getPort());
			mailSender.setUsername(mailInformation.getUsername());
			mailSender.setPassword(mailInformation.getPassword());
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			// props.put("mail.smtp.ssl.enable", "true");
			mailSender.setJavaMailProperties(props);

			// Create an email instance
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
			mimeMessageHelper.setFrom(mailMessage.getFrom() != null ? mailMessage.getFrom() : "");
			mimeMessageHelper.setTo(mailMessage.getTo() != null ? mailMessage.getTo() : new String[0]);
			mimeMessageHelper.setSubject(mailMessage.getSubject() != null ? mailMessage.getSubject() : "");
			mimeMessageHelper.setCc(mailMessage.getCc() != null ? mailMessage.getCc() : new String[0]);
			mimeMessageHelper.setBcc(mailMessage.getBcc() != null ? mailMessage.getBcc() : new String[0]);
			String text = generateMailHtml(mailMessage.getTemplate(), mailMessage.getVariables());
			mimeMessageHelper.setText(text, true);

			// Send mail
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Generate mail html.
	 *
	 * @param templateFileName the template file name
	 * @param variables        the variables
	 * @return the string
	 */
	private String generateMailHtml(String templateFileName, Map<String, Object> variables) {
		String output = this.templateEngine.process(templateFileName, new Context(Locale.getDefault(), variables));

		return output;
	}

}
