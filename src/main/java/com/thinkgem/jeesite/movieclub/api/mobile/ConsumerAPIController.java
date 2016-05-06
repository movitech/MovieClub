/**
 *
 */
package com.thinkgem.jeesite.movieclub.api.mobile;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.movieclub.consumer.entity.Consumer;
import com.thinkgem.jeesite.movieclub.consumer.service.ConsumerService;
import com.thinkgem.jeesite.movieclub.mail.MailService;
import com.thinkgem.jeesite.movieclub.util.Const;
import com.thinkgem.jeesite.movieclub.util.Message;
import com.thinkgem.jeesite.movieclub.vo.ConsumerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

/**
 * ConsumerAPIController
 *
 * @author jason.xu
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${mobileApiPath}/consumer")
public class ConsumerAPIController extends BaseController {

	@Autowired
	private MailService mailService;

	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(@RequestBody ConsumerVO consumerVO) {
		try {
			Consumer consumer = consumerService.getByEmail(consumerVO.getEmail());
			//验证有限是否已经注册
			if (null == consumer) {
				consumer = new Consumer();
				//生日
				Date birthday = DateUtils.parseDate(consumerVO.getBirthday(), "yyyy-MM-dd");
				consumer.setBirthday(birthday);

				String resetPasswordToken = createNewToken();

				consumer.setEmail(consumerVO.getEmail());
				consumer.setFirstName(consumerVO.getFirstName());
				consumer.setLastName(consumerVO.getLastName());
				consumer.setNickName(consumerVO.getNickName());
				consumer.setGender(consumerVO.getGender());
				consumer.setPassword(consumerVO.getPassword());
				consumer.setActive(false);
				consumer.setResetPasswordToken(resetPasswordToken);
				consumer.setResetPasswordTokenExpireDate(getResetPasswordExpireDate());
				consumerService.save(consumer);


				sendActiveEmail(consumer, resetPasswordToken);
				return Const.APIResult.SUCCESS_MESSAGE;
			} else {
				return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_EXIST, Message.Consumer.EMAIL_ALREADY_REGISTERED);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.KNOWN_ISSUE, Message.Consumer.DATE_FORMAT_NOT_VALIDATE);
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.SERVER_ERROR, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/sendActiveEmail", method = RequestMethod.POST)
	public String resendActiveEmail(@RequestBody ConsumerVO consumerVO) {
		try {
			Consumer consumer = consumerService.getByEmail(consumerVO.getEmail());
			if (null == consumer) {
				String errMsg = String.format(Message.Consumer.ACCOUNT_DOES_NOT_EXISTS, consumerVO.getEmail());
				if (logger.isDebugEnabled()) {
					logger.debug(errMsg);
				}
				return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_EXIST, errMsg);
			} else {
				if (consumer.isActive()) {
					return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_HAS_ALREADY_ACTIVE, Message.Consumer.ACCOUNT_HAS_ALREADY_ACTIVE);
				}
				String token = createNewToken();
				consumerService.updateResetPasswordToken(consumer.getId(), token, getResetPasswordExpireDate());
				sendActiveEmail(consumer, token);

				return Const.APIResult.SUCCESS_MESSAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.SERVER_ERROR, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody ConsumerVO consumerVO) {
		try {
			Consumer consumer = consumerService.getByEmail(consumerVO.getEmail());
			if (null == consumer) {
				String errMsg = String.format(Message.Consumer.ACCOUNT_DOES_NOT_EXISTS, consumerVO.getEmail());
				if (logger.isDebugEnabled()) {
					logger.debug(errMsg);
				}
				return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_EXIST, errMsg);
			} else {
				if (!consumer.isActive()) {
					return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_ACTIVE, Message.Consumer.ACCOUNT_IS_INACTIVE);
				}
				if (logger.isDebugEnabled()) {
					logger.debug(consumer.getEmail() + " " + consumer.getPassword());
				}
				Date birthday = DateUtils.parseDate(consumerVO.getBirthday(), "yyyy-MM-dd");
				consumer.setBirthday(birthday);

				consumer.setGender(consumerVO.getGender());
				consumer.setFirstName(consumerVO.getFirstName());
				consumer.setLastName(consumerVO.getLastName());
				consumerService.save(consumer);
				return Const.APIResult.SUCCESS_MESSAGE;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.DATE_FORMAT_ERROR, Message.Consumer.DATE_FORMAT_NOT_VALIDATE);
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.SERVER_ERROR, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestBody ConsumerVO consumerVO) {
		try {
			Consumer consumer = consumerService.validate(consumerVO.getEmail(), consumerVO.getPassword());
			if (null == consumer) {
				String errMsg = String.format(Message.Consumer.ACCOUNT_DOES_NOT_EXISTS_OR_PASSWORD_NOT_CORRECT, consumerVO.getEmail());
				if (logger.isDebugEnabled()) {
					logger.debug(errMsg);
				}
				return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_VERIFICATION_FAILURE, errMsg);
			} else {
				if (!consumer.isActive()) {
					return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_ACTIVE, Message.Consumer.ACCOUNT_IS_INACTIVE);
				}
				//当单点登录或者token已过期时更新token
				if (Global.isMobileSSOEnable()
						|| consumer.getMobileTokenExpireDate().before(new Date())) {

					//更新数据用于返回给前台
					String newToken = createNewToken();
					consumer.setMobileAccessToken(newToken);
					consumer.setMobileTokenExpireDate(getExpireDate());

					consumerService.updateMobileAccessToken(consumer.getId(), newToken, getExpireDate());
				}
				return consumer;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.SERVER_ERROR, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(@RequestBody ConsumerVO consumerVO) {
		try {
			Consumer consumer = consumerService.validate(consumerVO.getEmail(), consumerVO.getPassword());
			if (null == consumer) {
				String errMsg = String.format(Message.Consumer.ACCOUNT_DOES_NOT_EXISTS_OR_PASSWORD_NOT_CORRECT, consumerVO.getEmail());
				if (logger.isDebugEnabled()) {
					logger.debug(errMsg);
				}
				return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_VERIFICATION_FAILURE, errMsg);
			} else {
				String newPassword = consumerVO.getNewPassword();
				consumerService.updatePassword(consumer.getId(), newPassword);
				return Const.APIResult.SUCCESS_MESSAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.SERVER_ERROR, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public String forgetPassword(@RequestBody ConsumerVO consumerVO) {
		try {
			Consumer consumer = consumerService.getByEmail(consumerVO.getEmail());
			if (null == consumer) {
				String errMsg = String.format(Message.Consumer.ACCOUNT_DOES_NOT_EXISTS, consumerVO.getEmail());
				return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_EXIST, errMsg);
			} else {
				if (!consumer.isActive()) {
					return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.Consumer.ACCOUNT_NOT_ACTIVE, Message.Consumer.ACCOUNT_IS_INACTIVE);
				}
				String resetPasswordToken = createNewToken();
				consumerService.updateResetPasswordToken(consumer.getId(), resetPasswordToken, getResetPasswordExpireDate());
				sendResetPasswordEmail(consumer, resetPasswordToken);
				return Const.APIResult.SUCCESS_MESSAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, e.getMessage());
		}
	}

	private String createNewToken() {
		return UUID.randomUUID().toString();
	}

	private Date getExpireDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, Global.getTokenExpireSeconds());
		return calendar.getTime();
	}

	private Date getResetPasswordExpireDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, Global.getResetPasswordTokenExpireSeconds());
		return calendar.getTime();
	}

	private void sendActiveEmail(Consumer consumer, String resetPasswordToken) {
		String activeConsumerUrl = String.format(
				"%s%s?token=%s",
				Global.getHomeUrl(),
				Const.WebPath.ACTIVE_ACCOUNT_WEB_PATH,
				resetPasswordToken
		);
		Map<String, String> param = new HashMap<String, String>();
		param.put("userName", consumer.getNickName());
		param.put("activeConsumerUrl", activeConsumerUrl);

		mailService.sendTemplateMail(
				Global.getEmailFrom(),
				consumer.getEmail(),
				String.format(Message.Consumer.ACTIVE_ACCOUNT_EMAIL_TITLE, consumer.getNickName()),
				param,
				Const.MailTemplateNames.ACTIVE_ACCOUNT_EMAIL_TEMPLATE_NAME);

	}

	private void sendResetPasswordEmail(Consumer consumer, String resetPasswordToken) {
		Map<String, String> params = new HashMap<String, String>();
		String emailTitle = String.format(Message.Consumer.RESET_PASSWORD_EMAIL_TITLE, consumer.getNickName());
		String url = String.format(
				"%s%s?token=%s",
				Global.getHomeUrl(),
				Const.WebPath.RESET_PASSWORD_WEB_PATH,
				resetPasswordToken
		);
		params.put("userName", consumer.getNickName());
		params.put("resetPasswordUrl", url);

		//TODO call send forget password email.
		mailService.sendTemplateMail(Global.getEmailFrom(), consumer.getEmail(), emailTitle, params, Const.MailTemplateNames.RESET_PASSWORD_EMAIL_TEMPLATE_NAME);
	}
}