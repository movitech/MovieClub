/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.sysconfig.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.movieclub.sysconfig.entity.SysConf;

import java.util.Map;

/**
 * sysconfigDAO接口
 *
 * @author eric.wang
 * @version 2015-10-14
 */
@MyBatisDao
public interface SysConfDao extends CrudDao<SysConf> {
	public Map<String, String> loadConfigMap() ;
}