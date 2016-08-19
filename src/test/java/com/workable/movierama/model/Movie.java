package com.workable.movierama.model;

import java.util.List;

public class Movie {
	private String title;
	private List<String> actors;
	private String description;
	private String review;
	private String starringActorsStr;

	public String getTitle() {
		return title;
	}

	public List<String> getActors() {
		return actors;
	}

	public String getDescription() {
		return description;
	}

	public String getReview() {
		return review;
	}

	public String getStarringActorsStr() {
		return starringActorsStr;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public void setStarringActorsStr(String starringActorsStr) {
		this.starringActorsStr = starringActorsStr;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", actors=" + actors + ", description=" + description + ", review=" + review
				+ ", starringActorsStr=" + starringActorsStr + "]";
	}

}
