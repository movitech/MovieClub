/*
 * Copyright (C) 2015 SEC BFO, Inc. All Rights Reserved.
 */
package com.thinkgem.jeesite.movieclub.portal.controller;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.movieclub.consumer.entity.Consumer;
import com.thinkgem.jeesite.movieclub.consumer.service.ConsumerService;
import com.thinkgem.jeesite.movieclub.vo.ConsumerVO;
import com.thinkgem.jeesite.movieclub.vo.MessageResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${frontPath}/consumer")
public class ConsumerPortalController
		extends BaseController{

	@Autowired
	private ConsumerService consumerService;

	@ModelAttribute
	public Consumer get(@RequestParam(required=false) String id) {
		Consumer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = consumerService.get(id);
		}
		if (entity == null){
			entity = new Consumer();
		}
		return entity;
	}

	//@ResponseBody
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
		String token = request.getParameter("token");
		Consumer consumer = null;
		consumer = consumerService.getByToken(token, "rt");
		if(consumer != null){
			model.addAttribute("consumer", consumer);
			return "modules/consumer/resetPasswordForm";
		}else{
			return "modules/consumer/tokenExpireForm";
		}

	}

	//@ResponseBody
	@RequestMapping(value = "/activeRegisterUser", method = RequestMethod.GET)
	public String activeRegisterUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		String token = request.getParameter("token");
		Consumer consumer = null;
		consumer = consumerService.getByToken(token, "rt");
		if(consumer != null){
			consumerService.activeRegisterUser(consumer.getId());
			MessageResultVO result=new MessageResultVO();
			result.setTitle("Active Register User");
			result.setMessage("Successful!.");
			result.setDesc("Congratulations! Active Register User Successfully.");
			model.addAttribute("result", result);
			return "modules/consumer/successForm";
		}else{
			return "modules/consumer/tokenExpireForm";
		}
	}

	@RequestMapping(value = "/resetNow")
	public String resetPassword(Consumer consumer, Model model, RedirectAttributes redirectAttributes) {

		String newPassword = consumer.getPassword();
		consumerService.updatePassword(consumer.getId(), newPassword);
		addMessage(redirectAttributes, "reset password success");
		MessageResultVO result=new MessageResultVO();
		result.setTitle("Rest Password Successful");
		result.setMessage("Successful!.");
		result.setDesc("Congratulations! Resetting Password Successfully.");
		model.addAttribute("result", result);
		return "modules/consumer/successForm";
		//return Const.APIResult.SUCCESS_MESSAGE;
	}
}
