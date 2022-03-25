package com.webservices.contentmediaapi.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;

import lombok.Data;
@Data
public class ContentMedia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private String title;
	
	private String url;
	
	private String category;
	
	private String globalDescription;
	
	private boolean isActive;
	
	private String countryAccepted;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	public ContentMedia() {
		
	}
	
	public ContentMedia(ObjectNode json) {
		id = JsonNodeUtil.getJsonNodeAsInteger(json,"id");
		title = JsonNodeUtil.getJsonNodeAsText(json,"title");
		url = JsonNodeUtil.getJsonNodeAsText(json,"url");
		category =JsonNodeUtil.getJsonNodeAsText(json,"category");
		isActive = JsonNodeUtil.getJsonNodeAsBoolean(json,"isActive");
		globalDescription = JsonNodeUtil.getJsonNodeAsText(json,"globalDescription");
		countryAccepted = JsonNodeUtil.getJsonNodeAsText(json,"countryAccepted");
	}
}
