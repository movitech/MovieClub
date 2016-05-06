/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.bucket.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * BucketEntity
 * @author eric.wang
 * @version 2015-10-16
 */
public class MovieBucket extends DataEntity<MovieBucket> {
	
	private static final long serialVersionUID = 1L;
	private String movieId;		// movie_id
	private String consumerId;		// consumer_id
	
	public MovieBucket() {
		super();
	}

	public MovieBucket(String id){
		super(id);
	}

	@Length(min=1, max=64, message="movie_id长度必须介于 1 和 64 之间")
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	@Length(min=1, max=64, message="consumer_id长度必须介于 1 和 64 之间")
	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	
}