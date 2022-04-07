package com.webservices.contentmediaapi.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;

import lombok.Data;

@Data
@Document
public class Episode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String title;
	
	private Integer number;
	
	private String description;
	
	private Integer duration;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	
	public String getId() {
		return id;
	}

	public Episode() {
		
	}
	
	public Episode(ObjectNode json) {
		id = UUID.randomUUID().toString();
		updateData(json);
	}
	
	public void updateData(ObjectNode json) {
		title = JsonNodeUtil.getJsonNodeAsText(json,"title");
		description = JsonNodeUtil.getJsonNodeAsText(json,"description");
		number = JsonNodeUtil.getJsonNodeAsInteger(json,"number");
		duration = JsonNodeUtil.getJsonNodeAsInteger(json,"duration");
	}
}
