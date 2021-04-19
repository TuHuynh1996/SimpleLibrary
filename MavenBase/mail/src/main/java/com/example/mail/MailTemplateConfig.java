package com.example.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class MailTemplateConfig {
	@Bean
	public ITemplateResolver templateResolver()
	{
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setPrefix("mail/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(StandardTemplateModeHandlers.HTML5.getTemplateModeName());

	    return templateResolver;
	}

	@Bean
	public TemplateEngine templateEngine()
	{
	    TemplateEngine templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(this.templateResolver());

	    return templateEngine;
	}
}
