/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.consumer_role.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.jeesite.common.persistence.TreeEntity;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * consumer_roleEntity
 * @author marvin.ma
 * @version 2015-10-19
 */
public class ConsumerRole  extends TreeEntity<ConsumerRole> {
	
	private static final long serialVersionUID = 1L;
	//private ConsumerRole parent;	// parent_role_id
	private String roleName;		// role_name
	private String description;		// description
	
	public ConsumerRole() {
		super();
	}

	public ConsumerRole(String id){
		super(id);
	}

	@JsonBackReference
	public ConsumerRole getParent() {
		return parent;
	}

	public void setParent(ConsumerRole parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=100, message="role_name长度必须介于 1 和 100 之间")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Length(min=0, max=255, message="description长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}