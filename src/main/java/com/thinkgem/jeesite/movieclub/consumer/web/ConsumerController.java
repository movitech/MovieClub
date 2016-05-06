/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.consumer.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.movieclub.consumer.entity.Consumer;
import com.thinkgem.jeesite.movieclub.consumer.service.ConsumerService;
import com.thinkgem.jeesite.movieclub.consumer_role.entity.ConsumerRole;
import com.thinkgem.jeesite.movieclub.consumer_role.service.ConsumerRoleService;
import com.thinkgem.jeesite.movieclub.util.Const;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * consumerController
 * @author eric.wang
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/consumer/consumer")
public class ConsumerController extends BaseController {

	@Autowired
	private ConsumerService consumerService;

    @Autowired
	private ConsumerRoleService consumerRoleService;
	
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
	
	@RequiresPermissions("consumer:consumer:view")
	@RequestMapping(value = {"list", ""})
	public String list(Consumer consumer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Consumer> page = consumerService.findPage(new Page<Consumer>(request, response), consumer); 
		model.addAttribute("page", page);
		return "modules/consumer/consumerList";
	}

	@RequiresPermissions("consumer:consumer:view")
	@RequestMapping(value = "form")
	public String form(Consumer consumer, Model model) {
        List<ConsumerRole> roles = consumerRoleService.findList(new ConsumerRole());
		model.addAttribute("consumer", consumer);
		model.addAttribute("roles", roles);
		return "modules/consumer/consumerForm";
	}

	@RequiresPermissions("consumer:consumer:edit")
	@RequestMapping(value = "save")
	public String save(Consumer consumer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, consumer)){
			return form(consumer, model);
		}
		consumerService.save(consumer);
		addMessage(redirectAttributes, "保存consumer成功");
		return "redirect:"+Global.getAdminPath()+"/consumer/consumer/?repage";
	}
	
	@RequiresPermissions("consumer:consumer:edit")
	@RequestMapping(value = "delete")
	public String delete(Consumer consumer, RedirectAttributes redirectAttributes) {
		consumerService.delete(consumer);
		addMessage(redirectAttributes, "删除consumer成功");
		return "redirect:"+Global.getAdminPath()+"/consumer/consumer/?repage";
	}

}