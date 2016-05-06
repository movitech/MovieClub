/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.comment.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.movieclub.comment.entity.MovieConsumerComments;

import java.util.List;

/**
 * commentDAO接口
 * @author eric.wang
 * @version 2015-10-16
 */
@MyBatisDao
public interface MovieConsumerCommentsDao extends CrudDao<MovieConsumerComments> {

	public List<MovieConsumerComments> getMovieComments(MovieConsumerComments param);
}