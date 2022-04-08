package com.webservices.contentmediaapi.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class RequestUserApiUtil {
    private static RestTemplate restTemplate = new RestTemplate();
    private static String urlBase = "http://localhost:8082/";

    public static ResponseEntity<ObjectNode> getUserById(String userId) {
        String url = urlBase + "user/" + userId;
        try {
            return restTemplate.getForEntity(new URI(url), ObjectNode.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }
}
