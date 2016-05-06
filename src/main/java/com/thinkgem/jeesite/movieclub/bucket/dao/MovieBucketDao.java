/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.bucket.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.movieclub.bucket.entity.MovieBucket;

import java.util.List;

/**
 * BucketDAO接口
 * @author eric.wang
 * @version 2015-10-16
 */
@MyBatisDao
public interface MovieBucketDao extends CrudDao<MovieBucket> {

	public MovieBucket getByMovieIdAndUserId(MovieBucket param);

	public List<MovieBucket> getMyBucket(MovieBucket param);
	
}