/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.consumer_role.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.movieclub.consumer_role.entity.ConsumerRole;

/**
 * consumer_roleDAO接口
 * @author marvin.ma
 * @version 2015-10-19
 */
@MyBatisDao
public interface ConsumerRoleDao extends CrudDao<ConsumerRole> {
	
}