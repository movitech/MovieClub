/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.bucket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.bucket.entity.MovieBucket;
import com.thinkgem.jeesite.movieclub.bucket.dao.MovieBucketDao;

/**
 * BucketService
 * @author eric.wang
 * @version 2015-10-16
 */
@Service
@Transactional(readOnly = true)
public class MovieBucketService extends CrudService<MovieBucketDao, MovieBucket> {

	@Autowired
	private MovieBucketDao movieBucketDao;

	public MovieBucket get(String id) {
		return super.get(id);
	}
	
	public List<MovieBucket> findList(MovieBucket movieBucket) {
		return super.findList(movieBucket);
	}
	
	public Page<MovieBucket> findPage(Page<MovieBucket> page, MovieBucket movieBucket) {
		return super.findPage(page, movieBucket);
	}
	
	@Transactional(readOnly = false)
	public void save(MovieBucket movieBucket) {
		super.save(movieBucket);
	}
	
	@Transactional(readOnly = false)
	public void delete(MovieBucket movieBucket) {
		super.delete(movieBucket);
	}

	public MovieBucket getByMovieIdAndUserId(String movieId,String ConsumerId) {
		MovieBucket param = new MovieBucket();
		param.setConsumerId(ConsumerId);
		param.setMovieId(movieId);
		return movieBucketDao.getByMovieIdAndUserId(param);
	}

	public List<MovieBucket> getMyBucket(String ConsumerId) {
		MovieBucket param = new MovieBucket();
		param.setConsumerId(ConsumerId);
		return movieBucketDao.getMyBucket(param);
	}
	
}