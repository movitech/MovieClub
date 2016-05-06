/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.tag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.tag.entity.MovieTag;
import com.thinkgem.jeesite.movieclub.tag.dao.MovieTagDao;

/**
 * tagService
 * @author marvin.ma
 * @version 2015-10-15
 */
@Service
@Transactional(readOnly = true)
public class MovieTagService extends CrudService<MovieTagDao, MovieTag> {

	public MovieTag get(String id) {
		return super.get(id);
	}
	
	public List<MovieTag> findList(MovieTag movieTag) {
		return super.findList(movieTag);
	}
	
	public Page<MovieTag> findPage(Page<MovieTag> page, MovieTag movieTag) {
		return super.findPage(page, movieTag);
	}
	
	@Transactional(readOnly = false)
	public void save(MovieTag movieTag) {
		super.save(movieTag);
	}
	
	@Transactional(readOnly = false)
	public void delete(MovieTag movieTag) {
		super.delete(movieTag);
	}
	
}