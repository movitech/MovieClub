/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.movie_tag.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.movieclub.movie_tag.entity.MovieTagLink;

/**
 * movie_tagDAO接口
 * @author marvin.ma
 * @version 2015-10-16
 */
@MyBatisDao
public interface MovieTagLinkDao extends CrudDao<MovieTagLink> {
	
}