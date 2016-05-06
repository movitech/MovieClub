/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.permission.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.movieclub.consumer_role.service.ConsumerRoleService;
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
import com.thinkgem.jeesite.movieclub.permission.entity.Permission;
import com.thinkgem.jeesite.movieclub.permission.service.PermissionService;

/**
 * permissionController
 * @author marvin.ma
 * @version 2015-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/permission/permission")
public class PermissionController extends BaseController {

	@Autowired
	private PermissionService permissionService;

	@ModelAttribute
	public Permission get(@RequestParam(required=false) String id) {
		Permission entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = permissionService.get(id);
		}
		if (entity == null){
			entity = new Permission();
		}
		return entity;
	}
	
	@RequiresPermissions("permission:permission:view")
	@RequestMapping(value = {"list", ""})
	public String list(Permission permission, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Permission> page = permissionService.findPage(new Page<Permission>(request, response), permission); 
		model.addAttribute("page", page);
		return "modules/permission/permissionList";
	}

	@RequiresPermissions("permission:permission:view")
	@RequestMapping(value = "form")
	public String form(Permission permission, Model model) {
		model.addAttribute("permission", permission);
		return "modules/permission/permissionForm";
	}

	@RequiresPermissions("permission:permission:edit")
	@RequestMapping(value = "save")
	public String save(Permission permission, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, permission)){
			return form(permission, model);
		}
		permissionService.save(permission);
		addMessage(redirectAttributes, "保存permission成功");
		return "redirect:"+Global.getAdminPath()+"/permission/permission/?repage";
	}
	
	@RequiresPermissions("permission:permission:edit")
	@RequestMapping(value = "delete")
	public String delete(Permission permission, RedirectAttributes redirectAttributes) {
		permissionService.delete(permission);
		addMessage(redirectAttributes, "删除permission成功");
		return "redirect:"+Global.getAdminPath()+"/permission/permission/?repage";
	}

}