/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.tag.web;

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
import com.thinkgem.jeesite.movieclub.tag.entity.MovieTag;
import com.thinkgem.jeesite.movieclub.tag.service.MovieTagService;

/**
 * tagController
 * @author marvin.ma
 * @version 2015-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/tag/movieTag")
public class MovieTagController extends BaseController {

	@Autowired
	private MovieTagService movieTagService;
	
	@ModelAttribute
	public MovieTag get(@RequestParam(required=false) String id) {
		MovieTag entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = movieTagService.get(id);
		}
		if (entity == null){
			entity = new MovieTag();
		}
		return entity;
	}
	
	@RequiresPermissions("tag:movieTag:view")
	@RequestMapping(value = {"list", ""})
	public String list(MovieTag movieTag, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MovieTag> page = movieTagService.findPage(new Page<MovieTag>(request, response), movieTag); 
		model.addAttribute("page", page);
		return "modules/tag/movieTagList";
	}

	@RequiresPermissions("tag:movieTag:view")
	@RequestMapping(value = "form")
	public String form(MovieTag movieTag, Model model) {
		model.addAttribute("movieTag", movieTag);
		return "modules/tag/movieTagForm";
	}

	@RequiresPermissions("tag:movieTag:edit")
	@RequestMapping(value = "save")
	public String save(MovieTag movieTag, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, movieTag)){
			return form(movieTag, model);
		}
		movieTagService.save(movieTag);
		addMessage(redirectAttributes, "保存tag成功");
		return "redirect:"+Global.getAdminPath()+"/tag/movieTag/?repage";
	}
	
	@RequiresPermissions("tag:movieTag:edit")
	@RequestMapping(value = "delete")
	public String delete(MovieTag movieTag, RedirectAttributes redirectAttributes) {
		movieTagService.delete(movieTag);
		addMessage(redirectAttributes, "删除tag成功");
		return "redirect:"+Global.getAdminPath()+"/tag/movieTag/?repage";
	}

}