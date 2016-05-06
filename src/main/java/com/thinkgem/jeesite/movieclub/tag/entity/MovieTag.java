/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.tag.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * tagEntity
 * @author marvin.ma
 * @version 2015-10-15
 */
public class MovieTag extends DataEntity<MovieTag> {
	
	private static final long serialVersionUID = 1L;
	private String tagTitle;		// tag_title
	
	public MovieTag() {
		super();
	}

	public MovieTag(String id){
		super(id);
	}

	@Length(min=1, max=64, message="tag_title长度必须介于 1 和 64 之间")
	public String getTagTitle() {
		return tagTitle;
	}

	public void setTagTitle(String tagTitle) {
		this.tagTitle = tagTitle;
	}
	
}