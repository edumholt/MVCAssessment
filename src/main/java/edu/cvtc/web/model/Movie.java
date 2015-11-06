package edu.cvtc.web.model;

import java.util.List;

/**
 * @author edumholt
 *
 */
public class Movie {
	
	private Integer id;
	private String title;
	private String director;
	private Integer minutes;
	private List<Star> stars;
	
	public Movie(final int id, final String title, final String director, final int minutes, final List<Star> stars) {
		this.id = id;
		this.title = title;
		this.director = director;
		this.minutes = minutes;
		this.stars = stars;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(final String director) {
		this.director = director;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(final Integer minutes) {
		this.minutes = minutes;
	}
	public List<Star> getStars() {
		return stars;
	}
	public void setStars(final List<Star> stars) {
		this.stars = stars;
	}
	
}
