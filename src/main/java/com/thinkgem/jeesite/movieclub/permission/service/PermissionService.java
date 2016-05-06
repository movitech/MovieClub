/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.permission.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.permission.entity.Permission;
import com.thinkgem.jeesite.movieclub.permission.dao.PermissionDao;

/**
 * permissionService
 * @author marvin.ma
 * @version 2015-10-19
 */
@Service
@Transactional(readOnly = true)
public class PermissionService extends CrudService<PermissionDao, Permission> {

	public Permission get(String id) {
		return super.get(id);
	}
	
	public List<Permission> findList(Permission permission) {
		return super.findList(permission);
	}
	
	public Page<Permission> findPage(Page<Permission> page, Permission permission) {
		return super.findPage(page, permission);
	}
	
	@Transactional(readOnly = false)
	public void save(Permission permission) {
		super.save(permission);
	}
	
	@Transactional(readOnly = false)
	public void delete(Permission permission) {
		super.delete(permission);
	}
	
}