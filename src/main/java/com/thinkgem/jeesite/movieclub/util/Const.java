package com.thinkgem.jeesite.movieclub.util;

public class Const {
	public static class StatusCode {

		//状态正常
		public static final int OK = 200;
		//已知错误
		public static final int KNOWN_ISSUE = 201;
		//时间格式错误
		public static final int DATE_FORMAT_ERROR = 401;
		//服务器内部错误
		public static final int SERVER_ERROR = 500;

		public static class Consumer {//100**
			public static final int ACCOUNT_HAS_ALREADY_ACTIVE = 10000;
			// 10001 邮箱已注册但是未激活(显示重新发送激活邮件按钮)
			public static final int ACCOUNT_NOT_ACTIVE = 10001;
			//10002 此邮箱对应的用户不存在(显示message)
			public static final int ACCOUNT_NOT_EXIST = 10002;
			//10003 账号或者密码不正确(显示message)
			public static final int ACCOUNT_VERIFICATION_FAILURE = 10003;
			//10004 账号不存在或token已过期(显示message)
			public static final int ACCOUNT_NOT_EXIST_OR_TOKEN_EXPIRED = 10004;

		}

	}

	public static class APIResult {
		public static final String SUCCESS_MESSAGE = String.format("{\"success\":true,\"errorCode\":%s,\"msg\":\"success\"}", StatusCode.OK);
		public static final String ERROR_MESSAGE = "{\"success\":false, \"errorCode\":%s,\"msg\":\"%s\"}";

	}

	public static class SysConfigKey {
		public static final String MAIL_SERVER_KEY = "mail.server";
		public static final String MAIL_USER_KEY = "mail.user";
		public static final String MAIL_PASSWORD_KEY = "mail.password";
	}

	public static class MailTemplateNames {
		public static final String RESET_PASSWORD_EMAIL_TEMPLATE_NAME = "resetPassword.ftl";
		public static final String ACTIVE_ACCOUNT_EMAIL_TEMPLATE_NAME = "activeConsumer.ftl";
	}

	public static class WebPath {
		public static final String RESET_PASSWORD_WEB_PATH = "/f/consumer/resetPassword";
		public static final String ACTIVE_ACCOUNT_WEB_PATH = "/f/consumer/activeRegisterUser";
	}


}
