/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.sysconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * sysconfigEntity
 * @author eric.wang
 * @version 2015-10-14
 */
public class SysConf extends DataEntity<SysConf> {
	
	private static final long serialVersionUID = 1L;
	private String key;		// key
	private String value;		// value
	private String description;		// description
	
	public SysConf() {
		super();
	}

	public SysConf(String id){
		super(id);
	}

	@Length(min=1, max=100, message="key长度必须介于 1 和 100 之间")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@Length(min=1, max=255, message="value长度必须介于 1 和 255 之间")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=1, max=255, message="description长度必须介于 1 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}