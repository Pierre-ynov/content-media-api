package com.webservices.contentmediaapi.models;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;

import lombok.Data;

@Data
public class User {
	@Id
	private Integer id;
	
	private String username;
	
	private String country;
	
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User() {
		
	}
	
	public User(ObjectNode json) {
		id = JsonNodeUtil.getJsonNodeAsInteger(json,"id");
		username = JsonNodeUtil.getJsonNodeAsText(json,"username");
		country = JsonNodeUtil.getJsonNodeAsText(json,"country");
		status = JsonNodeUtil.getJsonNodeAsText(json,"status");
	}
	
}
