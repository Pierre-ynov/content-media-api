package com.webservices.contentmediaapi.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;

import lombok.Data;
@Data
@Document
@Inheritance(strategy = InheritanceType.JOINED)
public class ContentMedia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@Field(name = "_id")
	private String id;
	
	private String title;
	
	private String url;
	
	private String category;
	
	private String globalDescription;
	
	private boolean isActive;
	
	private String countryAccepted;
	
	private String urlPoster;
	
	private String genre;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGlobalDescription() {
		return globalDescription;
	}

	public void setGlobalDescription(String globalDescription) {
		this.globalDescription = globalDescription;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCountryAccepted() {
		return countryAccepted;
	}

	public void setCountryAccepted(String countryAccepted) {
		this.countryAccepted = countryAccepted;
	}
	
	public String getUrlPoster() {
		return urlPoster;
	}

	public void setUrlPoster(String urlPoster) {
		this.urlPoster = urlPoster;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public ContentMedia() {
		
	}
	
	public ContentMedia(ObjectNode json, String genre) {
		id = UUID.randomUUID().toString();
		isActive = true;
		this.genre = genre;
		updateData(json);
	}

	public void updateData(ObjectNode json) {
		title = JsonNodeUtil.getJsonNodeAsText(json,"title");
		url = JsonNodeUtil.getJsonNodeAsText(json,"url");
		category = JsonNodeUtil.getJsonNodeAsText(json,"category");
		globalDescription = JsonNodeUtil.getJsonNodeAsText(json,"globalDescription");
		countryAccepted = JsonNodeUtil.getJsonNodeAsText(json,"countryAccepted");
	}
}
