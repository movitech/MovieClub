/*
 * Copyright (C) 2015 SEC BFO, Inc. All Rights Reserved.
 */
package com.thinkgem.jeesite.movieclub.vo;

public class BucketVO
{
	private String movieId;		// movie_id
	private String consumerId;		// consumer_id
	private String title;
	private String movieImage;

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

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getMovieImage()
	{
		return movieImage;
	}

	public void setMovieImage(String movieImage)
	{
		this.movieImage = movieImage;
	}
}
