package com.webservices.contentmediaapi.models;

import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;

import lombok.Data;

@Data
@Document
@PrimaryKeyJoinColumn(name = "id")
public class Movie extends ContentMedia {

	private Integer duration;

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public Movie(ObjectNode json) {
		super(json,"movie");
		duration = JsonNodeUtil.getJsonNodeAsInteger(json, "duration");
	}
	
	public Movie() {
		super();
	}
	
}
