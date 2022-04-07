package com.webservices.contentmediaapi.models;

import java.util.List;

import lombok.Data;

@Data
public class ListEpisodeResponse {
	private List<Episode> content;
	
	private boolean isOk;
	
	private String message;

	public ListEpisodeResponse() {
		
	}

	public ListEpisodeResponse(boolean isOk) {
		this.isOk = isOk;
	}
	
	public ListEpisodeResponse(boolean isOk, List<Episode> content) {
		this.isOk = isOk;
		this.content = content;
	}
	
	public ListEpisodeResponse(boolean isOk, List<Episode> content, String message) {
		this.isOk = isOk;
		this.content = content;
		this.message = message;
	}
	
	public ListEpisodeResponse(boolean isOk, String message) {
		this.isOk = isOk;
		this.message = message;
	}
}
