package com.thinkgem.jeesite.movieclub.mail.template;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Map;

@Component
public class FreeMakerMailTemplate implements MailTemplate {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected FreeMarkerConfigurer freeMarkerConfigurer;

	@Override
	public String getMailText(Map paramMap, String templateName) {
		String htmlText = null;
		try {
			Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, paramMap);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		} catch (TemplateException e) {
			logger.error(e.getMessage());
		}
		return htmlText;
	}
}
