/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.recommendation.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.movieclub.recommendation.entity.Recommendation;

/**
 * RecommendationDAO接口
 * @author eric.wang
 * @version 2015-10-13
 */
@MyBatisDao
public interface RecommendationDao extends CrudDao<Recommendation> {
    public Recommendation getByMovieId(String movieId);

}