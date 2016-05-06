/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.contact.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.movieclub.contact.entity.Contact;

/**
 * ContactDAO接口
 * @author eric.wang
 * @version 2015-10-15
 */
@MyBatisDao
public interface ContactDao extends CrudDao<Contact> {
	
}