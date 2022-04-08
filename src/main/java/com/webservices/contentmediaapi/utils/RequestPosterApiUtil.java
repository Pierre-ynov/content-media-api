package com.webservices.contentmediaapi.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class RequestPosterApiUtil {
    private static RestTemplate restTemplate = new RestTemplate();
    private static String urlBase = "http://localhost:8083/";

    public static ResponseEntity<ObjectNode> getPosterByContentMedia(String contentId, String timeOfDay) {
        String url = urlBase + "poster/byContentId/" + contentId + "/" + timeOfDay;
        try {
            return restTemplate.getForEntity(new URI(url), ObjectNode.class);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println("Aucun poster trouvé pour ce média");
        }
        return ResponseEntity.noContent().build();
    }
}
