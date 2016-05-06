/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.bucket.web;

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
import com.thinkgem.jeesite.movieclub.bucket.entity.MovieBucket;
import com.thinkgem.jeesite.movieclub.bucket.service.MovieBucketService;

/**
 * BucketController
 * @author eric.wang
 * @version 2015-10-16
 */
@Controller
@RequestMapping(value = "${adminPath}/bucket/movieBucket")
public class MovieBucketController extends BaseController {

	@Autowired
	private MovieBucketService movieBucketService;
	
	@ModelAttribute
	public MovieBucket get(@RequestParam(required=false) String id) {
		MovieBucket entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = movieBucketService.get(id);
		}
		if (entity == null){
			entity = new MovieBucket();
		}
		return entity;
	}
	
	@RequiresPermissions("bucket:movieBucket:view")
	@RequestMapping(value = {"list", ""})
	public String list(MovieBucket movieBucket, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MovieBucket> page = movieBucketService.findPage(new Page<MovieBucket>(request, response), movieBucket); 
		model.addAttribute("page", page);
		return "modules/bucket/movieBucketList";
	}

	@RequiresPermissions("bucket:movieBucket:view")
	@RequestMapping(value = "form")
	public String form(MovieBucket movieBucket, Model model) {
		model.addAttribute("movieBucket", movieBucket);
		return "modules/bucket/movieBucketForm";
	}

	@RequiresPermissions("bucket:movieBucket:edit")
	@RequestMapping(value = "save")
	public String save(MovieBucket movieBucket, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, movieBucket)){
			return form(movieBucket, model);
		}
		movieBucketService.save(movieBucket);
		addMessage(redirectAttributes, "保存Bucket成功");
		return "redirect:"+Global.getAdminPath()+"/bucket/movieBucket/?repage";
	}
	
	@RequiresPermissions("bucket:movieBucket:edit")
	@RequestMapping(value = "delete")
	public String delete(MovieBucket movieBucket, RedirectAttributes redirectAttributes) {
		movieBucketService.delete(movieBucket);
		addMessage(redirectAttributes, "删除Bucket成功");
		return "redirect:"+Global.getAdminPath()+"/bucket/movieBucket/?repage";
	}

}