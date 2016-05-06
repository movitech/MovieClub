/*
 * Copyright (C) 2015 SEC BFO, Inc. All Rights Reserved.
 */
package com.thinkgem.jeesite.movieclub.vo;

import java.util.Date;

public class CommentVO
{
	private String parentCommentId;		// parent_comment_id
	private String movieId;		// movie_id
	private String consumerId;		// consumer_id
	private String commentDescription;		// comment_description

	private String firstName;
	private String lastName;
	private String nickName;
	private Date createDate;

	public String getParentCommentId()
	{
		return parentCommentId;
	}

	public void setParentCommentId(String parentCommentId)
	{
		this.parentCommentId = parentCommentId;
	}

	public String getMovieId()
	{
		return movieId;
	}

	public void setMovieId(String movieId)
	{
		this.movieId = movieId;
	}

	public String getConsumerId()
	{
		return consumerId;
	}

	public void setConsumerId(String consumerId)
	{
		this.consumerId = consumerId;
	}

	public String getCommentDescription()
	{
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription)
	{
		this.commentDescription = commentDescription;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
}
