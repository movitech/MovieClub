/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.comment.entity.MovieConsumerComments;
import com.thinkgem.jeesite.movieclub.comment.dao.MovieConsumerCommentsDao;

/**
 * commentService
 * @author eric.wang
 * @version 2015-10-16
 */
@Service
@Transactional(readOnly = true)
public class MovieConsumerCommentsService extends CrudService<MovieConsumerCommentsDao, MovieConsumerComments> {

	@Autowired
	private MovieConsumerCommentsDao movieConsumerCommentsDao;


	public MovieConsumerComments get(String id) {
		return super.get(id);
	}
	
	public List<MovieConsumerComments> findList(MovieConsumerComments movieConsumerComments) {
		return super.findList(movieConsumerComments);
	}
	
	public Page<MovieConsumerComments> findPage(Page<MovieConsumerComments> page, MovieConsumerComments movieConsumerComments) {
		return super.findPage(page, movieConsumerComments);
	}
	
	@Transactional(readOnly = false)
	public void save(MovieConsumerComments movieConsumerComments) {
		super.save(movieConsumerComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(MovieConsumerComments movieConsumerComments) {
		super.delete(movieConsumerComments);
	}

	public List<MovieConsumerComments> getMovieComments(String movieId) {
		MovieConsumerComments param=new MovieConsumerComments();
		param.setMovieId(movieId);
		return movieConsumerCommentsDao.getMovieComments(param);
	}
	
}