package com.thinkgem.jeesite.movieclub.mail.template;

import java.util.Map;

public interface MailTemplate {
	public String getMailText(Map paramMap, String templateName);
}
