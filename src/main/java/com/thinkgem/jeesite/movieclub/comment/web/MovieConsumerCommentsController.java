/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.comment.web;

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
import com.thinkgem.jeesite.movieclub.comment.entity.MovieConsumerComments;
import com.thinkgem.jeesite.movieclub.comment.service.MovieConsumerCommentsService;

/**
 * commentController
 * @author eric.wang
 * @version 2015-10-16
 */
@Controller
@RequestMapping(value = "${adminPath}/comment/movieConsumerComments")
public class MovieConsumerCommentsController extends BaseController {

	@Autowired
	private MovieConsumerCommentsService movieConsumerCommentsService;
	
	@ModelAttribute
	public MovieConsumerComments get(@RequestParam(required=false) String id) {
		MovieConsumerComments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = movieConsumerCommentsService.get(id);
		}
		if (entity == null){
			entity = new MovieConsumerComments();
		}
		return entity;
	}
	
	@RequiresPermissions("comment:movieConsumerComments:view")
	@RequestMapping(value = {"list", ""})
	public String list(MovieConsumerComments movieConsumerComments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MovieConsumerComments> page = movieConsumerCommentsService.findPage(new Page<MovieConsumerComments>(request, response), movieConsumerComments); 
		model.addAttribute("page", page);
		return "modules/comment/movieConsumerCommentsList";
	}

	@RequiresPermissions("comment:movieConsumerComments:view")
	@RequestMapping(value = "form")
	public String form(MovieConsumerComments movieConsumerComments, Model model) {
		model.addAttribute("movieConsumerComments", movieConsumerComments);
		return "modules/comment/movieConsumerCommentsForm";
	}

	@RequiresPermissions("comment:movieConsumerComments:edit")
	@RequestMapping(value = "save")
	public String save(MovieConsumerComments movieConsumerComments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, movieConsumerComments)){
			return form(movieConsumerComments, model);
		}
		movieConsumerCommentsService.save(movieConsumerComments);
		addMessage(redirectAttributes, "保存comment成功");
		return "redirect:"+Global.getAdminPath()+"/comment/movieConsumerComments/?repage";
	}
	
	@RequiresPermissions("comment:movieConsumerComments:edit")
	@RequestMapping(value = "delete")
	public String delete(MovieConsumerComments movieConsumerComments, RedirectAttributes redirectAttributes) {
		movieConsumerCommentsService.delete(movieConsumerComments);
		addMessage(redirectAttributes, "删除comment成功");
		return "redirect:"+Global.getAdminPath()+"/comment/movieConsumerComments/?repage";
	}

}