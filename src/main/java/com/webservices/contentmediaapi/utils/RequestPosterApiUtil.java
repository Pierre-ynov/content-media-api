package com.webservices.contentmediaapi.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class RequestPosterApiUtil {

	private static RestTemplate restTemplate  = new RestTemplate();
	private static String urlBase = "http://localhost:8083"; 
	
	public static ResponseEntity<ObjectNode> getPosterByContentMedia(Integer contentId, boolean isMorning) {
		String url = urlBase + "/poster/content-media/"+ contentId;
		return restTemplate.postForEntity(url,isMorning,ObjectNode.class);
	} 
}
