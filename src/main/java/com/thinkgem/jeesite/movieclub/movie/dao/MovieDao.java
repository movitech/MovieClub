/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.movie.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.movieclub.movie.entity.Movie;
import java.util.List;

/**
 * movieDAO接口
 * @author marvin.ma
 * @version 2015-10-15
 */
@MyBatisDao
public interface MovieDao extends CrudDao<Movie> {
    public List<Movie> getMovieListWithoutRecommendation (Movie movie);
}