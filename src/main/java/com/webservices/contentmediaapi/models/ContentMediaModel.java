package com.webservices.contentmediaapi.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public class ContentMediaModel implements Serializable{

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
}
