/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.recommendation.service;

import java.util.List;

import com.thinkgem.jeesite.movieclub.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.recommendation.entity.Recommendation;
import com.thinkgem.jeesite.movieclub.recommendation.dao.RecommendationDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * RecommendationService
 * @author eric.wang
 * @version 2015-10-13
 */
@Service
@Transactional(readOnly = true)
public class RecommendationService extends CrudService<RecommendationDao, Recommendation> {

	@Autowired
	private RecommendationDao recommendationDao;

	public Recommendation get(String id) {
		return super.get(id);
	}

	public Recommendation getByMovieId(String movieId) {
		return recommendationDao.getByMovieId(movieId);
	}

	public List<Recommendation> findList(Recommendation recommendation) {
		return super.findList(recommendation);
	}
	
	public Page<Recommendation> findPage(Page<Recommendation> page, Recommendation recommendation) {
		return super.findPage(page, recommendation);
	}
	
	@Transactional(readOnly = false)
	public void save(Recommendation recommendation) {
		super.save(recommendation);
	}
	
	@Transactional(readOnly = false)
	public void delete(Recommendation recommendation) {
		super.delete(recommendation);
	}

}