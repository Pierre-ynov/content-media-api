package com.webservices.contentmediaapi.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestPosterApiUtil {

    private static RestTemplate restTemplate = new RestTemplate();
    private static String urlBase = "http://localhost:8083";

    public static ResponseEntity<ObjectNode> getPosterByContentMedia(String contentId, String timeOfDay) {
        String url = urlBase + "/poster/byContentId/" + contentId + "/" + timeOfDay;
        return restTemplate.postForEntity(url, null, ObjectNode.class);
    }
}
