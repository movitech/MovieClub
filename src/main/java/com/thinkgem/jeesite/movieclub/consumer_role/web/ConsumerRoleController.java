/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.consumer_role.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.movieclub.consumer_role.entity.ConsumerRole;
import com.thinkgem.jeesite.movieclub.consumer_role.service.ConsumerRoleService;
import com.thinkgem.jeesite.movieclub.permission.entity.Permission;
import com.thinkgem.jeesite.movieclub.permission.service.PermissionService;

import java.util.List;
import java.util.Map;

/**
 * consumer_roleController
 * @author marvin.ma
 * @version 2015-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/consumer_role/consumerRole")
public class ConsumerRoleController extends BaseController {

	@Autowired
	private ConsumerRoleService consumerRoleService;

    @Autowired
    private PermissionService permissionService;

	@ModelAttribute
	public ConsumerRole get(@RequestParam(required=false) String id) {
		ConsumerRole entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = consumerRoleService.get(id);
		}
		if (entity == null){
			entity = new ConsumerRole();
		}
		return entity;
	}
	
	@RequiresPermissions("consumer_role:consumerRole:view")
	@RequestMapping(value = {"list", ""})
	public String list(ConsumerRole consumerRole, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ConsumerRole> page = consumerRoleService.findPage(new Page<ConsumerRole>(request, response), consumerRole); 
		model.addAttribute("page", page);
		return "modules/consumer_role/consumerRoleList";
	}

	@RequiresPermissions("consumer_role:consumerRole:view")
	@RequestMapping(value = "form")
	public String form(ConsumerRole consumerRole, Model model) {
		model.addAttribute("consumerRole", consumerRole);
		return "modules/consumer_role/consumerRoleForm";
	}

    @RequiresPermissions("consumer_role:consumerRole:edit")
    @RequestMapping(value = "assign")
    public String assign(Permission permission,Model model) {
        List<Permission> permissionList = permissionService.findList(new Permission());
        model.addAttribute("permissionList", permissionList);
        return "modules/consumer_role/consumerRoleAssign";
    }


	@RequiresPermissions("consumer_role:consumerRole:edit")
	@RequestMapping(value = "save")
	public String save(ConsumerRole consumerRole, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, consumerRole)){
			return form(consumerRole, model);
		}
		consumerRoleService.save(consumerRole);
		addMessage(redirectAttributes, "保存consumer_role成功");
		return "redirect:"+Global.getAdminPath()+"/consumer_role/consumerRole/?repage";
	}
	
	@RequiresPermissions("consumer_role:consumerRole:edit")
	@RequestMapping(value = "delete")
	public String delete(ConsumerRole consumerRole, RedirectAttributes redirectAttributes) {
		consumerRoleService.delete(consumerRole);
		addMessage(redirectAttributes, "删除consumer_role成功");
		return "redirect:"+Global.getAdminPath()+"/consumer_role/consumerRole/?repage";
	}


	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,
											  HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ConsumerRole> list = consumerRoleService.findList(new ConsumerRole());
		for (int i=0; i<list.size(); i++){
			ConsumerRole e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId())
					&& e.getParentIds().indexOf(","+extId+",")==-1)) ){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				//if (type != null && "3".equals(type)){
				//	map.put("isParent", true);
				//}
				mapList.add(map);
			}
		}
		return mapList;
	}

}