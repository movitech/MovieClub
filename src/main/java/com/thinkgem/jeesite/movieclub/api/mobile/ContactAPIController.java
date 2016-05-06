/*
 * Copyright (C) 2015 SEC BFO, Inc. All Rights Reserved.
 */
package com.thinkgem.jeesite.movieclub.api.mobile;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.movieclub.consumer.entity.Consumer;
import com.thinkgem.jeesite.movieclub.consumer.service.ConsumerService;
import com.thinkgem.jeesite.movieclub.contact.entity.Contact;
import com.thinkgem.jeesite.movieclub.contact.service.ContactService;
import com.thinkgem.jeesite.movieclub.util.Const;
import com.thinkgem.jeesite.movieclub.vo.ContactVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "${mobileApiPath}/contact")
public class ContactAPIController extends BaseController
{
	@Autowired
	private ContactService contactService;

	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value = "/advertise", method = RequestMethod.POST)
	@ResponseBody
	public String saveAdvertise(@RequestBody ContactVO contactVO,@RequestParam(required=false) String token) {
		try {
			//Consumer consumer = consumerService.getByToken(token,"mt");
			Contact contact=new Contact();
			BeanUtils.copyProperties(contactVO, contact);
			contact.setObjType("1");//advertise
			//contact.setConsumerId(consumer.getId());
			contact.setRemarks(contactVO.getComments());
			contactService.save(contact);
			return Const.APIResult.SUCCESS_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/contactUs", method = RequestMethod.POST)
	@ResponseBody
	public String saveContactUs(@RequestBody ContactVO contactVO,@RequestParam(required=false) String token) {
		try {
			//Consumer consumer = consumerService.getByToken(token,"mt");
			Contact contact=new Contact();
			BeanUtils.copyProperties(contactVO, contact);
			contact.setObjType("2");//contact us
			//contact.setConsumerId(consumer.getId());
			contact.setRemarks(contactVO.getComments());
			contactService.save(contact);
			return Const.APIResult.SUCCESS_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(Const.APIResult.ERROR_MESSAGE, Const.StatusCode.SERVER_ERROR, e.getMessage());
		}
	}
}
