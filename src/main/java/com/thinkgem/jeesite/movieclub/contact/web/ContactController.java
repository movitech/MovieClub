/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.contact.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.movieclub.contact.entity.Contact;
import com.thinkgem.jeesite.movieclub.contact.service.ContactService;

/**
 * ContactController
 * @author eric.wang
 * @version 2015-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/contact/contact")
public class ContactController extends BaseController {

	@Autowired
	private ContactService contactService;
	
	@ModelAttribute
	public Contact get(@RequestParam(required=false) String id) {
		Contact entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = contactService.get(id);
		}
		if (entity == null){
			entity = new Contact();
		}
		return entity;
	}
	
	@RequiresPermissions("contact:contact:view")
	@RequestMapping(value = {"list", ""})
	public String list(Contact contact, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Contact> page = contactService.findPage(new Page<Contact>(request, response), contact); 
		model.addAttribute("page", page);
		return "modules/contact/contactList";
	}

	@RequiresPermissions("contact:contact:view")
	@RequestMapping(value = "form")
	public String form(Contact contact, Model model) {
		model.addAttribute("contact", contact);
		return "modules/contact/contactForm";
	}

	@RequiresPermissions("contact:contact:edit")
	@RequestMapping(value = "save")
	public String save(Contact contact, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, contact)){
			return form(contact, model);
		}
		contactService.save(contact);
		addMessage(redirectAttributes, "保存Contact成功");
		return "redirect:"+Global.getAdminPath()+"/contact/contact/?repage";
	}
	
	@RequiresPermissions("contact:contact:edit")
	@RequestMapping(value = "delete")
	public String delete(Contact contact, RedirectAttributes redirectAttributes) {
		contactService.delete(contact);
		addMessage(redirectAttributes, "删除Contact成功");
		return "redirect:"+Global.getAdminPath()+"/contact/contact/?repage";
	}

}