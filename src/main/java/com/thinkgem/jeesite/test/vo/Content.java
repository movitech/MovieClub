/*
 * Copyright (C) 2015 SEC BFO, Inc. All Rights Reserved.
 */
package com.thinkgem.jeesite.test.vo;

import java.util.Date;

public class Content
{
	private String android_update_id;
	private String version_name;
	private String version_code;
	private String file;
	private String date_created;

	public String getAndroid_update_id()
	{
		return android_update_id;
	}

	public void setAndroid_update_id(String android_update_id)
	{
		this.android_update_id = android_update_id;
	}

	public String getVersion_name()
	{
		return version_name;
	}

	public void setVersion_name(String version_name)
	{
		this.version_name = version_name;
	}

	public String getVersion_code()
	{
		return version_code;
	}

	public void setVersion_code(String version_code)
	{
		this.version_code = version_code;
	}

	public String getFile()
	{
		return file;
	}

	public void setFile(String file)
	{
		this.file = file;
	}

	public String getDate_created()
	{
		return date_created;
	}

	public void setDate_created(String date_created)
	{
		this.date_created = date_created;
	}
}
