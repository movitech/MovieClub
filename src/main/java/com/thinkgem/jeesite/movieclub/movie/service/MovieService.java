/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.movie.service;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.movieclub.movie_tag.entity.MovieTagLink;
import com.thinkgem.jeesite.movieclub.movie_tag.service.MovieTagLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.movieclub.movie.entity.Movie;
import com.thinkgem.jeesite.movieclub.movie.dao.MovieDao;

/**
 * movieService
 * @author marvin.ma
 * @version 2015-10-15
 */
@Service
@Transactional(readOnly = true)
public class MovieService extends CrudService<MovieDao, Movie> {

	@Autowired
	private MovieTagLinkService movieTagLinkService;

	@Autowired
	private MovieDao movieDao ;

	public Movie get(String id) {
		Movie movie = super.get(id);

		//add by marvin: 2015-10-16 14:00
		//use for: 设置 movie tags 相关信息
		List<String> tags = new ArrayList<String>();
		MovieTagLink movieTagLink = new MovieTagLink();
		movieTagLink.setMovieId(id);
		List<MovieTagLink> linkList = movieTagLinkService.findList(movieTagLink);
		for (MovieTagLink tmpLink : linkList) {
			//tags += tags == null || tags.length() <= 0 ? tmpLink.getTagId() : "," + tmpLink.getTagId();
			tags.add(tmpLink.getTagId());
		}
		movie.setTags(tags);
		return movie;
	}
	
	public List<Movie> findList(Movie movie) {
		return super.findList(movie);
	}

	public List<Movie> getMovieListWithoutRecommendation(Movie movie) {
		return movieDao.getMovieListWithoutRecommendation(movie);
	}

	public Page<Movie> findPage(Page<Movie> page, Movie movie) {
		return super.findPage(page, movie);
	}
	
	@Transactional(readOnly = false)
	public void save(Movie movie) {
		super.save(movie);
	}
	
	@Transactional(readOnly = false)
	public void delete(Movie movie) {
		super.delete(movie);
	}
	
}