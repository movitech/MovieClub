/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.contact.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * ContactEntity
 * @author eric.wang
 * @version 2015-10-15
 */
public class Contact extends DataEntity<Contact> {
	
	private static final long serialVersionUID = 1L;
	private String objType;		// obj_type
	private String name;		// name
	private String company;		// company
	private String email;		// email
	private String phone;		// phone
	private String consumerId;

	public Contact() {
		super();
	}

	public Contact(String id){
		super(id);
	}

	@Length(min=0, max=10, message="obj_type长度必须介于 0 和 10 之间")
	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}
	
	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="company长度必须介于 0 和 255 之间")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Length(min=0, max=64, message="phone长度必须介于 0 和 64 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getConsumerId()
	{
		return consumerId;
	}

	public void setConsumerId(String consumerId)
	{
		this.consumerId = consumerId;
	}
}