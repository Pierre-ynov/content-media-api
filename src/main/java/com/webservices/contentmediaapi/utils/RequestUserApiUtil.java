package com.webservices.contentmediaapi.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class RequestUserApiUtil {
    @Autowired
    private static RestTemplate restTemplate;

    private static String urlBase = "http://localhost:8082/";

    public static ResponseEntity<ObjectNode> getUserById(String userId) {
        String url = urlBase + "user/" + userId;

        try {
            return restTemplate.getForEntity(new URI(url), ObjectNode.class);
        } catch (URISyntaxException uriExc) {
            System.out.println(uriExc.getMessage());
            return null;
        }
    }
}
