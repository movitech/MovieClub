/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.comment.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * commentEntity
 * @author eric.wang
 * @version 2015-10-16
 */
public class MovieConsumerComments extends DataEntity<MovieConsumerComments> {
	
	private static final long serialVersionUID = 1L;
	private String parentCommentId;		// parent_comment_id
	private String movieId;		// movie_id
	private String consumerId;		// consumer_id
	private String commentDescription;		// comment_description

	private String firstName;
	private String lastName;
	private String nickName;
	
	public MovieConsumerComments() {
		super();
	}

	public MovieConsumerComments(String id){
		super(id);
	}

	@Length(min=1, max=64, message="parent_comment_id长度必须介于 1 和 64 之间")
	public String getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(String parentCommentId) {
		this.parentCommentId = parentCommentId;
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
	
	public String getCommentDescription() {
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
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
}