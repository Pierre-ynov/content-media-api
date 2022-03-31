package com.webservices.contentmediaapi.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class RequestUserApiUtil {

	private static RestTemplate restTemplate  = new RestTemplate();
	private static String urlBase = "http://localhost:8082"; 
	
	public static ResponseEntity<ObjectNode> getUserById(Integer userId) {
		String url = urlBase + "/user/" + userId;
		return restTemplate.getForEntity(url, null,ObjectNode.class);
	} 
}
