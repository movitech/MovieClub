/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.permission.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * permissionEntity
 * @author marvin.ma
 * @version 2015-10-19
 */
public class Permission extends DataEntity<Permission> {
	
	private static final long serialVersionUID = 1L;
	private String permissionName;		// permission_name
	private String description;		// description
	
	public Permission() {
		super();
	}

	public Permission(String id){
		super(id);
	}

	@Length(min=0, max=64, message="permission_name长度必须介于 0 和 64 之间")
	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	@Length(min=0, max=255, message="description长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}