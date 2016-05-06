/*
 * Copyright (C) 2015 SEC BFO, Inc. All Rights Reserved.
 */
package com.thinkgem.jeesite.movieclub.vo;

public class ContactVO
{
	private String objType;		// obj_type
	private String name;		// name
	private String company;		// company
	private String email;		// email
	private String phone;		// phone
	private String comments;

	public String getObjType()
	{
		return objType;
	}

	public void setObjType(String objType)
	{
		this.objType = objType;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
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

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}
}
