/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.genre.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * movie genreEntity
 * @author eric.wang
 * @version 2015-10-12
 */
public class MovieGenre extends DataEntity<MovieGenre> {
	
	private static final long serialVersionUID = 1L;
	private String genreName;		// Genre Title
	private String genreImage;		// Filename of Genre poster
	private String genreImageTitle;		// Title of featured genre movie
	private String sortOrder;		// Change the order of menu on front end
	private String status;		// 1 - published, 2 - unpublished
	
	public MovieGenre() {
		super();
	}

	public MovieGenre(String id){
		super(id);
	}

	@Length(min=1, max=255, message="Genre Title长度必须介于 1 和 255 之间")
	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	@Length(min=0, max=255, message="Filename of Genre poster长度必须介于 0 和 255 之间")
	public String getGenreImage() {
		return genreImage;
	}

	public void setGenreImage(String genreImage) {
		this.genreImage = genreImage;
	}
	
	@Length(min=0, max=255, message="Title of featured genre movie长度必须介于 0 和 255 之间")
	public String getGenreImageTitle() {
		return genreImageTitle;
	}

	public void setGenreImageTitle(String genreImageTitle) {
		this.genreImageTitle = genreImageTitle;
	}
	
	@Length(min=0, max=6, message="Change the order of menu on front end长度必须介于 0 和 6 之间")
	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Length(min=0, max=1, message="1 - published, 2 - unpublished长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}