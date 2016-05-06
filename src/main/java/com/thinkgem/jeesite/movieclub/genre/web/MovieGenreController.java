/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.genre.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.movieclub.genre.entity.MovieGenre;
import com.thinkgem.jeesite.movieclub.genre.service.MovieGenreService;


/**
 * movie genreController
 * @author eric.wang
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/genre/movieGenre")
public class MovieGenreController extends BaseController {

	@Autowired
	private MovieGenreService movieGenreService;
	
	@ModelAttribute
	public MovieGenre get(@RequestParam(required=false) String id) {
		MovieGenre entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = movieGenreService.get(id);
		}
		if (entity == null){
			entity = new MovieGenre();
		}
		return entity;
	}
	
	@RequiresPermissions("genre:movieGenre:view")
	@RequestMapping(value = {"list", ""})
	public String list(MovieGenre movieGenre, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MovieGenre> page = movieGenreService.findPage(new Page<MovieGenre>(request, response), movieGenre); 
		model.addAttribute("page", page);
		return "modules/genre/movieGenreList";
	}

	@RequiresPermissions("genre:movieGenre:view")
	@RequestMapping(value = "form")
	public String form(MovieGenre movieGenre, Model model) {
		model.addAttribute("movieGenre", movieGenre);
		return "modules/genre/movieGenreForm";
	}

	@RequiresPermissions("genre:movieGenre:edit")
	@RequestMapping(value = "save")
	public String save(MovieGenre movieGenre, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, movieGenre)){
			return form(movieGenre, model);
		}
		movieGenreService.save(movieGenre);
		addMessage(redirectAttributes, "save movie genre successful.");
		return "redirect:"+Global.getAdminPath()+"/genre/movieGenre/?repage";
	}
	
	@RequiresPermissions("genre:movieGenre:edit")
	@RequestMapping(value = "delete")
	public String delete(MovieGenre movieGenre, RedirectAttributes redirectAttributes) {
		movieGenreService.delete(movieGenre);
		addMessage(redirectAttributes, "delete movie genre successful.");
		return "redirect:"+Global.getAdminPath()+"/genre/movieGenre/?repage";
	}

}