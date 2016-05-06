/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.movie_tag.web;

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
import com.thinkgem.jeesite.movieclub.movie_tag.entity.MovieTagLink;
import com.thinkgem.jeesite.movieclub.movie_tag.service.MovieTagLinkService;

/**
 * movie_tagController
 * @author marvin.ma
 * @version 2015-10-16
 */
@Controller
@RequestMapping(value = "${adminPath}/movie_tag/movieTagLink")
public class MovieTagLinkController extends BaseController {

	@Autowired
	private MovieTagLinkService movieTagLinkService;
	
	@ModelAttribute
	public MovieTagLink get(@RequestParam(required=false) String id) {
		MovieTagLink entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = movieTagLinkService.get(id);
		}
		if (entity == null){
			entity = new MovieTagLink();
		}
		return entity;
	}
	
	@RequiresPermissions("movie_tag:movieTagLink:view")
	@RequestMapping(value = {"list", ""})
	public String list(MovieTagLink movieTagLink, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MovieTagLink> page = movieTagLinkService.findPage(new Page<MovieTagLink>(request, response), movieTagLink); 
		model.addAttribute("page", page);
		return "modules/movie_tag/movieTagLinkList";
	}

	@RequiresPermissions("movie_tag:movieTagLink:view")
	@RequestMapping(value = "form")
	public String form(MovieTagLink movieTagLink, Model model) {
		model.addAttribute("movieTagLink", movieTagLink);
		return "modules/movie_tag/movieTagLinkForm";
	}

	@RequiresPermissions("movie_tag:movieTagLink:edit")
	@RequestMapping(value = "save")
	public String save(MovieTagLink movieTagLink, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, movieTagLink)){
			return form(movieTagLink, model);
		}
		movieTagLinkService.save(movieTagLink);
		addMessage(redirectAttributes, "保存movie_tag成功");
		return "redirect:"+Global.getAdminPath()+"/movie_tag/movieTagLink/?repage";
	}
	
	@RequiresPermissions("movie_tag:movieTagLink:edit")
	@RequestMapping(value = "delete")
	public String delete(MovieTagLink movieTagLink, RedirectAttributes redirectAttributes) {
		movieTagLinkService.delete(movieTagLink);
		addMessage(redirectAttributes, "删除movie_tag成功");
		return "redirect:"+Global.getAdminPath()+"/movie_tag/movieTagLink/?repage";
	}

}