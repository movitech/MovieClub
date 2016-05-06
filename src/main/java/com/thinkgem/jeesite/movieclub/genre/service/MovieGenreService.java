/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.genre.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.genre.entity.MovieGenre;
import com.thinkgem.jeesite.movieclub.genre.dao.MovieGenreDao;

/**
 * movie genreService
 * @author eric.wang
 * @version 2015-10-12
 */
@Service
@Transactional(readOnly = true)
public class MovieGenreService extends CrudService<MovieGenreDao, MovieGenre> {

	public MovieGenre get(String id) {
		return super.get(id);
	}
	
	public List<MovieGenre> findList(MovieGenre movieGenre) {
		return super.findList(movieGenre);
	}
	
	public Page<MovieGenre> findPage(Page<MovieGenre> page, MovieGenre movieGenre) {
		return super.findPage(page, movieGenre);
	}
	
	@Transactional(readOnly = false)
	public void save(MovieGenre movieGenre) {
		super.save(movieGenre);
	}
	
	@Transactional(readOnly = false)
	public void delete(MovieGenre movieGenre) {
		super.delete(movieGenre);
	}
	
}