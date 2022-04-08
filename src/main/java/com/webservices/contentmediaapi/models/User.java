package com.webservices.contentmediaapi.models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {
    @Id
    private String id;
    private String username;
    private String country;
    private String status;

    public User() {

    }

    public User(ObjectNode json) {
        id = JsonNodeUtil.getJsonNodeAsText(json, "id");
        username = JsonNodeUtil.getJsonNodeAsText(json, "username");
        country = JsonNodeUtil.getJsonNodeAsText(json, "country");
        status = JsonNodeUtil.getJsonNodeAsText(json, "accountStatus");
    }
}
