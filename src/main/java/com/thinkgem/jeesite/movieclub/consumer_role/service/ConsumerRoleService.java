/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.consumer_role.service;

import java.util.List;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.consumer_role.entity.ConsumerRole;
import com.thinkgem.jeesite.movieclub.consumer_role.dao.ConsumerRoleDao;

/**
 * consumer_roleService
 * @author marvin.ma
 * @version 2015-10-19
 */
@Service
@Transactional(readOnly = true)
public class ConsumerRoleService extends CrudService<ConsumerRoleDao, ConsumerRole> {

	public ConsumerRole get(String id) {
		return super.get(id);
	}
	
	public List<ConsumerRole> findList(ConsumerRole consumerRole) {
		return super.findList(consumerRole);
	}

	public Page<ConsumerRole> findPage(Page<ConsumerRole> page, ConsumerRole consumerRole) {
		return super.findPage(page, consumerRole);
	}
	
	@Transactional(readOnly = false)
	public void save(ConsumerRole consumerRole) {
		super.save(consumerRole);
	}
	
	@Transactional(readOnly = false)
	public void delete(ConsumerRole consumerRole) {
		super.delete(consumerRole);
	}
	
}