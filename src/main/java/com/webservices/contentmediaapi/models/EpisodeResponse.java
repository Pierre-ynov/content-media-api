package com.webservices.contentmediaapi.models;

import lombok.Data;

@Data
public class EpisodeResponse {
	private Episode content;
	
	private boolean isOk;
	
	private String message;

	public EpisodeResponse() {
		
	}

	public EpisodeResponse(boolean isOk) {
		this.isOk = isOk;
	}
	
	public EpisodeResponse(boolean isOk, Episode content) {
		this.isOk = isOk;
		this.content = content;
	}
	
	public EpisodeResponse(boolean isOk, Episode content, String message) {
		this.isOk = isOk;
		this.content = content;
		this.message = message;
	}
	
	public EpisodeResponse(boolean isOk, String message) {
		this.isOk = isOk;
		this.message = message;
	}
}
