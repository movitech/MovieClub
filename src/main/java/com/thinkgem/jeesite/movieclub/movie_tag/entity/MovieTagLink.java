/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.movie_tag.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * movie_tagEntity
 * @author marvin.ma
 * @version 2015-10-16
 */
public class MovieTagLink extends DataEntity<MovieTagLink> {
	
	private static final long serialVersionUID = 1L;
	private String tagId;		// tag_id
	private String movieId;		// movie_id
	
	public MovieTagLink() {
		super();
	}

	public MovieTagLink(String id){
		super(id);
	}

	@Length(min=1, max=64, message="tag_id长度必须介于 1 和 64 之间")
	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	
	@Length(min=1, max=64, message="movie_id长度必须介于 1 和 64 之间")
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
}