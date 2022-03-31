package com.webservices.contentmediaapi.models;

import lombok.Data;

@Data
public class ContentMediaResponse<T extends ContentMedia> {

	private T content;
	
	private boolean isOk;
	
	private String message;

	public ContentMediaResponse() {
		
	}

	public ContentMediaResponse(boolean isOk) {
		this.isOk = isOk;
	}
	
	public ContentMediaResponse(boolean isOk, T content) {
		this.isOk = isOk;
		this.content = content;
	}
	
	public ContentMediaResponse(boolean isOk, T content, String message) {
		this.isOk = isOk;
		this.content = content;
		this.message = message;
	}
	
	public ContentMediaResponse(boolean isOk, String message) {
		this.isOk = isOk;
		this.message = message;
	}
}
