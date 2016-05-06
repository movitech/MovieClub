/*
 * Copyright (C) 2014 SEC BFO, Inc. All Rights Reserved.
 */
package com.thinkgem.jeesite.movieclub.mail;

import com.thinkgem.jeesite.movieclub.mail.template.MailTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class MailService {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected JavaMailSender mailSender;

	@Autowired
	private MailTemplate mailTemplate;

	@Autowired
	protected SimpleMailMessage simpleMailTemplate;

	@Autowired
	protected FreeMarkerConfigurer freeMarkerConfigurer;


//    @Autowired
//    @Qualifier("mdMessageSender")
//    protected MailMessageSender messageSender;

	/**
	 * Send Simple Text Mail
	 *
	 * @param toList
	 * @param subject
	 * @param mailMsg
	 */
	public void sendSimpleMessage(String[] toList, String subject, String mailMsg) {
		this.sendSimpleMessage(toList, null, subject, mailMsg);
	}

	/**
	 * Send Simple Text Mail
	 *
	 * @param toList
	 * @param cc
	 * @param subject
	 * @param mailMsg
	 */
	public void sendSimpleMessage(String[] toList, String[] cc, String subject, String mailMsg) {
		SimpleMailMessage msg = new SimpleMailMessage(simpleMailTemplate);
		msg.setTo(toList);
		msg.setSubject(subject);
		msg.setText(mailMsg);
		msg.setCc(cc);
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			log.error(ex.getMessage());
		}
	}

	/**
	 * Send HTML Text Mail
	 *
	 * @param toList
	 * @param subject
	 * @param htmlMailMsg
	 */
	public void sendHtmlMessage(String from, String[] toList, String subject, String htmlMailMsg) {
		try {
			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "utf-8");

			messageHelper.setTo(toList);
			messageHelper.setFrom(from);
			messageHelper.setSubject(subject);
			messageHelper.setText(htmlMailMsg, true);

			this.mailSender.send(mailMessage);
		} catch (MessagingException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Send Contains Attachment Message
	 *
	 * @param toList
	 * @param subject
	 * @param htmlMailMsg
	 */
	public void sendAttachmentMessage(String from, String[] toList, String subject, String htmlMailMsg, List<File> files) {
		this.sendMultiMessage(from, toList, subject, htmlMailMsg, files, false);
	}

	/**
	 * Send Contains Inline Image Message
	 *
	 * @param toList
	 * @param subject
	 * @param htmlMailMsg
	 */
	public void sendInlineImgMessage(String from, String[] toList, String subject, String htmlMailMsg, List<File> files) {
		this.sendMultiMessage(from, toList, subject, htmlMailMsg, files, true);
	}

	/**
	 * @param toList
	 * @param subject
	 * @param htmlMailMsg
	 */
	public void sendMultiMessage(String from, String[] toList, String subject, String htmlMailMsg, List<File> files, Boolean isAttachInline) {
		try {
			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

			messageHelper.setTo(toList);
			messageHelper.setFrom(from);
			messageHelper.setSubject(subject);
			messageHelper.setText(htmlMailMsg, true);

			if (CollectionUtils.isNotEmpty(files) && isAttachInline != null) {
				if (isAttachInline) {
					List<String> cidList = new ArrayList<String>();
					for (File file : files) {
						cidList.add("<br><img src='cid:" + MimeUtility.encodeWord(file.getName()) + "'>");
					}
					messageHelper.setText(htmlMailMsg + StringUtils.join(cidList, ""), true);
					for (File file : files) {
						messageHelper.addInline(MimeUtility.encodeWord(file.getName()), new FileSystemResource(file));
					}
				} else {
					for (File file : files) {
						messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), new FileSystemResource(file));
					}
				}
			}
			this.mailSender.send(mailMessage);
		} catch (MessagingException e) {
			log.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * @param from
	 * @param to
	 * @param subject
	 * @param paramMap
	 * @param templateName
	 * @return
	 */
	public Boolean sendTemplateMail(String from, String to, String subject, Map paramMap, String templateName) {
		String[] toList = new String[]{to};
		return sendTemplateMail(from, toList, null, subject, paramMap, templateName);
	}

	/**
	 * @param from
	 * @param toList
	 * @param subject
	 * @param paramMap
	 * @param templateName
	 * @return
	 */
	public Boolean sendTemplateMail(String from, String[] toList, String subject, Map paramMap, String templateName) {
		return sendTemplateMail(from, toList, null, subject, paramMap, templateName);
	}


	/**
	 * Send Mail Using Freemarker Template With isAsynchronous
	 *
	 * @param from
	 * @param toList
	 * @param cc
	 * @param subject
	 * @param paramMap
	 * @param templateName
	 * @return
	 */
	public Boolean sendTemplateMail(String from, String[] toList, String[] cc, String subject, Map paramMap, String templateName) {
		Boolean flag = true;

		log.info(String.format("Send mail from:%s, to: %s, cc: %s, subject: %s.", from, Arrays.toString(toList), Arrays.toString(cc), subject));

		try {
			String content = mailTemplate.getMailText(paramMap, templateName);

			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, false, "utf-8");
			messageHelper.setSubject(subject);
			messageHelper.setFrom(from);
			messageHelper.setTo(toList);
			if (ArrayUtils.isNotEmpty(cc)) {
				messageHelper.setCc(cc);
			}
			messageHelper.setText(content, true);

//				if (configProp.getBccMailEnable() == 1) {
//					messageHelper.setBcc(configProp.getBccMailAddress().split(","));
//				}

			this.mailSender.send(mailMessage);
			log.debug(generateEMailLog(toList, cc, subject, content));
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			log.error(e.getMessage());
			flag = false;
		}

		log.info("Send mail " + (flag ? "successful" : "failure"));
		return flag;
	}


	protected String generateEMailLog(String[] to, String[] cc, String subject, String mailMsg) {
		String log = "\r\n ======= 发送邮件 ========" + "\r\n";
		log += "=============================================" + "\r\n";
		log += "To:\r\n" + StringUtils.join(to, "\r\n") + "\r\n";
		log += "-------------------------\r\n";
		log += "Title:\t" + subject + "\r\n";
		log += "-----------------------------------------\r\n";
		log += mailMsg + "\r\n";
		log += "=============================================";
		return log;
	}
}
