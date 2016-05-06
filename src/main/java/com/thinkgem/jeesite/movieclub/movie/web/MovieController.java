/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.movie.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.thinkgem.jeesite.movieclub.genre.entity.MovieGenre;
import com.thinkgem.jeesite.movieclub.genre.service.MovieGenreService;
import com.thinkgem.jeesite.movieclub.movie_tag.entity.MovieTagLink;
import com.thinkgem.jeesite.movieclub.movie_tag.service.MovieTagLinkService;
import com.thinkgem.jeesite.movieclub.tag.entity.MovieTag;
import com.thinkgem.jeesite.movieclub.tag.service.MovieTagService;
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
import com.thinkgem.jeesite.movieclub.movie.entity.Movie;
import com.thinkgem.jeesite.movieclub.movie.service.MovieService;

import java.util.*;

/**
 * movieController
 * @author marvin.ma
 * @version 2015-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/movie/movie")
public class MovieController extends BaseController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieTagLinkService movieTagLinkService;

	@Autowired
	private MovieGenreService movieGenreService;

	@Autowired
	private MovieTagService movieTagService;

	@ModelAttribute
	public Movie get(@RequestParam(required=false) String id) {
		Movie entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = movieService.get(id);
		}
		if (entity == null){
			entity = new Movie();
		}
		return entity;
	}
	
	@RequiresPermissions("movie:movie:view")
	@RequestMapping(value = {"list", ""})
	public String list(Movie movie, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Movie> page = movieService.findPage(new Page<Movie>(request, response), movie);
		List<MovieGenre> movieGenres=movieGenreService.findList(new MovieGenre());
		model.addAttribute("page", page);
		model.addAttribute("movieGenres", movieGenres);
		return "modules/movie/movieList";
	}

	@RequiresPermissions("movie:movie:view")
	@RequestMapping(value = "form")
	public String form(Movie movie, Model model) {
		model.addAttribute("movie", movie);
		List<MovieGenre> movieGenres=movieGenreService.findList(new MovieGenre());
		List<MovieTag> movieTags=movieTagService.findList(new MovieTag());
		model.addAttribute("movieGenres", movieGenres);
		model.addAttribute("movieTags", movieTags);
		return "modules/movie/movieForm";
	}

	@RequiresPermissions("movie:movie:edit")
	@RequestMapping(value = "save")
	public String save(Movie movie, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, movie)){
			return form(movie, model);
		}
		movieService.save(movie);

		//add by marvin: 2015-10-16 14:00
		//use for: 存储 movie tags 相关信息
		MovieTagLink movieTagLink = new MovieTagLink();
		movieTagLink.setMovieId(movie.getId());
		List<MovieTagLink> linkList = movieTagLinkService.findList(movieTagLink);
		List<String> tagList = movie.getTags();
		//List<MovieTagLink> saveLinkList = new ArrayList<MovieTagLink>();
		//List<MovieTagLink> deleteLinkList = new ArrayList<MovieTagLink>();
		for (MovieTagLink tmpLink : linkList) {
			Boolean hasTag = false;

			for (String tmpTag : tagList) {
				String _tmpTag = tmpLink.getTagId();
				if (_tmpTag.equals(tmpTag)) {
					hasTag = true;
					break;
				}
			}
			if (!hasTag) {
				movieTagLinkService.delete(tmpLink);
				//deleteLinkList.add(tmpLink);
			}

		}
		for (String tmpTag : tagList) {
			Boolean hasLink = false;
			for (MovieTagLink tmpLink : linkList) {
				String _tmpTag = tmpLink.getTagId();
				if (tmpTag.equals(_tmpTag)) {
					hasLink = true;
					break;
				}
			}

			if (!hasLink) {
				MovieTagLink tagLink = new MovieTagLink();
				tagLink.setMovieId(movie.getId());
				tagLink.setTagId(tmpTag);
				movieTagLinkService.save(tagLink);
			}
		}
		addMessage(redirectAttributes, "Save movie successful.");
		return "redirect:"+Global.getAdminPath()+"/movie/movie/?repage";
	}
	
	@RequiresPermissions("movie:movie:edit")
	@RequestMapping(value = "delete")
	public String delete(Movie movie, RedirectAttributes redirectAttributes) {
		movieService.delete(movie);
		addMessage(redirectAttributes, "delete movie successful.");
		return "redirect:"+Global.getAdminPath()+"/movie/movie/?repage";
	}

	@RequestMapping(value = "searchMovieList", method = RequestMethod.GET)
	@ResponseBody
	public List<Movie> searchMovieList(@RequestParam(required=false) String movieName, @RequestParam(required=false) String movieGenre) {
		Movie movie = new Movie();
		movie.setTitle(movieName);
		movie.setGenre(movieGenre);
		List<Movie> movieList = movieService.findList(movie);
		return movieList;
	}
}