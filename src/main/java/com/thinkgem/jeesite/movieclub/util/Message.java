package com.thinkgem.jeesite.movieclub.util;

public class Message {

	public static class Consumer {
		public static final String EMAIL_ALREADY_REGISTERED = "this email has already registered.";
		public static final String DATE_FORMAT_NOT_VALIDATE = "the format of the birthday is not validate.";
		public static final String ACCOUNT_DOES_NOT_EXISTS = "the account %s is not exists.";
		public static final String ACCOUNT_IS_INACTIVE = "the account is inactive,please activate it";
		public static final String ACCOUNT_HAS_ALREADY_ACTIVE = "the account is active already,please enjoy it";
		public static final String ACCOUNT_DOES_NOT_EXISTS_OR_PASSWORD_NOT_CORRECT = "the account %s is not exists or the password is not correct.";
		public static final String RESET_PASSWORD_EMAIL_TITLE = "Reset the password of %s@MovieClub";
		public static final String ACTIVE_ACCOUNT_EMAIL_TITLE = "Active your account %s@MovieClub";
		public static final String ACCOUNT_DOES_NOT_EXISTS_OR_TOKEN_EXPIRED = "the account %s is not exists or token expired";
	}
}
