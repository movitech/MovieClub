/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.recommendation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.movieclub.movie.entity.Movie;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * RecommendationEntity
 * @author eric.wang
 * @version 2015-10-13
 */
public class Recommendation extends DataEntity<Recommendation> {
	
	private static final long serialVersionUID = 1L;
	//private String movieId;		//movie_id
	private Movie movie;			//movie
	private String sortOrder;		//sort_order
	
	public Recommendation() {
		super();
	}

	public Recommendation(String id){
		super(id);
	}

	//
	//@Length(min=1, max=64, message="movie_id长度必须介于 1 和 64 之间")
	//public String getMovieId() {
	//	return movieId;
	//}
	//
	//public void setMovieId(String movieId) {
	//	this.movieId = movieId;
	//}
	//

	@JsonIgnore
	@NotNull(message="电影信息不能为空")
	@ExcelField(title="电影名称", align=2, sort=25)
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Length(min=0, max=6, message="The sortOrder 长度必须介于 0 和 6 之间")
	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}