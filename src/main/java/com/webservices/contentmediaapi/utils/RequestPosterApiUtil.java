package com.webservices.contentmediaapi.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class RequestPosterApiUtil {

	private static RestTemplate restTemplate  = new RestTemplate();
	private static String urlBase = "http://localhost:8083"; 
	
	public static ResponseEntity<ObjectNode> getPosterByContentMedia(String contentId, String timeOfDay) {
		String url = urlBase + "/poster/byContent-media/"+ contentId + "/"+ timeOfDay;
		return restTemplate.postForEntity(url,null,ObjectNode.class);
	} 
}
