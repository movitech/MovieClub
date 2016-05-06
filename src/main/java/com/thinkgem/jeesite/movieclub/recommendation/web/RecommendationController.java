/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.recommendation.web;

import java.util.List;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.movieclub.movie.entity.Movie;
import com.thinkgem.jeesite.movieclub.movie.service.MovieService;

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
import com.thinkgem.jeesite.movieclub.recommendation.entity.Recommendation;
import com.thinkgem.jeesite.movieclub.recommendation.service.RecommendationService;

/**
 * RecommendationController
 * @author eric.wang
 * @version 2015-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/recommendation/recommendation")
public class RecommendationController extends BaseController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private RecommendationService recommendationService;

	@ModelAttribute
	public Recommendation get(@RequestParam(required=false) String id) {
		Recommendation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = recommendationService.get(id);
		}
		if (entity == null){
			entity = new Recommendation();
		}
		return entity;
	}

	@ModelAttribute
	public Recommendation getByMovieId(@RequestParam(required=false) String movieId) {
		Recommendation entity = null;
		if (StringUtils.isNotBlank(movieId)) {
			entity = recommendationService.getByMovieId(movieId);
		}
		if (entity == null) {
			entity = new Recommendation();
		}
		return entity;
	}

	@RequiresPermissions("recommendation:recommendation:view")
	@RequestMapping(value = {"list", ""})
	public String list(Recommendation recommendation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Recommendation> page = recommendationService.findPage(new Page<Recommendation>(request, response), recommendation); 
		model.addAttribute("page", page);
		return "modules/recommendation/recommendationList";
	}

	@RequiresPermissions("recommendation:recommendation:view")
	@RequestMapping(value = "form")
	public String form(Recommendation recommendation, Model model) {
		model.addAttribute("recommendation", recommendation);
		return "modules/recommendation/recommendationForm";
	}

	@RequiresPermissions("recommendation:recommendation:edit")
	@RequestMapping(value = "save")
	public String save(Recommendation recommendation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recommendation)) {
			return form(recommendation, model);
		}
		recommendationService.save(recommendation);
		addMessage(redirectAttributes, "Save recommendation successful.");
		return "redirect:"+Global.getAdminPath()+"/recommendation/recommendation/?repage";
	}
	
	@RequiresPermissions("recommendation:recommendation:edit")
	@RequestMapping(value = "delete")
	public String delete(Recommendation recommendation, RedirectAttributes redirectAttributes) {
		recommendationService.delete(recommendation);
		addMessage(redirectAttributes, "Delete recommendation successful.");
		return "redirect:"+Global.getAdminPath()+"/recommendation/recommendation/?repage";
	}

	@RequestMapping(value = "searchMovieList", method = RequestMethod.GET)
	@ResponseBody
	public List<Movie> searchMovieList(@RequestParam(required=false) String movieName, @RequestParam(required=false) String movieGenre) {
		Movie movie = new Movie();
		movie.setTitle(movieName);
		movie.setGenre(movieGenre);
		List<Movie> movieList = movieService.getMovieListWithoutRecommendation(movie);
		return movieList;
	}

}