/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.movie.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * movieEntity
 * @author marvin.ma
 * @version 2015-10-15
 */
public class Movie extends DataEntity<Movie> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// title
	private String genre;		// movie genre
	private String genreName;		// movie genre
	private List<String> tags;		// movie tags
	private String type;		// 1 - Free movie, 2 - Premium movie
	private String category;	// Points to movie_category_ID field of movie_category table
	private String price;		// Price of movie if premium
	private String hangoutPrice;		// Price when created for hangout viewing
	private String image;		// Movie Poster
	private String videoStream;		// Name of smil file used for video streaming on website
	private String videoStreamHlsDir;		// Folder location of stream files used for video streaming on mobile app
	private String yearRelease;		// year_release
	private String restriction;		// Movie rating. PG13, PG18
	private String ageRequirement;		// Age required to view the movie
	private String videoTime;		// Length of the movie
	private String studio;		// studio
	private String legal;		// legal
	private String plot;		// plot
	private String starring;		// starring
	private String director;		// director
	private String screenwriter;		// screenwriter
	private String producer;		// producer
	private String originalSoundTrack;		// original_sound_track
	private String castAndCrew;		// cast_and_crew
	private Long pageViewCount;		// Number of time the Movie page has loaded.
	private Long videoWatchCount;		// Number of time movie has been played
	private Long fiveStarsCount;		// Number of
	private Long fourStarsCount;		// Number of
	private Long threeStarsCount;		// Number of
	private Long twoStarsCount;		// Number of
	private Long oneStarsCount;		// Number of
	private Long sortOrder;		// Used in backend only to change the order the movies in the backend.
	private String status;		// 1 - enabled, 0 - disabled
	
	public Movie() {
		super();
	}

	public Movie(String id){
		super(id);
	}

	@Length(min=1, max=255, message="The length of movie name should within the range of 1-255")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=64, message="Please select the movie genre.")
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	//@Length(min=1, max=1024, message="Move Tags 长度必须介于 1 和 1024 之间")
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Length(min=0, max=11, message="1 - Free movie, 2 - Premium movie长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=4, message="Points to movie_category_ID field of movie_category table长度必须介于 0 和 4 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getHangoutPrice() {
		return hangoutPrice;
	}

	public void setHangoutPrice(String hangoutPrice) {
		this.hangoutPrice = hangoutPrice;
	}
	
	@Length(min=0, max=255, message="Movie Poster长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=255, message="Name of smil file used for video streaming on website长度必须介于 0 和 255 之间")
	public String getVideoStream() {
		return videoStream;
	}

	public void setVideoStream(String videoStream) {
		this.videoStream = videoStream;
	}
	
	@Length(min=0, max=255, message="Folder location of stream files used for video streaming on mobile app长度必须介于 0 和 255 之间")
	public String getVideoStreamHlsDir() {
		return videoStreamHlsDir;
	}

	public void setVideoStreamHlsDir(String videoStreamHlsDir) {
		this.videoStreamHlsDir = videoStreamHlsDir;
	}
	
	@Length(min=0, max=4, message="year_release长度必须介于 0 和 4 之间")
	public String getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(String yearRelease) {
		this.yearRelease = yearRelease;
	}
	
	@Length(min=0, max=4, message="Movie rating. PG13, PG18 长度必须介于 0 和 255 之间")
	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}
	
	@Length(min=0, max=11, message="Age required to view the movie长度必须介于 0 和 11 之间")
	public String getAgeRequirement() {
		return ageRequirement;
	}

	public void setAgeRequirement(String ageRequirement) {
		this.ageRequirement = ageRequirement;
	}
	
	@Length(min=0, max=255, message="Length of the movie长度必须介于 0 和 255 之间")
	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	
	@Length(min=0, max=255, message="studio长度必须介于 0 和 255 之间")
	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}
	
	@Length(min=0, max=11, message="legal长度必须介于 0 和 11 之间")
	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}
	
	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}
	
	@Length(min=0, max=255, message="starring长度必须介于 0 和 255 之间")
	public String getStarring() {
		return starring;
	}

	public void setStarring(String starring) {
		this.starring = starring;
	}
	
	@Length(min=0, max=255, message="director长度必须介于 0 和 255 之间")
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	@Length(min=0, max=255, message="screenwriter长度必须介于 0 和 255 之间")
	public String getScreenwriter() {
		return screenwriter;
	}

	public void setScreenwriter(String screenwriter) {
		this.screenwriter = screenwriter;
	}
	
	@Length(min=0, max=255, message="producer长度必须介于 0 和 255 之间")
	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public String getOriginalSoundTrack() {
		return originalSoundTrack;
	}

	public void setOriginalSoundTrack(String originalSoundTrack) {
		this.originalSoundTrack = originalSoundTrack;
	}
	
	public String getCastAndCrew() {
		return castAndCrew;
	}

	public void setCastAndCrew(String castAndCrew) {
		this.castAndCrew = castAndCrew;
	}
	
	public Long getPageViewCount() {
		return pageViewCount;
	}

	public void setPageViewCount(Long pageViewCount) {
		this.pageViewCount = pageViewCount;
	}
	
	public Long getVideoWatchCount() {
		return videoWatchCount;
	}

	public void setVideoWatchCount(Long videoWatchCount) {
		this.videoWatchCount = videoWatchCount;
	}
	
	public Long getFiveStarsCount() {
		return fiveStarsCount;
	}

	public void setFiveStarsCount(Long fiveStarsCount) {
		this.fiveStarsCount = fiveStarsCount;
	}
	
	public Long getFourStarsCount() {
		return fourStarsCount;
	}

	public void setFourStarsCount(Long fourStarsCount) {
		this.fourStarsCount = fourStarsCount;
	}
	
	public Long getThreeStarsCount() {
		return threeStarsCount;
	}

	public void setThreeStarsCount(Long threeStarsCount) {
		this.threeStarsCount = threeStarsCount;
	}
	
	public Long getTwoStarsCount() {
		return twoStarsCount;
	}

	public void setTwoStarsCount(Long twoStarsCount) {
		this.twoStarsCount = twoStarsCount;
	}
	
	public Long getOneStarsCount() {
		return oneStarsCount;
	}

	public void setOneStarsCount(Long oneStarsCount) {
		this.oneStarsCount = oneStarsCount;
	}
	
	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Length(min=0, max=1, message="1 - enabled, 0 - disabled长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGenreName()
	{
		return genreName;
	}

	public void setGenreName(String genreName)
	{
		this.genreName = genreName;
	}
}