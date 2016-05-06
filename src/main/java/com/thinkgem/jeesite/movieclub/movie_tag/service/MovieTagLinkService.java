/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.movie_tag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.movie_tag.entity.MovieTagLink;
import com.thinkgem.jeesite.movieclub.movie_tag.dao.MovieTagLinkDao;

/**
 * movie_tagService
 * @author marvin.ma
 * @version 2015-10-16
 */
@Service
@Transactional(readOnly = true)
public class MovieTagLinkService extends CrudService<MovieTagLinkDao, MovieTagLink> {

	public MovieTagLink get(String id) {
		return super.get(id);
	}
	
	public List<MovieTagLink> findList(MovieTagLink movieTagLink) {
		return super.findList(movieTagLink);
	}
	
	public Page<MovieTagLink> findPage(Page<MovieTagLink> page, MovieTagLink movieTagLink) {
		return super.findPage(page, movieTagLink);
	}
	
	@Transactional(readOnly = false)
	public void save(MovieTagLink movieTagLink) {
		super.save(movieTagLink);
	}
	
	@Transactional(readOnly = false)
	public void delete(MovieTagLink movieTagLink) {
		super.delete(movieTagLink);
	}
	
}